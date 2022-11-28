package kotlin;

import kotlin.internal.InlineOnly;
/* loaded from: classes3.dex */
public final class ULongKt {
    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final long toULong(byte b2) {
        return ULong.m359constructorimpl(b2);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final long toULong(double d2) {
        return UnsignedKt.doubleToULong(d2);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final long toULong(float f2) {
        return UnsignedKt.doubleToULong(f2);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final long toULong(int i2) {
        return ULong.m359constructorimpl(i2);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final long toULong(long j2) {
        return ULong.m359constructorimpl(j2);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final long toULong(short s2) {
        return ULong.m359constructorimpl(s2);
    }
}
