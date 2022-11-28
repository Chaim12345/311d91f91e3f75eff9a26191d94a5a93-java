package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.renderer.BarLineScatterCandleBubbleRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;
/* loaded from: classes.dex */
public class BubbleChartRenderer extends BarLineScatterCandleBubbleRenderer {
    private float[] _hsvBuffer;

    /* renamed from: g  reason: collision with root package name */
    protected BubbleDataProvider f5394g;
    private float[] pointBuffer;
    private float[] sizeBuffer;

    public BubbleChartRenderer(BubbleDataProvider bubbleDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.sizeBuffer = new float[4];
        this.pointBuffer = new float[2];
        this._hsvBuffer = new float[3];
        this.f5394g = bubbleDataProvider;
        this.f5401c.setStyle(Paint.Style.FILL);
        this.f5402d.setStyle(Paint.Style.STROKE);
        this.f5402d.setStrokeWidth(Utils.convertDpToPixel(1.5f));
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        for (T t2 : this.f5394g.getBubbleData().getDataSets()) {
            if (t2.isVisible()) {
                e(canvas, t2);
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        BubbleData bubbleData = this.f5394g.getBubbleData();
        float phaseY = this.f5400b.getPhaseY();
        for (Highlight highlight : highlightArr) {
            IBubbleDataSet iBubbleDataSet = (IBubbleDataSet) bubbleData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iBubbleDataSet != null && iBubbleDataSet.isHighlightEnabled()) {
                BubbleEntry bubbleEntry = (BubbleEntry) iBubbleDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (bubbleEntry.getY() == highlight.getY() && c(bubbleEntry, iBubbleDataSet)) {
                    Transformer transformer = this.f5394g.getTransformer(iBubbleDataSet.getAxisDependency());
                    float[] fArr = this.sizeBuffer;
                    fArr[0] = 0.0f;
                    fArr[2] = 1.0f;
                    transformer.pointValuesToPixel(fArr);
                    boolean isNormalizeSizeEnabled = iBubbleDataSet.isNormalizeSizeEnabled();
                    float[] fArr2 = this.sizeBuffer;
                    float min = Math.min(Math.abs(this.f5436a.contentBottom() - this.f5436a.contentTop()), Math.abs(fArr2[2] - fArr2[0]));
                    this.pointBuffer[0] = bubbleEntry.getX();
                    this.pointBuffer[1] = bubbleEntry.getY() * phaseY;
                    transformer.pointValuesToPixel(this.pointBuffer);
                    float[] fArr3 = this.pointBuffer;
                    highlight.setDraw(fArr3[0], fArr3[1]);
                    float f2 = f(bubbleEntry.getSize(), iBubbleDataSet.getMaxSize(), min, isNormalizeSizeEnabled) / 2.0f;
                    if (this.f5436a.isInBoundsTop(this.pointBuffer[1] + f2) && this.f5436a.isInBoundsBottom(this.pointBuffer[1] - f2) && this.f5436a.isInBoundsLeft(this.pointBuffer[0] + f2)) {
                        if (!this.f5436a.isInBoundsRight(this.pointBuffer[0] - f2)) {
                            return;
                        }
                        int color = iBubbleDataSet.getColor((int) bubbleEntry.getX());
                        Color.RGBToHSV(Color.red(color), Color.green(color), Color.blue(color), this._hsvBuffer);
                        float[] fArr4 = this._hsvBuffer;
                        fArr4[2] = fArr4[2] * 0.5f;
                        this.f5402d.setColor(Color.HSVToColor(Color.alpha(color), this._hsvBuffer));
                        this.f5402d.setStrokeWidth(iBubbleDataSet.getHighlightCircleWidth());
                        float[] fArr5 = this.pointBuffer;
                        canvas.drawCircle(fArr5[0], fArr5[1], f2, this.f5402d);
                    }
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
        BubbleData bubbleData = this.f5394g.getBubbleData();
        if (bubbleData != null && b(this.f5394g)) {
            List<T> dataSets = bubbleData.getDataSets();
            float calcTextHeight = Utils.calcTextHeight(this.f5403e, "1");
            for (int i3 = 0; i3 < dataSets.size(); i3++) {
                IBubbleDataSet iBubbleDataSet = (IBubbleDataSet) dataSets.get(i3);
                if (d(iBubbleDataSet) && iBubbleDataSet.getEntryCount() >= 1) {
                    a(iBubbleDataSet);
                    float max = Math.max(0.0f, Math.min(1.0f, this.f5400b.getPhaseX()));
                    float phaseY = this.f5400b.getPhaseY();
                    this.f5392f.set(this.f5394g, iBubbleDataSet);
                    Transformer transformer = this.f5394g.getTransformer(iBubbleDataSet.getAxisDependency());
                    BarLineScatterCandleBubbleRenderer.XBounds xBounds = this.f5392f;
                    float[] generateTransformedValuesBubble = transformer.generateTransformedValuesBubble(iBubbleDataSet, phaseY, xBounds.min, xBounds.max);
                    float f4 = max == 1.0f ? phaseY : max;
                    MPPointF mPPointF2 = MPPointF.getInstance(iBubbleDataSet.getIconsOffset());
                    mPPointF2.x = Utils.convertDpToPixel(mPPointF2.x);
                    mPPointF2.y = Utils.convertDpToPixel(mPPointF2.y);
                    int i4 = 0;
                    while (i4 < generateTransformedValuesBubble.length) {
                        int i5 = i4 / 2;
                        int valueTextColor = iBubbleDataSet.getValueTextColor(this.f5392f.min + i5);
                        int argb = Color.argb(Math.round(255.0f * f4), Color.red(valueTextColor), Color.green(valueTextColor), Color.blue(valueTextColor));
                        float f5 = generateTransformedValuesBubble[i4];
                        float f6 = generateTransformedValuesBubble[i4 + 1];
                        if (!this.f5436a.isInBoundsRight(f5)) {
                            break;
                        }
                        if (this.f5436a.isInBoundsLeft(f5) && this.f5436a.isInBoundsY(f6)) {
                            BubbleEntry bubbleEntry = (BubbleEntry) iBubbleDataSet.getEntryForIndex(i5 + this.f5392f.min);
                            if (iBubbleDataSet.isDrawValuesEnabled()) {
                                f2 = f6;
                                f3 = f5;
                                i2 = i4;
                                mPPointF = mPPointF2;
                                drawValue(canvas, iBubbleDataSet.getValueFormatter(), bubbleEntry.getSize(), bubbleEntry, i3, f5, f6 + (0.5f * calcTextHeight), argb);
                            } else {
                                f2 = f6;
                                f3 = f5;
                                i2 = i4;
                                mPPointF = mPPointF2;
                            }
                            if (bubbleEntry.getIcon() != null && iBubbleDataSet.isDrawIconsEnabled()) {
                                Drawable icon = bubbleEntry.getIcon();
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

    protected void e(Canvas canvas, IBubbleDataSet iBubbleDataSet) {
        if (iBubbleDataSet.getEntryCount() < 1) {
            return;
        }
        Transformer transformer = this.f5394g.getTransformer(iBubbleDataSet.getAxisDependency());
        float phaseY = this.f5400b.getPhaseY();
        this.f5392f.set(this.f5394g, iBubbleDataSet);
        float[] fArr = this.sizeBuffer;
        fArr[0] = 0.0f;
        fArr[2] = 1.0f;
        transformer.pointValuesToPixel(fArr);
        boolean isNormalizeSizeEnabled = iBubbleDataSet.isNormalizeSizeEnabled();
        float[] fArr2 = this.sizeBuffer;
        float min = Math.min(Math.abs(this.f5436a.contentBottom() - this.f5436a.contentTop()), Math.abs(fArr2[2] - fArr2[0]));
        int i2 = this.f5392f.min;
        while (true) {
            BarLineScatterCandleBubbleRenderer.XBounds xBounds = this.f5392f;
            if (i2 > xBounds.range + xBounds.min) {
                return;
            }
            BubbleEntry bubbleEntry = (BubbleEntry) iBubbleDataSet.getEntryForIndex(i2);
            this.pointBuffer[0] = bubbleEntry.getX();
            this.pointBuffer[1] = bubbleEntry.getY() * phaseY;
            transformer.pointValuesToPixel(this.pointBuffer);
            float f2 = f(bubbleEntry.getSize(), iBubbleDataSet.getMaxSize(), min, isNormalizeSizeEnabled) / 2.0f;
            if (this.f5436a.isInBoundsTop(this.pointBuffer[1] + f2) && this.f5436a.isInBoundsBottom(this.pointBuffer[1] - f2) && this.f5436a.isInBoundsLeft(this.pointBuffer[0] + f2)) {
                if (!this.f5436a.isInBoundsRight(this.pointBuffer[0] - f2)) {
                    return;
                }
                this.f5401c.setColor(iBubbleDataSet.getColor((int) bubbleEntry.getX()));
                float[] fArr3 = this.pointBuffer;
                canvas.drawCircle(fArr3[0], fArr3[1], f2, this.f5401c);
            }
            i2++;
        }
    }

    protected float f(float f2, float f3, float f4, boolean z) {
        if (z) {
            f2 = f3 == 0.0f ? 1.0f : (float) Math.sqrt(f2 / f3);
        }
        return f4 * f2;
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
    }
}
