package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
/* loaded from: classes.dex */
public class GridLayoutManager extends LinearLayoutManager {
    private static final boolean DEBUG = false;
    public static final int DEFAULT_SPAN_COUNT = -1;
    private static final String TAG = "GridLayoutManager";
    private boolean mUsingSpansToEstimateScrollBarDimensions;

    /* renamed from: r  reason: collision with root package name */
    boolean f3584r;

    /* renamed from: s  reason: collision with root package name */
    int f3585s;

    /* renamed from: t  reason: collision with root package name */
    int[] f3586t;
    View[] u;
    final SparseIntArray v;
    final SparseIntArray w;
    SpanSizeLookup x;
    final Rect y;

    /* loaded from: classes.dex */
    public static final class DefaultSpanSizeLookup extends SpanSizeLookup {
        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanIndex(int i2, int i3) {
            return i2 % i3;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int i2) {
            return 1;
        }
    }

    /* loaded from: classes.dex */
    public static class LayoutParams extends RecyclerView.LayoutParams {
        public static final int INVALID_SPAN_ID = -1;

        /* renamed from: e  reason: collision with root package name */
        int f3587e;

        /* renamed from: f  reason: collision with root package name */
        int f3588f;

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
            this.f3587e = -1;
            this.f3588f = 0;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f3587e = -1;
            this.f3588f = 0;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f3587e = -1;
            this.f3588f = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f3587e = -1;
            this.f3588f = 0;
        }

        public LayoutParams(RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
            this.f3587e = -1;
            this.f3588f = 0;
        }

        public int getSpanIndex() {
            return this.f3587e;
        }

        public int getSpanSize() {
            return this.f3588f;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class SpanSizeLookup {

        /* renamed from: a  reason: collision with root package name */
        final SparseIntArray f3589a = new SparseIntArray();

        /* renamed from: b  reason: collision with root package name */
        final SparseIntArray f3590b = new SparseIntArray();
        private boolean mCacheSpanIndices = false;
        private boolean mCacheSpanGroupIndices = false;

        static int a(SparseIntArray sparseIntArray, int i2) {
            int size = sparseIntArray.size() - 1;
            int i3 = 0;
            while (i3 <= size) {
                int i4 = (i3 + size) >>> 1;
                if (sparseIntArray.keyAt(i4) < i2) {
                    i3 = i4 + 1;
                } else {
                    size = i4 - 1;
                }
            }
            int i5 = i3 - 1;
            if (i5 < 0 || i5 >= sparseIntArray.size()) {
                return -1;
            }
            return sparseIntArray.keyAt(i5);
        }

        int b(int i2, int i3) {
            if (this.mCacheSpanGroupIndices) {
                int i4 = this.f3590b.get(i2, -1);
                if (i4 != -1) {
                    return i4;
                }
                int spanGroupIndex = getSpanGroupIndex(i2, i3);
                this.f3590b.put(i2, spanGroupIndex);
                return spanGroupIndex;
            }
            return getSpanGroupIndex(i2, i3);
        }

        int c(int i2, int i3) {
            if (this.mCacheSpanIndices) {
                int i4 = this.f3589a.get(i2, -1);
                if (i4 != -1) {
                    return i4;
                }
                int spanIndex = getSpanIndex(i2, i3);
                this.f3589a.put(i2, spanIndex);
                return spanIndex;
            }
            return getSpanIndex(i2, i3);
        }

        public int getSpanGroupIndex(int i2, int i3) {
            int i4;
            int i5;
            int i6;
            int a2;
            if (!this.mCacheSpanGroupIndices || (a2 = a(this.f3590b, i2)) == -1) {
                i4 = 0;
                i5 = 0;
                i6 = 0;
            } else {
                i4 = this.f3590b.get(a2);
                i5 = a2 + 1;
                i6 = c(a2, i3) + getSpanSize(a2);
                if (i6 == i3) {
                    i4++;
                    i6 = 0;
                }
            }
            int spanSize = getSpanSize(i2);
            while (i5 < i2) {
                int spanSize2 = getSpanSize(i5);
                i6 += spanSize2;
                if (i6 == i3) {
                    i4++;
                    i6 = 0;
                } else if (i6 > i3) {
                    i4++;
                    i6 = spanSize2;
                }
                i5++;
            }
            return i6 + spanSize > i3 ? i4 + 1 : i4;
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0033  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x002b -> B:17:0x0030). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x002d -> B:17:0x0030). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x002f -> B:17:0x0030). Please submit an issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public int getSpanIndex(int i2, int i3) {
            int i4;
            int i5;
            int spanSize = getSpanSize(i2);
            if (spanSize == i3) {
                return 0;
            }
            if (!this.mCacheSpanIndices || (i4 = a(this.f3589a, i2)) < 0) {
                i4 = 0;
                i5 = 0;
                if (i4 >= i2) {
                    int spanSize2 = getSpanSize(i4);
                    i5 += spanSize2;
                    if (i5 == i3) {
                        i5 = 0;
                    } else if (i5 > i3) {
                        i5 = spanSize2;
                    }
                    i4++;
                    if (i4 >= i2) {
                        if (spanSize + i5 <= i3) {
                            return i5;
                        }
                        return 0;
                    }
                }
            } else {
                i5 = this.f3589a.get(i4) + getSpanSize(i4);
                i4++;
                if (i4 >= i2) {
                }
            }
        }

        public abstract int getSpanSize(int i2);

        public void invalidateSpanGroupIndexCache() {
            this.f3590b.clear();
        }

        public void invalidateSpanIndexCache() {
            this.f3589a.clear();
        }

        public boolean isSpanGroupIndexCacheEnabled() {
            return this.mCacheSpanGroupIndices;
        }

        public boolean isSpanIndexCacheEnabled() {
            return this.mCacheSpanIndices;
        }

        public void setSpanGroupIndexCacheEnabled(boolean z) {
            if (!z) {
                this.f3590b.clear();
            }
            this.mCacheSpanGroupIndices = z;
        }

        public void setSpanIndexCacheEnabled(boolean z) {
            if (!z) {
                this.f3590b.clear();
            }
            this.mCacheSpanIndices = z;
        }
    }

    public GridLayoutManager(Context context, int i2) {
        super(context);
        this.f3584r = false;
        this.f3585s = -1;
        this.v = new SparseIntArray();
        this.w = new SparseIntArray();
        this.x = new DefaultSpanSizeLookup();
        this.y = new Rect();
        setSpanCount(i2);
    }

    public GridLayoutManager(Context context, int i2, int i3, boolean z) {
        super(context, i3, z);
        this.f3584r = false;
        this.f3585s = -1;
        this.v = new SparseIntArray();
        this.w = new SparseIntArray();
        this.x = new DefaultSpanSizeLookup();
        this.y = new Rect();
        setSpanCount(i2);
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.f3584r = false;
        this.f3585s = -1;
        this.v = new SparseIntArray();
        this.w = new SparseIntArray();
        this.x = new DefaultSpanSizeLookup();
        this.y = new Rect();
        setSpanCount(RecyclerView.LayoutManager.getProperties(context, attributeSet, i2, i3).spanCount);
    }

    static int[] H(int[] iArr, int i2, int i3) {
        int i4;
        if (iArr == null || iArr.length != i2 + 1 || iArr[iArr.length - 1] != i3) {
            iArr = new int[i2 + 1];
        }
        int i5 = 0;
        iArr[0] = 0;
        int i6 = i3 / i2;
        int i7 = i3 % i2;
        int i8 = 0;
        for (int i9 = 1; i9 <= i2; i9++) {
            i5 += i7;
            if (i5 <= 0 || i2 - i5 >= i7) {
                i4 = i6;
            } else {
                i4 = i6 + 1;
                i5 -= i2;
            }
            i8 += i4;
            iArr[i9] = i8;
        }
        return iArr;
    }

    private void assignSpans(RecyclerView.Recycler recycler, RecyclerView.State state, int i2, boolean z) {
        int i3;
        int i4;
        int i5 = 0;
        int i6 = -1;
        if (z) {
            i4 = 1;
            i6 = i2;
            i3 = 0;
        } else {
            i3 = i2 - 1;
            i4 = -1;
        }
        while (i3 != i6) {
            View view = this.u[i3];
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            int spanSize = getSpanSize(recycler, state, getPosition(view));
            layoutParams.f3588f = spanSize;
            layoutParams.f3587e = i5;
            i5 += spanSize;
            i3 += i4;
        }
    }

    private void cachePreLayoutSpanMapping() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            LayoutParams layoutParams = (LayoutParams) getChildAt(i2).getLayoutParams();
            int viewLayoutPosition = layoutParams.getViewLayoutPosition();
            this.v.put(viewLayoutPosition, layoutParams.getSpanSize());
            this.w.put(viewLayoutPosition, layoutParams.getSpanIndex());
        }
    }

    private void calculateItemBorders(int i2) {
        this.f3586t = H(this.f3586t, this.f3585s, i2);
    }

    private void clearPreLayoutSpanMappingCache() {
        this.v.clear();
        this.w.clear();
    }

    private int computeScrollOffsetWithSpanInfo(RecyclerView.State state) {
        if (getChildCount() != 0 && state.getItemCount() != 0) {
            u();
            boolean isSmoothScrollbarEnabled = isSmoothScrollbarEnabled();
            View x = x(!isSmoothScrollbarEnabled, true);
            View w = w(!isSmoothScrollbarEnabled, true);
            if (x != null && w != null) {
                int b2 = this.x.b(getPosition(x), this.f3585s);
                int b3 = this.x.b(getPosition(w), this.f3585s);
                int min = Math.min(b2, b3);
                int max = this.f3642m ? Math.max(0, ((this.x.b(state.getItemCount() - 1, this.f3585s) + 1) - Math.max(b2, b3)) - 1) : Math.max(0, min);
                if (isSmoothScrollbarEnabled) {
                    return Math.round((max * (Math.abs(this.f3641l.getDecoratedEnd(w) - this.f3641l.getDecoratedStart(x)) / ((this.x.b(getPosition(w), this.f3585s) - this.x.b(getPosition(x), this.f3585s)) + 1))) + (this.f3641l.getStartAfterPadding() - this.f3641l.getDecoratedStart(x)));
                }
                return max;
            }
        }
        return 0;
    }

    private int computeScrollRangeWithSpanInfo(RecyclerView.State state) {
        if (getChildCount() != 0 && state.getItemCount() != 0) {
            u();
            View x = x(!isSmoothScrollbarEnabled(), true);
            View w = w(!isSmoothScrollbarEnabled(), true);
            if (x != null && w != null) {
                if (isSmoothScrollbarEnabled()) {
                    int decoratedEnd = this.f3641l.getDecoratedEnd(w) - this.f3641l.getDecoratedStart(x);
                    int b2 = this.x.b(getPosition(x), this.f3585s);
                    return (int) ((decoratedEnd / ((this.x.b(getPosition(w), this.f3585s) - b2) + 1)) * (this.x.b(state.getItemCount() - 1, this.f3585s) + 1));
                }
                return this.x.b(state.getItemCount() - 1, this.f3585s) + 1;
            }
        }
        return 0;
    }

    private void ensureAnchorIsInCorrectSpan(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.AnchorInfo anchorInfo, int i2) {
        boolean z = i2 == 1;
        int spanIndex = getSpanIndex(recycler, state, anchorInfo.f3648b);
        if (z) {
            while (spanIndex > 0) {
                int i3 = anchorInfo.f3648b;
                if (i3 <= 0) {
                    return;
                }
                int i4 = i3 - 1;
                anchorInfo.f3648b = i4;
                spanIndex = getSpanIndex(recycler, state, i4);
            }
            return;
        }
        int itemCount = state.getItemCount() - 1;
        int i5 = anchorInfo.f3648b;
        while (i5 < itemCount) {
            int i6 = i5 + 1;
            int spanIndex2 = getSpanIndex(recycler, state, i6);
            if (spanIndex2 <= spanIndex) {
                break;
            }
            i5 = i6;
            spanIndex = spanIndex2;
        }
        anchorInfo.f3648b = i5;
    }

    private void ensureViewSet() {
        View[] viewArr = this.u;
        if (viewArr == null || viewArr.length != this.f3585s) {
            this.u = new View[this.f3585s];
        }
    }

    private int getSpanGroupIndex(RecyclerView.Recycler recycler, RecyclerView.State state, int i2) {
        if (state.isPreLayout()) {
            int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i2);
            if (convertPreLayoutPositionToPostLayout == -1) {
                StringBuilder sb = new StringBuilder();
                sb.append("Cannot find span size for pre layout position. ");
                sb.append(i2);
                return 0;
            }
            return this.x.b(convertPreLayoutPositionToPostLayout, this.f3585s);
        }
        return this.x.b(i2, this.f3585s);
    }

    private int getSpanIndex(RecyclerView.Recycler recycler, RecyclerView.State state, int i2) {
        if (state.isPreLayout()) {
            int i3 = this.w.get(i2, -1);
            if (i3 != -1) {
                return i3;
            }
            int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i2);
            if (convertPreLayoutPositionToPostLayout == -1) {
                StringBuilder sb = new StringBuilder();
                sb.append("Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:");
                sb.append(i2);
                return 0;
            }
            return this.x.c(convertPreLayoutPositionToPostLayout, this.f3585s);
        }
        return this.x.c(i2, this.f3585s);
    }

    private int getSpanSize(RecyclerView.Recycler recycler, RecyclerView.State state, int i2) {
        if (state.isPreLayout()) {
            int i3 = this.v.get(i2, -1);
            if (i3 != -1) {
                return i3;
            }
            int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i2);
            if (convertPreLayoutPositionToPostLayout == -1) {
                StringBuilder sb = new StringBuilder();
                sb.append("Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:");
                sb.append(i2);
                return 1;
            }
            return this.x.getSpanSize(convertPreLayoutPositionToPostLayout);
        }
        return this.x.getSpanSize(i2);
    }

    private void guessMeasurement(float f2, int i2) {
        calculateItemBorders(Math.max(Math.round(f2 * this.f3585s), i2));
    }

    private void measureChild(View view, int i2, boolean z) {
        int i3;
        int i4;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect = layoutParams.f3729b;
        int i5 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        int i6 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        int I = I(layoutParams.f3587e, layoutParams.f3588f);
        if (this.f3640k == 1) {
            i4 = RecyclerView.LayoutManager.getChildMeasureSpec(I, i2, i6, ((ViewGroup.MarginLayoutParams) layoutParams).width, false);
            i3 = RecyclerView.LayoutManager.getChildMeasureSpec(this.f3641l.getTotalSpace(), getHeightMode(), i5, ((ViewGroup.MarginLayoutParams) layoutParams).height, true);
        } else {
            int childMeasureSpec = RecyclerView.LayoutManager.getChildMeasureSpec(I, i2, i5, ((ViewGroup.MarginLayoutParams) layoutParams).height, false);
            int childMeasureSpec2 = RecyclerView.LayoutManager.getChildMeasureSpec(this.f3641l.getTotalSpace(), getWidthMode(), i6, ((ViewGroup.MarginLayoutParams) layoutParams).width, true);
            i3 = childMeasureSpec;
            i4 = childMeasureSpec2;
        }
        measureChildWithDecorationsAndMargin(view, i4, i3, z);
    }

    private void measureChildWithDecorationsAndMargin(View view, int i2, int i3, boolean z) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (z ? p(view, i2, i3, layoutParams) : n(view, i2, i3, layoutParams)) {
            view.measure(i2, i3);
        }
    }

    private void updateMeasurements() {
        int height;
        int paddingTop;
        if (getOrientation() == 1) {
            height = getWidth() - getPaddingRight();
            paddingTop = getPaddingLeft();
        } else {
            height = getHeight() - getPaddingBottom();
            paddingTop = getPaddingTop();
        }
        calculateItemBorders(height - paddingTop);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    View A(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z, boolean z2) {
        int i2;
        int childCount = getChildCount();
        int i3 = -1;
        int i4 = 1;
        if (z2) {
            i2 = getChildCount() - 1;
            i4 = -1;
        } else {
            i3 = childCount;
            i2 = 0;
        }
        int itemCount = state.getItemCount();
        u();
        int startAfterPadding = this.f3641l.getStartAfterPadding();
        int endAfterPadding = this.f3641l.getEndAfterPadding();
        View view = null;
        View view2 = null;
        while (i2 != i3) {
            View childAt = getChildAt(i2);
            int position = getPosition(childAt);
            if (position >= 0 && position < itemCount && getSpanIndex(recycler, state, position) == 0) {
                if (((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else if (this.f3641l.getDecoratedStart(childAt) < endAfterPadding && this.f3641l.getDecoratedEnd(childAt) >= startAfterPadding) {
                    return childAt;
                } else {
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i2 += i4;
        }
        return view != null ? view : view2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x009f, code lost:
        r21.mFinished = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a1, code lost:
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r5v19 */
    @Override // androidx.recyclerview.widget.LinearLayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void D(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.LayoutState layoutState, LinearLayoutManager.LayoutChunkResult layoutChunkResult) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int decoratedMeasurementInOther;
        int childMeasureSpec;
        int i9;
        View b2;
        int modeInOther = this.f3641l.getModeInOther();
        ?? r5 = 0;
        boolean z = modeInOther != 1073741824;
        int i10 = getChildCount() > 0 ? this.f3586t[this.f3585s] : 0;
        if (z) {
            updateMeasurements();
        }
        boolean z2 = layoutState.f3656e == 1;
        int i11 = this.f3585s;
        if (!z2) {
            i11 = getSpanIndex(recycler, state, layoutState.f3655d) + getSpanSize(recycler, state, layoutState.f3655d);
        }
        int i12 = 0;
        while (i12 < this.f3585s && layoutState.a(state) && i11 > 0) {
            int i13 = layoutState.f3655d;
            int spanSize = getSpanSize(recycler, state, i13);
            if (spanSize > this.f3585s) {
                throw new IllegalArgumentException("Item at position " + i13 + " requires " + spanSize + " spans but GridLayoutManager has only " + this.f3585s + " spans.");
            }
            i11 -= spanSize;
            if (i11 < 0 || (b2 = layoutState.b(recycler)) == null) {
                break;
            }
            this.u[i12] = b2;
            i12++;
        }
        float f2 = 0.0f;
        assignSpans(recycler, state, i12, z2);
        int i14 = 0;
        int i15 = 0;
        while (i14 < i12) {
            View view = this.u[i14];
            if (layoutState.f3663l == null) {
                if (z2) {
                    addView(view);
                } else {
                    addView(view, r5);
                }
            } else if (z2) {
                addDisappearingView(view);
            } else {
                addDisappearingView(view, r5);
            }
            calculateItemDecorationsForChild(view, this.y);
            measureChild(view, modeInOther, (boolean) r5);
            int decoratedMeasurement = this.f3641l.getDecoratedMeasurement(view);
            if (decoratedMeasurement > i15) {
                i15 = decoratedMeasurement;
            }
            float decoratedMeasurementInOther2 = (this.f3641l.getDecoratedMeasurementInOther(view) * 1.0f) / ((LayoutParams) view.getLayoutParams()).f3588f;
            if (decoratedMeasurementInOther2 > f2) {
                f2 = decoratedMeasurementInOther2;
            }
            i14++;
            r5 = 0;
        }
        if (z) {
            guessMeasurement(f2, i10);
            i15 = 0;
            for (int i16 = 0; i16 < i12; i16++) {
                View view2 = this.u[i16];
                measureChild(view2, 1073741824, true);
                int decoratedMeasurement2 = this.f3641l.getDecoratedMeasurement(view2);
                if (decoratedMeasurement2 > i15) {
                    i15 = decoratedMeasurement2;
                }
            }
        }
        for (int i17 = 0; i17 < i12; i17++) {
            View view3 = this.u[i17];
            if (this.f3641l.getDecoratedMeasurement(view3) != i15) {
                LayoutParams layoutParams = (LayoutParams) view3.getLayoutParams();
                Rect rect = layoutParams.f3729b;
                int i18 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                int i19 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                int I = I(layoutParams.f3587e, layoutParams.f3588f);
                if (this.f3640k == 1) {
                    i9 = RecyclerView.LayoutManager.getChildMeasureSpec(I, 1073741824, i19, ((ViewGroup.MarginLayoutParams) layoutParams).width, false);
                    childMeasureSpec = View.MeasureSpec.makeMeasureSpec(i15 - i18, 1073741824);
                } else {
                    int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i15 - i19, 1073741824);
                    childMeasureSpec = RecyclerView.LayoutManager.getChildMeasureSpec(I, 1073741824, i18, ((ViewGroup.MarginLayoutParams) layoutParams).height, false);
                    i9 = makeMeasureSpec;
                }
                measureChildWithDecorationsAndMargin(view3, i9, childMeasureSpec, true);
            }
        }
        int i20 = 0;
        layoutChunkResult.mConsumed = i15;
        if (this.f3640k == 1) {
            if (layoutState.f3657f == -1) {
                i4 = layoutState.f3653b;
                i5 = i4 - i15;
            } else {
                int i21 = layoutState.f3653b;
                i5 = i21;
                i4 = i15 + i21;
            }
            i2 = 0;
            i3 = 0;
        } else if (layoutState.f3657f == -1) {
            int i22 = layoutState.f3653b;
            i3 = i22 - i15;
            i5 = 0;
            i2 = i22;
            i4 = 0;
        } else {
            int i23 = layoutState.f3653b;
            i2 = i15 + i23;
            i3 = i23;
            i4 = 0;
            i5 = 0;
        }
        while (i20 < i12) {
            View view4 = this.u[i20];
            LayoutParams layoutParams2 = (LayoutParams) view4.getLayoutParams();
            if (this.f3640k == 1) {
                if (C()) {
                    int paddingLeft = getPaddingLeft() + this.f3586t[this.f3585s - layoutParams2.f3587e];
                    decoratedMeasurementInOther = i4;
                    i7 = paddingLeft;
                    i8 = paddingLeft - this.f3641l.getDecoratedMeasurementInOther(view4);
                } else {
                    int paddingLeft2 = getPaddingLeft() + this.f3586t[layoutParams2.f3587e];
                    decoratedMeasurementInOther = i4;
                    i8 = paddingLeft2;
                    i7 = this.f3641l.getDecoratedMeasurementInOther(view4) + paddingLeft2;
                }
                i6 = i5;
            } else {
                int paddingTop = getPaddingTop() + this.f3586t[layoutParams2.f3587e];
                i6 = paddingTop;
                i7 = i2;
                i8 = i3;
                decoratedMeasurementInOther = this.f3641l.getDecoratedMeasurementInOther(view4) + paddingTop;
            }
            layoutDecoratedWithMargins(view4, i8, i6, i7, decoratedMeasurementInOther);
            if (layoutParams2.isItemRemoved() || layoutParams2.isItemChanged()) {
                layoutChunkResult.mIgnoreConsumed = true;
            }
            layoutChunkResult.mFocusable |= view4.hasFocusable();
            i20++;
            i4 = decoratedMeasurementInOther;
            i2 = i7;
            i3 = i8;
            i5 = i6;
        }
        Arrays.fill(this.u, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void E(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.AnchorInfo anchorInfo, int i2) {
        super.E(recycler, state, anchorInfo, i2);
        updateMeasurements();
        if (state.getItemCount() > 0 && !state.isPreLayout()) {
            ensureAnchorIsInCorrectSpan(recycler, state, anchorInfo, i2);
        }
        ensureViewSet();
    }

    int I(int i2, int i3) {
        if (this.f3640k != 1 || !C()) {
            int[] iArr = this.f3586t;
            return iArr[i3 + i2] - iArr[i2];
        }
        int[] iArr2 = this.f3586t;
        int i4 = this.f3585s;
        return iArr2[i4 - i2] - iArr2[(i4 - i2) - i3];
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return this.mUsingSpansToEstimateScrollBarDimensions ? computeScrollOffsetWithSpanInfo(state) : super.computeHorizontalScrollOffset(state);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return this.mUsingSpansToEstimateScrollBarDimensions ? computeScrollRangeWithSpanInfo(state) : super.computeHorizontalScrollRange(state);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return this.mUsingSpansToEstimateScrollBarDimensions ? computeScrollOffsetWithSpanInfo(state) : super.computeVerticalScrollOffset(state);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return this.mUsingSpansToEstimateScrollBarDimensions ? computeScrollRangeWithSpanInfo(state) : super.computeVerticalScrollRange(state);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return this.f3640k == 0 ? new LayoutParams(-2, -1) : new LayoutParams(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getColumnCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.f3640k == 1) {
            return this.f3585s;
        }
        if (state.getItemCount() < 1) {
            return 0;
        }
        return getSpanGroupIndex(recycler, state, state.getItemCount() - 1) + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.f3640k == 0) {
            return this.f3585s;
        }
        if (state.getItemCount() < 1) {
            return 0;
        }
        return getSpanGroupIndex(recycler, state, state.getItemCount() - 1) + 1;
    }

    public int getSpanCount() {
        return this.f3585s;
    }

    public SpanSizeLookup getSpanSizeLookup() {
        return this.x;
    }

    public boolean isUsingSpansToEstimateScrollbarDimensions() {
        return this.mUsingSpansToEstimateScrollBarDimensions;
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x00d6, code lost:
        if (r13 == (r2 > r15)) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00f6, code lost:
        if (r13 == (r2 > r7)) goto L51;
     */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0107  */
    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public View onFocusSearchFailed(View view, int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int childCount;
        int i3;
        int i4;
        View view2;
        View view3;
        int i5;
        int i6;
        boolean z;
        int i7;
        int i8;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        View findContainingItemView = findContainingItemView(view);
        View view4 = null;
        if (findContainingItemView == null) {
            return null;
        }
        LayoutParams layoutParams = (LayoutParams) findContainingItemView.getLayoutParams();
        int i9 = layoutParams.f3587e;
        int i10 = layoutParams.f3588f + i9;
        if (super.onFocusSearchFailed(view, i2, recycler, state) == null) {
            return null;
        }
        if ((convertFocusDirectionToLayoutDirection(i2) == 1) != this.f3642m) {
            i4 = getChildCount() - 1;
            childCount = -1;
            i3 = -1;
        } else {
            childCount = getChildCount();
            i3 = 1;
            i4 = 0;
        }
        boolean z2 = this.f3640k == 1 && C();
        int spanGroupIndex = getSpanGroupIndex(recycler2, state2, i4);
        int i11 = -1;
        int i12 = -1;
        int i13 = 0;
        int i14 = 0;
        int i15 = i4;
        View view5 = null;
        while (i15 != childCount) {
            int spanGroupIndex2 = getSpanGroupIndex(recycler2, state2, i15);
            View childAt = getChildAt(i15);
            if (childAt == findContainingItemView) {
                break;
            }
            if (!childAt.hasFocusable() || spanGroupIndex2 == spanGroupIndex) {
                LayoutParams layoutParams2 = (LayoutParams) childAt.getLayoutParams();
                int i16 = layoutParams2.f3587e;
                view2 = findContainingItemView;
                int i17 = layoutParams2.f3588f + i16;
                if (childAt.hasFocusable() && i16 == i9 && i17 == i10) {
                    return childAt;
                }
                if (!(childAt.hasFocusable() && view4 == null) && (childAt.hasFocusable() || view5 != null)) {
                    view3 = view5;
                    int min = Math.min(i17, i10) - Math.max(i16, i9);
                    if (childAt.hasFocusable()) {
                        if (min <= i13) {
                            if (min == i13) {
                            }
                        }
                    } else if (view4 == null) {
                        i5 = i13;
                        i6 = childCount;
                        if (isViewPartiallyVisible(childAt, false, true)) {
                            i7 = i14;
                            if (min > i7) {
                                i8 = i12;
                                if (z) {
                                    if (childAt.hasFocusable()) {
                                        i11 = layoutParams2.f3587e;
                                        i12 = i8;
                                        i14 = i7;
                                        view5 = view3;
                                        view4 = childAt;
                                        i13 = Math.min(i17, i10) - Math.max(i16, i9);
                                    } else {
                                        int i18 = layoutParams2.f3587e;
                                        i14 = Math.min(i17, i10) - Math.max(i16, i9);
                                        i12 = i18;
                                        i13 = i5;
                                        view5 = childAt;
                                    }
                                    i15 += i3;
                                    recycler2 = recycler;
                                    state2 = state;
                                    findContainingItemView = view2;
                                    childCount = i6;
                                }
                            } else {
                                if (min == i7) {
                                    i8 = i12;
                                } else {
                                    i8 = i12;
                                }
                                z = false;
                                if (z) {
                                }
                            }
                        }
                        i8 = i12;
                        i7 = i14;
                        z = false;
                        if (z) {
                        }
                    }
                    i5 = i13;
                    i6 = childCount;
                    i8 = i12;
                    i7 = i14;
                    z = false;
                    if (z) {
                    }
                } else {
                    view3 = view5;
                }
                i5 = i13;
                i6 = childCount;
                i8 = i12;
                i7 = i14;
                z = true;
                if (z) {
                }
            } else if (view4 != null) {
                break;
            } else {
                view2 = findContainingItemView;
                view3 = view5;
                i5 = i13;
                i6 = childCount;
                i8 = i12;
                i7 = i14;
            }
            i12 = i8;
            i14 = i7;
            i13 = i5;
            view5 = view3;
            i15 += i3;
            recycler2 = recycler;
            state2 = state;
            findContainingItemView = view2;
            childCount = i6;
        }
        return view4 != null ? view4 : view5;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        int i2;
        int spanIndex;
        int spanSize;
        boolean z;
        boolean z2;
        int i3;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.e(view, accessibilityNodeInfoCompat);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        int spanGroupIndex = getSpanGroupIndex(recycler, state, layoutParams2.getViewLayoutPosition());
        if (this.f3640k == 0) {
            i3 = layoutParams2.getSpanIndex();
            i2 = layoutParams2.getSpanSize();
            spanSize = 1;
            z = false;
            z2 = false;
            spanIndex = spanGroupIndex;
        } else {
            i2 = 1;
            spanIndex = layoutParams2.getSpanIndex();
            spanSize = layoutParams2.getSpanSize();
            z = false;
            z2 = false;
            i3 = spanGroupIndex;
        }
        accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(i3, i2, spanIndex, spanSize, z, z2));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView recyclerView, int i2, int i3) {
        this.x.invalidateSpanIndexCache();
        this.x.invalidateSpanGroupIndexCache();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsChanged(RecyclerView recyclerView) {
        this.x.invalidateSpanIndexCache();
        this.x.invalidateSpanGroupIndexCache();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsMoved(RecyclerView recyclerView, int i2, int i3, int i4) {
        this.x.invalidateSpanIndexCache();
        this.x.invalidateSpanGroupIndexCache();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView, int i2, int i3) {
        this.x.invalidateSpanIndexCache();
        this.x.invalidateSpanGroupIndexCache();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView, int i2, int i3, Object obj) {
        this.x.invalidateSpanIndexCache();
        this.x.invalidateSpanGroupIndexCache();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.isPreLayout()) {
            cachePreLayoutSpanMapping();
        }
        super.onLayoutChildren(recycler, state);
        clearPreLayoutSpanMappingCache();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.f3584r = false;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    void s(RecyclerView.State state, LinearLayoutManager.LayoutState layoutState, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int i2 = this.f3585s;
        for (int i3 = 0; i3 < this.f3585s && layoutState.a(state) && i2 > 0; i3++) {
            int i4 = layoutState.f3655d;
            layoutPrefetchRegistry.addPosition(i4, Math.max(0, layoutState.f3658g));
            i2 -= this.x.getSpanSize(i4);
            layoutState.f3655d += layoutState.f3656e;
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        updateMeasurements();
        ensureViewSet();
        return super.scrollHorizontallyBy(i2, recycler, state);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        updateMeasurements();
        ensureViewSet();
        return super.scrollVerticallyBy(i2, recycler, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void setMeasuredDimension(Rect rect, int i2, int i3) {
        int chooseSize;
        int chooseSize2;
        if (this.f3586t == null) {
            super.setMeasuredDimension(rect, i2, i3);
        }
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (this.f3640k == 1) {
            chooseSize2 = RecyclerView.LayoutManager.chooseSize(i3, rect.height() + paddingTop, getMinimumHeight());
            int[] iArr = this.f3586t;
            chooseSize = RecyclerView.LayoutManager.chooseSize(i2, iArr[iArr.length - 1] + paddingLeft, getMinimumWidth());
        } else {
            chooseSize = RecyclerView.LayoutManager.chooseSize(i2, rect.width() + paddingLeft, getMinimumWidth());
            int[] iArr2 = this.f3586t;
            chooseSize2 = RecyclerView.LayoutManager.chooseSize(i3, iArr2[iArr2.length - 1] + paddingTop, getMinimumHeight());
        }
        setMeasuredDimension(chooseSize, chooseSize2);
    }

    public void setSpanCount(int i2) {
        if (i2 == this.f3585s) {
            return;
        }
        this.f3584r = true;
        if (i2 >= 1) {
            this.f3585s = i2;
            this.x.invalidateSpanIndexCache();
            requestLayout();
            return;
        }
        throw new IllegalArgumentException("Span count should be at least 1. Provided " + i2);
    }

    public void setSpanSizeLookup(SpanSizeLookup spanSizeLookup) {
        this.x = spanSizeLookup;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void setStackFromEnd(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.setStackFromEnd(false);
    }

    public void setUsingSpansToEstimateScrollbarDimensions(boolean z) {
        this.mUsingSpansToEstimateScrollBarDimensions = z;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return this.f3645p == null && !this.f3584r;
    }
}
