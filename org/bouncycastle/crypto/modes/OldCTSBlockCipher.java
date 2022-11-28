package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
/* loaded from: classes3.dex */
public class OldCTSBlockCipher extends BufferedBlockCipher {
    private int blockSize;

    public OldCTSBlockCipher(BlockCipher blockCipher) {
        if ((blockCipher instanceof OFBBlockCipher) || (blockCipher instanceof CFBBlockCipher)) {
            throw new IllegalArgumentException("CTSBlockCipher can only accept ECB, or CBC ciphers");
        }
        this.f13237d = blockCipher;
        int blockSize = blockCipher.getBlockSize();
        this.blockSize = blockSize;
        this.f13234a = new byte[blockSize * 2];
        this.f13235b = 0;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int doFinal(byte[] bArr, int i2) {
        if (this.f13235b + i2 <= bArr.length) {
            int blockSize = this.f13237d.getBlockSize();
            int i3 = this.f13235b - blockSize;
            byte[] bArr2 = new byte[blockSize];
            if (this.f13236c) {
                this.f13237d.processBlock(this.f13234a, 0, bArr2, 0);
                int i4 = this.f13235b;
                if (i4 < blockSize) {
                    throw new DataLengthException("need at least one block of input for CTS");
                }
                while (true) {
                    byte[] bArr3 = this.f13234a;
                    if (i4 == bArr3.length) {
                        break;
                    }
                    bArr3[i4] = bArr2[i4 - blockSize];
                    i4++;
                }
                for (int i5 = blockSize; i5 != this.f13235b; i5++) {
                    byte[] bArr4 = this.f13234a;
                    bArr4[i5] = (byte) (bArr4[i5] ^ bArr2[i5 - blockSize]);
                }
                BlockCipher blockCipher = this.f13237d;
                if (blockCipher instanceof CBCBlockCipher) {
                    ((CBCBlockCipher) blockCipher).getUnderlyingCipher().processBlock(this.f13234a, blockSize, bArr, i2);
                } else {
                    blockCipher.processBlock(this.f13234a, blockSize, bArr, i2);
                }
                System.arraycopy(bArr2, 0, bArr, i2 + blockSize, i3);
            } else {
                byte[] bArr5 = new byte[blockSize];
                BlockCipher blockCipher2 = this.f13237d;
                if (blockCipher2 instanceof CBCBlockCipher) {
                    ((CBCBlockCipher) blockCipher2).getUnderlyingCipher().processBlock(this.f13234a, 0, bArr2, 0);
                } else {
                    blockCipher2.processBlock(this.f13234a, 0, bArr2, 0);
                }
                for (int i6 = blockSize; i6 != this.f13235b; i6++) {
                    int i7 = i6 - blockSize;
                    bArr5[i7] = (byte) (bArr2[i7] ^ this.f13234a[i6]);
                }
                System.arraycopy(this.f13234a, blockSize, bArr2, 0, i3);
                this.f13237d.processBlock(bArr2, 0, bArr, i2);
                System.arraycopy(bArr5, 0, bArr, i2 + blockSize, i3);
            }
            int i8 = this.f13235b;
            reset();
            return i8;
        }
        throw new OutputLengthException("output buffer to small in doFinal");
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int getOutputSize(int i2) {
        return i2 + this.f13235b;
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
            byte[] bArr3 = this.f13234a;
            int i5 = this.blockSize;
            System.arraycopy(bArr3, i5, bArr3, 0, i5);
            this.f13235b = this.blockSize;
            i4 = processBlock;
        }
        byte[] bArr4 = this.f13234a;
        int i6 = this.f13235b;
        this.f13235b = i6 + 1;
        bArr4[i6] = b2;
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
                    int processBlock = this.f13237d.processBlock(this.f13234a, 0, bArr2, i4) + 0;
                    byte[] bArr4 = this.f13234a;
                    System.arraycopy(bArr4, blockSize, bArr4, 0, blockSize);
                    this.f13235b = blockSize;
                    i3 -= i6;
                    i2 += i6;
                    while (i3 > blockSize) {
                        System.arraycopy(bArr, i2, this.f13234a, this.f13235b, blockSize);
                        processBlock += this.f13237d.processBlock(this.f13234a, 0, bArr2, i4 + processBlock);
                        byte[] bArr5 = this.f13234a;
                        System.arraycopy(bArr5, blockSize, bArr5, 0, blockSize);
                        i3 -= blockSize;
                        i2 += blockSize;
                    }
                    i7 = processBlock;
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
