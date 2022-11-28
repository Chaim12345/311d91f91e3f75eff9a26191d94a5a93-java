package org.bouncycastle.pqc.crypto.sphincs;

import com.google.common.base.Ascii;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class Wots {
    static void a(byte[] bArr, int i2, byte[] bArr2, int i3) {
        clear(bArr, i2, 2144);
        Seed.b(bArr, i2, 2144L, bArr2, i3);
    }

    static void b(HashFunctions hashFunctions, byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4, int i5) {
        for (int i6 = 0; i6 < 32; i6++) {
            bArr[i6 + i2] = bArr2[i6 + i3];
        }
        for (int i7 = 0; i7 < i5 && i7 < 16; i7++) {
            hashFunctions.e(bArr, i2, bArr, i2, bArr3, i4 + (i7 * 32));
        }
    }

    private static void clear(byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 != i3; i4++) {
            bArr[i4 + i2] = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(HashFunctions hashFunctions, byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4) {
        a(bArr, i2, bArr2, i3);
        for (int i5 = 0; i5 < 67; i5++) {
            int i6 = i2 + (i5 * 32);
            b(hashFunctions, bArr, i6, bArr, i6, bArr3, i4, 15);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(HashFunctions hashFunctions, byte[] bArr, int i2, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        int[] iArr = new int[67];
        int i3 = 0;
        int i4 = 0;
        while (i3 < 64) {
            int i5 = i3 / 2;
            iArr[i3] = bArr2[i5] & Ascii.SI;
            int i6 = i3 + 1;
            iArr[i6] = (bArr2[i5] & 255) >>> 4;
            i4 = i4 + (15 - iArr[i3]) + (15 - iArr[i6]);
            i3 += 2;
        }
        while (i3 < 67) {
            iArr[i3] = i4 & 15;
            i4 >>>= 4;
            i3++;
        }
        a(bArr, i2, bArr3, 0);
        for (int i7 = 0; i7 < 67; i7++) {
            int i8 = i2 + (i7 * 32);
            b(hashFunctions, bArr, i8, bArr, i8, bArr4, 0, iArr[i7]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(HashFunctions hashFunctions, byte[] bArr, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4) {
        int[] iArr = new int[67];
        int i3 = 0;
        int i4 = 0;
        while (i3 < 64) {
            int i5 = i3 / 2;
            iArr[i3] = bArr3[i5] & Ascii.SI;
            int i6 = i3 + 1;
            iArr[i6] = (bArr3[i5] & 255) >>> 4;
            i4 = i4 + (15 - iArr[i3]) + (15 - iArr[i6]);
            i3 += 2;
        }
        while (i3 < 67) {
            iArr[i3] = i4 & 15;
            i4 >>>= 4;
            i3++;
        }
        for (int i7 = 0; i7 < 67; i7++) {
            int i8 = i7 * 32;
            b(hashFunctions, bArr, i8, bArr2, i2 + i8, bArr4, iArr[i7] * 32, 15 - iArr[i7]);
        }
    }
}
