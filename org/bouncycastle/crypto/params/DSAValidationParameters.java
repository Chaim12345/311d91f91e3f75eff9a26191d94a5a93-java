package org.bouncycastle.crypto.params;

import org.bouncycastle.util.Arrays;
/* loaded from: classes3.dex */
public class DSAValidationParameters {
    private int counter;
    private byte[] seed;
    private int usageIndex;

    public DSAValidationParameters(byte[] bArr, int i2) {
        this(bArr, i2, -1);
    }

    public DSAValidationParameters(byte[] bArr, int i2, int i3) {
        this.seed = Arrays.clone(bArr);
        this.counter = i2;
        this.usageIndex = i3;
    }

    public boolean equals(Object obj) {
        if (obj instanceof DSAValidationParameters) {
            DSAValidationParameters dSAValidationParameters = (DSAValidationParameters) obj;
            if (dSAValidationParameters.counter != this.counter) {
                return false;
            }
            return Arrays.areEqual(this.seed, dSAValidationParameters.seed);
        }
        return false;
    }

    public int getCounter() {
        return this.counter;
    }

    public byte[] getSeed() {
        return Arrays.clone(this.seed);
    }

    public int getUsageIndex() {
        return this.usageIndex;
    }

    public int hashCode() {
        return this.counter ^ Arrays.hashCode(this.seed);
    }
}
