package com.google.android.gms.internal.mlkit_vision_barcode;

import javax.annotation.CheckForNull;
/* loaded from: classes2.dex */
final class zzbt {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(int i2) {
        return (i2 < 32 ? 4 : 2) * (i2 + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(@CheckForNull Object obj, @CheckForNull Object obj2, int i2, Object obj3, int[] iArr, Object[] objArr, @CheckForNull Object[] objArr2) {
        int i3;
        int i4;
        int a2 = zzbu.a(obj);
        int i5 = a2 & i2;
        int c2 = c(obj3, i5);
        if (c2 != 0) {
            int i6 = ~i2;
            int i7 = a2 & i6;
            int i8 = -1;
            while (true) {
                i3 = c2 - 1;
                i4 = iArr[i3];
                if ((i4 & i6) != i7 || !zzam.zza(obj, objArr[i3]) || (objArr2 != null && !zzam.zza(obj2, objArr2[i3]))) {
                    int i9 = i4 & i2;
                    if (i9 == 0) {
                        break;
                    }
                    i8 = i3;
                    c2 = i9;
                }
            }
            int i10 = i4 & i2;
            if (i8 == -1) {
                e(obj3, i5, i10);
            } else {
                iArr[i8] = (i10 & i2) | (iArr[i8] & i6);
            }
            return i3;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(Object obj, int i2) {
        return obj instanceof byte[] ? ((byte[]) obj)[i2] & 255 : obj instanceof short[] ? (char) ((short[]) obj)[i2] : ((int[]) obj)[i2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object d(int i2) {
        if (i2 >= 2 && i2 <= 1073741824 && Integer.highestOneBit(i2) == i2) {
            return i2 <= 256 ? new byte[i2] : i2 <= 65536 ? new short[i2] : new int[i2];
        }
        StringBuilder sb = new StringBuilder(52);
        sb.append("must be power of 2 between 2^1 and 2^30: ");
        sb.append(i2);
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void e(Object obj, int i2, int i3) {
        if (obj instanceof byte[]) {
            ((byte[]) obj)[i2] = (byte) i3;
        } else if (obj instanceof short[]) {
            ((short[]) obj)[i2] = (short) i3;
        } else {
            ((int[]) obj)[i2] = i3;
        }
    }
}
