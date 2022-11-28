package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import java.util.Arrays;
import kotlin.UShort;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtIncompatible
/* loaded from: classes2.dex */
final class CompactHashing {
    private static final int BYTE_MASK = 255;
    private static final int BYTE_MAX_SIZE = 256;
    private static final int HASH_TABLE_BITS_MAX_BITS = 5;
    private static final int MIN_HASH_TABLE_SIZE = 4;
    private static final int SHORT_MASK = 65535;
    private static final int SHORT_MAX_SIZE = 65536;

    private CompactHashing() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object a(int i2) {
        if (i2 >= 2 && i2 <= 1073741824 && Integer.highestOneBit(i2) == i2) {
            return i2 <= 256 ? new byte[i2] : i2 <= 65536 ? new short[i2] : new int[i2];
        }
        throw new IllegalArgumentException("must be power of 2 between 2^1 and 2^30: " + i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(int i2, int i3) {
        return i2 & (~i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(int i2, int i3) {
        return i2 & i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int d(int i2, int i3, int i4) {
        return (i2 & (~i4)) | (i3 & i4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int e(int i2) {
        return (i2 < 32 ? 4 : 2) * (i2 + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int f(@NullableDecl Object obj, @NullableDecl Object obj2, int i2, Object obj3, int[] iArr, Object[] objArr, @NullableDecl Object[] objArr2) {
        int i3;
        int i4;
        int d2 = Hashing.d(obj);
        int i5 = d2 & i2;
        int h2 = h(obj3, i5);
        if (h2 == 0) {
            return -1;
        }
        int b2 = b(d2, i2);
        int i6 = -1;
        while (true) {
            i3 = h2 - 1;
            i4 = iArr[i3];
            if (b(i4, i2) != b2 || !Objects.equal(obj, objArr[i3]) || (objArr2 != null && !Objects.equal(obj2, objArr2[i3]))) {
                int c2 = c(i4, i2);
                if (c2 == 0) {
                    return -1;
                }
                i6 = i3;
                h2 = c2;
            }
        }
        int c3 = c(i4, i2);
        if (i6 == -1) {
            i(obj3, i5, c3);
        } else {
            iArr[i6] = d(iArr[i6], c3, i2);
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void g(Object obj) {
        if (obj instanceof byte[]) {
            Arrays.fill((byte[]) obj, (byte) 0);
        } else if (obj instanceof short[]) {
            Arrays.fill((short[]) obj, (short) 0);
        } else {
            Arrays.fill((int[]) obj, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int h(Object obj, int i2) {
        return obj instanceof byte[] ? ((byte[]) obj)[i2] & 255 : obj instanceof short[] ? ((short[]) obj)[i2] & UShort.MAX_VALUE : ((int[]) obj)[i2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void i(Object obj, int i2, int i3) {
        if (obj instanceof byte[]) {
            ((byte[]) obj)[i2] = (byte) i3;
        } else if (obj instanceof short[]) {
            ((short[]) obj)[i2] = (short) i3;
        } else {
            ((int[]) obj)[i2] = i3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int j(int i2) {
        return Math.max(4, Hashing.a(i2 + 1, 1.0d));
    }
}
