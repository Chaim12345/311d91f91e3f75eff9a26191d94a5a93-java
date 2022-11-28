package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;
/* loaded from: classes.dex */
public class ArcCurveFit extends CurveFit {
    public static final int ARC_START_FLIP = 3;
    public static final int ARC_START_HORIZONTAL = 2;
    public static final int ARC_START_LINEAR = 0;
    public static final int ARC_START_VERTICAL = 1;
    private static final int START_HORIZONTAL = 2;
    private static final int START_LINEAR = 3;
    private static final int START_VERTICAL = 1;

    /* renamed from: a  reason: collision with root package name */
    Arc[] f1730a;
    private boolean mExtrapolate = true;
    private final double[] mTime;

    /* loaded from: classes.dex */
    private static class Arc {
        private static final double EPSILON = 0.001d;
        private static final String TAG = "Arc";
        private static double[] ourPercent = new double[91];

        /* renamed from: a  reason: collision with root package name */
        double[] f1731a;

        /* renamed from: b  reason: collision with root package name */
        double f1732b;

        /* renamed from: c  reason: collision with root package name */
        double f1733c;

        /* renamed from: d  reason: collision with root package name */
        double f1734d;

        /* renamed from: e  reason: collision with root package name */
        double f1735e;

        /* renamed from: f  reason: collision with root package name */
        double f1736f;

        /* renamed from: g  reason: collision with root package name */
        double f1737g;

        /* renamed from: h  reason: collision with root package name */
        double f1738h;

        /* renamed from: i  reason: collision with root package name */
        double f1739i;

        /* renamed from: j  reason: collision with root package name */
        double f1740j;

        /* renamed from: k  reason: collision with root package name */
        double f1741k;

        /* renamed from: l  reason: collision with root package name */
        double f1742l;

        /* renamed from: m  reason: collision with root package name */
        double f1743m;

        /* renamed from: n  reason: collision with root package name */
        double f1744n;

        /* renamed from: o  reason: collision with root package name */
        double f1745o;

        /* renamed from: p  reason: collision with root package name */
        double f1746p;

        /* renamed from: q  reason: collision with root package name */
        boolean f1747q;

        /* renamed from: r  reason: collision with root package name */
        boolean f1748r;

        Arc(int i2, double d2, double d3, double d4, double d5, double d6, double d7) {
            this.f1748r = false;
            this.f1747q = i2 == 1;
            this.f1733c = d2;
            this.f1734d = d3;
            this.f1739i = 1.0d / (d3 - d2);
            if (3 == i2) {
                this.f1748r = true;
            }
            double d8 = d6 - d4;
            double d9 = d7 - d5;
            if (!this.f1748r && Math.abs(d8) >= EPSILON && Math.abs(d9) >= EPSILON) {
                this.f1731a = new double[101];
                boolean z = this.f1747q;
                this.f1740j = d8 * (z ? -1 : 1);
                this.f1741k = d9 * (z ? 1 : -1);
                this.f1742l = z ? d6 : d4;
                this.f1743m = z ? d5 : d7;
                buildTable(d4, d5, d6, d7);
                this.f1744n = this.f1732b * this.f1739i;
                return;
            }
            this.f1748r = true;
            this.f1735e = d4;
            this.f1736f = d6;
            this.f1737g = d5;
            this.f1738h = d7;
            double hypot = Math.hypot(d9, d8);
            this.f1732b = hypot;
            this.f1744n = hypot * this.f1739i;
            double d10 = this.f1734d;
            double d11 = this.f1733c;
            this.f1742l = d8 / (d10 - d11);
            this.f1743m = d9 / (d10 - d11);
        }

        private void buildTable(double d2, double d3, double d4, double d5) {
            double[] dArr;
            double[] dArr2;
            double d6;
            double d7 = d4 - d2;
            double d8 = d3 - d5;
            int i2 = 0;
            double d9 = 0.0d;
            double d10 = 0.0d;
            double d11 = 0.0d;
            while (true) {
                if (i2 >= ourPercent.length) {
                    break;
                }
                double d12 = d9;
                double radians = Math.toRadians((i2 * 90.0d) / (dArr.length - 1));
                double sin = Math.sin(radians) * d7;
                double cos = Math.cos(radians) * d8;
                if (i2 > 0) {
                    d6 = Math.hypot(sin - d10, cos - d11) + d12;
                    ourPercent[i2] = d6;
                } else {
                    d6 = d12;
                }
                i2++;
                d11 = cos;
                d9 = d6;
                d10 = sin;
            }
            double d13 = d9;
            this.f1732b = d13;
            int i3 = 0;
            while (true) {
                double[] dArr3 = ourPercent;
                if (i3 >= dArr3.length) {
                    break;
                }
                dArr3[i3] = dArr3[i3] / d13;
                i3++;
            }
            int i4 = 0;
            while (true) {
                if (i4 >= this.f1731a.length) {
                    return;
                }
                double length = i4 / (dArr2.length - 1);
                int binarySearch = Arrays.binarySearch(ourPercent, length);
                if (binarySearch >= 0) {
                    this.f1731a[i4] = binarySearch / (ourPercent.length - 1);
                } else if (binarySearch == -1) {
                    this.f1731a[i4] = 0.0d;
                } else {
                    int i5 = -binarySearch;
                    int i6 = i5 - 2;
                    double[] dArr4 = ourPercent;
                    this.f1731a[i4] = (i6 + ((length - dArr4[i6]) / (dArr4[i5 - 1] - dArr4[i6]))) / (dArr4.length - 1);
                }
                i4++;
            }
        }

        double a() {
            double d2 = this.f1740j * this.f1746p;
            double hypot = this.f1744n / Math.hypot(d2, (-this.f1741k) * this.f1745o);
            if (this.f1747q) {
                d2 = -d2;
            }
            return d2 * hypot;
        }

        double b() {
            double d2 = this.f1740j * this.f1746p;
            double d3 = (-this.f1741k) * this.f1745o;
            double hypot = this.f1744n / Math.hypot(d2, d3);
            return this.f1747q ? (-d3) * hypot : d3 * hypot;
        }

        double c() {
            return this.f1742l + (this.f1740j * this.f1745o);
        }

        double d() {
            return this.f1743m + (this.f1741k * this.f1746p);
        }

        double e(double d2) {
            if (d2 <= 0.0d) {
                return 0.0d;
            }
            if (d2 >= 1.0d) {
                return 1.0d;
            }
            double[] dArr = this.f1731a;
            double length = d2 * (dArr.length - 1);
            int i2 = (int) length;
            return dArr[i2] + ((length - i2) * (dArr[i2 + 1] - dArr[i2]));
        }

        void f(double d2) {
            double e2 = e((this.f1747q ? this.f1734d - d2 : d2 - this.f1733c) * this.f1739i) * 1.5707963267948966d;
            this.f1745o = Math.sin(e2);
            this.f1746p = Math.cos(e2);
        }

        public double getLinearDX(double d2) {
            return this.f1742l;
        }

        public double getLinearDY(double d2) {
            return this.f1743m;
        }

        public double getLinearX(double d2) {
            double d3 = (d2 - this.f1733c) * this.f1739i;
            double d4 = this.f1735e;
            return d4 + (d3 * (this.f1736f - d4));
        }

        public double getLinearY(double d2) {
            double d3 = (d2 - this.f1733c) * this.f1739i;
            double d4 = this.f1737g;
            return d4 + (d3 * (this.f1738h - d4));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0028, code lost:
        if (r5 == 1) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ArcCurveFit(int[] iArr, double[] dArr, double[][] dArr2) {
        this.mTime = dArr;
        this.f1730a = new Arc[dArr.length - 1];
        int i2 = 1;
        int i3 = 1;
        int i4 = 0;
        while (true) {
            Arc[] arcArr = this.f1730a;
            if (i4 >= arcArr.length) {
                return;
            }
            int i5 = iArr[i4];
            if (i5 != 0) {
                if (i5 != 1) {
                    if (i5 != 2) {
                        if (i5 == 3) {
                        }
                    }
                    i2 = 2;
                    i3 = i2;
                }
                i2 = 1;
                i3 = i2;
            } else {
                i3 = 3;
            }
            int i6 = i4 + 1;
            arcArr[i4] = new Arc(i3, dArr[i4], dArr[i6], dArr2[i4][0], dArr2[i4][1], dArr2[i6][0], dArr2[i6][1]);
            i4 = i6;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getPos(double d2, int i2) {
        double d3;
        double linearY;
        double linearDY;
        double d4;
        double b2;
        int i3 = 0;
        if (this.mExtrapolate) {
            Arc[] arcArr = this.f1730a;
            if (d2 < arcArr[0].f1733c) {
                double d5 = arcArr[0].f1733c;
                d3 = d2 - arcArr[0].f1733c;
                if (!arcArr[0].f1748r) {
                    arcArr[0].f(d5);
                    if (i2 == 0) {
                        d4 = this.f1730a[0].c();
                        b2 = this.f1730a[0].a();
                    } else {
                        d4 = this.f1730a[0].d();
                        b2 = this.f1730a[0].b();
                    }
                    return d4 + (d3 * b2);
                } else if (i2 == 0) {
                    linearY = arcArr[0].getLinearX(d5);
                    linearDY = this.f1730a[0].getLinearDX(d5);
                } else {
                    linearY = arcArr[0].getLinearY(d5);
                    linearDY = this.f1730a[0].getLinearDY(d5);
                }
            } else if (d2 > arcArr[arcArr.length - 1].f1734d) {
                double d6 = arcArr[arcArr.length - 1].f1734d;
                d3 = d2 - d6;
                int length = arcArr.length - 1;
                if (i2 == 0) {
                    linearY = arcArr[length].getLinearX(d6);
                    linearDY = this.f1730a[length].getLinearDX(d6);
                } else {
                    linearY = arcArr[length].getLinearY(d6);
                    linearDY = this.f1730a[length].getLinearDY(d6);
                }
            }
            return linearY + (d3 * linearDY);
        }
        Arc[] arcArr2 = this.f1730a;
        if (d2 < arcArr2[0].f1733c) {
            d2 = arcArr2[0].f1733c;
        } else if (d2 > arcArr2[arcArr2.length - 1].f1734d) {
            d2 = arcArr2[arcArr2.length - 1].f1734d;
        }
        while (true) {
            Arc[] arcArr3 = this.f1730a;
            if (i3 >= arcArr3.length) {
                return Double.NaN;
            }
            if (d2 <= arcArr3[i3].f1734d) {
                if (arcArr3[i3].f1748r) {
                    return i2 == 0 ? arcArr3[i3].getLinearX(d2) : arcArr3[i3].getLinearY(d2);
                }
                arcArr3[i3].f(d2);
                Arc[] arcArr4 = this.f1730a;
                return i2 == 0 ? arcArr4[i3].c() : arcArr4[i3].d();
            }
            i3++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getPos(double d2, double[] dArr) {
        if (this.mExtrapolate) {
            Arc[] arcArr = this.f1730a;
            if (d2 < arcArr[0].f1733c) {
                double d3 = arcArr[0].f1733c;
                double d4 = d2 - arcArr[0].f1733c;
                if (arcArr[0].f1748r) {
                    dArr[0] = arcArr[0].getLinearX(d3) + (this.f1730a[0].getLinearDX(d3) * d4);
                    dArr[1] = this.f1730a[0].getLinearY(d3) + (d4 * this.f1730a[0].getLinearDY(d3));
                    return;
                }
                arcArr[0].f(d3);
                dArr[0] = this.f1730a[0].c() + (this.f1730a[0].a() * d4);
                dArr[1] = this.f1730a[0].d() + (d4 * this.f1730a[0].b());
                return;
            } else if (d2 > arcArr[arcArr.length - 1].f1734d) {
                double d5 = arcArr[arcArr.length - 1].f1734d;
                double d6 = d2 - d5;
                int length = arcArr.length - 1;
                if (arcArr[length].f1748r) {
                    dArr[0] = arcArr[length].getLinearX(d5) + (this.f1730a[length].getLinearDX(d5) * d6);
                    dArr[1] = this.f1730a[length].getLinearY(d5) + (d6 * this.f1730a[length].getLinearDY(d5));
                    return;
                }
                arcArr[length].f(d2);
                dArr[0] = this.f1730a[length].c() + (this.f1730a[length].a() * d6);
                dArr[1] = this.f1730a[length].d() + (d6 * this.f1730a[length].b());
                return;
            }
        } else {
            Arc[] arcArr2 = this.f1730a;
            if (d2 < arcArr2[0].f1733c) {
                d2 = arcArr2[0].f1733c;
            }
            if (d2 > arcArr2[arcArr2.length - 1].f1734d) {
                d2 = arcArr2[arcArr2.length - 1].f1734d;
            }
        }
        int i2 = 0;
        while (true) {
            Arc[] arcArr3 = this.f1730a;
            if (i2 >= arcArr3.length) {
                return;
            }
            if (d2 <= arcArr3[i2].f1734d) {
                if (arcArr3[i2].f1748r) {
                    dArr[0] = arcArr3[i2].getLinearX(d2);
                    dArr[1] = this.f1730a[i2].getLinearY(d2);
                    return;
                }
                arcArr3[i2].f(d2);
                dArr[0] = this.f1730a[i2].c();
                dArr[1] = this.f1730a[i2].d();
                return;
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getPos(double d2, float[] fArr) {
        if (this.mExtrapolate) {
            Arc[] arcArr = this.f1730a;
            if (d2 < arcArr[0].f1733c) {
                double d3 = arcArr[0].f1733c;
                double d4 = d2 - arcArr[0].f1733c;
                if (arcArr[0].f1748r) {
                    fArr[0] = (float) (arcArr[0].getLinearX(d3) + (this.f1730a[0].getLinearDX(d3) * d4));
                    fArr[1] = (float) (this.f1730a[0].getLinearY(d3) + (d4 * this.f1730a[0].getLinearDY(d3)));
                    return;
                }
                arcArr[0].f(d3);
                fArr[0] = (float) (this.f1730a[0].c() + (this.f1730a[0].a() * d4));
                fArr[1] = (float) (this.f1730a[0].d() + (d4 * this.f1730a[0].b()));
                return;
            } else if (d2 > arcArr[arcArr.length - 1].f1734d) {
                double d5 = arcArr[arcArr.length - 1].f1734d;
                double d6 = d2 - d5;
                int length = arcArr.length - 1;
                if (arcArr[length].f1748r) {
                    fArr[0] = (float) (arcArr[length].getLinearX(d5) + (this.f1730a[length].getLinearDX(d5) * d6));
                    fArr[1] = (float) (this.f1730a[length].getLinearY(d5) + (d6 * this.f1730a[length].getLinearDY(d5)));
                    return;
                }
                arcArr[length].f(d2);
                fArr[0] = (float) this.f1730a[length].c();
                fArr[1] = (float) this.f1730a[length].d();
                return;
            }
        } else {
            Arc[] arcArr2 = this.f1730a;
            if (d2 < arcArr2[0].f1733c) {
                d2 = arcArr2[0].f1733c;
            } else if (d2 > arcArr2[arcArr2.length - 1].f1734d) {
                d2 = arcArr2[arcArr2.length - 1].f1734d;
            }
        }
        int i2 = 0;
        while (true) {
            Arc[] arcArr3 = this.f1730a;
            if (i2 >= arcArr3.length) {
                return;
            }
            if (d2 <= arcArr3[i2].f1734d) {
                if (arcArr3[i2].f1748r) {
                    fArr[0] = (float) arcArr3[i2].getLinearX(d2);
                    fArr[1] = (float) this.f1730a[i2].getLinearY(d2);
                    return;
                }
                arcArr3[i2].f(d2);
                fArr[0] = (float) this.f1730a[i2].c();
                fArr[1] = (float) this.f1730a[i2].d();
                return;
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getSlope(double d2, int i2) {
        Arc[] arcArr = this.f1730a;
        int i3 = 0;
        if (d2 < arcArr[0].f1733c) {
            d2 = arcArr[0].f1733c;
        }
        if (d2 > arcArr[arcArr.length - 1].f1734d) {
            d2 = arcArr[arcArr.length - 1].f1734d;
        }
        while (true) {
            Arc[] arcArr2 = this.f1730a;
            if (i3 >= arcArr2.length) {
                return Double.NaN;
            }
            if (d2 <= arcArr2[i3].f1734d) {
                if (arcArr2[i3].f1748r) {
                    return i2 == 0 ? arcArr2[i3].getLinearDX(d2) : arcArr2[i3].getLinearDY(d2);
                }
                arcArr2[i3].f(d2);
                Arc[] arcArr3 = this.f1730a;
                return i2 == 0 ? arcArr3[i3].a() : arcArr3[i3].b();
            }
            i3++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getSlope(double d2, double[] dArr) {
        Arc[] arcArr = this.f1730a;
        if (d2 < arcArr[0].f1733c) {
            d2 = arcArr[0].f1733c;
        } else if (d2 > arcArr[arcArr.length - 1].f1734d) {
            d2 = arcArr[arcArr.length - 1].f1734d;
        }
        int i2 = 0;
        while (true) {
            Arc[] arcArr2 = this.f1730a;
            if (i2 >= arcArr2.length) {
                return;
            }
            if (d2 <= arcArr2[i2].f1734d) {
                if (arcArr2[i2].f1748r) {
                    dArr[0] = arcArr2[i2].getLinearDX(d2);
                    dArr[1] = this.f1730a[i2].getLinearDY(d2);
                    return;
                }
                arcArr2[i2].f(d2);
                dArr[0] = this.f1730a[i2].a();
                dArr[1] = this.f1730a[i2].b();
                return;
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double[] getTimePoints() {
        return this.mTime;
    }
}
