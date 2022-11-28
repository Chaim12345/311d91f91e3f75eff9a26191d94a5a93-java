package kotlin;

import kotlin.internal.InlineOnly;
/* loaded from: classes3.dex */
public final class UShortKt {
    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final short toUShort(byte b2) {
        return UShort.m465constructorimpl(b2);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final short toUShort(int i2) {
        return UShort.m465constructorimpl((short) i2);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final short toUShort(long j2) {
        return UShort.m465constructorimpl((short) j2);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final short toUShort(short s2) {
        return UShort.m465constructorimpl(s2);
    }
}
