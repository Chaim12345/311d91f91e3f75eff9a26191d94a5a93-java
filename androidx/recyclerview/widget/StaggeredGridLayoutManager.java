package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes.dex */
public class StaggeredGridLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {
    @Deprecated
    public static final int GAP_HANDLING_LAZY = 1;
    public static final int GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS = 2;
    public static final int GAP_HANDLING_NONE = 0;
    public static final int HORIZONTAL = 0;
    private static final float MAX_SCROLL_FACTOR = 0.33333334f;
    private static final String TAG = "StaggeredGridLManager";
    public static final int VERTICAL = 1;

    /* renamed from: k  reason: collision with root package name */
    Span[] f3775k;
    @NonNull

    /* renamed from: l  reason: collision with root package name */
    OrientationHelper f3776l;
    @NonNull

    /* renamed from: m  reason: collision with root package name */
    OrientationHelper f3777m;
    private int mFullSizeSpec;
    private boolean mLastLayoutFromEnd;
    private boolean mLastLayoutRTL;
    @NonNull
    private final LayoutState mLayoutState;
    private int mOrientation;
    private SavedState mPendingSavedState;
    private int[] mPrefetchDistances;
    private BitSet mRemainingSpans;
    private int mSizePerSpan;
    private int mSpanCount = -1;

    /* renamed from: n  reason: collision with root package name */
    boolean f3778n = false;

    /* renamed from: o  reason: collision with root package name */
    boolean f3779o = false;

    /* renamed from: p  reason: collision with root package name */
    int f3780p = -1;

    /* renamed from: q  reason: collision with root package name */
    int f3781q = Integer.MIN_VALUE;

    /* renamed from: r  reason: collision with root package name */
    LazySpanLookup f3782r = new LazySpanLookup();
    private int mGapStrategy = 2;
    private final Rect mTmpRect = new Rect();
    private final AnchorInfo mAnchorInfo = new AnchorInfo();
    private boolean mLaidOutInvalidFullSpan = false;
    private boolean mSmoothScrollbarEnabled = true;
    private final Runnable mCheckForGapsRunnable = new Runnable() { // from class: androidx.recyclerview.widget.StaggeredGridLayoutManager.1
        @Override // java.lang.Runnable
        public void run() {
            StaggeredGridLayoutManager.this.t();
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class AnchorInfo {

        /* renamed from: a  reason: collision with root package name */
        int f3784a;

        /* renamed from: b  reason: collision with root package name */
        int f3785b;

        /* renamed from: c  reason: collision with root package name */
        boolean f3786c;

        /* renamed from: d  reason: collision with root package name */
        boolean f3787d;

        /* renamed from: e  reason: collision with root package name */
        boolean f3788e;

        /* renamed from: f  reason: collision with root package name */
        int[] f3789f;

        AnchorInfo() {
            c();
        }

        void a() {
            this.f3785b = this.f3786c ? StaggeredGridLayoutManager.this.f3776l.getEndAfterPadding() : StaggeredGridLayoutManager.this.f3776l.getStartAfterPadding();
        }

        void b(int i2) {
            this.f3785b = this.f3786c ? StaggeredGridLayoutManager.this.f3776l.getEndAfterPadding() - i2 : StaggeredGridLayoutManager.this.f3776l.getStartAfterPadding() + i2;
        }

        void c() {
            this.f3784a = -1;
            this.f3785b = Integer.MIN_VALUE;
            this.f3786c = false;
            this.f3787d = false;
            this.f3788e = false;
            int[] iArr = this.f3789f;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }

        void d(Span[] spanArr) {
            int length = spanArr.length;
            int[] iArr = this.f3789f;
            if (iArr == null || iArr.length < length) {
                this.f3789f = new int[StaggeredGridLayoutManager.this.f3775k.length];
            }
            for (int i2 = 0; i2 < length; i2++) {
                this.f3789f[i2] = spanArr[i2].m(Integer.MIN_VALUE);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class LayoutParams extends RecyclerView.LayoutParams {
        public static final int INVALID_SPAN_ID = -1;

        /* renamed from: e  reason: collision with root package name */
        Span f3791e;

        /* renamed from: f  reason: collision with root package name */
        boolean f3792f;

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public final int getSpanIndex() {
            Span span = this.f3791e;
            if (span == null) {
                return -1;
            }
            return span.f3813e;
        }

        public boolean isFullSpan() {
            return this.f3792f;
        }

        public void setFullSpan(boolean z) {
            this.f3792f = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class LazySpanLookup {
        private static final int MIN_SIZE = 10;

        /* renamed from: a  reason: collision with root package name */
        int[] f3793a;

        /* renamed from: b  reason: collision with root package name */
        List f3794b;

        /* JADX INFO: Access modifiers changed from: package-private */
        @SuppressLint({"BanParcelableUsage"})
        /* loaded from: classes.dex */
        public static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR = new Parcelable.Creator<FullSpanItem>() { // from class: androidx.recyclerview.widget.StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public FullSpanItem createFromParcel(Parcel parcel) {
                    return new FullSpanItem(parcel);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public FullSpanItem[] newArray(int i2) {
                    return new FullSpanItem[i2];
                }
            };

            /* renamed from: a  reason: collision with root package name */
            int f3795a;

            /* renamed from: b  reason: collision with root package name */
            int f3796b;

            /* renamed from: c  reason: collision with root package name */
            int[] f3797c;

            /* renamed from: d  reason: collision with root package name */
            boolean f3798d;

            FullSpanItem() {
            }

            FullSpanItem(Parcel parcel) {
                this.f3795a = parcel.readInt();
                this.f3796b = parcel.readInt();
                this.f3798d = parcel.readInt() == 1;
                int readInt = parcel.readInt();
                if (readInt > 0) {
                    int[] iArr = new int[readInt];
                    this.f3797c = iArr;
                    parcel.readIntArray(iArr);
                }
            }

            int a(int i2) {
                int[] iArr = this.f3797c;
                if (iArr == null) {
                    return 0;
                }
                return iArr[i2];
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.f3795a + ", mGapDir=" + this.f3796b + ", mHasUnwantedGapAfter=" + this.f3798d + ", mGapPerSpan=" + Arrays.toString(this.f3797c) + AbstractJsonLexerKt.END_OBJ;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i2) {
                parcel.writeInt(this.f3795a);
                parcel.writeInt(this.f3796b);
                parcel.writeInt(this.f3798d ? 1 : 0);
                int[] iArr = this.f3797c;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                    return;
                }
                parcel.writeInt(iArr.length);
                parcel.writeIntArray(this.f3797c);
            }
        }

        LazySpanLookup() {
        }

        private int invalidateFullSpansAfter(int i2) {
            if (this.f3794b == null) {
                return -1;
            }
            FullSpanItem fullSpanItem = getFullSpanItem(i2);
            if (fullSpanItem != null) {
                this.f3794b.remove(fullSpanItem);
            }
            int size = this.f3794b.size();
            int i3 = 0;
            while (true) {
                if (i3 >= size) {
                    i3 = -1;
                    break;
                } else if (((FullSpanItem) this.f3794b.get(i3)).f3795a >= i2) {
                    break;
                } else {
                    i3++;
                }
            }
            if (i3 != -1) {
                this.f3794b.remove(i3);
                return ((FullSpanItem) this.f3794b.get(i3)).f3795a;
            }
            return -1;
        }

        private void offsetFullSpansForAddition(int i2, int i3) {
            List list = this.f3794b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = (FullSpanItem) this.f3794b.get(size);
                int i4 = fullSpanItem.f3795a;
                if (i4 >= i2) {
                    fullSpanItem.f3795a = i4 + i3;
                }
            }
        }

        private void offsetFullSpansForRemoval(int i2, int i3) {
            List list = this.f3794b;
            if (list == null) {
                return;
            }
            int i4 = i2 + i3;
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = (FullSpanItem) this.f3794b.get(size);
                int i5 = fullSpanItem.f3795a;
                if (i5 >= i2) {
                    if (i5 < i4) {
                        this.f3794b.remove(size);
                    } else {
                        fullSpanItem.f3795a = i5 - i3;
                    }
                }
            }
        }

        void a() {
            int[] iArr = this.f3793a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f3794b = null;
        }

        public void addFullSpanItem(FullSpanItem fullSpanItem) {
            if (this.f3794b == null) {
                this.f3794b = new ArrayList();
            }
            int size = this.f3794b.size();
            for (int i2 = 0; i2 < size; i2++) {
                FullSpanItem fullSpanItem2 = (FullSpanItem) this.f3794b.get(i2);
                if (fullSpanItem2.f3795a == fullSpanItem.f3795a) {
                    this.f3794b.remove(i2);
                }
                if (fullSpanItem2.f3795a >= fullSpanItem.f3795a) {
                    this.f3794b.add(i2, fullSpanItem);
                    return;
                }
            }
            this.f3794b.add(fullSpanItem);
        }

        void b(int i2) {
            int[] iArr = this.f3793a;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i2, 10) + 1];
                this.f3793a = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i2 >= iArr.length) {
                int[] iArr3 = new int[i(i2)];
                this.f3793a = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.f3793a;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        int c(int i2) {
            List list = this.f3794b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (((FullSpanItem) this.f3794b.get(size)).f3795a >= i2) {
                        this.f3794b.remove(size);
                    }
                }
            }
            return e(i2);
        }

        int d(int i2) {
            int[] iArr = this.f3793a;
            if (iArr == null || i2 >= iArr.length) {
                return -1;
            }
            return iArr[i2];
        }

        int e(int i2) {
            int[] iArr = this.f3793a;
            if (iArr != null && i2 < iArr.length) {
                int invalidateFullSpansAfter = invalidateFullSpansAfter(i2);
                if (invalidateFullSpansAfter == -1) {
                    int[] iArr2 = this.f3793a;
                    Arrays.fill(iArr2, i2, iArr2.length, -1);
                    return this.f3793a.length;
                }
                int min = Math.min(invalidateFullSpansAfter + 1, this.f3793a.length);
                Arrays.fill(this.f3793a, i2, min, -1);
                return min;
            }
            return -1;
        }

        void f(int i2, int i3) {
            int[] iArr = this.f3793a;
            if (iArr == null || i2 >= iArr.length) {
                return;
            }
            int i4 = i2 + i3;
            b(i4);
            int[] iArr2 = this.f3793a;
            System.arraycopy(iArr2, i2, iArr2, i4, (iArr2.length - i2) - i3);
            Arrays.fill(this.f3793a, i2, i4, -1);
            offsetFullSpansForAddition(i2, i3);
        }

        void g(int i2, int i3) {
            int[] iArr = this.f3793a;
            if (iArr == null || i2 >= iArr.length) {
                return;
            }
            int i4 = i2 + i3;
            b(i4);
            int[] iArr2 = this.f3793a;
            System.arraycopy(iArr2, i4, iArr2, i2, (iArr2.length - i2) - i3);
            int[] iArr3 = this.f3793a;
            Arrays.fill(iArr3, iArr3.length - i3, iArr3.length, -1);
            offsetFullSpansForRemoval(i2, i3);
        }

        public FullSpanItem getFirstFullSpanItemInRange(int i2, int i3, int i4, boolean z) {
            List list = this.f3794b;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i5 = 0; i5 < size; i5++) {
                FullSpanItem fullSpanItem = (FullSpanItem) this.f3794b.get(i5);
                int i6 = fullSpanItem.f3795a;
                if (i6 >= i3) {
                    return null;
                }
                if (i6 >= i2 && (i4 == 0 || fullSpanItem.f3796b == i4 || (z && fullSpanItem.f3798d))) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public FullSpanItem getFullSpanItem(int i2) {
            List list = this.f3794b;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = (FullSpanItem) this.f3794b.get(size);
                if (fullSpanItem.f3795a == i2) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        void h(int i2, Span span) {
            b(i2);
            this.f3793a[i2] = span.f3813e;
        }

        int i(int i2) {
            int length = this.f3793a.length;
            while (length <= i2) {
                length *= 2;
            }
            return length;
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: androidx.recyclerview.widget.StaggeredGridLayoutManager.SavedState.1
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
        int f3799a;

        /* renamed from: b  reason: collision with root package name */
        int f3800b;

        /* renamed from: c  reason: collision with root package name */
        int f3801c;

        /* renamed from: d  reason: collision with root package name */
        int[] f3802d;

        /* renamed from: e  reason: collision with root package name */
        int f3803e;

        /* renamed from: f  reason: collision with root package name */
        int[] f3804f;

        /* renamed from: g  reason: collision with root package name */
        List f3805g;

        /* renamed from: h  reason: collision with root package name */
        boolean f3806h;

        /* renamed from: i  reason: collision with root package name */
        boolean f3807i;

        /* renamed from: j  reason: collision with root package name */
        boolean f3808j;

        public SavedState() {
        }

        SavedState(Parcel parcel) {
            this.f3799a = parcel.readInt();
            this.f3800b = parcel.readInt();
            int readInt = parcel.readInt();
            this.f3801c = readInt;
            if (readInt > 0) {
                int[] iArr = new int[readInt];
                this.f3802d = iArr;
                parcel.readIntArray(iArr);
            }
            int readInt2 = parcel.readInt();
            this.f3803e = readInt2;
            if (readInt2 > 0) {
                int[] iArr2 = new int[readInt2];
                this.f3804f = iArr2;
                parcel.readIntArray(iArr2);
            }
            this.f3806h = parcel.readInt() == 1;
            this.f3807i = parcel.readInt() == 1;
            this.f3808j = parcel.readInt() == 1;
            this.f3805g = parcel.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.f3801c = savedState.f3801c;
            this.f3799a = savedState.f3799a;
            this.f3800b = savedState.f3800b;
            this.f3802d = savedState.f3802d;
            this.f3803e = savedState.f3803e;
            this.f3804f = savedState.f3804f;
            this.f3806h = savedState.f3806h;
            this.f3807i = savedState.f3807i;
            this.f3808j = savedState.f3808j;
            this.f3805g = savedState.f3805g;
        }

        void a() {
            this.f3802d = null;
            this.f3801c = 0;
            this.f3799a = -1;
            this.f3800b = -1;
        }

        void b() {
            this.f3802d = null;
            this.f3801c = 0;
            this.f3803e = 0;
            this.f3804f = null;
            this.f3805g = null;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f3799a);
            parcel.writeInt(this.f3800b);
            parcel.writeInt(this.f3801c);
            if (this.f3801c > 0) {
                parcel.writeIntArray(this.f3802d);
            }
            parcel.writeInt(this.f3803e);
            if (this.f3803e > 0) {
                parcel.writeIntArray(this.f3804f);
            }
            parcel.writeInt(this.f3806h ? 1 : 0);
            parcel.writeInt(this.f3807i ? 1 : 0);
            parcel.writeInt(this.f3808j ? 1 : 0);
            parcel.writeList(this.f3805g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class Span {

        /* renamed from: a  reason: collision with root package name */
        ArrayList f3809a = new ArrayList();

        /* renamed from: b  reason: collision with root package name */
        int f3810b = Integer.MIN_VALUE;

        /* renamed from: c  reason: collision with root package name */
        int f3811c = Integer.MIN_VALUE;

        /* renamed from: d  reason: collision with root package name */
        int f3812d = 0;

        /* renamed from: e  reason: collision with root package name */
        final int f3813e;

        Span(int i2) {
            this.f3813e = i2;
        }

        void a(View view) {
            LayoutParams k2 = k(view);
            k2.f3791e = this;
            this.f3809a.add(view);
            this.f3811c = Integer.MIN_VALUE;
            if (this.f3809a.size() == 1) {
                this.f3810b = Integer.MIN_VALUE;
            }
            if (k2.isItemRemoved() || k2.isItemChanged()) {
                this.f3812d += StaggeredGridLayoutManager.this.f3776l.getDecoratedMeasurement(view);
            }
        }

        void b(boolean z, int i2) {
            int j2 = z ? j(Integer.MIN_VALUE) : m(Integer.MIN_VALUE);
            e();
            if (j2 == Integer.MIN_VALUE) {
                return;
            }
            if (!z || j2 >= StaggeredGridLayoutManager.this.f3776l.getEndAfterPadding()) {
                if (z || j2 <= StaggeredGridLayoutManager.this.f3776l.getStartAfterPadding()) {
                    if (i2 != Integer.MIN_VALUE) {
                        j2 += i2;
                    }
                    this.f3811c = j2;
                    this.f3810b = j2;
                }
            }
        }

        void c() {
            LazySpanLookup.FullSpanItem fullSpanItem;
            ArrayList arrayList = this.f3809a;
            View view = (View) arrayList.get(arrayList.size() - 1);
            LayoutParams k2 = k(view);
            this.f3811c = StaggeredGridLayoutManager.this.f3776l.getDecoratedEnd(view);
            if (k2.f3792f && (fullSpanItem = StaggeredGridLayoutManager.this.f3782r.getFullSpanItem(k2.getViewLayoutPosition())) != null && fullSpanItem.f3796b == 1) {
                this.f3811c += fullSpanItem.a(this.f3813e);
            }
        }

        void d() {
            LazySpanLookup.FullSpanItem fullSpanItem;
            View view = (View) this.f3809a.get(0);
            LayoutParams k2 = k(view);
            this.f3810b = StaggeredGridLayoutManager.this.f3776l.getDecoratedStart(view);
            if (k2.f3792f && (fullSpanItem = StaggeredGridLayoutManager.this.f3782r.getFullSpanItem(k2.getViewLayoutPosition())) != null && fullSpanItem.f3796b == -1) {
                this.f3810b -= fullSpanItem.a(this.f3813e);
            }
        }

        void e() {
            this.f3809a.clear();
            n();
            this.f3812d = 0;
        }

        int f(int i2, int i3, boolean z, boolean z2, boolean z3) {
            int startAfterPadding = StaggeredGridLayoutManager.this.f3776l.getStartAfterPadding();
            int endAfterPadding = StaggeredGridLayoutManager.this.f3776l.getEndAfterPadding();
            int i4 = i3 > i2 ? 1 : -1;
            while (i2 != i3) {
                View view = (View) this.f3809a.get(i2);
                int decoratedStart = StaggeredGridLayoutManager.this.f3776l.getDecoratedStart(view);
                int decoratedEnd = StaggeredGridLayoutManager.this.f3776l.getDecoratedEnd(view);
                boolean z4 = false;
                boolean z5 = !z3 ? decoratedStart >= endAfterPadding : decoratedStart > endAfterPadding;
                if (!z3 ? decoratedEnd > startAfterPadding : decoratedEnd >= startAfterPadding) {
                    z4 = true;
                }
                if (z5 && z4) {
                    if (!z || !z2) {
                        if (!z2 && decoratedStart >= startAfterPadding && decoratedEnd <= endAfterPadding) {
                        }
                        return StaggeredGridLayoutManager.this.getPosition(view);
                    } else if (decoratedStart >= startAfterPadding && decoratedEnd <= endAfterPadding) {
                        return StaggeredGridLayoutManager.this.getPosition(view);
                    }
                }
                i2 += i4;
            }
            return -1;
        }

        public int findFirstCompletelyVisibleItemPosition() {
            int i2;
            int size;
            if (StaggeredGridLayoutManager.this.f3778n) {
                i2 = this.f3809a.size() - 1;
                size = -1;
            } else {
                i2 = 0;
                size = this.f3809a.size();
            }
            return h(i2, size, true);
        }

        public int findFirstPartiallyVisibleItemPosition() {
            int i2;
            int size;
            if (StaggeredGridLayoutManager.this.f3778n) {
                i2 = this.f3809a.size() - 1;
                size = -1;
            } else {
                i2 = 0;
                size = this.f3809a.size();
            }
            return g(i2, size, true);
        }

        public int findFirstVisibleItemPosition() {
            return StaggeredGridLayoutManager.this.f3778n ? h(this.f3809a.size() - 1, -1, false) : h(0, this.f3809a.size(), false);
        }

        public int findLastCompletelyVisibleItemPosition() {
            int size;
            int i2;
            if (StaggeredGridLayoutManager.this.f3778n) {
                size = 0;
                i2 = this.f3809a.size();
            } else {
                size = this.f3809a.size() - 1;
                i2 = -1;
            }
            return h(size, i2, true);
        }

        public int findLastPartiallyVisibleItemPosition() {
            int size;
            int i2;
            if (StaggeredGridLayoutManager.this.f3778n) {
                size = 0;
                i2 = this.f3809a.size();
            } else {
                size = this.f3809a.size() - 1;
                i2 = -1;
            }
            return g(size, i2, true);
        }

        public int findLastVisibleItemPosition() {
            return StaggeredGridLayoutManager.this.f3778n ? h(0, this.f3809a.size(), false) : h(this.f3809a.size() - 1, -1, false);
        }

        int g(int i2, int i3, boolean z) {
            return f(i2, i3, false, false, z);
        }

        public int getDeletedSize() {
            return this.f3812d;
        }

        public View getFocusableViewAfter(int i2, int i3) {
            View view = null;
            if (i3 != -1) {
                int size = this.f3809a.size() - 1;
                while (size >= 0) {
                    View view2 = (View) this.f3809a.get(size);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager.f3778n && staggeredGridLayoutManager.getPosition(view2) >= i2) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager2 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager2.f3778n && staggeredGridLayoutManager2.getPosition(view2) <= i2) || !view2.hasFocusable()) {
                        break;
                    }
                    size--;
                    view = view2;
                }
            } else {
                int size2 = this.f3809a.size();
                int i4 = 0;
                while (i4 < size2) {
                    View view3 = (View) this.f3809a.get(i4);
                    StaggeredGridLayoutManager staggeredGridLayoutManager3 = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager3.f3778n && staggeredGridLayoutManager3.getPosition(view3) <= i2) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager4 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager4.f3778n && staggeredGridLayoutManager4.getPosition(view3) >= i2) || !view3.hasFocusable()) {
                        break;
                    }
                    i4++;
                    view = view3;
                }
            }
            return view;
        }

        int h(int i2, int i3, boolean z) {
            return f(i2, i3, z, true, false);
        }

        int i() {
            int i2 = this.f3811c;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            c();
            return this.f3811c;
        }

        int j(int i2) {
            int i3 = this.f3811c;
            if (i3 != Integer.MIN_VALUE) {
                return i3;
            }
            if (this.f3809a.size() == 0) {
                return i2;
            }
            c();
            return this.f3811c;
        }

        LayoutParams k(View view) {
            return (LayoutParams) view.getLayoutParams();
        }

        int l() {
            int i2 = this.f3810b;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            d();
            return this.f3810b;
        }

        int m(int i2) {
            int i3 = this.f3810b;
            if (i3 != Integer.MIN_VALUE) {
                return i3;
            }
            if (this.f3809a.size() == 0) {
                return i2;
            }
            d();
            return this.f3810b;
        }

        void n() {
            this.f3810b = Integer.MIN_VALUE;
            this.f3811c = Integer.MIN_VALUE;
        }

        void o(int i2) {
            int i3 = this.f3810b;
            if (i3 != Integer.MIN_VALUE) {
                this.f3810b = i3 + i2;
            }
            int i4 = this.f3811c;
            if (i4 != Integer.MIN_VALUE) {
                this.f3811c = i4 + i2;
            }
        }

        void p() {
            int size = this.f3809a.size();
            View view = (View) this.f3809a.remove(size - 1);
            LayoutParams k2 = k(view);
            k2.f3791e = null;
            if (k2.isItemRemoved() || k2.isItemChanged()) {
                this.f3812d -= StaggeredGridLayoutManager.this.f3776l.getDecoratedMeasurement(view);
            }
            if (size == 1) {
                this.f3810b = Integer.MIN_VALUE;
            }
            this.f3811c = Integer.MIN_VALUE;
        }

        void q() {
            View view = (View) this.f3809a.remove(0);
            LayoutParams k2 = k(view);
            k2.f3791e = null;
            if (this.f3809a.size() == 0) {
                this.f3811c = Integer.MIN_VALUE;
            }
            if (k2.isItemRemoved() || k2.isItemChanged()) {
                this.f3812d -= StaggeredGridLayoutManager.this.f3776l.getDecoratedMeasurement(view);
            }
            this.f3810b = Integer.MIN_VALUE;
        }

        void r(View view) {
            LayoutParams k2 = k(view);
            k2.f3791e = this;
            this.f3809a.add(0, view);
            this.f3810b = Integer.MIN_VALUE;
            if (this.f3809a.size() == 1) {
                this.f3811c = Integer.MIN_VALUE;
            }
            if (k2.isItemRemoved() || k2.isItemChanged()) {
                this.f3812d += StaggeredGridLayoutManager.this.f3776l.getDecoratedMeasurement(view);
            }
        }

        void s(int i2) {
            this.f3810b = i2;
            this.f3811c = i2;
        }
    }

    public StaggeredGridLayoutManager(int i2, int i3) {
        this.mOrientation = i3;
        setSpanCount(i2);
        this.mLayoutState = new LayoutState();
        createOrientationHelpers();
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        RecyclerView.LayoutManager.Properties properties = RecyclerView.LayoutManager.getProperties(context, attributeSet, i2, i3);
        setOrientation(properties.orientation);
        setSpanCount(properties.spanCount);
        setReverseLayout(properties.reverseLayout);
        this.mLayoutState = new LayoutState();
        createOrientationHelpers();
    }

    private void appendViewToAllSpans(View view) {
        for (int i2 = this.mSpanCount - 1; i2 >= 0; i2--) {
            this.f3775k[i2].a(view);
        }
    }

    private void applyPendingSavedState(AnchorInfo anchorInfo) {
        boolean z;
        SavedState savedState = this.mPendingSavedState;
        int i2 = savedState.f3801c;
        if (i2 > 0) {
            if (i2 == this.mSpanCount) {
                for (int i3 = 0; i3 < this.mSpanCount; i3++) {
                    this.f3775k[i3].e();
                    SavedState savedState2 = this.mPendingSavedState;
                    int i4 = savedState2.f3802d[i3];
                    if (i4 != Integer.MIN_VALUE) {
                        i4 += savedState2.f3807i ? this.f3776l.getEndAfterPadding() : this.f3776l.getStartAfterPadding();
                    }
                    this.f3775k[i3].s(i4);
                }
            } else {
                savedState.b();
                SavedState savedState3 = this.mPendingSavedState;
                savedState3.f3799a = savedState3.f3800b;
            }
        }
        SavedState savedState4 = this.mPendingSavedState;
        this.mLastLayoutRTL = savedState4.f3808j;
        setReverseLayout(savedState4.f3806h);
        resolveShouldLayoutReverse();
        SavedState savedState5 = this.mPendingSavedState;
        int i5 = savedState5.f3799a;
        if (i5 != -1) {
            this.f3780p = i5;
            z = savedState5.f3807i;
        } else {
            z = this.f3779o;
        }
        anchorInfo.f3786c = z;
        if (savedState5.f3803e > 1) {
            LazySpanLookup lazySpanLookup = this.f3782r;
            lazySpanLookup.f3793a = savedState5.f3804f;
            lazySpanLookup.f3794b = savedState5.f3805g;
        }
    }

    private void attachViewToSpans(View view, LayoutParams layoutParams, LayoutState layoutState) {
        if (layoutState.f3635e == 1) {
            if (layoutParams.f3792f) {
                appendViewToAllSpans(view);
            } else {
                layoutParams.f3791e.a(view);
            }
        } else if (layoutParams.f3792f) {
            prependViewToAllSpans(view);
        } else {
            layoutParams.f3791e.r(view);
        }
    }

    private int calculateScrollDirectionForPosition(int i2) {
        if (getChildCount() == 0) {
            return this.f3779o ? 1 : -1;
        }
        return (i2 < x()) != this.f3779o ? -1 : 1;
    }

    private boolean checkSpanForGap(Span span) {
        if (this.f3779o) {
            if (span.i() < this.f3776l.getEndAfterPadding()) {
                ArrayList arrayList = span.f3809a;
                return !span.k((View) arrayList.get(arrayList.size() - 1)).f3792f;
            }
        } else if (span.l() > this.f3776l.getStartAfterPadding()) {
            return !span.k((View) span.f3809a.get(0)).f3792f;
        }
        return false;
    }

    private int computeScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.a(state, this.f3776l, v(!this.mSmoothScrollbarEnabled), u(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    private int computeScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.b(state, this.f3776l, v(!this.mSmoothScrollbarEnabled), u(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled, this.f3779o);
    }

    private int computeScrollRange(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.c(state, this.f3776l, v(!this.mSmoothScrollbarEnabled), u(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    private int convertFocusDirectionToLayoutDirection(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 17 ? i2 != 33 ? i2 != 66 ? (i2 == 130 && this.mOrientation == 1) ? 1 : Integer.MIN_VALUE : this.mOrientation == 0 ? 1 : Integer.MIN_VALUE : this.mOrientation == 1 ? -1 : Integer.MIN_VALUE : this.mOrientation == 0 ? -1 : Integer.MIN_VALUE : (this.mOrientation != 1 && A()) ? -1 : 1 : (this.mOrientation != 1 && A()) ? 1 : -1;
    }

    private LazySpanLookup.FullSpanItem createFullSpanItemFromEnd(int i2) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.f3797c = new int[this.mSpanCount];
        for (int i3 = 0; i3 < this.mSpanCount; i3++) {
            fullSpanItem.f3797c[i3] = i2 - this.f3775k[i3].j(i2);
        }
        return fullSpanItem;
    }

    private LazySpanLookup.FullSpanItem createFullSpanItemFromStart(int i2) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.f3797c = new int[this.mSpanCount];
        for (int i3 = 0; i3 < this.mSpanCount; i3++) {
            fullSpanItem.f3797c[i3] = this.f3775k[i3].m(i2) - i2;
        }
        return fullSpanItem;
    }

    private void createOrientationHelpers() {
        this.f3776l = OrientationHelper.createOrientationHelper(this, this.mOrientation);
        this.f3777m = OrientationHelper.createOrientationHelper(this, 1 - this.mOrientation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r9v7 */
    private int fill(RecyclerView.Recycler recycler, LayoutState layoutState, RecyclerView.State state) {
        Span span;
        int decoratedMeasurement;
        int i2;
        int i3;
        int decoratedMeasurement2;
        RecyclerView.LayoutManager layoutManager;
        View view;
        int i4;
        int i5;
        boolean z;
        ?? r9 = 0;
        this.mRemainingSpans.set(0, this.mSpanCount, true);
        int i6 = this.mLayoutState.f3639i ? layoutState.f3635e == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE : layoutState.f3635e == 1 ? layoutState.f3637g + layoutState.f3632b : layoutState.f3636f - layoutState.f3632b;
        updateAllRemainingSpans(layoutState.f3635e, i6);
        int endAfterPadding = this.f3779o ? this.f3776l.getEndAfterPadding() : this.f3776l.getStartAfterPadding();
        boolean z2 = false;
        while (layoutState.a(state) && (this.mLayoutState.f3639i || !this.mRemainingSpans.isEmpty())) {
            View b2 = layoutState.b(recycler);
            LayoutParams layoutParams = (LayoutParams) b2.getLayoutParams();
            int viewLayoutPosition = layoutParams.getViewLayoutPosition();
            int d2 = this.f3782r.d(viewLayoutPosition);
            boolean z3 = d2 == -1 ? true : r9;
            if (z3) {
                span = layoutParams.f3792f ? this.f3775k[r9] : getNextSpan(layoutState);
                this.f3782r.h(viewLayoutPosition, span);
            } else {
                span = this.f3775k[d2];
            }
            Span span2 = span;
            layoutParams.f3791e = span2;
            if (layoutState.f3635e == 1) {
                addView(b2);
            } else {
                addView(b2, r9);
            }
            measureChildWithDecorationsAndMargin(b2, layoutParams, r9);
            if (layoutState.f3635e == 1) {
                int maxEnd = layoutParams.f3792f ? getMaxEnd(endAfterPadding) : span2.j(endAfterPadding);
                int decoratedMeasurement3 = this.f3776l.getDecoratedMeasurement(b2) + maxEnd;
                if (z3 && layoutParams.f3792f) {
                    LazySpanLookup.FullSpanItem createFullSpanItemFromEnd = createFullSpanItemFromEnd(maxEnd);
                    createFullSpanItemFromEnd.f3796b = -1;
                    createFullSpanItemFromEnd.f3795a = viewLayoutPosition;
                    this.f3782r.addFullSpanItem(createFullSpanItemFromEnd);
                }
                i2 = decoratedMeasurement3;
                decoratedMeasurement = maxEnd;
            } else {
                int minStart = layoutParams.f3792f ? getMinStart(endAfterPadding) : span2.m(endAfterPadding);
                decoratedMeasurement = minStart - this.f3776l.getDecoratedMeasurement(b2);
                if (z3 && layoutParams.f3792f) {
                    LazySpanLookup.FullSpanItem createFullSpanItemFromStart = createFullSpanItemFromStart(minStart);
                    createFullSpanItemFromStart.f3796b = 1;
                    createFullSpanItemFromStart.f3795a = viewLayoutPosition;
                    this.f3782r.addFullSpanItem(createFullSpanItemFromStart);
                }
                i2 = minStart;
            }
            if (layoutParams.f3792f && layoutState.f3634d == -1) {
                if (!z3) {
                    if (!(layoutState.f3635e == 1 ? r() : s())) {
                        LazySpanLookup.FullSpanItem fullSpanItem = this.f3782r.getFullSpanItem(viewLayoutPosition);
                        if (fullSpanItem != null) {
                            fullSpanItem.f3798d = true;
                        }
                    }
                }
                this.mLaidOutInvalidFullSpan = true;
            }
            attachViewToSpans(b2, layoutParams, layoutState);
            if (A() && this.mOrientation == 1) {
                int endAfterPadding2 = layoutParams.f3792f ? this.f3777m.getEndAfterPadding() : this.f3777m.getEndAfterPadding() - (((this.mSpanCount - 1) - span2.f3813e) * this.mSizePerSpan);
                decoratedMeasurement2 = endAfterPadding2;
                i3 = endAfterPadding2 - this.f3777m.getDecoratedMeasurement(b2);
            } else {
                int startAfterPadding = layoutParams.f3792f ? this.f3777m.getStartAfterPadding() : (span2.f3813e * this.mSizePerSpan) + this.f3777m.getStartAfterPadding();
                i3 = startAfterPadding;
                decoratedMeasurement2 = this.f3777m.getDecoratedMeasurement(b2) + startAfterPadding;
            }
            if (this.mOrientation == 1) {
                layoutManager = this;
                view = b2;
                i4 = i3;
                i3 = decoratedMeasurement;
                i5 = decoratedMeasurement2;
            } else {
                layoutManager = this;
                view = b2;
                i4 = decoratedMeasurement;
                i5 = i2;
                i2 = decoratedMeasurement2;
            }
            layoutManager.layoutDecoratedWithMargins(view, i4, i3, i5, i2);
            if (layoutParams.f3792f) {
                updateAllRemainingSpans(this.mLayoutState.f3635e, i6);
            } else {
                updateRemainingSpans(span2, this.mLayoutState.f3635e, i6);
            }
            recycle(recycler, this.mLayoutState);
            if (this.mLayoutState.f3638h && b2.hasFocusable()) {
                if (layoutParams.f3792f) {
                    this.mRemainingSpans.clear();
                } else {
                    z = false;
                    this.mRemainingSpans.set(span2.f3813e, false);
                    r9 = z;
                    z2 = true;
                }
            }
            z = false;
            r9 = z;
            z2 = true;
        }
        int i7 = r9;
        if (!z2) {
            recycle(recycler, this.mLayoutState);
        }
        int startAfterPadding2 = this.mLayoutState.f3635e == -1 ? this.f3776l.getStartAfterPadding() - getMinStart(this.f3776l.getStartAfterPadding()) : getMaxEnd(this.f3776l.getEndAfterPadding()) - this.f3776l.getEndAfterPadding();
        return startAfterPadding2 > 0 ? Math.min(layoutState.f3632b, startAfterPadding2) : i7;
    }

    private int findFirstReferenceChildPosition(int i2) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            int position = getPosition(getChildAt(i3));
            if (position >= 0 && position < i2) {
                return position;
            }
        }
        return 0;
    }

    private int findLastReferenceChildPosition(int i2) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            int position = getPosition(getChildAt(childCount));
            if (position >= 0 && position < i2) {
                return position;
            }
        }
        return 0;
    }

    private void fixEndGap(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int endAfterPadding;
        int maxEnd = getMaxEnd(Integer.MIN_VALUE);
        if (maxEnd != Integer.MIN_VALUE && (endAfterPadding = this.f3776l.getEndAfterPadding() - maxEnd) > 0) {
            int i2 = endAfterPadding - (-C(-endAfterPadding, recycler, state));
            if (!z || i2 <= 0) {
                return;
            }
            this.f3776l.offsetChildren(i2);
        }
    }

    private void fixStartGap(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int startAfterPadding;
        int minStart = getMinStart(Integer.MAX_VALUE);
        if (minStart != Integer.MAX_VALUE && (startAfterPadding = minStart - this.f3776l.getStartAfterPadding()) > 0) {
            int C = startAfterPadding - C(startAfterPadding, recycler, state);
            if (!z || C <= 0) {
                return;
            }
            this.f3776l.offsetChildren(-C);
        }
    }

    private int getMaxEnd(int i2) {
        int j2 = this.f3775k[0].j(i2);
        for (int i3 = 1; i3 < this.mSpanCount; i3++) {
            int j3 = this.f3775k[i3].j(i2);
            if (j3 > j2) {
                j2 = j3;
            }
        }
        return j2;
    }

    private int getMaxStart(int i2) {
        int m2 = this.f3775k[0].m(i2);
        for (int i3 = 1; i3 < this.mSpanCount; i3++) {
            int m3 = this.f3775k[i3].m(i2);
            if (m3 > m2) {
                m2 = m3;
            }
        }
        return m2;
    }

    private int getMinEnd(int i2) {
        int j2 = this.f3775k[0].j(i2);
        for (int i3 = 1; i3 < this.mSpanCount; i3++) {
            int j3 = this.f3775k[i3].j(i2);
            if (j3 < j2) {
                j2 = j3;
            }
        }
        return j2;
    }

    private int getMinStart(int i2) {
        int m2 = this.f3775k[0].m(i2);
        for (int i3 = 1; i3 < this.mSpanCount; i3++) {
            int m3 = this.f3775k[i3].m(i2);
            if (m3 < m2) {
                m2 = m3;
            }
        }
        return m2;
    }

    private Span getNextSpan(LayoutState layoutState) {
        int i2;
        int i3;
        int i4 = -1;
        if (preferLastSpan(layoutState.f3635e)) {
            i2 = this.mSpanCount - 1;
            i3 = -1;
        } else {
            i2 = 0;
            i4 = this.mSpanCount;
            i3 = 1;
        }
        Span span = null;
        if (layoutState.f3635e == 1) {
            int i5 = Integer.MAX_VALUE;
            int startAfterPadding = this.f3776l.getStartAfterPadding();
            while (i2 != i4) {
                Span span2 = this.f3775k[i2];
                int j2 = span2.j(startAfterPadding);
                if (j2 < i5) {
                    span = span2;
                    i5 = j2;
                }
                i2 += i3;
            }
            return span;
        }
        int i6 = Integer.MIN_VALUE;
        int endAfterPadding = this.f3776l.getEndAfterPadding();
        while (i2 != i4) {
            Span span3 = this.f3775k[i2];
            int m2 = span3.m(endAfterPadding);
            if (m2 > i6) {
                span = span3;
                i6 = m2;
            }
            i2 += i3;
        }
        return span;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0043 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void handleUpdate(int i2, int i3, int i4) {
        int i5;
        int i6;
        int y = this.f3779o ? y() : x();
        if (i4 != 8) {
            i5 = i2 + i3;
        } else if (i2 >= i3) {
            i5 = i2 + 1;
            i6 = i3;
            this.f3782r.e(i6);
            if (i4 != 1) {
                this.f3782r.f(i2, i3);
            } else if (i4 == 2) {
                this.f3782r.g(i2, i3);
            } else if (i4 == 8) {
                this.f3782r.g(i2, 1);
                this.f3782r.f(i3, 1);
            }
            if (i5 > y) {
                return;
            }
            if (i6 <= (this.f3779o ? x() : y())) {
                requestLayout();
                return;
            }
            return;
        } else {
            i5 = i3 + 1;
        }
        i6 = i2;
        this.f3782r.e(i6);
        if (i4 != 1) {
        }
        if (i5 > y) {
        }
    }

    private void measureChildWithDecorationsAndMargin(View view, int i2, int i3, boolean z) {
        calculateItemDecorationsForChild(view, this.mTmpRect);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i4 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
        Rect rect = this.mTmpRect;
        int updateSpecWithExtra = updateSpecWithExtra(i2, i4 + rect.left, ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + rect.right);
        int i5 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
        Rect rect2 = this.mTmpRect;
        int updateSpecWithExtra2 = updateSpecWithExtra(i3, i5 + rect2.top, ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin + rect2.bottom);
        if (z ? p(view, updateSpecWithExtra, updateSpecWithExtra2, layoutParams) : n(view, updateSpecWithExtra, updateSpecWithExtra2, layoutParams)) {
            view.measure(updateSpecWithExtra, updateSpecWithExtra2);
        }
    }

    private void measureChildWithDecorationsAndMargin(View view, LayoutParams layoutParams, boolean z) {
        int childMeasureSpec;
        int childMeasureSpec2;
        if (layoutParams.f3792f) {
            if (this.mOrientation != 1) {
                measureChildWithDecorationsAndMargin(view, RecyclerView.LayoutManager.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight(), ((ViewGroup.MarginLayoutParams) layoutParams).width, true), this.mFullSizeSpec, z);
                return;
            }
            childMeasureSpec = this.mFullSizeSpec;
        } else if (this.mOrientation != 1) {
            childMeasureSpec = RecyclerView.LayoutManager.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight(), ((ViewGroup.MarginLayoutParams) layoutParams).width, true);
            childMeasureSpec2 = RecyclerView.LayoutManager.getChildMeasureSpec(this.mSizePerSpan, getHeightMode(), 0, ((ViewGroup.MarginLayoutParams) layoutParams).height, false);
            measureChildWithDecorationsAndMargin(view, childMeasureSpec, childMeasureSpec2, z);
        } else {
            childMeasureSpec = RecyclerView.LayoutManager.getChildMeasureSpec(this.mSizePerSpan, getWidthMode(), 0, ((ViewGroup.MarginLayoutParams) layoutParams).width, false);
        }
        childMeasureSpec2 = RecyclerView.LayoutManager.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom(), ((ViewGroup.MarginLayoutParams) layoutParams).height, true);
        measureChildWithDecorationsAndMargin(view, childMeasureSpec, childMeasureSpec2, z);
    }

    /* JADX WARN: Code restructure failed: missing block: B:87:0x014b, code lost:
        if (t() != false) goto L83;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        SavedState savedState;
        AnchorInfo anchorInfo = this.mAnchorInfo;
        if (!(this.mPendingSavedState == null && this.f3780p == -1) && state.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            anchorInfo.c();
            return;
        }
        boolean z2 = true;
        boolean z3 = (anchorInfo.f3788e && this.f3780p == -1 && this.mPendingSavedState == null) ? false : true;
        if (z3) {
            anchorInfo.c();
            if (this.mPendingSavedState != null) {
                applyPendingSavedState(anchorInfo);
            } else {
                resolveShouldLayoutReverse();
                anchorInfo.f3786c = this.f3779o;
            }
            E(state, anchorInfo);
            anchorInfo.f3788e = true;
        }
        if (this.mPendingSavedState == null && this.f3780p == -1 && (anchorInfo.f3786c != this.mLastLayoutFromEnd || A() != this.mLastLayoutRTL)) {
            this.f3782r.a();
            anchorInfo.f3787d = true;
        }
        if (getChildCount() > 0 && ((savedState = this.mPendingSavedState) == null || savedState.f3801c < 1)) {
            if (anchorInfo.f3787d) {
                for (int i2 = 0; i2 < this.mSpanCount; i2++) {
                    this.f3775k[i2].e();
                    int i3 = anchorInfo.f3785b;
                    if (i3 != Integer.MIN_VALUE) {
                        this.f3775k[i2].s(i3);
                    }
                }
            } else if (z3 || this.mAnchorInfo.f3789f == null) {
                for (int i4 = 0; i4 < this.mSpanCount; i4++) {
                    this.f3775k[i4].b(this.f3779o, anchorInfo.f3785b);
                }
                this.mAnchorInfo.d(this.f3775k);
            } else {
                for (int i5 = 0; i5 < this.mSpanCount; i5++) {
                    Span span = this.f3775k[i5];
                    span.e();
                    span.s(this.mAnchorInfo.f3789f[i5]);
                }
            }
        }
        detachAndScrapAttachedViews(recycler);
        this.mLayoutState.f3631a = false;
        this.mLaidOutInvalidFullSpan = false;
        F(this.f3777m.getTotalSpace());
        updateLayoutState(anchorInfo.f3784a, state);
        if (anchorInfo.f3786c) {
            setLayoutStateDirection(-1);
            fill(recycler, this.mLayoutState, state);
            setLayoutStateDirection(1);
        } else {
            setLayoutStateDirection(1);
            fill(recycler, this.mLayoutState, state);
            setLayoutStateDirection(-1);
        }
        LayoutState layoutState = this.mLayoutState;
        layoutState.f3633c = anchorInfo.f3784a + layoutState.f3634d;
        fill(recycler, layoutState, state);
        repositionToWrapContentIfNecessary();
        if (getChildCount() > 0) {
            if (this.f3779o) {
                fixEndGap(recycler, state, true);
                fixStartGap(recycler, state, false);
            } else {
                fixStartGap(recycler, state, true);
                fixEndGap(recycler, state, false);
            }
        }
        if (z && !state.isPreLayout()) {
            if (this.mGapStrategy != 0 && getChildCount() > 0 && (this.mLaidOutInvalidFullSpan || z() != null)) {
                removeCallbacks(this.mCheckForGapsRunnable);
            }
        }
        z2 = false;
        if (state.isPreLayout()) {
            this.mAnchorInfo.c();
        }
        this.mLastLayoutFromEnd = anchorInfo.f3786c;
        this.mLastLayoutRTL = A();
        if (z2) {
            this.mAnchorInfo.c();
            onLayoutChildren(recycler, state, false);
        }
    }

    private boolean preferLastSpan(int i2) {
        if (this.mOrientation == 0) {
            return (i2 == -1) != this.f3779o;
        }
        return ((i2 == -1) == this.f3779o) == A();
    }

    private void prependViewToAllSpans(View view) {
        for (int i2 = this.mSpanCount - 1; i2 >= 0; i2--) {
            this.f3775k[i2].r(view);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0010, code lost:
        if (r4.f3635e == (-1)) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void recycle(RecyclerView.Recycler recycler, LayoutState layoutState) {
        int min;
        int min2;
        if (!layoutState.f3631a || layoutState.f3639i) {
            return;
        }
        if (layoutState.f3632b != 0) {
            if (layoutState.f3635e == -1) {
                int i2 = layoutState.f3636f;
                int maxStart = i2 - getMaxStart(i2);
                if (maxStart >= 0) {
                    min2 = layoutState.f3637g - Math.min(maxStart, layoutState.f3632b);
                    recycleFromEnd(recycler, min2);
                    return;
                }
                min2 = layoutState.f3637g;
                recycleFromEnd(recycler, min2);
                return;
            }
            int minEnd = getMinEnd(layoutState.f3637g) - layoutState.f3637g;
            if (minEnd >= 0) {
                min = Math.min(minEnd, layoutState.f3632b) + layoutState.f3636f;
                recycleFromStart(recycler, min);
            }
            min = layoutState.f3636f;
            recycleFromStart(recycler, min);
        }
    }

    private void recycleFromEnd(RecyclerView.Recycler recycler, int i2) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (this.f3776l.getDecoratedStart(childAt) < i2 || this.f3776l.getTransformedStartWithDecoration(childAt) < i2) {
                return;
            }
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (layoutParams.f3792f) {
                for (int i3 = 0; i3 < this.mSpanCount; i3++) {
                    if (this.f3775k[i3].f3809a.size() == 1) {
                        return;
                    }
                }
                for (int i4 = 0; i4 < this.mSpanCount; i4++) {
                    this.f3775k[i4].p();
                }
            } else if (layoutParams.f3791e.f3809a.size() == 1) {
                return;
            } else {
                layoutParams.f3791e.p();
            }
            removeAndRecycleView(childAt, recycler);
        }
    }

    private void recycleFromStart(RecyclerView.Recycler recycler, int i2) {
        while (getChildCount() > 0) {
            View childAt = getChildAt(0);
            if (this.f3776l.getDecoratedEnd(childAt) > i2 || this.f3776l.getTransformedEndWithDecoration(childAt) > i2) {
                return;
            }
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (layoutParams.f3792f) {
                for (int i3 = 0; i3 < this.mSpanCount; i3++) {
                    if (this.f3775k[i3].f3809a.size() == 1) {
                        return;
                    }
                }
                for (int i4 = 0; i4 < this.mSpanCount; i4++) {
                    this.f3775k[i4].q();
                }
            } else if (layoutParams.f3791e.f3809a.size() == 1) {
                return;
            } else {
                layoutParams.f3791e.q();
            }
            removeAndRecycleView(childAt, recycler);
        }
    }

    private void repositionToWrapContentIfNecessary() {
        if (this.f3777m.getMode() == 1073741824) {
            return;
        }
        float f2 = 0.0f;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            float decoratedMeasurement = this.f3777m.getDecoratedMeasurement(childAt);
            if (decoratedMeasurement >= f2) {
                if (((LayoutParams) childAt.getLayoutParams()).isFullSpan()) {
                    decoratedMeasurement = (decoratedMeasurement * 1.0f) / this.mSpanCount;
                }
                f2 = Math.max(f2, decoratedMeasurement);
            }
        }
        int i3 = this.mSizePerSpan;
        int round = Math.round(f2 * this.mSpanCount);
        if (this.f3777m.getMode() == Integer.MIN_VALUE) {
            round = Math.min(round, this.f3777m.getTotalSpace());
        }
        F(round);
        if (this.mSizePerSpan == i3) {
            return;
        }
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt2 = getChildAt(i4);
            LayoutParams layoutParams = (LayoutParams) childAt2.getLayoutParams();
            if (!layoutParams.f3792f) {
                if (A() && this.mOrientation == 1) {
                    int i5 = this.mSpanCount;
                    int i6 = layoutParams.f3791e.f3813e;
                    childAt2.offsetLeftAndRight(((-((i5 - 1) - i6)) * this.mSizePerSpan) - ((-((i5 - 1) - i6)) * i3));
                } else {
                    int i7 = layoutParams.f3791e.f3813e;
                    int i8 = this.mOrientation;
                    int i9 = (this.mSizePerSpan * i7) - (i7 * i3);
                    if (i8 == 1) {
                        childAt2.offsetLeftAndRight(i9);
                    } else {
                        childAt2.offsetTopAndBottom(i9);
                    }
                }
            }
        }
    }

    private void resolveShouldLayoutReverse() {
        this.f3779o = (this.mOrientation == 1 || !A()) ? this.f3778n : !this.f3778n;
    }

    private void setLayoutStateDirection(int i2) {
        LayoutState layoutState = this.mLayoutState;
        layoutState.f3635e = i2;
        layoutState.f3634d = this.f3779o != (i2 == -1) ? -1 : 1;
    }

    private void updateAllRemainingSpans(int i2, int i3) {
        for (int i4 = 0; i4 < this.mSpanCount; i4++) {
            if (!this.f3775k[i4].f3809a.isEmpty()) {
                updateRemainingSpans(this.f3775k[i4], i2, i3);
            }
        }
    }

    private boolean updateAnchorFromChildren(RecyclerView.State state, AnchorInfo anchorInfo) {
        boolean z = this.mLastLayoutFromEnd;
        int itemCount = state.getItemCount();
        anchorInfo.f3784a = z ? findLastReferenceChildPosition(itemCount) : findFirstReferenceChildPosition(itemCount);
        anchorInfo.f3785b = Integer.MIN_VALUE;
        return true;
    }

    private void updateLayoutState(int i2, RecyclerView.State state) {
        int i3;
        int i4;
        int targetScrollPosition;
        LayoutState layoutState = this.mLayoutState;
        boolean z = false;
        layoutState.f3632b = 0;
        layoutState.f3633c = i2;
        if (!isSmoothScrolling() || (targetScrollPosition = state.getTargetScrollPosition()) == -1) {
            i3 = 0;
            i4 = 0;
        } else {
            if (this.f3779o == (targetScrollPosition < i2)) {
                i3 = this.f3776l.getTotalSpace();
                i4 = 0;
            } else {
                i4 = this.f3776l.getTotalSpace();
                i3 = 0;
            }
        }
        if (getClipToPadding()) {
            this.mLayoutState.f3636f = this.f3776l.getStartAfterPadding() - i4;
            this.mLayoutState.f3637g = this.f3776l.getEndAfterPadding() + i3;
        } else {
            this.mLayoutState.f3637g = this.f3776l.getEnd() + i3;
            this.mLayoutState.f3636f = -i4;
        }
        LayoutState layoutState2 = this.mLayoutState;
        layoutState2.f3638h = false;
        layoutState2.f3631a = true;
        if (this.f3776l.getMode() == 0 && this.f3776l.getEnd() == 0) {
            z = true;
        }
        layoutState2.f3639i = z;
    }

    private void updateRemainingSpans(Span span, int i2, int i3) {
        int deletedSize = span.getDeletedSize();
        if (i2 == -1) {
            if (span.l() + deletedSize > i3) {
                return;
            }
        } else if (span.i() - deletedSize < i3) {
            return;
        }
        this.mRemainingSpans.set(span.f3813e, false);
    }

    private int updateSpecWithExtra(int i2, int i3, int i4) {
        if (i3 == 0 && i4 == 0) {
            return i2;
        }
        int mode = View.MeasureSpec.getMode(i2);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i2) - i3) - i4), mode) : i2;
    }

    boolean A() {
        return getLayoutDirection() == 1;
    }

    void B(int i2, RecyclerView.State state) {
        int i3;
        int x;
        if (i2 > 0) {
            x = y();
            i3 = 1;
        } else {
            i3 = -1;
            x = x();
        }
        this.mLayoutState.f3631a = true;
        updateLayoutState(x, state);
        setLayoutStateDirection(i3);
        LayoutState layoutState = this.mLayoutState;
        layoutState.f3633c = x + layoutState.f3634d;
        layoutState.f3632b = Math.abs(i2);
    }

    int C(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i2 == 0) {
            return 0;
        }
        B(i2, state);
        int fill = fill(recycler, this.mLayoutState, state);
        if (this.mLayoutState.f3632b >= fill) {
            i2 = i2 < 0 ? -fill : fill;
        }
        this.f3776l.offsetChildren(-i2);
        this.mLastLayoutFromEnd = this.f3779o;
        LayoutState layoutState = this.mLayoutState;
        layoutState.f3632b = 0;
        recycle(recycler, layoutState);
        return i2;
    }

    boolean D(RecyclerView.State state, AnchorInfo anchorInfo) {
        int i2;
        int startAfterPadding;
        int decoratedStart;
        if (!state.isPreLayout() && (i2 = this.f3780p) != -1) {
            if (i2 >= 0 && i2 < state.getItemCount()) {
                SavedState savedState = this.mPendingSavedState;
                if (savedState == null || savedState.f3799a == -1 || savedState.f3801c < 1) {
                    View findViewByPosition = findViewByPosition(this.f3780p);
                    if (findViewByPosition != null) {
                        anchorInfo.f3784a = this.f3779o ? y() : x();
                        if (this.f3781q != Integer.MIN_VALUE) {
                            if (anchorInfo.f3786c) {
                                startAfterPadding = this.f3776l.getEndAfterPadding() - this.f3781q;
                                decoratedStart = this.f3776l.getDecoratedEnd(findViewByPosition);
                            } else {
                                startAfterPadding = this.f3776l.getStartAfterPadding() + this.f3781q;
                                decoratedStart = this.f3776l.getDecoratedStart(findViewByPosition);
                            }
                            anchorInfo.f3785b = startAfterPadding - decoratedStart;
                            return true;
                        } else if (this.f3776l.getDecoratedMeasurement(findViewByPosition) > this.f3776l.getTotalSpace()) {
                            anchorInfo.f3785b = anchorInfo.f3786c ? this.f3776l.getEndAfterPadding() : this.f3776l.getStartAfterPadding();
                            return true;
                        } else {
                            int decoratedStart2 = this.f3776l.getDecoratedStart(findViewByPosition) - this.f3776l.getStartAfterPadding();
                            if (decoratedStart2 < 0) {
                                anchorInfo.f3785b = -decoratedStart2;
                                return true;
                            }
                            int endAfterPadding = this.f3776l.getEndAfterPadding() - this.f3776l.getDecoratedEnd(findViewByPosition);
                            if (endAfterPadding < 0) {
                                anchorInfo.f3785b = endAfterPadding;
                                return true;
                            }
                            anchorInfo.f3785b = Integer.MIN_VALUE;
                        }
                    } else {
                        int i3 = this.f3780p;
                        anchorInfo.f3784a = i3;
                        int i4 = this.f3781q;
                        if (i4 == Integer.MIN_VALUE) {
                            anchorInfo.f3786c = calculateScrollDirectionForPosition(i3) == 1;
                            anchorInfo.a();
                        } else {
                            anchorInfo.b(i4);
                        }
                        anchorInfo.f3787d = true;
                    }
                } else {
                    anchorInfo.f3785b = Integer.MIN_VALUE;
                    anchorInfo.f3784a = this.f3780p;
                }
                return true;
            }
            this.f3780p = -1;
            this.f3781q = Integer.MIN_VALUE;
        }
        return false;
    }

    void E(RecyclerView.State state, AnchorInfo anchorInfo) {
        if (D(state, anchorInfo) || updateAnchorFromChildren(state, anchorInfo)) {
            return;
        }
        anchorInfo.a();
        anchorInfo.f3784a = 0;
    }

    void F(int i2) {
        this.mSizePerSpan = i2 / this.mSpanCount;
        this.mFullSizeSpec = View.MeasureSpec.makeMeasureSpec(i2, this.f3777m.getMode());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void assertNotInLayoutOrScroll(String str) {
        if (this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.mOrientation == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void collectAdjacentPrefetchPositions(int i2, int i3, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int j2;
        int i4;
        if (this.mOrientation != 0) {
            i2 = i3;
        }
        if (getChildCount() == 0 || i2 == 0) {
            return;
        }
        B(i2, state);
        int[] iArr = this.mPrefetchDistances;
        if (iArr == null || iArr.length < this.mSpanCount) {
            this.mPrefetchDistances = new int[this.mSpanCount];
        }
        int i5 = 0;
        for (int i6 = 0; i6 < this.mSpanCount; i6++) {
            LayoutState layoutState = this.mLayoutState;
            if (layoutState.f3634d == -1) {
                j2 = layoutState.f3636f;
                i4 = this.f3775k[i6].m(j2);
            } else {
                j2 = this.f3775k[i6].j(layoutState.f3637g);
                i4 = this.mLayoutState.f3637g;
            }
            int i7 = j2 - i4;
            if (i7 >= 0) {
                this.mPrefetchDistances[i5] = i7;
                i5++;
            }
        }
        Arrays.sort(this.mPrefetchDistances, 0, i5);
        for (int i8 = 0; i8 < i5 && this.mLayoutState.a(state); i8++) {
            layoutPrefetchRegistry.addPosition(this.mLayoutState.f3633c, this.mPrefetchDistances[i8]);
            LayoutState layoutState2 = this.mLayoutState;
            layoutState2.f3633c += layoutState2.f3634d;
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
        int calculateScrollDirectionForPosition = calculateScrollDirectionForPosition(i2);
        PointF pointF = new PointF();
        if (calculateScrollDirectionForPosition == 0) {
            return null;
        }
        if (this.mOrientation == 0) {
            pointF.x = calculateScrollDirectionForPosition;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = calculateScrollDirectionForPosition;
        }
        return pointF;
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

    public int[] findFirstCompletelyVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.mSpanCount];
        } else if (iArr.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + iArr.length);
        }
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            iArr[i2] = this.f3775k[i2].findFirstCompletelyVisibleItemPosition();
        }
        return iArr;
    }

    public int[] findFirstVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.mSpanCount];
        } else if (iArr.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + iArr.length);
        }
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            iArr[i2] = this.f3775k[i2].findFirstVisibleItemPosition();
        }
        return iArr;
    }

    public int[] findLastCompletelyVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.mSpanCount];
        } else if (iArr.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + iArr.length);
        }
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            iArr[i2] = this.f3775k[i2].findLastCompletelyVisibleItemPosition();
        }
        return iArr;
    }

    public int[] findLastVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.mSpanCount];
        } else if (iArr.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + iArr.length);
        }
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            iArr[i2] = this.f3775k[i2].findLastVisibleItemPosition();
        }
        return iArr;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return this.mOrientation == 0 ? new LayoutParams(-2, -1) : new LayoutParams(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    public int getGapStrategy() {
        return this.mGapStrategy;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public boolean getReverseLayout() {
        return this.f3778n;
    }

    public int getSpanCount() {
        return this.mSpanCount;
    }

    public void invalidateSpanAssignments() {
        this.f3782r.a();
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean isAutoMeasureEnabled() {
        return this.mGapStrategy != 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void offsetChildrenHorizontal(int i2) {
        super.offsetChildrenHorizontal(i2);
        for (int i3 = 0; i3 < this.mSpanCount; i3++) {
            this.f3775k[i3].o(i2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void offsetChildrenVertical(int i2) {
        super.offsetChildrenVertical(i2);
        for (int i3 = 0; i3 < this.mSpanCount; i3++) {
            this.f3775k[i3].o(i2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAdapterChanged(@Nullable RecyclerView.Adapter adapter, @Nullable RecyclerView.Adapter adapter2) {
        this.f3782r.a();
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            this.f3775k[i2].e();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        removeCallbacks(this.mCheckForGapsRunnable);
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            this.f3775k[i2].e();
        }
        recyclerView.requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    @Nullable
    public View onFocusSearchFailed(View view, int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        View findContainingItemView;
        View focusableViewAfter;
        if (getChildCount() == 0 || (findContainingItemView = findContainingItemView(view)) == null) {
            return null;
        }
        resolveShouldLayoutReverse();
        int convertFocusDirectionToLayoutDirection = convertFocusDirectionToLayoutDirection(i2);
        if (convertFocusDirectionToLayoutDirection == Integer.MIN_VALUE) {
            return null;
        }
        LayoutParams layoutParams = (LayoutParams) findContainingItemView.getLayoutParams();
        boolean z = layoutParams.f3792f;
        Span span = layoutParams.f3791e;
        int y = convertFocusDirectionToLayoutDirection == 1 ? y() : x();
        updateLayoutState(y, state);
        setLayoutStateDirection(convertFocusDirectionToLayoutDirection);
        LayoutState layoutState = this.mLayoutState;
        layoutState.f3633c = layoutState.f3634d + y;
        layoutState.f3632b = (int) (this.f3776l.getTotalSpace() * MAX_SCROLL_FACTOR);
        LayoutState layoutState2 = this.mLayoutState;
        layoutState2.f3638h = true;
        layoutState2.f3631a = false;
        fill(recycler, layoutState2, state);
        this.mLastLayoutFromEnd = this.f3779o;
        if (z || (focusableViewAfter = span.getFocusableViewAfter(y, convertFocusDirectionToLayoutDirection)) == null || focusableViewAfter == findContainingItemView) {
            if (preferLastSpan(convertFocusDirectionToLayoutDirection)) {
                for (int i3 = this.mSpanCount - 1; i3 >= 0; i3--) {
                    View focusableViewAfter2 = this.f3775k[i3].getFocusableViewAfter(y, convertFocusDirectionToLayoutDirection);
                    if (focusableViewAfter2 != null && focusableViewAfter2 != findContainingItemView) {
                        return focusableViewAfter2;
                    }
                }
            } else {
                for (int i4 = 0; i4 < this.mSpanCount; i4++) {
                    View focusableViewAfter3 = this.f3775k[i4].getFocusableViewAfter(y, convertFocusDirectionToLayoutDirection);
                    if (focusableViewAfter3 != null && focusableViewAfter3 != findContainingItemView) {
                        return focusableViewAfter3;
                    }
                }
            }
            boolean z2 = (this.f3778n ^ true) == (convertFocusDirectionToLayoutDirection == -1);
            if (!z) {
                View findViewByPosition = findViewByPosition(z2 ? span.findFirstPartiallyVisibleItemPosition() : span.findLastPartiallyVisibleItemPosition());
                if (findViewByPosition != null && findViewByPosition != findContainingItemView) {
                    return findViewByPosition;
                }
            }
            if (preferLastSpan(convertFocusDirectionToLayoutDirection)) {
                for (int i5 = this.mSpanCount - 1; i5 >= 0; i5--) {
                    if (i5 != span.f3813e) {
                        Span[] spanArr = this.f3775k;
                        View findViewByPosition2 = findViewByPosition(z2 ? spanArr[i5].findFirstPartiallyVisibleItemPosition() : spanArr[i5].findLastPartiallyVisibleItemPosition());
                        if (findViewByPosition2 != null && findViewByPosition2 != findContainingItemView) {
                            return findViewByPosition2;
                        }
                    }
                }
            } else {
                for (int i6 = 0; i6 < this.mSpanCount; i6++) {
                    Span[] spanArr2 = this.f3775k;
                    View findViewByPosition3 = findViewByPosition(z2 ? spanArr2[i6].findFirstPartiallyVisibleItemPosition() : spanArr2[i6].findLastPartiallyVisibleItemPosition());
                    if (findViewByPosition3 != null && findViewByPosition3 != findContainingItemView) {
                        return findViewByPosition3;
                    }
                }
            }
            return null;
        }
        return focusableViewAfter;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            View v = v(false);
            View u = u(false);
            if (v == null || u == null) {
                return;
            }
            int position = getPosition(v);
            int position2 = getPosition(u);
            if (position < position2) {
                accessibilityEvent.setFromIndex(position);
                accessibilityEvent.setToIndex(position2);
                return;
            }
            accessibilityEvent.setFromIndex(position2);
            accessibilityEvent.setToIndex(position);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView recyclerView, int i2, int i3) {
        handleUpdate(i2, i3, 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsChanged(RecyclerView recyclerView) {
        this.f3782r.a();
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsMoved(RecyclerView recyclerView, int i2, int i3, int i4) {
        handleUpdate(i2, i3, 8);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView, int i2, int i3) {
        handleUpdate(i2, i3, 2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView, int i2, int i3, Object obj) {
        handleUpdate(i2, i3, 4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        onLayoutChildren(recycler, state, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.f3780p = -1;
        this.f3781q = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo.c();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.mPendingSavedState = savedState;
            if (this.f3780p != -1) {
                savedState.a();
                this.mPendingSavedState.b();
            }
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        int m2;
        int startAfterPadding;
        int[] iArr;
        if (this.mPendingSavedState != null) {
            return new SavedState(this.mPendingSavedState);
        }
        SavedState savedState = new SavedState();
        savedState.f3806h = this.f3778n;
        savedState.f3807i = this.mLastLayoutFromEnd;
        savedState.f3808j = this.mLastLayoutRTL;
        LazySpanLookup lazySpanLookup = this.f3782r;
        if (lazySpanLookup == null || (iArr = lazySpanLookup.f3793a) == null) {
            savedState.f3803e = 0;
        } else {
            savedState.f3804f = iArr;
            savedState.f3803e = iArr.length;
            savedState.f3805g = lazySpanLookup.f3794b;
        }
        if (getChildCount() > 0) {
            savedState.f3799a = this.mLastLayoutFromEnd ? y() : x();
            savedState.f3800b = w();
            int i2 = this.mSpanCount;
            savedState.f3801c = i2;
            savedState.f3802d = new int[i2];
            for (int i3 = 0; i3 < this.mSpanCount; i3++) {
                if (this.mLastLayoutFromEnd) {
                    m2 = this.f3775k[i3].j(Integer.MIN_VALUE);
                    if (m2 != Integer.MIN_VALUE) {
                        startAfterPadding = this.f3776l.getEndAfterPadding();
                        m2 -= startAfterPadding;
                        savedState.f3802d[i3] = m2;
                    } else {
                        savedState.f3802d[i3] = m2;
                    }
                } else {
                    m2 = this.f3775k[i3].m(Integer.MIN_VALUE);
                    if (m2 != Integer.MIN_VALUE) {
                        startAfterPadding = this.f3776l.getStartAfterPadding();
                        m2 -= startAfterPadding;
                        savedState.f3802d[i3] = m2;
                    } else {
                        savedState.f3802d[i3] = m2;
                    }
                }
            }
        } else {
            savedState.f3799a = -1;
            savedState.f3800b = -1;
            savedState.f3801c = 0;
        }
        return savedState;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onScrollStateChanged(int i2) {
        if (i2 == 0) {
            t();
        }
    }

    boolean r() {
        int j2 = this.f3775k[0].j(Integer.MIN_VALUE);
        for (int i2 = 1; i2 < this.mSpanCount; i2++) {
            if (this.f3775k[i2].j(Integer.MIN_VALUE) != j2) {
                return false;
            }
        }
        return true;
    }

    boolean s() {
        int m2 = this.f3775k[0].m(Integer.MIN_VALUE);
        for (int i2 = 1; i2 < this.mSpanCount; i2++) {
            if (this.f3775k[i2].m(Integer.MIN_VALUE) != m2) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return C(i2, recycler, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i2) {
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null && savedState.f3799a != i2) {
            savedState.a();
        }
        this.f3780p = i2;
        this.f3781q = Integer.MIN_VALUE;
        requestLayout();
    }

    public void scrollToPositionWithOffset(int i2, int i3) {
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.a();
        }
        this.f3780p = i2;
        this.f3781q = i3;
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return C(i2, recycler, state);
    }

    public void setGapStrategy(int i2) {
        assertNotInLayoutOrScroll(null);
        if (i2 == this.mGapStrategy) {
            return;
        }
        if (i2 != 0 && i2 != 2) {
            throw new IllegalArgumentException("invalid gap strategy. Must be GAP_HANDLING_NONE or GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS");
        }
        this.mGapStrategy = i2;
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void setMeasuredDimension(Rect rect, int i2, int i3) {
        int chooseSize;
        int chooseSize2;
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (this.mOrientation == 1) {
            chooseSize2 = RecyclerView.LayoutManager.chooseSize(i3, rect.height() + paddingTop, getMinimumHeight());
            chooseSize = RecyclerView.LayoutManager.chooseSize(i2, (this.mSizePerSpan * this.mSpanCount) + paddingLeft, getMinimumWidth());
        } else {
            chooseSize = RecyclerView.LayoutManager.chooseSize(i2, rect.width() + paddingLeft, getMinimumWidth());
            chooseSize2 = RecyclerView.LayoutManager.chooseSize(i3, (this.mSizePerSpan * this.mSpanCount) + paddingTop, getMinimumHeight());
        }
        setMeasuredDimension(chooseSize, chooseSize2);
    }

    public void setOrientation(int i2) {
        if (i2 != 0 && i2 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        assertNotInLayoutOrScroll(null);
        if (i2 == this.mOrientation) {
            return;
        }
        this.mOrientation = i2;
        OrientationHelper orientationHelper = this.f3776l;
        this.f3776l = this.f3777m;
        this.f3777m = orientationHelper;
        requestLayout();
    }

    public void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll(null);
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null && savedState.f3806h != z) {
            savedState.f3806h = z;
        }
        this.f3778n = z;
        requestLayout();
    }

    public void setSpanCount(int i2) {
        assertNotInLayoutOrScroll(null);
        if (i2 != this.mSpanCount) {
            invalidateSpanAssignments();
            this.mSpanCount = i2;
            this.mRemainingSpans = new BitSet(this.mSpanCount);
            this.f3775k = new Span[this.mSpanCount];
            for (int i3 = 0; i3 < this.mSpanCount; i3++) {
                this.f3775k[i3] = new Span(i3);
            }
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i2) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i2);
        startSmoothScroll(linearSmoothScroller);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null;
    }

    boolean t() {
        int x;
        int y;
        if (getChildCount() == 0 || this.mGapStrategy == 0 || !isAttachedToWindow()) {
            return false;
        }
        if (this.f3779o) {
            x = y();
            y = x();
        } else {
            x = x();
            y = y();
        }
        if (x == 0 && z() != null) {
            this.f3782r.a();
        } else if (!this.mLaidOutInvalidFullSpan) {
            return false;
        } else {
            int i2 = this.f3779o ? -1 : 1;
            int i3 = y + 1;
            LazySpanLookup.FullSpanItem firstFullSpanItemInRange = this.f3782r.getFirstFullSpanItemInRange(x, i3, i2, true);
            if (firstFullSpanItemInRange == null) {
                this.mLaidOutInvalidFullSpan = false;
                this.f3782r.c(i3);
                return false;
            }
            LazySpanLookup.FullSpanItem firstFullSpanItemInRange2 = this.f3782r.getFirstFullSpanItemInRange(x, firstFullSpanItemInRange.f3795a, i2 * (-1), true);
            if (firstFullSpanItemInRange2 == null) {
                this.f3782r.c(firstFullSpanItemInRange.f3795a);
            } else {
                this.f3782r.c(firstFullSpanItemInRange2.f3795a + 1);
            }
        }
        requestSimpleAnimationsInNextLayout();
        requestLayout();
        return true;
    }

    View u(boolean z) {
        int startAfterPadding = this.f3776l.getStartAfterPadding();
        int endAfterPadding = this.f3776l.getEndAfterPadding();
        View view = null;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            int decoratedStart = this.f3776l.getDecoratedStart(childAt);
            int decoratedEnd = this.f3776l.getDecoratedEnd(childAt);
            if (decoratedEnd > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedEnd <= endAfterPadding || !z) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    View v(boolean z) {
        int startAfterPadding = this.f3776l.getStartAfterPadding();
        int endAfterPadding = this.f3776l.getEndAfterPadding();
        int childCount = getChildCount();
        View view = null;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            int decoratedStart = this.f3776l.getDecoratedStart(childAt);
            if (this.f3776l.getDecoratedEnd(childAt) > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedStart >= startAfterPadding || !z) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    int w() {
        View u = this.f3779o ? u(true) : v(true);
        if (u == null) {
            return -1;
        }
        return getPosition(u);
    }

    int x() {
        if (getChildCount() == 0) {
            return 0;
        }
        return getPosition(getChildAt(0));
    }

    int y() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return 0;
        }
        return getPosition(getChildAt(childCount - 1));
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0074, code lost:
        if (r10 == r11) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0086, code lost:
        if (r10 == r11) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0088, code lost:
        r10 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x008a, code lost:
        r10 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    View z() {
        int i2;
        int i3;
        boolean z;
        int childCount = getChildCount() - 1;
        BitSet bitSet = new BitSet(this.mSpanCount);
        bitSet.set(0, this.mSpanCount, true);
        char c2 = (this.mOrientation == 1 && A()) ? (char) 1 : (char) 65535;
        if (this.f3779o) {
            i2 = -1;
        } else {
            i2 = childCount + 1;
            childCount = 0;
        }
        int i4 = childCount < i2 ? 1 : -1;
        while (childCount != i2) {
            View childAt = getChildAt(childCount);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (bitSet.get(layoutParams.f3791e.f3813e)) {
                if (checkSpanForGap(layoutParams.f3791e)) {
                    return childAt;
                }
                bitSet.clear(layoutParams.f3791e.f3813e);
            }
            if (!layoutParams.f3792f && (i3 = childCount + i4) != i2) {
                View childAt2 = getChildAt(i3);
                if (this.f3779o) {
                    int decoratedEnd = this.f3776l.getDecoratedEnd(childAt);
                    int decoratedEnd2 = this.f3776l.getDecoratedEnd(childAt2);
                    if (decoratedEnd < decoratedEnd2) {
                        return childAt;
                    }
                } else {
                    int decoratedStart = this.f3776l.getDecoratedStart(childAt);
                    int decoratedStart2 = this.f3776l.getDecoratedStart(childAt2);
                    if (decoratedStart > decoratedStart2) {
                        return childAt;
                    }
                }
                if (z) {
                    if ((layoutParams.f3791e.f3813e - ((LayoutParams) childAt2.getLayoutParams()).f3791e.f3813e < 0) != (c2 < 0)) {
                        return childAt;
                    }
                } else {
                    continue;
                }
            }
            childCount += i4;
        }
        return null;
    }
}
