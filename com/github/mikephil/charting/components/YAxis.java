package com.github.mikephil.charting.components;

import android.graphics.Paint;
import com.github.mikephil.charting.utils.Utils;
/* loaded from: classes.dex */
public class YAxis extends AxisBase {
    protected float A;
    protected float B;
    protected float C;
    private AxisDependency mAxisDependency;
    private boolean mDrawBottomYLabelEntry;
    private boolean mDrawTopYLabelEntry;
    private YAxisLabelPosition mPosition;
    private boolean mUseAutoScaleRestrictionMax;
    private boolean mUseAutoScaleRestrictionMin;
    protected boolean v;
    protected boolean w;
    protected int x;
    protected float y;
    protected float z;

    /* loaded from: classes.dex */
    public enum AxisDependency {
        LEFT,
        RIGHT
    }

    /* loaded from: classes.dex */
    public enum YAxisLabelPosition {
        OUTSIDE_CHART,
        INSIDE_CHART
    }

    public YAxis() {
        this.mDrawBottomYLabelEntry = true;
        this.mDrawTopYLabelEntry = true;
        this.v = false;
        this.w = false;
        this.mUseAutoScaleRestrictionMin = false;
        this.mUseAutoScaleRestrictionMax = false;
        this.x = -7829368;
        this.y = 1.0f;
        this.z = 10.0f;
        this.A = 10.0f;
        this.mPosition = YAxisLabelPosition.OUTSIDE_CHART;
        this.B = 0.0f;
        this.C = Float.POSITIVE_INFINITY;
        this.mAxisDependency = AxisDependency.LEFT;
        this.f5308c = 0.0f;
    }

    public YAxis(AxisDependency axisDependency) {
        this.mDrawBottomYLabelEntry = true;
        this.mDrawTopYLabelEntry = true;
        this.v = false;
        this.w = false;
        this.mUseAutoScaleRestrictionMin = false;
        this.mUseAutoScaleRestrictionMax = false;
        this.x = -7829368;
        this.y = 1.0f;
        this.z = 10.0f;
        this.A = 10.0f;
        this.mPosition = YAxisLabelPosition.OUTSIDE_CHART;
        this.B = 0.0f;
        this.C = Float.POSITIVE_INFINITY;
        this.mAxisDependency = axisDependency;
        this.f5308c = 0.0f;
    }

    @Override // com.github.mikephil.charting.components.AxisBase
    public void calculate(float f2, float f3) {
        if (Math.abs(f3 - f2) == 0.0f) {
            f3 += 1.0f;
            f2 -= 1.0f;
        }
        float abs = Math.abs(f3 - f2);
        this.mAxisMinimum = this.f5305t ? this.mAxisMinimum : f2 - ((abs / 100.0f) * getSpaceBottom());
        float spaceTop = this.u ? this.mAxisMaximum : f3 + ((abs / 100.0f) * getSpaceTop());
        this.mAxisMaximum = spaceTop;
        this.mAxisRange = Math.abs(this.mAxisMinimum - spaceTop);
    }

    public AxisDependency getAxisDependency() {
        return this.mAxisDependency;
    }

    public YAxisLabelPosition getLabelPosition() {
        return this.mPosition;
    }

    public float getMaxWidth() {
        return this.C;
    }

    public float getMinWidth() {
        return this.B;
    }

    public float getRequiredHeightSpace(Paint paint) {
        paint.setTextSize(this.f5310e);
        return Utils.calcTextHeight(paint, getLongestLabel()) + (getYOffset() * 2.0f);
    }

    public float getRequiredWidthSpace(Paint paint) {
        paint.setTextSize(this.f5310e);
        float calcTextWidth = Utils.calcTextWidth(paint, getLongestLabel()) + (getXOffset() * 2.0f);
        float minWidth = getMinWidth();
        float maxWidth = getMaxWidth();
        if (minWidth > 0.0f) {
            minWidth = Utils.convertDpToPixel(minWidth);
        }
        if (maxWidth > 0.0f && maxWidth != Float.POSITIVE_INFINITY) {
            maxWidth = Utils.convertDpToPixel(maxWidth);
        }
        if (maxWidth <= 0.0d) {
            maxWidth = calcTextWidth;
        }
        return Math.max(minWidth, Math.min(calcTextWidth, maxWidth));
    }

    public float getSpaceBottom() {
        return this.A;
    }

    public float getSpaceTop() {
        return this.z;
    }

    public int getZeroLineColor() {
        return this.x;
    }

    public float getZeroLineWidth() {
        return this.y;
    }

    public boolean isDrawBottomYLabelEntryEnabled() {
        return this.mDrawBottomYLabelEntry;
    }

    public boolean isDrawTopYLabelEntryEnabled() {
        return this.mDrawTopYLabelEntry;
    }

    public boolean isDrawZeroLineEnabled() {
        return this.w;
    }

    public boolean isInverted() {
        return this.v;
    }

    @Deprecated
    public boolean isUseAutoScaleMaxRestriction() {
        return this.mUseAutoScaleRestrictionMax;
    }

    @Deprecated
    public boolean isUseAutoScaleMinRestriction() {
        return this.mUseAutoScaleRestrictionMin;
    }

    public boolean needsOffset() {
        return isEnabled() && isDrawLabelsEnabled() && getLabelPosition() == YAxisLabelPosition.OUTSIDE_CHART;
    }

    public void setDrawTopYLabelEntry(boolean z) {
        this.mDrawTopYLabelEntry = z;
    }

    public void setDrawZeroLine(boolean z) {
        this.w = z;
    }

    public void setInverted(boolean z) {
        this.v = z;
    }

    public void setMaxWidth(float f2) {
        this.C = f2;
    }

    public void setMinWidth(float f2) {
        this.B = f2;
    }

    public void setPosition(YAxisLabelPosition yAxisLabelPosition) {
        this.mPosition = yAxisLabelPosition;
    }

    public void setSpaceBottom(float f2) {
        this.A = f2;
    }

    public void setSpaceTop(float f2) {
        this.z = f2;
    }

    @Deprecated
    public void setStartAtZero(boolean z) {
        if (z) {
            setAxisMinimum(0.0f);
        } else {
            resetAxisMinimum();
        }
    }

    @Deprecated
    public void setUseAutoScaleMaxRestriction(boolean z) {
        this.mUseAutoScaleRestrictionMax = z;
    }

    @Deprecated
    public void setUseAutoScaleMinRestriction(boolean z) {
        this.mUseAutoScaleRestrictionMin = z;
    }

    public void setZeroLineColor(int i2) {
        this.x = i2;
    }

    public void setZeroLineWidth(float f2) {
        this.y = Utils.convertDpToPixel(f2);
    }
}
