package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import java.util.List;
/* loaded from: classes.dex */
public class RadarHighlighter extends PieRadarHighlighter<RadarChart> {
    public RadarHighlighter(RadarChart radarChart) {
        super(radarChart);
    }

    @Override // com.github.mikephil.charting.highlight.PieRadarHighlighter
    protected Highlight a(int i2, float f2, float f3) {
        List b2 = b(i2);
        float distanceToCenter = ((RadarChart) this.f5353a).distanceToCenter(f2, f3) / ((RadarChart) this.f5353a).getFactor();
        Highlight highlight = null;
        float f4 = Float.MAX_VALUE;
        for (int i3 = 0; i3 < b2.size(); i3++) {
            Highlight highlight2 = (Highlight) b2.get(i3);
            float abs = Math.abs(highlight2.getY() - distanceToCenter);
            if (abs < f4) {
                highlight = highlight2;
                f4 = abs;
            }
        }
        return highlight;
    }

    /* JADX WARN: Type inference failed for: r9v0, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    protected List b(int i2) {
        int i3 = i2;
        this.f5354b.clear();
        float phaseX = ((RadarChart) this.f5353a).getAnimator().getPhaseX();
        float phaseY = ((RadarChart) this.f5353a).getAnimator().getPhaseY();
        float sliceAngle = ((RadarChart) this.f5353a).getSliceAngle();
        float factor = ((RadarChart) this.f5353a).getFactor();
        MPPointF mPPointF = MPPointF.getInstance(0.0f, 0.0f);
        int i4 = 0;
        while (i4 < ((RadarData) ((RadarChart) this.f5353a).getData()).getDataSetCount()) {
            IRadarDataSet dataSetByIndex = ((RadarData) ((RadarChart) this.f5353a).getData()).getDataSetByIndex(i4);
            ?? entryForIndex = dataSetByIndex.getEntryForIndex(i3);
            float f2 = i3;
            Utils.getPosition(((RadarChart) this.f5353a).getCenterOffsets(), (entryForIndex.getY() - ((RadarChart) this.f5353a).getYChartMin()) * factor * phaseY, (sliceAngle * f2 * phaseX) + ((RadarChart) this.f5353a).getRotationAngle(), mPPointF);
            this.f5354b.add(new Highlight(f2, entryForIndex.getY(), mPPointF.x, mPPointF.y, i4, dataSetByIndex.getAxisDependency()));
            i4++;
            i3 = i2;
        }
        return this.f5354b;
    }
}
