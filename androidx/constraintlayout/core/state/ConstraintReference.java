package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.state.State;
import androidx.constraintlayout.core.state.helpers.Facade;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: classes.dex */
public class ConstraintReference implements Reference {
    Dimension U;
    Dimension V;

    /* renamed from: a  reason: collision with root package name */
    final State f1873a;
    private Object key;
    private float mCircularAngle;
    private float mCircularDistance;
    private ConstraintWidget mConstraintWidget;
    private HashMap<String, Integer> mCustomColors;
    private HashMap<String, Float> mCustomFloats;
    private Object mView;

    /* renamed from: b  reason: collision with root package name */
    String f1874b = null;

    /* renamed from: c  reason: collision with root package name */
    Facade f1875c = null;

    /* renamed from: d  reason: collision with root package name */
    int f1876d = 0;

    /* renamed from: e  reason: collision with root package name */
    int f1877e = 0;

    /* renamed from: f  reason: collision with root package name */
    float f1878f = 0.5f;

    /* renamed from: g  reason: collision with root package name */
    float f1879g = 0.5f;

    /* renamed from: h  reason: collision with root package name */
    int f1880h = 0;

    /* renamed from: i  reason: collision with root package name */
    int f1881i = 0;

    /* renamed from: j  reason: collision with root package name */
    protected int f1882j = 0;

    /* renamed from: k  reason: collision with root package name */
    protected int f1883k = 0;

    /* renamed from: l  reason: collision with root package name */
    int f1884l = 0;

    /* renamed from: m  reason: collision with root package name */
    int f1885m = 0;

    /* renamed from: n  reason: collision with root package name */
    int f1886n = 0;

    /* renamed from: o  reason: collision with root package name */
    int f1887o = 0;

    /* renamed from: p  reason: collision with root package name */
    int f1888p = 0;

    /* renamed from: q  reason: collision with root package name */
    int f1889q = 0;

    /* renamed from: r  reason: collision with root package name */
    int f1890r = 0;

    /* renamed from: s  reason: collision with root package name */
    int f1891s = 0;

    /* renamed from: t  reason: collision with root package name */
    float f1892t = Float.NaN;
    float u = Float.NaN;
    float v = Float.NaN;
    float w = Float.NaN;
    float x = Float.NaN;
    float y = Float.NaN;
    float z = Float.NaN;
    float A = Float.NaN;
    float B = Float.NaN;
    float C = Float.NaN;
    float D = Float.NaN;
    int E = 0;
    Object F = null;
    Object G = null;
    Object H = null;
    Object I = null;
    protected Object J = null;
    protected Object K = null;
    protected Object L = null;
    protected Object M = null;
    protected Object N = null;
    protected Object O = null;
    protected Object P = null;
    protected Object Q = null;
    Object R = null;
    Object S = null;
    State.Constraint T = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.constraintlayout.core.state.ConstraintReference$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1893a;

        static {
            int[] iArr = new int[State.Constraint.values().length];
            f1893a = iArr;
            try {
                iArr[State.Constraint.LEFT_TO_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1893a[State.Constraint.LEFT_TO_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1893a[State.Constraint.RIGHT_TO_LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1893a[State.Constraint.RIGHT_TO_RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1893a[State.Constraint.START_TO_START.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1893a[State.Constraint.START_TO_END.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1893a[State.Constraint.END_TO_START.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1893a[State.Constraint.END_TO_END.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1893a[State.Constraint.TOP_TO_TOP.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1893a[State.Constraint.TOP_TO_BOTTOM.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1893a[State.Constraint.BOTTOM_TO_TOP.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1893a[State.Constraint.BOTTOM_TO_BOTTOM.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f1893a[State.Constraint.BASELINE_TO_BASELINE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f1893a[State.Constraint.CIRCULAR_CONSTRAINT.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f1893a[State.Constraint.CENTER_HORIZONTALLY.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f1893a[State.Constraint.CENTER_VERTICALLY.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    /* loaded from: classes.dex */
    public interface ConstraintReferenceFactory {
        ConstraintReference create(State state);
    }

    /* loaded from: classes.dex */
    static class IncorrectConstraintException extends Exception {
        private final ArrayList<String> mErrors;

        public IncorrectConstraintException(ArrayList<String> arrayList) {
            this.mErrors = arrayList;
        }

        public ArrayList<String> getErrors() {
            return this.mErrors;
        }

        @Override // java.lang.Throwable
        public String toString() {
            return "IncorrectConstraintException: " + this.mErrors.toString();
        }
    }

    public ConstraintReference(State state) {
        Object obj = Dimension.WRAP_DIMENSION;
        this.U = Dimension.Fixed(obj);
        this.V = Dimension.Fixed(obj);
        this.mCustomColors = new HashMap<>();
        this.mCustomFloats = new HashMap<>();
        this.f1873a = state;
    }

    private void applyConnection(ConstraintWidget constraintWidget, Object obj, State.Constraint constraint) {
        ConstraintAnchor.Type type;
        ConstraintAnchor anchor;
        ConstraintAnchor anchor2;
        int i2;
        int i3;
        ConstraintAnchor.Type type2;
        ConstraintAnchor.Type type3;
        ConstraintAnchor.Type type4;
        ConstraintAnchor.Type type5;
        ConstraintAnchor.Type type6;
        ConstraintWidget target = getTarget(obj);
        if (target == null) {
            return;
        }
        int[] iArr = AnonymousClass1.f1893a;
        int i4 = iArr[constraint.ordinal()];
        switch (iArr[constraint.ordinal()]) {
            case 1:
                type = ConstraintAnchor.Type.LEFT;
                anchor = constraintWidget.getAnchor(type);
                anchor2 = target.getAnchor(type);
                i2 = this.f1880h;
                i3 = this.f1886n;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 2:
                anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT);
                type = ConstraintAnchor.Type.RIGHT;
                anchor2 = target.getAnchor(type);
                i2 = this.f1880h;
                i3 = this.f1886n;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 3:
                anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT);
                type2 = ConstraintAnchor.Type.LEFT;
                anchor2 = target.getAnchor(type2);
                i2 = this.f1881i;
                i3 = this.f1887o;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 4:
                type2 = ConstraintAnchor.Type.RIGHT;
                anchor = constraintWidget.getAnchor(type2);
                anchor2 = target.getAnchor(type2);
                i2 = this.f1881i;
                i3 = this.f1887o;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 5:
                type3 = ConstraintAnchor.Type.LEFT;
                anchor = constraintWidget.getAnchor(type3);
                anchor2 = target.getAnchor(type3);
                i2 = this.f1882j;
                i3 = this.f1888p;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 6:
                anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT);
                type3 = ConstraintAnchor.Type.RIGHT;
                anchor2 = target.getAnchor(type3);
                i2 = this.f1882j;
                i3 = this.f1888p;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 7:
                anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT);
                type4 = ConstraintAnchor.Type.LEFT;
                anchor2 = target.getAnchor(type4);
                i2 = this.f1883k;
                i3 = this.f1889q;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 8:
                type4 = ConstraintAnchor.Type.RIGHT;
                anchor = constraintWidget.getAnchor(type4);
                anchor2 = target.getAnchor(type4);
                i2 = this.f1883k;
                i3 = this.f1889q;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 9:
                type5 = ConstraintAnchor.Type.TOP;
                anchor = constraintWidget.getAnchor(type5);
                anchor2 = target.getAnchor(type5);
                i2 = this.f1884l;
                i3 = this.f1890r;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 10:
                anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.TOP);
                type5 = ConstraintAnchor.Type.BOTTOM;
                anchor2 = target.getAnchor(type5);
                i2 = this.f1884l;
                i3 = this.f1890r;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 11:
                anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM);
                type6 = ConstraintAnchor.Type.TOP;
                anchor2 = target.getAnchor(type6);
                i2 = this.f1885m;
                i3 = this.f1891s;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 12:
                type6 = ConstraintAnchor.Type.BOTTOM;
                anchor = constraintWidget.getAnchor(type6);
                anchor2 = target.getAnchor(type6);
                i2 = this.f1885m;
                i3 = this.f1891s;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 13:
                ConstraintAnchor.Type type7 = ConstraintAnchor.Type.BASELINE;
                constraintWidget.immediateConnect(type7, target, type7, 0, 0);
                return;
            case 14:
                constraintWidget.connectCircularConstraint(target, this.mCircularAngle, (int) this.mCircularDistance);
                return;
            default:
                return;
        }
    }

    private void dereference() {
        this.F = get(this.F);
        this.G = get(this.G);
        this.H = get(this.H);
        this.I = get(this.I);
        this.J = get(this.J);
        this.K = get(this.K);
        this.L = get(this.L);
        this.M = get(this.M);
        this.N = get(this.N);
        this.O = get(this.O);
        this.P = get(this.P);
        this.Q = get(this.Q);
        this.R = get(this.R);
    }

    private Object get(Object obj) {
        if (obj == null) {
            return null;
        }
        return !(obj instanceof ConstraintReference) ? this.f1873a.a(obj) : obj;
    }

    private ConstraintWidget getTarget(Object obj) {
        if (obj instanceof Reference) {
            return ((Reference) obj).getConstraintWidget();
        }
        return null;
    }

    public void addCustomColor(String str, int i2) {
        this.mCustomColors.put(str, Integer.valueOf(i2));
    }

    public void addCustomFloat(String str, float f2) {
        if (this.mCustomFloats == null) {
            this.mCustomFloats = new HashMap<>();
        }
        this.mCustomFloats.put(str, Float.valueOf(f2));
    }

    public ConstraintReference alpha(float f2) {
        this.B = f2;
        return this;
    }

    @Override // androidx.constraintlayout.core.state.Reference
    public void apply() {
        if (this.mConstraintWidget == null) {
            return;
        }
        Facade facade = this.f1875c;
        if (facade != null) {
            facade.apply();
        }
        this.U.apply(this.f1873a, this.mConstraintWidget, 0);
        this.V.apply(this.f1873a, this.mConstraintWidget, 1);
        dereference();
        applyConnection(this.mConstraintWidget, this.F, State.Constraint.LEFT_TO_LEFT);
        applyConnection(this.mConstraintWidget, this.G, State.Constraint.LEFT_TO_RIGHT);
        applyConnection(this.mConstraintWidget, this.H, State.Constraint.RIGHT_TO_LEFT);
        applyConnection(this.mConstraintWidget, this.I, State.Constraint.RIGHT_TO_RIGHT);
        applyConnection(this.mConstraintWidget, this.J, State.Constraint.START_TO_START);
        applyConnection(this.mConstraintWidget, this.K, State.Constraint.START_TO_END);
        applyConnection(this.mConstraintWidget, this.L, State.Constraint.END_TO_START);
        applyConnection(this.mConstraintWidget, this.M, State.Constraint.END_TO_END);
        applyConnection(this.mConstraintWidget, this.N, State.Constraint.TOP_TO_TOP);
        applyConnection(this.mConstraintWidget, this.O, State.Constraint.TOP_TO_BOTTOM);
        applyConnection(this.mConstraintWidget, this.P, State.Constraint.BOTTOM_TO_TOP);
        applyConnection(this.mConstraintWidget, this.Q, State.Constraint.BOTTOM_TO_BOTTOM);
        applyConnection(this.mConstraintWidget, this.R, State.Constraint.BASELINE_TO_BASELINE);
        applyConnection(this.mConstraintWidget, this.S, State.Constraint.CIRCULAR_CONSTRAINT);
        int i2 = this.f1876d;
        if (i2 != 0) {
            this.mConstraintWidget.setHorizontalChainStyle(i2);
        }
        int i3 = this.f1877e;
        if (i3 != 0) {
            this.mConstraintWidget.setVerticalChainStyle(i3);
        }
        this.mConstraintWidget.setHorizontalBiasPercent(this.f1878f);
        this.mConstraintWidget.setVerticalBiasPercent(this.f1879g);
        ConstraintWidget constraintWidget = this.mConstraintWidget;
        WidgetFrame widgetFrame = constraintWidget.frame;
        widgetFrame.pivotX = this.f1892t;
        widgetFrame.pivotY = this.u;
        widgetFrame.rotationX = this.v;
        widgetFrame.rotationY = this.w;
        widgetFrame.rotationZ = this.x;
        widgetFrame.translationX = this.y;
        widgetFrame.translationY = this.z;
        widgetFrame.translationZ = this.A;
        widgetFrame.scaleX = this.C;
        widgetFrame.scaleY = this.D;
        widgetFrame.alpha = this.B;
        int i4 = this.E;
        widgetFrame.visibility = i4;
        constraintWidget.setVisibility(i4);
        HashMap<String, Integer> hashMap = this.mCustomColors;
        if (hashMap != null) {
            for (String str : hashMap.keySet()) {
                this.mConstraintWidget.frame.setCustomAttribute(str, TypedValues.Custom.TYPE_COLOR, this.mCustomColors.get(str).intValue());
            }
        }
        HashMap<String, Float> hashMap2 = this.mCustomFloats;
        if (hashMap2 != null) {
            for (String str2 : hashMap2.keySet()) {
                this.mConstraintWidget.frame.setCustomAttribute(str2, TypedValues.Custom.TYPE_FLOAT, this.mCustomFloats.get(str2).floatValue());
            }
        }
    }

    public ConstraintReference baseline() {
        this.T = State.Constraint.BASELINE_TO_BASELINE;
        return this;
    }

    public ConstraintReference baselineToBaseline(Object obj) {
        this.T = State.Constraint.BASELINE_TO_BASELINE;
        this.R = obj;
        return this;
    }

    public ConstraintReference bias(float f2) {
        State.Constraint constraint = this.T;
        if (constraint == null) {
            return this;
        }
        switch (AnonymousClass1.f1893a[constraint.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 15:
                this.f1878f = f2;
                break;
            case 9:
            case 10:
            case 11:
            case 12:
            case 16:
                this.f1879g = f2;
                break;
        }
        return this;
    }

    public ConstraintReference bottom() {
        this.T = this.P != null ? State.Constraint.BOTTOM_TO_TOP : State.Constraint.BOTTOM_TO_BOTTOM;
        return this;
    }

    public ConstraintReference bottomToBottom(Object obj) {
        this.T = State.Constraint.BOTTOM_TO_BOTTOM;
        this.Q = obj;
        return this;
    }

    public ConstraintReference bottomToTop(Object obj) {
        this.T = State.Constraint.BOTTOM_TO_TOP;
        this.P = obj;
        return this;
    }

    public ConstraintReference centerHorizontally(Object obj) {
        Object obj2 = get(obj);
        this.J = obj2;
        this.M = obj2;
        this.T = State.Constraint.CENTER_HORIZONTALLY;
        this.f1878f = 0.5f;
        return this;
    }

    public ConstraintReference centerVertically(Object obj) {
        Object obj2 = get(obj);
        this.N = obj2;
        this.Q = obj2;
        this.T = State.Constraint.CENTER_VERTICALLY;
        this.f1879g = 0.5f;
        return this;
    }

    public ConstraintReference circularConstraint(Object obj, float f2, float f3) {
        this.S = get(obj);
        this.mCircularAngle = f2;
        this.mCircularDistance = f3;
        this.T = State.Constraint.CIRCULAR_CONSTRAINT;
        return this;
    }

    public ConstraintReference clear() {
        State.Constraint constraint = this.T;
        if (constraint != null) {
            switch (AnonymousClass1.f1893a[constraint.ordinal()]) {
                case 1:
                case 2:
                    this.F = null;
                    this.G = null;
                    this.f1880h = 0;
                    this.f1886n = 0;
                    break;
                case 3:
                case 4:
                    this.H = null;
                    this.I = null;
                    this.f1881i = 0;
                    this.f1887o = 0;
                    break;
                case 5:
                case 6:
                    this.J = null;
                    this.K = null;
                    this.f1882j = 0;
                    this.f1888p = 0;
                    break;
                case 7:
                case 8:
                    this.L = null;
                    this.M = null;
                    this.f1883k = 0;
                    this.f1889q = 0;
                    break;
                case 9:
                case 10:
                    this.N = null;
                    this.O = null;
                    this.f1884l = 0;
                    this.f1890r = 0;
                    break;
                case 11:
                case 12:
                    this.P = null;
                    this.Q = null;
                    this.f1885m = 0;
                    break;
                case 13:
                    this.R = null;
                    break;
                case 14:
                    this.S = null;
                    break;
            }
            return this;
        }
        this.F = null;
        this.G = null;
        this.f1880h = 0;
        this.H = null;
        this.I = null;
        this.f1881i = 0;
        this.J = null;
        this.K = null;
        this.f1882j = 0;
        this.L = null;
        this.M = null;
        this.f1883k = 0;
        this.N = null;
        this.O = null;
        this.f1884l = 0;
        this.P = null;
        this.Q = null;
        this.f1885m = 0;
        this.R = null;
        this.S = null;
        this.f1878f = 0.5f;
        this.f1879g = 0.5f;
        this.f1886n = 0;
        this.f1887o = 0;
        this.f1888p = 0;
        this.f1889q = 0;
        this.f1890r = 0;
        this.f1891s = 0;
        return this;
    }

    public ConstraintReference clearHorizontal() {
        start().clear();
        end().clear();
        left().clear();
        right().clear();
        return this;
    }

    public ConstraintReference clearVertical() {
        top().clear();
        baseline().clear();
        bottom().clear();
        return this;
    }

    public ConstraintWidget createConstraintWidget() {
        return new ConstraintWidget(getWidth().a(), getHeight().a());
    }

    public ConstraintReference end() {
        this.T = this.L != null ? State.Constraint.END_TO_START : State.Constraint.END_TO_END;
        return this;
    }

    public ConstraintReference endToEnd(Object obj) {
        this.T = State.Constraint.END_TO_END;
        this.M = obj;
        return this;
    }

    public ConstraintReference endToStart(Object obj) {
        this.T = State.Constraint.END_TO_START;
        this.L = obj;
        return this;
    }

    public float getAlpha() {
        return this.B;
    }

    @Override // androidx.constraintlayout.core.state.Reference
    public ConstraintWidget getConstraintWidget() {
        if (this.mConstraintWidget == null) {
            ConstraintWidget createConstraintWidget = createConstraintWidget();
            this.mConstraintWidget = createConstraintWidget;
            createConstraintWidget.setCompanionWidget(this.mView);
        }
        return this.mConstraintWidget;
    }

    @Override // androidx.constraintlayout.core.state.Reference
    public Facade getFacade() {
        return this.f1875c;
    }

    public Dimension getHeight() {
        return this.V;
    }

    public int getHorizontalChainStyle() {
        return this.f1876d;
    }

    @Override // androidx.constraintlayout.core.state.Reference
    public Object getKey() {
        return this.key;
    }

    public float getPivotX() {
        return this.f1892t;
    }

    public float getPivotY() {
        return this.u;
    }

    public float getRotationX() {
        return this.v;
    }

    public float getRotationY() {
        return this.w;
    }

    public float getRotationZ() {
        return this.x;
    }

    public float getScaleX() {
        return this.C;
    }

    public float getScaleY() {
        return this.D;
    }

    public String getTag() {
        return this.f1874b;
    }

    public float getTranslationX() {
        return this.y;
    }

    public float getTranslationY() {
        return this.z;
    }

    public float getTranslationZ() {
        return this.A;
    }

    public int getVerticalChainStyle(int i2) {
        return this.f1877e;
    }

    public Object getView() {
        return this.mView;
    }

    public Dimension getWidth() {
        return this.U;
    }

    public ConstraintReference height(Dimension dimension) {
        return setHeight(dimension);
    }

    public ConstraintReference horizontalBias(float f2) {
        this.f1878f = f2;
        return this;
    }

    public ConstraintReference left() {
        this.T = this.F != null ? State.Constraint.LEFT_TO_LEFT : State.Constraint.LEFT_TO_RIGHT;
        return this;
    }

    public ConstraintReference leftToLeft(Object obj) {
        this.T = State.Constraint.LEFT_TO_LEFT;
        this.F = obj;
        return this;
    }

    public ConstraintReference leftToRight(Object obj) {
        this.T = State.Constraint.LEFT_TO_RIGHT;
        this.G = obj;
        return this;
    }

    public ConstraintReference margin(int i2) {
        State.Constraint constraint = this.T;
        if (constraint != null) {
            switch (AnonymousClass1.f1893a[constraint.ordinal()]) {
                case 1:
                case 2:
                    this.f1880h = i2;
                    break;
                case 3:
                case 4:
                    this.f1881i = i2;
                    break;
                case 5:
                case 6:
                    this.f1882j = i2;
                    break;
                case 7:
                case 8:
                    this.f1883k = i2;
                    break;
                case 9:
                case 10:
                    this.f1884l = i2;
                    break;
                case 14:
                    this.mCircularDistance = i2;
                    break;
            }
            return this;
        }
        this.f1880h = i2;
        this.f1881i = i2;
        this.f1882j = i2;
        this.f1883k = i2;
        this.f1884l = i2;
        this.f1885m = i2;
        return this;
    }

    public ConstraintReference margin(Object obj) {
        return margin(this.f1873a.convertDimension(obj));
    }

    public ConstraintReference marginGone(int i2) {
        State.Constraint constraint = this.T;
        if (constraint != null) {
            switch (AnonymousClass1.f1893a[constraint.ordinal()]) {
                case 1:
                case 2:
                    this.f1886n = i2;
                    break;
                case 3:
                case 4:
                    this.f1887o = i2;
                    break;
                case 5:
                case 6:
                    this.f1888p = i2;
                    break;
                case 7:
                case 8:
                    this.f1889q = i2;
                    break;
                case 9:
                case 10:
                    this.f1890r = i2;
                    break;
            }
            return this;
        }
        this.f1886n = i2;
        this.f1887o = i2;
        this.f1888p = i2;
        this.f1889q = i2;
        this.f1890r = i2;
        this.f1891s = i2;
        return this;
    }

    public ConstraintReference pivotX(float f2) {
        this.f1892t = f2;
        return this;
    }

    public ConstraintReference pivotY(float f2) {
        this.u = f2;
        return this;
    }

    public ConstraintReference right() {
        this.T = this.H != null ? State.Constraint.RIGHT_TO_LEFT : State.Constraint.RIGHT_TO_RIGHT;
        return this;
    }

    public ConstraintReference rightToLeft(Object obj) {
        this.T = State.Constraint.RIGHT_TO_LEFT;
        this.H = obj;
        return this;
    }

    public ConstraintReference rightToRight(Object obj) {
        this.T = State.Constraint.RIGHT_TO_RIGHT;
        this.I = obj;
        return this;
    }

    public ConstraintReference rotationX(float f2) {
        this.v = f2;
        return this;
    }

    public ConstraintReference rotationY(float f2) {
        this.w = f2;
        return this;
    }

    public ConstraintReference rotationZ(float f2) {
        this.x = f2;
        return this;
    }

    public ConstraintReference scaleX(float f2) {
        this.C = f2;
        return this;
    }

    public ConstraintReference scaleY(float f2) {
        this.D = f2;
        return this;
    }

    @Override // androidx.constraintlayout.core.state.Reference
    public void setConstraintWidget(ConstraintWidget constraintWidget) {
        if (constraintWidget == null) {
            return;
        }
        this.mConstraintWidget = constraintWidget;
        constraintWidget.setCompanionWidget(this.mView);
    }

    public void setFacade(Facade facade) {
        this.f1875c = facade;
        if (facade != null) {
            setConstraintWidget(facade.getConstraintWidget());
        }
    }

    public ConstraintReference setHeight(Dimension dimension) {
        this.V = dimension;
        return this;
    }

    public void setHorizontalChainStyle(int i2) {
        this.f1876d = i2;
    }

    @Override // androidx.constraintlayout.core.state.Reference
    public void setKey(Object obj) {
        this.key = obj;
    }

    public void setTag(String str) {
        this.f1874b = str;
    }

    public void setVerticalChainStyle(int i2) {
        this.f1877e = i2;
    }

    public void setView(Object obj) {
        this.mView = obj;
        ConstraintWidget constraintWidget = this.mConstraintWidget;
        if (constraintWidget != null) {
            constraintWidget.setCompanionWidget(obj);
        }
    }

    public ConstraintReference setWidth(Dimension dimension) {
        this.U = dimension;
        return this;
    }

    public ConstraintReference start() {
        this.T = this.J != null ? State.Constraint.START_TO_START : State.Constraint.START_TO_END;
        return this;
    }

    public ConstraintReference startToEnd(Object obj) {
        this.T = State.Constraint.START_TO_END;
        this.K = obj;
        return this;
    }

    public ConstraintReference startToStart(Object obj) {
        this.T = State.Constraint.START_TO_START;
        this.J = obj;
        return this;
    }

    public ConstraintReference top() {
        this.T = this.N != null ? State.Constraint.TOP_TO_TOP : State.Constraint.TOP_TO_BOTTOM;
        return this;
    }

    public ConstraintReference topToBottom(Object obj) {
        this.T = State.Constraint.TOP_TO_BOTTOM;
        this.O = obj;
        return this;
    }

    public ConstraintReference topToTop(Object obj) {
        this.T = State.Constraint.TOP_TO_TOP;
        this.N = obj;
        return this;
    }

    public ConstraintReference translationX(float f2) {
        this.y = f2;
        return this;
    }

    public ConstraintReference translationY(float f2) {
        this.z = f2;
        return this;
    }

    public ConstraintReference translationZ(float f2) {
        this.A = f2;
        return this;
    }

    public void validate() {
        ArrayList arrayList = new ArrayList();
        if (this.F != null && this.G != null) {
            arrayList.add("LeftToLeft and LeftToRight both defined");
        }
        if (this.H != null && this.I != null) {
            arrayList.add("RightToLeft and RightToRight both defined");
        }
        if (this.J != null && this.K != null) {
            arrayList.add("StartToStart and StartToEnd both defined");
        }
        if (this.L != null && this.M != null) {
            arrayList.add("EndToStart and EndToEnd both defined");
        }
        if ((this.F != null || this.G != null || this.H != null || this.I != null) && (this.J != null || this.K != null || this.L != null || this.M != null)) {
            arrayList.add("Both left/right and start/end constraints defined");
        }
        if (arrayList.size() > 0) {
            throw new IncorrectConstraintException(arrayList);
        }
    }

    public ConstraintReference verticalBias(float f2) {
        this.f1879g = f2;
        return this;
    }

    public ConstraintReference visibility(int i2) {
        this.E = i2;
        return this;
    }

    public ConstraintReference width(Dimension dimension) {
        return setWidth(dimension);
    }
}
