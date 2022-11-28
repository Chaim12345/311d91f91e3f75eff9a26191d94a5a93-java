package kotlin;

import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt__CharJVMKt;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.jetbrains.annotations.NotNull;
@JvmName(name = "UnsignedKt")
/* loaded from: classes3.dex */
public final class UnsignedKt {
    @PublishedApi
    public static final int doubleToUInt(double d2) {
        if (!Double.isNaN(d2) && d2 > uintToDouble(0)) {
            if (d2 >= uintToDouble(-1)) {
                return -1;
            }
            return UInt.m281constructorimpl(d2 <= 2.147483647E9d ? (int) d2 : UInt.m281constructorimpl((int) (d2 - Integer.MAX_VALUE)) + UInt.m281constructorimpl(Integer.MAX_VALUE));
        }
        return 0;
    }

    @PublishedApi
    public static final long doubleToULong(double d2) {
        if (!Double.isNaN(d2) && d2 > ulongToDouble(0L)) {
            if (d2 >= ulongToDouble(-1L)) {
                return -1L;
            }
            return ULong.m359constructorimpl(d2 < 9.223372036854776E18d ? (long) d2 : ULong.m359constructorimpl((long) (d2 - 9.223372036854776E18d)) - Long.MIN_VALUE);
        }
        return 0L;
    }

    @PublishedApi
    public static final int uintCompare(int i2, int i3) {
        return Intrinsics.compare(i2 ^ Integer.MIN_VALUE, i3 ^ Integer.MIN_VALUE);
    }

    @PublishedApi
    /* renamed from: uintDivide-J1ME1BU  reason: not valid java name */
    public static final int m534uintDivideJ1ME1BU(int i2, int i3) {
        return UInt.m281constructorimpl((int) ((i2 & BodyPartID.bodyIdMax) / (i3 & BodyPartID.bodyIdMax)));
    }

    @PublishedApi
    /* renamed from: uintRemainder-J1ME1BU  reason: not valid java name */
    public static final int m535uintRemainderJ1ME1BU(int i2, int i3) {
        return UInt.m281constructorimpl((int) ((i2 & BodyPartID.bodyIdMax) % (i3 & BodyPartID.bodyIdMax)));
    }

    @PublishedApi
    public static final double uintToDouble(int i2) {
        return (Integer.MAX_VALUE & i2) + (((i2 >>> 31) << 30) * 2);
    }

    @PublishedApi
    public static final int ulongCompare(long j2, long j3) {
        return Intrinsics.compare(j2 ^ Long.MIN_VALUE, j3 ^ Long.MIN_VALUE);
    }

    @PublishedApi
    /* renamed from: ulongDivide-eb3DHEI  reason: not valid java name */
    public static final long m536ulongDivideeb3DHEI(long j2, long j3) {
        if (j3 < 0) {
            return ulongCompare(j2, j3) < 0 ? ULong.m359constructorimpl(0L) : ULong.m359constructorimpl(1L);
        } else if (j2 >= 0) {
            return ULong.m359constructorimpl(j2 / j3);
        } else {
            long j4 = ((j2 >>> 1) / j3) << 1;
            return ULong.m359constructorimpl(j4 + (ulongCompare(ULong.m359constructorimpl(j2 - (j4 * j3)), ULong.m359constructorimpl(j3)) < 0 ? 0 : 1));
        }
    }

    @PublishedApi
    /* renamed from: ulongRemainder-eb3DHEI  reason: not valid java name */
    public static final long m537ulongRemaindereb3DHEI(long j2, long j3) {
        long j4;
        if (j3 < 0) {
            return ulongCompare(j2, j3) < 0 ? j2 : ULong.m359constructorimpl(j2 - j3);
        }
        if (j2 >= 0) {
            j4 = j2 % j3;
        } else {
            long j5 = j2 - ((((j2 >>> 1) / j3) << 1) * j3);
            if (ulongCompare(ULong.m359constructorimpl(j5), ULong.m359constructorimpl(j3)) < 0) {
                j3 = 0;
            }
            j4 = j5 - j3;
        }
        return ULong.m359constructorimpl(j4);
    }

    @PublishedApi
    public static final double ulongToDouble(long j2) {
        return ((j2 >>> 11) * 2048) + (j2 & 2047);
    }

    @NotNull
    public static final String ulongToString(long j2) {
        return ulongToString(j2, 10);
    }

    @NotNull
    public static final String ulongToString(long j2, int i2) {
        int checkRadix;
        int checkRadix2;
        int checkRadix3;
        if (j2 >= 0) {
            checkRadix3 = CharsKt__CharJVMKt.checkRadix(i2);
            String l2 = Long.toString(j2, checkRadix3);
            Intrinsics.checkNotNullExpressionValue(l2, "toString(this, checkRadix(radix))");
            return l2;
        }
        long j3 = i2;
        long j4 = ((j2 >>> 1) / j3) << 1;
        long j5 = j2 - (j4 * j3);
        if (j5 >= j3) {
            j5 -= j3;
            j4++;
        }
        StringBuilder sb = new StringBuilder();
        checkRadix = CharsKt__CharJVMKt.checkRadix(i2);
        String l3 = Long.toString(j4, checkRadix);
        Intrinsics.checkNotNullExpressionValue(l3, "toString(this, checkRadix(radix))");
        sb.append(l3);
        checkRadix2 = CharsKt__CharJVMKt.checkRadix(i2);
        String l4 = Long.toString(j5, checkRadix2);
        Intrinsics.checkNotNullExpressionValue(l4, "toString(this, checkRadix(radix))");
        sb.append(l4);
        return sb.toString();
    }
}
