package org.bouncycastle.crypto.fpe;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.FPEParameters;
import org.bouncycastle.util.Pack;
/* loaded from: classes3.dex */
public abstract class FPEEngine {

    /* renamed from: a  reason: collision with root package name */
    protected final BlockCipher f13407a;

    /* renamed from: b  reason: collision with root package name */
    protected boolean f13408b;

    /* renamed from: c  reason: collision with root package name */
    protected FPEParameters f13409c;

    /* JADX INFO: Access modifiers changed from: protected */
    public FPEEngine(BlockCipher blockCipher) {
        this.f13407a = blockCipher;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static byte[] c(short[] sArr) {
        byte[] bArr = new byte[sArr.length * 2];
        for (int i2 = 0; i2 != sArr.length; i2++) {
            Pack.shortToBigEndian(sArr[i2], bArr, i2 * 2);
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static short[] d(byte[] bArr) {
        if ((bArr.length & 1) == 0) {
            int length = bArr.length / 2;
            short[] sArr = new short[length];
            for (int i2 = 0; i2 != length; i2++) {
                sArr[i2] = Pack.bigEndianToShort(bArr, i2 * 2);
            }
            return sArr;
        }
        throw new IllegalArgumentException("data must be an even number of bytes for a wide radix");
    }

    protected abstract int a(byte[] bArr, int i2, int i3, byte[] bArr2, int i4);

    protected abstract int b(byte[] bArr, int i2, int i3, byte[] bArr2, int i4);

    public abstract String getAlgorithmName();

    public abstract void init(boolean z, CipherParameters cipherParameters);

    public int processBlock(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        if (this.f13409c != null) {
            if (i3 >= 0) {
                if (bArr == null || bArr2 == null) {
                    throw new NullPointerException("buffer value is null");
                }
                if (bArr.length >= i2 + i3) {
                    if (bArr2.length >= i4 + i3) {
                        return this.f13408b ? b(bArr, i2, i3, bArr2, i4) : a(bArr, i2, i3, bArr2, i4);
                    }
                    throw new OutputLengthException("output buffer too short");
                }
                throw new DataLengthException("input buffer too short");
            }
            throw new IllegalArgumentException("input length cannot be negative");
        }
        throw new IllegalStateException("FPE engine not initialized");
    }
}
