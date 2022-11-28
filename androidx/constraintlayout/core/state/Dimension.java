package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
/* loaded from: classes.dex */
public class Dimension {
    private final int WRAP_CONTENT;

    /* renamed from: a  reason: collision with root package name */
    int f1894a;

    /* renamed from: b  reason: collision with root package name */
    int f1895b;

    /* renamed from: c  reason: collision with root package name */
    float f1896c;

    /* renamed from: d  reason: collision with root package name */
    int f1897d;

    /* renamed from: e  reason: collision with root package name */
    String f1898e;

    /* renamed from: f  reason: collision with root package name */
    Object f1899f;

    /* renamed from: g  reason: collision with root package name */
    boolean f1900g;
    public static final Object FIXED_DIMENSION = new Object();
    public static final Object WRAP_DIMENSION = new Object();
    public static final Object SPREAD_DIMENSION = new Object();
    public static final Object PARENT_DIMENSION = new Object();
    public static final Object PERCENT_DIMENSION = new Object();
    public static final Object RATIO_DIMENSION = new Object();

    /* loaded from: classes.dex */
    public enum Type {
        FIXED,
        WRAP,
        MATCH_PARENT,
        MATCH_CONSTRAINT
    }

    private Dimension() {
        this.WRAP_CONTENT = -2;
        this.f1894a = 0;
        this.f1895b = Integer.MAX_VALUE;
        this.f1896c = 1.0f;
        this.f1897d = 0;
        this.f1898e = null;
        this.f1899f = WRAP_DIMENSION;
        this.f1900g = false;
    }

    private Dimension(Object obj) {
        this.WRAP_CONTENT = -2;
        this.f1894a = 0;
        this.f1895b = Integer.MAX_VALUE;
        this.f1896c = 1.0f;
        this.f1897d = 0;
        this.f1898e = null;
        this.f1899f = WRAP_DIMENSION;
        this.f1900g = false;
        this.f1899f = obj;
    }

    public static Dimension Fixed(int i2) {
        Dimension dimension = new Dimension(FIXED_DIMENSION);
        dimension.fixed(i2);
        return dimension;
    }

    public static Dimension Fixed(Object obj) {
        Dimension dimension = new Dimension(FIXED_DIMENSION);
        dimension.fixed(obj);
        return dimension;
    }

    public static Dimension Parent() {
        return new Dimension(PARENT_DIMENSION);
    }

    public static Dimension Percent(Object obj, float f2) {
        Dimension dimension = new Dimension(PERCENT_DIMENSION);
        dimension.percent(obj, f2);
        return dimension;
    }

    public static Dimension Ratio(String str) {
        Dimension dimension = new Dimension(RATIO_DIMENSION);
        dimension.ratio(str);
        return dimension;
    }

    public static Dimension Spread() {
        return new Dimension(SPREAD_DIMENSION);
    }

    public static Dimension Suggested(int i2) {
        Dimension dimension = new Dimension();
        dimension.suggested(i2);
        return dimension;
    }

    public static Dimension Suggested(Object obj) {
        Dimension dimension = new Dimension();
        dimension.suggested(obj);
        return dimension;
    }

    public static Dimension Wrap() {
        return new Dimension(WRAP_DIMENSION);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        return this.f1897d;
    }

    public void apply(State state, ConstraintWidget constraintWidget, int i2) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2;
        String str = this.f1898e;
        if (str != null) {
            constraintWidget.setDimensionRatio(str);
        }
        int i3 = 2;
        if (i2 == 0) {
            if (this.f1900g) {
                constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                Object obj = this.f1899f;
                if (obj == WRAP_DIMENSION) {
                    i3 = 1;
                } else if (obj != PERCENT_DIMENSION) {
                    i3 = 0;
                }
                constraintWidget.setHorizontalMatchStyle(i3, this.f1894a, this.f1895b, this.f1896c);
                return;
            }
            int i4 = this.f1894a;
            if (i4 > 0) {
                constraintWidget.setMinWidth(i4);
            }
            int i5 = this.f1895b;
            if (i5 < Integer.MAX_VALUE) {
                constraintWidget.setMaxWidth(i5);
            }
            Object obj2 = this.f1899f;
            if (obj2 == WRAP_DIMENSION) {
                dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            } else if (obj2 != PARENT_DIMENSION) {
                if (obj2 == null) {
                    constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                    constraintWidget.setWidth(this.f1897d);
                    return;
                }
                return;
            } else {
                dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
            }
            constraintWidget.setHorizontalDimensionBehaviour(dimensionBehaviour2);
        } else if (this.f1900g) {
            constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
            Object obj3 = this.f1899f;
            if (obj3 == WRAP_DIMENSION) {
                i3 = 1;
            } else if (obj3 != PERCENT_DIMENSION) {
                i3 = 0;
            }
            constraintWidget.setVerticalMatchStyle(i3, this.f1894a, this.f1895b, this.f1896c);
        } else {
            int i6 = this.f1894a;
            if (i6 > 0) {
                constraintWidget.setMinHeight(i6);
            }
            int i7 = this.f1895b;
            if (i7 < Integer.MAX_VALUE) {
                constraintWidget.setMaxHeight(i7);
            }
            Object obj4 = this.f1899f;
            if (obj4 == WRAP_DIMENSION) {
                dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            } else if (obj4 != PARENT_DIMENSION) {
                if (obj4 == null) {
                    constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                    constraintWidget.setHeight(this.f1897d);
                    return;
                }
                return;
            } else {
                dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
            }
            constraintWidget.setVerticalDimensionBehaviour(dimensionBehaviour);
        }
    }

    public boolean equalsFixedValue(int i2) {
        return this.f1899f == null && this.f1897d == i2;
    }

    public Dimension fixed(int i2) {
        this.f1899f = null;
        this.f1897d = i2;
        return this;
    }

    public Dimension fixed(Object obj) {
        this.f1899f = obj;
        if (obj instanceof Integer) {
            this.f1897d = ((Integer) obj).intValue();
            this.f1899f = null;
        }
        return this;
    }

    public Dimension max(int i2) {
        if (this.f1895b >= 0) {
            this.f1895b = i2;
        }
        return this;
    }

    public Dimension max(Object obj) {
        Object obj2 = WRAP_DIMENSION;
        if (obj == obj2 && this.f1900g) {
            this.f1899f = obj2;
            this.f1895b = Integer.MAX_VALUE;
        }
        return this;
    }

    public Dimension min(int i2) {
        if (i2 >= 0) {
            this.f1894a = i2;
        }
        return this;
    }

    public Dimension min(Object obj) {
        if (obj == WRAP_DIMENSION) {
            this.f1894a = -2;
        }
        return this;
    }

    public Dimension percent(Object obj, float f2) {
        this.f1896c = f2;
        return this;
    }

    public Dimension ratio(String str) {
        this.f1898e = str;
        return this;
    }

    public Dimension suggested(int i2) {
        this.f1900g = true;
        return this;
    }

    public Dimension suggested(Object obj) {
        this.f1899f = obj;
        this.f1900g = true;
        return this;
    }
}
