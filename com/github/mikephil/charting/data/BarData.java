package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import java.util.List;
/* loaded from: classes.dex */
public class BarData extends BarLineScatterCandleBubbleData<IBarDataSet> {
    private float mBarWidth;

    public BarData() {
        this.mBarWidth = 0.85f;
    }

    public BarData(List<IBarDataSet> list) {
        super(list);
        this.mBarWidth = 0.85f;
    }

    public BarData(IBarDataSet... iBarDataSetArr) {
        super(iBarDataSetArr);
        this.mBarWidth = 0.85f;
    }

    public float getBarWidth() {
        return this.mBarWidth;
    }

    public float getGroupWidth(float f2, float f3) {
        return (this.f5337i.size() * (this.mBarWidth + f3)) + f2;
    }

    public void groupBars(float f2, float f3, float f4) {
        BarEntry barEntry;
        if (this.f5337i.size() <= 1) {
            throw new RuntimeException("BarData needs to hold at least 2 BarDataSets to allow grouping.");
        }
        int entryCount = ((IBarDataSet) getMaxEntryCountSet()).getEntryCount();
        float f5 = f3 / 2.0f;
        float f6 = f4 / 2.0f;
        float f7 = this.mBarWidth / 2.0f;
        float groupWidth = getGroupWidth(f3, f4);
        for (int i2 = 0; i2 < entryCount; i2++) {
            float f8 = f2 + f5;
            for (IBarDataSet iBarDataSet : this.f5337i) {
                float f9 = f8 + f6 + f7;
                if (i2 < iBarDataSet.getEntryCount() && (barEntry = (BarEntry) iBarDataSet.getEntryForIndex(i2)) != null) {
                    barEntry.setX(f9);
                }
                f8 = f9 + f7 + f6;
            }
            float f10 = f8 + f5;
            float f11 = groupWidth - (f10 - f2);
            if (f11 > 0.0f || f11 < 0.0f) {
                f10 += f11;
            }
            f2 = f10;
        }
        notifyDataChanged();
    }

    public void setBarWidth(float f2) {
        this.mBarWidth = f2;
    }
}
