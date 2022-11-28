package org.bouncycastle.jcajce.provider.asymmetric.gost;

import java.math.BigInteger;
import org.bouncycastle.crypto.params.GOST3410Parameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Fingerprint;
import org.bouncycastle.util.Strings;
/* loaded from: classes3.dex */
class GOSTUtil {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(String str, BigInteger bigInteger, GOST3410Parameters gOST3410Parameters) {
        StringBuffer stringBuffer = new StringBuffer();
        String lineSeparator = Strings.lineSeparator();
        BigInteger modPow = gOST3410Parameters.getA().modPow(bigInteger, gOST3410Parameters.getP());
        stringBuffer.append(str);
        stringBuffer.append(" Private Key [");
        stringBuffer.append(generateKeyFingerprint(modPow, gOST3410Parameters));
        stringBuffer.append("]");
        stringBuffer.append(lineSeparator);
        stringBuffer.append("                  Y: ");
        stringBuffer.append(modPow.toString(16));
        stringBuffer.append(lineSeparator);
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(String str, BigInteger bigInteger, GOST3410Parameters gOST3410Parameters) {
        StringBuffer stringBuffer = new StringBuffer();
        String lineSeparator = Strings.lineSeparator();
        stringBuffer.append(str);
        stringBuffer.append(" Public Key [");
        stringBuffer.append(generateKeyFingerprint(bigInteger, gOST3410Parameters));
        stringBuffer.append("]");
        stringBuffer.append(lineSeparator);
        stringBuffer.append("                 Y: ");
        stringBuffer.append(bigInteger.toString(16));
        stringBuffer.append(lineSeparator);
        return stringBuffer.toString();
    }

    private static String generateKeyFingerprint(BigInteger bigInteger, GOST3410Parameters gOST3410Parameters) {
        return new Fingerprint(Arrays.concatenate(bigInteger.toByteArray(), gOST3410Parameters.getP().toByteArray(), gOST3410Parameters.getA().toByteArray())).toString();
    }
}
