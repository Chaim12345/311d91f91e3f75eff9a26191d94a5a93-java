package kotlin;

import kotlin.internal.InlineOnly;
/* loaded from: classes3.dex */
public final class UByteKt {
    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final byte toUByte(byte b2) {
        return UByte.m205constructorimpl(b2);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final byte toUByte(int i2) {
        return UByte.m205constructorimpl((byte) i2);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final byte toUByte(long j2) {
        return UByte.m205constructorimpl((byte) j2);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    private static final byte toUByte(short s2) {
        return UByte.m205constructorimpl((byte) s2);
    }
}
