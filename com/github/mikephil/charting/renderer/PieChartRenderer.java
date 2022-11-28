package com.github.mikephil.charting.renderer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.lang.ref.WeakReference;
import java.util.List;
/* loaded from: classes.dex */
public class PieChartRenderer extends DataRenderer {

    /* renamed from: f  reason: collision with root package name */
    protected PieChart f5423f;

    /* renamed from: g  reason: collision with root package name */
    protected Paint f5424g;

    /* renamed from: h  reason: collision with root package name */
    protected Paint f5425h;

    /* renamed from: i  reason: collision with root package name */
    protected Paint f5426i;

    /* renamed from: j  reason: collision with root package name */
    protected WeakReference f5427j;

    /* renamed from: k  reason: collision with root package name */
    protected Canvas f5428k;

    /* renamed from: l  reason: collision with root package name */
    protected Path f5429l;

    /* renamed from: m  reason: collision with root package name */
    protected RectF f5430m;
    private RectF mCenterTextLastBounds;
    private CharSequence mCenterTextLastValue;
    private StaticLayout mCenterTextLayout;
    private TextPaint mCenterTextPaint;
    private Paint mEntryLabelsPaint;
    private Path mHoleCirclePath;
    private RectF mInnerRectBuffer;
    private Path mPathBuffer;
    private RectF[] mRectBuffer;

    public PieChartRenderer(PieChart pieChart, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mCenterTextLastBounds = new RectF();
        this.mRectBuffer = new RectF[]{new RectF(), new RectF(), new RectF()};
        this.mPathBuffer = new Path();
        this.mInnerRectBuffer = new RectF();
        this.mHoleCirclePath = new Path();
        this.f5429l = new Path();
        this.f5430m = new RectF();
        this.f5423f = pieChart;
        Paint paint = new Paint(1);
        this.f5424g = paint;
        paint.setColor(-1);
        this.f5424g.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint(1);
        this.f5425h = paint2;
        paint2.setColor(-1);
        this.f5425h.setStyle(Paint.Style.FILL);
        this.f5425h.setAlpha(105);
        TextPaint textPaint = new TextPaint(1);
        this.mCenterTextPaint = textPaint;
        textPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.mCenterTextPaint.setTextSize(Utils.convertDpToPixel(12.0f));
        this.f5403e.setTextSize(Utils.convertDpToPixel(13.0f));
        this.f5403e.setColor(-1);
        this.f5403e.setTextAlign(Paint.Align.CENTER);
        Paint paint3 = new Paint(1);
        this.mEntryLabelsPaint = paint3;
        paint3.setColor(-1);
        this.mEntryLabelsPaint.setTextAlign(Paint.Align.CENTER);
        this.mEntryLabelsPaint.setTextSize(Utils.convertDpToPixel(13.0f));
        Paint paint4 = new Paint(1);
        this.f5426i = paint4;
        paint4.setStyle(Paint.Style.STROKE);
    }

    protected float c(MPPointF mPPointF, float f2, float f3, float f4, float f5, float f6, float f7) {
        double d2 = (f6 + f7) * 0.017453292f;
        float cos = mPPointF.x + (((float) Math.cos(d2)) * f2);
        float sin = mPPointF.y + (((float) Math.sin(d2)) * f2);
        double d3 = (f6 + (f7 / 2.0f)) * 0.017453292f;
        return (float) ((f2 - ((float) ((Math.sqrt(Math.pow(cos - f4, 2.0d) + Math.pow(sin - f5, 2.0d)) / 2.0d) * Math.tan(((180.0d - f3) / 2.0d) * 0.017453292519943295d)))) - Math.sqrt(Math.pow((mPPointF.x + (((float) Math.cos(d3)) * f2)) - ((cos + f4) / 2.0f), 2.0d) + Math.pow((mPPointF.y + (((float) Math.sin(d3)) * f2)) - ((sin + f5) / 2.0f), 2.0d)));
    }

    protected void d(Canvas canvas) {
        MPPointF mPPointF;
        CharSequence centerText = this.f5423f.getCenterText();
        if (!this.f5423f.isDrawCenterTextEnabled() || centerText == null) {
            return;
        }
        MPPointF centerCircleBox = this.f5423f.getCenterCircleBox();
        MPPointF centerTextOffset = this.f5423f.getCenterTextOffset();
        float f2 = centerCircleBox.x + centerTextOffset.x;
        float f3 = centerCircleBox.y + centerTextOffset.y;
        float radius = (!this.f5423f.isDrawHoleEnabled() || this.f5423f.isDrawSlicesUnderHoleEnabled()) ? this.f5423f.getRadius() : this.f5423f.getRadius() * (this.f5423f.getHoleRadius() / 100.0f);
        RectF[] rectFArr = this.mRectBuffer;
        RectF rectF = rectFArr[0];
        rectF.left = f2 - radius;
        rectF.top = f3 - radius;
        rectF.right = f2 + radius;
        rectF.bottom = f3 + radius;
        RectF rectF2 = rectFArr[1];
        rectF2.set(rectF);
        float centerTextRadiusPercent = this.f5423f.getCenterTextRadiusPercent() / 100.0f;
        if (centerTextRadiusPercent > 0.0d) {
            rectF2.inset((rectF2.width() - (rectF2.width() * centerTextRadiusPercent)) / 2.0f, (rectF2.height() - (rectF2.height() * centerTextRadiusPercent)) / 2.0f);
        }
        if (centerText.equals(this.mCenterTextLastValue) && rectF2.equals(this.mCenterTextLastBounds)) {
            mPPointF = centerTextOffset;
        } else {
            this.mCenterTextLastBounds.set(rectF2);
            this.mCenterTextLastValue = centerText;
            mPPointF = centerTextOffset;
            this.mCenterTextLayout = new StaticLayout(centerText, 0, centerText.length(), this.mCenterTextPaint, (int) Math.max(Math.ceil(this.mCenterTextLastBounds.width()), 1.0d), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        }
        float height = this.mCenterTextLayout.getHeight();
        canvas.save();
        if (Build.VERSION.SDK_INT >= 18) {
            Path path = this.f5429l;
            path.reset();
            path.addOval(rectF, Path.Direction.CW);
            canvas.clipPath(path);
        }
        canvas.translate(rectF2.left, rectF2.top + ((rectF2.height() - height) / 2.0f));
        this.mCenterTextLayout.draw(canvas);
        canvas.restore();
        MPPointF.recycleInstance(centerCircleBox);
        MPPointF.recycleInstance(mPPointF);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        int chartWidth = (int) this.f5436a.getChartWidth();
        int chartHeight = (int) this.f5436a.getChartHeight();
        WeakReference weakReference = this.f5427j;
        Bitmap bitmap = weakReference == null ? null : (Bitmap) weakReference.get();
        if (bitmap == null || bitmap.getWidth() != chartWidth || bitmap.getHeight() != chartHeight) {
            if (chartWidth <= 0 || chartHeight <= 0) {
                return;
            }
            bitmap = Bitmap.createBitmap(chartWidth, chartHeight, Bitmap.Config.ARGB_4444);
            this.f5427j = new WeakReference(bitmap);
            this.f5428k = new Canvas(bitmap);
        }
        bitmap.eraseColor(0);
        for (IPieDataSet iPieDataSet : ((PieData) this.f5423f.getData()).getDataSets()) {
            if (iPieDataSet.isVisible() && iPieDataSet.getEntryCount() > 0) {
                e(canvas, iPieDataSet);
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
        g(canvas);
        canvas.drawBitmap((Bitmap) this.f5427j.get(), 0.0f, 0.0f, (Paint) null);
        d(canvas);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        int i2;
        RectF rectF;
        float f2;
        float f3;
        float f4;
        float[] fArr;
        float[] fArr2;
        IPieDataSet dataSetByIndex;
        float f5;
        int i3;
        float f6;
        float f7;
        int i4;
        int i5;
        float f8;
        float f9;
        Highlight[] highlightArr2 = highlightArr;
        float phaseX = this.f5400b.getPhaseX();
        float phaseY = this.f5400b.getPhaseY();
        float rotationAngle = this.f5423f.getRotationAngle();
        float[] drawAngles = this.f5423f.getDrawAngles();
        float[] absoluteAngles = this.f5423f.getAbsoluteAngles();
        MPPointF centerCircleBox = this.f5423f.getCenterCircleBox();
        float radius = this.f5423f.getRadius();
        boolean z = this.f5423f.isDrawHoleEnabled() && !this.f5423f.isDrawSlicesUnderHoleEnabled();
        float f10 = 0.0f;
        float holeRadius = z ? (this.f5423f.getHoleRadius() / 100.0f) * radius : 0.0f;
        RectF rectF2 = this.f5430m;
        rectF2.set(0.0f, 0.0f, 0.0f, 0.0f);
        int i6 = 0;
        while (i6 < highlightArr2.length) {
            int x = (int) highlightArr2[i6].getX();
            if (x < drawAngles.length && (dataSetByIndex = ((PieData) this.f5423f.getData()).getDataSetByIndex(highlightArr2[i6].getDataSetIndex())) != null && dataSetByIndex.isHighlightEnabled()) {
                int entryCount = dataSetByIndex.getEntryCount();
                int i7 = 0;
                for (int i8 = 0; i8 < entryCount; i8++) {
                    if (Math.abs(dataSetByIndex.getEntryForIndex(i8).getY()) > Utils.FLOAT_EPSILON) {
                        i7++;
                    }
                }
                if (x == 0) {
                    i3 = 1;
                    f5 = 0.0f;
                } else {
                    f5 = absoluteAngles[x - 1] * phaseX;
                    i3 = 1;
                }
                float sliceSpace = i7 <= i3 ? 0.0f : dataSetByIndex.getSliceSpace();
                float f11 = drawAngles[x];
                float selectionShift = dataSetByIndex.getSelectionShift();
                float f12 = radius + selectionShift;
                int i9 = i6;
                rectF2.set(this.f5423f.getCircleBox());
                float f13 = -selectionShift;
                rectF2.inset(f13, f13);
                boolean z2 = sliceSpace > 0.0f && f11 <= 180.0f;
                this.f5401c.setColor(dataSetByIndex.getColor(x));
                float f14 = i7 == 1 ? 0.0f : sliceSpace / (radius * 0.017453292f);
                float f15 = i7 == 1 ? 0.0f : sliceSpace / (f12 * 0.017453292f);
                float f16 = rotationAngle + ((f5 + (f14 / 2.0f)) * phaseY);
                float f17 = (f11 - f14) * phaseY;
                float f18 = f17 < 0.0f ? 0.0f : f17;
                float f19 = ((f5 + (f15 / 2.0f)) * phaseY) + rotationAngle;
                float f20 = (f11 - f15) * phaseY;
                if (f20 < 0.0f) {
                    f20 = 0.0f;
                }
                this.mPathBuffer.reset();
                int i10 = (f18 > 360.0f ? 1 : (f18 == 360.0f ? 0 : -1));
                if (i10 < 0 || f18 % 360.0f > Utils.FLOAT_EPSILON) {
                    f6 = holeRadius;
                    f4 = phaseX;
                    double d2 = f19 * 0.017453292f;
                    fArr = drawAngles;
                    fArr2 = absoluteAngles;
                    this.mPathBuffer.moveTo(centerCircleBox.x + (((float) Math.cos(d2)) * f12), centerCircleBox.y + (f12 * ((float) Math.sin(d2))));
                    this.mPathBuffer.arcTo(rectF2, f19, f20);
                } else {
                    this.mPathBuffer.addCircle(centerCircleBox.x, centerCircleBox.y, f12, Path.Direction.CW);
                    f6 = holeRadius;
                    f4 = phaseX;
                    fArr = drawAngles;
                    fArr2 = absoluteAngles;
                }
                if (z2) {
                    double d3 = f16 * 0.017453292f;
                    i2 = i9;
                    f7 = f6;
                    f3 = 0.0f;
                    rectF = rectF2;
                    i5 = 1;
                    i4 = i7;
                    f8 = c(centerCircleBox, radius, f11 * phaseY, (((float) Math.cos(d3)) * radius) + centerCircleBox.x, centerCircleBox.y + (((float) Math.sin(d3)) * radius), f16, f18);
                } else {
                    f7 = f6;
                    rectF = rectF2;
                    i4 = i7;
                    i2 = i9;
                    f3 = 0.0f;
                    i5 = 1;
                    f8 = 0.0f;
                }
                RectF rectF3 = this.mInnerRectBuffer;
                float f21 = centerCircleBox.x;
                float f22 = centerCircleBox.y;
                rectF3.set(f21 - f7, f22 - f7, f21 + f7, f22 + f7);
                if (!z || (f7 <= f3 && !z2)) {
                    f2 = f7;
                    if (f18 % 360.0f > Utils.FLOAT_EPSILON) {
                        if (z2) {
                            double d4 = (f16 + (f18 / 2.0f)) * 0.017453292f;
                            this.mPathBuffer.lineTo(centerCircleBox.x + (((float) Math.cos(d4)) * f8), centerCircleBox.y + (f8 * ((float) Math.sin(d4))));
                        } else {
                            this.mPathBuffer.lineTo(centerCircleBox.x, centerCircleBox.y);
                        }
                    }
                } else {
                    if (z2) {
                        if (f8 < f3) {
                            f8 = -f8;
                        }
                        f9 = Math.max(f7, f8);
                    } else {
                        f9 = f7;
                    }
                    float f23 = (i4 == i5 || f9 == f3) ? f3 : sliceSpace / (f9 * 0.017453292f);
                    float f24 = rotationAngle + ((f5 + (f23 / 2.0f)) * phaseY);
                    float f25 = (f11 - f23) * phaseY;
                    if (f25 < f3) {
                        f25 = f3;
                    }
                    float f26 = f24 + f25;
                    if (i10 < 0 || f18 % 360.0f > Utils.FLOAT_EPSILON) {
                        double d5 = f26 * 0.017453292f;
                        f2 = f7;
                        this.mPathBuffer.lineTo(centerCircleBox.x + (((float) Math.cos(d5)) * f9), centerCircleBox.y + (f9 * ((float) Math.sin(d5))));
                        this.mPathBuffer.arcTo(this.mInnerRectBuffer, f26, -f25);
                    } else {
                        this.mPathBuffer.addCircle(centerCircleBox.x, centerCircleBox.y, f9, Path.Direction.CCW);
                        f2 = f7;
                    }
                }
                this.mPathBuffer.close();
                this.f5428k.drawPath(this.mPathBuffer, this.f5401c);
            } else {
                i2 = i6;
                rectF = rectF2;
                f2 = holeRadius;
                f3 = f10;
                f4 = phaseX;
                fArr = drawAngles;
                fArr2 = absoluteAngles;
            }
            i6 = i2 + 1;
            rectF2 = rectF;
            highlightArr2 = highlightArr;
            holeRadius = f2;
            f10 = f3;
            phaseX = f4;
            drawAngles = fArr;
            absoluteAngles = fArr2;
        }
        MPPointF.recycleInstance(centerCircleBox);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        int i2;
        List<IPieDataSet> list;
        float f2;
        float f3;
        float[] fArr;
        float[] fArr2;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        PieDataSet.ValuePosition valuePosition;
        int i3;
        IPieDataSet iPieDataSet;
        List<IPieDataSet> list2;
        float f9;
        float f10;
        IPieDataSet iPieDataSet2;
        String label;
        float f11;
        float f12;
        float f13;
        String label2;
        float f14;
        MPPointF mPPointF;
        MPPointF centerCircleBox = this.f5423f.getCenterCircleBox();
        float radius = this.f5423f.getRadius();
        float rotationAngle = this.f5423f.getRotationAngle();
        float[] drawAngles = this.f5423f.getDrawAngles();
        float[] absoluteAngles = this.f5423f.getAbsoluteAngles();
        float phaseX = this.f5400b.getPhaseX();
        float phaseY = this.f5400b.getPhaseY();
        float holeRadius = this.f5423f.getHoleRadius() / 100.0f;
        float f15 = (radius / 10.0f) * 3.6f;
        if (this.f5423f.isDrawHoleEnabled()) {
            f15 = (radius - (radius * holeRadius)) / 2.0f;
        }
        float f16 = radius - f15;
        PieData pieData = (PieData) this.f5423f.getData();
        List<IPieDataSet> dataSets = pieData.getDataSets();
        float yValueSum = pieData.getYValueSum();
        boolean isDrawEntryLabelsEnabled = this.f5423f.isDrawEntryLabelsEnabled();
        canvas.save();
        float convertDpToPixel = Utils.convertDpToPixel(5.0f);
        int i4 = 0;
        int i5 = 0;
        while (i5 < dataSets.size()) {
            IPieDataSet iPieDataSet3 = dataSets.get(i5);
            boolean isDrawValuesEnabled = iPieDataSet3.isDrawValuesEnabled();
            if (isDrawValuesEnabled || isDrawEntryLabelsEnabled) {
                PieDataSet.ValuePosition xValuePosition = iPieDataSet3.getXValuePosition();
                PieDataSet.ValuePosition yValuePosition = iPieDataSet3.getYValuePosition();
                a(iPieDataSet3);
                float calcTextHeight = Utils.calcTextHeight(this.f5403e, "Q") + Utils.convertDpToPixel(4.0f);
                IValueFormatter valueFormatter = iPieDataSet3.getValueFormatter();
                int entryCount = iPieDataSet3.getEntryCount();
                this.f5426i.setColor(iPieDataSet3.getValueLineColor());
                this.f5426i.setStrokeWidth(Utils.convertDpToPixel(iPieDataSet3.getValueLineWidth()));
                float h2 = h(iPieDataSet3);
                MPPointF mPPointF2 = MPPointF.getInstance(iPieDataSet3.getIconsOffset());
                mPPointF2.x = Utils.convertDpToPixel(mPPointF2.x);
                mPPointF2.y = Utils.convertDpToPixel(mPPointF2.y);
                int i6 = i4;
                int i7 = 0;
                while (i7 < entryCount) {
                    PieEntry entryForIndex = iPieDataSet3.getEntryForIndex(i7);
                    float f17 = (((i6 == 0 ? 0.0f : absoluteAngles[i6 - 1] * phaseX) + ((drawAngles[i6] - ((h2 / (f16 * 0.017453292f)) / 2.0f)) / 2.0f)) * phaseY) + rotationAngle;
                    MPPointF mPPointF3 = mPPointF2;
                    float y = this.f5423f.isUsePercentValuesEnabled() ? (entryForIndex.getY() / yValueSum) * 100.0f : entryForIndex.getY();
                    int i8 = entryCount;
                    double d2 = f17 * 0.017453292f;
                    int i9 = i5;
                    List<IPieDataSet> list3 = dataSets;
                    float cos = (float) Math.cos(d2);
                    float f18 = rotationAngle;
                    float[] fArr3 = drawAngles;
                    float sin = (float) Math.sin(d2);
                    boolean z = isDrawEntryLabelsEnabled && xValuePosition == PieDataSet.ValuePosition.OUTSIDE_SLICE;
                    boolean z2 = isDrawValuesEnabled && yValuePosition == PieDataSet.ValuePosition.OUTSIDE_SLICE;
                    float[] fArr4 = absoluteAngles;
                    boolean z3 = isDrawEntryLabelsEnabled && xValuePosition == PieDataSet.ValuePosition.INSIDE_SLICE;
                    PieDataSet.ValuePosition valuePosition2 = xValuePosition;
                    boolean z4 = isDrawValuesEnabled && yValuePosition == PieDataSet.ValuePosition.INSIDE_SLICE;
                    if (z || z2) {
                        float valueLinePart1Length = iPieDataSet3.getValueLinePart1Length();
                        float valueLinePart2Length = iPieDataSet3.getValueLinePart2Length();
                        float valueLinePart1OffsetPercentage = iPieDataSet3.getValueLinePart1OffsetPercentage() / 100.0f;
                        PieDataSet.ValuePosition valuePosition3 = yValuePosition;
                        if (this.f5423f.isDrawHoleEnabled()) {
                            float f19 = radius * holeRadius;
                            f4 = ((radius - f19) * valueLinePart1OffsetPercentage) + f19;
                        } else {
                            f4 = radius * valueLinePart1OffsetPercentage;
                        }
                        float f20 = valueLinePart2Length * f16;
                        if (iPieDataSet3.isValueLineVariableLength()) {
                            f20 *= (float) Math.abs(Math.sin(d2));
                        }
                        float f21 = centerCircleBox.x;
                        float f22 = (f4 * cos) + f21;
                        float f23 = centerCircleBox.y;
                        float f24 = (f4 * sin) + f23;
                        float f25 = (valueLinePart1Length + 1.0f) * f16;
                        float f26 = (f25 * cos) + f21;
                        float f27 = (f25 * sin) + f23;
                        double d3 = f17 % 360.0d;
                        if (d3 < 90.0d || d3 > 270.0d) {
                            f5 = f26 + f20;
                            this.f5403e.setTextAlign(Paint.Align.LEFT);
                            if (z) {
                                this.mEntryLabelsPaint.setTextAlign(Paint.Align.LEFT);
                            }
                            f6 = f5 + convertDpToPixel;
                        } else {
                            float f28 = f26 - f20;
                            this.f5403e.setTextAlign(Paint.Align.RIGHT);
                            if (z) {
                                this.mEntryLabelsPaint.setTextAlign(Paint.Align.RIGHT);
                            }
                            f5 = f28;
                            f6 = f28 - convertDpToPixel;
                        }
                        if (iPieDataSet3.getValueLineColor() != 1122867) {
                            if (iPieDataSet3.isUsingSliceColorAsValueLineColor()) {
                                this.f5426i.setColor(iPieDataSet3.getColor(i7));
                            }
                            f8 = radius;
                            i3 = i7;
                            valuePosition = valuePosition3;
                            f7 = f6;
                            canvas.drawLine(f22, f24, f26, f27, this.f5426i);
                            canvas.drawLine(f26, f27, f5, f27, this.f5426i);
                        } else {
                            f7 = f6;
                            f8 = radius;
                            valuePosition = valuePosition3;
                            i3 = i7;
                        }
                        if (z && z2) {
                            iPieDataSet = iPieDataSet3;
                            list2 = list3;
                            f10 = cos;
                            drawValue(canvas, valueFormatter, y, entryForIndex, 0, f7, f27, iPieDataSet3.getValueTextColor(i3));
                            if (i3 < pieData.getEntryCount() && entryForIndex.getLabel() != null) {
                                label = entryForIndex.getLabel();
                                f11 = f27 + calcTextHeight;
                                f9 = f7;
                                f(canvas, label, f9, f11);
                            }
                            iPieDataSet2 = iPieDataSet;
                        } else {
                            iPieDataSet = iPieDataSet3;
                            list2 = list3;
                            f9 = f7;
                            f10 = cos;
                            if (z) {
                                if (i3 < pieData.getEntryCount() && entryForIndex.getLabel() != null) {
                                    label = entryForIndex.getLabel();
                                    f11 = f27 + (calcTextHeight / 2.0f);
                                    f(canvas, label, f9, f11);
                                }
                            } else if (z2) {
                                iPieDataSet2 = iPieDataSet;
                                drawValue(canvas, valueFormatter, y, entryForIndex, 0, f9, f27 + (calcTextHeight / 2.0f), iPieDataSet2.getValueTextColor(i3));
                            }
                            iPieDataSet2 = iPieDataSet;
                        }
                    } else {
                        valuePosition = yValuePosition;
                        iPieDataSet2 = iPieDataSet3;
                        f8 = radius;
                        list2 = list3;
                        i3 = i7;
                        f10 = cos;
                    }
                    if (z3 || z4) {
                        float f29 = (f16 * f10) + centerCircleBox.x;
                        float f30 = (f16 * sin) + centerCircleBox.y;
                        this.f5403e.setTextAlign(Paint.Align.CENTER);
                        if (z3 && z4) {
                            f12 = sin;
                            f13 = f29;
                            drawValue(canvas, valueFormatter, y, entryForIndex, 0, f29, f30, iPieDataSet2.getValueTextColor(i3));
                            if (i3 < pieData.getEntryCount() && entryForIndex.getLabel() != null) {
                                label2 = entryForIndex.getLabel();
                                f14 = f30 + calcTextHeight;
                                f(canvas, label2, f13, f14);
                            }
                        } else {
                            f12 = sin;
                            f13 = f29;
                            if (z3) {
                                if (i3 < pieData.getEntryCount() && entryForIndex.getLabel() != null) {
                                    label2 = entryForIndex.getLabel();
                                    f14 = f30 + (calcTextHeight / 2.0f);
                                    f(canvas, label2, f13, f14);
                                }
                            } else if (z4) {
                                drawValue(canvas, valueFormatter, y, entryForIndex, 0, f13, f30 + (calcTextHeight / 2.0f), iPieDataSet2.getValueTextColor(i3));
                            }
                        }
                    } else {
                        f12 = sin;
                    }
                    if (entryForIndex.getIcon() == null || !iPieDataSet2.isDrawIconsEnabled()) {
                        mPPointF = mPPointF3;
                    } else {
                        Drawable icon = entryForIndex.getIcon();
                        mPPointF = mPPointF3;
                        float f31 = mPPointF.y;
                        Utils.drawImage(canvas, icon, (int) (((f16 + f31) * f10) + centerCircleBox.x), (int) (((f16 + f31) * f12) + centerCircleBox.y + mPPointF.x), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                    }
                    i6++;
                    i7 = i3 + 1;
                    mPPointF2 = mPPointF;
                    iPieDataSet3 = iPieDataSet2;
                    entryCount = i8;
                    i5 = i9;
                    rotationAngle = f18;
                    drawAngles = fArr3;
                    absoluteAngles = fArr4;
                    xValuePosition = valuePosition2;
                    dataSets = list2;
                    yValuePosition = valuePosition;
                    radius = f8;
                }
                i2 = i5;
                list = dataSets;
                f2 = radius;
                f3 = rotationAngle;
                fArr = drawAngles;
                fArr2 = absoluteAngles;
                MPPointF.recycleInstance(mPPointF2);
                i4 = i6;
            } else {
                i2 = i5;
                list = dataSets;
                f2 = radius;
                f3 = rotationAngle;
                fArr = drawAngles;
                fArr2 = absoluteAngles;
            }
            i5 = i2 + 1;
            rotationAngle = f3;
            drawAngles = fArr;
            absoluteAngles = fArr2;
            dataSets = list;
            radius = f2;
        }
        MPPointF.recycleInstance(centerCircleBox);
        canvas.restore();
    }

    protected void e(Canvas canvas, IPieDataSet iPieDataSet) {
        int i2;
        int i3;
        float f2;
        float f3;
        float f4;
        RectF rectF;
        int i4;
        float[] fArr;
        int i5;
        float f5;
        MPPointF mPPointF;
        float f6;
        float f7;
        MPPointF mPPointF2;
        float f8;
        int i6;
        PieChartRenderer pieChartRenderer = this;
        IPieDataSet iPieDataSet2 = iPieDataSet;
        float rotationAngle = pieChartRenderer.f5423f.getRotationAngle();
        float phaseX = pieChartRenderer.f5400b.getPhaseX();
        float phaseY = pieChartRenderer.f5400b.getPhaseY();
        RectF circleBox = pieChartRenderer.f5423f.getCircleBox();
        int entryCount = iPieDataSet.getEntryCount();
        float[] drawAngles = pieChartRenderer.f5423f.getDrawAngles();
        MPPointF centerCircleBox = pieChartRenderer.f5423f.getCenterCircleBox();
        float radius = pieChartRenderer.f5423f.getRadius();
        int i7 = 1;
        boolean z = pieChartRenderer.f5423f.isDrawHoleEnabled() && !pieChartRenderer.f5423f.isDrawSlicesUnderHoleEnabled();
        float holeRadius = z ? (pieChartRenderer.f5423f.getHoleRadius() / 100.0f) * radius : 0.0f;
        int i8 = 0;
        for (int i9 = 0; i9 < entryCount; i9++) {
            if (Math.abs(iPieDataSet2.getEntryForIndex(i9).getY()) > Utils.FLOAT_EPSILON) {
                i8++;
            }
        }
        float h2 = i8 <= 1 ? 0.0f : pieChartRenderer.h(iPieDataSet2);
        int i10 = 0;
        float f9 = 0.0f;
        while (i10 < entryCount) {
            float f10 = drawAngles[i10];
            float abs = Math.abs(iPieDataSet2.getEntryForIndex(i10).getY());
            float f11 = Utils.FLOAT_EPSILON;
            if (abs <= f11 || pieChartRenderer.f5423f.needsHighlight(i10)) {
                i2 = i10;
                i3 = i7;
                f2 = radius;
                f3 = rotationAngle;
                f4 = phaseX;
                rectF = circleBox;
                i4 = entryCount;
                fArr = drawAngles;
                i5 = i8;
                f5 = holeRadius;
                mPPointF = centerCircleBox;
            } else {
                int i11 = (h2 <= 0.0f || f10 > 180.0f) ? 0 : i7;
                pieChartRenderer.f5401c.setColor(iPieDataSet2.getColor(i10));
                float f12 = i8 == 1 ? 0.0f : h2 / (radius * 0.017453292f);
                float f13 = rotationAngle + ((f9 + (f12 / 2.0f)) * phaseY);
                float f14 = (f10 - f12) * phaseY;
                if (f14 < 0.0f) {
                    f14 = 0.0f;
                }
                pieChartRenderer.mPathBuffer.reset();
                int i12 = i10;
                int i13 = i8;
                double d2 = f13 * 0.017453292f;
                i4 = entryCount;
                fArr = drawAngles;
                float cos = centerCircleBox.x + (((float) Math.cos(d2)) * radius);
                float sin = centerCircleBox.y + (((float) Math.sin(d2)) * radius);
                int i14 = (f14 > 360.0f ? 1 : (f14 == 360.0f ? 0 : -1));
                if (i14 < 0 || f14 % 360.0f > f11) {
                    f4 = phaseX;
                    pieChartRenderer.mPathBuffer.moveTo(cos, sin);
                    pieChartRenderer.mPathBuffer.arcTo(circleBox, f13, f14);
                } else {
                    f4 = phaseX;
                    pieChartRenderer.mPathBuffer.addCircle(centerCircleBox.x, centerCircleBox.y, radius, Path.Direction.CW);
                }
                RectF rectF2 = pieChartRenderer.mInnerRectBuffer;
                float f15 = centerCircleBox.x;
                float f16 = centerCircleBox.y;
                float f17 = f14;
                rectF2.set(f15 - holeRadius, f16 - holeRadius, f15 + holeRadius, f16 + holeRadius);
                if (!z) {
                    f5 = holeRadius;
                    f3 = rotationAngle;
                    f6 = f17;
                    i3 = 1;
                    f2 = radius;
                    mPPointF = centerCircleBox;
                    rectF = circleBox;
                    i5 = i13;
                    i2 = i12;
                    f7 = 360.0f;
                } else if (holeRadius > 0.0f || i11 != 0) {
                    if (i11 != 0) {
                        f8 = f17;
                        rectF = circleBox;
                        i5 = i13;
                        i2 = i12;
                        f5 = holeRadius;
                        i6 = 1;
                        f2 = radius;
                        mPPointF2 = centerCircleBox;
                        float c2 = c(centerCircleBox, radius, f10 * phaseY, cos, sin, f13, f8);
                        if (c2 < 0.0f) {
                            c2 = -c2;
                        }
                        holeRadius = Math.max(f5, c2);
                    } else {
                        f5 = holeRadius;
                        mPPointF2 = centerCircleBox;
                        f8 = f17;
                        i6 = 1;
                        f2 = radius;
                        rectF = circleBox;
                        i5 = i13;
                        i2 = i12;
                    }
                    float f18 = (i5 == i6 || holeRadius == 0.0f) ? 0.0f : h2 / (holeRadius * 0.017453292f);
                    float f19 = ((f9 + (f18 / 2.0f)) * phaseY) + rotationAngle;
                    float f20 = (f10 - f18) * phaseY;
                    if (f20 < 0.0f) {
                        f20 = 0.0f;
                    }
                    float f21 = f19 + f20;
                    if (i14 < 0 || f8 % 360.0f > f11) {
                        i3 = i6;
                        pieChartRenderer = this;
                        double d3 = f21 * 0.017453292f;
                        f3 = rotationAngle;
                        pieChartRenderer.mPathBuffer.lineTo(mPPointF2.x + (((float) Math.cos(d3)) * holeRadius), mPPointF2.y + (holeRadius * ((float) Math.sin(d3))));
                        pieChartRenderer.mPathBuffer.arcTo(pieChartRenderer.mInnerRectBuffer, f21, -f20);
                    } else {
                        i3 = i6;
                        pieChartRenderer = this;
                        pieChartRenderer.mPathBuffer.addCircle(mPPointF2.x, mPPointF2.y, holeRadius, Path.Direction.CCW);
                        f3 = rotationAngle;
                    }
                    mPPointF = mPPointF2;
                    pieChartRenderer.mPathBuffer.close();
                    pieChartRenderer.f5428k.drawPath(pieChartRenderer.mPathBuffer, pieChartRenderer.f5401c);
                } else {
                    f5 = holeRadius;
                    f3 = rotationAngle;
                    f6 = f17;
                    f7 = 360.0f;
                    i3 = 1;
                    f2 = radius;
                    mPPointF = centerCircleBox;
                    rectF = circleBox;
                    i5 = i13;
                    i2 = i12;
                }
                if (f6 % f7 > f11) {
                    if (i11 != 0) {
                        float c3 = c(mPPointF, f2, f10 * phaseY, cos, sin, f13, f6);
                        double d4 = (f13 + (f6 / 2.0f)) * 0.017453292f;
                        pieChartRenderer.mPathBuffer.lineTo(mPPointF.x + (((float) Math.cos(d4)) * c3), mPPointF.y + (c3 * ((float) Math.sin(d4))));
                    } else {
                        pieChartRenderer.mPathBuffer.lineTo(mPPointF.x, mPPointF.y);
                    }
                }
                pieChartRenderer.mPathBuffer.close();
                pieChartRenderer.f5428k.drawPath(pieChartRenderer.mPathBuffer, pieChartRenderer.f5401c);
            }
            f9 += f10 * f4;
            i10 = i2 + 1;
            iPieDataSet2 = iPieDataSet;
            centerCircleBox = mPPointF;
            i8 = i5;
            holeRadius = f5;
            circleBox = rectF;
            entryCount = i4;
            drawAngles = fArr;
            i7 = i3;
            phaseX = f4;
            radius = f2;
            rotationAngle = f3;
        }
        MPPointF.recycleInstance(centerCircleBox);
    }

    protected void f(Canvas canvas, String str, float f2, float f3) {
        canvas.drawText(str, f2, f3, this.mEntryLabelsPaint);
    }

    protected void g(Canvas canvas) {
        if (!this.f5423f.isDrawHoleEnabled() || this.f5428k == null) {
            return;
        }
        float radius = this.f5423f.getRadius();
        float holeRadius = (this.f5423f.getHoleRadius() / 100.0f) * radius;
        MPPointF centerCircleBox = this.f5423f.getCenterCircleBox();
        if (Color.alpha(this.f5424g.getColor()) > 0) {
            this.f5428k.drawCircle(centerCircleBox.x, centerCircleBox.y, holeRadius, this.f5424g);
        }
        if (Color.alpha(this.f5425h.getColor()) > 0 && this.f5423f.getTransparentCircleRadius() > this.f5423f.getHoleRadius()) {
            int alpha = this.f5425h.getAlpha();
            float transparentCircleRadius = radius * (this.f5423f.getTransparentCircleRadius() / 100.0f);
            this.f5425h.setAlpha((int) (alpha * this.f5400b.getPhaseX() * this.f5400b.getPhaseY()));
            this.mHoleCirclePath.reset();
            this.mHoleCirclePath.addCircle(centerCircleBox.x, centerCircleBox.y, transparentCircleRadius, Path.Direction.CW);
            this.mHoleCirclePath.addCircle(centerCircleBox.x, centerCircleBox.y, holeRadius, Path.Direction.CCW);
            this.f5428k.drawPath(this.mHoleCirclePath, this.f5425h);
            this.f5425h.setAlpha(alpha);
        }
        MPPointF.recycleInstance(centerCircleBox);
    }

    public TextPaint getPaintCenterText() {
        return this.mCenterTextPaint;
    }

    public Paint getPaintEntryLabels() {
        return this.mEntryLabelsPaint;
    }

    public Paint getPaintHole() {
        return this.f5424g;
    }

    public Paint getPaintTransparentCircle() {
        return this.f5425h;
    }

    protected float h(IPieDataSet iPieDataSet) {
        if (iPieDataSet.isAutomaticallyDisableSliceSpacingEnabled() && iPieDataSet.getSliceSpace() / this.f5436a.getSmallestContentExtension() > (iPieDataSet.getYMin() / ((PieData) this.f5423f.getData()).getYValueSum()) * 2.0f) {
            return 0.0f;
        }
        return iPieDataSet.getSliceSpace();
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
    }

    public void releaseBitmap() {
        Canvas canvas = this.f5428k;
        if (canvas != null) {
            canvas.setBitmap(null);
            this.f5428k = null;
        }
        WeakReference weakReference = this.f5427j;
        if (weakReference != null) {
            Bitmap bitmap = (Bitmap) weakReference.get();
            if (bitmap != null) {
                bitmap.recycle();
            }
            this.f5427j.clear();
            this.f5427j = null;
        }
    }
}
