package androidx.constraintlayout.core.motion.utils;

import java.lang.reflect.Array;
/* loaded from: classes.dex */
public class HyperSpline {

    /* renamed from: a  reason: collision with root package name */
    int f1757a;

    /* renamed from: b  reason: collision with root package name */
    Cubic[][] f1758b;

    /* renamed from: c  reason: collision with root package name */
    int f1759c;

    /* renamed from: d  reason: collision with root package name */
    double[] f1760d;

    /* renamed from: e  reason: collision with root package name */
    double f1761e;

    /* renamed from: f  reason: collision with root package name */
    double[][] f1762f;

    /* loaded from: classes.dex */
    public static class Cubic {

        /* renamed from: a  reason: collision with root package name */
        double f1763a;

        /* renamed from: b  reason: collision with root package name */
        double f1764b;

        /* renamed from: c  reason: collision with root package name */
        double f1765c;

        /* renamed from: d  reason: collision with root package name */
        double f1766d;

        public Cubic(double d2, double d3, double d4, double d5) {
            this.f1763a = d2;
            this.f1764b = d3;
            this.f1765c = d4;
            this.f1766d = d5;
        }

        public double eval(double d2) {
            return (((((this.f1766d * d2) + this.f1765c) * d2) + this.f1764b) * d2) + this.f1763a;
        }

        public double vel(double d2) {
            return (((this.f1766d * 3.0d * d2) + (this.f1765c * 2.0d)) * d2) + this.f1764b;
        }
    }

    public HyperSpline() {
    }

    public HyperSpline(double[][] dArr) {
        setup(dArr);
    }

    static Cubic[] a(int i2, double[] dArr) {
        double[] dArr2 = new double[i2];
        double[] dArr3 = new double[i2];
        double[] dArr4 = new double[i2];
        int i3 = i2 - 1;
        int i4 = 0;
        dArr2[0] = 0.5d;
        int i5 = 1;
        for (int i6 = 1; i6 < i3; i6++) {
            dArr2[i6] = 1.0d / (4.0d - dArr2[i6 - 1]);
        }
        int i7 = i3 - 1;
        dArr2[i3] = 1.0d / (2.0d - dArr2[i7]);
        dArr3[0] = (dArr[1] - dArr[0]) * 3.0d * dArr2[0];
        while (i5 < i3) {
            int i8 = i5 + 1;
            int i9 = i5 - 1;
            dArr3[i5] = (((dArr[i8] - dArr[i9]) * 3.0d) - dArr3[i9]) * dArr2[i5];
            i5 = i8;
        }
        dArr3[i3] = (((dArr[i3] - dArr[i7]) * 3.0d) - dArr3[i7]) * dArr2[i3];
        dArr4[i3] = dArr3[i3];
        while (i7 >= 0) {
            dArr4[i7] = dArr3[i7] - (dArr2[i7] * dArr4[i7 + 1]);
            i7--;
        }
        Cubic[] cubicArr = new Cubic[i3];
        while (i4 < i3) {
            int i10 = i4 + 1;
            cubicArr[i4] = new Cubic((float) dArr[i4], dArr4[i4], (((dArr[i10] - dArr[i4]) * 3.0d) - (dArr4[i4] * 2.0d)) - dArr4[i10], ((dArr[i4] - dArr[i10]) * 2.0d) + dArr4[i4] + dArr4[i10]);
            i4 = i10;
        }
        return cubicArr;
    }

    public double approxLength(Cubic[] cubicArr) {
        int i2;
        int length = cubicArr.length;
        double[] dArr = new double[cubicArr.length];
        double d2 = 0.0d;
        double d3 = 0.0d;
        double d4 = 0.0d;
        while (true) {
            i2 = 0;
            if (d3 >= 1.0d) {
                break;
            }
            double d5 = 0.0d;
            while (i2 < cubicArr.length) {
                double d6 = dArr[i2];
                double eval = cubicArr[i2].eval(d3);
                dArr[i2] = eval;
                double d7 = d6 - eval;
                d5 += d7 * d7;
                i2++;
            }
            if (d3 > 0.0d) {
                d4 += Math.sqrt(d5);
            }
            d3 += 0.1d;
        }
        while (i2 < cubicArr.length) {
            double d8 = dArr[i2];
            double eval2 = cubicArr[i2].eval(1.0d);
            dArr[i2] = eval2;
            double d9 = d8 - eval2;
            d2 += d9 * d9;
            i2++;
        }
        return d4 + Math.sqrt(d2);
    }

    public double getPos(double d2, int i2) {
        double[] dArr;
        double d3 = d2 * this.f1761e;
        int i3 = 0;
        while (true) {
            dArr = this.f1760d;
            if (i3 >= dArr.length - 1 || dArr[i3] >= d3) {
                break;
            }
            d3 -= dArr[i3];
            i3++;
        }
        return this.f1758b[i2][i3].eval(d3 / dArr[i3]);
    }

    public void getPos(double d2, double[] dArr) {
        double d3 = d2 * this.f1761e;
        int i2 = 0;
        while (true) {
            double[] dArr2 = this.f1760d;
            if (i2 >= dArr2.length - 1 || dArr2[i2] >= d3) {
                break;
            }
            d3 -= dArr2[i2];
            i2++;
        }
        for (int i3 = 0; i3 < dArr.length; i3++) {
            dArr[i3] = this.f1758b[i3][i2].eval(d3 / this.f1760d[i2]);
        }
    }

    public void getPos(double d2, float[] fArr) {
        double d3 = d2 * this.f1761e;
        int i2 = 0;
        while (true) {
            double[] dArr = this.f1760d;
            if (i2 >= dArr.length - 1 || dArr[i2] >= d3) {
                break;
            }
            d3 -= dArr[i2];
            i2++;
        }
        for (int i3 = 0; i3 < fArr.length; i3++) {
            fArr[i3] = (float) this.f1758b[i3][i2].eval(d3 / this.f1760d[i2]);
        }
    }

    public void getVelocity(double d2, double[] dArr) {
        double d3 = d2 * this.f1761e;
        int i2 = 0;
        while (true) {
            double[] dArr2 = this.f1760d;
            if (i2 >= dArr2.length - 1 || dArr2[i2] >= d3) {
                break;
            }
            d3 -= dArr2[i2];
            i2++;
        }
        for (int i3 = 0; i3 < dArr.length; i3++) {
            dArr[i3] = this.f1758b[i3][i2].vel(d3 / this.f1760d[i2]);
        }
    }

    public void setup(double[][] dArr) {
        int i2;
        int length = dArr[0].length;
        this.f1759c = length;
        int length2 = dArr.length;
        this.f1757a = length2;
        this.f1762f = (double[][]) Array.newInstance(double.class, length, length2);
        this.f1758b = new Cubic[this.f1759c];
        for (int i3 = 0; i3 < this.f1759c; i3++) {
            for (int i4 = 0; i4 < this.f1757a; i4++) {
                this.f1762f[i3][i4] = dArr[i4][i3];
            }
        }
        int i5 = 0;
        while (true) {
            i2 = this.f1759c;
            if (i5 >= i2) {
                break;
            }
            Cubic[][] cubicArr = this.f1758b;
            double[][] dArr2 = this.f1762f;
            cubicArr[i5] = a(dArr2[i5].length, dArr2[i5]);
            i5++;
        }
        this.f1760d = new double[this.f1757a - 1];
        this.f1761e = 0.0d;
        Cubic[] cubicArr2 = new Cubic[i2];
        for (int i6 = 0; i6 < this.f1760d.length; i6++) {
            for (int i7 = 0; i7 < this.f1759c; i7++) {
                cubicArr2[i7] = this.f1758b[i7][i6];
            }
            double d2 = this.f1761e;
            double[] dArr3 = this.f1760d;
            double approxLength = approxLength(cubicArr2);
            dArr3[i6] = approxLength;
            this.f1761e = d2 + approxLength;
        }
    }
}
