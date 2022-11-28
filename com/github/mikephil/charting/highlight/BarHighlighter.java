package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.MPPointD;
/* loaded from: classes.dex */
public class BarHighlighter extends ChartHighlighter<BarDataProvider> {
    public BarHighlighter(BarDataProvider barDataProvider) {
        super(barDataProvider);
    }

    @Override // com.github.mikephil.charting.highlight.ChartHighlighter
    protected BarLineScatterCandleBubbleData b() {
        return ((BarDataProvider) this.f5350a).getBarData();
    }

    @Override // com.github.mikephil.charting.highlight.ChartHighlighter
    protected float c(float f2, float f3, float f4, float f5) {
        return Math.abs(f2 - f4);
    }

    @Override // com.github.mikephil.charting.highlight.ChartHighlighter, com.github.mikephil.charting.highlight.IHighlighter
    public Highlight getHighlight(float f2, float f3) {
        Highlight highlight = super.getHighlight(f2, f3);
        if (highlight == null) {
            return null;
        }
        MPPointD h2 = h(f2, f3);
        IBarDataSet iBarDataSet = (IBarDataSet) ((BarDataProvider) this.f5350a).getBarData().getDataSetByIndex(highlight.getDataSetIndex());
        if (iBarDataSet.isStacked()) {
            return getStackedHighlight(highlight, iBarDataSet, (float) h2.x, (float) h2.y);
        }
        MPPointD.recycleInstance(h2);
        return highlight;
    }

    public Highlight getStackedHighlight(Highlight highlight, IBarDataSet iBarDataSet, float f2, float f3) {
        BarEntry barEntry = (BarEntry) iBarDataSet.getEntryForXValue(f2, f3);
        if (barEntry == null) {
            return null;
        }
        if (barEntry.getYVals() == null) {
            return highlight;
        }
        Range[] ranges = barEntry.getRanges();
        if (ranges.length > 0) {
            int i2 = i(ranges, f3);
            MPPointD pixelForValues = ((BarDataProvider) this.f5350a).getTransformer(iBarDataSet.getAxisDependency()).getPixelForValues(highlight.getX(), ranges[i2].to);
            Highlight highlight2 = new Highlight(barEntry.getX(), barEntry.getY(), (float) pixelForValues.x, (float) pixelForValues.y, highlight.getDataSetIndex(), i2, highlight.getAxis());
            MPPointD.recycleInstance(pixelForValues);
            return highlight2;
        }
        return null;
    }

    protected int i(Range[] rangeArr, float f2) {
        if (rangeArr == null || rangeArr.length == 0) {
            return 0;
        }
        int i2 = 0;
        for (Range range : rangeArr) {
            if (range.contains(f2)) {
                return i2;
            }
            i2++;
        }
        int max = Math.max(rangeArr.length - 1, 0);
        if (f2 > rangeArr[max].to) {
            return max;
        }
        return 0;
    }
}
