package kotlin.collections;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class UArraySortingKt {
    @ExperimentalUnsignedTypes
    /* renamed from: partition--nroSd4  reason: not valid java name */
    private static final int m634partitionnroSd4(long[] jArr, int i2, int i3) {
        long m418getsVKNKU = ULongArray.m418getsVKNKU(jArr, (i2 + i3) / 2);
        while (i2 <= i3) {
            while (UnsignedKt.ulongCompare(ULongArray.m418getsVKNKU(jArr, i2), m418getsVKNKU) < 0) {
                i2++;
            }
            while (UnsignedKt.ulongCompare(ULongArray.m418getsVKNKU(jArr, i3), m418getsVKNKU) > 0) {
                i3--;
            }
            if (i2 <= i3) {
                long m418getsVKNKU2 = ULongArray.m418getsVKNKU(jArr, i2);
                ULongArray.m423setk8EXiF4(jArr, i2, ULongArray.m418getsVKNKU(jArr, i3));
                ULongArray.m423setk8EXiF4(jArr, i3, m418getsVKNKU2);
                i2++;
                i3--;
            }
        }
        return i2;
    }

    @ExperimentalUnsignedTypes
    /* renamed from: partition-4UcCI2c  reason: not valid java name */
    private static final int m635partition4UcCI2c(byte[] bArr, int i2, int i3) {
        int i4;
        byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(bArr, (i2 + i3) / 2);
        while (i2 <= i3) {
            while (true) {
                i4 = m262getw2LRezQ & 255;
                if (Intrinsics.compare(UByteArray.m262getw2LRezQ(bArr, i2) & 255, i4) >= 0) {
                    break;
                }
                i2++;
            }
            while (Intrinsics.compare(UByteArray.m262getw2LRezQ(bArr, i3) & 255, i4) > 0) {
                i3--;
            }
            if (i2 <= i3) {
                byte m262getw2LRezQ2 = UByteArray.m262getw2LRezQ(bArr, i2);
                UByteArray.m267setVurrAj0(bArr, i2, UByteArray.m262getw2LRezQ(bArr, i3));
                UByteArray.m267setVurrAj0(bArr, i3, m262getw2LRezQ2);
                i2++;
                i3--;
            }
        }
        return i2;
    }

    @ExperimentalUnsignedTypes
    /* renamed from: partition-Aa5vz7o  reason: not valid java name */
    private static final int m636partitionAa5vz7o(short[] sArr, int i2, int i3) {
        int i4;
        short m522getMh2AYeg = UShortArray.m522getMh2AYeg(sArr, (i2 + i3) / 2);
        while (i2 <= i3) {
            while (true) {
                int m522getMh2AYeg2 = UShortArray.m522getMh2AYeg(sArr, i2) & UShort.MAX_VALUE;
                i4 = m522getMh2AYeg & UShort.MAX_VALUE;
                if (Intrinsics.compare(m522getMh2AYeg2, i4) >= 0) {
                    break;
                }
                i2++;
            }
            while (Intrinsics.compare(UShortArray.m522getMh2AYeg(sArr, i3) & UShort.MAX_VALUE, i4) > 0) {
                i3--;
            }
            if (i2 <= i3) {
                short m522getMh2AYeg3 = UShortArray.m522getMh2AYeg(sArr, i2);
                UShortArray.m527set01HTLdE(sArr, i2, UShortArray.m522getMh2AYeg(sArr, i3));
                UShortArray.m527set01HTLdE(sArr, i3, m522getMh2AYeg3);
                i2++;
                i3--;
            }
        }
        return i2;
    }

    @ExperimentalUnsignedTypes
    /* renamed from: partition-oBK06Vg  reason: not valid java name */
    private static final int m637partitionoBK06Vg(int[] iArr, int i2, int i3) {
        int m340getpVg5ArA = UIntArray.m340getpVg5ArA(iArr, (i2 + i3) / 2);
        while (i2 <= i3) {
            while (UnsignedKt.uintCompare(UIntArray.m340getpVg5ArA(iArr, i2), m340getpVg5ArA) < 0) {
                i2++;
            }
            while (UnsignedKt.uintCompare(UIntArray.m340getpVg5ArA(iArr, i3), m340getpVg5ArA) > 0) {
                i3--;
            }
            if (i2 <= i3) {
                int m340getpVg5ArA2 = UIntArray.m340getpVg5ArA(iArr, i2);
                UIntArray.m345setVXSXFK8(iArr, i2, UIntArray.m340getpVg5ArA(iArr, i3));
                UIntArray.m345setVXSXFK8(iArr, i3, m340getpVg5ArA2);
                i2++;
                i3--;
            }
        }
        return i2;
    }

    @ExperimentalUnsignedTypes
    /* renamed from: quickSort--nroSd4  reason: not valid java name */
    private static final void m638quickSortnroSd4(long[] jArr, int i2, int i3) {
        int m634partitionnroSd4 = m634partitionnroSd4(jArr, i2, i3);
        int i4 = m634partitionnroSd4 - 1;
        if (i2 < i4) {
            m638quickSortnroSd4(jArr, i2, i4);
        }
        if (m634partitionnroSd4 < i3) {
            m638quickSortnroSd4(jArr, m634partitionnroSd4, i3);
        }
    }

    @ExperimentalUnsignedTypes
    /* renamed from: quickSort-4UcCI2c  reason: not valid java name */
    private static final void m639quickSort4UcCI2c(byte[] bArr, int i2, int i3) {
        int m635partition4UcCI2c = m635partition4UcCI2c(bArr, i2, i3);
        int i4 = m635partition4UcCI2c - 1;
        if (i2 < i4) {
            m639quickSort4UcCI2c(bArr, i2, i4);
        }
        if (m635partition4UcCI2c < i3) {
            m639quickSort4UcCI2c(bArr, m635partition4UcCI2c, i3);
        }
    }

    @ExperimentalUnsignedTypes
    /* renamed from: quickSort-Aa5vz7o  reason: not valid java name */
    private static final void m640quickSortAa5vz7o(short[] sArr, int i2, int i3) {
        int m636partitionAa5vz7o = m636partitionAa5vz7o(sArr, i2, i3);
        int i4 = m636partitionAa5vz7o - 1;
        if (i2 < i4) {
            m640quickSortAa5vz7o(sArr, i2, i4);
        }
        if (m636partitionAa5vz7o < i3) {
            m640quickSortAa5vz7o(sArr, m636partitionAa5vz7o, i3);
        }
    }

    @ExperimentalUnsignedTypes
    /* renamed from: quickSort-oBK06Vg  reason: not valid java name */
    private static final void m641quickSortoBK06Vg(int[] iArr, int i2, int i3) {
        int m637partitionoBK06Vg = m637partitionoBK06Vg(iArr, i2, i3);
        int i4 = m637partitionoBK06Vg - 1;
        if (i2 < i4) {
            m641quickSortoBK06Vg(iArr, i2, i4);
        }
        if (m637partitionoBK06Vg < i3) {
            m641quickSortoBK06Vg(iArr, m637partitionoBK06Vg, i3);
        }
    }

    @ExperimentalUnsignedTypes
    /* renamed from: sortArray--nroSd4  reason: not valid java name */
    public static final void m642sortArraynroSd4(@NotNull long[] array, int i2, int i3) {
        Intrinsics.checkNotNullParameter(array, "array");
        m638quickSortnroSd4(array, i2, i3 - 1);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: sortArray-4UcCI2c  reason: not valid java name */
    public static final void m643sortArray4UcCI2c(@NotNull byte[] array, int i2, int i3) {
        Intrinsics.checkNotNullParameter(array, "array");
        m639quickSort4UcCI2c(array, i2, i3 - 1);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: sortArray-Aa5vz7o  reason: not valid java name */
    public static final void m644sortArrayAa5vz7o(@NotNull short[] array, int i2, int i3) {
        Intrinsics.checkNotNullParameter(array, "array");
        m640quickSortAa5vz7o(array, i2, i3 - 1);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: sortArray-oBK06Vg  reason: not valid java name */
    public static final void m645sortArrayoBK06Vg(@NotNull int[] array, int i2, int i3) {
        Intrinsics.checkNotNullParameter(array, "array");
        m641quickSortoBK06Vg(array, i2, i3 - 1);
    }
}
