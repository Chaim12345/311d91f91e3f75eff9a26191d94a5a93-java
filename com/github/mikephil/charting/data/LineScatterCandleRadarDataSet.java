package com.github.mikephil.charting.data;

import android.graphics.DashPathEffect;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet;
import com.github.mikephil.charting.utils.Utils;
import java.util.List;
/* loaded from: classes.dex */
public abstract class LineScatterCandleRadarDataSet<T extends Entry> extends BarLineScatterCandleBubbleDataSet<T> implements ILineScatterCandleRadarDataSet<T> {

    /* renamed from: t  reason: collision with root package name */
    protected boolean f5343t;
    protected boolean u;
    protected float v;
    protected DashPathEffect w;

    public LineScatterCandleRadarDataSet(List<T> list, String str) {
        super(list, str);
        this.f5343t = true;
        this.u = true;
        this.v = 0.5f;
        this.w = null;
        this.v = Utils.convertDpToPixel(0.5f);
    }

    public void disableDashedHighlightLine() {
        this.w = null;
    }

    public void enableDashedHighlightLine(float f2, float f3, float f4) {
        this.w = new DashPathEffect(new float[]{f2, f3}, f4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void g(LineScatterCandleRadarDataSet lineScatterCandleRadarDataSet) {
        super.f(lineScatterCandleRadarDataSet);
        lineScatterCandleRadarDataSet.u = this.u;
        lineScatterCandleRadarDataSet.f5343t = this.f5343t;
        lineScatterCandleRadarDataSet.v = this.v;
        lineScatterCandleRadarDataSet.w = this.w;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet
    public DashPathEffect getDashPathEffectHighlight() {
        return this.w;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet
    public float getHighlightLineWidth() {
        return this.v;
    }

    public boolean isDashedHighlightLineEnabled() {
        return this.w != null;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet
    public boolean isHorizontalHighlightIndicatorEnabled() {
        return this.u;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet
    public boolean isVerticalHighlightIndicatorEnabled() {
        return this.f5343t;
    }

    public void setDrawHighlightIndicators(boolean z) {
        setDrawVerticalHighlightIndicator(z);
        setDrawHorizontalHighlightIndicator(z);
    }

    public void setDrawHorizontalHighlightIndicator(boolean z) {
        this.u = z;
    }

    public void setDrawVerticalHighlightIndicator(boolean z) {
        this.f5343t = z;
    }

    public void setHighlightLineWidth(float f2) {
        this.v = Utils.convertDpToPixel(f2);
    }
}
