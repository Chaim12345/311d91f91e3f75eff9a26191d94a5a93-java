package org.bouncycastle.crypto.params;
/* loaded from: classes3.dex */
public class DESedeParameters extends DESParameters {
    public static final int DES_EDE_KEY_LENGTH = 24;

    public DESedeParameters(byte[] bArr) {
        super(bArr);
        if (isWeakKey(bArr, 0, bArr.length)) {
            throw new IllegalArgumentException("attempt to create weak DESede key");
        }
    }

    public static boolean isReal2Key(byte[] bArr, int i2) {
        boolean z = false;
        for (int i3 = i2; i3 != i2 + 8; i3++) {
            if (bArr[i3] != bArr[i3 + 8]) {
                z = true;
            }
        }
        return z;
    }

    public static boolean isReal3Key(byte[] bArr, int i2) {
        int i3 = i2;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (true) {
            boolean z4 = true;
            if (i3 == i2 + 8) {
                break;
            }
            int i4 = i3 + 8;
            z |= bArr[i3] != bArr[i4];
            int i5 = i3 + 16;
            z2 |= bArr[i3] != bArr[i5];
            if (bArr[i4] == bArr[i5]) {
                z4 = false;
            }
            z3 |= z4;
            i3++;
        }
        return z && z2 && z3;
    }

    public static boolean isRealEDEKey(byte[] bArr, int i2) {
        return bArr.length == 16 ? isReal2Key(bArr, i2) : isReal3Key(bArr, i2);
    }

    public static boolean isWeakKey(byte[] bArr, int i2) {
        return isWeakKey(bArr, i2, bArr.length - i2);
    }

    public static boolean isWeakKey(byte[] bArr, int i2, int i3) {
        while (i2 < i3) {
            if (DESParameters.isWeakKey(bArr, i2)) {
                return true;
            }
            i2 += 8;
        }
        return false;
    }
}
