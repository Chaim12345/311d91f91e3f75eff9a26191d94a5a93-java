package org.bouncycastle.jcajce.provider.asymmetric.rsa;

import java.math.BigInteger;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.util.Fingerprint;
/* loaded from: classes3.dex */
public class RSAUtil {
    public static final ASN1ObjectIdentifier[] rsaOids = {PKCSObjectIdentifiers.rsaEncryption, X509ObjectIdentifiers.id_ea_rsa, PKCSObjectIdentifiers.id_RSAES_OAEP, PKCSObjectIdentifiers.id_RSASSA_PSS};

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(BigInteger bigInteger) {
        return new Fingerprint(bigInteger.toByteArray(), 32).toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(BigInteger bigInteger) {
        return new Fingerprint(bigInteger.toByteArray()).toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RSAKeyParameters c(RSAPrivateKey rSAPrivateKey) {
        if (rSAPrivateKey instanceof BCRSAPrivateKey) {
            return ((BCRSAPrivateKey) rSAPrivateKey).a();
        }
        if (rSAPrivateKey instanceof RSAPrivateCrtKey) {
            RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) rSAPrivateKey;
            return new RSAPrivateCrtKeyParameters(rSAPrivateCrtKey.getModulus(), rSAPrivateCrtKey.getPublicExponent(), rSAPrivateCrtKey.getPrivateExponent(), rSAPrivateCrtKey.getPrimeP(), rSAPrivateCrtKey.getPrimeQ(), rSAPrivateCrtKey.getPrimeExponentP(), rSAPrivateCrtKey.getPrimeExponentQ(), rSAPrivateCrtKey.getCrtCoefficient());
        }
        return new RSAKeyParameters(true, rSAPrivateKey.getModulus(), rSAPrivateKey.getPrivateExponent());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RSAKeyParameters d(RSAPublicKey rSAPublicKey) {
        return rSAPublicKey instanceof BCRSAPublicKey ? ((BCRSAPublicKey) rSAPublicKey).a() : new RSAKeyParameters(false, rSAPublicKey.getModulus(), rSAPublicKey.getPublicExponent());
    }

    public static boolean isRsaOid(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        int i2 = 0;
        while (true) {
            ASN1ObjectIdentifier[] aSN1ObjectIdentifierArr = rsaOids;
            if (i2 == aSN1ObjectIdentifierArr.length) {
                return false;
            }
            if (aSN1ObjectIdentifier.equals((ASN1Primitive) aSN1ObjectIdentifierArr[i2])) {
                return true;
            }
            i2++;
        }
    }
}