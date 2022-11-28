package org.bouncycastle.tls.crypto.impl.jcajce;

import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.interfaces.ECKey;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECField;
import java.security.spec.ECFieldF2m;
import java.security.spec.ECFieldFp;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.EllipticCurve;
import org.bouncycastle.math.ec.ECCurve;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class ECUtil {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static ECCurve a(EllipticCurve ellipticCurve, BigInteger bigInteger, int i2) {
        ECField field = ellipticCurve.getField();
        BigInteger a2 = ellipticCurve.getA();
        BigInteger b2 = ellipticCurve.getB();
        if (field instanceof ECFieldFp) {
            return new ECCurve.Fp(((ECFieldFp) field).getP(), a2, b2, bigInteger, BigInteger.valueOf(i2));
        }
        ECFieldF2m eCFieldF2m = (ECFieldF2m) field;
        int m2 = eCFieldF2m.getM();
        int[] b3 = b(eCFieldF2m.getMidTermsOfReductionPolynomial());
        return new ECCurve.F2m(m2, b3[0], b3[1], b3[2], a2, b2, bigInteger, BigInteger.valueOf(i2));
    }

    static int[] b(int[] iArr) {
        int[] iArr2 = new int[3];
        if (iArr.length == 1) {
            iArr2[0] = iArr[0];
        } else if (iArr.length != 3) {
            throw new IllegalArgumentException("Only Trinomials and pentanomials supported");
        } else {
            if (iArr[0] < iArr[1] && iArr[0] < iArr[2]) {
                iArr2[0] = iArr[0];
                if (iArr[1] < iArr[2]) {
                    iArr2[1] = iArr[1];
                    iArr2[2] = iArr[2];
                } else {
                    iArr2[1] = iArr[2];
                    iArr2[2] = iArr[1];
                }
            } else if (iArr[1] < iArr[2]) {
                iArr2[0] = iArr[1];
                if (iArr[0] < iArr[2]) {
                    iArr2[1] = iArr[0];
                    iArr2[2] = iArr[2];
                } else {
                    iArr2[1] = iArr[2];
                    iArr2[2] = iArr[0];
                }
            } else {
                iArr2[0] = iArr[2];
                if (iArr[0] < iArr[1]) {
                    iArr2[1] = iArr[0];
                    iArr2[2] = iArr[1];
                } else {
                    iArr2[1] = iArr[1];
                    iArr2[2] = iArr[0];
                }
            }
        }
        return iArr2;
    }

    static AlgorithmParameterSpec c(String str) {
        return new ECGenParameterSpec(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlgorithmParameters d(JcaTlsCrypto jcaTlsCrypto, String str) {
        return e(jcaTlsCrypto, new ECGenParameterSpec(str));
    }

    static AlgorithmParameters e(JcaTlsCrypto jcaTlsCrypto, AlgorithmParameterSpec algorithmParameterSpec) {
        try {
            AlgorithmParameters createAlgorithmParameters = jcaTlsCrypto.getHelper().createAlgorithmParameters("EC");
            createAlgorithmParameters.init(algorithmParameterSpec);
            if (((ECParameterSpec) createAlgorithmParameters.getParameterSpec(ECParameterSpec.class)) != null) {
                return createAlgorithmParameters;
            }
            return null;
        } catch (AssertionError | Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ECParameterSpec f(JcaTlsCrypto jcaTlsCrypto, String str) {
        return g(jcaTlsCrypto, c(str));
    }

    static ECParameterSpec g(JcaTlsCrypto jcaTlsCrypto, AlgorithmParameterSpec algorithmParameterSpec) {
        try {
            KeyPairGenerator createKeyPairGenerator = jcaTlsCrypto.getHelper().createKeyPairGenerator("EC");
            createKeyPairGenerator.initialize(algorithmParameterSpec, jcaTlsCrypto.getSecureRandom());
            try {
                AlgorithmParameters createAlgorithmParameters = jcaTlsCrypto.getHelper().createAlgorithmParameters("EC");
                createAlgorithmParameters.init(algorithmParameterSpec);
                ECParameterSpec eCParameterSpec = (ECParameterSpec) createAlgorithmParameters.getParameterSpec(ECParameterSpec.class);
                if (eCParameterSpec != null) {
                    return eCParameterSpec;
                }
            } catch (AssertionError | Exception unused) {
            }
            return ((ECKey) createKeyPairGenerator.generateKeyPair().getPrivate()).getParams();
        } catch (AssertionError | Exception unused2) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean h(JcaTlsCrypto jcaTlsCrypto, String str) {
        return str != null && i(jcaTlsCrypto, new ECGenParameterSpec(str));
    }

    static boolean i(JcaTlsCrypto jcaTlsCrypto, ECGenParameterSpec eCGenParameterSpec) {
        return g(jcaTlsCrypto, eCGenParameterSpec) != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean j(PrivateKey privateKey) {
        return (privateKey instanceof ECPrivateKey) || "EC".equalsIgnoreCase(privateKey.getAlgorithm());
    }
}
