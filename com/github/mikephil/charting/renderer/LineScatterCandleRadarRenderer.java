package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Path;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
/* loaded from: classes.dex */
public abstract class LineScatterCandleRadarRenderer extends BarLineScatterCandleBubbleRenderer {
    private Path mHighlightLinePath;

    public LineScatterCandleRadarRenderer(ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mHighlightLinePath = new Path();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e(Canvas canvas, float f2, float f3, ILineScatterCandleRadarDataSet iLineScatterCandleRadarDataSet) {
        this.f5402d.setColor(iLineScatterCandleRadarDataSet.getHighLightColor());
        this.f5402d.setStrokeWidth(iLineScatterCandleRadarDataSet.getHighlightLineWidth());
        this.f5402d.setPathEffect(iLineScatterCandleRadarDataSet.getDashPathEffectHighlight());
        if (iLineScatterCandleRadarDataSet.isVerticalHighlightIndicatorEnabled()) {
            this.mHighlightLinePath.reset();
            this.mHighlightLinePath.moveTo(f2, this.f5436a.contentTop());
            this.mHighlightLinePath.lineTo(f2, this.f5436a.contentBottom());
            canvas.drawPath(this.mHighlightLinePath, this.f5402d);
        }
        if (iLineScatterCandleRadarDataSet.isHorizontalHighlightIndicatorEnabled()) {
            this.mHighlightLinePath.reset();
            this.mHighlightLinePath.moveTo(this.f5436a.contentLeft(), f3);
            this.mHighlightLinePath.lineTo(this.f5436a.contentRight(), f3);
            canvas.drawPath(this.mHighlightLinePath, this.f5402d);
        }
    }
}
