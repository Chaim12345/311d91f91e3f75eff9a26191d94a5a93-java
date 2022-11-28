package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.CandleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet;
import com.github.mikephil.charting.renderer.BarLineScatterCandleBubbleRenderer;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;
/* loaded from: classes.dex */
public class CandleStickChartRenderer extends LineScatterCandleRadarRenderer {

    /* renamed from: g  reason: collision with root package name */
    protected CandleDataProvider f5395g;
    private float[] mBodyBuffers;
    private float[] mCloseBuffers;
    private float[] mOpenBuffers;
    private float[] mRangeBuffers;
    private float[] mShadowBuffers;

    public CandleStickChartRenderer(CandleDataProvider candleDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mShadowBuffers = new float[8];
        this.mBodyBuffers = new float[4];
        this.mRangeBuffers = new float[4];
        this.mOpenBuffers = new float[4];
        this.mCloseBuffers = new float[4];
        this.f5395g = candleDataProvider;
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        for (T t2 : this.f5395g.getCandleData().getDataSets()) {
            if (t2.isVisible()) {
                f(canvas, t2);
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        CandleData candleData = this.f5395g.getCandleData();
        for (Highlight highlight : highlightArr) {
            ILineScatterCandleRadarDataSet iLineScatterCandleRadarDataSet = (ICandleDataSet) candleData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iLineScatterCandleRadarDataSet != null && iLineScatterCandleRadarDataSet.isHighlightEnabled()) {
                CandleEntry candleEntry = (CandleEntry) iLineScatterCandleRadarDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (c(candleEntry, iLineScatterCandleRadarDataSet)) {
                    MPPointD pixelForValues = this.f5395g.getTransformer(iLineScatterCandleRadarDataSet.getAxisDependency()).getPixelForValues(candleEntry.getX(), ((candleEntry.getLow() * this.f5400b.getPhaseY()) + (candleEntry.getHigh() * this.f5400b.getPhaseY())) / 2.0f);
                    highlight.setDraw((float) pixelForValues.x, (float) pixelForValues.y);
                    e(canvas, (float) pixelForValues.x, (float) pixelForValues.y, iLineScatterCandleRadarDataSet);
                }
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        int i2;
        MPPointF mPPointF;
        float f2;
        float f3;
        if (b(this.f5395g)) {
            List<T> dataSets = this.f5395g.getCandleData().getDataSets();
            for (int i3 = 0; i3 < dataSets.size(); i3++) {
                ICandleDataSet iCandleDataSet = (ICandleDataSet) dataSets.get(i3);
                if (d(iCandleDataSet) && iCandleDataSet.getEntryCount() >= 1) {
                    a(iCandleDataSet);
                    Transformer transformer = this.f5395g.getTransformer(iCandleDataSet.getAxisDependency());
                    this.f5392f.set(this.f5395g, iCandleDataSet);
                    float phaseX = this.f5400b.getPhaseX();
                    float phaseY = this.f5400b.getPhaseY();
                    BarLineScatterCandleBubbleRenderer.XBounds xBounds = this.f5392f;
                    float[] generateTransformedValuesCandle = transformer.generateTransformedValuesCandle(iCandleDataSet, phaseX, phaseY, xBounds.min, xBounds.max);
                    float convertDpToPixel = Utils.convertDpToPixel(5.0f);
                    MPPointF mPPointF2 = MPPointF.getInstance(iCandleDataSet.getIconsOffset());
                    mPPointF2.x = Utils.convertDpToPixel(mPPointF2.x);
                    mPPointF2.y = Utils.convertDpToPixel(mPPointF2.y);
                    int i4 = 0;
                    while (i4 < generateTransformedValuesCandle.length) {
                        float f4 = generateTransformedValuesCandle[i4];
                        float f5 = generateTransformedValuesCandle[i4 + 1];
                        if (!this.f5436a.isInBoundsRight(f4)) {
                            break;
                        }
                        if (this.f5436a.isInBoundsLeft(f4) && this.f5436a.isInBoundsY(f5)) {
                            int i5 = i4 / 2;
                            CandleEntry candleEntry = (CandleEntry) iCandleDataSet.getEntryForIndex(this.f5392f.min + i5);
                            if (iCandleDataSet.isDrawValuesEnabled()) {
                                f2 = f5;
                                f3 = f4;
                                i2 = i4;
                                mPPointF = mPPointF2;
                                drawValue(canvas, iCandleDataSet.getValueFormatter(), candleEntry.getHigh(), candleEntry, i3, f4, f5 - convertDpToPixel, iCandleDataSet.getValueTextColor(i5));
                            } else {
                                f2 = f5;
                                f3 = f4;
                                i2 = i4;
                                mPPointF = mPPointF2;
                            }
                            if (candleEntry.getIcon() != null && iCandleDataSet.isDrawIconsEnabled()) {
                                Drawable icon = candleEntry.getIcon();
                                Utils.drawImage(canvas, icon, (int) (f3 + mPPointF.x), (int) (f2 + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                            }
                        } else {
                            i2 = i4;
                            mPPointF = mPPointF2;
                        }
                        i4 = i2 + 2;
                        mPPointF2 = mPPointF;
                    }
                    MPPointF.recycleInstance(mPPointF2);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0155  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void f(Canvas canvas, ICandleDataSet iCandleDataSet) {
        int neutralColor;
        Canvas canvas2;
        float f2;
        float f3;
        float f4;
        float f5;
        Paint paint;
        Paint paint2;
        int shadowColor;
        Paint paint3;
        int neutralColor2;
        Paint paint4;
        int increasingColor;
        float f6;
        float f7;
        float f8;
        float f9;
        Paint paint5;
        int decreasingColor;
        Transformer transformer = this.f5395g.getTransformer(iCandleDataSet.getAxisDependency());
        float phaseY = this.f5400b.getPhaseY();
        float barSpace = iCandleDataSet.getBarSpace();
        boolean showCandleBar = iCandleDataSet.getShowCandleBar();
        this.f5392f.set(this.f5395g, iCandleDataSet);
        this.f5401c.setStrokeWidth(iCandleDataSet.getShadowWidth());
        int i2 = this.f5392f.min;
        while (true) {
            BarLineScatterCandleBubbleRenderer.XBounds xBounds = this.f5392f;
            if (i2 > xBounds.range + xBounds.min) {
                return;
            }
            CandleEntry candleEntry = (CandleEntry) iCandleDataSet.getEntryForIndex(i2);
            if (candleEntry != null) {
                float x = candleEntry.getX();
                float open = candleEntry.getOpen();
                float close = candleEntry.getClose();
                float high = candleEntry.getHigh();
                float low = candleEntry.getLow();
                if (showCandleBar) {
                    float[] fArr = this.mShadowBuffers;
                    fArr[0] = x;
                    fArr[2] = x;
                    fArr[4] = x;
                    fArr[6] = x;
                    int i3 = (open > close ? 1 : (open == close ? 0 : -1));
                    if (i3 > 0) {
                        fArr[1] = high * phaseY;
                        fArr[3] = open * phaseY;
                        fArr[5] = low * phaseY;
                        fArr[7] = close * phaseY;
                    } else {
                        float f10 = high * phaseY;
                        if (open < close) {
                            fArr[1] = f10;
                            fArr[3] = close * phaseY;
                            fArr[5] = low * phaseY;
                            fArr[7] = open * phaseY;
                        } else {
                            fArr[1] = f10;
                            fArr[3] = open * phaseY;
                            fArr[5] = low * phaseY;
                            fArr[7] = fArr[3];
                        }
                    }
                    transformer.pointValuesToPixel(fArr);
                    if (!iCandleDataSet.getShadowColorSameAsCandle()) {
                        paint2 = this.f5401c;
                        if (iCandleDataSet.getShadowColor() != 1122867) {
                            shadowColor = iCandleDataSet.getShadowColor();
                            paint2.setColor(shadowColor);
                            this.f5401c.setStyle(Paint.Style.STROKE);
                            canvas.drawLines(this.mShadowBuffers, this.f5401c);
                            float[] fArr2 = this.mBodyBuffers;
                            fArr2[0] = (x - 0.5f) + barSpace;
                            fArr2[1] = close * phaseY;
                            fArr2[2] = (x + 0.5f) - barSpace;
                            fArr2[3] = open * phaseY;
                            transformer.pointValuesToPixel(fArr2);
                            if (i3 <= 0) {
                            }
                            canvas.drawRect(f6, f7, f8, f9, this.f5401c);
                        }
                        shadowColor = iCandleDataSet.getColor(i2);
                        paint2.setColor(shadowColor);
                        this.f5401c.setStyle(Paint.Style.STROKE);
                        canvas.drawLines(this.mShadowBuffers, this.f5401c);
                        float[] fArr22 = this.mBodyBuffers;
                        fArr22[0] = (x - 0.5f) + barSpace;
                        fArr22[1] = close * phaseY;
                        fArr22[2] = (x + 0.5f) - barSpace;
                        fArr22[3] = open * phaseY;
                        transformer.pointValuesToPixel(fArr22);
                        if (i3 <= 0) {
                        }
                        canvas.drawRect(f6, f7, f8, f9, this.f5401c);
                    } else if (i3 > 0) {
                        paint2 = this.f5401c;
                        if (iCandleDataSet.getDecreasingColor() != 1122867) {
                            shadowColor = iCandleDataSet.getDecreasingColor();
                            paint2.setColor(shadowColor);
                            this.f5401c.setStyle(Paint.Style.STROKE);
                            canvas.drawLines(this.mShadowBuffers, this.f5401c);
                            float[] fArr222 = this.mBodyBuffers;
                            fArr222[0] = (x - 0.5f) + barSpace;
                            fArr222[1] = close * phaseY;
                            fArr222[2] = (x + 0.5f) - barSpace;
                            fArr222[3] = open * phaseY;
                            transformer.pointValuesToPixel(fArr222);
                            if (i3 <= 0) {
                                if (iCandleDataSet.getDecreasingColor() == 1122867) {
                                    paint5 = this.f5401c;
                                    decreasingColor = iCandleDataSet.getColor(i2);
                                } else {
                                    paint5 = this.f5401c;
                                    decreasingColor = iCandleDataSet.getDecreasingColor();
                                }
                                paint5.setColor(decreasingColor);
                                this.f5401c.setStyle(iCandleDataSet.getDecreasingPaintStyle());
                                float[] fArr3 = this.mBodyBuffers;
                                f6 = fArr3[0];
                                f7 = fArr3[3];
                                f8 = fArr3[2];
                                f9 = fArr3[1];
                            } else if (open < close) {
                                if (iCandleDataSet.getIncreasingColor() == 1122867) {
                                    paint4 = this.f5401c;
                                    increasingColor = iCandleDataSet.getColor(i2);
                                } else {
                                    paint4 = this.f5401c;
                                    increasingColor = iCandleDataSet.getIncreasingColor();
                                }
                                paint4.setColor(increasingColor);
                                this.f5401c.setStyle(iCandleDataSet.getIncreasingPaintStyle());
                                float[] fArr4 = this.mBodyBuffers;
                                f6 = fArr4[0];
                                f7 = fArr4[1];
                                f8 = fArr4[2];
                                f9 = fArr4[3];
                            } else {
                                if (iCandleDataSet.getNeutralColor() == 1122867) {
                                    paint3 = this.f5401c;
                                    neutralColor2 = iCandleDataSet.getColor(i2);
                                } else {
                                    paint3 = this.f5401c;
                                    neutralColor2 = iCandleDataSet.getNeutralColor();
                                }
                                paint3.setColor(neutralColor2);
                                float[] fArr5 = this.mBodyBuffers;
                                f2 = fArr5[0];
                                f3 = fArr5[1];
                                f4 = fArr5[2];
                                f5 = fArr5[3];
                                paint = this.f5401c;
                                canvas2 = canvas;
                            }
                            canvas.drawRect(f6, f7, f8, f9, this.f5401c);
                        }
                        shadowColor = iCandleDataSet.getColor(i2);
                        paint2.setColor(shadowColor);
                        this.f5401c.setStyle(Paint.Style.STROKE);
                        canvas.drawLines(this.mShadowBuffers, this.f5401c);
                        float[] fArr2222 = this.mBodyBuffers;
                        fArr2222[0] = (x - 0.5f) + barSpace;
                        fArr2222[1] = close * phaseY;
                        fArr2222[2] = (x + 0.5f) - barSpace;
                        fArr2222[3] = open * phaseY;
                        transformer.pointValuesToPixel(fArr2222);
                        if (i3 <= 0) {
                        }
                        canvas.drawRect(f6, f7, f8, f9, this.f5401c);
                    } else if (open < close) {
                        paint2 = this.f5401c;
                        if (iCandleDataSet.getIncreasingColor() != 1122867) {
                            shadowColor = iCandleDataSet.getIncreasingColor();
                            paint2.setColor(shadowColor);
                            this.f5401c.setStyle(Paint.Style.STROKE);
                            canvas.drawLines(this.mShadowBuffers, this.f5401c);
                            float[] fArr22222 = this.mBodyBuffers;
                            fArr22222[0] = (x - 0.5f) + barSpace;
                            fArr22222[1] = close * phaseY;
                            fArr22222[2] = (x + 0.5f) - barSpace;
                            fArr22222[3] = open * phaseY;
                            transformer.pointValuesToPixel(fArr22222);
                            if (i3 <= 0) {
                            }
                            canvas.drawRect(f6, f7, f8, f9, this.f5401c);
                        }
                        shadowColor = iCandleDataSet.getColor(i2);
                        paint2.setColor(shadowColor);
                        this.f5401c.setStyle(Paint.Style.STROKE);
                        canvas.drawLines(this.mShadowBuffers, this.f5401c);
                        float[] fArr222222 = this.mBodyBuffers;
                        fArr222222[0] = (x - 0.5f) + barSpace;
                        fArr222222[1] = close * phaseY;
                        fArr222222[2] = (x + 0.5f) - barSpace;
                        fArr222222[3] = open * phaseY;
                        transformer.pointValuesToPixel(fArr222222);
                        if (i3 <= 0) {
                        }
                        canvas.drawRect(f6, f7, f8, f9, this.f5401c);
                    } else {
                        paint2 = this.f5401c;
                        if (iCandleDataSet.getNeutralColor() != 1122867) {
                            shadowColor = iCandleDataSet.getNeutralColor();
                            paint2.setColor(shadowColor);
                            this.f5401c.setStyle(Paint.Style.STROKE);
                            canvas.drawLines(this.mShadowBuffers, this.f5401c);
                            float[] fArr2222222 = this.mBodyBuffers;
                            fArr2222222[0] = (x - 0.5f) + barSpace;
                            fArr2222222[1] = close * phaseY;
                            fArr2222222[2] = (x + 0.5f) - barSpace;
                            fArr2222222[3] = open * phaseY;
                            transformer.pointValuesToPixel(fArr2222222);
                            if (i3 <= 0) {
                            }
                            canvas.drawRect(f6, f7, f8, f9, this.f5401c);
                        }
                        shadowColor = iCandleDataSet.getColor(i2);
                        paint2.setColor(shadowColor);
                        this.f5401c.setStyle(Paint.Style.STROKE);
                        canvas.drawLines(this.mShadowBuffers, this.f5401c);
                        float[] fArr22222222 = this.mBodyBuffers;
                        fArr22222222[0] = (x - 0.5f) + barSpace;
                        fArr22222222[1] = close * phaseY;
                        fArr22222222[2] = (x + 0.5f) - barSpace;
                        fArr22222222[3] = open * phaseY;
                        transformer.pointValuesToPixel(fArr22222222);
                        if (i3 <= 0) {
                        }
                        canvas.drawRect(f6, f7, f8, f9, this.f5401c);
                    }
                } else {
                    float[] fArr6 = this.mRangeBuffers;
                    fArr6[0] = x;
                    fArr6[1] = high * phaseY;
                    fArr6[2] = x;
                    fArr6[3] = low * phaseY;
                    float[] fArr7 = this.mOpenBuffers;
                    fArr7[0] = (x - 0.5f) + barSpace;
                    float f11 = open * phaseY;
                    fArr7[1] = f11;
                    fArr7[2] = x;
                    fArr7[3] = f11;
                    float[] fArr8 = this.mCloseBuffers;
                    fArr8[0] = (0.5f + x) - barSpace;
                    float f12 = close * phaseY;
                    fArr8[1] = f12;
                    fArr8[2] = x;
                    fArr8[3] = f12;
                    transformer.pointValuesToPixel(fArr6);
                    transformer.pointValuesToPixel(this.mOpenBuffers);
                    transformer.pointValuesToPixel(this.mCloseBuffers);
                    if (open > close) {
                        if (iCandleDataSet.getDecreasingColor() != 1122867) {
                            neutralColor = iCandleDataSet.getDecreasingColor();
                            this.f5401c.setColor(neutralColor);
                            float[] fArr9 = this.mRangeBuffers;
                            canvas2 = canvas;
                            canvas2.drawLine(fArr9[0], fArr9[1], fArr9[2], fArr9[3], this.f5401c);
                            float[] fArr10 = this.mOpenBuffers;
                            canvas2.drawLine(fArr10[0], fArr10[1], fArr10[2], fArr10[3], this.f5401c);
                            float[] fArr11 = this.mCloseBuffers;
                            f2 = fArr11[0];
                            f3 = fArr11[1];
                            f4 = fArr11[2];
                            f5 = fArr11[3];
                            paint = this.f5401c;
                        }
                        neutralColor = iCandleDataSet.getColor(i2);
                        this.f5401c.setColor(neutralColor);
                        float[] fArr92 = this.mRangeBuffers;
                        canvas2 = canvas;
                        canvas2.drawLine(fArr92[0], fArr92[1], fArr92[2], fArr92[3], this.f5401c);
                        float[] fArr102 = this.mOpenBuffers;
                        canvas2.drawLine(fArr102[0], fArr102[1], fArr102[2], fArr102[3], this.f5401c);
                        float[] fArr112 = this.mCloseBuffers;
                        f2 = fArr112[0];
                        f3 = fArr112[1];
                        f4 = fArr112[2];
                        f5 = fArr112[3];
                        paint = this.f5401c;
                    } else if (open < close) {
                        if (iCandleDataSet.getIncreasingColor() != 1122867) {
                            neutralColor = iCandleDataSet.getIncreasingColor();
                            this.f5401c.setColor(neutralColor);
                            float[] fArr922 = this.mRangeBuffers;
                            canvas2 = canvas;
                            canvas2.drawLine(fArr922[0], fArr922[1], fArr922[2], fArr922[3], this.f5401c);
                            float[] fArr1022 = this.mOpenBuffers;
                            canvas2.drawLine(fArr1022[0], fArr1022[1], fArr1022[2], fArr1022[3], this.f5401c);
                            float[] fArr1122 = this.mCloseBuffers;
                            f2 = fArr1122[0];
                            f3 = fArr1122[1];
                            f4 = fArr1122[2];
                            f5 = fArr1122[3];
                            paint = this.f5401c;
                        }
                        neutralColor = iCandleDataSet.getColor(i2);
                        this.f5401c.setColor(neutralColor);
                        float[] fArr9222 = this.mRangeBuffers;
                        canvas2 = canvas;
                        canvas2.drawLine(fArr9222[0], fArr9222[1], fArr9222[2], fArr9222[3], this.f5401c);
                        float[] fArr10222 = this.mOpenBuffers;
                        canvas2.drawLine(fArr10222[0], fArr10222[1], fArr10222[2], fArr10222[3], this.f5401c);
                        float[] fArr11222 = this.mCloseBuffers;
                        f2 = fArr11222[0];
                        f3 = fArr11222[1];
                        f4 = fArr11222[2];
                        f5 = fArr11222[3];
                        paint = this.f5401c;
                    } else {
                        if (iCandleDataSet.getNeutralColor() != 1122867) {
                            neutralColor = iCandleDataSet.getNeutralColor();
                            this.f5401c.setColor(neutralColor);
                            float[] fArr92222 = this.mRangeBuffers;
                            canvas2 = canvas;
                            canvas2.drawLine(fArr92222[0], fArr92222[1], fArr92222[2], fArr92222[3], this.f5401c);
                            float[] fArr102222 = this.mOpenBuffers;
                            canvas2.drawLine(fArr102222[0], fArr102222[1], fArr102222[2], fArr102222[3], this.f5401c);
                            float[] fArr112222 = this.mCloseBuffers;
                            f2 = fArr112222[0];
                            f3 = fArr112222[1];
                            f4 = fArr112222[2];
                            f5 = fArr112222[3];
                            paint = this.f5401c;
                        }
                        neutralColor = iCandleDataSet.getColor(i2);
                        this.f5401c.setColor(neutralColor);
                        float[] fArr922222 = this.mRangeBuffers;
                        canvas2 = canvas;
                        canvas2.drawLine(fArr922222[0], fArr922222[1], fArr922222[2], fArr922222[3], this.f5401c);
                        float[] fArr1022222 = this.mOpenBuffers;
                        canvas2.drawLine(fArr1022222[0], fArr1022222[1], fArr1022222[2], fArr1022222[3], this.f5401c);
                        float[] fArr1122222 = this.mCloseBuffers;
                        f2 = fArr1122222[0];
                        f3 = fArr1122222[1];
                        f4 = fArr1122222[2];
                        f5 = fArr1122222[3];
                        paint = this.f5401c;
                    }
                }
                canvas2.drawLine(f2, f3, f4, f5, paint);
            }
            i2++;
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
    }
}
