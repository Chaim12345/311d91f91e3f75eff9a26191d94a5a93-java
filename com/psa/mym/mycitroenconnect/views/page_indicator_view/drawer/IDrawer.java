package com.psa.mym.mycitroenconnect.views.page_indicator_view.drawer;

import android.graphics.Canvas;
import com.psa.mym.mycitroenconnect.views.page_indicator_view.drawer.BaseDrawer;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface IDrawer {
    void onDraw(@NotNull Canvas canvas);

    void onLayout(boolean z, int i2, int i3, int i4, int i5);

    @NotNull
    BaseDrawer.MeasureResult onMeasure(int i2, int i3);
}
