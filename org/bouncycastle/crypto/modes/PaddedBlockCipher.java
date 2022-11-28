package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
/* loaded from: classes3.dex */
public class PaddedBlockCipher extends BufferedBlockCipher {
    public PaddedBlockCipher(BlockCipher blockCipher) {
        this.f13237d = blockCipher;
        this.f13234a = new byte[blockCipher.getBlockSize()];
        this.f13235b = 0;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int doFinal(byte[] bArr, int i2) {
        int i3;
        int i4;
        int blockSize = this.f13237d.getBlockSize();
        if (this.f13236c) {
            if (this.f13235b != blockSize) {
                i4 = 0;
            } else if ((blockSize * 2) + i2 > bArr.length) {
                throw new OutputLengthException("output buffer too short");
            } else {
                i4 = this.f13237d.processBlock(this.f13234a, 0, bArr, i2);
                this.f13235b = 0;
            }
            byte b2 = (byte) (blockSize - this.f13235b);
            while (true) {
                int i5 = this.f13235b;
                if (i5 >= blockSize) {
                    break;
                }
                this.f13234a[i5] = b2;
                this.f13235b = i5 + 1;
            }
            i3 = i4 + this.f13237d.processBlock(this.f13234a, 0, bArr, i2 + i4);
        } else if (this.f13235b != blockSize) {
            throw new DataLengthException("last block incomplete in decryption");
        } else {
            BlockCipher blockCipher = this.f13237d;
            byte[] bArr2 = this.f13234a;
            int processBlock = blockCipher.processBlock(bArr2, 0, bArr2, 0);
            this.f13235b = 0;
            byte[] bArr3 = this.f13234a;
            int i6 = bArr3[blockSize - 1] & 255;
            if (i6 > blockSize) {
                throw new InvalidCipherTextException("pad block corrupted");
            }
            i3 = processBlock - i6;
            System.arraycopy(bArr3, 0, bArr, i2, i3);
        }
        reset();
        return i3;
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
        return length == 0 ? i3 - bArr.length : i3 - length;
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
