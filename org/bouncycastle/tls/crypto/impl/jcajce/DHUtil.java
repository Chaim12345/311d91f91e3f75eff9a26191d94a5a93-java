package org.bouncycastle.tls.crypto.impl.jcajce;

import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import javax.crypto.spec.DHParameterSpec;
import org.bouncycastle.jcajce.spec.DHDomainParameterSpec;
import org.bouncycastle.jcajce.spec.DHExtendedPublicKeySpec;
import org.bouncycastle.tls.crypto.DHGroup;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class DHUtil {
    static AlgorithmParameterSpec a(DHGroup dHGroup) {
        return new DHDomainParameterSpec(dHGroup.getP(), dHGroup.getQ(), dHGroup.getG(), dHGroup.getL());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeySpec b(BigInteger bigInteger, DHParameterSpec dHParameterSpec) {
        return new DHExtendedPublicKeySpec(bigInteger, dHParameterSpec);
    }

    static AlgorithmParameters c(JcaTlsCrypto jcaTlsCrypto, AlgorithmParameterSpec algorithmParameterSpec) {
        try {
            AlgorithmParameters createAlgorithmParameters = jcaTlsCrypto.getHelper().createAlgorithmParameters("DiffieHellman");
            createAlgorithmParameters.init(algorithmParameterSpec);
            if (((DHParameterSpec) createAlgorithmParameters.getParameterSpec(DHParameterSpec.class)) != null) {
                return createAlgorithmParameters;
            }
            return null;
        } catch (AssertionError | Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlgorithmParameters d(JcaTlsCrypto jcaTlsCrypto, DHGroup dHGroup) {
        return c(jcaTlsCrypto, a(dHGroup));
    }

    static DHParameterSpec e(JcaTlsCrypto jcaTlsCrypto, AlgorithmParameterSpec algorithmParameterSpec) {
        try {
            AlgorithmParameters createAlgorithmParameters = jcaTlsCrypto.getHelper().createAlgorithmParameters("DiffieHellman");
            createAlgorithmParameters.init(algorithmParameterSpec);
            DHParameterSpec dHParameterSpec = (DHParameterSpec) createAlgorithmParameters.getParameterSpec(DHParameterSpec.class);
            if (dHParameterSpec != null) {
                return dHParameterSpec;
            }
            return null;
        } catch (AssertionError | Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DHParameterSpec f(JcaTlsCrypto jcaTlsCrypto, DHGroup dHGroup) {
        return e(jcaTlsCrypto, a(dHGroup));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean g(JcaTlsCrypto jcaTlsCrypto, DHGroup dHGroup) {
        return (dHGroup == null || f(jcaTlsCrypto, dHGroup) == null) ? false : true;
    }
}
