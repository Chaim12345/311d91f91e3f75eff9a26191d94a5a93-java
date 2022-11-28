package com.github.mikephil.charting.data;

import android.graphics.Paint;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class CandleDataSet extends LineScatterCandleRadarDataSet<CandleEntry> implements ICandleDataSet {
    protected int A;
    protected int B;
    protected int C;
    private float mBarSpace;
    private boolean mShadowColorSameAsCandle;
    private float mShadowWidth;
    private boolean mShowCandleBar;
    protected Paint.Style x;
    protected Paint.Style y;
    protected int z;

    public CandleDataSet(List<CandleEntry> list, String str) {
        super(list, str);
        this.mShadowWidth = 3.0f;
        this.mShowCandleBar = true;
        this.mBarSpace = 0.1f;
        this.mShadowColorSameAsCandle = false;
        this.x = Paint.Style.STROKE;
        this.y = Paint.Style.FILL;
        this.z = ColorTemplate.COLOR_SKIP;
        this.A = ColorTemplate.COLOR_SKIP;
        this.B = ColorTemplate.COLOR_SKIP;
        this.C = ColorTemplate.COLOR_SKIP;
    }

    @Override // com.github.mikephil.charting.data.DataSet
    public DataSet<CandleEntry> copy() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.f5338n.size(); i2++) {
            arrayList.add(((CandleEntry) this.f5338n.get(i2)).copy());
        }
        CandleDataSet candleDataSet = new CandleDataSet(arrayList, getLabel());
        j(candleDataSet);
        return candleDataSet;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ICandleDataSet
    public float getBarSpace() {
        return this.mBarSpace;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ICandleDataSet
    public int getDecreasingColor() {
        return this.B;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ICandleDataSet
    public Paint.Style getDecreasingPaintStyle() {
        return this.y;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ICandleDataSet
    public int getIncreasingColor() {
        return this.A;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ICandleDataSet
    public Paint.Style getIncreasingPaintStyle() {
        return this.x;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ICandleDataSet
    public int getNeutralColor() {
        return this.z;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ICandleDataSet
    public int getShadowColor() {
        return this.C;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ICandleDataSet
    public boolean getShadowColorSameAsCandle() {
        return this.mShadowColorSameAsCandle;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ICandleDataSet
    public float getShadowWidth() {
        return this.mShadowWidth;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ICandleDataSet
    public boolean getShowCandleBar() {
        return this.mShowCandleBar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.data.DataSet
    /* renamed from: h */
    public void b(CandleEntry candleEntry) {
        if (candleEntry.getLow() < this.f5340p) {
            this.f5340p = candleEntry.getLow();
        }
        if (candleEntry.getHigh() > this.f5339o) {
            this.f5339o = candleEntry.getHigh();
        }
        c(candleEntry);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.data.DataSet
    /* renamed from: i */
    public void d(CandleEntry candleEntry) {
        if (candleEntry.getHigh() < this.f5340p) {
            this.f5340p = candleEntry.getHigh();
        }
        if (candleEntry.getHigh() > this.f5339o) {
            this.f5339o = candleEntry.getHigh();
        }
        if (candleEntry.getLow() < this.f5340p) {
            this.f5340p = candleEntry.getLow();
        }
        if (candleEntry.getLow() > this.f5339o) {
            this.f5339o = candleEntry.getLow();
        }
    }

    protected void j(CandleDataSet candleDataSet) {
        super.g(candleDataSet);
        candleDataSet.mShadowWidth = this.mShadowWidth;
        candleDataSet.mShowCandleBar = this.mShowCandleBar;
        candleDataSet.mBarSpace = this.mBarSpace;
        candleDataSet.mShadowColorSameAsCandle = this.mShadowColorSameAsCandle;
        candleDataSet.f5314s = this.f5314s;
        candleDataSet.x = this.x;
        candleDataSet.y = this.y;
        candleDataSet.z = this.z;
        candleDataSet.A = this.A;
        candleDataSet.B = this.B;
        candleDataSet.C = this.C;
    }

    public void setBarSpace(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > 0.45f) {
            f2 = 0.45f;
        }
        this.mBarSpace = f2;
    }

    public void setDecreasingColor(int i2) {
        this.B = i2;
    }

    public void setDecreasingPaintStyle(Paint.Style style) {
        this.y = style;
    }

    public void setIncreasingColor(int i2) {
        this.A = i2;
    }

    public void setIncreasingPaintStyle(Paint.Style style) {
        this.x = style;
    }

    public void setNeutralColor(int i2) {
        this.z = i2;
    }

    public void setShadowColor(int i2) {
        this.C = i2;
    }

    public void setShadowColorSameAsCandle(boolean z) {
        this.mShadowColorSameAsCandle = z;
    }

    public void setShadowWidth(float f2) {
        this.mShadowWidth = Utils.convertDpToPixel(f2);
    }

    public void setShowCandleBar(boolean z) {
        this.mShowCandleBar = z;
    }
}
