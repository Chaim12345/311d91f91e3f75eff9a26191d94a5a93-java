package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.util.Arrays;
/* loaded from: classes3.dex */
public class FPEParameterSpec implements AlgorithmParameterSpec {
    private final int radix;
    private final byte[] tweak;
    private final boolean useInverse;

    public FPEParameterSpec(int i2, byte[] bArr) {
        this(i2, bArr, false);
    }

    public FPEParameterSpec(int i2, byte[] bArr, boolean z) {
        this.radix = i2;
        this.tweak = Arrays.clone(bArr);
        this.useInverse = z;
    }

    public int getRadix() {
        return this.radix;
    }

    public byte[] getTweak() {
        return Arrays.clone(this.tweak);
    }

    public boolean isUsingInverseFunction() {
        return this.useInverse;
    }
}
