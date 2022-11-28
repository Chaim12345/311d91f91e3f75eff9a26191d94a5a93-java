package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.Range;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;
/* loaded from: classes.dex */
public class BarChartRenderer extends BarLineScatterCandleBubbleRenderer {

    /* renamed from: g  reason: collision with root package name */
    protected BarDataProvider f5387g;

    /* renamed from: h  reason: collision with root package name */
    protected RectF f5388h;

    /* renamed from: i  reason: collision with root package name */
    protected BarBuffer[] f5389i;

    /* renamed from: j  reason: collision with root package name */
    protected Paint f5390j;

    /* renamed from: k  reason: collision with root package name */
    protected Paint f5391k;
    private RectF mBarShadowRectBuffer;

    public BarChartRenderer(BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.f5388h = new RectF();
        this.mBarShadowRectBuffer = new RectF();
        this.f5387g = barDataProvider;
        Paint paint = new Paint(1);
        this.f5402d = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f5402d.setColor(Color.rgb(0, 0, 0));
        this.f5402d.setAlpha(120);
        Paint paint2 = new Paint(1);
        this.f5390j = paint2;
        paint2.setStyle(Paint.Style.FILL);
        Paint paint3 = new Paint(1);
        this.f5391k = paint3;
        paint3.setStyle(Paint.Style.STROKE);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        BarData barData = this.f5387g.getBarData();
        for (int i2 = 0; i2 < barData.getDataSetCount(); i2++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i2);
            if (iBarDataSet.isVisible()) {
                e(canvas, iBarDataSet, i2);
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
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
                        canvas.drawRect(this.f5388h, this.f5402d);
                    }
                    f3 = f2;
                    f4 = y;
                    f(barEntry.getX(), f4, f3, barData.getBarWidth() / 2.0f, transformer);
                    g(highlight, this.f5388h);
                    canvas.drawRect(this.f5388h, this.f5402d);
                }
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        MPPointF mPPointF;
        List list;
        int i2;
        float f2;
        boolean z;
        float[] fArr;
        Transformer transformer;
        int i3;
        float[] fArr2;
        int i4;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        boolean z2;
        int i5;
        MPPointF mPPointF2;
        List list2;
        BarBuffer barBuffer;
        float f8;
        if (b(this.f5387g)) {
            List dataSets = this.f5387g.getBarData().getDataSets();
            float convertDpToPixel = Utils.convertDpToPixel(4.5f);
            boolean isDrawValueAboveBarEnabled = this.f5387g.isDrawValueAboveBarEnabled();
            int i6 = 0;
            while (i6 < this.f5387g.getBarData().getDataSetCount()) {
                IBarDataSet iBarDataSet = (IBarDataSet) dataSets.get(i6);
                if (d(iBarDataSet)) {
                    a(iBarDataSet);
                    boolean isInverted = this.f5387g.isInverted(iBarDataSet.getAxisDependency());
                    float calcTextHeight = Utils.calcTextHeight(this.f5403e, "8");
                    float f9 = isDrawValueAboveBarEnabled ? -convertDpToPixel : calcTextHeight + convertDpToPixel;
                    float f10 = isDrawValueAboveBarEnabled ? calcTextHeight + convertDpToPixel : -convertDpToPixel;
                    if (isInverted) {
                        f9 = (-f9) - calcTextHeight;
                        f10 = (-f10) - calcTextHeight;
                    }
                    float f11 = f9;
                    float f12 = f10;
                    BarBuffer barBuffer2 = this.f5389i[i6];
                    float phaseY = this.f5400b.getPhaseY();
                    MPPointF mPPointF3 = MPPointF.getInstance(iBarDataSet.getIconsOffset());
                    mPPointF3.x = Utils.convertDpToPixel(mPPointF3.x);
                    mPPointF3.y = Utils.convertDpToPixel(mPPointF3.y);
                    if (iBarDataSet.isStacked()) {
                        mPPointF = mPPointF3;
                        list = dataSets;
                        Transformer transformer2 = this.f5387g.getTransformer(iBarDataSet.getAxisDependency());
                        int i7 = 0;
                        int i8 = 0;
                        while (i7 < iBarDataSet.getEntryCount() * this.f5400b.getPhaseX()) {
                            BarEntry barEntry = (BarEntry) iBarDataSet.getEntryForIndex(i7);
                            float[] yVals = barEntry.getYVals();
                            float[] fArr3 = barBuffer2.buffer;
                            float f13 = (fArr3[i8] + fArr3[i8 + 2]) / 2.0f;
                            int valueTextColor = iBarDataSet.getValueTextColor(i7);
                            if (yVals != null) {
                                i2 = i7;
                                f2 = convertDpToPixel;
                                z = isDrawValueAboveBarEnabled;
                                fArr = yVals;
                                transformer = transformer2;
                                float f14 = f13;
                                int length = fArr.length * 2;
                                float[] fArr4 = new float[length];
                                float f15 = -barEntry.getNegativeSum();
                                float f16 = 0.0f;
                                int i9 = 0;
                                int i10 = 0;
                                while (i9 < length) {
                                    float f17 = fArr[i10];
                                    int i11 = (f17 > 0.0f ? 1 : (f17 == 0.0f ? 0 : -1));
                                    if (i11 == 0 && (f16 == 0.0f || f15 == 0.0f)) {
                                        float f18 = f15;
                                        f15 = f17;
                                        f5 = f18;
                                    } else if (i11 >= 0) {
                                        f16 += f17;
                                        f5 = f15;
                                        f15 = f16;
                                    } else {
                                        f5 = f15 - f17;
                                    }
                                    fArr4[i9 + 1] = f15 * phaseY;
                                    i9 += 2;
                                    i10++;
                                    f15 = f5;
                                }
                                transformer.pointValuesToPixel(fArr4);
                                int i12 = 0;
                                while (i12 < length) {
                                    int i13 = i12 / 2;
                                    float f19 = fArr[i13];
                                    float f20 = fArr4[i12 + 1] + (((f19 > 0.0f ? 1 : (f19 == 0.0f ? 0 : -1)) == 0 && (f15 > 0.0f ? 1 : (f15 == 0.0f ? 0 : -1)) == 0 && (f16 > 0.0f ? 1 : (f16 == 0.0f ? 0 : -1)) > 0) || (f19 > 0.0f ? 1 : (f19 == 0.0f ? 0 : -1)) < 0 ? f12 : f11);
                                    if (!this.f5436a.isInBoundsRight(f14)) {
                                        break;
                                    }
                                    if (this.f5436a.isInBoundsY(f20) && this.f5436a.isInBoundsLeft(f14)) {
                                        if (iBarDataSet.isDrawValuesEnabled()) {
                                            f4 = f20;
                                            i3 = i12;
                                            fArr2 = fArr4;
                                            i4 = length;
                                            f3 = f14;
                                            drawValue(canvas, iBarDataSet.getValueFormatter(), fArr[i13], barEntry, i6, f14, f4, valueTextColor);
                                        } else {
                                            f4 = f20;
                                            i3 = i12;
                                            fArr2 = fArr4;
                                            i4 = length;
                                            f3 = f14;
                                        }
                                        if (barEntry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                            Drawable icon = barEntry.getIcon();
                                            Utils.drawImage(canvas, icon, (int) (f3 + mPPointF.x), (int) (f4 + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                        }
                                    } else {
                                        i3 = i12;
                                        fArr2 = fArr4;
                                        i4 = length;
                                        f3 = f14;
                                    }
                                    i12 = i3 + 2;
                                    fArr4 = fArr2;
                                    length = i4;
                                    f14 = f3;
                                }
                            } else if (!this.f5436a.isInBoundsRight(f13)) {
                                break;
                            } else {
                                int i14 = i8 + 1;
                                if (this.f5436a.isInBoundsY(barBuffer2.buffer[i14]) && this.f5436a.isInBoundsLeft(f13)) {
                                    if (iBarDataSet.isDrawValuesEnabled()) {
                                        f6 = f13;
                                        f2 = convertDpToPixel;
                                        fArr = yVals;
                                        i2 = i7;
                                        z = isDrawValueAboveBarEnabled;
                                        transformer = transformer2;
                                        drawValue(canvas, iBarDataSet.getValueFormatter(), barEntry.getY(), barEntry, i6, f6, barBuffer2.buffer[i14] + (barEntry.getY() >= 0.0f ? f11 : f12), valueTextColor);
                                    } else {
                                        f6 = f13;
                                        i2 = i7;
                                        f2 = convertDpToPixel;
                                        z = isDrawValueAboveBarEnabled;
                                        fArr = yVals;
                                        transformer = transformer2;
                                    }
                                    if (barEntry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                        Drawable icon2 = barEntry.getIcon();
                                        Utils.drawImage(canvas, icon2, (int) (f6 + mPPointF.x), (int) (barBuffer2.buffer[i14] + (barEntry.getY() >= 0.0f ? f11 : f12) + mPPointF.y), icon2.getIntrinsicWidth(), icon2.getIntrinsicHeight());
                                    }
                                } else {
                                    transformer2 = transformer2;
                                    isDrawValueAboveBarEnabled = isDrawValueAboveBarEnabled;
                                    convertDpToPixel = convertDpToPixel;
                                    i7 = i7;
                                }
                            }
                            i8 = fArr == null ? i8 + 4 : i8 + (fArr.length * 4);
                            i7 = i2 + 1;
                            transformer2 = transformer;
                            isDrawValueAboveBarEnabled = z;
                            convertDpToPixel = f2;
                        }
                    } else {
                        int i15 = 0;
                        while (i15 < barBuffer2.buffer.length * this.f5400b.getPhaseX()) {
                            float[] fArr5 = barBuffer2.buffer;
                            float f21 = (fArr5[i15] + fArr5[i15 + 2]) / 2.0f;
                            if (!this.f5436a.isInBoundsRight(f21)) {
                                break;
                            }
                            int i16 = i15 + 1;
                            if (this.f5436a.isInBoundsY(barBuffer2.buffer[i16]) && this.f5436a.isInBoundsLeft(f21)) {
                                int i17 = i15 / 4;
                                Entry entry = (BarEntry) iBarDataSet.getEntryForIndex(i17);
                                float y = entry.getY();
                                if (iBarDataSet.isDrawValuesEnabled()) {
                                    f8 = f21;
                                    i5 = i15;
                                    mPPointF2 = mPPointF3;
                                    list2 = dataSets;
                                    barBuffer = barBuffer2;
                                    drawValue(canvas, iBarDataSet.getValueFormatter(), y, entry, i6, f8, y >= 0.0f ? barBuffer2.buffer[i16] + f11 : barBuffer2.buffer[i15 + 3] + f12, iBarDataSet.getValueTextColor(i17));
                                } else {
                                    f8 = f21;
                                    i5 = i15;
                                    mPPointF2 = mPPointF3;
                                    list2 = dataSets;
                                    barBuffer = barBuffer2;
                                }
                                if (entry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                    Drawable icon3 = entry.getIcon();
                                    Utils.drawImage(canvas, icon3, (int) (f8 + mPPointF2.x), (int) ((y >= 0.0f ? barBuffer.buffer[i16] + f11 : barBuffer.buffer[i5 + 3] + f12) + mPPointF2.y), icon3.getIntrinsicWidth(), icon3.getIntrinsicHeight());
                                }
                            } else {
                                i5 = i15;
                                mPPointF2 = mPPointF3;
                                list2 = dataSets;
                                barBuffer = barBuffer2;
                            }
                            i15 = i5 + 4;
                            barBuffer2 = barBuffer;
                            mPPointF3 = mPPointF2;
                            dataSets = list2;
                        }
                        mPPointF = mPPointF3;
                        list = dataSets;
                    }
                    f7 = convertDpToPixel;
                    z2 = isDrawValueAboveBarEnabled;
                    MPPointF.recycleInstance(mPPointF);
                } else {
                    list = dataSets;
                    f7 = convertDpToPixel;
                    z2 = isDrawValueAboveBarEnabled;
                }
                i6++;
                dataSets = list;
                isDrawValueAboveBarEnabled = z2;
                convertDpToPixel = f7;
            }
        }
    }

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
            int i5 = i4 + 2;
            if (this.f5436a.isInBoundsLeft(barBuffer.buffer[i5])) {
                if (!this.f5436a.isInBoundsRight(barBuffer.buffer[i4])) {
                    return;
                }
                if (!z2) {
                    this.f5401c.setColor(iBarDataSet.getColor(i4 / 4));
                }
                if (iBarDataSet.getGradientColor() != null) {
                    GradientColor gradientColor = iBarDataSet.getGradientColor();
                    Paint paint = this.f5401c;
                    float[] fArr = barBuffer.buffer;
                    paint.setShader(new LinearGradient(fArr[i4], fArr[i4 + 3], fArr[i4], fArr[i4 + 1], gradientColor.getStartColor(), gradientColor.getEndColor(), Shader.TileMode.MIRROR));
                }
                if (iBarDataSet.getGradientColors() != null) {
                    Paint paint2 = this.f5401c;
                    float[] fArr2 = barBuffer.buffer;
                    float f2 = fArr2[i4];
                    float f3 = fArr2[i4 + 3];
                    float f4 = fArr2[i4];
                    float f5 = fArr2[i4 + 1];
                    int i6 = i4 / 4;
                    paint2.setShader(new LinearGradient(f2, f3, f4, f5, iBarDataSet.getGradientColor(i6).getStartColor(), iBarDataSet.getGradientColor(i6).getEndColor(), Shader.TileMode.MIRROR));
                }
                float[] fArr3 = barBuffer.buffer;
                int i7 = i4 + 1;
                int i8 = i4 + 3;
                canvas.drawRect(fArr3[i4], fArr3[i7], fArr3[i5], fArr3[i8], this.f5401c);
                if (z) {
                    float[] fArr4 = barBuffer.buffer;
                    canvas.drawRect(fArr4[i4], fArr4[i7], fArr4[i5], fArr4[i8], this.f5391k);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void f(float f2, float f3, float f4, float f5, Transformer transformer) {
        this.f5388h.set(f2 - f5, f3, f2 + f5, f4);
        transformer.rectToPixelPhase(this.f5388h, this.f5400b.getPhaseY());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void g(Highlight highlight, RectF rectF) {
        highlight.setDraw(rectF.centerX(), rectF.top);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
        BarData barData = this.f5387g.getBarData();
        this.f5389i = new BarBuffer[barData.getDataSetCount()];
        for (int i2 = 0; i2 < this.f5389i.length; i2++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i2);
            this.f5389i[i2] = new BarBuffer(iBarDataSet.getEntryCount() * 4 * (iBarDataSet.isStacked() ? iBarDataSet.getStackSize() : 1), barData.getDataSetCount(), iBarDataSet.isStacked());
        }
    }
}
