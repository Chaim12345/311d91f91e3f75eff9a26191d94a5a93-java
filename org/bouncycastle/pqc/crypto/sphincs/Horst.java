package org.bouncycastle.pqc.crypto.sphincs;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.constraintlayout.core.motion.utils.TypedValues;
/* loaded from: classes4.dex */
class Horst {
    static void a(byte[] bArr, byte[] bArr2) {
        Seed.b(bArr, 0, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, bArr2, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(HashFunctions hashFunctions, byte[] bArr, int i2, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        byte[] bArr6 = new byte[2097152];
        byte[] bArr7 = new byte[4194272];
        a(bArr6, bArr3);
        for (int i3 = 0; i3 < 65536; i3++) {
            hashFunctions.d(bArr7, (65535 + i3) * 32, bArr6, i3 * 32);
        }
        for (int i4 = 0; i4 < 16; i4++) {
            int i5 = 16 - i4;
            long j2 = (1 << i5) - 1;
            int i6 = 1 << (i5 - 1);
            long j3 = i6 - 1;
            int i7 = 0;
            while (i7 < i6) {
                hashFunctions.c(bArr7, (int) ((i7 + j3) * 32), bArr7, (int) (((i7 * 2) + j2) * 32), bArr4, i4 * 2 * 32);
                i7++;
                i6 = i6;
                j3 = j3;
            }
        }
        int i8 = 2016;
        int i9 = i2;
        while (i8 < 4064) {
            bArr[i9] = bArr7[i8];
            i8++;
            i9++;
        }
        for (int i10 = 0; i10 < 32; i10++) {
            int i11 = i10 * 2;
            int i12 = (bArr5[i11] & 255) + ((bArr5[i11 + 1] & 255) << 8);
            int i13 = 0;
            while (i13 < 32) {
                bArr[i9] = bArr6[(i12 * 32) + i13];
                i13++;
                i9++;
            }
            int i14 = i12 + 65535;
            for (int i15 = 0; i15 < 10; i15++) {
                int i16 = (i14 & 1) != 0 ? i14 + 1 : i14 - 1;
                int i17 = 0;
                while (i17 < 32) {
                    bArr[i9] = bArr7[(i16 * 32) + i17];
                    i17++;
                    i9++;
                }
                i14 = (i16 - 1) / 2;
            }
        }
        for (int i18 = 0; i18 < 32; i18++) {
            bArr2[i18] = bArr7[i18];
        }
        return 13312;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(HashFunctions hashFunctions, byte[] bArr, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4) {
        int i3;
        byte[] bArr5 = new byte[1024];
        int i4 = i2 + 2048;
        int i5 = 0;
        while (i5 < 32) {
            int i6 = i5 * 2;
            int i7 = (bArr4[i6] & 255) + ((bArr4[i6 + 1] & 255) << 8);
            if ((i7 & 1) == 0) {
                hashFunctions.d(bArr5, 0, bArr2, i4);
                for (int i8 = 0; i8 < 32; i8++) {
                    bArr5[i8 + 32] = bArr2[i4 + 32 + i8];
                }
            } else {
                hashFunctions.d(bArr5, 32, bArr2, i4);
                for (int i9 = 0; i9 < 32; i9++) {
                    bArr5[i9] = bArr2[i4 + 32 + i9];
                }
            }
            int i10 = i4 + 64;
            int i11 = 1;
            while (i11 < 10) {
                int i12 = i7 >>> 1;
                if ((i12 & 1) == 0) {
                    i3 = i11;
                    hashFunctions.c(bArr5, 0, bArr5, 0, bArr3, (i11 - 1) * 2 * 32);
                    for (int i13 = 0; i13 < 32; i13++) {
                        bArr5[i13 + 32] = bArr2[i10 + i13];
                    }
                } else {
                    i3 = i11;
                    hashFunctions.c(bArr5, 32, bArr5, 0, bArr3, (i3 - 1) * 2 * 32);
                    for (int i14 = 0; i14 < 32; i14++) {
                        bArr5[i14] = bArr2[i10 + i14];
                    }
                }
                i10 += 32;
                i11 = i3 + 1;
                i7 = i12;
            }
            int i15 = i7 >>> 1;
            hashFunctions.c(bArr5, 0, bArr5, 0, bArr3, 576);
            for (int i16 = 0; i16 < 32; i16++) {
                if (bArr2[(i15 * 32) + i2 + i16] != bArr5[i16]) {
                    for (int i17 = 0; i17 < 32; i17++) {
                        bArr[i17] = 0;
                    }
                    return -1;
                }
            }
            i5++;
            i4 = i10;
        }
        for (int i18 = 0; i18 < 32; i18++) {
            hashFunctions.c(bArr5, i18 * 32, bArr2, i2 + (i18 * 2 * 32), bArr3, 640);
        }
        for (int i19 = 0; i19 < 16; i19++) {
            hashFunctions.c(bArr5, i19 * 32, bArr5, i19 * 2 * 32, bArr3, TypedValues.Transition.TYPE_AUTO_TRANSITION);
        }
        for (int i20 = 0; i20 < 8; i20++) {
            hashFunctions.c(bArr5, i20 * 32, bArr5, i20 * 2 * 32, bArr3, 768);
        }
        for (int i21 = 0; i21 < 4; i21++) {
            hashFunctions.c(bArr5, i21 * 32, bArr5, i21 * 2 * 32, bArr3, 832);
        }
        for (int i22 = 0; i22 < 2; i22++) {
            hashFunctions.c(bArr5, i22 * 32, bArr5, i22 * 2 * 32, bArr3, 896);
        }
        hashFunctions.c(bArr, 0, bArr5, 0, bArr3, 960);
        return 0;
    }
}
