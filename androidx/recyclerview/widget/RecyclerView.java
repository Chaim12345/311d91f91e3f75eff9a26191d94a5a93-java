package androidx.recyclerview.widget;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.os.TraceCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.NestedScrollingChild2;
import androidx.core.view.NestedScrollingChild3;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ScrollingView;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.EdgeEffectCompat;
import androidx.customview.view.AbsSavedState;
import androidx.recyclerview.R;
import androidx.recyclerview.widget.AdapterHelper;
import androidx.recyclerview.widget.ChildHelper;
import androidx.recyclerview.widget.GapWorker;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
import androidx.recyclerview.widget.ViewBoundsCheck;
import androidx.recyclerview.widget.ViewInfoStore;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements ScrollingView, NestedScrollingChild2, NestedScrollingChild3 {
    private static final boolean FORCE_ABS_FOCUS_SEARCH_DIRECTION;
    public static final int HORIZONTAL = 0;
    static final boolean I;
    private static final boolean IGNORE_DETACHED_FOCUSED_CHILD;
    private static final int INVALID_POINTER = -1;
    public static final int INVALID_TYPE = -1;
    static final boolean J;
    static final boolean K;
    static final boolean L;
    private static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
    static final Interpolator M;
    private static final int[] NESTED_SCROLLING_ATTRS = {16843830};
    public static final long NO_ID = -1;
    public static final int NO_POSITION = -1;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    public static final int TOUCH_SLOP_DEFAULT = 0;
    public static final int TOUCH_SLOP_PAGING = 1;
    private static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG = "RV PartialInvalidate";
    private static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG = "RV FullInvalidate";
    private static final String TRACE_ON_LAYOUT_TAG = "RV OnLayout";
    public static final int UNDEFINED_DURATION = Integer.MIN_VALUE;
    public static final int VERTICAL = 1;
    GapWorker.LayoutPrefetchRegistryImpl A;
    final State B;
    boolean C;
    boolean D;
    boolean E;
    RecyclerViewAccessibilityDelegate F;
    final int[] G;
    @VisibleForTesting
    final List H;

    /* renamed from: a  reason: collision with root package name */
    final Recycler f3689a;

    /* renamed from: b  reason: collision with root package name */
    SavedState f3690b;

    /* renamed from: c  reason: collision with root package name */
    AdapterHelper f3691c;

    /* renamed from: d  reason: collision with root package name */
    ChildHelper f3692d;

    /* renamed from: e  reason: collision with root package name */
    final ViewInfoStore f3693e;

    /* renamed from: f  reason: collision with root package name */
    boolean f3694f;

    /* renamed from: g  reason: collision with root package name */
    final Runnable f3695g;

    /* renamed from: h  reason: collision with root package name */
    final Rect f3696h;

    /* renamed from: i  reason: collision with root package name */
    final RectF f3697i;

    /* renamed from: j  reason: collision with root package name */
    Adapter f3698j;
    @VisibleForTesting

    /* renamed from: k  reason: collision with root package name */
    LayoutManager f3699k;

    /* renamed from: l  reason: collision with root package name */
    RecyclerListener f3700l;

    /* renamed from: m  reason: collision with root package name */
    final List f3701m;
    private final AccessibilityManager mAccessibilityManager;
    private EdgeEffect mBottomGlow;
    private ChildDrawingOrderCallback mChildDrawingOrderCallback;
    private int mDispatchScrollCounter;
    private int mEatenAccessibilityChangeFlags;
    @NonNull
    private EdgeEffectFactory mEdgeEffectFactory;
    private boolean mIgnoreMotionEventTillDown;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private int mInterceptRequestLayoutDepth;
    private OnItemTouchListener mInterceptingOnItemTouchListener;
    private ItemAnimator.ItemAnimatorListener mItemAnimatorListener;
    private Runnable mItemAnimatorRunner;
    private int mLastAutoMeasureNonExactMeasuredHeight;
    private int mLastAutoMeasureNonExactMeasuredWidth;
    private boolean mLastAutoMeasureSkippedDueToExact;
    private int mLastTouchX;
    private int mLastTouchY;
    private int mLayoutOrScrollCounter;
    private EdgeEffect mLeftGlow;
    private final int mMaxFlingVelocity;
    private final int mMinFlingVelocity;
    private final int[] mMinMaxLayoutPositions;
    private final int[] mNestedOffsets;
    private final RecyclerViewDataObserver mObserver;
    private List<OnChildAttachStateChangeListener> mOnChildAttachStateListeners;
    private OnFlingListener mOnFlingListener;
    private final ArrayList<OnItemTouchListener> mOnItemTouchListeners;
    private boolean mPreserveFocusAfterLayout;
    private EdgeEffect mRightGlow;
    private float mScaledHorizontalScrollFactor;
    private float mScaledVerticalScrollFactor;
    private OnScrollListener mScrollListener;
    private List<OnScrollListener> mScrollListeners;
    private final int[] mScrollOffset;
    private int mScrollPointerId;
    private int mScrollState;
    private NestedScrollingChildHelper mScrollingChildHelper;
    private final Rect mTempRect2;
    private EdgeEffect mTopGlow;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private final ViewInfoStore.ProcessCallback mViewInfoProcessCallback;

    /* renamed from: n  reason: collision with root package name */
    final ArrayList f3702n;

    /* renamed from: o  reason: collision with root package name */
    boolean f3703o;

    /* renamed from: p  reason: collision with root package name */
    boolean f3704p;

    /* renamed from: q  reason: collision with root package name */
    boolean f3705q;
    @VisibleForTesting

    /* renamed from: r  reason: collision with root package name */
    boolean f3706r;

    /* renamed from: s  reason: collision with root package name */
    boolean f3707s;

    /* renamed from: t  reason: collision with root package name */
    boolean f3708t;
    boolean u;
    boolean v;
    boolean w;
    ItemAnimator x;
    final ViewFlinger y;
    GapWorker z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.recyclerview.widget.RecyclerView$7  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass7 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3714a;

        static {
            int[] iArr = new int[Adapter.StateRestorationPolicy.values().length];
            f3714a = iArr;
            try {
                iArr[Adapter.StateRestorationPolicy.PREVENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3714a[Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Adapter<VH extends ViewHolder> {
        private final AdapterDataObservable mObservable = new AdapterDataObservable();
        private boolean mHasStableIds = false;
        private StateRestorationPolicy mStateRestorationPolicy = StateRestorationPolicy.ALLOW;

        /* loaded from: classes.dex */
        public enum StateRestorationPolicy {
            ALLOW,
            PREVENT_WHEN_EMPTY,
            PREVENT
        }

        public final void bindViewHolder(@NonNull VH vh, int i2) {
            boolean z = vh.mBindingAdapter == null;
            if (z) {
                vh.mPosition = i2;
                if (hasStableIds()) {
                    vh.mItemId = getItemId(i2);
                }
                vh.setFlags(1, 519);
                TraceCompat.beginSection("RV OnBindView");
            }
            vh.mBindingAdapter = this;
            onBindViewHolder(vh, i2, vh.getUnmodifiedPayloads());
            if (z) {
                vh.clearPayload();
                ViewGroup.LayoutParams layoutParams = vh.itemView.getLayoutParams();
                if (layoutParams instanceof LayoutParams) {
                    ((LayoutParams) layoutParams).f3730c = true;
                }
                TraceCompat.endSection();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean canRestoreState() {
            int i2 = AnonymousClass7.f3714a[this.mStateRestorationPolicy.ordinal()];
            if (i2 != 1) {
                return i2 != 2 || getItemCount() > 0;
            }
            return false;
        }

        @NonNull
        public final VH createViewHolder(@NonNull ViewGroup viewGroup, int i2) {
            try {
                TraceCompat.beginSection("RV CreateView");
                VH onCreateViewHolder = onCreateViewHolder(viewGroup, i2);
                if (onCreateViewHolder.itemView.getParent() == null) {
                    onCreateViewHolder.mItemViewType = i2;
                    return onCreateViewHolder;
                }
                throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
            } finally {
                TraceCompat.endSection();
            }
        }

        public int findRelativeAdapterPositionIn(@NonNull Adapter<? extends ViewHolder> adapter, @NonNull ViewHolder viewHolder, int i2) {
            if (adapter == this) {
                return i2;
            }
            return -1;
        }

        public abstract int getItemCount();

        public long getItemId(int i2) {
            return -1L;
        }

        public int getItemViewType(int i2) {
            return 0;
        }

        @NonNull
        public final StateRestorationPolicy getStateRestorationPolicy() {
            return this.mStateRestorationPolicy;
        }

        public final boolean hasObservers() {
            return this.mObservable.hasObservers();
        }

        public final boolean hasStableIds() {
            return this.mHasStableIds;
        }

        public final void notifyDataSetChanged() {
            this.mObservable.notifyChanged();
        }

        public final void notifyItemChanged(int i2) {
            this.mObservable.notifyItemRangeChanged(i2, 1);
        }

        public final void notifyItemChanged(int i2, @Nullable Object obj) {
            this.mObservable.notifyItemRangeChanged(i2, 1, obj);
        }

        public final void notifyItemInserted(int i2) {
            this.mObservable.notifyItemRangeInserted(i2, 1);
        }

        public final void notifyItemMoved(int i2, int i3) {
            this.mObservable.notifyItemMoved(i2, i3);
        }

        public final void notifyItemRangeChanged(int i2, int i3) {
            this.mObservable.notifyItemRangeChanged(i2, i3);
        }

        public final void notifyItemRangeChanged(int i2, int i3, @Nullable Object obj) {
            this.mObservable.notifyItemRangeChanged(i2, i3, obj);
        }

        public final void notifyItemRangeInserted(int i2, int i3) {
            this.mObservable.notifyItemRangeInserted(i2, i3);
        }

        public final void notifyItemRangeRemoved(int i2, int i3) {
            this.mObservable.notifyItemRangeRemoved(i2, i3);
        }

        public final void notifyItemRemoved(int i2) {
            this.mObservable.notifyItemRangeRemoved(i2, 1);
        }

        public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        }

        public abstract void onBindViewHolder(@NonNull VH vh, int i2);

        public void onBindViewHolder(@NonNull VH vh, int i2, @NonNull List<Object> list) {
            onBindViewHolder(vh, i2);
        }

        @NonNull
        public abstract VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2);

        public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        }

        public boolean onFailedToRecycleView(@NonNull VH vh) {
            return false;
        }

        public void onViewAttachedToWindow(@NonNull VH vh) {
        }

        public void onViewDetachedFromWindow(@NonNull VH vh) {
        }

        public void onViewRecycled(@NonNull VH vh) {
        }

        public void registerAdapterDataObserver(@NonNull AdapterDataObserver adapterDataObserver) {
            this.mObservable.registerObserver(adapterDataObserver);
        }

        public void setHasStableIds(boolean z) {
            if (hasObservers()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            this.mHasStableIds = z;
        }

        public void setStateRestorationPolicy(@NonNull StateRestorationPolicy stateRestorationPolicy) {
            this.mStateRestorationPolicy = stateRestorationPolicy;
            this.mObservable.notifyStateRestorationPolicyChanged();
        }

        public void unregisterAdapterDataObserver(@NonNull AdapterDataObserver adapterDataObserver) {
            this.mObservable.unregisterObserver(adapterDataObserver);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class AdapterDataObservable extends Observable<AdapterDataObserver> {
        AdapterDataObservable() {
        }

        public boolean hasObservers() {
            return !((Observable) this).mObservers.isEmpty();
        }

        public void notifyChanged() {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onChanged();
            }
        }

        public void notifyItemMoved(int i2, int i3) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onItemRangeMoved(i2, i3, 1);
            }
        }

        public void notifyItemRangeChanged(int i2, int i3) {
            notifyItemRangeChanged(i2, i3, null);
        }

        public void notifyItemRangeChanged(int i2, int i3, @Nullable Object obj) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onItemRangeChanged(i2, i3, obj);
            }
        }

        public void notifyItemRangeInserted(int i2, int i3) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onItemRangeInserted(i2, i3);
            }
        }

        public void notifyItemRangeRemoved(int i2, int i3) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onItemRangeRemoved(i2, i3);
            }
        }

        public void notifyStateRestorationPolicyChanged() {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onStateRestorationPolicyChanged();
            }
        }
    }

    /* loaded from: classes.dex */
    public static abstract class AdapterDataObserver {
        public void onChanged() {
        }

        public void onItemRangeChanged(int i2, int i3) {
        }

        public void onItemRangeChanged(int i2, int i3, @Nullable Object obj) {
            onItemRangeChanged(i2, i3);
        }

        public void onItemRangeInserted(int i2, int i3) {
        }

        public void onItemRangeMoved(int i2, int i3, int i4) {
        }

        public void onItemRangeRemoved(int i2, int i3) {
        }

        public void onStateRestorationPolicyChanged() {
        }
    }

    /* loaded from: classes.dex */
    public interface ChildDrawingOrderCallback {
        int onGetChildDrawingOrder(int i2, int i3);
    }

    /* loaded from: classes.dex */
    public static class EdgeEffectFactory {
        public static final int DIRECTION_BOTTOM = 3;
        public static final int DIRECTION_LEFT = 0;
        public static final int DIRECTION_RIGHT = 2;
        public static final int DIRECTION_TOP = 1;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes.dex */
        public @interface EdgeDirection {
        }

        @NonNull
        protected EdgeEffect a(@NonNull RecyclerView recyclerView, int i2) {
            return new EdgeEffect(recyclerView.getContext());
        }
    }

    /* loaded from: classes.dex */
    public static abstract class ItemAnimator {
        public static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        public static final int FLAG_CHANGED = 2;
        public static final int FLAG_INVALIDATED = 4;
        public static final int FLAG_MOVED = 2048;
        public static final int FLAG_REMOVED = 8;
        private ItemAnimatorListener mListener = null;
        private ArrayList<ItemAnimatorFinishedListener> mFinishedListeners = new ArrayList<>();
        private long mAddDuration = 120;
        private long mRemoveDuration = 120;
        private long mMoveDuration = 250;
        private long mChangeDuration = 250;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes.dex */
        public @interface AdapterChanges {
        }

        /* loaded from: classes.dex */
        public interface ItemAnimatorFinishedListener {
            void onAnimationsFinished();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public interface ItemAnimatorListener {
            void onAnimationFinished(@NonNull ViewHolder viewHolder);
        }

        /* loaded from: classes.dex */
        public static class ItemHolderInfo {
            public int bottom;
            public int changeFlags;
            public int left;
            public int right;
            public int top;

            @NonNull
            public ItemHolderInfo setFrom(@NonNull ViewHolder viewHolder) {
                return setFrom(viewHolder, 0);
            }

            @NonNull
            public ItemHolderInfo setFrom(@NonNull ViewHolder viewHolder, int i2) {
                View view = viewHolder.itemView;
                this.left = view.getLeft();
                this.top = view.getTop();
                this.right = view.getRight();
                this.bottom = view.getBottom();
                return this;
            }
        }

        static int buildAdapterChangeFlagsForAnimations(ViewHolder viewHolder) {
            int i2 = viewHolder.mFlags & 14;
            if (viewHolder.isInvalid()) {
                return 4;
            }
            if ((i2 & 4) == 0) {
                int oldPosition = viewHolder.getOldPosition();
                int absoluteAdapterPosition = viewHolder.getAbsoluteAdapterPosition();
                return (oldPosition == -1 || absoluteAdapterPosition == -1 || oldPosition == absoluteAdapterPosition) ? i2 : i2 | 2048;
            }
            return i2;
        }

        public abstract boolean animateAppearance(@NonNull ViewHolder viewHolder, @Nullable ItemHolderInfo itemHolderInfo, @NonNull ItemHolderInfo itemHolderInfo2);

        public abstract boolean animateChange(@NonNull ViewHolder viewHolder, @NonNull ViewHolder viewHolder2, @NonNull ItemHolderInfo itemHolderInfo, @NonNull ItemHolderInfo itemHolderInfo2);

        public abstract boolean animateDisappearance(@NonNull ViewHolder viewHolder, @NonNull ItemHolderInfo itemHolderInfo, @Nullable ItemHolderInfo itemHolderInfo2);

        public abstract boolean animatePersistence(@NonNull ViewHolder viewHolder, @NonNull ItemHolderInfo itemHolderInfo, @NonNull ItemHolderInfo itemHolderInfo2);

        public boolean canReuseUpdatedViewHolder(@NonNull ViewHolder viewHolder) {
            return true;
        }

        public boolean canReuseUpdatedViewHolder(@NonNull ViewHolder viewHolder, @NonNull List<Object> list) {
            return canReuseUpdatedViewHolder(viewHolder);
        }

        public final void dispatchAnimationFinished(@NonNull ViewHolder viewHolder) {
            onAnimationFinished(viewHolder);
            ItemAnimatorListener itemAnimatorListener = this.mListener;
            if (itemAnimatorListener != null) {
                itemAnimatorListener.onAnimationFinished(viewHolder);
            }
        }

        public final void dispatchAnimationStarted(@NonNull ViewHolder viewHolder) {
            onAnimationStarted(viewHolder);
        }

        public final void dispatchAnimationsFinished() {
            int size = this.mFinishedListeners.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.mFinishedListeners.get(i2).onAnimationsFinished();
            }
            this.mFinishedListeners.clear();
        }

        public abstract void endAnimation(@NonNull ViewHolder viewHolder);

        public abstract void endAnimations();

        public long getAddDuration() {
            return this.mAddDuration;
        }

        public long getChangeDuration() {
            return this.mChangeDuration;
        }

        public long getMoveDuration() {
            return this.mMoveDuration;
        }

        public long getRemoveDuration() {
            return this.mRemoveDuration;
        }

        public abstract boolean isRunning();

        public final boolean isRunning(@Nullable ItemAnimatorFinishedListener itemAnimatorFinishedListener) {
            boolean isRunning = isRunning();
            if (itemAnimatorFinishedListener != null) {
                if (isRunning) {
                    this.mFinishedListeners.add(itemAnimatorFinishedListener);
                } else {
                    itemAnimatorFinishedListener.onAnimationsFinished();
                }
            }
            return isRunning;
        }

        @NonNull
        public ItemHolderInfo obtainHolderInfo() {
            return new ItemHolderInfo();
        }

        public void onAnimationFinished(@NonNull ViewHolder viewHolder) {
        }

        public void onAnimationStarted(@NonNull ViewHolder viewHolder) {
        }

        @NonNull
        public ItemHolderInfo recordPostLayoutInformation(@NonNull State state, @NonNull ViewHolder viewHolder) {
            return obtainHolderInfo().setFrom(viewHolder);
        }

        @NonNull
        public ItemHolderInfo recordPreLayoutInformation(@NonNull State state, @NonNull ViewHolder viewHolder, int i2, @NonNull List<Object> list) {
            return obtainHolderInfo().setFrom(viewHolder);
        }

        public abstract void runPendingAnimations();

        public void setAddDuration(long j2) {
            this.mAddDuration = j2;
        }

        public void setChangeDuration(long j2) {
            this.mChangeDuration = j2;
        }

        void setListener(ItemAnimatorListener itemAnimatorListener) {
            this.mListener = itemAnimatorListener;
        }

        public void setMoveDuration(long j2) {
            this.mMoveDuration = j2;
        }

        public void setRemoveDuration(long j2) {
            this.mRemoveDuration = j2;
        }
    }

    /* loaded from: classes.dex */
    private class ItemAnimatorRestoreListener implements ItemAnimator.ItemAnimatorListener {
        ItemAnimatorRestoreListener() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator.ItemAnimatorListener
        public void onAnimationFinished(ViewHolder viewHolder) {
            viewHolder.setIsRecyclable(true);
            if (viewHolder.mShadowedHolder != null && viewHolder.mShadowingHolder == null) {
                viewHolder.mShadowedHolder = null;
            }
            viewHolder.mShadowingHolder = null;
            if (viewHolder.shouldBeKeptAsChild() || RecyclerView.this.Z(viewHolder.itemView) || !viewHolder.isTmpDetached()) {
                return;
            }
            RecyclerView.this.removeDetachedView(viewHolder.itemView, false);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class ItemDecoration {
        @Deprecated
        public void getItemOffsets(@NonNull Rect rect, int i2, @NonNull RecyclerView recyclerView) {
            rect.set(0, 0, 0, 0);
        }

        public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull State state) {
            getItemOffsets(rect, ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition(), recyclerView);
        }

        @Deprecated
        public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView) {
        }

        public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull State state) {
            onDraw(canvas, recyclerView);
        }

        @Deprecated
        public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView) {
        }

        public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull State state) {
            onDrawOver(canvas, recyclerView);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class LayoutManager {

        /* renamed from: a  reason: collision with root package name */
        ChildHelper f3716a;

        /* renamed from: b  reason: collision with root package name */
        RecyclerView f3717b;

        /* renamed from: c  reason: collision with root package name */
        ViewBoundsCheck f3718c;

        /* renamed from: d  reason: collision with root package name */
        ViewBoundsCheck f3719d;
        @Nullable

        /* renamed from: e  reason: collision with root package name */
        SmoothScroller f3720e;

        /* renamed from: f  reason: collision with root package name */
        boolean f3721f;

        /* renamed from: g  reason: collision with root package name */
        boolean f3722g;

        /* renamed from: h  reason: collision with root package name */
        boolean f3723h;

        /* renamed from: i  reason: collision with root package name */
        int f3724i;

        /* renamed from: j  reason: collision with root package name */
        boolean f3725j;
        private int mHeight;
        private int mHeightMode;
        private final ViewBoundsCheck.Callback mHorizontalBoundCheckCallback;
        private boolean mItemPrefetchEnabled;
        private boolean mMeasurementCacheEnabled;
        private final ViewBoundsCheck.Callback mVerticalBoundCheckCallback;
        private int mWidth;
        private int mWidthMode;

        /* loaded from: classes.dex */
        public interface LayoutPrefetchRegistry {
            void addPosition(int i2, int i3);
        }

        /* loaded from: classes.dex */
        public static class Properties {
            public int orientation;
            public boolean reverseLayout;
            public int spanCount;
            public boolean stackFromEnd;
        }

        public LayoutManager() {
            ViewBoundsCheck.Callback callback = new ViewBoundsCheck.Callback() { // from class: androidx.recyclerview.widget.RecyclerView.LayoutManager.1
                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public View getChildAt(int i2) {
                    return LayoutManager.this.getChildAt(i2);
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int getChildEnd(View view) {
                    return LayoutManager.this.getDecoratedRight(view) + ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).rightMargin;
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int getChildStart(View view) {
                    return LayoutManager.this.getDecoratedLeft(view) - ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).leftMargin;
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int getParentEnd() {
                    return LayoutManager.this.getWidth() - LayoutManager.this.getPaddingRight();
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int getParentStart() {
                    return LayoutManager.this.getPaddingLeft();
                }
            };
            this.mHorizontalBoundCheckCallback = callback;
            ViewBoundsCheck.Callback callback2 = new ViewBoundsCheck.Callback() { // from class: androidx.recyclerview.widget.RecyclerView.LayoutManager.2
                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public View getChildAt(int i2) {
                    return LayoutManager.this.getChildAt(i2);
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int getChildEnd(View view) {
                    return LayoutManager.this.getDecoratedBottom(view) + ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).bottomMargin;
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int getChildStart(View view) {
                    return LayoutManager.this.getDecoratedTop(view) - ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).topMargin;
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int getParentEnd() {
                    return LayoutManager.this.getHeight() - LayoutManager.this.getPaddingBottom();
                }

                @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
                public int getParentStart() {
                    return LayoutManager.this.getPaddingTop();
                }
            };
            this.mVerticalBoundCheckCallback = callback2;
            this.f3718c = new ViewBoundsCheck(callback);
            this.f3719d = new ViewBoundsCheck(callback2);
            this.f3721f = false;
            this.f3722g = false;
            this.f3723h = false;
            this.mMeasurementCacheEnabled = true;
            this.mItemPrefetchEnabled = true;
        }

        private void addViewInt(View view, int i2, boolean z) {
            ViewHolder F = RecyclerView.F(view);
            if (z || F.isRemoved()) {
                this.f3717b.f3693e.b(F);
            } else {
                this.f3717b.f3693e.n(F);
            }
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (F.wasReturnedFromScrap() || F.isScrap()) {
                if (F.isScrap()) {
                    F.unScrap();
                } else {
                    F.clearReturnedFromScrapFlag();
                }
                this.f3716a.c(view, i2, view.getLayoutParams(), false);
            } else if (view.getParent() == this.f3717b) {
                int k2 = this.f3716a.k(view);
                if (i2 == -1) {
                    i2 = this.f3716a.g();
                }
                if (k2 == -1) {
                    throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.f3717b.indexOfChild(view) + this.f3717b.z());
                } else if (k2 != i2) {
                    this.f3717b.f3699k.moveView(k2, i2);
                }
            } else {
                this.f3716a.a(view, i2, false);
                layoutParams.f3730c = true;
                SmoothScroller smoothScroller = this.f3720e;
                if (smoothScroller != null && smoothScroller.isRunning()) {
                    this.f3720e.c(view);
                }
            }
            if (layoutParams.f3731d) {
                F.itemView.invalidate();
                layoutParams.f3731d = false;
            }
        }

        public static int chooseSize(int i2, int i3, int i4) {
            int mode = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i3, i4) : size : Math.min(size, Math.max(i3, i4));
        }

        private void detachViewInternal(int i2, @NonNull View view) {
            this.f3716a.d(i2);
        }

        /* JADX WARN: Code restructure failed: missing block: B:9:0x0017, code lost:
            if (r5 == 1073741824) goto L8;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static int getChildMeasureSpec(int i2, int i3, int i4, int i5, boolean z) {
            int max = Math.max(0, i2 - i4);
            if (z) {
                if (i5 < 0) {
                    if (i5 == -1) {
                        if (i3 != Integer.MIN_VALUE) {
                            if (i3 != 0) {
                            }
                        }
                        i5 = max;
                    }
                    i3 = 0;
                    i5 = 0;
                }
                i3 = 1073741824;
            } else {
                if (i5 < 0) {
                    if (i5 != -1) {
                        if (i5 == -2) {
                            if (i3 == Integer.MIN_VALUE || i3 == 1073741824) {
                                i5 = max;
                                i3 = Integer.MIN_VALUE;
                            } else {
                                i5 = max;
                                i3 = 0;
                            }
                        }
                        i3 = 0;
                        i5 = 0;
                    }
                    i5 = max;
                }
                i3 = 1073741824;
            }
            return View.MeasureSpec.makeMeasureSpec(i5, i3);
        }

        /* JADX WARN: Code restructure failed: missing block: B:4:0x000a, code lost:
            if (r3 >= 0) goto L8;
         */
        @Deprecated
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static int getChildMeasureSpec(int i2, int i3, int i4, boolean z) {
            int i5 = i2 - i3;
            int i6 = 0;
            int max = Math.max(0, i5);
            if (!z) {
                if (i4 < 0) {
                    if (i4 == -1) {
                        i4 = max;
                    } else {
                        if (i4 == -2) {
                            i6 = Integer.MIN_VALUE;
                            i4 = max;
                        }
                        i4 = 0;
                    }
                }
                i6 = 1073741824;
            }
            return View.MeasureSpec.makeMeasureSpec(i4, i6);
        }

        private int[] getChildRectangleOnScreenScrollAmount(View view, Rect rect) {
            int[] iArr = new int[2];
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int width = getWidth() - getPaddingRight();
            int height = getHeight() - getPaddingBottom();
            int left = (view.getLeft() + rect.left) - view.getScrollX();
            int top = (view.getTop() + rect.top) - view.getScrollY();
            int width2 = rect.width() + left;
            int height2 = rect.height() + top;
            int i2 = left - paddingLeft;
            int min = Math.min(0, i2);
            int i3 = top - paddingTop;
            int min2 = Math.min(0, i3);
            int i4 = width2 - width;
            int max = Math.max(0, i4);
            int max2 = Math.max(0, height2 - height);
            if (getLayoutDirection() != 1) {
                if (min == 0) {
                    min = Math.min(i2, max);
                }
                max = min;
            } else if (max == 0) {
                max = Math.max(min, i4);
            }
            if (min2 == 0) {
                min2 = Math.min(i3, max2);
            }
            iArr[0] = max;
            iArr[1] = min2;
            return iArr;
        }

        public static Properties getProperties(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
            Properties properties = new Properties();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RecyclerView, i2, i3);
            properties.orientation = obtainStyledAttributes.getInt(R.styleable.RecyclerView_android_orientation, 1);
            properties.spanCount = obtainStyledAttributes.getInt(R.styleable.RecyclerView_spanCount, 1);
            properties.reverseLayout = obtainStyledAttributes.getBoolean(R.styleable.RecyclerView_reverseLayout, false);
            properties.stackFromEnd = obtainStyledAttributes.getBoolean(R.styleable.RecyclerView_stackFromEnd, false);
            obtainStyledAttributes.recycle();
            return properties;
        }

        private boolean isFocusedChildVisibleAfterScrolling(RecyclerView recyclerView, int i2, int i3) {
            View focusedChild = recyclerView.getFocusedChild();
            if (focusedChild == null) {
                return false;
            }
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int width = getWidth() - getPaddingRight();
            int height = getHeight() - getPaddingBottom();
            Rect rect = this.f3717b.f3696h;
            getDecoratedBoundsWithMargins(focusedChild, rect);
            return rect.left - i2 < width && rect.right - i2 > paddingLeft && rect.top - i3 < height && rect.bottom - i3 > paddingTop;
        }

        private static boolean isMeasurementUpToDate(int i2, int i3, int i4) {
            int mode = View.MeasureSpec.getMode(i3);
            int size = View.MeasureSpec.getSize(i3);
            if (i4 <= 0 || i2 == i4) {
                if (mode == Integer.MIN_VALUE) {
                    return size >= i2;
                } else if (mode != 0) {
                    return mode == 1073741824 && size == i2;
                } else {
                    return true;
                }
            }
            return false;
        }

        private void scrapOrRecycleView(Recycler recycler, int i2, View view) {
            ViewHolder F = RecyclerView.F(view);
            if (F.shouldIgnore()) {
                return;
            }
            if (F.isInvalid() && !F.isRemoved() && !this.f3717b.f3698j.hasStableIds()) {
                removeViewAt(i2);
                recycler.u(F);
                return;
            }
            detachViewAt(i2);
            recycler.v(view);
            this.f3717b.f3693e.onViewDetached(F);
        }

        void a(RecyclerView recyclerView) {
            this.f3722g = true;
            onAttachedToWindow(recyclerView);
        }

        public void addDisappearingView(View view) {
            addDisappearingView(view, -1);
        }

        public void addDisappearingView(View view, int i2) {
            addViewInt(view, i2, true);
        }

        public void addView(View view) {
            addView(view, -1);
        }

        public void addView(View view, int i2) {
            addViewInt(view, i2, false);
        }

        public void assertInLayoutOrScroll(String str) {
            RecyclerView recyclerView = this.f3717b;
            if (recyclerView != null) {
                recyclerView.h(str);
            }
        }

        public void assertNotInLayoutOrScroll(String str) {
            RecyclerView recyclerView = this.f3717b;
            if (recyclerView != null) {
                recyclerView.i(str);
            }
        }

        public void attachView(@NonNull View view) {
            attachView(view, -1);
        }

        public void attachView(@NonNull View view, int i2) {
            attachView(view, i2, (LayoutParams) view.getLayoutParams());
        }

        public void attachView(@NonNull View view, int i2, LayoutParams layoutParams) {
            ViewHolder F = RecyclerView.F(view);
            if (F.isRemoved()) {
                this.f3717b.f3693e.b(F);
            } else {
                this.f3717b.f3693e.n(F);
            }
            this.f3716a.c(view, i2, layoutParams, F.isRemoved());
        }

        void b(RecyclerView recyclerView, Recycler recycler) {
            this.f3722g = false;
            onDetachedFromWindow(recyclerView, recycler);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean c() {
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                ViewGroup.LayoutParams layoutParams = getChildAt(i2).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
            return false;
        }

        public void calculateItemDecorationsForChild(@NonNull View view, @NonNull Rect rect) {
            RecyclerView recyclerView = this.f3717b;
            if (recyclerView == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(recyclerView.H(view));
            }
        }

        public boolean canScrollHorizontally() {
            return false;
        }

        public boolean canScrollVertically() {
            return false;
        }

        public boolean checkLayoutParams(LayoutParams layoutParams) {
            return layoutParams != null;
        }

        public void collectAdjacentPrefetchPositions(int i2, int i3, State state, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        }

        public void collectInitialPrefetchPositions(int i2, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        }

        public int computeHorizontalScrollExtent(@NonNull State state) {
            return 0;
        }

        public int computeHorizontalScrollOffset(@NonNull State state) {
            return 0;
        }

        public int computeHorizontalScrollRange(@NonNull State state) {
            return 0;
        }

        public int computeVerticalScrollExtent(@NonNull State state) {
            return 0;
        }

        public int computeVerticalScrollOffset(@NonNull State state) {
            return 0;
        }

        public int computeVerticalScrollRange(@NonNull State state) {
            return 0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void d(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            RecyclerView recyclerView = this.f3717b;
            onInitializeAccessibilityNodeInfo(recyclerView.f3689a, recyclerView.B, accessibilityNodeInfoCompat);
        }

        public void detachAndScrapAttachedViews(@NonNull Recycler recycler) {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                scrapOrRecycleView(recycler, childCount, getChildAt(childCount));
            }
        }

        public void detachAndScrapView(@NonNull View view, @NonNull Recycler recycler) {
            scrapOrRecycleView(recycler, this.f3716a.k(view), view);
        }

        public void detachAndScrapViewAt(int i2, @NonNull Recycler recycler) {
            scrapOrRecycleView(recycler, i2, getChildAt(i2));
        }

        public void detachView(@NonNull View view) {
            int k2 = this.f3716a.k(view);
            if (k2 >= 0) {
                detachViewInternal(k2, view);
            }
        }

        public void detachViewAt(int i2) {
            detachViewInternal(i2, getChildAt(i2));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void e(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            ViewHolder F = RecyclerView.F(view);
            if (F == null || F.isRemoved() || this.f3716a.l(F.itemView)) {
                return;
            }
            RecyclerView recyclerView = this.f3717b;
            onInitializeAccessibilityNodeInfoForItem(recyclerView.f3689a, recyclerView.B, view, accessibilityNodeInfoCompat);
        }

        public void endAnimation(View view) {
            ItemAnimator itemAnimator = this.f3717b.x;
            if (itemAnimator != null) {
                itemAnimator.endAnimation(RecyclerView.F(view));
            }
        }

        void f(SmoothScroller smoothScroller) {
            if (this.f3720e == smoothScroller) {
                this.f3720e = null;
            }
        }

        @Nullable
        public View findContainingItemView(@NonNull View view) {
            View findContainingItemView;
            RecyclerView recyclerView = this.f3717b;
            if (recyclerView == null || (findContainingItemView = recyclerView.findContainingItemView(view)) == null || this.f3716a.l(findContainingItemView)) {
                return null;
            }
            return findContainingItemView;
        }

        @Nullable
        public View findViewByPosition(int i2) {
            int childCount = getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                ViewHolder F = RecyclerView.F(childAt);
                if (F != null && F.getLayoutPosition() == i2 && !F.shouldIgnore() && (this.f3717b.B.isPreLayout() || !F.isRemoved())) {
                    return childAt;
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean g(int i2, @Nullable Bundle bundle) {
            RecyclerView recyclerView = this.f3717b;
            return performAccessibilityAction(recyclerView.f3689a, recyclerView.B, i2, bundle);
        }

        public abstract LayoutParams generateDefaultLayoutParams();

        public LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
            return new LayoutParams(context, attributeSet);
        }

        public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
            return layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
        }

        public int getBaseline() {
            return -1;
        }

        public int getBottomDecorationHeight(@NonNull View view) {
            return ((LayoutParams) view.getLayoutParams()).f3729b.bottom;
        }

        @Nullable
        public View getChildAt(int i2) {
            ChildHelper childHelper = this.f3716a;
            if (childHelper != null) {
                return childHelper.f(i2);
            }
            return null;
        }

        public int getChildCount() {
            ChildHelper childHelper = this.f3716a;
            if (childHelper != null) {
                return childHelper.g();
            }
            return 0;
        }

        public boolean getClipToPadding() {
            RecyclerView recyclerView = this.f3717b;
            return recyclerView != null && recyclerView.f3694f;
        }

        public int getColumnCountForAccessibility(@NonNull Recycler recycler, @NonNull State state) {
            return -1;
        }

        public int getDecoratedBottom(@NonNull View view) {
            return view.getBottom() + getBottomDecorationHeight(view);
        }

        public void getDecoratedBoundsWithMargins(@NonNull View view, @NonNull Rect rect) {
            RecyclerView.G(view, rect);
        }

        public int getDecoratedLeft(@NonNull View view) {
            return view.getLeft() - getLeftDecorationWidth(view);
        }

        public int getDecoratedMeasuredHeight(@NonNull View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).f3729b;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        public int getDecoratedMeasuredWidth(@NonNull View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).f3729b;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        public int getDecoratedRight(@NonNull View view) {
            return view.getRight() + getRightDecorationWidth(view);
        }

        public int getDecoratedTop(@NonNull View view) {
            return view.getTop() - getTopDecorationHeight(view);
        }

        @Nullable
        public View getFocusedChild() {
            View focusedChild;
            RecyclerView recyclerView = this.f3717b;
            if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.f3716a.l(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        @Px
        public int getHeight() {
            return this.mHeight;
        }

        public int getHeightMode() {
            return this.mHeightMode;
        }

        public int getItemCount() {
            RecyclerView recyclerView = this.f3717b;
            Adapter adapter = recyclerView != null ? recyclerView.getAdapter() : null;
            if (adapter != null) {
                return adapter.getItemCount();
            }
            return 0;
        }

        public int getItemViewType(@NonNull View view) {
            return RecyclerView.F(view).getItemViewType();
        }

        public int getLayoutDirection() {
            return ViewCompat.getLayoutDirection(this.f3717b);
        }

        public int getLeftDecorationWidth(@NonNull View view) {
            return ((LayoutParams) view.getLayoutParams()).f3729b.left;
        }

        @Px
        public int getMinimumHeight() {
            return ViewCompat.getMinimumHeight(this.f3717b);
        }

        @Px
        public int getMinimumWidth() {
            return ViewCompat.getMinimumWidth(this.f3717b);
        }

        @Px
        public int getPaddingBottom() {
            RecyclerView recyclerView = this.f3717b;
            if (recyclerView != null) {
                return recyclerView.getPaddingBottom();
            }
            return 0;
        }

        @Px
        public int getPaddingEnd() {
            RecyclerView recyclerView = this.f3717b;
            if (recyclerView != null) {
                return ViewCompat.getPaddingEnd(recyclerView);
            }
            return 0;
        }

        @Px
        public int getPaddingLeft() {
            RecyclerView recyclerView = this.f3717b;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        @Px
        public int getPaddingRight() {
            RecyclerView recyclerView = this.f3717b;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        @Px
        public int getPaddingStart() {
            RecyclerView recyclerView = this.f3717b;
            if (recyclerView != null) {
                return ViewCompat.getPaddingStart(recyclerView);
            }
            return 0;
        }

        @Px
        public int getPaddingTop() {
            RecyclerView recyclerView = this.f3717b;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        public int getPosition(@NonNull View view) {
            return ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        }

        public int getRightDecorationWidth(@NonNull View view) {
            return ((LayoutParams) view.getLayoutParams()).f3729b.right;
        }

        public int getRowCountForAccessibility(@NonNull Recycler recycler, @NonNull State state) {
            return -1;
        }

        public int getSelectionModeForAccessibility(@NonNull Recycler recycler, @NonNull State state) {
            return 0;
        }

        public int getTopDecorationHeight(@NonNull View view) {
            return ((LayoutParams) view.getLayoutParams()).f3729b.top;
        }

        public void getTransformedBoundingBox(@NonNull View view, boolean z, @NonNull Rect rect) {
            Matrix matrix;
            if (z) {
                Rect rect2 = ((LayoutParams) view.getLayoutParams()).f3729b;
                rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            } else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (this.f3717b != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
                RectF rectF = this.f3717b.f3697i;
                rectF.set(rect);
                matrix.mapRect(rectF);
                rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        @Px
        public int getWidth() {
            return this.mWidth;
        }

        public int getWidthMode() {
            return this.mWidthMode;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean h(@NonNull View view, int i2, @Nullable Bundle bundle) {
            RecyclerView recyclerView = this.f3717b;
            return performAccessibilityActionForItem(recyclerView.f3689a, recyclerView.B, view, i2, bundle);
        }

        public boolean hasFocus() {
            RecyclerView recyclerView = this.f3717b;
            return recyclerView != null && recyclerView.hasFocus();
        }

        void i(Recycler recycler) {
            int g2 = recycler.g();
            for (int i2 = g2 - 1; i2 >= 0; i2--) {
                View j2 = recycler.j(i2);
                ViewHolder F = RecyclerView.F(j2);
                if (!F.shouldIgnore()) {
                    F.setIsRecyclable(false);
                    if (F.isTmpDetached()) {
                        this.f3717b.removeDetachedView(j2, false);
                    }
                    ItemAnimator itemAnimator = this.f3717b.x;
                    if (itemAnimator != null) {
                        itemAnimator.endAnimation(F);
                    }
                    F.setIsRecyclable(true);
                    recycler.r(j2);
                }
            }
            recycler.c();
            if (g2 > 0) {
                this.f3717b.invalidate();
            }
        }

        public void ignoreView(@NonNull View view) {
            ViewParent parent = view.getParent();
            RecyclerView recyclerView = this.f3717b;
            if (parent != recyclerView || recyclerView.indexOfChild(view) == -1) {
                throw new IllegalArgumentException("View should be fully attached to be ignored" + this.f3717b.z());
            }
            ViewHolder F = RecyclerView.F(view);
            F.addFlags(128);
            this.f3717b.f3693e.o(F);
        }

        public boolean isAttachedToWindow() {
            return this.f3722g;
        }

        public boolean isAutoMeasureEnabled() {
            return this.f3723h;
        }

        public boolean isFocused() {
            RecyclerView recyclerView = this.f3717b;
            return recyclerView != null && recyclerView.isFocused();
        }

        public final boolean isItemPrefetchEnabled() {
            return this.mItemPrefetchEnabled;
        }

        public boolean isLayoutHierarchical(@NonNull Recycler recycler, @NonNull State state) {
            return false;
        }

        public boolean isMeasurementCacheEnabled() {
            return this.mMeasurementCacheEnabled;
        }

        public boolean isSmoothScrolling() {
            SmoothScroller smoothScroller = this.f3720e;
            return smoothScroller != null && smoothScroller.isRunning();
        }

        public boolean isViewPartiallyVisible(@NonNull View view, boolean z, boolean z2) {
            boolean z3 = this.f3718c.b(view, 24579) && this.f3719d.b(view, 24579);
            return z ? z3 : !z3;
        }

        void j(RecyclerView recyclerView) {
            k(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
        }

        void k(int i2, int i3) {
            this.mWidth = View.MeasureSpec.getSize(i2);
            int mode = View.MeasureSpec.getMode(i2);
            this.mWidthMode = mode;
            if (mode == 0 && !RecyclerView.J) {
                this.mWidth = 0;
            }
            this.mHeight = View.MeasureSpec.getSize(i3);
            int mode2 = View.MeasureSpec.getMode(i3);
            this.mHeightMode = mode2;
            if (mode2 != 0 || RecyclerView.J) {
                return;
            }
            this.mHeight = 0;
        }

        void l(int i2, int i3) {
            int childCount = getChildCount();
            if (childCount == 0) {
                this.f3717b.o(i2, i3);
                return;
            }
            int i4 = Integer.MIN_VALUE;
            int i5 = Integer.MAX_VALUE;
            int i6 = Integer.MAX_VALUE;
            int i7 = Integer.MIN_VALUE;
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = getChildAt(i8);
                Rect rect = this.f3717b.f3696h;
                getDecoratedBoundsWithMargins(childAt, rect);
                int i9 = rect.left;
                if (i9 < i5) {
                    i5 = i9;
                }
                int i10 = rect.right;
                if (i10 > i4) {
                    i4 = i10;
                }
                int i11 = rect.top;
                if (i11 < i6) {
                    i6 = i11;
                }
                int i12 = rect.bottom;
                if (i12 > i7) {
                    i7 = i12;
                }
            }
            this.f3717b.f3696h.set(i5, i6, i4, i7);
            setMeasuredDimension(this.f3717b.f3696h, i2, i3);
        }

        public void layoutDecorated(@NonNull View view, int i2, int i3, int i4, int i5) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).f3729b;
            view.layout(i2 + rect.left, i3 + rect.top, i4 - rect.right, i5 - rect.bottom);
        }

        public void layoutDecoratedWithMargins(@NonNull View view, int i2, int i3, int i4, int i5) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect rect = layoutParams.f3729b;
            view.layout(i2 + rect.left + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, i3 + rect.top + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, (i4 - rect.right) - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, (i5 - rect.bottom) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
        }

        void m(RecyclerView recyclerView) {
            int height;
            if (recyclerView == null) {
                this.f3717b = null;
                this.f3716a = null;
                height = 0;
                this.mWidth = 0;
            } else {
                this.f3717b = recyclerView;
                this.f3716a = recyclerView.f3692d;
                this.mWidth = recyclerView.getWidth();
                height = recyclerView.getHeight();
            }
            this.mHeight = height;
            this.mWidthMode = 1073741824;
            this.mHeightMode = 1073741824;
        }

        public void measureChild(@NonNull View view, int i2, int i3) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect H = this.f3717b.H(view);
            int i4 = i2 + H.left + H.right;
            int i5 = i3 + H.top + H.bottom;
            int childMeasureSpec = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight() + i4, ((ViewGroup.MarginLayoutParams) layoutParams).width, canScrollHorizontally());
            int childMeasureSpec2 = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom() + i5, ((ViewGroup.MarginLayoutParams) layoutParams).height, canScrollVertically());
            if (n(view, childMeasureSpec, childMeasureSpec2, layoutParams)) {
                view.measure(childMeasureSpec, childMeasureSpec2);
            }
        }

        public void measureChildWithMargins(@NonNull View view, int i2, int i3) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect H = this.f3717b.H(view);
            int i4 = i2 + H.left + H.right;
            int i5 = i3 + H.top + H.bottom;
            int childMeasureSpec = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + i4, ((ViewGroup.MarginLayoutParams) layoutParams).width, canScrollHorizontally());
            int childMeasureSpec2 = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin + i5, ((ViewGroup.MarginLayoutParams) layoutParams).height, canScrollVertically());
            if (n(view, childMeasureSpec, childMeasureSpec2, layoutParams)) {
                view.measure(childMeasureSpec, childMeasureSpec2);
            }
        }

        public void moveView(int i2, int i3) {
            View childAt = getChildAt(i2);
            if (childAt != null) {
                detachViewAt(i2);
                attachView(childAt, i3);
                return;
            }
            throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i2 + this.f3717b.toString());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean n(View view, int i2, int i3, LayoutParams layoutParams) {
            return (!view.isLayoutRequested() && this.mMeasurementCacheEnabled && isMeasurementUpToDate(view.getWidth(), i2, ((ViewGroup.MarginLayoutParams) layoutParams).width) && isMeasurementUpToDate(view.getHeight(), i3, ((ViewGroup.MarginLayoutParams) layoutParams).height)) ? false : true;
        }

        boolean o() {
            return false;
        }

        public void offsetChildrenHorizontal(@Px int i2) {
            RecyclerView recyclerView = this.f3717b;
            if (recyclerView != null) {
                recyclerView.offsetChildrenHorizontal(i2);
            }
        }

        public void offsetChildrenVertical(@Px int i2) {
            RecyclerView recyclerView = this.f3717b;
            if (recyclerView != null) {
                recyclerView.offsetChildrenVertical(i2);
            }
        }

        public void onAdapterChanged(@Nullable Adapter adapter, @Nullable Adapter adapter2) {
        }

        public boolean onAddFocusables(@NonNull RecyclerView recyclerView, @NonNull ArrayList<View> arrayList, int i2, int i3) {
            return false;
        }

        @CallSuper
        public void onAttachedToWindow(RecyclerView recyclerView) {
        }

        @Deprecated
        public void onDetachedFromWindow(RecyclerView recyclerView) {
        }

        @CallSuper
        public void onDetachedFromWindow(RecyclerView recyclerView, Recycler recycler) {
            onDetachedFromWindow(recyclerView);
        }

        @Nullable
        public View onFocusSearchFailed(@NonNull View view, int i2, @NonNull Recycler recycler, @NonNull State state) {
            return null;
        }

        public void onInitializeAccessibilityEvent(@NonNull AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f3717b;
            onInitializeAccessibilityEvent(recyclerView.f3689a, recyclerView.B, accessibilityEvent);
        }

        public void onInitializeAccessibilityEvent(@NonNull Recycler recycler, @NonNull State state, @NonNull AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f3717b;
            if (recyclerView == null || accessibilityEvent == null) {
                return;
            }
            boolean z = true;
            if (!recyclerView.canScrollVertically(1) && !this.f3717b.canScrollVertically(-1) && !this.f3717b.canScrollHorizontally(-1) && !this.f3717b.canScrollHorizontally(1)) {
                z = false;
            }
            accessibilityEvent.setScrollable(z);
            Adapter adapter = this.f3717b.f3698j;
            if (adapter != null) {
                accessibilityEvent.setItemCount(adapter.getItemCount());
            }
        }

        public void onInitializeAccessibilityNodeInfo(@NonNull Recycler recycler, @NonNull State state, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (this.f3717b.canScrollVertically(-1) || this.f3717b.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.addAction(8192);
                accessibilityNodeInfoCompat.setScrollable(true);
            }
            if (this.f3717b.canScrollVertically(1) || this.f3717b.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.addAction(4096);
                accessibilityNodeInfoCompat.setScrollable(true);
            }
            accessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(getRowCountForAccessibility(recycler, state), getColumnCountForAccessibility(recycler, state), isLayoutHierarchical(recycler, state), getSelectionModeForAccessibility(recycler, state)));
        }

        public void onInitializeAccessibilityNodeInfoForItem(@NonNull Recycler recycler, @NonNull State state, @NonNull View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        }

        @Nullable
        public View onInterceptFocusSearch(@NonNull View view, int i2) {
            return null;
        }

        public void onItemsAdded(@NonNull RecyclerView recyclerView, int i2, int i3) {
        }

        public void onItemsChanged(@NonNull RecyclerView recyclerView) {
        }

        public void onItemsMoved(@NonNull RecyclerView recyclerView, int i2, int i3, int i4) {
        }

        public void onItemsRemoved(@NonNull RecyclerView recyclerView, int i2, int i3) {
        }

        public void onItemsUpdated(@NonNull RecyclerView recyclerView, int i2, int i3) {
        }

        public void onItemsUpdated(@NonNull RecyclerView recyclerView, int i2, int i3, @Nullable Object obj) {
            onItemsUpdated(recyclerView, i2, i3);
        }

        public void onLayoutChildren(Recycler recycler, State state) {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public void onLayoutCompleted(State state) {
        }

        public void onMeasure(@NonNull Recycler recycler, @NonNull State state, int i2, int i3) {
            this.f3717b.o(i2, i3);
        }

        @Deprecated
        public boolean onRequestChildFocus(@NonNull RecyclerView recyclerView, @NonNull View view, @Nullable View view2) {
            return isSmoothScrolling() || recyclerView.isComputingLayout();
        }

        public boolean onRequestChildFocus(@NonNull RecyclerView recyclerView, @NonNull State state, @NonNull View view, @Nullable View view2) {
            return onRequestChildFocus(recyclerView, view, view2);
        }

        public void onRestoreInstanceState(Parcelable parcelable) {
        }

        @Nullable
        public Parcelable onSaveInstanceState() {
            return null;
        }

        public void onScrollStateChanged(int i2) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean p(View view, int i2, int i3, LayoutParams layoutParams) {
            return (this.mMeasurementCacheEnabled && isMeasurementUpToDate(view.getMeasuredWidth(), i2, ((ViewGroup.MarginLayoutParams) layoutParams).width) && isMeasurementUpToDate(view.getMeasuredHeight(), i3, ((ViewGroup.MarginLayoutParams) layoutParams).height)) ? false : true;
        }

        public boolean performAccessibilityAction(@NonNull Recycler recycler, @NonNull State state, int i2, @Nullable Bundle bundle) {
            int height;
            int width;
            int i3;
            int i4;
            RecyclerView recyclerView = this.f3717b;
            if (recyclerView == null) {
                return false;
            }
            if (i2 == 4096) {
                height = recyclerView.canScrollVertically(1) ? (getHeight() - getPaddingTop()) - getPaddingBottom() : 0;
                if (this.f3717b.canScrollHorizontally(1)) {
                    width = (getWidth() - getPaddingLeft()) - getPaddingRight();
                    i3 = height;
                    i4 = width;
                }
                i3 = height;
                i4 = 0;
            } else if (i2 != 8192) {
                i4 = 0;
                i3 = 0;
            } else {
                height = recyclerView.canScrollVertically(-1) ? -((getHeight() - getPaddingTop()) - getPaddingBottom()) : 0;
                if (this.f3717b.canScrollHorizontally(-1)) {
                    width = -((getWidth() - getPaddingLeft()) - getPaddingRight());
                    i3 = height;
                    i4 = width;
                }
                i3 = height;
                i4 = 0;
            }
            if (i3 == 0 && i4 == 0) {
                return false;
            }
            this.f3717b.g0(i4, i3, null, Integer.MIN_VALUE, true);
            return true;
        }

        public boolean performAccessibilityActionForItem(@NonNull Recycler recycler, @NonNull State state, @NonNull View view, int i2, @Nullable Bundle bundle) {
            return false;
        }

        public void postOnAnimation(Runnable runnable) {
            RecyclerView recyclerView = this.f3717b;
            if (recyclerView != null) {
                ViewCompat.postOnAnimation(recyclerView, runnable);
            }
        }

        void q() {
            SmoothScroller smoothScroller = this.f3720e;
            if (smoothScroller != null) {
                smoothScroller.i();
            }
        }

        public void removeAllViews() {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                this.f3716a.o(childCount);
            }
        }

        public void removeAndRecycleAllViews(@NonNull Recycler recycler) {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                if (!RecyclerView.F(getChildAt(childCount)).shouldIgnore()) {
                    removeAndRecycleViewAt(childCount, recycler);
                }
            }
        }

        public void removeAndRecycleView(@NonNull View view, @NonNull Recycler recycler) {
            removeView(view);
            recycler.recycleView(view);
        }

        public void removeAndRecycleViewAt(int i2, @NonNull Recycler recycler) {
            View childAt = getChildAt(i2);
            removeViewAt(i2);
            recycler.recycleView(childAt);
        }

        public boolean removeCallbacks(Runnable runnable) {
            RecyclerView recyclerView = this.f3717b;
            if (recyclerView != null) {
                return recyclerView.removeCallbacks(runnable);
            }
            return false;
        }

        public void removeDetachedView(@NonNull View view) {
            this.f3717b.removeDetachedView(view, false);
        }

        public void removeView(View view) {
            this.f3716a.n(view);
        }

        public void removeViewAt(int i2) {
            if (getChildAt(i2) != null) {
                this.f3716a.o(i2);
            }
        }

        public boolean requestChildRectangleOnScreen(@NonNull RecyclerView recyclerView, @NonNull View view, @NonNull Rect rect, boolean z) {
            return requestChildRectangleOnScreen(recyclerView, view, rect, z, false);
        }

        public boolean requestChildRectangleOnScreen(@NonNull RecyclerView recyclerView, @NonNull View view, @NonNull Rect rect, boolean z, boolean z2) {
            int[] childRectangleOnScreenScrollAmount = getChildRectangleOnScreenScrollAmount(view, rect);
            int i2 = childRectangleOnScreenScrollAmount[0];
            int i3 = childRectangleOnScreenScrollAmount[1];
            if ((!z2 || isFocusedChildVisibleAfterScrolling(recyclerView, i2, i3)) && !(i2 == 0 && i3 == 0)) {
                if (z) {
                    recyclerView.scrollBy(i2, i3);
                } else {
                    recyclerView.smoothScrollBy(i2, i3);
                }
                return true;
            }
            return false;
        }

        public void requestLayout() {
            RecyclerView recyclerView = this.f3717b;
            if (recyclerView != null) {
                recyclerView.requestLayout();
            }
        }

        public void requestSimpleAnimationsInNextLayout() {
            this.f3721f = true;
        }

        public int scrollHorizontallyBy(int i2, Recycler recycler, State state) {
            return 0;
        }

        public void scrollToPosition(int i2) {
        }

        public int scrollVerticallyBy(int i2, Recycler recycler, State state) {
            return 0;
        }

        @Deprecated
        public void setAutoMeasureEnabled(boolean z) {
            this.f3723h = z;
        }

        public final void setItemPrefetchEnabled(boolean z) {
            if (z != this.mItemPrefetchEnabled) {
                this.mItemPrefetchEnabled = z;
                this.f3724i = 0;
                RecyclerView recyclerView = this.f3717b;
                if (recyclerView != null) {
                    recyclerView.f3689a.A();
                }
            }
        }

        public void setMeasuredDimension(int i2, int i3) {
            this.f3717b.setMeasuredDimension(i2, i3);
        }

        public void setMeasuredDimension(Rect rect, int i2, int i3) {
            setMeasuredDimension(chooseSize(i2, rect.width() + getPaddingLeft() + getPaddingRight(), getMinimumWidth()), chooseSize(i3, rect.height() + getPaddingTop() + getPaddingBottom(), getMinimumHeight()));
        }

        public void setMeasurementCacheEnabled(boolean z) {
            this.mMeasurementCacheEnabled = z;
        }

        public void smoothScrollToPosition(RecyclerView recyclerView, State state, int i2) {
            Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
        }

        public void startSmoothScroll(SmoothScroller smoothScroller) {
            SmoothScroller smoothScroller2 = this.f3720e;
            if (smoothScroller2 != null && smoothScroller != smoothScroller2 && smoothScroller2.isRunning()) {
                this.f3720e.i();
            }
            this.f3720e = smoothScroller;
            smoothScroller.h(this.f3717b, this);
        }

        public void stopIgnoringView(@NonNull View view) {
            ViewHolder F = RecyclerView.F(view);
            F.stopIgnoring();
            F.resetInternal();
            F.addFlags(4);
        }

        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    /* loaded from: classes.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {

        /* renamed from: a  reason: collision with root package name */
        ViewHolder f3728a;

        /* renamed from: b  reason: collision with root package name */
        final Rect f3729b;

        /* renamed from: c  reason: collision with root package name */
        boolean f3730c;

        /* renamed from: d  reason: collision with root package name */
        boolean f3731d;

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
            this.f3729b = new Rect();
            this.f3730c = true;
            this.f3731d = false;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f3729b = new Rect();
            this.f3730c = true;
            this.f3731d = false;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f3729b = new Rect();
            this.f3730c = true;
            this.f3731d = false;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f3729b = new Rect();
            this.f3730c = true;
            this.f3731d = false;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.LayoutParams) layoutParams);
            this.f3729b = new Rect();
            this.f3730c = true;
            this.f3731d = false;
        }

        public int getAbsoluteAdapterPosition() {
            return this.f3728a.getAbsoluteAdapterPosition();
        }

        public int getBindingAdapterPosition() {
            return this.f3728a.getBindingAdapterPosition();
        }

        @Deprecated
        public int getViewAdapterPosition() {
            return this.f3728a.getBindingAdapterPosition();
        }

        public int getViewLayoutPosition() {
            return this.f3728a.getLayoutPosition();
        }

        @Deprecated
        public int getViewPosition() {
            return this.f3728a.getPosition();
        }

        public boolean isItemChanged() {
            return this.f3728a.isUpdated();
        }

        public boolean isItemRemoved() {
            return this.f3728a.isRemoved();
        }

        public boolean isViewInvalid() {
            return this.f3728a.isInvalid();
        }

        public boolean viewNeedsUpdate() {
            return this.f3728a.needsUpdate();
        }
    }

    /* loaded from: classes.dex */
    public interface OnChildAttachStateChangeListener {
        void onChildViewAttachedToWindow(@NonNull View view);

        void onChildViewDetachedFromWindow(@NonNull View view);
    }

    /* loaded from: classes.dex */
    public static abstract class OnFlingListener {
        public abstract boolean onFling(int i2, int i3);
    }

    /* loaded from: classes.dex */
    public interface OnItemTouchListener {
        boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent);

        void onRequestDisallowInterceptTouchEvent(boolean z);

        void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent);
    }

    /* loaded from: classes.dex */
    public static abstract class OnScrollListener {
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i2) {
        }

        public void onScrolled(@NonNull RecyclerView recyclerView, int i2, int i3) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface Orientation {
    }

    /* loaded from: classes.dex */
    public static class RecycledViewPool {
        private static final int DEFAULT_MAX_SCRAP = 5;

        /* renamed from: a  reason: collision with root package name */
        SparseArray f3732a = new SparseArray();
        private int mAttachCount = 0;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public static class ScrapData {

            /* renamed from: a  reason: collision with root package name */
            final ArrayList f3733a = new ArrayList();

            /* renamed from: b  reason: collision with root package name */
            int f3734b = 5;

            /* renamed from: c  reason: collision with root package name */
            long f3735c = 0;

            /* renamed from: d  reason: collision with root package name */
            long f3736d = 0;

            ScrapData() {
            }
        }

        private ScrapData getScrapDataForType(int i2) {
            ScrapData scrapData = (ScrapData) this.f3732a.get(i2);
            if (scrapData == null) {
                ScrapData scrapData2 = new ScrapData();
                this.f3732a.put(i2, scrapData2);
                return scrapData2;
            }
            return scrapData;
        }

        void a() {
            this.mAttachCount++;
        }

        void b() {
            this.mAttachCount--;
        }

        void c(int i2, long j2) {
            ScrapData scrapDataForType = getScrapDataForType(i2);
            scrapDataForType.f3736d = f(scrapDataForType.f3736d, j2);
        }

        public void clear() {
            for (int i2 = 0; i2 < this.f3732a.size(); i2++) {
                ((ScrapData) this.f3732a.valueAt(i2)).f3733a.clear();
            }
        }

        void d(int i2, long j2) {
            ScrapData scrapDataForType = getScrapDataForType(i2);
            scrapDataForType.f3735c = f(scrapDataForType.f3735c, j2);
        }

        void e(Adapter adapter, Adapter adapter2, boolean z) {
            if (adapter != null) {
                b();
            }
            if (!z && this.mAttachCount == 0) {
                clear();
            }
            if (adapter2 != null) {
                a();
            }
        }

        long f(long j2, long j3) {
            return j2 == 0 ? j3 : ((j2 / 4) * 3) + (j3 / 4);
        }

        boolean g(int i2, long j2, long j3) {
            long j4 = getScrapDataForType(i2).f3736d;
            return j4 == 0 || j2 + j4 < j3;
        }

        @Nullable
        public ViewHolder getRecycledView(int i2) {
            ScrapData scrapData = (ScrapData) this.f3732a.get(i2);
            if (scrapData == null || scrapData.f3733a.isEmpty()) {
                return null;
            }
            ArrayList arrayList = scrapData.f3733a;
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (!((ViewHolder) arrayList.get(size)).isAttachedToTransitionOverlay()) {
                    return (ViewHolder) arrayList.remove(size);
                }
            }
            return null;
        }

        public int getRecycledViewCount(int i2) {
            return getScrapDataForType(i2).f3733a.size();
        }

        boolean h(int i2, long j2, long j3) {
            long j4 = getScrapDataForType(i2).f3735c;
            return j4 == 0 || j2 + j4 < j3;
        }

        public void putRecycledView(ViewHolder viewHolder) {
            int itemViewType = viewHolder.getItemViewType();
            ArrayList arrayList = getScrapDataForType(itemViewType).f3733a;
            if (((ScrapData) this.f3732a.get(itemViewType)).f3734b <= arrayList.size()) {
                return;
            }
            viewHolder.resetInternal();
            arrayList.add(viewHolder);
        }

        public void setMaxRecycledViews(int i2, int i3) {
            ScrapData scrapDataForType = getScrapDataForType(i2);
            scrapDataForType.f3734b = i3;
            ArrayList arrayList = scrapDataForType.f3733a;
            while (arrayList.size() > i3) {
                arrayList.remove(arrayList.size() - 1);
            }
        }
    }

    /* loaded from: classes.dex */
    public final class Recycler {

        /* renamed from: a  reason: collision with root package name */
        final ArrayList f3737a;

        /* renamed from: b  reason: collision with root package name */
        ArrayList f3738b;

        /* renamed from: c  reason: collision with root package name */
        final ArrayList f3739c;

        /* renamed from: d  reason: collision with root package name */
        int f3740d;

        /* renamed from: e  reason: collision with root package name */
        RecycledViewPool f3741e;
        private int mRequestedCacheMax;
        private final List<ViewHolder> mUnmodifiableAttachedScrap;
        private ViewCacheExtension mViewCacheExtension;

        public Recycler() {
            ArrayList arrayList = new ArrayList();
            this.f3737a = arrayList;
            this.f3738b = null;
            this.f3739c = new ArrayList();
            this.mUnmodifiableAttachedScrap = Collections.unmodifiableList(arrayList);
            this.mRequestedCacheMax = 2;
            this.f3740d = 2;
        }

        private void attachAccessibilityDelegateOnBind(ViewHolder viewHolder) {
            if (RecyclerView.this.L()) {
                View view = viewHolder.itemView;
                if (ViewCompat.getImportantForAccessibility(view) == 0) {
                    ViewCompat.setImportantForAccessibility(view, 1);
                }
                RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate = RecyclerView.this.F;
                if (recyclerViewAccessibilityDelegate == null) {
                    return;
                }
                AccessibilityDelegateCompat itemDelegate = recyclerViewAccessibilityDelegate.getItemDelegate();
                if (itemDelegate instanceof RecyclerViewAccessibilityDelegate.ItemDelegate) {
                    ((RecyclerViewAccessibilityDelegate.ItemDelegate) itemDelegate).d(view);
                }
                ViewCompat.setAccessibilityDelegate(view, itemDelegate);
            }
        }

        private void invalidateDisplayListInt(ViewGroup viewGroup, boolean z) {
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (childAt instanceof ViewGroup) {
                    invalidateDisplayListInt((ViewGroup) childAt, true);
                }
            }
            if (z) {
                if (viewGroup.getVisibility() == 4) {
                    viewGroup.setVisibility(0);
                    viewGroup.setVisibility(4);
                    return;
                }
                int visibility = viewGroup.getVisibility();
                viewGroup.setVisibility(4);
                viewGroup.setVisibility(visibility);
            }
        }

        private void invalidateDisplayListInt(ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            if (view instanceof ViewGroup) {
                invalidateDisplayListInt((ViewGroup) view, false);
            }
        }

        private boolean tryBindViewHolderByDeadline(@NonNull ViewHolder viewHolder, int i2, int i3, long j2) {
            viewHolder.mBindingAdapter = null;
            viewHolder.mOwnerRecyclerView = RecyclerView.this;
            int itemViewType = viewHolder.getItemViewType();
            long nanoTime = RecyclerView.this.getNanoTime();
            if (j2 == LongCompanionObject.MAX_VALUE || this.f3741e.g(itemViewType, nanoTime, j2)) {
                RecyclerView.this.f3698j.bindViewHolder(viewHolder, i2);
                this.f3741e.c(viewHolder.getItemViewType(), RecyclerView.this.getNanoTime() - nanoTime);
                attachAccessibilityDelegateOnBind(viewHolder);
                if (RecyclerView.this.B.isPreLayout()) {
                    viewHolder.mPreLayoutPosition = i3;
                    return true;
                }
                return true;
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void A() {
            LayoutManager layoutManager = RecyclerView.this.f3699k;
            this.f3740d = this.mRequestedCacheMax + (layoutManager != null ? layoutManager.f3724i : 0);
            for (int size = this.f3739c.size() - 1; size >= 0 && this.f3739c.size() > this.f3740d; size--) {
                t(size);
            }
        }

        boolean B(ViewHolder viewHolder) {
            if (viewHolder.isRemoved()) {
                return RecyclerView.this.B.isPreLayout();
            }
            int i2 = viewHolder.mPosition;
            if (i2 >= 0 && i2 < RecyclerView.this.f3698j.getItemCount()) {
                if (RecyclerView.this.B.isPreLayout() || RecyclerView.this.f3698j.getItemViewType(viewHolder.mPosition) == viewHolder.getItemViewType()) {
                    return !RecyclerView.this.f3698j.hasStableIds() || viewHolder.getItemId() == RecyclerView.this.f3698j.getItemId(viewHolder.mPosition);
                }
                return false;
            }
            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + viewHolder + RecyclerView.this.z());
        }

        void C(int i2, int i3) {
            int i4;
            int i5 = i3 + i2;
            for (int size = this.f3739c.size() - 1; size >= 0; size--) {
                ViewHolder viewHolder = (ViewHolder) this.f3739c.get(size);
                if (viewHolder != null && (i4 = viewHolder.mPosition) >= i2 && i4 < i5) {
                    viewHolder.addFlags(2);
                    t(size);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a(@NonNull ViewHolder viewHolder, boolean z) {
            RecyclerView.k(viewHolder);
            View view = viewHolder.itemView;
            RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate = RecyclerView.this.F;
            if (recyclerViewAccessibilityDelegate != null) {
                AccessibilityDelegateCompat itemDelegate = recyclerViewAccessibilityDelegate.getItemDelegate();
                ViewCompat.setAccessibilityDelegate(view, itemDelegate instanceof RecyclerViewAccessibilityDelegate.ItemDelegate ? ((RecyclerViewAccessibilityDelegate.ItemDelegate) itemDelegate).c(view) : null);
            }
            if (z) {
                d(viewHolder);
            }
            viewHolder.mBindingAdapter = null;
            viewHolder.mOwnerRecyclerView = null;
            f().putRecycledView(viewHolder);
        }

        void b() {
            int size = this.f3739c.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((ViewHolder) this.f3739c.get(i2)).clearOldPosition();
            }
            int size2 = this.f3737a.size();
            for (int i3 = 0; i3 < size2; i3++) {
                ((ViewHolder) this.f3737a.get(i3)).clearOldPosition();
            }
            ArrayList arrayList = this.f3738b;
            if (arrayList != null) {
                int size3 = arrayList.size();
                for (int i4 = 0; i4 < size3; i4++) {
                    ((ViewHolder) this.f3738b.get(i4)).clearOldPosition();
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x005a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void bindViewToPosition(@NonNull View view, int i2) {
            LayoutParams layoutParams;
            ViewGroup.LayoutParams generateLayoutParams;
            ViewHolder F = RecyclerView.F(view);
            if (F == null) {
                throw new IllegalArgumentException("The view does not have a ViewHolder. You cannot pass arbitrary views to this method, they should be created by the Adapter" + RecyclerView.this.z());
            }
            int d2 = RecyclerView.this.f3691c.d(i2);
            if (d2 < 0 || d2 >= RecyclerView.this.f3698j.getItemCount()) {
                throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + i2 + "(offset:" + d2 + ").state:" + RecyclerView.this.B.getItemCount() + RecyclerView.this.z());
            }
            tryBindViewHolderByDeadline(F, d2, i2, LongCompanionObject.MAX_VALUE);
            ViewGroup.LayoutParams layoutParams2 = F.itemView.getLayoutParams();
            if (layoutParams2 == null) {
                generateLayoutParams = RecyclerView.this.generateDefaultLayoutParams();
            } else if (RecyclerView.this.checkLayoutParams(layoutParams2)) {
                layoutParams = (LayoutParams) layoutParams2;
                layoutParams.f3730c = true;
                layoutParams.f3728a = F;
                layoutParams.f3731d = F.itemView.getParent() == null;
            } else {
                generateLayoutParams = RecyclerView.this.generateLayoutParams(layoutParams2);
            }
            layoutParams = (LayoutParams) generateLayoutParams;
            F.itemView.setLayoutParams(layoutParams);
            layoutParams.f3730c = true;
            layoutParams.f3728a = F;
            layoutParams.f3731d = F.itemView.getParent() == null;
        }

        void c() {
            this.f3737a.clear();
            ArrayList arrayList = this.f3738b;
            if (arrayList != null) {
                arrayList.clear();
            }
        }

        public void clear() {
            this.f3737a.clear();
            s();
        }

        public int convertPreLayoutPositionToPostLayout(int i2) {
            if (i2 >= 0 && i2 < RecyclerView.this.B.getItemCount()) {
                return !RecyclerView.this.B.isPreLayout() ? i2 : RecyclerView.this.f3691c.d(i2);
            }
            throw new IndexOutOfBoundsException("invalid position " + i2 + ". State item count is " + RecyclerView.this.B.getItemCount() + RecyclerView.this.z());
        }

        void d(@NonNull ViewHolder viewHolder) {
            RecyclerListener recyclerListener = RecyclerView.this.f3700l;
            if (recyclerListener != null) {
                recyclerListener.onViewRecycled(viewHolder);
            }
            int size = RecyclerView.this.f3701m.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((RecyclerListener) RecyclerView.this.f3701m.get(i2)).onViewRecycled(viewHolder);
            }
            Adapter adapter = RecyclerView.this.f3698j;
            if (adapter != null) {
                adapter.onViewRecycled(viewHolder);
            }
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.B != null) {
                recyclerView.f3693e.o(viewHolder);
            }
        }

        ViewHolder e(int i2) {
            int size;
            int d2;
            ArrayList arrayList = this.f3738b;
            if (arrayList != null && (size = arrayList.size()) != 0) {
                for (int i3 = 0; i3 < size; i3++) {
                    ViewHolder viewHolder = (ViewHolder) this.f3738b.get(i3);
                    if (!viewHolder.wasReturnedFromScrap() && viewHolder.getLayoutPosition() == i2) {
                        viewHolder.addFlags(32);
                        return viewHolder;
                    }
                }
                if (RecyclerView.this.f3698j.hasStableIds() && (d2 = RecyclerView.this.f3691c.d(i2)) > 0 && d2 < RecyclerView.this.f3698j.getItemCount()) {
                    long itemId = RecyclerView.this.f3698j.getItemId(d2);
                    for (int i4 = 0; i4 < size; i4++) {
                        ViewHolder viewHolder2 = (ViewHolder) this.f3738b.get(i4);
                        if (!viewHolder2.wasReturnedFromScrap() && viewHolder2.getItemId() == itemId) {
                            viewHolder2.addFlags(32);
                            return viewHolder2;
                        }
                    }
                }
            }
            return null;
        }

        RecycledViewPool f() {
            if (this.f3741e == null) {
                this.f3741e = new RecycledViewPool();
            }
            return this.f3741e;
        }

        int g() {
            return this.f3737a.size();
        }

        @NonNull
        public List<ViewHolder> getScrapList() {
            return this.mUnmodifiableAttachedScrap;
        }

        @NonNull
        public View getViewForPosition(int i2) {
            return k(i2, false);
        }

        ViewHolder h(long j2, int i2, boolean z) {
            for (int size = this.f3737a.size() - 1; size >= 0; size--) {
                ViewHolder viewHolder = (ViewHolder) this.f3737a.get(size);
                if (viewHolder.getItemId() == j2 && !viewHolder.wasReturnedFromScrap()) {
                    if (i2 == viewHolder.getItemViewType()) {
                        viewHolder.addFlags(32);
                        if (viewHolder.isRemoved() && !RecyclerView.this.B.isPreLayout()) {
                            viewHolder.setFlags(2, 14);
                        }
                        return viewHolder;
                    } else if (!z) {
                        this.f3737a.remove(size);
                        RecyclerView.this.removeDetachedView(viewHolder.itemView, false);
                        r(viewHolder.itemView);
                    }
                }
            }
            int size2 = this.f3739c.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return null;
                }
                ViewHolder viewHolder2 = (ViewHolder) this.f3739c.get(size2);
                if (viewHolder2.getItemId() == j2 && !viewHolder2.isAttachedToTransitionOverlay()) {
                    if (i2 == viewHolder2.getItemViewType()) {
                        if (!z) {
                            this.f3739c.remove(size2);
                        }
                        return viewHolder2;
                    } else if (!z) {
                        t(size2);
                        return null;
                    }
                }
            }
        }

        ViewHolder i(int i2, boolean z) {
            View e2;
            int size = this.f3737a.size();
            for (int i3 = 0; i3 < size; i3++) {
                ViewHolder viewHolder = (ViewHolder) this.f3737a.get(i3);
                if (!viewHolder.wasReturnedFromScrap() && viewHolder.getLayoutPosition() == i2 && !viewHolder.isInvalid() && (RecyclerView.this.B.f3751g || !viewHolder.isRemoved())) {
                    viewHolder.addFlags(32);
                    return viewHolder;
                }
            }
            if (z || (e2 = RecyclerView.this.f3692d.e(i2)) == null) {
                int size2 = this.f3739c.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    ViewHolder viewHolder2 = (ViewHolder) this.f3739c.get(i4);
                    if (!viewHolder2.isInvalid() && viewHolder2.getLayoutPosition() == i2 && !viewHolder2.isAttachedToTransitionOverlay()) {
                        if (!z) {
                            this.f3739c.remove(i4);
                        }
                        return viewHolder2;
                    }
                }
                return null;
            }
            ViewHolder F = RecyclerView.F(e2);
            RecyclerView.this.f3692d.q(e2);
            int k2 = RecyclerView.this.f3692d.k(e2);
            if (k2 != -1) {
                RecyclerView.this.f3692d.d(k2);
                v(e2);
                F.addFlags(8224);
                return F;
            }
            throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + F + RecyclerView.this.z());
        }

        View j(int i2) {
            return ((ViewHolder) this.f3737a.get(i2)).itemView;
        }

        View k(int i2, boolean z) {
            return y(i2, z, LongCompanionObject.MAX_VALUE).itemView;
        }

        void l() {
            int size = this.f3739c.size();
            for (int i2 = 0; i2 < size; i2++) {
                LayoutParams layoutParams = (LayoutParams) ((ViewHolder) this.f3739c.get(i2)).itemView.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.f3730c = true;
                }
            }
        }

        void m() {
            int size = this.f3739c.size();
            for (int i2 = 0; i2 < size; i2++) {
                ViewHolder viewHolder = (ViewHolder) this.f3739c.get(i2);
                if (viewHolder != null) {
                    viewHolder.addFlags(6);
                    viewHolder.addChangePayload(null);
                }
            }
            Adapter adapter = RecyclerView.this.f3698j;
            if (adapter == null || !adapter.hasStableIds()) {
                s();
            }
        }

        void n(int i2, int i3) {
            int size = this.f3739c.size();
            for (int i4 = 0; i4 < size; i4++) {
                ViewHolder viewHolder = (ViewHolder) this.f3739c.get(i4);
                if (viewHolder != null && viewHolder.mPosition >= i2) {
                    viewHolder.offsetPosition(i3, false);
                }
            }
        }

        void o(int i2, int i3) {
            int i4;
            int i5;
            int i6;
            int i7;
            if (i2 < i3) {
                i4 = -1;
                i6 = i2;
                i5 = i3;
            } else {
                i4 = 1;
                i5 = i2;
                i6 = i3;
            }
            int size = this.f3739c.size();
            for (int i8 = 0; i8 < size; i8++) {
                ViewHolder viewHolder = (ViewHolder) this.f3739c.get(i8);
                if (viewHolder != null && (i7 = viewHolder.mPosition) >= i6 && i7 <= i5) {
                    if (i7 == i2) {
                        viewHolder.offsetPosition(i3 - i2, false);
                    } else {
                        viewHolder.offsetPosition(i4, false);
                    }
                }
            }
        }

        void p(int i2, int i3, boolean z) {
            int i4 = i2 + i3;
            for (int size = this.f3739c.size() - 1; size >= 0; size--) {
                ViewHolder viewHolder = (ViewHolder) this.f3739c.get(size);
                if (viewHolder != null) {
                    int i5 = viewHolder.mPosition;
                    if (i5 >= i4) {
                        viewHolder.offsetPosition(-i3, z);
                    } else if (i5 >= i2) {
                        viewHolder.addFlags(8);
                        t(size);
                    }
                }
            }
        }

        void q(Adapter adapter, Adapter adapter2, boolean z) {
            clear();
            f().e(adapter, adapter2, z);
        }

        void r(View view) {
            ViewHolder F = RecyclerView.F(view);
            F.mScrapContainer = null;
            F.mInChangeScrap = false;
            F.clearReturnedFromScrapFlag();
            u(F);
        }

        public void recycleView(@NonNull View view) {
            ViewHolder F = RecyclerView.F(view);
            if (F.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(view, false);
            }
            if (F.isScrap()) {
                F.unScrap();
            } else if (F.wasReturnedFromScrap()) {
                F.clearReturnedFromScrapFlag();
            }
            u(F);
            if (RecyclerView.this.x == null || F.isRecyclable()) {
                return;
            }
            RecyclerView.this.x.endAnimation(F);
        }

        void s() {
            for (int size = this.f3739c.size() - 1; size >= 0; size--) {
                t(size);
            }
            this.f3739c.clear();
            if (RecyclerView.L) {
                RecyclerView.this.A.a();
            }
        }

        public void setViewCacheSize(int i2) {
            this.mRequestedCacheMax = i2;
            A();
        }

        void t(int i2) {
            a((ViewHolder) this.f3739c.get(i2), true);
            this.f3739c.remove(i2);
        }

        void u(ViewHolder viewHolder) {
            boolean z;
            boolean z2 = true;
            if (viewHolder.isScrap() || viewHolder.itemView.getParent() != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Scrapped or attached views may not be recycled. isScrap:");
                sb.append(viewHolder.isScrap());
                sb.append(" isAttached:");
                sb.append(viewHolder.itemView.getParent() != null);
                sb.append(RecyclerView.this.z());
                throw new IllegalArgumentException(sb.toString());
            } else if (viewHolder.isTmpDetached()) {
                throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + viewHolder + RecyclerView.this.z());
            } else if (viewHolder.shouldIgnore()) {
                throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle." + RecyclerView.this.z());
            } else {
                boolean doesTransientStatePreventRecycling = viewHolder.doesTransientStatePreventRecycling();
                Adapter adapter = RecyclerView.this.f3698j;
                if ((adapter != null && doesTransientStatePreventRecycling && adapter.onFailedToRecycleView(viewHolder)) || viewHolder.isRecyclable()) {
                    if (this.f3740d <= 0 || viewHolder.hasAnyOfTheFlags(526)) {
                        z = false;
                    } else {
                        int size = this.f3739c.size();
                        if (size >= this.f3740d && size > 0) {
                            t(0);
                            size--;
                        }
                        if (RecyclerView.L && size > 0 && !RecyclerView.this.A.c(viewHolder.mPosition)) {
                            int i2 = size - 1;
                            while (i2 >= 0) {
                                if (!RecyclerView.this.A.c(((ViewHolder) this.f3739c.get(i2)).mPosition)) {
                                    break;
                                }
                                i2--;
                            }
                            size = i2 + 1;
                        }
                        this.f3739c.add(size, viewHolder);
                        z = true;
                    }
                    if (z) {
                        z2 = false;
                    } else {
                        a(viewHolder, true);
                    }
                    r1 = z;
                } else {
                    z2 = false;
                }
                RecyclerView.this.f3693e.o(viewHolder);
                if (r1 || z2 || !doesTransientStatePreventRecycling) {
                    return;
                }
                viewHolder.mBindingAdapter = null;
                viewHolder.mOwnerRecyclerView = null;
            }
        }

        void v(View view) {
            ArrayList arrayList;
            ViewHolder F = RecyclerView.F(view);
            if (!F.hasAnyOfTheFlags(12) && F.isUpdated() && !RecyclerView.this.j(F)) {
                if (this.f3738b == null) {
                    this.f3738b = new ArrayList();
                }
                F.setScrapContainer(this, true);
                arrayList = this.f3738b;
            } else if (F.isInvalid() && !F.isRemoved() && !RecyclerView.this.f3698j.hasStableIds()) {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + RecyclerView.this.z());
            } else {
                F.setScrapContainer(this, false);
                arrayList = this.f3737a;
            }
            arrayList.add(F);
        }

        void w(RecycledViewPool recycledViewPool) {
            RecycledViewPool recycledViewPool2 = this.f3741e;
            if (recycledViewPool2 != null) {
                recycledViewPool2.b();
            }
            this.f3741e = recycledViewPool;
            if (recycledViewPool == null || RecyclerView.this.getAdapter() == null) {
                return;
            }
            this.f3741e.a();
        }

        void x(ViewCacheExtension viewCacheExtension) {
            this.mViewCacheExtension = viewCacheExtension;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Removed duplicated region for block: B:101:0x020c  */
        /* JADX WARN: Removed duplicated region for block: B:107:0x0221 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x005c  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x005f  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x0185  */
        /* JADX WARN: Removed duplicated region for block: B:82:0x01a2  */
        /* JADX WARN: Removed duplicated region for block: B:85:0x01c5  */
        /* JADX WARN: Removed duplicated region for block: B:90:0x01d4  */
        /* JADX WARN: Removed duplicated region for block: B:99:0x01fe  */
        @Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public ViewHolder y(int i2, boolean z, long j2) {
            ViewHolder viewHolder;
            boolean z2;
            ViewHolder viewHolder2;
            boolean z3;
            boolean tryBindViewHolderByDeadline;
            ViewGroup.LayoutParams layoutParams;
            LayoutParams layoutParams2;
            ViewGroup.LayoutParams generateLayoutParams;
            RecyclerView B;
            ViewCacheExtension viewCacheExtension;
            View viewForPositionAndType;
            if (i2 < 0 || i2 >= RecyclerView.this.B.getItemCount()) {
                throw new IndexOutOfBoundsException("Invalid item position " + i2 + "(" + i2 + "). Item count:" + RecyclerView.this.B.getItemCount() + RecyclerView.this.z());
            }
            boolean z4 = true;
            if (RecyclerView.this.B.isPreLayout()) {
                viewHolder = e(i2);
                if (viewHolder != null) {
                    z2 = true;
                    if (viewHolder == null && (viewHolder = i(i2, z)) != null) {
                        if (B(viewHolder)) {
                            if (!z) {
                                viewHolder.addFlags(4);
                                if (viewHolder.isScrap()) {
                                    RecyclerView.this.removeDetachedView(viewHolder.itemView, false);
                                    viewHolder.unScrap();
                                } else if (viewHolder.wasReturnedFromScrap()) {
                                    viewHolder.clearReturnedFromScrapFlag();
                                }
                                u(viewHolder);
                            }
                            viewHolder = null;
                        } else {
                            z2 = true;
                        }
                    }
                    if (viewHolder == null) {
                        int d2 = RecyclerView.this.f3691c.d(i2);
                        if (d2 < 0 || d2 >= RecyclerView.this.f3698j.getItemCount()) {
                            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + i2 + "(offset:" + d2 + ").state:" + RecyclerView.this.B.getItemCount() + RecyclerView.this.z());
                        }
                        int itemViewType = RecyclerView.this.f3698j.getItemViewType(d2);
                        if (RecyclerView.this.f3698j.hasStableIds() && (viewHolder = h(RecyclerView.this.f3698j.getItemId(d2), itemViewType, z)) != null) {
                            viewHolder.mPosition = d2;
                            z2 = true;
                        }
                        if (viewHolder == null && (viewCacheExtension = this.mViewCacheExtension) != null && (viewForPositionAndType = viewCacheExtension.getViewForPositionAndType(this, i2, itemViewType)) != null) {
                            viewHolder = RecyclerView.this.getChildViewHolder(viewForPositionAndType);
                            if (viewHolder == null) {
                                throw new IllegalArgumentException("getViewForPositionAndType returned a view which does not have a ViewHolder" + RecyclerView.this.z());
                            } else if (viewHolder.shouldIgnore()) {
                                throw new IllegalArgumentException("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view." + RecyclerView.this.z());
                            }
                        }
                        if (viewHolder == null) {
                            ViewHolder recycledView = f().getRecycledView(itemViewType);
                            if (recycledView != null) {
                                recycledView.resetInternal();
                                if (RecyclerView.I) {
                                    invalidateDisplayListInt(recycledView);
                                }
                            }
                            viewHolder = recycledView;
                        }
                        if (viewHolder == null) {
                            long nanoTime = RecyclerView.this.getNanoTime();
                            if (j2 == LongCompanionObject.MAX_VALUE || this.f3741e.h(itemViewType, nanoTime, j2)) {
                                RecyclerView recyclerView = RecyclerView.this;
                                ViewHolder createViewHolder = recyclerView.f3698j.createViewHolder(recyclerView, itemViewType);
                                if (RecyclerView.L && (B = RecyclerView.B(createViewHolder.itemView)) != null) {
                                    createViewHolder.mNestedRecyclerView = new WeakReference<>(B);
                                }
                                this.f3741e.d(itemViewType, RecyclerView.this.getNanoTime() - nanoTime);
                                viewHolder2 = createViewHolder;
                                z3 = z2;
                                if (z3 && !RecyclerView.this.B.isPreLayout() && viewHolder2.hasAnyOfTheFlags(8192)) {
                                    viewHolder2.setFlags(0, 8192);
                                    if (RecyclerView.this.B.f3754j) {
                                        RecyclerView recyclerView2 = RecyclerView.this;
                                        RecyclerView.this.X(viewHolder2, recyclerView2.x.recordPreLayoutInformation(recyclerView2.B, viewHolder2, ItemAnimator.buildAdapterChangeFlagsForAnimations(viewHolder2) | 4096, viewHolder2.getUnmodifiedPayloads()));
                                    }
                                }
                                if (!RecyclerView.this.B.isPreLayout() && viewHolder2.isBound()) {
                                    viewHolder2.mPreLayoutPosition = i2;
                                } else if (viewHolder2.isBound() || viewHolder2.needsUpdate() || viewHolder2.isInvalid()) {
                                    tryBindViewHolderByDeadline = tryBindViewHolderByDeadline(viewHolder2, RecyclerView.this.f3691c.d(i2), i2, j2);
                                    layoutParams = viewHolder2.itemView.getLayoutParams();
                                    if (layoutParams != null) {
                                        generateLayoutParams = RecyclerView.this.generateDefaultLayoutParams();
                                    } else if (RecyclerView.this.checkLayoutParams(layoutParams)) {
                                        layoutParams2 = (LayoutParams) layoutParams;
                                        layoutParams2.f3728a = viewHolder2;
                                        if (z3 || !tryBindViewHolderByDeadline) {
                                            z4 = false;
                                        }
                                        layoutParams2.f3731d = z4;
                                        return viewHolder2;
                                    } else {
                                        generateLayoutParams = RecyclerView.this.generateLayoutParams(layoutParams);
                                    }
                                    layoutParams2 = (LayoutParams) generateLayoutParams;
                                    viewHolder2.itemView.setLayoutParams(layoutParams2);
                                    layoutParams2.f3728a = viewHolder2;
                                    if (z3) {
                                    }
                                    z4 = false;
                                    layoutParams2.f3731d = z4;
                                    return viewHolder2;
                                }
                                tryBindViewHolderByDeadline = false;
                                layoutParams = viewHolder2.itemView.getLayoutParams();
                                if (layoutParams != null) {
                                }
                                layoutParams2 = (LayoutParams) generateLayoutParams;
                                viewHolder2.itemView.setLayoutParams(layoutParams2);
                                layoutParams2.f3728a = viewHolder2;
                                if (z3) {
                                }
                                z4 = false;
                                layoutParams2.f3731d = z4;
                                return viewHolder2;
                            }
                            return null;
                        }
                    }
                    viewHolder2 = viewHolder;
                    z3 = z2;
                    if (z3) {
                        viewHolder2.setFlags(0, 8192);
                        if (RecyclerView.this.B.f3754j) {
                        }
                    }
                    if (!RecyclerView.this.B.isPreLayout()) {
                    }
                    if (viewHolder2.isBound()) {
                    }
                    tryBindViewHolderByDeadline = tryBindViewHolderByDeadline(viewHolder2, RecyclerView.this.f3691c.d(i2), i2, j2);
                    layoutParams = viewHolder2.itemView.getLayoutParams();
                    if (layoutParams != null) {
                    }
                    layoutParams2 = (LayoutParams) generateLayoutParams;
                    viewHolder2.itemView.setLayoutParams(layoutParams2);
                    layoutParams2.f3728a = viewHolder2;
                    if (z3) {
                    }
                    z4 = false;
                    layoutParams2.f3731d = z4;
                    return viewHolder2;
                }
            } else {
                viewHolder = null;
            }
            z2 = false;
            if (viewHolder == null) {
                if (B(viewHolder)) {
                }
            }
            if (viewHolder == null) {
            }
            viewHolder2 = viewHolder;
            z3 = z2;
            if (z3) {
            }
            if (!RecyclerView.this.B.isPreLayout()) {
            }
            if (viewHolder2.isBound()) {
            }
            tryBindViewHolderByDeadline = tryBindViewHolderByDeadline(viewHolder2, RecyclerView.this.f3691c.d(i2), i2, j2);
            layoutParams = viewHolder2.itemView.getLayoutParams();
            if (layoutParams != null) {
            }
            layoutParams2 = (LayoutParams) generateLayoutParams;
            viewHolder2.itemView.setLayoutParams(layoutParams2);
            layoutParams2.f3728a = viewHolder2;
            if (z3) {
            }
            z4 = false;
            layoutParams2.f3731d = z4;
            return viewHolder2;
        }

        void z(ViewHolder viewHolder) {
            (viewHolder.mInChangeScrap ? this.f3738b : this.f3737a).remove(viewHolder);
            viewHolder.mScrapContainer = null;
            viewHolder.mInChangeScrap = false;
            viewHolder.clearReturnedFromScrapFlag();
        }
    }

    /* loaded from: classes.dex */
    public interface RecyclerListener {
        void onViewRecycled(@NonNull ViewHolder viewHolder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class RecyclerViewDataObserver extends AdapterDataObserver {
        RecyclerViewDataObserver() {
        }

        void a() {
            if (RecyclerView.K) {
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.f3704p && recyclerView.f3703o) {
                    ViewCompat.postOnAnimation(recyclerView, recyclerView.f3695g);
                    return;
                }
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            recyclerView2.u = true;
            recyclerView2.requestLayout();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            RecyclerView.this.i(null);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.B.f3750f = true;
            recyclerView.W(true);
            if (RecyclerView.this.f3691c.g()) {
                return;
            }
            RecyclerView.this.requestLayout();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i2, int i3, Object obj) {
            RecyclerView.this.i(null);
            if (RecyclerView.this.f3691c.i(i2, i3, obj)) {
                a();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int i2, int i3) {
            RecyclerView.this.i(null);
            if (RecyclerView.this.f3691c.j(i2, i3)) {
                a();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int i2, int i3, int i4) {
            RecyclerView.this.i(null);
            if (RecyclerView.this.f3691c.k(i2, i3, i4)) {
                a();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int i2, int i3) {
            RecyclerView.this.i(null);
            if (RecyclerView.this.f3691c.l(i2, i3)) {
                a();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onStateRestorationPolicyChanged() {
            Adapter adapter;
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.f3690b == null || (adapter = recyclerView.f3698j) == null || !adapter.canRestoreState()) {
                return;
            }
            RecyclerView.this.requestLayout();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: androidx.recyclerview.widget.RecyclerView.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        Parcelable f3744a;

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f3744a = parcel.readParcelable(classLoader == null ? LayoutManager.class.getClassLoader() : classLoader);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        void a(SavedState savedState) {
            this.f3744a = savedState.f3744a;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeParcelable(this.f3744a, 0);
        }
    }

    /* loaded from: classes.dex */
    public static class SimpleOnItemTouchListener implements OnItemTouchListener {
        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class SmoothScroller {
        private LayoutManager mLayoutManager;
        private boolean mPendingInitialRun;
        private RecyclerView mRecyclerView;
        private boolean mRunning;
        private boolean mStarted;
        private View mTargetView;
        private int mTargetPosition = -1;
        private final Action mRecyclingAction = new Action(0, 0);

        /* loaded from: classes.dex */
        public static class Action {
            public static final int UNDEFINED_DURATION = Integer.MIN_VALUE;
            private boolean mChanged;
            private int mConsecutiveUpdates;
            private int mDuration;
            private int mDx;
            private int mDy;
            private Interpolator mInterpolator;
            private int mJumpToPosition;

            public Action(@Px int i2, @Px int i3) {
                this(i2, i3, Integer.MIN_VALUE, null);
            }

            public Action(@Px int i2, @Px int i3, int i4) {
                this(i2, i3, i4, null);
            }

            public Action(@Px int i2, @Px int i3, int i4, @Nullable Interpolator interpolator) {
                this.mJumpToPosition = -1;
                this.mChanged = false;
                this.mConsecutiveUpdates = 0;
                this.mDx = i2;
                this.mDy = i3;
                this.mDuration = i4;
                this.mInterpolator = interpolator;
            }

            private void validate() {
                if (this.mInterpolator != null && this.mDuration < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                }
                if (this.mDuration < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            }

            boolean a() {
                return this.mJumpToPosition >= 0;
            }

            void b(RecyclerView recyclerView) {
                int i2 = this.mJumpToPosition;
                if (i2 >= 0) {
                    this.mJumpToPosition = -1;
                    recyclerView.M(i2);
                    this.mChanged = false;
                } else if (!this.mChanged) {
                    this.mConsecutiveUpdates = 0;
                } else {
                    validate();
                    recyclerView.y.smoothScrollBy(this.mDx, this.mDy, this.mDuration, this.mInterpolator);
                    int i3 = this.mConsecutiveUpdates + 1;
                    this.mConsecutiveUpdates = i3;
                    if (i3 > 10) {
                        Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                    }
                    this.mChanged = false;
                }
            }

            public int getDuration() {
                return this.mDuration;
            }

            @Px
            public int getDx() {
                return this.mDx;
            }

            @Px
            public int getDy() {
                return this.mDy;
            }

            @Nullable
            public Interpolator getInterpolator() {
                return this.mInterpolator;
            }

            public void jumpTo(int i2) {
                this.mJumpToPosition = i2;
            }

            public void setDuration(int i2) {
                this.mChanged = true;
                this.mDuration = i2;
            }

            public void setDx(@Px int i2) {
                this.mChanged = true;
                this.mDx = i2;
            }

            public void setDy(@Px int i2) {
                this.mChanged = true;
                this.mDy = i2;
            }

            public void setInterpolator(@Nullable Interpolator interpolator) {
                this.mChanged = true;
                this.mInterpolator = interpolator;
            }

            public void update(@Px int i2, @Px int i3, int i4, @Nullable Interpolator interpolator) {
                this.mDx = i2;
                this.mDy = i3;
                this.mDuration = i4;
                this.mInterpolator = interpolator;
                this.mChanged = true;
            }
        }

        /* loaded from: classes.dex */
        public interface ScrollVectorProvider {
            @Nullable
            PointF computeScrollVectorForPosition(int i2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void a(@NonNull PointF pointF) {
            float f2 = pointF.x;
            float f3 = pointF.y;
            float sqrt = (float) Math.sqrt((f2 * f2) + (f3 * f3));
            pointF.x /= sqrt;
            pointF.y /= sqrt;
        }

        void b(int i2, int i3) {
            PointF computeScrollVectorForPosition;
            RecyclerView recyclerView = this.mRecyclerView;
            if (this.mTargetPosition == -1 || recyclerView == null) {
                i();
            }
            if (this.mPendingInitialRun && this.mTargetView == null && this.mLayoutManager != null && (computeScrollVectorForPosition = computeScrollVectorForPosition(this.mTargetPosition)) != null) {
                float f2 = computeScrollVectorForPosition.x;
                if (f2 != 0.0f || computeScrollVectorForPosition.y != 0.0f) {
                    recyclerView.d0((int) Math.signum(f2), (int) Math.signum(computeScrollVectorForPosition.y), null);
                }
            }
            this.mPendingInitialRun = false;
            View view = this.mTargetView;
            if (view != null) {
                if (getChildPosition(view) == this.mTargetPosition) {
                    g(this.mTargetView, recyclerView.B, this.mRecyclingAction);
                    this.mRecyclingAction.b(recyclerView);
                    i();
                } else {
                    Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                    this.mTargetView = null;
                }
            }
            if (this.mRunning) {
                d(i2, i3, recyclerView.B, this.mRecyclingAction);
                boolean a2 = this.mRecyclingAction.a();
                this.mRecyclingAction.b(recyclerView);
                if (a2 && this.mRunning) {
                    this.mPendingInitialRun = true;
                    recyclerView.y.a();
                }
            }
        }

        protected void c(View view) {
            if (getChildPosition(view) == getTargetPosition()) {
                this.mTargetView = view;
            }
        }

        @Nullable
        public PointF computeScrollVectorForPosition(int i2) {
            LayoutManager layoutManager = getLayoutManager();
            if (layoutManager instanceof ScrollVectorProvider) {
                return ((ScrollVectorProvider) layoutManager).computeScrollVectorForPosition(i2);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("You should override computeScrollVectorForPosition when the LayoutManager does not implement ");
            sb.append(ScrollVectorProvider.class.getCanonicalName());
            return null;
        }

        protected abstract void d(@Px int i2, @Px int i3, @NonNull State state, @NonNull Action action);

        protected abstract void e();

        protected abstract void f();

        public View findViewByPosition(int i2) {
            return this.mRecyclerView.f3699k.findViewByPosition(i2);
        }

        protected abstract void g(@NonNull View view, @NonNull State state, @NonNull Action action);

        public int getChildCount() {
            return this.mRecyclerView.f3699k.getChildCount();
        }

        public int getChildPosition(View view) {
            return this.mRecyclerView.getChildLayoutPosition(view);
        }

        @Nullable
        public LayoutManager getLayoutManager() {
            return this.mLayoutManager;
        }

        public int getTargetPosition() {
            return this.mTargetPosition;
        }

        void h(RecyclerView recyclerView, LayoutManager layoutManager) {
            recyclerView.y.stop();
            if (this.mStarted) {
                StringBuilder sb = new StringBuilder();
                sb.append("An instance of ");
                sb.append(getClass().getSimpleName());
                sb.append(" was started more than once. Each instance of");
                sb.append(getClass().getSimpleName());
                sb.append(" is intended to only be used once. You should create a new instance for each use.");
            }
            this.mRecyclerView = recyclerView;
            this.mLayoutManager = layoutManager;
            int i2 = this.mTargetPosition;
            if (i2 == -1) {
                throw new IllegalArgumentException("Invalid target position");
            }
            recyclerView.B.f3745a = i2;
            this.mRunning = true;
            this.mPendingInitialRun = true;
            this.mTargetView = findViewByPosition(getTargetPosition());
            e();
            this.mRecyclerView.y.a();
            this.mStarted = true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public final void i() {
            if (this.mRunning) {
                this.mRunning = false;
                f();
                this.mRecyclerView.B.f3745a = -1;
                this.mTargetView = null;
                this.mTargetPosition = -1;
                this.mPendingInitialRun = false;
                this.mLayoutManager.f(this);
                this.mLayoutManager = null;
                this.mRecyclerView = null;
            }
        }

        @Deprecated
        public void instantScrollToPosition(int i2) {
            this.mRecyclerView.scrollToPosition(i2);
        }

        public boolean isPendingInitialRun() {
            return this.mPendingInitialRun;
        }

        public boolean isRunning() {
            return this.mRunning;
        }

        public void setTargetPosition(int i2) {
            this.mTargetPosition = i2;
        }
    }

    /* loaded from: classes.dex */
    public static class State {

        /* renamed from: a  reason: collision with root package name */
        int f3745a = -1;

        /* renamed from: b  reason: collision with root package name */
        int f3746b = 0;

        /* renamed from: c  reason: collision with root package name */
        int f3747c = 0;

        /* renamed from: d  reason: collision with root package name */
        int f3748d = 1;

        /* renamed from: e  reason: collision with root package name */
        int f3749e = 0;

        /* renamed from: f  reason: collision with root package name */
        boolean f3750f = false;

        /* renamed from: g  reason: collision with root package name */
        boolean f3751g = false;

        /* renamed from: h  reason: collision with root package name */
        boolean f3752h = false;

        /* renamed from: i  reason: collision with root package name */
        boolean f3753i = false;

        /* renamed from: j  reason: collision with root package name */
        boolean f3754j = false;

        /* renamed from: k  reason: collision with root package name */
        boolean f3755k = false;

        /* renamed from: l  reason: collision with root package name */
        int f3756l;

        /* renamed from: m  reason: collision with root package name */
        long f3757m;
        private SparseArray<Object> mData;

        /* renamed from: n  reason: collision with root package name */
        int f3758n;

        /* renamed from: o  reason: collision with root package name */
        int f3759o;

        /* renamed from: p  reason: collision with root package name */
        int f3760p;

        void a(int i2) {
            if ((this.f3748d & i2) != 0) {
                return;
            }
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i2) + " but it is " + Integer.toBinaryString(this.f3748d));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void b(Adapter adapter) {
            this.f3748d = 1;
            this.f3749e = adapter.getItemCount();
            this.f3751g = false;
            this.f3752h = false;
            this.f3753i = false;
        }

        public boolean didStructureChange() {
            return this.f3750f;
        }

        public <T> T get(int i2) {
            SparseArray<Object> sparseArray = this.mData;
            if (sparseArray == null) {
                return null;
            }
            return (T) sparseArray.get(i2);
        }

        public int getItemCount() {
            return this.f3751g ? this.f3746b - this.f3747c : this.f3749e;
        }

        public int getRemainingScrollHorizontal() {
            return this.f3759o;
        }

        public int getRemainingScrollVertical() {
            return this.f3760p;
        }

        public int getTargetScrollPosition() {
            return this.f3745a;
        }

        public boolean hasTargetScrollPosition() {
            return this.f3745a != -1;
        }

        public boolean isMeasuring() {
            return this.f3753i;
        }

        public boolean isPreLayout() {
            return this.f3751g;
        }

        public void put(int i2, Object obj) {
            if (this.mData == null) {
                this.mData = new SparseArray<>();
            }
            this.mData.put(i2, obj);
        }

        public void remove(int i2) {
            SparseArray<Object> sparseArray = this.mData;
            if (sparseArray == null) {
                return;
            }
            sparseArray.remove(i2);
        }

        public String toString() {
            return "State{mTargetPosition=" + this.f3745a + ", mData=" + this.mData + ", mItemCount=" + this.f3749e + ", mIsMeasuring=" + this.f3753i + ", mPreviousLayoutItemCount=" + this.f3746b + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.f3747c + ", mStructureChanged=" + this.f3750f + ", mInPreLayout=" + this.f3751g + ", mRunSimpleAnimations=" + this.f3754j + ", mRunPredictiveAnimations=" + this.f3755k + AbstractJsonLexerKt.END_OBJ;
        }

        public boolean willRunPredictiveAnimations() {
            return this.f3755k;
        }

        public boolean willRunSimpleAnimations() {
            return this.f3754j;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class ViewCacheExtension {
        @Nullable
        public abstract View getViewForPositionAndType(@NonNull Recycler recycler, int i2, int i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ViewFlinger implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        OverScroller f3761a;

        /* renamed from: b  reason: collision with root package name */
        Interpolator f3762b;
        private boolean mEatRunOnAnimationRequest;
        private int mLastFlingX;
        private int mLastFlingY;
        private boolean mReSchedulePostAnimationCallback;

        ViewFlinger() {
            Interpolator interpolator = RecyclerView.M;
            this.f3762b = interpolator;
            this.mEatRunOnAnimationRequest = false;
            this.mReSchedulePostAnimationCallback = false;
            this.f3761a = new OverScroller(RecyclerView.this.getContext(), interpolator);
        }

        private int computeScrollDuration(int i2, int i3) {
            int abs = Math.abs(i2);
            int abs2 = Math.abs(i3);
            boolean z = abs > abs2;
            RecyclerView recyclerView = RecyclerView.this;
            int width = z ? recyclerView.getWidth() : recyclerView.getHeight();
            if (!z) {
                abs = abs2;
            }
            return Math.min((int) (((abs / width) + 1.0f) * 300.0f), 2000);
        }

        private void internalPostOnAnimation() {
            RecyclerView.this.removeCallbacks(this);
            ViewCompat.postOnAnimation(RecyclerView.this, this);
        }

        void a() {
            if (this.mEatRunOnAnimationRequest) {
                this.mReSchedulePostAnimationCallback = true;
            } else {
                internalPostOnAnimation();
            }
        }

        public void fling(int i2, int i3) {
            RecyclerView.this.setScrollState(2);
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            Interpolator interpolator = this.f3762b;
            Interpolator interpolator2 = RecyclerView.M;
            if (interpolator != interpolator2) {
                this.f3762b = interpolator2;
                this.f3761a = new OverScroller(RecyclerView.this.getContext(), interpolator2);
            }
            this.f3761a.fling(0, 0, i2, i3, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            a();
        }

        @Override // java.lang.Runnable
        public void run() {
            int i2;
            int i3;
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.f3699k == null) {
                stop();
                return;
            }
            this.mReSchedulePostAnimationCallback = false;
            this.mEatRunOnAnimationRequest = true;
            recyclerView.n();
            OverScroller overScroller = this.f3761a;
            if (overScroller.computeScrollOffset()) {
                int currX = overScroller.getCurrX();
                int currY = overScroller.getCurrY();
                int i4 = currX - this.mLastFlingX;
                int i5 = currY - this.mLastFlingY;
                this.mLastFlingX = currX;
                this.mLastFlingY = currY;
                RecyclerView recyclerView2 = RecyclerView.this;
                int[] iArr = recyclerView2.G;
                iArr[0] = 0;
                iArr[1] = 0;
                if (recyclerView2.dispatchNestedPreScroll(i4, i5, iArr, null, 1)) {
                    int[] iArr2 = RecyclerView.this.G;
                    i4 -= iArr2[0];
                    i5 -= iArr2[1];
                }
                if (RecyclerView.this.getOverScrollMode() != 2) {
                    RecyclerView.this.m(i4, i5);
                }
                RecyclerView recyclerView3 = RecyclerView.this;
                if (recyclerView3.f3698j != null) {
                    int[] iArr3 = recyclerView3.G;
                    iArr3[0] = 0;
                    iArr3[1] = 0;
                    recyclerView3.d0(i4, i5, iArr3);
                    RecyclerView recyclerView4 = RecyclerView.this;
                    int[] iArr4 = recyclerView4.G;
                    i3 = iArr4[0];
                    i2 = iArr4[1];
                    i4 -= i3;
                    i5 -= i2;
                    SmoothScroller smoothScroller = recyclerView4.f3699k.f3720e;
                    if (smoothScroller != null && !smoothScroller.isPendingInitialRun() && smoothScroller.isRunning()) {
                        int itemCount = RecyclerView.this.B.getItemCount();
                        if (itemCount == 0) {
                            smoothScroller.i();
                        } else {
                            if (smoothScroller.getTargetPosition() >= itemCount) {
                                smoothScroller.setTargetPosition(itemCount - 1);
                            }
                            smoothScroller.b(i3, i2);
                        }
                    }
                } else {
                    i2 = 0;
                    i3 = 0;
                }
                if (!RecyclerView.this.f3702n.isEmpty()) {
                    RecyclerView.this.invalidate();
                }
                RecyclerView recyclerView5 = RecyclerView.this;
                int[] iArr5 = recyclerView5.G;
                iArr5[0] = 0;
                iArr5[1] = 0;
                recyclerView5.dispatchNestedScroll(i3, i2, i4, i5, null, 1, iArr5);
                RecyclerView recyclerView6 = RecyclerView.this;
                int[] iArr6 = recyclerView6.G;
                int i6 = i4 - iArr6[0];
                int i7 = i5 - iArr6[1];
                if (i3 != 0 || i2 != 0) {
                    recyclerView6.t(i3, i2);
                }
                if (!RecyclerView.this.awakenScrollBars()) {
                    RecyclerView.this.invalidate();
                }
                boolean z = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i6 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i7 != 0));
                SmoothScroller smoothScroller2 = RecyclerView.this.f3699k.f3720e;
                if ((smoothScroller2 != null && smoothScroller2.isPendingInitialRun()) || !z) {
                    a();
                    RecyclerView recyclerView7 = RecyclerView.this;
                    GapWorker gapWorker = recyclerView7.z;
                    if (gapWorker != null) {
                        gapWorker.b(recyclerView7, i3, i2);
                    }
                } else {
                    if (RecyclerView.this.getOverScrollMode() != 2) {
                        int currVelocity = (int) overScroller.getCurrVelocity();
                        int i8 = i6 < 0 ? -currVelocity : i6 > 0 ? currVelocity : 0;
                        if (i7 < 0) {
                            currVelocity = -currVelocity;
                        } else if (i7 <= 0) {
                            currVelocity = 0;
                        }
                        RecyclerView.this.a(i8, currVelocity);
                    }
                    if (RecyclerView.L) {
                        RecyclerView.this.A.a();
                    }
                }
            }
            SmoothScroller smoothScroller3 = RecyclerView.this.f3699k.f3720e;
            if (smoothScroller3 != null && smoothScroller3.isPendingInitialRun()) {
                smoothScroller3.b(0, 0);
            }
            this.mEatRunOnAnimationRequest = false;
            if (this.mReSchedulePostAnimationCallback) {
                internalPostOnAnimation();
                return;
            }
            RecyclerView.this.setScrollState(0);
            RecyclerView.this.stopNestedScroll(1);
        }

        public void smoothScrollBy(int i2, int i3, int i4, @Nullable Interpolator interpolator) {
            if (i4 == Integer.MIN_VALUE) {
                i4 = computeScrollDuration(i2, i3);
            }
            int i5 = i4;
            if (interpolator == null) {
                interpolator = RecyclerView.M;
            }
            if (this.f3762b != interpolator) {
                this.f3762b = interpolator;
                this.f3761a = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            RecyclerView.this.setScrollState(2);
            this.f3761a.startScroll(0, 0, i2, i3, i5);
            if (Build.VERSION.SDK_INT < 23) {
                this.f3761a.computeScrollOffset();
            }
            a();
        }

        public void stop() {
            RecyclerView.this.removeCallbacks(this);
            this.f3761a.abortAnimation();
        }
    }

    /* loaded from: classes.dex */
    public static abstract class ViewHolder {
        static final int FLAG_ADAPTER_FULLUPDATE = 1024;
        static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
        static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
        static final int FLAG_BOUND = 1;
        static final int FLAG_IGNORE = 128;
        static final int FLAG_INVALID = 4;
        static final int FLAG_MOVED = 2048;
        static final int FLAG_NOT_RECYCLABLE = 16;
        static final int FLAG_REMOVED = 8;
        static final int FLAG_RETURNED_FROM_SCRAP = 32;
        static final int FLAG_TMP_DETACHED = 256;
        static final int FLAG_UPDATE = 2;
        private static final List<Object> FULLUPDATE_PAYLOADS = Collections.emptyList();
        static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
        @NonNull
        public final View itemView;
        Adapter<? extends ViewHolder> mBindingAdapter;
        int mFlags;
        WeakReference<RecyclerView> mNestedRecyclerView;
        RecyclerView mOwnerRecyclerView;
        int mPosition = -1;
        int mOldPosition = -1;
        long mItemId = -1;
        int mItemViewType = -1;
        int mPreLayoutPosition = -1;
        ViewHolder mShadowedHolder = null;
        ViewHolder mShadowingHolder = null;
        List<Object> mPayloads = null;
        List<Object> mUnmodifiedPayloads = null;
        private int mIsRecyclableCount = 0;
        Recycler mScrapContainer = null;
        boolean mInChangeScrap = false;
        private int mWasImportantForAccessibilityBeforeHidden = 0;
        @VisibleForTesting
        int mPendingAccessibilityState = -1;

        public ViewHolder(@NonNull View view) {
            if (view == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.itemView = view;
        }

        private void createPayloadsIfNeeded() {
            if (this.mPayloads == null) {
                ArrayList arrayList = new ArrayList();
                this.mPayloads = arrayList;
                this.mUnmodifiedPayloads = Collections.unmodifiableList(arrayList);
            }
        }

        void addChangePayload(Object obj) {
            if (obj == null) {
                addFlags(1024);
            } else if ((1024 & this.mFlags) == 0) {
                createPayloadsIfNeeded();
                this.mPayloads.add(obj);
            }
        }

        void addFlags(int i2) {
            this.mFlags = i2 | this.mFlags;
        }

        void clearOldPosition() {
            this.mOldPosition = -1;
            this.mPreLayoutPosition = -1;
        }

        void clearPayload() {
            List<Object> list = this.mPayloads;
            if (list != null) {
                list.clear();
            }
            this.mFlags &= -1025;
        }

        void clearReturnedFromScrapFlag() {
            this.mFlags &= -33;
        }

        void clearTmpDetachFlag() {
            this.mFlags &= -257;
        }

        boolean doesTransientStatePreventRecycling() {
            return (this.mFlags & 16) == 0 && ViewCompat.hasTransientState(this.itemView);
        }

        void flagRemovedAndOffsetPosition(int i2, int i3, boolean z) {
            addFlags(8);
            offsetPosition(i3, z);
            this.mPosition = i2;
        }

        public final int getAbsoluteAdapterPosition() {
            RecyclerView recyclerView = this.mOwnerRecyclerView;
            if (recyclerView == null) {
                return -1;
            }
            return recyclerView.D(this);
        }

        @Deprecated
        public final int getAdapterPosition() {
            return getBindingAdapterPosition();
        }

        @Nullable
        public final Adapter<? extends ViewHolder> getBindingAdapter() {
            return this.mBindingAdapter;
        }

        public final int getBindingAdapterPosition() {
            RecyclerView recyclerView;
            Adapter adapter;
            int D;
            if (this.mBindingAdapter == null || (recyclerView = this.mOwnerRecyclerView) == null || (adapter = recyclerView.getAdapter()) == null || (D = this.mOwnerRecyclerView.D(this)) == -1) {
                return -1;
            }
            return adapter.findRelativeAdapterPositionIn(this.mBindingAdapter, this, D);
        }

        public final long getItemId() {
            return this.mItemId;
        }

        public final int getItemViewType() {
            return this.mItemViewType;
        }

        public final int getLayoutPosition() {
            int i2 = this.mPreLayoutPosition;
            return i2 == -1 ? this.mPosition : i2;
        }

        public final int getOldPosition() {
            return this.mOldPosition;
        }

        @Deprecated
        public final int getPosition() {
            int i2 = this.mPreLayoutPosition;
            return i2 == -1 ? this.mPosition : i2;
        }

        List<Object> getUnmodifiedPayloads() {
            if ((this.mFlags & 1024) == 0) {
                List<Object> list = this.mPayloads;
                return (list == null || list.size() == 0) ? FULLUPDATE_PAYLOADS : this.mUnmodifiedPayloads;
            }
            return FULLUPDATE_PAYLOADS;
        }

        boolean hasAnyOfTheFlags(int i2) {
            return (i2 & this.mFlags) != 0;
        }

        boolean isAdapterPositionUnknown() {
            return (this.mFlags & 512) != 0 || isInvalid();
        }

        boolean isAttachedToTransitionOverlay() {
            return (this.itemView.getParent() == null || this.itemView.getParent() == this.mOwnerRecyclerView) ? false : true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean isBound() {
            return (this.mFlags & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean isInvalid() {
            return (this.mFlags & 4) != 0;
        }

        public final boolean isRecyclable() {
            return (this.mFlags & 16) == 0 && !ViewCompat.hasTransientState(this.itemView);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean isRemoved() {
            return (this.mFlags & 8) != 0;
        }

        boolean isScrap() {
            return this.mScrapContainer != null;
        }

        boolean isTmpDetached() {
            return (this.mFlags & 256) != 0;
        }

        boolean isUpdated() {
            return (this.mFlags & 2) != 0;
        }

        boolean needsUpdate() {
            return (this.mFlags & 2) != 0;
        }

        void offsetPosition(int i2, boolean z) {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
            if (this.mPreLayoutPosition == -1) {
                this.mPreLayoutPosition = this.mPosition;
            }
            if (z) {
                this.mPreLayoutPosition += i2;
            }
            this.mPosition += i2;
            if (this.itemView.getLayoutParams() != null) {
                ((LayoutParams) this.itemView.getLayoutParams()).f3730c = true;
            }
        }

        void onEnteredHiddenState(RecyclerView recyclerView) {
            int i2 = this.mPendingAccessibilityState;
            if (i2 == -1) {
                i2 = ViewCompat.getImportantForAccessibility(this.itemView);
            }
            this.mWasImportantForAccessibilityBeforeHidden = i2;
            recyclerView.e0(this, 4);
        }

        void onLeftHiddenState(RecyclerView recyclerView) {
            recyclerView.e0(this, this.mWasImportantForAccessibilityBeforeHidden);
            this.mWasImportantForAccessibilityBeforeHidden = 0;
        }

        void resetInternal() {
            this.mFlags = 0;
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1L;
            this.mPreLayoutPosition = -1;
            this.mIsRecyclableCount = 0;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            clearPayload();
            this.mWasImportantForAccessibilityBeforeHidden = 0;
            this.mPendingAccessibilityState = -1;
            RecyclerView.k(this);
        }

        void saveOldPosition() {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
        }

        void setFlags(int i2, int i3) {
            this.mFlags = (i2 & i3) | (this.mFlags & (~i3));
        }

        public final void setIsRecyclable(boolean z) {
            int i2;
            int i3 = this.mIsRecyclableCount;
            int i4 = z ? i3 - 1 : i3 + 1;
            this.mIsRecyclableCount = i4;
            if (i4 < 0) {
                this.mIsRecyclableCount = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
                return;
            }
            if (!z && i4 == 1) {
                i2 = this.mFlags | 16;
            } else if (!z || i4 != 0) {
                return;
            } else {
                i2 = this.mFlags & (-17);
            }
            this.mFlags = i2;
        }

        void setScrapContainer(Recycler recycler, boolean z) {
            this.mScrapContainer = recycler;
            this.mInChangeScrap = z;
        }

        boolean shouldBeKeptAsChild() {
            return (this.mFlags & 16) != 0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean shouldIgnore() {
            return (this.mFlags & 128) != 0;
        }

        void stopIgnoring() {
            this.mFlags &= -129;
        }

        public String toString() {
            String simpleName = getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName();
            StringBuilder sb = new StringBuilder(simpleName + "{" + Integer.toHexString(hashCode()) + " position=" + this.mPosition + " id=" + this.mItemId + ", oldPos=" + this.mOldPosition + ", pLpos:" + this.mPreLayoutPosition);
            if (isScrap()) {
                sb.append(" scrap ");
                sb.append(this.mInChangeScrap ? "[changeScrap]" : "[attachedScrap]");
            }
            if (isInvalid()) {
                sb.append(" invalid");
            }
            if (!isBound()) {
                sb.append(" unbound");
            }
            if (needsUpdate()) {
                sb.append(" update");
            }
            if (isRemoved()) {
                sb.append(" removed");
            }
            if (shouldIgnore()) {
                sb.append(" ignored");
            }
            if (isTmpDetached()) {
                sb.append(" tmpDetached");
            }
            if (!isRecyclable()) {
                sb.append(" not recyclable(" + this.mIsRecyclableCount + ")");
            }
            if (isAdapterPositionUnknown()) {
                sb.append(" undefined adapter position");
            }
            if (this.itemView.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        void unScrap() {
            this.mScrapContainer.z(this);
        }

        boolean wasReturnedFromScrap() {
            return (this.mFlags & 32) != 0;
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        I = i2 == 18 || i2 == 19 || i2 == 20;
        J = i2 >= 23;
        K = i2 >= 16;
        L = i2 >= 21;
        FORCE_ABS_FOCUS_SEARCH_DIRECTION = i2 <= 15;
        IGNORE_DETACHED_FOCUSED_CHILD = i2 <= 15;
        Class<?> cls = Integer.TYPE;
        LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = new Class[]{Context.class, AttributeSet.class, cls, cls};
        M = new Interpolator() { // from class: androidx.recyclerview.widget.RecyclerView.3
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f2) {
                float f3 = f2 - 1.0f;
                return (f3 * f3 * f3 * f3 * f3) + 1.0f;
            }
        };
    }

    public RecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.recyclerViewStyle);
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mObserver = new RecyclerViewDataObserver();
        this.f3689a = new Recycler();
        this.f3693e = new ViewInfoStore();
        this.f3695g = new Runnable() { // from class: androidx.recyclerview.widget.RecyclerView.1
            @Override // java.lang.Runnable
            public void run() {
                RecyclerView recyclerView = RecyclerView.this;
                if (!recyclerView.f3706r || recyclerView.isLayoutRequested()) {
                    return;
                }
                RecyclerView recyclerView2 = RecyclerView.this;
                if (!recyclerView2.f3703o) {
                    recyclerView2.requestLayout();
                } else if (recyclerView2.f3708t) {
                    recyclerView2.f3707s = true;
                } else {
                    recyclerView2.n();
                }
            }
        };
        this.f3696h = new Rect();
        this.mTempRect2 = new Rect();
        this.f3697i = new RectF();
        this.f3701m = new ArrayList();
        this.f3702n = new ArrayList();
        this.mOnItemTouchListeners = new ArrayList<>();
        this.mInterceptRequestLayoutDepth = 0;
        this.v = false;
        this.w = false;
        this.mLayoutOrScrollCounter = 0;
        this.mDispatchScrollCounter = 0;
        this.mEdgeEffectFactory = new EdgeEffectFactory();
        this.x = new DefaultItemAnimator();
        this.mScrollState = 0;
        this.mScrollPointerId = -1;
        this.mScaledHorizontalScrollFactor = Float.MIN_VALUE;
        this.mScaledVerticalScrollFactor = Float.MIN_VALUE;
        boolean z = true;
        this.mPreserveFocusAfterLayout = true;
        this.y = new ViewFlinger();
        this.A = L ? new GapWorker.LayoutPrefetchRegistryImpl() : null;
        this.B = new State();
        this.C = false;
        this.D = false;
        this.mItemAnimatorListener = new ItemAnimatorRestoreListener();
        this.E = false;
        this.mMinMaxLayoutPositions = new int[2];
        this.mScrollOffset = new int[2];
        this.mNestedOffsets = new int[2];
        this.G = new int[2];
        this.H = new ArrayList();
        this.mItemAnimatorRunner = new Runnable() { // from class: androidx.recyclerview.widget.RecyclerView.2
            @Override // java.lang.Runnable
            public void run() {
                ItemAnimator itemAnimator = RecyclerView.this.x;
                if (itemAnimator != null) {
                    itemAnimator.runPendingAnimations();
                }
                RecyclerView.this.E = false;
            }
        };
        this.mLastAutoMeasureNonExactMeasuredWidth = 0;
        this.mLastAutoMeasureNonExactMeasuredHeight = 0;
        this.mViewInfoProcessCallback = new ViewInfoStore.ProcessCallback() { // from class: androidx.recyclerview.widget.RecyclerView.4
            @Override // androidx.recyclerview.widget.ViewInfoStore.ProcessCallback
            public void processAppeared(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo, ItemAnimator.ItemHolderInfo itemHolderInfo2) {
                RecyclerView.this.f(viewHolder, itemHolderInfo, itemHolderInfo2);
            }

            @Override // androidx.recyclerview.widget.ViewInfoStore.ProcessCallback
            public void processDisappeared(ViewHolder viewHolder, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo, @Nullable ItemAnimator.ItemHolderInfo itemHolderInfo2) {
                RecyclerView.this.f3689a.z(viewHolder);
                RecyclerView.this.g(viewHolder, itemHolderInfo, itemHolderInfo2);
            }

            @Override // androidx.recyclerview.widget.ViewInfoStore.ProcessCallback
            public void processPersistent(ViewHolder viewHolder, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo2) {
                viewHolder.setIsRecyclable(false);
                RecyclerView recyclerView = RecyclerView.this;
                boolean z2 = recyclerView.v;
                ItemAnimator itemAnimator = recyclerView.x;
                if (z2) {
                    if (!itemAnimator.animateChange(viewHolder, viewHolder, itemHolderInfo, itemHolderInfo2)) {
                        return;
                    }
                } else if (!itemAnimator.animatePersistence(viewHolder, itemHolderInfo, itemHolderInfo2)) {
                    return;
                }
                RecyclerView.this.V();
            }

            @Override // androidx.recyclerview.widget.ViewInfoStore.ProcessCallback
            public void unused(ViewHolder viewHolder) {
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.f3699k.removeAndRecycleView(viewHolder.itemView, recyclerView.f3689a);
            }
        };
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mScaledHorizontalScrollFactor = ViewConfigurationCompat.getScaledHorizontalScrollFactor(viewConfiguration, context);
        this.mScaledVerticalScrollFactor = ViewConfigurationCompat.getScaledVerticalScrollFactor(viewConfiguration, context);
        this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.x.setListener(this.mItemAnimatorListener);
        I();
        initChildrenHelper();
        initAutofill();
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
        int[] iArr = R.styleable.RecyclerView;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i2, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context, iArr, attributeSet, obtainStyledAttributes, i2, 0);
        String string = obtainStyledAttributes.getString(R.styleable.RecyclerView_layoutManager);
        if (obtainStyledAttributes.getInt(R.styleable.RecyclerView_android_descendantFocusability, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.f3694f = obtainStyledAttributes.getBoolean(R.styleable.RecyclerView_android_clipToPadding, true);
        boolean z2 = obtainStyledAttributes.getBoolean(R.styleable.RecyclerView_fastScrollEnabled, false);
        this.f3705q = z2;
        if (z2) {
            J((StateListDrawable) obtainStyledAttributes.getDrawable(R.styleable.RecyclerView_fastScrollVerticalThumbDrawable), obtainStyledAttributes.getDrawable(R.styleable.RecyclerView_fastScrollVerticalTrackDrawable), (StateListDrawable) obtainStyledAttributes.getDrawable(R.styleable.RecyclerView_fastScrollHorizontalThumbDrawable), obtainStyledAttributes.getDrawable(R.styleable.RecyclerView_fastScrollHorizontalTrackDrawable));
        }
        obtainStyledAttributes.recycle();
        createLayoutManager(context, string, attributeSet, i2, 0);
        if (Build.VERSION.SDK_INT >= 21) {
            int[] iArr2 = NESTED_SCROLLING_ATTRS;
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr2, i2, 0);
            ViewCompat.saveAttributeDataForStyleable(this, context, iArr2, attributeSet, obtainStyledAttributes2, i2, 0);
            z = obtainStyledAttributes2.getBoolean(0, true);
            obtainStyledAttributes2.recycle();
        }
        setNestedScrollingEnabled(z);
    }

    @Nullable
    static RecyclerView B(@NonNull View view) {
        if (view instanceof ViewGroup) {
            if (view instanceof RecyclerView) {
                return (RecyclerView) view;
            }
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                RecyclerView B = B(viewGroup.getChildAt(i2));
                if (B != null) {
                    return B;
                }
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ViewHolder F(View view) {
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).f3728a;
    }

    static void G(View view, Rect rect) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect2 = layoutParams.f3729b;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
    }

    private void addAnimatingView(ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        boolean z = view.getParent() == this;
        this.f3689a.z(getChildViewHolder(view));
        if (viewHolder.isTmpDetached()) {
            this.f3692d.c(view, -1, view.getLayoutParams(), true);
            return;
        }
        ChildHelper childHelper = this.f3692d;
        if (z) {
            childHelper.j(view);
        } else {
            childHelper.b(view, true);
        }
    }

    private void animateChange(@NonNull ViewHolder viewHolder, @NonNull ViewHolder viewHolder2, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo2, boolean z, boolean z2) {
        viewHolder.setIsRecyclable(false);
        if (z) {
            addAnimatingView(viewHolder);
        }
        if (viewHolder != viewHolder2) {
            if (z2) {
                addAnimatingView(viewHolder2);
            }
            viewHolder.mShadowedHolder = viewHolder2;
            addAnimatingView(viewHolder);
            this.f3689a.z(viewHolder);
            viewHolder2.setIsRecyclable(false);
            viewHolder2.mShadowingHolder = viewHolder;
        }
        if (this.x.animateChange(viewHolder, viewHolder2, itemHolderInfo, itemHolderInfo2)) {
            V();
        }
    }

    private void cancelScroll() {
        resetScroll();
        setScrollState(0);
    }

    private void createLayoutManager(Context context, String str, AttributeSet attributeSet, int i2, int i3) {
        Constructor constructor;
        if (str != null) {
            String trim = str.trim();
            if (trim.isEmpty()) {
                return;
            }
            String fullClassName = getFullClassName(context, trim);
            try {
                Class<? extends U> asSubclass = Class.forName(fullClassName, false, isInEditMode() ? getClass().getClassLoader() : context.getClassLoader()).asSubclass(LayoutManager.class);
                Object[] objArr = null;
                try {
                    constructor = asSubclass.getConstructor(LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
                    objArr = new Object[]{context, attributeSet, Integer.valueOf(i2), Integer.valueOf(i3)};
                } catch (NoSuchMethodException e2) {
                    try {
                        constructor = asSubclass.getConstructor(new Class[0]);
                    } catch (NoSuchMethodException e3) {
                        e3.initCause(e2);
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + fullClassName, e3);
                    }
                }
                constructor.setAccessible(true);
                setLayoutManager((LayoutManager) constructor.newInstance(objArr));
            } catch (ClassCastException e4) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + fullClassName, e4);
            } catch (ClassNotFoundException e5) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + fullClassName, e5);
            } catch (IllegalAccessException e6) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + fullClassName, e6);
            } catch (InstantiationException e7) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + fullClassName, e7);
            } catch (InvocationTargetException e8) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + fullClassName, e8);
            }
        }
    }

    private boolean didChildRangeChange(int i2, int i3) {
        findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        int[] iArr = this.mMinMaxLayoutPositions;
        return (iArr[0] == i2 && iArr[1] == i3) ? false : true;
    }

    private void dispatchContentChangedIfNecessary() {
        int i2 = this.mEatenAccessibilityChangeFlags;
        this.mEatenAccessibilityChangeFlags = 0;
        if (i2 == 0 || !L()) {
            return;
        }
        AccessibilityEvent obtain = AccessibilityEvent.obtain();
        obtain.setEventType(2048);
        AccessibilityEventCompat.setContentChangeTypes(obtain, i2);
        sendAccessibilityEventUnchecked(obtain);
    }

    private void dispatchLayoutStep1() {
        boolean z = true;
        this.B.a(1);
        A(this.B);
        this.B.f3753i = false;
        h0();
        this.f3693e.f();
        S();
        processAdapterUpdatesAndSetAnimationFlags();
        saveFocusInfo();
        State state = this.B;
        state.f3752h = (state.f3754j && this.D) ? false : false;
        this.D = false;
        this.C = false;
        state.f3751g = state.f3755k;
        state.f3749e = this.f3698j.getItemCount();
        findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        if (this.B.f3754j) {
            int g2 = this.f3692d.g();
            for (int i2 = 0; i2 < g2; i2++) {
                ViewHolder F = F(this.f3692d.f(i2));
                if (!F.shouldIgnore() && (!F.isInvalid() || this.f3698j.hasStableIds())) {
                    this.f3693e.e(F, this.x.recordPreLayoutInformation(this.B, F, ItemAnimator.buildAdapterChangeFlagsForAnimations(F), F.getUnmodifiedPayloads()));
                    if (this.B.f3752h && F.isUpdated() && !F.isRemoved() && !F.shouldIgnore() && !F.isInvalid()) {
                        this.f3693e.c(E(F), F);
                    }
                }
            }
        }
        if (this.B.f3755k) {
            b0();
            State state2 = this.B;
            boolean z2 = state2.f3750f;
            state2.f3750f = false;
            this.f3699k.onLayoutChildren(this.f3689a, state2);
            this.B.f3750f = z2;
            for (int i3 = 0; i3 < this.f3692d.g(); i3++) {
                ViewHolder F2 = F(this.f3692d.f(i3));
                if (!F2.shouldIgnore() && !this.f3693e.i(F2)) {
                    int buildAdapterChangeFlagsForAnimations = ItemAnimator.buildAdapterChangeFlagsForAnimations(F2);
                    boolean hasAnyOfTheFlags = F2.hasAnyOfTheFlags(8192);
                    if (!hasAnyOfTheFlags) {
                        buildAdapterChangeFlagsForAnimations |= 4096;
                    }
                    ItemAnimator.ItemHolderInfo recordPreLayoutInformation = this.x.recordPreLayoutInformation(this.B, F2, buildAdapterChangeFlagsForAnimations, F2.getUnmodifiedPayloads());
                    if (hasAnyOfTheFlags) {
                        X(F2, recordPreLayoutInformation);
                    } else {
                        this.f3693e.a(F2, recordPreLayoutInformation);
                    }
                }
            }
        }
        l();
        T();
        i0(false);
        this.B.f3748d = 2;
    }

    private void dispatchLayoutStep2() {
        h0();
        S();
        this.B.a(6);
        this.f3691c.b();
        this.B.f3749e = this.f3698j.getItemCount();
        this.B.f3747c = 0;
        if (this.f3690b != null && this.f3698j.canRestoreState()) {
            Parcelable parcelable = this.f3690b.f3744a;
            if (parcelable != null) {
                this.f3699k.onRestoreInstanceState(parcelable);
            }
            this.f3690b = null;
        }
        State state = this.B;
        state.f3751g = false;
        this.f3699k.onLayoutChildren(this.f3689a, state);
        State state2 = this.B;
        state2.f3750f = false;
        state2.f3754j = state2.f3754j && this.x != null;
        state2.f3748d = 4;
        T();
        i0(false);
    }

    private void dispatchLayoutStep3() {
        this.B.a(4);
        h0();
        S();
        State state = this.B;
        state.f3748d = 1;
        if (state.f3754j) {
            for (int g2 = this.f3692d.g() - 1; g2 >= 0; g2--) {
                ViewHolder F = F(this.f3692d.f(g2));
                if (!F.shouldIgnore()) {
                    long E = E(F);
                    ItemAnimator.ItemHolderInfo recordPostLayoutInformation = this.x.recordPostLayoutInformation(this.B, F);
                    ViewHolder g3 = this.f3693e.g(E);
                    if (g3 != null && !g3.shouldIgnore()) {
                        boolean h2 = this.f3693e.h(g3);
                        boolean h3 = this.f3693e.h(F);
                        if (!h2 || g3 != F) {
                            ItemAnimator.ItemHolderInfo l2 = this.f3693e.l(g3);
                            this.f3693e.d(F, recordPostLayoutInformation);
                            ItemAnimator.ItemHolderInfo k2 = this.f3693e.k(F);
                            if (l2 == null) {
                                handleMissingPreInfoForChangeError(E, F, g3);
                            } else {
                                animateChange(g3, F, l2, k2, h2, h3);
                            }
                        }
                    }
                    this.f3693e.d(F, recordPostLayoutInformation);
                }
            }
            this.f3693e.m(this.mViewInfoProcessCallback);
        }
        this.f3699k.i(this.f3689a);
        State state2 = this.B;
        state2.f3746b = state2.f3749e;
        this.v = false;
        this.w = false;
        state2.f3754j = false;
        state2.f3755k = false;
        this.f3699k.f3721f = false;
        ArrayList arrayList = this.f3689a.f3738b;
        if (arrayList != null) {
            arrayList.clear();
        }
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager.f3725j) {
            layoutManager.f3724i = 0;
            layoutManager.f3725j = false;
            this.f3689a.A();
        }
        this.f3699k.onLayoutCompleted(this.B);
        T();
        i0(false);
        this.f3693e.f();
        int[] iArr = this.mMinMaxLayoutPositions;
        if (didChildRangeChange(iArr[0], iArr[1])) {
            t(0, 0);
        }
        recoverFocusFromState();
        resetFocusInfo();
    }

    private boolean dispatchToOnItemTouchListeners(MotionEvent motionEvent) {
        OnItemTouchListener onItemTouchListener = this.mInterceptingOnItemTouchListener;
        if (onItemTouchListener == null) {
            if (motionEvent.getAction() == 0) {
                return false;
            }
            return findInterceptingOnItemTouchListener(motionEvent);
        }
        onItemTouchListener.onTouchEvent(this, motionEvent);
        int action = motionEvent.getAction();
        if (action == 3 || action == 1) {
            this.mInterceptingOnItemTouchListener = null;
        }
        return true;
    }

    private boolean findInterceptingOnItemTouchListener(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int size = this.mOnItemTouchListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            OnItemTouchListener onItemTouchListener = this.mOnItemTouchListeners.get(i2);
            if (onItemTouchListener.onInterceptTouchEvent(this, motionEvent) && action != 3) {
                this.mInterceptingOnItemTouchListener = onItemTouchListener;
                return true;
            }
        }
        return false;
    }

    private void findMinMaxChildLayoutPositions(int[] iArr) {
        int g2 = this.f3692d.g();
        if (g2 == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MIN_VALUE;
        for (int i4 = 0; i4 < g2; i4++) {
            ViewHolder F = F(this.f3692d.f(i4));
            if (!F.shouldIgnore()) {
                int layoutPosition = F.getLayoutPosition();
                if (layoutPosition < i2) {
                    i2 = layoutPosition;
                }
                if (layoutPosition > i3) {
                    i3 = layoutPosition;
                }
            }
        }
        iArr[0] = i2;
        iArr[1] = i3;
    }

    @Nullable
    private View findNextViewToFocus() {
        ViewHolder findViewHolderForAdapterPosition;
        State state = this.B;
        int i2 = state.f3756l;
        if (i2 == -1) {
            i2 = 0;
        }
        int itemCount = state.getItemCount();
        for (int i3 = i2; i3 < itemCount; i3++) {
            ViewHolder findViewHolderForAdapterPosition2 = findViewHolderForAdapterPosition(i3);
            if (findViewHolderForAdapterPosition2 == null) {
                break;
            } else if (findViewHolderForAdapterPosition2.itemView.hasFocusable()) {
                return findViewHolderForAdapterPosition2.itemView;
            }
        }
        int min = Math.min(itemCount, i2);
        while (true) {
            min--;
            if (min < 0 || (findViewHolderForAdapterPosition = findViewHolderForAdapterPosition(min)) == null) {
                return null;
            }
            if (findViewHolderForAdapterPosition.itemView.hasFocusable()) {
                return findViewHolderForAdapterPosition.itemView;
            }
        }
    }

    private int getDeepestFocusedViewWithId(View view) {
        int id;
        loop0: while (true) {
            id = view.getId();
            while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
                view = ((ViewGroup) view).getFocusedChild();
                if (view.getId() != -1) {
                    break;
                }
            }
        }
        return id;
    }

    private String getFullClassName(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        } else if (str.contains(".")) {
            return str;
        } else {
            return RecyclerView.class.getPackage().getName() + '.' + str;
        }
    }

    private NestedScrollingChildHelper getScrollingChildHelper() {
        if (this.mScrollingChildHelper == null) {
            this.mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        return this.mScrollingChildHelper;
    }

    private void handleMissingPreInfoForChangeError(long j2, ViewHolder viewHolder, ViewHolder viewHolder2) {
        int g2 = this.f3692d.g();
        for (int i2 = 0; i2 < g2; i2++) {
            ViewHolder F = F(this.f3692d.f(i2));
            if (F != viewHolder && E(F) == j2) {
                Adapter adapter = this.f3698j;
                if (adapter == null || !adapter.hasStableIds()) {
                    throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + F + " \n View Holder 2:" + viewHolder + z());
                }
                throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + F + " \n View Holder 2:" + viewHolder + z());
            }
        }
        Log.e("RecyclerView", "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + viewHolder2 + " cannot be found but it is necessary for " + viewHolder + z());
    }

    private boolean hasUpdatedView() {
        int g2 = this.f3692d.g();
        for (int i2 = 0; i2 < g2; i2++) {
            ViewHolder F = F(this.f3692d.f(i2));
            if (F != null && !F.shouldIgnore() && F.isUpdated()) {
                return true;
            }
        }
        return false;
    }

    @SuppressLint({"InlinedApi"})
    private void initAutofill() {
        if (ViewCompat.getImportantForAutofill(this) == 0) {
            ViewCompat.setImportantForAutofill(this, 8);
        }
    }

    private void initChildrenHelper() {
        this.f3692d = new ChildHelper(new ChildHelper.Callback() { // from class: androidx.recyclerview.widget.RecyclerView.5
            @Override // androidx.recyclerview.widget.ChildHelper.Callback
            public void addView(View view, int i2) {
                RecyclerView.this.addView(view, i2);
                RecyclerView.this.p(view);
            }

            @Override // androidx.recyclerview.widget.ChildHelper.Callback
            public void attachViewToParent(View view, int i2, ViewGroup.LayoutParams layoutParams) {
                ViewHolder F = RecyclerView.F(view);
                if (F != null) {
                    if (!F.isTmpDetached() && !F.shouldIgnore()) {
                        throw new IllegalArgumentException("Called attach on a child which is not detached: " + F + RecyclerView.this.z());
                    }
                    F.clearTmpDetachFlag();
                }
                RecyclerView.this.attachViewToParent(view, i2, layoutParams);
            }

            @Override // androidx.recyclerview.widget.ChildHelper.Callback
            public void detachViewFromParent(int i2) {
                ViewHolder F;
                View childAt = getChildAt(i2);
                if (childAt != null && (F = RecyclerView.F(childAt)) != null) {
                    if (F.isTmpDetached() && !F.shouldIgnore()) {
                        throw new IllegalArgumentException("called detach on an already detached child " + F + RecyclerView.this.z());
                    }
                    F.addFlags(256);
                }
                RecyclerView.this.detachViewFromParent(i2);
            }

            @Override // androidx.recyclerview.widget.ChildHelper.Callback
            public View getChildAt(int i2) {
                return RecyclerView.this.getChildAt(i2);
            }

            @Override // androidx.recyclerview.widget.ChildHelper.Callback
            public int getChildCount() {
                return RecyclerView.this.getChildCount();
            }

            @Override // androidx.recyclerview.widget.ChildHelper.Callback
            public ViewHolder getChildViewHolder(View view) {
                return RecyclerView.F(view);
            }

            @Override // androidx.recyclerview.widget.ChildHelper.Callback
            public int indexOfChild(View view) {
                return RecyclerView.this.indexOfChild(view);
            }

            @Override // androidx.recyclerview.widget.ChildHelper.Callback
            public void onEnteredHiddenState(View view) {
                ViewHolder F = RecyclerView.F(view);
                if (F != null) {
                    F.onEnteredHiddenState(RecyclerView.this);
                }
            }

            @Override // androidx.recyclerview.widget.ChildHelper.Callback
            public void onLeftHiddenState(View view) {
                ViewHolder F = RecyclerView.F(view);
                if (F != null) {
                    F.onLeftHiddenState(RecyclerView.this);
                }
            }

            @Override // androidx.recyclerview.widget.ChildHelper.Callback
            public void removeAllViews() {
                int childCount = getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = getChildAt(i2);
                    RecyclerView.this.q(childAt);
                    childAt.clearAnimation();
                }
                RecyclerView.this.removeAllViews();
            }

            @Override // androidx.recyclerview.widget.ChildHelper.Callback
            public void removeViewAt(int i2) {
                View childAt = RecyclerView.this.getChildAt(i2);
                if (childAt != null) {
                    RecyclerView.this.q(childAt);
                    childAt.clearAnimation();
                }
                RecyclerView.this.removeViewAt(i2);
            }
        });
    }

    private boolean isPreferredNextFocus(View view, View view2, int i2) {
        int i3;
        if (view2 == null || view2 == this || view2 == view || findContainingItemView(view2) == null) {
            return false;
        }
        if (view == null || findContainingItemView(view) == null) {
            return true;
        }
        this.f3696h.set(0, 0, view.getWidth(), view.getHeight());
        this.mTempRect2.set(0, 0, view2.getWidth(), view2.getHeight());
        offsetDescendantRectToMyCoords(view, this.f3696h);
        offsetDescendantRectToMyCoords(view2, this.mTempRect2);
        char c2 = 65535;
        int i4 = this.f3699k.getLayoutDirection() == 1 ? -1 : 1;
        Rect rect = this.f3696h;
        int i5 = rect.left;
        Rect rect2 = this.mTempRect2;
        int i6 = rect2.left;
        if ((i5 < i6 || rect.right <= i6) && rect.right < rect2.right) {
            i3 = 1;
        } else {
            int i7 = rect.right;
            int i8 = rect2.right;
            i3 = ((i7 > i8 || i5 >= i8) && i5 > i6) ? -1 : 0;
        }
        int i9 = rect.top;
        int i10 = rect2.top;
        if ((i9 < i10 || rect.bottom <= i10) && rect.bottom < rect2.bottom) {
            c2 = 1;
        } else {
            int i11 = rect.bottom;
            int i12 = rect2.bottom;
            if ((i11 <= i12 && i9 < i12) || i9 <= i10) {
                c2 = 0;
            }
        }
        if (i2 == 1) {
            return c2 < 0 || (c2 == 0 && i3 * i4 < 0);
        } else if (i2 == 2) {
            return c2 > 0 || (c2 == 0 && i3 * i4 > 0);
        } else if (i2 == 17) {
            return i3 < 0;
        } else if (i2 == 33) {
            return c2 < 0;
        } else if (i2 == 66) {
            return i3 > 0;
        } else if (i2 == 130) {
            return c2 > 0;
        } else {
            throw new IllegalArgumentException("Invalid direction: " + i2 + z());
        }
    }

    static void k(@NonNull ViewHolder viewHolder) {
        WeakReference<RecyclerView> weakReference = viewHolder.mNestedRecyclerView;
        if (weakReference != null) {
            ViewParent viewParent = weakReference.get();
            while (true) {
                for (View view = (View) viewParent; view != null; view = null) {
                    if (view == viewHolder.itemView) {
                        return;
                    }
                    viewParent = view.getParent();
                    if (viewParent instanceof View) {
                        break;
                    }
                }
                viewHolder.mNestedRecyclerView = null;
                return;
            }
        }
    }

    private void nestedScrollByInternal(int i2, int i3, @Nullable MotionEvent motionEvent, int i4) {
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (this.f3708t) {
        } else {
            int[] iArr = this.G;
            iArr[0] = 0;
            iArr[1] = 0;
            boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
            boolean canScrollVertically = this.f3699k.canScrollVertically();
            startNestedScroll(canScrollVertically ? canScrollHorizontally | 2 : canScrollHorizontally, i4);
            if (dispatchNestedPreScroll(canScrollHorizontally != 0 ? i2 : 0, canScrollVertically ? i3 : 0, this.G, this.mScrollOffset, i4)) {
                int[] iArr2 = this.G;
                i2 -= iArr2[0];
                i3 -= iArr2[1];
            }
            c0(canScrollHorizontally != 0 ? i2 : 0, canScrollVertically ? i3 : 0, motionEvent, i4);
            GapWorker gapWorker = this.z;
            if (gapWorker != null && (i2 != 0 || i3 != 0)) {
                gapWorker.b(this, i2, i3);
            }
            stopNestedScroll(i4);
        }
    }

    private void onPointerUp(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mScrollPointerId) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.mScrollPointerId = motionEvent.getPointerId(i2);
            int x = (int) (motionEvent.getX(i2) + 0.5f);
            this.mLastTouchX = x;
            this.mInitialTouchX = x;
            int y = (int) (motionEvent.getY(i2) + 0.5f);
            this.mLastTouchY = y;
            this.mInitialTouchY = y;
        }
    }

    private boolean predictiveItemAnimationsEnabled() {
        return this.x != null && this.f3699k.supportsPredictiveItemAnimations();
    }

    private void processAdapterUpdatesAndSetAnimationFlags() {
        boolean z;
        if (this.v) {
            this.f3691c.o();
            if (this.w) {
                this.f3699k.onItemsChanged(this);
            }
        }
        if (predictiveItemAnimationsEnabled()) {
            this.f3691c.m();
        } else {
            this.f3691c.b();
        }
        boolean z2 = false;
        boolean z3 = this.C || this.D;
        this.B.f3754j = this.f3706r && this.x != null && ((z = this.v) || z3 || this.f3699k.f3721f) && (!z || this.f3698j.hasStableIds());
        State state = this.B;
        if (state.f3754j && z3 && !this.v && predictiveItemAnimationsEnabled()) {
            z2 = true;
        }
        state.f3755k = z2;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void pullGlows(float f2, float f3, float f4, float f5) {
        boolean z;
        EdgeEffect edgeEffect;
        float width;
        float height;
        boolean z2 = true;
        if (f3 < 0.0f) {
            w();
            edgeEffect = this.mLeftGlow;
            width = (-f3) / getWidth();
            height = 1.0f - (f4 / getHeight());
        } else if (f3 <= 0.0f) {
            z = false;
            if (f5 >= 0.0f) {
                y();
                EdgeEffectCompat.onPull(this.mTopGlow, (-f5) / getHeight(), f2 / getWidth());
            } else if (f5 > 0.0f) {
                v();
                EdgeEffectCompat.onPull(this.mBottomGlow, f5 / getHeight(), 1.0f - (f2 / getWidth()));
            } else {
                z2 = z;
            }
            if (z2 && f3 == 0.0f && f5 == 0.0f) {
                return;
            }
            ViewCompat.postInvalidateOnAnimation(this);
        } else {
            x();
            edgeEffect = this.mRightGlow;
            width = f3 / getWidth();
            height = f4 / getHeight();
        }
        EdgeEffectCompat.onPull(edgeEffect, width, height);
        z = true;
        if (f5 >= 0.0f) {
        }
        if (z2) {
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    private void recoverFocusFromState() {
        View findViewById;
        if (!this.mPreserveFocusAfterLayout || this.f3698j == null || !hasFocus() || getDescendantFocusability() == 393216) {
            return;
        }
        if (getDescendantFocusability() == 131072 && isFocused()) {
            return;
        }
        if (!isFocused()) {
            View focusedChild = getFocusedChild();
            if (!IGNORE_DETACHED_FOCUSED_CHILD || (focusedChild.getParent() != null && focusedChild.hasFocus())) {
                if (!this.f3692d.l(focusedChild)) {
                    return;
                }
            } else if (this.f3692d.g() == 0) {
                requestFocus();
                return;
            }
        }
        View view = null;
        ViewHolder findViewHolderForItemId = (this.B.f3757m == -1 || !this.f3698j.hasStableIds()) ? null : findViewHolderForItemId(this.B.f3757m);
        if (findViewHolderForItemId != null && !this.f3692d.l(findViewHolderForItemId.itemView) && findViewHolderForItemId.itemView.hasFocusable()) {
            view = findViewHolderForItemId.itemView;
        } else if (this.f3692d.g() > 0) {
            view = findNextViewToFocus();
        }
        if (view != null) {
            int i2 = this.B.f3758n;
            if (i2 != -1 && (findViewById = view.findViewById(i2)) != null && findViewById.isFocusable()) {
                view = findViewById;
            }
            view.requestFocus();
        }
    }

    private void releaseGlows() {
        boolean z;
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            z = this.mLeftGlow.isFinished();
        } else {
            z = false;
        }
        EdgeEffect edgeEffect2 = this.mTopGlow;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            z |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mRightGlow;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            z |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            z |= this.mBottomGlow.isFinished();
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private void requestChildOnScreen(@NonNull View view, @Nullable View view2) {
        View view3 = view2 != null ? view2 : view;
        this.f3696h.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            if (!layoutParams2.f3730c) {
                Rect rect = layoutParams2.f3729b;
                Rect rect2 = this.f3696h;
                rect2.left -= rect.left;
                rect2.right += rect.right;
                rect2.top -= rect.top;
                rect2.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.f3696h);
            offsetRectIntoDescendantCoords(view, this.f3696h);
        }
        this.f3699k.requestChildRectangleOnScreen(this, view, this.f3696h, !this.f3706r, view2 == null);
    }

    private void resetFocusInfo() {
        State state = this.B;
        state.f3757m = -1L;
        state.f3756l = -1;
        state.f3758n = -1;
    }

    private void resetScroll() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        stopNestedScroll(0);
        releaseGlows();
    }

    private void saveFocusInfo() {
        View focusedChild = (this.mPreserveFocusAfterLayout && hasFocus() && this.f3698j != null) ? getFocusedChild() : null;
        ViewHolder findContainingViewHolder = focusedChild != null ? findContainingViewHolder(focusedChild) : null;
        if (findContainingViewHolder == null) {
            resetFocusInfo();
            return;
        }
        this.B.f3757m = this.f3698j.hasStableIds() ? findContainingViewHolder.getItemId() : -1L;
        this.B.f3756l = this.v ? -1 : findContainingViewHolder.isRemoved() ? findContainingViewHolder.mOldPosition : findContainingViewHolder.getAbsoluteAdapterPosition();
        this.B.f3758n = getDeepestFocusedViewWithId(findContainingViewHolder.itemView);
    }

    private void setAdapterInternal(@Nullable Adapter adapter, boolean z, boolean z2) {
        Adapter adapter2 = this.f3698j;
        if (adapter2 != null) {
            adapter2.unregisterAdapterDataObserver(this.mObserver);
            this.f3698j.onDetachedFromRecyclerView(this);
        }
        if (!z || z2) {
            Y();
        }
        this.f3691c.o();
        Adapter adapter3 = this.f3698j;
        this.f3698j = adapter;
        if (adapter != null) {
            adapter.registerAdapterDataObserver(this.mObserver);
            adapter.onAttachedToRecyclerView(this);
        }
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager != null) {
            layoutManager.onAdapterChanged(adapter3, this.f3698j);
        }
        this.f3689a.q(adapter3, this.f3698j, z);
        this.B.f3750f = true;
    }

    private void stopScrollersInternal() {
        this.y.stop();
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager != null) {
            layoutManager.q();
        }
    }

    final void A(State state) {
        if (getScrollState() != 2) {
            state.f3759o = 0;
            state.f3760p = 0;
            return;
        }
        OverScroller overScroller = this.y.f3761a;
        state.f3759o = overScroller.getFinalX() - overScroller.getCurrX();
        state.f3760p = overScroller.getFinalY() - overScroller.getCurrY();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0036 A[SYNTHETIC] */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    ViewHolder C(int i2, boolean z) {
        int i3 = this.f3692d.i();
        ViewHolder viewHolder = null;
        for (int i4 = 0; i4 < i3; i4++) {
            ViewHolder F = F(this.f3692d.h(i4));
            if (F != null && !F.isRemoved()) {
                if (z) {
                    if (F.mPosition != i2) {
                        continue;
                    }
                    if (this.f3692d.l(F.itemView)) {
                        return F;
                    }
                    viewHolder = F;
                } else {
                    if (F.getLayoutPosition() != i2) {
                        continue;
                    }
                    if (this.f3692d.l(F.itemView)) {
                    }
                }
            }
        }
        return viewHolder;
    }

    int D(ViewHolder viewHolder) {
        if (viewHolder.hasAnyOfTheFlags(524) || !viewHolder.isBound()) {
            return -1;
        }
        return this.f3691c.applyPendingUpdatesToPosition(viewHolder.mPosition);
    }

    long E(ViewHolder viewHolder) {
        return this.f3698j.hasStableIds() ? viewHolder.getItemId() : viewHolder.mPosition;
    }

    Rect H(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams.f3730c) {
            if (this.B.isPreLayout() && (layoutParams.isItemChanged() || layoutParams.isViewInvalid())) {
                return layoutParams.f3729b;
            }
            Rect rect = layoutParams.f3729b;
            rect.set(0, 0, 0, 0);
            int size = this.f3702n.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f3696h.set(0, 0, 0, 0);
                ((ItemDecoration) this.f3702n.get(i2)).getItemOffsets(this.f3696h, view, this, this.B);
                int i3 = rect.left;
                Rect rect2 = this.f3696h;
                rect.left = i3 + rect2.left;
                rect.top += rect2.top;
                rect.right += rect2.right;
                rect.bottom += rect2.bottom;
            }
            layoutParams.f3730c = false;
            return rect;
        }
        return layoutParams.f3729b;
    }

    void I() {
        this.f3691c = new AdapterHelper(new AdapterHelper.Callback() { // from class: androidx.recyclerview.widget.RecyclerView.6
            void a(AdapterHelper.UpdateOp updateOp) {
                int i2 = updateOp.f3479a;
                if (i2 == 1) {
                    RecyclerView recyclerView = RecyclerView.this;
                    recyclerView.f3699k.onItemsAdded(recyclerView, updateOp.f3480b, updateOp.f3482d);
                } else if (i2 == 2) {
                    RecyclerView recyclerView2 = RecyclerView.this;
                    recyclerView2.f3699k.onItemsRemoved(recyclerView2, updateOp.f3480b, updateOp.f3482d);
                } else if (i2 == 4) {
                    RecyclerView recyclerView3 = RecyclerView.this;
                    recyclerView3.f3699k.onItemsUpdated(recyclerView3, updateOp.f3480b, updateOp.f3482d, updateOp.f3481c);
                } else if (i2 != 8) {
                } else {
                    RecyclerView recyclerView4 = RecyclerView.this;
                    recyclerView4.f3699k.onItemsMoved(recyclerView4, updateOp.f3480b, updateOp.f3482d, 1);
                }
            }

            @Override // androidx.recyclerview.widget.AdapterHelper.Callback
            public ViewHolder findViewHolder(int i2) {
                ViewHolder C = RecyclerView.this.C(i2, true);
                if (C == null || RecyclerView.this.f3692d.l(C.itemView)) {
                    return null;
                }
                return C;
            }

            @Override // androidx.recyclerview.widget.AdapterHelper.Callback
            public void markViewHoldersUpdated(int i2, int i3, Object obj) {
                RecyclerView.this.j0(i2, i3, obj);
                RecyclerView.this.D = true;
            }

            @Override // androidx.recyclerview.widget.AdapterHelper.Callback
            public void offsetPositionsForAdd(int i2, int i3) {
                RecyclerView.this.P(i2, i3);
                RecyclerView.this.C = true;
            }

            @Override // androidx.recyclerview.widget.AdapterHelper.Callback
            public void offsetPositionsForMove(int i2, int i3) {
                RecyclerView.this.Q(i2, i3);
                RecyclerView.this.C = true;
            }

            @Override // androidx.recyclerview.widget.AdapterHelper.Callback
            public void offsetPositionsForRemovingInvisible(int i2, int i3) {
                RecyclerView.this.R(i2, i3, true);
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.C = true;
                recyclerView.B.f3747c += i3;
            }

            @Override // androidx.recyclerview.widget.AdapterHelper.Callback
            public void offsetPositionsForRemovingLaidOutOrNewView(int i2, int i3) {
                RecyclerView.this.R(i2, i3, false);
                RecyclerView.this.C = true;
            }

            @Override // androidx.recyclerview.widget.AdapterHelper.Callback
            public void onDispatchFirstPass(AdapterHelper.UpdateOp updateOp) {
                a(updateOp);
            }

            @Override // androidx.recyclerview.widget.AdapterHelper.Callback
            public void onDispatchSecondPass(AdapterHelper.UpdateOp updateOp) {
                a(updateOp);
            }
        });
    }

    @VisibleForTesting
    void J(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable != null && drawable != null && stateListDrawable2 != null && drawable2 != null) {
            Resources resources = getContext().getResources();
            new FastScroller(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(R.dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(R.dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(R.dimen.fastscroll_margin));
            return;
        }
        throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + z());
    }

    void K() {
        this.mBottomGlow = null;
        this.mTopGlow = null;
        this.mRightGlow = null;
        this.mLeftGlow = null;
    }

    boolean L() {
        AccessibilityManager accessibilityManager = this.mAccessibilityManager;
        return accessibilityManager != null && accessibilityManager.isEnabled();
    }

    void M(int i2) {
        if (this.f3699k == null) {
            return;
        }
        setScrollState(2);
        this.f3699k.scrollToPosition(i2);
        awakenScrollBars();
    }

    void N() {
        int i2 = this.f3692d.i();
        for (int i3 = 0; i3 < i2; i3++) {
            ((LayoutParams) this.f3692d.h(i3).getLayoutParams()).f3730c = true;
        }
        this.f3689a.l();
    }

    void O() {
        int i2 = this.f3692d.i();
        for (int i3 = 0; i3 < i2; i3++) {
            ViewHolder F = F(this.f3692d.h(i3));
            if (F != null && !F.shouldIgnore()) {
                F.addFlags(6);
            }
        }
        N();
        this.f3689a.m();
    }

    void P(int i2, int i3) {
        int i4 = this.f3692d.i();
        for (int i5 = 0; i5 < i4; i5++) {
            ViewHolder F = F(this.f3692d.h(i5));
            if (F != null && !F.shouldIgnore() && F.mPosition >= i2) {
                F.offsetPosition(i3, false);
                this.B.f3750f = true;
            }
        }
        this.f3689a.n(i2, i3);
        requestLayout();
    }

    void Q(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8 = this.f3692d.i();
        if (i2 < i3) {
            i6 = -1;
            i5 = i2;
            i4 = i3;
        } else {
            i4 = i2;
            i5 = i3;
            i6 = 1;
        }
        for (int i9 = 0; i9 < i8; i9++) {
            ViewHolder F = F(this.f3692d.h(i9));
            if (F != null && (i7 = F.mPosition) >= i5 && i7 <= i4) {
                if (i7 == i2) {
                    F.offsetPosition(i3 - i2, false);
                } else {
                    F.offsetPosition(i6, false);
                }
                this.B.f3750f = true;
            }
        }
        this.f3689a.o(i2, i3);
        requestLayout();
    }

    void R(int i2, int i3, boolean z) {
        int i4 = i2 + i3;
        int i5 = this.f3692d.i();
        for (int i6 = 0; i6 < i5; i6++) {
            ViewHolder F = F(this.f3692d.h(i6));
            if (F != null && !F.shouldIgnore()) {
                int i7 = F.mPosition;
                if (i7 >= i4) {
                    F.offsetPosition(-i3, z);
                } else if (i7 >= i2) {
                    F.flagRemovedAndOffsetPosition(i2 - 1, -i3, z);
                }
                this.B.f3750f = true;
            }
        }
        this.f3689a.p(i2, i3, z);
        requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void S() {
        this.mLayoutOrScrollCounter++;
    }

    void T() {
        U(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void U(boolean z) {
        int i2 = this.mLayoutOrScrollCounter - 1;
        this.mLayoutOrScrollCounter = i2;
        if (i2 < 1) {
            this.mLayoutOrScrollCounter = 0;
            if (z) {
                dispatchContentChangedIfNecessary();
                u();
            }
        }
    }

    void V() {
        if (this.E || !this.f3703o) {
            return;
        }
        ViewCompat.postOnAnimation(this, this.mItemAnimatorRunner);
        this.E = true;
    }

    void W(boolean z) {
        this.w = z | this.w;
        this.v = true;
        O();
    }

    void X(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo) {
        viewHolder.setFlags(0, 8192);
        if (this.B.f3752h && viewHolder.isUpdated() && !viewHolder.isRemoved() && !viewHolder.shouldIgnore()) {
            this.f3693e.c(E(viewHolder), viewHolder);
        }
        this.f3693e.e(viewHolder, itemHolderInfo);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void Y() {
        ItemAnimator itemAnimator = this.x;
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager != null) {
            layoutManager.removeAndRecycleAllViews(this.f3689a);
            this.f3699k.i(this.f3689a);
        }
        this.f3689a.clear();
    }

    boolean Z(View view) {
        h0();
        boolean p2 = this.f3692d.p(view);
        if (p2) {
            ViewHolder F = F(view);
            this.f3689a.z(F);
            this.f3689a.u(F);
        }
        i0(!p2);
        return p2;
    }

    void a(int i2, int i3) {
        if (i2 < 0) {
            w();
            if (this.mLeftGlow.isFinished()) {
                this.mLeftGlow.onAbsorb(-i2);
            }
        } else if (i2 > 0) {
            x();
            if (this.mRightGlow.isFinished()) {
                this.mRightGlow.onAbsorb(i2);
            }
        }
        if (i3 < 0) {
            y();
            if (this.mTopGlow.isFinished()) {
                this.mTopGlow.onAbsorb(-i3);
            }
        } else if (i3 > 0) {
            v();
            if (this.mBottomGlow.isFinished()) {
                this.mBottomGlow.onAbsorb(i3);
            }
        }
        if (i2 == 0 && i3 == 0) {
            return;
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    void a0() {
        ViewHolder viewHolder;
        int g2 = this.f3692d.g();
        for (int i2 = 0; i2 < g2; i2++) {
            View f2 = this.f3692d.f(i2);
            ViewHolder childViewHolder = getChildViewHolder(f2);
            if (childViewHolder != null && (viewHolder = childViewHolder.mShadowingHolder) != null) {
                View view = viewHolder.itemView;
                int left = f2.getLeft();
                int top = f2.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager == null || !layoutManager.onAddFocusables(this, arrayList, i2, i3)) {
            super.addFocusables(arrayList, i2, i3);
        }
    }

    public void addItemDecoration(@NonNull ItemDecoration itemDecoration) {
        addItemDecoration(itemDecoration, -1);
    }

    public void addItemDecoration(@NonNull ItemDecoration itemDecoration, int i2) {
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager != null) {
            layoutManager.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
        }
        if (this.f3702n.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i2 < 0) {
            this.f3702n.add(itemDecoration);
        } else {
            this.f3702n.add(i2, itemDecoration);
        }
        N();
        requestLayout();
    }

    public void addOnChildAttachStateChangeListener(@NonNull OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        if (this.mOnChildAttachStateListeners == null) {
            this.mOnChildAttachStateListeners = new ArrayList();
        }
        this.mOnChildAttachStateListeners.add(onChildAttachStateChangeListener);
    }

    public void addOnItemTouchListener(@NonNull OnItemTouchListener onItemTouchListener) {
        this.mOnItemTouchListeners.add(onItemTouchListener);
    }

    public void addOnScrollListener(@NonNull OnScrollListener onScrollListener) {
        if (this.mScrollListeners == null) {
            this.mScrollListeners = new ArrayList();
        }
        this.mScrollListeners.add(onScrollListener);
    }

    public void addRecyclerListener(@NonNull RecyclerListener recyclerListener) {
        Preconditions.checkArgument(recyclerListener != null, "'listener' arg cannot be null.");
        this.f3701m.add(recyclerListener);
    }

    void b0() {
        int i2 = this.f3692d.i();
        for (int i3 = 0; i3 < i2; i3++) {
            ViewHolder F = F(this.f3692d.h(i3));
            if (!F.shouldIgnore()) {
                F.saveOldPosition();
            }
        }
    }

    boolean c0(int i2, int i3, MotionEvent motionEvent, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        n();
        if (this.f3698j != null) {
            int[] iArr = this.G;
            iArr[0] = 0;
            iArr[1] = 0;
            d0(i2, i3, iArr);
            int[] iArr2 = this.G;
            int i9 = iArr2[0];
            int i10 = iArr2[1];
            i5 = i10;
            i6 = i9;
            i7 = i2 - i9;
            i8 = i3 - i10;
        } else {
            i5 = 0;
            i6 = 0;
            i7 = 0;
            i8 = 0;
        }
        if (!this.f3702n.isEmpty()) {
            invalidate();
        }
        int[] iArr3 = this.G;
        iArr3[0] = 0;
        iArr3[1] = 0;
        dispatchNestedScroll(i6, i5, i7, i8, this.mScrollOffset, i4, iArr3);
        int[] iArr4 = this.G;
        int i11 = i7 - iArr4[0];
        int i12 = i8 - iArr4[1];
        boolean z = (iArr4[0] == 0 && iArr4[1] == 0) ? false : true;
        int i13 = this.mLastTouchX;
        int[] iArr5 = this.mScrollOffset;
        this.mLastTouchX = i13 - iArr5[0];
        this.mLastTouchY -= iArr5[1];
        int[] iArr6 = this.mNestedOffsets;
        iArr6[0] = iArr6[0] + iArr5[0];
        iArr6[1] = iArr6[1] + iArr5[1];
        if (getOverScrollMode() != 2) {
            if (motionEvent != null && !MotionEventCompat.isFromSource(motionEvent, 8194)) {
                pullGlows(motionEvent.getX(), i11, motionEvent.getY(), i12);
            }
            m(i2, i3);
        }
        if (i6 != 0 || i5 != 0) {
            t(i6, i5);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        return (!z && i6 == 0 && i5 == 0) ? false : true;
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && this.f3699k.checkLayoutParams((LayoutParams) layoutParams);
    }

    public void clearOnChildAttachStateChangeListeners() {
        List<OnChildAttachStateChangeListener> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            list.clear();
        }
    }

    public void clearOnScrollListeners() {
        List<OnScrollListener> list = this.mScrollListeners;
        if (list != null) {
            list.clear();
        }
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public int computeHorizontalScrollExtent() {
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.f3699k.computeHorizontalScrollExtent(this.B);
        }
        return 0;
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public int computeHorizontalScrollOffset() {
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.f3699k.computeHorizontalScrollOffset(this.B);
        }
        return 0;
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public int computeHorizontalScrollRange() {
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.f3699k.computeHorizontalScrollRange(this.B);
        }
        return 0;
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public int computeVerticalScrollExtent() {
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.f3699k.computeVerticalScrollExtent(this.B);
        }
        return 0;
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public int computeVerticalScrollOffset() {
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.f3699k.computeVerticalScrollOffset(this.B);
        }
        return 0;
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public int computeVerticalScrollRange() {
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.f3699k.computeVerticalScrollRange(this.B);
        }
        return 0;
    }

    void d0(int i2, int i3, @Nullable int[] iArr) {
        h0();
        S();
        TraceCompat.beginSection("RV Scroll");
        A(this.B);
        int scrollHorizontallyBy = i2 != 0 ? this.f3699k.scrollHorizontallyBy(i2, this.f3689a, this.B) : 0;
        int scrollVerticallyBy = i3 != 0 ? this.f3699k.scrollVerticallyBy(i3, this.f3689a, this.B) : 0;
        TraceCompat.endSection();
        a0();
        T();
        i0(false);
        if (iArr != null) {
            iArr[0] = scrollHorizontallyBy;
            iArr[1] = scrollVerticallyBy;
        }
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedFling(float f2, float f3, boolean z) {
        return getScrollingChildHelper().dispatchNestedFling(f2, f3, z);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f2, float f3) {
        return getScrollingChildHelper().dispatchNestedPreFling(f2, f3);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().dispatchNestedPreScroll(i2, i3, iArr, iArr2);
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        return getScrollingChildHelper().dispatchNestedPreScroll(i2, i3, iArr, iArr2, i4);
    }

    @Override // androidx.core.view.NestedScrollingChild3
    public final void dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr, int i6, @NonNull int[] iArr2) {
        getScrollingChildHelper().dispatchNestedScroll(i2, i3, i4, i5, iArr, i6, iArr2);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return getScrollingChildHelper().dispatchNestedScroll(i2, i3, i4, i5, iArr);
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr, int i6) {
        return getScrollingChildHelper().dispatchNestedScroll(i2, i3, i4, i5, iArr, i6);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchSaveInstanceState(SparseArray sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z;
        float f2;
        int i2;
        super.draw(canvas);
        int size = this.f3702n.size();
        boolean z2 = false;
        for (int i3 = 0; i3 < size; i3++) {
            ((ItemDecoration) this.f3702n.get(i3)).onDrawOver(canvas, this, this.B);
        }
        EdgeEffect edgeEffect = this.mLeftGlow;
        boolean z3 = true;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z = false;
        } else {
            int save = canvas.save();
            int paddingBottom = this.f3694f ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            EdgeEffect edgeEffect2 = this.mLeftGlow;
            z = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(save);
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int save2 = canvas.save();
            if (this.f3694f) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.mTopGlow;
            z |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(save2);
        }
        EdgeEffect edgeEffect5 = this.mRightGlow;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int save3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.f3694f ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(paddingTop, -width);
            EdgeEffect edgeEffect6 = this.mRightGlow;
            z |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(save3);
        }
        EdgeEffect edgeEffect7 = this.mBottomGlow;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int save4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.f3694f) {
                f2 = (-getWidth()) + getPaddingRight();
                i2 = (-getHeight()) + getPaddingBottom();
            } else {
                f2 = -getWidth();
                i2 = -getHeight();
            }
            canvas.translate(f2, i2);
            EdgeEffect edgeEffect8 = this.mBottomGlow;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z2 = true;
            }
            z |= z2;
            canvas.restoreToCount(save4);
        }
        if (z || this.x == null || this.f3702n.size() <= 0 || !this.x.isRunning()) {
            z3 = z;
        }
        if (z3) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j2) {
        return super.drawChild(canvas, view, j2);
    }

    @VisibleForTesting
    boolean e0(ViewHolder viewHolder, int i2) {
        if (!isComputingLayout()) {
            ViewCompat.setImportantForAccessibility(viewHolder.itemView, i2);
            return true;
        }
        viewHolder.mPendingAccessibilityState = i2;
        this.H.add(viewHolder);
        return false;
    }

    void f(@NonNull ViewHolder viewHolder, @Nullable ItemAnimator.ItemHolderInfo itemHolderInfo, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        viewHolder.setIsRecyclable(false);
        if (this.x.animateAppearance(viewHolder, itemHolderInfo, itemHolderInfo2)) {
            V();
        }
    }

    boolean f0(AccessibilityEvent accessibilityEvent) {
        if (isComputingLayout()) {
            int contentChangeTypes = accessibilityEvent != null ? AccessibilityEventCompat.getContentChangeTypes(accessibilityEvent) : 0;
            this.mEatenAccessibilityChangeFlags |= contentChangeTypes != 0 ? contentChangeTypes : 0;
            return true;
        }
        return false;
    }

    @Nullable
    public View findChildViewUnder(float f2, float f3) {
        for (int g2 = this.f3692d.g() - 1; g2 >= 0; g2--) {
            View f4 = this.f3692d.f(g2);
            float translationX = f4.getTranslationX();
            float translationY = f4.getTranslationY();
            if (f2 >= f4.getLeft() + translationX && f2 <= f4.getRight() + translationX && f3 >= f4.getTop() + translationY && f3 <= f4.getBottom() + translationY) {
                return f4;
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:?, code lost:
        return r3;
     */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public View findContainingItemView(@NonNull View view) {
        while (true) {
            ViewParent parent = view.getParent();
            if (parent == null || parent == this || !(parent instanceof View)) {
                break;
            }
            view = (View) parent;
        }
        return null;
    }

    @Nullable
    public ViewHolder findContainingViewHolder(@NonNull View view) {
        View findContainingItemView = findContainingItemView(view);
        if (findContainingItemView == null) {
            return null;
        }
        return getChildViewHolder(findContainingItemView);
    }

    @Nullable
    public ViewHolder findViewHolderForAdapterPosition(int i2) {
        ViewHolder viewHolder = null;
        if (this.v) {
            return null;
        }
        int i3 = this.f3692d.i();
        for (int i4 = 0; i4 < i3; i4++) {
            ViewHolder F = F(this.f3692d.h(i4));
            if (F != null && !F.isRemoved() && D(F) == i2) {
                if (!this.f3692d.l(F.itemView)) {
                    return F;
                }
                viewHolder = F;
            }
        }
        return viewHolder;
    }

    public ViewHolder findViewHolderForItemId(long j2) {
        Adapter adapter = this.f3698j;
        ViewHolder viewHolder = null;
        if (adapter != null && adapter.hasStableIds()) {
            int i2 = this.f3692d.i();
            for (int i3 = 0; i3 < i2; i3++) {
                ViewHolder F = F(this.f3692d.h(i3));
                if (F != null && !F.isRemoved() && F.getItemId() == j2) {
                    if (!this.f3692d.l(F.itemView)) {
                        return F;
                    }
                    viewHolder = F;
                }
            }
        }
        return viewHolder;
    }

    @Nullable
    public ViewHolder findViewHolderForLayoutPosition(int i2) {
        return C(i2, false);
    }

    @Nullable
    @Deprecated
    public ViewHolder findViewHolderForPosition(int i2) {
        return C(i2, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v6 */
    public boolean fling(int i2, int i3) {
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        } else if (this.f3708t) {
            return false;
        } else {
            int canScrollHorizontally = layoutManager.canScrollHorizontally();
            boolean canScrollVertically = this.f3699k.canScrollVertically();
            if (canScrollHorizontally == 0 || Math.abs(i2) < this.mMinFlingVelocity) {
                i2 = 0;
            }
            if (!canScrollVertically || Math.abs(i3) < this.mMinFlingVelocity) {
                i3 = 0;
            }
            if (i2 == 0 && i3 == 0) {
                return false;
            }
            float f2 = i2;
            float f3 = i3;
            if (!dispatchNestedPreFling(f2, f3)) {
                boolean z = canScrollHorizontally != 0 || canScrollVertically;
                dispatchNestedFling(f2, f3, z);
                OnFlingListener onFlingListener = this.mOnFlingListener;
                if (onFlingListener != null && onFlingListener.onFling(i2, i3)) {
                    return true;
                }
                if (z) {
                    if (canScrollVertically) {
                        canScrollHorizontally = (canScrollHorizontally == true ? 1 : 0) | 2;
                    }
                    startNestedScroll(canScrollHorizontally, 1);
                    int i4 = this.mMaxFlingVelocity;
                    int max = Math.max(-i4, Math.min(i2, i4));
                    int i5 = this.mMaxFlingVelocity;
                    this.y.fling(max, Math.max(-i5, Math.min(i3, i5)));
                    return true;
                }
            }
            return false;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View view, int i2) {
        View view2;
        boolean z;
        View onInterceptFocusSearch = this.f3699k.onInterceptFocusSearch(view, i2);
        if (onInterceptFocusSearch != null) {
            return onInterceptFocusSearch;
        }
        boolean z2 = (this.f3698j == null || this.f3699k == null || isComputingLayout() || this.f3708t) ? false : true;
        FocusFinder focusFinder = FocusFinder.getInstance();
        if (z2 && (i2 == 2 || i2 == 1)) {
            if (this.f3699k.canScrollVertically()) {
                int i3 = i2 == 2 ? 130 : 33;
                z = focusFinder.findNextFocus(this, view, i3) == null;
                if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
                    i2 = i3;
                }
            } else {
                z = false;
            }
            if (!z && this.f3699k.canScrollHorizontally()) {
                int i4 = (this.f3699k.getLayoutDirection() == 1) ^ (i2 == 2) ? 66 : 17;
                boolean z3 = focusFinder.findNextFocus(this, view, i4) == null;
                if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
                    i2 = i4;
                }
                z = z3;
            }
            if (z) {
                n();
                if (findContainingItemView(view) == null) {
                    return null;
                }
                h0();
                this.f3699k.onFocusSearchFailed(view, i2, this.f3689a, this.B);
                i0(false);
            }
            view2 = focusFinder.findNextFocus(this, view, i2);
        } else {
            View findNextFocus = focusFinder.findNextFocus(this, view, i2);
            if (findNextFocus == null && z2) {
                n();
                if (findContainingItemView(view) == null) {
                    return null;
                }
                h0();
                view2 = this.f3699k.onFocusSearchFailed(view, i2, this.f3689a, this.B);
                i0(false);
            } else {
                view2 = findNextFocus;
            }
        }
        if (view2 == null || view2.hasFocusable()) {
            return isPreferredNextFocus(view, view2, i2) ? view2 : super.focusSearch(view, i2);
        } else if (getFocusedChild() == null) {
            return super.focusSearch(view, i2);
        } else {
            requestChildOnScreen(view2, null);
            return view;
        }
    }

    void g(@NonNull ViewHolder viewHolder, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo, @Nullable ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        addAnimatingView(viewHolder);
        viewHolder.setIsRecyclable(false);
        if (this.x.animateDisappearance(viewHolder, itemHolderInfo, itemHolderInfo2)) {
            V();
        }
    }

    void g0(@Px int i2, @Px int i3, @Nullable Interpolator interpolator, int i4, boolean z) {
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (this.f3708t) {
        } else {
            if (!layoutManager.canScrollHorizontally()) {
                i2 = 0;
            }
            if (!this.f3699k.canScrollVertically()) {
                i3 = 0;
            }
            if (i2 == 0 && i3 == 0) {
                return;
            }
            if (!(i4 == Integer.MIN_VALUE || i4 > 0)) {
                scrollBy(i2, i3);
                return;
            }
            if (z) {
                int i5 = i2 != 0 ? 1 : 0;
                if (i3 != 0) {
                    i5 |= 2;
                }
                startNestedScroll(i5, 1);
            }
            this.y.smoothScrollBy(i2, i3, i4, interpolator);
        }
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager != null) {
            return layoutManager.generateDefaultLayoutParams();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + z());
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager != null) {
            return layoutManager.generateLayoutParams(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + z());
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager != null) {
            return layoutManager.generateLayoutParams(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + z());
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    @Nullable
    public Adapter getAdapter() {
        return this.f3698j;
    }

    @Override // android.view.View
    public int getBaseline() {
        LayoutManager layoutManager = this.f3699k;
        return layoutManager != null ? layoutManager.getBaseline() : super.getBaseline();
    }

    public int getChildAdapterPosition(@NonNull View view) {
        ViewHolder F = F(view);
        if (F != null) {
            return F.getAbsoluteAdapterPosition();
        }
        return -1;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        ChildDrawingOrderCallback childDrawingOrderCallback = this.mChildDrawingOrderCallback;
        return childDrawingOrderCallback == null ? super.getChildDrawingOrder(i2, i3) : childDrawingOrderCallback.onGetChildDrawingOrder(i2, i3);
    }

    public long getChildItemId(@NonNull View view) {
        ViewHolder F;
        Adapter adapter = this.f3698j;
        if (adapter == null || !adapter.hasStableIds() || (F = F(view)) == null) {
            return -1L;
        }
        return F.getItemId();
    }

    public int getChildLayoutPosition(@NonNull View view) {
        ViewHolder F = F(view);
        if (F != null) {
            return F.getLayoutPosition();
        }
        return -1;
    }

    @Deprecated
    public int getChildPosition(@NonNull View view) {
        return getChildAdapterPosition(view);
    }

    public ViewHolder getChildViewHolder(@NonNull View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return F(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.f3694f;
    }

    @Nullable
    public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate() {
        return this.F;
    }

    public void getDecoratedBoundsWithMargins(@NonNull View view, @NonNull Rect rect) {
        G(view, rect);
    }

    @NonNull
    public EdgeEffectFactory getEdgeEffectFactory() {
        return this.mEdgeEffectFactory;
    }

    @Nullable
    public ItemAnimator getItemAnimator() {
        return this.x;
    }

    @NonNull
    public ItemDecoration getItemDecorationAt(int i2) {
        int itemDecorationCount = getItemDecorationCount();
        if (i2 < 0 || i2 >= itemDecorationCount) {
            throw new IndexOutOfBoundsException(i2 + " is an invalid index for size " + itemDecorationCount);
        }
        return (ItemDecoration) this.f3702n.get(i2);
    }

    public int getItemDecorationCount() {
        return this.f3702n.size();
    }

    @Nullable
    public LayoutManager getLayoutManager() {
        return this.f3699k;
    }

    public int getMaxFlingVelocity() {
        return this.mMaxFlingVelocity;
    }

    public int getMinFlingVelocity() {
        return this.mMinFlingVelocity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getNanoTime() {
        if (L) {
            return System.nanoTime();
        }
        return 0L;
    }

    @Nullable
    public OnFlingListener getOnFlingListener() {
        return this.mOnFlingListener;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.mPreserveFocusAfterLayout;
    }

    @NonNull
    public RecycledViewPool getRecycledViewPool() {
        return this.f3689a.f();
    }

    public int getScrollState() {
        return this.mScrollState;
    }

    void h(String str) {
        if (isComputingLayout()) {
            return;
        }
        if (str == null) {
            throw new IllegalStateException("Cannot call this method unless RecyclerView is computing a layout or scrolling" + z());
        }
        throw new IllegalStateException(str + z());
    }

    void h0() {
        int i2 = this.mInterceptRequestLayoutDepth + 1;
        this.mInterceptRequestLayoutDepth = i2;
        if (i2 != 1 || this.f3708t) {
            return;
        }
        this.f3707s = false;
    }

    public boolean hasFixedSize() {
        return this.f3704p;
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().hasNestedScrollingParent();
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean hasNestedScrollingParent(int i2) {
        return getScrollingChildHelper().hasNestedScrollingParent(i2);
    }

    public boolean hasPendingAdapterUpdates() {
        return !this.f3706r || this.v || this.f3691c.g();
    }

    void i(String str) {
        if (isComputingLayout()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + z());
        } else if (this.mDispatchScrollCounter > 0) {
            new IllegalStateException("" + z());
        }
    }

    void i0(boolean z) {
        if (this.mInterceptRequestLayoutDepth < 1) {
            this.mInterceptRequestLayoutDepth = 1;
        }
        if (!z && !this.f3708t) {
            this.f3707s = false;
        }
        if (this.mInterceptRequestLayoutDepth == 1) {
            if (z && this.f3707s && !this.f3708t && this.f3699k != null && this.f3698j != null) {
                r();
            }
            if (!this.f3708t) {
                this.f3707s = false;
            }
        }
        this.mInterceptRequestLayoutDepth--;
    }

    public void invalidateItemDecorations() {
        if (this.f3702n.size() == 0) {
            return;
        }
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager != null) {
            layoutManager.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout");
        }
        N();
        requestLayout();
    }

    public boolean isAnimating() {
        ItemAnimator itemAnimator = this.x;
        return itemAnimator != null && itemAnimator.isRunning();
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return this.f3703o;
    }

    public boolean isComputingLayout() {
        return this.mLayoutOrScrollCounter > 0;
    }

    @Deprecated
    public boolean isLayoutFrozen() {
        return isLayoutSuppressed();
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.f3708t;
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().isNestedScrollingEnabled();
    }

    boolean j(ViewHolder viewHolder) {
        ItemAnimator itemAnimator = this.x;
        return itemAnimator == null || itemAnimator.canReuseUpdatedViewHolder(viewHolder, viewHolder.getUnmodifiedPayloads());
    }

    void j0(int i2, int i3, Object obj) {
        int i4;
        int i5 = this.f3692d.i();
        int i6 = i2 + i3;
        for (int i7 = 0; i7 < i5; i7++) {
            View h2 = this.f3692d.h(i7);
            ViewHolder F = F(h2);
            if (F != null && !F.shouldIgnore() && (i4 = F.mPosition) >= i2 && i4 < i6) {
                F.addFlags(2);
                F.addChangePayload(obj);
                ((LayoutParams) h2.getLayoutParams()).f3730c = true;
            }
        }
        this.f3689a.C(i2, i3);
    }

    void l() {
        int i2 = this.f3692d.i();
        for (int i3 = 0; i3 < i2; i3++) {
            ViewHolder F = F(this.f3692d.h(i3));
            if (!F.shouldIgnore()) {
                F.clearOldPosition();
            }
        }
        this.f3689a.b();
    }

    void m(int i2, int i3) {
        boolean z;
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect == null || edgeEffect.isFinished() || i2 <= 0) {
            z = false;
        } else {
            this.mLeftGlow.onRelease();
            z = this.mLeftGlow.isFinished();
        }
        EdgeEffect edgeEffect2 = this.mRightGlow;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i2 < 0) {
            this.mRightGlow.onRelease();
            z |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i3 > 0) {
            this.mTopGlow.onRelease();
            z |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i3 < 0) {
            this.mBottomGlow.onRelease();
            z |= this.mBottomGlow.isFinished();
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    void n() {
        if (!this.f3706r || this.v) {
            TraceCompat.beginSection(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
            r();
            TraceCompat.endSection();
        } else if (this.f3691c.g()) {
            if (this.f3691c.f(4) && !this.f3691c.f(11)) {
                TraceCompat.beginSection(TRACE_HANDLE_ADAPTER_UPDATES_TAG);
                h0();
                S();
                this.f3691c.m();
                if (!this.f3707s) {
                    if (hasUpdatedView()) {
                        r();
                    } else {
                        this.f3691c.a();
                    }
                }
                i0(true);
                T();
            } else if (!this.f3691c.g()) {
                return;
            } else {
                TraceCompat.beginSection(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
                r();
            }
            TraceCompat.endSection();
        }
    }

    public void nestedScrollBy(int i2, int i3) {
        nestedScrollByInternal(i2, i3, null, 1);
    }

    void o(int i2, int i3) {
        setMeasuredDimension(LayoutManager.chooseSize(i2, getPaddingLeft() + getPaddingRight(), ViewCompat.getMinimumWidth(this)), LayoutManager.chooseSize(i3, getPaddingTop() + getPaddingBottom(), ViewCompat.getMinimumHeight(this)));
    }

    public void offsetChildrenHorizontal(@Px int i2) {
        int g2 = this.f3692d.g();
        for (int i3 = 0; i3 < g2; i3++) {
            this.f3692d.f(i3).offsetLeftAndRight(i2);
        }
    }

    public void offsetChildrenVertical(@Px int i2) {
        int g2 = this.f3692d.g();
        for (int i3 = 0; i3 < g2; i3++) {
            this.f3692d.f(i3).offsetTopAndBottom(i2);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mLayoutOrScrollCounter = 0;
        boolean z = true;
        this.f3703o = true;
        if (!this.f3706r || isLayoutRequested()) {
            z = false;
        }
        this.f3706r = z;
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager != null) {
            layoutManager.a(this);
        }
        this.E = false;
        if (L) {
            ThreadLocal threadLocal = GapWorker.f3575d;
            GapWorker gapWorker = (GapWorker) threadLocal.get();
            this.z = gapWorker;
            if (gapWorker == null) {
                this.z = new GapWorker();
                Display display = ViewCompat.getDisplay(this);
                float f2 = 60.0f;
                if (!isInEditMode() && display != null) {
                    float refreshRate = display.getRefreshRate();
                    if (refreshRate >= 30.0f) {
                        f2 = refreshRate;
                    }
                }
                GapWorker gapWorker2 = this.z;
                gapWorker2.f3579c = 1.0E9f / f2;
                threadLocal.set(gapWorker2);
            }
            this.z.add(this);
        }
    }

    public void onChildAttachedToWindow(@NonNull View view) {
    }

    public void onChildDetachedFromWindow(@NonNull View view) {
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        GapWorker gapWorker;
        super.onDetachedFromWindow();
        ItemAnimator itemAnimator = this.x;
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
        stopScroll();
        this.f3703o = false;
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager != null) {
            layoutManager.b(this, this.f3689a);
        }
        this.H.clear();
        removeCallbacks(this.mItemAnimatorRunner);
        this.f3693e.j();
        if (!L || (gapWorker = this.z) == null) {
            return;
        }
        gapWorker.remove(this);
        this.z = null;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.f3702n.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((ItemDecoration) this.f3702n.get(i2)).onDraw(canvas, this, this.B);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0068  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        float f2;
        float f3;
        if (this.f3699k != null && !this.f3708t && motionEvent.getAction() == 8) {
            if ((motionEvent.getSource() & 2) != 0) {
                f2 = this.f3699k.canScrollVertically() ? -motionEvent.getAxisValue(9) : 0.0f;
                if (this.f3699k.canScrollHorizontally()) {
                    f3 = motionEvent.getAxisValue(10);
                    if (f2 == 0.0f || f3 != 0.0f) {
                        nestedScrollByInternal((int) (f3 * this.mScaledHorizontalScrollFactor), (int) (f2 * this.mScaledVerticalScrollFactor), motionEvent, 1);
                    }
                }
                f3 = 0.0f;
                if (f2 == 0.0f) {
                }
                nestedScrollByInternal((int) (f3 * this.mScaledHorizontalScrollFactor), (int) (f2 * this.mScaledVerticalScrollFactor), motionEvent, 1);
            } else {
                if ((motionEvent.getSource() & 4194304) != 0) {
                    float axisValue = motionEvent.getAxisValue(26);
                    if (this.f3699k.canScrollVertically()) {
                        f2 = -axisValue;
                        f3 = 0.0f;
                        if (f2 == 0.0f) {
                        }
                        nestedScrollByInternal((int) (f3 * this.mScaledHorizontalScrollFactor), (int) (f2 * this.mScaledVerticalScrollFactor), motionEvent, 1);
                    } else if (this.f3699k.canScrollHorizontally()) {
                        f3 = axisValue;
                        f2 = 0.0f;
                        if (f2 == 0.0f) {
                        }
                        nestedScrollByInternal((int) (f3 * this.mScaledHorizontalScrollFactor), (int) (f2 * this.mScaledVerticalScrollFactor), motionEvent, 1);
                    }
                }
                f2 = 0.0f;
                f3 = 0.0f;
                if (f2 == 0.0f) {
                }
                nestedScrollByInternal((int) (f3 * this.mScaledHorizontalScrollFactor), (int) (f2 * this.mScaledVerticalScrollFactor), motionEvent, 1);
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        if (this.f3708t) {
            return false;
        }
        this.mInterceptingOnItemTouchListener = null;
        if (findInterceptingOnItemTouchListener(motionEvent)) {
            cancelScroll();
            return true;
        }
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager == null) {
            return false;
        }
        boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
        boolean canScrollVertically = this.f3699k.canScrollVertically();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            if (this.mIgnoreMotionEventTillDown) {
                this.mIgnoreMotionEventTillDown = false;
            }
            this.mScrollPointerId = motionEvent.getPointerId(0);
            int x = (int) (motionEvent.getX() + 0.5f);
            this.mLastTouchX = x;
            this.mInitialTouchX = x;
            int y = (int) (motionEvent.getY() + 0.5f);
            this.mLastTouchY = y;
            this.mInitialTouchY = y;
            if (this.mScrollState == 2) {
                getParent().requestDisallowInterceptTouchEvent(true);
                setScrollState(1);
                stopNestedScroll(1);
            }
            int[] iArr = this.mNestedOffsets;
            iArr[1] = 0;
            iArr[0] = 0;
            int i2 = canScrollHorizontally;
            if (canScrollVertically) {
                i2 = (canScrollHorizontally ? 1 : 0) | 2;
            }
            startNestedScroll(i2, 0);
        } else if (actionMasked == 1) {
            this.mVelocityTracker.clear();
            stopNestedScroll(0);
        } else if (actionMasked == 2) {
            int findPointerIndex = motionEvent.findPointerIndex(this.mScrollPointerId);
            if (findPointerIndex < 0) {
                Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                return false;
            }
            int x2 = (int) (motionEvent.getX(findPointerIndex) + 0.5f);
            int y2 = (int) (motionEvent.getY(findPointerIndex) + 0.5f);
            if (this.mScrollState != 1) {
                int i3 = x2 - this.mInitialTouchX;
                int i4 = y2 - this.mInitialTouchY;
                if (!canScrollHorizontally || Math.abs(i3) <= this.mTouchSlop) {
                    z = false;
                } else {
                    this.mLastTouchX = x2;
                    z = true;
                }
                if (canScrollVertically && Math.abs(i4) > this.mTouchSlop) {
                    this.mLastTouchY = y2;
                    z = true;
                }
                if (z) {
                    setScrollState(1);
                }
            }
        } else if (actionMasked == 3) {
            cancelScroll();
        } else if (actionMasked == 5) {
            this.mScrollPointerId = motionEvent.getPointerId(actionIndex);
            int x3 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.mLastTouchX = x3;
            this.mInitialTouchX = x3;
            int y3 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.mLastTouchY = y3;
            this.mInitialTouchY = y3;
        } else if (actionMasked == 6) {
            onPointerUp(motionEvent);
        }
        return this.mScrollState == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        TraceCompat.beginSection(TRACE_ON_LAYOUT_TAG);
        r();
        TraceCompat.endSection();
        this.f3706r = true;
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager == null) {
            o(i2, i3);
            return;
        }
        boolean z = false;
        if (layoutManager.isAutoMeasureEnabled()) {
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            this.f3699k.onMeasure(this.f3689a, this.B, i2, i3);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z = true;
            }
            this.mLastAutoMeasureSkippedDueToExact = z;
            if (z || this.f3698j == null) {
                return;
            }
            if (this.B.f3748d == 1) {
                dispatchLayoutStep1();
            }
            this.f3699k.k(i2, i3);
            this.B.f3753i = true;
            dispatchLayoutStep2();
            this.f3699k.l(i2, i3);
            if (this.f3699k.o()) {
                this.f3699k.k(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                this.B.f3753i = true;
                dispatchLayoutStep2();
                this.f3699k.l(i2, i3);
            }
            this.mLastAutoMeasureNonExactMeasuredWidth = getMeasuredWidth();
            this.mLastAutoMeasureNonExactMeasuredHeight = getMeasuredHeight();
        } else if (this.f3704p) {
            this.f3699k.onMeasure(this.f3689a, this.B, i2, i3);
        } else {
            if (this.u) {
                h0();
                S();
                processAdapterUpdatesAndSetAnimationFlags();
                T();
                State state = this.B;
                if (state.f3755k) {
                    state.f3751g = true;
                } else {
                    this.f3691c.b();
                    this.B.f3751g = false;
                }
                this.u = false;
                i0(false);
            } else if (this.B.f3755k) {
                setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
                return;
            }
            Adapter adapter = this.f3698j;
            if (adapter != null) {
                this.B.f3749e = adapter.getItemCount();
            } else {
                this.B.f3749e = 0;
            }
            h0();
            this.f3699k.onMeasure(this.f3689a, this.B, i2, i3);
            i0(false);
            this.B.f3751g = false;
        }
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i2, Rect rect) {
        if (isComputingLayout()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i2, rect);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.f3690b = savedState;
        super.onRestoreInstanceState(savedState.getSuperState());
        requestLayout();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.f3690b;
        if (savedState2 != null) {
            savedState.a(savedState2);
        } else {
            LayoutManager layoutManager = this.f3699k;
            savedState.f3744a = layoutManager != null ? layoutManager.onSaveInstanceState() : null;
        }
        return savedState;
    }

    public void onScrollStateChanged(int i2) {
    }

    public void onScrolled(@Px int i2, @Px int i3) {
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 == i4 && i3 == i5) {
            return;
        }
        K();
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00f4  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z2 = false;
        if (this.f3708t || this.mIgnoreMotionEventTillDown) {
            return false;
        }
        if (dispatchToOnItemTouchListeners(motionEvent)) {
            cancelScroll();
            return true;
        }
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager == null) {
            return false;
        }
        boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
        boolean canScrollVertically = this.f3699k.canScrollVertically();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            int[] iArr = this.mNestedOffsets;
            iArr[1] = 0;
            iArr[0] = 0;
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        int[] iArr2 = this.mNestedOffsets;
        obtain.offsetLocation(iArr2[0], iArr2[1]);
        if (actionMasked == 0) {
            this.mScrollPointerId = motionEvent.getPointerId(0);
            int x = (int) (motionEvent.getX() + 0.5f);
            this.mLastTouchX = x;
            this.mInitialTouchX = x;
            int y = (int) (motionEvent.getY() + 0.5f);
            this.mLastTouchY = y;
            this.mInitialTouchY = y;
            int i2 = canScrollHorizontally;
            if (canScrollVertically) {
                i2 = (canScrollHorizontally ? 1 : 0) | 2;
            }
            startNestedScroll(i2, 0);
        } else if (actionMasked == 1) {
            this.mVelocityTracker.addMovement(obtain);
            this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaxFlingVelocity);
            float f2 = canScrollHorizontally ? -this.mVelocityTracker.getXVelocity(this.mScrollPointerId) : 0.0f;
            float f3 = canScrollVertically ? -this.mVelocityTracker.getYVelocity(this.mScrollPointerId) : 0.0f;
            if ((f2 == 0.0f && f3 == 0.0f) || !fling((int) f2, (int) f3)) {
                setScrollState(0);
            }
            resetScroll();
            z2 = true;
        } else if (actionMasked == 2) {
            int findPointerIndex = motionEvent.findPointerIndex(this.mScrollPointerId);
            if (findPointerIndex < 0) {
                Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                return false;
            }
            int x2 = (int) (motionEvent.getX(findPointerIndex) + 0.5f);
            int y2 = (int) (motionEvent.getY(findPointerIndex) + 0.5f);
            int i3 = this.mLastTouchX - x2;
            int i4 = this.mLastTouchY - y2;
            if (this.mScrollState != 1) {
                if (canScrollHorizontally) {
                    int i5 = this.mTouchSlop;
                    i3 = i3 > 0 ? Math.max(0, i3 - i5) : Math.min(0, i3 + i5);
                    if (i3 != 0) {
                        z = true;
                        if (canScrollVertically) {
                            int i6 = this.mTouchSlop;
                            i4 = i4 > 0 ? Math.max(0, i4 - i6) : Math.min(0, i4 + i6);
                            if (i4 != 0) {
                                z = true;
                            }
                        }
                        if (z) {
                            setScrollState(1);
                        }
                    }
                }
                z = false;
                if (canScrollVertically) {
                }
                if (z) {
                }
            }
            int i7 = i3;
            int i8 = i4;
            if (this.mScrollState == 1) {
                int[] iArr3 = this.G;
                iArr3[0] = 0;
                iArr3[1] = 0;
                if (dispatchNestedPreScroll(canScrollHorizontally ? i7 : 0, canScrollVertically ? i8 : 0, iArr3, this.mScrollOffset, 0)) {
                    int[] iArr4 = this.G;
                    i7 -= iArr4[0];
                    i8 -= iArr4[1];
                    int[] iArr5 = this.mNestedOffsets;
                    int i9 = iArr5[0];
                    int[] iArr6 = this.mScrollOffset;
                    iArr5[0] = i9 + iArr6[0];
                    iArr5[1] = iArr5[1] + iArr6[1];
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                int i10 = i8;
                int[] iArr7 = this.mScrollOffset;
                this.mLastTouchX = x2 - iArr7[0];
                this.mLastTouchY = y2 - iArr7[1];
                if (c0(canScrollHorizontally ? i7 : 0, canScrollVertically ? i10 : 0, motionEvent, 0)) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                GapWorker gapWorker = this.z;
                if (gapWorker != null && (i7 != 0 || i10 != 0)) {
                    gapWorker.b(this, i7, i10);
                }
            }
        } else if (actionMasked == 3) {
            cancelScroll();
        } else if (actionMasked == 5) {
            this.mScrollPointerId = motionEvent.getPointerId(actionIndex);
            int x3 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.mLastTouchX = x3;
            this.mInitialTouchX = x3;
            int y3 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.mLastTouchY = y3;
            this.mInitialTouchY = y3;
        } else if (actionMasked == 6) {
            onPointerUp(motionEvent);
        }
        if (!z2) {
            this.mVelocityTracker.addMovement(obtain);
        }
        obtain.recycle();
        return true;
    }

    void p(View view) {
        ViewHolder F = F(view);
        onChildAttachedToWindow(view);
        Adapter adapter = this.f3698j;
        if (adapter != null && F != null) {
            adapter.onViewAttachedToWindow(F);
        }
        List<OnChildAttachStateChangeListener> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mOnChildAttachStateListeners.get(size).onChildViewAttachedToWindow(view);
            }
        }
    }

    void q(View view) {
        ViewHolder F = F(view);
        onChildDetachedFromWindow(view);
        Adapter adapter = this.f3698j;
        if (adapter != null && F != null) {
            adapter.onViewDetachedFromWindow(F);
        }
        List<OnChildAttachStateChangeListener> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mOnChildAttachStateListeners.get(size).onChildViewDetachedFromWindow(view);
            }
        }
    }

    void r() {
        if (this.f3698j == null) {
            return;
        }
        if (this.f3699k == null) {
            Log.e("RecyclerView", "No layout manager attached; skipping layout");
            return;
        }
        this.B.f3753i = false;
        boolean z = this.mLastAutoMeasureSkippedDueToExact && !(this.mLastAutoMeasureNonExactMeasuredWidth == getWidth() && this.mLastAutoMeasureNonExactMeasuredHeight == getHeight());
        this.mLastAutoMeasureNonExactMeasuredWidth = 0;
        this.mLastAutoMeasureNonExactMeasuredHeight = 0;
        this.mLastAutoMeasureSkippedDueToExact = false;
        if (this.B.f3748d == 1) {
            dispatchLayoutStep1();
        } else if (!this.f3691c.h() && !z && this.f3699k.getWidth() == getWidth() && this.f3699k.getHeight() == getHeight()) {
            this.f3699k.j(this);
            dispatchLayoutStep3();
        }
        this.f3699k.j(this);
        dispatchLayoutStep2();
        dispatchLayoutStep3();
    }

    @Override // android.view.ViewGroup
    protected void removeDetachedView(View view, boolean z) {
        ViewHolder F = F(view);
        if (F != null) {
            if (F.isTmpDetached()) {
                F.clearTmpDetachFlag();
            } else if (!F.shouldIgnore()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + F + z());
            }
        }
        view.clearAnimation();
        q(view);
        super.removeDetachedView(view, z);
    }

    public void removeItemDecoration(@NonNull ItemDecoration itemDecoration) {
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager != null) {
            layoutManager.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
        }
        this.f3702n.remove(itemDecoration);
        if (this.f3702n.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        N();
        requestLayout();
    }

    public void removeItemDecorationAt(int i2) {
        int itemDecorationCount = getItemDecorationCount();
        if (i2 >= 0 && i2 < itemDecorationCount) {
            removeItemDecoration(getItemDecorationAt(i2));
            return;
        }
        throw new IndexOutOfBoundsException(i2 + " is an invalid index for size " + itemDecorationCount);
    }

    public void removeOnChildAttachStateChangeListener(@NonNull OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        List<OnChildAttachStateChangeListener> list = this.mOnChildAttachStateListeners;
        if (list == null) {
            return;
        }
        list.remove(onChildAttachStateChangeListener);
    }

    public void removeOnItemTouchListener(@NonNull OnItemTouchListener onItemTouchListener) {
        this.mOnItemTouchListeners.remove(onItemTouchListener);
        if (this.mInterceptingOnItemTouchListener == onItemTouchListener) {
            this.mInterceptingOnItemTouchListener = null;
        }
    }

    public void removeOnScrollListener(@NonNull OnScrollListener onScrollListener) {
        List<OnScrollListener> list = this.mScrollListeners;
        if (list != null) {
            list.remove(onScrollListener);
        }
    }

    public void removeRecyclerListener(@NonNull RecyclerListener recyclerListener) {
        this.f3701m.remove(recyclerListener);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (!this.f3699k.onRequestChildFocus(this, this.B, view, view2) && view2 != null) {
            requestChildOnScreen(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.f3699k.requestChildRectangleOnScreen(this, view, rect, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        int size = this.mOnItemTouchListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mOnItemTouchListeners.get(i2).onRequestDisallowInterceptTouchEvent(z);
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.mInterceptRequestLayoutDepth != 0 || this.f3708t) {
            this.f3707s = true;
        } else {
            super.requestLayout();
        }
    }

    void s(int i2) {
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager != null) {
            layoutManager.onScrollStateChanged(i2);
        }
        onScrollStateChanged(i2);
        OnScrollListener onScrollListener = this.mScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScrollStateChanged(this, i2);
        }
        List<OnScrollListener> list = this.mScrollListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mScrollListeners.get(size).onScrollStateChanged(this, i2);
            }
        }
    }

    @Override // android.view.View
    public void scrollBy(int i2, int i3) {
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (this.f3708t) {
        } else {
            boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
            boolean canScrollVertically = this.f3699k.canScrollVertically();
            if (canScrollHorizontally || canScrollVertically) {
                if (!canScrollHorizontally) {
                    i2 = 0;
                }
                if (!canScrollVertically) {
                    i3 = 0;
                }
                c0(i2, i3, null, 0);
            }
        }
    }

    @Override // android.view.View
    public void scrollTo(int i2, int i3) {
    }

    public void scrollToPosition(int i2) {
        if (this.f3708t) {
            return;
        }
        stopScroll();
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        layoutManager.scrollToPosition(i2);
        awakenScrollBars();
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (f0(accessibilityEvent)) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }

    public void setAccessibilityDelegateCompat(@Nullable RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate) {
        this.F = recyclerViewAccessibilityDelegate;
        ViewCompat.setAccessibilityDelegate(this, recyclerViewAccessibilityDelegate);
    }

    public void setAdapter(@Nullable Adapter adapter) {
        setLayoutFrozen(false);
        setAdapterInternal(adapter, false, true);
        W(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(@Nullable ChildDrawingOrderCallback childDrawingOrderCallback) {
        if (childDrawingOrderCallback == this.mChildDrawingOrderCallback) {
            return;
        }
        this.mChildDrawingOrderCallback = childDrawingOrderCallback;
        setChildrenDrawingOrderEnabled(childDrawingOrderCallback != null);
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z) {
        if (z != this.f3694f) {
            K();
        }
        this.f3694f = z;
        super.setClipToPadding(z);
        if (this.f3706r) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(@NonNull EdgeEffectFactory edgeEffectFactory) {
        Preconditions.checkNotNull(edgeEffectFactory);
        this.mEdgeEffectFactory = edgeEffectFactory;
        K();
    }

    public void setHasFixedSize(boolean z) {
        this.f3704p = z;
    }

    public void setItemAnimator(@Nullable ItemAnimator itemAnimator) {
        ItemAnimator itemAnimator2 = this.x;
        if (itemAnimator2 != null) {
            itemAnimator2.endAnimations();
            this.x.setListener(null);
        }
        this.x = itemAnimator;
        if (itemAnimator != null) {
            itemAnimator.setListener(this.mItemAnimatorListener);
        }
    }

    public void setItemViewCacheSize(int i2) {
        this.f3689a.setViewCacheSize(i2);
    }

    @Deprecated
    public void setLayoutFrozen(boolean z) {
        suppressLayout(z);
    }

    public void setLayoutManager(@Nullable LayoutManager layoutManager) {
        if (layoutManager == this.f3699k) {
            return;
        }
        stopScroll();
        if (this.f3699k != null) {
            ItemAnimator itemAnimator = this.x;
            if (itemAnimator != null) {
                itemAnimator.endAnimations();
            }
            this.f3699k.removeAndRecycleAllViews(this.f3689a);
            this.f3699k.i(this.f3689a);
            this.f3689a.clear();
            if (this.f3703o) {
                this.f3699k.b(this, this.f3689a);
            }
            this.f3699k.m(null);
            this.f3699k = null;
        } else {
            this.f3689a.clear();
        }
        this.f3692d.m();
        this.f3699k = layoutManager;
        if (layoutManager != null) {
            if (layoutManager.f3717b != null) {
                throw new IllegalArgumentException("LayoutManager " + layoutManager + " is already attached to a RecyclerView:" + layoutManager.f3717b.z());
            }
            layoutManager.m(this);
            if (this.f3703o) {
                this.f3699k.a(this);
            }
        }
        this.f3689a.A();
        requestLayout();
    }

    @Override // android.view.ViewGroup
    @Deprecated
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        if (Build.VERSION.SDK_INT < 18) {
            if (layoutTransition == null) {
                suppressLayout(false);
                return;
            } else if (layoutTransition.getAnimator(0) == null && layoutTransition.getAnimator(1) == null && layoutTransition.getAnimator(2) == null && layoutTransition.getAnimator(3) == null && layoutTransition.getAnimator(4) == null) {
                suppressLayout(true);
                return;
            }
        }
        if (layoutTransition != null) {
            throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
        }
        super.setLayoutTransition(null);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z) {
        getScrollingChildHelper().setNestedScrollingEnabled(z);
    }

    public void setOnFlingListener(@Nullable OnFlingListener onFlingListener) {
        this.mOnFlingListener = onFlingListener;
    }

    @Deprecated
    public void setOnScrollListener(@Nullable OnScrollListener onScrollListener) {
        this.mScrollListener = onScrollListener;
    }

    public void setPreserveFocusAfterLayout(boolean z) {
        this.mPreserveFocusAfterLayout = z;
    }

    public void setRecycledViewPool(@Nullable RecycledViewPool recycledViewPool) {
        this.f3689a.w(recycledViewPool);
    }

    @Deprecated
    public void setRecyclerListener(@Nullable RecyclerListener recyclerListener) {
        this.f3700l = recyclerListener;
    }

    void setScrollState(int i2) {
        if (i2 == this.mScrollState) {
            return;
        }
        this.mScrollState = i2;
        if (i2 != 2) {
            stopScrollersInternal();
        }
        s(i2);
    }

    public void setScrollingTouchSlop(int i2) {
        int scaledTouchSlop;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i2 != 0) {
            if (i2 == 1) {
                scaledTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
                this.mTouchSlop = scaledTouchSlop;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("setScrollingTouchSlop(): bad argument constant ");
            sb.append(i2);
            sb.append("; using default value");
        }
        scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mTouchSlop = scaledTouchSlop;
    }

    public void setViewCacheExtension(@Nullable ViewCacheExtension viewCacheExtension) {
        this.f3689a.x(viewCacheExtension);
    }

    public void smoothScrollBy(@Px int i2, @Px int i3) {
        smoothScrollBy(i2, i3, null);
    }

    public void smoothScrollBy(@Px int i2, @Px int i3, @Nullable Interpolator interpolator) {
        smoothScrollBy(i2, i3, interpolator, Integer.MIN_VALUE);
    }

    public void smoothScrollBy(@Px int i2, @Px int i3, @Nullable Interpolator interpolator, int i4) {
        g0(i2, i3, interpolator, i4, false);
    }

    public void smoothScrollToPosition(int i2) {
        if (this.f3708t) {
            return;
        }
        LayoutManager layoutManager = this.f3699k;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            layoutManager.smoothScrollToPosition(this, this.B, i2);
        }
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean startNestedScroll(int i2) {
        return getScrollingChildHelper().startNestedScroll(i2);
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean startNestedScroll(int i2, int i3) {
        return getScrollingChildHelper().startNestedScroll(i2, i3);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void stopNestedScroll() {
        getScrollingChildHelper().stopNestedScroll();
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public void stopNestedScroll(int i2) {
        getScrollingChildHelper().stopNestedScroll(i2);
    }

    public void stopScroll() {
        setScrollState(0);
        stopScrollersInternal();
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean z) {
        if (z != this.f3708t) {
            i("Do not suppressLayout in layout or scroll");
            if (z) {
                long uptimeMillis = SystemClock.uptimeMillis();
                onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0));
                this.f3708t = true;
                this.mIgnoreMotionEventTillDown = true;
                stopScroll();
                return;
            }
            this.f3708t = false;
            if (this.f3707s && this.f3699k != null && this.f3698j != null) {
                requestLayout();
            }
            this.f3707s = false;
        }
    }

    public void swapAdapter(@Nullable Adapter adapter, boolean z) {
        setLayoutFrozen(false);
        setAdapterInternal(adapter, true, z);
        W(true);
        requestLayout();
    }

    void t(int i2, int i3) {
        this.mDispatchScrollCounter++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i2, scrollY - i3);
        onScrolled(i2, i3);
        OnScrollListener onScrollListener = this.mScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScrolled(this, i2, i3);
        }
        List<OnScrollListener> list = this.mScrollListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mScrollListeners.get(size).onScrolled(this, i2, i3);
            }
        }
        this.mDispatchScrollCounter--;
    }

    void u() {
        int i2;
        for (int size = this.H.size() - 1; size >= 0; size--) {
            ViewHolder viewHolder = (ViewHolder) this.H.get(size);
            if (viewHolder.itemView.getParent() == this && !viewHolder.shouldIgnore() && (i2 = viewHolder.mPendingAccessibilityState) != -1) {
                ViewCompat.setImportantForAccessibility(viewHolder.itemView, i2);
                viewHolder.mPendingAccessibilityState = -1;
            }
        }
        this.H.clear();
    }

    void v() {
        int measuredWidth;
        int measuredHeight;
        if (this.mBottomGlow != null) {
            return;
        }
        EdgeEffect a2 = this.mEdgeEffectFactory.a(this, 3);
        this.mBottomGlow = a2;
        if (this.f3694f) {
            measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        } else {
            measuredWidth = getMeasuredWidth();
            measuredHeight = getMeasuredHeight();
        }
        a2.setSize(measuredWidth, measuredHeight);
    }

    void w() {
        int measuredHeight;
        int measuredWidth;
        if (this.mLeftGlow != null) {
            return;
        }
        EdgeEffect a2 = this.mEdgeEffectFactory.a(this, 0);
        this.mLeftGlow = a2;
        if (this.f3694f) {
            measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
            measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        } else {
            measuredHeight = getMeasuredHeight();
            measuredWidth = getMeasuredWidth();
        }
        a2.setSize(measuredHeight, measuredWidth);
    }

    void x() {
        int measuredHeight;
        int measuredWidth;
        if (this.mRightGlow != null) {
            return;
        }
        EdgeEffect a2 = this.mEdgeEffectFactory.a(this, 2);
        this.mRightGlow = a2;
        if (this.f3694f) {
            measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
            measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        } else {
            measuredHeight = getMeasuredHeight();
            measuredWidth = getMeasuredWidth();
        }
        a2.setSize(measuredHeight, measuredWidth);
    }

    void y() {
        int measuredWidth;
        int measuredHeight;
        if (this.mTopGlow != null) {
            return;
        }
        EdgeEffect a2 = this.mEdgeEffectFactory.a(this, 1);
        this.mTopGlow = a2;
        if (this.f3694f) {
            measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        } else {
            measuredWidth = getMeasuredWidth();
            measuredHeight = getMeasuredHeight();
        }
        a2.setSize(measuredWidth, measuredHeight);
    }

    String z() {
        return " " + super.toString() + ", adapter:" + this.f3698j + ", layout:" + this.f3699k + ", context:" + getContext();
    }
}
