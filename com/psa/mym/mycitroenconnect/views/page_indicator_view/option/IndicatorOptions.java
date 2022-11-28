package com.psa.mym.mycitroenconnect.views.page_indicator_view.option;

import android.graphics.Color;
import com.psa.mym.mycitroenconnect.views.page_indicator_view.utils.IndicatorUtils;
/* loaded from: classes3.dex */
public final class IndicatorOptions {
    private int checkedSliderColor;
    private float checkedSliderWidth;
    private int currentPosition;
    private int indicatorStyle;
    private int normalSliderColor;
    private float normalSliderWidth;
    private int orientation;
    private int pageSize;
    private boolean showIndicatorOneItem;
    private int slideMode;
    private float slideProgress;
    private float sliderGap;
    private float sliderHeight;

    public IndicatorOptions() {
        float dp2px = IndicatorUtils.dp2px(8.0f);
        this.normalSliderWidth = dp2px;
        this.checkedSliderWidth = dp2px;
        this.sliderGap = dp2px;
        this.normalSliderColor = Color.parseColor("#8C18171C");
        this.checkedSliderColor = Color.parseColor("#8C6C6D72");
        this.slideMode = 0;
    }

    public final int getCheckedSliderColor() {
        return this.checkedSliderColor;
    }

    public final float getCheckedSliderWidth() {
        return this.checkedSliderWidth;
    }

    public final int getCurrentPosition() {
        return this.currentPosition;
    }

    public final int getIndicatorStyle() {
        return this.indicatorStyle;
    }

    public final int getNormalSliderColor() {
        return this.normalSliderColor;
    }

    public final float getNormalSliderWidth() {
        return this.normalSliderWidth;
    }

    public final int getOrientation() {
        return this.orientation;
    }

    public final int getPageSize() {
        return this.pageSize;
    }

    public final boolean getShowIndicatorOneItem() {
        return this.showIndicatorOneItem;
    }

    public final int getSlideMode() {
        return this.slideMode;
    }

    public final float getSlideProgress() {
        return this.slideProgress;
    }

    public final float getSliderGap() {
        return this.sliderGap;
    }

    public final float getSliderHeight() {
        float f2 = this.sliderHeight;
        return f2 > 0.0f ? f2 : this.normalSliderWidth / 2;
    }

    public final void setCheckedColor(int i2) {
        this.checkedSliderColor = i2;
    }

    public final void setCheckedSliderColor(int i2) {
        this.checkedSliderColor = i2;
    }

    public final void setCheckedSliderWidth(float f2) {
        this.checkedSliderWidth = f2;
    }

    public final void setCurrentPosition(int i2) {
        this.currentPosition = i2;
    }

    public final void setIndicatorStyle(int i2) {
        this.indicatorStyle = i2;
    }

    public final void setNormalSliderColor(int i2) {
        this.normalSliderColor = i2;
    }

    public final void setNormalSliderWidth(float f2) {
        this.normalSliderWidth = f2;
    }

    public final void setOrientation(int i2) {
        this.orientation = i2;
    }

    public final void setPageSize(int i2) {
        this.pageSize = i2;
    }

    public final void setShowIndicatorOneItem(boolean z) {
        this.showIndicatorOneItem = z;
    }

    public final void setSlideMode(int i2) {
        this.slideMode = i2;
    }

    public final void setSlideProgress(float f2) {
        this.slideProgress = f2;
    }

    public final void setSliderColor(int i2, int i3) {
        this.normalSliderColor = i2;
        this.checkedSliderColor = i3;
    }

    public final void setSliderGap(float f2) {
        this.sliderGap = f2;
    }

    public final void setSliderHeight(float f2) {
        this.sliderHeight = f2;
    }

    public final void setSliderWidth(float f2) {
        setSliderWidth(f2, f2);
    }

    public final void setSliderWidth(float f2, float f3) {
        this.normalSliderWidth = f2;
        this.checkedSliderWidth = f3;
    }
}
