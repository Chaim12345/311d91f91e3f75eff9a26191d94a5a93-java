package com.github.mikephil.charting.charts;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.PieRadarChartTouchListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
/* loaded from: classes.dex */
public abstract class PieRadarChartBase<T extends ChartData<? extends IDataSet<? extends Entry>>> extends Chart<T> {
    private float mRawRotationAngle;
    private float mRotationAngle;
    protected boolean w;
    protected float x;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.github.mikephil.charting.charts.PieRadarChartBase$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f5289a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f5290b;

        /* renamed from: c  reason: collision with root package name */
        static final /* synthetic */ int[] f5291c;

        static {
            int[] iArr = new int[Legend.LegendOrientation.values().length];
            f5291c = iArr;
            try {
                iArr[Legend.LegendOrientation.VERTICAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5291c[Legend.LegendOrientation.HORIZONTAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[Legend.LegendHorizontalAlignment.values().length];
            f5290b = iArr2;
            try {
                iArr2[Legend.LegendHorizontalAlignment.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f5290b[Legend.LegendHorizontalAlignment.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f5290b[Legend.LegendHorizontalAlignment.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr3 = new int[Legend.LegendVerticalAlignment.values().length];
            f5289a = iArr3;
            try {
                iArr3[Legend.LegendVerticalAlignment.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f5289a[Legend.LegendVerticalAlignment.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public PieRadarChartBase(Context context) {
        super(context);
        this.mRotationAngle = 270.0f;
        this.mRawRotationAngle = 270.0f;
        this.w = true;
        this.x = 0.0f;
    }

    public PieRadarChartBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRotationAngle = 270.0f;
        this.mRawRotationAngle = 270.0f;
        this.w = true;
        this.x = 0.0f;
    }

    public PieRadarChartBase(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mRotationAngle = 270.0f;
        this.mRawRotationAngle = 270.0f;
        this.w = true;
        this.x = 0.0f;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x007b, code lost:
        if (r2 != 2) goto L33;
     */
    @Override // com.github.mikephil.charting.charts.Chart
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void calculateOffsets() {
        float f2;
        float f3;
        float f4;
        float convertDpToPixel;
        float f5;
        float f6;
        float min;
        Legend legend = this.f5275j;
        float f7 = 0.0f;
        if (legend == null || !legend.isEnabled() || this.f5275j.isDrawInsideEnabled()) {
            f2 = 0.0f;
            f3 = 0.0f;
            f4 = 0.0f;
        } else {
            float min2 = Math.min(this.f5275j.mNeededWidth, this.f5281p.getChartWidth() * this.f5275j.getMaxSizePercent());
            int i2 = AnonymousClass2.f5291c[this.f5275j.getOrientation().ordinal()];
            if (i2 != 1) {
                if (i2 == 2 && (this.f5275j.getVerticalAlignment() == Legend.LegendVerticalAlignment.TOP || this.f5275j.getVerticalAlignment() == Legend.LegendVerticalAlignment.BOTTOM)) {
                    min = Math.min(this.f5275j.mNeededHeight + getRequiredLegendOffset(), this.f5281p.getChartHeight() * this.f5275j.getMaxSizePercent());
                    int i3 = AnonymousClass2.f5289a[this.f5275j.getVerticalAlignment().ordinal()];
                    if (i3 != 1) {
                    }
                    f6 = min;
                    convertDpToPixel = 0.0f;
                    f5 = 0.0f;
                    f7 += getRequiredBaseOffset();
                    f2 = convertDpToPixel + getRequiredBaseOffset();
                    f4 = f6 + getRequiredBaseOffset();
                    f3 = f5 + getRequiredBaseOffset();
                }
                convertDpToPixel = 0.0f;
                f5 = 0.0f;
                f6 = f5;
                f7 += getRequiredBaseOffset();
                f2 = convertDpToPixel + getRequiredBaseOffset();
                f4 = f6 + getRequiredBaseOffset();
                f3 = f5 + getRequiredBaseOffset();
            } else {
                if (this.f5275j.getHorizontalAlignment() != Legend.LegendHorizontalAlignment.LEFT && this.f5275j.getHorizontalAlignment() != Legend.LegendHorizontalAlignment.RIGHT) {
                    convertDpToPixel = 0.0f;
                } else if (this.f5275j.getVerticalAlignment() == Legend.LegendVerticalAlignment.CENTER) {
                    convertDpToPixel = min2 + Utils.convertDpToPixel(13.0f);
                } else {
                    convertDpToPixel = min2 + Utils.convertDpToPixel(8.0f);
                    Legend legend2 = this.f5275j;
                    float f8 = legend2.mNeededHeight + legend2.mTextHeightMax;
                    MPPointF center = getCenter();
                    float width = this.f5275j.getHorizontalAlignment() == Legend.LegendHorizontalAlignment.RIGHT ? (getWidth() - convertDpToPixel) + 15.0f : convertDpToPixel - 15.0f;
                    float f9 = f8 + 15.0f;
                    float distanceToCenter = distanceToCenter(width, f9);
                    MPPointF position = getPosition(center, getRadius(), getAngleForPoint(width, f9));
                    float distanceToCenter2 = distanceToCenter(position.x, position.y);
                    float convertDpToPixel2 = Utils.convertDpToPixel(5.0f);
                    if (f9 < center.y || getHeight() - convertDpToPixel <= getWidth()) {
                        convertDpToPixel = distanceToCenter < distanceToCenter2 ? convertDpToPixel2 + (distanceToCenter2 - distanceToCenter) : 0.0f;
                    }
                    MPPointF.recycleInstance(center);
                    MPPointF.recycleInstance(position);
                }
                int i4 = AnonymousClass2.f5290b[this.f5275j.getHorizontalAlignment().ordinal()];
                if (i4 == 1) {
                    f5 = 0.0f;
                    f6 = 0.0f;
                    f7 = convertDpToPixel;
                    convertDpToPixel = 0.0f;
                    f7 += getRequiredBaseOffset();
                    f2 = convertDpToPixel + getRequiredBaseOffset();
                    f4 = f6 + getRequiredBaseOffset();
                    f3 = f5 + getRequiredBaseOffset();
                } else if (i4 != 2) {
                    if (i4 == 3) {
                        int i5 = AnonymousClass2.f5289a[this.f5275j.getVerticalAlignment().ordinal()];
                        if (i5 == 1) {
                            min = Math.min(this.f5275j.mNeededHeight, this.f5281p.getChartHeight() * this.f5275j.getMaxSizePercent());
                            f6 = min;
                            convertDpToPixel = 0.0f;
                            f5 = 0.0f;
                            f7 += getRequiredBaseOffset();
                            f2 = convertDpToPixel + getRequiredBaseOffset();
                            f4 = f6 + getRequiredBaseOffset();
                            f3 = f5 + getRequiredBaseOffset();
                        } else if (i5 == 2) {
                            min = Math.min(this.f5275j.mNeededHeight, this.f5281p.getChartHeight() * this.f5275j.getMaxSizePercent());
                            f5 = min;
                            convertDpToPixel = 0.0f;
                            f6 = 0.0f;
                            f7 += getRequiredBaseOffset();
                            f2 = convertDpToPixel + getRequiredBaseOffset();
                            f4 = f6 + getRequiredBaseOffset();
                            f3 = f5 + getRequiredBaseOffset();
                        }
                    }
                    convertDpToPixel = 0.0f;
                    f5 = 0.0f;
                    f6 = f5;
                    f7 += getRequiredBaseOffset();
                    f2 = convertDpToPixel + getRequiredBaseOffset();
                    f4 = f6 + getRequiredBaseOffset();
                    f3 = f5 + getRequiredBaseOffset();
                } else {
                    f5 = 0.0f;
                    f6 = f5;
                    f7 += getRequiredBaseOffset();
                    f2 = convertDpToPixel + getRequiredBaseOffset();
                    f4 = f6 + getRequiredBaseOffset();
                    f3 = f5 + getRequiredBaseOffset();
                }
            }
        }
        float convertDpToPixel3 = Utils.convertDpToPixel(this.x);
        if (this instanceof RadarChart) {
            XAxis xAxis = getXAxis();
            if (xAxis.isEnabled() && xAxis.isDrawLabelsEnabled()) {
                convertDpToPixel3 = Math.max(convertDpToPixel3, xAxis.mLabelRotatedWidth);
            }
        }
        float extraTopOffset = f4 + getExtraTopOffset();
        float extraRightOffset = f2 + getExtraRightOffset();
        float extraBottomOffset = f3 + getExtraBottomOffset();
        float max = Math.max(convertDpToPixel3, f7 + getExtraLeftOffset());
        float max2 = Math.max(convertDpToPixel3, extraTopOffset);
        float max3 = Math.max(convertDpToPixel3, extraRightOffset);
        float max4 = Math.max(convertDpToPixel3, Math.max(getRequiredBaseOffset(), extraBottomOffset));
        this.f5281p.restrainViewPort(max, max2, max3, max4);
        if (this.f5266a) {
            StringBuilder sb = new StringBuilder();
            sb.append("offsetLeft: ");
            sb.append(max);
            sb.append(", offsetTop: ");
            sb.append(max2);
            sb.append(", offsetRight: ");
            sb.append(max3);
            sb.append(", offsetBottom: ");
            sb.append(max4);
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        ChartTouchListener chartTouchListener = this.f5277l;
        if (chartTouchListener instanceof PieRadarChartTouchListener) {
            ((PieRadarChartTouchListener) chartTouchListener).computeScroll();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.charts.Chart
    public void d() {
        super.d();
        this.f5277l = new PieRadarChartTouchListener(this);
    }

    public float distanceToCenter(float f2, float f3) {
        MPPointF centerOffsets = getCenterOffsets();
        float f4 = centerOffsets.x;
        float f5 = f2 > f4 ? f2 - f4 : f4 - f2;
        float f6 = centerOffsets.y;
        float sqrt = (float) Math.sqrt(Math.pow(f5, 2.0d) + Math.pow(f3 > f6 ? f3 - f6 : f6 - f3, 2.0d));
        MPPointF.recycleInstance(centerOffsets);
        return sqrt;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void f() {
    }

    public float getAngleForPoint(float f2, float f3) {
        MPPointF centerOffsets = getCenterOffsets();
        double d2 = f2 - centerOffsets.x;
        double d3 = f3 - centerOffsets.y;
        float degrees = (float) Math.toDegrees(Math.acos(d3 / Math.sqrt((d2 * d2) + (d3 * d3))));
        if (f2 > centerOffsets.x) {
            degrees = 360.0f - degrees;
        }
        float f4 = degrees + 90.0f;
        if (f4 > 360.0f) {
            f4 -= 360.0f;
        }
        MPPointF.recycleInstance(centerOffsets);
        return f4;
    }

    public float getDiameter() {
        RectF contentRect = this.f5281p.getContentRect();
        contentRect.left += getExtraLeftOffset();
        contentRect.top += getExtraTopOffset();
        contentRect.right -= getExtraRightOffset();
        contentRect.bottom -= getExtraBottomOffset();
        return Math.min(contentRect.width(), contentRect.height());
    }

    public abstract int getIndexForAngle(float f2);

    @Override // com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public int getMaxVisibleCount() {
        return this.f5267b.getEntryCount();
    }

    public float getMinOffset() {
        return this.x;
    }

    public MPPointF getPosition(MPPointF mPPointF, float f2, float f3) {
        MPPointF mPPointF2 = MPPointF.getInstance(0.0f, 0.0f);
        getPosition(mPPointF, f2, f3, mPPointF2);
        return mPPointF2;
    }

    public void getPosition(MPPointF mPPointF, float f2, float f3, MPPointF mPPointF2) {
        double d2 = f2;
        double d3 = f3;
        mPPointF2.x = (float) (mPPointF.x + (Math.cos(Math.toRadians(d3)) * d2));
        mPPointF2.y = (float) (mPPointF.y + (d2 * Math.sin(Math.toRadians(d3))));
    }

    public abstract float getRadius();

    public float getRawRotationAngle() {
        return this.mRawRotationAngle;
    }

    protected abstract float getRequiredBaseOffset();

    protected abstract float getRequiredLegendOffset();

    public float getRotationAngle() {
        return this.mRotationAngle;
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public float getYChartMax() {
        return 0.0f;
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public float getYChartMin() {
        return 0.0f;
    }

    public boolean isRotationEnabled() {
        return this.w;
    }

    @Override // com.github.mikephil.charting.charts.Chart
    public void notifyDataSetChanged() {
        if (this.f5267b == null) {
            return;
        }
        f();
        if (this.f5275j != null) {
            this.f5278m.computeLegend(this.f5267b);
        }
        calculateOffsets();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ChartTouchListener chartTouchListener;
        return (!this.f5273h || (chartTouchListener = this.f5277l) == null) ? super.onTouchEvent(motionEvent) : chartTouchListener.onTouch(this, motionEvent);
    }

    public void setMinOffset(float f2) {
        this.x = f2;
    }

    public void setRotationAngle(float f2) {
        this.mRawRotationAngle = f2;
        this.mRotationAngle = Utils.getNormalizedAngle(f2);
    }

    public void setRotationEnabled(boolean z) {
        this.w = z;
    }

    @SuppressLint({"NewApi"})
    public void spin(int i2, float f2, float f3, Easing.EasingFunction easingFunction) {
        if (Build.VERSION.SDK_INT < 11) {
            return;
        }
        setRotationAngle(f2);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "rotationAngle", f2, f3);
        ofFloat.setDuration(i2);
        ofFloat.setInterpolator(easingFunction);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.github.mikephil.charting.charts.PieRadarChartBase.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PieRadarChartBase.this.postInvalidate();
            }
        });
        ofFloat.start();
    }
}
