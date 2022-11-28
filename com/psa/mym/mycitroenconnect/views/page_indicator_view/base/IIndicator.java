package com.psa.mym.mycitroenconnect.views.page_indicator_view.base;

import androidx.viewpager.widget.ViewPager;
import com.psa.mym.mycitroenconnect.views.page_indicator_view.option.IndicatorOptions;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface IIndicator extends ViewPager.OnPageChangeListener {
    void notifyDataChanged();

    void setIndicatorOptions(@NotNull IndicatorOptions indicatorOptions);
}
