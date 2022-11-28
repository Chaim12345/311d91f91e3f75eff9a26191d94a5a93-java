package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;
/* loaded from: classes.dex */
public class XAxisRendererHorizontalBarChart extends XAxisRenderer {

    /* renamed from: o  reason: collision with root package name */
    protected Path f5446o;

    public XAxisRendererHorizontalBarChart(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer transformer, BarChart barChart) {
        super(viewPortHandler, xAxis, transformer);
        this.f5446o = new Path();
    }

    @Override // com.github.mikephil.charting.renderer.XAxisRenderer
    protected void b() {
        this.f5384e.setTypeface(this.f5439h.getTypeface());
        this.f5384e.setTextSize(this.f5439h.getTextSize());
        FSize calcTextSize = Utils.calcTextSize(this.f5384e, this.f5439h.getLongestLabel());
        float f2 = calcTextSize.height;
        FSize sizeOfRotatedRectangleByDegrees = Utils.getSizeOfRotatedRectangleByDegrees(calcTextSize.width, f2, this.f5439h.getLabelRotationAngle());
        this.f5439h.mLabelWidth = Math.round((int) (calcTextSize.width + (this.f5439h.getXOffset() * 3.5f)));
        this.f5439h.mLabelHeight = Math.round(f2);
        XAxis xAxis = this.f5439h;
        xAxis.mLabelRotatedWidth = (int) (sizeOfRotatedRectangleByDegrees.width + (xAxis.getXOffset() * 3.5f));
        this.f5439h.mLabelRotatedHeight = Math.round(sizeOfRotatedRectangleByDegrees.height);
        FSize.recycleInstance(sizeOfRotatedRectangleByDegrees);
    }

    @Override // com.github.mikephil.charting.renderer.XAxisRenderer
    protected void c(Canvas canvas, float f2, float f3, Path path) {
        path.moveTo(this.f5436a.contentRight(), f3);
        path.lineTo(this.f5436a.contentLeft(), f3);
        canvas.drawPath(path, this.f5383d);
        path.reset();
    }

    @Override // com.github.mikephil.charting.renderer.XAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void computeAxis(float f2, float f3, boolean z) {
        float f4;
        double d2;
        if (this.f5436a.contentWidth() > 10.0f && !this.f5436a.isFullyZoomedOutY()) {
            MPPointD valuesByTouchPoint = this.f5382c.getValuesByTouchPoint(this.f5436a.contentLeft(), this.f5436a.contentBottom());
            MPPointD valuesByTouchPoint2 = this.f5382c.getValuesByTouchPoint(this.f5436a.contentLeft(), this.f5436a.contentTop());
            if (z) {
                f4 = (float) valuesByTouchPoint2.y;
                d2 = valuesByTouchPoint.y;
            } else {
                f4 = (float) valuesByTouchPoint.y;
                d2 = valuesByTouchPoint2.y;
            }
            MPPointD.recycleInstance(valuesByTouchPoint);
            MPPointD.recycleInstance(valuesByTouchPoint2);
            f2 = f4;
            f3 = (float) d2;
        }
        a(f2, f3);
    }

    @Override // com.github.mikephil.charting.renderer.XAxisRenderer
    protected void e(Canvas canvas, float f2, MPPointF mPPointF) {
        float labelRotationAngle = this.f5439h.getLabelRotationAngle();
        boolean isCenterAxisLabelsEnabled = this.f5439h.isCenterAxisLabelsEnabled();
        int i2 = this.f5439h.mEntryCount * 2;
        float[] fArr = new float[i2];
        for (int i3 = 0; i3 < i2; i3 += 2) {
            int i4 = i3 + 1;
            XAxis xAxis = this.f5439h;
            if (isCenterAxisLabelsEnabled) {
                fArr[i4] = xAxis.mCenteredEntries[i3 / 2];
            } else {
                fArr[i4] = xAxis.mEntries[i3 / 2];
            }
        }
        this.f5382c.pointValuesToPixel(fArr);
        for (int i5 = 0; i5 < i2; i5 += 2) {
            float f3 = fArr[i5 + 1];
            if (this.f5436a.isInBoundsY(f3)) {
                IAxisValueFormatter valueFormatter = this.f5439h.getValueFormatter();
                XAxis xAxis2 = this.f5439h;
                d(canvas, valueFormatter.getFormattedValue(xAxis2.mEntries[i5 / 2], xAxis2), f2, f3, mPPointF, labelRotationAngle);
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.XAxisRenderer
    public RectF getGridClippingRect() {
        this.f5442k.set(this.f5436a.getContentRect());
        this.f5442k.inset(0.0f, -this.f5381b.getGridLineWidth());
        return this.f5442k;
    }

    @Override // com.github.mikephil.charting.renderer.XAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderAxisLabels(Canvas canvas) {
        float contentLeft;
        float contentLeft2;
        float f2;
        if (this.f5439h.isEnabled() && this.f5439h.isDrawLabelsEnabled()) {
            float xOffset = this.f5439h.getXOffset();
            this.f5384e.setTypeface(this.f5439h.getTypeface());
            this.f5384e.setTextSize(this.f5439h.getTextSize());
            this.f5384e.setColor(this.f5439h.getTextColor());
            MPPointF mPPointF = MPPointF.getInstance(0.0f, 0.0f);
            if (this.f5439h.getPosition() != XAxis.XAxisPosition.TOP) {
                if (this.f5439h.getPosition() == XAxis.XAxisPosition.TOP_INSIDE) {
                    mPPointF.x = 1.0f;
                    mPPointF.y = 0.5f;
                    contentLeft2 = this.f5436a.contentRight();
                } else {
                    if (this.f5439h.getPosition() != XAxis.XAxisPosition.BOTTOM) {
                        if (this.f5439h.getPosition() == XAxis.XAxisPosition.BOTTOM_INSIDE) {
                            mPPointF.x = 1.0f;
                            mPPointF.y = 0.5f;
                            contentLeft = this.f5436a.contentLeft();
                        } else {
                            mPPointF.x = 0.0f;
                            mPPointF.y = 0.5f;
                            e(canvas, this.f5436a.contentRight() + xOffset, mPPointF);
                        }
                    }
                    mPPointF.x = 1.0f;
                    mPPointF.y = 0.5f;
                    contentLeft2 = this.f5436a.contentLeft();
                }
                f2 = contentLeft2 - xOffset;
                e(canvas, f2, mPPointF);
                MPPointF.recycleInstance(mPPointF);
            }
            mPPointF.x = 0.0f;
            mPPointF.y = 0.5f;
            contentLeft = this.f5436a.contentRight();
            f2 = contentLeft + xOffset;
            e(canvas, f2, mPPointF);
            MPPointF.recycleInstance(mPPointF);
        }
    }

    @Override // com.github.mikephil.charting.renderer.XAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderAxisLine(Canvas canvas) {
        if (this.f5439h.isDrawAxisLineEnabled() && this.f5439h.isEnabled()) {
            this.f5385f.setColor(this.f5439h.getAxisLineColor());
            this.f5385f.setStrokeWidth(this.f5439h.getAxisLineWidth());
            if (this.f5439h.getPosition() == XAxis.XAxisPosition.TOP || this.f5439h.getPosition() == XAxis.XAxisPosition.TOP_INSIDE || this.f5439h.getPosition() == XAxis.XAxisPosition.BOTH_SIDED) {
                canvas.drawLine(this.f5436a.contentRight(), this.f5436a.contentTop(), this.f5436a.contentRight(), this.f5436a.contentBottom(), this.f5385f);
            }
            if (this.f5439h.getPosition() == XAxis.XAxisPosition.BOTTOM || this.f5439h.getPosition() == XAxis.XAxisPosition.BOTTOM_INSIDE || this.f5439h.getPosition() == XAxis.XAxisPosition.BOTH_SIDED) {
                canvas.drawLine(this.f5436a.contentLeft(), this.f5436a.contentTop(), this.f5436a.contentLeft(), this.f5436a.contentBottom(), this.f5385f);
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.XAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderLimitLines(Canvas canvas) {
        float offsetLeft;
        float f2;
        float contentLeft;
        float f3;
        List<LimitLine> limitLines = this.f5439h.getLimitLines();
        if (limitLines == null || limitLines.size() <= 0) {
            return;
        }
        float[] fArr = this.f5443l;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        Path path = this.f5446o;
        path.reset();
        for (int i2 = 0; i2 < limitLines.size(); i2++) {
            LimitLine limitLine = limitLines.get(i2);
            if (limitLine.isEnabled()) {
                int save = canvas.save();
                this.f5444m.set(this.f5436a.getContentRect());
                this.f5444m.inset(0.0f, -limitLine.getLineWidth());
                canvas.clipRect(this.f5444m);
                this.f5386g.setStyle(Paint.Style.STROKE);
                this.f5386g.setColor(limitLine.getLineColor());
                this.f5386g.setStrokeWidth(limitLine.getLineWidth());
                this.f5386g.setPathEffect(limitLine.getDashPathEffect());
                fArr[1] = limitLine.getLimit();
                this.f5382c.pointValuesToPixel(fArr);
                path.moveTo(this.f5436a.contentLeft(), fArr[1]);
                path.lineTo(this.f5436a.contentRight(), fArr[1]);
                canvas.drawPath(path, this.f5386g);
                path.reset();
                String label = limitLine.getLabel();
                if (label != null && !label.equals("")) {
                    this.f5386g.setStyle(limitLine.getTextStyle());
                    this.f5386g.setPathEffect(null);
                    this.f5386g.setColor(limitLine.getTextColor());
                    this.f5386g.setStrokeWidth(0.5f);
                    this.f5386g.setTextSize(limitLine.getTextSize());
                    float calcTextHeight = Utils.calcTextHeight(this.f5386g, label);
                    float convertDpToPixel = Utils.convertDpToPixel(4.0f) + limitLine.getXOffset();
                    float lineWidth = limitLine.getLineWidth() + calcTextHeight + limitLine.getYOffset();
                    LimitLine.LimitLabelPosition labelPosition = limitLine.getLabelPosition();
                    if (labelPosition == LimitLine.LimitLabelPosition.RIGHT_TOP) {
                        this.f5386g.setTextAlign(Paint.Align.RIGHT);
                        contentLeft = this.f5436a.contentRight() - convertDpToPixel;
                        f3 = fArr[1];
                    } else {
                        if (labelPosition == LimitLine.LimitLabelPosition.RIGHT_BOTTOM) {
                            this.f5386g.setTextAlign(Paint.Align.RIGHT);
                            offsetLeft = this.f5436a.contentRight() - convertDpToPixel;
                            f2 = fArr[1];
                        } else if (labelPosition == LimitLine.LimitLabelPosition.LEFT_TOP) {
                            this.f5386g.setTextAlign(Paint.Align.LEFT);
                            contentLeft = this.f5436a.contentLeft() + convertDpToPixel;
                            f3 = fArr[1];
                        } else {
                            this.f5386g.setTextAlign(Paint.Align.LEFT);
                            offsetLeft = this.f5436a.offsetLeft() + convertDpToPixel;
                            f2 = fArr[1];
                        }
                        canvas.drawText(label, offsetLeft, f2 + lineWidth, this.f5386g);
                    }
                    canvas.drawText(label, contentLeft, (f3 - lineWidth) + calcTextHeight, this.f5386g);
                }
                canvas.restoreToCount(save);
            }
        }
    }
}
