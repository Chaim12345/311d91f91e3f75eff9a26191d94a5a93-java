package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.core.view.ViewCompat;
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
public class XAxisRenderer extends AxisRenderer {

    /* renamed from: h  reason: collision with root package name */
    protected XAxis f5439h;

    /* renamed from: i  reason: collision with root package name */
    protected Path f5440i;

    /* renamed from: j  reason: collision with root package name */
    protected float[] f5441j;

    /* renamed from: k  reason: collision with root package name */
    protected RectF f5442k;

    /* renamed from: l  reason: collision with root package name */
    protected float[] f5443l;

    /* renamed from: m  reason: collision with root package name */
    protected RectF f5444m;
    private Path mLimitLinePath;

    /* renamed from: n  reason: collision with root package name */
    float[] f5445n;

    public XAxisRenderer(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer transformer) {
        super(viewPortHandler, transformer, xAxis);
        this.f5440i = new Path();
        this.f5441j = new float[2];
        this.f5442k = new RectF();
        this.f5443l = new float[2];
        this.f5444m = new RectF();
        this.f5445n = new float[4];
        this.mLimitLinePath = new Path();
        this.f5439h = xAxis;
        this.f5384e.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.f5384e.setTextAlign(Paint.Align.CENTER);
        this.f5384e.setTextSize(Utils.convertDpToPixel(10.0f));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.renderer.AxisRenderer
    public void a(float f2, float f3) {
        super.a(f2, f3);
        b();
    }

    protected void b() {
        String longestLabel = this.f5439h.getLongestLabel();
        this.f5384e.setTypeface(this.f5439h.getTypeface());
        this.f5384e.setTextSize(this.f5439h.getTextSize());
        FSize calcTextSize = Utils.calcTextSize(this.f5384e, longestLabel);
        float f2 = calcTextSize.width;
        float calcTextHeight = Utils.calcTextHeight(this.f5384e, "Q");
        FSize sizeOfRotatedRectangleByDegrees = Utils.getSizeOfRotatedRectangleByDegrees(f2, calcTextHeight, this.f5439h.getLabelRotationAngle());
        this.f5439h.mLabelWidth = Math.round(f2);
        this.f5439h.mLabelHeight = Math.round(calcTextHeight);
        this.f5439h.mLabelRotatedWidth = Math.round(sizeOfRotatedRectangleByDegrees.width);
        this.f5439h.mLabelRotatedHeight = Math.round(sizeOfRotatedRectangleByDegrees.height);
        FSize.recycleInstance(sizeOfRotatedRectangleByDegrees);
        FSize.recycleInstance(calcTextSize);
    }

    protected void c(Canvas canvas, float f2, float f3, Path path) {
        path.moveTo(f2, this.f5436a.contentBottom());
        path.lineTo(f2, this.f5436a.contentTop());
        canvas.drawPath(path, this.f5383d);
        path.reset();
    }

    @Override // com.github.mikephil.charting.renderer.AxisRenderer
    public void computeAxis(float f2, float f3, boolean z) {
        float f4;
        double d2;
        if (this.f5436a.contentWidth() > 10.0f && !this.f5436a.isFullyZoomedOutX()) {
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

    /* JADX INFO: Access modifiers changed from: protected */
    public void d(Canvas canvas, String str, float f2, float f3, MPPointF mPPointF, float f4) {
        Utils.drawXAxisValue(canvas, str, f2, f3, this.f5384e, mPPointF, f4);
    }

    protected void e(Canvas canvas, float f2, MPPointF mPPointF) {
        float labelRotationAngle = this.f5439h.getLabelRotationAngle();
        boolean isCenterAxisLabelsEnabled = this.f5439h.isCenterAxisLabelsEnabled();
        int i2 = this.f5439h.mEntryCount * 2;
        float[] fArr = new float[i2];
        for (int i3 = 0; i3 < i2; i3 += 2) {
            XAxis xAxis = this.f5439h;
            if (isCenterAxisLabelsEnabled) {
                fArr[i3] = xAxis.mCenteredEntries[i3 / 2];
            } else {
                fArr[i3] = xAxis.mEntries[i3 / 2];
            }
        }
        this.f5382c.pointValuesToPixel(fArr);
        for (int i4 = 0; i4 < i2; i4 += 2) {
            float f3 = fArr[i4];
            if (this.f5436a.isInBoundsX(f3)) {
                IAxisValueFormatter valueFormatter = this.f5439h.getValueFormatter();
                XAxis xAxis2 = this.f5439h;
                int i5 = i4 / 2;
                String formattedValue = valueFormatter.getFormattedValue(xAxis2.mEntries[i5], xAxis2);
                if (this.f5439h.isAvoidFirstLastClippingEnabled()) {
                    int i6 = this.f5439h.mEntryCount;
                    if (i5 == i6 - 1 && i6 > 1) {
                        float calcTextWidth = Utils.calcTextWidth(this.f5384e, formattedValue);
                        if (calcTextWidth > this.f5436a.offsetRight() * 2.0f && f3 + calcTextWidth > this.f5436a.getChartWidth()) {
                            f3 -= calcTextWidth / 2.0f;
                        }
                    } else if (i4 == 0) {
                        f3 += Utils.calcTextWidth(this.f5384e, formattedValue) / 2.0f;
                    }
                }
                d(canvas, formattedValue, f3, f2, mPPointF, labelRotationAngle);
            }
        }
    }

    protected void f() {
        this.f5383d.setColor(this.f5439h.getGridColor());
        this.f5383d.setStrokeWidth(this.f5439h.getGridLineWidth());
        this.f5383d.setPathEffect(this.f5439h.getGridDashPathEffect());
    }

    public RectF getGridClippingRect() {
        this.f5442k.set(this.f5436a.getContentRect());
        this.f5442k.inset(-this.f5381b.getGridLineWidth(), 0.0f);
        return this.f5442k;
    }

    @Override // com.github.mikephil.charting.renderer.AxisRenderer
    public void renderAxisLabels(Canvas canvas) {
        float contentBottom;
        float contentBottom2;
        float f2;
        if (this.f5439h.isEnabled() && this.f5439h.isDrawLabelsEnabled()) {
            float yOffset = this.f5439h.getYOffset();
            this.f5384e.setTypeface(this.f5439h.getTypeface());
            this.f5384e.setTextSize(this.f5439h.getTextSize());
            this.f5384e.setColor(this.f5439h.getTextColor());
            MPPointF mPPointF = MPPointF.getInstance(0.0f, 0.0f);
            if (this.f5439h.getPosition() != XAxis.XAxisPosition.TOP) {
                if (this.f5439h.getPosition() == XAxis.XAxisPosition.TOP_INSIDE) {
                    mPPointF.x = 0.5f;
                    mPPointF.y = 1.0f;
                    contentBottom2 = this.f5436a.contentTop() + yOffset;
                    yOffset = this.f5439h.mLabelRotatedHeight;
                } else {
                    if (this.f5439h.getPosition() != XAxis.XAxisPosition.BOTTOM) {
                        XAxis.XAxisPosition position = this.f5439h.getPosition();
                        XAxis.XAxisPosition xAxisPosition = XAxis.XAxisPosition.BOTTOM_INSIDE;
                        mPPointF.x = 0.5f;
                        if (position == xAxisPosition) {
                            mPPointF.y = 0.0f;
                            contentBottom = this.f5436a.contentBottom() - yOffset;
                            yOffset = this.f5439h.mLabelRotatedHeight;
                        } else {
                            mPPointF.y = 1.0f;
                            e(canvas, this.f5436a.contentTop() - yOffset, mPPointF);
                        }
                    }
                    mPPointF.x = 0.5f;
                    mPPointF.y = 0.0f;
                    contentBottom2 = this.f5436a.contentBottom();
                }
                f2 = contentBottom2 + yOffset;
                e(canvas, f2, mPPointF);
                MPPointF.recycleInstance(mPPointF);
            }
            mPPointF.x = 0.5f;
            mPPointF.y = 1.0f;
            contentBottom = this.f5436a.contentTop();
            f2 = contentBottom - yOffset;
            e(canvas, f2, mPPointF);
            MPPointF.recycleInstance(mPPointF);
        }
    }

    @Override // com.github.mikephil.charting.renderer.AxisRenderer
    public void renderAxisLine(Canvas canvas) {
        if (this.f5439h.isDrawAxisLineEnabled() && this.f5439h.isEnabled()) {
            this.f5385f.setColor(this.f5439h.getAxisLineColor());
            this.f5385f.setStrokeWidth(this.f5439h.getAxisLineWidth());
            this.f5385f.setPathEffect(this.f5439h.getAxisLineDashPathEffect());
            if (this.f5439h.getPosition() == XAxis.XAxisPosition.TOP || this.f5439h.getPosition() == XAxis.XAxisPosition.TOP_INSIDE || this.f5439h.getPosition() == XAxis.XAxisPosition.BOTH_SIDED) {
                canvas.drawLine(this.f5436a.contentLeft(), this.f5436a.contentTop(), this.f5436a.contentRight(), this.f5436a.contentTop(), this.f5385f);
            }
            if (this.f5439h.getPosition() == XAxis.XAxisPosition.BOTTOM || this.f5439h.getPosition() == XAxis.XAxisPosition.BOTTOM_INSIDE || this.f5439h.getPosition() == XAxis.XAxisPosition.BOTH_SIDED) {
                canvas.drawLine(this.f5436a.contentLeft(), this.f5436a.contentBottom(), this.f5436a.contentRight(), this.f5436a.contentBottom(), this.f5385f);
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.AxisRenderer
    public void renderGridLines(Canvas canvas) {
        if (this.f5439h.isDrawGridLinesEnabled() && this.f5439h.isEnabled()) {
            int save = canvas.save();
            canvas.clipRect(getGridClippingRect());
            if (this.f5441j.length != this.f5381b.mEntryCount * 2) {
                this.f5441j = new float[this.f5439h.mEntryCount * 2];
            }
            float[] fArr = this.f5441j;
            for (int i2 = 0; i2 < fArr.length; i2 += 2) {
                float[] fArr2 = this.f5439h.mEntries;
                int i3 = i2 / 2;
                fArr[i2] = fArr2[i3];
                fArr[i2 + 1] = fArr2[i3];
            }
            this.f5382c.pointValuesToPixel(fArr);
            f();
            Path path = this.f5440i;
            path.reset();
            for (int i4 = 0; i4 < fArr.length; i4 += 2) {
                c(canvas, fArr[i4], fArr[i4 + 1], path);
            }
            canvas.restoreToCount(save);
        }
    }

    public void renderLimitLineLabel(Canvas canvas, LimitLine limitLine, float[] fArr, float f2) {
        float f3;
        float calcTextHeight;
        float f4;
        String label = limitLine.getLabel();
        if (label == null || label.equals("")) {
            return;
        }
        this.f5386g.setStyle(limitLine.getTextStyle());
        this.f5386g.setPathEffect(null);
        this.f5386g.setColor(limitLine.getTextColor());
        this.f5386g.setStrokeWidth(0.5f);
        this.f5386g.setTextSize(limitLine.getTextSize());
        float lineWidth = limitLine.getLineWidth() + limitLine.getXOffset();
        LimitLine.LimitLabelPosition labelPosition = limitLine.getLabelPosition();
        if (labelPosition != LimitLine.LimitLabelPosition.RIGHT_TOP) {
            if (labelPosition == LimitLine.LimitLabelPosition.RIGHT_BOTTOM) {
                this.f5386g.setTextAlign(Paint.Align.LEFT);
                f3 = fArr[0] + lineWidth;
            } else if (labelPosition == LimitLine.LimitLabelPosition.LEFT_TOP) {
                this.f5386g.setTextAlign(Paint.Align.RIGHT);
                calcTextHeight = Utils.calcTextHeight(this.f5386g, label);
                f4 = fArr[0] - lineWidth;
            } else {
                this.f5386g.setTextAlign(Paint.Align.RIGHT);
                f3 = fArr[0] - lineWidth;
            }
            canvas.drawText(label, f3, this.f5436a.contentBottom() - f2, this.f5386g);
            return;
        }
        calcTextHeight = Utils.calcTextHeight(this.f5386g, label);
        this.f5386g.setTextAlign(Paint.Align.LEFT);
        f4 = fArr[0] + lineWidth;
        canvas.drawText(label, f4, this.f5436a.contentTop() + f2 + calcTextHeight, this.f5386g);
    }

    public void renderLimitLineLine(Canvas canvas, LimitLine limitLine, float[] fArr) {
        float[] fArr2 = this.f5445n;
        fArr2[0] = fArr[0];
        fArr2[1] = this.f5436a.contentTop();
        float[] fArr3 = this.f5445n;
        fArr3[2] = fArr[0];
        fArr3[3] = this.f5436a.contentBottom();
        this.mLimitLinePath.reset();
        Path path = this.mLimitLinePath;
        float[] fArr4 = this.f5445n;
        path.moveTo(fArr4[0], fArr4[1]);
        Path path2 = this.mLimitLinePath;
        float[] fArr5 = this.f5445n;
        path2.lineTo(fArr5[2], fArr5[3]);
        this.f5386g.setStyle(Paint.Style.STROKE);
        this.f5386g.setColor(limitLine.getLineColor());
        this.f5386g.setStrokeWidth(limitLine.getLineWidth());
        this.f5386g.setPathEffect(limitLine.getDashPathEffect());
        canvas.drawPath(this.mLimitLinePath, this.f5386g);
    }

    @Override // com.github.mikephil.charting.renderer.AxisRenderer
    public void renderLimitLines(Canvas canvas) {
        List<LimitLine> limitLines = this.f5439h.getLimitLines();
        if (limitLines == null || limitLines.size() <= 0) {
            return;
        }
        float[] fArr = this.f5443l;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        for (int i2 = 0; i2 < limitLines.size(); i2++) {
            LimitLine limitLine = limitLines.get(i2);
            if (limitLine.isEnabled()) {
                int save = canvas.save();
                this.f5444m.set(this.f5436a.getContentRect());
                this.f5444m.inset(-limitLine.getLineWidth(), 0.0f);
                canvas.clipRect(this.f5444m);
                fArr[0] = limitLine.getLimit();
                fArr[1] = 0.0f;
                this.f5382c.pointValuesToPixel(fArr);
                renderLimitLineLine(canvas, limitLine, fArr);
                renderLimitLineLabel(canvas, limitLine, fArr, limitLine.getYOffset() + 2.0f);
                canvas.restoreToCount(save);
            }
        }
    }
}
