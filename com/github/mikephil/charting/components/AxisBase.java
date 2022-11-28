package com.github.mikephil.charting.components;

import android.graphics.DashPathEffect;
import android.util.Log;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public abstract class AxisBase extends ComponentBase {

    /* renamed from: g  reason: collision with root package name */
    protected IAxisValueFormatter f5292g;
    public int mDecimals;
    public int mEntryCount;

    /* renamed from: o  reason: collision with root package name */
    protected List f5300o;
    private int mGridColor = -7829368;
    private float mGridLineWidth = 1.0f;
    private int mAxisLineColor = -7829368;
    private float mAxisLineWidth = 1.0f;
    public float[] mEntries = new float[0];
    public float[] mCenteredEntries = new float[0];
    private int mLabelCount = 6;

    /* renamed from: h  reason: collision with root package name */
    protected float f5293h = 1.0f;

    /* renamed from: i  reason: collision with root package name */
    protected boolean f5294i = false;

    /* renamed from: j  reason: collision with root package name */
    protected boolean f5295j = false;

    /* renamed from: k  reason: collision with root package name */
    protected boolean f5296k = true;

    /* renamed from: l  reason: collision with root package name */
    protected boolean f5297l = true;

    /* renamed from: m  reason: collision with root package name */
    protected boolean f5298m = true;

    /* renamed from: n  reason: collision with root package name */
    protected boolean f5299n = false;
    private DashPathEffect mAxisLineDashPathEffect = null;
    private DashPathEffect mGridDashPathEffect = null;

    /* renamed from: p  reason: collision with root package name */
    protected boolean f5301p = false;

    /* renamed from: q  reason: collision with root package name */
    protected boolean f5302q = true;

    /* renamed from: r  reason: collision with root package name */
    protected float f5303r = 0.0f;

    /* renamed from: s  reason: collision with root package name */
    protected float f5304s = 0.0f;

    /* renamed from: t  reason: collision with root package name */
    protected boolean f5305t = false;
    protected boolean u = false;
    public float mAxisMaximum = 0.0f;
    public float mAxisMinimum = 0.0f;
    public float mAxisRange = 0.0f;

    public AxisBase() {
        this.f5310e = Utils.convertDpToPixel(10.0f);
        this.f5307b = Utils.convertDpToPixel(5.0f);
        this.f5308c = Utils.convertDpToPixel(5.0f);
        this.f5300o = new ArrayList();
    }

    public void addLimitLine(LimitLine limitLine) {
        this.f5300o.add(limitLine);
        if (this.f5300o.size() > 6) {
            Log.e("MPAndroiChart", "Warning! You have more than 6 LimitLines on your axis, do you really want that?");
        }
    }

    public void calculate(float f2, float f3) {
        float f4 = this.f5305t ? this.mAxisMinimum : f2 - this.f5303r;
        float f5 = this.u ? this.mAxisMaximum : f3 + this.f5304s;
        if (Math.abs(f5 - f4) == 0.0f) {
            f5 += 1.0f;
            f4 -= 1.0f;
        }
        this.mAxisMinimum = f4;
        this.mAxisMaximum = f5;
        this.mAxisRange = Math.abs(f5 - f4);
    }

    public void disableAxisLineDashedLine() {
        this.mAxisLineDashPathEffect = null;
    }

    public void disableGridDashedLine() {
        this.mGridDashPathEffect = null;
    }

    public void enableAxisLineDashedLine(float f2, float f3, float f4) {
        this.mAxisLineDashPathEffect = new DashPathEffect(new float[]{f2, f3}, f4);
    }

    public void enableGridDashedLine(float f2, float f3, float f4) {
        this.mGridDashPathEffect = new DashPathEffect(new float[]{f2, f3}, f4);
    }

    public int getAxisLineColor() {
        return this.mAxisLineColor;
    }

    public DashPathEffect getAxisLineDashPathEffect() {
        return this.mAxisLineDashPathEffect;
    }

    public float getAxisLineWidth() {
        return this.mAxisLineWidth;
    }

    public float getAxisMaximum() {
        return this.mAxisMaximum;
    }

    public float getAxisMinimum() {
        return this.mAxisMinimum;
    }

    public String getFormattedLabel(int i2) {
        return (i2 < 0 || i2 >= this.mEntries.length) ? "" : getValueFormatter().getFormattedValue(this.mEntries[i2], this);
    }

    public float getGranularity() {
        return this.f5293h;
    }

    public int getGridColor() {
        return this.mGridColor;
    }

    public DashPathEffect getGridDashPathEffect() {
        return this.mGridDashPathEffect;
    }

    public float getGridLineWidth() {
        return this.mGridLineWidth;
    }

    public int getLabelCount() {
        return this.mLabelCount;
    }

    public List<LimitLine> getLimitLines() {
        return this.f5300o;
    }

    public String getLongestLabel() {
        String str = "";
        for (int i2 = 0; i2 < this.mEntries.length; i2++) {
            String formattedLabel = getFormattedLabel(i2);
            if (formattedLabel != null && str.length() < formattedLabel.length()) {
                str = formattedLabel;
            }
        }
        return str;
    }

    public float getSpaceMax() {
        return this.f5304s;
    }

    public float getSpaceMin() {
        return this.f5303r;
    }

    public IAxisValueFormatter getValueFormatter() {
        IAxisValueFormatter iAxisValueFormatter = this.f5292g;
        if (iAxisValueFormatter == null || ((iAxisValueFormatter instanceof DefaultAxisValueFormatter) && ((DefaultAxisValueFormatter) iAxisValueFormatter).getDecimalDigits() != this.mDecimals)) {
            this.f5292g = new DefaultAxisValueFormatter(this.mDecimals);
        }
        return this.f5292g;
    }

    public boolean isAxisLineDashedLineEnabled() {
        return this.mAxisLineDashPathEffect != null;
    }

    public boolean isAxisMaxCustom() {
        return this.u;
    }

    public boolean isAxisMinCustom() {
        return this.f5305t;
    }

    public boolean isCenterAxisLabelsEnabled() {
        return this.f5299n && this.mEntryCount > 0;
    }

    public boolean isDrawAxisLineEnabled() {
        return this.f5297l;
    }

    public boolean isDrawGridLinesBehindDataEnabled() {
        return this.f5302q;
    }

    public boolean isDrawGridLinesEnabled() {
        return this.f5296k;
    }

    public boolean isDrawLabelsEnabled() {
        return this.f5298m;
    }

    public boolean isDrawLimitLinesBehindDataEnabled() {
        return this.f5301p;
    }

    public boolean isForceLabelsEnabled() {
        return this.f5295j;
    }

    public boolean isGranularityEnabled() {
        return this.f5294i;
    }

    public boolean isGridDashedLineEnabled() {
        return this.mGridDashPathEffect != null;
    }

    public void removeAllLimitLines() {
        this.f5300o.clear();
    }

    public void removeLimitLine(LimitLine limitLine) {
        this.f5300o.remove(limitLine);
    }

    public void resetAxisMaximum() {
        this.u = false;
    }

    public void resetAxisMinimum() {
        this.f5305t = false;
    }

    public void setAxisLineColor(int i2) {
        this.mAxisLineColor = i2;
    }

    public void setAxisLineDashedLine(DashPathEffect dashPathEffect) {
        this.mAxisLineDashPathEffect = dashPathEffect;
    }

    public void setAxisLineWidth(float f2) {
        this.mAxisLineWidth = Utils.convertDpToPixel(f2);
    }

    @Deprecated
    public void setAxisMaxValue(float f2) {
        setAxisMaximum(f2);
    }

    public void setAxisMaximum(float f2) {
        this.u = true;
        this.mAxisMaximum = f2;
        this.mAxisRange = Math.abs(f2 - this.mAxisMinimum);
    }

    @Deprecated
    public void setAxisMinValue(float f2) {
        setAxisMinimum(f2);
    }

    public void setAxisMinimum(float f2) {
        this.f5305t = true;
        this.mAxisMinimum = f2;
        this.mAxisRange = Math.abs(this.mAxisMaximum - f2);
    }

    public void setCenterAxisLabels(boolean z) {
        this.f5299n = z;
    }

    public void setDrawAxisLine(boolean z) {
        this.f5297l = z;
    }

    public void setDrawGridLines(boolean z) {
        this.f5296k = z;
    }

    public void setDrawGridLinesBehindData(boolean z) {
        this.f5302q = z;
    }

    public void setDrawLabels(boolean z) {
        this.f5298m = z;
    }

    public void setDrawLimitLinesBehindData(boolean z) {
        this.f5301p = z;
    }

    public void setGranularity(float f2) {
        this.f5293h = f2;
        this.f5294i = true;
    }

    public void setGranularityEnabled(boolean z) {
        this.f5294i = z;
    }

    public void setGridColor(int i2) {
        this.mGridColor = i2;
    }

    public void setGridDashedLine(DashPathEffect dashPathEffect) {
        this.mGridDashPathEffect = dashPathEffect;
    }

    public void setGridLineWidth(float f2) {
        this.mGridLineWidth = Utils.convertDpToPixel(f2);
    }

    public void setLabelCount(int i2) {
        if (i2 > 25) {
            i2 = 25;
        }
        if (i2 < 2) {
            i2 = 2;
        }
        this.mLabelCount = i2;
        this.f5295j = false;
    }

    public void setLabelCount(int i2, boolean z) {
        setLabelCount(i2);
        this.f5295j = z;
    }

    public void setSpaceMax(float f2) {
        this.f5304s = f2;
    }

    public void setSpaceMin(float f2) {
        this.f5303r = f2;
    }

    public void setValueFormatter(IAxisValueFormatter iAxisValueFormatter) {
        if (iAxisValueFormatter == null) {
            iAxisValueFormatter = new DefaultAxisValueFormatter(this.mDecimals);
        }
        this.f5292g = iAxisValueFormatter;
    }
}
