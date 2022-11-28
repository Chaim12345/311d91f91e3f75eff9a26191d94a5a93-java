package com.github.mikephil.charting.listener;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieRadarChartBase;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class PieRadarChartTouchListener extends ChartTouchListener<PieRadarChartBase<?>> {
    private ArrayList<AngularVelocitySample> _velocitySamples;
    private float mDecelerationAngularVelocity;
    private long mDecelerationLastTime;
    private float mStartAngle;
    private MPPointF mTouchStartPoint;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class AngularVelocitySample {
        public float angle;
        public long time;

        public AngularVelocitySample(PieRadarChartTouchListener pieRadarChartTouchListener, long j2, float f2) {
            this.time = j2;
            this.angle = f2;
        }
    }

    public PieRadarChartTouchListener(PieRadarChartBase<?> pieRadarChartBase) {
        super(pieRadarChartBase);
        this.mTouchStartPoint = MPPointF.getInstance(0.0f, 0.0f);
        this.mStartAngle = 0.0f;
        this._velocitySamples = new ArrayList<>();
        this.mDecelerationLastTime = 0L;
        this.mDecelerationAngularVelocity = 0.0f;
    }

    private float calculateVelocity() {
        float f2;
        float f3;
        if (this._velocitySamples.isEmpty()) {
            return 0.0f;
        }
        AngularVelocitySample angularVelocitySample = this._velocitySamples.get(0);
        ArrayList<AngularVelocitySample> arrayList = this._velocitySamples;
        AngularVelocitySample angularVelocitySample2 = arrayList.get(arrayList.size() - 1);
        AngularVelocitySample angularVelocitySample3 = angularVelocitySample;
        for (int size = this._velocitySamples.size() - 1; size >= 0; size--) {
            angularVelocitySample3 = this._velocitySamples.get(size);
            if (angularVelocitySample3.angle != angularVelocitySample2.angle) {
                break;
            }
        }
        float f4 = ((float) (angularVelocitySample2.time - angularVelocitySample.time)) / 1000.0f;
        if (f4 == 0.0f) {
            f4 = 0.1f;
        }
        boolean z = angularVelocitySample2.angle >= angularVelocitySample3.angle;
        if (Math.abs(f2 - f3) > 270.0d) {
            z = !z;
        }
        float f5 = angularVelocitySample2.angle;
        float f6 = angularVelocitySample.angle;
        if (f5 - f6 > 180.0d) {
            angularVelocitySample.angle = (float) (f6 + 360.0d);
        } else if (f6 - f5 > 180.0d) {
            angularVelocitySample2.angle = (float) (f5 + 360.0d);
        }
        float abs = Math.abs((angularVelocitySample2.angle - angularVelocitySample.angle) / f4);
        return !z ? -abs : abs;
    }

    private void resetVelocity() {
        this._velocitySamples.clear();
    }

    private void sampleVelocity(float f2, float f3) {
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        this._velocitySamples.add(new AngularVelocitySample(this, currentAnimationTimeMillis, ((PieRadarChartBase) this.f5380e).getAngleForPoint(f2, f3)));
        for (int size = this._velocitySamples.size(); size - 2 > 0 && currentAnimationTimeMillis - this._velocitySamples.get(0).time > 1000; size--) {
            this._velocitySamples.remove(0);
        }
    }

    public void computeScroll() {
        if (this.mDecelerationAngularVelocity == 0.0f) {
            return;
        }
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        this.mDecelerationAngularVelocity *= ((PieRadarChartBase) this.f5380e).getDragDecelerationFrictionCoef();
        Chart chart = this.f5380e;
        ((PieRadarChartBase) chart).setRotationAngle(((PieRadarChartBase) chart).getRotationAngle() + (this.mDecelerationAngularVelocity * (((float) (currentAnimationTimeMillis - this.mDecelerationLastTime)) / 1000.0f)));
        this.mDecelerationLastTime = currentAnimationTimeMillis;
        if (Math.abs(this.mDecelerationAngularVelocity) >= 0.001d) {
            Utils.postInvalidateOnAnimation(this.f5380e);
        } else {
            stopDeceleration();
        }
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        this.f5376a = ChartTouchListener.ChartGesture.LONG_PRESS;
        OnChartGestureListener onChartGestureListener = ((PieRadarChartBase) this.f5380e).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartLongPressed(motionEvent);
        }
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return true;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        this.f5376a = ChartTouchListener.ChartGesture.SINGLE_TAP;
        OnChartGestureListener onChartGestureListener = ((PieRadarChartBase) this.f5380e).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartSingleTapped(motionEvent);
        }
        if (((PieRadarChartBase) this.f5380e).isHighlightPerTapEnabled()) {
            b(((PieRadarChartBase) this.f5380e).getHighlightByTouchPoint(motionEvent.getX(), motionEvent.getY()), motionEvent);
            return true;
        }
        return false;
    }

    @Override // android.view.View.OnTouchListener
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!this.f5379d.onTouchEvent(motionEvent) && ((PieRadarChartBase) this.f5380e).isRotationEnabled()) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int action = motionEvent.getAction();
            if (action != 0) {
                if (action == 1) {
                    if (((PieRadarChartBase) this.f5380e).isDragDecelerationEnabled()) {
                        stopDeceleration();
                        sampleVelocity(x, y);
                        float calculateVelocity = calculateVelocity();
                        this.mDecelerationAngularVelocity = calculateVelocity;
                        if (calculateVelocity != 0.0f) {
                            this.mDecelerationLastTime = AnimationUtils.currentAnimationTimeMillis();
                            Utils.postInvalidateOnAnimation(this.f5380e);
                        }
                    }
                    ((PieRadarChartBase) this.f5380e).enableScroll();
                    this.f5377b = 0;
                } else if (action == 2) {
                    if (((PieRadarChartBase) this.f5380e).isDragDecelerationEnabled()) {
                        sampleVelocity(x, y);
                    }
                    if (this.f5377b == 0) {
                        MPPointF mPPointF = this.mTouchStartPoint;
                        if (ChartTouchListener.a(x, mPPointF.x, y, mPPointF.y) > Utils.convertDpToPixel(8.0f)) {
                            this.f5376a = ChartTouchListener.ChartGesture.ROTATE;
                            this.f5377b = 6;
                            ((PieRadarChartBase) this.f5380e).disableScroll();
                        }
                    }
                    if (this.f5377b == 6) {
                        updateGestureRotation(x, y);
                        ((PieRadarChartBase) this.f5380e).invalidate();
                    }
                }
                endAction(motionEvent);
            } else {
                startAction(motionEvent);
                stopDeceleration();
                resetVelocity();
                if (((PieRadarChartBase) this.f5380e).isDragDecelerationEnabled()) {
                    sampleVelocity(x, y);
                }
                setGestureStartAngle(x, y);
                MPPointF mPPointF2 = this.mTouchStartPoint;
                mPPointF2.x = x;
                mPPointF2.y = y;
            }
        }
        return true;
    }

    public void setGestureStartAngle(float f2, float f3) {
        this.mStartAngle = ((PieRadarChartBase) this.f5380e).getAngleForPoint(f2, f3) - ((PieRadarChartBase) this.f5380e).getRawRotationAngle();
    }

    public void stopDeceleration() {
        this.mDecelerationAngularVelocity = 0.0f;
    }

    public void updateGestureRotation(float f2, float f3) {
        Chart chart = this.f5380e;
        ((PieRadarChartBase) chart).setRotationAngle(((PieRadarChartBase) chart).getAngleForPoint(f2, f3) - this.mStartAngle);
    }
}
