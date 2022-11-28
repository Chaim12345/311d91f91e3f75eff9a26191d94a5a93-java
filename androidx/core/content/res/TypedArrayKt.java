package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import androidx.annotation.AnyRes;
import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.RequiresApi;
import androidx.annotation.StyleableRes;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\b\b\u0001\u0010\u0002\u001a\u00020\u0001H\u0002\u001a\u0014\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\b\b\u0001\u0010\u0002\u001a\u00020\u0001\u001a\u0016\u0010\u0007\u001a\u00020\u0001*\u00020\u00002\b\b\u0001\u0010\u0002\u001a\u00020\u0001H\u0007\u001a\u0014\u0010\t\u001a\u00020\b*\u00020\u00002\b\b\u0001\u0010\u0002\u001a\u00020\u0001\u001a\u0014\u0010\u000b\u001a\u00020\n*\u00020\u00002\b\b\u0001\u0010\u0002\u001a\u00020\u0001\u001a\u0016\u0010\f\u001a\u00020\u0001*\u00020\u00002\b\b\u0001\u0010\u0002\u001a\u00020\u0001H\u0007\u001a\u0016\u0010\r\u001a\u00020\u0001*\u00020\u00002\b\b\u0001\u0010\u0002\u001a\u00020\u0001H\u0007\u001a\u0014\u0010\u000f\u001a\u00020\u000e*\u00020\u00002\b\b\u0001\u0010\u0002\u001a\u00020\u0001\u001a\u0014\u0010\u0010\u001a\u00020\n*\u00020\u00002\b\b\u0001\u0010\u0002\u001a\u00020\u0001\u001a\u0016\u0010\u0012\u001a\u00020\u0011*\u00020\u00002\b\b\u0001\u0010\u0002\u001a\u00020\u0001H\u0007\u001a\u0014\u0010\u0013\u001a\u00020\u0001*\u00020\u00002\b\b\u0001\u0010\u0002\u001a\u00020\u0001\u001a\u0014\u0010\u0014\u001a\u00020\u0001*\u00020\u00002\b\b\u0001\u0010\u0002\u001a\u00020\u0001\u001a\u0016\u0010\u0015\u001a\u00020\u0001*\u00020\u00002\b\b\u0001\u0010\u0002\u001a\u00020\u0001H\u0007\u001a\u0014\u0010\u0017\u001a\u00020\u0016*\u00020\u00002\b\b\u0001\u0010\u0002\u001a\u00020\u0001\u001a\u0014\u0010\u0019\u001a\u00020\u0018*\u00020\u00002\b\b\u0001\u0010\u0002\u001a\u00020\u0001\u001a!\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u001a*\u00020\u00002\b\b\u0001\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u001b\u0010\u001c\u001a1\u0010 \u001a\u00028\u0000\"\u0004\b\u0000\u0010\u001d*\u00020\u00002\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00028\u00000\u001eH\u0086\bø\u0001\u0000¢\u0006\u0004\b \u0010!\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\""}, d2 = {"Landroid/content/res/TypedArray;", "", FirebaseAnalytics.Param.INDEX, "", "checkAttribute", "", "getBooleanOrThrow", "getColorOrThrow", "Landroid/content/res/ColorStateList;", "getColorStateListOrThrow", "", "getDimensionOrThrow", "getDimensionPixelOffsetOrThrow", "getDimensionPixelSizeOrThrow", "Landroid/graphics/drawable/Drawable;", "getDrawableOrThrow", "getFloatOrThrow", "Landroid/graphics/Typeface;", "getFontOrThrow", "getIntOrThrow", "getIntegerOrThrow", "getResourceIdOrThrow", "", "getStringOrThrow", "", "getTextOrThrow", "", "getTextArrayOrThrow", "(Landroid/content/res/TypedArray;I)[Ljava/lang/CharSequence;", "R", "Lkotlin/Function1;", "block", "use", "(Landroid/content/res/TypedArray;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "core-ktx_release"}, k = 2, mv = {1, 4, 2})
/* loaded from: classes.dex */
public final class TypedArrayKt {
    private static final void checkAttribute(TypedArray typedArray, @StyleableRes int i2) {
        if (!typedArray.hasValue(i2)) {
            throw new IllegalArgumentException("Attribute not defined in set.");
        }
    }

    public static final boolean getBooleanOrThrow(@NotNull TypedArray getBooleanOrThrow, @StyleableRes int i2) {
        Intrinsics.checkNotNullParameter(getBooleanOrThrow, "$this$getBooleanOrThrow");
        checkAttribute(getBooleanOrThrow, i2);
        return getBooleanOrThrow.getBoolean(i2, false);
    }

    @ColorInt
    public static final int getColorOrThrow(@NotNull TypedArray getColorOrThrow, @StyleableRes int i2) {
        Intrinsics.checkNotNullParameter(getColorOrThrow, "$this$getColorOrThrow");
        checkAttribute(getColorOrThrow, i2);
        return getColorOrThrow.getColor(i2, 0);
    }

    @NotNull
    public static final ColorStateList getColorStateListOrThrow(@NotNull TypedArray getColorStateListOrThrow, @StyleableRes int i2) {
        Intrinsics.checkNotNullParameter(getColorStateListOrThrow, "$this$getColorStateListOrThrow");
        checkAttribute(getColorStateListOrThrow, i2);
        ColorStateList colorStateList = getColorStateListOrThrow.getColorStateList(i2);
        if (colorStateList != null) {
            return colorStateList;
        }
        throw new IllegalStateException("Attribute value was not a color or color state list.".toString());
    }

    public static final float getDimensionOrThrow(@NotNull TypedArray getDimensionOrThrow, @StyleableRes int i2) {
        Intrinsics.checkNotNullParameter(getDimensionOrThrow, "$this$getDimensionOrThrow");
        checkAttribute(getDimensionOrThrow, i2);
        return getDimensionOrThrow.getDimension(i2, 0.0f);
    }

    @Dimension
    public static final int getDimensionPixelOffsetOrThrow(@NotNull TypedArray getDimensionPixelOffsetOrThrow, @StyleableRes int i2) {
        Intrinsics.checkNotNullParameter(getDimensionPixelOffsetOrThrow, "$this$getDimensionPixelOffsetOrThrow");
        checkAttribute(getDimensionPixelOffsetOrThrow, i2);
        return getDimensionPixelOffsetOrThrow.getDimensionPixelOffset(i2, 0);
    }

    @Dimension
    public static final int getDimensionPixelSizeOrThrow(@NotNull TypedArray getDimensionPixelSizeOrThrow, @StyleableRes int i2) {
        Intrinsics.checkNotNullParameter(getDimensionPixelSizeOrThrow, "$this$getDimensionPixelSizeOrThrow");
        checkAttribute(getDimensionPixelSizeOrThrow, i2);
        return getDimensionPixelSizeOrThrow.getDimensionPixelSize(i2, 0);
    }

    @NotNull
    public static final Drawable getDrawableOrThrow(@NotNull TypedArray getDrawableOrThrow, @StyleableRes int i2) {
        Intrinsics.checkNotNullParameter(getDrawableOrThrow, "$this$getDrawableOrThrow");
        checkAttribute(getDrawableOrThrow, i2);
        Drawable drawable = getDrawableOrThrow.getDrawable(i2);
        Intrinsics.checkNotNull(drawable);
        return drawable;
    }

    public static final float getFloatOrThrow(@NotNull TypedArray getFloatOrThrow, @StyleableRes int i2) {
        Intrinsics.checkNotNullParameter(getFloatOrThrow, "$this$getFloatOrThrow");
        checkAttribute(getFloatOrThrow, i2);
        return getFloatOrThrow.getFloat(i2, 0.0f);
    }

    @RequiresApi(26)
    @NotNull
    public static final Typeface getFontOrThrow(@NotNull TypedArray getFontOrThrow, @StyleableRes int i2) {
        Intrinsics.checkNotNullParameter(getFontOrThrow, "$this$getFontOrThrow");
        checkAttribute(getFontOrThrow, i2);
        Typeface font = getFontOrThrow.getFont(i2);
        Intrinsics.checkNotNull(font);
        return font;
    }

    public static final int getIntOrThrow(@NotNull TypedArray getIntOrThrow, @StyleableRes int i2) {
        Intrinsics.checkNotNullParameter(getIntOrThrow, "$this$getIntOrThrow");
        checkAttribute(getIntOrThrow, i2);
        return getIntOrThrow.getInt(i2, 0);
    }

    public static final int getIntegerOrThrow(@NotNull TypedArray getIntegerOrThrow, @StyleableRes int i2) {
        Intrinsics.checkNotNullParameter(getIntegerOrThrow, "$this$getIntegerOrThrow");
        checkAttribute(getIntegerOrThrow, i2);
        return getIntegerOrThrow.getInteger(i2, 0);
    }

    @AnyRes
    public static final int getResourceIdOrThrow(@NotNull TypedArray getResourceIdOrThrow, @StyleableRes int i2) {
        Intrinsics.checkNotNullParameter(getResourceIdOrThrow, "$this$getResourceIdOrThrow");
        checkAttribute(getResourceIdOrThrow, i2);
        return getResourceIdOrThrow.getResourceId(i2, 0);
    }

    @NotNull
    public static final String getStringOrThrow(@NotNull TypedArray getStringOrThrow, @StyleableRes int i2) {
        Intrinsics.checkNotNullParameter(getStringOrThrow, "$this$getStringOrThrow");
        checkAttribute(getStringOrThrow, i2);
        String string = getStringOrThrow.getString(i2);
        if (string != null) {
            return string;
        }
        throw new IllegalStateException("Attribute value could not be coerced to String.".toString());
    }

    @NotNull
    public static final CharSequence[] getTextArrayOrThrow(@NotNull TypedArray getTextArrayOrThrow, @StyleableRes int i2) {
        Intrinsics.checkNotNullParameter(getTextArrayOrThrow, "$this$getTextArrayOrThrow");
        checkAttribute(getTextArrayOrThrow, i2);
        CharSequence[] textArray = getTextArrayOrThrow.getTextArray(i2);
        Intrinsics.checkNotNullExpressionValue(textArray, "getTextArray(index)");
        return textArray;
    }

    @NotNull
    public static final CharSequence getTextOrThrow(@NotNull TypedArray getTextOrThrow, @StyleableRes int i2) {
        Intrinsics.checkNotNullParameter(getTextOrThrow, "$this$getTextOrThrow");
        checkAttribute(getTextOrThrow, i2);
        CharSequence text = getTextOrThrow.getText(i2);
        if (text != null) {
            return text;
        }
        throw new IllegalStateException("Attribute value could not be coerced to CharSequence.".toString());
    }

    public static final <R> R use(@NotNull TypedArray use, @NotNull Function1<? super TypedArray, ? extends R> block) {
        Intrinsics.checkNotNullParameter(use, "$this$use");
        Intrinsics.checkNotNullParameter(block, "block");
        R invoke = block.invoke(use);
        use.recycle();
        return invoke;
    }
}
