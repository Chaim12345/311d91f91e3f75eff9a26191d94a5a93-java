package kotlin.collections;

import java.util.Collection;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.WasExperimental;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
class UCollectionsKt___UCollectionsKt {
    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @JvmName(name = "sumOfUByte")
    public static final int sumOfUByte(@NotNull Iterable<UByte> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        int i2 = 0;
        for (UByte uByte : iterable) {
            i2 = UInt.m281constructorimpl(i2 + UInt.m281constructorimpl(uByte.m254unboximpl() & 255));
        }
        return i2;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @JvmName(name = "sumOfUInt")
    public static final int sumOfUInt(@NotNull Iterable<UInt> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        int i2 = 0;
        for (UInt uInt : iterable) {
            i2 = UInt.m281constructorimpl(i2 + uInt.m332unboximpl());
        }
        return i2;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @JvmName(name = "sumOfULong")
    public static final long sumOfULong(@NotNull Iterable<ULong> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        long j2 = 0;
        for (ULong uLong : iterable) {
            j2 = ULong.m359constructorimpl(j2 + uLong.m410unboximpl());
        }
        return j2;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @JvmName(name = "sumOfUShort")
    public static final int sumOfUShort(@NotNull Iterable<UShort> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        int i2 = 0;
        for (UShort uShort : iterable) {
            i2 = UInt.m281constructorimpl(i2 + UInt.m281constructorimpl(uShort.m514unboximpl() & UShort.MAX_VALUE));
        }
        return i2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final byte[] toUByteArray(@NotNull Collection<UByte> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        byte[] m256constructorimpl = UByteArray.m256constructorimpl(collection.size());
        int i2 = 0;
        for (UByte uByte : collection) {
            UByteArray.m267setVurrAj0(m256constructorimpl, i2, uByte.m254unboximpl());
            i2++;
        }
        return m256constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final int[] toUIntArray(@NotNull Collection<UInt> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        int[] m334constructorimpl = UIntArray.m334constructorimpl(collection.size());
        int i2 = 0;
        for (UInt uInt : collection) {
            UIntArray.m345setVXSXFK8(m334constructorimpl, i2, uInt.m332unboximpl());
            i2++;
        }
        return m334constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final long[] toULongArray(@NotNull Collection<ULong> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        long[] m412constructorimpl = ULongArray.m412constructorimpl(collection.size());
        int i2 = 0;
        for (ULong uLong : collection) {
            ULongArray.m423setk8EXiF4(m412constructorimpl, i2, uLong.m410unboximpl());
            i2++;
        }
        return m412constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final short[] toUShortArray(@NotNull Collection<UShort> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        short[] m516constructorimpl = UShortArray.m516constructorimpl(collection.size());
        int i2 = 0;
        for (UShort uShort : collection) {
            UShortArray.m527set01HTLdE(m516constructorimpl, i2, uShort.m514unboximpl());
            i2++;
        }
        return m516constructorimpl;
    }
}
