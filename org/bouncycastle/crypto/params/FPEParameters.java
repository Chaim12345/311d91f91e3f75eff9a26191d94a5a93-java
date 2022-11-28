package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.util.Arrays;
/* loaded from: classes3.dex */
public final class FPEParameters implements CipherParameters {
    private final KeyParameter key;
    private final int radix;
    private final byte[] tweak;
    private final boolean useInverse;

    public FPEParameters(KeyParameter keyParameter, int i2, byte[] bArr) {
        this(keyParameter, i2, bArr, false);
    }

    public FPEParameters(KeyParameter keyParameter, int i2, byte[] bArr, boolean z) {
        this.key = keyParameter;
        this.radix = i2;
        this.tweak = Arrays.clone(bArr);
        this.useInverse = z;
    }

    public KeyParameter getKey() {
        return this.key;
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
