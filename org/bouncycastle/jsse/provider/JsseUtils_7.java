package org.bouncycastle.jsse.provider;

import java.security.AlgorithmConstraints;
import java.security.AlgorithmParameters;
import java.security.CryptoPrimitive;
import java.security.Key;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.bouncycastle.jsse.java.security.BCAlgorithmConstraints;
import org.bouncycastle.jsse.java.security.BCCryptoPrimitive;
/* loaded from: classes3.dex */
abstract class JsseUtils_7 extends JsseUtils {

    /* renamed from: f  reason: collision with root package name */
    static final Set f13901f = Collections.unmodifiableSet(EnumSet.of(CryptoPrimitive.KEY_AGREEMENT));

    /* renamed from: g  reason: collision with root package name */
    static final Set f13902g = Collections.unmodifiableSet(EnumSet.of(CryptoPrimitive.KEY_ENCAPSULATION));

    /* renamed from: h  reason: collision with root package name */
    static final Set f13903h = Collections.unmodifiableSet(EnumSet.of(CryptoPrimitive.SIGNATURE));

    /* renamed from: i  reason: collision with root package name */
    static final AlgorithmConstraints f13904i = new ExportAlgorithmConstraints(ProvAlgorithmConstraints.f13911b);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.bouncycastle.jsse.provider.JsseUtils_7$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f13905a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f13906b;

        static {
            int[] iArr = new int[CryptoPrimitive.values().length];
            f13906b = iArr;
            try {
                iArr[CryptoPrimitive.MESSAGE_DIGEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13906b[CryptoPrimitive.SECURE_RANDOM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f13906b[CryptoPrimitive.BLOCK_CIPHER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f13906b[CryptoPrimitive.STREAM_CIPHER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f13906b[CryptoPrimitive.MAC.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f13906b[CryptoPrimitive.KEY_WRAP.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f13906b[CryptoPrimitive.PUBLIC_KEY_ENCRYPTION.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f13906b[CryptoPrimitive.SIGNATURE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f13906b[CryptoPrimitive.KEY_ENCAPSULATION.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f13906b[CryptoPrimitive.KEY_AGREEMENT.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            int[] iArr2 = new int[BCCryptoPrimitive.values().length];
            f13905a = iArr2;
            try {
                iArr2[BCCryptoPrimitive.MESSAGE_DIGEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f13905a[BCCryptoPrimitive.SECURE_RANDOM.ordinal()] = 2;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f13905a[BCCryptoPrimitive.BLOCK_CIPHER.ordinal()] = 3;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f13905a[BCCryptoPrimitive.STREAM_CIPHER.ordinal()] = 4;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f13905a[BCCryptoPrimitive.MAC.ordinal()] = 5;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f13905a[BCCryptoPrimitive.KEY_WRAP.ordinal()] = 6;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f13905a[BCCryptoPrimitive.PUBLIC_KEY_ENCRYPTION.ordinal()] = 7;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f13905a[BCCryptoPrimitive.SIGNATURE.ordinal()] = 8;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f13905a[BCCryptoPrimitive.KEY_ENCAPSULATION.ordinal()] = 9;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f13905a[BCCryptoPrimitive.KEY_AGREEMENT.ordinal()] = 10;
            } catch (NoSuchFieldError unused20) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ExportAlgorithmConstraints implements AlgorithmConstraints {
        private final BCAlgorithmConstraints constraints;

        ExportAlgorithmConstraints(BCAlgorithmConstraints bCAlgorithmConstraints) {
            this.constraints = bCAlgorithmConstraints;
        }

        BCAlgorithmConstraints a() {
            return this.constraints;
        }

        @Override // java.security.AlgorithmConstraints
        public boolean permits(Set<CryptoPrimitive> set, String str, AlgorithmParameters algorithmParameters) {
            return this.constraints.permits(JsseUtils_7.i0(set), str, algorithmParameters);
        }

        @Override // java.security.AlgorithmConstraints
        public boolean permits(Set<CryptoPrimitive> set, String str, Key key, AlgorithmParameters algorithmParameters) {
            return this.constraints.permits(JsseUtils_7.i0(set), str, key, algorithmParameters);
        }

        @Override // java.security.AlgorithmConstraints
        public boolean permits(Set<CryptoPrimitive> set, Key key) {
            return this.constraints.permits(JsseUtils_7.i0(set), key);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ImportAlgorithmConstraints implements BCAlgorithmConstraints {
        private final AlgorithmConstraints constraints;

        ImportAlgorithmConstraints(AlgorithmConstraints algorithmConstraints) {
            this.constraints = algorithmConstraints;
        }

        AlgorithmConstraints a() {
            return this.constraints;
        }

        @Override // org.bouncycastle.jsse.java.security.BCAlgorithmConstraints
        public boolean permits(Set<BCCryptoPrimitive> set, String str, AlgorithmParameters algorithmParameters) {
            return this.constraints.permits(JsseUtils_7.e0(set), str, algorithmParameters);
        }

        @Override // org.bouncycastle.jsse.java.security.BCAlgorithmConstraints
        public boolean permits(Set<BCCryptoPrimitive> set, String str, Key key, AlgorithmParameters algorithmParameters) {
            return this.constraints.permits(JsseUtils_7.e0(set), str, key, algorithmParameters);
        }

        @Override // org.bouncycastle.jsse.java.security.BCAlgorithmConstraints
        public boolean permits(Set<BCCryptoPrimitive> set, Key key) {
            return this.constraints.permits(JsseUtils_7.e0(set), key);
        }
    }

    static AlgorithmConstraints b0(BCAlgorithmConstraints bCAlgorithmConstraints) {
        if (ProvAlgorithmConstraints.f13911b == bCAlgorithmConstraints) {
            return f13904i;
        }
        if (bCAlgorithmConstraints == null) {
            return null;
        }
        return bCAlgorithmConstraints instanceof ImportAlgorithmConstraints ? ((ImportAlgorithmConstraints) bCAlgorithmConstraints).a() : new ExportAlgorithmConstraints(bCAlgorithmConstraints);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object c0(BCAlgorithmConstraints bCAlgorithmConstraints) {
        return b0(bCAlgorithmConstraints);
    }

    static CryptoPrimitive d0(BCCryptoPrimitive bCCryptoPrimitive) {
        switch (AnonymousClass1.f13905a[bCCryptoPrimitive.ordinal()]) {
            case 1:
                return CryptoPrimitive.MESSAGE_DIGEST;
            case 2:
                return CryptoPrimitive.SECURE_RANDOM;
            case 3:
                return CryptoPrimitive.BLOCK_CIPHER;
            case 4:
                return CryptoPrimitive.STREAM_CIPHER;
            case 5:
                return CryptoPrimitive.MAC;
            case 6:
                return CryptoPrimitive.KEY_WRAP;
            case 7:
                return CryptoPrimitive.PUBLIC_KEY_ENCRYPTION;
            case 8:
                return CryptoPrimitive.SIGNATURE;
            case 9:
                return CryptoPrimitive.KEY_ENCAPSULATION;
            case 10:
                return CryptoPrimitive.KEY_AGREEMENT;
            default:
                return null;
        }
    }

    static Set e0(Set set) {
        if (JsseUtils.f13898c == set) {
            return f13903h;
        }
        if (JsseUtils.f13896a == set) {
            return f13901f;
        }
        if (JsseUtils.f13897b == set) {
            return f13902g;
        }
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            hashSet.add(d0((BCCryptoPrimitive) it.next()));
        }
        return hashSet;
    }

    static BCAlgorithmConstraints f0(AlgorithmConstraints algorithmConstraints) {
        if (algorithmConstraints == null) {
            return null;
        }
        return algorithmConstraints instanceof ExportAlgorithmConstraints ? ((ExportAlgorithmConstraints) algorithmConstraints).a() : new ImportAlgorithmConstraints(algorithmConstraints);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BCAlgorithmConstraints g0(Object obj) {
        return f0((AlgorithmConstraints) obj);
    }

    static BCCryptoPrimitive h0(CryptoPrimitive cryptoPrimitive) {
        switch (AnonymousClass1.f13906b[cryptoPrimitive.ordinal()]) {
            case 1:
                return BCCryptoPrimitive.MESSAGE_DIGEST;
            case 2:
                return BCCryptoPrimitive.SECURE_RANDOM;
            case 3:
                return BCCryptoPrimitive.BLOCK_CIPHER;
            case 4:
                return BCCryptoPrimitive.STREAM_CIPHER;
            case 5:
                return BCCryptoPrimitive.MAC;
            case 6:
                return BCCryptoPrimitive.KEY_WRAP;
            case 7:
                return BCCryptoPrimitive.PUBLIC_KEY_ENCRYPTION;
            case 8:
                return BCCryptoPrimitive.SIGNATURE;
            case 9:
                return BCCryptoPrimitive.KEY_ENCAPSULATION;
            case 10:
                return BCCryptoPrimitive.KEY_AGREEMENT;
            default:
                return null;
        }
    }

    static Set i0(Set set) {
        if (f13903h == set) {
            return JsseUtils.f13898c;
        }
        if (f13901f == set) {
            return JsseUtils.f13896a;
        }
        if (f13902g == set) {
            return JsseUtils.f13897b;
        }
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            hashSet.add(h0((CryptoPrimitive) it.next()));
        }
        return hashSet;
    }
}
