package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.buffer.HorizontalBarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;
/* loaded from: classes.dex */
public class HorizontalBarChartRenderer extends BarChartRenderer {
    private RectF mBarShadowRectBuffer;

    public HorizontalBarChartRenderer(BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(barDataProvider, chartAnimator, viewPortHandler);
        this.mBarShadowRectBuffer = new RectF();
        this.f5403e.setTextAlign(Paint.Align.LEFT);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public boolean b(ChartInterface chartInterface) {
        return ((float) chartInterface.getData().getEntryCount()) < ((float) chartInterface.getMaxVisibleCount()) * this.f5436a.getScaleY();
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        List list;
        MPPointF mPPointF;
        int i2;
        float[] fArr;
        float f2;
        int i3;
        float[] fArr2;
        float f3;
        float f4;
        BarEntry barEntry;
        float f5;
        int i4;
        List list2;
        boolean z;
        MPPointF mPPointF2;
        IValueFormatter iValueFormatter;
        float f6;
        BarBuffer barBuffer;
        if (b(this.f5387g)) {
            List dataSets = this.f5387g.getBarData().getDataSets();
            float convertDpToPixel = Utils.convertDpToPixel(5.0f);
            boolean isDrawValueAboveBarEnabled = this.f5387g.isDrawValueAboveBarEnabled();
            int i5 = 0;
            while (i5 < this.f5387g.getBarData().getDataSetCount()) {
                IBarDataSet iBarDataSet = (IBarDataSet) dataSets.get(i5);
                if (d(iBarDataSet)) {
                    boolean isInverted = this.f5387g.isInverted(iBarDataSet.getAxisDependency());
                    a(iBarDataSet);
                    float f7 = 2.0f;
                    float calcTextHeight = Utils.calcTextHeight(this.f5403e, "10") / 2.0f;
                    IValueFormatter valueFormatter = iBarDataSet.getValueFormatter();
                    BarBuffer barBuffer2 = this.f5389i[i5];
                    float phaseY = this.f5400b.getPhaseY();
                    MPPointF mPPointF3 = MPPointF.getInstance(iBarDataSet.getIconsOffset());
                    mPPointF3.x = Utils.convertDpToPixel(mPPointF3.x);
                    mPPointF3.y = Utils.convertDpToPixel(mPPointF3.y);
                    if (iBarDataSet.isStacked()) {
                        list = dataSets;
                        mPPointF = mPPointF3;
                        Transformer transformer = this.f5387g.getTransformer(iBarDataSet.getAxisDependency());
                        int i6 = 0;
                        int i7 = 0;
                        while (i6 < iBarDataSet.getEntryCount() * this.f5400b.getPhaseX()) {
                            BarEntry barEntry2 = (BarEntry) iBarDataSet.getEntryForIndex(i6);
                            int valueTextColor = iBarDataSet.getValueTextColor(i6);
                            float[] yVals = barEntry2.getYVals();
                            if (yVals == null) {
                                int i8 = i7 + 1;
                                if (!this.f5436a.isInBoundsTop(barBuffer2.buffer[i8])) {
                                    break;
                                } else if (this.f5436a.isInBoundsX(barBuffer2.buffer[i7]) && this.f5436a.isInBoundsBottom(barBuffer2.buffer[i8])) {
                                    String formattedValue = valueFormatter.getFormattedValue(barEntry2.getY(), barEntry2, i5, this.f5436a);
                                    float calcTextWidth = Utils.calcTextWidth(this.f5403e, formattedValue);
                                    float f8 = isDrawValueAboveBarEnabled ? convertDpToPixel : -(calcTextWidth + convertDpToPixel);
                                    float f9 = isDrawValueAboveBarEnabled ? -(calcTextWidth + convertDpToPixel) : convertDpToPixel;
                                    if (isInverted) {
                                        f8 = (-f8) - calcTextWidth;
                                        f9 = (-f9) - calcTextWidth;
                                    }
                                    float f10 = f8;
                                    float f11 = f9;
                                    if (iBarDataSet.isDrawValuesEnabled()) {
                                        i2 = i6;
                                        fArr = yVals;
                                        barEntry = barEntry2;
                                        h(canvas, formattedValue, barBuffer2.buffer[i7 + 2] + (barEntry2.getY() >= 0.0f ? f10 : f11), barBuffer2.buffer[i8] + calcTextHeight, valueTextColor);
                                    } else {
                                        barEntry = barEntry2;
                                        i2 = i6;
                                        fArr = yVals;
                                    }
                                    if (barEntry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                        Drawable icon = barEntry.getIcon();
                                        float f12 = barBuffer2.buffer[i7 + 2];
                                        if (barEntry.getY() < 0.0f) {
                                            f10 = f11;
                                        }
                                        Utils.drawImage(canvas, icon, (int) (f12 + f10 + mPPointF.x), (int) (barBuffer2.buffer[i8] + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                    }
                                }
                            } else {
                                i2 = i6;
                                fArr = yVals;
                                int length = fArr.length * 2;
                                float[] fArr3 = new float[length];
                                float f13 = -barEntry2.getNegativeSum();
                                float f14 = 0.0f;
                                int i9 = 0;
                                int i10 = 0;
                                while (i9 < length) {
                                    float f15 = fArr[i10];
                                    int i11 = (f15 > 0.0f ? 1 : (f15 == 0.0f ? 0 : -1));
                                    if (i11 == 0 && (f14 == 0.0f || f13 == 0.0f)) {
                                        float f16 = f13;
                                        f13 = f15;
                                        f4 = f16;
                                    } else if (i11 >= 0) {
                                        f14 += f15;
                                        f4 = f13;
                                        f13 = f14;
                                    } else {
                                        f4 = f13 - f15;
                                    }
                                    fArr3[i9] = f13 * phaseY;
                                    i9 += 2;
                                    i10++;
                                    f13 = f4;
                                }
                                transformer.pointValuesToPixel(fArr3);
                                int i12 = 0;
                                while (i12 < length) {
                                    float f17 = fArr[i12 / 2];
                                    String formattedValue2 = valueFormatter.getFormattedValue(f17, barEntry2, i5, this.f5436a);
                                    float calcTextWidth2 = Utils.calcTextWidth(this.f5403e, formattedValue2);
                                    float f18 = isDrawValueAboveBarEnabled ? convertDpToPixel : -(calcTextWidth2 + convertDpToPixel);
                                    int i13 = length;
                                    float f19 = isDrawValueAboveBarEnabled ? -(calcTextWidth2 + convertDpToPixel) : convertDpToPixel;
                                    if (isInverted) {
                                        f18 = (-f18) - calcTextWidth2;
                                        f19 = (-f19) - calcTextWidth2;
                                    }
                                    boolean z2 = (f17 == 0.0f && f13 == 0.0f && f14 > 0.0f) || f17 < 0.0f;
                                    float f20 = fArr3[i12];
                                    if (z2) {
                                        f18 = f19;
                                    }
                                    float f21 = f20 + f18;
                                    float[] fArr4 = barBuffer2.buffer;
                                    float f22 = (fArr4[i7 + 1] + fArr4[i7 + 3]) / 2.0f;
                                    if (!this.f5436a.isInBoundsTop(f22)) {
                                        break;
                                    }
                                    if (this.f5436a.isInBoundsX(f21) && this.f5436a.isInBoundsBottom(f22)) {
                                        if (iBarDataSet.isDrawValuesEnabled()) {
                                            f2 = f22;
                                            i3 = i12;
                                            fArr2 = fArr3;
                                            f3 = f21;
                                            h(canvas, formattedValue2, f21, f22 + calcTextHeight, valueTextColor);
                                        } else {
                                            f2 = f22;
                                            i3 = i12;
                                            fArr2 = fArr3;
                                            f3 = f21;
                                        }
                                        if (barEntry2.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                            Drawable icon2 = barEntry2.getIcon();
                                            Utils.drawImage(canvas, icon2, (int) (f3 + mPPointF.x), (int) (f2 + mPPointF.y), icon2.getIntrinsicWidth(), icon2.getIntrinsicHeight());
                                        }
                                    } else {
                                        i3 = i12;
                                        fArr2 = fArr3;
                                    }
                                    i12 = i3 + 2;
                                    length = i13;
                                    fArr3 = fArr2;
                                }
                            }
                            i7 = fArr == null ? i7 + 4 : i7 + (fArr.length * 4);
                            i6 = i2 + 1;
                        }
                    } else {
                        int i14 = 0;
                        while (i14 < barBuffer2.buffer.length * this.f5400b.getPhaseX()) {
                            float[] fArr5 = barBuffer2.buffer;
                            int i15 = i14 + 1;
                            float f23 = (fArr5[i15] + fArr5[i14 + 3]) / f7;
                            if (!this.f5436a.isInBoundsTop(fArr5[i15])) {
                                break;
                            }
                            if (this.f5436a.isInBoundsX(barBuffer2.buffer[i14]) && this.f5436a.isInBoundsBottom(barBuffer2.buffer[i15])) {
                                BarEntry barEntry3 = (BarEntry) iBarDataSet.getEntryForIndex(i14 / 4);
                                float y = barEntry3.getY();
                                String formattedValue3 = valueFormatter.getFormattedValue(y, barEntry3, i5, this.f5436a);
                                MPPointF mPPointF4 = mPPointF3;
                                float calcTextWidth3 = Utils.calcTextWidth(this.f5403e, formattedValue3);
                                float f24 = isDrawValueAboveBarEnabled ? convertDpToPixel : -(calcTextWidth3 + convertDpToPixel);
                                IValueFormatter iValueFormatter2 = valueFormatter;
                                float f25 = isDrawValueAboveBarEnabled ? -(calcTextWidth3 + convertDpToPixel) : convertDpToPixel;
                                if (isInverted) {
                                    f24 = (-f24) - calcTextWidth3;
                                    f25 = (-f25) - calcTextWidth3;
                                }
                                float f26 = f24;
                                float f27 = f25;
                                if (iBarDataSet.isDrawValuesEnabled()) {
                                    f5 = y;
                                    i4 = i14;
                                    list2 = dataSets;
                                    mPPointF2 = mPPointF4;
                                    f6 = calcTextHeight;
                                    barBuffer = barBuffer2;
                                    z = isInverted;
                                    iValueFormatter = iValueFormatter2;
                                    h(canvas, formattedValue3, (y >= 0.0f ? f26 : f27) + barBuffer2.buffer[i14 + 2], f23 + calcTextHeight, iBarDataSet.getValueTextColor(i14 / 2));
                                } else {
                                    f5 = y;
                                    i4 = i14;
                                    list2 = dataSets;
                                    z = isInverted;
                                    mPPointF2 = mPPointF4;
                                    iValueFormatter = iValueFormatter2;
                                    f6 = calcTextHeight;
                                    barBuffer = barBuffer2;
                                }
                                if (barEntry3.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                    Drawable icon3 = barEntry3.getIcon();
                                    float f28 = barBuffer.buffer[i4 + 2];
                                    if (f5 < 0.0f) {
                                        f26 = f27;
                                    }
                                    Utils.drawImage(canvas, icon3, (int) (f28 + f26 + mPPointF2.x), (int) (f23 + mPPointF2.y), icon3.getIntrinsicWidth(), icon3.getIntrinsicHeight());
                                }
                            } else {
                                i4 = i14;
                                list2 = dataSets;
                                z = isInverted;
                                f6 = calcTextHeight;
                                mPPointF2 = mPPointF3;
                                barBuffer = barBuffer2;
                                iValueFormatter = valueFormatter;
                            }
                            i14 = i4 + 4;
                            mPPointF3 = mPPointF2;
                            valueFormatter = iValueFormatter;
                            barBuffer2 = barBuffer;
                            calcTextHeight = f6;
                            dataSets = list2;
                            isInverted = z;
                            f7 = 2.0f;
                        }
                        list = dataSets;
                        mPPointF = mPPointF3;
                    }
                    MPPointF.recycleInstance(mPPointF);
                } else {
                    list = dataSets;
                }
                i5++;
                dataSets = list;
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
                rectF.top = x - barWidth;
                rectF.bottom = x + barWidth;
                transformer.rectValueToPixel(rectF);
                if (this.f5436a.isInBoundsTop(this.mBarShadowRectBuffer.bottom)) {
                    if (!this.f5436a.isInBoundsBottom(this.mBarShadowRectBuffer.top)) {
                        break;
                    }
                    this.mBarShadowRectBuffer.left = this.f5436a.contentLeft();
                    this.mBarShadowRectBuffer.right = this.f5436a.contentRight();
                    canvas.drawRect(this.mBarShadowRectBuffer, this.f5390j);
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
        for (int i4 = 0; i4 < barBuffer.size(); i4 += 4) {
            int i5 = i4 + 3;
            if (!this.f5436a.isInBoundsTop(barBuffer.buffer[i5])) {
                return;
            }
            int i6 = i4 + 1;
            if (this.f5436a.isInBoundsBottom(barBuffer.buffer[i6])) {
                if (!z2) {
                    this.f5401c.setColor(iBarDataSet.getColor(i4 / 4));
                }
                float[] fArr = barBuffer.buffer;
                int i7 = i4 + 2;
                canvas.drawRect(fArr[i4], fArr[i6], fArr[i7], fArr[i5], this.f5401c);
                if (z) {
                    float[] fArr2 = barBuffer.buffer;
                    canvas.drawRect(fArr2[i4], fArr2[i6], fArr2[i7], fArr2[i5], this.f5391k);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    public void f(float f2, float f3, float f4, float f5, Transformer transformer) {
        this.f5388h.set(f3, f2 - f5, f4, f2 + f5);
        transformer.rectToPixelPhaseHorizontal(this.f5388h, this.f5400b.getPhaseY());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    public void g(Highlight highlight, RectF rectF) {
        highlight.setDraw(rectF.centerY(), rectF.right);
    }

    protected void h(Canvas canvas, String str, float f2, float f3, int i2) {
        this.f5403e.setColor(i2);
        canvas.drawText(str, f2, f3, this.f5403e);
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
        BarData barData = this.f5387g.getBarData();
        this.f5389i = new HorizontalBarBuffer[barData.getDataSetCount()];
        for (int i2 = 0; i2 < this.f5389i.length; i2++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i2);
            this.f5389i[i2] = new HorizontalBarBuffer(iBarDataSet.getEntryCount() * 4 * (iBarDataSet.isStacked() ? iBarDataSet.getStackSize() : 1), barData.getDataSetCount(), iBarDataSet.isStacked());
        }
    }
}
