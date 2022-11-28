package org.bouncycastle.crypto;
/* loaded from: classes3.dex */
public class BufferedBlockCipher {

    /* renamed from: a  reason: collision with root package name */
    protected byte[] f13234a;

    /* renamed from: b  reason: collision with root package name */
    protected int f13235b;

    /* renamed from: c  reason: collision with root package name */
    protected boolean f13236c;

    /* renamed from: d  reason: collision with root package name */
    protected BlockCipher f13237d;

    /* renamed from: e  reason: collision with root package name */
    protected boolean f13238e;

    /* renamed from: f  reason: collision with root package name */
    protected boolean f13239f;

    /* JADX INFO: Access modifiers changed from: protected */
    public BufferedBlockCipher() {
    }

    public BufferedBlockCipher(BlockCipher blockCipher) {
        this.f13237d = blockCipher;
        this.f13234a = new byte[blockCipher.getBlockSize()];
        boolean z = false;
        this.f13235b = 0;
        String algorithmName = blockCipher.getAlgorithmName();
        int indexOf = algorithmName.indexOf(47) + 1;
        boolean z2 = indexOf > 0 && algorithmName.startsWith("PGP", indexOf);
        this.f13239f = z2;
        if (z2 || (blockCipher instanceof StreamCipher)) {
            this.f13238e = true;
            return;
        }
        if (indexOf > 0 && algorithmName.startsWith("OpenPGP", indexOf)) {
            z = true;
        }
        this.f13238e = z;
    }

    public int doFinal(byte[] bArr, int i2) {
        try {
            int i3 = this.f13235b;
            if (i2 + i3 <= bArr.length) {
                int i4 = 0;
                if (i3 != 0) {
                    if (!this.f13238e) {
                        throw new DataLengthException("data not block size aligned");
                    }
                    BlockCipher blockCipher = this.f13237d;
                    byte[] bArr2 = this.f13234a;
                    blockCipher.processBlock(bArr2, 0, bArr2, 0);
                    int i5 = this.f13235b;
                    this.f13235b = 0;
                    System.arraycopy(this.f13234a, 0, bArr, i2, i5);
                    i4 = i5;
                }
                return i4;
            }
            throw new OutputLengthException("output buffer too short for doFinal()");
        } finally {
            reset();
        }
    }

    public int getBlockSize() {
        return this.f13237d.getBlockSize();
    }

    public int getOutputSize(int i2) {
        return i2 + this.f13235b;
    }

    public BlockCipher getUnderlyingCipher() {
        return this.f13237d;
    }

    public int getUpdateOutputSize(int i2) {
        int length;
        int i3;
        int i4 = i2 + this.f13235b;
        if (!this.f13239f) {
            length = this.f13234a.length;
        } else if (this.f13236c) {
            i3 = (i4 % this.f13234a.length) - (this.f13237d.getBlockSize() + 2);
            return i4 - i3;
        } else {
            length = this.f13234a.length;
        }
        i3 = i4 % length;
        return i4 - i3;
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        this.f13236c = z;
        reset();
        this.f13237d.init(z, cipherParameters);
    }

    public int processByte(byte b2, byte[] bArr, int i2) {
        byte[] bArr2 = this.f13234a;
        int i3 = this.f13235b;
        int i4 = i3 + 1;
        this.f13235b = i4;
        bArr2[i3] = b2;
        if (i4 == bArr2.length) {
            int processBlock = this.f13237d.processBlock(bArr2, 0, bArr, i2);
            this.f13235b = 0;
            return processBlock;
        }
        return 0;
    }

    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        int i5;
        if (i3 >= 0) {
            int blockSize = getBlockSize();
            int updateOutputSize = getUpdateOutputSize(i3);
            if (updateOutputSize <= 0 || updateOutputSize + i4 <= bArr2.length) {
                byte[] bArr3 = this.f13234a;
                int length = bArr3.length;
                int i6 = this.f13235b;
                int i7 = length - i6;
                if (i3 > i7) {
                    System.arraycopy(bArr, i2, bArr3, i6, i7);
                    i5 = this.f13237d.processBlock(this.f13234a, 0, bArr2, i4) + 0;
                    this.f13235b = 0;
                    i3 -= i7;
                    i2 += i7;
                    while (i3 > this.f13234a.length) {
                        i5 += this.f13237d.processBlock(bArr, i2, bArr2, i4 + i5);
                        i3 -= blockSize;
                        i2 += blockSize;
                    }
                } else {
                    i5 = 0;
                }
                System.arraycopy(bArr, i2, this.f13234a, this.f13235b, i3);
                int i8 = this.f13235b + i3;
                this.f13235b = i8;
                byte[] bArr4 = this.f13234a;
                if (i8 == bArr4.length) {
                    int processBlock = i5 + this.f13237d.processBlock(bArr4, 0, bArr2, i4 + i5);
                    this.f13235b = 0;
                    return processBlock;
                }
                return i5;
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new IllegalArgumentException("Can't have a negative input length!");
    }

    public void reset() {
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f13234a;
            if (i2 >= bArr.length) {
                this.f13235b = 0;
                this.f13237d.reset();
                return;
            }
            bArr[i2] = 0;
            i2++;
        }
    }
}
