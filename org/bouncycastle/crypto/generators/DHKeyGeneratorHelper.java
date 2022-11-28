package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.math.ec.WNafUtil;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes3.dex */
class DHKeyGeneratorHelper {

    /* renamed from: a  reason: collision with root package name */
    static final DHKeyGeneratorHelper f13419a = new DHKeyGeneratorHelper();
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger TWO = BigInteger.valueOf(2);

    private DHKeyGeneratorHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BigInteger a(DHParameters dHParameters, SecureRandom secureRandom) {
        BigInteger createRandomInRange;
        BigInteger bit;
        int l2 = dHParameters.getL();
        if (l2 != 0) {
            int i2 = l2 >>> 2;
            do {
                bit = BigIntegers.createRandomBigInteger(l2, secureRandom).setBit(l2 - 1);
            } while (WNafUtil.getNafWeight(bit) < i2);
            return bit;
        }
        BigInteger bigInteger = TWO;
        int m2 = dHParameters.getM();
        BigInteger shiftLeft = m2 != 0 ? ONE.shiftLeft(m2 - 1) : bigInteger;
        BigInteger q2 = dHParameters.getQ();
        if (q2 == null) {
            q2 = dHParameters.getP();
        }
        BigInteger subtract = q2.subtract(bigInteger);
        int bitLength = subtract.bitLength() >>> 2;
        do {
            createRandomInRange = BigIntegers.createRandomInRange(shiftLeft, subtract, secureRandom);
        } while (WNafUtil.getNafWeight(createRandomInRange) < bitLength);
        return createRandomInRange;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BigInteger b(DHParameters dHParameters, BigInteger bigInteger) {
        return dHParameters.getG().modPow(bigInteger, dHParameters.getP());
    }
}
