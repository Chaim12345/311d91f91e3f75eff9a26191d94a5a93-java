package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes.dex */
public class LinearLayoutManager extends RecyclerView.LayoutManager implements ItemTouchHelper.ViewDropHandler, RecyclerView.SmoothScroller.ScrollVectorProvider {
    public static final int HORIZONTAL = 0;
    public static final int INVALID_OFFSET = Integer.MIN_VALUE;
    private static final float MAX_SCROLL_FACTOR = 0.33333334f;
    private static final String TAG = "LinearLayoutManager";
    public static final int VERTICAL = 1;

    /* renamed from: k  reason: collision with root package name */
    int f3640k;

    /* renamed from: l  reason: collision with root package name */
    OrientationHelper f3641l;

    /* renamed from: m  reason: collision with root package name */
    boolean f3642m;
    private int mInitialPrefetchItemCount;
    private boolean mLastStackFromEnd;
    private final LayoutChunkResult mLayoutChunkResult;
    private LayoutState mLayoutState;
    private boolean mRecycleChildrenOnDetach;
    private int[] mReusableIntPair;
    private boolean mReverseLayout;
    private boolean mSmoothScrollbarEnabled;
    private boolean mStackFromEnd;

    /* renamed from: n  reason: collision with root package name */
    int f3643n;

    /* renamed from: o  reason: collision with root package name */
    int f3644o;

    /* renamed from: p  reason: collision with root package name */
    SavedState f3645p;

    /* renamed from: q  reason: collision with root package name */
    final AnchorInfo f3646q;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class AnchorInfo {

        /* renamed from: a  reason: collision with root package name */
        OrientationHelper f3647a;

        /* renamed from: b  reason: collision with root package name */
        int f3648b;

        /* renamed from: c  reason: collision with root package name */
        int f3649c;

        /* renamed from: d  reason: collision with root package name */
        boolean f3650d;

        /* renamed from: e  reason: collision with root package name */
        boolean f3651e;

        AnchorInfo() {
            c();
        }

        void a() {
            this.f3649c = this.f3650d ? this.f3647a.getEndAfterPadding() : this.f3647a.getStartAfterPadding();
        }

        public void assignFromView(View view, int i2) {
            this.f3649c = this.f3650d ? this.f3647a.getDecoratedEnd(view) + this.f3647a.getTotalSpaceChange() : this.f3647a.getDecoratedStart(view);
            this.f3648b = i2;
        }

        public void assignFromViewAndKeepVisibleRect(View view, int i2) {
            int totalSpaceChange = this.f3647a.getTotalSpaceChange();
            if (totalSpaceChange >= 0) {
                assignFromView(view, i2);
                return;
            }
            this.f3648b = i2;
            if (this.f3650d) {
                int endAfterPadding = (this.f3647a.getEndAfterPadding() - totalSpaceChange) - this.f3647a.getDecoratedEnd(view);
                this.f3649c = this.f3647a.getEndAfterPadding() - endAfterPadding;
                if (endAfterPadding > 0) {
                    int decoratedMeasurement = this.f3649c - this.f3647a.getDecoratedMeasurement(view);
                    int startAfterPadding = this.f3647a.getStartAfterPadding();
                    int min = decoratedMeasurement - (startAfterPadding + Math.min(this.f3647a.getDecoratedStart(view) - startAfterPadding, 0));
                    if (min < 0) {
                        this.f3649c += Math.min(endAfterPadding, -min);
                        return;
                    }
                    return;
                }
                return;
            }
            int decoratedStart = this.f3647a.getDecoratedStart(view);
            int startAfterPadding2 = decoratedStart - this.f3647a.getStartAfterPadding();
            this.f3649c = decoratedStart;
            if (startAfterPadding2 > 0) {
                int endAfterPadding2 = (this.f3647a.getEndAfterPadding() - Math.min(0, (this.f3647a.getEndAfterPadding() - totalSpaceChange) - this.f3647a.getDecoratedEnd(view))) - (decoratedStart + this.f3647a.getDecoratedMeasurement(view));
                if (endAfterPadding2 < 0) {
                    this.f3649c -= Math.min(startAfterPadding2, -endAfterPadding2);
                }
            }
        }

        boolean b(View view, RecyclerView.State state) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return !layoutParams.isItemRemoved() && layoutParams.getViewLayoutPosition() >= 0 && layoutParams.getViewLayoutPosition() < state.getItemCount();
        }

        void c() {
            this.f3648b = -1;
            this.f3649c = Integer.MIN_VALUE;
            this.f3650d = false;
            this.f3651e = false;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.f3648b + ", mCoordinate=" + this.f3649c + ", mLayoutFromEnd=" + this.f3650d + ", mValid=" + this.f3651e + AbstractJsonLexerKt.END_OBJ;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static class LayoutChunkResult {
        public int mConsumed;
        public boolean mFinished;
        public boolean mFocusable;
        public boolean mIgnoreConsumed;

        protected LayoutChunkResult() {
        }

        void a() {
            this.mConsumed = 0;
            this.mFinished = false;
            this.mIgnoreConsumed = false;
            this.mFocusable = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class LayoutState {

        /* renamed from: b  reason: collision with root package name */
        int f3653b;

        /* renamed from: c  reason: collision with root package name */
        int f3654c;

        /* renamed from: d  reason: collision with root package name */
        int f3655d;

        /* renamed from: e  reason: collision with root package name */
        int f3656e;

        /* renamed from: f  reason: collision with root package name */
        int f3657f;

        /* renamed from: g  reason: collision with root package name */
        int f3658g;

        /* renamed from: j  reason: collision with root package name */
        boolean f3661j;

        /* renamed from: k  reason: collision with root package name */
        int f3662k;

        /* renamed from: m  reason: collision with root package name */
        boolean f3664m;

        /* renamed from: a  reason: collision with root package name */
        boolean f3652a = true;

        /* renamed from: h  reason: collision with root package name */
        int f3659h = 0;

        /* renamed from: i  reason: collision with root package name */
        int f3660i = 0;

        /* renamed from: l  reason: collision with root package name */
        List f3663l = null;

        LayoutState() {
        }

        private View nextViewFromScrapList() {
            int size = this.f3663l.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = ((RecyclerView.ViewHolder) this.f3663l.get(i2)).itemView;
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                if (!layoutParams.isItemRemoved() && this.f3655d == layoutParams.getViewLayoutPosition()) {
                    assignPositionFromScrapList(view);
                    return view;
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean a(RecyclerView.State state) {
            int i2 = this.f3655d;
            return i2 >= 0 && i2 < state.getItemCount();
        }

        public void assignPositionFromScrapList() {
            assignPositionFromScrapList(null);
        }

        public void assignPositionFromScrapList(View view) {
            View nextViewInLimitedList = nextViewInLimitedList(view);
            this.f3655d = nextViewInLimitedList == null ? -1 : ((RecyclerView.LayoutParams) nextViewInLimitedList.getLayoutParams()).getViewLayoutPosition();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public View b(RecyclerView.Recycler recycler) {
            if (this.f3663l != null) {
                return nextViewFromScrapList();
            }
            View viewForPosition = recycler.getViewForPosition(this.f3655d);
            this.f3655d += this.f3656e;
            return viewForPosition;
        }

        public View nextViewInLimitedList(View view) {
            int viewLayoutPosition;
            int size = this.f3663l.size();
            View view2 = null;
            int i2 = Integer.MAX_VALUE;
            for (int i3 = 0; i3 < size; i3++) {
                View view3 = ((RecyclerView.ViewHolder) this.f3663l.get(i3)).itemView;
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view3.getLayoutParams();
                if (view3 != view && !layoutParams.isItemRemoved() && (viewLayoutPosition = (layoutParams.getViewLayoutPosition() - this.f3655d) * this.f3656e) >= 0 && viewLayoutPosition < i2) {
                    view2 = view3;
                    if (viewLayoutPosition == 0) {
                        break;
                    }
                    i2 = viewLayoutPosition;
                }
            }
            return view2;
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: androidx.recyclerview.widget.LinearLayoutManager.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        int f3665a;

        /* renamed from: b  reason: collision with root package name */
        int f3666b;

        /* renamed from: c  reason: collision with root package name */
        boolean f3667c;

        public SavedState() {
        }

        SavedState(Parcel parcel) {
            this.f3665a = parcel.readInt();
            this.f3666b = parcel.readInt();
            this.f3667c = parcel.readInt() == 1;
        }

        public SavedState(SavedState savedState) {
            this.f3665a = savedState.f3665a;
            this.f3666b = savedState.f3666b;
            this.f3667c = savedState.f3667c;
        }

        boolean a() {
            return this.f3665a >= 0;
        }

        void b() {
            this.f3665a = -1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f3665a);
            parcel.writeInt(this.f3666b);
            parcel.writeInt(this.f3667c ? 1 : 0);
        }
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    public LinearLayoutManager(Context context, int i2, boolean z) {
        this.f3640k = 1;
        this.mReverseLayout = false;
        this.f3642m = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.f3643n = -1;
        this.f3644o = Integer.MIN_VALUE;
        this.f3645p = null;
        this.f3646q = new AnchorInfo();
        this.mLayoutChunkResult = new LayoutChunkResult();
        this.mInitialPrefetchItemCount = 2;
        this.mReusableIntPair = new int[2];
        setOrientation(i2);
        setReverseLayout(z);
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        this.f3640k = 1;
        this.mReverseLayout = false;
        this.f3642m = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.f3643n = -1;
        this.f3644o = Integer.MIN_VALUE;
        this.f3645p = null;
        this.f3646q = new AnchorInfo();
        this.mLayoutChunkResult = new LayoutChunkResult();
        this.mInitialPrefetchItemCount = 2;
        this.mReusableIntPair = new int[2];
        RecyclerView.LayoutManager.Properties properties = RecyclerView.LayoutManager.getProperties(context, attributeSet, i2, i3);
        setOrientation(properties.orientation);
        setReverseLayout(properties.reverseLayout);
        setStackFromEnd(properties.stackFromEnd);
    }

    private int computeScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        u();
        return ScrollbarHelper.a(state, this.f3641l, x(!this.mSmoothScrollbarEnabled, true), w(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
    }

    private int computeScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        u();
        return ScrollbarHelper.b(state, this.f3641l, x(!this.mSmoothScrollbarEnabled, true), w(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled, this.f3642m);
    }

    private int computeScrollRange(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        u();
        return ScrollbarHelper.c(state, this.f3641l, x(!this.mSmoothScrollbarEnabled, true), w(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
    }

    private View findFirstPartiallyOrCompletelyInvisibleChild() {
        return y(0, getChildCount());
    }

    private View findLastPartiallyOrCompletelyInvisibleChild() {
        return y(getChildCount() - 1, -1);
    }

    private View findPartiallyOrCompletelyInvisibleChildClosestToEnd() {
        return this.f3642m ? findFirstPartiallyOrCompletelyInvisibleChild() : findLastPartiallyOrCompletelyInvisibleChild();
    }

    private View findPartiallyOrCompletelyInvisibleChildClosestToStart() {
        return this.f3642m ? findLastPartiallyOrCompletelyInvisibleChild() : findFirstPartiallyOrCompletelyInvisibleChild();
    }

    private int fixLayoutEndGap(int i2, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int endAfterPadding;
        int endAfterPadding2 = this.f3641l.getEndAfterPadding() - i2;
        if (endAfterPadding2 > 0) {
            int i3 = -G(-endAfterPadding2, recycler, state);
            int i4 = i2 + i3;
            if (!z || (endAfterPadding = this.f3641l.getEndAfterPadding() - i4) <= 0) {
                return i3;
            }
            this.f3641l.offsetChildren(endAfterPadding);
            return endAfterPadding + i3;
        }
        return 0;
    }

    private int fixLayoutStartGap(int i2, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int startAfterPadding;
        int startAfterPadding2 = i2 - this.f3641l.getStartAfterPadding();
        if (startAfterPadding2 > 0) {
            int i3 = -G(startAfterPadding2, recycler, state);
            int i4 = i2 + i3;
            if (!z || (startAfterPadding = i4 - this.f3641l.getStartAfterPadding()) <= 0) {
                return i3;
            }
            this.f3641l.offsetChildren(-startAfterPadding);
            return i3 - startAfterPadding;
        }
        return 0;
    }

    private View getChildClosestToEnd() {
        return getChildAt(this.f3642m ? 0 : getChildCount() - 1);
    }

    private View getChildClosestToStart() {
        return getChildAt(this.f3642m ? getChildCount() - 1 : 0);
    }

    private void layoutForPredictiveAnimations(RecyclerView.Recycler recycler, RecyclerView.State state, int i2, int i3) {
        if (!state.willRunPredictiveAnimations() || getChildCount() == 0 || state.isPreLayout() || !supportsPredictiveItemAnimations()) {
            return;
        }
        List<RecyclerView.ViewHolder> scrapList = recycler.getScrapList();
        int size = scrapList.size();
        int position = getPosition(getChildAt(0));
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < size; i6++) {
            RecyclerView.ViewHolder viewHolder = scrapList.get(i6);
            if (!viewHolder.isRemoved()) {
                boolean z = (viewHolder.getLayoutPosition() < position) != this.f3642m ? true : true;
                int decoratedMeasurement = this.f3641l.getDecoratedMeasurement(viewHolder.itemView);
                if (z) {
                    i4 += decoratedMeasurement;
                } else {
                    i5 += decoratedMeasurement;
                }
            }
        }
        this.mLayoutState.f3663l = scrapList;
        if (i4 > 0) {
            updateLayoutStateToFillStart(getPosition(getChildClosestToStart()), i2);
            LayoutState layoutState = this.mLayoutState;
            layoutState.f3659h = i4;
            layoutState.f3654c = 0;
            layoutState.assignPositionFromScrapList();
            v(recycler, this.mLayoutState, state, false);
        }
        if (i5 > 0) {
            updateLayoutStateToFillEnd(getPosition(getChildClosestToEnd()), i3);
            LayoutState layoutState2 = this.mLayoutState;
            layoutState2.f3659h = i5;
            layoutState2.f3654c = 0;
            layoutState2.assignPositionFromScrapList();
            v(recycler, this.mLayoutState, state, false);
        }
        this.mLayoutState.f3663l = null;
    }

    private void logChildren() {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            StringBuilder sb = new StringBuilder();
            sb.append("item ");
            sb.append(getPosition(childAt));
            sb.append(", coord:");
            sb.append(this.f3641l.getDecoratedStart(childAt));
        }
    }

    private void recycleByLayoutState(RecyclerView.Recycler recycler, LayoutState layoutState) {
        if (!layoutState.f3652a || layoutState.f3664m) {
            return;
        }
        int i2 = layoutState.f3658g;
        int i3 = layoutState.f3660i;
        if (layoutState.f3657f == -1) {
            recycleViewsFromEnd(recycler, i2, i3);
        } else {
            recycleViewsFromStart(recycler, i2, i3);
        }
    }

    private void recycleChildren(RecyclerView.Recycler recycler, int i2, int i3) {
        if (i2 == i3) {
            return;
        }
        if (i3 <= i2) {
            while (i2 > i3) {
                removeAndRecycleViewAt(i2, recycler);
                i2--;
            }
            return;
        }
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            removeAndRecycleViewAt(i4, recycler);
        }
    }

    private void recycleViewsFromEnd(RecyclerView.Recycler recycler, int i2, int i3) {
        int childCount = getChildCount();
        if (i2 < 0) {
            return;
        }
        int end = (this.f3641l.getEnd() - i2) + i3;
        if (this.f3642m) {
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                if (this.f3641l.getDecoratedStart(childAt) < end || this.f3641l.getTransformedStartWithDecoration(childAt) < end) {
                    recycleChildren(recycler, 0, i4);
                    return;
                }
            }
            return;
        }
        int i5 = childCount - 1;
        for (int i6 = i5; i6 >= 0; i6--) {
            View childAt2 = getChildAt(i6);
            if (this.f3641l.getDecoratedStart(childAt2) < end || this.f3641l.getTransformedStartWithDecoration(childAt2) < end) {
                recycleChildren(recycler, i5, i6);
                return;
            }
        }
    }

    private void recycleViewsFromStart(RecyclerView.Recycler recycler, int i2, int i3) {
        if (i2 < 0) {
            return;
        }
        int i4 = i2 - i3;
        int childCount = getChildCount();
        if (!this.f3642m) {
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = getChildAt(i5);
                if (this.f3641l.getDecoratedEnd(childAt) > i4 || this.f3641l.getTransformedEndWithDecoration(childAt) > i4) {
                    recycleChildren(recycler, 0, i5);
                    return;
                }
            }
            return;
        }
        int i6 = childCount - 1;
        for (int i7 = i6; i7 >= 0; i7--) {
            View childAt2 = getChildAt(i7);
            if (this.f3641l.getDecoratedEnd(childAt2) > i4 || this.f3641l.getTransformedEndWithDecoration(childAt2) > i4) {
                recycleChildren(recycler, i6, i7);
                return;
            }
        }
    }

    private void resolveShouldLayoutReverse() {
        this.f3642m = (this.f3640k == 1 || !C()) ? this.mReverseLayout : !this.mReverseLayout;
    }

    private boolean updateAnchorFromChildren(RecyclerView.Recycler recycler, RecyclerView.State state, AnchorInfo anchorInfo) {
        View A;
        boolean z = false;
        if (getChildCount() == 0) {
            return false;
        }
        View focusedChild = getFocusedChild();
        if (focusedChild != null && anchorInfo.b(focusedChild, state)) {
            anchorInfo.assignFromViewAndKeepVisibleRect(focusedChild, getPosition(focusedChild));
            return true;
        }
        boolean z2 = this.mLastStackFromEnd;
        boolean z3 = this.mStackFromEnd;
        if (z2 == z3 && (A = A(recycler, state, anchorInfo.f3650d, z3)) != null) {
            anchorInfo.assignFromView(A, getPosition(A));
            if (!state.isPreLayout() && supportsPredictiveItemAnimations()) {
                int decoratedStart = this.f3641l.getDecoratedStart(A);
                int decoratedEnd = this.f3641l.getDecoratedEnd(A);
                int startAfterPadding = this.f3641l.getStartAfterPadding();
                int endAfterPadding = this.f3641l.getEndAfterPadding();
                boolean z4 = decoratedEnd <= startAfterPadding && decoratedStart < startAfterPadding;
                if (decoratedStart >= endAfterPadding && decoratedEnd > endAfterPadding) {
                    z = true;
                }
                if (z4 || z) {
                    if (anchorInfo.f3650d) {
                        startAfterPadding = endAfterPadding;
                    }
                    anchorInfo.f3649c = startAfterPadding;
                }
            }
            return true;
        }
        return false;
    }

    private boolean updateAnchorFromPendingData(RecyclerView.State state, AnchorInfo anchorInfo) {
        int i2;
        if (!state.isPreLayout() && (i2 = this.f3643n) != -1) {
            if (i2 >= 0 && i2 < state.getItemCount()) {
                anchorInfo.f3648b = this.f3643n;
                SavedState savedState = this.f3645p;
                if (savedState != null && savedState.a()) {
                    boolean z = this.f3645p.f3667c;
                    anchorInfo.f3650d = z;
                    anchorInfo.f3649c = z ? this.f3641l.getEndAfterPadding() - this.f3645p.f3666b : this.f3641l.getStartAfterPadding() + this.f3645p.f3666b;
                    return true;
                } else if (this.f3644o != Integer.MIN_VALUE) {
                    boolean z2 = this.f3642m;
                    anchorInfo.f3650d = z2;
                    anchorInfo.f3649c = z2 ? this.f3641l.getEndAfterPadding() - this.f3644o : this.f3641l.getStartAfterPadding() + this.f3644o;
                    return true;
                } else {
                    View findViewByPosition = findViewByPosition(this.f3643n);
                    if (findViewByPosition == null) {
                        if (getChildCount() > 0) {
                            anchorInfo.f3650d = (this.f3643n < getPosition(getChildAt(0))) == this.f3642m;
                        }
                        anchorInfo.a();
                    } else if (this.f3641l.getDecoratedMeasurement(findViewByPosition) > this.f3641l.getTotalSpace()) {
                        anchorInfo.a();
                        return true;
                    } else if (this.f3641l.getDecoratedStart(findViewByPosition) - this.f3641l.getStartAfterPadding() < 0) {
                        anchorInfo.f3649c = this.f3641l.getStartAfterPadding();
                        anchorInfo.f3650d = false;
                        return true;
                    } else if (this.f3641l.getEndAfterPadding() - this.f3641l.getDecoratedEnd(findViewByPosition) < 0) {
                        anchorInfo.f3649c = this.f3641l.getEndAfterPadding();
                        anchorInfo.f3650d = true;
                        return true;
                    } else {
                        anchorInfo.f3649c = anchorInfo.f3650d ? this.f3641l.getDecoratedEnd(findViewByPosition) + this.f3641l.getTotalSpaceChange() : this.f3641l.getDecoratedStart(findViewByPosition);
                    }
                    return true;
                }
            }
            this.f3643n = -1;
            this.f3644o = Integer.MIN_VALUE;
        }
        return false;
    }

    private void updateAnchorInfoForLayout(RecyclerView.Recycler recycler, RecyclerView.State state, AnchorInfo anchorInfo) {
        if (updateAnchorFromPendingData(state, anchorInfo) || updateAnchorFromChildren(recycler, state, anchorInfo)) {
            return;
        }
        anchorInfo.a();
        anchorInfo.f3648b = this.mStackFromEnd ? state.getItemCount() - 1 : 0;
    }

    private void updateLayoutState(int i2, int i3, boolean z, RecyclerView.State state) {
        int startAfterPadding;
        this.mLayoutState.f3664m = F();
        this.mLayoutState.f3657f = i2;
        int[] iArr = this.mReusableIntPair;
        iArr[0] = 0;
        iArr[1] = 0;
        r(state, iArr);
        int max = Math.max(0, this.mReusableIntPair[0]);
        int max2 = Math.max(0, this.mReusableIntPair[1]);
        boolean z2 = i2 == 1;
        LayoutState layoutState = this.mLayoutState;
        int i4 = z2 ? max2 : max;
        layoutState.f3659h = i4;
        if (!z2) {
            max = max2;
        }
        layoutState.f3660i = max;
        if (z2) {
            layoutState.f3659h = i4 + this.f3641l.getEndPadding();
            View childClosestToEnd = getChildClosestToEnd();
            LayoutState layoutState2 = this.mLayoutState;
            layoutState2.f3656e = this.f3642m ? -1 : 1;
            int position = getPosition(childClosestToEnd);
            LayoutState layoutState3 = this.mLayoutState;
            layoutState2.f3655d = position + layoutState3.f3656e;
            layoutState3.f3653b = this.f3641l.getDecoratedEnd(childClosestToEnd);
            startAfterPadding = this.f3641l.getDecoratedEnd(childClosestToEnd) - this.f3641l.getEndAfterPadding();
        } else {
            View childClosestToStart = getChildClosestToStart();
            this.mLayoutState.f3659h += this.f3641l.getStartAfterPadding();
            LayoutState layoutState4 = this.mLayoutState;
            layoutState4.f3656e = this.f3642m ? 1 : -1;
            int position2 = getPosition(childClosestToStart);
            LayoutState layoutState5 = this.mLayoutState;
            layoutState4.f3655d = position2 + layoutState5.f3656e;
            layoutState5.f3653b = this.f3641l.getDecoratedStart(childClosestToStart);
            startAfterPadding = (-this.f3641l.getDecoratedStart(childClosestToStart)) + this.f3641l.getStartAfterPadding();
        }
        LayoutState layoutState6 = this.mLayoutState;
        layoutState6.f3654c = i3;
        if (z) {
            layoutState6.f3654c = i3 - startAfterPadding;
        }
        layoutState6.f3658g = startAfterPadding;
    }

    private void updateLayoutStateToFillEnd(int i2, int i3) {
        this.mLayoutState.f3654c = this.f3641l.getEndAfterPadding() - i3;
        LayoutState layoutState = this.mLayoutState;
        layoutState.f3656e = this.f3642m ? -1 : 1;
        layoutState.f3655d = i2;
        layoutState.f3657f = 1;
        layoutState.f3653b = i3;
        layoutState.f3658g = Integer.MIN_VALUE;
    }

    private void updateLayoutStateToFillEnd(AnchorInfo anchorInfo) {
        updateLayoutStateToFillEnd(anchorInfo.f3648b, anchorInfo.f3649c);
    }

    private void updateLayoutStateToFillStart(int i2, int i3) {
        this.mLayoutState.f3654c = i3 - this.f3641l.getStartAfterPadding();
        LayoutState layoutState = this.mLayoutState;
        layoutState.f3655d = i2;
        layoutState.f3656e = this.f3642m ? 1 : -1;
        layoutState.f3657f = -1;
        layoutState.f3653b = i3;
        layoutState.f3658g = Integer.MIN_VALUE;
    }

    private void updateLayoutStateToFillStart(AnchorInfo anchorInfo) {
        updateLayoutStateToFillStart(anchorInfo.f3648b, anchorInfo.f3649c);
    }

    View A(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z, boolean z2) {
        int i2;
        int i3;
        u();
        int childCount = getChildCount();
        int i4 = -1;
        if (z2) {
            i2 = getChildCount() - 1;
            i3 = -1;
        } else {
            i4 = childCount;
            i2 = 0;
            i3 = 1;
        }
        int itemCount = state.getItemCount();
        int startAfterPadding = this.f3641l.getStartAfterPadding();
        int endAfterPadding = this.f3641l.getEndAfterPadding();
        View view = null;
        View view2 = null;
        View view3 = null;
        while (i2 != i4) {
            View childAt = getChildAt(i2);
            int position = getPosition(childAt);
            int decoratedStart = this.f3641l.getDecoratedStart(childAt);
            int decoratedEnd = this.f3641l.getDecoratedEnd(childAt);
            if (position >= 0 && position < itemCount) {
                if (!((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    boolean z3 = decoratedEnd <= startAfterPadding && decoratedStart < startAfterPadding;
                    boolean z4 = decoratedStart >= endAfterPadding && decoratedEnd > endAfterPadding;
                    if (!z3 && !z4) {
                        return childAt;
                    }
                    if (z) {
                        if (!z4) {
                            if (view != null) {
                            }
                            view = childAt;
                        }
                        view2 = childAt;
                    } else {
                        if (!z3) {
                            if (view != null) {
                            }
                            view = childAt;
                        }
                        view2 = childAt;
                    }
                } else if (view3 == null) {
                    view3 = childAt;
                }
            }
            i2 += i3;
        }
        return view != null ? view : view2 != null ? view2 : view3;
    }

    @Deprecated
    protected int B(RecyclerView.State state) {
        if (state.hasTargetScrollPosition()) {
            return this.f3641l.getTotalSpace();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean C() {
        return getLayoutDirection() == 1;
    }

    void D(RecyclerView.Recycler recycler, RecyclerView.State state, LayoutState layoutState, LayoutChunkResult layoutChunkResult) {
        int i2;
        int i3;
        int i4;
        int i5;
        int decoratedMeasurementInOther;
        View b2 = layoutState.b(recycler);
        if (b2 == null) {
            layoutChunkResult.mFinished = true;
            return;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) b2.getLayoutParams();
        if (layoutState.f3663l == null) {
            if (this.f3642m == (layoutState.f3657f == -1)) {
                addView(b2);
            } else {
                addView(b2, 0);
            }
        } else {
            if (this.f3642m == (layoutState.f3657f == -1)) {
                addDisappearingView(b2);
            } else {
                addDisappearingView(b2, 0);
            }
        }
        measureChildWithMargins(b2, 0, 0);
        layoutChunkResult.mConsumed = this.f3641l.getDecoratedMeasurement(b2);
        if (this.f3640k == 1) {
            if (C()) {
                decoratedMeasurementInOther = getWidth() - getPaddingRight();
                i5 = decoratedMeasurementInOther - this.f3641l.getDecoratedMeasurementInOther(b2);
            } else {
                i5 = getPaddingLeft();
                decoratedMeasurementInOther = this.f3641l.getDecoratedMeasurementInOther(b2) + i5;
            }
            int i6 = layoutState.f3657f;
            int i7 = layoutState.f3653b;
            if (i6 == -1) {
                i4 = i7;
                i3 = decoratedMeasurementInOther;
                i2 = i7 - layoutChunkResult.mConsumed;
            } else {
                i2 = i7;
                i3 = decoratedMeasurementInOther;
                i4 = layoutChunkResult.mConsumed + i7;
            }
        } else {
            int paddingTop = getPaddingTop();
            int decoratedMeasurementInOther2 = this.f3641l.getDecoratedMeasurementInOther(b2) + paddingTop;
            int i8 = layoutState.f3657f;
            int i9 = layoutState.f3653b;
            if (i8 == -1) {
                i3 = i9;
                i2 = paddingTop;
                i4 = decoratedMeasurementInOther2;
                i5 = i9 - layoutChunkResult.mConsumed;
            } else {
                i2 = paddingTop;
                i3 = layoutChunkResult.mConsumed + i9;
                i4 = decoratedMeasurementInOther2;
                i5 = i9;
            }
        }
        layoutDecoratedWithMargins(b2, i5, i2, i3, i4);
        if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
            layoutChunkResult.mIgnoreConsumed = true;
        }
        layoutChunkResult.mFocusable = b2.hasFocusable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void E(RecyclerView.Recycler recycler, RecyclerView.State state, AnchorInfo anchorInfo, int i2) {
    }

    boolean F() {
        return this.f3641l.getMode() == 0 && this.f3641l.getEnd() == 0;
    }

    int G(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i2 == 0) {
            return 0;
        }
        u();
        this.mLayoutState.f3652a = true;
        int i3 = i2 > 0 ? 1 : -1;
        int abs = Math.abs(i2);
        updateLayoutState(i3, abs, true, state);
        LayoutState layoutState = this.mLayoutState;
        int v = layoutState.f3658g + v(recycler, layoutState, state, false);
        if (v < 0) {
            return 0;
        }
        if (abs > v) {
            i2 = i3 * v;
        }
        this.f3641l.offsetChildren(-i2);
        this.mLayoutState.f3662k = i2;
        return i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void assertNotInLayoutOrScroll(String str) {
        if (this.f3645p == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return this.f3640k == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.f3640k == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void collectAdjacentPrefetchPositions(int i2, int i3, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        if (this.f3640k != 0) {
            i2 = i3;
        }
        if (getChildCount() == 0 || i2 == 0) {
            return;
        }
        u();
        updateLayoutState(i2 > 0 ? 1 : -1, Math.abs(i2), true, state);
        s(state, this.mLayoutState, layoutPrefetchRegistry);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void collectInitialPrefetchPositions(int i2, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        boolean z;
        int i3;
        SavedState savedState = this.f3645p;
        if (savedState == null || !savedState.a()) {
            resolveShouldLayoutReverse();
            z = this.f3642m;
            i3 = this.f3643n;
            if (i3 == -1) {
                i3 = z ? i2 - 1 : 0;
            }
        } else {
            SavedState savedState2 = this.f3645p;
            z = savedState2.f3667c;
            i3 = savedState2.f3665a;
        }
        int i4 = z ? -1 : 1;
        for (int i5 = 0; i5 < this.mInitialPrefetchItemCount && i3 >= 0 && i3 < i2; i5++) {
            layoutPrefetchRegistry.addPosition(i3, 0);
            i3 += i4;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int i2) {
        if (getChildCount() == 0) {
            return null;
        }
        int i3 = (i2 < getPosition(getChildAt(0))) != this.f3642m ? -1 : 1;
        return this.f3640k == 0 ? new PointF(i3, 0.0f) : new PointF(0.0f, i3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int convertFocusDirectionToLayoutDirection(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 17 ? i2 != 33 ? i2 != 66 ? (i2 == 130 && this.f3640k == 1) ? 1 : Integer.MIN_VALUE : this.f3640k == 0 ? 1 : Integer.MIN_VALUE : this.f3640k == 1 ? -1 : Integer.MIN_VALUE : this.f3640k == 0 ? -1 : Integer.MIN_VALUE : (this.f3640k != 1 && C()) ? -1 : 1 : (this.f3640k != 1 && C()) ? 1 : -1;
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View z = z(0, getChildCount(), true, false);
        if (z == null) {
            return -1;
        }
        return getPosition(z);
    }

    public int findFirstVisibleItemPosition() {
        View z = z(0, getChildCount(), false, true);
        if (z == null) {
            return -1;
        }
        return getPosition(z);
    }

    public int findLastCompletelyVisibleItemPosition() {
        View z = z(getChildCount() - 1, -1, true, false);
        if (z == null) {
            return -1;
        }
        return getPosition(z);
    }

    public int findLastVisibleItemPosition() {
        View z = z(getChildCount() - 1, -1, false, true);
        if (z == null) {
            return -1;
        }
        return getPosition(z);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View findViewByPosition(int i2) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return null;
        }
        int position = i2 - getPosition(getChildAt(0));
        if (position >= 0 && position < childCount) {
            View childAt = getChildAt(position);
            if (getPosition(childAt) == i2) {
                return childAt;
            }
        }
        return super.findViewByPosition(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    public int getInitialPrefetchItemCount() {
        return this.mInitialPrefetchItemCount;
    }

    public int getOrientation() {
        return this.f3640k;
    }

    public boolean getRecycleChildrenOnDetach() {
        return this.mRecycleChildrenOnDetach;
    }

    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }

    public boolean getStackFromEnd() {
        return this.mStackFromEnd;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    public boolean isSmoothScrollbarEnabled() {
        return this.mSmoothScrollbarEnabled;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    boolean o() {
        return (getHeightMode() == 1073741824 || getWidthMode() == 1073741824 || !c()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        if (this.mRecycleChildrenOnDetach) {
            removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View onFocusSearchFailed(View view, int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int convertFocusDirectionToLayoutDirection;
        resolveShouldLayoutReverse();
        if (getChildCount() == 0 || (convertFocusDirectionToLayoutDirection = convertFocusDirectionToLayoutDirection(i2)) == Integer.MIN_VALUE) {
            return null;
        }
        u();
        updateLayoutState(convertFocusDirectionToLayoutDirection, (int) (this.f3641l.getTotalSpace() * MAX_SCROLL_FACTOR), false, state);
        LayoutState layoutState = this.mLayoutState;
        layoutState.f3658g = Integer.MIN_VALUE;
        layoutState.f3652a = false;
        v(recycler, layoutState, state, true);
        View findPartiallyOrCompletelyInvisibleChildClosestToStart = convertFocusDirectionToLayoutDirection == -1 ? findPartiallyOrCompletelyInvisibleChildClosestToStart() : findPartiallyOrCompletelyInvisibleChildClosestToEnd();
        View childClosestToStart = convertFocusDirectionToLayoutDirection == -1 ? getChildClosestToStart() : getChildClosestToEnd();
        if (childClosestToStart.hasFocusable()) {
            if (findPartiallyOrCompletelyInvisibleChildClosestToStart == null) {
                return null;
            }
            return childClosestToStart;
        }
        return findPartiallyOrCompletelyInvisibleChildClosestToStart;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            accessibilityEvent.setFromIndex(findFirstVisibleItemPosition());
            accessibilityEvent.setToIndex(findLastVisibleItemPosition());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i2;
        int i3;
        int i4;
        int i5;
        int fixLayoutEndGap;
        int i6;
        View findViewByPosition;
        int decoratedStart;
        int i7;
        int i8 = -1;
        if (!(this.f3645p == null && this.f3643n == -1) && state.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            return;
        }
        SavedState savedState = this.f3645p;
        if (savedState != null && savedState.a()) {
            this.f3643n = this.f3645p.f3665a;
        }
        u();
        this.mLayoutState.f3652a = false;
        resolveShouldLayoutReverse();
        View focusedChild = getFocusedChild();
        AnchorInfo anchorInfo = this.f3646q;
        if (!anchorInfo.f3651e || this.f3643n != -1 || this.f3645p != null) {
            anchorInfo.c();
            AnchorInfo anchorInfo2 = this.f3646q;
            anchorInfo2.f3650d = this.f3642m ^ this.mStackFromEnd;
            updateAnchorInfoForLayout(recycler, state, anchorInfo2);
            this.f3646q.f3651e = true;
        } else if (focusedChild != null && (this.f3641l.getDecoratedStart(focusedChild) >= this.f3641l.getEndAfterPadding() || this.f3641l.getDecoratedEnd(focusedChild) <= this.f3641l.getStartAfterPadding())) {
            this.f3646q.assignFromViewAndKeepVisibleRect(focusedChild, getPosition(focusedChild));
        }
        LayoutState layoutState = this.mLayoutState;
        layoutState.f3657f = layoutState.f3662k >= 0 ? 1 : -1;
        int[] iArr = this.mReusableIntPair;
        iArr[0] = 0;
        iArr[1] = 0;
        r(state, iArr);
        int max = Math.max(0, this.mReusableIntPair[0]) + this.f3641l.getStartAfterPadding();
        int max2 = Math.max(0, this.mReusableIntPair[1]) + this.f3641l.getEndPadding();
        if (state.isPreLayout() && (i6 = this.f3643n) != -1 && this.f3644o != Integer.MIN_VALUE && (findViewByPosition = findViewByPosition(i6)) != null) {
            if (this.f3642m) {
                i7 = this.f3641l.getEndAfterPadding() - this.f3641l.getDecoratedEnd(findViewByPosition);
                decoratedStart = this.f3644o;
            } else {
                decoratedStart = this.f3641l.getDecoratedStart(findViewByPosition) - this.f3641l.getStartAfterPadding();
                i7 = this.f3644o;
            }
            int i9 = i7 - decoratedStart;
            if (i9 > 0) {
                max += i9;
            } else {
                max2 -= i9;
            }
        }
        AnchorInfo anchorInfo3 = this.f3646q;
        if (!anchorInfo3.f3650d ? !this.f3642m : this.f3642m) {
            i8 = 1;
        }
        E(recycler, state, anchorInfo3, i8);
        detachAndScrapAttachedViews(recycler);
        this.mLayoutState.f3664m = F();
        this.mLayoutState.f3661j = state.isPreLayout();
        this.mLayoutState.f3660i = 0;
        AnchorInfo anchorInfo4 = this.f3646q;
        if (anchorInfo4.f3650d) {
            updateLayoutStateToFillStart(anchorInfo4);
            LayoutState layoutState2 = this.mLayoutState;
            layoutState2.f3659h = max;
            v(recycler, layoutState2, state, false);
            LayoutState layoutState3 = this.mLayoutState;
            i3 = layoutState3.f3653b;
            int i10 = layoutState3.f3655d;
            int i11 = layoutState3.f3654c;
            if (i11 > 0) {
                max2 += i11;
            }
            updateLayoutStateToFillEnd(this.f3646q);
            LayoutState layoutState4 = this.mLayoutState;
            layoutState4.f3659h = max2;
            layoutState4.f3655d += layoutState4.f3656e;
            v(recycler, layoutState4, state, false);
            LayoutState layoutState5 = this.mLayoutState;
            i2 = layoutState5.f3653b;
            int i12 = layoutState5.f3654c;
            if (i12 > 0) {
                updateLayoutStateToFillStart(i10, i3);
                LayoutState layoutState6 = this.mLayoutState;
                layoutState6.f3659h = i12;
                v(recycler, layoutState6, state, false);
                i3 = this.mLayoutState.f3653b;
            }
        } else {
            updateLayoutStateToFillEnd(anchorInfo4);
            LayoutState layoutState7 = this.mLayoutState;
            layoutState7.f3659h = max2;
            v(recycler, layoutState7, state, false);
            LayoutState layoutState8 = this.mLayoutState;
            i2 = layoutState8.f3653b;
            int i13 = layoutState8.f3655d;
            int i14 = layoutState8.f3654c;
            if (i14 > 0) {
                max += i14;
            }
            updateLayoutStateToFillStart(this.f3646q);
            LayoutState layoutState9 = this.mLayoutState;
            layoutState9.f3659h = max;
            layoutState9.f3655d += layoutState9.f3656e;
            v(recycler, layoutState9, state, false);
            LayoutState layoutState10 = this.mLayoutState;
            i3 = layoutState10.f3653b;
            int i15 = layoutState10.f3654c;
            if (i15 > 0) {
                updateLayoutStateToFillEnd(i13, i2);
                LayoutState layoutState11 = this.mLayoutState;
                layoutState11.f3659h = i15;
                v(recycler, layoutState11, state, false);
                i2 = this.mLayoutState.f3653b;
            }
        }
        if (getChildCount() > 0) {
            if (this.f3642m ^ this.mStackFromEnd) {
                int fixLayoutEndGap2 = fixLayoutEndGap(i2, recycler, state, true);
                i4 = i3 + fixLayoutEndGap2;
                i5 = i2 + fixLayoutEndGap2;
                fixLayoutEndGap = fixLayoutStartGap(i4, recycler, state, false);
            } else {
                int fixLayoutStartGap = fixLayoutStartGap(i3, recycler, state, true);
                i4 = i3 + fixLayoutStartGap;
                i5 = i2 + fixLayoutStartGap;
                fixLayoutEndGap = fixLayoutEndGap(i5, recycler, state, false);
            }
            i3 = i4 + fixLayoutEndGap;
            i2 = i5 + fixLayoutEndGap;
        }
        layoutForPredictiveAnimations(recycler, state, i3, i2);
        if (state.isPreLayout()) {
            this.f3646q.c();
        } else {
            this.f3641l.onLayoutComplete();
        }
        this.mLastStackFromEnd = this.mStackFromEnd;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.f3645p = null;
        this.f3643n = -1;
        this.f3644o = Integer.MIN_VALUE;
        this.f3646q.c();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.f3645p = savedState;
            if (this.f3643n != -1) {
                savedState.b();
            }
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        if (this.f3645p != null) {
            return new SavedState(this.f3645p);
        }
        SavedState savedState = new SavedState();
        if (getChildCount() > 0) {
            u();
            boolean z = this.mLastStackFromEnd ^ this.f3642m;
            savedState.f3667c = z;
            if (z) {
                View childClosestToEnd = getChildClosestToEnd();
                savedState.f3666b = this.f3641l.getEndAfterPadding() - this.f3641l.getDecoratedEnd(childClosestToEnd);
                savedState.f3665a = getPosition(childClosestToEnd);
            } else {
                View childClosestToStart = getChildClosestToStart();
                savedState.f3665a = getPosition(childClosestToStart);
                savedState.f3666b = this.f3641l.getDecoratedStart(childClosestToStart) - this.f3641l.getStartAfterPadding();
            }
        } else {
            savedState.b();
        }
        return savedState;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.ViewDropHandler
    public void prepareForDrop(@NonNull View view, @NonNull View view2, int i2, int i3) {
        int decoratedStart;
        assertNotInLayoutOrScroll("Cannot drop a view during a scroll or layout calculation");
        u();
        resolveShouldLayoutReverse();
        int position = getPosition(view);
        int position2 = getPosition(view2);
        boolean z = position < position2 ? true : true;
        if (this.f3642m) {
            if (z) {
                scrollToPositionWithOffset(position2, this.f3641l.getEndAfterPadding() - (this.f3641l.getDecoratedStart(view2) + this.f3641l.getDecoratedMeasurement(view)));
                return;
            }
            decoratedStart = this.f3641l.getEndAfterPadding() - this.f3641l.getDecoratedEnd(view2);
        } else if (!z) {
            scrollToPositionWithOffset(position2, this.f3641l.getDecoratedEnd(view2) - this.f3641l.getDecoratedMeasurement(view));
            return;
        } else {
            decoratedStart = this.f3641l.getDecoratedStart(view2);
        }
        scrollToPositionWithOffset(position2, decoratedStart);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void r(@NonNull RecyclerView.State state, @NonNull int[] iArr) {
        int i2;
        int B = B(state);
        if (this.mLayoutState.f3657f == -1) {
            i2 = 0;
        } else {
            i2 = B;
            B = 0;
        }
        iArr[0] = B;
        iArr[1] = i2;
    }

    void s(RecyclerView.State state, LayoutState layoutState, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int i2 = layoutState.f3655d;
        if (i2 < 0 || i2 >= state.getItemCount()) {
            return;
        }
        layoutPrefetchRegistry.addPosition(i2, Math.max(0, layoutState.f3658g));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.f3640k == 1) {
            return 0;
        }
        return G(i2, recycler, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i2) {
        this.f3643n = i2;
        this.f3644o = Integer.MIN_VALUE;
        SavedState savedState = this.f3645p;
        if (savedState != null) {
            savedState.b();
        }
        requestLayout();
    }

    public void scrollToPositionWithOffset(int i2, int i3) {
        this.f3643n = i2;
        this.f3644o = i3;
        SavedState savedState = this.f3645p;
        if (savedState != null) {
            savedState.b();
        }
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.f3640k == 0) {
            return 0;
        }
        return G(i2, recycler, state);
    }

    public void setInitialPrefetchItemCount(int i2) {
        this.mInitialPrefetchItemCount = i2;
    }

    public void setOrientation(int i2) {
        if (i2 != 0 && i2 != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i2);
        }
        assertNotInLayoutOrScroll(null);
        if (i2 != this.f3640k || this.f3641l == null) {
            OrientationHelper createOrientationHelper = OrientationHelper.createOrientationHelper(this, i2);
            this.f3641l = createOrientationHelper;
            this.f3646q.f3647a = createOrientationHelper;
            this.f3640k = i2;
            requestLayout();
        }
    }

    public void setRecycleChildrenOnDetach(boolean z) {
        this.mRecycleChildrenOnDetach = z;
    }

    public void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (z == this.mReverseLayout) {
            return;
        }
        this.mReverseLayout = z;
        requestLayout();
    }

    public void setSmoothScrollbarEnabled(boolean z) {
        this.mSmoothScrollbarEnabled = z;
    }

    public void setStackFromEnd(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (this.mStackFromEnd == z) {
            return;
        }
        this.mStackFromEnd = z;
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i2) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i2);
        startSmoothScroll(linearSmoothScroller);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return this.f3645p == null && this.mLastStackFromEnd == this.mStackFromEnd;
    }

    LayoutState t() {
        return new LayoutState();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void u() {
        if (this.mLayoutState == null) {
            this.mLayoutState = t();
        }
    }

    int v(RecyclerView.Recycler recycler, LayoutState layoutState, RecyclerView.State state, boolean z) {
        int i2 = layoutState.f3654c;
        int i3 = layoutState.f3658g;
        if (i3 != Integer.MIN_VALUE) {
            if (i2 < 0) {
                layoutState.f3658g = i3 + i2;
            }
            recycleByLayoutState(recycler, layoutState);
        }
        int i4 = layoutState.f3654c + layoutState.f3659h;
        LayoutChunkResult layoutChunkResult = this.mLayoutChunkResult;
        while (true) {
            if ((!layoutState.f3664m && i4 <= 0) || !layoutState.a(state)) {
                break;
            }
            layoutChunkResult.a();
            D(recycler, state, layoutState, layoutChunkResult);
            if (!layoutChunkResult.mFinished) {
                layoutState.f3653b += layoutChunkResult.mConsumed * layoutState.f3657f;
                if (!layoutChunkResult.mIgnoreConsumed || layoutState.f3663l != null || !state.isPreLayout()) {
                    int i5 = layoutState.f3654c;
                    int i6 = layoutChunkResult.mConsumed;
                    layoutState.f3654c = i5 - i6;
                    i4 -= i6;
                }
                int i7 = layoutState.f3658g;
                if (i7 != Integer.MIN_VALUE) {
                    int i8 = i7 + layoutChunkResult.mConsumed;
                    layoutState.f3658g = i8;
                    int i9 = layoutState.f3654c;
                    if (i9 < 0) {
                        layoutState.f3658g = i8 + i9;
                    }
                    recycleByLayoutState(recycler, layoutState);
                }
                if (z && layoutChunkResult.mFocusable) {
                    break;
                }
            } else {
                break;
            }
        }
        return i2 - layoutState.f3654c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View w(boolean z, boolean z2) {
        int childCount;
        int i2;
        if (this.f3642m) {
            childCount = 0;
            i2 = getChildCount();
        } else {
            childCount = getChildCount() - 1;
            i2 = -1;
        }
        return z(childCount, i2, z, z2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View x(boolean z, boolean z2) {
        int i2;
        int childCount;
        if (this.f3642m) {
            i2 = getChildCount() - 1;
            childCount = -1;
        } else {
            i2 = 0;
            childCount = getChildCount();
        }
        return z(i2, childCount, z, z2);
    }

    View y(int i2, int i3) {
        int i4;
        int i5;
        u();
        if ((i3 > i2 ? (char) 1 : i3 < i2 ? (char) 65535 : (char) 0) == 0) {
            return getChildAt(i2);
        }
        if (this.f3641l.getDecoratedStart(getChildAt(i2)) < this.f3641l.getStartAfterPadding()) {
            i4 = 16644;
            i5 = 16388;
        } else {
            i4 = 4161;
            i5 = FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
        }
        return (this.f3640k == 0 ? this.f3718c : this.f3719d).a(i2, i3, i4, i5);
    }

    View z(int i2, int i3, boolean z, boolean z2) {
        u();
        return (this.f3640k == 0 ? this.f3718c : this.f3719d).a(i2, i3, z ? 24579 : 320, z2 ? 320 : 0);
    }
}
