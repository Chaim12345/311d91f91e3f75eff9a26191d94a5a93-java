package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
/* JADX INFO: Access modifiers changed from: package-private */
@VisibleForTesting
/* loaded from: classes.dex */
public class FastScroller extends RecyclerView.ItemDecoration implements RecyclerView.OnItemTouchListener {
    private static final int ANIMATION_STATE_FADING_IN = 1;
    private static final int ANIMATION_STATE_FADING_OUT = 3;
    private static final int ANIMATION_STATE_IN = 2;
    private static final int ANIMATION_STATE_OUT = 0;
    private static final int DRAG_NONE = 0;
    private static final int DRAG_X = 1;
    private static final int DRAG_Y = 2;
    private static final int HIDE_DELAY_AFTER_DRAGGING_MS = 1200;
    private static final int HIDE_DELAY_AFTER_VISIBLE_MS = 1500;
    private static final int HIDE_DURATION_MS = 500;
    private static final int SCROLLBAR_FULL_OPAQUE = 255;
    private static final int SHOW_DURATION_MS = 500;
    private static final int STATE_DRAGGING = 2;
    private static final int STATE_HIDDEN = 0;
    private static final int STATE_VISIBLE = 1;

    /* renamed from: a  reason: collision with root package name */
    final StateListDrawable f3561a;

    /* renamed from: b  reason: collision with root package name */
    final Drawable f3562b;
    @VisibleForTesting

    /* renamed from: c  reason: collision with root package name */
    int f3563c;
    @VisibleForTesting

    /* renamed from: d  reason: collision with root package name */
    int f3564d;
    @VisibleForTesting

    /* renamed from: e  reason: collision with root package name */
    float f3565e;
    @VisibleForTesting

    /* renamed from: f  reason: collision with root package name */
    int f3566f;
    @VisibleForTesting

    /* renamed from: g  reason: collision with root package name */
    int f3567g;
    @VisibleForTesting

    /* renamed from: h  reason: collision with root package name */
    float f3568h;

    /* renamed from: i  reason: collision with root package name */
    final ValueAnimator f3569i;

    /* renamed from: j  reason: collision with root package name */
    int f3570j;
    private final Runnable mHideRunnable;
    private final StateListDrawable mHorizontalThumbDrawable;
    private final int mHorizontalThumbHeight;
    private final Drawable mHorizontalTrackDrawable;
    private final int mHorizontalTrackHeight;
    private final int mMargin;
    private final RecyclerView.OnScrollListener mOnScrollListener;
    private RecyclerView mRecyclerView;
    private final int mScrollbarMinimumRange;
    private final int mVerticalThumbWidth;
    private final int mVerticalTrackWidth;
    private static final int[] PRESSED_STATE_SET = {16842919};
    private static final int[] EMPTY_STATE_SET = new int[0];
    private int mRecyclerViewWidth = 0;
    private int mRecyclerViewHeight = 0;
    private boolean mNeedVerticalScrollbar = false;
    private boolean mNeedHorizontalScrollbar = false;
    private int mState = 0;
    private int mDragState = 0;
    private final int[] mVerticalRange = new int[2];
    private final int[] mHorizontalRange = new int[2];

    /* loaded from: classes.dex */
    private class AnimatorListener extends AnimatorListenerAdapter {
        private boolean mCanceled = false;

        AnimatorListener() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.mCanceled = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.mCanceled) {
                this.mCanceled = false;
            } else if (((Float) FastScroller.this.f3569i.getAnimatedValue()).floatValue() == 0.0f) {
                FastScroller fastScroller = FastScroller.this;
                fastScroller.f3570j = 0;
                fastScroller.e(0);
            } else {
                FastScroller fastScroller2 = FastScroller.this;
                fastScroller2.f3570j = 2;
                fastScroller2.d();
            }
        }
    }

    /* loaded from: classes.dex */
    private class AnimatorUpdater implements ValueAnimator.AnimatorUpdateListener {
        AnimatorUpdater() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int floatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
            FastScroller.this.f3561a.setAlpha(floatValue);
            FastScroller.this.f3562b.setAlpha(floatValue);
            FastScroller.this.d();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FastScroller(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i2, int i3, int i4) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.f3569i = ofFloat;
        this.f3570j = 0;
        this.mHideRunnable = new Runnable() { // from class: androidx.recyclerview.widget.FastScroller.1
            @Override // java.lang.Runnable
            public void run() {
                FastScroller.this.a(500);
            }
        };
        this.mOnScrollListener = new RecyclerView.OnScrollListener() { // from class: androidx.recyclerview.widget.FastScroller.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView2, int i5, int i6) {
                FastScroller.this.f(recyclerView2.computeHorizontalScrollOffset(), recyclerView2.computeVerticalScrollOffset());
            }
        };
        this.f3561a = stateListDrawable;
        this.f3562b = drawable;
        this.mHorizontalThumbDrawable = stateListDrawable2;
        this.mHorizontalTrackDrawable = drawable2;
        this.mVerticalThumbWidth = Math.max(i2, stateListDrawable.getIntrinsicWidth());
        this.mVerticalTrackWidth = Math.max(i2, drawable.getIntrinsicWidth());
        this.mHorizontalThumbHeight = Math.max(i2, stateListDrawable2.getIntrinsicWidth());
        this.mHorizontalTrackHeight = Math.max(i2, drawable2.getIntrinsicWidth());
        this.mScrollbarMinimumRange = i3;
        this.mMargin = i4;
        stateListDrawable.setAlpha(255);
        drawable.setAlpha(255);
        ofFloat.addListener(new AnimatorListener());
        ofFloat.addUpdateListener(new AnimatorUpdater());
        attachToRecyclerView(recyclerView);
    }

    private void cancelHide() {
        this.mRecyclerView.removeCallbacks(this.mHideRunnable);
    }

    private void destroyCallbacks() {
        this.mRecyclerView.removeItemDecoration(this);
        this.mRecyclerView.removeOnItemTouchListener(this);
        this.mRecyclerView.removeOnScrollListener(this.mOnScrollListener);
        cancelHide();
    }

    private void drawHorizontalScrollbar(Canvas canvas) {
        int i2 = this.mRecyclerViewHeight;
        int i3 = this.mHorizontalThumbHeight;
        int i4 = i2 - i3;
        int i5 = this.f3567g;
        int i6 = this.f3566f;
        int i7 = i5 - (i6 / 2);
        this.mHorizontalThumbDrawable.setBounds(0, 0, i6, i3);
        this.mHorizontalTrackDrawable.setBounds(0, 0, this.mRecyclerViewWidth, this.mHorizontalTrackHeight);
        canvas.translate(0.0f, i4);
        this.mHorizontalTrackDrawable.draw(canvas);
        canvas.translate(i7, 0.0f);
        this.mHorizontalThumbDrawable.draw(canvas);
        canvas.translate(-i7, -i4);
    }

    private void drawVerticalScrollbar(Canvas canvas) {
        int i2 = this.mRecyclerViewWidth;
        int i3 = this.mVerticalThumbWidth;
        int i4 = i2 - i3;
        int i5 = this.f3564d;
        int i6 = this.f3563c;
        int i7 = i5 - (i6 / 2);
        this.f3561a.setBounds(0, 0, i3, i6);
        this.f3562b.setBounds(0, 0, this.mVerticalTrackWidth, this.mRecyclerViewHeight);
        if (isLayoutRTL()) {
            this.f3562b.draw(canvas);
            canvas.translate(this.mVerticalThumbWidth, i7);
            canvas.scale(-1.0f, 1.0f);
            this.f3561a.draw(canvas);
            canvas.scale(-1.0f, 1.0f);
            i4 = this.mVerticalThumbWidth;
        } else {
            canvas.translate(i4, 0.0f);
            this.f3562b.draw(canvas);
            canvas.translate(0.0f, i7);
            this.f3561a.draw(canvas);
        }
        canvas.translate(-i4, -i7);
    }

    private int[] getHorizontalRange() {
        int[] iArr = this.mHorizontalRange;
        int i2 = this.mMargin;
        iArr[0] = i2;
        iArr[1] = this.mRecyclerViewWidth - i2;
        return iArr;
    }

    private int[] getVerticalRange() {
        int[] iArr = this.mVerticalRange;
        int i2 = this.mMargin;
        iArr[0] = i2;
        iArr[1] = this.mRecyclerViewHeight - i2;
        return iArr;
    }

    private void horizontalScrollTo(float f2) {
        int[] horizontalRange = getHorizontalRange();
        float max = Math.max(horizontalRange[0], Math.min(horizontalRange[1], f2));
        if (Math.abs(this.f3567g - max) < 2.0f) {
            return;
        }
        int scrollTo = scrollTo(this.f3568h, max, horizontalRange, this.mRecyclerView.computeHorizontalScrollRange(), this.mRecyclerView.computeHorizontalScrollOffset(), this.mRecyclerViewWidth);
        if (scrollTo != 0) {
            this.mRecyclerView.scrollBy(scrollTo, 0);
        }
        this.f3568h = max;
    }

    private boolean isLayoutRTL() {
        return ViewCompat.getLayoutDirection(this.mRecyclerView) == 1;
    }

    private void resetHideDelay(int i2) {
        cancelHide();
        this.mRecyclerView.postDelayed(this.mHideRunnable, i2);
    }

    private int scrollTo(float f2, float f3, int[] iArr, int i2, int i3, int i4) {
        int i5 = iArr[1] - iArr[0];
        if (i5 == 0) {
            return 0;
        }
        int i6 = i2 - i4;
        int i7 = (int) (((f3 - f2) / i5) * i6);
        int i8 = i3 + i7;
        if (i8 >= i6 || i8 < 0) {
            return 0;
        }
        return i7;
    }

    private void setupCallbacks() {
        this.mRecyclerView.addItemDecoration(this);
        this.mRecyclerView.addOnItemTouchListener(this);
        this.mRecyclerView.addOnScrollListener(this.mOnScrollListener);
    }

    private void verticalScrollTo(float f2) {
        int[] verticalRange = getVerticalRange();
        float max = Math.max(verticalRange[0], Math.min(verticalRange[1], f2));
        if (Math.abs(this.f3564d - max) < 2.0f) {
            return;
        }
        int scrollTo = scrollTo(this.f3565e, max, verticalRange, this.mRecyclerView.computeVerticalScrollRange(), this.mRecyclerView.computeVerticalScrollOffset(), this.mRecyclerViewHeight);
        if (scrollTo != 0) {
            this.mRecyclerView.scrollBy(0, scrollTo);
        }
        this.f3565e = max;
    }

    @VisibleForTesting
    void a(int i2) {
        int i3 = this.f3570j;
        if (i3 == 1) {
            this.f3569i.cancel();
        } else if (i3 != 2) {
            return;
        }
        this.f3570j = 3;
        ValueAnimator valueAnimator = this.f3569i;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f);
        this.f3569i.setDuration(i2);
        this.f3569i.start();
    }

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            destroyCallbacks();
        }
        this.mRecyclerView = recyclerView;
        if (recyclerView != null) {
            setupCallbacks();
        }
    }

    @VisibleForTesting
    boolean b(float f2, float f3) {
        if (f3 >= this.mRecyclerViewHeight - this.mHorizontalThumbHeight) {
            int i2 = this.f3567g;
            int i3 = this.f3566f;
            if (f2 >= i2 - (i3 / 2) && f2 <= i2 + (i3 / 2)) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    boolean c(float f2, float f3) {
        if (!isLayoutRTL() ? f2 >= this.mRecyclerViewWidth - this.mVerticalThumbWidth : f2 <= this.mVerticalThumbWidth) {
            int i2 = this.f3564d;
            int i3 = this.f3563c;
            if (f3 >= i2 - (i3 / 2) && f3 <= i2 + (i3 / 2)) {
                return true;
            }
        }
        return false;
    }

    void d() {
        this.mRecyclerView.invalidate();
    }

    void e(int i2) {
        int i3;
        if (i2 == 2 && this.mState != 2) {
            this.f3561a.setState(PRESSED_STATE_SET);
            cancelHide();
        }
        if (i2 == 0) {
            d();
        } else {
            show();
        }
        if (this.mState != 2 || i2 == 2) {
            i3 = i2 == 1 ? 1500 : 1500;
            this.mState = i2;
        }
        this.f3561a.setState(EMPTY_STATE_SET);
        i3 = HIDE_DELAY_AFTER_DRAGGING_MS;
        resetHideDelay(i3);
        this.mState = i2;
    }

    void f(int i2, int i3) {
        int computeVerticalScrollRange = this.mRecyclerView.computeVerticalScrollRange();
        int i4 = this.mRecyclerViewHeight;
        this.mNeedVerticalScrollbar = computeVerticalScrollRange - i4 > 0 && i4 >= this.mScrollbarMinimumRange;
        int computeHorizontalScrollRange = this.mRecyclerView.computeHorizontalScrollRange();
        int i5 = this.mRecyclerViewWidth;
        boolean z = computeHorizontalScrollRange - i5 > 0 && i5 >= this.mScrollbarMinimumRange;
        this.mNeedHorizontalScrollbar = z;
        boolean z2 = this.mNeedVerticalScrollbar;
        if (!z2 && !z) {
            if (this.mState != 0) {
                e(0);
                return;
            }
            return;
        }
        if (z2) {
            float f2 = i4;
            this.f3564d = (int) ((f2 * (i3 + (f2 / 2.0f))) / computeVerticalScrollRange);
            this.f3563c = Math.min(i4, (i4 * i4) / computeVerticalScrollRange);
        }
        if (this.mNeedHorizontalScrollbar) {
            float f3 = i5;
            this.f3567g = (int) ((f3 * (i2 + (f3 / 2.0f))) / computeHorizontalScrollRange);
            this.f3566f = Math.min(i5, (i5 * i5) / computeHorizontalScrollRange);
        }
        int i6 = this.mState;
        if (i6 == 0 || i6 == 1) {
            e(1);
        }
    }

    public boolean isDragging() {
        return this.mState == 2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        if (this.mRecyclerViewWidth != this.mRecyclerView.getWidth() || this.mRecyclerViewHeight != this.mRecyclerView.getHeight()) {
            this.mRecyclerViewWidth = this.mRecyclerView.getWidth();
            this.mRecyclerViewHeight = this.mRecyclerView.getHeight();
            e(0);
        } else if (this.f3570j != 0) {
            if (this.mNeedVerticalScrollbar) {
                drawVerticalScrollbar(canvas);
            }
            if (this.mNeedHorizontalScrollbar) {
                drawHorizontalScrollbar(canvas);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        int i2 = this.mState;
        if (i2 == 1) {
            boolean c2 = c(motionEvent.getX(), motionEvent.getY());
            boolean b2 = b(motionEvent.getX(), motionEvent.getY());
            if (motionEvent.getAction() != 0) {
                return false;
            }
            if (!c2 && !b2) {
                return false;
            }
            if (b2) {
                this.mDragState = 1;
                this.f3568h = (int) motionEvent.getX();
            } else if (c2) {
                this.mDragState = 2;
                this.f3565e = (int) motionEvent.getY();
            }
            e(2);
        } else if (i2 != 2) {
            return false;
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        if (this.mState == 0) {
            return;
        }
        if (motionEvent.getAction() == 0) {
            boolean c2 = c(motionEvent.getX(), motionEvent.getY());
            boolean b2 = b(motionEvent.getX(), motionEvent.getY());
            if (c2 || b2) {
                if (b2) {
                    this.mDragState = 1;
                    this.f3568h = (int) motionEvent.getX();
                } else if (c2) {
                    this.mDragState = 2;
                    this.f3565e = (int) motionEvent.getY();
                }
                e(2);
            }
        } else if (motionEvent.getAction() == 1 && this.mState == 2) {
            this.f3565e = 0.0f;
            this.f3568h = 0.0f;
            e(1);
            this.mDragState = 0;
        } else if (motionEvent.getAction() == 2 && this.mState == 2) {
            show();
            if (this.mDragState == 1) {
                horizontalScrollTo(motionEvent.getX());
            }
            if (this.mDragState == 2) {
                verticalScrollTo(motionEvent.getY());
            }
        }
    }

    public void show() {
        int i2 = this.f3570j;
        if (i2 != 0) {
            if (i2 != 3) {
                return;
            }
            this.f3569i.cancel();
        }
        this.f3570j = 1;
        ValueAnimator valueAnimator = this.f3569i;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 1.0f);
        this.f3569i.setDuration(500L);
        this.f3569i.setStartDelay(0L);
        this.f3569i.start();
    }
}
