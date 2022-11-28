package kotlin.internal;

import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedKt;
/* loaded from: classes3.dex */
public final class UProgressionUtilKt {
    /* renamed from: differenceModulo-WZ9TVnA  reason: not valid java name */
    private static final int m1383differenceModuloWZ9TVnA(int i2, int i3, int i4) {
        int m535uintRemainderJ1ME1BU = UnsignedKt.m535uintRemainderJ1ME1BU(i2, i4);
        int m535uintRemainderJ1ME1BU2 = UnsignedKt.m535uintRemainderJ1ME1BU(i3, i4);
        int uintCompare = UnsignedKt.uintCompare(m535uintRemainderJ1ME1BU, m535uintRemainderJ1ME1BU2);
        int m281constructorimpl = UInt.m281constructorimpl(m535uintRemainderJ1ME1BU - m535uintRemainderJ1ME1BU2);
        return uintCompare >= 0 ? m281constructorimpl : UInt.m281constructorimpl(m281constructorimpl + i4);
    }

    /* renamed from: differenceModulo-sambcqE  reason: not valid java name */
    private static final long m1384differenceModulosambcqE(long j2, long j3, long j4) {
        long m537ulongRemaindereb3DHEI = UnsignedKt.m537ulongRemaindereb3DHEI(j2, j4);
        long m537ulongRemaindereb3DHEI2 = UnsignedKt.m537ulongRemaindereb3DHEI(j3, j4);
        int ulongCompare = UnsignedKt.ulongCompare(m537ulongRemaindereb3DHEI, m537ulongRemaindereb3DHEI2);
        long m359constructorimpl = ULong.m359constructorimpl(m537ulongRemaindereb3DHEI - m537ulongRemaindereb3DHEI2);
        return ulongCompare >= 0 ? m359constructorimpl : ULong.m359constructorimpl(m359constructorimpl + j4);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    /* renamed from: getProgressionLastElement-7ftBX0g  reason: not valid java name */
    public static final long m1385getProgressionLastElement7ftBX0g(long j2, long j3, long j4) {
        long m1384differenceModulosambcqE;
        int i2 = (j4 > 0L ? 1 : (j4 == 0L ? 0 : -1));
        if (i2 > 0) {
            if (UnsignedKt.ulongCompare(j2, j3) >= 0) {
                return j3;
            }
            m1384differenceModulosambcqE = j3 - m1384differenceModulosambcqE(j3, j2, ULong.m359constructorimpl(j4));
        } else if (i2 >= 0) {
            throw new IllegalArgumentException("Step is zero.");
        } else {
            if (UnsignedKt.ulongCompare(j2, j3) <= 0) {
                return j3;
            }
            m1384differenceModulosambcqE = j3 + m1384differenceModulosambcqE(j2, j3, ULong.m359constructorimpl(-j4));
        }
        return ULong.m359constructorimpl(m1384differenceModulosambcqE);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    /* renamed from: getProgressionLastElement-Nkh28Cs  reason: not valid java name */
    public static final int m1386getProgressionLastElementNkh28Cs(int i2, int i3, int i4) {
        int m1383differenceModuloWZ9TVnA;
        if (i4 > 0) {
            if (UnsignedKt.uintCompare(i2, i3) >= 0) {
                return i3;
            }
            m1383differenceModuloWZ9TVnA = i3 - m1383differenceModuloWZ9TVnA(i3, i2, UInt.m281constructorimpl(i4));
        } else if (i4 >= 0) {
            throw new IllegalArgumentException("Step is zero.");
        } else {
            if (UnsignedKt.uintCompare(i2, i3) <= 0) {
                return i3;
            }
            m1383differenceModuloWZ9TVnA = i3 + m1383differenceModuloWZ9TVnA(i2, i3, UInt.m281constructorimpl(-i4));
        }
        return UInt.m281constructorimpl(m1383differenceModuloWZ9TVnA);
    }
}
