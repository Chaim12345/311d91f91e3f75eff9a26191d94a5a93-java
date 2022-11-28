package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.params.ElGamalParameters;
/* loaded from: classes3.dex */
public class ElGamalParametersGenerator {
    private int certainty;
    private SecureRandom random;
    private int size;

    public ElGamalParameters generateParameters() {
        BigInteger[] a2 = DHParametersHelper.a(this.size, this.certainty, this.random);
        BigInteger bigInteger = a2[0];
        return new ElGamalParameters(bigInteger, DHParametersHelper.b(bigInteger, a2[1], this.random));
    }

    public void init(int i2, int i3, SecureRandom secureRandom) {
        this.size = i2;
        this.certainty = i3;
        this.random = secureRandom;
    }
}
