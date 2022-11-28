package com.github.mikephil.charting.listener;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
/* loaded from: classes.dex */
public class BarLineChartTouchListener extends ChartTouchListener<BarLineChartBase<? extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>>> {
    private IDataSet mClosestDataSetToTouch;
    private MPPointF mDecelerationCurrentPoint;
    private long mDecelerationLastTime;
    private MPPointF mDecelerationVelocity;
    private float mDragTriggerDist;
    private Matrix mMatrix;
    private float mMinScalePointerDistance;
    private float mSavedDist;
    private Matrix mSavedMatrix;
    private float mSavedXDist;
    private float mSavedYDist;
    private MPPointF mTouchPointCenter;
    private MPPointF mTouchStartPoint;
    private VelocityTracker mVelocityTracker;

    public BarLineChartTouchListener(BarLineChartBase<? extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>> barLineChartBase, Matrix matrix, float f2) {
        super(barLineChartBase);
        this.mMatrix = new Matrix();
        this.mSavedMatrix = new Matrix();
        this.mTouchStartPoint = MPPointF.getInstance(0.0f, 0.0f);
        this.mTouchPointCenter = MPPointF.getInstance(0.0f, 0.0f);
        this.mSavedXDist = 1.0f;
        this.mSavedYDist = 1.0f;
        this.mSavedDist = 1.0f;
        this.mDecelerationLastTime = 0L;
        this.mDecelerationCurrentPoint = MPPointF.getInstance(0.0f, 0.0f);
        this.mDecelerationVelocity = MPPointF.getInstance(0.0f, 0.0f);
        this.mMatrix = matrix;
        this.mDragTriggerDist = Utils.convertDpToPixel(f2);
        this.mMinScalePointerDistance = Utils.convertDpToPixel(3.5f);
    }

    private static float getXDist(MotionEvent motionEvent) {
        return Math.abs(motionEvent.getX(0) - motionEvent.getX(1));
    }

    private static float getYDist(MotionEvent motionEvent) {
        return Math.abs(motionEvent.getY(0) - motionEvent.getY(1));
    }

    private boolean inverted() {
        IDataSet iDataSet;
        return (this.mClosestDataSetToTouch == null && ((BarLineChartBase) this.f5380e).isAnyAxisInverted()) || ((iDataSet = this.mClosestDataSetToTouch) != null && ((BarLineChartBase) this.f5380e).isInverted(iDataSet.getAxisDependency()));
    }

    private static void midPoint(MPPointF mPPointF, MotionEvent motionEvent) {
        mPPointF.x = (motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f;
        mPPointF.y = (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f;
    }

    private void performDrag(MotionEvent motionEvent, float f2, float f3) {
        this.f5376a = ChartTouchListener.ChartGesture.DRAG;
        this.mMatrix.set(this.mSavedMatrix);
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.f5380e).getOnChartGestureListener();
        if (inverted()) {
            if (this.f5380e instanceof HorizontalBarChart) {
                f2 = -f2;
            } else {
                f3 = -f3;
            }
        }
        this.mMatrix.postTranslate(f2, f3);
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartTranslate(motionEvent, f2, f3);
        }
    }

    private void performHighlightDrag(MotionEvent motionEvent) {
        Highlight highlightByTouchPoint = ((BarLineChartBase) this.f5380e).getHighlightByTouchPoint(motionEvent.getX(), motionEvent.getY());
        if (highlightByTouchPoint == null || highlightByTouchPoint.equalTo(this.f5378c)) {
            return;
        }
        this.f5378c = highlightByTouchPoint;
        ((BarLineChartBase) this.f5380e).highlightValue(highlightByTouchPoint, true);
    }

    private void performZoom(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() >= 2) {
            OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.f5380e).getOnChartGestureListener();
            float spacing = spacing(motionEvent);
            if (spacing > this.mMinScalePointerDistance) {
                MPPointF mPPointF = this.mTouchPointCenter;
                MPPointF trans = getTrans(mPPointF.x, mPPointF.y);
                ViewPortHandler viewPortHandler = ((BarLineChartBase) this.f5380e).getViewPortHandler();
                int i2 = this.f5377b;
                if (i2 == 4) {
                    this.f5376a = ChartTouchListener.ChartGesture.PINCH_ZOOM;
                    float f2 = spacing / this.mSavedDist;
                    boolean z = f2 < 1.0f;
                    boolean canZoomOutMoreX = z ? viewPortHandler.canZoomOutMoreX() : viewPortHandler.canZoomInMoreX();
                    boolean canZoomOutMoreY = z ? viewPortHandler.canZoomOutMoreY() : viewPortHandler.canZoomInMoreY();
                    float f3 = ((BarLineChartBase) this.f5380e).isScaleXEnabled() ? f2 : 1.0f;
                    float f4 = ((BarLineChartBase) this.f5380e).isScaleYEnabled() ? f2 : 1.0f;
                    if (canZoomOutMoreY || canZoomOutMoreX) {
                        this.mMatrix.set(this.mSavedMatrix);
                        this.mMatrix.postScale(f3, f4, trans.x, trans.y);
                        if (onChartGestureListener != null) {
                            onChartGestureListener.onChartScale(motionEvent, f3, f4);
                        }
                    }
                } else if (i2 == 2 && ((BarLineChartBase) this.f5380e).isScaleXEnabled()) {
                    this.f5376a = ChartTouchListener.ChartGesture.X_ZOOM;
                    float xDist = getXDist(motionEvent) / this.mSavedXDist;
                    if (xDist < 1.0f ? viewPortHandler.canZoomOutMoreX() : viewPortHandler.canZoomInMoreX()) {
                        this.mMatrix.set(this.mSavedMatrix);
                        this.mMatrix.postScale(xDist, 1.0f, trans.x, trans.y);
                        if (onChartGestureListener != null) {
                            onChartGestureListener.onChartScale(motionEvent, xDist, 1.0f);
                        }
                    }
                } else if (this.f5377b == 3 && ((BarLineChartBase) this.f5380e).isScaleYEnabled()) {
                    this.f5376a = ChartTouchListener.ChartGesture.Y_ZOOM;
                    float yDist = getYDist(motionEvent) / this.mSavedYDist;
                    if (yDist < 1.0f ? viewPortHandler.canZoomOutMoreY() : viewPortHandler.canZoomInMoreY()) {
                        this.mMatrix.set(this.mSavedMatrix);
                        this.mMatrix.postScale(1.0f, yDist, trans.x, trans.y);
                        if (onChartGestureListener != null) {
                            onChartGestureListener.onChartScale(motionEvent, 1.0f, yDist);
                        }
                    }
                }
                MPPointF.recycleInstance(trans);
            }
        }
    }

    private void saveTouchStart(MotionEvent motionEvent) {
        this.mSavedMatrix.set(this.mMatrix);
        this.mTouchStartPoint.x = motionEvent.getX();
        this.mTouchStartPoint.y = motionEvent.getY();
        this.mClosestDataSetToTouch = ((BarLineChartBase) this.f5380e).getDataSetByTouchPoint(motionEvent.getX(), motionEvent.getY());
    }

    private static float spacing(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    public void computeScroll() {
        MPPointF mPPointF = this.mDecelerationVelocity;
        if (mPPointF.x == 0.0f && mPPointF.y == 0.0f) {
            return;
        }
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        this.mDecelerationVelocity.x *= ((BarLineChartBase) this.f5380e).getDragDecelerationFrictionCoef();
        this.mDecelerationVelocity.y *= ((BarLineChartBase) this.f5380e).getDragDecelerationFrictionCoef();
        float f2 = ((float) (currentAnimationTimeMillis - this.mDecelerationLastTime)) / 1000.0f;
        MPPointF mPPointF2 = this.mDecelerationVelocity;
        float f3 = mPPointF2.x * f2;
        float f4 = mPPointF2.y * f2;
        MPPointF mPPointF3 = this.mDecelerationCurrentPoint;
        float f5 = mPPointF3.x + f3;
        mPPointF3.x = f5;
        float f6 = mPPointF3.y + f4;
        mPPointF3.y = f6;
        MotionEvent obtain = MotionEvent.obtain(currentAnimationTimeMillis, currentAnimationTimeMillis, 2, f5, f6, 0);
        performDrag(obtain, ((BarLineChartBase) this.f5380e).isDragXEnabled() ? this.mDecelerationCurrentPoint.x - this.mTouchStartPoint.x : 0.0f, ((BarLineChartBase) this.f5380e).isDragYEnabled() ? this.mDecelerationCurrentPoint.y - this.mTouchStartPoint.y : 0.0f);
        obtain.recycle();
        this.mMatrix = ((BarLineChartBase) this.f5380e).getViewPortHandler().refresh(this.mMatrix, this.f5380e, false);
        this.mDecelerationLastTime = currentAnimationTimeMillis;
        if (Math.abs(this.mDecelerationVelocity.x) >= 0.01d || Math.abs(this.mDecelerationVelocity.y) >= 0.01d) {
            Utils.postInvalidateOnAnimation(this.f5380e);
            return;
        }
        ((BarLineChartBase) this.f5380e).calculateOffsets();
        ((BarLineChartBase) this.f5380e).postInvalidate();
        stopDeceleration();
    }

    public Matrix getMatrix() {
        return this.mMatrix;
    }

    public MPPointF getTrans(float f2, float f3) {
        ViewPortHandler viewPortHandler = ((BarLineChartBase) this.f5380e).getViewPortHandler();
        return MPPointF.getInstance(f2 - viewPortHandler.offsetLeft(), inverted() ? -(f3 - viewPortHandler.offsetTop()) : -((((BarLineChartBase) this.f5380e).getMeasuredHeight() - f3) - viewPortHandler.offsetBottom()));
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        this.f5376a = ChartTouchListener.ChartGesture.DOUBLE_TAP;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.f5380e).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartDoubleTapped(motionEvent);
        }
        if (((BarLineChartBase) this.f5380e).isDoubleTapToZoomEnabled() && ((BarLineScatterCandleBubbleData) ((BarLineChartBase) this.f5380e).getData()).getEntryCount() > 0) {
            MPPointF trans = getTrans(motionEvent.getX(), motionEvent.getY());
            Chart chart = this.f5380e;
            ((BarLineChartBase) chart).zoom(((BarLineChartBase) chart).isScaleXEnabled() ? 1.4f : 1.0f, ((BarLineChartBase) this.f5380e).isScaleYEnabled() ? 1.4f : 1.0f, trans.x, trans.y);
            if (((BarLineChartBase) this.f5380e).isLogEnabled()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Double-Tap, Zooming In, x: ");
                sb.append(trans.x);
                sb.append(", y: ");
                sb.append(trans.y);
            }
            MPPointF.recycleInstance(trans);
        }
        return super.onDoubleTap(motionEvent);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        this.f5376a = ChartTouchListener.ChartGesture.FLING;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.f5380e).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartFling(motionEvent, motionEvent2, f2, f3);
        }
        return super.onFling(motionEvent, motionEvent2, f2, f3);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        this.f5376a = ChartTouchListener.ChartGesture.LONG_PRESS;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.f5380e).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartLongPressed(motionEvent);
        }
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        this.f5376a = ChartTouchListener.ChartGesture.SINGLE_TAP;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.f5380e).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartSingleTapped(motionEvent);
        }
        if (((BarLineChartBase) this.f5380e).isHighlightPerTapEnabled()) {
            b(((BarLineChartBase) this.f5380e).getHighlightByTouchPoint(motionEvent.getX(), motionEvent.getY()), motionEvent);
            return super.onSingleTapUp(motionEvent);
        }
        return false;
    }

    @Override // android.view.View.OnTouchListener
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View view, MotionEvent motionEvent) {
        VelocityTracker velocityTracker;
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int i2 = 3;
        if (motionEvent.getActionMasked() == 3 && (velocityTracker = this.mVelocityTracker) != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
        if (this.f5377b == 0) {
            this.f5379d.onTouchEvent(motionEvent);
        }
        if (((BarLineChartBase) this.f5380e).isDragEnabled() || ((BarLineChartBase) this.f5380e).isScaleXEnabled() || ((BarLineChartBase) this.f5380e).isScaleYEnabled()) {
            int action = motionEvent.getAction() & 255;
            if (action != 0) {
                boolean z = false;
                if (action == 1) {
                    VelocityTracker velocityTracker2 = this.mVelocityTracker;
                    int pointerId = motionEvent.getPointerId(0);
                    velocityTracker2.computeCurrentVelocity(1000, Utils.getMaximumFlingVelocity());
                    float yVelocity = velocityTracker2.getYVelocity(pointerId);
                    float xVelocity = velocityTracker2.getXVelocity(pointerId);
                    if ((Math.abs(xVelocity) > Utils.getMinimumFlingVelocity() || Math.abs(yVelocity) > Utils.getMinimumFlingVelocity()) && this.f5377b == 1 && ((BarLineChartBase) this.f5380e).isDragDecelerationEnabled()) {
                        stopDeceleration();
                        this.mDecelerationLastTime = AnimationUtils.currentAnimationTimeMillis();
                        this.mDecelerationCurrentPoint.x = motionEvent.getX();
                        this.mDecelerationCurrentPoint.y = motionEvent.getY();
                        MPPointF mPPointF = this.mDecelerationVelocity;
                        mPPointF.x = xVelocity;
                        mPPointF.y = yVelocity;
                        Utils.postInvalidateOnAnimation(this.f5380e);
                    }
                    int i3 = this.f5377b;
                    if (i3 == 2 || i3 == 3 || i3 == 4 || i3 == 5) {
                        ((BarLineChartBase) this.f5380e).calculateOffsets();
                        ((BarLineChartBase) this.f5380e).postInvalidate();
                    }
                    this.f5377b = 0;
                    ((BarLineChartBase) this.f5380e).enableScroll();
                    VelocityTracker velocityTracker3 = this.mVelocityTracker;
                    if (velocityTracker3 != null) {
                        velocityTracker3.recycle();
                        this.mVelocityTracker = null;
                    }
                } else if (action == 2) {
                    int i4 = this.f5377b;
                    if (i4 == 1) {
                        ((BarLineChartBase) this.f5380e).disableScroll();
                        performDrag(motionEvent, ((BarLineChartBase) this.f5380e).isDragXEnabled() ? motionEvent.getX() - this.mTouchStartPoint.x : 0.0f, ((BarLineChartBase) this.f5380e).isDragYEnabled() ? motionEvent.getY() - this.mTouchStartPoint.y : 0.0f);
                    } else if (i4 == 2 || i4 == 3 || i4 == 4) {
                        ((BarLineChartBase) this.f5380e).disableScroll();
                        if (((BarLineChartBase) this.f5380e).isScaleXEnabled() || ((BarLineChartBase) this.f5380e).isScaleYEnabled()) {
                            performZoom(motionEvent);
                        }
                    } else if (i4 == 0 && Math.abs(ChartTouchListener.a(motionEvent.getX(), this.mTouchStartPoint.x, motionEvent.getY(), this.mTouchStartPoint.y)) > this.mDragTriggerDist && ((BarLineChartBase) this.f5380e).isDragEnabled()) {
                        if (!((BarLineChartBase) this.f5380e).isFullyZoomedOut() || !((BarLineChartBase) this.f5380e).hasNoDragOffset()) {
                            z = true;
                        }
                        if (z) {
                            float abs = Math.abs(motionEvent.getX() - this.mTouchStartPoint.x);
                            float abs2 = Math.abs(motionEvent.getY() - this.mTouchStartPoint.y);
                            if ((((BarLineChartBase) this.f5380e).isDragXEnabled() || abs2 >= abs) && (((BarLineChartBase) this.f5380e).isDragYEnabled() || abs2 <= abs)) {
                                this.f5376a = ChartTouchListener.ChartGesture.DRAG;
                                this.f5377b = 1;
                            }
                        } else if (((BarLineChartBase) this.f5380e).isHighlightPerDragEnabled()) {
                            this.f5376a = ChartTouchListener.ChartGesture.DRAG;
                            if (((BarLineChartBase) this.f5380e).isHighlightPerDragEnabled()) {
                                performHighlightDrag(motionEvent);
                            }
                        }
                    }
                } else if (action == 3) {
                    this.f5377b = 0;
                } else if (action != 5) {
                    if (action == 6) {
                        Utils.velocityTrackerPointerUpCleanUpIfNecessary(motionEvent, this.mVelocityTracker);
                        this.f5377b = 5;
                    }
                } else if (motionEvent.getPointerCount() >= 2) {
                    ((BarLineChartBase) this.f5380e).disableScroll();
                    saveTouchStart(motionEvent);
                    this.mSavedXDist = getXDist(motionEvent);
                    this.mSavedYDist = getYDist(motionEvent);
                    float spacing = spacing(motionEvent);
                    this.mSavedDist = spacing;
                    if (spacing > 10.0f) {
                        if (((BarLineChartBase) this.f5380e).isPinchZoomEnabled()) {
                            this.f5377b = 4;
                        } else {
                            if (((BarLineChartBase) this.f5380e).isScaleXEnabled() == ((BarLineChartBase) this.f5380e).isScaleYEnabled() ? this.mSavedXDist > this.mSavedYDist : ((BarLineChartBase) this.f5380e).isScaleXEnabled()) {
                                i2 = 2;
                            }
                            this.f5377b = i2;
                        }
                    }
                    midPoint(this.mTouchPointCenter, motionEvent);
                }
                endAction(motionEvent);
            } else {
                startAction(motionEvent);
                stopDeceleration();
                saveTouchStart(motionEvent);
            }
            this.mMatrix = ((BarLineChartBase) this.f5380e).getViewPortHandler().refresh(this.mMatrix, this.f5380e, true);
            return true;
        }
        return true;
    }

    public void setDragTriggerDist(float f2) {
        this.mDragTriggerDist = Utils.convertDpToPixel(f2);
    }

    public void stopDeceleration() {
        MPPointF mPPointF = this.mDecelerationVelocity;
        mPPointF.x = 0.0f;
        mPPointF.y = 0.0f;
    }
}
