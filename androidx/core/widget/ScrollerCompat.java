package androidx.core.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
@Deprecated
/* loaded from: classes.dex */
public final class ScrollerCompat {

    /* renamed from: a  reason: collision with root package name */
    OverScroller f2742a;

    ScrollerCompat(Context context, Interpolator interpolator) {
        this.f2742a = interpolator != null ? new OverScroller(context, interpolator) : new OverScroller(context);
    }

    @Deprecated
    public static ScrollerCompat create(Context context) {
        return create(context, null);
    }

    @Deprecated
    public static ScrollerCompat create(Context context, Interpolator interpolator) {
        return new ScrollerCompat(context, interpolator);
    }

    @Deprecated
    public void abortAnimation() {
        this.f2742a.abortAnimation();
    }

    @Deprecated
    public boolean computeScrollOffset() {
        return this.f2742a.computeScrollOffset();
    }

    @Deprecated
    public void fling(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.f2742a.fling(i2, i3, i4, i5, i6, i7, i8, i9);
    }

    @Deprecated
    public void fling(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
        this.f2742a.fling(i2, i3, i4, i5, i6, i7, i8, i9, i10, i11);
    }

    @Deprecated
    public float getCurrVelocity() {
        return this.f2742a.getCurrVelocity();
    }

    @Deprecated
    public int getCurrX() {
        return this.f2742a.getCurrX();
    }

    @Deprecated
    public int getCurrY() {
        return this.f2742a.getCurrY();
    }

    @Deprecated
    public int getFinalX() {
        return this.f2742a.getFinalX();
    }

    @Deprecated
    public int getFinalY() {
        return this.f2742a.getFinalY();
    }

    @Deprecated
    public boolean isFinished() {
        return this.f2742a.isFinished();
    }

    @Deprecated
    public boolean isOverScrolled() {
        return this.f2742a.isOverScrolled();
    }

    @Deprecated
    public void notifyHorizontalEdgeReached(int i2, int i3, int i4) {
        this.f2742a.notifyHorizontalEdgeReached(i2, i3, i4);
    }

    @Deprecated
    public void notifyVerticalEdgeReached(int i2, int i3, int i4) {
        this.f2742a.notifyVerticalEdgeReached(i2, i3, i4);
    }

    @Deprecated
    public boolean springBack(int i2, int i3, int i4, int i5, int i6, int i7) {
        return this.f2742a.springBack(i2, i3, i4, i5, i6, i7);
    }

    @Deprecated
    public void startScroll(int i2, int i3, int i4, int i5) {
        this.f2742a.startScroll(i2, i3, i4, i5);
    }

    @Deprecated
    public void startScroll(int i2, int i3, int i4, int i5, int i6) {
        this.f2742a.startScroll(i2, i3, i4, i5, i6);
    }
}
