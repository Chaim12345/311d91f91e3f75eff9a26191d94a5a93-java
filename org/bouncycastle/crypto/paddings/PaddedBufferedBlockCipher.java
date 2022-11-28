package org.bouncycastle.crypto.paddings;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.ParametersWithRandom;
/* loaded from: classes3.dex */
public class PaddedBufferedBlockCipher extends BufferedBlockCipher {

    /* renamed from: g  reason: collision with root package name */
    BlockCipherPadding f13462g;

    public PaddedBufferedBlockCipher(BlockCipher blockCipher) {
        this(blockCipher, new PKCS7Padding());
    }

    public PaddedBufferedBlockCipher(BlockCipher blockCipher, BlockCipherPadding blockCipherPadding) {
        this.f13237d = blockCipher;
        this.f13462g = blockCipherPadding;
        this.f13234a = new byte[blockCipher.getBlockSize()];
        this.f13235b = 0;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int doFinal(byte[] bArr, int i2) {
        int padCount;
        int i3;
        int blockSize = this.f13237d.getBlockSize();
        if (this.f13236c) {
            if (this.f13235b != blockSize) {
                i3 = 0;
            } else if ((blockSize * 2) + i2 > bArr.length) {
                reset();
                throw new OutputLengthException("output buffer too short");
            } else {
                i3 = this.f13237d.processBlock(this.f13234a, 0, bArr, i2);
                this.f13235b = 0;
            }
            this.f13462g.addPadding(this.f13234a, this.f13235b);
            padCount = i3 + this.f13237d.processBlock(this.f13234a, 0, bArr, i2 + i3);
        } else if (this.f13235b != blockSize) {
            reset();
            throw new DataLengthException("last block incomplete in decryption");
        } else {
            BlockCipher blockCipher = this.f13237d;
            byte[] bArr2 = this.f13234a;
            int processBlock = blockCipher.processBlock(bArr2, 0, bArr2, 0);
            this.f13235b = 0;
            try {
                padCount = processBlock - this.f13462g.padCount(this.f13234a);
                System.arraycopy(this.f13234a, 0, bArr, i2, padCount);
            } finally {
                reset();
            }
        }
        return padCount;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int getOutputSize(int i2) {
        int i3 = i2 + this.f13235b;
        byte[] bArr = this.f13234a;
        int length = i3 % bArr.length;
        if (length != 0) {
            i3 -= length;
        } else if (!this.f13236c) {
            return i3;
        }
        return i3 + bArr.length;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int getUpdateOutputSize(int i2) {
        int i3 = i2 + this.f13235b;
        byte[] bArr = this.f13234a;
        int length = i3 % bArr.length;
        return length == 0 ? Math.max(0, i3 - bArr.length) : i3 - length;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        BlockCipher blockCipher;
        this.f13236c = z;
        reset();
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.f13462g.init(parametersWithRandom.getRandom());
            blockCipher = this.f13237d;
            cipherParameters = parametersWithRandom.getParameters();
        } else {
            this.f13462g.init(null);
            blockCipher = this.f13237d;
        }
        blockCipher.init(z, cipherParameters);
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int processByte(byte b2, byte[] bArr, int i2) {
        int i3 = this.f13235b;
        byte[] bArr2 = this.f13234a;
        int i4 = 0;
        if (i3 == bArr2.length) {
            int processBlock = this.f13237d.processBlock(bArr2, 0, bArr, i2);
            this.f13235b = 0;
            i4 = processBlock;
        }
        byte[] bArr3 = this.f13234a;
        int i5 = this.f13235b;
        this.f13235b = i5 + 1;
        bArr3[i5] = b2;
        return i4;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        if (i3 >= 0) {
            int blockSize = getBlockSize();
            int updateOutputSize = getUpdateOutputSize(i3);
            if (updateOutputSize <= 0 || updateOutputSize + i4 <= bArr2.length) {
                byte[] bArr3 = this.f13234a;
                int length = bArr3.length;
                int i5 = this.f13235b;
                int i6 = length - i5;
                int i7 = 0;
                if (i3 > i6) {
                    System.arraycopy(bArr, i2, bArr3, i5, i6);
                    this.f13235b = 0;
                    i3 -= i6;
                    i2 += i6;
                    i7 = this.f13237d.processBlock(this.f13234a, 0, bArr2, i4) + 0;
                    while (i3 > this.f13234a.length) {
                        i7 += this.f13237d.processBlock(bArr, i2, bArr2, i4 + i7);
                        i3 -= blockSize;
                        i2 += blockSize;
                    }
                }
                System.arraycopy(bArr, i2, this.f13234a, this.f13235b, i3);
                this.f13235b += i3;
                return i7;
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new IllegalArgumentException("Can't have a negative input length!");
    }
}
