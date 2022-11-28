package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Path;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;
/* loaded from: classes.dex */
public class YAxisRendererRadarChart extends YAxisRenderer {
    private RadarChart mChart;
    private Path mRenderLimitLinesPathBuffer;

    public YAxisRendererRadarChart(ViewPortHandler viewPortHandler, YAxis yAxis, RadarChart radarChart) {
        super(viewPortHandler, yAxis, null);
        this.mRenderLimitLinesPathBuffer = new Path();
        this.mChart = radarChart;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.renderer.AxisRenderer
    public void a(float f2, float f3) {
        int i2;
        AxisBase axisBase;
        int i3;
        float f4 = f2;
        int labelCount = this.f5381b.getLabelCount();
        double abs = Math.abs(f3 - f4);
        if (labelCount == 0 || abs <= 0.0d || Double.isInfinite(abs)) {
            AxisBase axisBase2 = this.f5381b;
            axisBase2.mEntries = new float[0];
            axisBase2.mCenteredEntries = new float[0];
            axisBase2.mEntryCount = 0;
            return;
        }
        double roundToNextSignificant = Utils.roundToNextSignificant(abs / labelCount);
        if (this.f5381b.isGranularityEnabled() && roundToNextSignificant < this.f5381b.getGranularity()) {
            roundToNextSignificant = this.f5381b.getGranularity();
        }
        double roundToNextSignificant2 = Utils.roundToNextSignificant(Math.pow(10.0d, (int) Math.log10(roundToNextSignificant)));
        if (((int) (roundToNextSignificant / roundToNextSignificant2)) > 5) {
            roundToNextSignificant = Math.floor(roundToNextSignificant2 * 10.0d);
        }
        boolean isCenterAxisLabelsEnabled = this.f5381b.isCenterAxisLabelsEnabled();
        if (this.f5381b.isForceLabelsEnabled()) {
            float f5 = ((float) abs) / (labelCount - 1);
            AxisBase axisBase3 = this.f5381b;
            axisBase3.mEntryCount = labelCount;
            if (axisBase3.mEntries.length < labelCount) {
                axisBase3.mEntries = new float[labelCount];
            }
            for (int i4 = 0; i4 < labelCount; i4++) {
                this.f5381b.mEntries[i4] = f4;
                f4 += f5;
            }
        } else {
            int i5 = (roundToNextSignificant > 0.0d ? 1 : (roundToNextSignificant == 0.0d ? 0 : -1));
            double ceil = i5 == 0 ? 0.0d : Math.ceil(f4 / roundToNextSignificant) * roundToNextSignificant;
            if (isCenterAxisLabelsEnabled) {
                ceil -= roundToNextSignificant;
            }
            double nextUp = i5 == 0 ? 0.0d : Utils.nextUp(Math.floor(f3 / roundToNextSignificant) * roundToNextSignificant);
            if (i5 != 0) {
                i2 = isCenterAxisLabelsEnabled;
                for (double d2 = ceil; d2 <= nextUp; d2 += roundToNextSignificant) {
                    i2++;
                }
            } else {
                i2 = isCenterAxisLabelsEnabled;
            }
            int i6 = i2 + 1;
            AxisBase axisBase4 = this.f5381b;
            axisBase4.mEntryCount = i6;
            if (axisBase4.mEntries.length < i6) {
                axisBase4.mEntries = new float[i6];
            }
            for (int i7 = 0; i7 < i6; i7++) {
                if (ceil == 0.0d) {
                    ceil = 0.0d;
                }
                this.f5381b.mEntries[i7] = (float) ceil;
                ceil += roundToNextSignificant;
            }
            labelCount = i6;
        }
        if (roundToNextSignificant < 1.0d) {
            axisBase = this.f5381b;
            i3 = (int) Math.ceil(-Math.log10(roundToNextSignificant));
        } else {
            axisBase = this.f5381b;
            i3 = 0;
        }
        axisBase.mDecimals = i3;
        if (isCenterAxisLabelsEnabled != 0) {
            AxisBase axisBase5 = this.f5381b;
            if (axisBase5.mCenteredEntries.length < labelCount) {
                axisBase5.mCenteredEntries = new float[labelCount];
            }
            float[] fArr = axisBase5.mEntries;
            float f6 = (fArr[1] - fArr[0]) / 2.0f;
            for (int i8 = 0; i8 < labelCount; i8++) {
                AxisBase axisBase6 = this.f5381b;
                axisBase6.mCenteredEntries[i8] = axisBase6.mEntries[i8] + f6;
            }
        }
        AxisBase axisBase7 = this.f5381b;
        float[] fArr2 = axisBase7.mEntries;
        float f7 = fArr2[0];
        axisBase7.mAxisMinimum = f7;
        float f8 = fArr2[labelCount - 1];
        axisBase7.mAxisMaximum = f8;
        axisBase7.mAxisRange = Math.abs(f8 - f7);
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderAxisLabels(Canvas canvas) {
        if (this.f5447h.isEnabled() && this.f5447h.isDrawLabelsEnabled()) {
            this.f5384e.setTypeface(this.f5447h.getTypeface());
            this.f5384e.setTextSize(this.f5447h.getTextSize());
            this.f5384e.setColor(this.f5447h.getTextColor());
            MPPointF centerOffsets = this.mChart.getCenterOffsets();
            MPPointF mPPointF = MPPointF.getInstance(0.0f, 0.0f);
            float factor = this.mChart.getFactor();
            int i2 = this.f5447h.isDrawTopYLabelEntryEnabled() ? this.f5447h.mEntryCount : this.f5447h.mEntryCount - 1;
            for (int i3 = !this.f5447h.isDrawBottomYLabelEntryEnabled(); i3 < i2; i3++) {
                YAxis yAxis = this.f5447h;
                Utils.getPosition(centerOffsets, (yAxis.mEntries[i3] - yAxis.mAxisMinimum) * factor, this.mChart.getRotationAngle(), mPPointF);
                canvas.drawText(this.f5447h.getFormattedLabel(i3), mPPointF.x + 10.0f, mPPointF.y, this.f5384e);
            }
            MPPointF.recycleInstance(centerOffsets);
            MPPointF.recycleInstance(mPPointF);
        }
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderLimitLines(Canvas canvas) {
        List<LimitLine> limitLines = this.f5447h.getLimitLines();
        if (limitLines == null) {
            return;
        }
        float sliceAngle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF centerOffsets = this.mChart.getCenterOffsets();
        MPPointF mPPointF = MPPointF.getInstance(0.0f, 0.0f);
        for (int i2 = 0; i2 < limitLines.size(); i2++) {
            LimitLine limitLine = limitLines.get(i2);
            if (limitLine.isEnabled()) {
                this.f5386g.setColor(limitLine.getLineColor());
                this.f5386g.setPathEffect(limitLine.getDashPathEffect());
                this.f5386g.setStrokeWidth(limitLine.getLineWidth());
                float limit = (limitLine.getLimit() - this.mChart.getYChartMin()) * factor;
                Path path = this.mRenderLimitLinesPathBuffer;
                path.reset();
                for (int i3 = 0; i3 < ((RadarData) this.mChart.getData()).getMaxEntryCountSet().getEntryCount(); i3++) {
                    Utils.getPosition(centerOffsets, limit, (i3 * sliceAngle) + this.mChart.getRotationAngle(), mPPointF);
                    float f2 = mPPointF.x;
                    float f3 = mPPointF.y;
                    if (i3 == 0) {
                        path.moveTo(f2, f3);
                    } else {
                        path.lineTo(f2, f3);
                    }
                }
                path.close();
                canvas.drawPath(path, this.f5386g);
            }
        }
        MPPointF.recycleInstance(centerOffsets);
        MPPointF.recycleInstance(mPPointF);
    }
}
