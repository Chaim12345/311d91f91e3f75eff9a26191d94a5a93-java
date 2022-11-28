package org.bouncycastle.crypto.engines;

import androidx.core.view.MotionEventCompat;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
/* loaded from: classes3.dex */
public class IDEAEngine implements BlockCipher {
    private static final int BASE = 65537;
    private static final int MASK = 65535;
    private int[] workingKey = null;

    private int bytesToWord(byte[] bArr, int i2) {
        return ((bArr[i2] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (bArr[i2 + 1] & 255);
    }

    private int[] expandKey(byte[] bArr) {
        int i2;
        int[] iArr = new int[52];
        int i3 = 0;
        if (bArr.length < 16) {
            byte[] bArr2 = new byte[16];
            System.arraycopy(bArr, 0, bArr2, 16 - bArr.length, bArr.length);
            bArr = bArr2;
        }
        while (true) {
            if (i3 >= 8) {
                break;
            }
            iArr[i3] = bytesToWord(bArr, i3 * 2);
            i3++;
        }
        for (i2 = 8; i2 < 52; i2++) {
            int i4 = i2 & 7;
            if (i4 < 6) {
                iArr[i2] = (((iArr[i2 - 7] & 127) << 9) | (iArr[i2 - 6] >> 7)) & 65535;
            } else if (i4 == 6) {
                iArr[i2] = (((iArr[i2 - 7] & 127) << 9) | (iArr[i2 - 14] >> 7)) & 65535;
            } else {
                iArr[i2] = (((iArr[i2 - 15] & 127) << 9) | (iArr[i2 - 14] >> 7)) & 65535;
            }
        }
        return iArr;
    }

    private int[] generateWorkingKey(boolean z, byte[] bArr) {
        return z ? expandKey(bArr) : invertKey(expandKey(bArr));
    }

    private void ideaFunc(int[] iArr, byte[] bArr, int i2, byte[] bArr2, int i3) {
        int bytesToWord = bytesToWord(bArr, i2);
        int bytesToWord2 = bytesToWord(bArr, i2 + 2);
        int bytesToWord3 = bytesToWord(bArr, i2 + 4);
        int bytesToWord4 = bytesToWord(bArr, i2 + 6);
        int i4 = 0;
        int i5 = bytesToWord3;
        int i6 = bytesToWord2;
        int i7 = bytesToWord;
        int i8 = 0;
        while (i4 < 8) {
            int i9 = i8 + 1;
            int mul = mul(i7, iArr[i8]);
            int i10 = i9 + 1;
            int i11 = (i6 + iArr[i9]) & 65535;
            int i12 = i10 + 1;
            int i13 = (i5 + iArr[i10]) & 65535;
            int i14 = i12 + 1;
            int mul2 = mul(bytesToWord4, iArr[i12]);
            int i15 = i14 + 1;
            int mul3 = mul(i13 ^ mul, iArr[i14]);
            int mul4 = mul(((i11 ^ mul2) + mul3) & 65535, iArr[i15]);
            int i16 = (mul3 + mul4) & 65535;
            bytesToWord4 = mul2 ^ i16;
            i5 = i16 ^ i11;
            i4++;
            i6 = i13 ^ mul4;
            i7 = mul ^ mul4;
            i8 = i15 + 1;
        }
        int i17 = i8 + 1;
        wordToBytes(mul(i7, iArr[i8]), bArr2, i3);
        int i18 = i17 + 1;
        wordToBytes(i5 + iArr[i17], bArr2, i3 + 2);
        wordToBytes(i6 + iArr[i18], bArr2, i3 + 4);
        wordToBytes(mul(bytesToWord4, iArr[i18 + 1]), bArr2, i3 + 6);
    }

    private int[] invertKey(int[] iArr) {
        int[] iArr2 = new int[52];
        int mulInv = mulInv(iArr[0]);
        int i2 = 1;
        int a2 = a(iArr[1]);
        int a3 = a(iArr[2]);
        iArr2[51] = mulInv(iArr[3]);
        iArr2[50] = a3;
        iArr2[49] = a2;
        int i3 = 48;
        iArr2[48] = mulInv;
        int i4 = 4;
        while (i2 < 8) {
            int i5 = i4 + 1;
            int i6 = iArr[i4];
            int i7 = i5 + 1;
            int i8 = i3 - 1;
            iArr2[i8] = iArr[i5];
            int i9 = i8 - 1;
            iArr2[i9] = i6;
            int i10 = i7 + 1;
            int mulInv2 = mulInv(iArr[i7]);
            int i11 = i10 + 1;
            int a4 = a(iArr[i10]);
            int i12 = i11 + 1;
            int a5 = a(iArr[i11]);
            int i13 = i9 - 1;
            iArr2[i13] = mulInv(iArr[i12]);
            int i14 = i13 - 1;
            iArr2[i14] = a4;
            int i15 = i14 - 1;
            iArr2[i15] = a5;
            i3 = i15 - 1;
            iArr2[i3] = mulInv2;
            i2++;
            i4 = i12 + 1;
        }
        int i16 = i4 + 1;
        int i17 = iArr[i4];
        int i18 = i16 + 1;
        int i19 = i3 - 1;
        iArr2[i19] = iArr[i16];
        int i20 = i19 - 1;
        iArr2[i20] = i17;
        int i21 = i18 + 1;
        int mulInv3 = mulInv(iArr[i18]);
        int i22 = i21 + 1;
        int a6 = a(iArr[i21]);
        int i23 = i22 + 1;
        int a7 = a(iArr[i22]);
        int i24 = i20 - 1;
        iArr2[i24] = mulInv(iArr[i23]);
        int i25 = i24 - 1;
        iArr2[i25] = a7;
        int i26 = i25 - 1;
        iArr2[i26] = a6;
        iArr2[i26 - 1] = mulInv3;
        return iArr2;
    }

    private int mul(int i2, int i3) {
        int i4;
        if (i2 == 0) {
            i4 = BASE - i3;
        } else if (i3 == 0) {
            i4 = BASE - i2;
        } else {
            int i5 = i2 * i3;
            int i6 = i5 & 65535;
            int i7 = i5 >>> 16;
            i4 = (i6 - i7) + (i6 < i7 ? 1 : 0);
        }
        return i4 & 65535;
    }

    private int mulInv(int i2) {
        if (i2 < 2) {
            return i2;
        }
        int i3 = BASE / i2;
        int i4 = BASE % i2;
        int i5 = 1;
        while (i4 != 1) {
            int i6 = i2 / i4;
            i2 %= i4;
            i5 = (i5 + (i6 * i3)) & 65535;
            if (i2 == 1) {
                return i5;
            }
            int i7 = i4 / i2;
            i4 %= i2;
            i3 = (i3 + (i7 * i5)) & 65535;
        }
        return (1 - i3) & 65535;
    }

    private void wordToBytes(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) (i2 >>> 8);
        bArr[i3 + 1] = (byte) i2;
    }

    int a(int i2) {
        return (0 - i2) & 65535;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "IDEA";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.workingKey = generateWorkingKey(z, ((KeyParameter) cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to IDEA init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int[] iArr = this.workingKey;
        if (iArr != null) {
            if (i2 + 8 <= bArr.length) {
                if (i3 + 8 <= bArr2.length) {
                    ideaFunc(iArr, bArr, i2, bArr2, i3);
                    return 8;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("IDEA engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
