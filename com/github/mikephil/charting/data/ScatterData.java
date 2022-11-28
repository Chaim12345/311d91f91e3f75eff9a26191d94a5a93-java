package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import java.util.List;
/* loaded from: classes.dex */
public class ScatterData extends BarLineScatterCandleBubbleData<IScatterDataSet> {
    public ScatterData() {
    }

    public ScatterData(List<IScatterDataSet> list) {
        super(list);
    }

    public ScatterData(IScatterDataSet... iScatterDataSetArr) {
        super(iScatterDataSetArr);
    }

    public float getGreatestShapeSize() {
        float f2 = 0.0f;
        for (IScatterDataSet iScatterDataSet : this.f5337i) {
            float scatterShapeSize = iScatterDataSet.getScatterShapeSize();
            if (scatterShapeSize > f2) {
                f2 = scatterShapeSize;
            }
        }
        return f2;
    }
}
