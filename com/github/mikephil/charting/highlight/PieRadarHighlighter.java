package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.PieRadarChartBase;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public abstract class PieRadarHighlighter<T extends PieRadarChartBase> implements IHighlighter {

    /* renamed from: a  reason: collision with root package name */
    protected PieRadarChartBase f5353a;

    /* renamed from: b  reason: collision with root package name */
    protected List f5354b = new ArrayList();

    public PieRadarHighlighter(T t2) {
        this.f5353a = t2;
    }

    protected abstract Highlight a(int i2, float f2, float f3);

    /* JADX WARN: Type inference failed for: r2v3, types: [com.github.mikephil.charting.data.ChartData] */
    @Override // com.github.mikephil.charting.highlight.IHighlighter
    public Highlight getHighlight(float f2, float f3) {
        if (this.f5353a.distanceToCenter(f2, f3) > this.f5353a.getRadius()) {
            return null;
        }
        float angleForPoint = this.f5353a.getAngleForPoint(f2, f3);
        PieRadarChartBase pieRadarChartBase = this.f5353a;
        if (pieRadarChartBase instanceof PieChart) {
            angleForPoint /= pieRadarChartBase.getAnimator().getPhaseY();
        }
        int indexForAngle = this.f5353a.getIndexForAngle(angleForPoint);
        if (indexForAngle < 0 || indexForAngle >= this.f5353a.getData().getMaxEntryCountSet().getEntryCount()) {
            return null;
        }
        return a(indexForAngle, f2, f3);
    }
}
