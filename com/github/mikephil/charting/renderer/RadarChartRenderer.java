package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import org.bouncycastle.tls.CipherSuite;
/* loaded from: classes.dex */
public class RadarChartRenderer extends LineRadarRenderer {

    /* renamed from: g  reason: collision with root package name */
    protected RadarChart f5431g;

    /* renamed from: h  reason: collision with root package name */
    protected Paint f5432h;

    /* renamed from: i  reason: collision with root package name */
    protected Paint f5433i;

    /* renamed from: j  reason: collision with root package name */
    protected Path f5434j;

    /* renamed from: k  reason: collision with root package name */
    protected Path f5435k;

    public RadarChartRenderer(RadarChart radarChart, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.f5434j = new Path();
        this.f5435k = new Path();
        this.f5431g = radarChart;
        Paint paint = new Paint(1);
        this.f5402d = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.f5402d.setStrokeWidth(2.0f);
        this.f5402d.setColor(Color.rgb(255, (int) CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256, 115));
        Paint paint2 = new Paint(1);
        this.f5432h = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.f5433i = new Paint(1);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        RadarData radarData = (RadarData) this.f5431g.getData();
        int entryCount = radarData.getMaxEntryCountSet().getEntryCount();
        for (IRadarDataSet iRadarDataSet : radarData.getDataSets()) {
            if (iRadarDataSet.isVisible()) {
                h(canvas, iRadarDataSet, entryCount);
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
        i(canvas);
    }

    public void drawHighlightCircle(Canvas canvas, MPPointF mPPointF, float f2, float f3, int i2, int i3, float f4) {
        canvas.save();
        float convertDpToPixel = Utils.convertDpToPixel(f3);
        float convertDpToPixel2 = Utils.convertDpToPixel(f2);
        if (i2 != 1122867) {
            Path path = this.f5435k;
            path.reset();
            path.addCircle(mPPointF.x, mPPointF.y, convertDpToPixel, Path.Direction.CW);
            if (convertDpToPixel2 > 0.0f) {
                path.addCircle(mPPointF.x, mPPointF.y, convertDpToPixel2, Path.Direction.CCW);
            }
            this.f5433i.setColor(i2);
            this.f5433i.setStyle(Paint.Style.FILL);
            canvas.drawPath(path, this.f5433i);
        }
        if (i3 != 1122867) {
            this.f5433i.setColor(i3);
            this.f5433i.setStyle(Paint.Style.STROKE);
            this.f5433i.setStrokeWidth(Utils.convertDpToPixel(f4));
            canvas.drawCircle(mPPointF.x, mPPointF.y, convertDpToPixel, this.f5433i);
        }
        canvas.restore();
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        int i2;
        int i3;
        float sliceAngle = this.f5431g.getSliceAngle();
        float factor = this.f5431g.getFactor();
        MPPointF centerOffsets = this.f5431g.getCenterOffsets();
        MPPointF mPPointF = MPPointF.getInstance(0.0f, 0.0f);
        RadarData radarData = (RadarData) this.f5431g.getData();
        int length = highlightArr.length;
        int i4 = 0;
        int i5 = 0;
        while (i5 < length) {
            Highlight highlight = highlightArr[i5];
            IRadarDataSet dataSetByIndex = radarData.getDataSetByIndex(highlight.getDataSetIndex());
            if (dataSetByIndex != null && dataSetByIndex.isHighlightEnabled()) {
                Entry entry = (RadarEntry) dataSetByIndex.getEntryForIndex((int) highlight.getX());
                if (c(entry, dataSetByIndex)) {
                    Utils.getPosition(centerOffsets, (entry.getY() - this.f5431g.getYChartMin()) * factor * this.f5400b.getPhaseY(), (highlight.getX() * sliceAngle * this.f5400b.getPhaseX()) + this.f5431g.getRotationAngle(), mPPointF);
                    highlight.setDraw(mPPointF.x, mPPointF.y);
                    e(canvas, mPPointF.x, mPPointF.y, dataSetByIndex);
                    if (dataSetByIndex.isDrawHighlightCircleEnabled() && !Float.isNaN(mPPointF.x) && !Float.isNaN(mPPointF.y)) {
                        int highlightCircleStrokeColor = dataSetByIndex.getHighlightCircleStrokeColor();
                        if (highlightCircleStrokeColor == 1122867) {
                            highlightCircleStrokeColor = dataSetByIndex.getColor(i4);
                        }
                        if (dataSetByIndex.getHighlightCircleStrokeAlpha() < 255) {
                            highlightCircleStrokeColor = ColorTemplate.colorWithAlpha(highlightCircleStrokeColor, dataSetByIndex.getHighlightCircleStrokeAlpha());
                        }
                        i2 = i5;
                        i3 = i4;
                        drawHighlightCircle(canvas, mPPointF, dataSetByIndex.getHighlightCircleInnerRadius(), dataSetByIndex.getHighlightCircleOuterRadius(), dataSetByIndex.getHighlightCircleFillColor(), highlightCircleStrokeColor, dataSetByIndex.getHighlightCircleStrokeWidth());
                        i5 = i2 + 1;
                        i4 = i3;
                    }
                }
            }
            i2 = i5;
            i3 = i4;
            i5 = i2 + 1;
            i4 = i3;
        }
        MPPointF.recycleInstance(centerOffsets);
        MPPointF.recycleInstance(mPPointF);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        int i2;
        float f2;
        float f3;
        MPPointF mPPointF;
        int i3;
        IRadarDataSet iRadarDataSet;
        int i4;
        float f4;
        float f5;
        MPPointF mPPointF2;
        MPPointF mPPointF3;
        float phaseX = this.f5400b.getPhaseX();
        float phaseY = this.f5400b.getPhaseY();
        float sliceAngle = this.f5431g.getSliceAngle();
        float factor = this.f5431g.getFactor();
        MPPointF centerOffsets = this.f5431g.getCenterOffsets();
        MPPointF mPPointF4 = MPPointF.getInstance(0.0f, 0.0f);
        MPPointF mPPointF5 = MPPointF.getInstance(0.0f, 0.0f);
        float convertDpToPixel = Utils.convertDpToPixel(5.0f);
        int i5 = 0;
        while (i5 < ((RadarData) this.f5431g.getData()).getDataSetCount()) {
            IRadarDataSet dataSetByIndex = ((RadarData) this.f5431g.getData()).getDataSetByIndex(i5);
            if (d(dataSetByIndex)) {
                a(dataSetByIndex);
                MPPointF mPPointF6 = MPPointF.getInstance(dataSetByIndex.getIconsOffset());
                mPPointF6.x = Utils.convertDpToPixel(mPPointF6.x);
                mPPointF6.y = Utils.convertDpToPixel(mPPointF6.y);
                int i6 = 0;
                while (i6 < dataSetByIndex.getEntryCount()) {
                    RadarEntry radarEntry = (RadarEntry) dataSetByIndex.getEntryForIndex(i6);
                    float f6 = i6 * sliceAngle * phaseX;
                    Utils.getPosition(centerOffsets, (radarEntry.getY() - this.f5431g.getYChartMin()) * factor * phaseY, f6 + this.f5431g.getRotationAngle(), mPPointF4);
                    if (dataSetByIndex.isDrawValuesEnabled()) {
                        i3 = i6;
                        f4 = phaseX;
                        mPPointF2 = mPPointF6;
                        iRadarDataSet = dataSetByIndex;
                        i4 = i5;
                        f5 = sliceAngle;
                        mPPointF3 = mPPointF5;
                        drawValue(canvas, dataSetByIndex.getValueFormatter(), radarEntry.getY(), radarEntry, i5, mPPointF4.x, mPPointF4.y - convertDpToPixel, dataSetByIndex.getValueTextColor(i6));
                    } else {
                        i3 = i6;
                        iRadarDataSet = dataSetByIndex;
                        i4 = i5;
                        f4 = phaseX;
                        f5 = sliceAngle;
                        mPPointF2 = mPPointF6;
                        mPPointF3 = mPPointF5;
                    }
                    if (radarEntry.getIcon() != null && iRadarDataSet.isDrawIconsEnabled()) {
                        Drawable icon = radarEntry.getIcon();
                        Utils.getPosition(centerOffsets, (radarEntry.getY() * factor * phaseY) + mPPointF2.y, f6 + this.f5431g.getRotationAngle(), mPPointF3);
                        float f7 = mPPointF3.y + mPPointF2.x;
                        mPPointF3.y = f7;
                        Utils.drawImage(canvas, icon, (int) mPPointF3.x, (int) f7, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                    }
                    i6 = i3 + 1;
                    mPPointF6 = mPPointF2;
                    mPPointF5 = mPPointF3;
                    sliceAngle = f5;
                    i5 = i4;
                    phaseX = f4;
                    dataSetByIndex = iRadarDataSet;
                }
                i2 = i5;
                f2 = phaseX;
                f3 = sliceAngle;
                mPPointF = mPPointF5;
                MPPointF.recycleInstance(mPPointF6);
            } else {
                i2 = i5;
                f2 = phaseX;
                f3 = sliceAngle;
                mPPointF = mPPointF5;
            }
            i5 = i2 + 1;
            mPPointF5 = mPPointF;
            sliceAngle = f3;
            phaseX = f2;
        }
        MPPointF.recycleInstance(centerOffsets);
        MPPointF.recycleInstance(mPPointF4);
        MPPointF.recycleInstance(mPPointF5);
    }

    public Paint getWebPaint() {
        return this.f5432h;
    }

    protected void h(Canvas canvas, IRadarDataSet iRadarDataSet, int i2) {
        float phaseX = this.f5400b.getPhaseX();
        float phaseY = this.f5400b.getPhaseY();
        float sliceAngle = this.f5431g.getSliceAngle();
        float factor = this.f5431g.getFactor();
        MPPointF centerOffsets = this.f5431g.getCenterOffsets();
        MPPointF mPPointF = MPPointF.getInstance(0.0f, 0.0f);
        Path path = this.f5434j;
        path.reset();
        boolean z = false;
        for (int i3 = 0; i3 < iRadarDataSet.getEntryCount(); i3++) {
            this.f5401c.setColor(iRadarDataSet.getColor(i3));
            Utils.getPosition(centerOffsets, (((RadarEntry) iRadarDataSet.getEntryForIndex(i3)).getY() - this.f5431g.getYChartMin()) * factor * phaseY, (i3 * sliceAngle * phaseX) + this.f5431g.getRotationAngle(), mPPointF);
            if (!Float.isNaN(mPPointF.x)) {
                if (z) {
                    path.lineTo(mPPointF.x, mPPointF.y);
                } else {
                    path.moveTo(mPPointF.x, mPPointF.y);
                    z = true;
                }
            }
        }
        if (iRadarDataSet.getEntryCount() > i2) {
            path.lineTo(centerOffsets.x, centerOffsets.y);
        }
        path.close();
        if (iRadarDataSet.isDrawFilledEnabled()) {
            Drawable fillDrawable = iRadarDataSet.getFillDrawable();
            if (fillDrawable != null) {
                g(canvas, path, fillDrawable);
            } else {
                f(canvas, path, iRadarDataSet.getFillColor(), iRadarDataSet.getFillAlpha());
            }
        }
        this.f5401c.setStrokeWidth(iRadarDataSet.getLineWidth());
        this.f5401c.setStyle(Paint.Style.STROKE);
        if (!iRadarDataSet.isDrawFilledEnabled() || iRadarDataSet.getFillAlpha() < 255) {
            canvas.drawPath(path, this.f5401c);
        }
        MPPointF.recycleInstance(centerOffsets);
        MPPointF.recycleInstance(mPPointF);
    }

    protected void i(Canvas canvas) {
        float sliceAngle = this.f5431g.getSliceAngle();
        float factor = this.f5431g.getFactor();
        float rotationAngle = this.f5431g.getRotationAngle();
        MPPointF centerOffsets = this.f5431g.getCenterOffsets();
        this.f5432h.setStrokeWidth(this.f5431g.getWebLineWidth());
        this.f5432h.setColor(this.f5431g.getWebColor());
        this.f5432h.setAlpha(this.f5431g.getWebAlpha());
        int skipWebLineCount = this.f5431g.getSkipWebLineCount() + 1;
        int entryCount = ((RadarData) this.f5431g.getData()).getMaxEntryCountSet().getEntryCount();
        MPPointF mPPointF = MPPointF.getInstance(0.0f, 0.0f);
        for (int i2 = 0; i2 < entryCount; i2 += skipWebLineCount) {
            Utils.getPosition(centerOffsets, this.f5431g.getYRange() * factor, (i2 * sliceAngle) + rotationAngle, mPPointF);
            canvas.drawLine(centerOffsets.x, centerOffsets.y, mPPointF.x, mPPointF.y, this.f5432h);
        }
        MPPointF.recycleInstance(mPPointF);
        this.f5432h.setStrokeWidth(this.f5431g.getWebLineWidthInner());
        this.f5432h.setColor(this.f5431g.getWebColorInner());
        this.f5432h.setAlpha(this.f5431g.getWebAlpha());
        int i3 = this.f5431g.getYAxis().mEntryCount;
        MPPointF mPPointF2 = MPPointF.getInstance(0.0f, 0.0f);
        MPPointF mPPointF3 = MPPointF.getInstance(0.0f, 0.0f);
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = 0;
            while (i5 < ((RadarData) this.f5431g.getData()).getEntryCount()) {
                float yChartMin = (this.f5431g.getYAxis().mEntries[i4] - this.f5431g.getYChartMin()) * factor;
                Utils.getPosition(centerOffsets, yChartMin, (i5 * sliceAngle) + rotationAngle, mPPointF2);
                i5++;
                Utils.getPosition(centerOffsets, yChartMin, (i5 * sliceAngle) + rotationAngle, mPPointF3);
                canvas.drawLine(mPPointF2.x, mPPointF2.y, mPPointF3.x, mPPointF3.y, this.f5432h);
            }
        }
        MPPointF.recycleInstance(mPPointF2);
        MPPointF.recycleInstance(mPPointF3);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
    }
}
