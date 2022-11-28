package com.github.mikephil.charting.renderer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.BaseEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.renderer.BarLineScatterCandleBubbleRenderer;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes.dex */
public class LineChartRenderer extends LineRadarRenderer {

    /* renamed from: g  reason: collision with root package name */
    protected LineDataProvider f5413g;

    /* renamed from: h  reason: collision with root package name */
    protected Paint f5414h;

    /* renamed from: i  reason: collision with root package name */
    protected WeakReference f5415i;

    /* renamed from: j  reason: collision with root package name */
    protected Canvas f5416j;

    /* renamed from: k  reason: collision with root package name */
    protected Bitmap.Config f5417k;

    /* renamed from: l  reason: collision with root package name */
    protected Path f5418l;

    /* renamed from: m  reason: collision with root package name */
    protected Path f5419m;
    private float[] mCirclesBuffer;
    private HashMap<IDataSet, DataSetImageCache> mImageCaches;
    private float[] mLineBuffer;

    /* renamed from: n  reason: collision with root package name */
    protected Path f5420n;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.github.mikephil.charting.renderer.LineChartRenderer$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f5421a;

        static {
            int[] iArr = new int[LineDataSet.Mode.values().length];
            f5421a = iArr;
            try {
                iArr[LineDataSet.Mode.LINEAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5421a[LineDataSet.Mode.STEPPED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5421a[LineDataSet.Mode.CUBIC_BEZIER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f5421a[LineDataSet.Mode.HORIZONTAL_BEZIER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class DataSetImageCache {
        private Bitmap[] circleBitmaps;
        private Path mCirclePathBuffer;

        private DataSetImageCache() {
            this.mCirclePathBuffer = new Path();
        }

        /* synthetic */ DataSetImageCache(LineChartRenderer lineChartRenderer, AnonymousClass1 anonymousClass1) {
            this();
        }

        protected void a(ILineDataSet iLineDataSet, boolean z, boolean z2) {
            int circleColorCount = iLineDataSet.getCircleColorCount();
            float circleRadius = iLineDataSet.getCircleRadius();
            float circleHoleRadius = iLineDataSet.getCircleHoleRadius();
            for (int i2 = 0; i2 < circleColorCount; i2++) {
                int i3 = (int) (circleRadius * 2.1d);
                Bitmap createBitmap = Bitmap.createBitmap(i3, i3, Bitmap.Config.ARGB_4444);
                Canvas canvas = new Canvas(createBitmap);
                this.circleBitmaps[i2] = createBitmap;
                LineChartRenderer.this.f5401c.setColor(iLineDataSet.getCircleColor(i2));
                if (z2) {
                    this.mCirclePathBuffer.reset();
                    this.mCirclePathBuffer.addCircle(circleRadius, circleRadius, circleRadius, Path.Direction.CW);
                    this.mCirclePathBuffer.addCircle(circleRadius, circleRadius, circleHoleRadius, Path.Direction.CCW);
                    canvas.drawPath(this.mCirclePathBuffer, LineChartRenderer.this.f5401c);
                } else {
                    canvas.drawCircle(circleRadius, circleRadius, circleRadius, LineChartRenderer.this.f5401c);
                    if (z) {
                        canvas.drawCircle(circleRadius, circleRadius, circleHoleRadius, LineChartRenderer.this.f5414h);
                    }
                }
            }
        }

        protected Bitmap b(int i2) {
            Bitmap[] bitmapArr = this.circleBitmaps;
            return bitmapArr[i2 % bitmapArr.length];
        }

        protected boolean c(ILineDataSet iLineDataSet) {
            int circleColorCount = iLineDataSet.getCircleColorCount();
            Bitmap[] bitmapArr = this.circleBitmaps;
            if (bitmapArr == null) {
                this.circleBitmaps = new Bitmap[circleColorCount];
                return true;
            } else if (bitmapArr.length != circleColorCount) {
                this.circleBitmaps = new Bitmap[circleColorCount];
                return true;
            } else {
                return false;
            }
        }
    }

    public LineChartRenderer(LineDataProvider lineDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.f5417k = Bitmap.Config.ARGB_8888;
        this.f5418l = new Path();
        this.f5419m = new Path();
        this.mLineBuffer = new float[4];
        this.f5420n = new Path();
        this.mImageCaches = new HashMap<>();
        this.mCirclesBuffer = new float[2];
        this.f5413g = lineDataProvider;
        Paint paint = new Paint(1);
        this.f5414h = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f5414h.setColor(-1);
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    /* JADX WARN: Type inference failed for: r5v4, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    private void generateFilledPath(ILineDataSet iLineDataSet, int i2, int i3, Path path) {
        float fillLinePosition = iLineDataSet.getFillFormatter().getFillLinePosition(iLineDataSet, this.f5413g);
        float phaseY = this.f5400b.getPhaseY();
        boolean z = iLineDataSet.getMode() == LineDataSet.Mode.STEPPED;
        path.reset();
        ?? entryForIndex = iLineDataSet.getEntryForIndex(i2);
        path.moveTo(entryForIndex.getX(), fillLinePosition);
        path.lineTo(entryForIndex.getX(), entryForIndex.getY() * phaseY);
        Entry entry = null;
        int i4 = i2 + 1;
        BaseEntry baseEntry = entryForIndex;
        while (i4 <= i3) {
            ?? entryForIndex2 = iLineDataSet.getEntryForIndex(i4);
            if (z) {
                path.lineTo(entryForIndex2.getX(), baseEntry.getY() * phaseY);
            }
            path.lineTo(entryForIndex2.getX(), entryForIndex2.getY() * phaseY);
            i4++;
            baseEntry = entryForIndex2;
            entry = entryForIndex2;
        }
        if (entry != null) {
            path.lineTo(entry.getX(), fillLinePosition);
        }
        path.close();
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        int chartWidth = (int) this.f5436a.getChartWidth();
        int chartHeight = (int) this.f5436a.getChartHeight();
        WeakReference weakReference = this.f5415i;
        Bitmap bitmap = weakReference == null ? null : (Bitmap) weakReference.get();
        if (bitmap == null || bitmap.getWidth() != chartWidth || bitmap.getHeight() != chartHeight) {
            if (chartWidth <= 0 || chartHeight <= 0) {
                return;
            }
            bitmap = Bitmap.createBitmap(chartWidth, chartHeight, this.f5417k);
            this.f5415i = new WeakReference(bitmap);
            this.f5416j = new Canvas(bitmap);
        }
        bitmap.eraseColor(0);
        for (T t2 : this.f5413g.getLineData().getDataSets()) {
            if (t2.isVisible()) {
                k(canvas, t2);
            }
        }
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.f5401c);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
        h(canvas);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v2, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        LineData lineData = this.f5413g.getLineData();
        for (Highlight highlight : highlightArr) {
            ILineDataSet iLineDataSet = (ILineDataSet) lineData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iLineDataSet != null && iLineDataSet.isHighlightEnabled()) {
                ?? entryForXValue = iLineDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (c(entryForXValue, iLineDataSet)) {
                    MPPointD pixelForValues = this.f5413g.getTransformer(iLineDataSet.getAxisDependency()).getPixelForValues(entryForXValue.getX(), entryForXValue.getY() * this.f5400b.getPhaseY());
                    highlight.setDraw((float) pixelForValues.x, (float) pixelForValues.y);
                    e(canvas, (float) pixelForValues.x, (float) pixelForValues.y, iLineDataSet);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v0, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        int i2;
        MPPointF mPPointF;
        float f2;
        float f3;
        if (b(this.f5413g)) {
            List<T> dataSets = this.f5413g.getLineData().getDataSets();
            for (int i3 = 0; i3 < dataSets.size(); i3++) {
                ILineDataSet iLineDataSet = (ILineDataSet) dataSets.get(i3);
                if (d(iLineDataSet) && iLineDataSet.getEntryCount() >= 1) {
                    a(iLineDataSet);
                    Transformer transformer = this.f5413g.getTransformer(iLineDataSet.getAxisDependency());
                    int circleRadius = (int) (iLineDataSet.getCircleRadius() * 1.75f);
                    if (!iLineDataSet.isDrawCirclesEnabled()) {
                        circleRadius /= 2;
                    }
                    int i4 = circleRadius;
                    this.f5392f.set(this.f5413g, iLineDataSet);
                    float phaseX = this.f5400b.getPhaseX();
                    float phaseY = this.f5400b.getPhaseY();
                    BarLineScatterCandleBubbleRenderer.XBounds xBounds = this.f5392f;
                    float[] generateTransformedValuesLine = transformer.generateTransformedValuesLine(iLineDataSet, phaseX, phaseY, xBounds.min, xBounds.max);
                    MPPointF mPPointF2 = MPPointF.getInstance(iLineDataSet.getIconsOffset());
                    mPPointF2.x = Utils.convertDpToPixel(mPPointF2.x);
                    mPPointF2.y = Utils.convertDpToPixel(mPPointF2.y);
                    int i5 = 0;
                    while (i5 < generateTransformedValuesLine.length) {
                        float f4 = generateTransformedValuesLine[i5];
                        float f5 = generateTransformedValuesLine[i5 + 1];
                        if (!this.f5436a.isInBoundsRight(f4)) {
                            break;
                        }
                        if (this.f5436a.isInBoundsLeft(f4) && this.f5436a.isInBoundsY(f5)) {
                            int i6 = i5 / 2;
                            ?? entryForIndex = iLineDataSet.getEntryForIndex(this.f5392f.min + i6);
                            if (iLineDataSet.isDrawValuesEnabled()) {
                                f2 = f5;
                                f3 = f4;
                                i2 = i5;
                                mPPointF = mPPointF2;
                                drawValue(canvas, iLineDataSet.getValueFormatter(), entryForIndex.getY(), entryForIndex, i3, f4, f5 - i4, iLineDataSet.getValueTextColor(i6));
                            } else {
                                f2 = f5;
                                f3 = f4;
                                i2 = i5;
                                mPPointF = mPPointF2;
                            }
                            if (entryForIndex.getIcon() != null && iLineDataSet.isDrawIconsEnabled()) {
                                Drawable icon = entryForIndex.getIcon();
                                Utils.drawImage(canvas, icon, (int) (f3 + mPPointF.x), (int) (f2 + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                            }
                        } else {
                            i2 = i5;
                            mPPointF = mPPointF2;
                        }
                        i5 = i2 + 2;
                        mPPointF2 = mPPointF;
                    }
                    MPPointF.recycleInstance(mPPointF2);
                }
            }
        }
    }

    public Bitmap.Config getBitmapConfig() {
        return this.f5417k;
    }

    /* JADX WARN: Type inference failed for: r14v2, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    protected void h(Canvas canvas) {
        DataSetImageCache dataSetImageCache;
        Bitmap b2;
        this.f5401c.setStyle(Paint.Style.FILL);
        float phaseY = this.f5400b.getPhaseY();
        float[] fArr = this.mCirclesBuffer;
        char c2 = 0;
        float f2 = 0.0f;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        List<T> dataSets = this.f5413g.getLineData().getDataSets();
        int i2 = 0;
        while (i2 < dataSets.size()) {
            ILineDataSet iLineDataSet = (ILineDataSet) dataSets.get(i2);
            if (iLineDataSet.isVisible() && iLineDataSet.isDrawCirclesEnabled() && iLineDataSet.getEntryCount() != 0) {
                this.f5414h.setColor(iLineDataSet.getCircleHoleColor());
                Transformer transformer = this.f5413g.getTransformer(iLineDataSet.getAxisDependency());
                this.f5392f.set(this.f5413g, iLineDataSet);
                float circleRadius = iLineDataSet.getCircleRadius();
                float circleHoleRadius = iLineDataSet.getCircleHoleRadius();
                boolean z = (!iLineDataSet.isDrawCircleHoleEnabled() || circleHoleRadius >= circleRadius || circleHoleRadius <= f2) ? c2 == 1 ? 1 : 0 : true;
                boolean z2 = (z && iLineDataSet.getCircleHoleColor() == 1122867) ? true : c2 == 1 ? 1 : 0;
                if (this.mImageCaches.containsKey(iLineDataSet)) {
                    dataSetImageCache = this.mImageCaches.get(iLineDataSet);
                } else {
                    dataSetImageCache = new DataSetImageCache(this, null);
                    this.mImageCaches.put(iLineDataSet, dataSetImageCache);
                }
                if (dataSetImageCache.c(iLineDataSet)) {
                    dataSetImageCache.a(iLineDataSet, z, z2);
                }
                BarLineScatterCandleBubbleRenderer.XBounds xBounds = this.f5392f;
                int i3 = xBounds.range;
                int i4 = xBounds.min;
                int i5 = i3 + i4;
                char c3 = c2;
                while (i4 <= i5) {
                    ?? entryForIndex = iLineDataSet.getEntryForIndex(i4);
                    if (entryForIndex == 0) {
                        break;
                    }
                    this.mCirclesBuffer[c3] = entryForIndex.getX();
                    this.mCirclesBuffer[1] = entryForIndex.getY() * phaseY;
                    transformer.pointValuesToPixel(this.mCirclesBuffer);
                    if (!this.f5436a.isInBoundsRight(this.mCirclesBuffer[c3])) {
                        break;
                    }
                    if (this.f5436a.isInBoundsLeft(this.mCirclesBuffer[c3]) && this.f5436a.isInBoundsY(this.mCirclesBuffer[1]) && (b2 = dataSetImageCache.b(i4)) != null) {
                        float[] fArr2 = this.mCirclesBuffer;
                        canvas.drawBitmap(b2, fArr2[c3] - circleRadius, fArr2[1] - circleRadius, (Paint) null);
                    }
                    i4++;
                    c3 = 0;
                }
            }
            i2++;
            c2 = 0;
            f2 = 0.0f;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v4, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    /* JADX WARN: Type inference failed for: r2v10, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    protected void i(ILineDataSet iLineDataSet) {
        float phaseY = this.f5400b.getPhaseY();
        Transformer transformer = this.f5413g.getTransformer(iLineDataSet.getAxisDependency());
        this.f5392f.set(this.f5413g, iLineDataSet);
        float cubicIntensity = iLineDataSet.getCubicIntensity();
        this.f5418l.reset();
        BarLineScatterCandleBubbleRenderer.XBounds xBounds = this.f5392f;
        if (xBounds.range >= 1) {
            int i2 = xBounds.min + 1;
            T entryForIndex = iLineDataSet.getEntryForIndex(Math.max(i2 - 2, 0));
            ?? entryForIndex2 = iLineDataSet.getEntryForIndex(Math.max(i2 - 1, 0));
            int i3 = -1;
            if (entryForIndex2 != 0) {
                this.f5418l.moveTo(entryForIndex2.getX(), entryForIndex2.getY() * phaseY);
                int i4 = this.f5392f.min + 1;
                Entry entry = entryForIndex2;
                Entry entry2 = entryForIndex2;
                Entry entry3 = entryForIndex;
                while (true) {
                    BarLineScatterCandleBubbleRenderer.XBounds xBounds2 = this.f5392f;
                    Entry entry4 = entry2;
                    if (i4 > xBounds2.range + xBounds2.min) {
                        break;
                    }
                    if (i3 != i4) {
                        entry4 = iLineDataSet.getEntryForIndex(i4);
                    }
                    int i5 = i4 + 1;
                    if (i5 < iLineDataSet.getEntryCount()) {
                        i4 = i5;
                    }
                    ?? entryForIndex3 = iLineDataSet.getEntryForIndex(i4);
                    this.f5418l.cubicTo(entry.getX() + ((entry4.getX() - entry3.getX()) * cubicIntensity), (entry.getY() + ((entry4.getY() - entry3.getY()) * cubicIntensity)) * phaseY, entry4.getX() - ((entryForIndex3.getX() - entry.getX()) * cubicIntensity), (entry4.getY() - ((entryForIndex3.getY() - entry.getY()) * cubicIntensity)) * phaseY, entry4.getX(), entry4.getY() * phaseY);
                    entry3 = entry;
                    entry = entry4;
                    entry2 = entryForIndex3;
                    int i6 = i4;
                    i4 = i5;
                    i3 = i6;
                }
            } else {
                return;
            }
        }
        if (iLineDataSet.isDrawFilledEnabled()) {
            this.f5419m.reset();
            this.f5419m.addPath(this.f5418l);
            j(this.f5416j, iLineDataSet, this.f5419m, transformer, this.f5392f);
        }
        this.f5401c.setColor(iLineDataSet.getColor());
        this.f5401c.setStyle(Paint.Style.STROKE);
        transformer.pathValueToPixel(this.f5418l);
        this.f5416j.drawPath(this.f5418l, this.f5401c);
        this.f5401c.setPathEffect(null);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.github.mikephil.charting.data.Entry] */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.github.mikephil.charting.data.Entry] */
    protected void j(Canvas canvas, ILineDataSet iLineDataSet, Path path, Transformer transformer, BarLineScatterCandleBubbleRenderer.XBounds xBounds) {
        float fillLinePosition = iLineDataSet.getFillFormatter().getFillLinePosition(iLineDataSet, this.f5413g);
        path.lineTo(iLineDataSet.getEntryForIndex(xBounds.min + xBounds.range).getX(), fillLinePosition);
        path.lineTo(iLineDataSet.getEntryForIndex(xBounds.min).getX(), fillLinePosition);
        path.close();
        transformer.pathValueToPixel(path);
        Drawable fillDrawable = iLineDataSet.getFillDrawable();
        if (fillDrawable != null) {
            g(canvas, path, fillDrawable);
        } else {
            f(canvas, path, iLineDataSet.getFillColor(), iLineDataSet.getFillAlpha());
        }
    }

    protected void k(Canvas canvas, ILineDataSet iLineDataSet) {
        if (iLineDataSet.getEntryCount() < 1) {
            return;
        }
        this.f5401c.setStrokeWidth(iLineDataSet.getLineWidth());
        this.f5401c.setPathEffect(iLineDataSet.getDashPathEffect());
        int i2 = AnonymousClass1.f5421a[iLineDataSet.getMode().ordinal()];
        if (i2 == 3) {
            i(iLineDataSet);
        } else if (i2 != 4) {
            m(canvas, iLineDataSet);
        } else {
            l(iLineDataSet);
        }
        this.f5401c.setPathEffect(null);
    }

    /* JADX WARN: Type inference failed for: r2v6, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    /* JADX WARN: Type inference failed for: r4v4, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    protected void l(ILineDataSet iLineDataSet) {
        float phaseY = this.f5400b.getPhaseY();
        Transformer transformer = this.f5413g.getTransformer(iLineDataSet.getAxisDependency());
        this.f5392f.set(this.f5413g, iLineDataSet);
        this.f5418l.reset();
        BarLineScatterCandleBubbleRenderer.XBounds xBounds = this.f5392f;
        if (xBounds.range >= 1) {
            ?? entryForIndex = iLineDataSet.getEntryForIndex(xBounds.min);
            this.f5418l.moveTo(entryForIndex.getX(), entryForIndex.getY() * phaseY);
            int i2 = this.f5392f.min + 1;
            Entry entry = entryForIndex;
            while (true) {
                BarLineScatterCandleBubbleRenderer.XBounds xBounds2 = this.f5392f;
                if (i2 > xBounds2.range + xBounds2.min) {
                    break;
                }
                ?? entryForIndex2 = iLineDataSet.getEntryForIndex(i2);
                float x = entry.getX() + ((entryForIndex2.getX() - entry.getX()) / 2.0f);
                this.f5418l.cubicTo(x, entry.getY() * phaseY, x, entryForIndex2.getY() * phaseY, entryForIndex2.getX(), entryForIndex2.getY() * phaseY);
                i2++;
                entry = entryForIndex2;
            }
        }
        if (iLineDataSet.isDrawFilledEnabled()) {
            this.f5419m.reset();
            this.f5419m.addPath(this.f5418l);
            j(this.f5416j, iLineDataSet, this.f5419m, transformer, this.f5392f);
        }
        this.f5401c.setColor(iLineDataSet.getColor());
        this.f5401c.setStyle(Paint.Style.STROKE);
        transformer.pathValueToPixel(this.f5418l);
        this.f5416j.drawPath(this.f5418l, this.f5401c);
        this.f5401c.setPathEffect(null);
    }

    /* JADX WARN: Type inference failed for: r10v11, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    /* JADX WARN: Type inference failed for: r13v4, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    /* JADX WARN: Type inference failed for: r6v22, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    /* JADX WARN: Type inference failed for: r6v4, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    protected void m(Canvas canvas, ILineDataSet iLineDataSet) {
        int entryCount = iLineDataSet.getEntryCount();
        boolean isDrawSteppedEnabled = iLineDataSet.isDrawSteppedEnabled();
        int i2 = isDrawSteppedEnabled ? 4 : 2;
        Transformer transformer = this.f5413g.getTransformer(iLineDataSet.getAxisDependency());
        float phaseY = this.f5400b.getPhaseY();
        this.f5401c.setStyle(Paint.Style.STROKE);
        Canvas canvas2 = iLineDataSet.isDashedLineEnabled() ? this.f5416j : canvas;
        this.f5392f.set(this.f5413g, iLineDataSet);
        if (iLineDataSet.isDrawFilledEnabled() && entryCount > 0) {
            n(canvas, iLineDataSet, transformer, this.f5392f);
        }
        if (iLineDataSet.getColors().size() > 1) {
            int i3 = i2 * 2;
            if (this.mLineBuffer.length <= i3) {
                this.mLineBuffer = new float[i2 * 4];
            }
            int i4 = this.f5392f.min;
            while (true) {
                BarLineScatterCandleBubbleRenderer.XBounds xBounds = this.f5392f;
                if (i4 > xBounds.range + xBounds.min) {
                    break;
                }
                ?? entryForIndex = iLineDataSet.getEntryForIndex(i4);
                if (entryForIndex != 0) {
                    this.mLineBuffer[0] = entryForIndex.getX();
                    this.mLineBuffer[1] = entryForIndex.getY() * phaseY;
                    if (i4 < this.f5392f.max) {
                        ?? entryForIndex2 = iLineDataSet.getEntryForIndex(i4 + 1);
                        if (entryForIndex2 == 0) {
                            break;
                        }
                        float[] fArr = this.mLineBuffer;
                        float x = entryForIndex2.getX();
                        if (isDrawSteppedEnabled) {
                            fArr[2] = x;
                            float[] fArr2 = this.mLineBuffer;
                            fArr2[3] = fArr2[1];
                            fArr2[4] = fArr2[2];
                            fArr2[5] = fArr2[3];
                            fArr2[6] = entryForIndex2.getX();
                            this.mLineBuffer[7] = entryForIndex2.getY() * phaseY;
                        } else {
                            fArr[2] = x;
                            this.mLineBuffer[3] = entryForIndex2.getY() * phaseY;
                        }
                    } else {
                        float[] fArr3 = this.mLineBuffer;
                        fArr3[2] = fArr3[0];
                        fArr3[3] = fArr3[1];
                    }
                    transformer.pointValuesToPixel(this.mLineBuffer);
                    if (!this.f5436a.isInBoundsRight(this.mLineBuffer[0])) {
                        break;
                    } else if (this.f5436a.isInBoundsLeft(this.mLineBuffer[2]) && (this.f5436a.isInBoundsTop(this.mLineBuffer[1]) || this.f5436a.isInBoundsBottom(this.mLineBuffer[3]))) {
                        this.f5401c.setColor(iLineDataSet.getColor(i4));
                        canvas2.drawLines(this.mLineBuffer, 0, i3, this.f5401c);
                    }
                }
                i4++;
            }
        } else {
            int i5 = entryCount * i2;
            if (this.mLineBuffer.length < Math.max(i5, i2) * 2) {
                this.mLineBuffer = new float[Math.max(i5, i2) * 4];
            }
            if (iLineDataSet.getEntryForIndex(this.f5392f.min) != 0) {
                int i6 = this.f5392f.min;
                int i7 = 0;
                while (true) {
                    BarLineScatterCandleBubbleRenderer.XBounds xBounds2 = this.f5392f;
                    if (i6 > xBounds2.range + xBounds2.min) {
                        break;
                    }
                    ?? entryForIndex3 = iLineDataSet.getEntryForIndex(i6 == 0 ? 0 : i6 - 1);
                    ?? entryForIndex4 = iLineDataSet.getEntryForIndex(i6);
                    if (entryForIndex3 != 0 && entryForIndex4 != 0) {
                        int i8 = i7 + 1;
                        this.mLineBuffer[i7] = entryForIndex3.getX();
                        int i9 = i8 + 1;
                        this.mLineBuffer[i8] = entryForIndex3.getY() * phaseY;
                        if (isDrawSteppedEnabled) {
                            int i10 = i9 + 1;
                            this.mLineBuffer[i9] = entryForIndex4.getX();
                            int i11 = i10 + 1;
                            this.mLineBuffer[i10] = entryForIndex3.getY() * phaseY;
                            int i12 = i11 + 1;
                            this.mLineBuffer[i11] = entryForIndex4.getX();
                            i9 = i12 + 1;
                            this.mLineBuffer[i12] = entryForIndex3.getY() * phaseY;
                        }
                        int i13 = i9 + 1;
                        this.mLineBuffer[i9] = entryForIndex4.getX();
                        this.mLineBuffer[i13] = entryForIndex4.getY() * phaseY;
                        i7 = i13 + 1;
                    }
                    i6++;
                }
                if (i7 > 0) {
                    transformer.pointValuesToPixel(this.mLineBuffer);
                    this.f5401c.setColor(iLineDataSet.getColor());
                    canvas2.drawLines(this.mLineBuffer, 0, Math.max((this.f5392f.range + 1) * i2, i2) * 2, this.f5401c);
                }
            }
        }
        this.f5401c.setPathEffect(null);
    }

    protected void n(Canvas canvas, ILineDataSet iLineDataSet, Transformer transformer, BarLineScatterCandleBubbleRenderer.XBounds xBounds) {
        int i2;
        int i3;
        Path path = this.f5420n;
        int i4 = xBounds.min;
        int i5 = xBounds.range + i4;
        int i6 = 0;
        do {
            i2 = (i6 * 128) + i4;
            i3 = i2 + 128;
            if (i3 > i5) {
                i3 = i5;
            }
            if (i2 <= i3) {
                generateFilledPath(iLineDataSet, i2, i3, path);
                transformer.pathValueToPixel(path);
                Drawable fillDrawable = iLineDataSet.getFillDrawable();
                if (fillDrawable != null) {
                    g(canvas, path, fillDrawable);
                } else {
                    f(canvas, path, iLineDataSet.getFillColor(), iLineDataSet.getFillAlpha());
                }
            }
            i6++;
        } while (i2 <= i3);
    }

    public void releaseBitmap() {
        Canvas canvas = this.f5416j;
        if (canvas != null) {
            canvas.setBitmap(null);
            this.f5416j = null;
        }
        WeakReference weakReference = this.f5415i;
        if (weakReference != null) {
            Bitmap bitmap = (Bitmap) weakReference.get();
            if (bitmap != null) {
                bitmap.recycle();
            }
            this.f5415i.clear();
            this.f5415i = null;
        }
    }

    public void setBitmapConfig(Bitmap.Config config) {
        this.f5417k = config;
        releaseBitmap();
    }
}
