package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.TweakableBlockCipherParameters;
/* loaded from: classes3.dex */
public class ThreefishEngine implements BlockCipher {
    public static final int BLOCKSIZE_1024 = 1024;
    public static final int BLOCKSIZE_256 = 256;
    public static final int BLOCKSIZE_512 = 512;
    private static final long C_240 = 2004413935125273122L;
    private static final int MAX_ROUNDS = 80;
    private static int[] MOD17 = null;
    private static int[] MOD3 = null;
    private static int[] MOD5 = null;
    private static int[] MOD9 = null;
    private static final int ROUNDS_1024 = 80;
    private static final int ROUNDS_256 = 72;
    private static final int ROUNDS_512 = 72;
    private static final int TWEAK_SIZE_BYTES = 16;
    private static final int TWEAK_SIZE_WORDS = 2;
    private int blocksizeBytes;
    private int blocksizeWords;
    private ThreefishCipher cipher;
    private long[] currentBlock;
    private boolean forEncryption;
    private long[] kw;

    /* renamed from: t  reason: collision with root package name */
    private long[] f13399t;

    /* loaded from: classes3.dex */
    private static final class Threefish1024Cipher extends ThreefishCipher {
        private static final int ROTATION_0_0 = 24;
        private static final int ROTATION_0_1 = 13;
        private static final int ROTATION_0_2 = 8;
        private static final int ROTATION_0_3 = 47;
        private static final int ROTATION_0_4 = 8;
        private static final int ROTATION_0_5 = 17;
        private static final int ROTATION_0_6 = 22;
        private static final int ROTATION_0_7 = 37;
        private static final int ROTATION_1_0 = 38;
        private static final int ROTATION_1_1 = 19;
        private static final int ROTATION_1_2 = 10;
        private static final int ROTATION_1_3 = 55;
        private static final int ROTATION_1_4 = 49;
        private static final int ROTATION_1_5 = 18;
        private static final int ROTATION_1_6 = 23;
        private static final int ROTATION_1_7 = 52;
        private static final int ROTATION_2_0 = 33;
        private static final int ROTATION_2_1 = 4;
        private static final int ROTATION_2_2 = 51;
        private static final int ROTATION_2_3 = 13;
        private static final int ROTATION_2_4 = 34;
        private static final int ROTATION_2_5 = 41;
        private static final int ROTATION_2_6 = 59;
        private static final int ROTATION_2_7 = 17;
        private static final int ROTATION_3_0 = 5;
        private static final int ROTATION_3_1 = 20;
        private static final int ROTATION_3_2 = 48;
        private static final int ROTATION_3_3 = 41;
        private static final int ROTATION_3_4 = 47;
        private static final int ROTATION_3_5 = 28;
        private static final int ROTATION_3_6 = 16;
        private static final int ROTATION_3_7 = 25;
        private static final int ROTATION_4_0 = 41;
        private static final int ROTATION_4_1 = 9;
        private static final int ROTATION_4_2 = 37;
        private static final int ROTATION_4_3 = 31;
        private static final int ROTATION_4_4 = 12;
        private static final int ROTATION_4_5 = 47;
        private static final int ROTATION_4_6 = 44;
        private static final int ROTATION_4_7 = 30;
        private static final int ROTATION_5_0 = 16;
        private static final int ROTATION_5_1 = 34;
        private static final int ROTATION_5_2 = 56;
        private static final int ROTATION_5_3 = 51;
        private static final int ROTATION_5_4 = 4;
        private static final int ROTATION_5_5 = 53;
        private static final int ROTATION_5_6 = 42;
        private static final int ROTATION_5_7 = 41;
        private static final int ROTATION_6_0 = 31;
        private static final int ROTATION_6_1 = 44;
        private static final int ROTATION_6_2 = 47;
        private static final int ROTATION_6_3 = 46;
        private static final int ROTATION_6_4 = 19;
        private static final int ROTATION_6_5 = 42;
        private static final int ROTATION_6_6 = 44;
        private static final int ROTATION_6_7 = 25;
        private static final int ROTATION_7_0 = 9;
        private static final int ROTATION_7_1 = 48;
        private static final int ROTATION_7_2 = 35;
        private static final int ROTATION_7_3 = 52;
        private static final int ROTATION_7_4 = 23;
        private static final int ROTATION_7_5 = 31;
        private static final int ROTATION_7_6 = 37;
        private static final int ROTATION_7_7 = 20;

        public Threefish1024Cipher(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        void decryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.f13401b;
            long[] jArr4 = this.f13400a;
            int[] iArr = ThreefishEngine.MOD17;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 33) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j2 = jArr[0];
            int i2 = 1;
            long j3 = jArr[1];
            long j4 = jArr[2];
            long j5 = jArr[3];
            long j6 = jArr[4];
            long j7 = jArr[5];
            long j8 = jArr[6];
            long j9 = jArr[7];
            long j10 = jArr[8];
            long j11 = jArr[9];
            long j12 = jArr[10];
            long j13 = jArr[11];
            long j14 = jArr[12];
            long j15 = jArr[13];
            long j16 = jArr[14];
            long j17 = jArr[15];
            int i3 = 19;
            while (i3 >= i2) {
                int i4 = iArr[i3];
                int i5 = iArr2[i3];
                int i6 = i4 + 1;
                long j18 = j2 - jArr3[i6];
                int i7 = i4 + 2;
                long j19 = j3 - jArr3[i7];
                int i8 = i4 + 3;
                long j20 = j4 - jArr3[i8];
                int i9 = i4 + 4;
                long j21 = j5 - jArr3[i9];
                int i10 = i4 + 5;
                long j22 = j6 - jArr3[i10];
                int i11 = i4 + 6;
                int[] iArr3 = iArr;
                int[] iArr4 = iArr2;
                long j23 = j7 - jArr3[i11];
                int i12 = i4 + 7;
                long j24 = j8 - jArr3[i12];
                int i13 = i4 + 8;
                long j25 = j9 - jArr3[i13];
                int i14 = i4 + 9;
                long j26 = j10 - jArr3[i14];
                int i15 = i4 + 10;
                long j27 = j11 - jArr3[i15];
                int i16 = i4 + 11;
                long j28 = j12 - jArr3[i16];
                int i17 = i4 + 12;
                long j29 = j13 - jArr3[i17];
                int i18 = i4 + 13;
                long j30 = j14 - jArr3[i18];
                int i19 = i4 + 14;
                int i20 = i5 + 1;
                long j31 = j15 - (jArr3[i19] + jArr4[i20]);
                int i21 = i4 + 15;
                long j32 = j16 - (jArr3[i21] + jArr4[i5 + 2]);
                long[] jArr5 = jArr3;
                long j33 = i3;
                long f2 = ThreefishEngine.f(j17 - ((jArr3[i4 + 16] + j33) + 1), 9, j18);
                long j34 = j18 - f2;
                long f3 = ThreefishEngine.f(j29, 48, j20);
                long j35 = j20 - f3;
                long f4 = ThreefishEngine.f(j31, 35, j24);
                long j36 = j24 - f4;
                long[] jArr6 = jArr4;
                long f5 = ThreefishEngine.f(j27, 52, j22);
                long j37 = j22 - f5;
                long f6 = ThreefishEngine.f(j19, 23, j32);
                long j38 = j32 - f6;
                long f7 = ThreefishEngine.f(j23, 31, j26);
                long j39 = j26 - f7;
                long f8 = ThreefishEngine.f(j21, 37, j28);
                long j40 = j28 - f8;
                long f9 = ThreefishEngine.f(j25, 20, j30);
                long j41 = j30 - f9;
                long f10 = ThreefishEngine.f(f9, 31, j34);
                long j42 = j34 - f10;
                long f11 = ThreefishEngine.f(f7, 44, j35);
                long j43 = j35 - f11;
                long f12 = ThreefishEngine.f(f8, 47, j37);
                long j44 = j37 - f12;
                long f13 = ThreefishEngine.f(f6, 46, j36);
                long j45 = j36 - f13;
                long f14 = ThreefishEngine.f(f2, 19, j41);
                long j46 = j41 - f14;
                long f15 = ThreefishEngine.f(f4, 42, j38);
                long j47 = j38 - f15;
                long f16 = ThreefishEngine.f(f3, 44, j39);
                long j48 = j39 - f16;
                long f17 = ThreefishEngine.f(f5, 25, j40);
                long j49 = j40 - f17;
                long f18 = ThreefishEngine.f(f17, 16, j42);
                long j50 = j42 - f18;
                long f19 = ThreefishEngine.f(f15, 34, j43);
                long j51 = j43 - f19;
                long f20 = ThreefishEngine.f(f16, 56, j45);
                long j52 = j45 - f20;
                long f21 = ThreefishEngine.f(f14, 51, j44);
                long j53 = j44 - f21;
                long f22 = ThreefishEngine.f(f10, 4, j49);
                long j54 = j49 - f22;
                long f23 = ThreefishEngine.f(f12, 53, j46);
                long j55 = j46 - f23;
                long f24 = ThreefishEngine.f(f11, 42, j47);
                long j56 = j47 - f24;
                long f25 = ThreefishEngine.f(f13, 41, j48);
                long j57 = j48 - f25;
                long f26 = ThreefishEngine.f(f25, 41, j50);
                long f27 = ThreefishEngine.f(f23, 9, j51);
                long f28 = ThreefishEngine.f(f24, 37, j53);
                long j58 = j53 - f28;
                long f29 = ThreefishEngine.f(f22, 31, j52);
                long j59 = j52 - f29;
                long f30 = ThreefishEngine.f(f18, 12, j57);
                long j60 = j57 - f30;
                long f31 = ThreefishEngine.f(f20, 47, j54);
                long j61 = j54 - f31;
                long f32 = ThreefishEngine.f(f19, 44, j55);
                long j62 = j55 - f32;
                long f33 = ThreefishEngine.f(f21, 30, j56);
                long j63 = j56 - f33;
                long j64 = (j50 - f26) - jArr5[i4];
                long j65 = f26 - jArr5[i6];
                long j66 = (j51 - f27) - jArr5[i7];
                long j67 = f27 - jArr5[i8];
                long j68 = j58 - jArr5[i9];
                long j69 = f28 - jArr5[i10];
                long j70 = j59 - jArr5[i11];
                long j71 = f29 - jArr5[i12];
                long j72 = j60 - jArr5[i13];
                long j73 = f30 - jArr5[i14];
                long j74 = j61 - jArr5[i15];
                long j75 = f31 - jArr5[i16];
                long j76 = j62 - jArr5[i17];
                long j77 = f32 - (jArr5[i18] + jArr6[i5]);
                long j78 = j63 - (jArr5[i19] + jArr6[i20]);
                long f34 = ThreefishEngine.f(f33 - (jArr5[i21] + j33), 5, j64);
                long j79 = j64 - f34;
                long f35 = ThreefishEngine.f(j75, 20, j66);
                long j80 = j66 - f35;
                long f36 = ThreefishEngine.f(j77, 48, j70);
                long j81 = j70 - f36;
                long f37 = ThreefishEngine.f(j73, 41, j68);
                long j82 = j68 - f37;
                long f38 = ThreefishEngine.f(j65, 47, j78);
                long j83 = j78 - f38;
                long f39 = ThreefishEngine.f(j69, 28, j72);
                long j84 = j72 - f39;
                long f40 = ThreefishEngine.f(j67, 16, j74);
                long j85 = j74 - f40;
                long f41 = ThreefishEngine.f(j71, 25, j76);
                long j86 = j76 - f41;
                long f42 = ThreefishEngine.f(f41, 33, j79);
                long j87 = j79 - f42;
                long f43 = ThreefishEngine.f(f39, 4, j80);
                long j88 = j80 - f43;
                long f44 = ThreefishEngine.f(f40, 51, j82);
                long j89 = j82 - f44;
                long f45 = ThreefishEngine.f(f38, 13, j81);
                long j90 = j81 - f45;
                long f46 = ThreefishEngine.f(f34, 34, j86);
                long j91 = j86 - f46;
                long f47 = ThreefishEngine.f(f36, 41, j83);
                long j92 = j83 - f47;
                long f48 = ThreefishEngine.f(f35, 59, j84);
                long j93 = j84 - f48;
                long f49 = ThreefishEngine.f(f37, 17, j85);
                long j94 = j85 - f49;
                long f50 = ThreefishEngine.f(f49, 38, j87);
                long j95 = j87 - f50;
                long f51 = ThreefishEngine.f(f47, 19, j88);
                long j96 = j88 - f51;
                long f52 = ThreefishEngine.f(f48, 10, j90);
                long j97 = j90 - f52;
                long f53 = ThreefishEngine.f(f46, 55, j89);
                long j98 = j89 - f53;
                long f54 = ThreefishEngine.f(f42, 49, j94);
                long j99 = j94 - f54;
                long f55 = ThreefishEngine.f(f44, 18, j91);
                long j100 = j91 - f55;
                long f56 = ThreefishEngine.f(f43, 23, j92);
                long j101 = j92 - f56;
                long f57 = ThreefishEngine.f(f45, 52, j93);
                long j102 = j93 - f57;
                long f58 = ThreefishEngine.f(f57, 24, j95);
                long j103 = j95 - f58;
                long f59 = ThreefishEngine.f(f55, 13, j96);
                j4 = j96 - f59;
                long f60 = ThreefishEngine.f(f56, 8, j98);
                long j104 = j98 - f60;
                long f61 = ThreefishEngine.f(f54, 47, j97);
                long j105 = j97 - f61;
                long f62 = ThreefishEngine.f(f50, 8, j102);
                long j106 = j102 - f62;
                long f63 = ThreefishEngine.f(f52, 17, j99);
                long j107 = j99 - f63;
                j15 = ThreefishEngine.f(f51, 22, j100);
                j17 = ThreefishEngine.f(f53, 37, j101);
                j16 = j101 - j17;
                j13 = f63;
                j14 = j100 - j15;
                iArr = iArr3;
                jArr4 = jArr6;
                jArr3 = jArr5;
                j10 = j106;
                j11 = f62;
                i2 = 1;
                j6 = j104;
                j3 = f58;
                i3 -= 2;
                j5 = f59;
                iArr2 = iArr4;
                j9 = f61;
                j12 = j107;
                j7 = f60;
                j8 = j105;
                j2 = j103;
            }
            long[] jArr7 = jArr3;
            long[] jArr8 = jArr4;
            long j108 = j2 - jArr7[0];
            long j109 = j3 - jArr7[1];
            long j110 = j4 - jArr7[2];
            long j111 = j5 - jArr7[3];
            long j112 = j6 - jArr7[4];
            long j113 = j7 - jArr7[5];
            long j114 = j8 - jArr7[6];
            long j115 = j9 - jArr7[7];
            long j116 = j10 - jArr7[8];
            long j117 = j11 - jArr7[9];
            long j118 = j12 - jArr7[10];
            long j119 = j14 - jArr7[12];
            long j120 = j15 - (jArr7[13] + jArr8[0]);
            long j121 = j16 - (jArr7[14] + jArr8[1]);
            jArr2[0] = j108;
            jArr2[1] = j109;
            jArr2[2] = j110;
            jArr2[3] = j111;
            jArr2[4] = j112;
            jArr2[5] = j113;
            jArr2[6] = j114;
            jArr2[7] = j115;
            jArr2[8] = j116;
            jArr2[9] = j117;
            jArr2[10] = j118;
            jArr2[11] = j13 - jArr7[11];
            jArr2[12] = j119;
            jArr2[13] = j120;
            jArr2[14] = j121;
            jArr2[15] = j17 - jArr7[15];
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        void encryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.f13401b;
            long[] jArr4 = this.f13400a;
            int[] iArr = ThreefishEngine.MOD17;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 33) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j2 = jArr[0];
            int i2 = 1;
            long j3 = jArr[1];
            long j4 = jArr[2];
            long j5 = jArr[3];
            long j6 = jArr[4];
            long j7 = jArr[5];
            long j8 = jArr[6];
            long j9 = jArr[7];
            long j10 = jArr[8];
            long j11 = jArr[9];
            long j12 = jArr[10];
            long j13 = jArr[11];
            long j14 = jArr[12];
            long j15 = jArr[13];
            long j16 = jArr[14];
            long j17 = jArr[15];
            long j18 = j2 + jArr3[0];
            long j19 = j3 + jArr3[1];
            long j20 = j4 + jArr3[2];
            long j21 = j5 + jArr3[3];
            long j22 = j6 + jArr3[4];
            long j23 = j7 + jArr3[5];
            long j24 = j8 + jArr3[6];
            long j25 = j9 + jArr3[7];
            long j26 = j10 + jArr3[8];
            long j27 = j11 + jArr3[9];
            long j28 = j12 + jArr3[10];
            long j29 = j13 + jArr3[11];
            long j30 = j14 + jArr3[12];
            long j31 = j15 + jArr3[13] + jArr4[0];
            long j32 = j16 + jArr3[14] + jArr4[1];
            long j33 = j21;
            long j34 = j23;
            long j35 = j25;
            long j36 = j27;
            long j37 = j29;
            long j38 = j31;
            long j39 = j17 + jArr3[15];
            while (i2 < 20) {
                int i3 = iArr[i2];
                int i4 = iArr2[i2];
                long j40 = j18 + j19;
                long e2 = ThreefishEngine.e(j19, 24, j40);
                long j41 = j20 + j33;
                long e3 = ThreefishEngine.e(j33, 13, j41);
                long[] jArr5 = jArr3;
                int[] iArr3 = iArr;
                int[] iArr4 = iArr2;
                long j42 = j34;
                long j43 = j22 + j42;
                long e4 = ThreefishEngine.e(j42, 8, j43);
                int i5 = i2;
                long j44 = j35;
                long j45 = j24 + j44;
                long e5 = ThreefishEngine.e(j44, 47, j45);
                long[] jArr6 = jArr4;
                long j46 = j36;
                long j47 = j26 + j46;
                long e6 = ThreefishEngine.e(j46, 8, j47);
                long j48 = j37;
                long j49 = j28 + j48;
                long e7 = ThreefishEngine.e(j48, 17, j49);
                long j50 = j38;
                long j51 = j30 + j50;
                long e8 = ThreefishEngine.e(j50, 22, j51);
                long j52 = j39;
                long j53 = j32 + j52;
                long e9 = ThreefishEngine.e(j52, 37, j53);
                long j54 = j40 + e6;
                long e10 = ThreefishEngine.e(e6, 38, j54);
                long j55 = j41 + e8;
                long e11 = ThreefishEngine.e(e8, 19, j55);
                long j56 = j45 + e7;
                long e12 = ThreefishEngine.e(e7, 10, j56);
                long j57 = j43 + e9;
                long e13 = ThreefishEngine.e(e9, 55, j57);
                long j58 = j49 + e5;
                long e14 = ThreefishEngine.e(e5, 49, j58);
                long j59 = j51 + e3;
                long e15 = ThreefishEngine.e(e3, 18, j59);
                long j60 = j53 + e4;
                long e16 = ThreefishEngine.e(e4, 23, j60);
                long j61 = j47 + e2;
                long e17 = ThreefishEngine.e(e2, 52, j61);
                long j62 = j54 + e14;
                long e18 = ThreefishEngine.e(e14, 33, j62);
                long j63 = j55 + e16;
                long e19 = ThreefishEngine.e(e16, 4, j63);
                long j64 = j57 + e15;
                long e20 = ThreefishEngine.e(e15, 51, j64);
                long j65 = j56 + e17;
                long e21 = ThreefishEngine.e(e17, 13, j65);
                long j66 = j59 + e13;
                long e22 = ThreefishEngine.e(e13, 34, j66);
                long j67 = j60 + e11;
                long e23 = ThreefishEngine.e(e11, 41, j67);
                long j68 = j61 + e12;
                long e24 = ThreefishEngine.e(e12, 59, j68);
                long j69 = j58 + e10;
                long e25 = ThreefishEngine.e(e10, 17, j69);
                long j70 = j62 + e22;
                long e26 = ThreefishEngine.e(e22, 5, j70);
                long j71 = j63 + e24;
                long e27 = ThreefishEngine.e(e24, 20, j71);
                long j72 = j65 + e23;
                long e28 = ThreefishEngine.e(e23, 48, j72);
                long j73 = j64 + e25;
                long e29 = ThreefishEngine.e(e25, 41, j73);
                long j74 = j67 + e21;
                long e30 = ThreefishEngine.e(e21, 47, j74);
                long j75 = j68 + e19;
                long e31 = ThreefishEngine.e(e19, 28, j75);
                long j76 = j69 + e20;
                long e32 = ThreefishEngine.e(e20, 16, j76);
                long j77 = j66 + e18;
                long e33 = ThreefishEngine.e(e18, 25, j77);
                long j78 = j70 + jArr5[i3];
                int i6 = i3 + 1;
                long j79 = e30 + jArr5[i6];
                int i7 = i3 + 2;
                long j80 = j71 + jArr5[i7];
                int i8 = i3 + 3;
                long j81 = e32 + jArr5[i8];
                int i9 = i3 + 4;
                long j82 = j73 + jArr5[i9];
                int i10 = i3 + 5;
                long j83 = e31 + jArr5[i10];
                int i11 = i3 + 6;
                long j84 = j72 + jArr5[i11];
                int i12 = i3 + 7;
                long j85 = e33 + jArr5[i12];
                int i13 = i3 + 8;
                long j86 = j75 + jArr5[i13];
                int i14 = i3 + 9;
                long j87 = e29 + jArr5[i14];
                int i15 = i3 + 10;
                long j88 = j76 + jArr5[i15];
                int i16 = i3 + 11;
                long j89 = e27 + jArr5[i16];
                int i17 = i3 + 12;
                long j90 = j77 + jArr5[i17];
                int i18 = i3 + 13;
                long j91 = e28 + jArr5[i18] + jArr6[i4];
                int i19 = i3 + 14;
                int i20 = i4 + 1;
                long j92 = j74 + jArr5[i19] + jArr6[i20];
                int i21 = i3 + 15;
                long j93 = jArr5[i21];
                long j94 = i5;
                long j95 = e26 + j93 + j94;
                long j96 = j78 + j79;
                long e34 = ThreefishEngine.e(j79, 41, j96);
                long j97 = j80 + j81;
                long e35 = ThreefishEngine.e(j81, 9, j97);
                long j98 = j82 + j83;
                long e36 = ThreefishEngine.e(j83, 37, j98);
                long j99 = j84 + j85;
                long e37 = ThreefishEngine.e(j85, 31, j99);
                long j100 = j86 + j87;
                long e38 = ThreefishEngine.e(j87, 12, j100);
                long j101 = j88 + j89;
                long e39 = ThreefishEngine.e(j89, 47, j101);
                long j102 = j90 + j91;
                long e40 = ThreefishEngine.e(j91, 44, j102);
                long j103 = j92 + j95;
                long e41 = ThreefishEngine.e(j95, 30, j103);
                long j104 = j96 + e38;
                long e42 = ThreefishEngine.e(e38, 16, j104);
                long j105 = j97 + e40;
                long e43 = ThreefishEngine.e(e40, 34, j105);
                long j106 = j99 + e39;
                long e44 = ThreefishEngine.e(e39, 56, j106);
                long j107 = j98 + e41;
                long e45 = ThreefishEngine.e(e41, 51, j107);
                long j108 = j101 + e37;
                long e46 = ThreefishEngine.e(e37, 4, j108);
                long j109 = j102 + e35;
                long e47 = ThreefishEngine.e(e35, 53, j109);
                long j110 = j103 + e36;
                long e48 = ThreefishEngine.e(e36, 42, j110);
                long j111 = j100 + e34;
                long e49 = ThreefishEngine.e(e34, 41, j111);
                long j112 = j104 + e46;
                long e50 = ThreefishEngine.e(e46, 31, j112);
                long j113 = j105 + e48;
                long e51 = ThreefishEngine.e(e48, 44, j113);
                long j114 = j107 + e47;
                long e52 = ThreefishEngine.e(e47, 47, j114);
                long j115 = j106 + e49;
                long e53 = ThreefishEngine.e(e49, 46, j115);
                long j116 = j109 + e45;
                long e54 = ThreefishEngine.e(e45, 19, j116);
                long j117 = j110 + e43;
                long e55 = ThreefishEngine.e(e43, 42, j117);
                long j118 = j111 + e44;
                long e56 = ThreefishEngine.e(e44, 44, j118);
                long j119 = j108 + e42;
                long e57 = ThreefishEngine.e(e42, 25, j119);
                long j120 = j112 + e54;
                long e58 = ThreefishEngine.e(e54, 9, j120);
                long j121 = j113 + e56;
                long e59 = ThreefishEngine.e(e56, 48, j121);
                long j122 = j115 + e55;
                long e60 = ThreefishEngine.e(e55, 35, j122);
                long j123 = j114 + e57;
                long e61 = ThreefishEngine.e(e57, 52, j123);
                long j124 = j117 + e53;
                long e62 = ThreefishEngine.e(e53, 23, j124);
                long j125 = j118 + e51;
                long e63 = ThreefishEngine.e(e51, 31, j125);
                long j126 = j119 + e52;
                long e64 = ThreefishEngine.e(e52, 37, j126);
                long j127 = j116 + e50;
                long e65 = ThreefishEngine.e(e50, 20, j127);
                long j128 = j120 + jArr5[i6];
                long j129 = e62 + jArr5[i7];
                long j130 = j121 + jArr5[i8];
                long j131 = e64 + jArr5[i9];
                long j132 = j123 + jArr5[i10];
                long j133 = e63 + jArr5[i11];
                long j134 = j122 + jArr5[i12];
                long j135 = e65 + jArr5[i13];
                long j136 = j125 + jArr5[i14];
                j36 = e61 + jArr5[i15];
                j28 = j126 + jArr5[i16];
                j37 = e59 + jArr5[i17];
                long j137 = j127 + jArr5[i18];
                j38 = e60 + jArr5[i19] + jArr6[i20];
                j39 = e58 + jArr5[i3 + 16] + j94 + 1;
                j35 = j135;
                j33 = j131;
                j34 = j133;
                j32 = j124 + jArr5[i21] + jArr6[i4 + 2];
                iArr2 = iArr4;
                j26 = j136;
                j24 = j134;
                j30 = j137;
                j19 = j129;
                iArr = iArr3;
                jArr4 = jArr6;
                jArr3 = jArr5;
                i2 = i5 + 2;
                j22 = j132;
                j18 = j128;
                j20 = j130;
            }
            jArr2[0] = j18;
            jArr2[1] = j19;
            jArr2[2] = j20;
            jArr2[3] = j33;
            jArr2[4] = j22;
            jArr2[5] = j34;
            jArr2[6] = j24;
            jArr2[7] = j35;
            jArr2[8] = j26;
            jArr2[9] = j36;
            jArr2[10] = j28;
            jArr2[11] = j37;
            jArr2[12] = j30;
            jArr2[13] = j38;
            jArr2[14] = j32;
            jArr2[15] = j39;
        }
    }

    /* loaded from: classes3.dex */
    private static final class Threefish256Cipher extends ThreefishCipher {
        private static final int ROTATION_0_0 = 14;
        private static final int ROTATION_0_1 = 16;
        private static final int ROTATION_1_0 = 52;
        private static final int ROTATION_1_1 = 57;
        private static final int ROTATION_2_0 = 23;
        private static final int ROTATION_2_1 = 40;
        private static final int ROTATION_3_0 = 5;
        private static final int ROTATION_3_1 = 37;
        private static final int ROTATION_4_0 = 25;
        private static final int ROTATION_4_1 = 33;
        private static final int ROTATION_5_0 = 46;
        private static final int ROTATION_5_1 = 12;
        private static final int ROTATION_6_0 = 58;
        private static final int ROTATION_6_1 = 22;
        private static final int ROTATION_7_0 = 32;
        private static final int ROTATION_7_1 = 32;

        public Threefish256Cipher(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        void decryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.f13401b;
            long[] jArr4 = this.f13400a;
            int[] iArr = ThreefishEngine.MOD5;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 9) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            char c2 = 0;
            long j2 = jArr[0];
            long j3 = jArr[1];
            long j4 = jArr[2];
            long j5 = jArr[3];
            int i2 = 17;
            for (int i3 = 1; i2 >= i3; i3 = 1) {
                int i4 = iArr[i2];
                int i5 = iArr2[i2];
                int i6 = i4 + 1;
                long j6 = j2 - jArr3[i6];
                int i7 = i4 + 2;
                int i8 = i5 + 1;
                long j7 = j3 - (jArr3[i7] + jArr4[i8]);
                int i9 = i4 + 3;
                long j8 = j4 - (jArr3[i9] + jArr4[i5 + 2]);
                long j9 = i2;
                long f2 = ThreefishEngine.f(j5 - ((jArr3[i4 + 4] + j9) + 1), 32, j6);
                long j10 = j6 - f2;
                int[] iArr3 = iArr;
                long f3 = ThreefishEngine.f(j7, 32, j8);
                long j11 = j8 - f3;
                long f4 = ThreefishEngine.f(f3, 58, j10);
                long j12 = j10 - f4;
                long f5 = ThreefishEngine.f(f2, 22, j11);
                long j13 = j11 - f5;
                long f6 = ThreefishEngine.f(f5, 46, j12);
                long j14 = j12 - f6;
                long f7 = ThreefishEngine.f(f4, 12, j13);
                long j15 = j13 - f7;
                long f8 = ThreefishEngine.f(f7, 25, j14);
                long f9 = ThreefishEngine.f(f6, 33, j15);
                long j16 = (j14 - f8) - jArr3[i4];
                long j17 = f8 - (jArr3[i6] + jArr4[i5]);
                long j18 = (j15 - f9) - (jArr3[i7] + jArr4[i8]);
                long f10 = ThreefishEngine.f(f9 - (jArr3[i9] + j9), 5, j16);
                long j19 = j16 - f10;
                long f11 = ThreefishEngine.f(j17, 37, j18);
                long j20 = j18 - f11;
                long f12 = ThreefishEngine.f(f11, 23, j19);
                long j21 = j19 - f12;
                long f13 = ThreefishEngine.f(f10, 40, j20);
                long j22 = j20 - f13;
                long f14 = ThreefishEngine.f(f13, 52, j21);
                long j23 = j21 - f14;
                long f15 = ThreefishEngine.f(f12, 57, j22);
                long j24 = j22 - f15;
                long f16 = ThreefishEngine.f(f15, 14, j23);
                j2 = j23 - f16;
                j5 = ThreefishEngine.f(f14, 16, j24);
                j4 = j24 - j5;
                i2 -= 2;
                j3 = f16;
                iArr = iArr3;
                iArr2 = iArr2;
                c2 = 0;
            }
            char c3 = c2;
            long j25 = j3 - (jArr3[1] + jArr4[c3]);
            long j26 = j4 - (jArr3[2] + jArr4[1]);
            jArr2[c3] = j2 - jArr3[c3];
            jArr2[1] = j25;
            jArr2[2] = j26;
            jArr2[3] = j5 - jArr3[3];
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        void encryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.f13401b;
            long[] jArr4 = this.f13400a;
            int[] iArr = ThreefishEngine.MOD5;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 9) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j2 = jArr[0];
            long j3 = jArr[1];
            long j4 = jArr[2];
            long j5 = jArr[3];
            long j6 = j2 + jArr3[0];
            long j7 = j3 + jArr3[1] + jArr4[0];
            long j8 = j4 + jArr3[2] + jArr4[1];
            int i2 = 1;
            long j9 = j5 + jArr3[3];
            while (i2 < 18) {
                int i3 = iArr[i2];
                int i4 = iArr2[i2];
                long j10 = j6 + j7;
                long e2 = ThreefishEngine.e(j7, 14, j10);
                long j11 = j8 + j9;
                long e3 = ThreefishEngine.e(j9, 16, j11);
                long j12 = j10 + e3;
                long e4 = ThreefishEngine.e(e3, 52, j12);
                long j13 = j11 + e2;
                long e5 = ThreefishEngine.e(e2, 57, j13);
                long j14 = j12 + e5;
                long e6 = ThreefishEngine.e(e5, 23, j14);
                long j15 = j13 + e4;
                long e7 = ThreefishEngine.e(e4, 40, j15);
                long j16 = j14 + e7;
                long e8 = ThreefishEngine.e(e7, 5, j16);
                long j17 = j15 + e6;
                long e9 = ThreefishEngine.e(e6, 37, j17);
                long j18 = j16 + jArr3[i3];
                int i5 = i3 + 1;
                long j19 = e9 + jArr3[i5] + jArr4[i4];
                int i6 = i3 + 2;
                int i7 = i4 + 1;
                long j20 = j17 + jArr3[i6] + jArr4[i7];
                int i8 = i3 + 3;
                int[] iArr3 = iArr;
                long j21 = i2;
                long j22 = e8 + jArr3[i8] + j21;
                long j23 = j18 + j19;
                long e10 = ThreefishEngine.e(j19, 25, j23);
                long j24 = j20 + j22;
                long e11 = ThreefishEngine.e(j22, 33, j24);
                long j25 = j23 + e11;
                long e12 = ThreefishEngine.e(e11, 46, j25);
                long j26 = j24 + e10;
                long e13 = ThreefishEngine.e(e10, 12, j26);
                long j27 = j25 + e13;
                long e14 = ThreefishEngine.e(e13, 58, j27);
                long j28 = j26 + e12;
                long e15 = ThreefishEngine.e(e12, 22, j28);
                long j29 = j27 + e15;
                long e16 = ThreefishEngine.e(e15, 32, j29);
                long j30 = j28 + e14;
                long e17 = ThreefishEngine.e(e14, 32, j30);
                j6 = j29 + jArr3[i5];
                j7 = e17 + jArr3[i6] + jArr4[i7];
                j8 = j30 + jArr3[i8] + jArr4[i4 + 2];
                j9 = e16 + jArr3[i3 + 4] + j21 + 1;
                i2 += 2;
                iArr = iArr3;
                iArr2 = iArr2;
            }
            jArr2[0] = j6;
            jArr2[1] = j7;
            jArr2[2] = j8;
            jArr2[3] = j9;
        }
    }

    /* loaded from: classes3.dex */
    private static final class Threefish512Cipher extends ThreefishCipher {
        private static final int ROTATION_0_0 = 46;
        private static final int ROTATION_0_1 = 36;
        private static final int ROTATION_0_2 = 19;
        private static final int ROTATION_0_3 = 37;
        private static final int ROTATION_1_0 = 33;
        private static final int ROTATION_1_1 = 27;
        private static final int ROTATION_1_2 = 14;
        private static final int ROTATION_1_3 = 42;
        private static final int ROTATION_2_0 = 17;
        private static final int ROTATION_2_1 = 49;
        private static final int ROTATION_2_2 = 36;
        private static final int ROTATION_2_3 = 39;
        private static final int ROTATION_3_0 = 44;
        private static final int ROTATION_3_1 = 9;
        private static final int ROTATION_3_2 = 54;
        private static final int ROTATION_3_3 = 56;
        private static final int ROTATION_4_0 = 39;
        private static final int ROTATION_4_1 = 30;
        private static final int ROTATION_4_2 = 34;
        private static final int ROTATION_4_3 = 24;
        private static final int ROTATION_5_0 = 13;
        private static final int ROTATION_5_1 = 50;
        private static final int ROTATION_5_2 = 10;
        private static final int ROTATION_5_3 = 17;
        private static final int ROTATION_6_0 = 25;
        private static final int ROTATION_6_1 = 29;
        private static final int ROTATION_6_2 = 39;
        private static final int ROTATION_6_3 = 43;
        private static final int ROTATION_7_0 = 8;
        private static final int ROTATION_7_1 = 35;
        private static final int ROTATION_7_2 = 56;
        private static final int ROTATION_7_3 = 22;

        protected Threefish512Cipher(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        public void decryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.f13401b;
            long[] jArr4 = this.f13400a;
            int[] iArr = ThreefishEngine.MOD9;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 17) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            char c2 = 0;
            long j2 = jArr[0];
            int i2 = 1;
            long j3 = jArr[1];
            long j4 = jArr[2];
            long j5 = jArr[3];
            long j6 = jArr[4];
            long j7 = jArr[5];
            long j8 = jArr[6];
            long j9 = jArr[7];
            int i3 = 17;
            while (i3 >= i2) {
                int i4 = iArr[i3];
                int i5 = iArr2[i3];
                int i6 = i4 + 1;
                long j10 = j2 - jArr3[i6];
                int i7 = i4 + 2;
                long j11 = j3 - jArr3[i7];
                int i8 = i4 + 3;
                long j12 = j4 - jArr3[i8];
                int i9 = i4 + 4;
                long j13 = j5 - jArr3[i9];
                int i10 = i4 + 5;
                long j14 = j6 - jArr3[i10];
                int i11 = i4 + 6;
                int i12 = i5 + 1;
                long j15 = j7 - (jArr3[i11] + jArr4[i12]);
                int i13 = i4 + 7;
                int[] iArr3 = iArr;
                int[] iArr4 = iArr2;
                long j16 = j8 - (jArr3[i13] + jArr4[i5 + 2]);
                long[] jArr5 = jArr3;
                long j17 = i3;
                int i14 = i3;
                long f2 = ThreefishEngine.f(j11, 8, j16);
                long j18 = j16 - f2;
                long f3 = ThreefishEngine.f(j9 - ((jArr3[i4 + 8] + j17) + 1), 35, j10);
                long j19 = j10 - f3;
                long f4 = ThreefishEngine.f(j15, 56, j12);
                long j20 = j12 - f4;
                long f5 = ThreefishEngine.f(j13, 22, j14);
                long j21 = j14 - f5;
                long f6 = ThreefishEngine.f(f2, 25, j21);
                long j22 = j21 - f6;
                long f7 = ThreefishEngine.f(f5, 29, j18);
                long j23 = j18 - f7;
                long f8 = ThreefishEngine.f(f4, 39, j19);
                long j24 = j19 - f8;
                long f9 = ThreefishEngine.f(f3, 43, j20);
                long j25 = j20 - f9;
                long f10 = ThreefishEngine.f(f6, 13, j25);
                long j26 = j25 - f10;
                long f11 = ThreefishEngine.f(f9, 50, j22);
                long j27 = j22 - f11;
                long f12 = ThreefishEngine.f(f8, 10, j23);
                long j28 = j23 - f12;
                long f13 = ThreefishEngine.f(f7, 17, j24);
                long j29 = j24 - f13;
                long f14 = ThreefishEngine.f(f10, 39, j29);
                long f15 = ThreefishEngine.f(f13, 30, j26);
                long f16 = ThreefishEngine.f(f12, 34, j27);
                long j30 = j27 - f16;
                long f17 = ThreefishEngine.f(f11, 24, j28);
                long j31 = (j29 - f14) - jArr5[i4];
                long j32 = f14 - jArr5[i6];
                long j33 = (j26 - f15) - jArr5[i7];
                long j34 = f15 - jArr5[i8];
                long j35 = j30 - jArr5[i9];
                long j36 = f16 - (jArr5[i10] + jArr4[i5]);
                long j37 = (j28 - f17) - (jArr5[i11] + jArr4[i12]);
                long f18 = ThreefishEngine.f(j32, 44, j37);
                long j38 = j37 - f18;
                long f19 = ThreefishEngine.f(f17 - (jArr5[i13] + j17), 9, j31);
                long j39 = j31 - f19;
                long f20 = ThreefishEngine.f(j36, 54, j33);
                long j40 = j33 - f20;
                long f21 = ThreefishEngine.f(j34, 56, j35);
                long j41 = j35 - f21;
                long f22 = ThreefishEngine.f(f18, 17, j41);
                long j42 = j41 - f22;
                long f23 = ThreefishEngine.f(f21, 49, j38);
                long j43 = j38 - f23;
                long f24 = ThreefishEngine.f(f20, 36, j39);
                long j44 = j39 - f24;
                long f25 = ThreefishEngine.f(f19, 39, j40);
                long j45 = j40 - f25;
                long f26 = ThreefishEngine.f(f22, 33, j45);
                long j46 = j45 - f26;
                long f27 = ThreefishEngine.f(f25, 27, j42);
                long j47 = j42 - f27;
                long f28 = ThreefishEngine.f(f24, 14, j43);
                long j48 = j43 - f28;
                long[] jArr6 = jArr4;
                long f29 = ThreefishEngine.f(f23, 42, j44);
                long j49 = j44 - f29;
                long f30 = ThreefishEngine.f(f26, 46, j49);
                long j50 = j49 - f30;
                j5 = ThreefishEngine.f(f29, 36, j46);
                long f31 = ThreefishEngine.f(f28, 19, j47);
                j6 = j47 - f31;
                j9 = ThreefishEngine.f(f27, 37, j48);
                j8 = j48 - j9;
                j4 = j46 - j5;
                j3 = f30;
                j7 = f31;
                i3 = i14 - 2;
                iArr2 = iArr4;
                jArr3 = jArr5;
                c2 = 0;
                i2 = 1;
                j2 = j50;
                jArr4 = jArr6;
                iArr = iArr3;
            }
            long[] jArr7 = jArr3;
            long[] jArr8 = jArr4;
            char c3 = c2;
            long j51 = j2 - jArr7[c3];
            long j52 = j3 - jArr7[1];
            long j53 = j4 - jArr7[2];
            long j54 = j5 - jArr7[3];
            long j55 = j6 - jArr7[4];
            long j56 = j8 - (jArr7[6] + jArr8[1]);
            jArr2[c3] = j51;
            jArr2[1] = j52;
            jArr2[2] = j53;
            jArr2[3] = j54;
            jArr2[4] = j55;
            jArr2[5] = j7 - (jArr7[5] + jArr8[c3]);
            jArr2[6] = j56;
            jArr2[7] = j9 - jArr7[7];
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        public void encryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.f13401b;
            long[] jArr4 = this.f13400a;
            int[] iArr = ThreefishEngine.MOD9;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 17) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j2 = jArr[0];
            long j3 = jArr[1];
            long j4 = jArr[2];
            long j5 = jArr[3];
            long j6 = jArr[4];
            long j7 = jArr[5];
            long j8 = jArr[6];
            long j9 = jArr[7];
            long j10 = j2 + jArr3[0];
            long j11 = j3 + jArr3[1];
            long j12 = j4 + jArr3[2];
            long j13 = j5 + jArr3[3];
            long j14 = j6 + jArr3[4];
            long j15 = j7 + jArr3[5] + jArr4[0];
            long j16 = j8 + jArr3[6] + jArr4[1];
            int i2 = 1;
            long j17 = j13;
            long j18 = j15;
            long j19 = j9 + jArr3[7];
            while (i2 < 18) {
                int i3 = iArr[i2];
                int i4 = iArr2[i2];
                long j20 = j10 + j11;
                long e2 = ThreefishEngine.e(j11, 46, j20);
                long j21 = j12 + j17;
                long e3 = ThreefishEngine.e(j17, 36, j21);
                long[] jArr5 = jArr3;
                int[] iArr3 = iArr;
                long j22 = j18;
                long j23 = j14 + j22;
                long e4 = ThreefishEngine.e(j22, 19, j23);
                int i5 = i2;
                long j24 = j19;
                long j25 = j16 + j24;
                long e5 = ThreefishEngine.e(j24, 37, j25);
                long j26 = j21 + e2;
                long e6 = ThreefishEngine.e(e2, 33, j26);
                long j27 = j23 + e5;
                long e7 = ThreefishEngine.e(e5, 27, j27);
                long j28 = j25 + e4;
                long e8 = ThreefishEngine.e(e4, 14, j28);
                long j29 = j20 + e3;
                long e9 = ThreefishEngine.e(e3, 42, j29);
                long j30 = j27 + e6;
                long e10 = ThreefishEngine.e(e6, 17, j30);
                long j31 = j28 + e9;
                long e11 = ThreefishEngine.e(e9, 49, j31);
                long j32 = j29 + e8;
                long e12 = ThreefishEngine.e(e8, 36, j32);
                long j33 = j26 + e7;
                long e13 = ThreefishEngine.e(e7, 39, j33);
                long j34 = j31 + e10;
                long e14 = ThreefishEngine.e(e10, 44, j34);
                long j35 = j32 + e13;
                long e15 = ThreefishEngine.e(e13, 9, j35);
                long j36 = j33 + e12;
                long e16 = ThreefishEngine.e(e12, 54, j36);
                long j37 = j30 + e11;
                long e17 = ThreefishEngine.e(e11, 56, j37);
                long j38 = j35 + jArr5[i3];
                int i6 = i3 + 1;
                long j39 = e14 + jArr5[i6];
                int i7 = i3 + 2;
                long j40 = j36 + jArr5[i7];
                int i8 = i3 + 3;
                long j41 = e17 + jArr5[i8];
                int i9 = i3 + 4;
                long j42 = j37 + jArr5[i9];
                int i10 = i3 + 5;
                long j43 = e16 + jArr5[i10] + jArr4[i4];
                int i11 = i3 + 6;
                int i12 = i4 + 1;
                long j44 = j34 + jArr5[i11] + jArr4[i12];
                int i13 = i3 + 7;
                long j45 = i5;
                long j46 = e15 + jArr5[i13] + j45;
                long j47 = j38 + j39;
                long e18 = ThreefishEngine.e(j39, 39, j47);
                long j48 = j40 + j41;
                long e19 = ThreefishEngine.e(j41, 30, j48);
                long j49 = j42 + j43;
                long e20 = ThreefishEngine.e(j43, 34, j49);
                long j50 = j44 + j46;
                long e21 = ThreefishEngine.e(j46, 24, j50);
                long j51 = j48 + e18;
                long e22 = ThreefishEngine.e(e18, 13, j51);
                long j52 = j49 + e21;
                long e23 = ThreefishEngine.e(e21, 50, j52);
                long j53 = j50 + e20;
                long e24 = ThreefishEngine.e(e20, 10, j53);
                long j54 = j47 + e19;
                long e25 = ThreefishEngine.e(e19, 17, j54);
                long j55 = j52 + e22;
                long e26 = ThreefishEngine.e(e22, 25, j55);
                long j56 = j53 + e25;
                long e27 = ThreefishEngine.e(e25, 29, j56);
                long j57 = j54 + e24;
                long e28 = ThreefishEngine.e(e24, 39, j57);
                long j58 = j51 + e23;
                long e29 = ThreefishEngine.e(e23, 43, j58);
                long j59 = j56 + e26;
                long e30 = ThreefishEngine.e(e26, 8, j59);
                long j60 = j57 + e29;
                long e31 = ThreefishEngine.e(e29, 35, j60);
                long j61 = j58 + e28;
                long e32 = ThreefishEngine.e(e28, 56, j61);
                long j62 = j55 + e27;
                long e33 = ThreefishEngine.e(e27, 22, j62);
                long j63 = j60 + jArr5[i6];
                j11 = e30 + jArr5[i7];
                long j64 = j61 + jArr5[i8];
                long j65 = e33 + jArr5[i9];
                long j66 = j62 + jArr5[i10];
                long j67 = e32 + jArr5[i11] + jArr4[i12];
                j16 = j59 + jArr5[i13] + jArr4[i4 + 2];
                j19 = e31 + jArr5[i3 + 8] + j45 + 1;
                j14 = j66;
                j18 = j67;
                iArr = iArr3;
                iArr2 = iArr2;
                jArr3 = jArr5;
                i2 = i5 + 2;
                j17 = j65;
                j12 = j64;
                j10 = j63;
            }
            jArr2[0] = j10;
            jArr2[1] = j11;
            jArr2[2] = j12;
            jArr2[3] = j17;
            jArr2[4] = j14;
            jArr2[5] = j18;
            jArr2[6] = j16;
            jArr2[7] = j19;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static abstract class ThreefishCipher {

        /* renamed from: a  reason: collision with root package name */
        protected final long[] f13400a;

        /* renamed from: b  reason: collision with root package name */
        protected final long[] f13401b;

        protected ThreefishCipher(long[] jArr, long[] jArr2) {
            this.f13401b = jArr;
            this.f13400a = jArr2;
        }

        abstract void decryptBlock(long[] jArr, long[] jArr2);

        abstract void encryptBlock(long[] jArr, long[] jArr2);
    }

    static {
        int[] iArr = new int[80];
        MOD9 = iArr;
        MOD17 = new int[iArr.length];
        MOD5 = new int[iArr.length];
        MOD3 = new int[iArr.length];
        int i2 = 0;
        while (true) {
            int[] iArr2 = MOD9;
            if (i2 >= iArr2.length) {
                return;
            }
            MOD17[i2] = i2 % 17;
            iArr2[i2] = i2 % 9;
            MOD5[i2] = i2 % 5;
            MOD3[i2] = i2 % 3;
            i2++;
        }
    }

    public ThreefishEngine(int i2) {
        ThreefishCipher threefish256Cipher;
        long[] jArr = new long[5];
        this.f13399t = jArr;
        int i3 = i2 / 8;
        this.blocksizeBytes = i3;
        int i4 = i3 / 8;
        this.blocksizeWords = i4;
        this.currentBlock = new long[i4];
        long[] jArr2 = new long[(i4 * 2) + 1];
        this.kw = jArr2;
        if (i2 == 256) {
            threefish256Cipher = new Threefish256Cipher(jArr2, jArr);
        } else if (i2 == 512) {
            threefish256Cipher = new Threefish512Cipher(jArr2, jArr);
        } else if (i2 != 1024) {
            throw new IllegalArgumentException("Invalid blocksize - Threefish is defined with block size of 256, 512, or 1024 bits");
        } else {
            threefish256Cipher = new Threefish1024Cipher(jArr2, jArr);
        }
        this.cipher = threefish256Cipher;
    }

    public static long bytesToWord(byte[] bArr, int i2) {
        if (i2 + 8 <= bArr.length) {
            int i3 = i2 + 1;
            int i4 = i3 + 1;
            int i5 = i4 + 1;
            int i6 = i5 + 1;
            int i7 = i6 + 1;
            int i8 = i7 + 1;
            return ((bArr[i8 + 1] & 255) << 56) | (bArr[i2] & 255) | ((bArr[i3] & 255) << 8) | ((bArr[i4] & 255) << 16) | ((bArr[i5] & 255) << 24) | ((bArr[i6] & 255) << 32) | ((bArr[i7] & 255) << 40) | ((bArr[i8] & 255) << 48);
        }
        throw new IllegalArgumentException();
    }

    static long e(long j2, int i2, long j3) {
        return ((j2 >>> (-i2)) | (j2 << i2)) ^ j3;
    }

    static long f(long j2, int i2, long j3) {
        long j4 = j2 ^ j3;
        return (j4 << (-i2)) | (j4 >>> i2);
    }

    private void setKey(long[] jArr) {
        if (jArr.length != this.blocksizeWords) {
            throw new IllegalArgumentException("Threefish key must be same size as block (" + this.blocksizeWords + " words)");
        }
        long j2 = C_240;
        int i2 = 0;
        while (true) {
            int i3 = this.blocksizeWords;
            if (i2 >= i3) {
                long[] jArr2 = this.kw;
                jArr2[i3] = j2;
                System.arraycopy(jArr2, 0, jArr2, i3 + 1, i3);
                return;
            }
            long[] jArr3 = this.kw;
            jArr3[i2] = jArr[i2];
            j2 ^= jArr3[i2];
            i2++;
        }
    }

    private void setTweak(long[] jArr) {
        if (jArr.length != 2) {
            throw new IllegalArgumentException("Tweak must be 2 words.");
        }
        long[] jArr2 = this.f13399t;
        jArr2[0] = jArr[0];
        jArr2[1] = jArr[1];
        jArr2[2] = jArr2[0] ^ jArr2[1];
        jArr2[3] = jArr2[0];
        jArr2[4] = jArr2[1];
    }

    public static void wordToBytes(long j2, byte[] bArr, int i2) {
        if (i2 + 8 > bArr.length) {
            throw new IllegalArgumentException();
        }
        int i3 = i2 + 1;
        bArr[i2] = (byte) j2;
        int i4 = i3 + 1;
        bArr[i3] = (byte) (j2 >> 8);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (j2 >> 16);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (j2 >> 24);
        int i7 = i6 + 1;
        bArr[i6] = (byte) (j2 >> 32);
        int i8 = i7 + 1;
        bArr[i7] = (byte) (j2 >> 40);
        bArr[i8] = (byte) (j2 >> 48);
        bArr[i8 + 1] = (byte) (j2 >> 56);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Threefish-" + (this.blocksizeBytes * 8);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.blocksizeBytes;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        byte[] key;
        byte[] bArr;
        long[] jArr;
        long[] jArr2 = null;
        if (cipherParameters instanceof TweakableBlockCipherParameters) {
            TweakableBlockCipherParameters tweakableBlockCipherParameters = (TweakableBlockCipherParameters) cipherParameters;
            key = tweakableBlockCipherParameters.getKey().getKey();
            bArr = tweakableBlockCipherParameters.getTweak();
        } else if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("Invalid parameter passed to Threefish init - " + cipherParameters.getClass().getName());
        } else {
            key = ((KeyParameter) cipherParameters).getKey();
            bArr = null;
        }
        if (key == null) {
            jArr = null;
        } else if (key.length != this.blocksizeBytes) {
            throw new IllegalArgumentException("Threefish key must be same size as block (" + this.blocksizeBytes + " bytes)");
        } else {
            int i2 = this.blocksizeWords;
            jArr = new long[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                jArr[i3] = bytesToWord(key, i3 * 8);
            }
        }
        if (bArr != null) {
            if (bArr.length != 16) {
                throw new IllegalArgumentException("Threefish tweak must be 16 bytes");
            }
            jArr2 = new long[]{bytesToWord(bArr, 0), bytesToWord(bArr, 8)};
        }
        init(z, jArr, jArr2);
    }

    public void init(boolean z, long[] jArr, long[] jArr2) {
        this.forEncryption = z;
        if (jArr != null) {
            setKey(jArr);
        }
        if (jArr2 != null) {
            setTweak(jArr2);
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int i4 = this.blocksizeBytes;
        if (i2 + i4 > bArr.length) {
            throw new DataLengthException("Input buffer too short");
        }
        if (i4 + i3 > bArr2.length) {
            throw new OutputLengthException("Output buffer too short");
        }
        int i5 = 0;
        for (int i6 = 0; i6 < this.blocksizeBytes; i6 += 8) {
            this.currentBlock[i6 >> 3] = bytesToWord(bArr, i2 + i6);
        }
        long[] jArr = this.currentBlock;
        processBlock(jArr, jArr);
        while (true) {
            int i7 = this.blocksizeBytes;
            if (i5 >= i7) {
                return i7;
            }
            wordToBytes(this.currentBlock[i5 >> 3], bArr2, i3 + i5);
            i5 += 8;
        }
    }

    public int processBlock(long[] jArr, long[] jArr2) {
        long[] jArr3 = this.kw;
        int i2 = this.blocksizeWords;
        if (jArr3[i2] != 0) {
            if (jArr.length == i2) {
                if (jArr2.length == i2) {
                    if (this.forEncryption) {
                        this.cipher.encryptBlock(jArr, jArr2);
                    } else {
                        this.cipher.decryptBlock(jArr, jArr2);
                    }
                    return this.blocksizeWords;
                }
                throw new OutputLengthException("Output buffer too short");
            }
            throw new DataLengthException("Input buffer too short");
        }
        throw new IllegalStateException("Threefish engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
