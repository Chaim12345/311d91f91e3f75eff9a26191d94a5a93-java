package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;
/* loaded from: classes.dex */
public class YAxisRendererHorizontalBarChart extends YAxisRenderer {

    /* renamed from: r  reason: collision with root package name */
    protected Path f5457r;

    /* renamed from: s  reason: collision with root package name */
    protected Path f5458s;

    /* renamed from: t  reason: collision with root package name */
    protected float[] f5459t;

    public YAxisRendererHorizontalBarChart(ViewPortHandler viewPortHandler, YAxis yAxis, Transformer transformer) {
        super(viewPortHandler, yAxis, transformer);
        this.f5457r = new Path();
        this.f5458s = new Path();
        this.f5459t = new float[4];
        this.f5386g.setTextAlign(Paint.Align.LEFT);
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer
    protected void b(Canvas canvas, float f2, float[] fArr, float f3) {
        this.f5384e.setTypeface(this.f5447h.getTypeface());
        this.f5384e.setTextSize(this.f5447h.getTextSize());
        this.f5384e.setColor(this.f5447h.getTextColor());
        int i2 = this.f5447h.isDrawTopYLabelEntryEnabled() ? this.f5447h.mEntryCount : this.f5447h.mEntryCount - 1;
        for (int i3 = !this.f5447h.isDrawBottomYLabelEntryEnabled(); i3 < i2; i3++) {
            canvas.drawText(this.f5447h.getFormattedLabel(i3), fArr[i3 * 2], f2 - f3, this.f5384e);
        }
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer
    protected void c(Canvas canvas) {
        int save = canvas.save();
        this.f5453n.set(this.f5436a.getContentRect());
        this.f5453n.inset(-this.f5447h.getZeroLineWidth(), 0.0f);
        canvas.clipRect(this.f5456q);
        MPPointD pixelForValues = this.f5382c.getPixelForValues(0.0f, 0.0f);
        this.f5448i.setColor(this.f5447h.getZeroLineColor());
        this.f5448i.setStrokeWidth(this.f5447h.getZeroLineWidth());
        Path path = this.f5457r;
        path.reset();
        path.moveTo(((float) pixelForValues.x) - 1.0f, this.f5436a.contentTop());
        path.lineTo(((float) pixelForValues.x) - 1.0f, this.f5436a.contentBottom());
        canvas.drawPath(path, this.f5448i);
        canvas.restoreToCount(save);
    }

    @Override // com.github.mikephil.charting.renderer.AxisRenderer
    public void computeAxis(float f2, float f3, boolean z) {
        float f4;
        double d2;
        if (this.f5436a.contentHeight() > 10.0f && !this.f5436a.isFullyZoomedOutX()) {
            MPPointD valuesByTouchPoint = this.f5382c.getValuesByTouchPoint(this.f5436a.contentLeft(), this.f5436a.contentTop());
            MPPointD valuesByTouchPoint2 = this.f5382c.getValuesByTouchPoint(this.f5436a.contentRight(), this.f5436a.contentTop());
            if (z) {
                f4 = (float) valuesByTouchPoint2.x;
                d2 = valuesByTouchPoint.x;
            } else {
                f4 = (float) valuesByTouchPoint.x;
                d2 = valuesByTouchPoint2.x;
            }
            MPPointD.recycleInstance(valuesByTouchPoint);
            MPPointD.recycleInstance(valuesByTouchPoint2);
            f2 = f4;
            f3 = (float) d2;
        }
        a(f2, f3);
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer
    protected float[] d() {
        int length = this.f5451l.length;
        int i2 = this.f5447h.mEntryCount;
        if (length != i2 * 2) {
            this.f5451l = new float[i2 * 2];
        }
        float[] fArr = this.f5451l;
        for (int i3 = 0; i3 < fArr.length; i3 += 2) {
            fArr[i3] = this.f5447h.mEntries[i3 / 2];
        }
        this.f5382c.pointValuesToPixel(fArr);
        return fArr;
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer
    protected Path e(Path path, int i2, float[] fArr) {
        path.moveTo(fArr[i2], this.f5436a.contentTop());
        path.lineTo(fArr[i2], this.f5436a.contentBottom());
        return path;
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer
    public RectF getGridClippingRect() {
        this.f5450k.set(this.f5436a.getContentRect());
        this.f5450k.inset(-this.f5381b.getGridLineWidth(), 0.0f);
        return this.f5450k;
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderAxisLabels(Canvas canvas) {
        float contentBottom;
        if (this.f5447h.isEnabled() && this.f5447h.isDrawLabelsEnabled()) {
            float[] d2 = d();
            this.f5384e.setTypeface(this.f5447h.getTypeface());
            this.f5384e.setTextSize(this.f5447h.getTextSize());
            this.f5384e.setColor(this.f5447h.getTextColor());
            this.f5384e.setTextAlign(Paint.Align.CENTER);
            float convertDpToPixel = Utils.convertDpToPixel(2.5f);
            float calcTextHeight = Utils.calcTextHeight(this.f5384e, "Q");
            YAxis.AxisDependency axisDependency = this.f5447h.getAxisDependency();
            this.f5447h.getLabelPosition();
            if (axisDependency == YAxis.AxisDependency.LEFT) {
                YAxis.YAxisLabelPosition yAxisLabelPosition = YAxis.YAxisLabelPosition.OUTSIDE_CHART;
                contentBottom = this.f5436a.contentTop() - convertDpToPixel;
            } else {
                YAxis.YAxisLabelPosition yAxisLabelPosition2 = YAxis.YAxisLabelPosition.OUTSIDE_CHART;
                contentBottom = this.f5436a.contentBottom() + calcTextHeight + convertDpToPixel;
            }
            b(canvas, contentBottom, d2, this.f5447h.getYOffset());
        }
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderAxisLine(Canvas canvas) {
        float contentLeft;
        float contentBottom;
        float contentRight;
        float contentBottom2;
        if (this.f5447h.isEnabled() && this.f5447h.isDrawAxisLineEnabled()) {
            this.f5385f.setColor(this.f5447h.getAxisLineColor());
            this.f5385f.setStrokeWidth(this.f5447h.getAxisLineWidth());
            if (this.f5447h.getAxisDependency() == YAxis.AxisDependency.LEFT) {
                contentLeft = this.f5436a.contentLeft();
                contentBottom = this.f5436a.contentTop();
                contentRight = this.f5436a.contentRight();
                contentBottom2 = this.f5436a.contentTop();
            } else {
                contentLeft = this.f5436a.contentLeft();
                contentBottom = this.f5436a.contentBottom();
                contentRight = this.f5436a.contentRight();
                contentBottom2 = this.f5436a.contentBottom();
            }
            canvas.drawLine(contentLeft, contentBottom, contentRight, contentBottom2, this.f5385f);
        }
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderLimitLines(Canvas canvas) {
        float f2;
        float calcTextHeight;
        float f3;
        List<LimitLine> limitLines = this.f5447h.getLimitLines();
        if (limitLines == null || limitLines.size() <= 0) {
            return;
        }
        float[] fArr = this.f5459t;
        float f4 = 0.0f;
        fArr[0] = 0.0f;
        char c2 = 1;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        Path path = this.f5458s;
        path.reset();
        int i2 = 0;
        while (i2 < limitLines.size()) {
            LimitLine limitLine = limitLines.get(i2);
            if (limitLine.isEnabled()) {
                int save = canvas.save();
                this.f5456q.set(this.f5436a.getContentRect());
                this.f5456q.inset(-limitLine.getLineWidth(), f4);
                canvas.clipRect(this.f5456q);
                fArr[0] = limitLine.getLimit();
                fArr[2] = limitLine.getLimit();
                this.f5382c.pointValuesToPixel(fArr);
                fArr[c2] = this.f5436a.contentTop();
                fArr[3] = this.f5436a.contentBottom();
                path.moveTo(fArr[0], fArr[c2]);
                path.lineTo(fArr[2], fArr[3]);
                this.f5386g.setStyle(Paint.Style.STROKE);
                this.f5386g.setColor(limitLine.getLineColor());
                this.f5386g.setPathEffect(limitLine.getDashPathEffect());
                this.f5386g.setStrokeWidth(limitLine.getLineWidth());
                canvas.drawPath(path, this.f5386g);
                path.reset();
                String label = limitLine.getLabel();
                if (label != null && !label.equals("")) {
                    this.f5386g.setStyle(limitLine.getTextStyle());
                    this.f5386g.setPathEffect(null);
                    this.f5386g.setColor(limitLine.getTextColor());
                    this.f5386g.setTypeface(limitLine.getTypeface());
                    this.f5386g.setStrokeWidth(0.5f);
                    this.f5386g.setTextSize(limitLine.getTextSize());
                    float lineWidth = limitLine.getLineWidth() + limitLine.getXOffset();
                    float convertDpToPixel = Utils.convertDpToPixel(2.0f) + limitLine.getYOffset();
                    LimitLine.LimitLabelPosition labelPosition = limitLine.getLabelPosition();
                    if (labelPosition == LimitLine.LimitLabelPosition.RIGHT_TOP) {
                        calcTextHeight = Utils.calcTextHeight(this.f5386g, label);
                        this.f5386g.setTextAlign(Paint.Align.LEFT);
                        f3 = fArr[0] + lineWidth;
                    } else {
                        if (labelPosition == LimitLine.LimitLabelPosition.RIGHT_BOTTOM) {
                            this.f5386g.setTextAlign(Paint.Align.LEFT);
                            f2 = fArr[0] + lineWidth;
                        } else if (labelPosition == LimitLine.LimitLabelPosition.LEFT_TOP) {
                            this.f5386g.setTextAlign(Paint.Align.RIGHT);
                            calcTextHeight = Utils.calcTextHeight(this.f5386g, label);
                            f3 = fArr[0] - lineWidth;
                        } else {
                            this.f5386g.setTextAlign(Paint.Align.RIGHT);
                            f2 = fArr[0] - lineWidth;
                        }
                        canvas.drawText(label, f2, this.f5436a.contentBottom() - convertDpToPixel, this.f5386g);
                    }
                    canvas.drawText(label, f3, this.f5436a.contentTop() + convertDpToPixel + calcTextHeight, this.f5386g);
                }
                canvas.restoreToCount(save);
            }
            i2++;
            f4 = 0.0f;
            c2 = 1;
        }
    }
}
