package org.bouncycastle.math.ec.rfc7748;

import com.google.common.base.Ascii;
import org.bouncycastle.math.raw.Mod;
/* loaded from: classes4.dex */
public abstract class X448Field {
    private static final int M28 = 268435455;
    private static final int[] P32 = {-1, -1, -1, -1, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1};
    public static final int SIZE = 16;
    private static final long U32 = 4294967295L;

    public static void add(int[] iArr, int[] iArr2, int[] iArr3) {
        for (int i2 = 0; i2 < 16; i2++) {
            iArr3[i2] = iArr[i2] + iArr2[i2];
        }
    }

    public static void addOne(int[] iArr) {
        iArr[0] = iArr[0] + 1;
    }

    public static void addOne(int[] iArr, int i2) {
        iArr[i2] = iArr[i2] + 1;
    }

    public static int areEqual(int[] iArr, int[] iArr2) {
        int i2 = 0;
        for (int i3 = 0; i3 < 16; i3++) {
            i2 |= iArr[i3] ^ iArr2[i3];
        }
        return (((i2 >>> 1) | (i2 & 1)) - 1) >> 31;
    }

    public static boolean areEqualVar(int[] iArr, int[] iArr2) {
        return areEqual(iArr, iArr2) != 0;
    }

    public static void carry(int[] iArr) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        int i5 = iArr[3];
        int i6 = iArr[4];
        int i7 = iArr[5];
        int i8 = iArr[6];
        int i9 = iArr[7];
        int i10 = iArr[8];
        int i11 = iArr[9];
        int i12 = iArr[10];
        int i13 = iArr[11];
        int i14 = iArr[12];
        int i15 = iArr[13];
        int i16 = iArr[14];
        int i17 = iArr[15];
        int i18 = i3 + (i2 >>> 28);
        int i19 = i2 & M28;
        int i20 = i7 + (i6 >>> 28);
        int i21 = i6 & M28;
        int i22 = i11 + (i10 >>> 28);
        int i23 = i10 & M28;
        int i24 = i15 + (i14 >>> 28);
        int i25 = i14 & M28;
        int i26 = i4 + (i18 >>> 28);
        int i27 = i18 & M28;
        int i28 = i8 + (i20 >>> 28);
        int i29 = i20 & M28;
        int i30 = i12 + (i22 >>> 28);
        int i31 = i22 & M28;
        int i32 = i16 + (i24 >>> 28);
        int i33 = i24 & M28;
        int i34 = i5 + (i26 >>> 28);
        int i35 = i26 & M28;
        int i36 = i9 + (i28 >>> 28);
        int i37 = i28 & M28;
        int i38 = i13 + (i30 >>> 28);
        int i39 = i30 & M28;
        int i40 = i17 + (i32 >>> 28);
        int i41 = i32 & M28;
        int i42 = i40 >>> 28;
        int i43 = i40 & M28;
        int i44 = i19 + i42;
        int i45 = i21 + (i34 >>> 28);
        int i46 = i34 & M28;
        int i47 = i23 + i42 + (i36 >>> 28);
        int i48 = i36 & M28;
        int i49 = i25 + (i38 >>> 28);
        int i50 = i38 & M28;
        int i51 = i27 + (i44 >>> 28);
        int i52 = i44 & M28;
        int i53 = i29 + (i45 >>> 28);
        int i54 = i45 & M28;
        int i55 = i31 + (i47 >>> 28);
        int i56 = i47 & M28;
        int i57 = i49 & M28;
        iArr[0] = i52;
        iArr[1] = i51;
        iArr[2] = i35;
        iArr[3] = i46;
        iArr[4] = i54;
        iArr[5] = i53;
        iArr[6] = i37;
        iArr[7] = i48;
        iArr[8] = i56;
        iArr[9] = i55;
        iArr[10] = i39;
        iArr[11] = i50;
        iArr[12] = i57;
        iArr[13] = i33 + (i49 >>> 28);
        iArr[14] = i41;
        iArr[15] = i43;
    }

    public static void cmov(int i2, int[] iArr, int i3, int[] iArr2, int i4) {
        for (int i5 = 0; i5 < 16; i5++) {
            int i6 = i4 + i5;
            int i7 = iArr2[i6];
            iArr2[i6] = i7 ^ ((iArr[i3 + i5] ^ i7) & i2);
        }
    }

    public static void cnegate(int i2, int[] iArr) {
        int[] create = create();
        sub(create, iArr, create);
        cmov(-i2, create, 0, iArr, 0);
    }

    public static void copy(int[] iArr, int i2, int[] iArr2, int i3) {
        for (int i4 = 0; i4 < 16; i4++) {
            iArr2[i3 + i4] = iArr[i2 + i4];
        }
    }

    public static int[] create() {
        return new int[16];
    }

    public static int[] createTable(int i2) {
        return new int[i2 * 16];
    }

    public static void cswap(int i2, int[] iArr, int[] iArr2) {
        int i3 = 0 - i2;
        for (int i4 = 0; i4 < 16; i4++) {
            int i5 = iArr[i4];
            int i6 = iArr2[i4];
            int i7 = (i5 ^ i6) & i3;
            iArr[i4] = i5 ^ i7;
            iArr2[i4] = i6 ^ i7;
        }
    }

    public static void decode(byte[] bArr, int i2, int[] iArr) {
        decode56(bArr, i2, iArr, 0);
        decode56(bArr, i2 + 7, iArr, 2);
        decode56(bArr, i2 + 14, iArr, 4);
        decode56(bArr, i2 + 21, iArr, 6);
        decode56(bArr, i2 + 28, iArr, 8);
        decode56(bArr, i2 + 35, iArr, 10);
        decode56(bArr, i2 + 42, iArr, 12);
        decode56(bArr, i2 + 49, iArr, 14);
    }

    public static void decode(int[] iArr, int i2, int[] iArr2) {
        decode224(iArr, i2, iArr2, 0);
        decode224(iArr, i2 + 7, iArr2, 8);
    }

    private static void decode224(int[] iArr, int i2, int[] iArr2, int i3) {
        int i4 = iArr[i2 + 0];
        int i5 = iArr[i2 + 1];
        int i6 = iArr[i2 + 2];
        int i7 = iArr[i2 + 3];
        int i8 = iArr[i2 + 4];
        int i9 = iArr[i2 + 5];
        int i10 = iArr[i2 + 6];
        iArr2[i3 + 0] = i4 & M28;
        iArr2[i3 + 1] = ((i4 >>> 28) | (i5 << 4)) & M28;
        iArr2[i3 + 2] = ((i5 >>> 24) | (i6 << 8)) & M28;
        iArr2[i3 + 3] = ((i6 >>> 20) | (i7 << 12)) & M28;
        iArr2[i3 + 4] = ((i7 >>> 16) | (i8 << 16)) & M28;
        iArr2[i3 + 5] = ((i8 >>> 12) | (i9 << 20)) & M28;
        iArr2[i3 + 6] = ((i9 >>> 8) | (i10 << 24)) & M28;
        iArr2[i3 + 7] = i10 >>> 4;
    }

    private static int decode24(byte[] bArr, int i2) {
        int i3 = i2 + 1;
        return ((bArr[i3 + 1] & 255) << 16) | (bArr[i2] & 255) | ((bArr[i3] & 255) << 8);
    }

    private static int decode32(byte[] bArr, int i2) {
        int i3 = i2 + 1;
        int i4 = i3 + 1;
        return (bArr[i4 + 1] << Ascii.CAN) | (bArr[i2] & 255) | ((bArr[i3] & 255) << 8) | ((bArr[i4] & 255) << 16);
    }

    private static void decode56(byte[] bArr, int i2, int[] iArr, int i3) {
        int decode32 = decode32(bArr, i2);
        int decode24 = decode24(bArr, i2 + 4);
        iArr[i3] = M28 & decode32;
        iArr[i3 + 1] = (decode24 << 4) | (decode32 >>> 28);
    }

    public static void encode(int[] iArr, byte[] bArr, int i2) {
        encode56(iArr, 0, bArr, i2);
        encode56(iArr, 2, bArr, i2 + 7);
        encode56(iArr, 4, bArr, i2 + 14);
        encode56(iArr, 6, bArr, i2 + 21);
        encode56(iArr, 8, bArr, i2 + 28);
        encode56(iArr, 10, bArr, i2 + 35);
        encode56(iArr, 12, bArr, i2 + 42);
        encode56(iArr, 14, bArr, i2 + 49);
    }

    public static void encode(int[] iArr, int[] iArr2, int i2) {
        encode224(iArr, 0, iArr2, i2);
        encode224(iArr, 8, iArr2, i2 + 7);
    }

    private static void encode224(int[] iArr, int i2, int[] iArr2, int i3) {
        int i4 = iArr[i2 + 0];
        int i5 = iArr[i2 + 1];
        int i6 = iArr[i2 + 2];
        int i7 = iArr[i2 + 3];
        int i8 = iArr[i2 + 4];
        int i9 = iArr[i2 + 5];
        int i10 = iArr[i2 + 6];
        int i11 = iArr[i2 + 7];
        iArr2[i3 + 0] = i4 | (i5 << 28);
        iArr2[i3 + 1] = (i5 >>> 4) | (i6 << 24);
        iArr2[i3 + 2] = (i6 >>> 8) | (i7 << 20);
        iArr2[i3 + 3] = (i7 >>> 12) | (i8 << 16);
        iArr2[i3 + 4] = (i8 >>> 16) | (i9 << 12);
        iArr2[i3 + 5] = (i9 >>> 20) | (i10 << 8);
        iArr2[i3 + 6] = (i11 << 4) | (i10 >>> 24);
    }

    private static void encode24(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) i2;
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i2 >>> 8);
        bArr[i4 + 1] = (byte) (i2 >>> 16);
    }

    private static void encode32(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) i2;
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i2 >>> 8);
        int i5 = i4 + 1;
        bArr[i5] = (byte) (i2 >>> 16);
        bArr[i5 + 1] = (byte) (i2 >>> 24);
    }

    private static void encode56(int[] iArr, int i2, byte[] bArr, int i3) {
        int i4 = iArr[i2];
        int i5 = iArr[i2 + 1];
        encode32((i5 << 28) | i4, bArr, i3);
        encode24(i5 >>> 4, bArr, i3 + 4);
    }

    public static void inv(int[] iArr, int[] iArr2) {
        int[] create = create();
        int[] iArr3 = new int[14];
        copy(iArr, 0, create, 0);
        normalize(create);
        encode(create, iArr3, 0);
        Mod.modOddInverse(P32, iArr3, iArr3);
        decode(iArr3, 0, iArr2);
    }

    public static void invVar(int[] iArr, int[] iArr2) {
        int[] create = create();
        int[] iArr3 = new int[14];
        copy(iArr, 0, create, 0);
        normalize(create);
        encode(create, iArr3, 0);
        Mod.modOddInverseVar(P32, iArr3, iArr3);
        decode(iArr3, 0, iArr2);
    }

    public static int isOne(int[] iArr) {
        int i2 = iArr[0] ^ 1;
        for (int i3 = 1; i3 < 16; i3++) {
            i2 |= iArr[i3];
        }
        return (((i2 >>> 1) | (i2 & 1)) - 1) >> 31;
    }

    public static boolean isOneVar(int[] iArr) {
        return isOne(iArr) != 0;
    }

    public static int isZero(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 16; i3++) {
            i2 |= iArr[i3];
        }
        return (((i2 >>> 1) | (i2 & 1)) - 1) >> 31;
    }

    public static boolean isZeroVar(int[] iArr) {
        return isZero(iArr) != 0;
    }

    public static void mul(int[] iArr, int i2, int[] iArr2) {
        int i3 = iArr[0];
        int i4 = iArr[1];
        int i5 = iArr[2];
        int i6 = iArr[3];
        int i7 = iArr[4];
        int i8 = iArr[5];
        int i9 = iArr[6];
        int i10 = iArr[7];
        int i11 = iArr[8];
        int i12 = iArr[9];
        int i13 = iArr[10];
        int i14 = iArr[11];
        int i15 = iArr[12];
        int i16 = iArr[13];
        int i17 = iArr[14];
        int i18 = iArr[15];
        long j2 = i4;
        long j3 = i2;
        long j4 = j2 * j3;
        int i19 = ((int) j4) & M28;
        long j5 = i8 * j3;
        int i20 = ((int) j5) & M28;
        long j6 = i12 * j3;
        int i21 = ((int) j6) & M28;
        long j7 = i16 * j3;
        int i22 = ((int) j7) & M28;
        long j8 = (j4 >>> 28) + (i5 * j3);
        iArr2[2] = ((int) j8) & M28;
        long j9 = (j5 >>> 28) + (i9 * j3);
        iArr2[6] = ((int) j9) & M28;
        long j10 = (j6 >>> 28) + (i13 * j3);
        iArr2[10] = ((int) j10) & M28;
        long j11 = (j7 >>> 28) + (i17 * j3);
        iArr2[14] = ((int) j11) & M28;
        long j12 = (j8 >>> 28) + (i6 * j3);
        iArr2[3] = ((int) j12) & M28;
        long j13 = (j9 >>> 28) + (i10 * j3);
        iArr2[7] = ((int) j13) & M28;
        long j14 = (j10 >>> 28) + (i14 * j3);
        iArr2[11] = ((int) j14) & M28;
        long j15 = (j11 >>> 28) + (i18 * j3);
        iArr2[15] = ((int) j15) & M28;
        long j16 = j15 >>> 28;
        long j17 = (j12 >>> 28) + (i7 * j3);
        iArr2[4] = ((int) j17) & M28;
        long j18 = (j13 >>> 28) + j16 + (i11 * j3);
        iArr2[8] = ((int) j18) & M28;
        long j19 = (j14 >>> 28) + (i15 * j3);
        iArr2[12] = ((int) j19) & M28;
        long j20 = j16 + (i3 * j3);
        iArr2[0] = ((int) j20) & M28;
        iArr2[1] = i19 + ((int) (j20 >>> 28));
        iArr2[5] = i20 + ((int) (j17 >>> 28));
        iArr2[9] = i21 + ((int) (j18 >>> 28));
        iArr2[13] = i22 + ((int) (j19 >>> 28));
    }

    public static void mul(int[] iArr, int[] iArr2, int[] iArr3) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        int i5 = iArr[3];
        int i6 = iArr[4];
        int i7 = iArr[5];
        int i8 = iArr[6];
        int i9 = iArr[7];
        int i10 = iArr[8];
        int i11 = iArr[9];
        int i12 = iArr[10];
        int i13 = iArr[11];
        int i14 = iArr[12];
        int i15 = iArr[13];
        int i16 = iArr[14];
        int i17 = iArr[15];
        int i18 = iArr2[0];
        int i19 = iArr2[1];
        int i20 = iArr2[2];
        int i21 = iArr2[3];
        int i22 = iArr2[4];
        int i23 = iArr2[5];
        int i24 = iArr2[6];
        int i25 = iArr2[7];
        int i26 = iArr2[8];
        int i27 = iArr2[9];
        int i28 = iArr2[10];
        int i29 = iArr2[11];
        int i30 = iArr2[12];
        int i31 = iArr2[13];
        int i32 = iArr2[14];
        int i33 = iArr2[15];
        int i34 = i2 + i10;
        int i35 = i4 + i12;
        int i36 = i5 + i13;
        int i37 = i6 + i14;
        int i38 = i7 + i15;
        int i39 = i8 + i16;
        int i40 = i18 + i26;
        int i41 = i19 + i27;
        int i42 = i20 + i28;
        int i43 = i21 + i29;
        int i44 = i22 + i30;
        int i45 = i23 + i31;
        int i46 = i24 + i32;
        int i47 = i25 + i33;
        long j2 = i2;
        long j3 = i18;
        long j4 = j2 * j3;
        long j5 = i9;
        long j6 = i19;
        long j7 = j5 * j6;
        long j8 = i8;
        long j9 = i20;
        long j10 = i7;
        long j11 = i21;
        long j12 = i6;
        long j13 = i22;
        long j14 = i5;
        long j15 = i23;
        long j16 = i4;
        long j17 = i24;
        long j18 = i3;
        long j19 = i25;
        long j20 = i10;
        long j21 = i26;
        long j22 = j20 * j21;
        long j23 = i17;
        long j24 = i27;
        long j25 = j23 * j24;
        long j26 = i16;
        long j27 = i28;
        long j28 = i15;
        long j29 = i29;
        long j30 = i14;
        long j31 = i30;
        long j32 = i13;
        long j33 = i31;
        long j34 = i12;
        long j35 = i32;
        long j36 = i11;
        long j37 = i33;
        long j38 = i34;
        long j39 = i40;
        long j40 = j38 * j39;
        long j41 = i9 + i17;
        long j42 = i41;
        long j43 = j41 * j42;
        long j44 = i39;
        long j45 = i42;
        long j46 = i38;
        long j47 = i43;
        long j48 = i37;
        long j49 = i44;
        long j50 = i36;
        long j51 = i45;
        long j52 = i35;
        long j53 = i46;
        long j54 = i3 + i11;
        long j55 = i47;
        long j56 = j43 + (j44 * j45) + (j46 * j47) + (j48 * j49) + (j50 * j51) + (j52 * j53) + (j54 * j55);
        long j57 = ((j4 + j22) + j56) - ((((((j7 + (j8 * j9)) + (j10 * j11)) + (j12 * j13)) + (j14 * j15)) + (j16 * j17)) + (j18 * j19));
        int i48 = ((int) j57) & M28;
        long j58 = j57 >>> 28;
        long j59 = ((((((((j25 + (j26 * j27)) + (j28 * j29)) + (j30 * j31)) + (j32 * j33)) + (j34 * j35)) + (j36 * j37)) + j40) - j4) + j56;
        int i49 = ((int) j59) & M28;
        long j60 = (j18 * j3) + (j2 * j6);
        long j61 = (j23 * j27) + (j26 * j29) + (j28 * j31) + (j30 * j33) + (j32 * j35) + (j34 * j37);
        long j62 = (j54 * j39) + (j38 * j42);
        long j63 = (j41 * j45) + (j44 * j47) + (j46 * j49) + (j48 * j51) + (j50 * j53) + (j52 * j55);
        long j64 = j58 + (((j60 + ((j36 * j21) + (j20 * j24))) + j63) - ((((((j5 * j9) + (j8 * j11)) + (j10 * j13)) + (j12 * j15)) + (j14 * j17)) + (j16 * j19)));
        int i50 = ((int) j64) & M28;
        long j65 = (j59 >>> 28) + ((j61 + j62) - j60) + j63;
        int i51 = ((int) j65) & M28;
        long j66 = (j16 * j3) + (j18 * j6) + (j2 * j9);
        long j67 = (j23 * j29) + (j26 * j31) + (j28 * j33) + (j30 * j35) + (j32 * j37);
        long j68 = (j52 * j39) + (j54 * j42) + (j38 * j45);
        long j69 = (j41 * j47) + (j44 * j49) + (j46 * j51) + (j48 * j53) + (j50 * j55);
        long j70 = (j64 >>> 28) + (((j66 + (((j34 * j21) + (j36 * j24)) + (j20 * j27))) + j69) - (((((j5 * j11) + (j8 * j13)) + (j10 * j15)) + (j12 * j17)) + (j14 * j19)));
        int i52 = ((int) j70) & M28;
        long j71 = (j65 >>> 28) + ((j67 + j68) - j66) + j69;
        int i53 = ((int) j71) & M28;
        long j72 = (j14 * j3) + (j16 * j6) + (j18 * j9) + (j2 * j11);
        long j73 = (j23 * j31) + (j26 * j33) + (j28 * j35) + (j30 * j37);
        long j74 = (j50 * j39) + (j52 * j42) + (j54 * j45) + (j38 * j47);
        long j75 = (j41 * j49) + (j44 * j51) + (j46 * j53) + (j48 * j55);
        long j76 = (j70 >>> 28) + (((j72 + ((((j32 * j21) + (j34 * j24)) + (j36 * j27)) + (j20 * j29))) + j75) - ((((j5 * j13) + (j8 * j15)) + (j10 * j17)) + (j12 * j19)));
        int i54 = ((int) j76) & M28;
        long j77 = (j71 >>> 28) + ((j73 + j74) - j72) + j75;
        int i55 = ((int) j77) & M28;
        long j78 = (j12 * j3) + (j14 * j6) + (j16 * j9) + (j18 * j11) + (j2 * j13);
        long j79 = (j23 * j33) + (j26 * j35) + (j28 * j37);
        long j80 = (j48 * j39) + (j50 * j42) + (j52 * j45) + (j54 * j47) + (j38 * j49);
        long j81 = (j41 * j51) + (j44 * j53) + (j46 * j55);
        long j82 = (j76 >>> 28) + (((j78 + (((((j30 * j21) + (j32 * j24)) + (j34 * j27)) + (j36 * j29)) + (j20 * j31))) + j81) - (((j5 * j15) + (j8 * j17)) + (j10 * j19)));
        int i56 = ((int) j82) & M28;
        long j83 = (j77 >>> 28) + ((j79 + j80) - j78) + j81;
        int i57 = ((int) j83) & M28;
        long j84 = (j10 * j3) + (j12 * j6) + (j14 * j9) + (j16 * j11) + (j18 * j13) + (j2 * j15);
        long j85 = (j23 * j35) + (j26 * j37);
        long j86 = (j46 * j39) + (j48 * j42) + (j50 * j45) + (j52 * j47) + (j54 * j49) + (j38 * j51);
        long j87 = (j41 * j53) + (j44 * j55);
        long j88 = (j82 >>> 28) + (((j84 + ((((((j28 * j21) + (j30 * j24)) + (j32 * j27)) + (j34 * j29)) + (j36 * j31)) + (j20 * j33))) + j87) - ((j5 * j17) + (j8 * j19)));
        int i58 = ((int) j88) & M28;
        long j89 = (j83 >>> 28) + ((j85 + j86) - j84) + j87;
        int i59 = ((int) j89) & M28;
        long j90 = (j8 * j3) + (j10 * j6) + (j12 * j9) + (j14 * j11) + (j16 * j13) + (j18 * j15) + (j2 * j17);
        long j91 = j23 * j37;
        long j92 = (j44 * j39) + (j46 * j42) + (j48 * j45) + (j50 * j47) + (j52 * j49) + (j54 * j51) + (j38 * j53);
        long j93 = j41 * j55;
        long j94 = (j88 >>> 28) + (((j90 + (((((((j26 * j21) + (j28 * j24)) + (j30 * j27)) + (j32 * j29)) + (j34 * j31)) + (j36 * j33)) + (j20 * j35))) + j93) - (j5 * j19));
        int i60 = ((int) j94) & M28;
        long j95 = (j89 >>> 28) + ((j91 + j92) - j90) + j93;
        int i61 = ((int) j95) & M28;
        long j96 = (j3 * j5) + (j6 * j8) + (j10 * j9) + (j12 * j11) + (j14 * j13) + (j16 * j15) + (j18 * j17) + (j2 * j19);
        long j97 = (j41 * j39) + (j44 * j42) + (j46 * j45) + (j48 * j47) + (j50 * j49) + (j52 * j51) + (j54 * j53) + (j38 * j55);
        long j98 = (j94 >>> 28) + j96 + (j23 * j21) + (j24 * j26) + (j28 * j27) + (j30 * j29) + (j32 * j31) + (j34 * j33) + (j36 * j35) + (j20 * j37);
        int i62 = ((int) j98) & M28;
        long j99 = (j95 >>> 28) + (j97 - j96);
        int i63 = ((int) j99) & M28;
        long j100 = j99 >>> 28;
        long j101 = (j98 >>> 28) + j100 + i49;
        int i64 = ((int) j101) & M28;
        long j102 = j100 + i48;
        iArr3[0] = ((int) j102) & M28;
        iArr3[1] = i50 + ((int) (j102 >>> 28));
        iArr3[2] = i52;
        iArr3[3] = i54;
        iArr3[4] = i56;
        iArr3[5] = i58;
        iArr3[6] = i60;
        iArr3[7] = i62;
        iArr3[8] = i64;
        iArr3[9] = i51 + ((int) (j101 >>> 28));
        iArr3[10] = i53;
        iArr3[11] = i55;
        iArr3[12] = i57;
        iArr3[13] = i59;
        iArr3[14] = i61;
        iArr3[15] = i63;
    }

    public static void negate(int[] iArr, int[] iArr2) {
        sub(create(), iArr, iArr2);
    }

    public static void normalize(int[] iArr) {
        reduce(iArr, 1);
        reduce(iArr, -1);
    }

    public static void one(int[] iArr) {
        iArr[0] = 1;
        for (int i2 = 1; i2 < 16; i2++) {
            iArr[i2] = 0;
        }
    }

    private static void powPm3d4(int[] iArr, int[] iArr2) {
        int[] create = create();
        sqr(iArr, create);
        mul(iArr, create, create);
        int[] create2 = create();
        sqr(create, create2);
        mul(iArr, create2, create2);
        int[] create3 = create();
        sqr(create2, 3, create3);
        mul(create2, create3, create3);
        int[] create4 = create();
        sqr(create3, 3, create4);
        mul(create2, create4, create4);
        int[] create5 = create();
        sqr(create4, 9, create5);
        mul(create4, create5, create5);
        int[] create6 = create();
        sqr(create5, create6);
        mul(iArr, create6, create6);
        int[] create7 = create();
        sqr(create6, 18, create7);
        mul(create5, create7, create7);
        int[] create8 = create();
        sqr(create7, 37, create8);
        mul(create7, create8, create8);
        int[] create9 = create();
        sqr(create8, 37, create9);
        mul(create7, create9, create9);
        int[] create10 = create();
        sqr(create9, 111, create10);
        mul(create9, create10, create10);
        int[] create11 = create();
        sqr(create10, create11);
        mul(iArr, create11, create11);
        int[] create12 = create();
        sqr(create11, 223, create12);
        mul(create12, create10, iArr2);
    }

    private static void reduce(int[] iArr, int i2) {
        int i3;
        int i4 = iArr[15];
        int i5 = i4 & M28;
        long j2 = (i4 >>> 28) + i2;
        int i6 = 0;
        long j3 = j2;
        while (true) {
            if (i6 >= 8) {
                break;
            }
            long j4 = j3 + (4294967295L & iArr[i6]);
            iArr[i6] = ((int) j4) & M28;
            j3 = j4 >> 28;
            i6++;
        }
        long j5 = j3 + j2;
        for (i3 = 8; i3 < 15; i3++) {
            long j6 = j5 + (iArr[i3] & 4294967295L);
            iArr[i3] = ((int) j6) & M28;
            j5 = j6 >> 28;
        }
        iArr[15] = i5 + ((int) j5);
    }

    public static void sqr(int[] iArr, int i2, int[] iArr2) {
        sqr(iArr, iArr2);
        while (true) {
            i2--;
            if (i2 <= 0) {
                return;
            }
            sqr(iArr2, iArr2);
        }
    }

    public static void sqr(int[] iArr, int[] iArr2) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        int i5 = iArr[3];
        int i6 = iArr[4];
        int i7 = iArr[5];
        int i8 = iArr[6];
        int i9 = iArr[7];
        int i10 = iArr[8];
        int i11 = iArr[9];
        int i12 = iArr[10];
        int i13 = iArr[11];
        int i14 = iArr[12];
        int i15 = iArr[13];
        int i16 = iArr[14];
        int i17 = iArr[15];
        int i18 = i2 * 2;
        int i19 = i3 * 2;
        int i20 = i4 * 2;
        int i21 = i5 * 2;
        int i22 = i6 * 2;
        int i23 = i7 * 2;
        int i24 = i8 * 2;
        int i25 = i10 * 2;
        int i26 = i11 * 2;
        int i27 = i12 * 2;
        int i28 = i13 * 2;
        int i29 = i14 * 2;
        int i30 = i15 * 2;
        int i31 = i16 * 2;
        int i32 = i2 + i10;
        int i33 = i3 + i11;
        int i34 = i4 + i12;
        int i35 = i5 + i13;
        int i36 = i6 + i14;
        int i37 = i7 + i15;
        int i38 = i8 + i16;
        int i39 = i9 + i17;
        int i40 = i32 * 2;
        int i41 = i33 * 2;
        int i42 = i34 * 2;
        int i43 = i35 * 2;
        int i44 = i37 * 2;
        long j2 = i2;
        long j3 = j2 * j2;
        long j4 = i9;
        long j5 = i19;
        long j6 = j4 * j5;
        long j7 = i8;
        long j8 = i20;
        long j9 = i7;
        long j10 = i21;
        long j11 = i6;
        long j12 = i10;
        long j13 = i17;
        long j14 = i26;
        long j15 = j13 * j14;
        long j16 = i16;
        long j17 = i27;
        long j18 = i15;
        long j19 = i28;
        long j20 = i14;
        long j21 = i32;
        long j22 = i39;
        long j23 = i41 & 4294967295L;
        long j24 = j22 * j23;
        long j25 = i38;
        long j26 = i42 & 4294967295L;
        long j27 = i37;
        long j28 = i43 & 4294967295L;
        long j29 = i36;
        long j30 = j24 + (j25 * j26) + (j27 * j28) + (j29 * j29);
        long j31 = ((j3 + (j12 * j12)) + j30) - (((j6 + (j7 * j8)) + (j9 * j10)) + (j11 * j11));
        int i45 = ((int) j31) & M28;
        long j32 = (((((j15 + (j16 * j17)) + (j18 * j19)) + (j20 * j20)) + (j21 * j21)) - j3) + j30;
        int i46 = ((int) j32) & M28;
        long j33 = j32 >>> 28;
        long j34 = i3;
        long j35 = i18;
        long j36 = j34 * j35;
        long j37 = i22;
        long j38 = i11;
        long j39 = i25;
        long j40 = j38 * j39;
        long j41 = i29;
        long j42 = i33;
        long j43 = i40 & 4294967295L;
        long j44 = (i36 * 2) & 4294967295L;
        long j45 = (j22 * j26) + (j25 * j28) + (j27 * j44);
        long j46 = (j31 >>> 28) + (((j36 + j40) + j45) - (((j4 * j8) + (j7 * j10)) + (j9 * j37)));
        int i47 = ((int) j46) & M28;
        long j47 = j33 + (((((j13 * j17) + (j16 * j19)) + (j18 * j41)) + (j42 * j43)) - j36) + j45;
        int i48 = ((int) j47) & M28;
        long j48 = j47 >>> 28;
        long j49 = i4;
        long j50 = (j49 * j35) + (j34 * j34);
        long j51 = (j4 * j10) + (j7 * j37) + (j9 * j9);
        long j52 = i12;
        long j53 = (j52 * j39) + (j38 * j38);
        long j54 = i34;
        long j55 = (j54 * j43) + (j42 * j42);
        long j56 = (j22 * j28) + (j25 * j44) + (j27 * j27);
        long j57 = (j46 >>> 28) + (((j50 + j53) + j56) - j51);
        int i49 = ((int) j57) & M28;
        long j58 = j48 + (((((j13 * j19) + (j16 * j41)) + (j18 * j18)) + j55) - j50) + j56;
        int i50 = ((int) j58) & M28;
        long j59 = i5;
        long j60 = (j59 * j35) + (j49 * j5);
        long j61 = i23;
        long j62 = i13;
        long j63 = (j62 * j39) + (j52 * j14);
        long j64 = i30;
        long j65 = i35;
        long j66 = j44 * j22;
        long j67 = i44 & 4294967295L;
        long j68 = j66 + (j25 * j67);
        long j69 = (j57 >>> 28) + (((j60 + j63) + j68) - ((j4 * j37) + (j7 * j61)));
        int i51 = ((int) j69) & M28;
        long j70 = (j58 >>> 28) + ((((j13 * j41) + (j16 * j64)) + ((j65 * j43) + (j54 * j23))) - j60) + j68;
        int i52 = ((int) j70) & M28;
        long j71 = (j11 * j35) + (j59 * j5) + (j49 * j49);
        long j72 = (j20 * j39) + (j62 * j14) + (j52 * j52);
        long j73 = (j29 * j43) + (j65 * j23) + (j54 * j54);
        long j74 = (j22 * j67) + (j25 * j25);
        long j75 = (j69 >>> 28) + (((j71 + j72) + j74) - ((j4 * j61) + (j7 * j7)));
        int i53 = ((int) j75) & M28;
        long j76 = (j70 >>> 28) + ((((j13 * j64) + (j16 * j16)) + j73) - j71) + j74;
        int i54 = ((int) j76) & M28;
        long j77 = (j9 * j35) + (j11 * j5) + (j59 * j8);
        long j78 = (j18 * j39) + (j20 * j14) + (j62 * j17);
        long j79 = ((i38 * 2) & 4294967295L) * j22;
        long j80 = (j75 >>> 28) + (((j77 + j78) + j79) - (i24 * j4));
        int i55 = ((int) j80) & M28;
        long j81 = (j76 >>> 28) + (((i31 * j13) + (((j27 * j43) + (j29 * j23)) + (j65 * j26))) - j77) + j79;
        int i56 = ((int) j81) & M28;
        long j82 = (j7 * j35) + (j9 * j5) + (j11 * j8) + (j59 * j59);
        long j83 = j22 * j22;
        long j84 = (j80 >>> 28) + (((j82 + ((((j16 * j39) + (j18 * j14)) + (j20 * j17)) + (j62 * j62))) + j83) - (j4 * j4));
        int i57 = ((int) j84) & M28;
        long j85 = (j81 >>> 28) + (((j13 * j13) + ((((j25 * j43) + (j27 * j23)) + (j29 * j26)) + (j65 * j65))) - j82) + j83;
        int i58 = ((int) j85) & M28;
        long j86 = (j4 * j35) + (j7 * j5) + (j9 * j8) + (j11 * j10);
        long j87 = (j84 >>> 28) + (j39 * j13) + (j16 * j14) + (j18 * j17) + (j20 * j19) + j86;
        int i59 = ((int) j87) & M28;
        long j88 = (j85 >>> 28) + (((((j43 * j22) + (j25 * j23)) + (j27 * j26)) + (j29 * j28)) - j86);
        int i60 = ((int) j88) & M28;
        long j89 = j88 >>> 28;
        long j90 = (j87 >>> 28) + j89 + i46;
        int i61 = ((int) j90) & M28;
        long j91 = j89 + i45;
        iArr2[0] = ((int) j91) & M28;
        iArr2[1] = i47 + ((int) (j91 >>> 28));
        iArr2[2] = i49;
        iArr2[3] = i51;
        iArr2[4] = i53;
        iArr2[5] = i55;
        iArr2[6] = i57;
        iArr2[7] = i59;
        iArr2[8] = i61;
        iArr2[9] = i48 + ((int) (j90 >>> 28));
        iArr2[10] = i50;
        iArr2[11] = i52;
        iArr2[12] = i54;
        iArr2[13] = i56;
        iArr2[14] = i58;
        iArr2[15] = i60;
    }

    public static boolean sqrtRatioVar(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] create = create();
        int[] create2 = create();
        sqr(iArr, create);
        mul(create, iArr2, create);
        sqr(create, create2);
        mul(create, iArr, create);
        mul(create2, iArr, create2);
        mul(create2, iArr2, create2);
        int[] create3 = create();
        powPm3d4(create2, create3);
        mul(create3, create, create3);
        int[] create4 = create();
        sqr(create3, create4);
        mul(create4, iArr2, create4);
        sub(iArr, create4, create4);
        normalize(create4);
        if (isZeroVar(create4)) {
            copy(create3, 0, iArr3, 0);
            return true;
        }
        return false;
    }

    public static void sub(int[] iArr, int[] iArr2, int[] iArr3) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        int i5 = iArr[3];
        int i6 = iArr[4];
        int i7 = iArr[5];
        int i8 = iArr[6];
        int i9 = iArr[7];
        int i10 = iArr[8];
        int i11 = iArr[9];
        int i12 = iArr[10];
        int i13 = iArr[11];
        int i14 = iArr[12];
        int i15 = iArr[13];
        int i16 = iArr[14];
        int i17 = iArr[15];
        int i18 = iArr2[0];
        int i19 = iArr2[1];
        int i20 = iArr2[2];
        int i21 = iArr2[3];
        int i22 = iArr2[4];
        int i23 = iArr2[5];
        int i24 = iArr2[6];
        int i25 = iArr2[7];
        int i26 = iArr2[8];
        int i27 = iArr2[9];
        int i28 = iArr2[10];
        int i29 = iArr2[11];
        int i30 = iArr2[12];
        int i31 = iArr2[13];
        int i32 = iArr2[14];
        int i33 = (i3 + 536870910) - i19;
        int i34 = (i7 + 536870910) - i23;
        int i35 = (i11 + 536870910) - i27;
        int i36 = (i15 + 536870910) - i31;
        int i37 = ((i4 + 536870910) - i20) + (i33 >>> 28);
        int i38 = i33 & M28;
        int i39 = ((i8 + 536870910) - i24) + (i34 >>> 28);
        int i40 = i34 & M28;
        int i41 = ((i12 + 536870910) - i28) + (i35 >>> 28);
        int i42 = i35 & M28;
        int i43 = ((i16 + 536870910) - i32) + (i36 >>> 28);
        int i44 = i36 & M28;
        int i45 = ((i5 + 536870910) - i21) + (i37 >>> 28);
        int i46 = i37 & M28;
        int i47 = ((i9 + 536870910) - i25) + (i39 >>> 28);
        int i48 = i39 & M28;
        int i49 = ((i13 + 536870910) - i29) + (i41 >>> 28);
        int i50 = i41 & M28;
        int i51 = ((i17 + 536870910) - iArr2[15]) + (i43 >>> 28);
        int i52 = i43 & M28;
        int i53 = i51 >>> 28;
        int i54 = i51 & M28;
        int i55 = ((i2 + 536870910) - i18) + i53;
        int i56 = ((i6 + 536870910) - i22) + (i45 >>> 28);
        int i57 = i45 & M28;
        int i58 = ((i10 + 536870908) - i26) + i53 + (i47 >>> 28);
        int i59 = i47 & M28;
        int i60 = ((i14 + 536870910) - i30) + (i49 >>> 28);
        int i61 = i49 & M28;
        int i62 = i38 + (i55 >>> 28);
        int i63 = i55 & M28;
        int i64 = i40 + (i56 >>> 28);
        int i65 = i56 & M28;
        int i66 = i42 + (i58 >>> 28);
        int i67 = i58 & M28;
        int i68 = i60 & M28;
        iArr3[0] = i63;
        iArr3[1] = i62;
        iArr3[2] = i46;
        iArr3[3] = i57;
        iArr3[4] = i65;
        iArr3[5] = i64;
        iArr3[6] = i48;
        iArr3[7] = i59;
        iArr3[8] = i67;
        iArr3[9] = i66;
        iArr3[10] = i50;
        iArr3[11] = i61;
        iArr3[12] = i68;
        iArr3[13] = i44 + (i60 >>> 28);
        iArr3[14] = i52;
        iArr3[15] = i54;
    }

    public static void subOne(int[] iArr) {
        int[] create = create();
        create[0] = 1;
        sub(iArr, create, iArr);
    }

    public static void zero(int[] iArr) {
        for (int i2 = 0; i2 < 16; i2++) {
            iArr[i2] = 0;
        }
    }
}
