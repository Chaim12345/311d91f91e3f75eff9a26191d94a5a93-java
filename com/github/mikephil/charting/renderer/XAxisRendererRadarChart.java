package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
/* loaded from: classes.dex */
public class XAxisRendererRadarChart extends XAxisRenderer {
    private RadarChart mChart;

    public XAxisRendererRadarChart(ViewPortHandler viewPortHandler, XAxis xAxis, RadarChart radarChart) {
        super(viewPortHandler, xAxis, null);
        this.mChart = radarChart;
    }

    @Override // com.github.mikephil.charting.renderer.XAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderAxisLabels(Canvas canvas) {
        if (this.f5439h.isEnabled() && this.f5439h.isDrawLabelsEnabled()) {
            float labelRotationAngle = this.f5439h.getLabelRotationAngle();
            MPPointF mPPointF = MPPointF.getInstance(0.5f, 0.25f);
            this.f5384e.setTypeface(this.f5439h.getTypeface());
            this.f5384e.setTextSize(this.f5439h.getTextSize());
            this.f5384e.setColor(this.f5439h.getTextColor());
            float sliceAngle = this.mChart.getSliceAngle();
            float factor = this.mChart.getFactor();
            MPPointF centerOffsets = this.mChart.getCenterOffsets();
            MPPointF mPPointF2 = MPPointF.getInstance(0.0f, 0.0f);
            for (int i2 = 0; i2 < ((RadarData) this.mChart.getData()).getMaxEntryCountSet().getEntryCount(); i2++) {
                float f2 = i2;
                String formattedValue = this.f5439h.getValueFormatter().getFormattedValue(f2, this.f5439h);
                Utils.getPosition(centerOffsets, (this.mChart.getYRange() * factor) + (this.f5439h.mLabelRotatedWidth / 2.0f), ((f2 * sliceAngle) + this.mChart.getRotationAngle()) % 360.0f, mPPointF2);
                d(canvas, formattedValue, mPPointF2.x, mPPointF2.y - (this.f5439h.mLabelRotatedHeight / 2.0f), mPPointF, labelRotationAngle);
            }
            MPPointF.recycleInstance(centerOffsets);
            MPPointF.recycleInstance(mPPointF2);
            MPPointF.recycleInstance(mPPointF);
        }
    }

    @Override // com.github.mikephil.charting.renderer.XAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderLimitLines(Canvas canvas) {
    }
}
