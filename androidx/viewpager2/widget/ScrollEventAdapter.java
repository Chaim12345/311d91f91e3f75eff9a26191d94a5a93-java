package androidx.viewpager2.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Locale;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ScrollEventAdapter extends RecyclerView.OnScrollListener {
    private static final int NO_POSITION = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_IN_PROGRESS_FAKE_DRAG = 4;
    private static final int STATE_IN_PROGRESS_IMMEDIATE_SCROLL = 3;
    private static final int STATE_IN_PROGRESS_MANUAL_DRAG = 1;
    private static final int STATE_IN_PROGRESS_SMOOTH_SCROLL = 2;
    private int mAdapterState;
    private ViewPager2.OnPageChangeCallback mCallback;
    private boolean mDataSetChangeHappened;
    private boolean mDispatchSelected;
    private int mDragStartPosition;
    private boolean mFakeDragging;
    @NonNull
    private final LinearLayoutManager mLayoutManager;
    @NonNull
    private final RecyclerView mRecyclerView;
    private boolean mScrollHappened;
    private int mScrollState;
    private ScrollEventValues mScrollValues;
    private int mTarget;
    @NonNull
    private final ViewPager2 mViewPager;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class ScrollEventValues {

        /* renamed from: a  reason: collision with root package name */
        int f4281a;

        /* renamed from: b  reason: collision with root package name */
        float f4282b;

        /* renamed from: c  reason: collision with root package name */
        int f4283c;

        ScrollEventValues() {
        }

        void a() {
            this.f4281a = -1;
            this.f4282b = 0.0f;
            this.f4283c = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ScrollEventAdapter(@NonNull ViewPager2 viewPager2) {
        this.mViewPager = viewPager2;
        RecyclerView recyclerView = viewPager2.f4287c;
        this.mRecyclerView = recyclerView;
        this.mLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        this.mScrollValues = new ScrollEventValues();
        resetState();
    }

    private void dispatchScrolled(int i2, float f2, int i3) {
        ViewPager2.OnPageChangeCallback onPageChangeCallback = this.mCallback;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.onPageScrolled(i2, f2, i3);
        }
    }

    private void dispatchSelected(int i2) {
        ViewPager2.OnPageChangeCallback onPageChangeCallback = this.mCallback;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.onPageSelected(i2);
        }
    }

    private void dispatchStateChanged(int i2) {
        if ((this.mAdapterState == 3 && this.mScrollState == 0) || this.mScrollState == i2) {
            return;
        }
        this.mScrollState = i2;
        ViewPager2.OnPageChangeCallback onPageChangeCallback = this.mCallback;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.onPageScrollStateChanged(i2);
        }
    }

    private int getPosition() {
        return this.mLayoutManager.findFirstVisibleItemPosition();
    }

    private boolean isInAnyDraggingState() {
        int i2 = this.mAdapterState;
        return i2 == 1 || i2 == 4;
    }

    private void resetState() {
        this.mAdapterState = 0;
        this.mScrollState = 0;
        this.mScrollValues.a();
        this.mDragStartPosition = -1;
        this.mTarget = -1;
        this.mDispatchSelected = false;
        this.mScrollHappened = false;
        this.mFakeDragging = false;
        this.mDataSetChangeHappened = false;
    }

    private void startDrag(boolean z) {
        this.mFakeDragging = z;
        this.mAdapterState = z ? 4 : 1;
        int i2 = this.mTarget;
        if (i2 != -1) {
            this.mDragStartPosition = i2;
            this.mTarget = -1;
        } else if (this.mDragStartPosition == -1) {
            this.mDragStartPosition = getPosition();
        }
        dispatchStateChanged(1);
    }

    private void updateScrollEventValues() {
        int top;
        ScrollEventValues scrollEventValues = this.mScrollValues;
        int findFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition();
        scrollEventValues.f4281a = findFirstVisibleItemPosition;
        if (findFirstVisibleItemPosition == -1) {
            scrollEventValues.a();
            return;
        }
        View findViewByPosition = this.mLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
        if (findViewByPosition == null) {
            scrollEventValues.a();
            return;
        }
        int leftDecorationWidth = this.mLayoutManager.getLeftDecorationWidth(findViewByPosition);
        int rightDecorationWidth = this.mLayoutManager.getRightDecorationWidth(findViewByPosition);
        int topDecorationHeight = this.mLayoutManager.getTopDecorationHeight(findViewByPosition);
        int bottomDecorationHeight = this.mLayoutManager.getBottomDecorationHeight(findViewByPosition);
        ViewGroup.LayoutParams layoutParams = findViewByPosition.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            leftDecorationWidth += marginLayoutParams.leftMargin;
            rightDecorationWidth += marginLayoutParams.rightMargin;
            topDecorationHeight += marginLayoutParams.topMargin;
            bottomDecorationHeight += marginLayoutParams.bottomMargin;
        }
        int height = findViewByPosition.getHeight() + topDecorationHeight + bottomDecorationHeight;
        int width = findViewByPosition.getWidth() + leftDecorationWidth + rightDecorationWidth;
        if (this.mLayoutManager.getOrientation() == 0) {
            top = (findViewByPosition.getLeft() - leftDecorationWidth) - this.mRecyclerView.getPaddingLeft();
            if (this.mViewPager.a()) {
                top = -top;
            }
            height = width;
        } else {
            top = (findViewByPosition.getTop() - topDecorationHeight) - this.mRecyclerView.getPaddingTop();
        }
        int i2 = -top;
        scrollEventValues.f4283c = i2;
        if (i2 >= 0) {
            scrollEventValues.f4282b = height == 0 ? 0.0f : i2 / height;
        } else if (!new AnimateLayoutChangeDetector(this.mLayoutManager).a()) {
            throw new IllegalStateException(String.format(Locale.US, "Page can only be offset by a positive amount, not by %d", Integer.valueOf(scrollEventValues.f4283c)));
        } else {
            throw new IllegalStateException("Page(s) contain a ViewGroup with a LayoutTransition (or animateLayoutChanges=\"true\"), which interferes with the scrolling animation. Make sure to call getLayoutTransition().setAnimateParentHierarchy(false) on all ViewGroups with a LayoutTransition before an animation is started.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double a() {
        updateScrollEventValues();
        ScrollEventValues scrollEventValues = this.mScrollValues;
        return scrollEventValues.f4281a + scrollEventValues.f4282b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b() {
        return this.mScrollState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c() {
        return this.mScrollState == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d() {
        return this.mFakeDragging;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean e() {
        return this.mScrollState == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        this.mAdapterState = 4;
        startDrag(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g() {
        this.mDataSetChangeHappened = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h() {
        if (!c() || this.mFakeDragging) {
            this.mFakeDragging = false;
            updateScrollEventValues();
            ScrollEventValues scrollEventValues = this.mScrollValues;
            if (scrollEventValues.f4283c != 0) {
                dispatchStateChanged(2);
                return;
            }
            int i2 = scrollEventValues.f4281a;
            if (i2 != this.mDragStartPosition) {
                dispatchSelected(i2);
            }
            dispatchStateChanged(0);
            resetState();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(int i2, boolean z) {
        this.mAdapterState = z ? 2 : 3;
        this.mFakeDragging = false;
        boolean z2 = this.mTarget != i2;
        this.mTarget = i2;
        dispatchStateChanged(2);
        if (z2) {
            dispatchSelected(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(ViewPager2.OnPageChangeCallback onPageChangeCallback) {
        this.mCallback = onPageChangeCallback;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i2) {
        boolean z = true;
        if (!(this.mAdapterState == 1 && this.mScrollState == 1) && i2 == 1) {
            startDrag(false);
        } else if (isInAnyDraggingState() && i2 == 2) {
            if (this.mScrollHappened) {
                dispatchStateChanged(2);
                this.mDispatchSelected = true;
            }
        } else {
            if (isInAnyDraggingState() && i2 == 0) {
                updateScrollEventValues();
                if (this.mScrollHappened) {
                    ScrollEventValues scrollEventValues = this.mScrollValues;
                    if (scrollEventValues.f4283c == 0) {
                        int i3 = this.mDragStartPosition;
                        int i4 = scrollEventValues.f4281a;
                        if (i3 != i4) {
                            dispatchSelected(i4);
                        }
                    } else {
                        z = false;
                    }
                } else {
                    int i5 = this.mScrollValues.f4281a;
                    if (i5 != -1) {
                        dispatchScrolled(i5, 0.0f, 0);
                    }
                }
                if (z) {
                    dispatchStateChanged(0);
                    resetState();
                }
            }
            if (this.mAdapterState == 2 && i2 == 0 && this.mDataSetChangeHappened) {
                updateScrollEventValues();
                ScrollEventValues scrollEventValues2 = this.mScrollValues;
                if (scrollEventValues2.f4283c == 0) {
                    int i6 = this.mTarget;
                    int i7 = scrollEventValues2.f4281a;
                    if (i6 != i7) {
                        if (i7 == -1) {
                            i7 = 0;
                        }
                        dispatchSelected(i7);
                    }
                    dispatchStateChanged(0);
                    resetState();
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x001d, code lost:
        if ((r5 < 0) == r3.mViewPager.a()) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0037, code lost:
        if (r3.mDragStartPosition != r5) goto L34;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0025  */
    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onScrolled(@NonNull RecyclerView recyclerView, int i2, int i3) {
        int i4;
        boolean z;
        this.mScrollHappened = true;
        updateScrollEventValues();
        if (this.mDispatchSelected) {
            this.mDispatchSelected = false;
            if (i3 <= 0) {
                if (i3 == 0) {
                }
                z = false;
                if (z) {
                    ScrollEventValues scrollEventValues = this.mScrollValues;
                    if (scrollEventValues.f4283c != 0) {
                        i4 = scrollEventValues.f4281a + 1;
                        this.mTarget = i4;
                    }
                }
                i4 = this.mScrollValues.f4281a;
                this.mTarget = i4;
            }
            z = true;
            if (z) {
            }
            i4 = this.mScrollValues.f4281a;
            this.mTarget = i4;
        } else if (this.mAdapterState == 0) {
            i4 = this.mScrollValues.f4281a;
            if (i4 == -1) {
                i4 = 0;
            }
            dispatchSelected(i4);
        }
        ScrollEventValues scrollEventValues2 = this.mScrollValues;
        int i5 = scrollEventValues2.f4281a;
        if (i5 == -1) {
            i5 = 0;
        }
        dispatchScrolled(i5, scrollEventValues2.f4282b, scrollEventValues2.f4283c);
        ScrollEventValues scrollEventValues3 = this.mScrollValues;
        int i6 = scrollEventValues3.f4281a;
        int i7 = this.mTarget;
        if ((i6 == i7 || i7 == -1) && scrollEventValues3.f4283c == 0 && this.mScrollState != 1) {
            dispatchStateChanged(0);
            resetState();
        }
    }
}
