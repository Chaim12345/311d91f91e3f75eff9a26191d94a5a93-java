package com.github.mikephil.charting.charts;

import android.animation.ValueAnimator;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.RequiresApi;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.IHighlighter;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.renderer.DataRenderer;
import com.github.mikephil.charting.renderer.LegendRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.bouncycastle.tls.CipherSuite;
/* loaded from: classes.dex */
public abstract class Chart<T extends ChartData<? extends IDataSet<? extends Entry>>> extends ViewGroup implements ChartInterface {
    public static final String LOG_TAG = "MPAndroidChart";
    public static final int PAINT_CENTER_TEXT = 14;
    public static final int PAINT_DESCRIPTION = 11;
    public static final int PAINT_GRID_BACKGROUND = 4;
    public static final int PAINT_HOLE = 13;
    public static final int PAINT_INFO = 7;
    public static final int PAINT_LEGEND_LABEL = 18;

    /* renamed from: a  reason: collision with root package name */
    protected boolean f5266a;

    /* renamed from: b  reason: collision with root package name */
    protected ChartData f5267b;

    /* renamed from: c  reason: collision with root package name */
    protected boolean f5268c;

    /* renamed from: d  reason: collision with root package name */
    protected DefaultValueFormatter f5269d;

    /* renamed from: e  reason: collision with root package name */
    protected Paint f5270e;

    /* renamed from: f  reason: collision with root package name */
    protected Paint f5271f;

    /* renamed from: g  reason: collision with root package name */
    protected XAxis f5272g;

    /* renamed from: h  reason: collision with root package name */
    protected boolean f5273h;

    /* renamed from: i  reason: collision with root package name */
    protected Description f5274i;

    /* renamed from: j  reason: collision with root package name */
    protected Legend f5275j;

    /* renamed from: k  reason: collision with root package name */
    protected OnChartValueSelectedListener f5276k;

    /* renamed from: l  reason: collision with root package name */
    protected ChartTouchListener f5277l;

    /* renamed from: m  reason: collision with root package name */
    protected LegendRenderer f5278m;
    private boolean mDragDecelerationEnabled;
    private float mDragDecelerationFrictionCoef;
    private float mExtraBottomOffset;
    private float mExtraLeftOffset;
    private float mExtraRightOffset;
    private float mExtraTopOffset;
    private OnChartGestureListener mGestureListener;
    private String mNoDataText;
    private boolean mOffsetsCalculated;
    private boolean mUnbind;

    /* renamed from: n  reason: collision with root package name */
    protected DataRenderer f5279n;

    /* renamed from: o  reason: collision with root package name */
    protected IHighlighter f5280o;

    /* renamed from: p  reason: collision with root package name */
    protected ViewPortHandler f5281p;

    /* renamed from: q  reason: collision with root package name */
    protected ChartAnimator f5282q;

    /* renamed from: r  reason: collision with root package name */
    protected Highlight[] f5283r;

    /* renamed from: s  reason: collision with root package name */
    protected float f5284s;

    /* renamed from: t  reason: collision with root package name */
    protected boolean f5285t;
    protected IMarker u;
    protected ArrayList v;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.github.mikephil.charting.charts.Chart$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f5287a;

        static {
            int[] iArr = new int[Bitmap.CompressFormat.values().length];
            f5287a = iArr;
            try {
                iArr[Bitmap.CompressFormat.PNG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5287a[Bitmap.CompressFormat.WEBP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5287a[Bitmap.CompressFormat.JPEG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public Chart(Context context) {
        super(context);
        this.f5266a = false;
        this.f5267b = null;
        this.f5268c = true;
        this.mDragDecelerationEnabled = true;
        this.mDragDecelerationFrictionCoef = 0.9f;
        this.f5269d = new DefaultValueFormatter(0);
        this.f5273h = true;
        this.mNoDataText = "No chart data available.";
        this.f5281p = new ViewPortHandler();
        this.mExtraTopOffset = 0.0f;
        this.mExtraRightOffset = 0.0f;
        this.mExtraBottomOffset = 0.0f;
        this.mExtraLeftOffset = 0.0f;
        this.mOffsetsCalculated = false;
        this.f5284s = 0.0f;
        this.f5285t = true;
        this.v = new ArrayList();
        this.mUnbind = false;
        d();
    }

    public Chart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5266a = false;
        this.f5267b = null;
        this.f5268c = true;
        this.mDragDecelerationEnabled = true;
        this.mDragDecelerationFrictionCoef = 0.9f;
        this.f5269d = new DefaultValueFormatter(0);
        this.f5273h = true;
        this.mNoDataText = "No chart data available.";
        this.f5281p = new ViewPortHandler();
        this.mExtraTopOffset = 0.0f;
        this.mExtraRightOffset = 0.0f;
        this.mExtraBottomOffset = 0.0f;
        this.mExtraLeftOffset = 0.0f;
        this.mOffsetsCalculated = false;
        this.f5284s = 0.0f;
        this.f5285t = true;
        this.v = new ArrayList();
        this.mUnbind = false;
        d();
    }

    public Chart(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f5266a = false;
        this.f5267b = null;
        this.f5268c = true;
        this.mDragDecelerationEnabled = true;
        this.mDragDecelerationFrictionCoef = 0.9f;
        this.f5269d = new DefaultValueFormatter(0);
        this.f5273h = true;
        this.mNoDataText = "No chart data available.";
        this.f5281p = new ViewPortHandler();
        this.mExtraTopOffset = 0.0f;
        this.mExtraRightOffset = 0.0f;
        this.mExtraBottomOffset = 0.0f;
        this.mExtraLeftOffset = 0.0f;
        this.mOffsetsCalculated = false;
        this.f5284s = 0.0f;
        this.f5285t = true;
        this.v = new ArrayList();
        this.mUnbind = false;
        d();
    }

    private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (!(view instanceof ViewGroup)) {
            return;
        }
        int i2 = 0;
        while (true) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (i2 >= viewGroup.getChildCount()) {
                viewGroup.removeAllViews();
                return;
            } else {
                unbindDrawables(viewGroup.getChildAt(i2));
                i2++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Canvas canvas) {
        float f2;
        float f3;
        Description description = this.f5274i;
        if (description == null || !description.isEnabled()) {
            return;
        }
        MPPointF position = this.f5274i.getPosition();
        this.f5270e.setTypeface(this.f5274i.getTypeface());
        this.f5270e.setTextSize(this.f5274i.getTextSize());
        this.f5270e.setColor(this.f5274i.getTextColor());
        this.f5270e.setTextAlign(this.f5274i.getTextAlign());
        if (position == null) {
            f3 = (getWidth() - this.f5281p.offsetRight()) - this.f5274i.getXOffset();
            f2 = (getHeight() - this.f5281p.offsetBottom()) - this.f5274i.getYOffset();
        } else {
            float f4 = position.x;
            f2 = position.y;
            f3 = f4;
        }
        canvas.drawText(this.f5274i.getText(), f3, f2, this.f5270e);
    }

    public void addViewportJob(Runnable runnable) {
        if (this.f5281p.hasChartDimens()) {
            post(runnable);
        } else {
            this.v.add(runnable);
        }
    }

    @RequiresApi(11)
    public void animateX(int i2) {
        this.f5282q.animateX(i2);
    }

    @RequiresApi(11)
    public void animateX(int i2, Easing.EasingFunction easingFunction) {
        this.f5282q.animateX(i2, easingFunction);
    }

    @Deprecated
    public void animateX(int i2, Easing.EasingOption easingOption) {
        this.f5282q.animateX(i2, easingOption);
    }

    @RequiresApi(11)
    public void animateXY(int i2, int i3) {
        this.f5282q.animateXY(i2, i3);
    }

    @RequiresApi(11)
    public void animateXY(int i2, int i3, Easing.EasingFunction easingFunction) {
        this.f5282q.animateXY(i2, i3, easingFunction);
    }

    @RequiresApi(11)
    public void animateXY(int i2, int i3, Easing.EasingFunction easingFunction, Easing.EasingFunction easingFunction2) {
        this.f5282q.animateXY(i2, i3, easingFunction, easingFunction2);
    }

    @Deprecated
    public void animateXY(int i2, int i3, Easing.EasingOption easingOption, Easing.EasingOption easingOption2) {
        this.f5282q.animateXY(i2, i3, easingOption, easingOption2);
    }

    @RequiresApi(11)
    public void animateY(int i2) {
        this.f5282q.animateY(i2);
    }

    @RequiresApi(11)
    public void animateY(int i2, Easing.EasingFunction easingFunction) {
        this.f5282q.animateY(i2, easingFunction);
    }

    @Deprecated
    public void animateY(int i2, Easing.EasingOption easingOption) {
        this.f5282q.animateY(i2, easingOption);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(Canvas canvas) {
        if (this.u == null || !isDrawMarkersEnabled() || !valuesToHighlight()) {
            return;
        }
        int i2 = 0;
        while (true) {
            Highlight[] highlightArr = this.f5283r;
            if (i2 >= highlightArr.length) {
                return;
            }
            Highlight highlight = highlightArr[i2];
            IDataSet dataSetByIndex = this.f5267b.getDataSetByIndex(highlight.getDataSetIndex());
            Entry entryForHighlight = this.f5267b.getEntryForHighlight(this.f5283r[i2]);
            int entryIndex = dataSetByIndex.getEntryIndex(entryForHighlight);
            if (entryForHighlight != null && entryIndex <= dataSetByIndex.getEntryCount() * this.f5282q.getPhaseX()) {
                float[] c2 = c(highlight);
                if (this.f5281p.isInBounds(c2[0], c2[1])) {
                    this.u.refreshContent(entryForHighlight, highlight);
                    this.u.draw(canvas, c2[0], c2[1]);
                }
            }
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float[] c(Highlight highlight) {
        return new float[]{highlight.getDrawX(), highlight.getDrawY()};
    }

    protected abstract void calculateOffsets();

    public void clear() {
        this.f5267b = null;
        this.mOffsetsCalculated = false;
        this.f5283r = null;
        this.f5277l.setLastHighlighted(null);
        invalidate();
    }

    public void clearAllViewportJobs() {
        this.v.clear();
    }

    public void clearValues() {
        this.f5267b.clearValues();
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d() {
        setWillNotDraw(false);
        this.f5282q = Build.VERSION.SDK_INT < 11 ? new ChartAnimator() : new ChartAnimator(new ValueAnimator.AnimatorUpdateListener() { // from class: com.github.mikephil.charting.charts.Chart.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Chart.this.postInvalidate();
            }
        });
        Utils.init(getContext());
        this.f5284s = Utils.convertDpToPixel(500.0f);
        this.f5274i = new Description();
        Legend legend = new Legend();
        this.f5275j = legend;
        this.f5278m = new LegendRenderer(this.f5281p, legend);
        this.f5272g = new XAxis();
        this.f5270e = new Paint(1);
        Paint paint = new Paint(1);
        this.f5271f = paint;
        paint.setColor(Color.rgb(247, (int) CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256, 51));
        this.f5271f.setTextAlign(Paint.Align.CENTER);
        this.f5271f.setTextSize(Utils.convertDpToPixel(12.0f));
    }

    public void disableScroll() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    protected void e(float f2, float f3) {
        ChartData chartData = this.f5267b;
        this.f5269d.setup(Utils.getDecimals((chartData == null || chartData.getEntryCount() < 2) ? Math.max(Math.abs(f2), Math.abs(f3)) : Math.abs(f3 - f2)));
    }

    public void enableScroll() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
    }

    public ChartAnimator getAnimator() {
        return this.f5282q;
    }

    public MPPointF getCenter() {
        return MPPointF.getInstance(getWidth() / 2.0f, getHeight() / 2.0f);
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public MPPointF getCenterOfView() {
        return getCenter();
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public MPPointF getCenterOffsets() {
        return this.f5281p.getContentCenter();
    }

    public Bitmap getChartBitmap() {
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        Drawable background = getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        draw(canvas);
        return createBitmap;
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public RectF getContentRect() {
        return this.f5281p.getContentRect();
    }

    public T getData() {
        return (T) this.f5267b;
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public IValueFormatter getDefaultValueFormatter() {
        return this.f5269d;
    }

    public Description getDescription() {
        return this.f5274i;
    }

    public float getDragDecelerationFrictionCoef() {
        return this.mDragDecelerationFrictionCoef;
    }

    public float getExtraBottomOffset() {
        return this.mExtraBottomOffset;
    }

    public float getExtraLeftOffset() {
        return this.mExtraLeftOffset;
    }

    public float getExtraRightOffset() {
        return this.mExtraRightOffset;
    }

    public float getExtraTopOffset() {
        return this.mExtraTopOffset;
    }

    public Highlight getHighlightByTouchPoint(float f2, float f3) {
        if (this.f5267b == null) {
            Log.e(LOG_TAG, "Can't select by touch. No data set.");
            return null;
        }
        return getHighlighter().getHighlight(f2, f3);
    }

    public Highlight[] getHighlighted() {
        return this.f5283r;
    }

    public IHighlighter getHighlighter() {
        return this.f5280o;
    }

    public ArrayList<Runnable> getJobs() {
        return this.v;
    }

    public Legend getLegend() {
        return this.f5275j;
    }

    public LegendRenderer getLegendRenderer() {
        return this.f5278m;
    }

    public IMarker getMarker() {
        return this.u;
    }

    @Deprecated
    public IMarker getMarkerView() {
        return getMarker();
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public float getMaxHighlightDistance() {
        return this.f5284s;
    }

    public OnChartGestureListener getOnChartGestureListener() {
        return this.mGestureListener;
    }

    public ChartTouchListener getOnTouchListener() {
        return this.f5277l;
    }

    public Paint getPaint(int i2) {
        if (i2 != 7) {
            if (i2 != 11) {
                return null;
            }
            return this.f5270e;
        }
        return this.f5271f;
    }

    public DataRenderer getRenderer() {
        return this.f5279n;
    }

    public ViewPortHandler getViewPortHandler() {
        return this.f5281p;
    }

    public XAxis getXAxis() {
        return this.f5272g;
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public float getXChartMax() {
        return this.f5272g.mAxisMaximum;
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public float getXChartMin() {
        return this.f5272g.mAxisMinimum;
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public float getXRange() {
        return this.f5272g.mAxisRange;
    }

    public float getYMax() {
        return this.f5267b.getYMax();
    }

    public float getYMin() {
        return this.f5267b.getYMin();
    }

    public void highlightValue(float f2, float f3, int i2) {
        highlightValue(f2, f3, i2, true);
    }

    public void highlightValue(float f2, float f3, int i2, boolean z) {
        if (i2 < 0 || i2 >= this.f5267b.getDataSetCount()) {
            highlightValue((Highlight) null, z);
        } else {
            highlightValue(new Highlight(f2, f3, i2), z);
        }
    }

    public void highlightValue(float f2, int i2) {
        highlightValue(f2, i2, true);
    }

    public void highlightValue(float f2, int i2, boolean z) {
        highlightValue(f2, Float.NaN, i2, z);
    }

    public void highlightValue(Highlight highlight) {
        highlightValue(highlight, false);
    }

    public void highlightValue(Highlight highlight, boolean z) {
        Entry entry = null;
        if (highlight == null) {
            this.f5283r = null;
        } else {
            if (this.f5266a) {
                StringBuilder sb = new StringBuilder();
                sb.append("Highlighted: ");
                sb.append(highlight.toString());
            }
            Entry entryForHighlight = this.f5267b.getEntryForHighlight(highlight);
            if (entryForHighlight == null) {
                this.f5283r = null;
                highlight = null;
            } else {
                this.f5283r = new Highlight[]{highlight};
            }
            entry = entryForHighlight;
        }
        setLastHighlighted(this.f5283r);
        if (z && this.f5276k != null) {
            if (valuesToHighlight()) {
                this.f5276k.onValueSelected(entry, highlight);
            } else {
                this.f5276k.onNothingSelected();
            }
        }
        invalidate();
    }

    public void highlightValues(Highlight[] highlightArr) {
        this.f5283r = highlightArr;
        setLastHighlighted(highlightArr);
        invalidate();
    }

    public boolean isDragDecelerationEnabled() {
        return this.mDragDecelerationEnabled;
    }

    @Deprecated
    public boolean isDrawMarkerViewsEnabled() {
        return isDrawMarkersEnabled();
    }

    public boolean isDrawMarkersEnabled() {
        return this.f5285t;
    }

    public boolean isEmpty() {
        ChartData chartData = this.f5267b;
        return chartData == null || chartData.getEntryCount() <= 0;
    }

    public boolean isHighlightPerTapEnabled() {
        return this.f5268c;
    }

    public boolean isLogEnabled() {
        return this.f5266a;
    }

    public abstract void notifyDataSetChanged();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mUnbind) {
            unbindDrawables(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.f5267b == null) {
            if (!TextUtils.isEmpty(this.mNoDataText)) {
                MPPointF center = getCenter();
                canvas.drawText(this.mNoDataText, center.x, center.y, this.f5271f);
            }
        } else if (this.mOffsetsCalculated) {
        } else {
            calculateOffsets();
            this.mOffsetsCalculated = true;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        for (int i6 = 0; i6 < getChildCount(); i6++) {
            getChildAt(i6).layout(i2, i3, i4, i5);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int convertDpToPixel = (int) Utils.convertDpToPixel(50.0f);
        setMeasuredDimension(Math.max(getSuggestedMinimumWidth(), ViewGroup.resolveSize(convertDpToPixel, i2)), Math.max(getSuggestedMinimumHeight(), ViewGroup.resolveSize(convertDpToPixel, i3)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        if (i2 > 0 && i3 > 0 && i2 < 10000 && i3 < 10000) {
            if (this.f5266a) {
                StringBuilder sb = new StringBuilder();
                sb.append("Setting chart dimens, width: ");
                sb.append(i2);
                sb.append(", height: ");
                sb.append(i3);
            }
            this.f5281p.setChartDimens(i2, i3);
        } else if (this.f5266a) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("*Avoiding* setting chart dimens! width: ");
            sb2.append(i2);
            sb2.append(", height: ");
            sb2.append(i3);
        }
        notifyDataSetChanged();
        Iterator it = this.v.iterator();
        while (it.hasNext()) {
            post((Runnable) it.next());
        }
        this.v.clear();
        super.onSizeChanged(i2, i3, i4, i5);
    }

    public void removeViewportJob(Runnable runnable) {
        this.v.remove(runnable);
    }

    public boolean saveToGallery(String str) {
        return saveToGallery(str, "", "MPAndroidChart-Library Save", Bitmap.CompressFormat.PNG, 40);
    }

    public boolean saveToGallery(String str, int i2) {
        return saveToGallery(str, "", "MPAndroidChart-Library Save", Bitmap.CompressFormat.PNG, i2);
    }

    public boolean saveToGallery(String str, String str2, String str3, Bitmap.CompressFormat compressFormat, int i2) {
        i2 = (i2 < 0 || i2 > 100) ? 50 : 50;
        long currentTimeMillis = System.currentTimeMillis();
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        File file = new File(externalStorageDirectory.getAbsolutePath() + "/DCIM/" + str2);
        if (file.exists() || file.mkdirs()) {
            int i3 = AnonymousClass2.f5287a[compressFormat.ordinal()];
            String str4 = "image/png";
            if (i3 != 1) {
                if (i3 != 2) {
                    if (!str.endsWith(".jpg") && !str.endsWith(".jpeg")) {
                        str = str + ".jpg";
                    }
                    str4 = "image/jpeg";
                } else {
                    if (!str.endsWith(".webp")) {
                        str = str + ".webp";
                    }
                    str4 = "image/webp";
                }
            } else if (!str.endsWith(".png")) {
                str = str + ".png";
            }
            String str5 = file.getAbsolutePath() + "/" + str;
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(str5);
                getChartBitmap().compress(compressFormat, i2, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                long length = new File(str5).length();
                ContentValues contentValues = new ContentValues(8);
                contentValues.put("title", str);
                contentValues.put("_display_name", str);
                contentValues.put("date_added", Long.valueOf(currentTimeMillis));
                contentValues.put("mime_type", str4);
                contentValues.put("description", str3);
                contentValues.put("orientation", (Integer) 0);
                contentValues.put("_data", str5);
                contentValues.put("_size", Long.valueOf(length));
                return getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues) != null;
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public boolean saveToPath(String str, String str2) {
        Bitmap chartBitmap = getChartBitmap();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + str2 + "/" + str + ".png");
            chartBitmap.compress(Bitmap.CompressFormat.PNG, 40, fileOutputStream);
            fileOutputStream.close();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void setData(T t2) {
        this.f5267b = t2;
        this.mOffsetsCalculated = false;
        if (t2 == null) {
            return;
        }
        e(t2.getYMin(), t2.getYMax());
        for (IDataSet iDataSet : this.f5267b.getDataSets()) {
            if (iDataSet.needsFormatter() || iDataSet.getValueFormatter() == this.f5269d) {
                iDataSet.setValueFormatter(this.f5269d);
            }
        }
        notifyDataSetChanged();
    }

    public void setDescription(Description description) {
        this.f5274i = description;
    }

    public void setDragDecelerationEnabled(boolean z) {
        this.mDragDecelerationEnabled = z;
    }

    public void setDragDecelerationFrictionCoef(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 >= 1.0f) {
            f2 = 0.999f;
        }
        this.mDragDecelerationFrictionCoef = f2;
    }

    @Deprecated
    public void setDrawMarkerViews(boolean z) {
        setDrawMarkers(z);
    }

    public void setDrawMarkers(boolean z) {
        this.f5285t = z;
    }

    public void setExtraBottomOffset(float f2) {
        this.mExtraBottomOffset = Utils.convertDpToPixel(f2);
    }

    public void setExtraLeftOffset(float f2) {
        this.mExtraLeftOffset = Utils.convertDpToPixel(f2);
    }

    public void setExtraOffsets(float f2, float f3, float f4, float f5) {
        setExtraLeftOffset(f2);
        setExtraTopOffset(f3);
        setExtraRightOffset(f4);
        setExtraBottomOffset(f5);
    }

    public void setExtraRightOffset(float f2) {
        this.mExtraRightOffset = Utils.convertDpToPixel(f2);
    }

    public void setExtraTopOffset(float f2) {
        this.mExtraTopOffset = Utils.convertDpToPixel(f2);
    }

    public void setHardwareAccelerationEnabled(boolean z) {
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(z ? 2 : 1, null);
        } else {
            Log.e(LOG_TAG, "Cannot enable/disable hardware acceleration for devices below API level 11.");
        }
    }

    public void setHighlightPerTapEnabled(boolean z) {
        this.f5268c = z;
    }

    public void setHighlighter(ChartHighlighter chartHighlighter) {
        this.f5280o = chartHighlighter;
    }

    protected void setLastHighlighted(Highlight[] highlightArr) {
        if (highlightArr == null || highlightArr.length <= 0 || highlightArr[0] == null) {
            this.f5277l.setLastHighlighted(null);
        } else {
            this.f5277l.setLastHighlighted(highlightArr[0]);
        }
    }

    public void setLogEnabled(boolean z) {
        this.f5266a = z;
    }

    public void setMarker(IMarker iMarker) {
        this.u = iMarker;
    }

    @Deprecated
    public void setMarkerView(IMarker iMarker) {
        setMarker(iMarker);
    }

    public void setMaxHighlightDistance(float f2) {
        this.f5284s = Utils.convertDpToPixel(f2);
    }

    public void setNoDataText(String str) {
        this.mNoDataText = str;
    }

    public void setNoDataTextColor(int i2) {
        this.f5271f.setColor(i2);
    }

    public void setNoDataTextTypeface(Typeface typeface) {
        this.f5271f.setTypeface(typeface);
    }

    public void setOnChartGestureListener(OnChartGestureListener onChartGestureListener) {
        this.mGestureListener = onChartGestureListener;
    }

    public void setOnChartValueSelectedListener(OnChartValueSelectedListener onChartValueSelectedListener) {
        this.f5276k = onChartValueSelectedListener;
    }

    public void setOnTouchListener(ChartTouchListener chartTouchListener) {
        this.f5277l = chartTouchListener;
    }

    public void setPaint(Paint paint, int i2) {
        if (i2 == 7) {
            this.f5271f = paint;
        } else if (i2 != 11) {
        } else {
            this.f5270e = paint;
        }
    }

    public void setRenderer(DataRenderer dataRenderer) {
        if (dataRenderer != null) {
            this.f5279n = dataRenderer;
        }
    }

    public void setTouchEnabled(boolean z) {
        this.f5273h = z;
    }

    public void setUnbindEnabled(boolean z) {
        this.mUnbind = z;
    }

    public boolean valuesToHighlight() {
        Highlight[] highlightArr = this.f5283r;
        return (highlightArr == null || highlightArr.length <= 0 || highlightArr[0] == null) ? false : true;
    }
}
