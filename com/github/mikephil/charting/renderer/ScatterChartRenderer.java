package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.ScatterDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.renderer.BarLineScatterCandleBubbleRenderer;
import com.github.mikephil.charting.renderer.scatter.IShapeRenderer;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;
/* loaded from: classes.dex */
public class ScatterChartRenderer extends LineScatterCandleRadarRenderer {

    /* renamed from: g  reason: collision with root package name */
    protected ScatterDataProvider f5437g;

    /* renamed from: h  reason: collision with root package name */
    float[] f5438h;

    public ScatterChartRenderer(ScatterDataProvider scatterDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.f5438h = new float[2];
        this.f5437g = scatterDataProvider;
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        for (T t2 : this.f5437g.getScatterData().getDataSets()) {
            if (t2.isVisible()) {
                f(canvas, t2);
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v2, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        ScatterData scatterData = this.f5437g.getScatterData();
        for (Highlight highlight : highlightArr) {
            IScatterDataSet iScatterDataSet = (IScatterDataSet) scatterData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iScatterDataSet != null && iScatterDataSet.isHighlightEnabled()) {
                ?? entryForXValue = iScatterDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (c(entryForXValue, iScatterDataSet)) {
                    MPPointD pixelForValues = this.f5437g.getTransformer(iScatterDataSet.getAxisDependency()).getPixelForValues(entryForXValue.getX(), entryForXValue.getY() * this.f5400b.getPhaseY());
                    highlight.setDraw((float) pixelForValues.x, (float) pixelForValues.y);
                    e(canvas, (float) pixelForValues.x, (float) pixelForValues.y, iScatterDataSet);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r17v0, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        int i2;
        MPPointF mPPointF;
        if (b(this.f5437g)) {
            List<T> dataSets = this.f5437g.getScatterData().getDataSets();
            for (int i3 = 0; i3 < this.f5437g.getScatterData().getDataSetCount(); i3++) {
                IScatterDataSet iScatterDataSet = (IScatterDataSet) dataSets.get(i3);
                if (d(iScatterDataSet) && iScatterDataSet.getEntryCount() >= 1) {
                    a(iScatterDataSet);
                    this.f5392f.set(this.f5437g, iScatterDataSet);
                    Transformer transformer = this.f5437g.getTransformer(iScatterDataSet.getAxisDependency());
                    float phaseX = this.f5400b.getPhaseX();
                    float phaseY = this.f5400b.getPhaseY();
                    BarLineScatterCandleBubbleRenderer.XBounds xBounds = this.f5392f;
                    float[] generateTransformedValuesScatter = transformer.generateTransformedValuesScatter(iScatterDataSet, phaseX, phaseY, xBounds.min, xBounds.max);
                    float convertDpToPixel = Utils.convertDpToPixel(iScatterDataSet.getScatterShapeSize());
                    MPPointF mPPointF2 = MPPointF.getInstance(iScatterDataSet.getIconsOffset());
                    mPPointF2.x = Utils.convertDpToPixel(mPPointF2.x);
                    mPPointF2.y = Utils.convertDpToPixel(mPPointF2.y);
                    int i4 = 0;
                    while (i4 < generateTransformedValuesScatter.length && this.f5436a.isInBoundsRight(generateTransformedValuesScatter[i4])) {
                        if (this.f5436a.isInBoundsLeft(generateTransformedValuesScatter[i4])) {
                            int i5 = i4 + 1;
                            if (this.f5436a.isInBoundsY(generateTransformedValuesScatter[i5])) {
                                int i6 = i4 / 2;
                                ?? entryForIndex = iScatterDataSet.getEntryForIndex(this.f5392f.min + i6);
                                if (iScatterDataSet.isDrawValuesEnabled()) {
                                    i2 = i4;
                                    mPPointF = mPPointF2;
                                    drawValue(canvas, iScatterDataSet.getValueFormatter(), entryForIndex.getY(), entryForIndex, i3, generateTransformedValuesScatter[i4], generateTransformedValuesScatter[i5] - convertDpToPixel, iScatterDataSet.getValueTextColor(i6 + this.f5392f.min));
                                } else {
                                    i2 = i4;
                                    mPPointF = mPPointF2;
                                }
                                if (entryForIndex.getIcon() != null && iScatterDataSet.isDrawIconsEnabled()) {
                                    Drawable icon = entryForIndex.getIcon();
                                    Utils.drawImage(canvas, icon, (int) (generateTransformedValuesScatter[i2] + mPPointF.x), (int) (generateTransformedValuesScatter[i5] + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                }
                                i4 = i2 + 2;
                                mPPointF2 = mPPointF;
                            }
                        }
                        i2 = i4;
                        mPPointF = mPPointF2;
                        i4 = i2 + 2;
                        mPPointF2 = mPPointF;
                    }
                    MPPointF.recycleInstance(mPPointF2);
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v9, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    protected void f(Canvas canvas, IScatterDataSet iScatterDataSet) {
        int i2;
        if (iScatterDataSet.getEntryCount() < 1) {
            return;
        }
        ViewPortHandler viewPortHandler = this.f5436a;
        Transformer transformer = this.f5437g.getTransformer(iScatterDataSet.getAxisDependency());
        float phaseY = this.f5400b.getPhaseY();
        IShapeRenderer shapeRenderer = iScatterDataSet.getShapeRenderer();
        if (shapeRenderer == null) {
            return;
        }
        int min = (int) Math.min(Math.ceil(iScatterDataSet.getEntryCount() * this.f5400b.getPhaseX()), iScatterDataSet.getEntryCount());
        int i3 = 0;
        while (i3 < min) {
            ?? entryForIndex = iScatterDataSet.getEntryForIndex(i3);
            this.f5438h[0] = entryForIndex.getX();
            this.f5438h[1] = entryForIndex.getY() * phaseY;
            transformer.pointValuesToPixel(this.f5438h);
            if (!viewPortHandler.isInBoundsRight(this.f5438h[0])) {
                return;
            }
            if (viewPortHandler.isInBoundsLeft(this.f5438h[0]) && viewPortHandler.isInBoundsY(this.f5438h[1])) {
                this.f5401c.setColor(iScatterDataSet.getColor(i3 / 2));
                ViewPortHandler viewPortHandler2 = this.f5436a;
                float[] fArr = this.f5438h;
                i2 = i3;
                shapeRenderer.renderShape(canvas, iScatterDataSet, viewPortHandler2, fArr[0], fArr[1], this.f5401c);
            } else {
                i2 = i3;
            }
            i3 = i2 + 1;
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
    }
}
