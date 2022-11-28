package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Comparator;
import org.bouncycastle.asn1.cmc.BodyPartID;
@Beta
@GwtCompatible
/* loaded from: classes2.dex */
public final class UnsignedInts {

    /* loaded from: classes2.dex */
    enum LexicographicalComparator implements Comparator<int[]> {
        INSTANCE;

        @Override // java.util.Comparator
        public int compare(int[] iArr, int[] iArr2) {
            int min = Math.min(iArr.length, iArr2.length);
            for (int i2 = 0; i2 < min; i2++) {
                if (iArr[i2] != iArr2[i2]) {
                    return UnsignedInts.compare(iArr[i2], iArr2[i2]);
                }
            }
            return iArr.length - iArr2.length;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "UnsignedInts.lexicographicalComparator()";
        }
    }

    private UnsignedInts() {
    }

    static int a(int i2) {
        return i2 ^ Integer.MIN_VALUE;
    }

    public static int checkedCast(long j2) {
        Preconditions.checkArgument((j2 >> 32) == 0, "out of range: %s", j2);
        return (int) j2;
    }

    public static int compare(int i2, int i3) {
        return Ints.compare(a(i2), a(i3));
    }

    @CanIgnoreReturnValue
    public static int decode(String str) {
        ParseRequest a2 = ParseRequest.a(str);
        try {
            return parseUnsignedInt(a2.f9367a, a2.f9368b);
        } catch (NumberFormatException e2) {
            NumberFormatException numberFormatException = new NumberFormatException("Error parsing value: " + str);
            numberFormatException.initCause(e2);
            throw numberFormatException;
        }
    }

    public static int divide(int i2, int i3) {
        return (int) (toLong(i2) / toLong(i3));
    }

    public static String join(String str, int... iArr) {
        Preconditions.checkNotNull(str);
        if (iArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(iArr.length * 5);
        sb.append(toString(iArr[0]));
        for (int i2 = 1; i2 < iArr.length; i2++) {
            sb.append(str);
            sb.append(toString(iArr[i2]));
        }
        return sb.toString();
    }

    public static Comparator<int[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static int max(int... iArr) {
        Preconditions.checkArgument(iArr.length > 0);
        int a2 = a(iArr[0]);
        for (int i2 = 1; i2 < iArr.length; i2++) {
            int a3 = a(iArr[i2]);
            if (a3 > a2) {
                a2 = a3;
            }
        }
        return a(a2);
    }

    public static int min(int... iArr) {
        Preconditions.checkArgument(iArr.length > 0);
        int a2 = a(iArr[0]);
        for (int i2 = 1; i2 < iArr.length; i2++) {
            int a3 = a(iArr[i2]);
            if (a3 < a2) {
                a2 = a3;
            }
        }
        return a(a2);
    }

    @CanIgnoreReturnValue
    public static int parseUnsignedInt(String str) {
        return parseUnsignedInt(str, 10);
    }

    @CanIgnoreReturnValue
    public static int parseUnsignedInt(String str, int i2) {
        Preconditions.checkNotNull(str);
        long parseLong = Long.parseLong(str, i2);
        if ((BodyPartID.bodyIdMax & parseLong) == parseLong) {
            return (int) parseLong;
        }
        throw new NumberFormatException("Input " + str + " in base " + i2 + " is not in the range of an unsigned integer");
    }

    public static int remainder(int i2, int i3) {
        return (int) (toLong(i2) % toLong(i3));
    }

    public static int saturatedCast(long j2) {
        if (j2 <= 0) {
            return 0;
        }
        if (j2 >= 4294967296L) {
            return -1;
        }
        return (int) j2;
    }

    public static void sort(int[] iArr) {
        Preconditions.checkNotNull(iArr);
        sort(iArr, 0, iArr.length);
    }

    public static void sort(int[] iArr, int i2, int i3) {
        Preconditions.checkNotNull(iArr);
        Preconditions.checkPositionIndexes(i2, i3, iArr.length);
        for (int i4 = i2; i4 < i3; i4++) {
            iArr[i4] = a(iArr[i4]);
        }
        Arrays.sort(iArr, i2, i3);
        while (i2 < i3) {
            iArr[i2] = a(iArr[i2]);
            i2++;
        }
    }

    public static void sortDescending(int[] iArr) {
        Preconditions.checkNotNull(iArr);
        sortDescending(iArr, 0, iArr.length);
    }

    public static void sortDescending(int[] iArr, int i2, int i3) {
        Preconditions.checkNotNull(iArr);
        Preconditions.checkPositionIndexes(i2, i3, iArr.length);
        for (int i4 = i2; i4 < i3; i4++) {
            iArr[i4] = Integer.MAX_VALUE ^ iArr[i4];
        }
        Arrays.sort(iArr, i2, i3);
        while (i2 < i3) {
            iArr[i2] = iArr[i2] ^ Integer.MAX_VALUE;
            i2++;
        }
    }

    public static long toLong(int i2) {
        return i2 & BodyPartID.bodyIdMax;
    }

    public static String toString(int i2) {
        return toString(i2, 10);
    }

    public static String toString(int i2, int i3) {
        return Long.toString(i2 & BodyPartID.bodyIdMax, i3);
    }
}
