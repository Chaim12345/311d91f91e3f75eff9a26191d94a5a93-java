package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.badge.BadgeDrawable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
public class LinearLayoutCompat extends ViewGroup {
    private static final String ACCESSIBILITY_CLASS_NAME = "androidx.appcompat.widget.LinearLayoutCompat";
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface DividerMode {
    }

    /* loaded from: classes.dex */
    public static class LayoutParams extends LinearLayout.LayoutParams {
        public LayoutParams(int i2, int i3) {
            super(i2, i3);
        }

        public LayoutParams(int i2, int i3, float f2) {
            super(i2, i3, f2);
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
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface OrientationMode {
    }

    public LinearLayoutCompat(@NonNull Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = BadgeDrawable.TOP_START;
        int[] iArr = R.styleable.LinearLayoutCompat;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, iArr, i2, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context, iArr, attributeSet, obtainStyledAttributes.getWrappedTypeArray(), i2, 0);
        int i3 = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (i3 >= 0) {
            setOrientation(i3);
        }
        int i4 = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (i4 >= 0) {
            setGravity(i4);
        }
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!z) {
            setBaselineAligned(z);
        }
        this.mWeightSum = obtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = obtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(obtainStyledAttributes.getDrawable(R.styleable.LinearLayoutCompat_divider));
        this.mShowDividers = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
        obtainStyledAttributes.recycle();
    }

    private void forceUniformHeight(int i2, int i3) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i4 = 0; i4 < i2; i4++) {
            View j2 = j(i4);
            if (j2.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) j2.getLayoutParams();
                if (((LinearLayout.LayoutParams) layoutParams).height == -1) {
                    int i5 = ((LinearLayout.LayoutParams) layoutParams).width;
                    ((LinearLayout.LayoutParams) layoutParams).width = j2.getMeasuredWidth();
                    measureChildWithMargins(j2, i3, 0, makeMeasureSpec, 0);
                    ((LinearLayout.LayoutParams) layoutParams).width = i5;
                }
            }
        }
    }

    private void forceUniformWidth(int i2, int i3) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i4 = 0; i4 < i2; i4++) {
            View j2 = j(i4);
            if (j2.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) j2.getLayoutParams();
                if (((LinearLayout.LayoutParams) layoutParams).width == -1) {
                    int i5 = ((LinearLayout.LayoutParams) layoutParams).height;
                    ((LinearLayout.LayoutParams) layoutParams).height = j2.getMeasuredHeight();
                    measureChildWithMargins(j2, makeMeasureSpec, 0, i3, 0);
                    ((LinearLayout.LayoutParams) layoutParams).height = i5;
                }
            }
        }
    }

    private void setChildFrame(View view, int i2, int i3, int i4, int i5) {
        view.layout(i2, i3, i4 + i2, i5 + i3);
    }

    void a(Canvas canvas) {
        int right;
        int left;
        int i2;
        int virtualChildCount = getVirtualChildCount();
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        for (int i3 = 0; i3 < virtualChildCount; i3++) {
            View j2 = j(i3);
            if (j2 != null && j2.getVisibility() != 8 && k(i3)) {
                LayoutParams layoutParams = (LayoutParams) j2.getLayoutParams();
                d(canvas, isLayoutRtl ? j2.getRight() + ((LinearLayout.LayoutParams) layoutParams).rightMargin : (j2.getLeft() - ((LinearLayout.LayoutParams) layoutParams).leftMargin) - this.mDividerWidth);
            }
        }
        if (k(virtualChildCount)) {
            View j3 = j(virtualChildCount - 1);
            if (j3 != null) {
                LayoutParams layoutParams2 = (LayoutParams) j3.getLayoutParams();
                if (isLayoutRtl) {
                    left = j3.getLeft();
                    i2 = ((LinearLayout.LayoutParams) layoutParams2).leftMargin;
                    right = (left - i2) - this.mDividerWidth;
                } else {
                    right = j3.getRight() + ((LinearLayout.LayoutParams) layoutParams2).rightMargin;
                }
            } else if (isLayoutRtl) {
                right = getPaddingLeft();
            } else {
                left = getWidth();
                i2 = getPaddingRight();
                right = (left - i2) - this.mDividerWidth;
            }
            d(canvas, right);
        }
    }

    void b(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        for (int i2 = 0; i2 < virtualChildCount; i2++) {
            View j2 = j(i2);
            if (j2 != null && j2.getVisibility() != 8 && k(i2)) {
                c(canvas, (j2.getTop() - ((LinearLayout.LayoutParams) ((LayoutParams) j2.getLayoutParams())).topMargin) - this.mDividerHeight);
            }
        }
        if (k(virtualChildCount)) {
            View j3 = j(virtualChildCount - 1);
            c(canvas, j3 == null ? (getHeight() - getPaddingBottom()) - this.mDividerHeight : j3.getBottom() + ((LinearLayout.LayoutParams) ((LayoutParams) j3.getLayoutParams())).bottomMargin);
        }
    }

    void c(Canvas canvas, int i2) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i2, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i2);
        this.mDivider.draw(canvas);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    void d(Canvas canvas, int i2) {
        this.mDivider.setBounds(i2, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i2, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: e */
    public LayoutParams generateDefaultLayoutParams() {
        int i2 = this.mOrientation;
        if (i2 == 0) {
            return new LayoutParams(-2, -2);
        }
        if (i2 == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: f */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    int g(View view, int i2) {
        return 0;
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.View
    public int getBaseline() {
        int i2;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i3 = this.mBaselineAlignedChildIndex;
        if (childCount > i3) {
            View childAt = getChildAt(i3);
            int baseline = childAt.getBaseline();
            if (baseline == -1) {
                if (this.mBaselineAlignedChildIndex == 0) {
                    return -1;
                }
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
            int i4 = this.mBaselineChildTop;
            if (this.mOrientation == 1 && (i2 = this.mGravity & 112) != 48) {
                if (i2 == 16) {
                    i4 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
                } else if (i2 == 80) {
                    i4 = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
                }
            }
            return i4 + ((LinearLayout.LayoutParams) ((LayoutParams) childAt.getLayoutParams())).topMargin + baseline;
        }
        throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    public int getGravity() {
        return this.mGravity;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    int h(View view) {
        return 0;
    }

    int i(View view) {
        return 0;
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    View j(int i2) {
        return getChildAt(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean k(int i2) {
        if (i2 == 0) {
            return (this.mShowDividers & 1) != 0;
        } else if (i2 == getChildCount()) {
            return (this.mShowDividers & 4) != 0;
        } else if ((this.mShowDividers & 2) != 0) {
            for (int i3 = i2 - 1; i3 >= 0; i3--) {
                if (getChildAt(i3).getVisibility() != 8) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void l(int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        boolean z;
        int i11;
        int i12;
        int i13;
        int i14;
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int paddingTop = getPaddingTop();
        int i15 = i5 - i3;
        int paddingBottom = i15 - getPaddingBottom();
        int paddingBottom2 = (i15 - paddingTop) - getPaddingBottom();
        int virtualChildCount = getVirtualChildCount();
        int i16 = this.mGravity;
        int i17 = i16 & 112;
        boolean z2 = this.mBaselineAligned;
        int[] iArr = this.mMaxAscent;
        int[] iArr2 = this.mMaxDescent;
        int absoluteGravity = GravityCompat.getAbsoluteGravity(8388615 & i16, ViewCompat.getLayoutDirection(this));
        boolean z3 = true;
        int paddingLeft = absoluteGravity != 1 ? absoluteGravity != 5 ? getPaddingLeft() : ((getPaddingLeft() + i4) - i2) - this.mTotalLength : getPaddingLeft() + (((i4 - i2) - this.mTotalLength) / 2);
        if (isLayoutRtl) {
            i6 = virtualChildCount - 1;
            i7 = -1;
        } else {
            i6 = 0;
            i7 = 1;
        }
        int i18 = 0;
        while (i18 < virtualChildCount) {
            int i19 = i6 + (i7 * i18);
            View j2 = j(i19);
            if (j2 == null) {
                paddingLeft += p(i19);
                z = z3;
                i8 = paddingTop;
                i9 = virtualChildCount;
                i10 = i17;
            } else if (j2.getVisibility() != 8) {
                int measuredWidth = j2.getMeasuredWidth();
                int measuredHeight = j2.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams) j2.getLayoutParams();
                int i20 = i18;
                if (z2) {
                    i9 = virtualChildCount;
                    if (((LinearLayout.LayoutParams) layoutParams).height != -1) {
                        i11 = j2.getBaseline();
                        i12 = ((LinearLayout.LayoutParams) layoutParams).gravity;
                        if (i12 < 0) {
                            i12 = i17;
                        }
                        i13 = i12 & 112;
                        i10 = i17;
                        if (i13 == 16) {
                            if (i13 == 48) {
                                i14 = ((LinearLayout.LayoutParams) layoutParams).topMargin + paddingTop;
                                if (i11 != -1) {
                                    z = true;
                                    i14 += iArr[1] - i11;
                                }
                            } else if (i13 != 80) {
                                i14 = paddingTop;
                            } else {
                                i14 = (paddingBottom - measuredHeight) - ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
                                if (i11 != -1) {
                                    i14 -= iArr2[2] - (j2.getMeasuredHeight() - i11);
                                }
                            }
                            z = true;
                        } else {
                            z = true;
                            i14 = ((((paddingBottom2 - measuredHeight) / 2) + paddingTop) + ((LinearLayout.LayoutParams) layoutParams).topMargin) - ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
                        }
                        if (k(i19)) {
                            paddingLeft += this.mDividerWidth;
                        }
                        int i21 = ((LinearLayout.LayoutParams) layoutParams).leftMargin + paddingLeft;
                        i8 = paddingTop;
                        setChildFrame(j2, i21 + h(j2), i14, measuredWidth, measuredHeight);
                        i18 = i20 + g(j2, i19);
                        paddingLeft = i21 + measuredWidth + ((LinearLayout.LayoutParams) layoutParams).rightMargin + i(j2);
                        i18++;
                        virtualChildCount = i9;
                        i17 = i10;
                        z3 = z;
                        paddingTop = i8;
                    }
                } else {
                    i9 = virtualChildCount;
                }
                i11 = -1;
                i12 = ((LinearLayout.LayoutParams) layoutParams).gravity;
                if (i12 < 0) {
                }
                i13 = i12 & 112;
                i10 = i17;
                if (i13 == 16) {
                }
                if (k(i19)) {
                }
                int i212 = ((LinearLayout.LayoutParams) layoutParams).leftMargin + paddingLeft;
                i8 = paddingTop;
                setChildFrame(j2, i212 + h(j2), i14, measuredWidth, measuredHeight);
                i18 = i20 + g(j2, i19);
                paddingLeft = i212 + measuredWidth + ((LinearLayout.LayoutParams) layoutParams).rightMargin + i(j2);
                i18++;
                virtualChildCount = i9;
                i17 = i10;
                z3 = z;
                paddingTop = i8;
            } else {
                i8 = paddingTop;
                i9 = virtualChildCount;
                i10 = i17;
                z = true;
            }
            i18++;
            virtualChildCount = i9;
            i17 = i10;
            z3 = z;
            paddingTop = i8;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x009d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void m(int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        int paddingLeft = getPaddingLeft();
        int i9 = i4 - i2;
        int paddingRight = i9 - getPaddingRight();
        int paddingRight2 = (i9 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        int i10 = this.mGravity;
        int i11 = i10 & 112;
        int i12 = i10 & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        int paddingTop = i11 != 16 ? i11 != 80 ? getPaddingTop() : ((getPaddingTop() + i5) - i3) - this.mTotalLength : getPaddingTop() + (((i5 - i3) - this.mTotalLength) / 2);
        int i13 = 0;
        while (i13 < virtualChildCount) {
            View j2 = j(i13);
            if (j2 == null) {
                paddingTop += p(i13);
            } else if (j2.getVisibility() != 8) {
                int measuredWidth = j2.getMeasuredWidth();
                int measuredHeight = j2.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams) j2.getLayoutParams();
                int i14 = ((LinearLayout.LayoutParams) layoutParams).gravity;
                if (i14 < 0) {
                    i14 = i12;
                }
                int absoluteGravity = GravityCompat.getAbsoluteGravity(i14, ViewCompat.getLayoutDirection(this)) & 7;
                if (absoluteGravity == 1) {
                    i6 = ((paddingRight2 - measuredWidth) / 2) + paddingLeft + ((LinearLayout.LayoutParams) layoutParams).leftMargin;
                } else if (absoluteGravity != 5) {
                    i7 = ((LinearLayout.LayoutParams) layoutParams).leftMargin + paddingLeft;
                    int i15 = i7;
                    if (k(i13)) {
                        paddingTop += this.mDividerHeight;
                    }
                    int i16 = paddingTop + ((LinearLayout.LayoutParams) layoutParams).topMargin;
                    setChildFrame(j2, i15, i16 + h(j2), measuredWidth, measuredHeight);
                    i13 += g(j2, i13);
                    paddingTop = i16 + measuredHeight + ((LinearLayout.LayoutParams) layoutParams).bottomMargin + i(j2);
                    i8 = 1;
                    i13 += i8;
                } else {
                    i6 = paddingRight - measuredWidth;
                }
                i7 = i6 - ((LinearLayout.LayoutParams) layoutParams).rightMargin;
                int i152 = i7;
                if (k(i13)) {
                }
                int i162 = paddingTop + ((LinearLayout.LayoutParams) layoutParams).topMargin;
                setChildFrame(j2, i152, i162 + h(j2), measuredWidth, measuredHeight);
                i13 += g(j2, i13);
                paddingTop = i162 + measuredHeight + ((LinearLayout.LayoutParams) layoutParams).bottomMargin + i(j2);
                i8 = 1;
                i13 += i8;
            }
            i8 = 1;
            i13 += i8;
        }
    }

    void n(View view, int i2, int i3, int i4, int i5, int i6) {
        measureChildWithMargins(view, i3, i4, i5, i6);
    }

    /* JADX WARN: Code restructure failed: missing block: B:164:0x03a5, code lost:
        if (r8 > 0) goto L176;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x03b0, code lost:
        if (r8 < 0) goto L175;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x03b2, code lost:
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x03b3, code lost:
        r14.measure(android.view.View.MeasureSpec.makeMeasureSpec(r8, r3), r0);
        r9 = android.view.View.combineMeasuredStates(r9, r14.getMeasuredState() & androidx.core.view.ViewCompat.MEASURED_STATE_MASK);
        r0 = r0;
        r3 = r2;
     */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0440  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void o(int i2, int i3) {
        int[] iArr;
        int i4;
        int max;
        int i5;
        int i6;
        int max2;
        int i7;
        int i8;
        int i9;
        float f2;
        int i10;
        boolean z;
        int baseline;
        int i11;
        int i12;
        int i13;
        char c2;
        int i14;
        int i15;
        boolean z2;
        boolean z3;
        View view;
        int max3;
        int i16;
        boolean z4;
        int measuredHeight;
        int g2;
        int baseline2;
        int i17;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        if (this.mMaxAscent == null || this.mMaxDescent == null) {
            this.mMaxAscent = new int[4];
            this.mMaxDescent = new int[4];
        }
        int[] iArr2 = this.mMaxAscent;
        int[] iArr3 = this.mMaxDescent;
        iArr2[3] = -1;
        iArr2[2] = -1;
        iArr2[1] = -1;
        iArr2[0] = -1;
        iArr3[3] = -1;
        iArr3[2] = -1;
        iArr3[1] = -1;
        iArr3[0] = -1;
        boolean z5 = this.mBaselineAligned;
        boolean z6 = this.mUseLargestChild;
        int i18 = 1073741824;
        boolean z7 = mode == 1073741824;
        int i19 = 0;
        int i20 = 0;
        int i21 = 0;
        int i22 = 0;
        int i23 = 0;
        boolean z8 = false;
        int i24 = 0;
        boolean z9 = false;
        boolean z10 = true;
        float f3 = 0.0f;
        while (true) {
            iArr = iArr3;
            if (i19 >= virtualChildCount) {
                break;
            }
            View j2 = j(i19);
            if (j2 == null) {
                this.mTotalLength += p(i19);
            } else if (j2.getVisibility() == 8) {
                i19 += g(j2, i19);
            } else {
                if (k(i19)) {
                    this.mTotalLength += this.mDividerWidth;
                }
                LayoutParams layoutParams = (LayoutParams) j2.getLayoutParams();
                float f4 = ((LinearLayout.LayoutParams) layoutParams).weight;
                float f5 = f3 + f4;
                if (mode == i18 && ((LinearLayout.LayoutParams) layoutParams).width == 0 && f4 > 0.0f) {
                    int i25 = this.mTotalLength;
                    this.mTotalLength = z7 ? i25 + ((LinearLayout.LayoutParams) layoutParams).leftMargin + ((LinearLayout.LayoutParams) layoutParams).rightMargin : Math.max(i25, ((LinearLayout.LayoutParams) layoutParams).leftMargin + i25 + ((LinearLayout.LayoutParams) layoutParams).rightMargin);
                    if (z5) {
                        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                        j2.measure(makeMeasureSpec, makeMeasureSpec);
                        i15 = i19;
                        z2 = z6;
                        z3 = z5;
                        view = j2;
                    } else {
                        i15 = i19;
                        z2 = z6;
                        z3 = z5;
                        view = j2;
                        z8 = true;
                        i16 = 1073741824;
                        if (mode2 == i16 && ((LinearLayout.LayoutParams) layoutParams).height == -1) {
                            z4 = true;
                            z9 = true;
                        } else {
                            z4 = false;
                        }
                        int i26 = ((LinearLayout.LayoutParams) layoutParams).topMargin + ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
                        measuredHeight = view.getMeasuredHeight() + i26;
                        i24 = View.combineMeasuredStates(i24, view.getMeasuredState());
                        if (z3 && (baseline2 = view.getBaseline()) != -1) {
                            i17 = ((LinearLayout.LayoutParams) layoutParams).gravity;
                            if (i17 < 0) {
                                i17 = this.mGravity;
                            }
                            int i27 = (((i17 & 112) >> 4) & (-2)) >> 1;
                            iArr2[i27] = Math.max(iArr2[i27], baseline2);
                            iArr[i27] = Math.max(iArr[i27], measuredHeight - baseline2);
                        }
                        i21 = Math.max(i21, measuredHeight);
                        z10 = !z10 && ((LinearLayout.LayoutParams) layoutParams).height == -1;
                        if (((LinearLayout.LayoutParams) layoutParams).weight <= 0.0f) {
                            if (!z4) {
                                i26 = measuredHeight;
                            }
                            i23 = Math.max(i23, i26);
                        } else {
                            int i28 = i23;
                            if (!z4) {
                                i26 = measuredHeight;
                            }
                            i22 = Math.max(i22, i26);
                            i23 = i28;
                        }
                        int i29 = i15;
                        g2 = g(view, i29) + i29;
                        f3 = f5;
                        int i30 = g2 + 1;
                        iArr3 = iArr;
                        z6 = z2;
                        z5 = z3;
                        i18 = i16;
                        i19 = i30;
                    }
                } else {
                    if (((LinearLayout.LayoutParams) layoutParams).width != 0 || f4 <= 0.0f) {
                        c2 = 65534;
                        i14 = Integer.MIN_VALUE;
                    } else {
                        c2 = 65534;
                        ((LinearLayout.LayoutParams) layoutParams).width = -2;
                        i14 = 0;
                    }
                    i15 = i19;
                    int i31 = i14;
                    z2 = z6;
                    z3 = z5;
                    n(j2, i15, i2, f5 == 0.0f ? this.mTotalLength : 0, i3, 0);
                    if (i31 != Integer.MIN_VALUE) {
                        ((LinearLayout.LayoutParams) layoutParams).width = i31;
                    }
                    int measuredWidth = j2.getMeasuredWidth();
                    if (z7) {
                        view = j2;
                        max3 = this.mTotalLength + ((LinearLayout.LayoutParams) layoutParams).leftMargin + measuredWidth + ((LinearLayout.LayoutParams) layoutParams).rightMargin + i(view);
                    } else {
                        view = j2;
                        int i32 = this.mTotalLength;
                        max3 = Math.max(i32, i32 + measuredWidth + ((LinearLayout.LayoutParams) layoutParams).leftMargin + ((LinearLayout.LayoutParams) layoutParams).rightMargin + i(view));
                    }
                    this.mTotalLength = max3;
                    if (z2) {
                        i20 = Math.max(measuredWidth, i20);
                    }
                }
                i16 = 1073741824;
                if (mode2 == i16) {
                }
                z4 = false;
                int i262 = ((LinearLayout.LayoutParams) layoutParams).topMargin + ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
                measuredHeight = view.getMeasuredHeight() + i262;
                i24 = View.combineMeasuredStates(i24, view.getMeasuredState());
                if (z3) {
                    i17 = ((LinearLayout.LayoutParams) layoutParams).gravity;
                    if (i17 < 0) {
                    }
                    int i272 = (((i17 & 112) >> 4) & (-2)) >> 1;
                    iArr2[i272] = Math.max(iArr2[i272], baseline2);
                    iArr[i272] = Math.max(iArr[i272], measuredHeight - baseline2);
                }
                i21 = Math.max(i21, measuredHeight);
                if (z10) {
                }
                if (((LinearLayout.LayoutParams) layoutParams).weight <= 0.0f) {
                }
                int i292 = i15;
                g2 = g(view, i292) + i292;
                f3 = f5;
                int i302 = g2 + 1;
                iArr3 = iArr;
                z6 = z2;
                z5 = z3;
                i18 = i16;
                i19 = i302;
            }
            z2 = z6;
            z3 = z5;
            int i33 = i18;
            g2 = i19;
            i16 = i33;
            int i3022 = g2 + 1;
            iArr3 = iArr;
            z6 = z2;
            z5 = z3;
            i18 = i16;
            i19 = i3022;
        }
        boolean z11 = z6;
        boolean z12 = z5;
        int i34 = i21;
        int i35 = i22;
        int i36 = i23;
        int i37 = i24;
        if (this.mTotalLength > 0 && k(virtualChildCount)) {
            this.mTotalLength += this.mDividerWidth;
        }
        if (iArr2[1] == -1 && iArr2[0] == -1 && iArr2[2] == -1 && iArr2[3] == -1) {
            max = i34;
            i4 = i37;
        } else {
            i4 = i37;
            max = Math.max(i34, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
        }
        if (z11 && (mode == Integer.MIN_VALUE || mode == 0)) {
            this.mTotalLength = 0;
            int i38 = 0;
            while (i38 < virtualChildCount) {
                View j3 = j(i38);
                if (j3 == null) {
                    this.mTotalLength += p(i38);
                } else if (j3.getVisibility() == 8) {
                    i38 += g(j3, i38);
                } else {
                    LayoutParams layoutParams2 = (LayoutParams) j3.getLayoutParams();
                    int i39 = this.mTotalLength;
                    if (z7) {
                        this.mTotalLength = i39 + ((LinearLayout.LayoutParams) layoutParams2).leftMargin + i20 + ((LinearLayout.LayoutParams) layoutParams2).rightMargin + i(j3);
                    } else {
                        i13 = max;
                        this.mTotalLength = Math.max(i39, i39 + i20 + ((LinearLayout.LayoutParams) layoutParams2).leftMargin + ((LinearLayout.LayoutParams) layoutParams2).rightMargin + i(j3));
                        i38++;
                        max = i13;
                    }
                }
                i13 = max;
                i38++;
                max = i13;
            }
        }
        int i40 = max;
        int paddingLeft = this.mTotalLength + getPaddingLeft() + getPaddingRight();
        this.mTotalLength = paddingLeft;
        int resolveSizeAndState = View.resolveSizeAndState(Math.max(paddingLeft, getSuggestedMinimumWidth()), i2, 0);
        int i41 = (16777215 & resolveSizeAndState) - this.mTotalLength;
        if (z8 || (i41 != 0 && f3 > 0.0f)) {
            float f6 = this.mWeightSum;
            if (f6 > 0.0f) {
                f3 = f6;
            }
            iArr2[3] = -1;
            iArr2[2] = -1;
            iArr2[1] = -1;
            iArr2[0] = -1;
            iArr[3] = -1;
            iArr[2] = -1;
            iArr[1] = -1;
            iArr[0] = -1;
            this.mTotalLength = 0;
            int i42 = i35;
            int i43 = -1;
            int i44 = i4;
            int i45 = 0;
            while (i45 < virtualChildCount) {
                View j4 = j(i45);
                if (j4 == null || j4.getVisibility() == 8) {
                    i8 = i41;
                    i9 = virtualChildCount;
                } else {
                    LayoutParams layoutParams3 = (LayoutParams) j4.getLayoutParams();
                    float f7 = ((LinearLayout.LayoutParams) layoutParams3).weight;
                    if (f7 > 0.0f) {
                        int i46 = (int) ((i41 * f7) / f3);
                        float f8 = f3 - f7;
                        int i47 = i41 - i46;
                        i9 = virtualChildCount;
                        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + ((LinearLayout.LayoutParams) layoutParams3).topMargin + ((LinearLayout.LayoutParams) layoutParams3).bottomMargin, ((LinearLayout.LayoutParams) layoutParams3).height);
                        if (((LinearLayout.LayoutParams) layoutParams3).width == 0) {
                            i12 = 1073741824;
                            if (mode == 1073741824) {
                            }
                        } else {
                            i12 = 1073741824;
                        }
                        i46 = j4.getMeasuredWidth() + i46;
                    } else {
                        i8 = i41;
                        i9 = virtualChildCount;
                    }
                    int i48 = this.mTotalLength;
                    if (z7) {
                        this.mTotalLength = i48 + j4.getMeasuredWidth() + ((LinearLayout.LayoutParams) layoutParams3).leftMargin + ((LinearLayout.LayoutParams) layoutParams3).rightMargin + i(j4);
                        f2 = f3;
                    } else {
                        f2 = f3;
                        this.mTotalLength = Math.max(i48, j4.getMeasuredWidth() + i48 + ((LinearLayout.LayoutParams) layoutParams3).leftMargin + ((LinearLayout.LayoutParams) layoutParams3).rightMargin + i(j4));
                    }
                    boolean z13 = mode2 != 1073741824 && ((LinearLayout.LayoutParams) layoutParams3).height == -1;
                    int i49 = ((LinearLayout.LayoutParams) layoutParams3).topMargin + ((LinearLayout.LayoutParams) layoutParams3).bottomMargin;
                    int measuredHeight2 = j4.getMeasuredHeight() + i49;
                    i43 = Math.max(i43, measuredHeight2);
                    if (!z13) {
                        i49 = measuredHeight2;
                    }
                    int max4 = Math.max(i42, i49);
                    if (z10) {
                        i10 = -1;
                        if (((LinearLayout.LayoutParams) layoutParams3).height == -1) {
                            z = true;
                            if (z12 && (baseline = j4.getBaseline()) != i10) {
                                i11 = ((LinearLayout.LayoutParams) layoutParams3).gravity;
                                if (i11 < 0) {
                                    i11 = this.mGravity;
                                }
                                int i50 = (((i11 & 112) >> 4) & (-2)) >> 1;
                                iArr2[i50] = Math.max(iArr2[i50], baseline);
                                iArr[i50] = Math.max(iArr[i50], measuredHeight2 - baseline);
                            }
                            z10 = z;
                            i42 = max4;
                            f3 = f2;
                        }
                    } else {
                        i10 = -1;
                    }
                    z = false;
                    if (z12) {
                        i11 = ((LinearLayout.LayoutParams) layoutParams3).gravity;
                        if (i11 < 0) {
                        }
                        int i502 = (((i11 & 112) >> 4) & (-2)) >> 1;
                        iArr2[i502] = Math.max(iArr2[i502], baseline);
                        iArr[i502] = Math.max(iArr[i502], measuredHeight2 - baseline);
                    }
                    z10 = z;
                    i42 = max4;
                    f3 = f2;
                }
                i45++;
                i41 = i8;
                virtualChildCount = i9;
            }
            i5 = i3;
            i6 = virtualChildCount;
            this.mTotalLength += getPaddingLeft() + getPaddingRight();
            max2 = (iArr2[1] == -1 && iArr2[0] == -1 && iArr2[2] == -1 && iArr2[3] == -1) ? i43 : Math.max(i43, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
            i7 = i42;
            i4 = i44;
        } else {
            i7 = Math.max(i35, i36);
            if (z11 && mode != 1073741824) {
                for (int i51 = 0; i51 < virtualChildCount; i51++) {
                    View j5 = j(i51);
                    if (j5 != null && j5.getVisibility() != 8 && ((LinearLayout.LayoutParams) ((LayoutParams) j5.getLayoutParams())).weight > 0.0f) {
                        j5.measure(View.MeasureSpec.makeMeasureSpec(i20, 1073741824), View.MeasureSpec.makeMeasureSpec(j5.getMeasuredHeight(), 1073741824));
                    }
                }
            }
            i5 = i3;
            i6 = virtualChildCount;
            max2 = i40;
        }
        if (z10 || mode2 == 1073741824) {
            i7 = max2;
        }
        setMeasuredDimension(resolveSizeAndState | (i4 & ViewCompat.MEASURED_STATE_MASK), View.resolveSizeAndState(Math.max(i7 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i5, i4 << 16));
        if (z9) {
            forceUniformHeight(i6, i2);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mDivider == null) {
            return;
        }
        if (this.mOrientation == 1) {
            b(canvas);
        } else {
            a(canvas);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(ACCESSIBILITY_CLASS_NAME);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(ACCESSIBILITY_CLASS_NAME);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (this.mOrientation == 1) {
            m(i2, i3, i4, i5);
        } else {
            l(i2, i3, i4, i5);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        if (this.mOrientation == 1) {
            q(i2, i3);
        } else {
            o(i2, i3);
        }
    }

    int p(int i2) {
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:131:0x02cf, code lost:
        if (r15 > 0) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x02da, code lost:
        if (r15 < 0) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x02dc, code lost:
        r15 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x02dd, code lost:
        r13.measure(r0, android.view.View.MeasureSpec.makeMeasureSpec(r15, r10));
        r1 = android.view.View.combineMeasuredStates(r1, r13.getMeasuredState() & androidx.core.view.InputDeviceCompat.SOURCE_ANY);
        r0 = r0;
     */
    /* JADX WARN: Removed duplicated region for block: B:148:0x031b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void q(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        boolean z;
        int i11;
        int max;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        View view;
        int max2;
        boolean z2;
        int max3;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int i20 = this.mBaselineAlignedChildIndex;
        boolean z3 = this.mUseLargestChild;
        int i21 = 0;
        int i22 = 0;
        int i23 = 0;
        int i24 = 0;
        int i25 = 0;
        int i26 = 0;
        boolean z4 = false;
        boolean z5 = false;
        float f2 = 0.0f;
        boolean z6 = true;
        while (true) {
            int i27 = 8;
            int i28 = i24;
            if (i26 >= virtualChildCount) {
                int i29 = i21;
                int i30 = i23;
                int i31 = i25;
                int i32 = virtualChildCount;
                int i33 = mode2;
                int i34 = i22;
                if (this.mTotalLength > 0) {
                    i4 = i32;
                    if (k(i4)) {
                        this.mTotalLength += this.mDividerHeight;
                    }
                } else {
                    i4 = i32;
                }
                if (z3 && (i33 == Integer.MIN_VALUE || i33 == 0)) {
                    this.mTotalLength = 0;
                    int i35 = 0;
                    while (i35 < i4) {
                        View j2 = j(i35);
                        if (j2 == null) {
                            max = this.mTotalLength + p(i35);
                        } else if (j2.getVisibility() == i27) {
                            i35 += g(j2, i35);
                            i35++;
                            i27 = 8;
                        } else {
                            LayoutParams layoutParams = (LayoutParams) j2.getLayoutParams();
                            int i36 = this.mTotalLength;
                            max = Math.max(i36, i36 + i30 + ((LinearLayout.LayoutParams) layoutParams).topMargin + ((LinearLayout.LayoutParams) layoutParams).bottomMargin + i(j2));
                        }
                        this.mTotalLength = max;
                        i35++;
                        i27 = 8;
                    }
                }
                int paddingTop = this.mTotalLength + getPaddingTop() + getPaddingBottom();
                this.mTotalLength = paddingTop;
                int resolveSizeAndState = View.resolveSizeAndState(Math.max(paddingTop, getSuggestedMinimumHeight()), i3, 0);
                int i37 = (16777215 & resolveSizeAndState) - this.mTotalLength;
                if (z4 || (i37 != 0 && f2 > 0.0f)) {
                    float f3 = this.mWeightSum;
                    if (f3 > 0.0f) {
                        f2 = f3;
                    }
                    this.mTotalLength = 0;
                    int i38 = i37;
                    int i39 = i31;
                    i5 = i29;
                    int i40 = 0;
                    while (i40 < i4) {
                        View j3 = j(i40);
                        if (j3.getVisibility() == 8) {
                            i8 = i38;
                        } else {
                            LayoutParams layoutParams2 = (LayoutParams) j3.getLayoutParams();
                            float f4 = ((LinearLayout.LayoutParams) layoutParams2).weight;
                            if (f4 > 0.0f) {
                                int i41 = (int) ((i38 * f4) / f2);
                                float f5 = f2 - f4;
                                i8 = i38 - i41;
                                int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + ((LinearLayout.LayoutParams) layoutParams2).leftMargin + ((LinearLayout.LayoutParams) layoutParams2).rightMargin, ((LinearLayout.LayoutParams) layoutParams2).width);
                                if (((LinearLayout.LayoutParams) layoutParams2).height == 0) {
                                    i11 = 1073741824;
                                    if (i33 == 1073741824) {
                                    }
                                } else {
                                    i11 = 1073741824;
                                }
                                i41 = j3.getMeasuredHeight() + i41;
                            } else {
                                i8 = i38;
                            }
                            int i42 = ((LinearLayout.LayoutParams) layoutParams2).leftMargin + ((LinearLayout.LayoutParams) layoutParams2).rightMargin;
                            int measuredWidth = j3.getMeasuredWidth() + i42;
                            i34 = Math.max(i34, measuredWidth);
                            float f6 = f2;
                            if (mode != 1073741824) {
                                i9 = i5;
                                i10 = -1;
                                if (((LinearLayout.LayoutParams) layoutParams2).width == -1) {
                                    z = true;
                                    if (!z) {
                                        i42 = measuredWidth;
                                    }
                                    int max4 = Math.max(i39, i42);
                                    boolean z7 = !z6 && ((LinearLayout.LayoutParams) layoutParams2).width == i10;
                                    int i43 = this.mTotalLength;
                                    this.mTotalLength = Math.max(i43, j3.getMeasuredHeight() + i43 + ((LinearLayout.LayoutParams) layoutParams2).topMargin + ((LinearLayout.LayoutParams) layoutParams2).bottomMargin + i(j3));
                                    z6 = z7;
                                    i5 = i9;
                                    i39 = max4;
                                    f2 = f6;
                                }
                            } else {
                                i9 = i5;
                                i10 = -1;
                            }
                            z = false;
                            if (!z) {
                            }
                            int max42 = Math.max(i39, i42);
                            if (z6) {
                            }
                            int i432 = this.mTotalLength;
                            this.mTotalLength = Math.max(i432, j3.getMeasuredHeight() + i432 + ((LinearLayout.LayoutParams) layoutParams2).topMargin + ((LinearLayout.LayoutParams) layoutParams2).bottomMargin + i(j3));
                            z6 = z7;
                            i5 = i9;
                            i39 = max42;
                            f2 = f6;
                        }
                        i40++;
                        i38 = i8;
                    }
                    i6 = i2;
                    this.mTotalLength += getPaddingTop() + getPaddingBottom();
                    i7 = i39;
                } else {
                    i7 = Math.max(i31, i28);
                    if (z3 && i33 != 1073741824) {
                        for (int i44 = 0; i44 < i4; i44++) {
                            View j4 = j(i44);
                            if (j4 != null && j4.getVisibility() != 8 && ((LinearLayout.LayoutParams) ((LayoutParams) j4.getLayoutParams())).weight > 0.0f) {
                                j4.measure(View.MeasureSpec.makeMeasureSpec(j4.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(i30, 1073741824));
                            }
                        }
                    }
                    i6 = i2;
                    i5 = i29;
                }
                if (z6 || mode == 1073741824) {
                    i7 = i34;
                }
                setMeasuredDimension(View.resolveSizeAndState(Math.max(i7 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i6, i5), resolveSizeAndState);
                if (z5) {
                    forceUniformWidth(i4, i3);
                    return;
                }
                return;
            }
            View j5 = j(i26);
            if (j5 == null) {
                this.mTotalLength += p(i26);
                i15 = virtualChildCount;
                i16 = mode2;
                i24 = i28;
            } else {
                int i45 = i21;
                if (j5.getVisibility() == 8) {
                    i26 += g(j5, i26);
                    i15 = virtualChildCount;
                    i24 = i28;
                    i21 = i45;
                    i16 = mode2;
                } else {
                    if (k(i26)) {
                        this.mTotalLength += this.mDividerHeight;
                    }
                    LayoutParams layoutParams3 = (LayoutParams) j5.getLayoutParams();
                    float f7 = ((LinearLayout.LayoutParams) layoutParams3).weight;
                    float f8 = f2 + f7;
                    if (mode2 == 1073741824 && ((LinearLayout.LayoutParams) layoutParams3).height == 0 && f7 > 0.0f) {
                        int i46 = this.mTotalLength;
                        this.mTotalLength = Math.max(i46, ((LinearLayout.LayoutParams) layoutParams3).topMargin + i46 + ((LinearLayout.LayoutParams) layoutParams3).bottomMargin);
                        max2 = i23;
                        view = j5;
                        i18 = i25;
                        z4 = true;
                        i13 = i45;
                        i14 = i22;
                        i15 = virtualChildCount;
                        i16 = mode2;
                        i17 = i28;
                        i19 = i26;
                    } else {
                        int i47 = i22;
                        if (((LinearLayout.LayoutParams) layoutParams3).height != 0 || f7 <= 0.0f) {
                            i12 = Integer.MIN_VALUE;
                        } else {
                            ((LinearLayout.LayoutParams) layoutParams3).height = -2;
                            i12 = 0;
                        }
                        i13 = i45;
                        int i48 = i12;
                        i14 = i47;
                        int i49 = i23;
                        i15 = virtualChildCount;
                        i16 = mode2;
                        i17 = i28;
                        i18 = i25;
                        i19 = i26;
                        n(j5, i26, i2, 0, i3, f8 == 0.0f ? this.mTotalLength : 0);
                        if (i48 != Integer.MIN_VALUE) {
                            ((LinearLayout.LayoutParams) layoutParams3).height = i48;
                        }
                        int measuredHeight = j5.getMeasuredHeight();
                        int i50 = this.mTotalLength;
                        view = j5;
                        this.mTotalLength = Math.max(i50, i50 + measuredHeight + ((LinearLayout.LayoutParams) layoutParams3).topMargin + ((LinearLayout.LayoutParams) layoutParams3).bottomMargin + i(view));
                        max2 = z3 ? Math.max(measuredHeight, i49) : i49;
                    }
                    if (i20 >= 0 && i20 == i19 + 1) {
                        this.mBaselineChildTop = this.mTotalLength;
                    }
                    if (i19 < i20 && ((LinearLayout.LayoutParams) layoutParams3).weight > 0.0f) {
                        throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                    }
                    if (mode == 1073741824 || ((LinearLayout.LayoutParams) layoutParams3).width != -1) {
                        z2 = false;
                    } else {
                        z2 = true;
                        z5 = true;
                    }
                    int i51 = ((LinearLayout.LayoutParams) layoutParams3).leftMargin + ((LinearLayout.LayoutParams) layoutParams3).rightMargin;
                    int measuredWidth2 = view.getMeasuredWidth() + i51;
                    int max5 = Math.max(i14, measuredWidth2);
                    int combineMeasuredStates = View.combineMeasuredStates(i13, view.getMeasuredState());
                    z6 = z6 && ((LinearLayout.LayoutParams) layoutParams3).width == -1;
                    if (((LinearLayout.LayoutParams) layoutParams3).weight > 0.0f) {
                        if (!z2) {
                            i51 = measuredWidth2;
                        }
                        i24 = Math.max(i17, i51);
                        max3 = i18;
                    } else {
                        if (!z2) {
                            i51 = measuredWidth2;
                        }
                        max3 = Math.max(i18, i51);
                        i24 = i17;
                    }
                    i23 = max2;
                    f2 = f8;
                    i25 = max3;
                    i21 = combineMeasuredStates;
                    i26 = g(view, i19) + i19;
                    i22 = max5;
                }
            }
            i26++;
            mode2 = i16;
            virtualChildCount = i15;
        }
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    public void setBaselineAlignedChildIndex(int i2) {
        if (i2 >= 0 && i2 < getChildCount()) {
            this.mBaselineAlignedChildIndex = i2;
            return;
        }
        throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.mDivider) {
            return;
        }
        this.mDivider = drawable;
        if (drawable != null) {
            this.mDividerWidth = drawable.getIntrinsicWidth();
            this.mDividerHeight = drawable.getIntrinsicHeight();
        } else {
            this.mDividerWidth = 0;
            this.mDividerHeight = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i2) {
        this.mDividerPadding = i2;
    }

    public void setGravity(int i2) {
        if (this.mGravity != i2) {
            if ((8388615 & i2) == 0) {
                i2 |= GravityCompat.START;
            }
            if ((i2 & 112) == 0) {
                i2 |= 48;
            }
            this.mGravity = i2;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i2) {
        int i3 = i2 & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        int i4 = this.mGravity;
        if ((8388615 & i4) != i3) {
            this.mGravity = i3 | ((-8388616) & i4);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.mUseLargestChild = z;
    }

    public void setOrientation(int i2) {
        if (this.mOrientation != i2) {
            this.mOrientation = i2;
            requestLayout();
        }
    }

    public void setShowDividers(int i2) {
        if (i2 != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = i2;
    }

    public void setVerticalGravity(int i2) {
        int i3 = i2 & 112;
        int i4 = this.mGravity;
        if ((i4 & 112) != i3) {
            this.mGravity = i3 | (i4 & (-113));
            requestLayout();
        }
    }

    public void setWeightSum(float f2) {
        this.mWeightSum = Math.max(0.0f, f2);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
