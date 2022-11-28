package androidx.constraintlayout.motion.widget;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintSet;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.util.Arrays;
import java.util.LinkedHashMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class MotionPaths implements Comparable<MotionPaths> {
    public static final boolean DEBUG = false;
    public static final boolean OLD_WAY = false;
    public static final String TAG = "MotionPaths";

    /* renamed from: s  reason: collision with root package name */
    static String[] f2130s = {AppConstants.ARG_POSITION, "x", "y", "width", "height", "pathRotate"};

    /* renamed from: a  reason: collision with root package name */
    Easing f2131a;

    /* renamed from: c  reason: collision with root package name */
    float f2133c;

    /* renamed from: d  reason: collision with root package name */
    float f2134d;

    /* renamed from: e  reason: collision with root package name */
    float f2135e;

    /* renamed from: f  reason: collision with root package name */
    float f2136f;

    /* renamed from: g  reason: collision with root package name */
    float f2137g;

    /* renamed from: h  reason: collision with root package name */
    float f2138h;

    /* renamed from: k  reason: collision with root package name */
    int f2141k;

    /* renamed from: l  reason: collision with root package name */
    int f2142l;

    /* renamed from: m  reason: collision with root package name */
    float f2143m;

    /* renamed from: n  reason: collision with root package name */
    MotionController f2144n;

    /* renamed from: o  reason: collision with root package name */
    LinkedHashMap f2145o;

    /* renamed from: p  reason: collision with root package name */
    int f2146p;

    /* renamed from: q  reason: collision with root package name */
    double[] f2147q;

    /* renamed from: r  reason: collision with root package name */
    double[] f2148r;

    /* renamed from: b  reason: collision with root package name */
    int f2132b = 0;

    /* renamed from: i  reason: collision with root package name */
    float f2139i = Float.NaN;

    /* renamed from: j  reason: collision with root package name */
    float f2140j = Float.NaN;

    public MotionPaths() {
        int i2 = Key.UNSET;
        this.f2141k = i2;
        this.f2142l = i2;
        this.f2143m = Float.NaN;
        this.f2144n = null;
        this.f2145o = new LinkedHashMap();
        this.f2146p = 0;
        this.f2147q = new double[18];
        this.f2148r = new double[18];
    }

    public MotionPaths(int i2, int i3, KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        int i4 = Key.UNSET;
        this.f2141k = i4;
        this.f2142l = i4;
        this.f2143m = Float.NaN;
        this.f2144n = null;
        this.f2145o = new LinkedHashMap();
        this.f2146p = 0;
        this.f2147q = new double[18];
        this.f2148r = new double[18];
        if (motionPaths.f2142l != Key.UNSET) {
            k(i2, i3, keyPosition, motionPaths, motionPaths2);
            return;
        }
        int i5 = keyPosition.f2056p;
        if (i5 == 1) {
            j(keyPosition, motionPaths, motionPaths2);
        } else if (i5 != 2) {
            i(keyPosition, motionPaths, motionPaths2);
        } else {
            l(i2, i3, keyPosition, motionPaths, motionPaths2);
        }
    }

    private boolean diff(float f2, float f3) {
        return (Float.isNaN(f2) || Float.isNaN(f3)) ? Float.isNaN(f2) != Float.isNaN(f3) : Math.abs(f2 - f3) > 1.0E-6f;
    }

    private static final float xRotate(float f2, float f3, float f4, float f5, float f6, float f7) {
        return (((f6 - f4) * f3) - ((f7 - f5) * f2)) + f4;
    }

    private static final float yRotate(float f2, float f3, float f4, float f5, float f6, float f7) {
        return ((f6 - f4) * f2) + ((f7 - f5) * f3) + f5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(MotionPaths motionPaths, boolean[] zArr, String[] strArr, boolean z) {
        boolean diff = diff(this.f2135e, motionPaths.f2135e);
        boolean diff2 = diff(this.f2136f, motionPaths.f2136f);
        zArr[0] = zArr[0] | diff(this.f2134d, motionPaths.f2134d);
        boolean z2 = diff | diff2 | z;
        zArr[1] = zArr[1] | z2;
        zArr[2] = z2 | zArr[2];
        zArr[3] = zArr[3] | diff(this.f2137g, motionPaths.f2137g);
        zArr[4] = diff(this.f2138h, motionPaths.f2138h) | zArr[4];
    }

    public void applyParameters(ConstraintSet.Constraint constraint) {
        this.f2131a = Easing.getInterpolator(constraint.motion.mTransitionEasing);
        ConstraintSet.Motion motion = constraint.motion;
        this.f2141k = motion.mPathMotionArc;
        this.f2142l = motion.mAnimateRelativeTo;
        this.f2139i = motion.mPathRotate;
        this.f2132b = motion.mDrawPath;
        int i2 = motion.mAnimateCircleAngleTo;
        this.f2140j = constraint.propertySet.mProgress;
        this.f2143m = constraint.layout.circleAngle;
        for (String str : constraint.mCustomConstraints.keySet()) {
            ConstraintAttribute constraintAttribute = constraint.mCustomConstraints.get(str);
            if (constraintAttribute != null && constraintAttribute.isContinuous()) {
                this.f2145o.put(str, constraintAttribute);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(double[] dArr, int[] iArr) {
        float[] fArr = {this.f2134d, this.f2135e, this.f2136f, this.f2137g, this.f2138h, this.f2139i};
        int i2 = 0;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            if (iArr[i3] < 6) {
                dArr[i2] = fArr[iArr[i3]];
                i2++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(double d2, int[] iArr, double[] dArr, float[] fArr, int i2) {
        float f2 = this.f2135e;
        float f3 = this.f2136f;
        float f4 = this.f2137g;
        float f5 = this.f2138h;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            float f6 = (float) dArr[i3];
            int i4 = iArr[i3];
            if (i4 == 1) {
                f2 = f6;
            } else if (i4 == 2) {
                f3 = f6;
            } else if (i4 == 3) {
                f4 = f6;
            } else if (i4 == 4) {
                f5 = f6;
            }
        }
        MotionController motionController = this.f2144n;
        if (motionController != null) {
            float[] fArr2 = new float[2];
            motionController.getCenter(d2, fArr2, new float[2]);
            float f7 = fArr2[0];
            float f8 = fArr2[1];
            double d3 = f7;
            double d4 = f2;
            double d5 = f3;
            f2 = (float) ((d3 + (Math.sin(d5) * d4)) - (f4 / 2.0f));
            f3 = (float) ((f8 - (d4 * Math.cos(d5))) - (f5 / 2.0f));
        }
        fArr[i2] = f2 + (f4 / 2.0f) + 0.0f;
        fArr[i2 + 1] = f3 + (f5 / 2.0f) + 0.0f;
    }

    @Override // java.lang.Comparable
    public int compareTo(@NonNull MotionPaths motionPaths) {
        return Float.compare(this.f2134d, motionPaths.f2134d);
    }

    public void configureRelativeTo(MotionController motionController) {
        motionController.k(this.f2140j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(double d2, int[] iArr, double[] dArr, float[] fArr, double[] dArr2, float[] fArr2) {
        float f2;
        float f3 = this.f2135e;
        float f4 = this.f2136f;
        float f5 = this.f2137g;
        float f6 = this.f2138h;
        float f7 = 0.0f;
        float f8 = 0.0f;
        float f9 = 0.0f;
        float f10 = 0.0f;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f11 = (float) dArr[i2];
            float f12 = (float) dArr2[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f3 = f11;
                f7 = f12;
            } else if (i3 == 2) {
                f4 = f11;
                f9 = f12;
            } else if (i3 == 3) {
                f5 = f11;
                f8 = f12;
            } else if (i3 == 4) {
                f6 = f11;
                f10 = f12;
            }
        }
        float f13 = 2.0f;
        float f14 = (f8 / 2.0f) + f7;
        float f15 = (f10 / 2.0f) + f9;
        MotionController motionController = this.f2144n;
        if (motionController != null) {
            float[] fArr3 = new float[2];
            float[] fArr4 = new float[2];
            motionController.getCenter(d2, fArr3, fArr4);
            float f16 = fArr3[0];
            float f17 = fArr3[1];
            float f18 = fArr4[0];
            float f19 = fArr4[1];
            double d3 = f3;
            double d4 = f4;
            f2 = f5;
            double d5 = f7;
            double d6 = f9;
            float sin = (float) (f18 + (Math.sin(d4) * d5) + (Math.cos(d4) * d6));
            f15 = (float) ((f19 - (d5 * Math.cos(d4))) + (Math.sin(d4) * d6));
            f14 = sin;
            f3 = (float) ((f16 + (Math.sin(d4) * d3)) - (f5 / 2.0f));
            f4 = (float) ((f17 - (d3 * Math.cos(d4))) - (f6 / 2.0f));
            f13 = 2.0f;
        } else {
            f2 = f5;
        }
        fArr[0] = f3 + (f2 / f13) + 0.0f;
        fArr[1] = f4 + (f6 / f13) + 0.0f;
        fArr2[0] = f14;
        fArr2[1] = f15;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int e(String str, double[] dArr, int i2) {
        ConstraintAttribute constraintAttribute = (ConstraintAttribute) this.f2145o.get(str);
        int i3 = 0;
        if (constraintAttribute == null) {
            return 0;
        }
        if (constraintAttribute.numberOfInterpolatedValues() == 1) {
            dArr[i2] = constraintAttribute.getValueToInterpolate();
            return 1;
        }
        int numberOfInterpolatedValues = constraintAttribute.numberOfInterpolatedValues();
        float[] fArr = new float[numberOfInterpolatedValues];
        constraintAttribute.getValuesToInterpolate(fArr);
        while (i3 < numberOfInterpolatedValues) {
            dArr[i2] = fArr[i3];
            i3++;
            i2++;
        }
        return numberOfInterpolatedValues;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f(String str) {
        ConstraintAttribute constraintAttribute = (ConstraintAttribute) this.f2145o.get(str);
        if (constraintAttribute == null) {
            return 0;
        }
        return constraintAttribute.numberOfInterpolatedValues();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(int[] iArr, double[] dArr, float[] fArr, int i2) {
        float f2 = this.f2135e;
        float f3 = this.f2136f;
        float f4 = this.f2137g;
        float f5 = this.f2138h;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            float f6 = (float) dArr[i3];
            int i4 = iArr[i3];
            if (i4 == 1) {
                f2 = f6;
            } else if (i4 == 2) {
                f3 = f6;
            } else if (i4 == 3) {
                f4 = f6;
            } else if (i4 == 4) {
                f5 = f6;
            }
        }
        MotionController motionController = this.f2144n;
        if (motionController != null) {
            float centerX = motionController.getCenterX();
            double d2 = f2;
            double d3 = f3;
            f3 = (float) ((this.f2144n.getCenterY() - (d2 * Math.cos(d3))) - (f5 / 2.0f));
            f2 = (float) ((centerX + (Math.sin(d3) * d2)) - (f4 / 2.0f));
        }
        float f7 = f4 + f2;
        float f8 = f5 + f3;
        Float.isNaN(Float.NaN);
        Float.isNaN(Float.NaN);
        int i5 = i2 + 1;
        fArr[i2] = f2 + 0.0f;
        int i6 = i5 + 1;
        fArr[i5] = f3 + 0.0f;
        int i7 = i6 + 1;
        fArr[i6] = f7 + 0.0f;
        int i8 = i7 + 1;
        fArr[i7] = f3 + 0.0f;
        int i9 = i8 + 1;
        fArr[i8] = f7 + 0.0f;
        int i10 = i9 + 1;
        fArr[i9] = f8 + 0.0f;
        fArr[i10] = f2 + 0.0f;
        fArr[i10 + 1] = f8 + 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean h(String str) {
        return this.f2145o.containsKey(str);
    }

    void i(KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f2 = keyPosition.f2041a / 100.0f;
        this.f2133c = f2;
        this.f2132b = keyPosition.f2049i;
        float f3 = Float.isNaN(keyPosition.f2050j) ? f2 : keyPosition.f2050j;
        float f4 = Float.isNaN(keyPosition.f2051k) ? f2 : keyPosition.f2051k;
        float f5 = motionPaths2.f2137g;
        float f6 = motionPaths.f2137g;
        float f7 = motionPaths2.f2138h;
        float f8 = motionPaths.f2138h;
        this.f2134d = this.f2133c;
        float f9 = motionPaths.f2135e;
        float f10 = motionPaths.f2136f;
        float f11 = (motionPaths2.f2135e + (f5 / 2.0f)) - ((f6 / 2.0f) + f9);
        float f12 = (motionPaths2.f2136f + (f7 / 2.0f)) - (f10 + (f8 / 2.0f));
        float f13 = (f5 - f6) * f3;
        float f14 = f13 / 2.0f;
        this.f2135e = (int) ((f9 + (f11 * f2)) - f14);
        float f15 = (f7 - f8) * f4;
        float f16 = f15 / 2.0f;
        this.f2136f = (int) ((f10 + (f12 * f2)) - f16);
        this.f2137g = (int) (f6 + f13);
        this.f2138h = (int) (f8 + f15);
        float f17 = Float.isNaN(keyPosition.f2052l) ? f2 : keyPosition.f2052l;
        float f18 = Float.isNaN(keyPosition.f2055o) ? 0.0f : keyPosition.f2055o;
        if (!Float.isNaN(keyPosition.f2053m)) {
            f2 = keyPosition.f2053m;
        }
        float f19 = Float.isNaN(keyPosition.f2054n) ? 0.0f : keyPosition.f2054n;
        this.f2146p = 0;
        this.f2135e = (int) (((motionPaths.f2135e + (f17 * f11)) + (f19 * f12)) - f14);
        this.f2136f = (int) (((motionPaths.f2136f + (f11 * f18)) + (f12 * f2)) - f16);
        this.f2131a = Easing.getInterpolator(keyPosition.f2047g);
        this.f2141k = keyPosition.f2048h;
    }

    void j(KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f2;
        float f3;
        float f4 = keyPosition.f2041a / 100.0f;
        this.f2133c = f4;
        this.f2132b = keyPosition.f2049i;
        float f5 = Float.isNaN(keyPosition.f2050j) ? f4 : keyPosition.f2050j;
        float f6 = Float.isNaN(keyPosition.f2051k) ? f4 : keyPosition.f2051k;
        float f7 = motionPaths2.f2137g - motionPaths.f2137g;
        float f8 = motionPaths2.f2138h - motionPaths.f2138h;
        this.f2134d = this.f2133c;
        if (!Float.isNaN(keyPosition.f2052l)) {
            f4 = keyPosition.f2052l;
        }
        float f9 = motionPaths.f2135e;
        float f10 = motionPaths.f2137g;
        float f11 = motionPaths.f2136f;
        float f12 = motionPaths.f2138h;
        float f13 = (motionPaths2.f2135e + (motionPaths2.f2137g / 2.0f)) - ((f10 / 2.0f) + f9);
        float f14 = (motionPaths2.f2136f + (motionPaths2.f2138h / 2.0f)) - ((f12 / 2.0f) + f11);
        float f15 = f13 * f4;
        float f16 = (f7 * f5) / 2.0f;
        this.f2135e = (int) ((f9 + f15) - f16);
        float f17 = f4 * f14;
        float f18 = (f8 * f6) / 2.0f;
        this.f2136f = (int) ((f11 + f17) - f18);
        this.f2137g = (int) (f10 + f2);
        this.f2138h = (int) (f12 + f3);
        float f19 = Float.isNaN(keyPosition.f2053m) ? 0.0f : keyPosition.f2053m;
        this.f2146p = 1;
        float f20 = (int) ((motionPaths.f2135e + f15) - f16);
        this.f2135e = f20;
        float f21 = (int) ((motionPaths.f2136f + f17) - f18);
        this.f2136f = f21;
        this.f2135e = f20 + ((-f14) * f19);
        this.f2136f = f21 + (f13 * f19);
        this.f2142l = this.f2142l;
        this.f2131a = Easing.getInterpolator(keyPosition.f2047g);
        this.f2141k = keyPosition.f2048h;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0065, code lost:
        if (java.lang.Float.isNaN(r9.f2053m) != false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0068, code lost:
        r7 = r9.f2053m;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b4, code lost:
        if (java.lang.Float.isNaN(r9.f2053m) != false) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void k(int i2, int i3, KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f2;
        float min;
        float f3 = keyPosition.f2041a / 100.0f;
        this.f2133c = f3;
        this.f2132b = keyPosition.f2049i;
        this.f2146p = keyPosition.f2056p;
        float f4 = Float.isNaN(keyPosition.f2050j) ? f3 : keyPosition.f2050j;
        float f5 = Float.isNaN(keyPosition.f2051k) ? f3 : keyPosition.f2051k;
        float f6 = motionPaths2.f2137g;
        float f7 = motionPaths.f2137g;
        float f8 = motionPaths2.f2138h;
        float f9 = motionPaths.f2138h;
        this.f2134d = this.f2133c;
        this.f2137g = (int) (f7 + ((f6 - f7) * f4));
        this.f2138h = (int) (f9 + ((f8 - f9) * f5));
        int i4 = keyPosition.f2056p;
        if (i4 != 1) {
            if (i4 != 2) {
                float f10 = Float.isNaN(keyPosition.f2052l) ? f3 : keyPosition.f2052l;
                float f11 = motionPaths2.f2135e;
                float f12 = motionPaths.f2135e;
                this.f2135e = (f10 * (f11 - f12)) + f12;
            } else {
                if (Float.isNaN(keyPosition.f2052l)) {
                    float f13 = motionPaths2.f2135e;
                    float f14 = motionPaths.f2135e;
                    min = ((f13 - f14) * f3) + f14;
                } else {
                    min = Math.min(f5, f4) * keyPosition.f2052l;
                }
                this.f2135e = min;
                if (!Float.isNaN(keyPosition.f2053m)) {
                    f2 = keyPosition.f2053m;
                }
            }
            float f15 = motionPaths2.f2136f;
            float f16 = motionPaths.f2136f;
            f2 = (f3 * (f15 - f16)) + f16;
        } else {
            float f17 = Float.isNaN(keyPosition.f2052l) ? f3 : keyPosition.f2052l;
            float f18 = motionPaths2.f2135e;
            float f19 = motionPaths.f2135e;
            this.f2135e = (f17 * (f18 - f19)) + f19;
        }
        this.f2136f = f2;
        this.f2142l = motionPaths.f2142l;
        this.f2131a = Easing.getInterpolator(keyPosition.f2047g);
        this.f2141k = keyPosition.f2048h;
    }

    void l(int i2, int i3, KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f2 = keyPosition.f2041a / 100.0f;
        this.f2133c = f2;
        this.f2132b = keyPosition.f2049i;
        float f3 = Float.isNaN(keyPosition.f2050j) ? f2 : keyPosition.f2050j;
        float f4 = Float.isNaN(keyPosition.f2051k) ? f2 : keyPosition.f2051k;
        float f5 = motionPaths2.f2137g;
        float f6 = motionPaths.f2137g;
        float f7 = motionPaths2.f2138h;
        float f8 = motionPaths.f2138h;
        this.f2134d = this.f2133c;
        float f9 = motionPaths.f2135e;
        float f10 = motionPaths.f2136f;
        float f11 = motionPaths2.f2135e + (f5 / 2.0f);
        float f12 = motionPaths2.f2136f + (f7 / 2.0f);
        float f13 = (f5 - f6) * f3;
        this.f2135e = (int) ((f9 + ((f11 - ((f6 / 2.0f) + f9)) * f2)) - (f13 / 2.0f));
        float f14 = (f7 - f8) * f4;
        this.f2136f = (int) ((f10 + ((f12 - (f10 + (f8 / 2.0f))) * f2)) - (f14 / 2.0f));
        this.f2137g = (int) (f6 + f13);
        this.f2138h = (int) (f8 + f14);
        this.f2146p = 2;
        if (!Float.isNaN(keyPosition.f2052l)) {
            this.f2135e = (int) (keyPosition.f2052l * ((int) (i2 - this.f2137g)));
        }
        if (!Float.isNaN(keyPosition.f2053m)) {
            this.f2136f = (int) (keyPosition.f2053m * ((int) (i3 - this.f2138h)));
        }
        this.f2142l = this.f2142l;
        this.f2131a = Easing.getInterpolator(keyPosition.f2047g);
        this.f2141k = keyPosition.f2048h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(float f2, float f3, float f4, float f5) {
        this.f2135e = f2;
        this.f2136f = f3;
        this.f2137g = f4;
        this.f2138h = f5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(float f2, float f3, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f8 = (float) dArr[i2];
            double d2 = dArr2[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f4 = f8;
            } else if (i3 == 2) {
                f6 = f8;
            } else if (i3 == 3) {
                f5 = f8;
            } else if (i3 == 4) {
                f7 = f8;
            }
        }
        float f9 = f4 - ((0.0f * f5) / 2.0f);
        float f10 = f6 - ((0.0f * f7) / 2.0f);
        fArr[0] = (f9 * (1.0f - f2)) + (((f5 * 1.0f) + f9) * f2) + 0.0f;
        fArr[1] = (f10 * (1.0f - f3)) + (((f7 * 1.0f) + f10) * f3) + 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(float f2, View view, int[] iArr, double[] dArr, double[] dArr2, double[] dArr3) {
        float f3;
        boolean z;
        boolean z2;
        float f4;
        float f5 = this.f2135e;
        float f6 = this.f2136f;
        float f7 = this.f2137g;
        float f8 = this.f2138h;
        if (iArr.length != 0 && this.f2147q.length <= iArr[iArr.length - 1]) {
            int i2 = iArr[iArr.length - 1] + 1;
            this.f2147q = new double[i2];
            this.f2148r = new double[i2];
        }
        Arrays.fill(this.f2147q, Double.NaN);
        for (int i3 = 0; i3 < iArr.length; i3++) {
            this.f2147q[iArr[i3]] = dArr[i3];
            this.f2148r[iArr[i3]] = dArr2[i3];
        }
        float f9 = Float.NaN;
        int i4 = 0;
        float f10 = 0.0f;
        float f11 = 0.0f;
        float f12 = 0.0f;
        float f13 = 0.0f;
        while (true) {
            double[] dArr4 = this.f2147q;
            if (i4 >= dArr4.length) {
                break;
            }
            if (Double.isNaN(dArr4[i4]) && (dArr3 == null || dArr3[i4] == 0.0d)) {
                f4 = f9;
            } else {
                double d2 = dArr3 != null ? dArr3[i4] : 0.0d;
                if (!Double.isNaN(this.f2147q[i4])) {
                    d2 = this.f2147q[i4] + d2;
                }
                f4 = f9;
                float f14 = (float) d2;
                float f15 = (float) this.f2148r[i4];
                if (i4 == 1) {
                    f9 = f4;
                    f10 = f15;
                    f5 = f14;
                } else if (i4 == 2) {
                    f9 = f4;
                    f11 = f15;
                    f6 = f14;
                } else if (i4 == 3) {
                    f9 = f4;
                    f12 = f15;
                    f7 = f14;
                } else if (i4 == 4) {
                    f9 = f4;
                    f13 = f15;
                    f8 = f14;
                } else if (i4 == 5) {
                    f9 = f14;
                }
                i4++;
            }
            f9 = f4;
            i4++;
        }
        float f16 = f9;
        MotionController motionController = this.f2144n;
        if (motionController != null) {
            float[] fArr = new float[2];
            float[] fArr2 = new float[2];
            motionController.getCenter(f2, fArr, fArr2);
            float f17 = fArr[0];
            float f18 = fArr[1];
            float f19 = fArr2[0];
            float f20 = fArr2[1];
            double d3 = f5;
            double d4 = f6;
            float sin = (float) ((f17 + (Math.sin(d4) * d3)) - (f7 / 2.0f));
            f3 = f8;
            float cos = (float) ((f18 - (Math.cos(d4) * d3)) - (f8 / 2.0f));
            double d5 = f10;
            double d6 = f11;
            float sin2 = (float) (f19 + (Math.sin(d4) * d5) + (Math.cos(d4) * d3 * d6));
            float cos2 = (float) ((f20 - (d5 * Math.cos(d4))) + (d3 * Math.sin(d4) * d6));
            if (dArr2.length >= 2) {
                z = false;
                dArr2[0] = sin2;
                z2 = true;
                dArr2[1] = cos2;
            } else {
                z = false;
                z2 = true;
            }
            if (!Float.isNaN(f16)) {
                view.setRotation((float) (f16 + Math.toDegrees(Math.atan2(cos2, sin2))));
            }
            f5 = sin;
            f6 = cos;
        } else {
            f3 = f8;
            z = false;
            z2 = true;
            if (!Float.isNaN(f16)) {
                view.setRotation((float) (0.0f + f16 + Math.toDegrees(Math.atan2(f11 + (f13 / 2.0f), f10 + (f12 / 2.0f)))));
            }
        }
        if (view instanceof FloatLayout) {
            ((FloatLayout) view).layout(f5, f6, f7 + f5, f6 + f3);
            return;
        }
        float f21 = f5 + 0.5f;
        int i5 = (int) f21;
        float f22 = f6 + 0.5f;
        int i6 = (int) f22;
        int i7 = (int) (f21 + f7);
        int i8 = (int) (f22 + f3);
        int i9 = i7 - i5;
        int i10 = i8 - i6;
        if (i9 != view.getMeasuredWidth() || i10 != view.getMeasuredHeight()) {
            z = z2;
        }
        if (z) {
            view.measure(View.MeasureSpec.makeMeasureSpec(i9, 1073741824), View.MeasureSpec.makeMeasureSpec(i10, 1073741824));
        }
        view.layout(i5, i6, i7, i8);
    }

    public void setupRelative(MotionController motionController, MotionPaths motionPaths) {
        double d2 = ((this.f2135e + (this.f2137g / 2.0f)) - motionPaths.f2135e) - (motionPaths.f2137g / 2.0f);
        double d3 = ((this.f2136f + (this.f2138h / 2.0f)) - motionPaths.f2136f) - (motionPaths.f2138h / 2.0f);
        this.f2144n = motionController;
        this.f2135e = (float) Math.hypot(d3, d2);
        this.f2136f = (float) (Float.isNaN(this.f2143m) ? Math.atan2(d3, d2) + 1.5707963267948966d : Math.toRadians(this.f2143m));
    }
}
