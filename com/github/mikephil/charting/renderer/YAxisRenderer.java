package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;
/* loaded from: classes.dex */
public class YAxisRenderer extends AxisRenderer {

    /* renamed from: h  reason: collision with root package name */
    protected YAxis f5447h;

    /* renamed from: i  reason: collision with root package name */
    protected Paint f5448i;

    /* renamed from: j  reason: collision with root package name */
    protected Path f5449j;

    /* renamed from: k  reason: collision with root package name */
    protected RectF f5450k;

    /* renamed from: l  reason: collision with root package name */
    protected float[] f5451l;

    /* renamed from: m  reason: collision with root package name */
    protected Path f5452m;

    /* renamed from: n  reason: collision with root package name */
    protected RectF f5453n;

    /* renamed from: o  reason: collision with root package name */
    protected Path f5454o;

    /* renamed from: p  reason: collision with root package name */
    protected float[] f5455p;

    /* renamed from: q  reason: collision with root package name */
    protected RectF f5456q;

    public YAxisRenderer(ViewPortHandler viewPortHandler, YAxis yAxis, Transformer transformer) {
        super(viewPortHandler, transformer, yAxis);
        this.f5449j = new Path();
        this.f5450k = new RectF();
        this.f5451l = new float[2];
        this.f5452m = new Path();
        this.f5453n = new RectF();
        this.f5454o = new Path();
        this.f5455p = new float[2];
        this.f5456q = new RectF();
        this.f5447h = yAxis;
        if (this.f5436a != null) {
            this.f5384e.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.f5384e.setTextSize(Utils.convertDpToPixel(10.0f));
            Paint paint = new Paint(1);
            this.f5448i = paint;
            paint.setColor(-7829368);
            this.f5448i.setStrokeWidth(1.0f);
            this.f5448i.setStyle(Paint.Style.STROKE);
        }
    }

    protected void b(Canvas canvas, float f2, float[] fArr, float f3) {
        int i2 = this.f5447h.isDrawTopYLabelEntryEnabled() ? this.f5447h.mEntryCount : this.f5447h.mEntryCount - 1;
        for (int i3 = !this.f5447h.isDrawBottomYLabelEntryEnabled(); i3 < i2; i3++) {
            canvas.drawText(this.f5447h.getFormattedLabel(i3), f2, fArr[(i3 * 2) + 1] + f3, this.f5384e);
        }
    }

    protected void c(Canvas canvas) {
        int save = canvas.save();
        this.f5453n.set(this.f5436a.getContentRect());
        this.f5453n.inset(0.0f, -this.f5447h.getZeroLineWidth());
        canvas.clipRect(this.f5453n);
        MPPointD pixelForValues = this.f5382c.getPixelForValues(0.0f, 0.0f);
        this.f5448i.setColor(this.f5447h.getZeroLineColor());
        this.f5448i.setStrokeWidth(this.f5447h.getZeroLineWidth());
        Path path = this.f5452m;
        path.reset();
        path.moveTo(this.f5436a.contentLeft(), (float) pixelForValues.y);
        path.lineTo(this.f5436a.contentRight(), (float) pixelForValues.y);
        canvas.drawPath(path, this.f5448i);
        canvas.restoreToCount(save);
    }

    protected float[] d() {
        int length = this.f5451l.length;
        int i2 = this.f5447h.mEntryCount;
        if (length != i2 * 2) {
            this.f5451l = new float[i2 * 2];
        }
        float[] fArr = this.f5451l;
        for (int i3 = 0; i3 < fArr.length; i3 += 2) {
            fArr[i3 + 1] = this.f5447h.mEntries[i3 / 2];
        }
        this.f5382c.pointValuesToPixel(fArr);
        return fArr;
    }

    protected Path e(Path path, int i2, float[] fArr) {
        int i3 = i2 + 1;
        path.moveTo(this.f5436a.offsetLeft(), fArr[i3]);
        path.lineTo(this.f5436a.contentRight(), fArr[i3]);
        return path;
    }

    public RectF getGridClippingRect() {
        this.f5450k.set(this.f5436a.getContentRect());
        this.f5450k.inset(0.0f, -this.f5381b.getGridLineWidth());
        return this.f5450k;
    }

    @Override // com.github.mikephil.charting.renderer.AxisRenderer
    public void renderAxisLabels(Canvas canvas) {
        float contentRight;
        float contentRight2;
        float f2;
        if (this.f5447h.isEnabled() && this.f5447h.isDrawLabelsEnabled()) {
            float[] d2 = d();
            this.f5384e.setTypeface(this.f5447h.getTypeface());
            this.f5384e.setTextSize(this.f5447h.getTextSize());
            this.f5384e.setColor(this.f5447h.getTextColor());
            float xOffset = this.f5447h.getXOffset();
            float calcTextHeight = (Utils.calcTextHeight(this.f5384e, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS) / 2.5f) + this.f5447h.getYOffset();
            YAxis.AxisDependency axisDependency = this.f5447h.getAxisDependency();
            YAxis.YAxisLabelPosition labelPosition = this.f5447h.getLabelPosition();
            if (axisDependency == YAxis.AxisDependency.LEFT) {
                if (labelPosition == YAxis.YAxisLabelPosition.OUTSIDE_CHART) {
                    this.f5384e.setTextAlign(Paint.Align.RIGHT);
                    contentRight = this.f5436a.offsetLeft();
                    f2 = contentRight - xOffset;
                } else {
                    this.f5384e.setTextAlign(Paint.Align.LEFT);
                    contentRight2 = this.f5436a.offsetLeft();
                    f2 = contentRight2 + xOffset;
                }
            } else if (labelPosition == YAxis.YAxisLabelPosition.OUTSIDE_CHART) {
                this.f5384e.setTextAlign(Paint.Align.LEFT);
                contentRight2 = this.f5436a.contentRight();
                f2 = contentRight2 + xOffset;
            } else {
                this.f5384e.setTextAlign(Paint.Align.RIGHT);
                contentRight = this.f5436a.contentRight();
                f2 = contentRight - xOffset;
            }
            b(canvas, f2, d2, calcTextHeight);
        }
    }

    @Override // com.github.mikephil.charting.renderer.AxisRenderer
    public void renderAxisLine(Canvas canvas) {
        float contentRight;
        float contentTop;
        float contentRight2;
        if (this.f5447h.isEnabled() && this.f5447h.isDrawAxisLineEnabled()) {
            this.f5385f.setColor(this.f5447h.getAxisLineColor());
            this.f5385f.setStrokeWidth(this.f5447h.getAxisLineWidth());
            if (this.f5447h.getAxisDependency() == YAxis.AxisDependency.LEFT) {
                contentRight = this.f5436a.contentLeft();
                contentTop = this.f5436a.contentTop();
                contentRight2 = this.f5436a.contentLeft();
            } else {
                contentRight = this.f5436a.contentRight();
                contentTop = this.f5436a.contentTop();
                contentRight2 = this.f5436a.contentRight();
            }
            canvas.drawLine(contentRight, contentTop, contentRight2, this.f5436a.contentBottom(), this.f5385f);
        }
    }

    @Override // com.github.mikephil.charting.renderer.AxisRenderer
    public void renderGridLines(Canvas canvas) {
        if (this.f5447h.isEnabled()) {
            if (this.f5447h.isDrawGridLinesEnabled()) {
                int save = canvas.save();
                canvas.clipRect(getGridClippingRect());
                float[] d2 = d();
                this.f5383d.setColor(this.f5447h.getGridColor());
                this.f5383d.setStrokeWidth(this.f5447h.getGridLineWidth());
                this.f5383d.setPathEffect(this.f5447h.getGridDashPathEffect());
                Path path = this.f5449j;
                path.reset();
                for (int i2 = 0; i2 < d2.length; i2 += 2) {
                    canvas.drawPath(e(path, i2, d2), this.f5383d);
                    path.reset();
                }
                canvas.restoreToCount(save);
            }
            if (this.f5447h.isDrawZeroLineEnabled()) {
                c(canvas);
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.AxisRenderer
    public void renderLimitLines(Canvas canvas) {
        float offsetLeft;
        float f2;
        float contentLeft;
        float f3;
        List<LimitLine> limitLines = this.f5447h.getLimitLines();
        if (limitLines == null || limitLines.size() <= 0) {
            return;
        }
        float[] fArr = this.f5455p;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        Path path = this.f5454o;
        path.reset();
        for (int i2 = 0; i2 < limitLines.size(); i2++) {
            LimitLine limitLine = limitLines.get(i2);
            if (limitLine.isEnabled()) {
                int save = canvas.save();
                this.f5456q.set(this.f5436a.getContentRect());
                this.f5456q.inset(0.0f, -limitLine.getLineWidth());
                canvas.clipRect(this.f5456q);
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
                    this.f5386g.setTypeface(limitLine.getTypeface());
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
