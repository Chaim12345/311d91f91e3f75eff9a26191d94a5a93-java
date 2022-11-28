package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
/* loaded from: classes3.dex */
public class NISTCTSBlockCipher extends BufferedBlockCipher {
    public static final int CS1 = 1;
    public static final int CS2 = 2;
    public static final int CS3 = 3;
    private final int blockSize;
    private final int type;

    public NISTCTSBlockCipher(int i2, BlockCipher blockCipher) {
        this.type = i2;
        this.f13237d = new CBCBlockCipher(blockCipher);
        int blockSize = blockCipher.getBlockSize();
        this.blockSize = blockSize;
        this.f13234a = new byte[blockSize * 2];
        this.f13235b = 0;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int doFinal(byte[] bArr, int i2) {
        if (this.f13235b + i2 <= bArr.length) {
            int blockSize = this.f13237d.getBlockSize();
            int i3 = this.f13235b;
            int i4 = i3 - blockSize;
            byte[] bArr2 = new byte[blockSize];
            if (this.f13236c) {
                if (i3 < blockSize) {
                    throw new DataLengthException("need at least one block of input for NISTCTS");
                }
                if (i3 > blockSize) {
                    byte[] bArr3 = new byte[blockSize];
                    int i5 = this.type;
                    if (i5 == 2 || i5 == 3) {
                        this.f13237d.processBlock(this.f13234a, 0, bArr2, 0);
                        System.arraycopy(this.f13234a, blockSize, bArr3, 0, i4);
                        this.f13237d.processBlock(bArr3, 0, bArr3, 0);
                        if (this.type == 2 && i4 == blockSize) {
                            System.arraycopy(bArr2, 0, bArr, i2, blockSize);
                            System.arraycopy(bArr3, 0, bArr, i2 + blockSize, i4);
                        } else {
                            System.arraycopy(bArr3, 0, bArr, i2, blockSize);
                            System.arraycopy(bArr2, 0, bArr, i2 + blockSize, i4);
                        }
                    } else {
                        System.arraycopy(this.f13234a, 0, bArr2, 0, blockSize);
                        this.f13237d.processBlock(bArr2, 0, bArr2, 0);
                        System.arraycopy(bArr2, 0, bArr, i2, i4);
                        System.arraycopy(this.f13234a, this.f13235b - i4, bArr3, 0, i4);
                        this.f13237d.processBlock(bArr3, 0, bArr3, 0);
                        System.arraycopy(bArr3, 0, bArr, i2 + i4, blockSize);
                    }
                } else {
                    this.f13237d.processBlock(this.f13234a, 0, bArr2, 0);
                    System.arraycopy(bArr2, 0, bArr, i2, blockSize);
                }
            } else if (i3 < blockSize) {
                throw new DataLengthException("need at least one block of input for CTS");
            } else {
                byte[] bArr4 = new byte[blockSize];
                if (i3 > blockSize) {
                    int i6 = this.type;
                    if (i6 == 3 || (i6 == 2 && (this.f13234a.length - i3) % blockSize != 0)) {
                        BlockCipher blockCipher = this.f13237d;
                        if (blockCipher instanceof CBCBlockCipher) {
                            ((CBCBlockCipher) blockCipher).getUnderlyingCipher().processBlock(this.f13234a, 0, bArr2, 0);
                        } else {
                            blockCipher.processBlock(this.f13234a, 0, bArr2, 0);
                        }
                        for (int i7 = blockSize; i7 != this.f13235b; i7++) {
                            int i8 = i7 - blockSize;
                            bArr4[i8] = (byte) (bArr2[i8] ^ this.f13234a[i7]);
                        }
                        System.arraycopy(this.f13234a, blockSize, bArr2, 0, i4);
                        this.f13237d.processBlock(bArr2, 0, bArr, i2);
                    } else {
                        ((CBCBlockCipher) this.f13237d).getUnderlyingCipher().processBlock(this.f13234a, this.f13235b - blockSize, bArr4, 0);
                        System.arraycopy(this.f13234a, 0, bArr2, 0, blockSize);
                        if (i4 != blockSize) {
                            System.arraycopy(bArr4, i4, bArr2, i4, blockSize - i4);
                        }
                        this.f13237d.processBlock(bArr2, 0, bArr2, 0);
                        System.arraycopy(bArr2, 0, bArr, i2, blockSize);
                        for (int i9 = 0; i9 != i4; i9++) {
                            bArr4[i9] = (byte) (bArr4[i9] ^ this.f13234a[i9]);
                        }
                    }
                    System.arraycopy(bArr4, 0, bArr, i2 + blockSize, i4);
                } else {
                    this.f13237d.processBlock(this.f13234a, 0, bArr2, 0);
                    System.arraycopy(bArr2, 0, bArr, i2, blockSize);
                }
            }
            int i10 = this.f13235b;
            reset();
            return i10;
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
