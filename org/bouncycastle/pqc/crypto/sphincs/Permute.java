package org.bouncycastle.pqc.crypto.sphincs;

import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
class Permute {
    private static final int CHACHA_ROUNDS = 12;

    protected static int b(int i2, int i3) {
        return (i2 >>> (-i3)) | (i2 << i3);
    }

    public static void permute(int i2, int[] iArr) {
        int i3 = 16;
        if (iArr.length != 16) {
            throw new IllegalArgumentException();
        }
        if (i2 % 2 != 0) {
            throw new IllegalArgumentException("Number of rounds must be even");
        }
        char c2 = 0;
        int i4 = iArr[0];
        int i5 = iArr[1];
        int i6 = iArr[2];
        int i7 = iArr[3];
        int i8 = iArr[4];
        int i9 = iArr[5];
        int i10 = iArr[6];
        int i11 = 7;
        int i12 = iArr[7];
        int i13 = 8;
        int i14 = iArr[8];
        int i15 = iArr[9];
        int i16 = iArr[10];
        int i17 = iArr[11];
        int i18 = iArr[12];
        int i19 = iArr[13];
        int i20 = iArr[14];
        int i21 = iArr[15];
        int i22 = i20;
        int i23 = i19;
        int i24 = i18;
        int i25 = i17;
        int i26 = i16;
        int i27 = i15;
        int i28 = i14;
        int i29 = i12;
        int i30 = i10;
        int i31 = i9;
        int i32 = i8;
        int i33 = i7;
        int i34 = i6;
        int i35 = i5;
        int i36 = i4;
        int i37 = i2;
        while (i37 > 0) {
            int i38 = i36 + i32;
            int b2 = b(i24 ^ i38, i3);
            int i39 = i28 + b2;
            int b3 = b(i32 ^ i39, 12);
            int i40 = i38 + b3;
            int b4 = b(b2 ^ i40, i13);
            int i41 = i39 + b4;
            int b5 = b(b3 ^ i41, i11);
            int i42 = i35 + i31;
            int b6 = b(i23 ^ i42, i3);
            int i43 = i27 + b6;
            int b7 = b(i31 ^ i43, 12);
            int i44 = i42 + b7;
            int b8 = b(b6 ^ i44, i13);
            int i45 = i43 + b8;
            int b9 = b(b7 ^ i45, i11);
            int i46 = i34 + i30;
            int b10 = b(i22 ^ i46, i3);
            int i47 = i26 + b10;
            int b11 = b(i30 ^ i47, 12);
            int i48 = i46 + b11;
            int b12 = b(b10 ^ i48, i13);
            int i49 = i47 + b12;
            int b13 = b(b11 ^ i49, i11);
            int i50 = i33 + i29;
            int b14 = b(i21 ^ i50, i3);
            int i51 = i25 + b14;
            int b15 = b(i29 ^ i51, 12);
            int i52 = i50 + b15;
            int b16 = b(b14 ^ i52, i13);
            int i53 = i51 + b16;
            int b17 = b(b15 ^ i53, 7);
            int i54 = i40 + b9;
            int b18 = b(b16 ^ i54, 16);
            int i55 = i49 + b18;
            int b19 = b(b9 ^ i55, 12);
            i36 = i54 + b19;
            i21 = b(b18 ^ i36, 8);
            i26 = i55 + i21;
            i31 = b(b19 ^ i26, 7);
            int i56 = i44 + b13;
            int b20 = b(b4 ^ i56, 16);
            int i57 = i53 + b20;
            int b21 = b(b13 ^ i57, 12);
            i35 = i56 + b21;
            i24 = b(b20 ^ i35, 8);
            i25 = i57 + i24;
            i30 = b(b21 ^ i25, 7);
            int i58 = i48 + b17;
            int b22 = b(b8 ^ i58, 16);
            int i59 = i41 + b22;
            int b23 = b(b17 ^ i59, 12);
            i34 = i58 + b23;
            i23 = b(b22 ^ i34, 8);
            i28 = i59 + i23;
            i29 = b(b23 ^ i28, 7);
            int i60 = i52 + b5;
            i3 = 16;
            int b24 = b(b12 ^ i60, 16);
            int i61 = i45 + b24;
            int b25 = b(b5 ^ i61, 12);
            i33 = i60 + b25;
            i22 = b(b24 ^ i33, 8);
            i27 = i61 + i22;
            i32 = b(b25 ^ i27, 7);
            i37 -= 2;
            i11 = 7;
            c2 = 0;
            i13 = 8;
        }
        iArr[c2] = i36;
        iArr[1] = i35;
        iArr[2] = i34;
        iArr[3] = i33;
        iArr[4] = i32;
        iArr[5] = i31;
        iArr[6] = i30;
        iArr[i11] = i29;
        iArr[8] = i28;
        iArr[9] = i27;
        iArr[10] = i26;
        iArr[11] = i25;
        iArr[12] = i24;
        iArr[13] = i23;
        iArr[14] = i22;
        iArr[15] = i21;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(byte[] bArr, byte[] bArr2) {
        int[] iArr = new int[16];
        for (int i2 = 0; i2 < 16; i2++) {
            iArr[i2] = Pack.littleEndianToInt(bArr2, i2 * 4);
        }
        permute(12, iArr);
        for (int i3 = 0; i3 < 16; i3++) {
            Pack.intToLittleEndian(iArr[i3], bArr, i3 * 4);
        }
    }
}
