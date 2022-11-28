package androidx.constraintlayout.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.Metrics;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Optimizer;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.internal.view.SupportMenu;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: classes.dex */
public class ConstraintLayout extends ViewGroup {
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_DRAW_CONSTRAINTS = false;
    public static final int DESIGN_INFO_ID = 0;
    private static final boolean MEASURE = false;
    private static final boolean OPTIMIZE_HEIGHT_CHANGE = false;
    private static final String TAG = "ConstraintLayout";
    private static final boolean USE_CONSTRAINTS_HELPER = true;
    public static final String VERSION = "ConstraintLayout-2.1.1";
    private static SharedValues sSharedValues;

    /* renamed from: a  reason: collision with root package name */
    SparseArray f2247a;

    /* renamed from: b  reason: collision with root package name */
    protected ConstraintWidgetContainer f2248b;

    /* renamed from: c  reason: collision with root package name */
    protected boolean f2249c;

    /* renamed from: d  reason: collision with root package name */
    protected ConstraintLayoutStates f2250d;

    /* renamed from: e  reason: collision with root package name */
    Measurer f2251e;
    private ArrayList<ConstraintHelper> mConstraintHelpers;
    private ConstraintSet mConstraintSet;
    private int mConstraintSetId;
    private ConstraintsChangedListener mConstraintsChangedListener;
    private HashMap<String, Integer> mDesignIds;
    private int mLastMeasureHeight;
    private int mLastMeasureWidth;
    private int mMaxHeight;
    private int mMaxWidth;
    private Metrics mMetrics;
    private int mMinHeight;
    private int mMinWidth;
    private int mOnMeasureHeightMeasureSpec;
    private int mOnMeasureWidthMeasureSpec;
    private int mOptimizationLevel;
    private SparseArray<ConstraintWidget> mTempMapIdToWidget;

    /* renamed from: androidx.constraintlayout.widget.ConstraintLayout$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f2252a;

        static {
            int[] iArr = new int[ConstraintWidget.DimensionBehaviour.values().length];
            f2252a = iArr;
            try {
                iArr[ConstraintWidget.DimensionBehaviour.FIXED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2252a[ConstraintWidget.DimensionBehaviour.WRAP_CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2252a[ConstraintWidget.DimensionBehaviour.MATCH_PARENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2252a[ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public static final int BASELINE = 5;
        public static final int BOTTOM = 4;
        public static final int CHAIN_PACKED = 2;
        public static final int CHAIN_SPREAD = 0;
        public static final int CHAIN_SPREAD_INSIDE = 1;
        public static final int CIRCLE = 8;
        public static final int END = 7;
        public static final int GONE_UNSET = Integer.MIN_VALUE;
        public static final int HORIZONTAL = 0;
        public static final int LEFT = 1;
        public static final int MATCH_CONSTRAINT = 0;
        public static final int MATCH_CONSTRAINT_PERCENT = 2;
        public static final int MATCH_CONSTRAINT_SPREAD = 0;
        public static final int MATCH_CONSTRAINT_WRAP = 1;
        public static final int PARENT_ID = 0;
        public static final int RIGHT = 2;
        public static final int START = 6;
        public static final int TOP = 3;
        public static final int UNSET = -1;
        public static final int VERTICAL = 1;
        public static final int WRAP_BEHAVIOR_HORIZONTAL_ONLY = 1;
        public static final int WRAP_BEHAVIOR_INCLUDED = 0;
        public static final int WRAP_BEHAVIOR_SKIPPED = 3;
        public static final int WRAP_BEHAVIOR_VERTICAL_ONLY = 2;

        /* renamed from: a  reason: collision with root package name */
        boolean f2253a;

        /* renamed from: b  reason: collision with root package name */
        boolean f2254b;
        public int baselineMargin;
        public int baselineToBaseline;
        public int baselineToBottom;
        public int baselineToTop;
        public int bottomToBottom;
        public int bottomToTop;

        /* renamed from: c  reason: collision with root package name */
        float f2255c;
        public float circleAngle;
        public int circleConstraint;
        public int circleRadius;
        public boolean constrainedHeight;
        public boolean constrainedWidth;
        public String constraintTag;

        /* renamed from: d  reason: collision with root package name */
        int f2256d;
        public String dimensionRatio;

        /* renamed from: e  reason: collision with root package name */
        boolean f2257e;
        public int editorAbsoluteX;
        public int editorAbsoluteY;
        public int endToEnd;
        public int endToStart;

        /* renamed from: f  reason: collision with root package name */
        boolean f2258f;

        /* renamed from: g  reason: collision with root package name */
        boolean f2259g;
        public int goneBaselineMargin;
        public int goneBottomMargin;
        public int goneEndMargin;
        public int goneLeftMargin;
        public int goneRightMargin;
        public int goneStartMargin;
        public int goneTopMargin;
        public int guideBegin;
        public int guideEnd;
        public float guidePercent;

        /* renamed from: h  reason: collision with root package name */
        boolean f2260h;
        public boolean helped;
        public float horizontalBias;
        public int horizontalChainStyle;
        public float horizontalWeight;

        /* renamed from: i  reason: collision with root package name */
        boolean f2261i;

        /* renamed from: j  reason: collision with root package name */
        boolean f2262j;

        /* renamed from: k  reason: collision with root package name */
        boolean f2263k;

        /* renamed from: l  reason: collision with root package name */
        int f2264l;
        public int leftToLeft;
        public int leftToRight;

        /* renamed from: m  reason: collision with root package name */
        int f2265m;
        public int matchConstraintDefaultHeight;
        public int matchConstraintDefaultWidth;
        public int matchConstraintMaxHeight;
        public int matchConstraintMaxWidth;
        public int matchConstraintMinHeight;
        public int matchConstraintMinWidth;
        public float matchConstraintPercentHeight;
        public float matchConstraintPercentWidth;

        /* renamed from: n  reason: collision with root package name */
        int f2266n;

        /* renamed from: o  reason: collision with root package name */
        int f2267o;
        public int orientation;

        /* renamed from: p  reason: collision with root package name */
        int f2268p;

        /* renamed from: q  reason: collision with root package name */
        int f2269q;

        /* renamed from: r  reason: collision with root package name */
        float f2270r;
        public int rightToLeft;
        public int rightToRight;

        /* renamed from: s  reason: collision with root package name */
        int f2271s;
        public int startToEnd;
        public int startToStart;

        /* renamed from: t  reason: collision with root package name */
        int f2272t;
        public int topToBottom;
        public int topToTop;
        float u;
        ConstraintWidget v;
        public float verticalBias;
        public int verticalChainStyle;
        public float verticalWeight;
        public int wrapBehaviorInParent;

        /* loaded from: classes.dex */
        private static class Table {
            public static final int ANDROID_ORIENTATION = 1;
            public static final int LAYOUT_CONSTRAINED_HEIGHT = 28;
            public static final int LAYOUT_CONSTRAINED_WIDTH = 27;
            public static final int LAYOUT_CONSTRAINT_BASELINE_CREATOR = 43;
            public static final int LAYOUT_CONSTRAINT_BASELINE_TO_BASELINE_OF = 16;
            public static final int LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF = 53;
            public static final int LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF = 52;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_CREATOR = 42;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_BOTTOM_OF = 15;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_TOP_OF = 14;
            public static final int LAYOUT_CONSTRAINT_CIRCLE = 2;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_ANGLE = 4;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_RADIUS = 3;
            public static final int LAYOUT_CONSTRAINT_DIMENSION_RATIO = 44;
            public static final int LAYOUT_CONSTRAINT_END_TO_END_OF = 20;
            public static final int LAYOUT_CONSTRAINT_END_TO_START_OF = 19;
            public static final int LAYOUT_CONSTRAINT_GUIDE_BEGIN = 5;
            public static final int LAYOUT_CONSTRAINT_GUIDE_END = 6;
            public static final int LAYOUT_CONSTRAINT_GUIDE_PERCENT = 7;
            public static final int LAYOUT_CONSTRAINT_HEIGHT = 65;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_DEFAULT = 32;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MAX = 37;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MIN = 36;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_PERCENT = 38;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_BIAS = 29;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_CHAINSTYLE = 47;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_WEIGHT = 45;
            public static final int LAYOUT_CONSTRAINT_LEFT_CREATOR = 39;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_LEFT_OF = 8;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_RIGHT_OF = 9;
            public static final int LAYOUT_CONSTRAINT_RIGHT_CREATOR = 41;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_LEFT_OF = 10;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_RIGHT_OF = 11;
            public static final int LAYOUT_CONSTRAINT_START_TO_END_OF = 17;
            public static final int LAYOUT_CONSTRAINT_START_TO_START_OF = 18;
            public static final int LAYOUT_CONSTRAINT_TAG = 51;
            public static final int LAYOUT_CONSTRAINT_TOP_CREATOR = 40;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_BOTTOM_OF = 13;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_TOP_OF = 12;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_BIAS = 30;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE = 48;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_WEIGHT = 46;
            public static final int LAYOUT_CONSTRAINT_WIDTH = 64;
            public static final int LAYOUT_CONSTRAINT_WIDTH_DEFAULT = 31;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MAX = 34;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MIN = 33;
            public static final int LAYOUT_CONSTRAINT_WIDTH_PERCENT = 35;
            public static final int LAYOUT_EDITOR_ABSOLUTEX = 49;
            public static final int LAYOUT_EDITOR_ABSOLUTEY = 50;
            public static final int LAYOUT_GONE_MARGIN_BASELINE = 55;
            public static final int LAYOUT_GONE_MARGIN_BOTTOM = 24;
            public static final int LAYOUT_GONE_MARGIN_END = 26;
            public static final int LAYOUT_GONE_MARGIN_LEFT = 21;
            public static final int LAYOUT_GONE_MARGIN_RIGHT = 23;
            public static final int LAYOUT_GONE_MARGIN_START = 25;
            public static final int LAYOUT_GONE_MARGIN_TOP = 22;
            public static final int LAYOUT_MARGIN_BASELINE = 54;
            public static final int LAYOUT_WRAP_BEHAVIOR_IN_PARENT = 66;
            public static final int UNUSED = 0;
            public static final SparseIntArray map;

            static {
                SparseIntArray sparseIntArray = new SparseIntArray();
                map = sparseIntArray;
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth, 64);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight, 65);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toTopOf, 52);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBottomOf, 53);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_android_orientation, 1);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginBaseline, 55);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_marginBaseline, 54);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintTag, 51);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_wrapBehaviorInParent, 66);
            }

            private Table() {
            }
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.baselineToTop = -1;
            this.baselineToBottom = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = Integer.MIN_VALUE;
            this.goneTopMargin = Integer.MIN_VALUE;
            this.goneRightMargin = Integer.MIN_VALUE;
            this.goneBottomMargin = Integer.MIN_VALUE;
            this.goneStartMargin = Integer.MIN_VALUE;
            this.goneEndMargin = Integer.MIN_VALUE;
            this.goneBaselineMargin = Integer.MIN_VALUE;
            this.baselineMargin = 0;
            this.f2253a = true;
            this.f2254b = true;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.f2255c = 0.0f;
            this.f2256d = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.constraintTag = null;
            this.wrapBehaviorInParent = 0;
            this.f2257e = true;
            this.f2258f = true;
            this.f2259g = false;
            this.f2260h = false;
            this.f2261i = false;
            this.f2262j = false;
            this.f2263k = false;
            this.f2264l = -1;
            this.f2265m = -1;
            this.f2266n = -1;
            this.f2267o = -1;
            this.f2268p = Integer.MIN_VALUE;
            this.f2269q = Integer.MIN_VALUE;
            this.f2270r = 0.5f;
            this.v = new ConstraintWidget();
            this.helped = false;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            String str;
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.baselineToTop = -1;
            this.baselineToBottom = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = Integer.MIN_VALUE;
            this.goneTopMargin = Integer.MIN_VALUE;
            this.goneRightMargin = Integer.MIN_VALUE;
            this.goneBottomMargin = Integer.MIN_VALUE;
            this.goneStartMargin = Integer.MIN_VALUE;
            this.goneEndMargin = Integer.MIN_VALUE;
            this.goneBaselineMargin = Integer.MIN_VALUE;
            this.baselineMargin = 0;
            this.f2253a = true;
            this.f2254b = true;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.f2255c = 0.0f;
            this.f2256d = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.constraintTag = null;
            this.wrapBehaviorInParent = 0;
            this.f2257e = true;
            this.f2258f = true;
            this.f2259g = false;
            this.f2260h = false;
            this.f2261i = false;
            this.f2262j = false;
            this.f2263k = false;
            this.f2264l = -1;
            this.f2265m = -1;
            this.f2266n = -1;
            this.f2267o = -1;
            this.f2268p = Integer.MIN_VALUE;
            this.f2269q = Integer.MIN_VALUE;
            this.f2270r = 0.5f;
            this.v = new ConstraintWidget();
            this.helped = false;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                int i3 = Table.map.get(index);
                switch (i3) {
                    case 1:
                        this.orientation = obtainStyledAttributes.getInt(index, this.orientation);
                        continue;
                    case 2:
                        int resourceId = obtainStyledAttributes.getResourceId(index, this.circleConstraint);
                        this.circleConstraint = resourceId;
                        if (resourceId == -1) {
                            this.circleConstraint = obtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                    case 3:
                        this.circleRadius = obtainStyledAttributes.getDimensionPixelSize(index, this.circleRadius);
                        continue;
                    case 4:
                        float f2 = obtainStyledAttributes.getFloat(index, this.circleAngle) % 360.0f;
                        this.circleAngle = f2;
                        if (f2 < 0.0f) {
                            this.circleAngle = (360.0f - f2) % 360.0f;
                        } else {
                            continue;
                        }
                    case 5:
                        this.guideBegin = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideBegin);
                        continue;
                    case 6:
                        this.guideEnd = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideEnd);
                        continue;
                    case 7:
                        this.guidePercent = obtainStyledAttributes.getFloat(index, this.guidePercent);
                        continue;
                    case 8:
                        int resourceId2 = obtainStyledAttributes.getResourceId(index, this.leftToLeft);
                        this.leftToLeft = resourceId2;
                        if (resourceId2 == -1) {
                            this.leftToLeft = obtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                    case 9:
                        int resourceId3 = obtainStyledAttributes.getResourceId(index, this.leftToRight);
                        this.leftToRight = resourceId3;
                        if (resourceId3 == -1) {
                            this.leftToRight = obtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                    case 10:
                        int resourceId4 = obtainStyledAttributes.getResourceId(index, this.rightToLeft);
                        this.rightToLeft = resourceId4;
                        if (resourceId4 == -1) {
                            this.rightToLeft = obtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                    case 11:
                        int resourceId5 = obtainStyledAttributes.getResourceId(index, this.rightToRight);
                        this.rightToRight = resourceId5;
                        if (resourceId5 == -1) {
                            this.rightToRight = obtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                    case 12:
                        int resourceId6 = obtainStyledAttributes.getResourceId(index, this.topToTop);
                        this.topToTop = resourceId6;
                        if (resourceId6 == -1) {
                            this.topToTop = obtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                    case 13:
                        int resourceId7 = obtainStyledAttributes.getResourceId(index, this.topToBottom);
                        this.topToBottom = resourceId7;
                        if (resourceId7 == -1) {
                            this.topToBottom = obtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                    case 14:
                        int resourceId8 = obtainStyledAttributes.getResourceId(index, this.bottomToTop);
                        this.bottomToTop = resourceId8;
                        if (resourceId8 == -1) {
                            this.bottomToTop = obtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                    case 15:
                        int resourceId9 = obtainStyledAttributes.getResourceId(index, this.bottomToBottom);
                        this.bottomToBottom = resourceId9;
                        if (resourceId9 == -1) {
                            this.bottomToBottom = obtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                    case 16:
                        int resourceId10 = obtainStyledAttributes.getResourceId(index, this.baselineToBaseline);
                        this.baselineToBaseline = resourceId10;
                        if (resourceId10 == -1) {
                            this.baselineToBaseline = obtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                    case 17:
                        int resourceId11 = obtainStyledAttributes.getResourceId(index, this.startToEnd);
                        this.startToEnd = resourceId11;
                        if (resourceId11 == -1) {
                            this.startToEnd = obtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                    case 18:
                        int resourceId12 = obtainStyledAttributes.getResourceId(index, this.startToStart);
                        this.startToStart = resourceId12;
                        if (resourceId12 == -1) {
                            this.startToStart = obtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                    case 19:
                        int resourceId13 = obtainStyledAttributes.getResourceId(index, this.endToStart);
                        this.endToStart = resourceId13;
                        if (resourceId13 == -1) {
                            this.endToStart = obtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                    case 20:
                        int resourceId14 = obtainStyledAttributes.getResourceId(index, this.endToEnd);
                        this.endToEnd = resourceId14;
                        if (resourceId14 == -1) {
                            this.endToEnd = obtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                    case 21:
                        this.goneLeftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneLeftMargin);
                        continue;
                    case 22:
                        this.goneTopMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneTopMargin);
                        continue;
                    case 23:
                        this.goneRightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneRightMargin);
                        continue;
                    case 24:
                        this.goneBottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneBottomMargin);
                        continue;
                    case 25:
                        this.goneStartMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneStartMargin);
                        continue;
                    case 26:
                        this.goneEndMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneEndMargin);
                        continue;
                    case 27:
                        this.constrainedWidth = obtainStyledAttributes.getBoolean(index, this.constrainedWidth);
                        continue;
                    case 28:
                        this.constrainedHeight = obtainStyledAttributes.getBoolean(index, this.constrainedHeight);
                        continue;
                    case 29:
                        this.horizontalBias = obtainStyledAttributes.getFloat(index, this.horizontalBias);
                        continue;
                    case 30:
                        this.verticalBias = obtainStyledAttributes.getFloat(index, this.verticalBias);
                        continue;
                    case 31:
                        int i4 = obtainStyledAttributes.getInt(index, 0);
                        this.matchConstraintDefaultWidth = i4;
                        if (i4 == 1) {
                            str = "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.";
                            break;
                        } else {
                            continue;
                        }
                    case 32:
                        int i5 = obtainStyledAttributes.getInt(index, 0);
                        this.matchConstraintDefaultHeight = i5;
                        if (i5 == 1) {
                            str = "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.";
                            break;
                        } else {
                            continue;
                        }
                    case 33:
                        try {
                            this.matchConstraintMinWidth = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMinWidth);
                            continue;
                        } catch (Exception unused) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMinWidth) == -2) {
                                this.matchConstraintMinWidth = -2;
                            }
                        }
                    case 34:
                        try {
                            this.matchConstraintMaxWidth = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMaxWidth);
                            continue;
                        } catch (Exception unused2) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMaxWidth) == -2) {
                                this.matchConstraintMaxWidth = -2;
                            }
                        }
                    case 35:
                        this.matchConstraintPercentWidth = Math.max(0.0f, obtainStyledAttributes.getFloat(index, this.matchConstraintPercentWidth));
                        this.matchConstraintDefaultWidth = 2;
                        continue;
                    case 36:
                        try {
                            this.matchConstraintMinHeight = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMinHeight);
                            continue;
                        } catch (Exception unused3) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMinHeight) == -2) {
                                this.matchConstraintMinHeight = -2;
                            }
                        }
                    case 37:
                        try {
                            this.matchConstraintMaxHeight = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMaxHeight);
                            continue;
                        } catch (Exception unused4) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMaxHeight) == -2) {
                                this.matchConstraintMaxHeight = -2;
                            }
                        }
                    case 38:
                        this.matchConstraintPercentHeight = Math.max(0.0f, obtainStyledAttributes.getFloat(index, this.matchConstraintPercentHeight));
                        this.matchConstraintDefaultHeight = 2;
                        continue;
                    default:
                        switch (i3) {
                            case 44:
                                ConstraintSet.k(this, obtainStyledAttributes.getString(index));
                                continue;
                            case 45:
                                this.horizontalWeight = obtainStyledAttributes.getFloat(index, this.horizontalWeight);
                                continue;
                            case 46:
                                this.verticalWeight = obtainStyledAttributes.getFloat(index, this.verticalWeight);
                                continue;
                            case 47:
                                this.horizontalChainStyle = obtainStyledAttributes.getInt(index, 0);
                                continue;
                            case 48:
                                this.verticalChainStyle = obtainStyledAttributes.getInt(index, 0);
                                continue;
                            case 49:
                                this.editorAbsoluteX = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteX);
                                continue;
                            case 50:
                                this.editorAbsoluteY = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteY);
                                continue;
                            case 51:
                                this.constraintTag = obtainStyledAttributes.getString(index);
                                continue;
                            case 52:
                                int resourceId15 = obtainStyledAttributes.getResourceId(index, this.baselineToTop);
                                this.baselineToTop = resourceId15;
                                if (resourceId15 == -1) {
                                    this.baselineToTop = obtainStyledAttributes.getInt(index, -1);
                                    break;
                                } else {
                                    continue;
                                }
                            case 53:
                                int resourceId16 = obtainStyledAttributes.getResourceId(index, this.baselineToBottom);
                                this.baselineToBottom = resourceId16;
                                if (resourceId16 == -1) {
                                    this.baselineToBottom = obtainStyledAttributes.getInt(index, -1);
                                    break;
                                } else {
                                    continue;
                                }
                            case 54:
                                this.baselineMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.baselineMargin);
                                continue;
                            case 55:
                                this.goneBaselineMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneBaselineMargin);
                                continue;
                            default:
                                switch (i3) {
                                    case 64:
                                        ConstraintSet.i(this, obtainStyledAttributes, index, 0);
                                        this.f2253a = true;
                                        continue;
                                    case 65:
                                        ConstraintSet.i(this, obtainStyledAttributes, index, 1);
                                        this.f2254b = true;
                                        continue;
                                    case 66:
                                        this.wrapBehaviorInParent = obtainStyledAttributes.getInt(index, this.wrapBehaviorInParent);
                                        continue;
                                        continue;
                                        continue;
                                }
                        }
                        break;
                }
                Log.e(ConstraintLayout.TAG, str);
            }
            obtainStyledAttributes.recycle();
            validate();
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.baselineToTop = -1;
            this.baselineToBottom = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = Integer.MIN_VALUE;
            this.goneTopMargin = Integer.MIN_VALUE;
            this.goneRightMargin = Integer.MIN_VALUE;
            this.goneBottomMargin = Integer.MIN_VALUE;
            this.goneStartMargin = Integer.MIN_VALUE;
            this.goneEndMargin = Integer.MIN_VALUE;
            this.goneBaselineMargin = Integer.MIN_VALUE;
            this.baselineMargin = 0;
            this.f2253a = true;
            this.f2254b = true;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.f2255c = 0.0f;
            this.f2256d = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.constraintTag = null;
            this.wrapBehaviorInParent = 0;
            this.f2257e = true;
            this.f2258f = true;
            this.f2259g = false;
            this.f2260h = false;
            this.f2261i = false;
            this.f2262j = false;
            this.f2263k = false;
            this.f2264l = -1;
            this.f2265m = -1;
            this.f2266n = -1;
            this.f2267o = -1;
            this.f2268p = Integer.MIN_VALUE;
            this.f2269q = Integer.MIN_VALUE;
            this.f2270r = 0.5f;
            this.v = new ConstraintWidget();
            this.helped = false;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.baselineToTop = -1;
            this.baselineToBottom = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = Integer.MIN_VALUE;
            this.goneTopMargin = Integer.MIN_VALUE;
            this.goneRightMargin = Integer.MIN_VALUE;
            this.goneBottomMargin = Integer.MIN_VALUE;
            this.goneStartMargin = Integer.MIN_VALUE;
            this.goneEndMargin = Integer.MIN_VALUE;
            this.goneBaselineMargin = Integer.MIN_VALUE;
            this.baselineMargin = 0;
            this.f2253a = true;
            this.f2254b = true;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.f2255c = 0.0f;
            this.f2256d = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.constraintTag = null;
            this.wrapBehaviorInParent = 0;
            this.f2257e = true;
            this.f2258f = true;
            this.f2259g = false;
            this.f2260h = false;
            this.f2261i = false;
            this.f2262j = false;
            this.f2263k = false;
            this.f2264l = -1;
            this.f2265m = -1;
            this.f2266n = -1;
            this.f2267o = -1;
            this.f2268p = Integer.MIN_VALUE;
            this.f2269q = Integer.MIN_VALUE;
            this.f2270r = 0.5f;
            this.v = new ConstraintWidget();
            this.helped = false;
            this.guideBegin = layoutParams.guideBegin;
            this.guideEnd = layoutParams.guideEnd;
            this.guidePercent = layoutParams.guidePercent;
            this.leftToLeft = layoutParams.leftToLeft;
            this.leftToRight = layoutParams.leftToRight;
            this.rightToLeft = layoutParams.rightToLeft;
            this.rightToRight = layoutParams.rightToRight;
            this.topToTop = layoutParams.topToTop;
            this.topToBottom = layoutParams.topToBottom;
            this.bottomToTop = layoutParams.bottomToTop;
            this.bottomToBottom = layoutParams.bottomToBottom;
            this.baselineToBaseline = layoutParams.baselineToBaseline;
            this.baselineToTop = layoutParams.baselineToTop;
            this.baselineToBottom = layoutParams.baselineToBottom;
            this.circleConstraint = layoutParams.circleConstraint;
            this.circleRadius = layoutParams.circleRadius;
            this.circleAngle = layoutParams.circleAngle;
            this.startToEnd = layoutParams.startToEnd;
            this.startToStart = layoutParams.startToStart;
            this.endToStart = layoutParams.endToStart;
            this.endToEnd = layoutParams.endToEnd;
            this.goneLeftMargin = layoutParams.goneLeftMargin;
            this.goneTopMargin = layoutParams.goneTopMargin;
            this.goneRightMargin = layoutParams.goneRightMargin;
            this.goneBottomMargin = layoutParams.goneBottomMargin;
            this.goneStartMargin = layoutParams.goneStartMargin;
            this.goneEndMargin = layoutParams.goneEndMargin;
            this.goneBaselineMargin = layoutParams.goneBaselineMargin;
            this.baselineMargin = layoutParams.baselineMargin;
            this.horizontalBias = layoutParams.horizontalBias;
            this.verticalBias = layoutParams.verticalBias;
            this.dimensionRatio = layoutParams.dimensionRatio;
            this.f2255c = layoutParams.f2255c;
            this.f2256d = layoutParams.f2256d;
            this.horizontalWeight = layoutParams.horizontalWeight;
            this.verticalWeight = layoutParams.verticalWeight;
            this.horizontalChainStyle = layoutParams.horizontalChainStyle;
            this.verticalChainStyle = layoutParams.verticalChainStyle;
            this.constrainedWidth = layoutParams.constrainedWidth;
            this.constrainedHeight = layoutParams.constrainedHeight;
            this.matchConstraintDefaultWidth = layoutParams.matchConstraintDefaultWidth;
            this.matchConstraintDefaultHeight = layoutParams.matchConstraintDefaultHeight;
            this.matchConstraintMinWidth = layoutParams.matchConstraintMinWidth;
            this.matchConstraintMaxWidth = layoutParams.matchConstraintMaxWidth;
            this.matchConstraintMinHeight = layoutParams.matchConstraintMinHeight;
            this.matchConstraintMaxHeight = layoutParams.matchConstraintMaxHeight;
            this.matchConstraintPercentWidth = layoutParams.matchConstraintPercentWidth;
            this.matchConstraintPercentHeight = layoutParams.matchConstraintPercentHeight;
            this.editorAbsoluteX = layoutParams.editorAbsoluteX;
            this.editorAbsoluteY = layoutParams.editorAbsoluteY;
            this.orientation = layoutParams.orientation;
            this.f2257e = layoutParams.f2257e;
            this.f2258f = layoutParams.f2258f;
            this.f2259g = layoutParams.f2259g;
            this.f2260h = layoutParams.f2260h;
            this.f2264l = layoutParams.f2264l;
            this.f2265m = layoutParams.f2265m;
            this.f2266n = layoutParams.f2266n;
            this.f2267o = layoutParams.f2267o;
            this.f2268p = layoutParams.f2268p;
            this.f2269q = layoutParams.f2269q;
            this.f2270r = layoutParams.f2270r;
            this.constraintTag = layoutParams.constraintTag;
            this.wrapBehaviorInParent = layoutParams.wrapBehaviorInParent;
            this.v = layoutParams.v;
            this.f2253a = layoutParams.f2253a;
            this.f2254b = layoutParams.f2254b;
        }

        public String getConstraintTag() {
            return this.constraintTag;
        }

        public ConstraintWidget getConstraintWidget() {
            return this.v;
        }

        public void reset() {
            ConstraintWidget constraintWidget = this.v;
            if (constraintWidget != null) {
                constraintWidget.reset();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:74:0x00d6, code lost:
            if (r1 > 0) goto L52;
         */
        /* JADX WARN: Code restructure failed: missing block: B:75:0x00d8, code lost:
            ((android.view.ViewGroup.MarginLayoutParams) r10).rightMargin = r1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:80:0x00e5, code lost:
            if (r1 > 0) goto L52;
         */
        /* JADX WARN: Removed duplicated region for block: B:10:0x0041  */
        /* JADX WARN: Removed duplicated region for block: B:19:0x0054  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x005b  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0062  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0068  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x006e  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0080  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0088  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0098  */
        /* JADX WARN: Removed duplicated region for block: B:84:0x00ec  */
        /* JADX WARN: Removed duplicated region for block: B:88:0x00f7  */
        @Override // android.view.ViewGroup.MarginLayoutParams, android.view.ViewGroup.LayoutParams
        @TargetApi(17)
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void resolveLayoutDirection(int i2) {
            boolean z;
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            int i8 = ((ViewGroup.MarginLayoutParams) this).leftMargin;
            int i9 = ((ViewGroup.MarginLayoutParams) this).rightMargin;
            boolean z2 = false;
            if (Build.VERSION.SDK_INT >= 17) {
                super.resolveLayoutDirection(i2);
                if (1 == getLayoutDirection()) {
                    z = true;
                    this.f2266n = -1;
                    this.f2267o = -1;
                    this.f2264l = -1;
                    this.f2265m = -1;
                    this.f2268p = -1;
                    this.f2269q = -1;
                    this.f2268p = this.goneLeftMargin;
                    this.f2269q = this.goneRightMargin;
                    float f2 = this.horizontalBias;
                    this.f2270r = f2;
                    int i10 = this.guideBegin;
                    this.f2271s = i10;
                    int i11 = this.guideEnd;
                    this.f2272t = i11;
                    float f3 = this.guidePercent;
                    this.u = f3;
                    if (z) {
                        int i12 = this.startToEnd;
                        if (i12 != -1) {
                            this.f2265m = i12;
                        }
                        int i13 = this.startToStart;
                        if (i13 != -1) {
                            this.f2264l = i13;
                        }
                        int i14 = this.endToStart;
                        if (i14 != -1) {
                            this.f2266n = i14;
                        }
                        int i15 = this.endToEnd;
                        if (i15 != -1) {
                            this.f2267o = i15;
                        }
                        int i16 = this.goneStartMargin;
                        if (i16 != Integer.MIN_VALUE) {
                            this.f2268p = i16;
                        }
                        int i17 = this.goneEndMargin;
                        if (i17 != Integer.MIN_VALUE) {
                            this.f2269q = i17;
                        }
                    } else {
                        int i18 = this.startToEnd;
                        if (i18 != -1) {
                            this.f2266n = i18;
                        } else {
                            int i19 = this.startToStart;
                            if (i19 != -1) {
                                this.f2267o = i19;
                            }
                            i4 = this.endToStart;
                            if (i4 != -1) {
                                this.f2265m = i4;
                                z2 = true;
                            }
                            i5 = this.endToEnd;
                            if (i5 != -1) {
                                this.f2264l = i5;
                                z2 = true;
                            }
                            i6 = this.goneStartMargin;
                            if (i6 != Integer.MIN_VALUE) {
                                this.f2269q = i6;
                            }
                            i7 = this.goneEndMargin;
                            if (i7 != Integer.MIN_VALUE) {
                                this.f2268p = i7;
                            }
                            if (z2) {
                                this.f2270r = 1.0f - f2;
                            }
                            if (this.f2260h && this.orientation == 1) {
                                if (f3 == -1.0f) {
                                    this.u = 1.0f - f3;
                                    this.f2271s = -1;
                                    this.f2272t = -1;
                                } else {
                                    if (i10 != -1) {
                                        this.f2272t = i10;
                                        this.f2271s = -1;
                                    } else if (i11 != -1) {
                                        this.f2271s = i11;
                                        this.f2272t = -1;
                                    }
                                    this.u = -1.0f;
                                }
                            }
                        }
                        z2 = true;
                        i4 = this.endToStart;
                        if (i4 != -1) {
                        }
                        i5 = this.endToEnd;
                        if (i5 != -1) {
                        }
                        i6 = this.goneStartMargin;
                        if (i6 != Integer.MIN_VALUE) {
                        }
                        i7 = this.goneEndMargin;
                        if (i7 != Integer.MIN_VALUE) {
                        }
                        if (z2) {
                        }
                        if (this.f2260h) {
                            if (f3 == -1.0f) {
                            }
                        }
                    }
                    if (this.endToStart != -1 && this.endToEnd == -1 && this.startToStart == -1 && this.startToEnd == -1) {
                        int i20 = this.rightToLeft;
                        if (i20 == -1) {
                            int i21 = this.rightToRight;
                            if (i21 != -1) {
                                this.f2267o = i21;
                                if (((ViewGroup.MarginLayoutParams) this).rightMargin <= 0) {
                                }
                            }
                            i3 = this.leftToLeft;
                            if (i3 == -1) {
                            }
                            ((ViewGroup.MarginLayoutParams) this).leftMargin = i8;
                            return;
                        }
                        this.f2266n = i20;
                        if (((ViewGroup.MarginLayoutParams) this).rightMargin <= 0) {
                        }
                        i3 = this.leftToLeft;
                        if (i3 == -1) {
                            this.f2264l = i3;
                            if (((ViewGroup.MarginLayoutParams) this).leftMargin > 0 || i8 <= 0) {
                                return;
                            }
                        } else {
                            int i22 = this.leftToRight;
                            if (i22 == -1) {
                                return;
                            }
                            this.f2265m = i22;
                            if (((ViewGroup.MarginLayoutParams) this).leftMargin > 0 || i8 <= 0) {
                                return;
                            }
                        }
                        ((ViewGroup.MarginLayoutParams) this).leftMargin = i8;
                        return;
                    }
                    return;
                }
            }
            z = false;
            this.f2266n = -1;
            this.f2267o = -1;
            this.f2264l = -1;
            this.f2265m = -1;
            this.f2268p = -1;
            this.f2269q = -1;
            this.f2268p = this.goneLeftMargin;
            this.f2269q = this.goneRightMargin;
            float f22 = this.horizontalBias;
            this.f2270r = f22;
            int i102 = this.guideBegin;
            this.f2271s = i102;
            int i112 = this.guideEnd;
            this.f2272t = i112;
            float f32 = this.guidePercent;
            this.u = f32;
            if (z) {
            }
            if (this.endToStart != -1) {
            }
        }

        public void setWidgetDebugName(String str) {
            this.v.setDebugName(str);
        }

        public void validate() {
            this.f2260h = false;
            this.f2257e = true;
            this.f2258f = true;
            int i2 = ((ViewGroup.MarginLayoutParams) this).width;
            if (i2 == -2 && this.constrainedWidth) {
                this.f2257e = false;
                if (this.matchConstraintDefaultWidth == 0) {
                    this.matchConstraintDefaultWidth = 1;
                }
            }
            int i3 = ((ViewGroup.MarginLayoutParams) this).height;
            if (i3 == -2 && this.constrainedHeight) {
                this.f2258f = false;
                if (this.matchConstraintDefaultHeight == 0) {
                    this.matchConstraintDefaultHeight = 1;
                }
            }
            if (i2 == 0 || i2 == -1) {
                this.f2257e = false;
                if (i2 == 0 && this.matchConstraintDefaultWidth == 1) {
                    ((ViewGroup.MarginLayoutParams) this).width = -2;
                    this.constrainedWidth = true;
                }
            }
            if (i3 == 0 || i3 == -1) {
                this.f2258f = false;
                if (i3 == 0 && this.matchConstraintDefaultHeight == 1) {
                    ((ViewGroup.MarginLayoutParams) this).height = -2;
                    this.constrainedHeight = true;
                }
            }
            if (this.guidePercent == -1.0f && this.guideBegin == -1 && this.guideEnd == -1) {
                return;
            }
            this.f2260h = true;
            this.f2257e = true;
            this.f2258f = true;
            if (!(this.v instanceof androidx.constraintlayout.core.widgets.Guideline)) {
                this.v = new androidx.constraintlayout.core.widgets.Guideline();
            }
            ((androidx.constraintlayout.core.widgets.Guideline) this.v).setOrientation(this.orientation);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class Measurer implements BasicMeasure.Measurer {

        /* renamed from: a  reason: collision with root package name */
        ConstraintLayout f2273a;

        /* renamed from: b  reason: collision with root package name */
        int f2274b;

        /* renamed from: c  reason: collision with root package name */
        int f2275c;

        /* renamed from: d  reason: collision with root package name */
        int f2276d;

        /* renamed from: e  reason: collision with root package name */
        int f2277e;

        /* renamed from: f  reason: collision with root package name */
        int f2278f;

        /* renamed from: g  reason: collision with root package name */
        int f2279g;

        public Measurer(ConstraintLayout constraintLayout) {
            this.f2273a = constraintLayout;
        }

        private boolean isSimilarSpec(int i2, int i3, int i4) {
            if (i2 == i3) {
                return true;
            }
            int mode = View.MeasureSpec.getMode(i2);
            View.MeasureSpec.getSize(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            int size = View.MeasureSpec.getSize(i3);
            if (mode2 == 1073741824) {
                return (mode == Integer.MIN_VALUE || mode == 0) && i4 == size;
            }
            return false;
        }

        public void captureLayoutInfo(int i2, int i3, int i4, int i5, int i6, int i7) {
            this.f2274b = i4;
            this.f2275c = i5;
            this.f2276d = i6;
            this.f2277e = i7;
            this.f2278f = i2;
            this.f2279g = i3;
        }

        @Override // androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measurer
        public final void didMeasures() {
            int childCount = this.f2273a.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = this.f2273a.getChildAt(i2);
                if (childAt instanceof Placeholder) {
                    ((Placeholder) childAt).updatePostMeasure(this.f2273a);
                }
            }
            int size = this.f2273a.mConstraintHelpers.size();
            if (size > 0) {
                for (int i3 = 0; i3 < size; i3++) {
                    ((ConstraintHelper) this.f2273a.mConstraintHelpers.get(i3)).updatePostMeasure(this.f2273a);
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:111:0x018c  */
        /* JADX WARN: Removed duplicated region for block: B:115:0x01a3  */
        /* JADX WARN: Removed duplicated region for block: B:116:0x01a5  */
        /* JADX WARN: Removed duplicated region for block: B:118:0x01a8  */
        /* JADX WARN: Removed duplicated region for block: B:119:0x01aa  */
        /* JADX WARN: Removed duplicated region for block: B:122:0x01af  */
        /* JADX WARN: Removed duplicated region for block: B:128:0x01b9  */
        /* JADX WARN: Removed duplicated region for block: B:135:0x01c4  */
        /* JADX WARN: Removed duplicated region for block: B:140:0x01cf  */
        /* JADX WARN: Removed duplicated region for block: B:145:0x01da A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:146:0x01db  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x00b6  */
        /* JADX WARN: Removed duplicated region for block: B:90:0x0125  */
        @Override // androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measurer
        @SuppressLint({"WrongCall"})
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void measure(ConstraintWidget constraintWidget, BasicMeasure.Measure measure) {
            int makeMeasureSpec;
            int i2;
            int makeMeasureSpec2;
            ConstraintWidgetContainer constraintWidgetContainer;
            int baseline;
            int max;
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            int i8;
            int i9;
            if (constraintWidget == null) {
                return;
            }
            if (constraintWidget.getVisibility() == 8 && !constraintWidget.isInPlaceholder()) {
                measure.measuredWidth = 0;
                measure.measuredHeight = 0;
                measure.measuredBaseline = 0;
            } else if (constraintWidget.getParent() == null) {
            } else {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = measure.horizontalBehavior;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = measure.verticalBehavior;
                int i10 = measure.horizontalDimension;
                int i11 = measure.verticalDimension;
                int i12 = this.f2274b + this.f2275c;
                int i13 = this.f2276d;
                View view = (View) constraintWidget.getCompanionWidget();
                int[] iArr = AnonymousClass1.f2252a;
                int i14 = iArr[dimensionBehaviour.ordinal()];
                if (i14 != 1) {
                    if (i14 == 2) {
                        i8 = this.f2278f;
                        i9 = -2;
                    } else if (i14 != 3) {
                        if (i14 != 4) {
                            makeMeasureSpec = 0;
                        } else {
                            makeMeasureSpec = ViewGroup.getChildMeasureSpec(this.f2278f, i13, -2);
                            boolean z = constraintWidget.mMatchConstraintDefaultWidth == 1;
                            int i15 = measure.measureStrategy;
                            if (i15 == BasicMeasure.Measure.TRY_GIVEN_DIMENSIONS || i15 == BasicMeasure.Measure.USE_GIVEN_DIMENSIONS) {
                                if (measure.measureStrategy == BasicMeasure.Measure.USE_GIVEN_DIMENSIONS || !z || (z && (view.getMeasuredHeight() == constraintWidget.getHeight())) || (view instanceof Placeholder) || constraintWidget.isResolvedHorizontally()) {
                                    i10 = constraintWidget.getWidth();
                                }
                            }
                        }
                        i2 = iArr[dimensionBehaviour2.ordinal()];
                        if (i2 != 1) {
                            if (i2 == 2) {
                                i6 = this.f2279g;
                                i7 = -2;
                            } else if (i2 != 3) {
                                if (i2 != 4) {
                                    makeMeasureSpec2 = 0;
                                } else {
                                    makeMeasureSpec2 = ViewGroup.getChildMeasureSpec(this.f2279g, i12, -2);
                                    boolean z2 = constraintWidget.mMatchConstraintDefaultHeight == 1;
                                    int i16 = measure.measureStrategy;
                                    if (i16 == BasicMeasure.Measure.TRY_GIVEN_DIMENSIONS || i16 == BasicMeasure.Measure.USE_GIVEN_DIMENSIONS) {
                                        if (measure.measureStrategy == BasicMeasure.Measure.USE_GIVEN_DIMENSIONS || !z2 || (z2 && (view.getMeasuredWidth() == constraintWidget.getWidth())) || (view instanceof Placeholder) || constraintWidget.isResolvedVertically()) {
                                            i11 = constraintWidget.getHeight();
                                        }
                                    }
                                }
                                constraintWidgetContainer = (ConstraintWidgetContainer) constraintWidget.getParent();
                                if (constraintWidgetContainer != null && Optimizer.enabled(ConstraintLayout.this.mOptimizationLevel, 256) && view.getMeasuredWidth() == constraintWidget.getWidth() && view.getMeasuredWidth() < constraintWidgetContainer.getWidth() && view.getMeasuredHeight() == constraintWidget.getHeight() && view.getMeasuredHeight() < constraintWidgetContainer.getHeight() && view.getBaseline() == constraintWidget.getBaselineDistance() && !constraintWidget.isMeasureRequested()) {
                                    if (!isSimilarSpec(constraintWidget.getLastHorizontalMeasureSpec(), makeMeasureSpec, constraintWidget.getWidth()) && isSimilarSpec(constraintWidget.getLastVerticalMeasureSpec(), makeMeasureSpec2, constraintWidget.getHeight())) {
                                        measure.measuredWidth = constraintWidget.getWidth();
                                        measure.measuredHeight = constraintWidget.getHeight();
                                        measure.measuredBaseline = constraintWidget.getBaselineDistance();
                                        return;
                                    }
                                }
                                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                                boolean z3 = dimensionBehaviour == dimensionBehaviour3;
                                boolean z4 = dimensionBehaviour2 == dimensionBehaviour3;
                                ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                                boolean z5 = dimensionBehaviour2 != dimensionBehaviour4 || dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.FIXED;
                                boolean z6 = dimensionBehaviour != dimensionBehaviour4 || dimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED;
                                boolean z7 = !z3 && constraintWidget.mDimensionRatio > 0.0f;
                                boolean z8 = !z4 && constraintWidget.mDimensionRatio > 0.0f;
                                if (view == null) {
                                    return;
                                }
                                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                                int i17 = measure.measureStrategy;
                                if (i17 != BasicMeasure.Measure.TRY_GIVEN_DIMENSIONS && i17 != BasicMeasure.Measure.USE_GIVEN_DIMENSIONS && z3 && constraintWidget.mMatchConstraintDefaultWidth == 0 && z4 && constraintWidget.mMatchConstraintDefaultHeight == 0) {
                                    i5 = -1;
                                    i4 = 0;
                                    baseline = 0;
                                    max = 0;
                                } else {
                                    if ((view instanceof VirtualLayout) && (constraintWidget instanceof androidx.constraintlayout.core.widgets.VirtualLayout)) {
                                        ((VirtualLayout) view).onMeasure((androidx.constraintlayout.core.widgets.VirtualLayout) constraintWidget, makeMeasureSpec, makeMeasureSpec2);
                                    } else {
                                        view.measure(makeMeasureSpec, makeMeasureSpec2);
                                    }
                                    constraintWidget.setLastMeasureSpec(makeMeasureSpec, makeMeasureSpec2);
                                    int measuredWidth = view.getMeasuredWidth();
                                    int measuredHeight = view.getMeasuredHeight();
                                    baseline = view.getBaseline();
                                    int i18 = constraintWidget.mMatchConstraintMinWidth;
                                    max = i18 > 0 ? Math.max(i18, measuredWidth) : measuredWidth;
                                    int i19 = constraintWidget.mMatchConstraintMaxWidth;
                                    if (i19 > 0) {
                                        max = Math.min(i19, max);
                                    }
                                    int i20 = constraintWidget.mMatchConstraintMinHeight;
                                    if (i20 > 0) {
                                        i4 = Math.max(i20, measuredHeight);
                                        i3 = makeMeasureSpec;
                                    } else {
                                        i3 = makeMeasureSpec;
                                        i4 = measuredHeight;
                                    }
                                    int i21 = constraintWidget.mMatchConstraintMaxHeight;
                                    if (i21 > 0) {
                                        i4 = Math.min(i21, i4);
                                    }
                                    if (!Optimizer.enabled(ConstraintLayout.this.mOptimizationLevel, 1)) {
                                        if (z7 && z5) {
                                            max = (int) ((i4 * constraintWidget.mDimensionRatio) + 0.5f);
                                        } else if (z8 && z6) {
                                            i4 = (int) ((max / constraintWidget.mDimensionRatio) + 0.5f);
                                        }
                                    }
                                    if (measuredWidth != max || measuredHeight != i4) {
                                        int makeMeasureSpec3 = measuredWidth != max ? View.MeasureSpec.makeMeasureSpec(max, 1073741824) : i3;
                                        if (measuredHeight != i4) {
                                            makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i4, 1073741824);
                                        }
                                        view.measure(makeMeasureSpec3, makeMeasureSpec2);
                                        constraintWidget.setLastMeasureSpec(makeMeasureSpec3, makeMeasureSpec2);
                                        max = view.getMeasuredWidth();
                                        i4 = view.getMeasuredHeight();
                                        baseline = view.getBaseline();
                                    }
                                    i5 = -1;
                                }
                                boolean z9 = baseline != i5;
                                measure.measuredNeedsSolverPass = (max == measure.horizontalDimension && i4 == measure.verticalDimension) ? false : true;
                                if (layoutParams.f2259g) {
                                    z9 = true;
                                }
                                if (z9 && baseline != -1 && constraintWidget.getBaselineDistance() != baseline) {
                                    measure.measuredNeedsSolverPass = true;
                                }
                                measure.measuredWidth = max;
                                measure.measuredHeight = i4;
                                measure.measuredHasBaseline = z9;
                                measure.measuredBaseline = baseline;
                                return;
                            } else {
                                i6 = this.f2279g;
                                i12 += constraintWidget.getVerticalMargin();
                                i7 = -1;
                            }
                            makeMeasureSpec2 = ViewGroup.getChildMeasureSpec(i6, i12, i7);
                            constraintWidgetContainer = (ConstraintWidgetContainer) constraintWidget.getParent();
                            if (constraintWidgetContainer != null) {
                                if (!isSimilarSpec(constraintWidget.getLastHorizontalMeasureSpec(), makeMeasureSpec, constraintWidget.getWidth()) && isSimilarSpec(constraintWidget.getLastVerticalMeasureSpec(), makeMeasureSpec2, constraintWidget.getHeight())) {
                                }
                            }
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour32 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                            if (dimensionBehaviour == dimensionBehaviour32) {
                            }
                            if (dimensionBehaviour2 == dimensionBehaviour32) {
                            }
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour42 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                            if (dimensionBehaviour2 != dimensionBehaviour42) {
                            }
                            if (dimensionBehaviour != dimensionBehaviour42) {
                            }
                            if (z3) {
                            }
                            if (z4) {
                            }
                            if (view == null) {
                            }
                        }
                        makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i11, 1073741824);
                        constraintWidgetContainer = (ConstraintWidgetContainer) constraintWidget.getParent();
                        if (constraintWidgetContainer != null) {
                        }
                        ConstraintWidget.DimensionBehaviour dimensionBehaviour322 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                        if (dimensionBehaviour == dimensionBehaviour322) {
                        }
                        if (dimensionBehaviour2 == dimensionBehaviour322) {
                        }
                        ConstraintWidget.DimensionBehaviour dimensionBehaviour422 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                        if (dimensionBehaviour2 != dimensionBehaviour422) {
                        }
                        if (dimensionBehaviour != dimensionBehaviour422) {
                        }
                        if (z3) {
                        }
                        if (z4) {
                        }
                        if (view == null) {
                        }
                    } else {
                        i8 = this.f2278f;
                        i13 += constraintWidget.getHorizontalMargin();
                        i9 = -1;
                    }
                    makeMeasureSpec = ViewGroup.getChildMeasureSpec(i8, i13, i9);
                    i2 = iArr[dimensionBehaviour2.ordinal()];
                    if (i2 != 1) {
                    }
                    makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i11, 1073741824);
                    constraintWidgetContainer = (ConstraintWidgetContainer) constraintWidget.getParent();
                    if (constraintWidgetContainer != null) {
                    }
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour3222 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                    if (dimensionBehaviour == dimensionBehaviour3222) {
                    }
                    if (dimensionBehaviour2 == dimensionBehaviour3222) {
                    }
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour4222 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                    if (dimensionBehaviour2 != dimensionBehaviour4222) {
                    }
                    if (dimensionBehaviour != dimensionBehaviour4222) {
                    }
                    if (z3) {
                    }
                    if (z4) {
                    }
                    if (view == null) {
                    }
                }
                makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i10, 1073741824);
                i2 = iArr[dimensionBehaviour2.ordinal()];
                if (i2 != 1) {
                }
                makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i11, 1073741824);
                constraintWidgetContainer = (ConstraintWidgetContainer) constraintWidget.getParent();
                if (constraintWidgetContainer != null) {
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour32222 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour == dimensionBehaviour32222) {
                }
                if (dimensionBehaviour2 == dimensionBehaviour32222) {
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour42222 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if (dimensionBehaviour2 != dimensionBehaviour42222) {
                }
                if (dimensionBehaviour != dimensionBehaviour42222) {
                }
                if (z3) {
                }
                if (z4) {
                }
                if (view == null) {
                }
            }
        }
    }

    public ConstraintLayout(@NonNull Context context) {
        super(context);
        this.f2247a = new SparseArray();
        this.mConstraintHelpers = new ArrayList<>(4);
        this.f2248b = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.f2249c = true;
        this.mOptimizationLevel = 257;
        this.mConstraintSet = null;
        this.f2250d = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap<>();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mTempMapIdToWidget = new SparseArray<>();
        this.f2251e = new Measurer(this);
        this.mOnMeasureWidthMeasureSpec = 0;
        this.mOnMeasureHeightMeasureSpec = 0;
        init(null, 0, 0);
    }

    public ConstraintLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2247a = new SparseArray();
        this.mConstraintHelpers = new ArrayList<>(4);
        this.f2248b = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.f2249c = true;
        this.mOptimizationLevel = 257;
        this.mConstraintSet = null;
        this.f2250d = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap<>();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mTempMapIdToWidget = new SparseArray<>();
        this.f2251e = new Measurer(this);
        this.mOnMeasureWidthMeasureSpec = 0;
        this.mOnMeasureHeightMeasureSpec = 0;
        init(attributeSet, 0, 0);
    }

    public ConstraintLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f2247a = new SparseArray();
        this.mConstraintHelpers = new ArrayList<>(4);
        this.f2248b = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.f2249c = true;
        this.mOptimizationLevel = 257;
        this.mConstraintSet = null;
        this.f2250d = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap<>();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mTempMapIdToWidget = new SparseArray<>();
        this.f2251e = new Measurer(this);
        this.mOnMeasureWidthMeasureSpec = 0;
        this.mOnMeasureHeightMeasureSpec = 0;
        init(attributeSet, i2, 0);
    }

    @TargetApi(21)
    public ConstraintLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.f2247a = new SparseArray();
        this.mConstraintHelpers = new ArrayList<>(4);
        this.f2248b = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.f2249c = true;
        this.mOptimizationLevel = 257;
        this.mConstraintSet = null;
        this.f2250d = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap<>();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mTempMapIdToWidget = new SparseArray<>();
        this.f2251e = new Measurer(this);
        this.mOnMeasureWidthMeasureSpec = 0;
        this.mOnMeasureHeightMeasureSpec = 0;
        init(attributeSet, i2, i3);
    }

    private int getPaddingWidth() {
        int max = Math.max(0, getPaddingLeft()) + Math.max(0, getPaddingRight());
        int max2 = Build.VERSION.SDK_INT >= 17 ? Math.max(0, getPaddingEnd()) + Math.max(0, getPaddingStart()) : 0;
        return max2 > 0 ? max2 : max;
    }

    public static SharedValues getSharedValues() {
        if (sSharedValues == null) {
            sSharedValues = new SharedValues();
        }
        return sSharedValues;
    }

    private final ConstraintWidget getTargetWidget(int i2) {
        if (i2 == 0) {
            return this.f2248b;
        }
        View view = (View) this.f2247a.get(i2);
        if (view == null && (view = findViewById(i2)) != null && view != this && view.getParent() == this) {
            onViewAdded(view);
        }
        if (view == this) {
            return this.f2248b;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).v;
    }

    private void init(AttributeSet attributeSet, int i2, int i3) {
        this.f2248b.setCompanionWidget(this);
        this.f2248b.setMeasurer(this.f2251e);
        this.f2247a.put(getId(), this);
        this.mConstraintSet = null;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout, i2, i3);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i4 = 0; i4 < indexCount; i4++) {
                int index = obtainStyledAttributes.getIndex(i4);
                if (index == R.styleable.ConstraintLayout_Layout_android_minWidth) {
                    this.mMinWidth = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMinWidth);
                } else if (index == R.styleable.ConstraintLayout_Layout_android_minHeight) {
                    this.mMinHeight = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMinHeight);
                } else if (index == R.styleable.ConstraintLayout_Layout_android_maxWidth) {
                    this.mMaxWidth = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxWidth);
                } else if (index == R.styleable.ConstraintLayout_Layout_android_maxHeight) {
                    this.mMaxHeight = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxHeight);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.mOptimizationLevel = obtainStyledAttributes.getInt(index, this.mOptimizationLevel);
                } else if (index == R.styleable.ConstraintLayout_Layout_layoutDescription) {
                    int resourceId = obtainStyledAttributes.getResourceId(index, 0);
                    if (resourceId != 0) {
                        try {
                            f(resourceId);
                        } catch (Resources.NotFoundException unused) {
                            this.f2250d = null;
                        }
                    }
                } else if (index == R.styleable.ConstraintLayout_Layout_constraintSet) {
                    int resourceId2 = obtainStyledAttributes.getResourceId(index, 0);
                    try {
                        ConstraintSet constraintSet = new ConstraintSet();
                        this.mConstraintSet = constraintSet;
                        constraintSet.load(getContext(), resourceId2);
                    } catch (Resources.NotFoundException unused2) {
                        this.mConstraintSet = null;
                    }
                    this.mConstraintSetId = resourceId2;
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.f2248b.setOptimizationLevel(this.mOptimizationLevel);
    }

    private void markHierarchyDirty() {
        this.f2249c = true;
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
    }

    private void setChildrenConstraints() {
        boolean isInEditMode = isInEditMode();
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            ConstraintWidget viewWidget = getViewWidget(getChildAt(i2));
            if (viewWidget != null) {
                viewWidget.reset();
            }
        }
        if (isInEditMode) {
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                try {
                    String resourceName = getResources().getResourceName(childAt.getId());
                    setDesignInformation(0, resourceName, Integer.valueOf(childAt.getId()));
                    int indexOf = resourceName.indexOf(47);
                    if (indexOf != -1) {
                        resourceName = resourceName.substring(indexOf + 1);
                    }
                    getTargetWidget(childAt.getId()).setDebugName(resourceName);
                } catch (Resources.NotFoundException unused) {
                }
            }
        }
        if (this.mConstraintSetId != -1) {
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt2 = getChildAt(i4);
                if (childAt2.getId() == this.mConstraintSetId && (childAt2 instanceof Constraints)) {
                    this.mConstraintSet = ((Constraints) childAt2).getConstraintSet();
                }
            }
        }
        ConstraintSet constraintSet = this.mConstraintSet;
        if (constraintSet != null) {
            constraintSet.h(this, true);
        }
        this.f2248b.removeAllChildren();
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            for (int i5 = 0; i5 < size; i5++) {
                this.mConstraintHelpers.get(i5).updatePreLayout(this);
            }
        }
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt3 = getChildAt(i6);
            if (childAt3 instanceof Placeholder) {
                ((Placeholder) childAt3).updatePreLayout(this);
            }
        }
        this.mTempMapIdToWidget.clear();
        this.mTempMapIdToWidget.put(0, this.f2248b);
        this.mTempMapIdToWidget.put(getId(), this.f2248b);
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt4 = getChildAt(i7);
            this.mTempMapIdToWidget.put(childAt4.getId(), getViewWidget(childAt4));
        }
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt5 = getChildAt(i8);
            ConstraintWidget viewWidget2 = getViewWidget(childAt5);
            if (viewWidget2 != null) {
                LayoutParams layoutParams = (LayoutParams) childAt5.getLayoutParams();
                this.f2248b.add(viewWidget2);
                c(isInEditMode, childAt5, viewWidget2, layoutParams, this.mTempMapIdToWidget);
            }
        }
    }

    private void setWidgetBaseline(ConstraintWidget constraintWidget, LayoutParams layoutParams, SparseArray<ConstraintWidget> sparseArray, int i2, ConstraintAnchor.Type type) {
        View view = (View) this.f2247a.get(i2);
        ConstraintWidget constraintWidget2 = sparseArray.get(i2);
        if (constraintWidget2 == null || view == null || !(view.getLayoutParams() instanceof LayoutParams)) {
            return;
        }
        layoutParams.f2259g = true;
        ConstraintAnchor.Type type2 = ConstraintAnchor.Type.BASELINE;
        if (type == type2) {
            LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
            layoutParams2.f2259g = true;
            layoutParams2.v.setHasBaseline(true);
        }
        constraintWidget.getAnchor(type2).connect(constraintWidget2.getAnchor(type), layoutParams.baselineMargin, layoutParams.goneBaselineMargin, true);
        constraintWidget.setHasBaseline(true);
        constraintWidget.getAnchor(ConstraintAnchor.Type.TOP).reset();
        constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM).reset();
    }

    private boolean updateHierarchy() {
        int childCount = getChildCount();
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            } else if (getChildAt(i2).isLayoutRequested()) {
                z = true;
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            setChildrenConstraints();
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0196  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void c(boolean z, View view, ConstraintWidget constraintWidget, LayoutParams layoutParams, SparseArray sparseArray) {
        int i2;
        float f2;
        int i3;
        int i4;
        ConstraintWidget constraintWidget2;
        ConstraintAnchor.Type type;
        ConstraintAnchor.Type type2;
        int i5;
        ConstraintWidget constraintWidget3;
        ConstraintWidget constraintWidget4;
        ConstraintAnchor.Type type3;
        int i6;
        ConstraintWidget constraintWidget5;
        ConstraintAnchor.Type type4;
        int i7;
        ConstraintWidget constraintWidget6;
        ConstraintAnchor.Type type5;
        ConstraintAnchor.Type type6;
        int i8;
        int i9;
        ConstraintWidget constraintWidget7;
        int i10;
        ConstraintWidget constraintWidget8;
        ConstraintAnchor.Type type7;
        int i11;
        int i12;
        ConstraintWidget constraintWidget9;
        ConstraintAnchor.Type type8;
        int i13;
        ConstraintAnchor.Type type9;
        float f3;
        int i14;
        layoutParams.validate();
        layoutParams.helped = false;
        constraintWidget.setVisibility(view.getVisibility());
        if (layoutParams.f2262j) {
            constraintWidget.setInPlaceholder(true);
            constraintWidget.setVisibility(8);
        }
        constraintWidget.setCompanionWidget(view);
        if (view instanceof ConstraintHelper) {
            ((ConstraintHelper) view).resolveRtl(constraintWidget, this.f2248b.isRtl());
        }
        if (layoutParams.f2260h) {
            androidx.constraintlayout.core.widgets.Guideline guideline = (androidx.constraintlayout.core.widgets.Guideline) constraintWidget;
            int i15 = layoutParams.f2271s;
            int i16 = layoutParams.f2272t;
            float f4 = layoutParams.u;
            if (Build.VERSION.SDK_INT < 17) {
                i15 = layoutParams.guideBegin;
                i16 = layoutParams.guideEnd;
                f4 = layoutParams.guidePercent;
            }
            if (f4 != -1.0f) {
                guideline.setGuidePercent(f4);
                return;
            } else if (i15 != -1) {
                guideline.setGuideBegin(i15);
                return;
            } else if (i16 != -1) {
                guideline.setGuideEnd(i16);
                return;
            } else {
                return;
            }
        }
        int i17 = layoutParams.f2264l;
        int i18 = layoutParams.f2265m;
        int i19 = layoutParams.f2266n;
        int i20 = layoutParams.f2267o;
        int i21 = layoutParams.f2268p;
        int i22 = layoutParams.f2269q;
        float f5 = layoutParams.f2270r;
        if (Build.VERSION.SDK_INT < 17) {
            i17 = layoutParams.leftToLeft;
            int i23 = layoutParams.leftToRight;
            int i24 = layoutParams.rightToLeft;
            int i25 = layoutParams.rightToRight;
            int i26 = layoutParams.goneLeftMargin;
            int i27 = layoutParams.goneRightMargin;
            float f6 = layoutParams.horizontalBias;
            if (i17 == -1 && i23 == -1) {
                int i28 = layoutParams.startToStart;
                if (i28 != -1) {
                    i17 = i28;
                } else {
                    int i29 = layoutParams.startToEnd;
                    if (i29 != -1) {
                        i23 = i29;
                    }
                }
            }
            if (i24 == -1 && i25 == -1) {
                i3 = layoutParams.endToStart;
                if (i3 == -1) {
                    int i30 = layoutParams.endToEnd;
                    if (i30 != -1) {
                        i2 = i27;
                        f2 = f6;
                        i21 = i26;
                        i4 = i30;
                        i18 = i23;
                        i3 = i24;
                    }
                }
                i2 = i27;
                f2 = f6;
                i21 = i26;
                i4 = i25;
                i18 = i23;
            }
            i3 = i24;
            i2 = i27;
            f2 = f6;
            i21 = i26;
            i4 = i25;
            i18 = i23;
        } else {
            i2 = i22;
            f2 = f5;
            i3 = i19;
            i4 = i20;
        }
        int i31 = layoutParams.circleConstraint;
        if (i31 != -1) {
            ConstraintWidget constraintWidget10 = (ConstraintWidget) sparseArray.get(i31);
            if (constraintWidget10 != null) {
                constraintWidget.connectCircularConstraint(constraintWidget10, layoutParams.circleAngle, layoutParams.circleRadius);
            }
        } else if (i17 != -1) {
            constraintWidget2 = (ConstraintWidget) sparseArray.get(i17);
            if (constraintWidget2 != null) {
                type2 = ConstraintAnchor.Type.LEFT;
                i5 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
                constraintWidget3 = constraintWidget;
                type = type2;
                constraintWidget3.immediateConnect(type, constraintWidget2, type2, i5, i21);
            }
            if (i3 == -1) {
                constraintWidget4 = (ConstraintWidget) sparseArray.get(i3);
                if (constraintWidget4 != null) {
                    type4 = ConstraintAnchor.Type.RIGHT;
                    type3 = ConstraintAnchor.Type.LEFT;
                    i6 = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                    constraintWidget5 = constraintWidget;
                    constraintWidget5.immediateConnect(type4, constraintWidget4, type3, i6, i2);
                }
                i7 = layoutParams.topToTop;
                if (i7 == -1) {
                    constraintWidget6 = (ConstraintWidget) sparseArray.get(i7);
                    if (constraintWidget6 != null) {
                        type6 = ConstraintAnchor.Type.TOP;
                        i8 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
                        i9 = layoutParams.goneTopMargin;
                        constraintWidget7 = constraintWidget;
                        type5 = type6;
                        constraintWidget7.immediateConnect(type5, constraintWidget6, type6, i8, i9);
                    }
                    i10 = layoutParams.bottomToTop;
                    if (i10 == -1) {
                        constraintWidget8 = (ConstraintWidget) sparseArray.get(i10);
                        if (constraintWidget8 != null) {
                            type8 = ConstraintAnchor.Type.BOTTOM;
                            type7 = ConstraintAnchor.Type.TOP;
                            i11 = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                            i12 = layoutParams.goneBottomMargin;
                            constraintWidget9 = constraintWidget;
                            constraintWidget9.immediateConnect(type8, constraintWidget8, type7, i11, i12);
                        }
                        i13 = layoutParams.baselineToBaseline;
                        if (i13 == -1) {
                            type9 = ConstraintAnchor.Type.BASELINE;
                        } else {
                            i13 = layoutParams.baselineToTop;
                            if (i13 != -1) {
                                type9 = ConstraintAnchor.Type.TOP;
                            } else {
                                i13 = layoutParams.baselineToBottom;
                                if (i13 != -1) {
                                    type9 = ConstraintAnchor.Type.BOTTOM;
                                }
                                if (f2 >= 0.0f) {
                                    constraintWidget.setHorizontalBiasPercent(f2);
                                }
                                f3 = layoutParams.verticalBias;
                                if (f3 >= 0.0f) {
                                    constraintWidget.setVerticalBiasPercent(f3);
                                }
                            }
                        }
                        setWidgetBaseline(constraintWidget, layoutParams, sparseArray, i13, type9);
                        if (f2 >= 0.0f) {
                        }
                        f3 = layoutParams.verticalBias;
                        if (f3 >= 0.0f) {
                        }
                    } else {
                        int i32 = layoutParams.bottomToBottom;
                        if (i32 != -1 && (constraintWidget8 = (ConstraintWidget) sparseArray.get(i32)) != null) {
                            type7 = ConstraintAnchor.Type.BOTTOM;
                            i11 = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                            i12 = layoutParams.goneBottomMargin;
                            constraintWidget9 = constraintWidget;
                            type8 = type7;
                            constraintWidget9.immediateConnect(type8, constraintWidget8, type7, i11, i12);
                        }
                        i13 = layoutParams.baselineToBaseline;
                        if (i13 == -1) {
                        }
                        setWidgetBaseline(constraintWidget, layoutParams, sparseArray, i13, type9);
                        if (f2 >= 0.0f) {
                        }
                        f3 = layoutParams.verticalBias;
                        if (f3 >= 0.0f) {
                        }
                    }
                } else {
                    int i33 = layoutParams.topToBottom;
                    if (i33 != -1 && (constraintWidget6 = (ConstraintWidget) sparseArray.get(i33)) != null) {
                        type5 = ConstraintAnchor.Type.TOP;
                        type6 = ConstraintAnchor.Type.BOTTOM;
                        i8 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
                        i9 = layoutParams.goneTopMargin;
                        constraintWidget7 = constraintWidget;
                        constraintWidget7.immediateConnect(type5, constraintWidget6, type6, i8, i9);
                    }
                    i10 = layoutParams.bottomToTop;
                    if (i10 == -1) {
                    }
                }
            } else {
                if (i4 != -1 && (constraintWidget4 = (ConstraintWidget) sparseArray.get(i4)) != null) {
                    type3 = ConstraintAnchor.Type.RIGHT;
                    i6 = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                    constraintWidget5 = constraintWidget;
                    type4 = type3;
                    constraintWidget5.immediateConnect(type4, constraintWidget4, type3, i6, i2);
                }
                i7 = layoutParams.topToTop;
                if (i7 == -1) {
                }
            }
        } else {
            if (i18 != -1 && (constraintWidget2 = (ConstraintWidget) sparseArray.get(i18)) != null) {
                type = ConstraintAnchor.Type.LEFT;
                type2 = ConstraintAnchor.Type.RIGHT;
                i5 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
                constraintWidget3 = constraintWidget;
                constraintWidget3.immediateConnect(type, constraintWidget2, type2, i5, i21);
            }
            if (i3 == -1) {
            }
        }
        if (z && ((i14 = layoutParams.editorAbsoluteX) != -1 || layoutParams.editorAbsoluteY != -1)) {
            constraintWidget.setOrigin(i14, layoutParams.editorAbsoluteY);
        }
        if (layoutParams.f2257e) {
            constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            constraintWidget.setWidth(((ViewGroup.MarginLayoutParams) layoutParams).width);
            if (((ViewGroup.MarginLayoutParams) layoutParams).width == -2) {
                constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
            }
        } else if (((ViewGroup.MarginLayoutParams) layoutParams).width == -1) {
            constraintWidget.setHorizontalDimensionBehaviour(layoutParams.constrainedWidth ? ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT : ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
            constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT).mMargin = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
            constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT).mMargin = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        } else {
            constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
            constraintWidget.setWidth(0);
        }
        if (layoutParams.f2258f) {
            constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            constraintWidget.setHeight(((ViewGroup.MarginLayoutParams) layoutParams).height);
            if (((ViewGroup.MarginLayoutParams) layoutParams).height == -2) {
                constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
            }
        } else if (((ViewGroup.MarginLayoutParams) layoutParams).height == -1) {
            constraintWidget.setVerticalDimensionBehaviour(layoutParams.constrainedHeight ? ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT : ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
            constraintWidget.getAnchor(ConstraintAnchor.Type.TOP).mMargin = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
            constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM).mMargin = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        } else {
            constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
            constraintWidget.setHeight(0);
        }
        constraintWidget.setDimensionRatio(layoutParams.dimensionRatio);
        constraintWidget.setHorizontalWeight(layoutParams.horizontalWeight);
        constraintWidget.setVerticalWeight(layoutParams.verticalWeight);
        constraintWidget.setHorizontalChainStyle(layoutParams.horizontalChainStyle);
        constraintWidget.setVerticalChainStyle(layoutParams.verticalChainStyle);
        constraintWidget.setWrapBehaviorInParent(layoutParams.wrapBehaviorInParent);
        constraintWidget.setHorizontalMatchStyle(layoutParams.matchConstraintDefaultWidth, layoutParams.matchConstraintMinWidth, layoutParams.matchConstraintMaxWidth, layoutParams.matchConstraintPercentWidth);
        constraintWidget.setVerticalMatchStyle(layoutParams.matchConstraintDefaultHeight, layoutParams.matchConstraintMinHeight, layoutParams.matchConstraintMaxHeight, layoutParams.matchConstraintPercentHeight);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* renamed from: d */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        Object tag;
        int size;
        ArrayList<ConstraintHelper> arrayList = this.mConstraintHelpers;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            for (int i2 = 0; i2 < size; i2++) {
                this.mConstraintHelpers.get(i2).updatePreDraw(this);
            }
        }
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            float width = getWidth();
            float height = getHeight();
            int childCount = getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() != 8 && (tag = childAt.getTag()) != null && (tag instanceof String)) {
                    String[] split = ((String) tag).split(",");
                    if (split.length == 4) {
                        int parseInt = Integer.parseInt(split[0]);
                        int parseInt2 = Integer.parseInt(split[1]);
                        int parseInt3 = Integer.parseInt(split[2]);
                        int i4 = (int) ((parseInt / 1080.0f) * width);
                        int i5 = (int) ((parseInt2 / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(SupportMenu.CATEGORY_MASK);
                        float f2 = i4;
                        float f3 = i5;
                        float f4 = i4 + ((int) ((parseInt3 / 1080.0f) * width));
                        canvas.drawLine(f2, f3, f4, f3, paint);
                        float parseInt4 = i5 + ((int) ((Integer.parseInt(split[3]) / 1920.0f) * height));
                        canvas.drawLine(f4, f3, f4, parseInt4, paint);
                        canvas.drawLine(f4, parseInt4, f2, parseInt4, paint);
                        canvas.drawLine(f2, parseInt4, f2, f3, paint);
                        paint.setColor(-16711936);
                        canvas.drawLine(f2, f3, f4, parseInt4, paint);
                        canvas.drawLine(f2, parseInt4, f4, f3, paint);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean e() {
        if (Build.VERSION.SDK_INT >= 17) {
            return ((getContext().getApplicationInfo().flags & 4194304) != 0) && 1 == getLayoutDirection();
        }
        return false;
    }

    protected void f(int i2) {
        this.f2250d = new ConstraintLayoutStates(getContext(), this, i2);
    }

    public void fillMetrics(Metrics metrics) {
        this.mMetrics = metrics;
        this.f2248b.fillMetrics(metrics);
    }

    @Override // android.view.View
    public void forceLayout() {
        markHierarchyDirty();
        super.forceLayout();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void g(int i2, int i3, int i4, int i5, boolean z, boolean z2) {
        Measurer measurer = this.f2251e;
        int i6 = measurer.f2277e;
        int resolveSizeAndState = ViewGroup.resolveSizeAndState(i4 + measurer.f2276d, i2, 0);
        int min = Math.min(this.mMaxWidth, resolveSizeAndState & 16777215);
        int min2 = Math.min(this.mMaxHeight, ViewGroup.resolveSizeAndState(i5 + i6, i3, 0) & 16777215);
        if (z) {
            min |= 16777216;
        }
        if (z2) {
            min2 |= 16777216;
        }
        setMeasuredDimension(min, min2);
        this.mLastMeasureWidth = min;
        this.mLastMeasureHeight = min2;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public Object getDesignInformation(int i2, Object obj) {
        if (i2 == 0 && (obj instanceof String)) {
            String str = (String) obj;
            HashMap<String, Integer> hashMap = this.mDesignIds;
            if (hashMap == null || !hashMap.containsKey(str)) {
                return null;
            }
            return this.mDesignIds.get(str);
        }
        return null;
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getOptimizationLevel() {
        return this.f2248b.getOptimizationLevel();
    }

    public View getViewById(int i2) {
        return (View) this.f2247a.get(i2);
    }

    public final ConstraintWidget getViewWidget(View view) {
        if (view == this) {
            return this.f2248b;
        }
        if (view != null) {
            if (!(view.getLayoutParams() instanceof LayoutParams)) {
                view.setLayoutParams(generateLayoutParams(view.getLayoutParams()));
                if (!(view.getLayoutParams() instanceof LayoutParams)) {
                    return null;
                }
            }
            return ((LayoutParams) view.getLayoutParams()).v;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void h(ConstraintWidgetContainer constraintWidgetContainer, int i2, int i3, int i4) {
        int max;
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i3);
        int mode2 = View.MeasureSpec.getMode(i4);
        int size2 = View.MeasureSpec.getSize(i4);
        int max2 = Math.max(0, getPaddingTop());
        int max3 = Math.max(0, getPaddingBottom());
        int i5 = max2 + max3;
        int paddingWidth = getPaddingWidth();
        this.f2251e.captureLayoutInfo(i3, i4, max2, max3, paddingWidth, i5);
        if (Build.VERSION.SDK_INT >= 17) {
            int max4 = Math.max(0, getPaddingStart());
            int max5 = Math.max(0, getPaddingEnd());
            if (max4 <= 0 && max5 <= 0) {
                max4 = Math.max(0, getPaddingLeft());
            } else if (e()) {
                max4 = max5;
            }
            max = max4;
        } else {
            max = Math.max(0, getPaddingLeft());
        }
        int i6 = size - paddingWidth;
        int i7 = size2 - i5;
        i(constraintWidgetContainer, mode, i6, mode2, i7);
        constraintWidgetContainer.measure(i2, mode, i6, mode2, i7, this.mLastMeasureWidth, this.mLastMeasureHeight, max, max2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0025, code lost:
        if (r3 == 0) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002a, code lost:
        if (r3 == 0) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002c, code lost:
        r10 = java.lang.Math.max(0, r7.mMinWidth);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0044, code lost:
        if (r3 == 0) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0049, code lost:
        if (r3 == 0) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x004b, code lost:
        r12 = java.lang.Math.max(0, r7.mMinHeight);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void i(ConstraintWidgetContainer constraintWidgetContainer, int i2, int i3, int i4, int i5) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        Measurer measurer = this.f2251e;
        int i6 = measurer.f2277e;
        int i7 = measurer.f2276d;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
        int childCount = getChildCount();
        if (i2 != Integer.MIN_VALUE) {
            if (i2 == 0) {
                dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            } else if (i2 != 1073741824) {
                dimensionBehaviour = dimensionBehaviour2;
            } else {
                i3 = Math.min(this.mMaxWidth - i7, i3);
                dimensionBehaviour = dimensionBehaviour2;
            }
            i3 = 0;
        } else {
            dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        }
        if (i4 != Integer.MIN_VALUE) {
            if (i4 == 0) {
                dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            } else if (i4 == 1073741824) {
                i5 = Math.min(this.mMaxHeight - i6, i5);
            }
            i5 = 0;
        } else {
            dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        }
        if (i3 != constraintWidgetContainer.getWidth() || i5 != constraintWidgetContainer.getHeight()) {
            constraintWidgetContainer.invalidateMeasures();
        }
        constraintWidgetContainer.setX(0);
        constraintWidgetContainer.setY(0);
        constraintWidgetContainer.setMaxWidth(this.mMaxWidth - i7);
        constraintWidgetContainer.setMaxHeight(this.mMaxHeight - i6);
        constraintWidgetContainer.setMinWidth(0);
        constraintWidgetContainer.setMinHeight(0);
        constraintWidgetContainer.setHorizontalDimensionBehaviour(dimensionBehaviour);
        constraintWidgetContainer.setWidth(i3);
        constraintWidgetContainer.setVerticalDimensionBehaviour(dimensionBehaviour2);
        constraintWidgetContainer.setHeight(i5);
        constraintWidgetContainer.setMinWidth(this.mMinWidth - i7);
        constraintWidgetContainer.setMinHeight(this.mMinHeight - i6);
    }

    public void loadLayoutDescription(int i2) {
        if (i2 != 0) {
            try {
                this.f2250d = new ConstraintLayoutStates(getContext(), this, i2);
                return;
            } catch (Resources.NotFoundException unused) {
            }
        }
        this.f2250d = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        View content;
        int childCount = getChildCount();
        boolean isInEditMode = isInEditMode();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            ConstraintWidget constraintWidget = layoutParams.v;
            if ((childAt.getVisibility() != 8 || layoutParams.f2260h || layoutParams.f2261i || layoutParams.f2263k || isInEditMode) && !layoutParams.f2262j) {
                int x = constraintWidget.getX();
                int y = constraintWidget.getY();
                int width = constraintWidget.getWidth() + x;
                int height = constraintWidget.getHeight() + y;
                childAt.layout(x, y, width, height);
                if ((childAt instanceof Placeholder) && (content = ((Placeholder) childAt).getContent()) != null) {
                    content.setVisibility(0);
                    content.layout(x, y, width, height);
                }
            }
        }
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            for (int i7 = 0; i7 < size; i7++) {
                this.mConstraintHelpers.get(i7).updatePostLayout(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        if (this.mOnMeasureWidthMeasureSpec == i2) {
            int i4 = this.mOnMeasureHeightMeasureSpec;
        }
        if (!this.f2249c) {
            int childCount = getChildCount();
            int i5 = 0;
            while (true) {
                if (i5 >= childCount) {
                    break;
                } else if (getChildAt(i5).isLayoutRequested()) {
                    this.f2249c = true;
                    break;
                } else {
                    i5++;
                }
            }
        }
        boolean z = this.f2249c;
        this.mOnMeasureWidthMeasureSpec = i2;
        this.mOnMeasureHeightMeasureSpec = i3;
        this.f2248b.setRtl(e());
        if (this.f2249c) {
            this.f2249c = false;
            if (updateHierarchy()) {
                this.f2248b.updateHierarchy();
            }
        }
        h(this.f2248b, this.mOptimizationLevel, i2, i3);
        g(i2, i3, this.f2248b.getWidth(), this.f2248b.getHeight(), this.f2248b.isWidthMeasuredTooSmall(), this.f2248b.isHeightMeasuredTooSmall());
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        ConstraintWidget viewWidget = getViewWidget(view);
        if ((view instanceof Guideline) && !(viewWidget instanceof androidx.constraintlayout.core.widgets.Guideline)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            androidx.constraintlayout.core.widgets.Guideline guideline = new androidx.constraintlayout.core.widgets.Guideline();
            layoutParams.v = guideline;
            layoutParams.f2260h = true;
            guideline.setOrientation(layoutParams.orientation);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper constraintHelper = (ConstraintHelper) view;
            constraintHelper.validateParams();
            ((LayoutParams) view.getLayoutParams()).f2261i = true;
            if (!this.mConstraintHelpers.contains(constraintHelper)) {
                this.mConstraintHelpers.add(constraintHelper);
            }
        }
        this.f2247a.put(view.getId(), view);
        this.f2249c = true;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        this.f2247a.remove(view.getId());
        this.f2248b.remove(getViewWidget(view));
        this.mConstraintHelpers.remove(view);
        this.f2249c = true;
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        markHierarchyDirty();
        super.requestLayout();
    }

    public void setConstraintSet(ConstraintSet constraintSet) {
        this.mConstraintSet = constraintSet;
    }

    public void setDesignInformation(int i2, Object obj, Object obj2) {
        if (i2 == 0 && (obj instanceof String) && (obj2 instanceof Integer)) {
            if (this.mDesignIds == null) {
                this.mDesignIds = new HashMap<>();
            }
            String str = (String) obj;
            int indexOf = str.indexOf("/");
            if (indexOf != -1) {
                str = str.substring(indexOf + 1);
            }
            this.mDesignIds.put(str, Integer.valueOf(((Integer) obj2).intValue()));
        }
    }

    @Override // android.view.View
    public void setId(int i2) {
        this.f2247a.remove(getId());
        super.setId(i2);
        this.f2247a.put(getId(), this);
    }

    public void setMaxHeight(int i2) {
        if (i2 == this.mMaxHeight) {
            return;
        }
        this.mMaxHeight = i2;
        requestLayout();
    }

    public void setMaxWidth(int i2) {
        if (i2 == this.mMaxWidth) {
            return;
        }
        this.mMaxWidth = i2;
        requestLayout();
    }

    public void setMinHeight(int i2) {
        if (i2 == this.mMinHeight) {
            return;
        }
        this.mMinHeight = i2;
        requestLayout();
    }

    public void setMinWidth(int i2) {
        if (i2 == this.mMinWidth) {
            return;
        }
        this.mMinWidth = i2;
        requestLayout();
    }

    public void setOnConstraintsChanged(ConstraintsChangedListener constraintsChangedListener) {
        this.mConstraintsChangedListener = constraintsChangedListener;
        ConstraintLayoutStates constraintLayoutStates = this.f2250d;
        if (constraintLayoutStates != null) {
            constraintLayoutStates.setOnConstraintsChanged(constraintsChangedListener);
        }
    }

    public void setOptimizationLevel(int i2) {
        this.mOptimizationLevel = i2;
        this.f2248b.setOptimizationLevel(i2);
    }

    public void setState(int i2, int i3, int i4) {
        ConstraintLayoutStates constraintLayoutStates = this.f2250d;
        if (constraintLayoutStates != null) {
            constraintLayoutStates.updateConstraints(i2, i3, i4);
        }
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
