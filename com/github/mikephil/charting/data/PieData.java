package com.github.mikephil.charting.data;

import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
/* loaded from: classes.dex */
public class PieData extends ChartData<IPieDataSet> {
    public PieData() {
    }

    public PieData(IPieDataSet iPieDataSet) {
        super(iPieDataSet);
    }

    public IPieDataSet getDataSet() {
        return (IPieDataSet) this.f5337i.get(0);
    }

    @Override // com.github.mikephil.charting.data.ChartData
    public IPieDataSet getDataSetByIndex(int i2) {
        if (i2 == 0) {
            return getDataSet();
        }
        return null;
    }

    @Override // com.github.mikephil.charting.data.ChartData
    public IPieDataSet getDataSetByLabel(String str, boolean z) {
        if (z) {
            if (!str.equalsIgnoreCase(((IPieDataSet) this.f5337i.get(0)).getLabel())) {
                return null;
            }
        } else if (!str.equals(((IPieDataSet) this.f5337i.get(0)).getLabel())) {
            return null;
        }
        return (IPieDataSet) this.f5337i.get(0);
    }

    @Override // com.github.mikephil.charting.data.ChartData
    public Entry getEntryForHighlight(Highlight highlight) {
        return getDataSet().getEntryForIndex((int) highlight.getX());
    }

    public float getYValueSum() {
        float f2 = 0.0f;
        for (int i2 = 0; i2 < getDataSet().getEntryCount(); i2++) {
            f2 += getDataSet().getEntryForIndex(i2).getY();
        }
        return f2;
    }

    public void setDataSet(IPieDataSet iPieDataSet) {
        this.f5337i.clear();
        this.f5337i.add(iPieDataSet);
        notifyDataChanged();
    }
}
