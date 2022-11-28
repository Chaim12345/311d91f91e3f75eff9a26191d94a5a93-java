package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class RadarDataSet extends LineRadarDataSet<RadarEntry> implements IRadarDataSet {
    protected int A;
    protected int B;
    protected float C;
    protected float D;
    protected float E;
    protected boolean y;
    protected int z;

    public RadarDataSet(List<RadarEntry> list, String str) {
        super(list, str);
        this.y = false;
        this.z = -1;
        this.A = ColorTemplate.COLOR_NONE;
        this.B = 76;
        this.C = 3.0f;
        this.D = 4.0f;
        this.E = 2.0f;
    }

    @Override // com.github.mikephil.charting.data.DataSet
    public DataSet<RadarEntry> copy() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.f5338n.size(); i2++) {
            arrayList.add(((RadarEntry) this.f5338n.get(i2)).copy());
        }
        RadarDataSet radarDataSet = new RadarDataSet(arrayList, getLabel());
        i(radarDataSet);
        return radarDataSet;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
    public int getHighlightCircleFillColor() {
        return this.z;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
    public float getHighlightCircleInnerRadius() {
        return this.C;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
    public float getHighlightCircleOuterRadius() {
        return this.D;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
    public int getHighlightCircleStrokeAlpha() {
        return this.B;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
    public int getHighlightCircleStrokeColor() {
        return this.A;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
    public float getHighlightCircleStrokeWidth() {
        return this.E;
    }

    protected void i(RadarDataSet radarDataSet) {
        super.h(radarDataSet);
        radarDataSet.y = this.y;
        radarDataSet.z = this.z;
        radarDataSet.C = this.C;
        radarDataSet.B = this.B;
        radarDataSet.A = this.A;
        radarDataSet.E = this.E;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
    public boolean isDrawHighlightCircleEnabled() {
        return this.y;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
    public void setDrawHighlightCircleEnabled(boolean z) {
        this.y = z;
    }

    public void setHighlightCircleFillColor(int i2) {
        this.z = i2;
    }

    public void setHighlightCircleInnerRadius(float f2) {
        this.C = f2;
    }

    public void setHighlightCircleOuterRadius(float f2) {
        this.D = f2;
    }

    public void setHighlightCircleStrokeAlpha(int i2) {
        this.B = i2;
    }

    public void setHighlightCircleStrokeColor(int i2) {
        this.A = i2;
    }

    public void setHighlightCircleStrokeWidth(float f2) {
        this.E = f2;
    }
}
