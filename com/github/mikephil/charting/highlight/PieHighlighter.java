package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
/* loaded from: classes.dex */
public class PieHighlighter extends PieRadarHighlighter<PieChart> {
    public PieHighlighter(PieChart pieChart) {
        super(pieChart);
    }

    @Override // com.github.mikephil.charting.highlight.PieRadarHighlighter
    protected Highlight a(int i2, float f2, float f3) {
        IPieDataSet dataSet = ((PieData) ((PieChart) this.f5353a).getData()).getDataSet();
        return new Highlight(i2, dataSet.getEntryForIndex(i2).getY(), f2, f3, 0, dataSet.getAxisDependency());
    }
}
