package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.R;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class ItemTouchHelper extends RecyclerView.ItemDecoration implements RecyclerView.OnChildAttachStateChangeListener {
    private static final int ACTION_MODE_IDLE_MASK = 255;
    public static final int ACTION_STATE_DRAG = 2;
    public static final int ACTION_STATE_IDLE = 0;
    public static final int ACTION_STATE_SWIPE = 1;
    private static final int ACTIVE_POINTER_ID_NONE = -1;
    public static final int ANIMATION_TYPE_DRAG = 8;
    public static final int ANIMATION_TYPE_SWIPE_CANCEL = 4;
    public static final int ANIMATION_TYPE_SWIPE_SUCCESS = 2;
    private static final boolean DEBUG = false;
    public static final int DOWN = 2;
    public static final int END = 32;
    public static final int LEFT = 4;
    private static final int PIXELS_PER_SECOND = 1000;
    public static final int RIGHT = 8;
    public static final int START = 16;
    private static final String TAG = "ItemTouchHelper";
    public static final int UP = 1;

    /* renamed from: c  reason: collision with root package name */
    float f3593c;

    /* renamed from: d  reason: collision with root package name */
    float f3594d;

    /* renamed from: e  reason: collision with root package name */
    float f3595e;

    /* renamed from: f  reason: collision with root package name */
    float f3596f;
    @NonNull

    /* renamed from: h  reason: collision with root package name */
    Callback f3598h;

    /* renamed from: i  reason: collision with root package name */
    int f3599i;

    /* renamed from: k  reason: collision with root package name */
    RecyclerView f3601k;

    /* renamed from: m  reason: collision with root package name */
    VelocityTracker f3603m;
    private List<Integer> mDistances;
    private long mDragScrollStartTimeInMs;
    private ItemTouchHelperGestureListener mItemTouchHelperGestureListener;
    private float mMaxSwipeVelocity;
    private float mSelectedStartX;
    private float mSelectedStartY;
    private int mSlop;
    private List<RecyclerView.ViewHolder> mSwapTargets;
    private float mSwipeEscapeVelocity;
    private Rect mTmpRect;

    /* renamed from: p  reason: collision with root package name */
    GestureDetectorCompat f3606p;

    /* renamed from: a  reason: collision with root package name */
    final List f3591a = new ArrayList();
    private final float[] mTmpPosition = new float[2];

    /* renamed from: b  reason: collision with root package name */
    RecyclerView.ViewHolder f3592b = null;

    /* renamed from: g  reason: collision with root package name */
    int f3597g = -1;
    private int mActionState = 0;
    @VisibleForTesting

    /* renamed from: j  reason: collision with root package name */
    List f3600j = new ArrayList();

    /* renamed from: l  reason: collision with root package name */
    final Runnable f3602l = new Runnable() { // from class: androidx.recyclerview.widget.ItemTouchHelper.1
        @Override // java.lang.Runnable
        public void run() {
            ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
            if (itemTouchHelper.f3592b == null || !itemTouchHelper.j()) {
                return;
            }
            ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
            RecyclerView.ViewHolder viewHolder = itemTouchHelper2.f3592b;
            if (viewHolder != null) {
                itemTouchHelper2.f(viewHolder);
            }
            ItemTouchHelper itemTouchHelper3 = ItemTouchHelper.this;
            itemTouchHelper3.f3601k.removeCallbacks(itemTouchHelper3.f3602l);
            ViewCompat.postOnAnimation(ItemTouchHelper.this.f3601k, this);
        }
    };
    private RecyclerView.ChildDrawingOrderCallback mChildDrawingOrderCallback = null;

    /* renamed from: n  reason: collision with root package name */
    View f3604n = null;

    /* renamed from: o  reason: collision with root package name */
    int f3605o = -1;
    private final RecyclerView.OnItemTouchListener mOnItemTouchListener = new RecyclerView.OnItemTouchListener() { // from class: androidx.recyclerview.widget.ItemTouchHelper.2
        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
            int findPointerIndex;
            RecoverAnimation c2;
            ItemTouchHelper.this.f3606p.onTouchEvent(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                ItemTouchHelper.this.f3597g = motionEvent.getPointerId(0);
                ItemTouchHelper.this.f3593c = motionEvent.getX();
                ItemTouchHelper.this.f3594d = motionEvent.getY();
                ItemTouchHelper.this.g();
                ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
                if (itemTouchHelper.f3592b == null && (c2 = itemTouchHelper.c(motionEvent)) != null) {
                    ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
                    itemTouchHelper2.f3593c -= c2.f3625i;
                    itemTouchHelper2.f3594d -= c2.f3626j;
                    itemTouchHelper2.b(c2.f3621e, true);
                    if (ItemTouchHelper.this.f3591a.remove(c2.f3621e.itemView)) {
                        ItemTouchHelper itemTouchHelper3 = ItemTouchHelper.this;
                        itemTouchHelper3.f3598h.clearView(itemTouchHelper3.f3601k, c2.f3621e);
                    }
                    ItemTouchHelper.this.k(c2.f3621e, c2.f3622f);
                    ItemTouchHelper itemTouchHelper4 = ItemTouchHelper.this;
                    itemTouchHelper4.l(motionEvent, itemTouchHelper4.f3599i, 0);
                }
            } else if (actionMasked == 3 || actionMasked == 1) {
                ItemTouchHelper itemTouchHelper5 = ItemTouchHelper.this;
                itemTouchHelper5.f3597g = -1;
                itemTouchHelper5.k(null, 0);
            } else {
                int i2 = ItemTouchHelper.this.f3597g;
                if (i2 != -1 && (findPointerIndex = motionEvent.findPointerIndex(i2)) >= 0) {
                    ItemTouchHelper.this.a(actionMasked, motionEvent, findPointerIndex);
                }
            }
            VelocityTracker velocityTracker = ItemTouchHelper.this.f3603m;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
            return ItemTouchHelper.this.f3592b != null;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
            if (z) {
                ItemTouchHelper.this.k(null, 0);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
            ItemTouchHelper.this.f3606p.onTouchEvent(motionEvent);
            VelocityTracker velocityTracker = ItemTouchHelper.this.f3603m;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
            if (ItemTouchHelper.this.f3597g == -1) {
                return;
            }
            int actionMasked = motionEvent.getActionMasked();
            int findPointerIndex = motionEvent.findPointerIndex(ItemTouchHelper.this.f3597g);
            if (findPointerIndex >= 0) {
                ItemTouchHelper.this.a(actionMasked, motionEvent, findPointerIndex);
            }
            ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
            RecyclerView.ViewHolder viewHolder = itemTouchHelper.f3592b;
            if (viewHolder == null) {
                return;
            }
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    if (findPointerIndex >= 0) {
                        itemTouchHelper.l(motionEvent, itemTouchHelper.f3599i, findPointerIndex);
                        ItemTouchHelper.this.f(viewHolder);
                        ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
                        itemTouchHelper2.f3601k.removeCallbacks(itemTouchHelper2.f3602l);
                        ItemTouchHelper.this.f3602l.run();
                        ItemTouchHelper.this.f3601k.invalidate();
                        return;
                    }
                    return;
                } else if (actionMasked != 3) {
                    if (actionMasked != 6) {
                        return;
                    }
                    int actionIndex = motionEvent.getActionIndex();
                    int pointerId = motionEvent.getPointerId(actionIndex);
                    ItemTouchHelper itemTouchHelper3 = ItemTouchHelper.this;
                    if (pointerId == itemTouchHelper3.f3597g) {
                        itemTouchHelper3.f3597g = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
                        ItemTouchHelper itemTouchHelper4 = ItemTouchHelper.this;
                        itemTouchHelper4.l(motionEvent, itemTouchHelper4.f3599i, actionIndex);
                        return;
                    }
                    return;
                } else {
                    VelocityTracker velocityTracker2 = itemTouchHelper.f3603m;
                    if (velocityTracker2 != null) {
                        velocityTracker2.clear();
                    }
                }
            }
            ItemTouchHelper.this.k(null, 0);
            ItemTouchHelper.this.f3597g = -1;
        }
    };

    /* loaded from: classes.dex */
    public static abstract class Callback {
        private static final int ABS_HORIZONTAL_DIR_FLAGS = 789516;
        public static final int DEFAULT_DRAG_ANIMATION_DURATION = 200;
        public static final int DEFAULT_SWIPE_ANIMATION_DURATION = 250;
        private static final long DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS = 2000;
        private static final Interpolator sDragScrollInterpolator = new Interpolator() { // from class: androidx.recyclerview.widget.ItemTouchHelper.Callback.1
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f2) {
                return f2 * f2 * f2 * f2 * f2;
            }
        };
        private static final Interpolator sDragViewScrollCapInterpolator = new Interpolator() { // from class: androidx.recyclerview.widget.ItemTouchHelper.Callback.2
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f2) {
                float f3 = f2 - 1.0f;
                return (f3 * f3 * f3 * f3 * f3) + 1.0f;
            }
        };
        private int mCachedMaxScrollSpeed = -1;

        public static int convertToRelativeDirection(int i2, int i3) {
            int i4;
            int i5 = i2 & ABS_HORIZONTAL_DIR_FLAGS;
            if (i5 == 0) {
                return i2;
            }
            int i6 = i2 & (~i5);
            if (i3 == 0) {
                i4 = i5 << 2;
            } else {
                int i7 = i5 << 1;
                i6 |= (-789517) & i7;
                i4 = (i7 & ABS_HORIZONTAL_DIR_FLAGS) << 2;
            }
            return i6 | i4;
        }

        @NonNull
        public static ItemTouchUIUtil getDefaultUIUtil() {
            return ItemTouchUIUtilImpl.f3630a;
        }

        private int getMaxDragScroll(RecyclerView recyclerView) {
            if (this.mCachedMaxScrollSpeed == -1) {
                this.mCachedMaxScrollSpeed = recyclerView.getResources().getDimensionPixelSize(R.dimen.item_touch_helper_max_drag_scroll_per_frame);
            }
            return this.mCachedMaxScrollSpeed;
        }

        public static int makeFlag(int i2, int i3) {
            return i3 << (i2 * 8);
        }

        public static int makeMovementFlags(int i2, int i3) {
            int makeFlag = makeFlag(0, i3 | i2);
            return makeFlag(2, i2) | makeFlag(1, i3) | makeFlag;
        }

        final int a(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return convertToAbsoluteDirection(getMovementFlags(recyclerView, viewHolder), ViewCompat.getLayoutDirection(recyclerView));
        }

        boolean b(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return (a(recyclerView, viewHolder) & 16711680) != 0;
        }

        boolean c(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return (a(recyclerView, viewHolder) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) != 0;
        }

        public boolean canDropOver(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder2) {
            return true;
        }

        public RecyclerView.ViewHolder chooseDropTarget(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull List<RecyclerView.ViewHolder> list, int i2, int i3) {
            int bottom;
            int abs;
            int top;
            int abs2;
            int left;
            int abs3;
            int right;
            int abs4;
            int width = i2 + viewHolder.itemView.getWidth();
            int height = i3 + viewHolder.itemView.getHeight();
            int left2 = i2 - viewHolder.itemView.getLeft();
            int top2 = i3 - viewHolder.itemView.getTop();
            int size = list.size();
            RecyclerView.ViewHolder viewHolder2 = null;
            int i4 = -1;
            for (int i5 = 0; i5 < size; i5++) {
                RecyclerView.ViewHolder viewHolder3 = list.get(i5);
                if (left2 > 0 && (right = viewHolder3.itemView.getRight() - width) < 0 && viewHolder3.itemView.getRight() > viewHolder.itemView.getRight() && (abs4 = Math.abs(right)) > i4) {
                    viewHolder2 = viewHolder3;
                    i4 = abs4;
                }
                if (left2 < 0 && (left = viewHolder3.itemView.getLeft() - i2) > 0 && viewHolder3.itemView.getLeft() < viewHolder.itemView.getLeft() && (abs3 = Math.abs(left)) > i4) {
                    viewHolder2 = viewHolder3;
                    i4 = abs3;
                }
                if (top2 < 0 && (top = viewHolder3.itemView.getTop() - i3) > 0 && viewHolder3.itemView.getTop() < viewHolder.itemView.getTop() && (abs2 = Math.abs(top)) > i4) {
                    viewHolder2 = viewHolder3;
                    i4 = abs2;
                }
                if (top2 > 0 && (bottom = viewHolder3.itemView.getBottom() - height) < 0 && viewHolder3.itemView.getBottom() > viewHolder.itemView.getBottom() && (abs = Math.abs(bottom)) > i4) {
                    viewHolder2 = viewHolder3;
                    i4 = abs;
                }
            }
            return viewHolder2;
        }

        public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            ItemTouchUIUtilImpl.f3630a.clearView(viewHolder.itemView);
        }

        public int convertToAbsoluteDirection(int i2, int i3) {
            int i4;
            int i5 = i2 & 3158064;
            if (i5 == 0) {
                return i2;
            }
            int i6 = i2 & (~i5);
            if (i3 == 0) {
                i4 = i5 >> 2;
            } else {
                int i7 = i5 >> 1;
                i6 |= (-3158065) & i7;
                i4 = (i7 & 3158064) >> 2;
            }
            return i6 | i4;
        }

        void d(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, List list, int i2, float f2, float f3) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                RecoverAnimation recoverAnimation = (RecoverAnimation) list.get(i3);
                recoverAnimation.update();
                int save = canvas.save();
                onChildDraw(canvas, recyclerView, recoverAnimation.f3621e, recoverAnimation.f3625i, recoverAnimation.f3626j, recoverAnimation.f3622f, false);
                canvas.restoreToCount(save);
            }
            if (viewHolder != null) {
                int save2 = canvas.save();
                onChildDraw(canvas, recyclerView, viewHolder, f2, f3, i2, true);
                canvas.restoreToCount(save2);
            }
        }

        void e(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, List list, int i2, float f2, float f3) {
            int size = list.size();
            boolean z = false;
            for (int i3 = 0; i3 < size; i3++) {
                RecoverAnimation recoverAnimation = (RecoverAnimation) list.get(i3);
                int save = canvas.save();
                onChildDrawOver(canvas, recyclerView, recoverAnimation.f3621e, recoverAnimation.f3625i, recoverAnimation.f3626j, recoverAnimation.f3622f, false);
                canvas.restoreToCount(save);
            }
            if (viewHolder != null) {
                int save2 = canvas.save();
                onChildDrawOver(canvas, recyclerView, viewHolder, f2, f3, i2, true);
                canvas.restoreToCount(save2);
            }
            for (int i4 = size - 1; i4 >= 0; i4--) {
                RecoverAnimation recoverAnimation2 = (RecoverAnimation) list.get(i4);
                boolean z2 = recoverAnimation2.f3628l;
                if (z2 && !recoverAnimation2.f3624h) {
                    list.remove(i4);
                } else if (!z2) {
                    z = true;
                }
            }
            if (z) {
                recyclerView.invalidate();
            }
        }

        public long getAnimationDuration(@NonNull RecyclerView recyclerView, int i2, float f2, float f3) {
            RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
            return itemAnimator == null ? i2 == 8 ? 200L : 250L : i2 == 8 ? itemAnimator.getMoveDuration() : itemAnimator.getRemoveDuration();
        }

        public int getBoundingBoxMargin() {
            return 0;
        }

        public float getMoveThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
            return 0.5f;
        }

        public abstract int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder);

        public float getSwipeEscapeVelocity(float f2) {
            return f2;
        }

        public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
            return 0.5f;
        }

        public float getSwipeVelocityThreshold(float f2) {
            return f2;
        }

        public int interpolateOutOfBoundsScroll(@NonNull RecyclerView recyclerView, int i2, int i3, int i4, long j2) {
            int signum = (int) (((int) (((int) Math.signum(i3)) * getMaxDragScroll(recyclerView) * sDragViewScrollCapInterpolator.getInterpolation(Math.min(1.0f, (Math.abs(i3) * 1.0f) / i2)))) * sDragScrollInterpolator.getInterpolation(j2 <= 2000 ? ((float) j2) / 2000.0f : 1.0f));
            return signum == 0 ? i3 > 0 ? 1 : -1 : signum;
        }

        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        public boolean isLongPressDragEnabled() {
            return true;
        }

        public void onChildDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float f2, float f3, int i2, boolean z) {
            ItemTouchUIUtilImpl.f3630a.onDraw(canvas, recyclerView, viewHolder.itemView, f2, f3, i2, z);
        }

        public void onChildDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f2, float f3, int i2, boolean z) {
            ItemTouchUIUtilImpl.f3630a.onDrawOver(canvas, recyclerView, viewHolder.itemView, f2, f3, i2, z);
        }

        public abstract boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder2);

        public void onMoved(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, int i2, @NonNull RecyclerView.ViewHolder viewHolder2, int i3, int i4, int i5) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof ViewDropHandler) {
                ((ViewDropHandler) layoutManager).prepareForDrop(viewHolder.itemView, viewHolder2.itemView, i4, i5);
                return;
            }
            if (layoutManager.canScrollHorizontally()) {
                if (layoutManager.getDecoratedLeft(viewHolder2.itemView) <= recyclerView.getPaddingLeft()) {
                    recyclerView.scrollToPosition(i3);
                }
                if (layoutManager.getDecoratedRight(viewHolder2.itemView) >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                    recyclerView.scrollToPosition(i3);
                }
            }
            if (layoutManager.canScrollVertically()) {
                if (layoutManager.getDecoratedTop(viewHolder2.itemView) <= recyclerView.getPaddingTop()) {
                    recyclerView.scrollToPosition(i3);
                }
                if (layoutManager.getDecoratedBottom(viewHolder2.itemView) >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                    recyclerView.scrollToPosition(i3);
                }
            }
        }

        public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int i2) {
            if (viewHolder != null) {
                ItemTouchUIUtilImpl.f3630a.onSelected(viewHolder.itemView);
            }
        }

        public abstract void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
        private boolean mShouldReactToLongPress = true;

        ItemTouchHelperGestureListener() {
        }

        void a() {
            this.mShouldReactToLongPress = false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            View d2;
            RecyclerView.ViewHolder childViewHolder;
            if (!this.mShouldReactToLongPress || (d2 = ItemTouchHelper.this.d(motionEvent)) == null || (childViewHolder = ItemTouchHelper.this.f3601k.getChildViewHolder(d2)) == null) {
                return;
            }
            ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
            if (itemTouchHelper.f3598h.b(itemTouchHelper.f3601k, childViewHolder)) {
                int pointerId = motionEvent.getPointerId(0);
                int i2 = ItemTouchHelper.this.f3597g;
                if (pointerId == i2) {
                    int findPointerIndex = motionEvent.findPointerIndex(i2);
                    float x = motionEvent.getX(findPointerIndex);
                    float y = motionEvent.getY(findPointerIndex);
                    ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
                    itemTouchHelper2.f3593c = x;
                    itemTouchHelper2.f3594d = y;
                    itemTouchHelper2.f3596f = 0.0f;
                    itemTouchHelper2.f3595e = 0.0f;
                    if (itemTouchHelper2.f3598h.isLongPressDragEnabled()) {
                        ItemTouchHelper.this.k(childViewHolder, 2);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes.dex */
    public static class RecoverAnimation implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        final float f3617a;

        /* renamed from: b  reason: collision with root package name */
        final float f3618b;

        /* renamed from: c  reason: collision with root package name */
        final float f3619c;

        /* renamed from: d  reason: collision with root package name */
        final float f3620d;

        /* renamed from: e  reason: collision with root package name */
        final RecyclerView.ViewHolder f3621e;

        /* renamed from: f  reason: collision with root package name */
        final int f3622f;
        @VisibleForTesting

        /* renamed from: g  reason: collision with root package name */
        final ValueAnimator f3623g;

        /* renamed from: h  reason: collision with root package name */
        boolean f3624h;

        /* renamed from: i  reason: collision with root package name */
        float f3625i;

        /* renamed from: j  reason: collision with root package name */
        float f3626j;

        /* renamed from: k  reason: collision with root package name */
        boolean f3627k = false;

        /* renamed from: l  reason: collision with root package name */
        boolean f3628l = false;
        private float mFraction;

        RecoverAnimation(RecyclerView.ViewHolder viewHolder, int i2, int i3, float f2, float f3, float f4, float f5) {
            this.f3622f = i3;
            this.f3621e = viewHolder;
            this.f3617a = f2;
            this.f3618b = f3;
            this.f3619c = f4;
            this.f3620d = f5;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.f3623g = ofFloat;
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: androidx.recyclerview.widget.ItemTouchHelper.RecoverAnimation.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    RecoverAnimation.this.setFraction(valueAnimator.getAnimatedFraction());
                }
            });
            ofFloat.setTarget(viewHolder.itemView);
            ofFloat.addListener(this);
            setFraction(0.0f);
        }

        public void cancel() {
            this.f3623g.cancel();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            setFraction(1.0f);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (!this.f3628l) {
                this.f3621e.setIsRecyclable(true);
            }
            this.f3628l = true;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        public void setDuration(long j2) {
            this.f3623g.setDuration(j2);
        }

        public void setFraction(float f2) {
            this.mFraction = f2;
        }

        public void start() {
            this.f3621e.setIsRecyclable(false);
            this.f3623g.start();
        }

        public void update() {
            float f2 = this.f3617a;
            float f3 = this.f3619c;
            this.f3625i = f2 == f3 ? this.f3621e.itemView.getTranslationX() : f2 + (this.mFraction * (f3 - f2));
            float f4 = this.f3618b;
            float f5 = this.f3620d;
            this.f3626j = f4 == f5 ? this.f3621e.itemView.getTranslationY() : f4 + (this.mFraction * (f5 - f4));
        }
    }

    /* loaded from: classes.dex */
    public static abstract class SimpleCallback extends Callback {
        private int mDefaultDragDirs;
        private int mDefaultSwipeDirs;

        public SimpleCallback(int i2, int i3) {
            this.mDefaultSwipeDirs = i3;
            this.mDefaultDragDirs = i2;
        }

        public int getDragDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return this.mDefaultDragDirs;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return Callback.makeMovementFlags(getDragDirs(recyclerView, viewHolder), getSwipeDirs(recyclerView, viewHolder));
        }

        public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return this.mDefaultSwipeDirs;
        }

        public void setDefaultDragDirs(int i2) {
            this.mDefaultDragDirs = i2;
        }

        public void setDefaultSwipeDirs(int i2) {
            this.mDefaultSwipeDirs = i2;
        }
    }

    /* loaded from: classes.dex */
    public interface ViewDropHandler {
        void prepareForDrop(@NonNull View view, @NonNull View view2, int i2, int i3);
    }

    public ItemTouchHelper(@NonNull Callback callback) {
        this.f3598h = callback;
    }

    private void addChildDrawingOrderCallback() {
        if (Build.VERSION.SDK_INT >= 21) {
            return;
        }
        if (this.mChildDrawingOrderCallback == null) {
            this.mChildDrawingOrderCallback = new RecyclerView.ChildDrawingOrderCallback() { // from class: androidx.recyclerview.widget.ItemTouchHelper.5
                @Override // androidx.recyclerview.widget.RecyclerView.ChildDrawingOrderCallback
                public int onGetChildDrawingOrder(int i2, int i3) {
                    ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
                    View view = itemTouchHelper.f3604n;
                    if (view == null) {
                        return i3;
                    }
                    int i4 = itemTouchHelper.f3605o;
                    if (i4 == -1) {
                        i4 = itemTouchHelper.f3601k.indexOfChild(view);
                        ItemTouchHelper.this.f3605o = i4;
                    }
                    return i3 == i2 + (-1) ? i4 : i3 < i4 ? i3 : i3 + 1;
                }
            };
        }
        this.f3601k.setChildDrawingOrderCallback(this.mChildDrawingOrderCallback);
    }

    private int checkHorizontalSwipe(RecyclerView.ViewHolder viewHolder, int i2) {
        if ((i2 & 12) != 0) {
            int i3 = this.f3595e > 0.0f ? 8 : 4;
            VelocityTracker velocityTracker = this.f3603m;
            if (velocityTracker != null && this.f3597g > -1) {
                velocityTracker.computeCurrentVelocity(1000, this.f3598h.getSwipeVelocityThreshold(this.mMaxSwipeVelocity));
                float xVelocity = this.f3603m.getXVelocity(this.f3597g);
                float yVelocity = this.f3603m.getYVelocity(this.f3597g);
                int i4 = xVelocity <= 0.0f ? 4 : 8;
                float abs = Math.abs(xVelocity);
                if ((i4 & i2) != 0 && i3 == i4 && abs >= this.f3598h.getSwipeEscapeVelocity(this.mSwipeEscapeVelocity) && abs > Math.abs(yVelocity)) {
                    return i4;
                }
            }
            float width = this.f3601k.getWidth() * this.f3598h.getSwipeThreshold(viewHolder);
            if ((i2 & i3) == 0 || Math.abs(this.f3595e) <= width) {
                return 0;
            }
            return i3;
        }
        return 0;
    }

    private int checkVerticalSwipe(RecyclerView.ViewHolder viewHolder, int i2) {
        if ((i2 & 3) != 0) {
            int i3 = this.f3596f > 0.0f ? 2 : 1;
            VelocityTracker velocityTracker = this.f3603m;
            if (velocityTracker != null && this.f3597g > -1) {
                velocityTracker.computeCurrentVelocity(1000, this.f3598h.getSwipeVelocityThreshold(this.mMaxSwipeVelocity));
                float xVelocity = this.f3603m.getXVelocity(this.f3597g);
                float yVelocity = this.f3603m.getYVelocity(this.f3597g);
                int i4 = yVelocity <= 0.0f ? 1 : 2;
                float abs = Math.abs(yVelocity);
                if ((i4 & i2) != 0 && i4 == i3 && abs >= this.f3598h.getSwipeEscapeVelocity(this.mSwipeEscapeVelocity) && abs > Math.abs(xVelocity)) {
                    return i4;
                }
            }
            float height = this.f3601k.getHeight() * this.f3598h.getSwipeThreshold(viewHolder);
            if ((i2 & i3) == 0 || Math.abs(this.f3596f) <= height) {
                return 0;
            }
            return i3;
        }
        return 0;
    }

    private void destroyCallbacks() {
        this.f3601k.removeItemDecoration(this);
        this.f3601k.removeOnItemTouchListener(this.mOnItemTouchListener);
        this.f3601k.removeOnChildAttachStateChangeListener(this);
        for (int size = this.f3600j.size() - 1; size >= 0; size--) {
            RecoverAnimation recoverAnimation = (RecoverAnimation) this.f3600j.get(0);
            recoverAnimation.cancel();
            this.f3598h.clearView(this.f3601k, recoverAnimation.f3621e);
        }
        this.f3600j.clear();
        this.f3604n = null;
        this.f3605o = -1;
        releaseVelocityTracker();
        stopGestureDetection();
    }

    private List<RecyclerView.ViewHolder> findSwapTargets(RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        List<RecyclerView.ViewHolder> list = this.mSwapTargets;
        if (list == null) {
            this.mSwapTargets = new ArrayList();
            this.mDistances = new ArrayList();
        } else {
            list.clear();
            this.mDistances.clear();
        }
        int boundingBoxMargin = this.f3598h.getBoundingBoxMargin();
        int round = Math.round(this.mSelectedStartX + this.f3595e) - boundingBoxMargin;
        int round2 = Math.round(this.mSelectedStartY + this.f3596f) - boundingBoxMargin;
        int i2 = boundingBoxMargin * 2;
        int width = viewHolder2.itemView.getWidth() + round + i2;
        int height = viewHolder2.itemView.getHeight() + round2 + i2;
        int i3 = (round + width) / 2;
        int i4 = (round2 + height) / 2;
        RecyclerView.LayoutManager layoutManager = this.f3601k.getLayoutManager();
        int childCount = layoutManager.getChildCount();
        int i5 = 0;
        while (i5 < childCount) {
            View childAt = layoutManager.getChildAt(i5);
            if (childAt != viewHolder2.itemView && childAt.getBottom() >= round2 && childAt.getTop() <= height && childAt.getRight() >= round && childAt.getLeft() <= width) {
                RecyclerView.ViewHolder childViewHolder = this.f3601k.getChildViewHolder(childAt);
                if (this.f3598h.canDropOver(this.f3601k, this.f3592b, childViewHolder)) {
                    int abs = Math.abs(i3 - ((childAt.getLeft() + childAt.getRight()) / 2));
                    int abs2 = Math.abs(i4 - ((childAt.getTop() + childAt.getBottom()) / 2));
                    int i6 = (abs * abs) + (abs2 * abs2);
                    int size = this.mSwapTargets.size();
                    int i7 = 0;
                    for (int i8 = 0; i8 < size && i6 > this.mDistances.get(i8).intValue(); i8++) {
                        i7++;
                    }
                    this.mSwapTargets.add(i7, childViewHolder);
                    this.mDistances.add(i7, Integer.valueOf(i6));
                }
            }
            i5++;
            viewHolder2 = viewHolder;
        }
        return this.mSwapTargets;
    }

    private RecyclerView.ViewHolder findSwipedView(MotionEvent motionEvent) {
        View d2;
        RecyclerView.LayoutManager layoutManager = this.f3601k.getLayoutManager();
        int i2 = this.f3597g;
        if (i2 == -1) {
            return null;
        }
        int findPointerIndex = motionEvent.findPointerIndex(i2);
        float abs = Math.abs(motionEvent.getX(findPointerIndex) - this.f3593c);
        float abs2 = Math.abs(motionEvent.getY(findPointerIndex) - this.f3594d);
        int i3 = this.mSlop;
        if (abs >= i3 || abs2 >= i3) {
            if (abs <= abs2 || !layoutManager.canScrollHorizontally()) {
                if ((abs2 <= abs || !layoutManager.canScrollVertically()) && (d2 = d(motionEvent)) != null) {
                    return this.f3601k.getChildViewHolder(d2);
                }
                return null;
            }
            return null;
        }
        return null;
    }

    private void getSelectedDxDy(float[] fArr) {
        if ((this.f3599i & 12) != 0) {
            fArr[0] = (this.mSelectedStartX + this.f3595e) - this.f3592b.itemView.getLeft();
        } else {
            fArr[0] = this.f3592b.itemView.getTranslationX();
        }
        if ((this.f3599i & 3) != 0) {
            fArr[1] = (this.mSelectedStartY + this.f3596f) - this.f3592b.itemView.getTop();
        } else {
            fArr[1] = this.f3592b.itemView.getTranslationY();
        }
    }

    private static boolean hitTest(View view, float f2, float f3, float f4, float f5) {
        return f2 >= f4 && f2 <= f4 + ((float) view.getWidth()) && f3 >= f5 && f3 <= f5 + ((float) view.getHeight());
    }

    private void releaseVelocityTracker() {
        VelocityTracker velocityTracker = this.f3603m;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f3603m = null;
        }
    }

    private void setupCallbacks() {
        this.mSlop = ViewConfiguration.get(this.f3601k.getContext()).getScaledTouchSlop();
        this.f3601k.addItemDecoration(this);
        this.f3601k.addOnItemTouchListener(this.mOnItemTouchListener);
        this.f3601k.addOnChildAttachStateChangeListener(this);
        startGestureDetection();
    }

    private void startGestureDetection() {
        this.mItemTouchHelperGestureListener = new ItemTouchHelperGestureListener();
        this.f3606p = new GestureDetectorCompat(this.f3601k.getContext(), this.mItemTouchHelperGestureListener);
    }

    private void stopGestureDetection() {
        ItemTouchHelperGestureListener itemTouchHelperGestureListener = this.mItemTouchHelperGestureListener;
        if (itemTouchHelperGestureListener != null) {
            itemTouchHelperGestureListener.a();
            this.mItemTouchHelperGestureListener = null;
        }
        if (this.f3606p != null) {
            this.f3606p = null;
        }
    }

    private int swipeIfNecessary(RecyclerView.ViewHolder viewHolder) {
        if (this.mActionState == 2) {
            return 0;
        }
        int movementFlags = this.f3598h.getMovementFlags(this.f3601k, viewHolder);
        int convertToAbsoluteDirection = (this.f3598h.convertToAbsoluteDirection(movementFlags, ViewCompat.getLayoutDirection(this.f3601k)) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
        if (convertToAbsoluteDirection == 0) {
            return 0;
        }
        int i2 = (movementFlags & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
        if (Math.abs(this.f3595e) > Math.abs(this.f3596f)) {
            int checkHorizontalSwipe = checkHorizontalSwipe(viewHolder, convertToAbsoluteDirection);
            if (checkHorizontalSwipe > 0) {
                return (i2 & checkHorizontalSwipe) == 0 ? Callback.convertToRelativeDirection(checkHorizontalSwipe, ViewCompat.getLayoutDirection(this.f3601k)) : checkHorizontalSwipe;
            }
            int checkVerticalSwipe = checkVerticalSwipe(viewHolder, convertToAbsoluteDirection);
            if (checkVerticalSwipe > 0) {
                return checkVerticalSwipe;
            }
        } else {
            int checkVerticalSwipe2 = checkVerticalSwipe(viewHolder, convertToAbsoluteDirection);
            if (checkVerticalSwipe2 > 0) {
                return checkVerticalSwipe2;
            }
            int checkHorizontalSwipe2 = checkHorizontalSwipe(viewHolder, convertToAbsoluteDirection);
            if (checkHorizontalSwipe2 > 0) {
                return (i2 & checkHorizontalSwipe2) == 0 ? Callback.convertToRelativeDirection(checkHorizontalSwipe2, ViewCompat.getLayoutDirection(this.f3601k)) : checkHorizontalSwipe2;
            }
        }
        return 0;
    }

    void a(int i2, MotionEvent motionEvent, int i3) {
        RecyclerView.ViewHolder findSwipedView;
        int a2;
        if (this.f3592b != null || i2 != 2 || this.mActionState == 2 || !this.f3598h.isItemViewSwipeEnabled() || this.f3601k.getScrollState() == 1 || (findSwipedView = findSwipedView(motionEvent)) == null || (a2 = (this.f3598h.a(this.f3601k, findSwipedView) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8) == 0) {
            return;
        }
        float x = motionEvent.getX(i3);
        float y = motionEvent.getY(i3);
        float f2 = x - this.f3593c;
        float f3 = y - this.f3594d;
        float abs = Math.abs(f2);
        float abs2 = Math.abs(f3);
        int i4 = this.mSlop;
        if (abs >= i4 || abs2 >= i4) {
            if (abs > abs2) {
                if (f2 < 0.0f && (a2 & 4) == 0) {
                    return;
                }
                if (f2 > 0.0f && (a2 & 8) == 0) {
                    return;
                }
            } else if (f3 < 0.0f && (a2 & 1) == 0) {
                return;
            } else {
                if (f3 > 0.0f && (a2 & 2) == 0) {
                    return;
                }
            }
            this.f3596f = 0.0f;
            this.f3595e = 0.0f;
            this.f3597g = motionEvent.getPointerId(0);
            k(findSwipedView, 1);
        }
    }

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.f3601k;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            destroyCallbacks();
        }
        this.f3601k = recyclerView;
        if (recyclerView != null) {
            Resources resources = recyclerView.getResources();
            this.mSwipeEscapeVelocity = resources.getDimension(R.dimen.item_touch_helper_swipe_escape_velocity);
            this.mMaxSwipeVelocity = resources.getDimension(R.dimen.item_touch_helper_swipe_escape_max_velocity);
            setupCallbacks();
        }
    }

    void b(RecyclerView.ViewHolder viewHolder, boolean z) {
        for (int size = this.f3600j.size() - 1; size >= 0; size--) {
            RecoverAnimation recoverAnimation = (RecoverAnimation) this.f3600j.get(size);
            if (recoverAnimation.f3621e == viewHolder) {
                recoverAnimation.f3627k |= z;
                if (!recoverAnimation.f3628l) {
                    recoverAnimation.cancel();
                }
                this.f3600j.remove(size);
                return;
            }
        }
    }

    RecoverAnimation c(MotionEvent motionEvent) {
        if (this.f3600j.isEmpty()) {
            return null;
        }
        View d2 = d(motionEvent);
        for (int size = this.f3600j.size() - 1; size >= 0; size--) {
            RecoverAnimation recoverAnimation = (RecoverAnimation) this.f3600j.get(size);
            if (recoverAnimation.f3621e.itemView == d2) {
                return recoverAnimation;
            }
        }
        return null;
    }

    View d(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        RecyclerView.ViewHolder viewHolder = this.f3592b;
        if (viewHolder != null) {
            View view = viewHolder.itemView;
            if (hitTest(view, x, y, this.mSelectedStartX + this.f3595e, this.mSelectedStartY + this.f3596f)) {
                return view;
            }
        }
        for (int size = this.f3600j.size() - 1; size >= 0; size--) {
            RecoverAnimation recoverAnimation = (RecoverAnimation) this.f3600j.get(size);
            View view2 = recoverAnimation.f3621e.itemView;
            if (hitTest(view2, x, y, recoverAnimation.f3625i, recoverAnimation.f3626j)) {
                return view2;
            }
        }
        return this.f3601k.findChildViewUnder(x, y);
    }

    boolean e() {
        int size = this.f3600j.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!((RecoverAnimation) this.f3600j.get(i2)).f3628l) {
                return true;
            }
        }
        return false;
    }

    void f(RecyclerView.ViewHolder viewHolder) {
        if (!this.f3601k.isLayoutRequested() && this.mActionState == 2) {
            float moveThreshold = this.f3598h.getMoveThreshold(viewHolder);
            int i2 = (int) (this.mSelectedStartX + this.f3595e);
            int i3 = (int) (this.mSelectedStartY + this.f3596f);
            if (Math.abs(i3 - viewHolder.itemView.getTop()) >= viewHolder.itemView.getHeight() * moveThreshold || Math.abs(i2 - viewHolder.itemView.getLeft()) >= viewHolder.itemView.getWidth() * moveThreshold) {
                List<RecyclerView.ViewHolder> findSwapTargets = findSwapTargets(viewHolder);
                if (findSwapTargets.size() == 0) {
                    return;
                }
                RecyclerView.ViewHolder chooseDropTarget = this.f3598h.chooseDropTarget(viewHolder, findSwapTargets, i2, i3);
                if (chooseDropTarget == null) {
                    this.mSwapTargets.clear();
                    this.mDistances.clear();
                    return;
                }
                int absoluteAdapterPosition = chooseDropTarget.getAbsoluteAdapterPosition();
                int absoluteAdapterPosition2 = viewHolder.getAbsoluteAdapterPosition();
                if (this.f3598h.onMove(this.f3601k, viewHolder, chooseDropTarget)) {
                    this.f3598h.onMoved(this.f3601k, viewHolder, absoluteAdapterPosition2, chooseDropTarget, absoluteAdapterPosition, i2, i3);
                }
            }
        }
    }

    void g() {
        VelocityTracker velocityTracker = this.f3603m;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        this.f3603m = VelocityTracker.obtain();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.setEmpty();
    }

    void h(final RecoverAnimation recoverAnimation, final int i2) {
        this.f3601k.post(new Runnable() { // from class: androidx.recyclerview.widget.ItemTouchHelper.4
            @Override // java.lang.Runnable
            public void run() {
                RecyclerView recyclerView = ItemTouchHelper.this.f3601k;
                if (recyclerView == null || !recyclerView.isAttachedToWindow()) {
                    return;
                }
                RecoverAnimation recoverAnimation2 = recoverAnimation;
                if (recoverAnimation2.f3627k || recoverAnimation2.f3621e.getAbsoluteAdapterPosition() == -1) {
                    return;
                }
                RecyclerView.ItemAnimator itemAnimator = ItemTouchHelper.this.f3601k.getItemAnimator();
                if ((itemAnimator == null || !itemAnimator.isRunning(null)) && !ItemTouchHelper.this.e()) {
                    ItemTouchHelper.this.f3598h.onSwiped(recoverAnimation.f3621e, i2);
                } else {
                    ItemTouchHelper.this.f3601k.post(this);
                }
            }
        });
    }

    void i(View view) {
        if (view == this.f3604n) {
            this.f3604n = null;
            if (this.mChildDrawingOrderCallback != null) {
                this.f3601k.setChildDrawingOrderCallback(null);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x00c1, code lost:
        if (r1 > 0) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0100 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x010c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    boolean j() {
        int i2;
        int i3;
        int i4;
        int width;
        if (this.f3592b == null) {
            this.mDragScrollStartTimeInMs = Long.MIN_VALUE;
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = this.mDragScrollStartTimeInMs;
        long j3 = j2 == Long.MIN_VALUE ? 0L : currentTimeMillis - j2;
        RecyclerView.LayoutManager layoutManager = this.f3601k.getLayoutManager();
        if (this.mTmpRect == null) {
            this.mTmpRect = new Rect();
        }
        layoutManager.calculateItemDecorationsForChild(this.f3592b.itemView, this.mTmpRect);
        if (layoutManager.canScrollHorizontally()) {
            int i5 = (int) (this.mSelectedStartX + this.f3595e);
            int paddingLeft = (i5 - this.mTmpRect.left) - this.f3601k.getPaddingLeft();
            float f2 = this.f3595e;
            if (f2 < 0.0f && paddingLeft < 0) {
                i2 = paddingLeft;
            } else if (f2 > 0.0f && (width = ((i5 + this.f3592b.itemView.getWidth()) + this.mTmpRect.right) - (this.f3601k.getWidth() - this.f3601k.getPaddingRight())) > 0) {
                i2 = width;
            }
            if (layoutManager.canScrollVertically()) {
                int i6 = (int) (this.mSelectedStartY + this.f3596f);
                int paddingTop = (i6 - this.mTmpRect.top) - this.f3601k.getPaddingTop();
                float f3 = this.f3596f;
                if (f3 < 0.0f && paddingTop < 0) {
                    i3 = paddingTop;
                } else if (f3 > 0.0f) {
                    i3 = ((i6 + this.f3592b.itemView.getHeight()) + this.mTmpRect.bottom) - (this.f3601k.getHeight() - this.f3601k.getPaddingBottom());
                }
                if (i2 != 0) {
                    i2 = this.f3598h.interpolateOutOfBoundsScroll(this.f3601k, this.f3592b.itemView.getWidth(), i2, this.f3601k.getWidth(), j3);
                }
                int i7 = i2;
                if (i3 != 0) {
                    i4 = i7;
                    i3 = this.f3598h.interpolateOutOfBoundsScroll(this.f3601k, this.f3592b.itemView.getHeight(), i3, this.f3601k.getHeight(), j3);
                } else {
                    i4 = i7;
                }
                if (i4 != 0 && i3 == 0) {
                    this.mDragScrollStartTimeInMs = Long.MIN_VALUE;
                    return false;
                }
                if (this.mDragScrollStartTimeInMs == Long.MIN_VALUE) {
                    this.mDragScrollStartTimeInMs = currentTimeMillis;
                }
                this.f3601k.scrollBy(i4, i3);
                return true;
            }
            i3 = 0;
            if (i2 != 0) {
            }
            int i72 = i2;
            if (i3 != 0) {
            }
            if (i4 != 0) {
            }
            if (this.mDragScrollStartTimeInMs == Long.MIN_VALUE) {
            }
            this.f3601k.scrollBy(i4, i3);
            return true;
        }
        i2 = 0;
        if (layoutManager.canScrollVertically()) {
        }
        i3 = 0;
        if (i2 != 0) {
        }
        int i722 = i2;
        if (i3 != 0) {
        }
        if (i4 != 0) {
        }
        if (this.mDragScrollStartTimeInMs == Long.MIN_VALUE) {
        }
        this.f3601k.scrollBy(i4, i3);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0136  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void k(@Nullable RecyclerView.ViewHolder viewHolder, int i2) {
        boolean z;
        boolean z2;
        ViewParent parent;
        float f2;
        float signum;
        if (viewHolder == this.f3592b && i2 == this.mActionState) {
            return;
        }
        this.mDragScrollStartTimeInMs = Long.MIN_VALUE;
        int i3 = this.mActionState;
        b(viewHolder, true);
        this.mActionState = i2;
        if (i2 == 2) {
            if (viewHolder == null) {
                throw new IllegalArgumentException("Must pass a ViewHolder when dragging");
            }
            this.f3604n = viewHolder.itemView;
            addChildDrawingOrderCallback();
        }
        int i4 = (1 << ((i2 * 8) + 8)) - 1;
        final RecyclerView.ViewHolder viewHolder2 = this.f3592b;
        if (viewHolder2 != null) {
            if (viewHolder2.itemView.getParent() != null) {
                int swipeIfNecessary = i3 == 2 ? 0 : swipeIfNecessary(viewHolder2);
                releaseVelocityTracker();
                if (swipeIfNecessary == 1 || swipeIfNecessary == 2) {
                    f2 = 0.0f;
                    signum = Math.signum(this.f3596f) * this.f3601k.getHeight();
                } else if (swipeIfNecessary == 4 || swipeIfNecessary == 8 || swipeIfNecessary == 16 || swipeIfNecessary == 32) {
                    signum = 0.0f;
                    f2 = Math.signum(this.f3595e) * this.f3601k.getWidth();
                } else {
                    f2 = 0.0f;
                    signum = 0.0f;
                }
                int i5 = i3 == 2 ? 8 : swipeIfNecessary > 0 ? 2 : 4;
                getSelectedDxDy(this.mTmpPosition);
                float[] fArr = this.mTmpPosition;
                float f3 = fArr[0];
                float f4 = fArr[1];
                final int i6 = swipeIfNecessary;
                RecoverAnimation recoverAnimation = new RecoverAnimation(viewHolder2, i5, i3, f3, f4, f2, signum) { // from class: androidx.recyclerview.widget.ItemTouchHelper.3
                    @Override // androidx.recyclerview.widget.ItemTouchHelper.RecoverAnimation, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        if (this.f3627k) {
                            return;
                        }
                        if (i6 <= 0) {
                            ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
                            itemTouchHelper.f3598h.clearView(itemTouchHelper.f3601k, viewHolder2);
                        } else {
                            ItemTouchHelper.this.f3591a.add(viewHolder2.itemView);
                            this.f3624h = true;
                            int i7 = i6;
                            if (i7 > 0) {
                                ItemTouchHelper.this.h(this, i7);
                            }
                        }
                        ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
                        View view = itemTouchHelper2.f3604n;
                        View view2 = viewHolder2.itemView;
                        if (view == view2) {
                            itemTouchHelper2.i(view2);
                        }
                    }
                };
                recoverAnimation.setDuration(this.f3598h.getAnimationDuration(this.f3601k, i5, f2 - f3, signum - f4));
                this.f3600j.add(recoverAnimation);
                recoverAnimation.start();
                z = true;
            } else {
                i(viewHolder2.itemView);
                this.f3598h.clearView(this.f3601k, viewHolder2);
                z = false;
            }
            this.f3592b = null;
        } else {
            z = false;
        }
        if (viewHolder != null) {
            this.f3599i = (this.f3598h.a(this.f3601k, viewHolder) & i4) >> (this.mActionState * 8);
            this.mSelectedStartX = viewHolder.itemView.getLeft();
            this.mSelectedStartY = viewHolder.itemView.getTop();
            this.f3592b = viewHolder;
            if (i2 == 2) {
                z2 = false;
                viewHolder.itemView.performHapticFeedback(0);
                parent = this.f3601k.getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(this.f3592b != null ? true : z2);
                }
                if (!z) {
                    this.f3601k.getLayoutManager().requestSimpleAnimationsInNextLayout();
                }
                this.f3598h.onSelectedChanged(this.f3592b, this.mActionState);
                this.f3601k.invalidate();
            }
        }
        z2 = false;
        parent = this.f3601k.getParent();
        if (parent != null) {
        }
        if (!z) {
        }
        this.f3598h.onSelectedChanged(this.f3592b, this.mActionState);
        this.f3601k.invalidate();
    }

    void l(MotionEvent motionEvent, int i2, int i3) {
        float x = motionEvent.getX(i3);
        float y = motionEvent.getY(i3);
        float f2 = x - this.f3593c;
        this.f3595e = f2;
        this.f3596f = y - this.f3594d;
        if ((i2 & 4) == 0) {
            this.f3595e = Math.max(0.0f, f2);
        }
        if ((i2 & 8) == 0) {
            this.f3595e = Math.min(0.0f, this.f3595e);
        }
        if ((i2 & 1) == 0) {
            this.f3596f = Math.max(0.0f, this.f3596f);
        }
        if ((i2 & 2) == 0) {
            this.f3596f = Math.min(0.0f, this.f3596f);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
    public void onChildViewAttachedToWindow(@NonNull View view) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
    public void onChildViewDetachedFromWindow(@NonNull View view) {
        i(view);
        RecyclerView.ViewHolder childViewHolder = this.f3601k.getChildViewHolder(view);
        if (childViewHolder == null) {
            return;
        }
        RecyclerView.ViewHolder viewHolder = this.f3592b;
        if (viewHolder != null && childViewHolder == viewHolder) {
            k(null, 0);
            return;
        }
        b(childViewHolder, false);
        if (this.f3591a.remove(childViewHolder.itemView)) {
            this.f3598h.clearView(this.f3601k, childViewHolder);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        float f2;
        float f3;
        this.f3605o = -1;
        if (this.f3592b != null) {
            getSelectedDxDy(this.mTmpPosition);
            float[] fArr = this.mTmpPosition;
            float f4 = fArr[0];
            f3 = fArr[1];
            f2 = f4;
        } else {
            f2 = 0.0f;
            f3 = 0.0f;
        }
        this.f3598h.d(canvas, recyclerView, this.f3592b, this.f3600j, this.mActionState, f2, f3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        float f2;
        float f3;
        if (this.f3592b != null) {
            getSelectedDxDy(this.mTmpPosition);
            float[] fArr = this.mTmpPosition;
            float f4 = fArr[0];
            f3 = fArr[1];
            f2 = f4;
        } else {
            f2 = 0.0f;
            f3 = 0.0f;
        }
        this.f3598h.e(canvas, recyclerView, this.f3592b, this.f3600j, this.mActionState, f2, f3);
    }

    public void startDrag(@NonNull RecyclerView.ViewHolder viewHolder) {
        if (!this.f3598h.b(this.f3601k, viewHolder)) {
            Log.e(TAG, "Start drag has been called but dragging is not enabled");
        } else if (viewHolder.itemView.getParent() != this.f3601k) {
            Log.e(TAG, "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
        } else {
            g();
            this.f3596f = 0.0f;
            this.f3595e = 0.0f;
            k(viewHolder, 2);
        }
    }

    public void startSwipe(@NonNull RecyclerView.ViewHolder viewHolder) {
        if (!this.f3598h.c(this.f3601k, viewHolder)) {
            Log.e(TAG, "Start swipe has been called but swiping is not enabled");
        } else if (viewHolder.itemView.getParent() != this.f3601k) {
            Log.e(TAG, "Start swipe has been called with a view holder which is not a child of the RecyclerView controlled by this ItemTouchHelper.");
        } else {
            g();
            this.f3596f = 0.0f;
            this.f3595e = 0.0f;
            k(viewHolder, 1);
        }
    }
}
