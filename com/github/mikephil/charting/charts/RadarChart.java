package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.highlight.RadarHighlighter;
import com.github.mikephil.charting.renderer.RadarChartRenderer;
import com.github.mikephil.charting.renderer.XAxisRendererRadarChart;
import com.github.mikephil.charting.renderer.YAxisRendererRadarChart;
import com.github.mikephil.charting.utils.Utils;
import org.bouncycastle.tls.CipherSuite;
/* loaded from: classes.dex */
public class RadarChart extends PieRadarChartBase<RadarData> {
    private boolean mDrawWeb;
    private float mInnerWebLineWidth;
    private int mSkipWebLineCount;
    private int mWebAlpha;
    private int mWebColor;
    private int mWebColorInner;
    private float mWebLineWidth;
    private YAxis mYAxis;
    protected YAxisRendererRadarChart y;
    protected XAxisRendererRadarChart z;

    public RadarChart(Context context) {
        super(context);
        this.mWebLineWidth = 2.5f;
        this.mInnerWebLineWidth = 1.5f;
        this.mWebColor = Color.rgb(122, 122, 122);
        this.mWebColorInner = Color.rgb(122, 122, 122);
        this.mWebAlpha = CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA;
        this.mDrawWeb = true;
        this.mSkipWebLineCount = 0;
    }

    public RadarChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mWebLineWidth = 2.5f;
        this.mInnerWebLineWidth = 1.5f;
        this.mWebColor = Color.rgb(122, 122, 122);
        this.mWebColorInner = Color.rgb(122, 122, 122);
        this.mWebAlpha = CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA;
        this.mDrawWeb = true;
        this.mSkipWebLineCount = 0;
    }

    public RadarChart(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mWebLineWidth = 2.5f;
        this.mInnerWebLineWidth = 1.5f;
        this.mWebColor = Color.rgb(122, 122, 122);
        this.mWebColorInner = Color.rgb(122, 122, 122);
        this.mWebAlpha = CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA;
        this.mDrawWeb = true;
        this.mSkipWebLineCount = 0;
    }

    @Override // com.github.mikephil.charting.charts.PieRadarChartBase, com.github.mikephil.charting.charts.Chart
    protected void d() {
        super.d();
        this.mYAxis = new YAxis(YAxis.AxisDependency.LEFT);
        this.mWebLineWidth = Utils.convertDpToPixel(1.5f);
        this.mInnerWebLineWidth = Utils.convertDpToPixel(0.75f);
        this.f5279n = new RadarChartRenderer(this, this.f5282q, this.f5281p);
        this.y = new YAxisRendererRadarChart(this.f5281p, this.mYAxis, this);
        this.z = new XAxisRendererRadarChart(this.f5281p, this.f5272g, this);
        this.f5280o = new RadarHighlighter(this);
    }

    @Override // com.github.mikephil.charting.charts.PieRadarChartBase
    protected void f() {
        super.f();
        YAxis yAxis = this.mYAxis;
        YAxis.AxisDependency axisDependency = YAxis.AxisDependency.LEFT;
        yAxis.calculate(((RadarData) this.f5267b).getYMin(axisDependency), ((RadarData) this.f5267b).getYMax(axisDependency));
        this.f5272g.calculate(0.0f, ((RadarData) this.f5267b).getMaxEntryCountSet().getEntryCount());
    }

    public float getFactor() {
        RectF contentRect = this.f5281p.getContentRect();
        return Math.min(contentRect.width() / 2.0f, contentRect.height() / 2.0f) / this.mYAxis.mAxisRange;
    }

    @Override // com.github.mikephil.charting.charts.PieRadarChartBase
    public int getIndexForAngle(float f2) {
        float normalizedAngle = Utils.getNormalizedAngle(f2 - getRotationAngle());
        float sliceAngle = getSliceAngle();
        int entryCount = ((RadarData) this.f5267b).getMaxEntryCountSet().getEntryCount();
        int i2 = 0;
        while (i2 < entryCount) {
            int i3 = i2 + 1;
            if ((i3 * sliceAngle) - (sliceAngle / 2.0f) > normalizedAngle) {
                return i2;
            }
            i2 = i3;
        }
        return 0;
    }

    @Override // com.github.mikephil.charting.charts.PieRadarChartBase
    public float getRadius() {
        RectF contentRect = this.f5281p.getContentRect();
        return Math.min(contentRect.width() / 2.0f, contentRect.height() / 2.0f);
    }

    @Override // com.github.mikephil.charting.charts.PieRadarChartBase
    protected float getRequiredBaseOffset() {
        return (this.f5272g.isEnabled() && this.f5272g.isDrawLabelsEnabled()) ? this.f5272g.mLabelRotatedWidth : Utils.convertDpToPixel(10.0f);
    }

    @Override // com.github.mikephil.charting.charts.PieRadarChartBase
    protected float getRequiredLegendOffset() {
        return this.f5278m.getLabelPaint().getTextSize() * 4.0f;
    }

    public int getSkipWebLineCount() {
        return this.mSkipWebLineCount;
    }

    public float getSliceAngle() {
        return 360.0f / ((RadarData) this.f5267b).getMaxEntryCountSet().getEntryCount();
    }

    public int getWebAlpha() {
        return this.mWebAlpha;
    }

    public int getWebColor() {
        return this.mWebColor;
    }

    public int getWebColorInner() {
        return this.mWebColorInner;
    }

    public float getWebLineWidth() {
        return this.mWebLineWidth;
    }

    public float getWebLineWidthInner() {
        return this.mInnerWebLineWidth;
    }

    public YAxis getYAxis() {
        return this.mYAxis;
    }

    @Override // com.github.mikephil.charting.charts.PieRadarChartBase, com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public float getYChartMax() {
        return this.mYAxis.mAxisMaximum;
    }

    @Override // com.github.mikephil.charting.charts.PieRadarChartBase, com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public float getYChartMin() {
        return this.mYAxis.mAxisMinimum;
    }

    public float getYRange() {
        return this.mYAxis.mAxisRange;
    }

    @Override // com.github.mikephil.charting.charts.PieRadarChartBase, com.github.mikephil.charting.charts.Chart
    public void notifyDataSetChanged() {
        if (this.f5267b == null) {
            return;
        }
        f();
        YAxisRendererRadarChart yAxisRendererRadarChart = this.y;
        YAxis yAxis = this.mYAxis;
        yAxisRendererRadarChart.computeAxis(yAxis.mAxisMinimum, yAxis.mAxisMaximum, yAxis.isInverted());
        XAxisRendererRadarChart xAxisRendererRadarChart = this.z;
        XAxis xAxis = this.f5272g;
        xAxisRendererRadarChart.computeAxis(xAxis.mAxisMinimum, xAxis.mAxisMaximum, false);
        Legend legend = this.f5275j;
        if (legend != null && !legend.isLegendCustom()) {
            this.f5278m.computeLegend(this.f5267b);
        }
        calculateOffsets();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.charts.Chart, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f5267b == null) {
            return;
        }
        if (this.f5272g.isEnabled()) {
            XAxisRendererRadarChart xAxisRendererRadarChart = this.z;
            XAxis xAxis = this.f5272g;
            xAxisRendererRadarChart.computeAxis(xAxis.mAxisMinimum, xAxis.mAxisMaximum, false);
        }
        this.z.renderAxisLabels(canvas);
        if (this.mDrawWeb) {
            this.f5279n.drawExtras(canvas);
        }
        if (this.mYAxis.isEnabled() && this.mYAxis.isDrawLimitLinesBehindDataEnabled()) {
            this.y.renderLimitLines(canvas);
        }
        this.f5279n.drawData(canvas);
        if (valuesToHighlight()) {
            this.f5279n.drawHighlighted(canvas, this.f5283r);
        }
        if (this.mYAxis.isEnabled() && !this.mYAxis.isDrawLimitLinesBehindDataEnabled()) {
            this.y.renderLimitLines(canvas);
        }
        this.y.renderAxisLabels(canvas);
        this.f5279n.drawValues(canvas);
        this.f5278m.renderLegend(canvas);
        a(canvas);
        b(canvas);
    }

    public void setDrawWeb(boolean z) {
        this.mDrawWeb = z;
    }

    public void setSkipWebLineCount(int i2) {
        this.mSkipWebLineCount = Math.max(0, i2);
    }

    public void setWebAlpha(int i2) {
        this.mWebAlpha = i2;
    }

    public void setWebColor(int i2) {
        this.mWebColor = i2;
    }

    public void setWebColorInner(int i2) {
        this.mWebColorInner = i2;
    }

    public void setWebLineWidth(float f2) {
        this.mWebLineWidth = Utils.convertDpToPixel(f2);
    }

    public void setWebLineWidthInner(float f2) {
        this.mInnerWebLineWidth = Utils.convertDpToPixel(f2);
    }
}
