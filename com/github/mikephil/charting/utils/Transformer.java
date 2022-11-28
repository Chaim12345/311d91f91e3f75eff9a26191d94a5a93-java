package com.github.mikephil.charting.utils;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import java.util.List;
/* loaded from: classes.dex */
public class Transformer {

    /* renamed from: c  reason: collision with root package name */
    protected ViewPortHandler f5464c;

    /* renamed from: a  reason: collision with root package name */
    protected Matrix f5462a = new Matrix();

    /* renamed from: b  reason: collision with root package name */
    protected Matrix f5463b = new Matrix();

    /* renamed from: d  reason: collision with root package name */
    protected float[] f5465d = new float[1];

    /* renamed from: e  reason: collision with root package name */
    protected float[] f5466e = new float[1];

    /* renamed from: f  reason: collision with root package name */
    protected float[] f5467f = new float[1];

    /* renamed from: g  reason: collision with root package name */
    protected float[] f5468g = new float[1];

    /* renamed from: h  reason: collision with root package name */
    protected Matrix f5469h = new Matrix();

    /* renamed from: i  reason: collision with root package name */
    float[] f5470i = new float[2];
    private Matrix mMBuffer1 = new Matrix();
    private Matrix mMBuffer2 = new Matrix();

    public Transformer(ViewPortHandler viewPortHandler) {
        this.f5464c = viewPortHandler;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    public float[] generateTransformedValuesBubble(IBubbleDataSet iBubbleDataSet, float f2, int i2, int i3) {
        int i4 = ((i3 - i2) + 1) * 2;
        if (this.f5466e.length != i4) {
            this.f5466e = new float[i4];
        }
        float[] fArr = this.f5466e;
        for (int i5 = 0; i5 < i4; i5 += 2) {
            ?? entryForIndex = iBubbleDataSet.getEntryForIndex((i5 / 2) + i2);
            if (entryForIndex != 0) {
                fArr[i5] = entryForIndex.getX();
                fArr[i5 + 1] = entryForIndex.getY() * f2;
            } else {
                fArr[i5] = 0.0f;
                fArr[i5 + 1] = 0.0f;
            }
        }
        getValueToPixelMatrix().mapPoints(fArr);
        return fArr;
    }

    public float[] generateTransformedValuesCandle(ICandleDataSet iCandleDataSet, float f2, float f3, int i2, int i3) {
        int i4 = ((int) (((i3 - i2) * f2) + 1.0f)) * 2;
        if (this.f5468g.length != i4) {
            this.f5468g = new float[i4];
        }
        float[] fArr = this.f5468g;
        for (int i5 = 0; i5 < i4; i5 += 2) {
            CandleEntry candleEntry = (CandleEntry) iCandleDataSet.getEntryForIndex((i5 / 2) + i2);
            if (candleEntry != null) {
                fArr[i5] = candleEntry.getX();
                fArr[i5 + 1] = candleEntry.getHigh() * f3;
            } else {
                fArr[i5] = 0.0f;
                fArr[i5 + 1] = 0.0f;
            }
        }
        getValueToPixelMatrix().mapPoints(fArr);
        return fArr;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    public float[] generateTransformedValuesLine(ILineDataSet iLineDataSet, float f2, float f3, int i2, int i3) {
        int i4 = (((int) ((i3 - i2) * f2)) + 1) * 2;
        if (this.f5467f.length != i4) {
            this.f5467f = new float[i4];
        }
        float[] fArr = this.f5467f;
        for (int i5 = 0; i5 < i4; i5 += 2) {
            ?? entryForIndex = iLineDataSet.getEntryForIndex((i5 / 2) + i2);
            if (entryForIndex != 0) {
                fArr[i5] = entryForIndex.getX();
                fArr[i5 + 1] = entryForIndex.getY() * f3;
            } else {
                fArr[i5] = 0.0f;
                fArr[i5 + 1] = 0.0f;
            }
        }
        getValueToPixelMatrix().mapPoints(fArr);
        return fArr;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    public float[] generateTransformedValuesScatter(IScatterDataSet iScatterDataSet, float f2, float f3, int i2, int i3) {
        int i4 = ((int) (((i3 - i2) * f2) + 1.0f)) * 2;
        if (this.f5465d.length != i4) {
            this.f5465d = new float[i4];
        }
        float[] fArr = this.f5465d;
        for (int i5 = 0; i5 < i4; i5 += 2) {
            ?? entryForIndex = iScatterDataSet.getEntryForIndex((i5 / 2) + i2);
            if (entryForIndex != 0) {
                fArr[i5] = entryForIndex.getX();
                fArr[i5 + 1] = entryForIndex.getY() * f3;
            } else {
                fArr[i5] = 0.0f;
                fArr[i5 + 1] = 0.0f;
            }
        }
        getValueToPixelMatrix().mapPoints(fArr);
        return fArr;
    }

    public Matrix getOffsetMatrix() {
        return this.f5463b;
    }

    public MPPointD getPixelForValues(float f2, float f3) {
        float[] fArr = this.f5470i;
        fArr[0] = f2;
        fArr[1] = f3;
        pointValuesToPixel(fArr);
        float[] fArr2 = this.f5470i;
        return MPPointD.getInstance(fArr2[0], fArr2[1]);
    }

    public Matrix getPixelToValueMatrix() {
        getValueToPixelMatrix().invert(this.mMBuffer2);
        return this.mMBuffer2;
    }

    public Matrix getValueMatrix() {
        return this.f5462a;
    }

    public Matrix getValueToPixelMatrix() {
        this.mMBuffer1.set(this.f5462a);
        this.mMBuffer1.postConcat(this.f5464c.f5471a);
        this.mMBuffer1.postConcat(this.f5463b);
        return this.mMBuffer1;
    }

    public MPPointD getValuesByTouchPoint(float f2, float f3) {
        MPPointD mPPointD = MPPointD.getInstance(0.0d, 0.0d);
        getValuesByTouchPoint(f2, f3, mPPointD);
        return mPPointD;
    }

    public void getValuesByTouchPoint(float f2, float f3, MPPointD mPPointD) {
        float[] fArr = this.f5470i;
        fArr[0] = f2;
        fArr[1] = f3;
        pixelsToValue(fArr);
        float[] fArr2 = this.f5470i;
        mPPointD.x = fArr2[0];
        mPPointD.y = fArr2[1];
    }

    public void pathValueToPixel(Path path) {
        path.transform(this.f5462a);
        path.transform(this.f5464c.getMatrixTouch());
        path.transform(this.f5463b);
    }

    public void pathValuesToPixel(List<Path> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            pathValueToPixel(list.get(i2));
        }
    }

    public void pixelsToValue(float[] fArr) {
        Matrix matrix = this.f5469h;
        matrix.reset();
        this.f5463b.invert(matrix);
        matrix.mapPoints(fArr);
        this.f5464c.getMatrixTouch().invert(matrix);
        matrix.mapPoints(fArr);
        this.f5462a.invert(matrix);
        matrix.mapPoints(fArr);
    }

    public void pointValuesToPixel(float[] fArr) {
        this.f5462a.mapPoints(fArr);
        this.f5464c.getMatrixTouch().mapPoints(fArr);
        this.f5463b.mapPoints(fArr);
    }

    public void prepareMatrixOffset(boolean z) {
        this.f5463b.reset();
        if (!z) {
            this.f5463b.postTranslate(this.f5464c.offsetLeft(), this.f5464c.getChartHeight() - this.f5464c.offsetBottom());
            return;
        }
        this.f5463b.setTranslate(this.f5464c.offsetLeft(), -this.f5464c.offsetTop());
        this.f5463b.postScale(1.0f, -1.0f);
    }

    public void prepareMatrixValuePx(float f2, float f3, float f4, float f5) {
        float contentWidth = this.f5464c.contentWidth() / f3;
        float contentHeight = this.f5464c.contentHeight() / f4;
        if (Float.isInfinite(contentWidth)) {
            contentWidth = 0.0f;
        }
        if (Float.isInfinite(contentHeight)) {
            contentHeight = 0.0f;
        }
        this.f5462a.reset();
        this.f5462a.postTranslate(-f2, -f5);
        this.f5462a.postScale(contentWidth, -contentHeight);
    }

    public void rectToPixelPhase(RectF rectF, float f2) {
        rectF.top *= f2;
        rectF.bottom *= f2;
        this.f5462a.mapRect(rectF);
        this.f5464c.getMatrixTouch().mapRect(rectF);
        this.f5463b.mapRect(rectF);
    }

    public void rectToPixelPhaseHorizontal(RectF rectF, float f2) {
        rectF.left *= f2;
        rectF.right *= f2;
        this.f5462a.mapRect(rectF);
        this.f5464c.getMatrixTouch().mapRect(rectF);
        this.f5463b.mapRect(rectF);
    }

    public void rectValueToPixel(RectF rectF) {
        this.f5462a.mapRect(rectF);
        this.f5464c.getMatrixTouch().mapRect(rectF);
        this.f5463b.mapRect(rectF);
    }

    public void rectValueToPixelHorizontal(RectF rectF) {
        this.f5462a.mapRect(rectF);
        this.f5464c.getMatrixTouch().mapRect(rectF);
        this.f5463b.mapRect(rectF);
    }

    public void rectValueToPixelHorizontal(RectF rectF, float f2) {
        rectF.left *= f2;
        rectF.right *= f2;
        this.f5462a.mapRect(rectF);
        this.f5464c.getMatrixTouch().mapRect(rectF);
        this.f5463b.mapRect(rectF);
    }

    public void rectValuesToPixel(List<RectF> list) {
        Matrix valueToPixelMatrix = getValueToPixelMatrix();
        for (int i2 = 0; i2 < list.size(); i2++) {
            valueToPixelMatrix.mapRect(list.get(i2));
        }
    }
}
