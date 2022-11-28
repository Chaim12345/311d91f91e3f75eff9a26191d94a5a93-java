package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.Cache;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.Metrics;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.state.WidgetFrame;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.analyzer.ChainRun;
import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun;
import androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes.dex */
public class ConstraintWidget {
    public static final int ANCHOR_BASELINE = 4;
    public static final int ANCHOR_BOTTOM = 3;
    public static final int ANCHOR_LEFT = 0;
    public static final int ANCHOR_RIGHT = 1;
    public static final int ANCHOR_TOP = 2;
    private static final boolean AUTOTAG_CENTER = false;
    public static final int BOTH = 2;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static float DEFAULT_BIAS = 0.5f;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_RATIO = 3;
    public static final int MATCH_CONSTRAINT_RATIO_RESOLVED = 4;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    public static final int UNKNOWN = -1;
    private static final boolean USE_WRAP_DIMENSION_FOR_SPREAD = false;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    private static final int WRAP = -2;
    public static final int WRAP_BEHAVIOR_HORIZONTAL_ONLY = 1;
    public static final int WRAP_BEHAVIOR_INCLUDED = 0;
    public static final int WRAP_BEHAVIOR_SKIPPED = 3;
    public static final int WRAP_BEHAVIOR_VERTICAL_ONLY = 2;
    boolean A;
    boolean B;
    boolean C;
    int D;
    int E;
    boolean F;
    boolean G;
    protected ConstraintWidget[] H;
    protected ConstraintWidget[] I;
    ConstraintWidget J;
    ConstraintWidget K;
    private boolean OPTIMIZE_WRAP;
    private boolean OPTIMIZE_WRAP_ON_RESOLVED;

    /* renamed from: a  reason: collision with root package name */
    int f1949a;

    /* renamed from: b  reason: collision with root package name */
    float f1950b;

    /* renamed from: c  reason: collision with root package name */
    ConstraintAnchor f1951c;

    /* renamed from: d  reason: collision with root package name */
    ConstraintAnchor f1952d;

    /* renamed from: e  reason: collision with root package name */
    protected ArrayList f1953e;

    /* renamed from: f  reason: collision with root package name */
    int f1954f;
    public WidgetFrame frame;

    /* renamed from: g  reason: collision with root package name */
    int f1955g;

    /* renamed from: h  reason: collision with root package name */
    protected int f1956h;
    private boolean hasBaseline;
    public ChainRun horizontalChainRun;
    public int horizontalGroup;
    public HorizontalWidgetRun horizontalRun;
    private boolean horizontalSolvingPass;

    /* renamed from: i  reason: collision with root package name */
    protected int f1957i;
    private boolean inPlaceholder;
    public boolean[] isTerminalWidget;

    /* renamed from: j  reason: collision with root package name */
    protected int f1958j;

    /* renamed from: k  reason: collision with root package name */
    int f1959k;

    /* renamed from: l  reason: collision with root package name */
    int f1960l;

    /* renamed from: m  reason: collision with root package name */
    protected int f1961m;
    public ConstraintAnchor mBaseline;
    public ConstraintAnchor mBottom;
    public ConstraintAnchor mCenter;
    private float mCircleConstraintAngle;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    public float mDimensionRatio;
    private int mHeightOverride;
    public int mHorizontalResolution;
    private boolean mInVirtualLayout;
    public boolean mIsHeightWrapContent;
    private boolean[] mIsInBarrier;
    public boolean mIsWidthWrapContent;
    private int mLastHorizontalMeasureSpec;
    private int mLastVerticalMeasureSpec;
    public ConstraintAnchor mLeft;
    public ConstraintAnchor[] mListAnchors;
    public DimensionBehaviour[] mListDimensionBehaviors;
    public int mMatchConstraintDefaultHeight;
    public int mMatchConstraintDefaultWidth;
    public int mMatchConstraintMaxHeight;
    public int mMatchConstraintMaxWidth;
    public int mMatchConstraintMinHeight;
    public int mMatchConstraintMinWidth;
    public float mMatchConstraintPercentHeight;
    public float mMatchConstraintPercentWidth;
    private int[] mMaxDimension;
    private boolean mMeasureRequested;
    public ConstraintWidget mParent;
    public int[] mResolvedMatchConstraintDefault;
    public ConstraintAnchor mRight;
    public ConstraintAnchor mTop;
    private String mType;
    public int mVerticalResolution;
    private int mVisibility;
    public float[] mWeight;
    private int mWidthOverride;
    private int mWrapBehaviorInParent;
    public boolean measured;

    /* renamed from: n  reason: collision with root package name */
    protected int f1962n;

    /* renamed from: o  reason: collision with root package name */
    int f1963o;

    /* renamed from: p  reason: collision with root package name */
    protected int f1964p;

    /* renamed from: q  reason: collision with root package name */
    protected int f1965q;

    /* renamed from: r  reason: collision with root package name */
    float f1966r;
    private boolean resolvedHorizontal;
    private boolean resolvedVertical;
    public WidgetRun[] run;

    /* renamed from: s  reason: collision with root package name */
    float f1967s;
    public String stringId;

    /* renamed from: t  reason: collision with root package name */
    int f1968t;
    int u;
    int v;
    public ChainRun verticalChainRun;
    public int verticalGroup;
    public VerticalWidgetRun verticalRun;
    private boolean verticalSolvingPass;
    int w;
    boolean x;
    boolean y;
    boolean z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.constraintlayout.core.widgets.ConstraintWidget$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1969a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f1970b;

        static {
            int[] iArr = new int[DimensionBehaviour.values().length];
            f1970b = iArr;
            try {
                iArr[DimensionBehaviour.FIXED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1970b[DimensionBehaviour.WRAP_CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1970b[DimensionBehaviour.MATCH_PARENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1970b[DimensionBehaviour.MATCH_CONSTRAINT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ConstraintAnchor.Type.values().length];
            f1969a = iArr2;
            try {
                iArr2[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1969a[ConstraintAnchor.Type.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1969a[ConstraintAnchor.Type.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1969a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1969a[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1969a[ConstraintAnchor.Type.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1969a[ConstraintAnchor.Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1969a[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f1969a[ConstraintAnchor.Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public ConstraintWidget() {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.horizontalRun = null;
        this.verticalRun = null;
        this.isTerminalWidget = new boolean[]{true, true};
        this.mMeasureRequested = true;
        this.OPTIMIZE_WRAP = false;
        this.OPTIMIZE_WRAP_ON_RESOLVED = true;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
        this.frame = new WidgetFrame(this);
        this.resolvedHorizontal = false;
        this.resolvedVertical = false;
        this.horizontalSolvingPass = false;
        this.verticalSolvingPass = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mWrapBehaviorInParent = 0;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.f1949a = -1;
        this.f1950b = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.hasBaseline = false;
        this.mInVirtualLayout = false;
        this.mLastHorizontalMeasureSpec = 0;
        this.mLastVerticalMeasureSpec = 0;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.f1951c = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.f1952d = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.f1953e = new ArrayList();
        this.mIsInBarrier = new boolean[2];
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors = new DimensionBehaviour[]{dimensionBehaviour, dimensionBehaviour};
        this.mParent = null;
        this.f1954f = 0;
        this.f1955g = 0;
        this.mDimensionRatio = 0.0f;
        this.f1956h = -1;
        this.f1957i = 0;
        this.f1958j = 0;
        this.f1959k = 0;
        this.f1960l = 0;
        this.f1961m = 0;
        this.f1962n = 0;
        this.f1963o = 0;
        float f2 = DEFAULT_BIAS;
        this.f1966r = f2;
        this.f1967s = f2;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.D = 0;
        this.E = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.H = new ConstraintWidget[]{null, null};
        this.I = new ConstraintWidget[]{null, null};
        this.J = null;
        this.K = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        addAnchors();
    }

    public ConstraintWidget(int i2, int i3) {
        this(0, 0, i2, i3);
    }

    public ConstraintWidget(int i2, int i3, int i4, int i5) {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.horizontalRun = null;
        this.verticalRun = null;
        this.isTerminalWidget = new boolean[]{true, true};
        this.mMeasureRequested = true;
        this.OPTIMIZE_WRAP = false;
        this.OPTIMIZE_WRAP_ON_RESOLVED = true;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
        this.frame = new WidgetFrame(this);
        this.resolvedHorizontal = false;
        this.resolvedVertical = false;
        this.horizontalSolvingPass = false;
        this.verticalSolvingPass = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mWrapBehaviorInParent = 0;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.f1949a = -1;
        this.f1950b = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.hasBaseline = false;
        this.mInVirtualLayout = false;
        this.mLastHorizontalMeasureSpec = 0;
        this.mLastVerticalMeasureSpec = 0;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.f1951c = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.f1952d = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.f1953e = new ArrayList();
        this.mIsInBarrier = new boolean[2];
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors = new DimensionBehaviour[]{dimensionBehaviour, dimensionBehaviour};
        this.mParent = null;
        this.f1954f = 0;
        this.f1955g = 0;
        this.mDimensionRatio = 0.0f;
        this.f1956h = -1;
        this.f1957i = 0;
        this.f1958j = 0;
        this.f1959k = 0;
        this.f1960l = 0;
        this.f1961m = 0;
        this.f1962n = 0;
        this.f1963o = 0;
        float f2 = DEFAULT_BIAS;
        this.f1966r = f2;
        this.f1967s = f2;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.D = 0;
        this.E = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.H = new ConstraintWidget[]{null, null};
        this.I = new ConstraintWidget[]{null, null};
        this.J = null;
        this.K = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        this.f1957i = i2;
        this.f1958j = i3;
        this.f1954f = i4;
        this.f1955g = i5;
        addAnchors();
    }

    public ConstraintWidget(String str) {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.horizontalRun = null;
        this.verticalRun = null;
        this.isTerminalWidget = new boolean[]{true, true};
        this.mMeasureRequested = true;
        this.OPTIMIZE_WRAP = false;
        this.OPTIMIZE_WRAP_ON_RESOLVED = true;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
        this.frame = new WidgetFrame(this);
        this.resolvedHorizontal = false;
        this.resolvedVertical = false;
        this.horizontalSolvingPass = false;
        this.verticalSolvingPass = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mWrapBehaviorInParent = 0;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.f1949a = -1;
        this.f1950b = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.hasBaseline = false;
        this.mInVirtualLayout = false;
        this.mLastHorizontalMeasureSpec = 0;
        this.mLastVerticalMeasureSpec = 0;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.f1951c = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.f1952d = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.f1953e = new ArrayList();
        this.mIsInBarrier = new boolean[2];
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors = new DimensionBehaviour[]{dimensionBehaviour, dimensionBehaviour};
        this.mParent = null;
        this.f1954f = 0;
        this.f1955g = 0;
        this.mDimensionRatio = 0.0f;
        this.f1956h = -1;
        this.f1957i = 0;
        this.f1958j = 0;
        this.f1959k = 0;
        this.f1960l = 0;
        this.f1961m = 0;
        this.f1962n = 0;
        this.f1963o = 0;
        float f2 = DEFAULT_BIAS;
        this.f1966r = f2;
        this.f1967s = f2;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.D = 0;
        this.E = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.H = new ConstraintWidget[]{null, null};
        this.I = new ConstraintWidget[]{null, null};
        this.J = null;
        this.K = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        addAnchors();
        setDebugName(str);
    }

    public ConstraintWidget(String str, int i2, int i3) {
        this(i2, i3);
        setDebugName(str);
    }

    public ConstraintWidget(String str, int i2, int i3, int i4, int i5) {
        this(i2, i3, i4, i5);
        setDebugName(str);
    }

    private void addAnchors() {
        this.f1953e.add(this.mLeft);
        this.f1953e.add(this.mTop);
        this.f1953e.add(this.mRight);
        this.f1953e.add(this.mBottom);
        this.f1953e.add(this.f1951c);
        this.f1953e.add(this.f1952d);
        this.f1953e.add(this.mCenter);
        this.f1953e.add(this.mBaseline);
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x039f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:241:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x03f0  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0400  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0409  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x042b  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x042f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0449  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x0493  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x04a5 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:329:0x04cb A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:348:0x04fb  */
    /* JADX WARN: Removed duplicated region for block: B:378:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ea  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void applyConstraints(LinearSystem linearSystem, boolean z, boolean z2, boolean z3, boolean z4, SolverVariable solverVariable, SolverVariable solverVariable2, DimensionBehaviour dimensionBehaviour, boolean z5, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i2, int i3, int i4, int i5, float f2, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, int i6, int i7, int i8, int i9, float f3, boolean z11) {
        int i10;
        boolean z12;
        int i11;
        SolverVariable solverVariable3;
        int i12;
        int i13;
        int i14;
        int i15;
        SolverVariable solverVariable4;
        SolverVariable solverVariable5;
        SolverVariable solverVariable6;
        int i16;
        boolean z13;
        boolean z14;
        SolverVariable createObjectVariable;
        ConstraintWidget constraintWidget;
        ConstraintAnchor.Type type;
        SolverVariable solverVariable7;
        SolverVariable solverVariable8;
        SolverVariable solverVariable9;
        int i17;
        SolverVariable solverVariable10;
        int i18;
        int i19;
        int i20;
        ConstraintAnchor constraintAnchor3;
        SolverVariable solverVariable11;
        int i21;
        SolverVariable solverVariable12;
        int i22;
        int i23;
        int i24;
        boolean z15;
        boolean z16;
        boolean z17;
        boolean z18;
        int i25;
        ConstraintWidget constraintWidget2;
        ConstraintWidget constraintWidget3;
        SolverVariable solverVariable13;
        SolverVariable solverVariable14;
        boolean z19;
        SolverVariable solverVariable15;
        ConstraintWidget constraintWidget4;
        int i26;
        int i27;
        int i28;
        int i29;
        boolean z20;
        int i30;
        int i31;
        int i32;
        boolean z21;
        int i33;
        boolean z22;
        ConstraintWidget constraintWidget5;
        int i34;
        SolverVariable solverVariable16;
        int i35;
        ConstraintWidget constraintWidget6;
        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(constraintAnchor);
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(constraintAnchor2);
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(constraintAnchor.getTarget());
        SolverVariable createObjectVariable5 = linearSystem.createObjectVariable(constraintAnchor2.getTarget());
        if (LinearSystem.getMetrics() != null) {
            LinearSystem.getMetrics().nonresolvedWidgets++;
        }
        boolean isConnected = constraintAnchor.isConnected();
        boolean isConnected2 = constraintAnchor2.isConnected();
        boolean isConnected3 = this.mCenter.isConnected();
        int i36 = isConnected2 ? (isConnected ? 1 : 0) + 1 : isConnected ? 1 : 0;
        if (isConnected3) {
            i36++;
        }
        int i37 = z6 ? 3 : i6;
        int i38 = AnonymousClass1.f1970b[dimensionBehaviour.ordinal()];
        if (i38 == 1 || i38 == 2 || i38 == 3 || i38 != 4) {
            i10 = i37;
        } else {
            i10 = i37;
            if (i10 != 4) {
                z12 = true;
                i11 = this.mWidthOverride;
                if (i11 == -1 && z) {
                    this.mWidthOverride = -1;
                    solverVariable3 = createObjectVariable5;
                    z12 = false;
                } else {
                    i11 = i3;
                    solverVariable3 = createObjectVariable5;
                }
                i12 = this.mHeightOverride;
                if (i12 != -1 && !z) {
                    this.mHeightOverride = -1;
                    i11 = i12;
                    z12 = false;
                }
                if (this.mVisibility == 8) {
                    i11 = 0;
                    z12 = false;
                }
                if (z11) {
                    if (!isConnected && !isConnected2 && !isConnected3) {
                        linearSystem.addEquality(createObjectVariable2, i2);
                    } else if (isConnected && !isConnected2) {
                        linearSystem.addEquality(createObjectVariable2, createObjectVariable4, constraintAnchor.getMargin(), 8);
                    }
                }
                if (z12) {
                    if (z5) {
                        linearSystem.addEquality(createObjectVariable3, createObjectVariable2, 0, 3);
                        if (i4 > 0) {
                            linearSystem.addGreaterThan(createObjectVariable3, createObjectVariable2, i4, 8);
                        }
                        if (i5 < Integer.MAX_VALUE) {
                            linearSystem.addLowerThan(createObjectVariable3, createObjectVariable2, i5, 8);
                        }
                    } else {
                        linearSystem.addEquality(createObjectVariable3, createObjectVariable2, i11, 8);
                    }
                    i13 = i9;
                    i15 = i36;
                    solverVariable4 = createObjectVariable4;
                    solverVariable5 = createObjectVariable3;
                    z13 = z12;
                    solverVariable6 = solverVariable3;
                    z14 = z4;
                    i16 = i8;
                } else if (i36 == 2 || z6 || !(i10 == 1 || i10 == 0)) {
                    int i39 = i8 == -2 ? i11 : i8;
                    i13 = i9 == -2 ? i11 : i9;
                    if (i11 > 0 && i10 != 1) {
                        i11 = 0;
                    }
                    if (i39 > 0) {
                        linearSystem.addGreaterThan(createObjectVariable3, createObjectVariable2, i39, 8);
                        i11 = Math.max(i11, i39);
                    }
                    if (i13 > 0) {
                        if ((z2 && i10 == 1) ? false : true) {
                            i14 = 8;
                            linearSystem.addLowerThan(createObjectVariable3, createObjectVariable2, i13, 8);
                        } else {
                            i14 = 8;
                        }
                        i11 = Math.min(i11, i13);
                    } else {
                        i14 = 8;
                    }
                    if (i10 == 1) {
                        if (z2) {
                            linearSystem.addEquality(createObjectVariable3, createObjectVariable2, i11, i14);
                        } else {
                            linearSystem.addEquality(createObjectVariable3, createObjectVariable2, i11, 5);
                            linearSystem.addLowerThan(createObjectVariable3, createObjectVariable2, i11, i14);
                        }
                        i15 = i36;
                        solverVariable4 = createObjectVariable4;
                        solverVariable5 = createObjectVariable3;
                        z13 = z12;
                        solverVariable6 = solverVariable3;
                        z14 = z4;
                        i16 = i39;
                    } else if (i10 == 2) {
                        ConstraintAnchor.Type type2 = constraintAnchor.getType();
                        ConstraintAnchor.Type type3 = ConstraintAnchor.Type.TOP;
                        if (type2 == type3 || constraintAnchor.getType() == ConstraintAnchor.Type.BOTTOM) {
                            createObjectVariable = linearSystem.createObjectVariable(this.mParent.getAnchor(type3));
                            constraintWidget = this.mParent;
                            type = ConstraintAnchor.Type.BOTTOM;
                        } else {
                            createObjectVariable = linearSystem.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.LEFT));
                            constraintWidget = this.mParent;
                            type = ConstraintAnchor.Type.RIGHT;
                        }
                        i15 = i36;
                        solverVariable6 = solverVariable3;
                        i16 = i39;
                        solverVariable4 = createObjectVariable4;
                        solverVariable5 = createObjectVariable3;
                        linearSystem.addConstraint(linearSystem.createRow().createRowDimensionRatio(createObjectVariable3, createObjectVariable2, linearSystem.createObjectVariable(constraintWidget.getAnchor(type)), createObjectVariable, f3));
                        if (z2) {
                            z12 = false;
                        }
                        z13 = z12;
                        z14 = z4;
                    } else {
                        i15 = i36;
                        solverVariable4 = createObjectVariable4;
                        solverVariable5 = createObjectVariable3;
                        solverVariable6 = solverVariable3;
                        i16 = i39;
                        z13 = z12;
                        z14 = true;
                    }
                } else {
                    int max = Math.max(i8, i11);
                    if (i9 > 0) {
                        max = Math.min(i9, max);
                    }
                    linearSystem.addEquality(createObjectVariable3, createObjectVariable2, max, 8);
                    z14 = z4;
                    i13 = i9;
                    i15 = i36;
                    solverVariable5 = createObjectVariable3;
                    z13 = false;
                    solverVariable6 = solverVariable3;
                    i16 = i8;
                    solverVariable4 = createObjectVariable4;
                }
                if (z11) {
                    solverVariable7 = solverVariable;
                    solverVariable8 = solverVariable2;
                    solverVariable9 = solverVariable5;
                    i17 = 0;
                    solverVariable10 = createObjectVariable2;
                    i18 = i15;
                    i19 = 2;
                } else if (z8) {
                    solverVariable7 = solverVariable;
                    solverVariable8 = solverVariable2;
                    i18 = i15;
                    solverVariable9 = solverVariable5;
                    i17 = 0;
                    i19 = 2;
                    solverVariable10 = createObjectVariable2;
                } else {
                    if (!isConnected && !isConnected2 && !isConnected3) {
                        solverVariable15 = solverVariable5;
                        i27 = 5;
                        i28 = 0;
                    } else if (!isConnected || isConnected2) {
                        if (!isConnected && isConnected2) {
                            linearSystem.addEquality(solverVariable5, solverVariable6, -constraintAnchor2.getMargin(), 8);
                            if (z2) {
                                if (this.OPTIMIZE_WRAP && createObjectVariable2.isFinalValue && (constraintWidget5 = this.mParent) != null) {
                                    ConstraintWidgetContainer constraintWidgetContainer = (ConstraintWidgetContainer) constraintWidget5;
                                    if (z) {
                                        constraintWidgetContainer.addHorizontalWrapMinVariable(constraintAnchor);
                                    } else {
                                        constraintWidgetContainer.g(constraintAnchor);
                                    }
                                } else {
                                    i27 = 5;
                                    linearSystem.addGreaterThan(createObjectVariable2, solverVariable, 0, 5);
                                    i28 = 0;
                                    solverVariable15 = solverVariable5;
                                }
                            }
                        } else if (isConnected && isConnected2) {
                            ConstraintWidget constraintWidget7 = constraintAnchor.mTarget.mOwner;
                            ConstraintWidget constraintWidget8 = constraintAnchor2.mTarget.mOwner;
                            ConstraintWidget parent = getParent();
                            if (z13) {
                                if (i10 == 0) {
                                    if (i13 != 0 || i16 != 0) {
                                        z21 = false;
                                        i31 = 5;
                                        i33 = 5;
                                        z22 = true;
                                        z15 = true;
                                    } else if (solverVariable4.isFinalValue && solverVariable6.isFinalValue) {
                                        linearSystem.addEquality(createObjectVariable2, solverVariable4, constraintAnchor.getMargin(), 8);
                                        linearSystem.addEquality(solverVariable5, solverVariable6, -constraintAnchor2.getMargin(), 8);
                                        return;
                                    } else {
                                        z22 = false;
                                        z15 = false;
                                        i31 = 8;
                                        i33 = 8;
                                        z21 = true;
                                    }
                                    if ((constraintWidget7 instanceof Barrier) || (constraintWidget8 instanceof Barrier)) {
                                        solverVariable12 = solverVariable2;
                                        i22 = i31;
                                        i23 = 6;
                                        z17 = z21;
                                        z16 = z22;
                                        i24 = 4;
                                        if (z15 || solverVariable4 != solverVariable6 || constraintWidget7 == parent) {
                                            z18 = true;
                                        } else {
                                            z15 = false;
                                            z18 = false;
                                        }
                                        if (z16) {
                                            i25 = i10;
                                            constraintWidget2 = parent;
                                            constraintWidget3 = constraintWidget8;
                                            solverVariable13 = solverVariable5;
                                            solverVariable14 = createObjectVariable2;
                                            z19 = z2;
                                        } else {
                                            if (z13 || z7 || z9 || solverVariable4 != solverVariable || solverVariable6 != solverVariable12) {
                                                z19 = z2;
                                                i29 = i23;
                                                z20 = z18;
                                                i30 = i22;
                                            } else {
                                                z19 = false;
                                                i30 = 8;
                                                i29 = 8;
                                                z20 = false;
                                            }
                                            i25 = i10;
                                            constraintWidget2 = parent;
                                            constraintWidget3 = constraintWidget8;
                                            SolverVariable solverVariable17 = solverVariable5;
                                            solverVariable13 = solverVariable5;
                                            solverVariable14 = createObjectVariable2;
                                            linearSystem.addCentering(createObjectVariable2, solverVariable4, constraintAnchor.getMargin(), f2, solverVariable6, solverVariable17, constraintAnchor2.getMargin(), i29);
                                            i22 = i30;
                                            z18 = z20;
                                        }
                                        if (this.mVisibility != 8 && !constraintAnchor2.hasDependents()) {
                                            return;
                                        }
                                        if (z15) {
                                            solverVariable15 = solverVariable13;
                                        } else {
                                            if (z19 && solverVariable4 != solverVariable6 && !z13 && ((constraintWidget7 instanceof Barrier) || (constraintWidget3 instanceof Barrier))) {
                                                i22 = 6;
                                            }
                                            linearSystem.addGreaterThan(solverVariable14, solverVariable4, constraintAnchor.getMargin(), i22);
                                            solverVariable15 = solverVariable13;
                                            linearSystem.addLowerThan(solverVariable15, solverVariable6, -constraintAnchor2.getMargin(), i22);
                                        }
                                        if (z19 || !z10 || (constraintWidget7 instanceof Barrier) || (constraintWidget3 instanceof Barrier)) {
                                            constraintWidget4 = constraintWidget2;
                                        } else {
                                            constraintWidget4 = constraintWidget2;
                                            if (constraintWidget3 != constraintWidget4) {
                                                i22 = 6;
                                                i26 = 6;
                                                z18 = true;
                                                if (z18) {
                                                    if (z17 && (!z9 || z3)) {
                                                        int i40 = (constraintWidget7 == constraintWidget4 || constraintWidget3 == constraintWidget4) ? 6 : i26;
                                                        i40 = ((constraintWidget7 instanceof Guideline) || (constraintWidget3 instanceof Guideline)) ? 5 : 5;
                                                        i40 = ((constraintWidget7 instanceof Barrier) || (constraintWidget3 instanceof Barrier)) ? 5 : 5;
                                                        if (z9) {
                                                            i40 = 5;
                                                        }
                                                        i26 = Math.max(i40, i26);
                                                    }
                                                    if (z19) {
                                                        i26 = (z6 && !z9 && (constraintWidget7 == constraintWidget4 || constraintWidget3 == constraintWidget4)) ? 4 : Math.min(i22, i26);
                                                    }
                                                    linearSystem.addEquality(solverVariable14, solverVariable4, constraintAnchor.getMargin(), i26);
                                                    linearSystem.addEquality(solverVariable15, solverVariable6, -constraintAnchor2.getMargin(), i26);
                                                }
                                                if (z19) {
                                                    int margin = solverVariable == solverVariable4 ? constraintAnchor.getMargin() : 0;
                                                    if (solverVariable4 != solverVariable) {
                                                        linearSystem.addGreaterThan(solverVariable14, solverVariable, margin, 5);
                                                    }
                                                }
                                                if (z19 || !z13 || i4 != 0 || i16 != 0) {
                                                    i27 = 5;
                                                    i28 = 0;
                                                } else if (z13 && i25 == 3) {
                                                    i28 = 0;
                                                    linearSystem.addGreaterThan(solverVariable15, solverVariable14, 0, 8);
                                                    i27 = 5;
                                                } else {
                                                    i28 = 0;
                                                    i27 = 5;
                                                    linearSystem.addGreaterThan(solverVariable15, solverVariable14, 0, 5);
                                                }
                                            }
                                        }
                                        i26 = i24;
                                        if (z18) {
                                        }
                                        if (z19) {
                                        }
                                        if (z19) {
                                        }
                                        i27 = 5;
                                        i28 = 0;
                                    } else {
                                        solverVariable12 = solverVariable2;
                                        z17 = z21;
                                        z16 = z22;
                                        i24 = i33;
                                        i22 = i31;
                                        i23 = 6;
                                        if (z15) {
                                        }
                                        z18 = true;
                                        if (z16) {
                                        }
                                        if (this.mVisibility != 8) {
                                        }
                                        if (z15) {
                                        }
                                        if (z19) {
                                        }
                                        constraintWidget4 = constraintWidget2;
                                        i26 = i24;
                                        if (z18) {
                                        }
                                        if (z19) {
                                        }
                                        if (z19) {
                                        }
                                        i27 = 5;
                                        i28 = 0;
                                    }
                                } else if (i10 == 2) {
                                    if (!(constraintWidget7 instanceof Barrier) && !(constraintWidget8 instanceof Barrier)) {
                                        solverVariable12 = solverVariable2;
                                        i23 = 6;
                                        i22 = 5;
                                        i24 = 5;
                                        z16 = true;
                                        z15 = true;
                                        z17 = false;
                                        if (z15) {
                                        }
                                        z18 = true;
                                        if (z16) {
                                        }
                                        if (this.mVisibility != 8) {
                                        }
                                        if (z15) {
                                        }
                                        if (z19) {
                                        }
                                        constraintWidget4 = constraintWidget2;
                                        i26 = i24;
                                        if (z18) {
                                        }
                                        if (z19) {
                                        }
                                        if (z19) {
                                        }
                                        i27 = 5;
                                        i28 = 0;
                                    }
                                } else if (i10 == 1) {
                                    solverVariable12 = solverVariable2;
                                    i23 = 6;
                                    i22 = 8;
                                    i24 = 4;
                                    z16 = true;
                                    z15 = true;
                                    z17 = false;
                                    if (z15) {
                                    }
                                    z18 = true;
                                    if (z16) {
                                    }
                                    if (this.mVisibility != 8) {
                                    }
                                    if (z15) {
                                    }
                                    if (z19) {
                                    }
                                    constraintWidget4 = constraintWidget2;
                                    i26 = i24;
                                    if (z18) {
                                    }
                                    if (z19) {
                                    }
                                    if (z19) {
                                    }
                                    i27 = 5;
                                    i28 = 0;
                                } else if (i10 == 3) {
                                    if (this.f1949a == -1) {
                                        solverVariable12 = solverVariable2;
                                        i23 = z9 ? z2 ? 5 : 4 : 8;
                                        i22 = 8;
                                    } else if (z6) {
                                        if (i7 == 2 || i7 == 1) {
                                            i31 = 5;
                                            i32 = 4;
                                        } else {
                                            i31 = 8;
                                            i32 = 5;
                                        }
                                        solverVariable12 = solverVariable2;
                                        i24 = i32;
                                        z16 = true;
                                        z15 = true;
                                        z17 = true;
                                        i22 = i31;
                                        i23 = 6;
                                        if (z15) {
                                        }
                                        z18 = true;
                                        if (z16) {
                                        }
                                        if (this.mVisibility != 8) {
                                        }
                                        if (z15) {
                                        }
                                        if (z19) {
                                        }
                                        constraintWidget4 = constraintWidget2;
                                        i26 = i24;
                                        if (z18) {
                                        }
                                        if (z19) {
                                        }
                                        if (z19) {
                                        }
                                        i27 = 5;
                                        i28 = 0;
                                    } else if (i13 > 0) {
                                        solverVariable12 = solverVariable2;
                                        i23 = 6;
                                        i22 = 5;
                                    } else {
                                        if (i13 != 0 || i16 != 0) {
                                            solverVariable12 = solverVariable2;
                                            i23 = 6;
                                            i22 = 5;
                                        } else if (z9) {
                                            solverVariable12 = solverVariable2;
                                            i22 = (constraintWidget7 == parent || constraintWidget8 == parent) ? 5 : 4;
                                            i23 = 6;
                                        } else {
                                            solverVariable12 = solverVariable2;
                                            i23 = 6;
                                            i22 = 5;
                                            i24 = 8;
                                            z16 = true;
                                            z15 = true;
                                            z17 = true;
                                            if (z15) {
                                            }
                                            z18 = true;
                                            if (z16) {
                                            }
                                            if (this.mVisibility != 8) {
                                            }
                                            if (z15) {
                                            }
                                            if (z19) {
                                            }
                                            constraintWidget4 = constraintWidget2;
                                            i26 = i24;
                                            if (z18) {
                                            }
                                            if (z19) {
                                            }
                                            if (z19) {
                                            }
                                            i27 = 5;
                                            i28 = 0;
                                        }
                                        i24 = 4;
                                        z16 = true;
                                        z15 = true;
                                        z17 = true;
                                        if (z15) {
                                        }
                                        z18 = true;
                                        if (z16) {
                                        }
                                        if (this.mVisibility != 8) {
                                        }
                                        if (z15) {
                                        }
                                        if (z19) {
                                        }
                                        constraintWidget4 = constraintWidget2;
                                        i26 = i24;
                                        if (z18) {
                                        }
                                        if (z19) {
                                        }
                                        if (z19) {
                                        }
                                        i27 = 5;
                                        i28 = 0;
                                    }
                                    i24 = 5;
                                    z16 = true;
                                    z15 = true;
                                    z17 = true;
                                    if (z15) {
                                    }
                                    z18 = true;
                                    if (z16) {
                                    }
                                    if (this.mVisibility != 8) {
                                    }
                                    if (z15) {
                                    }
                                    if (z19) {
                                    }
                                    constraintWidget4 = constraintWidget2;
                                    i26 = i24;
                                    if (z18) {
                                    }
                                    if (z19) {
                                    }
                                    if (z19) {
                                    }
                                    i27 = 5;
                                    i28 = 0;
                                } else {
                                    solverVariable12 = solverVariable2;
                                    i23 = 6;
                                    i22 = 5;
                                    i24 = 4;
                                    z16 = false;
                                    z15 = false;
                                    z17 = false;
                                    if (z15) {
                                    }
                                    z18 = true;
                                    if (z16) {
                                    }
                                    if (this.mVisibility != 8) {
                                    }
                                    if (z15) {
                                    }
                                    if (z19) {
                                    }
                                    constraintWidget4 = constraintWidget2;
                                    i26 = i24;
                                    if (z18) {
                                    }
                                    if (z19) {
                                    }
                                    if (z19) {
                                    }
                                    i27 = 5;
                                    i28 = 0;
                                }
                                i34 = i27;
                                if (z19 || !z14) {
                                    return;
                                }
                                if (constraintAnchor2.mTarget != null) {
                                    i35 = constraintAnchor2.getMargin();
                                    solverVariable16 = solverVariable2;
                                } else {
                                    solverVariable16 = solverVariable2;
                                    i35 = i28;
                                }
                                if (solverVariable6 != solverVariable16) {
                                    if (!this.OPTIMIZE_WRAP || !solverVariable15.isFinalValue || (constraintWidget6 = this.mParent) == null) {
                                        linearSystem.addGreaterThan(solverVariable16, solverVariable15, i35, i34);
                                        return;
                                    }
                                    ConstraintWidgetContainer constraintWidgetContainer2 = (ConstraintWidgetContainer) constraintWidget6;
                                    if (z) {
                                        constraintWidgetContainer2.addHorizontalWrapMaxVariable(constraintAnchor2);
                                        return;
                                    } else {
                                        constraintWidgetContainer2.f(constraintAnchor2);
                                        return;
                                    }
                                }
                                return;
                            } else if (solverVariable4.isFinalValue && solverVariable6.isFinalValue) {
                                linearSystem.addCentering(createObjectVariable2, solverVariable4, constraintAnchor.getMargin(), f2, solverVariable6, solverVariable5, constraintAnchor2.getMargin(), 8);
                                if (z2 && z14) {
                                    if (constraintAnchor2.mTarget != null) {
                                        i21 = constraintAnchor2.getMargin();
                                        solverVariable11 = solverVariable2;
                                    } else {
                                        solverVariable11 = solverVariable2;
                                        i21 = 0;
                                    }
                                    if (solverVariable6 != solverVariable11) {
                                        linearSystem.addGreaterThan(solverVariable11, solverVariable5, i21, 5);
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            solverVariable12 = solverVariable2;
                            i23 = 6;
                            i22 = 5;
                            i24 = 4;
                            z16 = true;
                            z15 = true;
                            z17 = false;
                            if (z15) {
                            }
                            z18 = true;
                            if (z16) {
                            }
                            if (this.mVisibility != 8) {
                            }
                            if (z15) {
                            }
                            if (z19) {
                            }
                            constraintWidget4 = constraintWidget2;
                            i26 = i24;
                            if (z18) {
                            }
                            if (z19) {
                            }
                            if (z19) {
                            }
                            i27 = 5;
                            i28 = 0;
                            i34 = i27;
                            if (z19) {
                                return;
                            }
                            return;
                        }
                        i28 = 0;
                        solverVariable15 = solverVariable5;
                        i27 = 5;
                    } else {
                        z19 = z2;
                        i28 = 0;
                        i34 = (z2 && (constraintAnchor.mTarget.mOwner instanceof Barrier)) ? 8 : 5;
                        solverVariable15 = solverVariable5;
                        if (z19) {
                        }
                    }
                    z19 = z2;
                    i34 = i27;
                    if (z19) {
                    }
                }
                if (i18 >= i19 && z2 && z14) {
                    linearSystem.addGreaterThan(solverVariable10, solverVariable7, i17, 8);
                    int i41 = (z || this.mBaseline.mTarget == null) ? 1 : i17;
                    if (z || (constraintAnchor3 = this.mBaseline.mTarget) == null) {
                        i20 = i41;
                    } else {
                        ConstraintWidget constraintWidget9 = constraintAnchor3.mOwner;
                        if (constraintWidget9.mDimensionRatio != 0.0f) {
                            DimensionBehaviour[] dimensionBehaviourArr = constraintWidget9.mListDimensionBehaviors;
                            DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[i17];
                            DimensionBehaviour dimensionBehaviour3 = DimensionBehaviour.MATCH_CONSTRAINT;
                            if (dimensionBehaviour2 == dimensionBehaviour3 && dimensionBehaviourArr[1] == dimensionBehaviour3) {
                                i20 = 1;
                            }
                        }
                        i20 = i17;
                    }
                    if (i20 != 0) {
                        linearSystem.addGreaterThan(solverVariable8, solverVariable9, i17, 8);
                        return;
                    }
                    return;
                }
                return;
            }
        }
        z12 = false;
        i11 = this.mWidthOverride;
        if (i11 == -1) {
        }
        i11 = i3;
        solverVariable3 = createObjectVariable5;
        i12 = this.mHeightOverride;
        if (i12 != -1) {
            this.mHeightOverride = -1;
            i11 = i12;
            z12 = false;
        }
        if (this.mVisibility == 8) {
        }
        if (z11) {
        }
        if (z12) {
        }
        if (z11) {
        }
        if (i18 >= i19) {
        }
    }

    private boolean isChainHead(int i2) {
        int i3 = i2 * 2;
        ConstraintAnchor[] constraintAnchorArr = this.mListAnchors;
        if (constraintAnchorArr[i3].mTarget != null && constraintAnchorArr[i3].mTarget.mTarget != constraintAnchorArr[i3]) {
            int i4 = i3 + 1;
            if (constraintAnchorArr[i4].mTarget != null && constraintAnchorArr[i4].mTarget.mTarget == constraintAnchorArr[i4]) {
                return true;
            }
        }
        return false;
    }

    private void serializeAnchor(StringBuilder sb, String str, ConstraintAnchor constraintAnchor) {
        if (constraintAnchor.mTarget == null) {
            return;
        }
        sb.append(str);
        sb.append(" : [ '");
        sb.append(constraintAnchor.mTarget);
        sb.append("',");
        sb.append(constraintAnchor.mMargin);
        sb.append(",");
        sb.append(constraintAnchor.f1946a);
        sb.append(",");
        sb.append(" ] ,\n");
    }

    private void serializeAttribute(StringBuilder sb, String str, float f2, float f3) {
        if (f2 == f3) {
            return;
        }
        sb.append(str);
        sb.append(" :   ");
        sb.append(f3);
        sb.append(",\n");
    }

    private void serializeCircle(StringBuilder sb, ConstraintAnchor constraintAnchor, float f2) {
        if (constraintAnchor.mTarget == null) {
            return;
        }
        sb.append("circle : [ '");
        sb.append(constraintAnchor.mTarget);
        sb.append("',");
        sb.append(constraintAnchor.mMargin);
        sb.append(",");
        sb.append(f2);
        sb.append(",");
        sb.append(" ] ,\n");
    }

    private void serializeDimensionRatio(StringBuilder sb, String str, float f2, int i2) {
        if (f2 == 0.0f) {
            return;
        }
        sb.append(str);
        sb.append(" :  [");
        sb.append(f2);
        sb.append(",");
        sb.append(i2);
        sb.append("");
        sb.append("],\n");
    }

    private void serializeSize(StringBuilder sb, String str, int i2, int i3, int i4, int i5, int i6, int i7, float f2, float f3) {
        sb.append(str);
        sb.append(" :  {\n");
        serializeAttribute(sb, "size", i2, -2.14748365E9f);
        serializeAttribute(sb, "min", i3, 0.0f);
        serializeAttribute(sb, "max", i4, 2.14748365E9f);
        serializeAttribute(sb, "matchMin", i6, 0.0f);
        float f4 = i7;
        serializeAttribute(sb, "matchDef", f4, 0.0f);
        serializeAttribute(sb, "matchPercent", f4, 1.0f);
        sb.append("},\n");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a() {
        return (this instanceof VirtualLayout) || (this instanceof Guideline);
    }

    public void addChildrenToSolverByDependency(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, HashSet<ConstraintWidget> hashSet, int i2, boolean z) {
        if (z) {
            if (!hashSet.contains(this)) {
                return;
            }
            Optimizer.a(constraintWidgetContainer, linearSystem, this);
            hashSet.remove(this);
            addToSolver(linearSystem, constraintWidgetContainer.optimizeFor(64));
        }
        if (i2 == 0) {
            HashSet<ConstraintAnchor> dependents = this.mLeft.getDependents();
            if (dependents != null) {
                Iterator<ConstraintAnchor> it = dependents.iterator();
                while (it.hasNext()) {
                    it.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i2, true);
                }
            }
            HashSet<ConstraintAnchor> dependents2 = this.mRight.getDependents();
            if (dependents2 != null) {
                Iterator<ConstraintAnchor> it2 = dependents2.iterator();
                while (it2.hasNext()) {
                    it2.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i2, true);
                }
                return;
            }
            return;
        }
        HashSet<ConstraintAnchor> dependents3 = this.mTop.getDependents();
        if (dependents3 != null) {
            Iterator<ConstraintAnchor> it3 = dependents3.iterator();
            while (it3.hasNext()) {
                it3.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i2, true);
            }
        }
        HashSet<ConstraintAnchor> dependents4 = this.mBottom.getDependents();
        if (dependents4 != null) {
            Iterator<ConstraintAnchor> it4 = dependents4.iterator();
            while (it4.hasNext()) {
                it4.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i2, true);
            }
        }
        HashSet<ConstraintAnchor> dependents5 = this.mBaseline.getDependents();
        if (dependents5 != null) {
            Iterator<ConstraintAnchor> it5 = dependents5.iterator();
            while (it5.hasNext()) {
                it5.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i2, true);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x033e  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0357  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x039f  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x03af  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x03b7  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x03bd  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x03ea  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x03ed  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x045c  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x04c0  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x04d4  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x04d6  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x04d9  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x0571  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0574  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x05ba  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x05e0  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x05ea  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0191  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void addToSolver(LinearSystem linearSystem, boolean z) {
        boolean z2;
        boolean z3;
        boolean z4;
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2;
        Metrics metrics;
        boolean z5;
        boolean z6;
        int i2;
        int i3;
        int i4;
        int i5;
        float f2;
        SolverVariable solverVariable;
        char c2;
        boolean z7;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        boolean z8;
        DimensionBehaviour dimensionBehaviour;
        boolean z9;
        int i11;
        boolean z10;
        boolean z11;
        boolean z12;
        boolean z13;
        DimensionBehaviour dimensionBehaviour2;
        DimensionBehaviour dimensionBehaviour3;
        SolverVariable solverVariable2;
        SolverVariable solverVariable3;
        SolverVariable solverVariable4;
        SolverVariable solverVariable5;
        SolverVariable solverVariable6;
        int i12;
        int i13;
        int i14;
        ConstraintWidget constraintWidget3;
        LinearSystem linearSystem2;
        SolverVariable solverVariable7;
        SolverVariable solverVariable8;
        SolverVariable solverVariable9;
        int i15;
        SolverVariable solverVariable10;
        SolverVariable solverVariable11;
        ConstraintWidget constraintWidget4;
        LinearSystem linearSystem3;
        SolverVariable solverVariable12;
        SolverVariable solverVariable13;
        SolverVariable solverVariable14;
        SolverVariable solverVariable15;
        boolean z14;
        HorizontalWidgetRun horizontalWidgetRun;
        DependencyNode dependencyNode;
        int i16;
        int i17;
        boolean isInHorizontalChain;
        boolean isInVerticalChain;
        HorizontalWidgetRun horizontalWidgetRun2;
        VerticalWidgetRun verticalWidgetRun;
        DependencyNode dependencyNode2;
        boolean[] zArr;
        SolverVariable createObjectVariable = linearSystem.createObjectVariable(this.mLeft);
        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(this.mRight);
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(this.mTop);
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(this.mBottom);
        SolverVariable createObjectVariable5 = linearSystem.createObjectVariable(this.mBaseline);
        ConstraintWidget constraintWidget5 = this.mParent;
        if (constraintWidget5 != null) {
            boolean z15 = constraintWidget5 != null && constraintWidget5.mListDimensionBehaviors[0] == DimensionBehaviour.WRAP_CONTENT;
            boolean z16 = constraintWidget5 != null && constraintWidget5.mListDimensionBehaviors[1] == DimensionBehaviour.WRAP_CONTENT;
            int i18 = this.mWrapBehaviorInParent;
            if (i18 == 1) {
                z2 = z15;
                z3 = false;
            } else if (i18 == 2) {
                z3 = z16;
                z2 = false;
            } else if (i18 != 3) {
                z3 = z16;
                z2 = z15;
            }
            if (this.mVisibility == 8 && !hasDependencies()) {
                zArr = this.mIsInBarrier;
                if (!zArr[0] && !zArr[1]) {
                    return;
                }
            }
            z4 = this.resolvedHorizontal;
            if (!z4 || this.resolvedVertical) {
                if (z4) {
                    linearSystem.addEquality(createObjectVariable, this.f1957i);
                    linearSystem.addEquality(createObjectVariable2, this.f1957i + this.f1954f);
                    if (z2 && (constraintWidget2 = this.mParent) != null) {
                        if (this.OPTIMIZE_WRAP_ON_RESOLVED) {
                            ConstraintWidgetContainer constraintWidgetContainer = (ConstraintWidgetContainer) constraintWidget2;
                            constraintWidgetContainer.addHorizontalWrapMinVariable(this.mLeft);
                            constraintWidgetContainer.addHorizontalWrapMaxVariable(this.mRight);
                        } else {
                            linearSystem.addGreaterThan(linearSystem.createObjectVariable(constraintWidget2.mRight), createObjectVariable2, 0, 5);
                        }
                    }
                }
                if (this.resolvedVertical) {
                    linearSystem.addEquality(createObjectVariable3, this.f1958j);
                    linearSystem.addEquality(createObjectVariable4, this.f1958j + this.f1955g);
                    if (this.mBaseline.hasDependents()) {
                        linearSystem.addEquality(createObjectVariable5, this.f1958j + this.f1963o);
                    }
                    if (z3 && (constraintWidget = this.mParent) != null) {
                        if (this.OPTIMIZE_WRAP_ON_RESOLVED) {
                            ConstraintWidgetContainer constraintWidgetContainer2 = (ConstraintWidgetContainer) constraintWidget;
                            constraintWidgetContainer2.g(this.mTop);
                            constraintWidgetContainer2.f(this.mBottom);
                        } else {
                            linearSystem.addGreaterThan(linearSystem.createObjectVariable(constraintWidget.mBottom), createObjectVariable4, 0, 5);
                        }
                    }
                }
                if (this.resolvedHorizontal && this.resolvedVertical) {
                    this.resolvedHorizontal = false;
                    this.resolvedVertical = false;
                    return;
                }
            }
            metrics = LinearSystem.sMetrics;
            if (metrics != null) {
                metrics.widgets++;
            }
            if (z && (horizontalWidgetRun2 = this.horizontalRun) != null && (verticalWidgetRun = this.verticalRun) != null) {
                dependencyNode2 = horizontalWidgetRun2.start;
                if (dependencyNode2.resolved && horizontalWidgetRun2.end.resolved && verticalWidgetRun.start.resolved && verticalWidgetRun.end.resolved) {
                    if (metrics != null) {
                        metrics.graphSolved++;
                    }
                    linearSystem.addEquality(createObjectVariable, dependencyNode2.value);
                    linearSystem.addEquality(createObjectVariable2, this.horizontalRun.end.value);
                    linearSystem.addEquality(createObjectVariable3, this.verticalRun.start.value);
                    linearSystem.addEquality(createObjectVariable4, this.verticalRun.end.value);
                    linearSystem.addEquality(createObjectVariable5, this.verticalRun.baseline.value);
                    if (this.mParent != null) {
                        if (z2 && this.isTerminalWidget[0] && !isInHorizontalChain()) {
                            linearSystem.addGreaterThan(linearSystem.createObjectVariable(this.mParent.mRight), createObjectVariable2, 0, 8);
                        }
                        if (z3 && this.isTerminalWidget[1] && !isInVerticalChain()) {
                            linearSystem.addGreaterThan(linearSystem.createObjectVariable(this.mParent.mBottom), createObjectVariable4, 0, 8);
                        }
                    }
                    this.resolvedHorizontal = false;
                    this.resolvedVertical = false;
                    return;
                }
            }
            if (metrics != null) {
                metrics.linearSolved++;
            }
            if (this.mParent == null) {
                if (isChainHead(0)) {
                    ((ConstraintWidgetContainer) this.mParent).e(this, 0);
                    isInHorizontalChain = true;
                } else {
                    isInHorizontalChain = isInHorizontalChain();
                }
                if (isChainHead(1)) {
                    ((ConstraintWidgetContainer) this.mParent).e(this, 1);
                    isInVerticalChain = true;
                } else {
                    isInVerticalChain = isInVerticalChain();
                }
                if (!isInHorizontalChain && z2 && this.mVisibility != 8 && this.mLeft.mTarget == null && this.mRight.mTarget == null) {
                    linearSystem.addGreaterThan(linearSystem.createObjectVariable(this.mParent.mRight), createObjectVariable2, 0, 1);
                }
                if (!isInVerticalChain && z3 && this.mVisibility != 8 && this.mTop.mTarget == null && this.mBottom.mTarget == null && this.mBaseline == null) {
                    linearSystem.addGreaterThan(linearSystem.createObjectVariable(this.mParent.mBottom), createObjectVariable4, 0, 1);
                }
                z6 = isInHorizontalChain;
                z5 = isInVerticalChain;
            } else {
                z5 = false;
                z6 = false;
            }
            i2 = this.f1954f;
            i3 = this.f1964p;
            if (i2 >= i3) {
                i3 = i2;
            }
            i4 = this.f1955g;
            i5 = this.f1965q;
            if (i4 >= i5) {
                i5 = i4;
            }
            DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
            DimensionBehaviour dimensionBehaviour4 = dimensionBehaviourArr[0];
            DimensionBehaviour dimensionBehaviour5 = DimensionBehaviour.MATCH_CONSTRAINT;
            int i19 = i3;
            boolean z17 = dimensionBehaviour4 == dimensionBehaviour5;
            int i20 = i5;
            boolean z18 = dimensionBehaviourArr[1] == dimensionBehaviour5;
            int i21 = this.f1956h;
            this.f1949a = i21;
            f2 = this.mDimensionRatio;
            this.f1950b = f2;
            int i22 = this.mMatchConstraintDefaultWidth;
            int i23 = this.mMatchConstraintDefaultHeight;
            if (f2 <= 0.0f) {
                solverVariable = createObjectVariable;
                if (this.mVisibility != 8) {
                    if (dimensionBehaviourArr[0] == dimensionBehaviour5 && i22 == 0) {
                        i22 = 3;
                    }
                    if (dimensionBehaviourArr[1] == dimensionBehaviour5 && i23 == 0) {
                        i23 = 3;
                    }
                    if (dimensionBehaviourArr[0] == dimensionBehaviour5 && dimensionBehaviourArr[1] == dimensionBehaviour5) {
                        i17 = 3;
                        if (i22 == 3 && i23 == 3) {
                            setupDimensionRatio(z2, z3, z17, z18);
                            c2 = 0;
                            i6 = i22;
                            i7 = i23;
                            i8 = i19;
                            i9 = i20;
                            z7 = true;
                            int[] iArr = this.mResolvedMatchConstraintDefault;
                            iArr[c2] = i6;
                            iArr[1] = i7;
                            if (z7) {
                                i10 = -1;
                            } else {
                                int i24 = this.f1949a;
                                i10 = -1;
                                if (i24 == 0 || i24 == -1) {
                                    z8 = true;
                                    boolean z19 = !z7 && ((i16 = this.f1949a) == 1 || i16 == i10);
                                    DimensionBehaviour dimensionBehaviour6 = this.mListDimensionBehaviors[0];
                                    dimensionBehaviour = DimensionBehaviour.WRAP_CONTENT;
                                    z9 = dimensionBehaviour6 != dimensionBehaviour && (this instanceof ConstraintWidgetContainer);
                                    i11 = z9 ? 0 : i8;
                                    z10 = !this.mCenter.isConnected();
                                    boolean[] zArr2 = this.mIsInBarrier;
                                    z11 = zArr2[0];
                                    boolean z20 = zArr2[1];
                                    if (this.mHorizontalResolution != 2 && !this.resolvedHorizontal) {
                                        if (z && (horizontalWidgetRun = this.horizontalRun) != null) {
                                            dependencyNode = horizontalWidgetRun.start;
                                            if (dependencyNode.resolved && horizontalWidgetRun.end.resolved) {
                                                if (!z) {
                                                    SolverVariable solverVariable16 = solverVariable;
                                                    linearSystem.addEquality(solverVariable16, dependencyNode.value);
                                                    linearSystem.addEquality(createObjectVariable2, this.horizontalRun.end.value);
                                                    if (this.mParent != null && z2 && this.isTerminalWidget[0] && !isInHorizontalChain()) {
                                                        linearSystem.addGreaterThan(linearSystem.createObjectVariable(this.mParent.mRight), createObjectVariable2, 0, 8);
                                                    }
                                                    z12 = z2;
                                                    z13 = z3;
                                                    dimensionBehaviour2 = dimensionBehaviour5;
                                                    dimensionBehaviour3 = dimensionBehaviour;
                                                    solverVariable2 = createObjectVariable5;
                                                    solverVariable3 = createObjectVariable4;
                                                    solverVariable4 = createObjectVariable3;
                                                    solverVariable5 = createObjectVariable2;
                                                    solverVariable6 = solverVariable16;
                                                    if (z) {
                                                        i12 = 8;
                                                        i13 = 0;
                                                        i14 = 1;
                                                        constraintWidget3 = this;
                                                        linearSystem2 = linearSystem;
                                                        solverVariable7 = solverVariable2;
                                                        solverVariable8 = solverVariable3;
                                                        solverVariable9 = solverVariable4;
                                                    } else {
                                                        constraintWidget3 = this;
                                                        VerticalWidgetRun verticalWidgetRun2 = constraintWidget3.verticalRun;
                                                        if (verticalWidgetRun2 != null) {
                                                            DependencyNode dependencyNode3 = verticalWidgetRun2.start;
                                                            if (dependencyNode3.resolved && verticalWidgetRun2.end.resolved) {
                                                                linearSystem2 = linearSystem;
                                                                solverVariable9 = solverVariable4;
                                                                linearSystem2.addEquality(solverVariable9, dependencyNode3.value);
                                                                solverVariable8 = solverVariable3;
                                                                linearSystem2.addEquality(solverVariable8, constraintWidget3.verticalRun.end.value);
                                                                solverVariable7 = solverVariable2;
                                                                linearSystem2.addEquality(solverVariable7, constraintWidget3.verticalRun.baseline.value);
                                                                ConstraintWidget constraintWidget6 = constraintWidget3.mParent;
                                                                if (constraintWidget6 == null || z5 || !z13) {
                                                                    i12 = 8;
                                                                    i13 = 0;
                                                                    i14 = 1;
                                                                } else {
                                                                    i14 = 1;
                                                                    if (constraintWidget3.isTerminalWidget[1]) {
                                                                        i12 = 8;
                                                                        i13 = 0;
                                                                        linearSystem2.addGreaterThan(linearSystem2.createObjectVariable(constraintWidget6.mBottom), solverVariable8, 0, 8);
                                                                    } else {
                                                                        i12 = 8;
                                                                        i13 = 0;
                                                                    }
                                                                }
                                                                i15 = i13;
                                                                if ((constraintWidget3.mVerticalResolution == 2 ? i13 : i15) != 0 || constraintWidget3.resolvedVertical) {
                                                                    solverVariable10 = solverVariable8;
                                                                    solverVariable11 = solverVariable9;
                                                                } else {
                                                                    boolean z21 = (constraintWidget3.mListDimensionBehaviors[i14] == dimensionBehaviour3 && (constraintWidget3 instanceof ConstraintWidgetContainer)) ? i14 : i13;
                                                                    if (z21) {
                                                                        i9 = i13;
                                                                    }
                                                                    ConstraintWidget constraintWidget7 = constraintWidget3.mParent;
                                                                    SolverVariable createObjectVariable6 = constraintWidget7 != null ? linearSystem2.createObjectVariable(constraintWidget7.mBottom) : null;
                                                                    ConstraintWidget constraintWidget8 = constraintWidget3.mParent;
                                                                    SolverVariable createObjectVariable7 = constraintWidget8 != null ? linearSystem2.createObjectVariable(constraintWidget8.mTop) : null;
                                                                    if (constraintWidget3.f1963o > 0 || constraintWidget3.mVisibility == i12) {
                                                                        ConstraintAnchor constraintAnchor = constraintWidget3.mBaseline;
                                                                        if (constraintAnchor.mTarget != null) {
                                                                            linearSystem2.addEquality(solverVariable7, solverVariable9, getBaselineDistance(), i12);
                                                                            linearSystem2.addEquality(solverVariable7, linearSystem2.createObjectVariable(constraintWidget3.mBaseline.mTarget), constraintWidget3.mBaseline.getMargin(), i12);
                                                                            if (z13) {
                                                                                linearSystem2.addGreaterThan(createObjectVariable6, linearSystem2.createObjectVariable(constraintWidget3.mBottom), i13, 5);
                                                                            }
                                                                            z14 = i13;
                                                                            boolean z22 = constraintWidget3.isTerminalWidget[i14];
                                                                            DimensionBehaviour[] dimensionBehaviourArr2 = constraintWidget3.mListDimensionBehaviors;
                                                                            solverVariable10 = solverVariable8;
                                                                            solverVariable11 = solverVariable9;
                                                                            applyConstraints(linearSystem, false, z13, z12, z22, createObjectVariable7, createObjectVariable6, dimensionBehaviourArr2[i14], z21, constraintWidget3.mTop, constraintWidget3.mBottom, constraintWidget3.f1958j, i9, constraintWidget3.f1965q, constraintWidget3.mMaxDimension[i14], constraintWidget3.f1967s, z19, dimensionBehaviourArr2[0] != dimensionBehaviour2, z5, z6, z20, i7, i6, constraintWidget3.mMatchConstraintMinHeight, constraintWidget3.mMatchConstraintMaxHeight, constraintWidget3.mMatchConstraintPercentHeight, z14);
                                                                        } else {
                                                                            linearSystem2.addEquality(solverVariable7, solverVariable9, constraintWidget3.mVisibility == i12 ? constraintAnchor.getMargin() : getBaselineDistance(), i12);
                                                                        }
                                                                    }
                                                                    z14 = z10;
                                                                    boolean z222 = constraintWidget3.isTerminalWidget[i14];
                                                                    DimensionBehaviour[] dimensionBehaviourArr22 = constraintWidget3.mListDimensionBehaviors;
                                                                    solverVariable10 = solverVariable8;
                                                                    solverVariable11 = solverVariable9;
                                                                    applyConstraints(linearSystem, false, z13, z12, z222, createObjectVariable7, createObjectVariable6, dimensionBehaviourArr22[i14], z21, constraintWidget3.mTop, constraintWidget3.mBottom, constraintWidget3.f1958j, i9, constraintWidget3.f1965q, constraintWidget3.mMaxDimension[i14], constraintWidget3.f1967s, z19, dimensionBehaviourArr22[0] != dimensionBehaviour2, z5, z6, z20, i7, i6, constraintWidget3.mMatchConstraintMinHeight, constraintWidget3.mMatchConstraintMaxHeight, constraintWidget3.mMatchConstraintPercentHeight, z14);
                                                                }
                                                                if (z7) {
                                                                    int i25 = 8;
                                                                    constraintWidget4 = this;
                                                                    int i26 = constraintWidget4.f1949a;
                                                                    float f3 = constraintWidget4.f1950b;
                                                                    if (i26 == 1) {
                                                                        linearSystem3 = linearSystem;
                                                                        solverVariable12 = solverVariable10;
                                                                        solverVariable13 = solverVariable11;
                                                                        solverVariable14 = solverVariable5;
                                                                        solverVariable15 = solverVariable6;
                                                                    } else {
                                                                        i25 = 8;
                                                                        linearSystem3 = linearSystem;
                                                                        solverVariable12 = solverVariable5;
                                                                        solverVariable13 = solverVariable6;
                                                                        solverVariable14 = solverVariable10;
                                                                        solverVariable15 = solverVariable11;
                                                                    }
                                                                    linearSystem3.addRatio(solverVariable12, solverVariable13, solverVariable14, solverVariable15, f3, i25);
                                                                } else {
                                                                    constraintWidget4 = this;
                                                                }
                                                                if (constraintWidget4.mCenter.isConnected()) {
                                                                    linearSystem.addCenterPoint(constraintWidget4, constraintWidget4.mCenter.getTarget().getOwner(), (float) Math.toRadians(constraintWidget4.mCircleConstraintAngle + 90.0f), constraintWidget4.mCenter.getMargin());
                                                                }
                                                                constraintWidget4.resolvedHorizontal = false;
                                                                constraintWidget4.resolvedVertical = false;
                                                            }
                                                        }
                                                        linearSystem2 = linearSystem;
                                                        solverVariable7 = solverVariable2;
                                                        solverVariable8 = solverVariable3;
                                                        solverVariable9 = solverVariable4;
                                                        i12 = 8;
                                                        i13 = 0;
                                                        i14 = 1;
                                                    }
                                                    i15 = i14;
                                                    if ((constraintWidget3.mVerticalResolution == 2 ? i13 : i15) != 0) {
                                                    }
                                                    solverVariable10 = solverVariable8;
                                                    solverVariable11 = solverVariable9;
                                                    if (z7) {
                                                    }
                                                    if (constraintWidget4.mCenter.isConnected()) {
                                                    }
                                                    constraintWidget4.resolvedHorizontal = false;
                                                    constraintWidget4.resolvedVertical = false;
                                                }
                                            }
                                        }
                                        SolverVariable solverVariable17 = solverVariable;
                                        ConstraintWidget constraintWidget9 = this.mParent;
                                        SolverVariable createObjectVariable8 = constraintWidget9 == null ? linearSystem.createObjectVariable(constraintWidget9.mRight) : null;
                                        ConstraintWidget constraintWidget10 = this.mParent;
                                        SolverVariable createObjectVariable9 = constraintWidget10 == null ? linearSystem.createObjectVariable(constraintWidget10.mLeft) : null;
                                        boolean z23 = this.isTerminalWidget[0];
                                        DimensionBehaviour[] dimensionBehaviourArr3 = this.mListDimensionBehaviors;
                                        z12 = z2;
                                        z13 = z3;
                                        dimensionBehaviour2 = dimensionBehaviour5;
                                        solverVariable2 = createObjectVariable5;
                                        solverVariable3 = createObjectVariable4;
                                        solverVariable4 = createObjectVariable3;
                                        solverVariable5 = createObjectVariable2;
                                        solverVariable6 = solverVariable17;
                                        dimensionBehaviour3 = dimensionBehaviour;
                                        applyConstraints(linearSystem, true, z2, z3, z23, createObjectVariable9, createObjectVariable8, dimensionBehaviourArr3[0], z9, this.mLeft, this.mRight, this.f1957i, i11, this.f1964p, this.mMaxDimension[0], this.f1966r, z8, dimensionBehaviourArr3[1] != dimensionBehaviour5, z6, z5, z11, i6, i7, this.mMatchConstraintMinWidth, this.mMatchConstraintMaxWidth, this.mMatchConstraintPercentWidth, z10);
                                        if (z) {
                                        }
                                        i15 = i14;
                                        if ((constraintWidget3.mVerticalResolution == 2 ? i13 : i15) != 0) {
                                        }
                                        solverVariable10 = solverVariable8;
                                        solverVariable11 = solverVariable9;
                                        if (z7) {
                                        }
                                        if (constraintWidget4.mCenter.isConnected()) {
                                        }
                                        constraintWidget4.resolvedHorizontal = false;
                                        constraintWidget4.resolvedVertical = false;
                                    }
                                    z12 = z2;
                                    z13 = z3;
                                    dimensionBehaviour2 = dimensionBehaviour5;
                                    dimensionBehaviour3 = dimensionBehaviour;
                                    solverVariable2 = createObjectVariable5;
                                    solverVariable3 = createObjectVariable4;
                                    solverVariable4 = createObjectVariable3;
                                    solverVariable5 = createObjectVariable2;
                                    solverVariable6 = solverVariable;
                                    if (z) {
                                    }
                                    i15 = i14;
                                    if ((constraintWidget3.mVerticalResolution == 2 ? i13 : i15) != 0) {
                                    }
                                    solverVariable10 = solverVariable8;
                                    solverVariable11 = solverVariable9;
                                    if (z7) {
                                    }
                                    if (constraintWidget4.mCenter.isConnected()) {
                                    }
                                    constraintWidget4.resolvedHorizontal = false;
                                    constraintWidget4.resolvedVertical = false;
                                }
                            }
                            z8 = false;
                            if (z7) {
                            }
                            DimensionBehaviour dimensionBehaviour62 = this.mListDimensionBehaviors[0];
                            dimensionBehaviour = DimensionBehaviour.WRAP_CONTENT;
                            if (dimensionBehaviour62 != dimensionBehaviour) {
                            }
                            if (z9) {
                            }
                            z10 = !this.mCenter.isConnected();
                            boolean[] zArr22 = this.mIsInBarrier;
                            z11 = zArr22[0];
                            boolean z202 = zArr22[1];
                            if (this.mHorizontalResolution != 2) {
                                if (z) {
                                    dependencyNode = horizontalWidgetRun.start;
                                    if (dependencyNode.resolved) {
                                        if (!z) {
                                        }
                                    }
                                }
                                SolverVariable solverVariable172 = solverVariable;
                                ConstraintWidget constraintWidget92 = this.mParent;
                                if (constraintWidget92 == null) {
                                }
                                ConstraintWidget constraintWidget102 = this.mParent;
                                if (constraintWidget102 == null) {
                                }
                                boolean z232 = this.isTerminalWidget[0];
                                DimensionBehaviour[] dimensionBehaviourArr32 = this.mListDimensionBehaviors;
                                z12 = z2;
                                z13 = z3;
                                dimensionBehaviour2 = dimensionBehaviour5;
                                solverVariable2 = createObjectVariable5;
                                solverVariable3 = createObjectVariable4;
                                solverVariable4 = createObjectVariable3;
                                solverVariable5 = createObjectVariable2;
                                solverVariable6 = solverVariable172;
                                dimensionBehaviour3 = dimensionBehaviour;
                                applyConstraints(linearSystem, true, z2, z3, z232, createObjectVariable9, createObjectVariable8, dimensionBehaviourArr32[0], z9, this.mLeft, this.mRight, this.f1957i, i11, this.f1964p, this.mMaxDimension[0], this.f1966r, z8, dimensionBehaviourArr32[1] != dimensionBehaviour5, z6, z5, z11, i6, i7, this.mMatchConstraintMinWidth, this.mMatchConstraintMaxWidth, this.mMatchConstraintPercentWidth, z10);
                                if (z) {
                                }
                                i15 = i14;
                                if ((constraintWidget3.mVerticalResolution == 2 ? i13 : i15) != 0) {
                                }
                                solverVariable10 = solverVariable8;
                                solverVariable11 = solverVariable9;
                                if (z7) {
                                }
                                if (constraintWidget4.mCenter.isConnected()) {
                                }
                                constraintWidget4.resolvedHorizontal = false;
                                constraintWidget4.resolvedVertical = false;
                            }
                            z12 = z2;
                            z13 = z3;
                            dimensionBehaviour2 = dimensionBehaviour5;
                            dimensionBehaviour3 = dimensionBehaviour;
                            solverVariable2 = createObjectVariable5;
                            solverVariable3 = createObjectVariable4;
                            solverVariable4 = createObjectVariable3;
                            solverVariable5 = createObjectVariable2;
                            solverVariable6 = solverVariable;
                            if (z) {
                            }
                            i15 = i14;
                            if ((constraintWidget3.mVerticalResolution == 2 ? i13 : i15) != 0) {
                            }
                            solverVariable10 = solverVariable8;
                            solverVariable11 = solverVariable9;
                            if (z7) {
                            }
                            if (constraintWidget4.mCenter.isConnected()) {
                            }
                            constraintWidget4.resolvedHorizontal = false;
                            constraintWidget4.resolvedVertical = false;
                        }
                    } else {
                        i17 = 3;
                    }
                    if (dimensionBehaviourArr[0] == dimensionBehaviour5 && i22 == i17) {
                        this.f1949a = 0;
                        i8 = (int) (f2 * i4);
                        if (dimensionBehaviourArr[1] != dimensionBehaviour5) {
                            i7 = i23;
                            i9 = i20;
                            i6 = 4;
                            c2 = 0;
                            z7 = false;
                        } else {
                            z7 = true;
                            i6 = i22;
                            i7 = i23;
                            i9 = i20;
                            c2 = 0;
                        }
                    } else {
                        if (dimensionBehaviourArr[1] == dimensionBehaviour5 && i23 == 3) {
                            this.f1949a = 1;
                            if (i21 == -1) {
                                this.f1950b = 1.0f / f2;
                            }
                            int i27 = (int) (this.f1950b * i2);
                            c2 = 0;
                            if (dimensionBehaviourArr[0] != dimensionBehaviour5) {
                                z7 = false;
                                i9 = i27;
                                i6 = i22;
                                i8 = i19;
                                i7 = 4;
                            } else {
                                i9 = i27;
                                i6 = i22;
                                i7 = i23;
                                i8 = i19;
                                z7 = true;
                            }
                        }
                        c2 = 0;
                        i6 = i22;
                        i7 = i23;
                        i8 = i19;
                        i9 = i20;
                        z7 = true;
                    }
                    int[] iArr2 = this.mResolvedMatchConstraintDefault;
                    iArr2[c2] = i6;
                    iArr2[1] = i7;
                    if (z7) {
                    }
                    z8 = false;
                    if (z7) {
                    }
                    DimensionBehaviour dimensionBehaviour622 = this.mListDimensionBehaviors[0];
                    dimensionBehaviour = DimensionBehaviour.WRAP_CONTENT;
                    if (dimensionBehaviour622 != dimensionBehaviour) {
                    }
                    if (z9) {
                    }
                    z10 = !this.mCenter.isConnected();
                    boolean[] zArr222 = this.mIsInBarrier;
                    z11 = zArr222[0];
                    boolean z2022 = zArr222[1];
                    if (this.mHorizontalResolution != 2) {
                    }
                    z12 = z2;
                    z13 = z3;
                    dimensionBehaviour2 = dimensionBehaviour5;
                    dimensionBehaviour3 = dimensionBehaviour;
                    solverVariable2 = createObjectVariable5;
                    solverVariable3 = createObjectVariable4;
                    solverVariable4 = createObjectVariable3;
                    solverVariable5 = createObjectVariable2;
                    solverVariable6 = solverVariable;
                    if (z) {
                    }
                    i15 = i14;
                    if ((constraintWidget3.mVerticalResolution == 2 ? i13 : i15) != 0) {
                    }
                    solverVariable10 = solverVariable8;
                    solverVariable11 = solverVariable9;
                    if (z7) {
                    }
                    if (constraintWidget4.mCenter.isConnected()) {
                    }
                    constraintWidget4.resolvedHorizontal = false;
                    constraintWidget4.resolvedVertical = false;
                }
            } else {
                solverVariable = createObjectVariable;
            }
            c2 = 0;
            z7 = false;
            i6 = i22;
            i7 = i23;
            i8 = i19;
            i9 = i20;
            int[] iArr22 = this.mResolvedMatchConstraintDefault;
            iArr22[c2] = i6;
            iArr22[1] = i7;
            if (z7) {
            }
            z8 = false;
            if (z7) {
            }
            DimensionBehaviour dimensionBehaviour6222 = this.mListDimensionBehaviors[0];
            dimensionBehaviour = DimensionBehaviour.WRAP_CONTENT;
            if (dimensionBehaviour6222 != dimensionBehaviour) {
            }
            if (z9) {
            }
            z10 = !this.mCenter.isConnected();
            boolean[] zArr2222 = this.mIsInBarrier;
            z11 = zArr2222[0];
            boolean z20222 = zArr2222[1];
            if (this.mHorizontalResolution != 2) {
            }
            z12 = z2;
            z13 = z3;
            dimensionBehaviour2 = dimensionBehaviour5;
            dimensionBehaviour3 = dimensionBehaviour;
            solverVariable2 = createObjectVariable5;
            solverVariable3 = createObjectVariable4;
            solverVariable4 = createObjectVariable3;
            solverVariable5 = createObjectVariable2;
            solverVariable6 = solverVariable;
            if (z) {
            }
            i15 = i14;
            if ((constraintWidget3.mVerticalResolution == 2 ? i13 : i15) != 0) {
            }
            solverVariable10 = solverVariable8;
            solverVariable11 = solverVariable9;
            if (z7) {
            }
            if (constraintWidget4.mCenter.isConnected()) {
            }
            constraintWidget4.resolvedHorizontal = false;
            constraintWidget4.resolvedVertical = false;
        }
        z2 = false;
        z3 = false;
        if (this.mVisibility == 8) {
            zArr = this.mIsInBarrier;
            if (!zArr[0]) {
                return;
            }
        }
        z4 = this.resolvedHorizontal;
        if (!z4) {
        }
        if (z4) {
        }
        if (this.resolvedVertical) {
        }
        if (this.resolvedHorizontal) {
            this.resolvedHorizontal = false;
            this.resolvedVertical = false;
            return;
        }
        metrics = LinearSystem.sMetrics;
        if (metrics != null) {
        }
        if (z) {
            dependencyNode2 = horizontalWidgetRun2.start;
            if (dependencyNode2.resolved) {
                if (metrics != null) {
                }
                linearSystem.addEquality(createObjectVariable, dependencyNode2.value);
                linearSystem.addEquality(createObjectVariable2, this.horizontalRun.end.value);
                linearSystem.addEquality(createObjectVariable3, this.verticalRun.start.value);
                linearSystem.addEquality(createObjectVariable4, this.verticalRun.end.value);
                linearSystem.addEquality(createObjectVariable5, this.verticalRun.baseline.value);
                if (this.mParent != null) {
                }
                this.resolvedHorizontal = false;
                this.resolvedVertical = false;
                return;
            }
        }
        if (metrics != null) {
        }
        if (this.mParent == null) {
        }
        i2 = this.f1954f;
        i3 = this.f1964p;
        if (i2 >= i3) {
        }
        i4 = this.f1955g;
        i5 = this.f1965q;
        if (i4 >= i5) {
        }
        DimensionBehaviour[] dimensionBehaviourArr4 = this.mListDimensionBehaviors;
        DimensionBehaviour dimensionBehaviour42 = dimensionBehaviourArr4[0];
        DimensionBehaviour dimensionBehaviour52 = DimensionBehaviour.MATCH_CONSTRAINT;
        int i192 = i3;
        if (dimensionBehaviour42 == dimensionBehaviour52) {
        }
        int i202 = i5;
        if (dimensionBehaviourArr4[1] == dimensionBehaviour52) {
        }
        int i212 = this.f1956h;
        this.f1949a = i212;
        f2 = this.mDimensionRatio;
        this.f1950b = f2;
        int i222 = this.mMatchConstraintDefaultWidth;
        int i232 = this.mMatchConstraintDefaultHeight;
        if (f2 <= 0.0f) {
        }
        c2 = 0;
        z7 = false;
        i6 = i222;
        i7 = i232;
        i8 = i192;
        i9 = i202;
        int[] iArr222 = this.mResolvedMatchConstraintDefault;
        iArr222[c2] = i6;
        iArr222[1] = i7;
        if (z7) {
        }
        z8 = false;
        if (z7) {
        }
        DimensionBehaviour dimensionBehaviour62222 = this.mListDimensionBehaviors[0];
        dimensionBehaviour = DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviour62222 != dimensionBehaviour) {
        }
        if (z9) {
        }
        z10 = !this.mCenter.isConnected();
        boolean[] zArr22222 = this.mIsInBarrier;
        z11 = zArr22222[0];
        boolean z202222 = zArr22222[1];
        if (this.mHorizontalResolution != 2) {
        }
        z12 = z2;
        z13 = z3;
        dimensionBehaviour2 = dimensionBehaviour52;
        dimensionBehaviour3 = dimensionBehaviour;
        solverVariable2 = createObjectVariable5;
        solverVariable3 = createObjectVariable4;
        solverVariable4 = createObjectVariable3;
        solverVariable5 = createObjectVariable2;
        solverVariable6 = solverVariable;
        if (z) {
        }
        i15 = i14;
        if ((constraintWidget3.mVerticalResolution == 2 ? i13 : i15) != 0) {
        }
        solverVariable10 = solverVariable8;
        solverVariable11 = solverVariable9;
        if (z7) {
        }
        if (constraintWidget4.mCenter.isConnected()) {
        }
        constraintWidget4.resolvedHorizontal = false;
        constraintWidget4.resolvedVertical = false;
    }

    public boolean allowedInBarrier() {
        return this.mVisibility != 8;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int b() {
        return this.f1957i + this.f1961m;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int c() {
        return this.f1958j + this.f1962n;
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2) {
        connect(type, constraintWidget, type2, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:71:0x0156, code lost:
        if (r11 != null) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0187, code lost:
        if (r11.isConnected() != false) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0189, code lost:
        r9.reset();
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01b6, code lost:
        if (r11.isConnected() != false) goto L92;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i2) {
        ConstraintAnchor opposite;
        ConstraintAnchor anchor;
        ConstraintAnchor anchor2;
        ConstraintAnchor.Type type3;
        ConstraintAnchor.Type type4;
        ConstraintAnchor anchor3;
        ConstraintAnchor.Type type5;
        boolean z;
        ConstraintAnchor.Type type6;
        ConstraintAnchor.Type type7 = ConstraintAnchor.Type.CENTER;
        if (type == type7) {
            ConstraintAnchor.Type type8 = ConstraintAnchor.Type.LEFT;
            if (type2 == type7) {
                ConstraintAnchor anchor4 = getAnchor(type8);
                ConstraintAnchor.Type type9 = ConstraintAnchor.Type.RIGHT;
                ConstraintAnchor anchor5 = getAnchor(type9);
                ConstraintAnchor.Type type10 = ConstraintAnchor.Type.TOP;
                ConstraintAnchor anchor6 = getAnchor(type10);
                ConstraintAnchor.Type type11 = ConstraintAnchor.Type.BOTTOM;
                ConstraintAnchor anchor7 = getAnchor(type11);
                boolean z2 = true;
                if ((anchor4 == null || !anchor4.isConnected()) && (anchor5 == null || !anchor5.isConnected())) {
                    connect(type8, constraintWidget, type8, 0);
                    connect(type9, constraintWidget, type9, 0);
                    z = true;
                } else {
                    z = false;
                }
                if ((anchor6 == null || !anchor6.isConnected()) && (anchor7 == null || !anchor7.isConnected())) {
                    connect(type10, constraintWidget, type10, 0);
                    connect(type11, constraintWidget, type11, 0);
                } else {
                    z2 = false;
                }
                if (!z || !z2) {
                    if (z) {
                        type6 = ConstraintAnchor.Type.CENTER_X;
                    } else if (!z2) {
                        return;
                    } else {
                        type6 = ConstraintAnchor.Type.CENTER_Y;
                    }
                    getAnchor(type6).connect(constraintWidget.getAnchor(type6), 0);
                    return;
                }
                anchor2 = getAnchor(type7);
                anchor3 = constraintWidget.getAnchor(type7);
            } else {
                if (type2 == type8 || type2 == ConstraintAnchor.Type.RIGHT) {
                    connect(type8, constraintWidget, type2, 0);
                    type5 = ConstraintAnchor.Type.RIGHT;
                } else {
                    ConstraintAnchor.Type type12 = ConstraintAnchor.Type.TOP;
                    if (type2 != type12 && type2 != ConstraintAnchor.Type.BOTTOM) {
                        return;
                    }
                    connect(type12, constraintWidget, type2, 0);
                    type5 = ConstraintAnchor.Type.BOTTOM;
                }
                connect(type5, constraintWidget, type2, 0);
                anchor2 = getAnchor(type7);
                anchor3 = constraintWidget.getAnchor(type2);
            }
        } else {
            ConstraintAnchor.Type type13 = ConstraintAnchor.Type.CENTER_X;
            if (type == type13 && (type2 == (type4 = ConstraintAnchor.Type.LEFT) || type2 == ConstraintAnchor.Type.RIGHT)) {
                ConstraintAnchor anchor8 = getAnchor(type4);
                anchor3 = constraintWidget.getAnchor(type2);
                ConstraintAnchor anchor9 = getAnchor(ConstraintAnchor.Type.RIGHT);
                anchor8.connect(anchor3, 0);
                anchor9.connect(anchor3, 0);
                anchor2 = getAnchor(type13);
            } else {
                ConstraintAnchor.Type type14 = ConstraintAnchor.Type.CENTER_Y;
                if (type == type14 && (type2 == (type3 = ConstraintAnchor.Type.TOP) || type2 == ConstraintAnchor.Type.BOTTOM)) {
                    ConstraintAnchor anchor10 = constraintWidget.getAnchor(type2);
                    getAnchor(type3).connect(anchor10, 0);
                    getAnchor(ConstraintAnchor.Type.BOTTOM).connect(anchor10, 0);
                    getAnchor(type14).connect(anchor10, 0);
                    return;
                }
                if (type == type13 && type2 == type13) {
                    ConstraintAnchor.Type type15 = ConstraintAnchor.Type.LEFT;
                    getAnchor(type15).connect(constraintWidget.getAnchor(type15), 0);
                    ConstraintAnchor.Type type16 = ConstraintAnchor.Type.RIGHT;
                    getAnchor(type16).connect(constraintWidget.getAnchor(type16), 0);
                    anchor2 = getAnchor(type13);
                } else if (type != type14 || type2 != type14) {
                    ConstraintAnchor anchor11 = getAnchor(type);
                    ConstraintAnchor anchor12 = constraintWidget.getAnchor(type2);
                    if (anchor11.isValidConnection(anchor12)) {
                        ConstraintAnchor.Type type17 = ConstraintAnchor.Type.BASELINE;
                        if (type == type17) {
                            ConstraintAnchor anchor13 = getAnchor(ConstraintAnchor.Type.TOP);
                            anchor = getAnchor(ConstraintAnchor.Type.BOTTOM);
                            if (anchor13 != null) {
                                anchor13.reset();
                            }
                        } else {
                            if (type == ConstraintAnchor.Type.TOP || type == ConstraintAnchor.Type.BOTTOM) {
                                ConstraintAnchor anchor14 = getAnchor(type17);
                                if (anchor14 != null) {
                                    anchor14.reset();
                                }
                                ConstraintAnchor anchor15 = getAnchor(type7);
                                if (anchor15.getTarget() != anchor12) {
                                    anchor15.reset();
                                }
                                opposite = getAnchor(type).getOpposite();
                                anchor = getAnchor(type14);
                            } else if (type == ConstraintAnchor.Type.LEFT || type == ConstraintAnchor.Type.RIGHT) {
                                ConstraintAnchor anchor16 = getAnchor(type7);
                                if (anchor16.getTarget() != anchor12) {
                                    anchor16.reset();
                                }
                                opposite = getAnchor(type).getOpposite();
                                anchor = getAnchor(type13);
                            }
                            anchor.reset();
                        }
                        anchor11.connect(anchor12, i2);
                        return;
                    }
                    return;
                } else {
                    ConstraintAnchor.Type type18 = ConstraintAnchor.Type.TOP;
                    getAnchor(type18).connect(constraintWidget.getAnchor(type18), 0);
                    ConstraintAnchor.Type type19 = ConstraintAnchor.Type.BOTTOM;
                    getAnchor(type19).connect(constraintWidget.getAnchor(type19), 0);
                    anchor2 = getAnchor(type14);
                }
                anchor3 = constraintWidget.getAnchor(type2);
            }
        }
        anchor2.connect(anchor3, 0);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i2) {
        if (constraintAnchor.getOwner() == this) {
            connect(constraintAnchor.getType(), constraintAnchor2.getOwner(), constraintAnchor2.getType(), i2);
        }
    }

    public void connectCircularConstraint(ConstraintWidget constraintWidget, float f2, int i2) {
        ConstraintAnchor.Type type = ConstraintAnchor.Type.CENTER;
        immediateConnect(type, constraintWidget, type, i2, 0);
        this.mCircleConstraintAngle = f2;
    }

    public void copy(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> hashMap) {
        this.mHorizontalResolution = constraintWidget.mHorizontalResolution;
        this.mVerticalResolution = constraintWidget.mVerticalResolution;
        this.mMatchConstraintDefaultWidth = constraintWidget.mMatchConstraintDefaultWidth;
        this.mMatchConstraintDefaultHeight = constraintWidget.mMatchConstraintDefaultHeight;
        int[] iArr = this.mResolvedMatchConstraintDefault;
        int[] iArr2 = constraintWidget.mResolvedMatchConstraintDefault;
        iArr[0] = iArr2[0];
        iArr[1] = iArr2[1];
        this.mMatchConstraintMinWidth = constraintWidget.mMatchConstraintMinWidth;
        this.mMatchConstraintMaxWidth = constraintWidget.mMatchConstraintMaxWidth;
        this.mMatchConstraintMinHeight = constraintWidget.mMatchConstraintMinHeight;
        this.mMatchConstraintMaxHeight = constraintWidget.mMatchConstraintMaxHeight;
        this.mMatchConstraintPercentHeight = constraintWidget.mMatchConstraintPercentHeight;
        this.mIsWidthWrapContent = constraintWidget.mIsWidthWrapContent;
        this.mIsHeightWrapContent = constraintWidget.mIsHeightWrapContent;
        this.f1949a = constraintWidget.f1949a;
        this.f1950b = constraintWidget.f1950b;
        int[] iArr3 = constraintWidget.mMaxDimension;
        this.mMaxDimension = Arrays.copyOf(iArr3, iArr3.length);
        this.mCircleConstraintAngle = constraintWidget.mCircleConstraintAngle;
        this.hasBaseline = constraintWidget.hasBaseline;
        this.inPlaceholder = constraintWidget.inPlaceholder;
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.f1951c.reset();
        this.f1952d.reset();
        this.mCenter.reset();
        this.mListDimensionBehaviors = (DimensionBehaviour[]) Arrays.copyOf(this.mListDimensionBehaviors, 2);
        this.mParent = this.mParent == null ? null : hashMap.get(constraintWidget.mParent);
        this.f1954f = constraintWidget.f1954f;
        this.f1955g = constraintWidget.f1955g;
        this.mDimensionRatio = constraintWidget.mDimensionRatio;
        this.f1956h = constraintWidget.f1956h;
        this.f1957i = constraintWidget.f1957i;
        this.f1958j = constraintWidget.f1958j;
        this.f1959k = constraintWidget.f1959k;
        this.f1960l = constraintWidget.f1960l;
        this.f1961m = constraintWidget.f1961m;
        this.f1962n = constraintWidget.f1962n;
        this.f1963o = constraintWidget.f1963o;
        this.f1964p = constraintWidget.f1964p;
        this.f1965q = constraintWidget.f1965q;
        this.f1966r = constraintWidget.f1966r;
        this.f1967s = constraintWidget.f1967s;
        this.mCompanionWidget = constraintWidget.mCompanionWidget;
        this.mContainerItemSkip = constraintWidget.mContainerItemSkip;
        this.mVisibility = constraintWidget.mVisibility;
        this.mDebugName = constraintWidget.mDebugName;
        this.mType = constraintWidget.mType;
        this.f1968t = constraintWidget.f1968t;
        this.u = constraintWidget.u;
        this.v = constraintWidget.v;
        this.w = constraintWidget.w;
        this.x = constraintWidget.x;
        this.y = constraintWidget.y;
        this.z = constraintWidget.z;
        this.A = constraintWidget.A;
        this.B = constraintWidget.B;
        this.C = constraintWidget.C;
        this.D = constraintWidget.D;
        this.E = constraintWidget.E;
        this.F = constraintWidget.F;
        this.G = constraintWidget.G;
        float[] fArr = this.mWeight;
        float[] fArr2 = constraintWidget.mWeight;
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[1];
        ConstraintWidget[] constraintWidgetArr = this.H;
        ConstraintWidget[] constraintWidgetArr2 = constraintWidget.H;
        constraintWidgetArr[0] = constraintWidgetArr2[0];
        constraintWidgetArr[1] = constraintWidgetArr2[1];
        ConstraintWidget[] constraintWidgetArr3 = this.I;
        ConstraintWidget[] constraintWidgetArr4 = constraintWidget.I;
        constraintWidgetArr3[0] = constraintWidgetArr4[0];
        constraintWidgetArr3[1] = constraintWidgetArr4[1];
        ConstraintWidget constraintWidget2 = constraintWidget.J;
        this.J = constraintWidget2 == null ? null : hashMap.get(constraintWidget2);
        ConstraintWidget constraintWidget3 = constraintWidget.K;
        this.K = constraintWidget3 != null ? hashMap.get(constraintWidget3) : null;
    }

    public void createObjectVariables(LinearSystem linearSystem) {
        linearSystem.createObjectVariable(this.mLeft);
        linearSystem.createObjectVariable(this.mTop);
        linearSystem.createObjectVariable(this.mRight);
        linearSystem.createObjectVariable(this.mBottom);
        if (this.f1963o > 0) {
            linearSystem.createObjectVariable(this.mBaseline);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d(int i2, boolean z) {
        this.mIsInBarrier[i2] = z;
    }

    public void ensureMeasureRequested() {
        this.mMeasureRequested = true;
    }

    public void ensureWidgetRuns() {
        if (this.horizontalRun == null) {
            this.horizontalRun = new HorizontalWidgetRun(this);
        }
        if (this.verticalRun == null) {
            this.verticalRun = new VerticalWidgetRun(this);
        }
    }

    public ConstraintAnchor getAnchor(ConstraintAnchor.Type type) {
        switch (AnonymousClass1.f1969a[type.ordinal()]) {
            case 1:
                return this.mLeft;
            case 2:
                return this.mTop;
            case 3:
                return this.mRight;
            case 4:
                return this.mBottom;
            case 5:
                return this.mBaseline;
            case 6:
                return this.mCenter;
            case 7:
                return this.f1951c;
            case 8:
                return this.f1952d;
            case 9:
                return null;
            default:
                throw new AssertionError(type.name());
        }
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.f1953e;
    }

    public int getBaselineDistance() {
        return this.f1963o;
    }

    public float getBiasPercent(int i2) {
        if (i2 == 0) {
            return this.f1966r;
        }
        if (i2 == 1) {
            return this.f1967s;
        }
        return -1.0f;
    }

    public int getBottom() {
        return getY() + this.f1955g;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public int getContainerItemSkip() {
        return this.mContainerItemSkip;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public DimensionBehaviour getDimensionBehaviour(int i2) {
        if (i2 == 0) {
            return getHorizontalDimensionBehaviour();
        }
        if (i2 == 1) {
            return getVerticalDimensionBehaviour();
        }
        return null;
    }

    public float getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getDimensionRatioSide() {
        return this.f1956h;
    }

    public boolean getHasBaseline() {
        return this.hasBaseline;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.f1955g;
    }

    public float getHorizontalBiasPercent() {
        return this.f1966r;
    }

    public ConstraintWidget getHorizontalChainControlWidget() {
        if (isInHorizontalChain()) {
            ConstraintWidget constraintWidget = this;
            ConstraintWidget constraintWidget2 = null;
            while (constraintWidget2 == null && constraintWidget != null) {
                ConstraintAnchor anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT);
                ConstraintAnchor target = anchor == null ? null : anchor.getTarget();
                ConstraintWidget owner = target == null ? null : target.getOwner();
                if (owner == getParent()) {
                    return constraintWidget;
                }
                ConstraintAnchor target2 = owner == null ? null : owner.getAnchor(ConstraintAnchor.Type.RIGHT).getTarget();
                if (target2 == null || target2.getOwner() == constraintWidget) {
                    constraintWidget = owner;
                } else {
                    constraintWidget2 = constraintWidget;
                }
            }
            return constraintWidget2;
        }
        return null;
    }

    public int getHorizontalChainStyle() {
        return this.D;
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public int getHorizontalMargin() {
        ConstraintAnchor constraintAnchor = this.mLeft;
        int i2 = constraintAnchor != null ? 0 + constraintAnchor.mMargin : 0;
        ConstraintAnchor constraintAnchor2 = this.mRight;
        return constraintAnchor2 != null ? i2 + constraintAnchor2.mMargin : i2;
    }

    public int getLastHorizontalMeasureSpec() {
        return this.mLastHorizontalMeasureSpec;
    }

    public int getLastVerticalMeasureSpec() {
        return this.mLastVerticalMeasureSpec;
    }

    public int getLeft() {
        return getX();
    }

    public int getLength(int i2) {
        if (i2 == 0) {
            return getWidth();
        }
        if (i2 == 1) {
            return getHeight();
        }
        return 0;
    }

    public int getMaxHeight() {
        return this.mMaxDimension[1];
    }

    public int getMaxWidth() {
        return this.mMaxDimension[0];
    }

    public int getMinHeight() {
        return this.f1965q;
    }

    public int getMinWidth() {
        return this.f1964p;
    }

    public ConstraintWidget getNextChainMember(int i2) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        if (i2 != 0) {
            if (i2 == 1 && (constraintAnchor2 = (constraintAnchor = this.mBottom).mTarget) != null && constraintAnchor2.mTarget == constraintAnchor) {
                return constraintAnchor2.mOwner;
            }
            return null;
        }
        ConstraintAnchor constraintAnchor3 = this.mRight;
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.mTarget;
        if (constraintAnchor4 == null || constraintAnchor4.mTarget != constraintAnchor3) {
            return null;
        }
        return constraintAnchor4.mOwner;
    }

    public int getOptimizerWrapHeight() {
        int i2;
        int i3 = this.f1955g;
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT) {
            if (this.mMatchConstraintDefaultHeight == 1) {
                i2 = Math.max(this.mMatchConstraintMinHeight, i3);
            } else {
                i2 = this.mMatchConstraintMinHeight;
                if (i2 > 0) {
                    this.f1955g = i2;
                } else {
                    i2 = 0;
                }
            }
            int i4 = this.mMatchConstraintMaxHeight;
            return (i4 <= 0 || i4 >= i2) ? i2 : i4;
        }
        return i3;
    }

    public int getOptimizerWrapWidth() {
        int i2;
        int i3 = this.f1954f;
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT) {
            if (this.mMatchConstraintDefaultWidth == 1) {
                i2 = Math.max(this.mMatchConstraintMinWidth, i3);
            } else {
                i2 = this.mMatchConstraintMinWidth;
                if (i2 > 0) {
                    this.f1954f = i2;
                } else {
                    i2 = 0;
                }
            }
            int i4 = this.mMatchConstraintMaxWidth;
            return (i4 <= 0 || i4 >= i2) ? i2 : i4;
        }
        return i3;
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public ConstraintWidget getPreviousChainMember(int i2) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        if (i2 != 0) {
            if (i2 == 1 && (constraintAnchor2 = (constraintAnchor = this.mTop).mTarget) != null && constraintAnchor2.mTarget == constraintAnchor) {
                return constraintAnchor2.mOwner;
            }
            return null;
        }
        ConstraintAnchor constraintAnchor3 = this.mLeft;
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.mTarget;
        if (constraintAnchor4 == null || constraintAnchor4.mTarget != constraintAnchor3) {
            return null;
        }
        return constraintAnchor4.mOwner;
    }

    public int getRight() {
        return getX() + this.f1954f;
    }

    public WidgetRun getRun(int i2) {
        if (i2 == 0) {
            return this.horizontalRun;
        }
        if (i2 == 1) {
            return this.verticalRun;
        }
        return null;
    }

    public int getTop() {
        return getY();
    }

    public String getType() {
        return this.mType;
    }

    public float getVerticalBiasPercent() {
        return this.f1967s;
    }

    public ConstraintWidget getVerticalChainControlWidget() {
        if (isInVerticalChain()) {
            ConstraintWidget constraintWidget = this;
            ConstraintWidget constraintWidget2 = null;
            while (constraintWidget2 == null && constraintWidget != null) {
                ConstraintAnchor anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.TOP);
                ConstraintAnchor target = anchor == null ? null : anchor.getTarget();
                ConstraintWidget owner = target == null ? null : target.getOwner();
                if (owner == getParent()) {
                    return constraintWidget;
                }
                ConstraintAnchor target2 = owner == null ? null : owner.getAnchor(ConstraintAnchor.Type.BOTTOM).getTarget();
                if (target2 == null || target2.getOwner() == constraintWidget) {
                    constraintWidget = owner;
                } else {
                    constraintWidget2 = constraintWidget;
                }
            }
            return constraintWidget2;
        }
        return null;
    }

    public int getVerticalChainStyle() {
        return this.E;
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public int getVerticalMargin() {
        int i2 = this.mLeft != null ? 0 + this.mTop.mMargin : 0;
        return this.mRight != null ? i2 + this.mBottom.mMargin : i2;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.f1954f;
    }

    public int getWrapBehaviorInParent() {
        return this.mWrapBehaviorInParent;
    }

    public int getX() {
        ConstraintWidget constraintWidget = this.mParent;
        return (constraintWidget == null || !(constraintWidget instanceof ConstraintWidgetContainer)) ? this.f1957i : ((ConstraintWidgetContainer) constraintWidget).O + this.f1957i;
    }

    public int getY() {
        ConstraintWidget constraintWidget = this.mParent;
        return (constraintWidget == null || !(constraintWidget instanceof ConstraintWidgetContainer)) ? this.f1958j : ((ConstraintWidgetContainer) constraintWidget).P + this.f1958j;
    }

    public boolean hasBaseline() {
        return this.hasBaseline;
    }

    public boolean hasDanglingDimension(int i2) {
        if (i2 == 0) {
            return (this.mLeft.mTarget != null ? 1 : 0) + (this.mRight.mTarget != null ? 1 : 0) < 2;
        }
        return ((this.mTop.mTarget != null ? 1 : 0) + (this.mBottom.mTarget != null ? 1 : 0)) + (this.mBaseline.mTarget != null ? 1 : 0) < 2;
    }

    public boolean hasDependencies() {
        int size = this.f1953e.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((ConstraintAnchor) this.f1953e.get(i2)).hasDependents()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasDimensionOverride() {
        return (this.mWidthOverride == -1 && this.mHeightOverride == -1) ? false : true;
    }

    public boolean hasResolvedTargets(int i2, int i3) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        if (i2 == 0) {
            ConstraintAnchor constraintAnchor3 = this.mLeft.mTarget;
            return constraintAnchor3 != null && constraintAnchor3.hasFinalValue() && (constraintAnchor2 = this.mRight.mTarget) != null && constraintAnchor2.hasFinalValue() && (this.mRight.mTarget.getFinalValue() - this.mRight.getMargin()) - (this.mLeft.mTarget.getFinalValue() + this.mLeft.getMargin()) >= i3;
        }
        ConstraintAnchor constraintAnchor4 = this.mTop.mTarget;
        return constraintAnchor4 != null && constraintAnchor4.hasFinalValue() && (constraintAnchor = this.mBottom.mTarget) != null && constraintAnchor.hasFinalValue() && (this.mBottom.mTarget.getFinalValue() - this.mBottom.getMargin()) - (this.mTop.mTarget.getFinalValue() + this.mTop.getMargin()) >= i3;
        return false;
    }

    public void immediateConnect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i2, int i3) {
        getAnchor(type).connect(constraintWidget.getAnchor(type2), i2, i3, true);
    }

    public boolean isHeightWrapContent() {
        return this.mIsHeightWrapContent;
    }

    public boolean isHorizontalSolvingPassDone() {
        return this.horizontalSolvingPass;
    }

    public boolean isInBarrier(int i2) {
        return this.mIsInBarrier[i2];
    }

    public boolean isInHorizontalChain() {
        ConstraintAnchor constraintAnchor = this.mLeft;
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 == null || constraintAnchor2.mTarget != constraintAnchor) {
            ConstraintAnchor constraintAnchor3 = this.mRight;
            ConstraintAnchor constraintAnchor4 = constraintAnchor3.mTarget;
            return constraintAnchor4 != null && constraintAnchor4.mTarget == constraintAnchor3;
        }
        return true;
    }

    public boolean isInPlaceholder() {
        return this.inPlaceholder;
    }

    public boolean isInVerticalChain() {
        ConstraintAnchor constraintAnchor = this.mTop;
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 == null || constraintAnchor2.mTarget != constraintAnchor) {
            ConstraintAnchor constraintAnchor3 = this.mBottom;
            ConstraintAnchor constraintAnchor4 = constraintAnchor3.mTarget;
            return constraintAnchor4 != null && constraintAnchor4.mTarget == constraintAnchor3;
        }
        return true;
    }

    public boolean isInVirtualLayout() {
        return this.mInVirtualLayout;
    }

    public boolean isMeasureRequested() {
        return this.mMeasureRequested && this.mVisibility != 8;
    }

    public boolean isResolvedHorizontally() {
        return this.resolvedHorizontal || (this.mLeft.hasFinalValue() && this.mRight.hasFinalValue());
    }

    public boolean isResolvedVertically() {
        return this.resolvedVertical || (this.mTop.hasFinalValue() && this.mBottom.hasFinalValue());
    }

    public boolean isRoot() {
        return this.mParent == null;
    }

    public boolean isSpreadHeight() {
        return this.mMatchConstraintDefaultHeight == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinHeight == 0 && this.mMatchConstraintMaxHeight == 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isSpreadWidth() {
        return this.mMatchConstraintDefaultWidth == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMaxWidth == 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isVerticalSolvingPassDone() {
        return this.verticalSolvingPass;
    }

    public boolean isWidthWrapContent() {
        return this.mIsWidthWrapContent;
    }

    public void markHorizontalSolvingPassDone() {
        this.horizontalSolvingPass = true;
    }

    public void markVerticalSolvingPassDone() {
        this.verticalSolvingPass = true;
    }

    public boolean oppositeDimensionDependsOn(int i2) {
        char c2 = i2 == 0 ? (char) 1 : (char) 0;
        DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
        DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[i2];
        DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[c2];
        DimensionBehaviour dimensionBehaviour3 = DimensionBehaviour.MATCH_CONSTRAINT;
        return dimensionBehaviour == dimensionBehaviour3 && dimensionBehaviour2 == dimensionBehaviour3;
    }

    public boolean oppositeDimensionsTied() {
        DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
        DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
        DimensionBehaviour dimensionBehaviour2 = DimensionBehaviour.MATCH_CONSTRAINT;
        return dimensionBehaviour == dimensionBehaviour2 && dimensionBehaviourArr[1] == dimensionBehaviour2;
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.f1951c.reset();
        this.f1952d.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mCircleConstraintAngle = 0.0f;
        this.f1954f = 0;
        this.f1955g = 0;
        this.mDimensionRatio = 0.0f;
        this.f1956h = -1;
        this.f1957i = 0;
        this.f1958j = 0;
        this.f1961m = 0;
        this.f1962n = 0;
        this.f1963o = 0;
        this.f1964p = 0;
        this.f1965q = 0;
        float f2 = DEFAULT_BIAS;
        this.f1966r = f2;
        this.f1967s = f2;
        DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        dimensionBehaviourArr[0] = dimensionBehaviour;
        dimensionBehaviourArr[1] = dimensionBehaviour;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mType = null;
        this.B = false;
        this.C = false;
        this.D = 0;
        this.E = 0;
        this.F = false;
        this.G = false;
        float[] fArr = this.mWeight;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        int[] iArr = this.mMaxDimension;
        iArr[0] = Integer.MAX_VALUE;
        iArr[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = Integer.MAX_VALUE;
        this.mMatchConstraintMaxHeight = Integer.MAX_VALUE;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.f1949a = -1;
        this.f1950b = 1.0f;
        boolean[] zArr = this.isTerminalWidget;
        zArr[0] = true;
        zArr[1] = true;
        this.mInVirtualLayout = false;
        boolean[] zArr2 = this.mIsInBarrier;
        zArr2[0] = false;
        zArr2[1] = false;
        this.mMeasureRequested = true;
        int[] iArr2 = this.mResolvedMatchConstraintDefault;
        iArr2[0] = 0;
        iArr2[1] = 0;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
    }

    public void resetAllConstraints() {
        resetAnchors();
        setVerticalBiasPercent(DEFAULT_BIAS);
        setHorizontalBiasPercent(DEFAULT_BIAS);
    }

    public void resetAnchor(ConstraintAnchor constraintAnchor) {
        if (getParent() != null && (getParent() instanceof ConstraintWidgetContainer) && ((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            return;
        }
        ConstraintAnchor anchor = getAnchor(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor anchor2 = getAnchor(ConstraintAnchor.Type.RIGHT);
        ConstraintAnchor anchor3 = getAnchor(ConstraintAnchor.Type.TOP);
        ConstraintAnchor anchor4 = getAnchor(ConstraintAnchor.Type.BOTTOM);
        ConstraintAnchor anchor5 = getAnchor(ConstraintAnchor.Type.CENTER);
        ConstraintAnchor anchor6 = getAnchor(ConstraintAnchor.Type.CENTER_X);
        ConstraintAnchor anchor7 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
        if (constraintAnchor != anchor5) {
            if (constraintAnchor == anchor6) {
                if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget().getOwner() == anchor2.getTarget().getOwner()) {
                    anchor.reset();
                    anchor2.reset();
                }
                this.f1966r = 0.5f;
            } else if (constraintAnchor == anchor7) {
                if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget().getOwner() == anchor4.getTarget().getOwner()) {
                    anchor3.reset();
                    anchor4.reset();
                }
            } else if (constraintAnchor == anchor || constraintAnchor == anchor2 ? !(!anchor.isConnected() || anchor.getTarget() != anchor2.getTarget()) : !((constraintAnchor != anchor3 && constraintAnchor != anchor4) || !anchor3.isConnected() || anchor3.getTarget() != anchor4.getTarget())) {
                anchor5.reset();
            }
            constraintAnchor.reset();
        }
        if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
            anchor.reset();
            anchor2.reset();
        }
        if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
            anchor3.reset();
            anchor4.reset();
        }
        this.f1966r = 0.5f;
        this.f1967s = 0.5f;
        constraintAnchor.reset();
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent != null && (parent instanceof ConstraintWidgetContainer) && ((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            return;
        }
        int size = this.f1953e.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((ConstraintAnchor) this.f1953e.get(i2)).reset();
        }
    }

    public void resetFinalResolution() {
        this.resolvedHorizontal = false;
        this.resolvedVertical = false;
        this.horizontalSolvingPass = false;
        this.verticalSolvingPass = false;
        int size = this.f1953e.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((ConstraintAnchor) this.f1953e.get(i2)).resetFinalResolution();
        }
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.f1951c.resetSolverVariable(cache);
        this.f1952d.resetSolverVariable(cache);
    }

    public void resetSolvingPassFlag() {
        this.horizontalSolvingPass = false;
        this.verticalSolvingPass = false;
    }

    public StringBuilder serialize(StringBuilder sb) {
        sb.append("{\n");
        serializeAnchor(sb, "left", this.mLeft);
        serializeAnchor(sb, "top", this.mTop);
        serializeAnchor(sb, "right", this.mRight);
        serializeAnchor(sb, "bottom", this.mBottom);
        serializeAnchor(sb, "baseline", this.mBaseline);
        serializeAnchor(sb, "centerX", this.f1951c);
        serializeAnchor(sb, "centerY", this.f1952d);
        serializeCircle(sb, this.mCenter, this.mCircleConstraintAngle);
        serializeSize(sb, "width", this.f1954f, this.f1964p, this.mMaxDimension[0], this.mWidthOverride, this.mMatchConstraintMinWidth, this.mMatchConstraintDefaultWidth, this.mMatchConstraintPercentWidth, this.mWeight[0]);
        serializeSize(sb, "height", this.f1955g, this.f1965q, this.mMaxDimension[1], this.mHeightOverride, this.mMatchConstraintMinHeight, this.mMatchConstraintDefaultHeight, this.mMatchConstraintPercentHeight, this.mWeight[1]);
        serializeDimensionRatio(sb, "dimensionRatio", this.mDimensionRatio, this.f1956h);
        serializeAttribute(sb, "horizontalBias", this.f1966r, DEFAULT_BIAS);
        serializeAttribute(sb, "verticalBias", this.f1967s, DEFAULT_BIAS);
        sb.append("}\n");
        return sb;
    }

    public void setBaselineDistance(int i2) {
        this.f1963o = i2;
        this.hasBaseline = i2 > 0;
    }

    public void setCompanionWidget(Object obj) {
        this.mCompanionWidget = obj;
    }

    public void setContainerItemSkip(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        this.mContainerItemSkip = i2;
    }

    public void setDebugName(String str) {
        this.mDebugName = str;
    }

    public void setDebugSolverName(LinearSystem linearSystem, String str) {
        this.mDebugName = str;
        SolverVariable createObjectVariable = linearSystem.createObjectVariable(this.mLeft);
        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(this.mTop);
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(this.mRight);
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(this.mBottom);
        createObjectVariable.setName(str + ".left");
        createObjectVariable2.setName(str + ".top");
        createObjectVariable3.setName(str + ".right");
        createObjectVariable4.setName(str + ".bottom");
        SolverVariable createObjectVariable5 = linearSystem.createObjectVariable(this.mBaseline);
        createObjectVariable5.setName(str + ".baseline");
    }

    public void setDimension(int i2, int i3) {
        this.f1954f = i2;
        int i4 = this.f1964p;
        if (i2 < i4) {
            this.f1954f = i4;
        }
        this.f1955g = i3;
        int i5 = this.f1965q;
        if (i3 < i5) {
            this.f1955g = i5;
        }
    }

    public void setDimensionRatio(float f2, int i2) {
        this.mDimensionRatio = f2;
        this.f1956h = i2;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0084 -> B:39:0x0085). Please submit an issue!!! */
    public void setDimensionRatio(String str) {
        float f2;
        int i2 = 0;
        if (str == null || str.length() == 0) {
            this.mDimensionRatio = 0.0f;
            return;
        }
        int i3 = -1;
        int length = str.length();
        int indexOf = str.indexOf(44);
        int i4 = 0;
        if (indexOf > 0 && indexOf < length - 1) {
            String substring = str.substring(0, indexOf);
            if (substring.equalsIgnoreCase(ExifInterface.LONGITUDE_WEST)) {
                i3 = 0;
            } else if (substring.equalsIgnoreCase("H")) {
                i3 = 1;
            }
            i4 = indexOf + 1;
        }
        int indexOf2 = str.indexOf(58);
        if (indexOf2 < 0 || indexOf2 >= length - 1) {
            String substring2 = str.substring(i4);
            if (substring2.length() > 0) {
                f2 = Float.parseFloat(substring2);
            }
            f2 = i2;
        } else {
            String substring3 = str.substring(i4, indexOf2);
            String substring4 = str.substring(indexOf2 + 1);
            if (substring3.length() > 0 && substring4.length() > 0) {
                float parseFloat = Float.parseFloat(substring3);
                float parseFloat2 = Float.parseFloat(substring4);
                if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                    f2 = i3 == 1 ? Math.abs(parseFloat2 / parseFloat) : Math.abs(parseFloat / parseFloat2);
                }
            }
            f2 = i2;
        }
        i2 = (f2 > i2 ? 1 : (f2 == i2 ? 0 : -1));
        if (i2 > 0) {
            this.mDimensionRatio = f2;
            this.f1956h = i3;
        }
    }

    public void setFinalBaseline(int i2) {
        if (this.hasBaseline) {
            int i3 = i2 - this.f1963o;
            int i4 = this.f1955g + i3;
            this.f1958j = i3;
            this.mTop.setFinalValue(i3);
            this.mBottom.setFinalValue(i4);
            this.mBaseline.setFinalValue(i2);
            this.resolvedVertical = true;
        }
    }

    public void setFinalFrame(int i2, int i3, int i4, int i5, int i6, int i7) {
        setFrame(i2, i3, i4, i5);
        setBaselineDistance(i6);
        if (i7 != 0) {
            if (i7 == 1) {
                this.resolvedHorizontal = false;
            } else if (i7 == 2) {
                this.resolvedHorizontal = true;
            } else {
                this.resolvedHorizontal = false;
            }
            this.resolvedVertical = true;
            return;
        }
        this.resolvedHorizontal = true;
        this.resolvedVertical = false;
    }

    public void setFinalHorizontal(int i2, int i3) {
        if (this.resolvedHorizontal) {
            return;
        }
        this.mLeft.setFinalValue(i2);
        this.mRight.setFinalValue(i3);
        this.f1957i = i2;
        this.f1954f = i3 - i2;
        this.resolvedHorizontal = true;
    }

    public void setFinalLeft(int i2) {
        this.mLeft.setFinalValue(i2);
        this.f1957i = i2;
    }

    public void setFinalTop(int i2) {
        this.mTop.setFinalValue(i2);
        this.f1958j = i2;
    }

    public void setFinalVertical(int i2, int i3) {
        if (this.resolvedVertical) {
            return;
        }
        this.mTop.setFinalValue(i2);
        this.mBottom.setFinalValue(i3);
        this.f1958j = i2;
        this.f1955g = i3 - i2;
        if (this.hasBaseline) {
            this.mBaseline.setFinalValue(i2 + this.f1963o);
        }
        this.resolvedVertical = true;
    }

    public void setFrame(int i2, int i3, int i4) {
        if (i4 == 0) {
            setHorizontalDimension(i2, i3);
        } else if (i4 == 1) {
            setVerticalDimension(i2, i3);
        }
    }

    public void setFrame(int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8 = i4 - i2;
        int i9 = i5 - i3;
        this.f1957i = i2;
        this.f1958j = i3;
        if (this.mVisibility == 8) {
            this.f1954f = 0;
            this.f1955g = 0;
            return;
        }
        DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
        DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
        DimensionBehaviour dimensionBehaviour2 = DimensionBehaviour.FIXED;
        if (dimensionBehaviour == dimensionBehaviour2 && i8 < (i7 = this.f1954f)) {
            i8 = i7;
        }
        if (dimensionBehaviourArr[1] == dimensionBehaviour2 && i9 < (i6 = this.f1955g)) {
            i9 = i6;
        }
        this.f1954f = i8;
        this.f1955g = i9;
        int i10 = this.f1965q;
        if (i9 < i10) {
            this.f1955g = i10;
        }
        int i11 = this.f1964p;
        if (i8 < i11) {
            this.f1954f = i11;
        }
        int i12 = this.mMatchConstraintMaxWidth;
        if (i12 > 0 && dimensionBehaviourArr[0] == DimensionBehaviour.MATCH_CONSTRAINT) {
            this.f1954f = Math.min(this.f1954f, i12);
        }
        int i13 = this.mMatchConstraintMaxHeight;
        if (i13 > 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT) {
            this.f1955g = Math.min(this.f1955g, i13);
        }
        int i14 = this.f1954f;
        if (i8 != i14) {
            this.mWidthOverride = i14;
        }
        int i15 = this.f1955g;
        if (i9 != i15) {
            this.mHeightOverride = i15;
        }
    }

    public void setGoneMargin(ConstraintAnchor.Type type, int i2) {
        ConstraintAnchor constraintAnchor;
        int i3 = AnonymousClass1.f1969a[type.ordinal()];
        if (i3 == 1) {
            constraintAnchor = this.mLeft;
        } else if (i3 == 2) {
            constraintAnchor = this.mTop;
        } else if (i3 == 3) {
            constraintAnchor = this.mRight;
        } else if (i3 == 4) {
            constraintAnchor = this.mBottom;
        } else if (i3 != 5) {
            return;
        } else {
            constraintAnchor = this.mBaseline;
        }
        constraintAnchor.f1946a = i2;
    }

    public void setHasBaseline(boolean z) {
        this.hasBaseline = z;
    }

    public void setHeight(int i2) {
        this.f1955g = i2;
        int i3 = this.f1965q;
        if (i2 < i3) {
            this.f1955g = i3;
        }
    }

    public void setHeightWrapContent(boolean z) {
        this.mIsHeightWrapContent = z;
    }

    public void setHorizontalBiasPercent(float f2) {
        this.f1966r = f2;
    }

    public void setHorizontalChainStyle(int i2) {
        this.D = i2;
    }

    public void setHorizontalDimension(int i2, int i3) {
        this.f1957i = i2;
        int i4 = i3 - i2;
        this.f1954f = i4;
        int i5 = this.f1964p;
        if (i4 < i5) {
            this.f1954f = i5;
        }
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[0] = dimensionBehaviour;
    }

    public void setHorizontalMatchStyle(int i2, int i3, int i4, float f2) {
        this.mMatchConstraintDefaultWidth = i2;
        this.mMatchConstraintMinWidth = i3;
        if (i4 == Integer.MAX_VALUE) {
            i4 = 0;
        }
        this.mMatchConstraintMaxWidth = i4;
        this.mMatchConstraintPercentWidth = f2;
        if (f2 <= 0.0f || f2 >= 1.0f || i2 != 0) {
            return;
        }
        this.mMatchConstraintDefaultWidth = 2;
    }

    public void setHorizontalWeight(float f2) {
        this.mWeight[0] = f2;
    }

    public void setInPlaceholder(boolean z) {
        this.inPlaceholder = z;
    }

    public void setInVirtualLayout(boolean z) {
        this.mInVirtualLayout = z;
    }

    public void setLastMeasureSpec(int i2, int i3) {
        this.mLastHorizontalMeasureSpec = i2;
        this.mLastVerticalMeasureSpec = i3;
        setMeasureRequested(false);
    }

    public void setLength(int i2, int i3) {
        if (i3 == 0) {
            setWidth(i2);
        } else if (i3 == 1) {
            setHeight(i2);
        }
    }

    public void setMaxHeight(int i2) {
        this.mMaxDimension[1] = i2;
    }

    public void setMaxWidth(int i2) {
        this.mMaxDimension[0] = i2;
    }

    public void setMeasureRequested(boolean z) {
        this.mMeasureRequested = z;
    }

    public void setMinHeight(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        this.f1965q = i2;
    }

    public void setMinWidth(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        this.f1964p = i2;
    }

    public void setOffset(int i2, int i3) {
        this.f1961m = i2;
        this.f1962n = i3;
    }

    public void setOrigin(int i2, int i3) {
        this.f1957i = i2;
        this.f1958j = i3;
    }

    public void setParent(ConstraintWidget constraintWidget) {
        this.mParent = constraintWidget;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public void setVerticalBiasPercent(float f2) {
        this.f1967s = f2;
    }

    public void setVerticalChainStyle(int i2) {
        this.E = i2;
    }

    public void setVerticalDimension(int i2, int i3) {
        this.f1958j = i2;
        int i4 = i3 - i2;
        this.f1955g = i4;
        int i5 = this.f1965q;
        if (i4 < i5) {
            this.f1955g = i5;
        }
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[1] = dimensionBehaviour;
    }

    public void setVerticalMatchStyle(int i2, int i3, int i4, float f2) {
        this.mMatchConstraintDefaultHeight = i2;
        this.mMatchConstraintMinHeight = i3;
        if (i4 == Integer.MAX_VALUE) {
            i4 = 0;
        }
        this.mMatchConstraintMaxHeight = i4;
        this.mMatchConstraintPercentHeight = f2;
        if (f2 <= 0.0f || f2 >= 1.0f || i2 != 0) {
            return;
        }
        this.mMatchConstraintDefaultHeight = 2;
    }

    public void setVerticalWeight(float f2) {
        this.mWeight[1] = f2;
    }

    public void setVisibility(int i2) {
        this.mVisibility = i2;
    }

    public void setWidth(int i2) {
        this.f1954f = i2;
        int i3 = this.f1964p;
        if (i2 < i3) {
            this.f1954f = i3;
        }
    }

    public void setWidthWrapContent(boolean z) {
        this.mIsWidthWrapContent = z;
    }

    public void setWrapBehaviorInParent(int i2) {
        if (i2 < 0 || i2 > 3) {
            return;
        }
        this.mWrapBehaviorInParent = i2;
    }

    public void setX(int i2) {
        this.f1957i = i2;
    }

    public void setY(int i2) {
        this.f1958j = i2;
    }

    public void setupDimensionRatio(boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.f1949a == -1) {
            if (z3 && !z4) {
                this.f1949a = 0;
            } else if (!z3 && z4) {
                this.f1949a = 1;
                if (this.f1956h == -1) {
                    this.f1950b = 1.0f / this.f1950b;
                }
            }
        }
        if (this.f1949a == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
            this.f1949a = 1;
        } else if (this.f1949a == 1 && (!this.mLeft.isConnected() || !this.mRight.isConnected())) {
            this.f1949a = 0;
        }
        if (this.f1949a == -1 && (!this.mTop.isConnected() || !this.mBottom.isConnected() || !this.mLeft.isConnected() || !this.mRight.isConnected())) {
            if (this.mTop.isConnected() && this.mBottom.isConnected()) {
                this.f1949a = 0;
            } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
                this.f1950b = 1.0f / this.f1950b;
                this.f1949a = 1;
            }
        }
        if (this.f1949a == -1) {
            int i2 = this.mMatchConstraintMinWidth;
            if (i2 > 0 && this.mMatchConstraintMinHeight == 0) {
                this.f1949a = 0;
            } else if (i2 != 0 || this.mMatchConstraintMinHeight <= 0) {
            } else {
                this.f1950b = 1.0f / this.f1950b;
                this.f1949a = 1;
            }
        }
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (this.mType != null) {
            str = "type: " + this.mType + " ";
        } else {
            str = "";
        }
        sb.append(str);
        if (this.mDebugName != null) {
            str2 = "id: " + this.mDebugName + " ";
        }
        sb.append(str2);
        sb.append("(");
        sb.append(this.f1957i);
        sb.append(", ");
        sb.append(this.f1958j);
        sb.append(") - (");
        sb.append(this.f1954f);
        sb.append(" x ");
        sb.append(this.f1955g);
        sb.append(")");
        return sb.toString();
    }

    public void updateFromRuns(boolean z, boolean z2) {
        int i2;
        int i3;
        boolean isResolved = z & this.horizontalRun.isResolved();
        boolean isResolved2 = z2 & this.verticalRun.isResolved();
        HorizontalWidgetRun horizontalWidgetRun = this.horizontalRun;
        int i4 = horizontalWidgetRun.start.value;
        VerticalWidgetRun verticalWidgetRun = this.verticalRun;
        int i5 = verticalWidgetRun.start.value;
        int i6 = horizontalWidgetRun.end.value;
        int i7 = verticalWidgetRun.end.value;
        int i8 = i7 - i5;
        if (i6 - i4 < 0 || i8 < 0 || i4 == Integer.MIN_VALUE || i4 == Integer.MAX_VALUE || i5 == Integer.MIN_VALUE || i5 == Integer.MAX_VALUE || i6 == Integer.MIN_VALUE || i6 == Integer.MAX_VALUE || i7 == Integer.MIN_VALUE || i7 == Integer.MAX_VALUE) {
            i6 = 0;
            i4 = 0;
            i7 = 0;
            i5 = 0;
        }
        int i9 = i6 - i4;
        int i10 = i7 - i5;
        if (isResolved) {
            this.f1957i = i4;
        }
        if (isResolved2) {
            this.f1958j = i5;
        }
        if (this.mVisibility == 8) {
            this.f1954f = 0;
            this.f1955g = 0;
            return;
        }
        if (isResolved) {
            if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && i9 < (i3 = this.f1954f)) {
                i9 = i3;
            }
            this.f1954f = i9;
            int i11 = this.f1964p;
            if (i9 < i11) {
                this.f1954f = i11;
            }
        }
        if (isResolved2) {
            if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && i10 < (i2 = this.f1955g)) {
                i10 = i2;
            }
            this.f1955g = i10;
            int i12 = this.f1965q;
            if (i10 < i12) {
                this.f1955g = i12;
            }
        }
    }

    public void updateFromSolver(LinearSystem linearSystem, boolean z) {
        VerticalWidgetRun verticalWidgetRun;
        HorizontalWidgetRun horizontalWidgetRun;
        int objectVariableValue = linearSystem.getObjectVariableValue(this.mLeft);
        int objectVariableValue2 = linearSystem.getObjectVariableValue(this.mTop);
        int objectVariableValue3 = linearSystem.getObjectVariableValue(this.mRight);
        int objectVariableValue4 = linearSystem.getObjectVariableValue(this.mBottom);
        if (z && (horizontalWidgetRun = this.horizontalRun) != null) {
            DependencyNode dependencyNode = horizontalWidgetRun.start;
            if (dependencyNode.resolved) {
                DependencyNode dependencyNode2 = horizontalWidgetRun.end;
                if (dependencyNode2.resolved) {
                    objectVariableValue = dependencyNode.value;
                    objectVariableValue3 = dependencyNode2.value;
                }
            }
        }
        if (z && (verticalWidgetRun = this.verticalRun) != null) {
            DependencyNode dependencyNode3 = verticalWidgetRun.start;
            if (dependencyNode3.resolved) {
                DependencyNode dependencyNode4 = verticalWidgetRun.end;
                if (dependencyNode4.resolved) {
                    objectVariableValue2 = dependencyNode3.value;
                    objectVariableValue4 = dependencyNode4.value;
                }
            }
        }
        int i2 = objectVariableValue4 - objectVariableValue2;
        if (objectVariableValue3 - objectVariableValue < 0 || i2 < 0 || objectVariableValue == Integer.MIN_VALUE || objectVariableValue == Integer.MAX_VALUE || objectVariableValue2 == Integer.MIN_VALUE || objectVariableValue2 == Integer.MAX_VALUE || objectVariableValue3 == Integer.MIN_VALUE || objectVariableValue3 == Integer.MAX_VALUE || objectVariableValue4 == Integer.MIN_VALUE || objectVariableValue4 == Integer.MAX_VALUE) {
            objectVariableValue4 = 0;
            objectVariableValue = 0;
            objectVariableValue2 = 0;
            objectVariableValue3 = 0;
        }
        setFrame(objectVariableValue, objectVariableValue2, objectVariableValue3, objectVariableValue4);
    }
}
