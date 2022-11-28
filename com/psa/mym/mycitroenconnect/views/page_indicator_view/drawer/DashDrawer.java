package com.psa.mym.mycitroenconnect.views.page_indicator_view.drawer;

import android.graphics.Canvas;
import com.psa.mym.mycitroenconnect.views.page_indicator_view.option.IndicatorOptions;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class DashDrawer extends RectDrawer {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DashDrawer(@NotNull IndicatorOptions indicatorOptions) {
        super(indicatorOptions);
        Intrinsics.checkNotNullParameter(indicatorOptions, "indicatorOptions");
    }

    @Override // com.psa.mym.mycitroenconnect.views.page_indicator_view.drawer.RectDrawer
    protected void c(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.drawRect(getMRectF$app_preprodQa(), getMPaint$app_preprodQa());
    }
}
