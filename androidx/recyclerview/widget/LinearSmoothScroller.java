package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes.dex */
public class LinearSmoothScroller extends RecyclerView.SmoothScroller {
    private static final boolean DEBUG = false;
    private static final float MILLISECONDS_PER_INCH = 25.0f;
    public static final int SNAP_TO_ANY = 0;
    public static final int SNAP_TO_END = 1;
    public static final int SNAP_TO_START = -1;
    private static final float TARGET_SEEK_EXTRA_SCROLL_RATIO = 1.2f;
    private static final int TARGET_SEEK_SCROLL_DISTANCE_PX = 10000;

    /* renamed from: c  reason: collision with root package name */
    protected PointF f3670c;
    private final DisplayMetrics mDisplayMetrics;
    private float mMillisPerPixel;

    /* renamed from: a  reason: collision with root package name */
    protected final LinearInterpolator f3668a = new LinearInterpolator();

    /* renamed from: b  reason: collision with root package name */
    protected final DecelerateInterpolator f3669b = new DecelerateInterpolator();
    private boolean mHasCalculatedMillisPerPixel = false;

    /* renamed from: d  reason: collision with root package name */
    protected int f3671d = 0;

    /* renamed from: e  reason: collision with root package name */
    protected int f3672e = 0;

    public LinearSmoothScroller(Context context) {
        this.mDisplayMetrics = context.getResources().getDisplayMetrics();
    }

    private int clampApplyScroll(int i2, int i3) {
        int i4 = i2 - i3;
        if (i2 * i4 <= 0) {
            return 0;
        }
        return i4;
    }

    private float getSpeedPerPixel() {
        if (!this.mHasCalculatedMillisPerPixel) {
            this.mMillisPerPixel = j(this.mDisplayMetrics);
            this.mHasCalculatedMillisPerPixel = true;
        }
        return this.mMillisPerPixel;
    }

    public int calculateDtToFit(int i2, int i3, int i4, int i5, int i6) {
        if (i6 != -1) {
            if (i6 != 0) {
                if (i6 == 1) {
                    return i5 - i3;
                }
                throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
            }
            int i7 = i4 - i2;
            if (i7 > 0) {
                return i7;
            }
            int i8 = i5 - i3;
            if (i8 < 0) {
                return i8;
            }
            return 0;
        }
        return i4 - i2;
    }

    public int calculateDxToMakeVisible(View view, int i2) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null || !layoutManager.canScrollHorizontally()) {
            return 0;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        return calculateDtToFit(layoutManager.getDecoratedLeft(view) - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, layoutManager.getDecoratedRight(view) + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, layoutManager.getPaddingLeft(), layoutManager.getWidth() - layoutManager.getPaddingRight(), i2);
    }

    public int calculateDyToMakeVisible(View view, int i2) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null || !layoutManager.canScrollVertically()) {
            return 0;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        return calculateDtToFit(layoutManager.getDecoratedTop(view) - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, layoutManager.getDecoratedBottom(view) + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin, layoutManager.getPaddingTop(), layoutManager.getHeight() - layoutManager.getPaddingBottom(), i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
    protected void d(int i2, int i3, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
        if (getChildCount() == 0) {
            i();
            return;
        }
        this.f3671d = clampApplyScroll(this.f3671d, i2);
        int clampApplyScroll = clampApplyScroll(this.f3672e, i3);
        this.f3672e = clampApplyScroll;
        if (this.f3671d == 0 && clampApplyScroll == 0) {
            o(action);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
    protected void e() {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
    protected void f() {
        this.f3672e = 0;
        this.f3671d = 0;
        this.f3670c = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
    protected void g(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
        int calculateDxToMakeVisible = calculateDxToMakeVisible(view, m());
        int calculateDyToMakeVisible = calculateDyToMakeVisible(view, n());
        int k2 = k((int) Math.sqrt((calculateDxToMakeVisible * calculateDxToMakeVisible) + (calculateDyToMakeVisible * calculateDyToMakeVisible)));
        if (k2 > 0) {
            action.update(-calculateDxToMakeVisible, -calculateDyToMakeVisible, k2, this.f3669b);
        }
    }

    protected float j(DisplayMetrics displayMetrics) {
        return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int k(int i2) {
        return (int) Math.ceil(l(i2) / 0.3356d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int l(int i2) {
        return (int) Math.ceil(Math.abs(i2) * getSpeedPerPixel());
    }

    protected int m() {
        PointF pointF = this.f3670c;
        if (pointF != null) {
            float f2 = pointF.x;
            if (f2 != 0.0f) {
                return f2 > 0.0f ? 1 : -1;
            }
        }
        return 0;
    }

    protected int n() {
        PointF pointF = this.f3670c;
        if (pointF != null) {
            float f2 = pointF.y;
            if (f2 != 0.0f) {
                return f2 > 0.0f ? 1 : -1;
            }
        }
        return 0;
    }

    protected void o(RecyclerView.SmoothScroller.Action action) {
        PointF computeScrollVectorForPosition = computeScrollVectorForPosition(getTargetPosition());
        if (computeScrollVectorForPosition == null || (computeScrollVectorForPosition.x == 0.0f && computeScrollVectorForPosition.y == 0.0f)) {
            action.jumpTo(getTargetPosition());
            i();
            return;
        }
        a(computeScrollVectorForPosition);
        this.f3670c = computeScrollVectorForPosition;
        this.f3671d = (int) (computeScrollVectorForPosition.x * 10000.0f);
        this.f3672e = (int) (computeScrollVectorForPosition.y * 10000.0f);
        action.update((int) (this.f3671d * TARGET_SEEK_EXTRA_SCROLL_RATIO), (int) (this.f3672e * TARGET_SEEK_EXTRA_SCROLL_RATIO), (int) (l(10000) * TARGET_SEEK_EXTRA_SCROLL_RATIO), this.f3668a);
    }
}
