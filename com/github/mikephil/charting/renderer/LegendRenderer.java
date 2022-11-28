package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public class LegendRenderer extends Renderer {

    /* renamed from: b  reason: collision with root package name */
    protected Paint f5404b;

    /* renamed from: c  reason: collision with root package name */
    protected Paint f5405c;

    /* renamed from: d  reason: collision with root package name */
    protected Legend f5406d;

    /* renamed from: e  reason: collision with root package name */
    protected List f5407e;

    /* renamed from: f  reason: collision with root package name */
    protected Paint.FontMetrics f5408f;
    private Path mLineFormPath;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.github.mikephil.charting.renderer.LegendRenderer$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f5409a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f5410b;

        /* renamed from: c  reason: collision with root package name */
        static final /* synthetic */ int[] f5411c;

        /* renamed from: d  reason: collision with root package name */
        static final /* synthetic */ int[] f5412d;

        static {
            int[] iArr = new int[Legend.LegendForm.values().length];
            f5412d = iArr;
            try {
                iArr[Legend.LegendForm.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5412d[Legend.LegendForm.EMPTY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5412d[Legend.LegendForm.DEFAULT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f5412d[Legend.LegendForm.CIRCLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f5412d[Legend.LegendForm.SQUARE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f5412d[Legend.LegendForm.LINE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr2 = new int[Legend.LegendOrientation.values().length];
            f5411c = iArr2;
            try {
                iArr2[Legend.LegendOrientation.HORIZONTAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f5411c[Legend.LegendOrientation.VERTICAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr3 = new int[Legend.LegendVerticalAlignment.values().length];
            f5410b = iArr3;
            try {
                iArr3[Legend.LegendVerticalAlignment.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f5410b[Legend.LegendVerticalAlignment.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f5410b[Legend.LegendVerticalAlignment.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            int[] iArr4 = new int[Legend.LegendHorizontalAlignment.values().length];
            f5409a = iArr4;
            try {
                iArr4[Legend.LegendHorizontalAlignment.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f5409a[Legend.LegendHorizontalAlignment.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f5409a[Legend.LegendHorizontalAlignment.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    public LegendRenderer(ViewPortHandler viewPortHandler, Legend legend) {
        super(viewPortHandler);
        this.f5407e = new ArrayList(16);
        this.f5408f = new Paint.FontMetrics();
        this.mLineFormPath = new Path();
        this.f5406d = legend;
        Paint paint = new Paint(1);
        this.f5404b = paint;
        paint.setTextSize(Utils.convertDpToPixel(9.0f));
        this.f5404b.setTextAlign(Paint.Align.LEFT);
        Paint paint2 = new Paint(1);
        this.f5405c = paint2;
        paint2.setStyle(Paint.Style.FILL);
    }

    protected void a(Canvas canvas, float f2, float f3, LegendEntry legendEntry, Legend legend) {
        int i2 = legendEntry.formColor;
        if (i2 == 1122868 || i2 == 1122867 || i2 == 0) {
            return;
        }
        int save = canvas.save();
        Legend.LegendForm legendForm = legendEntry.form;
        if (legendForm == Legend.LegendForm.DEFAULT) {
            legendForm = legend.getForm();
        }
        this.f5405c.setColor(legendEntry.formColor);
        float convertDpToPixel = Utils.convertDpToPixel(Float.isNaN(legendEntry.formSize) ? legend.getFormSize() : legendEntry.formSize);
        float f4 = convertDpToPixel / 2.0f;
        int i3 = AnonymousClass1.f5412d[legendForm.ordinal()];
        if (i3 == 3 || i3 == 4) {
            this.f5405c.setStyle(Paint.Style.FILL);
            canvas.drawCircle(f2 + f4, f3, f4, this.f5405c);
        } else if (i3 == 5) {
            this.f5405c.setStyle(Paint.Style.FILL);
            canvas.drawRect(f2, f3 - f4, f2 + convertDpToPixel, f3 + f4, this.f5405c);
        } else if (i3 == 6) {
            float convertDpToPixel2 = Utils.convertDpToPixel(Float.isNaN(legendEntry.formLineWidth) ? legend.getFormLineWidth() : legendEntry.formLineWidth);
            DashPathEffect dashPathEffect = legendEntry.formLineDashEffect;
            if (dashPathEffect == null) {
                dashPathEffect = legend.getFormLineDashEffect();
            }
            this.f5405c.setStyle(Paint.Style.STROKE);
            this.f5405c.setStrokeWidth(convertDpToPixel2);
            this.f5405c.setPathEffect(dashPathEffect);
            this.mLineFormPath.reset();
            this.mLineFormPath.moveTo(f2, f3);
            this.mLineFormPath.lineTo(f2 + convertDpToPixel, f3);
            canvas.drawPath(this.mLineFormPath, this.f5405c);
        }
        canvas.restoreToCount(save);
    }

    protected void b(Canvas canvas, float f2, float f3, String str) {
        canvas.drawText(str, f2, f3, this.f5404b);
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [com.github.mikephil.charting.interfaces.datasets.IDataSet] */
    /* JADX WARN: Type inference failed for: r7v1, types: [com.github.mikephil.charting.interfaces.datasets.IDataSet] */
    public void computeLegend(ChartData<?> chartData) {
        ChartData<?> chartData2;
        ChartData<?> chartData3 = chartData;
        if (!this.f5406d.isLegendCustom()) {
            this.f5407e.clear();
            int i2 = 0;
            while (i2 < chartData.getDataSetCount()) {
                ?? dataSetByIndex = chartData3.getDataSetByIndex(i2);
                List<Integer> colors = dataSetByIndex.getColors();
                int entryCount = dataSetByIndex.getEntryCount();
                if (dataSetByIndex instanceof IBarDataSet) {
                    IBarDataSet iBarDataSet = (IBarDataSet) dataSetByIndex;
                    if (iBarDataSet.isStacked()) {
                        String[] stackLabels = iBarDataSet.getStackLabels();
                        for (int i3 = 0; i3 < colors.size() && i3 < iBarDataSet.getStackSize(); i3++) {
                            this.f5407e.add(new LegendEntry(stackLabels[i3 % stackLabels.length], dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), colors.get(i3).intValue()));
                        }
                        if (iBarDataSet.getLabel() != null) {
                            this.f5407e.add(new LegendEntry(dataSetByIndex.getLabel(), Legend.LegendForm.NONE, Float.NaN, Float.NaN, null, ColorTemplate.COLOR_NONE));
                        }
                        chartData2 = chartData3;
                        i2++;
                        chartData3 = chartData2;
                    }
                }
                if (dataSetByIndex instanceof IPieDataSet) {
                    IPieDataSet iPieDataSet = (IPieDataSet) dataSetByIndex;
                    for (int i4 = 0; i4 < colors.size() && i4 < entryCount; i4++) {
                        this.f5407e.add(new LegendEntry(iPieDataSet.getEntryForIndex(i4).getLabel(), dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), colors.get(i4).intValue()));
                    }
                    if (iPieDataSet.getLabel() != null) {
                        this.f5407e.add(new LegendEntry(dataSetByIndex.getLabel(), Legend.LegendForm.NONE, Float.NaN, Float.NaN, null, ColorTemplate.COLOR_NONE));
                    }
                } else {
                    if (dataSetByIndex instanceof ICandleDataSet) {
                        ICandleDataSet iCandleDataSet = (ICandleDataSet) dataSetByIndex;
                        if (iCandleDataSet.getDecreasingColor() != 1122867) {
                            int decreasingColor = iCandleDataSet.getDecreasingColor();
                            int increasingColor = iCandleDataSet.getIncreasingColor();
                            this.f5407e.add(new LegendEntry(null, dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), decreasingColor));
                            this.f5407e.add(new LegendEntry(dataSetByIndex.getLabel(), dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), increasingColor));
                        }
                    }
                    int i5 = 0;
                    while (i5 < colors.size() && i5 < entryCount) {
                        this.f5407e.add(new LegendEntry((i5 >= colors.size() + (-1) || i5 >= entryCount + (-1)) ? chartData.getDataSetByIndex(i2).getLabel() : null, dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), colors.get(i5).intValue()));
                        i5++;
                    }
                }
                chartData2 = chartData;
                i2++;
                chartData3 = chartData2;
            }
            if (this.f5406d.getExtraEntries() != null) {
                Collections.addAll(this.f5407e, this.f5406d.getExtraEntries());
            }
            this.f5406d.setEntries(this.f5407e);
        }
        Typeface typeface = this.f5406d.getTypeface();
        if (typeface != null) {
            this.f5404b.setTypeface(typeface);
        }
        this.f5404b.setTextSize(this.f5406d.getTextSize());
        this.f5404b.setColor(this.f5406d.getTextColor());
        this.f5406d.calculateDimensions(this.f5404b, this.f5436a);
    }

    public Paint getFormPaint() {
        return this.f5405c;
    }

    public Paint getLabelPaint() {
        return this.f5404b;
    }

    public void renderLegend(Canvas canvas) {
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        List<Boolean> list;
        List<FSize> list2;
        int i2;
        float f7;
        float f8;
        float f9;
        float f10;
        float contentTop;
        float f11;
        float f12;
        float f13;
        Legend.LegendDirection legendDirection;
        LegendEntry legendEntry;
        float f14;
        String str;
        Canvas canvas2;
        float f15;
        String str2;
        double d2;
        if (this.f5406d.isEnabled()) {
            Typeface typeface = this.f5406d.getTypeface();
            if (typeface != null) {
                this.f5404b.setTypeface(typeface);
            }
            this.f5404b.setTextSize(this.f5406d.getTextSize());
            this.f5404b.setColor(this.f5406d.getTextColor());
            float lineHeight = Utils.getLineHeight(this.f5404b, this.f5408f);
            float lineSpacing = Utils.getLineSpacing(this.f5404b, this.f5408f) + Utils.convertDpToPixel(this.f5406d.getYEntrySpace());
            float calcTextHeight = lineHeight - (Utils.calcTextHeight(this.f5404b, "ABC") / 2.0f);
            LegendEntry[] entries = this.f5406d.getEntries();
            float convertDpToPixel = Utils.convertDpToPixel(this.f5406d.getFormToTextSpace());
            float convertDpToPixel2 = Utils.convertDpToPixel(this.f5406d.getXEntrySpace());
            Legend.LegendOrientation orientation = this.f5406d.getOrientation();
            Legend.LegendHorizontalAlignment horizontalAlignment = this.f5406d.getHorizontalAlignment();
            Legend.LegendVerticalAlignment verticalAlignment = this.f5406d.getVerticalAlignment();
            Legend.LegendDirection direction = this.f5406d.getDirection();
            float convertDpToPixel3 = Utils.convertDpToPixel(this.f5406d.getFormSize());
            float convertDpToPixel4 = Utils.convertDpToPixel(this.f5406d.getStackSpace());
            float yOffset = this.f5406d.getYOffset();
            float xOffset = this.f5406d.getXOffset();
            int i3 = AnonymousClass1.f5409a[horizontalAlignment.ordinal()];
            float f16 = convertDpToPixel4;
            float f17 = convertDpToPixel2;
            if (i3 == 1) {
                f2 = lineHeight;
                f3 = lineSpacing;
                if (orientation != Legend.LegendOrientation.VERTICAL) {
                    xOffset += this.f5436a.contentLeft();
                }
                f4 = direction == Legend.LegendDirection.RIGHT_TO_LEFT ? xOffset + this.f5406d.mNeededWidth : xOffset;
            } else if (i3 == 2) {
                f2 = lineHeight;
                f3 = lineSpacing;
                f4 = (orientation == Legend.LegendOrientation.VERTICAL ? this.f5436a.getChartWidth() : this.f5436a.contentRight()) - xOffset;
                if (direction == Legend.LegendDirection.LEFT_TO_RIGHT) {
                    f4 -= this.f5406d.mNeededWidth;
                }
            } else if (i3 != 3) {
                f2 = lineHeight;
                f3 = lineSpacing;
                f4 = 0.0f;
            } else {
                Legend.LegendOrientation legendOrientation = Legend.LegendOrientation.VERTICAL;
                float chartWidth = orientation == legendOrientation ? this.f5436a.getChartWidth() / 2.0f : this.f5436a.contentLeft() + (this.f5436a.contentWidth() / 2.0f);
                Legend.LegendDirection legendDirection2 = Legend.LegendDirection.LEFT_TO_RIGHT;
                f3 = lineSpacing;
                f4 = chartWidth + (direction == legendDirection2 ? xOffset : -xOffset);
                if (orientation == legendOrientation) {
                    double d3 = f4;
                    if (direction == legendDirection2) {
                        f2 = lineHeight;
                        d2 = ((-this.f5406d.mNeededWidth) / 2.0d) + xOffset;
                    } else {
                        f2 = lineHeight;
                        d2 = (this.f5406d.mNeededWidth / 2.0d) - xOffset;
                    }
                    f4 = (float) (d3 + d2);
                } else {
                    f2 = lineHeight;
                }
            }
            int i4 = AnonymousClass1.f5411c[orientation.ordinal()];
            if (i4 != 1) {
                if (i4 != 2) {
                    return;
                }
                int i5 = AnonymousClass1.f5410b[verticalAlignment.ordinal()];
                if (i5 == 1) {
                    contentTop = (horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER ? 0.0f : this.f5436a.contentTop()) + yOffset;
                } else if (i5 == 2) {
                    contentTop = (horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER ? this.f5436a.getChartHeight() : this.f5436a.contentBottom()) - (this.f5406d.mNeededHeight + yOffset);
                } else if (i5 != 3) {
                    contentTop = 0.0f;
                } else {
                    Legend legend = this.f5406d;
                    contentTop = ((this.f5436a.getChartHeight() / 2.0f) - (legend.mNeededHeight / 2.0f)) + legend.getYOffset();
                }
                float f18 = contentTop;
                float f19 = 0.0f;
                boolean z = false;
                int i6 = 0;
                while (i6 < entries.length) {
                    LegendEntry legendEntry2 = entries[i6];
                    boolean z2 = legendEntry2.form != Legend.LegendForm.NONE;
                    float convertDpToPixel5 = Float.isNaN(legendEntry2.formSize) ? convertDpToPixel3 : Utils.convertDpToPixel(legendEntry2.formSize);
                    if (z2) {
                        Legend.LegendDirection legendDirection3 = Legend.LegendDirection.LEFT_TO_RIGHT;
                        f14 = direction == legendDirection3 ? f4 + f19 : f4 - (convertDpToPixel5 - f19);
                        f12 = calcTextHeight;
                        f13 = f16;
                        f11 = f4;
                        legendDirection = direction;
                        a(canvas, f14, f18 + calcTextHeight, legendEntry2, this.f5406d);
                        if (legendDirection == legendDirection3) {
                            f14 += convertDpToPixel5;
                        }
                        legendEntry = legendEntry2;
                    } else {
                        f11 = f4;
                        f12 = calcTextHeight;
                        f13 = f16;
                        legendDirection = direction;
                        legendEntry = legendEntry2;
                        f14 = f11;
                    }
                    if (legendEntry.label != null) {
                        if (z2 && !z) {
                            f14 += legendDirection == Legend.LegendDirection.LEFT_TO_RIGHT ? convertDpToPixel : -convertDpToPixel;
                        } else if (z) {
                            f14 = f11;
                        }
                        if (legendDirection == Legend.LegendDirection.RIGHT_TO_LEFT) {
                            f14 -= Utils.calcTextWidth(this.f5404b, str);
                        }
                        float f20 = f14;
                        if (z) {
                            canvas2 = canvas;
                            f18 += f2 + f3;
                            f15 = f18 + f2;
                            str2 = legendEntry.label;
                        } else {
                            f15 = f18 + f2;
                            str2 = legendEntry.label;
                            canvas2 = canvas;
                        }
                        b(canvas2, f20, f15, str2);
                        f18 += f2 + f3;
                        f19 = 0.0f;
                    } else {
                        f19 += convertDpToPixel5 + f13;
                        z = true;
                    }
                    i6++;
                    direction = legendDirection;
                    f16 = f13;
                    calcTextHeight = f12;
                    f4 = f11;
                }
                return;
            }
            float f21 = f4;
            float f22 = f16;
            List<FSize> calculatedLineSizes = this.f5406d.getCalculatedLineSizes();
            List<FSize> calculatedLabelSizes = this.f5406d.getCalculatedLabelSizes();
            List<Boolean> calculatedLabelBreakPoints = this.f5406d.getCalculatedLabelBreakPoints();
            int i7 = AnonymousClass1.f5410b[verticalAlignment.ordinal()];
            if (i7 != 1) {
                yOffset = i7 != 2 ? i7 != 3 ? 0.0f : yOffset + ((this.f5436a.getChartHeight() - this.f5406d.mNeededHeight) / 2.0f) : (this.f5436a.getChartHeight() - yOffset) - this.f5406d.mNeededHeight;
            }
            int length = entries.length;
            float f23 = f21;
            int i8 = 0;
            int i9 = 0;
            while (i8 < length) {
                float f24 = f22;
                LegendEntry legendEntry3 = entries[i8];
                float f25 = f23;
                int i10 = length;
                boolean z3 = legendEntry3.form != Legend.LegendForm.NONE;
                float convertDpToPixel6 = Float.isNaN(legendEntry3.formSize) ? convertDpToPixel3 : Utils.convertDpToPixel(legendEntry3.formSize);
                if (i8 >= calculatedLabelBreakPoints.size() || !calculatedLabelBreakPoints.get(i8).booleanValue()) {
                    f5 = f25;
                    f6 = yOffset;
                } else {
                    f6 = yOffset + f2 + f3;
                    f5 = f21;
                }
                if (f5 == f21 && horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER && i9 < calculatedLineSizes.size()) {
                    f5 += (direction == Legend.LegendDirection.RIGHT_TO_LEFT ? calculatedLineSizes.get(i9).width : -calculatedLineSizes.get(i9).width) / 2.0f;
                    i9++;
                }
                int i11 = i9;
                boolean z4 = legendEntry3.label == null;
                if (z3) {
                    if (direction == Legend.LegendDirection.RIGHT_TO_LEFT) {
                        f5 -= convertDpToPixel6;
                    }
                    float f26 = f5;
                    list2 = calculatedLineSizes;
                    i2 = i8;
                    list = calculatedLabelBreakPoints;
                    a(canvas, f26, f6 + calcTextHeight, legendEntry3, this.f5406d);
                    f5 = direction == Legend.LegendDirection.LEFT_TO_RIGHT ? f26 + convertDpToPixel6 : f26;
                } else {
                    list = calculatedLabelBreakPoints;
                    list2 = calculatedLineSizes;
                    i2 = i8;
                }
                if (z4) {
                    f7 = f17;
                    if (direction == Legend.LegendDirection.RIGHT_TO_LEFT) {
                        f8 = f24;
                        f9 = -f8;
                    } else {
                        f8 = f24;
                        f9 = f8;
                    }
                    f23 = f5 + f9;
                } else {
                    if (z3) {
                        f5 += direction == Legend.LegendDirection.RIGHT_TO_LEFT ? -convertDpToPixel : convertDpToPixel;
                    }
                    Legend.LegendDirection legendDirection4 = Legend.LegendDirection.RIGHT_TO_LEFT;
                    if (direction == legendDirection4) {
                        f5 -= calculatedLabelSizes.get(i2).width;
                    }
                    b(canvas, f5, f6 + f2, legendEntry3.label);
                    if (direction == Legend.LegendDirection.LEFT_TO_RIGHT) {
                        f5 += calculatedLabelSizes.get(i2).width;
                    }
                    if (direction == legendDirection4) {
                        f7 = f17;
                        f10 = -f7;
                    } else {
                        f7 = f17;
                        f10 = f7;
                    }
                    f23 = f5 + f10;
                    f8 = f24;
                }
                f17 = f7;
                f22 = f8;
                i8 = i2 + 1;
                yOffset = f6;
                length = i10;
                i9 = i11;
                calculatedLineSizes = list2;
                calculatedLabelBreakPoints = list;
            }
        }
    }
}
