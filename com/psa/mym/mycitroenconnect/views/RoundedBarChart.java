package com.psa.mym.mycitroenconnect.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.Range;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.renderer.BarChartRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public class RoundedBarChart extends BarChart {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class RoundedBarChartRenderer extends BarChartRenderer {
        private final RectF mBarShadowRectBuffer;
        private final int mRadius;

        RoundedBarChartRenderer(BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler, int i2) {
            super(barDataProvider, chartAnimator, viewPortHandler);
            this.mBarShadowRectBuffer = new RectF();
            this.mRadius = i2;
        }

        @Override // com.github.mikephil.charting.renderer.BarChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
        public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
            float y;
            float f2;
            float f3;
            float f4;
            BarData barData = this.f5387g.getBarData();
            for (Highlight highlight : highlightArr) {
                IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(highlight.getDataSetIndex());
                if (iBarDataSet != null && iBarDataSet.isHighlightEnabled()) {
                    BarEntry barEntry = (BarEntry) iBarDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                    if (c(barEntry, iBarDataSet)) {
                        Transformer transformer = this.f5387g.getTransformer(iBarDataSet.getAxisDependency());
                        this.f5402d.setColor(iBarDataSet.getHighLightColor());
                        this.f5402d.setAlpha(iBarDataSet.getHighLightAlpha());
                        if (!(highlight.getStackIndex() >= 0 && barEntry.isStacked())) {
                            y = barEntry.getY();
                            f2 = 0.0f;
                        } else if (this.f5387g.isHighlightFullBarEnabled()) {
                            y = barEntry.getPositiveSum();
                            f2 = -barEntry.getNegativeSum();
                        } else {
                            Range range = barEntry.getRanges()[highlight.getStackIndex()];
                            f4 = range.from;
                            f3 = range.to;
                            f(barEntry.getX(), f4, f3, barData.getBarWidth() / 2.0f, transformer);
                            g(highlight, this.f5388h);
                            RectF rectF = this.f5388h;
                            int i2 = this.mRadius;
                            canvas.drawRoundRect(rectF, i2, i2, this.f5402d);
                        }
                        f3 = f2;
                        f4 = y;
                        f(barEntry.getX(), f4, f3, barData.getBarWidth() / 2.0f, transformer);
                        g(highlight, this.f5388h);
                        RectF rectF2 = this.f5388h;
                        int i22 = this.mRadius;
                        canvas.drawRoundRect(rectF2, i22, i22, this.f5402d);
                    }
                }
            }
        }

        @Override // com.github.mikephil.charting.renderer.BarChartRenderer
        protected void e(Canvas canvas, IBarDataSet iBarDataSet, int i2) {
            Transformer transformer = this.f5387g.getTransformer(iBarDataSet.getAxisDependency());
            this.f5391k.setColor(iBarDataSet.getBarBorderColor());
            this.f5391k.setStrokeWidth(Utils.convertDpToPixel(iBarDataSet.getBarBorderWidth()));
            boolean z = iBarDataSet.getBarBorderWidth() > 0.0f;
            float phaseX = this.f5400b.getPhaseX();
            float phaseY = this.f5400b.getPhaseY();
            if (this.f5387g.isDrawBarShadowEnabled()) {
                this.f5390j.setColor(iBarDataSet.getBarShadowColor());
                float barWidth = this.f5387g.getBarData().getBarWidth() / 2.0f;
                int min = Math.min((int) Math.ceil(iBarDataSet.getEntryCount() * phaseX), iBarDataSet.getEntryCount());
                for (int i3 = 0; i3 < min; i3++) {
                    float x = ((BarEntry) iBarDataSet.getEntryForIndex(i3)).getX();
                    RectF rectF = this.mBarShadowRectBuffer;
                    rectF.left = x - barWidth;
                    rectF.right = x + barWidth;
                    transformer.rectValueToPixel(rectF);
                    if (this.f5436a.isInBoundsLeft(this.mBarShadowRectBuffer.right)) {
                        if (!this.f5436a.isInBoundsRight(this.mBarShadowRectBuffer.left)) {
                            break;
                        }
                        this.mBarShadowRectBuffer.top = this.f5436a.contentTop();
                        this.mBarShadowRectBuffer.bottom = this.f5436a.contentBottom();
                        RectF rectF2 = this.mBarShadowRectBuffer;
                        int i4 = this.mRadius;
                        canvas.drawRoundRect(rectF2, i4, i4, this.f5390j);
                    }
                }
            }
            BarBuffer barBuffer = this.f5389i[i2];
            barBuffer.setPhases(phaseX, phaseY);
            barBuffer.setDataSet(i2);
            barBuffer.setInverted(this.f5387g.isInverted(iBarDataSet.getAxisDependency()));
            barBuffer.setBarWidth(this.f5387g.getBarData().getBarWidth());
            barBuffer.feed(iBarDataSet);
            transformer.pointValuesToPixel(barBuffer.buffer);
            boolean z2 = iBarDataSet.getColors().size() == 1;
            if (z2) {
                this.f5401c.setColor(iBarDataSet.getColor());
            }
            for (int i5 = 0; i5 < barBuffer.size(); i5 += 4) {
                int i6 = i5 + 2;
                if (this.f5436a.isInBoundsLeft(barBuffer.buffer[i6])) {
                    if (!this.f5436a.isInBoundsRight(barBuffer.buffer[i5])) {
                        return;
                    }
                    if (!z2) {
                        this.f5401c.setColor(iBarDataSet.getColor(i5 / 4));
                    }
                    if (iBarDataSet.getGradientColor() != null) {
                        GradientColor gradientColor = iBarDataSet.getGradientColor();
                        Paint paint = this.f5401c;
                        float[] fArr = barBuffer.buffer;
                        paint.setShader(new LinearGradient(fArr[i5], fArr[i5 + 3], fArr[i5], fArr[i5 + 1], gradientColor.getStartColor(), gradientColor.getEndColor(), Shader.TileMode.MIRROR));
                    }
                    if (iBarDataSet.getGradientColors() != null) {
                        Paint paint2 = this.f5401c;
                        float[] fArr2 = barBuffer.buffer;
                        float f2 = fArr2[i5];
                        float f3 = fArr2[i5 + 3];
                        float f4 = fArr2[i5];
                        float f5 = fArr2[i5 + 1];
                        int i7 = i5 / 4;
                        paint2.setShader(new LinearGradient(f2, f3, f4, f5, iBarDataSet.getGradientColor(i7).getStartColor(), iBarDataSet.getGradientColor(i7).getEndColor(), Shader.TileMode.MIRROR));
                    }
                    float[] fArr3 = barBuffer.buffer;
                    float f6 = fArr3[i5];
                    int i8 = i5 + 1;
                    float f7 = fArr3[i8];
                    float f8 = fArr3[i6];
                    int i9 = i5 + 3;
                    float f9 = fArr3[i9];
                    int i10 = this.mRadius;
                    canvas.drawRoundRect(f6, f7, f8, f9, i10, i10, this.f5401c);
                    if (z) {
                        float[] fArr4 = barBuffer.buffer;
                        float f10 = fArr4[i5];
                        float f11 = fArr4[i8];
                        float f12 = fArr4[i6];
                        float f13 = fArr4[i9];
                        int i11 = this.mRadius;
                        canvas.drawRoundRect(f10, f11, f12, f13, i11, i11, this.f5391k);
                    }
                }
            }
        }
    }

    public RoundedBarChart(Context context) {
        super(context);
    }

    public RoundedBarChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        readRadiusAttr(context, attributeSet);
    }

    public RoundedBarChart(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        readRadiusAttr(context, attributeSet);
    }

    private void readRadiusAttr(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.RoundedBarChart, 0, 0);
        try {
            setRadius(obtainStyledAttributes.getDimensionPixelSize(0, 0));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void setRadius(int i2) {
        setRenderer(new RoundedBarChartRenderer(this, getAnimator(), getViewPortHandler(), i2));
    }
}
