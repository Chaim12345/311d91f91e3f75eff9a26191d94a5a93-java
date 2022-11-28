package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
/* loaded from: classes.dex */
public class Flow extends VirtualLayout {
    public static final int HORIZONTAL_ALIGN_CENTER = 2;
    public static final int HORIZONTAL_ALIGN_END = 1;
    public static final int HORIZONTAL_ALIGN_START = 0;
    public static final int VERTICAL_ALIGN_BASELINE = 3;
    public static final int VERTICAL_ALIGN_BOTTOM = 1;
    public static final int VERTICAL_ALIGN_CENTER = 2;
    public static final int VERTICAL_ALIGN_TOP = 0;
    public static final int WRAP_ALIGNED = 2;
    public static final int WRAP_CHAIN = 1;
    public static final int WRAP_NONE = 0;
    private ConstraintWidget[] mDisplayedWidgets;
    private int mHorizontalStyle = -1;
    private int mVerticalStyle = -1;
    private int mFirstHorizontalStyle = -1;
    private int mFirstVerticalStyle = -1;
    private int mLastHorizontalStyle = -1;
    private int mLastVerticalStyle = -1;
    private float mHorizontalBias = 0.5f;
    private float mVerticalBias = 0.5f;
    private float mFirstHorizontalBias = 0.5f;
    private float mFirstVerticalBias = 0.5f;
    private float mLastHorizontalBias = 0.5f;
    private float mLastVerticalBias = 0.5f;
    private int mHorizontalGap = 0;
    private int mVerticalGap = 0;
    private int mHorizontalAlign = 2;
    private int mVerticalAlign = 2;
    private int mWrapMode = 0;
    private int mMaxElementsWrap = -1;
    private int mOrientation = 0;
    private ArrayList<WidgetsList> mChainList = new ArrayList<>();
    private ConstraintWidget[] mAlignedBiggestElementsInRows = null;
    private ConstraintWidget[] mAlignedBiggestElementsInCols = null;
    private int[] mAlignedDimensions = null;
    private int mDisplayedWidgetsCount = 0;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class WidgetsList {
        private ConstraintAnchor mBottom;
        private ConstraintAnchor mLeft;
        private int mMax;
        private int mOrientation;
        private int mPaddingBottom;
        private int mPaddingLeft;
        private int mPaddingRight;
        private int mPaddingTop;
        private ConstraintAnchor mRight;
        private ConstraintAnchor mTop;
        private ConstraintWidget biggest = null;

        /* renamed from: a  reason: collision with root package name */
        int f1971a = 0;
        private int mWidth = 0;
        private int mHeight = 0;
        private int mStartIndex = 0;
        private int mCount = 0;
        private int mNbMatchConstraintsWidgets = 0;

        public WidgetsList(int i2, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i3) {
            this.mOrientation = 0;
            this.mPaddingLeft = 0;
            this.mPaddingTop = 0;
            this.mPaddingRight = 0;
            this.mPaddingBottom = 0;
            this.mMax = 0;
            this.mOrientation = i2;
            this.mLeft = constraintAnchor;
            this.mTop = constraintAnchor2;
            this.mRight = constraintAnchor3;
            this.mBottom = constraintAnchor4;
            this.mPaddingLeft = Flow.this.getPaddingLeft();
            this.mPaddingTop = Flow.this.getPaddingTop();
            this.mPaddingRight = Flow.this.getPaddingRight();
            this.mPaddingBottom = Flow.this.getPaddingBottom();
            this.mMax = i3;
        }

        private void recomputeDimensions() {
            this.mWidth = 0;
            this.mHeight = 0;
            this.biggest = null;
            this.f1971a = 0;
            int i2 = this.mCount;
            for (int i3 = 0; i3 < i2 && this.mStartIndex + i3 < Flow.this.mDisplayedWidgetsCount; i3++) {
                ConstraintWidget constraintWidget = Flow.this.mDisplayedWidgets[this.mStartIndex + i3];
                if (this.mOrientation == 0) {
                    int width = constraintWidget.getWidth();
                    int i4 = Flow.this.mHorizontalGap;
                    if (constraintWidget.getVisibility() == 8) {
                        i4 = 0;
                    }
                    this.mWidth += width + i4;
                    int widgetHeight = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                    if (this.biggest == null || this.f1971a < widgetHeight) {
                        this.biggest = constraintWidget;
                        this.f1971a = widgetHeight;
                        this.mHeight = widgetHeight;
                    }
                } else {
                    int widgetWidth = Flow.this.getWidgetWidth(constraintWidget, this.mMax);
                    int widgetHeight2 = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                    int i5 = Flow.this.mVerticalGap;
                    if (constraintWidget.getVisibility() == 8) {
                        i5 = 0;
                    }
                    this.mHeight += widgetHeight2 + i5;
                    if (this.biggest == null || this.f1971a < widgetWidth) {
                        this.biggest = constraintWidget;
                        this.f1971a = widgetWidth;
                        this.mWidth = widgetWidth;
                    }
                }
            }
        }

        public void add(ConstraintWidget constraintWidget) {
            if (this.mOrientation == 0) {
                int widgetWidth = Flow.this.getWidgetWidth(constraintWidget, this.mMax);
                if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.mNbMatchConstraintsWidgets++;
                    widgetWidth = 0;
                }
                this.mWidth += widgetWidth + (constraintWidget.getVisibility() != 8 ? Flow.this.mHorizontalGap : 0);
                int widgetHeight = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                if (this.biggest == null || this.f1971a < widgetHeight) {
                    this.biggest = constraintWidget;
                    this.f1971a = widgetHeight;
                    this.mHeight = widgetHeight;
                }
            } else {
                int widgetWidth2 = Flow.this.getWidgetWidth(constraintWidget, this.mMax);
                int widgetHeight2 = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                if (constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.mNbMatchConstraintsWidgets++;
                    widgetHeight2 = 0;
                }
                this.mHeight += widgetHeight2 + (constraintWidget.getVisibility() != 8 ? Flow.this.mVerticalGap : 0);
                if (this.biggest == null || this.f1971a < widgetWidth2) {
                    this.biggest = constraintWidget;
                    this.f1971a = widgetWidth2;
                    this.mWidth = widgetWidth2;
                }
            }
            this.mCount++;
        }

        public void clear() {
            this.f1971a = 0;
            this.biggest = null;
            this.mWidth = 0;
            this.mHeight = 0;
            this.mStartIndex = 0;
            this.mCount = 0;
            this.mNbMatchConstraintsWidgets = 0;
        }

        /* JADX WARN: Removed duplicated region for block: B:147:0x0267  */
        /* JADX WARN: Removed duplicated region for block: B:159:0x02b5  */
        /* JADX WARN: Removed duplicated region for block: B:161:0x02c0  */
        /* JADX WARN: Removed duplicated region for block: B:168:0x02eb  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void createConstraints(boolean z, int i2, boolean z2) {
            ConstraintAnchor constraintAnchor;
            ConstraintAnchor constraintAnchor2;
            int i3;
            ConstraintWidget constraintWidget;
            ConstraintAnchor constraintAnchor3;
            ConstraintAnchor constraintAnchor4;
            ConstraintAnchor constraintAnchor5;
            ConstraintAnchor constraintAnchor6;
            ConstraintWidget constraintWidget2;
            char c2;
            ConstraintAnchor constraintAnchor7;
            ConstraintAnchor constraintAnchor8;
            float f2;
            float f3;
            int i4 = this.mCount;
            for (int i5 = 0; i5 < i4 && this.mStartIndex + i5 < Flow.this.mDisplayedWidgetsCount; i5++) {
                ConstraintWidget constraintWidget3 = Flow.this.mDisplayedWidgets[this.mStartIndex + i5];
                if (constraintWidget3 != null) {
                    constraintWidget3.resetAnchors();
                }
            }
            if (i4 == 0 || this.biggest == null) {
                return;
            }
            boolean z3 = z2 && i2 == 0;
            int i6 = -1;
            int i7 = -1;
            for (int i8 = 0; i8 < i4; i8++) {
                int i9 = z ? (i4 - 1) - i8 : i8;
                if (this.mStartIndex + i9 >= Flow.this.mDisplayedWidgetsCount) {
                    break;
                }
                if (Flow.this.mDisplayedWidgets[this.mStartIndex + i9].getVisibility() == 0) {
                    if (i6 == -1) {
                        i6 = i8;
                    }
                    i7 = i8;
                }
            }
            ConstraintWidget constraintWidget4 = null;
            if (this.mOrientation == 0) {
                ConstraintWidget constraintWidget5 = this.biggest;
                constraintWidget5.setVerticalChainStyle(Flow.this.mVerticalStyle);
                int i10 = this.mPaddingTop;
                if (i2 > 0) {
                    i10 += Flow.this.mVerticalGap;
                }
                constraintWidget5.mTop.connect(this.mTop, i10);
                if (z2) {
                    constraintWidget5.mBottom.connect(this.mBottom, this.mPaddingBottom);
                }
                if (i2 > 0) {
                    this.mTop.mOwner.mBottom.connect(constraintWidget5.mTop, 0);
                }
                if (Flow.this.mVerticalAlign == 3 && !constraintWidget5.hasBaseline()) {
                    for (int i11 = 0; i11 < i4; i11++) {
                        int i12 = z ? (i4 - 1) - i11 : i11;
                        if (this.mStartIndex + i12 >= Flow.this.mDisplayedWidgetsCount) {
                            break;
                        }
                        constraintWidget2 = Flow.this.mDisplayedWidgets[this.mStartIndex + i12];
                        if (constraintWidget2.hasBaseline()) {
                            break;
                        }
                    }
                }
                constraintWidget2 = constraintWidget5;
                int i13 = 0;
                while (i13 < i4) {
                    int i14 = z ? (i4 - 1) - i13 : i13;
                    if (this.mStartIndex + i14 >= Flow.this.mDisplayedWidgetsCount) {
                        return;
                    }
                    ConstraintWidget constraintWidget6 = Flow.this.mDisplayedWidgets[this.mStartIndex + i14];
                    if (i13 == 0) {
                        constraintWidget6.connect(constraintWidget6.mLeft, this.mLeft, this.mPaddingLeft);
                    }
                    if (i14 == 0) {
                        int i15 = Flow.this.mHorizontalStyle;
                        float f4 = Flow.this.mHorizontalBias;
                        if (z) {
                            f4 = 1.0f - f4;
                        }
                        if (this.mStartIndex == 0 && Flow.this.mFirstHorizontalStyle != -1) {
                            i15 = Flow.this.mFirstHorizontalStyle;
                            if (z) {
                                f3 = Flow.this.mFirstHorizontalBias;
                                f2 = 1.0f - f3;
                                f4 = f2;
                            } else {
                                f2 = Flow.this.mFirstHorizontalBias;
                                f4 = f2;
                            }
                        } else if (z2 && Flow.this.mLastHorizontalStyle != -1) {
                            i15 = Flow.this.mLastHorizontalStyle;
                            if (z) {
                                f3 = Flow.this.mLastHorizontalBias;
                                f2 = 1.0f - f3;
                                f4 = f2;
                            } else {
                                f2 = Flow.this.mLastHorizontalBias;
                                f4 = f2;
                            }
                        }
                        constraintWidget6.setHorizontalChainStyle(i15);
                        constraintWidget6.setHorizontalBiasPercent(f4);
                    }
                    if (i13 == i4 - 1) {
                        constraintWidget6.connect(constraintWidget6.mRight, this.mRight, this.mPaddingRight);
                    }
                    if (constraintWidget4 != null) {
                        constraintWidget6.mLeft.connect(constraintWidget4.mRight, Flow.this.mHorizontalGap);
                        if (i13 == i6) {
                            constraintWidget6.mLeft.setGoneMargin(this.mPaddingLeft);
                        }
                        constraintWidget4.mRight.connect(constraintWidget6.mLeft, 0);
                        if (i13 == i7 + 1) {
                            constraintWidget4.mRight.setGoneMargin(this.mPaddingRight);
                        }
                    }
                    if (constraintWidget6 != constraintWidget5) {
                        c2 = 3;
                        if (Flow.this.mVerticalAlign == 3 && constraintWidget2.hasBaseline() && constraintWidget6 != constraintWidget2 && constraintWidget6.hasBaseline()) {
                            constraintAnchor7 = constraintWidget6.mBaseline;
                            constraintAnchor8 = constraintWidget2.mBaseline;
                        } else {
                            int i16 = Flow.this.mVerticalAlign;
                            if (i16 != 0) {
                                if (i16 != 1) {
                                    ConstraintAnchor constraintAnchor9 = constraintWidget6.mTop;
                                    if (z3) {
                                        constraintAnchor9.connect(this.mTop, this.mPaddingTop);
                                        constraintWidget6.mBottom.connect(this.mBottom, this.mPaddingBottom);
                                    } else {
                                        constraintAnchor9.connect(constraintWidget5.mTop, 0);
                                    }
                                }
                                constraintAnchor7 = constraintWidget6.mBottom;
                                constraintAnchor8 = constraintWidget5.mBottom;
                            } else {
                                constraintAnchor7 = constraintWidget6.mTop;
                                constraintAnchor8 = constraintWidget5.mTop;
                            }
                        }
                        constraintAnchor7.connect(constraintAnchor8, 0);
                    } else {
                        c2 = 3;
                    }
                    i13++;
                    constraintWidget4 = constraintWidget6;
                }
                return;
            }
            ConstraintWidget constraintWidget7 = this.biggest;
            constraintWidget7.setHorizontalChainStyle(Flow.this.mHorizontalStyle);
            int i17 = this.mPaddingLeft;
            if (i2 > 0) {
                i17 += Flow.this.mHorizontalGap;
            }
            if (!z) {
                constraintWidget7.mLeft.connect(this.mLeft, i17);
                if (z2) {
                    constraintWidget7.mRight.connect(this.mRight, this.mPaddingRight);
                }
                if (i2 > 0) {
                    constraintAnchor = this.mLeft.mOwner.mRight;
                    constraintAnchor2 = constraintWidget7.mLeft;
                    constraintAnchor.connect(constraintAnchor2, 0);
                }
                i3 = 0;
                while (i3 < i4) {
                    constraintWidget = Flow.this.mDisplayedWidgets[this.mStartIndex + i3];
                    if (i3 == 0) {
                    }
                    if (i3 == i4 - 1) {
                    }
                    if (constraintWidget4 != null) {
                    }
                    if (constraintWidget != constraintWidget7) {
                    }
                    i3++;
                    constraintWidget4 = constraintWidget;
                }
                return;
            }
            constraintWidget7.mRight.connect(this.mRight, i17);
            if (z2) {
                constraintWidget7.mLeft.connect(this.mLeft, this.mPaddingRight);
            }
            if (i2 > 0) {
                constraintAnchor = this.mRight.mOwner.mLeft;
                constraintAnchor2 = constraintWidget7.mRight;
                constraintAnchor.connect(constraintAnchor2, 0);
            }
            i3 = 0;
            while (i3 < i4 && this.mStartIndex + i3 < Flow.this.mDisplayedWidgetsCount) {
                constraintWidget = Flow.this.mDisplayedWidgets[this.mStartIndex + i3];
                if (i3 == 0) {
                    constraintWidget.connect(constraintWidget.mTop, this.mTop, this.mPaddingTop);
                    int i18 = Flow.this.mVerticalStyle;
                    float f5 = Flow.this.mVerticalBias;
                    if (this.mStartIndex == 0 && Flow.this.mFirstVerticalStyle != -1) {
                        i18 = Flow.this.mFirstVerticalStyle;
                        f5 = Flow.this.mFirstVerticalBias;
                    } else if (z2 && Flow.this.mLastVerticalStyle != -1) {
                        i18 = Flow.this.mLastVerticalStyle;
                        f5 = Flow.this.mLastVerticalBias;
                    }
                    constraintWidget.setVerticalChainStyle(i18);
                    constraintWidget.setVerticalBiasPercent(f5);
                }
                if (i3 == i4 - 1) {
                    constraintWidget.connect(constraintWidget.mBottom, this.mBottom, this.mPaddingBottom);
                }
                if (constraintWidget4 != null) {
                    constraintWidget.mTop.connect(constraintWidget4.mBottom, Flow.this.mVerticalGap);
                    if (i3 == i6) {
                        constraintWidget.mTop.setGoneMargin(this.mPaddingTop);
                    }
                    constraintWidget4.mBottom.connect(constraintWidget.mTop, 0);
                    if (i3 == i7 + 1) {
                        constraintWidget4.mBottom.setGoneMargin(this.mPaddingBottom);
                    }
                }
                if (constraintWidget != constraintWidget7) {
                    int i19 = Flow.this.mHorizontalAlign;
                    if (z) {
                        if (i19 != 0) {
                            if (i19 == 1) {
                                constraintAnchor5 = constraintWidget.mLeft;
                                constraintAnchor6 = constraintWidget7.mLeft;
                                constraintAnchor5.connect(constraintAnchor6, 0);
                            } else if (i19 == 2) {
                                constraintWidget.mLeft.connect(constraintWidget7.mLeft, 0);
                            }
                        }
                        constraintAnchor5 = constraintWidget.mRight;
                        constraintAnchor6 = constraintWidget7.mRight;
                        constraintAnchor5.connect(constraintAnchor6, 0);
                    } else {
                        if (i19 != 0) {
                            if (i19 != 1) {
                                if (i19 == 2) {
                                    ConstraintAnchor constraintAnchor10 = constraintWidget.mLeft;
                                    if (z3) {
                                        constraintAnchor10.connect(this.mLeft, this.mPaddingLeft);
                                        constraintWidget.mRight.connect(this.mRight, this.mPaddingRight);
                                    } else {
                                        constraintAnchor10.connect(constraintWidget7.mLeft, 0);
                                    }
                                }
                                i3++;
                                constraintWidget4 = constraintWidget;
                            }
                            constraintAnchor3 = constraintWidget.mRight;
                            constraintAnchor4 = constraintWidget7.mRight;
                        } else {
                            constraintAnchor3 = constraintWidget.mLeft;
                            constraintAnchor4 = constraintWidget7.mLeft;
                        }
                        constraintAnchor3.connect(constraintAnchor4, 0);
                        i3++;
                        constraintWidget4 = constraintWidget;
                    }
                }
                i3++;
                constraintWidget4 = constraintWidget;
            }
        }

        public int getHeight() {
            return this.mOrientation == 1 ? this.mHeight - Flow.this.mVerticalGap : this.mHeight;
        }

        public int getWidth() {
            return this.mOrientation == 0 ? this.mWidth - Flow.this.mHorizontalGap : this.mWidth;
        }

        public void measureMatchConstraints(int i2) {
            Flow flow;
            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour;
            int width;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour;
            int i3;
            int i4 = this.mNbMatchConstraintsWidgets;
            if (i4 == 0) {
                return;
            }
            int i5 = this.mCount;
            int i6 = i2 / i4;
            for (int i7 = 0; i7 < i5 && this.mStartIndex + i7 < Flow.this.mDisplayedWidgetsCount; i7++) {
                ConstraintWidget constraintWidget = Flow.this.mDisplayedWidgets[this.mStartIndex + i7];
                if (this.mOrientation == 0) {
                    if (constraintWidget != null && constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultWidth == 0) {
                        flow = Flow.this;
                        horizontalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                        dimensionBehaviour = constraintWidget.getVerticalDimensionBehaviour();
                        i3 = constraintWidget.getHeight();
                        width = i6;
                        flow.e(constraintWidget, horizontalDimensionBehaviour, width, dimensionBehaviour, i3);
                    }
                } else {
                    if (constraintWidget != null && constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultHeight == 0) {
                        flow = Flow.this;
                        horizontalDimensionBehaviour = constraintWidget.getHorizontalDimensionBehaviour();
                        width = constraintWidget.getWidth();
                        dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                        i3 = i6;
                        flow.e(constraintWidget, horizontalDimensionBehaviour, width, dimensionBehaviour, i3);
                    }
                }
            }
            recomputeDimensions();
        }

        public void setStartIndex(int i2) {
            this.mStartIndex = i2;
        }

        public void setup(int i2, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i3, int i4, int i5, int i6, int i7) {
            this.mOrientation = i2;
            this.mLeft = constraintAnchor;
            this.mTop = constraintAnchor2;
            this.mRight = constraintAnchor3;
            this.mBottom = constraintAnchor4;
            this.mPaddingLeft = i3;
            this.mPaddingTop = i4;
            this.mPaddingRight = i5;
            this.mPaddingBottom = i6;
            this.mMax = i7;
        }
    }

    private void createAlignedConstraints(boolean z) {
        ConstraintWidget constraintWidget;
        float f2;
        int i2;
        if (this.mAlignedDimensions == null || this.mAlignedBiggestElementsInCols == null || this.mAlignedBiggestElementsInRows == null) {
            return;
        }
        for (int i3 = 0; i3 < this.mDisplayedWidgetsCount; i3++) {
            this.mDisplayedWidgets[i3].resetAnchors();
        }
        int[] iArr = this.mAlignedDimensions;
        int i4 = iArr[0];
        int i5 = iArr[1];
        ConstraintWidget constraintWidget2 = null;
        float f3 = this.mHorizontalBias;
        int i6 = 0;
        while (i6 < i4) {
            if (z) {
                i2 = (i4 - i6) - 1;
                f2 = 1.0f - this.mHorizontalBias;
            } else {
                f2 = f3;
                i2 = i6;
            }
            ConstraintWidget constraintWidget3 = this.mAlignedBiggestElementsInCols[i2];
            if (constraintWidget3 != null && constraintWidget3.getVisibility() != 8) {
                if (i6 == 0) {
                    constraintWidget3.connect(constraintWidget3.mLeft, this.mLeft, getPaddingLeft());
                    constraintWidget3.setHorizontalChainStyle(this.mHorizontalStyle);
                    constraintWidget3.setHorizontalBiasPercent(f2);
                }
                if (i6 == i4 - 1) {
                    constraintWidget3.connect(constraintWidget3.mRight, this.mRight, getPaddingRight());
                }
                if (i6 > 0 && constraintWidget2 != null) {
                    constraintWidget3.connect(constraintWidget3.mLeft, constraintWidget2.mRight, this.mHorizontalGap);
                    constraintWidget2.connect(constraintWidget2.mRight, constraintWidget3.mLeft, 0);
                }
                constraintWidget2 = constraintWidget3;
            }
            i6++;
            f3 = f2;
        }
        for (int i7 = 0; i7 < i5; i7++) {
            ConstraintWidget constraintWidget4 = this.mAlignedBiggestElementsInRows[i7];
            if (constraintWidget4 != null && constraintWidget4.getVisibility() != 8) {
                if (i7 == 0) {
                    constraintWidget4.connect(constraintWidget4.mTop, this.mTop, getPaddingTop());
                    constraintWidget4.setVerticalChainStyle(this.mVerticalStyle);
                    constraintWidget4.setVerticalBiasPercent(this.mVerticalBias);
                }
                if (i7 == i5 - 1) {
                    constraintWidget4.connect(constraintWidget4.mBottom, this.mBottom, getPaddingBottom());
                }
                if (i7 > 0 && constraintWidget2 != null) {
                    constraintWidget4.connect(constraintWidget4.mTop, constraintWidget2.mBottom, this.mVerticalGap);
                    constraintWidget2.connect(constraintWidget2.mBottom, constraintWidget4.mTop, 0);
                }
                constraintWidget2 = constraintWidget4;
            }
        }
        for (int i8 = 0; i8 < i4; i8++) {
            for (int i9 = 0; i9 < i5; i9++) {
                int i10 = (i9 * i4) + i8;
                if (this.mOrientation == 1) {
                    i10 = (i8 * i5) + i9;
                }
                ConstraintWidget[] constraintWidgetArr = this.mDisplayedWidgets;
                if (i10 < constraintWidgetArr.length && (constraintWidget = constraintWidgetArr[i10]) != null && constraintWidget.getVisibility() != 8) {
                    ConstraintWidget constraintWidget5 = this.mAlignedBiggestElementsInCols[i8];
                    ConstraintWidget constraintWidget6 = this.mAlignedBiggestElementsInRows[i9];
                    if (constraintWidget != constraintWidget5) {
                        constraintWidget.connect(constraintWidget.mLeft, constraintWidget5.mLeft, 0);
                        constraintWidget.connect(constraintWidget.mRight, constraintWidget5.mRight, 0);
                    }
                    if (constraintWidget != constraintWidget6) {
                        constraintWidget.connect(constraintWidget.mTop, constraintWidget6.mTop, 0);
                        constraintWidget.connect(constraintWidget.mBottom, constraintWidget6.mBottom, 0);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getWidgetHeight(ConstraintWidget constraintWidget, int i2) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i3 = constraintWidget.mMatchConstraintDefaultHeight;
            if (i3 == 0) {
                return 0;
            }
            if (i3 == 2) {
                int i4 = (int) (constraintWidget.mMatchConstraintPercentHeight * i2);
                if (i4 != constraintWidget.getHeight()) {
                    constraintWidget.setMeasureRequested(true);
                    e(constraintWidget, constraintWidget.getHorizontalDimensionBehaviour(), constraintWidget.getWidth(), ConstraintWidget.DimensionBehaviour.FIXED, i4);
                }
                return i4;
            } else if (i3 == 1) {
                return constraintWidget.getHeight();
            } else {
                if (i3 == 3) {
                    return (int) ((constraintWidget.getWidth() * constraintWidget.mDimensionRatio) + 0.5f);
                }
            }
        }
        return constraintWidget.getHeight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getWidgetWidth(ConstraintWidget constraintWidget, int i2) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i3 = constraintWidget.mMatchConstraintDefaultWidth;
            if (i3 == 0) {
                return 0;
            }
            if (i3 == 2) {
                int i4 = (int) (constraintWidget.mMatchConstraintPercentWidth * i2);
                if (i4 != constraintWidget.getWidth()) {
                    constraintWidget.setMeasureRequested(true);
                    e(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i4, constraintWidget.getVerticalDimensionBehaviour(), constraintWidget.getHeight());
                }
                return i4;
            } else if (i3 == 1) {
                return constraintWidget.getWidth();
            } else {
                if (i3 == 3) {
                    return (int) ((constraintWidget.getHeight() * constraintWidget.mDimensionRatio) + 0.5f);
                }
            }
        }
        return constraintWidget.getWidth();
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0066  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:103:0x0119 -> B:40:0x0061). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:104:0x011b -> B:40:0x0061). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:106:0x0121 -> B:40:0x0061). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:107:0x0123 -> B:40:0x0061). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void measureAligned(ConstraintWidget[] constraintWidgetArr, int i2, int i3, int i4, int[] iArr) {
        int i5;
        boolean z;
        ConstraintWidget constraintWidget;
        int i6 = this.mMaxElementsWrap;
        if (i3 == 0) {
            if (i6 <= 0) {
                i6 = 0;
                int i7 = 0;
                for (int i8 = 0; i8 < i2; i8++) {
                    if (i8 > 0) {
                        i7 += this.mHorizontalGap;
                    }
                    ConstraintWidget constraintWidget2 = constraintWidgetArr[i8];
                    if (constraintWidget2 != null) {
                        i7 += getWidgetWidth(constraintWidget2, i4);
                        if (i7 > i4) {
                            break;
                        }
                        i6++;
                    }
                }
            }
            i5 = i6;
            i6 = 0;
        } else {
            if (i6 <= 0) {
                i6 = 0;
                int i9 = 0;
                for (int i10 = 0; i10 < i2; i10++) {
                    if (i10 > 0) {
                        i9 += this.mVerticalGap;
                    }
                    ConstraintWidget constraintWidget3 = constraintWidgetArr[i10];
                    if (constraintWidget3 != null) {
                        i9 += getWidgetHeight(constraintWidget3, i4);
                        if (i9 > i4) {
                            break;
                        }
                        i6++;
                    }
                }
            }
            i5 = 0;
        }
        if (this.mAlignedDimensions == null) {
            this.mAlignedDimensions = new int[2];
        }
        if ((i6 != 0 || i3 != 1) && (i5 != 0 || i3 != 0)) {
            z = false;
            while (!z) {
                if (i3 == 0) {
                    i6 = (int) Math.ceil(i2 / i5);
                } else {
                    i5 = (int) Math.ceil(i2 / i6);
                }
                ConstraintWidget[] constraintWidgetArr2 = this.mAlignedBiggestElementsInCols;
                if (constraintWidgetArr2 == null || constraintWidgetArr2.length < i5) {
                    this.mAlignedBiggestElementsInCols = new ConstraintWidget[i5];
                } else {
                    Arrays.fill(constraintWidgetArr2, (Object) null);
                }
                ConstraintWidget[] constraintWidgetArr3 = this.mAlignedBiggestElementsInRows;
                if (constraintWidgetArr3 == null || constraintWidgetArr3.length < i6) {
                    this.mAlignedBiggestElementsInRows = new ConstraintWidget[i6];
                } else {
                    Arrays.fill(constraintWidgetArr3, (Object) null);
                }
                for (int i11 = 0; i11 < i5; i11++) {
                    for (int i12 = 0; i12 < i6; i12++) {
                        int i13 = (i12 * i5) + i11;
                        if (i3 == 1) {
                            i13 = (i11 * i6) + i12;
                        }
                        if (i13 < constraintWidgetArr.length && (constraintWidget = constraintWidgetArr[i13]) != null) {
                            int widgetWidth = getWidgetWidth(constraintWidget, i4);
                            ConstraintWidget[] constraintWidgetArr4 = this.mAlignedBiggestElementsInCols;
                            if (constraintWidgetArr4[i11] == null || constraintWidgetArr4[i11].getWidth() < widgetWidth) {
                                this.mAlignedBiggestElementsInCols[i11] = constraintWidget;
                            }
                            int widgetHeight = getWidgetHeight(constraintWidget, i4);
                            ConstraintWidget[] constraintWidgetArr5 = this.mAlignedBiggestElementsInRows;
                            if (constraintWidgetArr5[i12] == null || constraintWidgetArr5[i12].getHeight() < widgetHeight) {
                                this.mAlignedBiggestElementsInRows[i12] = constraintWidget;
                            }
                        }
                    }
                }
                int i14 = 0;
                for (int i15 = 0; i15 < i5; i15++) {
                    ConstraintWidget constraintWidget4 = this.mAlignedBiggestElementsInCols[i15];
                    if (constraintWidget4 != null) {
                        if (i15 > 0) {
                            i14 += this.mHorizontalGap;
                        }
                        i14 += getWidgetWidth(constraintWidget4, i4);
                    }
                }
                int i16 = 0;
                for (int i17 = 0; i17 < i6; i17++) {
                    ConstraintWidget constraintWidget5 = this.mAlignedBiggestElementsInRows[i17];
                    if (constraintWidget5 != null) {
                        if (i17 > 0) {
                            i16 += this.mVerticalGap;
                        }
                        i16 += getWidgetHeight(constraintWidget5, i4);
                    }
                }
                iArr[0] = i14;
                iArr[1] = i16;
                if (i3 != 0) {
                    if (i16 > i4 && i6 > 1) {
                        i6--;
                    }
                } else if (i14 > i4 && i5 > 1) {
                    i5--;
                }
                while (!z) {
                }
            }
            int[] iArr2 = this.mAlignedDimensions;
            iArr2[0] = i5;
            iArr2[1] = i6;
        }
        z = true;
        while (!z) {
        }
        int[] iArr22 = this.mAlignedDimensions;
        iArr22[0] = i5;
        iArr22[1] = i6;
    }

    private void measureChainWrap(ConstraintWidget[] constraintWidgetArr, int i2, int i3, int i4, int[] iArr) {
        int i5;
        int i6;
        ConstraintAnchor constraintAnchor;
        int paddingRight;
        ConstraintAnchor constraintAnchor2;
        int paddingBottom;
        int i7;
        if (i2 == 0) {
            return;
        }
        this.mChainList.clear();
        WidgetsList widgetsList = new WidgetsList(i3, this.mLeft, this.mTop, this.mRight, this.mBottom, i4);
        this.mChainList.add(widgetsList);
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        if (i3 == 0) {
            while (i10 < i2) {
                ConstraintWidget constraintWidget = constraintWidgetArr[i10];
                int widgetWidth = getWidgetWidth(constraintWidget, i4);
                if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i8++;
                }
                int i11 = i8;
                boolean z = (i9 == i4 || (this.mHorizontalGap + i9) + widgetWidth > i4) && widgetsList.biggest != null;
                if (!z && i10 > 0 && (i7 = this.mMaxElementsWrap) > 0 && i10 % i7 == 0) {
                    z = true;
                }
                if (z) {
                    widgetsList = new WidgetsList(i3, this.mLeft, this.mTop, this.mRight, this.mBottom, i4);
                    widgetsList.setStartIndex(i10);
                    this.mChainList.add(widgetsList);
                } else if (i10 > 0) {
                    i9 += this.mHorizontalGap + widgetWidth;
                    widgetsList.add(constraintWidget);
                    i10++;
                    i8 = i11;
                }
                i9 = widgetWidth;
                widgetsList.add(constraintWidget);
                i10++;
                i8 = i11;
            }
        } else {
            while (i10 < i2) {
                ConstraintWidget constraintWidget2 = constraintWidgetArr[i10];
                int widgetHeight = getWidgetHeight(constraintWidget2, i4);
                if (constraintWidget2.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i8++;
                }
                int i12 = i8;
                boolean z2 = (i9 == i4 || (this.mVerticalGap + i9) + widgetHeight > i4) && widgetsList.biggest != null;
                if (!z2 && i10 > 0 && (i5 = this.mMaxElementsWrap) > 0 && i10 % i5 == 0) {
                    z2 = true;
                }
                if (z2) {
                    widgetsList = new WidgetsList(i3, this.mLeft, this.mTop, this.mRight, this.mBottom, i4);
                    widgetsList.setStartIndex(i10);
                    this.mChainList.add(widgetsList);
                } else if (i10 > 0) {
                    i9 += this.mVerticalGap + widgetHeight;
                    widgetsList.add(constraintWidget2);
                    i10++;
                    i8 = i12;
                }
                i9 = widgetHeight;
                widgetsList.add(constraintWidget2);
                i10++;
                i8 = i12;
            }
        }
        int size = this.mChainList.size();
        ConstraintAnchor constraintAnchor3 = this.mLeft;
        ConstraintAnchor constraintAnchor4 = this.mTop;
        ConstraintAnchor constraintAnchor5 = this.mRight;
        ConstraintAnchor constraintAnchor6 = this.mBottom;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight2 = getPaddingRight();
        int paddingBottom2 = getPaddingBottom();
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        boolean z3 = horizontalDimensionBehaviour == dimensionBehaviour || getVerticalDimensionBehaviour() == dimensionBehaviour;
        if (i8 > 0 && z3) {
            for (int i13 = 0; i13 < size; i13++) {
                WidgetsList widgetsList2 = this.mChainList.get(i13);
                widgetsList2.measureMatchConstraints(i4 - (i3 == 0 ? widgetsList2.getWidth() : widgetsList2.getHeight()));
            }
        }
        int i14 = paddingTop;
        int i15 = paddingRight2;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = paddingLeft;
        ConstraintAnchor constraintAnchor7 = constraintAnchor4;
        ConstraintAnchor constraintAnchor8 = constraintAnchor3;
        int i20 = paddingBottom2;
        while (i18 < size) {
            WidgetsList widgetsList3 = this.mChainList.get(i18);
            if (i3 == 0) {
                if (i18 < size - 1) {
                    constraintAnchor2 = this.mChainList.get(i18 + 1).biggest.mTop;
                    paddingBottom = 0;
                } else {
                    constraintAnchor2 = this.mBottom;
                    paddingBottom = getPaddingBottom();
                }
                ConstraintAnchor constraintAnchor9 = widgetsList3.biggest.mBottom;
                ConstraintAnchor constraintAnchor10 = constraintAnchor8;
                ConstraintAnchor constraintAnchor11 = constraintAnchor8;
                int i21 = i16;
                ConstraintAnchor constraintAnchor12 = constraintAnchor7;
                int i22 = i17;
                ConstraintAnchor constraintAnchor13 = constraintAnchor5;
                ConstraintAnchor constraintAnchor14 = constraintAnchor5;
                i6 = i18;
                widgetsList3.setup(i3, constraintAnchor10, constraintAnchor12, constraintAnchor13, constraintAnchor2, i19, i14, i15, paddingBottom, i4);
                int max = Math.max(i22, widgetsList3.getWidth());
                i16 = i21 + widgetsList3.getHeight();
                if (i6 > 0) {
                    i16 += this.mVerticalGap;
                }
                constraintAnchor8 = constraintAnchor11;
                i17 = max;
                i14 = 0;
                constraintAnchor7 = constraintAnchor9;
                constraintAnchor = constraintAnchor14;
                int i23 = paddingBottom;
                constraintAnchor6 = constraintAnchor2;
                i20 = i23;
            } else {
                ConstraintAnchor constraintAnchor15 = constraintAnchor8;
                int i24 = i16;
                int i25 = i17;
                i6 = i18;
                if (i6 < size - 1) {
                    constraintAnchor = this.mChainList.get(i6 + 1).biggest.mLeft;
                    paddingRight = 0;
                } else {
                    constraintAnchor = this.mRight;
                    paddingRight = getPaddingRight();
                }
                ConstraintAnchor constraintAnchor16 = widgetsList3.biggest.mRight;
                widgetsList3.setup(i3, constraintAnchor15, constraintAnchor7, constraintAnchor, constraintAnchor6, i19, i14, paddingRight, i20, i4);
                i17 = i25 + widgetsList3.getWidth();
                int max2 = Math.max(i24, widgetsList3.getHeight());
                if (i6 > 0) {
                    i17 += this.mHorizontalGap;
                }
                i16 = max2;
                i19 = 0;
                i15 = paddingRight;
                constraintAnchor8 = constraintAnchor16;
            }
            i18 = i6 + 1;
            constraintAnchor5 = constraintAnchor;
        }
        iArr[0] = i17;
        iArr[1] = i16;
    }

    private void measureNoWrap(ConstraintWidget[] constraintWidgetArr, int i2, int i3, int i4, int[] iArr) {
        WidgetsList widgetsList;
        if (i2 == 0) {
            return;
        }
        if (this.mChainList.size() == 0) {
            widgetsList = new WidgetsList(i3, this.mLeft, this.mTop, this.mRight, this.mBottom, i4);
            this.mChainList.add(widgetsList);
        } else {
            WidgetsList widgetsList2 = this.mChainList.get(0);
            widgetsList2.clear();
            widgetsList = widgetsList2;
            widgetsList.setup(i3, this.mLeft, this.mTop, this.mRight, this.mBottom, getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom(), i4);
        }
        for (int i5 = 0; i5 < i2; i5++) {
            widgetsList.add(constraintWidgetArr[i5]);
        }
        iArr[0] = widgetsList.getWidth();
        iArr[1] = widgetsList.getHeight();
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void addToSolver(LinearSystem linearSystem, boolean z) {
        super.addToSolver(linearSystem, z);
        boolean z2 = getParent() != null && ((ConstraintWidgetContainer) getParent()).isRtl();
        int i2 = this.mWrapMode;
        if (i2 != 0) {
            if (i2 == 1) {
                int size = this.mChainList.size();
                int i3 = 0;
                while (i3 < size) {
                    this.mChainList.get(i3).createConstraints(z2, i3, i3 == size + (-1));
                    i3++;
                }
            } else if (i2 == 2) {
                createAlignedConstraints(z2);
            }
        } else if (this.mChainList.size() > 0) {
            this.mChainList.get(0).createConstraints(z2, 0, true);
        }
        g(false);
    }

    @Override // androidx.constraintlayout.core.widgets.HelperWidget, androidx.constraintlayout.core.widgets.ConstraintWidget
    public void copy(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> hashMap) {
        super.copy(constraintWidget, hashMap);
        Flow flow = (Flow) constraintWidget;
        this.mHorizontalStyle = flow.mHorizontalStyle;
        this.mVerticalStyle = flow.mVerticalStyle;
        this.mFirstHorizontalStyle = flow.mFirstHorizontalStyle;
        this.mFirstVerticalStyle = flow.mFirstVerticalStyle;
        this.mLastHorizontalStyle = flow.mLastHorizontalStyle;
        this.mLastVerticalStyle = flow.mLastVerticalStyle;
        this.mHorizontalBias = flow.mHorizontalBias;
        this.mVerticalBias = flow.mVerticalBias;
        this.mFirstHorizontalBias = flow.mFirstHorizontalBias;
        this.mFirstVerticalBias = flow.mFirstVerticalBias;
        this.mLastHorizontalBias = flow.mLastHorizontalBias;
        this.mLastVerticalBias = flow.mLastVerticalBias;
        this.mHorizontalGap = flow.mHorizontalGap;
        this.mVerticalGap = flow.mVerticalGap;
        this.mHorizontalAlign = flow.mHorizontalAlign;
        this.mVerticalAlign = flow.mVerticalAlign;
        this.mWrapMode = flow.mWrapMode;
        this.mMaxElementsWrap = flow.mMaxElementsWrap;
        this.mOrientation = flow.mOrientation;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0047, code lost:
        if (r18.mVerticalStyle == (-1)) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0052, code lost:
        if (r18.mVerticalStyle == (-1)) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0054, code lost:
        r18.mVerticalStyle = 0;
     */
    @Override // androidx.constraintlayout.core.widgets.VirtualLayout
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void measure(int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int[] iArr;
        boolean z;
        if (this.mWidgetsCount > 0 && !f()) {
            setMeasure(0, 0);
            g(false);
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int[] iArr2 = new int[2];
        int i8 = (i3 - paddingLeft) - paddingRight;
        int i9 = this.mOrientation;
        if (i9 == 1) {
            i8 = (i5 - paddingTop) - paddingBottom;
        }
        int i10 = i8;
        if (i9 == 0) {
            if (this.mHorizontalStyle == -1) {
                this.mHorizontalStyle = 0;
            }
        } else if (this.mHorizontalStyle == -1) {
            this.mHorizontalStyle = 0;
        }
        ConstraintWidget[] constraintWidgetArr = this.mWidgets;
        int i11 = 0;
        int i12 = 0;
        while (true) {
            i6 = this.mWidgetsCount;
            if (i11 >= i6) {
                break;
            }
            if (this.mWidgets[i11].getVisibility() == 8) {
                i12++;
            }
            i11++;
        }
        if (i12 > 0) {
            constraintWidgetArr = new ConstraintWidget[i6 - i12];
            int i13 = 0;
            for (int i14 = 0; i14 < this.mWidgetsCount; i14++) {
                ConstraintWidget constraintWidget = this.mWidgets[i14];
                if (constraintWidget.getVisibility() != 8) {
                    constraintWidgetArr[i13] = constraintWidget;
                    i13++;
                }
            }
            i7 = i13;
        } else {
            i7 = i6;
        }
        this.mDisplayedWidgets = constraintWidgetArr;
        this.mDisplayedWidgetsCount = i7;
        int i15 = this.mWrapMode;
        if (i15 == 0) {
            iArr = iArr2;
            z = true;
            measureNoWrap(constraintWidgetArr, i7, this.mOrientation, i10, iArr2);
        } else if (i15 == 1) {
            z = true;
            iArr = iArr2;
            measureChainWrap(constraintWidgetArr, i7, this.mOrientation, i10, iArr2);
        } else if (i15 != 2) {
            z = true;
            iArr = iArr2;
        } else {
            z = true;
            iArr = iArr2;
            measureAligned(constraintWidgetArr, i7, this.mOrientation, i10, iArr2);
        }
        int i16 = iArr[0] + paddingLeft + paddingRight;
        int i17 = iArr[z ? 1 : 0] + paddingTop + paddingBottom;
        if (i2 == 1073741824) {
            i16 = i3;
        } else if (i2 == Integer.MIN_VALUE) {
            i16 = Math.min(i16, i3);
        } else if (i2 != 0) {
            i16 = 0;
        }
        if (i4 == 1073741824) {
            i17 = i5;
        } else if (i4 == Integer.MIN_VALUE) {
            i17 = Math.min(i17, i5);
        } else if (i4 != 0) {
            i17 = 0;
        }
        setMeasure(i16, i17);
        setWidth(i16);
        setHeight(i17);
        if (this.mWidgetsCount <= 0) {
            z = false;
        }
        g(z);
    }

    public void setFirstHorizontalBias(float f2) {
        this.mFirstHorizontalBias = f2;
    }

    public void setFirstHorizontalStyle(int i2) {
        this.mFirstHorizontalStyle = i2;
    }

    public void setFirstVerticalBias(float f2) {
        this.mFirstVerticalBias = f2;
    }

    public void setFirstVerticalStyle(int i2) {
        this.mFirstVerticalStyle = i2;
    }

    public void setHorizontalAlign(int i2) {
        this.mHorizontalAlign = i2;
    }

    public void setHorizontalBias(float f2) {
        this.mHorizontalBias = f2;
    }

    public void setHorizontalGap(int i2) {
        this.mHorizontalGap = i2;
    }

    public void setHorizontalStyle(int i2) {
        this.mHorizontalStyle = i2;
    }

    public void setLastHorizontalBias(float f2) {
        this.mLastHorizontalBias = f2;
    }

    public void setLastHorizontalStyle(int i2) {
        this.mLastHorizontalStyle = i2;
    }

    public void setLastVerticalBias(float f2) {
        this.mLastVerticalBias = f2;
    }

    public void setLastVerticalStyle(int i2) {
        this.mLastVerticalStyle = i2;
    }

    public void setMaxElementsWrap(int i2) {
        this.mMaxElementsWrap = i2;
    }

    public void setOrientation(int i2) {
        this.mOrientation = i2;
    }

    public void setVerticalAlign(int i2) {
        this.mVerticalAlign = i2;
    }

    public void setVerticalBias(float f2) {
        this.mVerticalBias = f2;
    }

    public void setVerticalGap(int i2) {
        this.mVerticalGap = i2;
    }

    public void setVerticalStyle(int i2) {
        this.mVerticalStyle = i2;
    }

    public void setWrapMode(int i2) {
        this.mWrapMode = i2;
    }
}