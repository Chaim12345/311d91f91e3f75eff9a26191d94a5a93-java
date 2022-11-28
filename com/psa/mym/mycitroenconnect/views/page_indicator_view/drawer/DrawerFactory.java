package com.psa.mym.mycitroenconnect.views.page_indicator_view.drawer;

import com.psa.mym.mycitroenconnect.views.page_indicator_view.option.IndicatorOptions;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class DrawerFactory {
    @NotNull
    public static final DrawerFactory INSTANCE = new DrawerFactory();

    private DrawerFactory() {
    }

    @NotNull
    public final IDrawer createDrawer(@NotNull IndicatorOptions indicatorOptions) {
        Intrinsics.checkNotNullParameter(indicatorOptions, "indicatorOptions");
        int indicatorStyle = indicatorOptions.getIndicatorStyle();
        return indicatorStyle != 2 ? indicatorStyle != 4 ? new CircleDrawer(indicatorOptions) : new RoundRectDrawer(indicatorOptions) : new DashDrawer(indicatorOptions);
    }
}
