package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.HorizontalBarHighlighter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.renderer.HorizontalBarChartRenderer;
import com.github.mikephil.charting.renderer.XAxisRendererHorizontalBarChart;
import com.github.mikephil.charting.renderer.YAxisRendererHorizontalBarChart;
import com.github.mikephil.charting.utils.HorizontalViewPortHandler;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.TransformerHorizontalBarChart;
import com.github.mikephil.charting.utils.Utils;
/* loaded from: classes.dex */
public class HorizontalBarChart extends BarChart {
    protected float[] a0;
    private RectF mOffsetsBuffer;

    public HorizontalBarChart(Context context) {
        super(context);
        this.mOffsetsBuffer = new RectF();
        this.a0 = new float[2];
    }

    public HorizontalBarChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOffsetsBuffer = new RectF();
        this.a0 = new float[2];
    }

    public HorizontalBarChart(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mOffsetsBuffer = new RectF();
        this.a0 = new float[2];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.charts.Chart
    public float[] c(Highlight highlight) {
        return new float[]{highlight.getDrawY(), highlight.getDrawX()};
    }

    @Override // com.github.mikephil.charting.charts.BarLineChartBase, com.github.mikephil.charting.charts.Chart
    public void calculateOffsets() {
        h(this.mOffsetsBuffer);
        RectF rectF = this.mOffsetsBuffer;
        float f2 = rectF.left + 0.0f;
        float f3 = rectF.top + 0.0f;
        float f4 = rectF.right + 0.0f;
        float f5 = rectF.bottom + 0.0f;
        if (this.J.needsOffset()) {
            f3 += this.J.getRequiredHeightSpace(this.L.getPaintAxisLabels());
        }
        if (this.K.needsOffset()) {
            f5 += this.K.getRequiredHeightSpace(this.M.getPaintAxisLabels());
        }
        XAxis xAxis = this.f5272g;
        float f6 = xAxis.mLabelRotatedWidth;
        if (xAxis.isEnabled()) {
            if (this.f5272g.getPosition() == XAxis.XAxisPosition.BOTTOM) {
                f2 += f6;
            } else {
                if (this.f5272g.getPosition() != XAxis.XAxisPosition.TOP) {
                    if (this.f5272g.getPosition() == XAxis.XAxisPosition.BOTH_SIDED) {
                        f2 += f6;
                    }
                }
                f4 += f6;
            }
        }
        float extraTopOffset = f3 + getExtraTopOffset();
        float extraRightOffset = f4 + getExtraRightOffset();
        float extraBottomOffset = f5 + getExtraBottomOffset();
        float extraLeftOffset = f2 + getExtraLeftOffset();
        float convertDpToPixel = Utils.convertDpToPixel(this.G);
        this.f5281p.restrainViewPort(Math.max(convertDpToPixel, extraLeftOffset), Math.max(convertDpToPixel, extraTopOffset), Math.max(convertDpToPixel, extraRightOffset), Math.max(convertDpToPixel, extraBottomOffset));
        if (this.f5266a) {
            StringBuilder sb = new StringBuilder();
            sb.append("offsetLeft: ");
            sb.append(extraLeftOffset);
            sb.append(", offsetTop: ");
            sb.append(extraTopOffset);
            sb.append(", offsetRight: ");
            sb.append(extraRightOffset);
            sb.append(", offsetBottom: ");
            sb.append(extraBottomOffset);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Content: ");
            sb2.append(this.f5281p.getContentRect().toString());
        }
        k();
        l();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.charts.BarChart, com.github.mikephil.charting.charts.BarLineChartBase, com.github.mikephil.charting.charts.Chart
    public void d() {
        this.f5281p = new HorizontalViewPortHandler();
        super.d();
        this.N = new TransformerHorizontalBarChart(this.f5281p);
        this.O = new TransformerHorizontalBarChart(this.f5281p);
        this.f5279n = new HorizontalBarChartRenderer(this, this.f5282q, this.f5281p);
        setHighlighter(new HorizontalBarHighlighter(this));
        this.L = new YAxisRendererHorizontalBarChart(this.f5281p, this.J, this.N);
        this.M = new YAxisRendererHorizontalBarChart(this.f5281p, this.K, this.O);
        this.P = new XAxisRendererHorizontalBarChart(this.f5281p, this.f5272g, this.N, this);
    }

    @Override // com.github.mikephil.charting.charts.BarChart
    public void getBarBounds(BarEntry barEntry, RectF rectF) {
        IBarDataSet iBarDataSet = (IBarDataSet) ((BarData) this.f5267b).getDataSetForEntry(barEntry);
        if (iBarDataSet == null) {
            rectF.set(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE);
            return;
        }
        float y = barEntry.getY();
        float x = barEntry.getX();
        float barWidth = ((BarData) this.f5267b).getBarWidth() / 2.0f;
        float f2 = x - barWidth;
        float f3 = x + barWidth;
        float f4 = y >= 0.0f ? y : 0.0f;
        if (y > 0.0f) {
            y = 0.0f;
        }
        rectF.set(f4, f2, y, f3);
        getTransformer(iBarDataSet.getAxisDependency()).rectValueToPixel(rectF);
    }

    @Override // com.github.mikephil.charting.charts.BarLineChartBase, com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider
    public float getHighestVisibleX() {
        getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(this.f5281p.contentLeft(), this.f5281p.contentTop(), this.U);
        return (float) Math.min(this.f5272g.mAxisMaximum, this.U.y);
    }

    @Override // com.github.mikephil.charting.charts.BarChart, com.github.mikephil.charting.charts.Chart
    public Highlight getHighlightByTouchPoint(float f2, float f3) {
        if (this.f5267b == null) {
            if (this.f5266a) {
                Log.e(Chart.LOG_TAG, "Can't select by touch. No data set.");
                return null;
            }
            return null;
        }
        return getHighlighter().getHighlight(f3, f2);
    }

    @Override // com.github.mikephil.charting.charts.BarLineChartBase, com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider
    public float getLowestVisibleX() {
        getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(this.f5281p.contentLeft(), this.f5281p.contentBottom(), this.T);
        return (float) Math.max(this.f5272g.mAxisMinimum, this.T.y);
    }

    @Override // com.github.mikephil.charting.charts.BarLineChartBase
    public MPPointF getPosition(Entry entry, YAxis.AxisDependency axisDependency) {
        if (entry == null) {
            return null;
        }
        float[] fArr = this.a0;
        fArr[0] = entry.getY();
        fArr[1] = entry.getX();
        getTransformer(axisDependency).pointValuesToPixel(fArr);
        return MPPointF.getInstance(fArr[0], fArr[1]);
    }

    @Override // com.github.mikephil.charting.charts.BarLineChartBase
    protected void l() {
        Transformer transformer = this.O;
        YAxis yAxis = this.K;
        float f2 = yAxis.mAxisMinimum;
        float f3 = yAxis.mAxisRange;
        XAxis xAxis = this.f5272g;
        transformer.prepareMatrixValuePx(f2, f3, xAxis.mAxisRange, xAxis.mAxisMinimum);
        Transformer transformer2 = this.N;
        YAxis yAxis2 = this.J;
        float f4 = yAxis2.mAxisMinimum;
        float f5 = yAxis2.mAxisRange;
        XAxis xAxis2 = this.f5272g;
        transformer2.prepareMatrixValuePx(f4, f5, xAxis2.mAxisRange, xAxis2.mAxisMinimum);
    }

    @Override // com.github.mikephil.charting.charts.BarLineChartBase
    public void setVisibleXRange(float f2, float f3) {
        float f4 = this.f5272g.mAxisRange;
        this.f5281p.setMinMaxScaleY(f4 / f2, f4 / f3);
    }

    @Override // com.github.mikephil.charting.charts.BarLineChartBase
    public void setVisibleXRangeMaximum(float f2) {
        this.f5281p.setMinimumScaleY(this.f5272g.mAxisRange / f2);
    }

    @Override // com.github.mikephil.charting.charts.BarLineChartBase
    public void setVisibleXRangeMinimum(float f2) {
        this.f5281p.setMaximumScaleY(this.f5272g.mAxisRange / f2);
    }

    @Override // com.github.mikephil.charting.charts.BarLineChartBase
    public void setVisibleYRange(float f2, float f3, YAxis.AxisDependency axisDependency) {
        this.f5281p.setMinMaxScaleX(j(axisDependency) / f2, j(axisDependency) / f3);
    }

    @Override // com.github.mikephil.charting.charts.BarLineChartBase
    public void setVisibleYRangeMaximum(float f2, YAxis.AxisDependency axisDependency) {
        this.f5281p.setMinimumScaleX(j(axisDependency) / f2);
    }

    @Override // com.github.mikephil.charting.charts.BarLineChartBase
    public void setVisibleYRangeMinimum(float f2, YAxis.AxisDependency axisDependency) {
        this.f5281p.setMaximumScaleX(j(axisDependency) / f2);
    }
}
