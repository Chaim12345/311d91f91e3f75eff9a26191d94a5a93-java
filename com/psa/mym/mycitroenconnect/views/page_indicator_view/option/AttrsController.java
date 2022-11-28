package com.psa.mym.mycitroenconnect.views.page_indicator_view.option;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.views.page_indicator_view.utils.IndicatorUtils;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class AttrsController {
    @NotNull
    public static final AttrsController INSTANCE = new AttrsController();

    private AttrsController() {
    }

    public final void initAttrs(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attributeSet, @NotNull IndicatorOptions indicatorOptions) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(indicatorOptions, "indicatorOptions");
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.IndicatorView);
            Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.obtainStyledAttr….styleable.IndicatorView)");
            int color = obtainStyledAttributes.getColor(3, Color.parseColor("#6C6D72"));
            float dimension = obtainStyledAttributes.getDimension(5, IndicatorUtils.dp2px(8.0f));
            indicatorOptions.setCheckedColor(color);
            float f2 = dimension * 2;
            indicatorOptions.setSliderWidth(f2, f2);
            obtainStyledAttributes.recycle();
        }
    }
}
