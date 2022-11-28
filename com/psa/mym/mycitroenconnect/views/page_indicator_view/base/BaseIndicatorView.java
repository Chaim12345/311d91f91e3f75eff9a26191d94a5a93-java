package com.psa.mym.mycitroenconnect.views.page_indicator_view.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import com.psa.mym.mycitroenconnect.views.page_indicator_view.option.IndicatorOptions;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public class BaseIndicatorView extends View implements IIndicator {
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    @NotNull
    private IndicatorOptions mIndicatorOptions;
    @NotNull
    private final BaseIndicatorView$mOnPageChangeCallback$1 mOnPageChangeCallback;
    @Nullable
    private ViewPager mViewPager;
    @Nullable
    private ViewPager2 mViewPager2;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v1, types: [com.psa.mym.mycitroenconnect.views.page_indicator_view.base.BaseIndicatorView$mOnPageChangeCallback$1] */
    public BaseIndicatorView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        this.mOnPageChangeCallback = new ViewPager2.OnPageChangeCallback() { // from class: com.psa.mym.mycitroenconnect.views.page_indicator_view.base.BaseIndicatorView$mOnPageChangeCallback$1
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrollStateChanged(int i3) {
                BaseIndicatorView.this.onPageScrollStateChanged(i3);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrolled(int i3, float f2, int i4) {
                BaseIndicatorView.this.onPageScrolled(i3, f2, i4);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i3) {
                BaseIndicatorView.this.onPageSelected(i3);
            }
        };
        this.mIndicatorOptions = new IndicatorOptions();
    }

    private final void scrollSlider(int i2, float f2) {
        if (this.mIndicatorOptions.getSlideMode() == 4 || this.mIndicatorOptions.getSlideMode() == 5 || i2 % getPageSize() != getPageSize() - 1) {
            setCurrentPosition(i2);
            setSlideProgress(f2);
            return;
        }
        if (f2 >= 0.5d) {
            i2 = 0;
        }
        setCurrentPosition(i2);
        setSlideProgress(0.0f);
    }

    private final void setupViewPager() {
        ViewPager viewPager = this.mViewPager;
        if (viewPager != null) {
            if (viewPager != null) {
                viewPager.removeOnPageChangeListener(this);
            }
            ViewPager viewPager2 = this.mViewPager;
            if (viewPager2 != null) {
                viewPager2.addOnPageChangeListener(this);
            }
            ViewPager viewPager3 = this.mViewPager;
            if (viewPager3 != null && viewPager3.getAdapter() != null) {
                ViewPager viewPager4 = this.mViewPager;
                Intrinsics.checkNotNull(viewPager4);
                PagerAdapter adapter = viewPager4.getAdapter();
                Intrinsics.checkNotNull(adapter);
                setPageSize(adapter.getCount());
            }
        }
        ViewPager2 viewPager22 = this.mViewPager2;
        if (viewPager22 != null) {
            if (viewPager22 != null) {
                viewPager22.unregisterOnPageChangeCallback(this.mOnPageChangeCallback);
            }
            ViewPager2 viewPager23 = this.mViewPager2;
            if (viewPager23 != null) {
                viewPager23.registerOnPageChangeCallback(this.mOnPageChangeCallback);
            }
            ViewPager2 viewPager24 = this.mViewPager2;
            if (viewPager24 == null || viewPager24.getAdapter() == null) {
                return;
            }
            ViewPager2 viewPager25 = this.mViewPager2;
            Intrinsics.checkNotNull(viewPager25);
            RecyclerView.Adapter adapter2 = viewPager25.getAdapter();
            Intrinsics.checkNotNull(adapter2);
            setPageSize(adapter2.getItemCount());
        }
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i2) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            if (findViewById != null) {
                map.put(Integer.valueOf(i2), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    public final int getCheckedColor() {
        return this.mIndicatorOptions.getCheckedSliderColor();
    }

    public final float getCheckedSlideWidth() {
        return this.mIndicatorOptions.getCheckedSliderWidth();
    }

    public final float getCheckedSliderWidth() {
        return this.mIndicatorOptions.getCheckedSliderWidth();
    }

    public final int getCurrentPosition() {
        return this.mIndicatorOptions.getCurrentPosition();
    }

    public final void getIndicatorGap(float f2) {
        this.mIndicatorOptions.setSliderGap(f2);
    }

    @NotNull
    public final IndicatorOptions getMIndicatorOptions() {
        return this.mIndicatorOptions;
    }

    public final float getNormalSlideWidth() {
        return this.mIndicatorOptions.getNormalSliderWidth();
    }

    public final int getPageSize() {
        return this.mIndicatorOptions.getPageSize();
    }

    public final int getSlideMode() {
        return this.mIndicatorOptions.getSlideMode();
    }

    public final float getSlideProgress() {
        return this.mIndicatorOptions.getSlideProgress();
    }

    public void notifyDataChanged() {
        setupViewPager();
        requestLayout();
        invalidate();
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
        if (getSlideMode() == 0 || getPageSize() <= 1) {
            return;
        }
        scrollSlider(i2, f2);
        invalidate();
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
        if (getSlideMode() == 0) {
            setCurrentPosition(i2);
            setSlideProgress(0.0f);
            invalidate();
        }
    }

    public final void setCheckedColor(@ColorInt int i2) {
        this.mIndicatorOptions.setCheckedSliderColor(i2);
    }

    public final void setCheckedSlideWidth(float f2) {
        this.mIndicatorOptions.setCheckedSliderWidth(f2);
    }

    public final void setCurrentPosition(int i2) {
        this.mIndicatorOptions.setCurrentPosition(i2);
    }

    public final void setIndicatorGap(float f2) {
        this.mIndicatorOptions.setSliderGap(f2);
    }

    public void setIndicatorOptions(@NotNull IndicatorOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        this.mIndicatorOptions = options;
    }

    @NotNull
    public final BaseIndicatorView setIndicatorStyle(int i2) {
        this.mIndicatorOptions.setIndicatorStyle(i2);
        return this;
    }

    public final void setMIndicatorOptions(@NotNull IndicatorOptions indicatorOptions) {
        Intrinsics.checkNotNullParameter(indicatorOptions, "<set-?>");
        this.mIndicatorOptions = indicatorOptions;
    }

    public final void setNormalColor(@ColorInt int i2) {
        this.mIndicatorOptions.setNormalSliderColor(i2);
    }

    public final void setNormalSlideWidth(float f2) {
        this.mIndicatorOptions.setNormalSliderWidth(f2);
    }

    @NotNull
    public final BaseIndicatorView setPageSize(int i2) {
        this.mIndicatorOptions.setPageSize(i2);
        return this;
    }

    @NotNull
    public final BaseIndicatorView setSlideMode(int i2) {
        this.mIndicatorOptions.setSlideMode(i2);
        return this;
    }

    public final void setSlideProgress(float f2) {
        this.mIndicatorOptions.setSlideProgress(f2);
    }

    @NotNull
    public final BaseIndicatorView setSliderColor(@ColorInt int i2, @ColorInt int i3) {
        this.mIndicatorOptions.setSliderColor(i2, i3);
        return this;
    }

    @NotNull
    public final BaseIndicatorView setSliderGap(float f2) {
        this.mIndicatorOptions.setSliderGap(f2);
        return this;
    }

    @NotNull
    public final BaseIndicatorView setSliderHeight(float f2) {
        this.mIndicatorOptions.setSliderHeight(f2);
        return this;
    }

    @NotNull
    public final BaseIndicatorView setSliderWidth(float f2) {
        this.mIndicatorOptions.setSliderWidth(f2);
        return this;
    }

    @NotNull
    public final BaseIndicatorView setSliderWidth(float f2, float f3) {
        this.mIndicatorOptions.setSliderWidth(f2, f3);
        return this;
    }

    public final void setupWithViewPager(@NotNull ViewPager viewPager) {
        Intrinsics.checkNotNullParameter(viewPager, "viewPager");
        this.mViewPager = viewPager;
        notifyDataChanged();
    }

    public final void setupWithViewPager(@NotNull ViewPager2 viewPager2) {
        Intrinsics.checkNotNullParameter(viewPager2, "viewPager2");
        this.mViewPager2 = viewPager2;
        notifyDataChanged();
    }

    public final void showIndicatorWhenOneItem(boolean z) {
        this.mIndicatorOptions.setShowIndicatorOneItem(z);
    }
}
