package kotlin.random;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.SinceKotlin;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.WasExperimental;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.ULongRange;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class URandomKt {
    /* renamed from: checkUIntRangeBounds-J1ME1BU  reason: not valid java name */
    public static final void m1391checkUIntRangeBoundsJ1ME1BU(int i2, int i3) {
        if (!(UnsignedKt.uintCompare(i3, i2) > 0)) {
            throw new IllegalArgumentException(RandomKt.boundsErrorMessage(UInt.m275boximpl(i2), UInt.m275boximpl(i3)).toString());
        }
    }

    /* renamed from: checkULongRangeBounds-eb3DHEI  reason: not valid java name */
    public static final void m1392checkULongRangeBoundseb3DHEI(long j2, long j3) {
        if (!(UnsignedKt.ulongCompare(j3, j2) > 0)) {
            throw new IllegalArgumentException(RandomKt.boundsErrorMessage(ULong.m353boximpl(j2), ULong.m353boximpl(j3)).toString());
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final byte[] nextUBytes(@NotNull Random random, int i2) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        return UByteArray.m257constructorimpl(random.nextBytes(i2));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: nextUBytes-EVgfTAA  reason: not valid java name */
    public static final byte[] m1393nextUBytesEVgfTAA(@NotNull Random nextUBytes, @NotNull byte[] array) {
        Intrinsics.checkNotNullParameter(nextUBytes, "$this$nextUBytes");
        Intrinsics.checkNotNullParameter(array, "array");
        nextUBytes.nextBytes(array);
        return array;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: nextUBytes-Wvrt4B4  reason: not valid java name */
    public static final byte[] m1394nextUBytesWvrt4B4(@NotNull Random nextUBytes, @NotNull byte[] array, int i2, int i3) {
        Intrinsics.checkNotNullParameter(nextUBytes, "$this$nextUBytes");
        Intrinsics.checkNotNullParameter(array, "array");
        nextUBytes.nextBytes(array, i2, i3);
        return array;
    }

    /* renamed from: nextUBytes-Wvrt4B4$default  reason: not valid java name */
    public static /* synthetic */ byte[] m1395nextUBytesWvrt4B4$default(Random random, byte[] bArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = UByteArray.m263getSizeimpl(bArr);
        }
        return m1394nextUBytesWvrt4B4(random, bArr, i2, i3);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int nextUInt(@NotNull Random random) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        return UInt.m281constructorimpl(random.nextInt());
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int nextUInt(@NotNull Random random, @NotNull UIntRange range) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        Intrinsics.checkNotNullParameter(range, "range");
        if (!range.isEmpty()) {
            return UnsignedKt.uintCompare(range.m1401getLastpVg5ArA(), -1) < 0 ? m1396nextUInta8DCA5k(random, range.m1400getFirstpVg5ArA(), UInt.m281constructorimpl(range.m1401getLastpVg5ArA() + 1)) : UnsignedKt.uintCompare(range.m1400getFirstpVg5ArA(), 0) > 0 ? UInt.m281constructorimpl(m1396nextUInta8DCA5k(random, UInt.m281constructorimpl(range.m1400getFirstpVg5ArA() - 1), range.m1401getLastpVg5ArA()) + 1) : nextUInt(random);
        }
        throw new IllegalArgumentException("Cannot get random in empty range: " + range);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* renamed from: nextUInt-a8DCA5k  reason: not valid java name */
    public static final int m1396nextUInta8DCA5k(@NotNull Random nextUInt, int i2, int i3) {
        Intrinsics.checkNotNullParameter(nextUInt, "$this$nextUInt");
        m1391checkUIntRangeBoundsJ1ME1BU(i2, i3);
        return UInt.m281constructorimpl(nextUInt.nextInt(i2 ^ Integer.MIN_VALUE, i3 ^ Integer.MIN_VALUE) ^ Integer.MIN_VALUE);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* renamed from: nextUInt-qCasIEU  reason: not valid java name */
    public static final int m1397nextUIntqCasIEU(@NotNull Random nextUInt, int i2) {
        Intrinsics.checkNotNullParameter(nextUInt, "$this$nextUInt");
        return m1396nextUInta8DCA5k(nextUInt, 0, i2);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final long nextULong(@NotNull Random random) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        return ULong.m359constructorimpl(random.nextLong());
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final long nextULong(@NotNull Random random, @NotNull ULongRange range) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        Intrinsics.checkNotNullParameter(range, "range");
        if (range.isEmpty()) {
            throw new IllegalArgumentException("Cannot get random in empty range: " + range);
        }
        int ulongCompare = UnsignedKt.ulongCompare(range.m1407getLastsVKNKU(), -1L);
        long m1406getFirstsVKNKU = range.m1406getFirstsVKNKU();
        if (ulongCompare < 0) {
            return m1399nextULongjmpaWc(random, m1406getFirstsVKNKU, ULong.m359constructorimpl(range.m1407getLastsVKNKU() + ULong.m359constructorimpl(1 & BodyPartID.bodyIdMax)));
        }
        if (UnsignedKt.ulongCompare(m1406getFirstsVKNKU, 0L) > 0) {
            long m1406getFirstsVKNKU2 = range.m1406getFirstsVKNKU();
            long j2 = 1 & BodyPartID.bodyIdMax;
            return ULong.m359constructorimpl(m1399nextULongjmpaWc(random, ULong.m359constructorimpl(m1406getFirstsVKNKU2 - ULong.m359constructorimpl(j2)), range.m1407getLastsVKNKU()) + ULong.m359constructorimpl(j2));
        }
        return nextULong(random);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* renamed from: nextULong-V1Xi4fY  reason: not valid java name */
    public static final long m1398nextULongV1Xi4fY(@NotNull Random nextULong, long j2) {
        Intrinsics.checkNotNullParameter(nextULong, "$this$nextULong");
        return m1399nextULongjmpaWc(nextULong, 0L, j2);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* renamed from: nextULong-jmpaW-c  reason: not valid java name */
    public static final long m1399nextULongjmpaWc(@NotNull Random nextULong, long j2, long j3) {
        Intrinsics.checkNotNullParameter(nextULong, "$this$nextULong");
        m1392checkULongRangeBoundseb3DHEI(j2, j3);
        return ULong.m359constructorimpl(nextULong.nextLong(j2 ^ Long.MIN_VALUE, j3 ^ Long.MIN_VALUE) ^ Long.MIN_VALUE);
    }
}
