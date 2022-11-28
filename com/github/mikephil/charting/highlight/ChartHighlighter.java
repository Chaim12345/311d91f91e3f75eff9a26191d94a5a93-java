package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class ChartHighlighter<T extends BarLineScatterCandleBubbleDataProvider> implements IHighlighter {

    /* renamed from: a  reason: collision with root package name */
    protected BarLineScatterCandleBubbleDataProvider f5350a;

    /* renamed from: b  reason: collision with root package name */
    protected List f5351b = new ArrayList();

    public ChartHighlighter(T t2) {
        this.f5350a = t2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List a(IDataSet iDataSet, int i2, float f2, DataSet.Rounding rounding) {
        Entry entryForXValue;
        ArrayList arrayList = new ArrayList();
        List<Entry> entriesForXValue = iDataSet.getEntriesForXValue(f2);
        if (entriesForXValue.size() == 0 && (entryForXValue = iDataSet.getEntryForXValue(f2, Float.NaN, rounding)) != null) {
            entriesForXValue = iDataSet.getEntriesForXValue(entryForXValue.getX());
        }
        if (entriesForXValue.size() == 0) {
            return arrayList;
        }
        for (Entry entry : entriesForXValue) {
            MPPointD pixelForValues = this.f5350a.getTransformer(iDataSet.getAxisDependency()).getPixelForValues(entry.getX(), entry.getY());
            arrayList.add(new Highlight(entry.getX(), entry.getY(), (float) pixelForValues.x, (float) pixelForValues.y, i2, iDataSet.getAxisDependency()));
        }
        return arrayList;
    }

    protected BarLineScatterCandleBubbleData b() {
        return this.f5350a.getData();
    }

    protected float c(float f2, float f3, float f4, float f5) {
        return (float) Math.hypot(f2 - f4, f3 - f5);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Highlight d(float f2, float f3, float f4) {
        List<Highlight> f5 = f(f2, f3, f4);
        if (f5.isEmpty()) {
            return null;
        }
        YAxis.AxisDependency axisDependency = YAxis.AxisDependency.LEFT;
        float g2 = g(f5, f4, axisDependency);
        YAxis.AxisDependency axisDependency2 = YAxis.AxisDependency.RIGHT;
        return getClosestHighlightByPixel(f5, f3, f4, g2 < g(f5, f4, axisDependency2) ? axisDependency : axisDependency2, this.f5350a.getMaxHighlightDistance());
    }

    protected float e(Highlight highlight) {
        return highlight.getYPx();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.github.mikephil.charting.interfaces.datasets.IDataSet] */
    protected List f(float f2, float f3, float f4) {
        this.f5351b.clear();
        BarLineScatterCandleBubbleData b2 = b();
        if (b2 == null) {
            return this.f5351b;
        }
        int dataSetCount = b2.getDataSetCount();
        for (int i2 = 0; i2 < dataSetCount; i2++) {
            ?? dataSetByIndex = b2.getDataSetByIndex(i2);
            if (dataSetByIndex.isHighlightEnabled()) {
                this.f5351b.addAll(a(dataSetByIndex, i2, f2, DataSet.Rounding.CLOSEST));
            }
        }
        return this.f5351b;
    }

    protected float g(List list, float f2, YAxis.AxisDependency axisDependency) {
        float f3 = Float.MAX_VALUE;
        for (int i2 = 0; i2 < list.size(); i2++) {
            Highlight highlight = (Highlight) list.get(i2);
            if (highlight.getAxis() == axisDependency) {
                float abs = Math.abs(e(highlight) - f2);
                if (abs < f3) {
                    f3 = abs;
                }
            }
        }
        return f3;
    }

    public Highlight getClosestHighlightByPixel(List<Highlight> list, float f2, float f3, YAxis.AxisDependency axisDependency, float f4) {
        Highlight highlight = null;
        for (int i2 = 0; i2 < list.size(); i2++) {
            Highlight highlight2 = list.get(i2);
            if (axisDependency == null || highlight2.getAxis() == axisDependency) {
                float c2 = c(f2, f3, highlight2.getXPx(), highlight2.getYPx());
                if (c2 < f4) {
                    highlight = highlight2;
                    f4 = c2;
                }
            }
        }
        return highlight;
    }

    @Override // com.github.mikephil.charting.highlight.IHighlighter
    public Highlight getHighlight(float f2, float f3) {
        MPPointD h2 = h(f2, f3);
        MPPointD.recycleInstance(h2);
        return d((float) h2.x, f2, f3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MPPointD h(float f2, float f3) {
        return this.f5350a.getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(f2, f3);
    }
}
