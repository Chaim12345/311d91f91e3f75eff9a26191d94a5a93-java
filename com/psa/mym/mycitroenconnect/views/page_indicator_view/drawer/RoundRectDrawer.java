package com.psa.mym.mycitroenconnect.views.page_indicator_view.drawer;

import android.graphics.Canvas;
import com.psa.mym.mycitroenconnect.views.page_indicator_view.option.IndicatorOptions;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class RoundRectDrawer extends RectDrawer {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RoundRectDrawer(@NotNull IndicatorOptions indicatorOptions) {
        super(indicatorOptions);
        Intrinsics.checkNotNullParameter(indicatorOptions, "indicatorOptions");
    }

    @Override // com.psa.mym.mycitroenconnect.views.page_indicator_view.drawer.RectDrawer
    protected void d(@NotNull Canvas canvas, float f2, float f3) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.drawRoundRect(getMRectF$app_preprodQa(), f2, f3, getMPaint$app_preprodQa());
    }
}
