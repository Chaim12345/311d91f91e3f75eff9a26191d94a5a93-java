package kotlin;

import kotlin.internal.InlineOnly;
/* loaded from: classes3.dex */
public final class CharCodeKt {
    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final char Char(int i2) {
        if (i2 < 0 || i2 > 65535) {
            throw new IllegalArgumentException("Invalid Char code: " + i2);
        }
        return (char) i2;
    }

    private static final int getCode(char c2) {
        return c2;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    public static /* synthetic */ void getCode$annotations(char c2) {
    }
}
