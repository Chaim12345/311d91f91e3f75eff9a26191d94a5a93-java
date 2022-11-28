package org.bouncycastle.crypto;
/* loaded from: classes3.dex */
public abstract class StreamBlockCipher implements BlockCipher, StreamCipher {
    private final BlockCipher cipher;

    /* JADX INFO: Access modifiers changed from: protected */
    public StreamBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
    }

    protected abstract byte a(byte b2);

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        int i5 = i2 + i3;
        if (i5 <= bArr.length) {
            if (i4 + i3 <= bArr2.length) {
                while (i2 < i5) {
                    bArr2[i4] = a(bArr[i2]);
                    i4++;
                    i2++;
                }
                return i3;
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new DataLengthException("input buffer too small");
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public final byte returnByte(byte b2) {
        return a(b2);
    }
}
