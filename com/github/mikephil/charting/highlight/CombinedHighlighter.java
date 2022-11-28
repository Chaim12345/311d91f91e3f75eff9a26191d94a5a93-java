package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.CombinedDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import java.util.List;
/* loaded from: classes.dex */
public class CombinedHighlighter extends ChartHighlighter<CombinedDataProvider> {

    /* renamed from: c  reason: collision with root package name */
    protected BarHighlighter f5352c;

    public CombinedHighlighter(CombinedDataProvider combinedDataProvider, BarDataProvider barDataProvider) {
        super(combinedDataProvider);
        this.f5352c = barDataProvider.getBarData() == null ? null : new BarHighlighter(barDataProvider);
    }

    @Override // com.github.mikephil.charting.highlight.ChartHighlighter
    protected List f(float f2, float f3, float f4) {
        this.f5351b.clear();
        List<BarLineScatterCandleBubbleData> allData = ((CombinedDataProvider) this.f5350a).getCombinedData().getAllData();
        for (int i2 = 0; i2 < allData.size(); i2++) {
            BarLineScatterCandleBubbleData barLineScatterCandleBubbleData = allData.get(i2);
            BarHighlighter barHighlighter = this.f5352c;
            if (barHighlighter == null || !(barLineScatterCandleBubbleData instanceof BarData)) {
                int dataSetCount = barLineScatterCandleBubbleData.getDataSetCount();
                for (int i3 = 0; i3 < dataSetCount; i3++) {
                    IDataSet dataSetByIndex = allData.get(i2).getDataSetByIndex(i3);
                    if (dataSetByIndex.isHighlightEnabled()) {
                        for (Highlight highlight : a(dataSetByIndex, i3, f2, DataSet.Rounding.CLOSEST)) {
                            highlight.setDataIndex(i2);
                            this.f5351b.add(highlight);
                        }
                    }
                }
            } else {
                Highlight highlight2 = barHighlighter.getHighlight(f3, f4);
                if (highlight2 != null) {
                    highlight2.setDataIndex(i2);
                    this.f5351b.add(highlight2);
                }
            }
        }
        return this.f5351b;
    }
}
