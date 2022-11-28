package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;
/* loaded from: classes.dex */
public class Oscillator {
    public static final int BOUNCE = 6;
    public static final int COS_WAVE = 5;
    public static final int CUSTOM = 7;
    public static final int REVERSE_SAW_WAVE = 4;
    public static final int SAW_WAVE = 3;
    public static final int SIN_WAVE = 0;
    public static final int SQUARE_WAVE = 1;
    public static String TAG = "Oscillator";
    public static final int TRIANGLE_WAVE = 2;

    /* renamed from: c  reason: collision with root package name */
    double[] f1800c;

    /* renamed from: d  reason: collision with root package name */
    String f1801d;

    /* renamed from: e  reason: collision with root package name */
    MonotonicCurveFit f1802e;

    /* renamed from: f  reason: collision with root package name */
    int f1803f;

    /* renamed from: a  reason: collision with root package name */
    float[] f1798a = new float[0];

    /* renamed from: b  reason: collision with root package name */
    double[] f1799b = new double[0];

    /* renamed from: g  reason: collision with root package name */
    double f1804g = 6.283185307179586d;
    private boolean mNormalized = false;

    double a(double d2) {
        if (d2 <= 0.0d) {
            d2 = 1.0E-5d;
        } else if (d2 >= 1.0d) {
            d2 = 0.999999d;
        }
        int binarySearch = Arrays.binarySearch(this.f1799b, d2);
        if (binarySearch <= 0 && binarySearch != 0) {
            int i2 = (-binarySearch) - 1;
            float[] fArr = this.f1798a;
            int i3 = i2 - 1;
            double d3 = fArr[i2] - fArr[i3];
            double[] dArr = this.f1799b;
            double d4 = d3 / (dArr[i2] - dArr[i3]);
            return (fArr[i3] - (d4 * dArr[i3])) + (d2 * d4);
        }
        return 0.0d;
    }

    public void addPoint(double d2, float f2) {
        int length = this.f1798a.length + 1;
        int binarySearch = Arrays.binarySearch(this.f1799b, d2);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 1;
        }
        this.f1799b = Arrays.copyOf(this.f1799b, length);
        this.f1798a = Arrays.copyOf(this.f1798a, length);
        this.f1800c = new double[length];
        double[] dArr = this.f1799b;
        System.arraycopy(dArr, binarySearch, dArr, binarySearch + 1, (length - binarySearch) - 1);
        this.f1799b[binarySearch] = d2;
        this.f1798a[binarySearch] = f2;
        this.mNormalized = false;
    }

    double b(double d2) {
        if (d2 < 0.0d) {
            d2 = 0.0d;
        } else if (d2 > 1.0d) {
            d2 = 1.0d;
        }
        int binarySearch = Arrays.binarySearch(this.f1799b, d2);
        if (binarySearch > 0) {
            return 1.0d;
        }
        if (binarySearch != 0) {
            int i2 = (-binarySearch) - 1;
            float[] fArr = this.f1798a;
            int i3 = i2 - 1;
            double d3 = fArr[i2] - fArr[i3];
            double[] dArr = this.f1799b;
            double d4 = d3 / (dArr[i2] - dArr[i3]);
            return this.f1800c[i3] + ((fArr[i3] - (dArr[i3] * d4)) * (d2 - dArr[i3])) + ((d4 * ((d2 * d2) - (dArr[i3] * dArr[i3]))) / 2.0d);
        }
        return 0.0d;
    }

    public double getSlope(double d2, double d3, double d4) {
        double b2 = d3 + b(d2);
        double a2 = a(d2) + d4;
        switch (this.f1803f) {
            case 1:
                return 0.0d;
            case 2:
                return a2 * 4.0d * Math.signum((((b2 * 4.0d) + 3.0d) % 4.0d) - 2.0d);
            case 3:
                return a2 * 2.0d;
            case 4:
                return (-a2) * 2.0d;
            case 5:
                double d5 = this.f1804g;
                return (-d5) * a2 * Math.sin(d5 * b2);
            case 6:
                return a2 * 4.0d * ((((b2 * 4.0d) + 2.0d) % 4.0d) - 2.0d);
            case 7:
                return this.f1802e.getSlope(b2 % 1.0d, 0);
            default:
                double d6 = this.f1804g;
                return a2 * d6 * Math.cos(d6 * b2);
        }
    }

    public double getValue(double d2, double d3) {
        double abs;
        double b2 = b(d2) + d3;
        switch (this.f1803f) {
            case 1:
                return Math.signum(0.5d - (b2 % 1.0d));
            case 2:
                abs = Math.abs((((b2 * 4.0d) + 1.0d) % 4.0d) - 2.0d);
                break;
            case 3:
                return (((b2 * 2.0d) + 1.0d) % 2.0d) - 1.0d;
            case 4:
                abs = ((b2 * 2.0d) + 1.0d) % 2.0d;
                break;
            case 5:
                return Math.cos(this.f1804g * (d3 + b2));
            case 6:
                double abs2 = 1.0d - Math.abs(((b2 * 4.0d) % 4.0d) - 2.0d);
                abs = abs2 * abs2;
                break;
            case 7:
                return this.f1802e.getPos(b2 % 1.0d, 0);
            default:
                return Math.sin(this.f1804g * b2);
        }
        return 1.0d - abs;
    }

    public void normalize() {
        float[] fArr;
        float[] fArr2;
        float[] fArr3;
        int i2;
        int i3 = 0;
        double d2 = 0.0d;
        while (true) {
            if (i3 >= this.f1798a.length) {
                break;
            }
            d2 += fArr[i3];
            i3++;
        }
        double d3 = 0.0d;
        int i4 = 1;
        while (true) {
            if (i4 >= this.f1798a.length) {
                break;
            }
            double[] dArr = this.f1799b;
            d3 += (dArr[i4] - dArr[i4 - 1]) * ((fArr2[i2] + fArr2[i4]) / 2.0f);
            i4++;
        }
        int i5 = 0;
        while (true) {
            float[] fArr4 = this.f1798a;
            if (i5 >= fArr4.length) {
                break;
            }
            fArr4[i5] = (float) (fArr4[i5] * (d2 / d3));
            i5++;
        }
        this.f1800c[0] = 0.0d;
        int i6 = 1;
        while (true) {
            if (i6 >= this.f1798a.length) {
                this.mNormalized = true;
                return;
            }
            int i7 = i6 - 1;
            double[] dArr2 = this.f1799b;
            double d4 = dArr2[i6] - dArr2[i7];
            double[] dArr3 = this.f1800c;
            dArr3[i6] = dArr3[i7] + (d4 * ((fArr3[i7] + fArr3[i6]) / 2.0f));
            i6++;
        }
    }

    public void setType(int i2, String str) {
        this.f1803f = i2;
        this.f1801d = str;
        if (str != null) {
            this.f1802e = MonotonicCurveFit.buildWave(str);
        }
    }

    public String toString() {
        return "pos =" + Arrays.toString(this.f1799b) + " period=" + Arrays.toString(this.f1798a);
    }
}
