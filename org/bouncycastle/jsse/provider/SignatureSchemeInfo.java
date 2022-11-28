package org.bouncycastle.jsse.provider;

import java.security.AlgorithmParameters;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import java.util.logging.Logger;
import org.bouncycastle.jcajce.spec.EdDSAParameterSpec;
import org.bouncycastle.jsse.java.security.BCAlgorithmConstraints;
import org.bouncycastle.jsse.java.security.BCCryptoPrimitive;
import org.bouncycastle.jsse.provider.NamedGroupInfo;
import org.bouncycastle.tls.NamedGroup;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.SignatureScheme;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class SignatureSchemeInfo {
    private static final String PROPERTY_CLIENT_SIGNATURE_SCHEMES = "jdk.tls.client.SignatureSchemes";
    private static final String PROPERTY_SERVER_SIGNATURE_SCHEMES = "jdk.tls.server.SignatureSchemes";
    private final AlgorithmParameters algorithmParameters;
    private final All all;
    private final boolean disabled13;
    private final boolean enabled;
    private final NamedGroupInfo namedGroupInfo;
    private static final Logger LOG = Logger.getLogger(SignatureSchemeInfo.class.getName());
    private static final int[] CANDIDATES_DEFAULT = createCandidatesDefault();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public enum All {
        ed25519(SignatureScheme.ed25519, EdDSAParameterSpec.Ed25519, EdDSAParameterSpec.Ed25519),
        ed448(SignatureScheme.ed448, EdDSAParameterSpec.Ed448, EdDSAParameterSpec.Ed448),
        ecdsa_secp256r1_sha256(SignatureScheme.ecdsa_secp256r1_sha256, "SHA256withECDSA", "EC"),
        ecdsa_secp384r1_sha384(SignatureScheme.ecdsa_secp384r1_sha384, "SHA384withECDSA", "EC"),
        ecdsa_secp521r1_sha512(SignatureScheme.ecdsa_secp521r1_sha512, "SHA512withECDSA", "EC"),
        ecdsa_brainpoolP256r1tls13_sha256(SignatureScheme.ecdsa_brainpoolP256r1tls13_sha256, "SHA256withECDSA", "EC"),
        ecdsa_brainpoolP384r1tls13_sha384(SignatureScheme.ecdsa_brainpoolP384r1tls13_sha384, "SHA384withECDSA", "EC"),
        ecdsa_brainpoolP512r1tls13_sha512(SignatureScheme.ecdsa_brainpoolP512r1tls13_sha512, "SHA512withECDSA", "EC"),
        rsa_pss_pss_sha256(SignatureScheme.rsa_pss_pss_sha256, "SHA256withRSAandMGF1", "RSASSA-PSS"),
        rsa_pss_pss_sha384(SignatureScheme.rsa_pss_pss_sha384, "SHA384withRSAandMGF1", "RSASSA-PSS"),
        rsa_pss_pss_sha512(SignatureScheme.rsa_pss_pss_sha512, "SHA512withRSAandMGF1", "RSASSA-PSS"),
        rsa_pss_rsae_sha256(SignatureScheme.rsa_pss_rsae_sha256, "SHA256withRSAandMGF1", "RSA"),
        rsa_pss_rsae_sha384(SignatureScheme.rsa_pss_rsae_sha384, "SHA384withRSAandMGF1", "RSA"),
        rsa_pss_rsae_sha512(SignatureScheme.rsa_pss_rsae_sha512, "SHA512withRSAandMGF1", "RSA"),
        rsa_pkcs1_sha256(1025, "SHA256withRSA", "RSA", true),
        rsa_pkcs1_sha384((int) SignatureScheme.rsa_pkcs1_sha384, "SHA384withRSA", "RSA", true),
        rsa_pkcs1_sha512((int) SignatureScheme.rsa_pkcs1_sha512, "SHA512withRSA", "RSA", true),
        sm2sig_sm3(SignatureScheme.sm2sig_sm3, "SM3withSM2", "EC"),
        dsa_sha256(1026, "dsa_sha256", "SHA256withDSA", "DSA"),
        ecdsa_sha224(771, "ecdsa_sha224", "SHA224withECDSA", "EC"),
        rsa_sha224(769, "rsa_sha224", "SHA224withRSA", "RSA"),
        dsa_sha224(770, "dsa_sha224", "SHA224withDSA", "DSA"),
        ecdsa_sha1((int) SignatureScheme.ecdsa_sha1, "SHA1withECDSA", "EC", true),
        rsa_pkcs1_sha1(513, "SHA1withRSA", "RSA", true),
        dsa_sha1(514, "dsa_sha1", "SHA1withDSA", "DSA"),
        rsa_md5(257, "rsa_md5", "MD5withRSA", "RSA");
        
        private final String jcaSignatureAlgorithm;
        private final String jcaSignatureAlgorithmBC;
        private final String keyAlgorithm;
        private final String keyType13;
        private final String name;
        private final int namedGroup13;
        private final int signatureScheme;
        private final boolean supportedCerts13;
        private final boolean supportedPost13;
        private final boolean supportedPre13;
        private final String text;

        All(int i2, String str, String str2) {
            this(i2, str, str2, true, true, SignatureScheme.getNamedGroup(i2));
        }

        All(int i2, String str, String str2, String str3) {
            this(i2, str, str2, str3, false, false, -1);
        }

        All(int i2, String str, String str2, String str3, boolean z, boolean z2, int i3) {
            String x = JsseUtils.x(str3, i3);
            String v = JsseUtils.v(str2, str3);
            this.signatureScheme = i2;
            this.name = str;
            this.text = str + "(0x" + Integer.toHexString(i2) + ")";
            this.jcaSignatureAlgorithm = str2;
            this.jcaSignatureAlgorithmBC = v;
            this.keyAlgorithm = str3;
            this.keyType13 = x;
            this.supportedPost13 = z;
            this.supportedPre13 = i3 < 0 || NamedGroup.canBeNegotiated(i3, ProtocolVersion.TLSv12);
            this.supportedCerts13 = z2;
            this.namedGroup13 = i3;
        }

        All(int i2, String str, String str2, boolean z) {
            this(i2, str, str2, false, z, -1);
        }

        All(int i2, String str, String str2, boolean z, boolean z2, int i3) {
            this(i2, SignatureScheme.getName(i2), str, str2, z, z2, i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class PerContext {
        private final int[] candidatesClient;
        private final int[] candidatesServer;
        private final Map<Integer, SignatureSchemeInfo> index;

        PerContext(Map map, int[] iArr, int[] iArr2) {
            this.index = map;
            this.candidatesClient = iArr;
            this.candidatesServer = iArr2;
        }
    }

    SignatureSchemeInfo(All all, AlgorithmParameters algorithmParameters, NamedGroupInfo namedGroupInfo, boolean z, boolean z2) {
        this.all = all;
        this.algorithmParameters = algorithmParameters;
        this.namedGroupInfo = namedGroupInfo;
        this.enabled = z;
        this.disabled13 = z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PerContext a(boolean z, JcaTlsCrypto jcaTlsCrypto, NamedGroupInfo.PerContext perContext) {
        Map<Integer, SignatureSchemeInfo> createIndex = createIndex(z, jcaTlsCrypto, perContext);
        return new PerContext(createIndex, createCandidates(createIndex, PROPERTY_CLIENT_SIGNATURE_SCHEMES), createCandidates(createIndex, PROPERTY_SERVER_SIGNATURE_SCHEMES));
    }

    private static void addSignatureScheme(boolean z, JcaTlsCrypto jcaTlsCrypto, NamedGroupInfo.PerContext perContext, Map<Integer, SignatureSchemeInfo> map, All all) {
        boolean z2;
        NamedGroupInfo namedGroupInfo;
        boolean z3;
        int i2 = all.signatureScheme;
        if (!z || FipsUtils.d(i2)) {
            int i3 = all.namedGroup13;
            AlgorithmParameters algorithmParameters = null;
            if (i3 >= 0) {
                NamedGroupInfo j2 = NamedGroupInfo.j(perContext, i3);
                if (j2 != null && j2.p() && j2.q()) {
                    namedGroupInfo = j2;
                    z2 = false;
                } else {
                    namedGroupInfo = j2;
                    z2 = true;
                }
            } else {
                z2 = false;
                namedGroupInfo = null;
            }
            boolean hasSignatureScheme = jcaTlsCrypto.hasSignatureScheme(i2);
            if (hasSignatureScheme) {
                try {
                    algorithmParameters = jcaTlsCrypto.getSignatureSchemeAlgorithmParameters(i2);
                } catch (Exception unused) {
                    z3 = false;
                }
            }
            z3 = hasSignatureScheme;
            if (map.put(Integer.valueOf(i2), new SignatureSchemeInfo(all, algorithmParameters, namedGroupInfo, z3, z2)) != null) {
                throw new IllegalStateException("Duplicate entries for SignatureSchemeInfo");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List b(PerContext perContext, boolean z, ProvSSLParameters provSSLParameters, ProtocolVersion[] protocolVersionArr, NamedGroupInfo.PerConnection perConnection) {
        ProtocolVersion latestTLS = ProtocolVersion.getLatestTLS(protocolVersionArr);
        if (TlsUtils.isSignatureAlgorithmsExtensionAllowed(latestTLS)) {
            int[] iArr = z ? perContext.candidatesServer : perContext.candidatesClient;
            ProtocolVersion earliestTLS = ProtocolVersion.getEarliestTLS(protocolVersionArr);
            BCAlgorithmConstraints algorithmConstraints = provSSLParameters.getAlgorithmConstraints();
            boolean isTLSv13 = TlsUtils.isTLSv13(latestTLS);
            boolean z2 = !TlsUtils.isTLSv13(earliestTLS);
            ArrayList arrayList = new ArrayList(iArr.length);
            for (int i2 : iArr) {
                SignatureSchemeInfo signatureSchemeInfo = (SignatureSchemeInfo) perContext.index.get(Integers.valueOf(i2));
                if (signatureSchemeInfo != null && signatureSchemeInfo.p(algorithmConstraints, isTLSv13, z2, perConnection)) {
                    arrayList.add(signatureSchemeInfo);
                }
            }
            if (arrayList.isEmpty()) {
                return Collections.emptyList();
            }
            arrayList.trimToSize();
            return Collections.unmodifiableList(arrayList);
        }
        return null;
    }

    private static int[] createCandidates(Map<Integer, SignatureSchemeInfo> map, String str) {
        Logger logger;
        StringBuilder sb;
        String str2;
        String[] g2 = PropertyUtils.g(str);
        if (g2 == null) {
            return CANDIDATES_DEFAULT;
        }
        int length = g2.length;
        int[] iArr = new int[length];
        int i2 = 0;
        for (String str3 : g2) {
            int signatureSchemeByName = getSignatureSchemeByName(str3);
            if (signatureSchemeByName < 0) {
                logger = LOG;
                sb = new StringBuilder();
                sb.append("'");
                sb.append(str);
                str2 = "' contains unrecognised SignatureScheme: ";
            } else {
                SignatureSchemeInfo signatureSchemeInfo = map.get(Integer.valueOf(signatureSchemeByName));
                if (signatureSchemeInfo == null) {
                    logger = LOG;
                    sb = new StringBuilder();
                    sb.append("'");
                    sb.append(str);
                    str2 = "' contains unsupported SignatureScheme: ";
                } else if (signatureSchemeInfo.q()) {
                    iArr[i2] = signatureSchemeByName;
                    i2++;
                } else {
                    logger = LOG;
                    sb = new StringBuilder();
                    sb.append("'");
                    sb.append(str);
                    str2 = "' contains disabled SignatureScheme: ";
                }
            }
            sb.append(str2);
            sb.append(str3);
            logger.warning(sb.toString());
        }
        if (i2 < length) {
            iArr = Arrays.copyOf(iArr, i2);
        }
        if (iArr.length < 1) {
            LOG.severe("'" + str + "' contained no usable SignatureScheme values");
        }
        return iArr;
    }

    private static int[] createCandidatesDefault() {
        All[] values = All.values();
        int[] iArr = new int[values.length];
        for (int i2 = 0; i2 < values.length; i2++) {
            iArr[i2] = values[i2].signatureScheme;
        }
        return iArr;
    }

    private static Map<Integer, SignatureSchemeInfo> createIndex(boolean z, JcaTlsCrypto jcaTlsCrypto, NamedGroupInfo.PerContext perContext) {
        TreeMap treeMap = new TreeMap();
        for (All all : All.values()) {
            addSignatureScheme(z, jcaTlsCrypto, perContext, treeMap, all);
        }
        return treeMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] e(Collection collection) {
        if (collection == null) {
            return TlsUtils.EMPTY_STRINGS;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(((SignatureSchemeInfo) it.next()).c());
        }
        return (String[]) arrayList.toArray(TlsUtils.EMPTY_STRINGS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] f(Collection collection) {
        if (collection == null) {
            return TlsUtils.EMPTY_STRINGS;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(((SignatureSchemeInfo) it.next()).d());
        }
        return (String[]) arrayList.toArray(TlsUtils.EMPTY_STRINGS);
    }

    private static int getSignatureSchemeByName(String str) {
        All[] values;
        for (All all : All.values()) {
            if (all.name.equalsIgnoreCase(str)) {
                return all.signatureScheme;
            }
        }
        return -1;
    }

    private static boolean isECDSA(int i2) {
        if (i2 == 515 || i2 == 771 || i2 == 1027 || i2 == 1283 || i2 == 1539) {
            return true;
        }
        switch (i2) {
            case SignatureScheme.ecdsa_brainpoolP256r1tls13_sha256 /* 2074 */:
            case SignatureScheme.ecdsa_brainpoolP384r1tls13_sha384 /* 2075 */:
            case SignatureScheme.ecdsa_brainpoolP512r1tls13_sha512 /* 2076 */:
                return true;
            default:
                return false;
        }
    }

    private boolean isNamedGroupOK(boolean z, boolean z2, NamedGroupInfo.PerConnection perConnection) {
        NamedGroupInfo namedGroupInfo = this.namedGroupInfo;
        if (namedGroupInfo != null) {
            return (z && NamedGroupInfo.n(perConnection, namedGroupInfo.i())) || (z2 && NamedGroupInfo.m(perConnection));
        } else if (z || z2) {
            return !isECDSA(this.all.signatureScheme) || NamedGroupInfo.m(perConnection);
        } else {
            return false;
        }
    }

    private boolean isPermittedBy(BCAlgorithmConstraints bCAlgorithmConstraints) {
        Set<BCCryptoPrimitive> set = JsseUtils.f13898c;
        return bCAlgorithmConstraints.permits(set, this.all.name, null) && bCAlgorithmConstraints.permits(set, this.all.keyAlgorithm, null) && bCAlgorithmConstraints.permits(set, this.all.jcaSignatureAlgorithm, this.algorithmParameters);
    }

    static SignatureAndHashAlgorithm k(int i2) {
        if (TlsUtils.isValidUint16(i2)) {
            return SignatureScheme.getSignatureAndHashAlgorithm(i2);
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Vector l(List list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        Vector vector = new Vector(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            SignatureSchemeInfo signatureSchemeInfo = (SignatureSchemeInfo) it.next();
            if (signatureSchemeInfo != null) {
                vector.add(signatureSchemeInfo.j());
            }
        }
        if (vector.isEmpty()) {
            return null;
        }
        vector.trimToSize();
        return vector;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List n(PerContext perContext, Vector vector) {
        if (vector == null || vector.isEmpty()) {
            return null;
        }
        int size = vector.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm) vector.elementAt(i2);
            if (signatureAndHashAlgorithm != null) {
                SignatureSchemeInfo signatureSchemeInfo = (SignatureSchemeInfo) perContext.index.get(Integer.valueOf(SignatureScheme.from(signatureAndHashAlgorithm)));
                if (signatureSchemeInfo != null) {
                    arrayList.add(signatureSchemeInfo);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        arrayList.trimToSize();
        return Collections.unmodifiableList(arrayList);
    }

    String c() {
        return this.all.jcaSignatureAlgorithm;
    }

    String d() {
        return this.all.jcaSignatureAlgorithmBC;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String g() {
        return this.all.keyAlgorithm;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String h() {
        return this.all.keyType13;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public short i() {
        return SignatureScheme.getSignatureAlgorithm(this.all.signatureScheme);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SignatureAndHashAlgorithm j() {
        return k(this.all.signatureScheme);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int m() {
        return this.all.signatureScheme;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean o(BCAlgorithmConstraints bCAlgorithmConstraints, boolean z, boolean z2, NamedGroupInfo.PerConnection perConnection) {
        if (this.enabled) {
            if (isNamedGroupOK(z && s(), z2 && t(), perConnection) && isPermittedBy(bCAlgorithmConstraints)) {
                return true;
            }
        }
        return false;
    }

    boolean p(BCAlgorithmConstraints bCAlgorithmConstraints, boolean z, boolean z2, NamedGroupInfo.PerConnection perConnection) {
        if (this.enabled) {
            if (isNamedGroupOK(z && r(), z2 && t(), perConnection) && isPermittedBy(bCAlgorithmConstraints)) {
                return true;
            }
        }
        return false;
    }

    boolean q() {
        return this.enabled;
    }

    boolean r() {
        return !this.disabled13 && this.all.supportedCerts13;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean s() {
        return !this.disabled13 && this.all.supportedPost13;
    }

    boolean t() {
        return this.all.supportedPre13;
    }

    public String toString() {
        return this.all.text;
    }
}
