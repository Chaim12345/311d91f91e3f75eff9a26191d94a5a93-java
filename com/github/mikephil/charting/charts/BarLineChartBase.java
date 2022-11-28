package com.github.mikephil.charting.charts;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.jobs.AnimatedMoveViewJob;
import com.github.mikephil.charting.jobs.AnimatedZoomJob;
import com.github.mikephil.charting.jobs.MoveViewJob;
import com.github.mikephil.charting.jobs.ZoomJob;
import com.github.mikephil.charting.listener.BarLineChartTouchListener;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnDrawListener;
import com.github.mikephil.charting.renderer.DataRenderer;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
@SuppressLint({"RtlHardcoded"})
/* loaded from: classes.dex */
public abstract class BarLineChartBase<T extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>> extends Chart<T> implements BarLineScatterCandleBubbleDataProvider {
    protected boolean A;
    protected Paint B;
    protected Paint C;
    protected boolean D;
    protected boolean E;
    protected boolean F;
    protected float G;
    protected boolean H;
    protected OnDrawListener I;
    protected YAxis J;
    protected YAxis K;
    protected YAxisRenderer L;
    protected YAxisRenderer M;
    protected Transformer N;
    protected Transformer O;
    protected XAxisRenderer P;
    protected Matrix Q;
    protected Matrix R;
    protected float[] S;
    protected MPPointD T;
    protected MPPointD U;
    protected float[] V;
    private long drawCycles;
    private boolean mCustomViewPortEnabled;
    private boolean mDragXEnabled;
    private boolean mDragYEnabled;
    private RectF mOffsetsBuffer;
    private boolean mScaleXEnabled;
    private boolean mScaleYEnabled;
    private long totalTime;
    protected int w;
    protected boolean x;
    protected boolean y;
    protected boolean z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.github.mikephil.charting.charts.BarLineChartBase$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f5263a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f5264b;

        /* renamed from: c  reason: collision with root package name */
        static final /* synthetic */ int[] f5265c;

        static {
            int[] iArr = new int[Legend.LegendOrientation.values().length];
            f5265c = iArr;
            try {
                iArr[Legend.LegendOrientation.VERTICAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5265c[Legend.LegendOrientation.HORIZONTAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[Legend.LegendHorizontalAlignment.values().length];
            f5264b = iArr2;
            try {
                iArr2[Legend.LegendHorizontalAlignment.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f5264b[Legend.LegendHorizontalAlignment.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f5264b[Legend.LegendHorizontalAlignment.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr3 = new int[Legend.LegendVerticalAlignment.values().length];
            f5263a = iArr3;
            try {
                iArr3[Legend.LegendVerticalAlignment.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f5263a[Legend.LegendVerticalAlignment.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public BarLineChartBase(Context context) {
        super(context);
        this.w = 100;
        this.x = false;
        this.y = false;
        this.z = true;
        this.A = true;
        this.mDragXEnabled = true;
        this.mDragYEnabled = true;
        this.mScaleXEnabled = true;
        this.mScaleYEnabled = true;
        this.D = false;
        this.E = false;
        this.F = false;
        this.G = 15.0f;
        this.H = false;
        this.totalTime = 0L;
        this.drawCycles = 0L;
        this.mOffsetsBuffer = new RectF();
        this.Q = new Matrix();
        this.R = new Matrix();
        this.mCustomViewPortEnabled = false;
        this.S = new float[2];
        this.T = MPPointD.getInstance(0.0d, 0.0d);
        this.U = MPPointD.getInstance(0.0d, 0.0d);
        this.V = new float[2];
    }

    public BarLineChartBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.w = 100;
        this.x = false;
        this.y = false;
        this.z = true;
        this.A = true;
        this.mDragXEnabled = true;
        this.mDragYEnabled = true;
        this.mScaleXEnabled = true;
        this.mScaleYEnabled = true;
        this.D = false;
        this.E = false;
        this.F = false;
        this.G = 15.0f;
        this.H = false;
        this.totalTime = 0L;
        this.drawCycles = 0L;
        this.mOffsetsBuffer = new RectF();
        this.Q = new Matrix();
        this.R = new Matrix();
        this.mCustomViewPortEnabled = false;
        this.S = new float[2];
        this.T = MPPointD.getInstance(0.0d, 0.0d);
        this.U = MPPointD.getInstance(0.0d, 0.0d);
        this.V = new float[2];
    }

    public BarLineChartBase(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.w = 100;
        this.x = false;
        this.y = false;
        this.z = true;
        this.A = true;
        this.mDragXEnabled = true;
        this.mDragYEnabled = true;
        this.mScaleXEnabled = true;
        this.mScaleYEnabled = true;
        this.D = false;
        this.E = false;
        this.F = false;
        this.G = 15.0f;
        this.H = false;
        this.totalTime = 0L;
        this.drawCycles = 0L;
        this.mOffsetsBuffer = new RectF();
        this.Q = new Matrix();
        this.R = new Matrix();
        this.mCustomViewPortEnabled = false;
        this.S = new float[2];
        this.T = MPPointD.getInstance(0.0d, 0.0d);
        this.U = MPPointD.getInstance(0.0d, 0.0d);
        this.V = new float[2];
    }

    @Override // com.github.mikephil.charting.charts.Chart
    public void calculateOffsets() {
        if (!this.mCustomViewPortEnabled) {
            h(this.mOffsetsBuffer);
            RectF rectF = this.mOffsetsBuffer;
            float f2 = rectF.left + 0.0f;
            float f3 = rectF.top + 0.0f;
            float f4 = rectF.right + 0.0f;
            float f5 = rectF.bottom + 0.0f;
            if (this.J.needsOffset()) {
                f2 += this.J.getRequiredWidthSpace(this.L.getPaintAxisLabels());
            }
            if (this.K.needsOffset()) {
                f4 += this.K.getRequiredWidthSpace(this.M.getPaintAxisLabels());
            }
            if (this.f5272g.isEnabled() && this.f5272g.isDrawLabelsEnabled()) {
                XAxis xAxis = this.f5272g;
                float yOffset = xAxis.mLabelRotatedHeight + xAxis.getYOffset();
                if (this.f5272g.getPosition() == XAxis.XAxisPosition.BOTTOM) {
                    f5 += yOffset;
                } else {
                    if (this.f5272g.getPosition() != XAxis.XAxisPosition.TOP) {
                        if (this.f5272g.getPosition() == XAxis.XAxisPosition.BOTH_SIDED) {
                            f5 += yOffset;
                        }
                    }
                    f3 += yOffset;
                }
            }
            float extraTopOffset = f3 + getExtraTopOffset();
            float extraRightOffset = f4 + getExtraRightOffset();
            float extraBottomOffset = f5 + getExtraBottomOffset();
            float extraLeftOffset = f2 + getExtraLeftOffset();
            float convertDpToPixel = Utils.convertDpToPixel(this.G);
            this.f5281p.restrainViewPort(Math.max(convertDpToPixel, extraLeftOffset), Math.max(convertDpToPixel, extraTopOffset), Math.max(convertDpToPixel, extraRightOffset), Math.max(convertDpToPixel, extraBottomOffset));
            if (this.f5266a) {
                StringBuilder sb = new StringBuilder();
                sb.append("offsetLeft: ");
                sb.append(extraLeftOffset);
                sb.append(", offsetTop: ");
                sb.append(extraTopOffset);
                sb.append(", offsetRight: ");
                sb.append(extraRightOffset);
                sb.append(", offsetBottom: ");
                sb.append(extraBottomOffset);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Content: ");
                sb2.append(this.f5281p.getContentRect().toString());
            }
        }
        k();
        l();
    }

    public void centerViewTo(float f2, float f3, YAxis.AxisDependency axisDependency) {
        float j2 = j(axisDependency) / this.f5281p.getScaleY();
        addViewportJob(MoveViewJob.getInstance(this.f5281p, f2 - ((getXAxis().mAxisRange / this.f5281p.getScaleX()) / 2.0f), f3 + (j2 / 2.0f), getTransformer(axisDependency), this));
    }

    @TargetApi(11)
    public void centerViewToAnimated(float f2, float f3, YAxis.AxisDependency axisDependency, long j2) {
        if (Build.VERSION.SDK_INT < 11) {
            Log.e(Chart.LOG_TAG, "Unable to execute centerViewToAnimated(...) on API level < 11");
            return;
        }
        MPPointD valuesByTouchPoint = getValuesByTouchPoint(this.f5281p.contentLeft(), this.f5281p.contentTop(), axisDependency);
        float j3 = j(axisDependency) / this.f5281p.getScaleY();
        addViewportJob(AnimatedMoveViewJob.getInstance(this.f5281p, f2 - ((getXAxis().mAxisRange / this.f5281p.getScaleX()) / 2.0f), f3 + (j3 / 2.0f), getTransformer(axisDependency), this, (float) valuesByTouchPoint.x, (float) valuesByTouchPoint.y, j2));
        MPPointD.recycleInstance(valuesByTouchPoint);
    }

    public void centerViewToY(float f2, YAxis.AxisDependency axisDependency) {
        addViewportJob(MoveViewJob.getInstance(this.f5281p, 0.0f, f2 + ((j(axisDependency) / this.f5281p.getScaleY()) / 2.0f), getTransformer(axisDependency), this));
    }

    @Override // android.view.View
    public void computeScroll() {
        ChartTouchListener chartTouchListener = this.f5277l;
        if (chartTouchListener instanceof BarLineChartTouchListener) {
            ((BarLineChartTouchListener) chartTouchListener).computeScroll();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.charts.Chart
    public void d() {
        super.d();
        this.J = new YAxis(YAxis.AxisDependency.LEFT);
        this.K = new YAxis(YAxis.AxisDependency.RIGHT);
        this.N = new Transformer(this.f5281p);
        this.O = new Transformer(this.f5281p);
        this.L = new YAxisRenderer(this.f5281p, this.J, this.N);
        this.M = new YAxisRenderer(this.f5281p, this.K, this.O);
        this.P = new XAxisRenderer(this.f5281p, this.f5272g, this.N);
        setHighlighter(new ChartHighlighter(this));
        this.f5277l = new BarLineChartTouchListener(this, this.f5281p.getMatrixTouch(), 3.0f);
        Paint paint = new Paint();
        this.B = paint;
        paint.setStyle(Paint.Style.FILL);
        this.B.setColor(Color.rgb(240, 240, 240));
        Paint paint2 = new Paint();
        this.C = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.C.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.C.setStrokeWidth(Utils.convertDpToPixel(1.0f));
    }

    protected void f() {
        ((BarLineScatterCandleBubbleData) this.f5267b).calcMinMaxY(getLowestVisibleX(), getHighestVisibleX());
        this.f5272g.calculate(((BarLineScatterCandleBubbleData) this.f5267b).getXMin(), ((BarLineScatterCandleBubbleData) this.f5267b).getXMax());
        if (this.J.isEnabled()) {
            YAxis yAxis = this.J;
            YAxis.AxisDependency axisDependency = YAxis.AxisDependency.LEFT;
            yAxis.calculate(((BarLineScatterCandleBubbleData) this.f5267b).getYMin(axisDependency), ((BarLineScatterCandleBubbleData) this.f5267b).getYMax(axisDependency));
        }
        if (this.K.isEnabled()) {
            YAxis yAxis2 = this.K;
            YAxis.AxisDependency axisDependency2 = YAxis.AxisDependency.RIGHT;
            yAxis2.calculate(((BarLineScatterCandleBubbleData) this.f5267b).getYMin(axisDependency2), ((BarLineScatterCandleBubbleData) this.f5267b).getYMax(axisDependency2));
        }
        calculateOffsets();
    }

    public void fitScreen() {
        Matrix matrix = this.R;
        this.f5281p.fitScreen(matrix);
        this.f5281p.refresh(matrix, this, false);
        calculateOffsets();
        postInvalidate();
    }

    protected void g() {
        this.f5272g.calculate(((BarLineScatterCandleBubbleData) this.f5267b).getXMin(), ((BarLineScatterCandleBubbleData) this.f5267b).getXMax());
        YAxis yAxis = this.J;
        YAxis.AxisDependency axisDependency = YAxis.AxisDependency.LEFT;
        yAxis.calculate(((BarLineScatterCandleBubbleData) this.f5267b).getYMin(axisDependency), ((BarLineScatterCandleBubbleData) this.f5267b).getYMax(axisDependency));
        YAxis yAxis2 = this.K;
        YAxis.AxisDependency axisDependency2 = YAxis.AxisDependency.RIGHT;
        yAxis2.calculate(((BarLineScatterCandleBubbleData) this.f5267b).getYMin(axisDependency2), ((BarLineScatterCandleBubbleData) this.f5267b).getYMax(axisDependency2));
    }

    public YAxis getAxis(YAxis.AxisDependency axisDependency) {
        return axisDependency == YAxis.AxisDependency.LEFT ? this.J : this.K;
    }

    public YAxis getAxisLeft() {
        return this.J;
    }

    public YAxis getAxisRight() {
        return this.K;
    }

    @Override // com.github.mikephil.charting.charts.Chart, com.github.mikephil.charting.interfaces.dataprovider.ChartInterface, com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider
    public /* bridge */ /* synthetic */ BarLineScatterCandleBubbleData getData() {
        return (BarLineScatterCandleBubbleData) super.getData();
    }

    public IBarLineScatterCandleBubbleDataSet getDataSetByTouchPoint(float f2, float f3) {
        Highlight highlightByTouchPoint = getHighlightByTouchPoint(f2, f3);
        if (highlightByTouchPoint != null) {
            return (IBarLineScatterCandleBubbleDataSet) ((BarLineScatterCandleBubbleData) this.f5267b).getDataSetByIndex(highlightByTouchPoint.getDataSetIndex());
        }
        return null;
    }

    public OnDrawListener getDrawListener() {
        return this.I;
    }

    public Entry getEntryByTouchPoint(float f2, float f3) {
        Highlight highlightByTouchPoint = getHighlightByTouchPoint(f2, f3);
        if (highlightByTouchPoint != null) {
            return ((BarLineScatterCandleBubbleData) this.f5267b).getEntryForHighlight(highlightByTouchPoint);
        }
        return null;
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider
    public float getHighestVisibleX() {
        getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(this.f5281p.contentRight(), this.f5281p.contentBottom(), this.U);
        return (float) Math.min(this.f5272g.mAxisMaximum, this.U.x);
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider
    public float getLowestVisibleX() {
        getTransformer(YAxis.AxisDependency.LEFT).getValuesByTouchPoint(this.f5281p.contentLeft(), this.f5281p.contentBottom(), this.T);
        return (float) Math.max(this.f5272g.mAxisMinimum, this.T.x);
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public int getMaxVisibleCount() {
        return this.w;
    }

    public float getMinOffset() {
        return this.G;
    }

    @Override // com.github.mikephil.charting.charts.Chart
    public Paint getPaint(int i2) {
        Paint paint = super.getPaint(i2);
        if (paint != null) {
            return paint;
        }
        if (i2 != 4) {
            return null;
        }
        return this.B;
    }

    public MPPointD getPixelForValues(float f2, float f3, YAxis.AxisDependency axisDependency) {
        return getTransformer(axisDependency).getPixelForValues(f2, f3);
    }

    public MPPointF getPosition(Entry entry, YAxis.AxisDependency axisDependency) {
        if (entry == null) {
            return null;
        }
        this.S[0] = entry.getX();
        this.S[1] = entry.getY();
        getTransformer(axisDependency).pointValuesToPixel(this.S);
        float[] fArr = this.S;
        return MPPointF.getInstance(fArr[0], fArr[1]);
    }

    public YAxisRenderer getRendererLeftYAxis() {
        return this.L;
    }

    public YAxisRenderer getRendererRightYAxis() {
        return this.M;
    }

    public XAxisRenderer getRendererXAxis() {
        return this.P;
    }

    @Override // android.view.View
    public float getScaleX() {
        ViewPortHandler viewPortHandler = this.f5281p;
        if (viewPortHandler == null) {
            return 1.0f;
        }
        return viewPortHandler.getScaleX();
    }

    @Override // android.view.View
    public float getScaleY() {
        ViewPortHandler viewPortHandler = this.f5281p;
        if (viewPortHandler == null) {
            return 1.0f;
        }
        return viewPortHandler.getScaleY();
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider
    public Transformer getTransformer(YAxis.AxisDependency axisDependency) {
        return axisDependency == YAxis.AxisDependency.LEFT ? this.N : this.O;
    }

    public MPPointD getValuesByTouchPoint(float f2, float f3, YAxis.AxisDependency axisDependency) {
        MPPointD mPPointD = MPPointD.getInstance(0.0d, 0.0d);
        getValuesByTouchPoint(f2, f3, axisDependency, mPPointD);
        return mPPointD;
    }

    public void getValuesByTouchPoint(float f2, float f3, YAxis.AxisDependency axisDependency, MPPointD mPPointD) {
        getTransformer(axisDependency).getValuesByTouchPoint(f2, f3, mPPointD);
    }

    public float getVisibleXRange() {
        return Math.abs(getHighestVisibleX() - getLowestVisibleX());
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public float getYChartMax() {
        return Math.max(this.J.mAxisMaximum, this.K.mAxisMaximum);
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public float getYChartMin() {
        return Math.min(this.J.mAxisMinimum, this.K.mAxisMinimum);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void h(RectF rectF) {
        rectF.left = 0.0f;
        rectF.right = 0.0f;
        rectF.top = 0.0f;
        rectF.bottom = 0.0f;
        Legend legend = this.f5275j;
        if (legend == null || !legend.isEnabled() || this.f5275j.isDrawInsideEnabled()) {
            return;
        }
        int i2 = AnonymousClass2.f5265c[this.f5275j.getOrientation().ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                return;
            }
            int i3 = AnonymousClass2.f5263a[this.f5275j.getVerticalAlignment().ordinal()];
            if (i3 != 1) {
                if (i3 != 2) {
                    return;
                }
                rectF.bottom += Math.min(this.f5275j.mNeededHeight, this.f5281p.getChartHeight() * this.f5275j.getMaxSizePercent()) + this.f5275j.getYOffset();
                return;
            }
            rectF.top += Math.min(this.f5275j.mNeededHeight, this.f5281p.getChartHeight() * this.f5275j.getMaxSizePercent()) + this.f5275j.getYOffset();
        }
        int i4 = AnonymousClass2.f5264b[this.f5275j.getHorizontalAlignment().ordinal()];
        if (i4 == 1) {
            rectF.left += Math.min(this.f5275j.mNeededWidth, this.f5281p.getChartWidth() * this.f5275j.getMaxSizePercent()) + this.f5275j.getXOffset();
        } else if (i4 == 2) {
            rectF.right += Math.min(this.f5275j.mNeededWidth, this.f5281p.getChartWidth() * this.f5275j.getMaxSizePercent()) + this.f5275j.getXOffset();
        } else if (i4 != 3) {
        } else {
            int i5 = AnonymousClass2.f5263a[this.f5275j.getVerticalAlignment().ordinal()];
            if (i5 != 1) {
                if (i5 != 2) {
                    return;
                }
                rectF.bottom += Math.min(this.f5275j.mNeededHeight, this.f5281p.getChartHeight() * this.f5275j.getMaxSizePercent()) + this.f5275j.getYOffset();
                return;
            }
            rectF.top += Math.min(this.f5275j.mNeededHeight, this.f5281p.getChartHeight() * this.f5275j.getMaxSizePercent()) + this.f5275j.getYOffset();
        }
    }

    public boolean hasNoDragOffset() {
        return this.f5281p.hasNoDragOffset();
    }

    protected void i(Canvas canvas) {
        if (this.D) {
            canvas.drawRect(this.f5281p.getContentRect(), this.B);
        }
        if (this.E) {
            canvas.drawRect(this.f5281p.getContentRect(), this.C);
        }
    }

    public boolean isAnyAxisInverted() {
        return this.J.isInverted() || this.K.isInverted();
    }

    public boolean isAutoScaleMinMaxEnabled() {
        return this.x;
    }

    public boolean isClipValuesToContentEnabled() {
        return this.F;
    }

    public boolean isDoubleTapToZoomEnabled() {
        return this.z;
    }

    public boolean isDragEnabled() {
        return this.mDragXEnabled || this.mDragYEnabled;
    }

    public boolean isDragXEnabled() {
        return this.mDragXEnabled;
    }

    public boolean isDragYEnabled() {
        return this.mDragYEnabled;
    }

    public boolean isDrawBordersEnabled() {
        return this.E;
    }

    public boolean isFullyZoomedOut() {
        return this.f5281p.isFullyZoomedOut();
    }

    public boolean isHighlightPerDragEnabled() {
        return this.A;
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider
    public boolean isInverted(YAxis.AxisDependency axisDependency) {
        return getAxis(axisDependency).isInverted();
    }

    public boolean isKeepPositionOnRotation() {
        return this.H;
    }

    public boolean isPinchZoomEnabled() {
        return this.y;
    }

    public boolean isScaleXEnabled() {
        return this.mScaleXEnabled;
    }

    public boolean isScaleYEnabled() {
        return this.mScaleYEnabled;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float j(YAxis.AxisDependency axisDependency) {
        return (axisDependency == YAxis.AxisDependency.LEFT ? this.J : this.K).mAxisRange;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void k() {
        this.O.prepareMatrixOffset(this.K.isInverted());
        this.N.prepareMatrixOffset(this.J.isInverted());
    }

    protected void l() {
        if (this.f5266a) {
            StringBuilder sb = new StringBuilder();
            sb.append("Preparing Value-Px Matrix, xmin: ");
            sb.append(this.f5272g.mAxisMinimum);
            sb.append(", xmax: ");
            sb.append(this.f5272g.mAxisMaximum);
            sb.append(", xdelta: ");
            sb.append(this.f5272g.mAxisRange);
        }
        Transformer transformer = this.O;
        XAxis xAxis = this.f5272g;
        float f2 = xAxis.mAxisMinimum;
        float f3 = xAxis.mAxisRange;
        YAxis yAxis = this.K;
        transformer.prepareMatrixValuePx(f2, f3, yAxis.mAxisRange, yAxis.mAxisMinimum);
        Transformer transformer2 = this.N;
        XAxis xAxis2 = this.f5272g;
        float f4 = xAxis2.mAxisMinimum;
        float f5 = xAxis2.mAxisRange;
        YAxis yAxis2 = this.J;
        transformer2.prepareMatrixValuePx(f4, f5, yAxis2.mAxisRange, yAxis2.mAxisMinimum);
    }

    public void moveViewTo(float f2, float f3, YAxis.AxisDependency axisDependency) {
        addViewportJob(MoveViewJob.getInstance(this.f5281p, f2, f3 + ((j(axisDependency) / this.f5281p.getScaleY()) / 2.0f), getTransformer(axisDependency), this));
    }

    @TargetApi(11)
    public void moveViewToAnimated(float f2, float f3, YAxis.AxisDependency axisDependency, long j2) {
        if (Build.VERSION.SDK_INT < 11) {
            Log.e(Chart.LOG_TAG, "Unable to execute moveViewToAnimated(...) on API level < 11");
            return;
        }
        MPPointD valuesByTouchPoint = getValuesByTouchPoint(this.f5281p.contentLeft(), this.f5281p.contentTop(), axisDependency);
        addViewportJob(AnimatedMoveViewJob.getInstance(this.f5281p, f2, f3 + ((j(axisDependency) / this.f5281p.getScaleY()) / 2.0f), getTransformer(axisDependency), this, (float) valuesByTouchPoint.x, (float) valuesByTouchPoint.y, j2));
        MPPointD.recycleInstance(valuesByTouchPoint);
    }

    public void moveViewToX(float f2) {
        addViewportJob(MoveViewJob.getInstance(this.f5281p, f2, 0.0f, getTransformer(YAxis.AxisDependency.LEFT), this));
    }

    @Override // com.github.mikephil.charting.charts.Chart
    public void notifyDataSetChanged() {
        if (this.f5267b == null) {
            return;
        }
        DataRenderer dataRenderer = this.f5279n;
        if (dataRenderer != null) {
            dataRenderer.initBuffers();
        }
        g();
        YAxisRenderer yAxisRenderer = this.L;
        YAxis yAxis = this.J;
        yAxisRenderer.computeAxis(yAxis.mAxisMinimum, yAxis.mAxisMaximum, yAxis.isInverted());
        YAxisRenderer yAxisRenderer2 = this.M;
        YAxis yAxis2 = this.K;
        yAxisRenderer2.computeAxis(yAxis2.mAxisMinimum, yAxis2.mAxisMaximum, yAxis2.isInverted());
        XAxisRenderer xAxisRenderer = this.P;
        XAxis xAxis = this.f5272g;
        xAxisRenderer.computeAxis(xAxis.mAxisMinimum, xAxis.mAxisMaximum, false);
        if (this.f5275j != null) {
            this.f5278m.computeLegend(this.f5267b);
        }
        calculateOffsets();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.charts.Chart, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f5267b == null) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        i(canvas);
        if (this.x) {
            f();
        }
        if (this.J.isEnabled()) {
            YAxisRenderer yAxisRenderer = this.L;
            YAxis yAxis = this.J;
            yAxisRenderer.computeAxis(yAxis.mAxisMinimum, yAxis.mAxisMaximum, yAxis.isInverted());
        }
        if (this.K.isEnabled()) {
            YAxisRenderer yAxisRenderer2 = this.M;
            YAxis yAxis2 = this.K;
            yAxisRenderer2.computeAxis(yAxis2.mAxisMinimum, yAxis2.mAxisMaximum, yAxis2.isInverted());
        }
        if (this.f5272g.isEnabled()) {
            XAxisRenderer xAxisRenderer = this.P;
            XAxis xAxis = this.f5272g;
            xAxisRenderer.computeAxis(xAxis.mAxisMinimum, xAxis.mAxisMaximum, false);
        }
        this.P.renderAxisLine(canvas);
        this.L.renderAxisLine(canvas);
        this.M.renderAxisLine(canvas);
        if (this.f5272g.isDrawGridLinesBehindDataEnabled()) {
            this.P.renderGridLines(canvas);
        }
        if (this.J.isDrawGridLinesBehindDataEnabled()) {
            this.L.renderGridLines(canvas);
        }
        if (this.K.isDrawGridLinesBehindDataEnabled()) {
            this.M.renderGridLines(canvas);
        }
        if (this.f5272g.isEnabled() && this.f5272g.isDrawLimitLinesBehindDataEnabled()) {
            this.P.renderLimitLines(canvas);
        }
        if (this.J.isEnabled() && this.J.isDrawLimitLinesBehindDataEnabled()) {
            this.L.renderLimitLines(canvas);
        }
        if (this.K.isEnabled() && this.K.isDrawLimitLinesBehindDataEnabled()) {
            this.M.renderLimitLines(canvas);
        }
        int save = canvas.save();
        canvas.clipRect(this.f5281p.getContentRect());
        this.f5279n.drawData(canvas);
        if (!this.f5272g.isDrawGridLinesBehindDataEnabled()) {
            this.P.renderGridLines(canvas);
        }
        if (!this.J.isDrawGridLinesBehindDataEnabled()) {
            this.L.renderGridLines(canvas);
        }
        if (!this.K.isDrawGridLinesBehindDataEnabled()) {
            this.M.renderGridLines(canvas);
        }
        if (valuesToHighlight()) {
            this.f5279n.drawHighlighted(canvas, this.f5283r);
        }
        canvas.restoreToCount(save);
        this.f5279n.drawExtras(canvas);
        if (this.f5272g.isEnabled() && !this.f5272g.isDrawLimitLinesBehindDataEnabled()) {
            this.P.renderLimitLines(canvas);
        }
        if (this.J.isEnabled() && !this.J.isDrawLimitLinesBehindDataEnabled()) {
            this.L.renderLimitLines(canvas);
        }
        if (this.K.isEnabled() && !this.K.isDrawLimitLinesBehindDataEnabled()) {
            this.M.renderLimitLines(canvas);
        }
        this.P.renderAxisLabels(canvas);
        this.L.renderAxisLabels(canvas);
        this.M.renderAxisLabels(canvas);
        if (isClipValuesToContentEnabled()) {
            int save2 = canvas.save();
            canvas.clipRect(this.f5281p.getContentRect());
            this.f5279n.drawValues(canvas);
            canvas.restoreToCount(save2);
        } else {
            this.f5279n.drawValues(canvas);
        }
        this.f5278m.renderLegend(canvas);
        a(canvas);
        b(canvas);
        if (this.f5266a) {
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            long j2 = this.totalTime + currentTimeMillis2;
            this.totalTime = j2;
            long j3 = this.drawCycles + 1;
            this.drawCycles = j3;
            StringBuilder sb = new StringBuilder();
            sb.append("Drawtime: ");
            sb.append(currentTimeMillis2);
            sb.append(" ms, average: ");
            sb.append(j2 / j3);
            sb.append(" ms, cycles: ");
            sb.append(this.drawCycles);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.charts.Chart, android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        float[] fArr = this.V;
        fArr[1] = 0.0f;
        fArr[0] = 0.0f;
        if (this.H) {
            fArr[0] = this.f5281p.contentLeft();
            this.V[1] = this.f5281p.contentTop();
            getTransformer(YAxis.AxisDependency.LEFT).pixelsToValue(this.V);
        }
        super.onSizeChanged(i2, i3, i4, i5);
        if (this.H) {
            getTransformer(YAxis.AxisDependency.LEFT).pointValuesToPixel(this.V);
            this.f5281p.centerViewPort(this.V, this);
            return;
        }
        ViewPortHandler viewPortHandler = this.f5281p;
        viewPortHandler.refresh(viewPortHandler.getMatrixTouch(), this, true);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        ChartTouchListener chartTouchListener = this.f5277l;
        if (chartTouchListener == null || this.f5267b == null || !this.f5273h) {
            return false;
        }
        return chartTouchListener.onTouch(this, motionEvent);
    }

    public void resetTracking() {
        this.totalTime = 0L;
        this.drawCycles = 0L;
    }

    public void resetViewPortOffsets() {
        this.mCustomViewPortEnabled = false;
        calculateOffsets();
    }

    public void resetZoom() {
        this.f5281p.resetZoom(this.Q);
        this.f5281p.refresh(this.Q, this, false);
        calculateOffsets();
        postInvalidate();
    }

    public void setAutoScaleMinMaxEnabled(boolean z) {
        this.x = z;
    }

    public void setBorderColor(int i2) {
        this.C.setColor(i2);
    }

    public void setBorderWidth(float f2) {
        this.C.setStrokeWidth(Utils.convertDpToPixel(f2));
    }

    public void setClipValuesToContent(boolean z) {
        this.F = z;
    }

    public void setDoubleTapToZoomEnabled(boolean z) {
        this.z = z;
    }

    public void setDragEnabled(boolean z) {
        this.mDragXEnabled = z;
        this.mDragYEnabled = z;
    }

    public void setDragOffsetX(float f2) {
        this.f5281p.setDragOffsetX(f2);
    }

    public void setDragOffsetY(float f2) {
        this.f5281p.setDragOffsetY(f2);
    }

    public void setDragXEnabled(boolean z) {
        this.mDragXEnabled = z;
    }

    public void setDragYEnabled(boolean z) {
        this.mDragYEnabled = z;
    }

    public void setDrawBorders(boolean z) {
        this.E = z;
    }

    public void setDrawGridBackground(boolean z) {
        this.D = z;
    }

    public void setGridBackgroundColor(int i2) {
        this.B.setColor(i2);
    }

    public void setHighlightPerDragEnabled(boolean z) {
        this.A = z;
    }

    public void setKeepPositionOnRotation(boolean z) {
        this.H = z;
    }

    public void setMaxVisibleValueCount(int i2) {
        this.w = i2;
    }

    public void setMinOffset(float f2) {
        this.G = f2;
    }

    public void setOnDrawListener(OnDrawListener onDrawListener) {
        this.I = onDrawListener;
    }

    @Override // com.github.mikephil.charting.charts.Chart
    public void setPaint(Paint paint, int i2) {
        super.setPaint(paint, i2);
        if (i2 != 4) {
            return;
        }
        this.B = paint;
    }

    public void setPinchZoom(boolean z) {
        this.y = z;
    }

    public void setRendererLeftYAxis(YAxisRenderer yAxisRenderer) {
        this.L = yAxisRenderer;
    }

    public void setRendererRightYAxis(YAxisRenderer yAxisRenderer) {
        this.M = yAxisRenderer;
    }

    public void setScaleEnabled(boolean z) {
        this.mScaleXEnabled = z;
        this.mScaleYEnabled = z;
    }

    public void setScaleMinima(float f2, float f3) {
        this.f5281p.setMinimumScaleX(f2);
        this.f5281p.setMinimumScaleY(f3);
    }

    public void setScaleXEnabled(boolean z) {
        this.mScaleXEnabled = z;
    }

    public void setScaleYEnabled(boolean z) {
        this.mScaleYEnabled = z;
    }

    public void setViewPortOffsets(final float f2, final float f3, final float f4, final float f5) {
        this.mCustomViewPortEnabled = true;
        post(new Runnable() { // from class: com.github.mikephil.charting.charts.BarLineChartBase.1
            @Override // java.lang.Runnable
            public void run() {
                BarLineChartBase.this.f5281p.restrainViewPort(f2, f3, f4, f5);
                BarLineChartBase.this.k();
                BarLineChartBase.this.l();
            }
        });
    }

    public void setVisibleXRange(float f2, float f3) {
        float f4 = this.f5272g.mAxisRange;
        this.f5281p.setMinMaxScaleX(f4 / f2, f4 / f3);
    }

    public void setVisibleXRangeMaximum(float f2) {
        this.f5281p.setMinimumScaleX(this.f5272g.mAxisRange / f2);
    }

    public void setVisibleXRangeMinimum(float f2) {
        this.f5281p.setMaximumScaleX(this.f5272g.mAxisRange / f2);
    }

    public void setVisibleYRange(float f2, float f3, YAxis.AxisDependency axisDependency) {
        this.f5281p.setMinMaxScaleY(j(axisDependency) / f2, j(axisDependency) / f3);
    }

    public void setVisibleYRangeMaximum(float f2, YAxis.AxisDependency axisDependency) {
        this.f5281p.setMinimumScaleY(j(axisDependency) / f2);
    }

    public void setVisibleYRangeMinimum(float f2, YAxis.AxisDependency axisDependency) {
        this.f5281p.setMaximumScaleY(j(axisDependency) / f2);
    }

    public void setXAxisRenderer(XAxisRenderer xAxisRenderer) {
        this.P = xAxisRenderer;
    }

    public void zoom(float f2, float f3, float f4, float f5) {
        this.f5281p.zoom(f2, f3, f4, -f5, this.Q);
        this.f5281p.refresh(this.Q, this, false);
        calculateOffsets();
        postInvalidate();
    }

    public void zoom(float f2, float f3, float f4, float f5, YAxis.AxisDependency axisDependency) {
        addViewportJob(ZoomJob.getInstance(this.f5281p, f2, f3, f4, f5, getTransformer(axisDependency), axisDependency, this));
    }

    @TargetApi(11)
    public void zoomAndCenterAnimated(float f2, float f3, float f4, float f5, YAxis.AxisDependency axisDependency, long j2) {
        if (Build.VERSION.SDK_INT < 11) {
            Log.e(Chart.LOG_TAG, "Unable to execute zoomAndCenterAnimated(...) on API level < 11");
            return;
        }
        MPPointD valuesByTouchPoint = getValuesByTouchPoint(this.f5281p.contentLeft(), this.f5281p.contentTop(), axisDependency);
        addViewportJob(AnimatedZoomJob.getInstance(this.f5281p, this, getTransformer(axisDependency), getAxis(axisDependency), this.f5272g.mAxisRange, f2, f3, this.f5281p.getScaleX(), this.f5281p.getScaleY(), f4, f5, (float) valuesByTouchPoint.x, (float) valuesByTouchPoint.y, j2));
        MPPointD.recycleInstance(valuesByTouchPoint);
    }

    public void zoomIn() {
        MPPointF contentCenter = this.f5281p.getContentCenter();
        this.f5281p.zoomIn(contentCenter.x, -contentCenter.y, this.Q);
        this.f5281p.refresh(this.Q, this, false);
        MPPointF.recycleInstance(contentCenter);
        calculateOffsets();
        postInvalidate();
    }

    public void zoomOut() {
        MPPointF contentCenter = this.f5281p.getContentCenter();
        this.f5281p.zoomOut(contentCenter.x, -contentCenter.y, this.Q);
        this.f5281p.refresh(this.Q, this, false);
        MPPointF.recycleInstance(contentCenter);
        calculateOffsets();
        postInvalidate();
    }

    public void zoomToCenter(float f2, float f3) {
        MPPointF centerOffsets = getCenterOffsets();
        Matrix matrix = this.Q;
        this.f5281p.zoom(f2, f3, centerOffsets.x, -centerOffsets.y, matrix);
        this.f5281p.refresh(matrix, this, false);
    }
}
