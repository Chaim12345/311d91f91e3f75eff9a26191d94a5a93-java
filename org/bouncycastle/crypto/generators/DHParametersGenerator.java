package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.params.DHValidationParameters;
/* loaded from: classes3.dex */
public class DHParametersGenerator {
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private int certainty;
    private SecureRandom random;
    private int size;

    public DHParameters generateParameters() {
        BigInteger[] a2 = DHParametersHelper.a(this.size, this.certainty, this.random);
        BigInteger bigInteger = a2[0];
        BigInteger bigInteger2 = a2[1];
        return new DHParameters(bigInteger, DHParametersHelper.b(bigInteger, bigInteger2, this.random), bigInteger2, TWO, (DHValidationParameters) null);
    }

    public void init(int i2, int i3, SecureRandom secureRandom) {
        this.size = i2;
        this.certainty = i3;
        this.random = secureRandom;
    }
}
