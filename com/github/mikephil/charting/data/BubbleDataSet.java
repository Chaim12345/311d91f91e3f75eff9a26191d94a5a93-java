package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class BubbleDataSet extends BarLineScatterCandleBubbleDataSet<BubbleEntry> implements IBubbleDataSet {
    private float mHighlightCircleWidth;

    /* renamed from: t  reason: collision with root package name */
    protected float f5328t;
    protected boolean u;

    public BubbleDataSet(List<BubbleEntry> list, String str) {
        super(list, str);
        this.u = true;
        this.mHighlightCircleWidth = 2.5f;
    }

    @Override // com.github.mikephil.charting.data.DataSet
    public DataSet<BubbleEntry> copy() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.f5338n.size(); i2++) {
            arrayList.add(((BubbleEntry) this.f5338n.get(i2)).copy());
        }
        BubbleDataSet bubbleDataSet = new BubbleDataSet(arrayList, getLabel());
        h(bubbleDataSet);
        return bubbleDataSet;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.data.DataSet
    /* renamed from: g */
    public void b(BubbleEntry bubbleEntry) {
        super.b(bubbleEntry);
        float size = bubbleEntry.getSize();
        if (size > this.f5328t) {
            this.f5328t = size;
        }
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet
    public float getHighlightCircleWidth() {
        return this.mHighlightCircleWidth;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet
    public float getMaxSize() {
        return this.f5328t;
    }

    protected void h(BubbleDataSet bubbleDataSet) {
        bubbleDataSet.mHighlightCircleWidth = this.mHighlightCircleWidth;
        bubbleDataSet.u = this.u;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet
    public boolean isNormalizeSizeEnabled() {
        return this.u;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet
    public void setHighlightCircleWidth(float f2) {
        this.mHighlightCircleWidth = Utils.convertDpToPixel(f2);
    }

    public void setNormalizeSizeEnabled(boolean z) {
        this.u = z;
    }
}
