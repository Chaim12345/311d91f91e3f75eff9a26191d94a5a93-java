package com.psa.mym.mycitroenconnect.views.page_indicator_view.drawer;

import android.graphics.Canvas;
import com.psa.mym.mycitroenconnect.views.page_indicator_view.drawer.BaseDrawer;
import com.psa.mym.mycitroenconnect.views.page_indicator_view.option.IndicatorOptions;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class DrawerProxy implements IDrawer {
    private IDrawer mIDrawer;

    public DrawerProxy(@NotNull IndicatorOptions indicatorOptions) {
        Intrinsics.checkNotNullParameter(indicatorOptions, "indicatorOptions");
        init(indicatorOptions);
    }

    private final void init(IndicatorOptions indicatorOptions) {
        this.mIDrawer = DrawerFactory.INSTANCE.createDrawer(indicatorOptions);
    }

    @Override // com.psa.mym.mycitroenconnect.views.page_indicator_view.drawer.IDrawer
    public void onDraw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        IDrawer iDrawer = this.mIDrawer;
        if (iDrawer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mIDrawer");
            iDrawer = null;
        }
        iDrawer.onDraw(canvas);
    }

    @Override // com.psa.mym.mycitroenconnect.views.page_indicator_view.drawer.IDrawer
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
    }

    @Override // com.psa.mym.mycitroenconnect.views.page_indicator_view.drawer.IDrawer
    @NotNull
    public BaseDrawer.MeasureResult onMeasure(int i2, int i3) {
        IDrawer iDrawer = this.mIDrawer;
        if (iDrawer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mIDrawer");
            iDrawer = null;
        }
        return iDrawer.onMeasure(i2, i3);
    }

    public final void setIndicatorOptions(@NotNull IndicatorOptions indicatorOptions) {
        Intrinsics.checkNotNullParameter(indicatorOptions, "indicatorOptions");
        init(indicatorOptions);
    }
}
