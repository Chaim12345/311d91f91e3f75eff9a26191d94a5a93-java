package androidx.core.graphics;

import android.graphics.Color;
import android.graphics.ColorSpace;
import androidx.annotation.ColorInt;
import androidx.annotation.RequiresApi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0006\u001a\r\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0087\n\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0000H\u0087\n\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u0087\n\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0000H\u0087\n\u001a\u0015\u0010\u0007\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\u0002\u001a\r\u0010\u0002\u001a\u00020\b*\u00020\bH\u0086\n\u001a\r\u0010\u0003\u001a\u00020\b*\u00020\bH\u0086\n\u001a\r\u0010\u0004\u001a\u00020\b*\u00020\bH\u0086\n\u001a\r\u0010\u0005\u001a\u00020\b*\u00020\bH\u0086\n\u001a\r\u0010\t\u001a\u00020\u0000*\u00020\bH\u0087\b\u001a\r\u0010\u000b\u001a\u00020\n*\u00020\bH\u0087\b\u001a\r\u0010\u0002\u001a\u00020\u0001*\u00020\nH\u0087\n\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\nH\u0087\n\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\nH\u0087\n\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\nH\u0087\n\u001a\r\u0010\t\u001a\u00020\u0000*\u00020\nH\u0087\b\u001a\r\u0010\f\u001a\u00020\b*\u00020\nH\u0087\b\u001a\u0015\u0010\u000f\u001a\u00020\n*\u00020\b2\u0006\u0010\u000e\u001a\u00020\rH\u0087\f\u001a\u0015\u0010\u000f\u001a\u00020\n*\u00020\b2\u0006\u0010\u000e\u001a\u00020\u0010H\u0087\f\u001a\u0015\u0010\u000f\u001a\u00020\n*\u00020\n2\u0006\u0010\u000e\u001a\u00020\rH\u0087\f\u001a\u0015\u0010\u000f\u001a\u00020\n*\u00020\n2\u0006\u0010\u000e\u001a\u00020\u0010H\u0087\f\u001a\u0015\u0010\u000f\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\rH\u0087\f\u001a\u0015\u0010\u000f\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0010H\u0087\f\u001a\r\u0010\f\u001a\u00020\b*\u00020\u0011H\u0087\b\"\u0018\u0010\u0014\u001a\u00020\b*\u00020\b8Æ\u0002@\u0006¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\"\u0018\u0010\u0016\u001a\u00020\b*\u00020\b8Æ\u0002@\u0006¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013\"\u0018\u0010\u0018\u001a\u00020\b*\u00020\b8Æ\u0002@\u0006¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013\"\u0018\u0010\u001a\u001a\u00020\b*\u00020\b8Æ\u0002@\u0006¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0013\"\u0018\u0010\u001d\u001a\u00020\u0001*\u00020\b8Ç\u0002@\u0006¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\"\u0018\u0010\u0014\u001a\u00020\u0001*\u00020\n8Ç\u0002@\u0006¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u001e\"\u0018\u0010\u0016\u001a\u00020\u0001*\u00020\n8Ç\u0002@\u0006¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u001e\"\u0018\u0010\u0018\u001a\u00020\u0001*\u00020\n8Ç\u0002@\u0006¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u001e\"\u0018\u0010\u001a\u001a\u00020\u0001*\u00020\n8Ç\u0002@\u0006¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001e\"\u0018\u0010\u001d\u001a\u00020\u0001*\u00020\n8Ç\u0002@\u0006¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001e\"\u0018\u0010 \u001a\u00020\u001f*\u00020\n8Ç\u0002@\u0006¢\u0006\u0006\u001a\u0004\b \u0010!\"\u0018\u0010\"\u001a\u00020\u001f*\u00020\n8Ç\u0002@\u0006¢\u0006\u0006\u001a\u0004\b\"\u0010!\"\u0018\u0010\u000e\u001a\u00020\u0010*\u00020\n8Ç\u0002@\u0006¢\u0006\u0006\u001a\u0004\b#\u0010$¨\u0006%"}, d2 = {"Landroid/graphics/Color;", "", "component1", "component2", "component3", "component4", "c", "plus", "", "toColor", "", "toColorLong", "toColorInt", "Landroid/graphics/ColorSpace$Named;", "colorSpace", "convertTo", "Landroid/graphics/ColorSpace;", "", "getAlpha", "(I)I", "alpha", "getRed", "red", "getGreen", "green", "getBlue", "blue", "getLuminance", "(I)F", "luminance", "(J)F", "", "isSrgb", "(J)Z", "isWideGamut", "getColorSpace", "(J)Landroid/graphics/ColorSpace;", "core-ktx_release"}, k = 2, mv = {1, 4, 2})
/* loaded from: classes.dex */
public final class ColorKt {
    @RequiresApi(26)
    public static final float component1(long j2) {
        return Color.red(j2);
    }

    @RequiresApi(26)
    public static final float component1(@NotNull Color component1) {
        Intrinsics.checkNotNullParameter(component1, "$this$component1");
        return component1.getComponent(0);
    }

    public static final int component1(@ColorInt int i2) {
        return (i2 >> 24) & 255;
    }

    @RequiresApi(26)
    public static final float component2(long j2) {
        return Color.green(j2);
    }

    @RequiresApi(26)
    public static final float component2(@NotNull Color component2) {
        Intrinsics.checkNotNullParameter(component2, "$this$component2");
        return component2.getComponent(1);
    }

    public static final int component2(@ColorInt int i2) {
        return (i2 >> 16) & 255;
    }

    @RequiresApi(26)
    public static final float component3(long j2) {
        return Color.blue(j2);
    }

    @RequiresApi(26)
    public static final float component3(@NotNull Color component3) {
        Intrinsics.checkNotNullParameter(component3, "$this$component3");
        return component3.getComponent(2);
    }

    public static final int component3(@ColorInt int i2) {
        return (i2 >> 8) & 255;
    }

    @RequiresApi(26)
    public static final float component4(long j2) {
        return Color.alpha(j2);
    }

    @RequiresApi(26)
    public static final float component4(@NotNull Color component4) {
        Intrinsics.checkNotNullParameter(component4, "$this$component4");
        return component4.getComponent(3);
    }

    public static final int component4(@ColorInt int i2) {
        return i2 & 255;
    }

    @RequiresApi(26)
    public static final long convertTo(@ColorInt int i2, @NotNull ColorSpace.Named colorSpace) {
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        return Color.convert(i2, ColorSpace.get(colorSpace));
    }

    @RequiresApi(26)
    public static final long convertTo(@ColorInt int i2, @NotNull ColorSpace colorSpace) {
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        return Color.convert(i2, colorSpace);
    }

    @RequiresApi(26)
    public static final long convertTo(long j2, @NotNull ColorSpace.Named colorSpace) {
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        return Color.convert(j2, ColorSpace.get(colorSpace));
    }

    @RequiresApi(26)
    public static final long convertTo(long j2, @NotNull ColorSpace colorSpace) {
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        return Color.convert(j2, colorSpace);
    }

    @RequiresApi(26)
    @NotNull
    public static final Color convertTo(@NotNull Color convertTo, @NotNull ColorSpace.Named colorSpace) {
        Intrinsics.checkNotNullParameter(convertTo, "$this$convertTo");
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        Color convert = convertTo.convert(ColorSpace.get(colorSpace));
        Intrinsics.checkNotNullExpressionValue(convert, "convert(ColorSpace.get(colorSpace))");
        return convert;
    }

    @RequiresApi(26)
    @NotNull
    public static final Color convertTo(@NotNull Color convertTo, @NotNull ColorSpace colorSpace) {
        Intrinsics.checkNotNullParameter(convertTo, "$this$convertTo");
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        Color convert = convertTo.convert(colorSpace);
        Intrinsics.checkNotNullExpressionValue(convert, "convert(colorSpace)");
        return convert;
    }

    @RequiresApi(26)
    public static final float getAlpha(long j2) {
        return Color.alpha(j2);
    }

    public static final int getAlpha(@ColorInt int i2) {
        return (i2 >> 24) & 255;
    }

    @RequiresApi(26)
    public static final float getBlue(long j2) {
        return Color.blue(j2);
    }

    public static final int getBlue(@ColorInt int i2) {
        return i2 & 255;
    }

    @RequiresApi(26)
    @NotNull
    public static final ColorSpace getColorSpace(long j2) {
        ColorSpace colorSpace = Color.colorSpace(j2);
        Intrinsics.checkNotNullExpressionValue(colorSpace, "Color.colorSpace(this)");
        return colorSpace;
    }

    @RequiresApi(26)
    public static final float getGreen(long j2) {
        return Color.green(j2);
    }

    public static final int getGreen(@ColorInt int i2) {
        return (i2 >> 8) & 255;
    }

    @RequiresApi(26)
    public static final float getLuminance(@ColorInt int i2) {
        return Color.luminance(i2);
    }

    @RequiresApi(26)
    public static final float getLuminance(long j2) {
        return Color.luminance(j2);
    }

    @RequiresApi(26)
    public static final float getRed(long j2) {
        return Color.red(j2);
    }

    public static final int getRed(@ColorInt int i2) {
        return (i2 >> 16) & 255;
    }

    @RequiresApi(26)
    public static final boolean isSrgb(long j2) {
        return Color.isSrgb(j2);
    }

    @RequiresApi(26)
    public static final boolean isWideGamut(long j2) {
        return Color.isWideGamut(j2);
    }

    @RequiresApi(26)
    @NotNull
    public static final Color plus(@NotNull Color plus, @NotNull Color c2) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(c2, "c");
        Color compositeColors = ColorUtils.compositeColors(c2, plus);
        Intrinsics.checkNotNullExpressionValue(compositeColors, "ColorUtils.compositeColors(c, this)");
        return compositeColors;
    }

    @RequiresApi(26)
    @NotNull
    public static final Color toColor(@ColorInt int i2) {
        Color valueOf = Color.valueOf(i2);
        Intrinsics.checkNotNullExpressionValue(valueOf, "Color.valueOf(this)");
        return valueOf;
    }

    @RequiresApi(26)
    @NotNull
    public static final Color toColor(long j2) {
        Color valueOf = Color.valueOf(j2);
        Intrinsics.checkNotNullExpressionValue(valueOf, "Color.valueOf(this)");
        return valueOf;
    }

    @ColorInt
    @RequiresApi(26)
    public static final int toColorInt(long j2) {
        return Color.toArgb(j2);
    }

    @ColorInt
    public static final int toColorInt(@NotNull String toColorInt) {
        Intrinsics.checkNotNullParameter(toColorInt, "$this$toColorInt");
        return Color.parseColor(toColorInt);
    }

    @RequiresApi(26)
    public static final long toColorLong(@ColorInt int i2) {
        return Color.pack(i2);
    }
}
