package com.github.mikephil.charting.listener;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.highlight.Highlight;
/* loaded from: classes.dex */
public abstract class ChartTouchListener<T extends Chart<?>> extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener {

    /* renamed from: a  reason: collision with root package name */
    protected ChartGesture f5376a = ChartGesture.NONE;

    /* renamed from: b  reason: collision with root package name */
    protected int f5377b = 0;

    /* renamed from: c  reason: collision with root package name */
    protected Highlight f5378c;

    /* renamed from: d  reason: collision with root package name */
    protected GestureDetector f5379d;

    /* renamed from: e  reason: collision with root package name */
    protected Chart f5380e;

    /* loaded from: classes.dex */
    public enum ChartGesture {
        NONE,
        DRAG,
        X_ZOOM,
        Y_ZOOM,
        PINCH_ZOOM,
        ROTATE,
        SINGLE_TAP,
        DOUBLE_TAP,
        LONG_PRESS,
        FLING
    }

    public ChartTouchListener(T t2) {
        this.f5380e = t2;
        this.f5379d = new GestureDetector(t2.getContext(), this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static float a(float f2, float f3, float f4, float f5) {
        float f6 = f2 - f3;
        float f7 = f4 - f5;
        return (float) Math.sqrt((f6 * f6) + (f7 * f7));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(Highlight highlight, MotionEvent motionEvent) {
        if (highlight == null || highlight.equalTo(this.f5378c)) {
            this.f5380e.highlightValue((Highlight) null, true);
            this.f5378c = null;
            return;
        }
        this.f5380e.highlightValue(highlight, true);
        this.f5378c = highlight;
    }

    public void endAction(MotionEvent motionEvent) {
        OnChartGestureListener onChartGestureListener = this.f5380e.getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartGestureEnd(motionEvent, this.f5376a);
        }
    }

    public ChartGesture getLastGesture() {
        return this.f5376a;
    }

    public int getTouchMode() {
        return this.f5377b;
    }

    public void setLastHighlighted(Highlight highlight) {
        this.f5378c = highlight;
    }

    public void startAction(MotionEvent motionEvent) {
        OnChartGestureListener onChartGestureListener = this.f5380e.getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartGestureStart(motionEvent, this.f5376a);
        }
    }
}
