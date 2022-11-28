package com.psa.mym.mycitroenconnect.views.page_indicator_view.utils;

import android.content.res.Resources;
import com.psa.mym.mycitroenconnect.views.page_indicator_view.option.IndicatorOptions;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class IndicatorUtils {
    @NotNull
    public static final IndicatorUtils INSTANCE = new IndicatorUtils();

    private IndicatorUtils() {
    }

    @JvmStatic
    public static final int dp2px(float f2) {
        return (int) ((f2 * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    public final float getCoordinateX(@NotNull IndicatorOptions indicatorOptions, float f2, int i2) {
        Intrinsics.checkNotNullParameter(indicatorOptions, "indicatorOptions");
        return (f2 / 2) + ((indicatorOptions.getNormalSliderWidth() + indicatorOptions.getSliderGap()) * i2);
    }

    public final float getCoordinateY(float f2) {
        return f2 / 2;
    }
}
