package org.bouncycastle.jsse.provider;

import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.interfaces.DSAKey;
import java.security.interfaces.DSAParams;
import java.security.interfaces.ECKey;
import java.security.interfaces.RSAKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHKey;
import javax.crypto.spec.DHParameterSpec;
import org.bouncycastle.jsse.java.security.BCCryptoPrimitive;
/* loaded from: classes3.dex */
class DisabledAlgorithmConstraints extends AbstractAlgorithmConstraints {
    private static final String INCLUDE_PREFIX = "include ";
    private static final String KEYWORD_KEYSIZE = "keySize";
    private static final Logger LOG = Logger.getLogger(DisabledAlgorithmConstraints.class.getName());
    private final Map<String, List<Constraint>> constraintsMap;
    private final Set<String> disabledAlgorithms;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.bouncycastle.jsse.provider.DisabledAlgorithmConstraints$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f13871a;

        static {
            int[] iArr = new int[BinOp.values().length];
            f13871a = iArr;
            try {
                iArr[BinOp.EQ.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13871a[BinOp.GE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f13871a[BinOp.GT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f13871a[BinOp.LE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f13871a[BinOp.LT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f13871a[BinOp.NE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public enum BinOp {
        EQ("=="),
        GE(">="),
        GT(">"),
        LE("<="),
        LT("<"),
        NE("!=");
        

        /* renamed from: s  reason: collision with root package name */
        private final String f13872s;

        BinOp(String str) {
            this.f13872s = str;
        }

        static boolean a(BinOp binOp, int i2, int i3) {
            switch (AnonymousClass1.f13871a[binOp.ordinal()]) {
                case 1:
                    return i2 == i3;
                case 2:
                    return i2 >= i3;
                case 3:
                    return i2 > i3;
                case 4:
                    return i2 <= i3;
                case 5:
                    return i2 < i3;
                case 6:
                    return i2 != i3;
                default:
                    return true;
            }
        }

        static BinOp b(String str) {
            BinOp[] values;
            for (BinOp binOp : values()) {
                if (binOp.f13872s.equals(str)) {
                    return binOp;
                }
            }
            throw new IllegalArgumentException("'s' is not a valid operator: " + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static abstract class Constraint {
        private Constraint() {
        }

        /* synthetic */ Constraint(AnonymousClass1 anonymousClass1) {
            this();
        }

        boolean a(AlgorithmParameters algorithmParameters) {
            return true;
        }

        boolean permits(Key key) {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class DisabledConstraint extends Constraint {

        /* renamed from: a  reason: collision with root package name */
        static final DisabledConstraint f13873a = new DisabledConstraint();

        private DisabledConstraint() {
            super(null);
        }

        @Override // org.bouncycastle.jsse.provider.DisabledAlgorithmConstraints.Constraint
        public boolean permits(Key key) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class KeySizeConstraint extends Constraint {
        private final int constraint;
        private final BinOp op;

        KeySizeConstraint(BinOp binOp, int i2) {
            super(null);
            this.op = binOp;
            this.constraint = i2;
        }

        private boolean checkKeySize(int i2) {
            return i2 < 1 ? i2 < 0 : !BinOp.a(this.op, i2, this.constraint);
        }

        private static int getKeySize(AlgorithmParameters algorithmParameters) {
            DHParameterSpec dHParameterSpec;
            String algorithm = algorithmParameters.getAlgorithm();
            try {
                if ("EC".equals(algorithm)) {
                    ECParameterSpec eCParameterSpec = (ECParameterSpec) algorithmParameters.getParameterSpec(ECParameterSpec.class);
                    if (eCParameterSpec != null) {
                        return eCParameterSpec.getOrder().bitLength();
                    }
                    return -1;
                } else if (!"DiffieHellman".equals(algorithm) || (dHParameterSpec = (DHParameterSpec) algorithmParameters.getParameterSpec(DHParameterSpec.class)) == null) {
                    return -1;
                } else {
                    return dHParameterSpec.getP().bitLength();
                }
            } catch (InvalidParameterSpecException unused) {
                return -1;
            }
        }

        private static int getKeySize(Key key) {
            byte[] encoded;
            BigInteger p2;
            if (key instanceof RSAKey) {
                p2 = ((RSAKey) key).getModulus();
            } else if (key instanceof ECKey) {
                p2 = ((ECKey) key).getParams().getOrder();
            } else if (key instanceof DSAKey) {
                DSAParams params = ((DSAKey) key).getParams();
                if (params == null) {
                    return -1;
                }
                p2 = params.getP();
            } else if (!(key instanceof DHKey)) {
                if (key instanceof SecretKey) {
                    SecretKey secretKey = (SecretKey) key;
                    if (!"RAW".equals(secretKey.getFormat()) || (encoded = secretKey.getEncoded()) == null) {
                        return -1;
                    }
                    if (encoded.length > 268435455) {
                        return 0;
                    }
                    return encoded.length * 8;
                }
                return -1;
            } else {
                p2 = ((DHKey) key).getParams().getP();
            }
            return p2.bitLength();
        }

        @Override // org.bouncycastle.jsse.provider.DisabledAlgorithmConstraints.Constraint
        boolean a(AlgorithmParameters algorithmParameters) {
            return checkKeySize(getKeySize(algorithmParameters));
        }

        @Override // org.bouncycastle.jsse.provider.DisabledAlgorithmConstraints.Constraint
        boolean permits(Key key) {
            return checkKeySize(getKeySize(key));
        }
    }

    private DisabledAlgorithmConstraints(AlgorithmDecomposer algorithmDecomposer, Set<String> set, Map<String, List<Constraint>> map) {
        super(algorithmDecomposer);
        this.disabledAlgorithms = set;
        this.constraintsMap = map;
    }

    private static void addConstraint(Map<String, List<Constraint>> map, String str, Constraint constraint) {
        List<Constraint> list = map.get(str);
        if (list == null) {
            list = new ArrayList<>(1);
            map.put(str, list);
        }
        list.add(constraint);
    }

    private static boolean addConstraint(Set<String> set, Map<String, List<Constraint>> map, String str) {
        if (str.regionMatches(true, 0, INCLUDE_PREFIX, 0, 8)) {
            return false;
        }
        int indexOf = str.indexOf(32);
        if (indexOf < 0) {
            String canonicalAlgorithm = getCanonicalAlgorithm(str);
            set.add(canonicalAlgorithm);
            addConstraint(map, canonicalAlgorithm, DisabledConstraint.f13873a);
            return true;
        }
        String canonicalAlgorithm2 = getCanonicalAlgorithm(str.substring(0, indexOf));
        String trim = str.substring(indexOf + 1).trim();
        if (trim.indexOf(38) < 0 && trim.startsWith(KEYWORD_KEYSIZE)) {
            StringTokenizer stringTokenizer = new StringTokenizer(trim);
            if (KEYWORD_KEYSIZE.equals(stringTokenizer.nextToken())) {
                BinOp b2 = BinOp.b(stringTokenizer.nextToken());
                int parseInt = Integer.parseInt(stringTokenizer.nextToken());
                if (stringTokenizer.hasMoreTokens()) {
                    return false;
                }
                addConstraint(map, canonicalAlgorithm2, new KeySizeConstraint(b2, parseInt));
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean checkConstraints(Set<BCCryptoPrimitive> set, String str, Key key, AlgorithmParameters algorithmParameters) {
        e(set);
        d(key);
        if ((!JsseUtils.Q(str) || permits(set, str, algorithmParameters)) && permits(set, JsseUtils.w(key), null)) {
            for (Constraint constraint : getConstraints(getConstraintsAlgorithm(key))) {
                if (!constraint.permits(key)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static String getCanonicalAlgorithm(String str) {
        return "DiffieHellman".equalsIgnoreCase(str) ? "DH" : str.toUpperCase(Locale.ENGLISH).replace("SHA-", "SHA");
    }

    private List<Constraint> getConstraints(String str) {
        List<Constraint> list;
        return (str == null || (list = this.constraintsMap.get(str)) == null) ? Collections.emptyList() : list;
    }

    private static String getConstraintsAlgorithm(String str, AlgorithmParameters algorithmParameters) {
        String algorithm;
        if (algorithmParameters == null || (algorithm = algorithmParameters.getAlgorithm()) == null) {
            return null;
        }
        String canonicalAlgorithm = getCanonicalAlgorithm(str);
        if (canonicalAlgorithm.equalsIgnoreCase(getCanonicalAlgorithm(algorithm))) {
            return canonicalAlgorithm;
        }
        return null;
    }

    private static String getConstraintsAlgorithm(Key key) {
        String w;
        if (key == null || (w = JsseUtils.w(key)) == null) {
            return null;
        }
        return getCanonicalAlgorithm(w);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DisabledAlgorithmConstraints i(AlgorithmDecomposer algorithmDecomposer, String str, String str2) {
        String[] f2 = PropertyUtils.f(str, str2);
        if (f2 == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        for (int i2 = 0; i2 < f2.length; i2++) {
            if (!addConstraint(hashSet, hashMap, f2[i2])) {
                Logger logger = LOG;
                logger.warning("Ignoring unsupported entry in '" + str + "': " + f2[i2]);
            }
        }
        return new DisabledAlgorithmConstraints(algorithmDecomposer, Collections.unmodifiableSet(hashSet), Collections.unmodifiableMap(hashMap));
    }

    @Override // org.bouncycastle.jsse.java.security.BCAlgorithmConstraints
    public final boolean permits(Set<BCCryptoPrimitive> set, String str, AlgorithmParameters algorithmParameters) {
        e(set);
        c(str);
        if (f(this.disabledAlgorithms, str)) {
            return false;
        }
        for (Constraint constraint : getConstraints(getConstraintsAlgorithm(str, algorithmParameters))) {
            if (!constraint.a(algorithmParameters)) {
                return false;
            }
        }
        return true;
    }

    @Override // org.bouncycastle.jsse.java.security.BCAlgorithmConstraints
    public final boolean permits(Set<BCCryptoPrimitive> set, String str, Key key, AlgorithmParameters algorithmParameters) {
        c(str);
        return checkConstraints(set, str, key, algorithmParameters);
    }

    @Override // org.bouncycastle.jsse.java.security.BCAlgorithmConstraints
    public final boolean permits(Set<BCCryptoPrimitive> set, Key key) {
        return checkConstraints(set, null, key, null);
    }
}
