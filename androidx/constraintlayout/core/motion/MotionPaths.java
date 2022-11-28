package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.MotionWidget;
import androidx.constraintlayout.core.motion.key.MotionKeyPosition;
import androidx.constraintlayout.core.motion.utils.Easing;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.util.Arrays;
import java.util.HashMap;
/* loaded from: classes.dex */
public class MotionPaths implements Comparable<MotionPaths> {
    public static final int CARTESIAN = 0;
    public static final boolean DEBUG = false;
    public static final boolean OLD_WAY = false;
    public static final int PERPENDICULAR = 1;
    public static final int SCREEN = 2;
    public static final String TAG = "MotionPaths";

    /* renamed from: s  reason: collision with root package name */
    static String[] f1699s = {AppConstants.ARG_POSITION, "x", "y", "width", "height", "pathRotate"};

    /* renamed from: a  reason: collision with root package name */
    Easing f1700a;

    /* renamed from: b  reason: collision with root package name */
    int f1701b;

    /* renamed from: c  reason: collision with root package name */
    float f1702c;

    /* renamed from: d  reason: collision with root package name */
    float f1703d;

    /* renamed from: e  reason: collision with root package name */
    float f1704e;

    /* renamed from: f  reason: collision with root package name */
    float f1705f;

    /* renamed from: g  reason: collision with root package name */
    float f1706g;

    /* renamed from: h  reason: collision with root package name */
    float f1707h;

    /* renamed from: i  reason: collision with root package name */
    float f1708i;

    /* renamed from: j  reason: collision with root package name */
    float f1709j;

    /* renamed from: k  reason: collision with root package name */
    int f1710k;

    /* renamed from: l  reason: collision with root package name */
    int f1711l;

    /* renamed from: m  reason: collision with root package name */
    float f1712m;

    /* renamed from: n  reason: collision with root package name */
    Motion f1713n;

    /* renamed from: o  reason: collision with root package name */
    HashMap f1714o;

    /* renamed from: p  reason: collision with root package name */
    int f1715p;

    /* renamed from: q  reason: collision with root package name */
    double[] f1716q;

    /* renamed from: r  reason: collision with root package name */
    double[] f1717r;

    public MotionPaths() {
        this.f1701b = 0;
        this.f1708i = Float.NaN;
        this.f1709j = Float.NaN;
        this.f1710k = -1;
        this.f1711l = -1;
        this.f1712m = Float.NaN;
        this.f1713n = null;
        this.f1714o = new HashMap();
        this.f1715p = 0;
        this.f1716q = new double[18];
        this.f1717r = new double[18];
    }

    public MotionPaths(int i2, int i3, MotionKeyPosition motionKeyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        this.f1701b = 0;
        this.f1708i = Float.NaN;
        this.f1709j = Float.NaN;
        this.f1710k = -1;
        this.f1711l = -1;
        this.f1712m = Float.NaN;
        this.f1713n = null;
        this.f1714o = new HashMap();
        this.f1715p = 0;
        this.f1716q = new double[18];
        this.f1717r = new double[18];
        if (motionPaths.f1711l != -1) {
            k(i2, i3, motionKeyPosition, motionPaths, motionPaths2);
            return;
        }
        int i4 = motionKeyPosition.mPositionType;
        if (i4 == 1) {
            j(motionKeyPosition, motionPaths, motionPaths2);
        } else if (i4 != 2) {
            i(motionKeyPosition, motionPaths, motionPaths2);
        } else {
            l(i2, i3, motionKeyPosition, motionPaths, motionPaths2);
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
        boolean diff = diff(this.f1704e, motionPaths.f1704e);
        boolean diff2 = diff(this.f1705f, motionPaths.f1705f);
        zArr[0] = zArr[0] | diff(this.f1703d, motionPaths.f1703d);
        boolean z2 = diff | diff2 | z;
        zArr[1] = zArr[1] | z2;
        zArr[2] = z2 | zArr[2];
        zArr[3] = zArr[3] | diff(this.f1706g, motionPaths.f1706g);
        zArr[4] = diff(this.f1707h, motionPaths.f1707h) | zArr[4];
    }

    public void applyParameters(MotionWidget motionWidget) {
        this.f1700a = Easing.getInterpolator(motionWidget.f1719b.mTransitionEasing);
        MotionWidget.Motion motion = motionWidget.f1719b;
        this.f1710k = motion.mPathMotionArc;
        this.f1711l = motion.mAnimateRelativeTo;
        this.f1708i = motion.mPathRotate;
        this.f1701b = motion.mDrawPath;
        int i2 = motion.mAnimateCircleAngleTo;
        this.f1709j = motionWidget.f1720c.mProgress;
        this.f1712m = 0.0f;
        for (String str : motionWidget.getCustomAttributeNames()) {
            CustomVariable customAttribute = motionWidget.getCustomAttribute(str);
            if (customAttribute != null && customAttribute.isContinuous()) {
                this.f1714o.put(str, customAttribute);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(double[] dArr, int[] iArr) {
        float[] fArr = {this.f1703d, this.f1704e, this.f1705f, this.f1706g, this.f1707h, this.f1708i};
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
        float f2 = this.f1704e;
        float f3 = this.f1705f;
        float f4 = this.f1706g;
        float f5 = this.f1707h;
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
        Motion motion = this.f1713n;
        if (motion != null) {
            float[] fArr2 = new float[2];
            motion.getCenter(d2, fArr2, new float[2]);
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
    public int compareTo(MotionPaths motionPaths) {
        return Float.compare(this.f1703d, motionPaths.f1703d);
    }

    public void configureRelativeTo(Motion motion) {
        motion.a(this.f1709j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(double d2, int[] iArr, double[] dArr, float[] fArr, double[] dArr2, float[] fArr2) {
        float f2;
        float f3 = this.f1704e;
        float f4 = this.f1705f;
        float f5 = this.f1706g;
        float f6 = this.f1707h;
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
        Motion motion = this.f1713n;
        if (motion != null) {
            float[] fArr3 = new float[2];
            float[] fArr4 = new float[2];
            motion.getCenter(d2, fArr3, fArr4);
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
        CustomVariable customVariable = (CustomVariable) this.f1714o.get(str);
        int i3 = 0;
        if (customVariable == null) {
            return 0;
        }
        if (customVariable.numberOfInterpolatedValues() == 1) {
            dArr[i2] = customVariable.getValueToInterpolate();
            return 1;
        }
        int numberOfInterpolatedValues = customVariable.numberOfInterpolatedValues();
        float[] fArr = new float[numberOfInterpolatedValues];
        customVariable.getValuesToInterpolate(fArr);
        while (i3 < numberOfInterpolatedValues) {
            dArr[i2] = fArr[i3];
            i3++;
            i2++;
        }
        return numberOfInterpolatedValues;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f(String str) {
        CustomVariable customVariable = (CustomVariable) this.f1714o.get(str);
        if (customVariable == null) {
            return 0;
        }
        return customVariable.numberOfInterpolatedValues();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(int[] iArr, double[] dArr, float[] fArr, int i2) {
        float f2 = this.f1704e;
        float f3 = this.f1705f;
        float f4 = this.f1706g;
        float f5 = this.f1707h;
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
        Motion motion = this.f1713n;
        if (motion != null) {
            float centerX = motion.getCenterX();
            double d2 = f2;
            double d3 = f3;
            f3 = (float) ((this.f1713n.getCenterY() - (d2 * Math.cos(d3))) - (f5 / 2.0f));
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
        return this.f1714o.containsKey(str);
    }

    void i(MotionKeyPosition motionKeyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f2 = motionKeyPosition.mFramePosition / 100.0f;
        this.f1702c = f2;
        this.f1701b = motionKeyPosition.mDrawPath;
        float f3 = Float.isNaN(motionKeyPosition.mPercentWidth) ? f2 : motionKeyPosition.mPercentWidth;
        float f4 = Float.isNaN(motionKeyPosition.mPercentHeight) ? f2 : motionKeyPosition.mPercentHeight;
        float f5 = motionPaths2.f1706g;
        float f6 = motionPaths.f1706g;
        float f7 = motionPaths2.f1707h;
        float f8 = motionPaths.f1707h;
        this.f1703d = this.f1702c;
        float f9 = motionPaths.f1704e;
        float f10 = motionPaths.f1705f;
        float f11 = (motionPaths2.f1704e + (f5 / 2.0f)) - ((f6 / 2.0f) + f9);
        float f12 = (motionPaths2.f1705f + (f7 / 2.0f)) - (f10 + (f8 / 2.0f));
        float f13 = (f5 - f6) * f3;
        float f14 = f13 / 2.0f;
        this.f1704e = (int) ((f9 + (f11 * f2)) - f14);
        float f15 = (f7 - f8) * f4;
        float f16 = f15 / 2.0f;
        this.f1705f = (int) ((f10 + (f12 * f2)) - f16);
        this.f1706g = (int) (f6 + f13);
        this.f1707h = (int) (f8 + f15);
        float f17 = Float.isNaN(motionKeyPosition.mPercentX) ? f2 : motionKeyPosition.mPercentX;
        float f18 = Float.isNaN(motionKeyPosition.mAltPercentY) ? 0.0f : motionKeyPosition.mAltPercentY;
        if (!Float.isNaN(motionKeyPosition.mPercentY)) {
            f2 = motionKeyPosition.mPercentY;
        }
        float f19 = Float.isNaN(motionKeyPosition.mAltPercentX) ? 0.0f : motionKeyPosition.mAltPercentX;
        this.f1715p = 0;
        this.f1704e = (int) (((motionPaths.f1704e + (f17 * f11)) + (f19 * f12)) - f14);
        this.f1705f = (int) (((motionPaths.f1705f + (f11 * f18)) + (f12 * f2)) - f16);
        this.f1700a = Easing.getInterpolator(motionKeyPosition.mTransitionEasing);
        this.f1710k = motionKeyPosition.mPathMotionArc;
    }

    void j(MotionKeyPosition motionKeyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f2;
        float f3;
        float f4 = motionKeyPosition.mFramePosition / 100.0f;
        this.f1702c = f4;
        this.f1701b = motionKeyPosition.mDrawPath;
        float f5 = Float.isNaN(motionKeyPosition.mPercentWidth) ? f4 : motionKeyPosition.mPercentWidth;
        float f6 = Float.isNaN(motionKeyPosition.mPercentHeight) ? f4 : motionKeyPosition.mPercentHeight;
        float f7 = motionPaths2.f1706g - motionPaths.f1706g;
        float f8 = motionPaths2.f1707h - motionPaths.f1707h;
        this.f1703d = this.f1702c;
        if (!Float.isNaN(motionKeyPosition.mPercentX)) {
            f4 = motionKeyPosition.mPercentX;
        }
        float f9 = motionPaths.f1704e;
        float f10 = motionPaths.f1706g;
        float f11 = motionPaths.f1705f;
        float f12 = motionPaths.f1707h;
        float f13 = (motionPaths2.f1704e + (motionPaths2.f1706g / 2.0f)) - ((f10 / 2.0f) + f9);
        float f14 = (motionPaths2.f1705f + (motionPaths2.f1707h / 2.0f)) - ((f12 / 2.0f) + f11);
        float f15 = f13 * f4;
        float f16 = (f7 * f5) / 2.0f;
        this.f1704e = (int) ((f9 + f15) - f16);
        float f17 = f4 * f14;
        float f18 = (f8 * f6) / 2.0f;
        this.f1705f = (int) ((f11 + f17) - f18);
        this.f1706g = (int) (f10 + f2);
        this.f1707h = (int) (f12 + f3);
        float f19 = Float.isNaN(motionKeyPosition.mPercentY) ? 0.0f : motionKeyPosition.mPercentY;
        this.f1715p = 1;
        float f20 = (int) ((motionPaths.f1704e + f15) - f16);
        this.f1704e = f20;
        float f21 = (int) ((motionPaths.f1705f + f17) - f18);
        this.f1705f = f21;
        this.f1704e = f20 + ((-f14) * f19);
        this.f1705f = f21 + (f13 * f19);
        this.f1711l = this.f1711l;
        this.f1700a = Easing.getInterpolator(motionKeyPosition.mTransitionEasing);
        this.f1710k = motionKeyPosition.mPathMotionArc;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0065, code lost:
        if (java.lang.Float.isNaN(r9.mPercentY) != false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0068, code lost:
        r7 = r9.mPercentY;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b4, code lost:
        if (java.lang.Float.isNaN(r9.mPercentY) != false) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void k(int i2, int i3, MotionKeyPosition motionKeyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f2;
        float min;
        float f3 = motionKeyPosition.mFramePosition / 100.0f;
        this.f1702c = f3;
        this.f1701b = motionKeyPosition.mDrawPath;
        this.f1715p = motionKeyPosition.mPositionType;
        float f4 = Float.isNaN(motionKeyPosition.mPercentWidth) ? f3 : motionKeyPosition.mPercentWidth;
        float f5 = Float.isNaN(motionKeyPosition.mPercentHeight) ? f3 : motionKeyPosition.mPercentHeight;
        float f6 = motionPaths2.f1706g;
        float f7 = motionPaths.f1706g;
        float f8 = motionPaths2.f1707h;
        float f9 = motionPaths.f1707h;
        this.f1703d = this.f1702c;
        this.f1706g = (int) (f7 + ((f6 - f7) * f4));
        this.f1707h = (int) (f9 + ((f8 - f9) * f5));
        int i4 = motionKeyPosition.mPositionType;
        if (i4 != 1) {
            if (i4 != 2) {
                float f10 = Float.isNaN(motionKeyPosition.mPercentX) ? f3 : motionKeyPosition.mPercentX;
                float f11 = motionPaths2.f1704e;
                float f12 = motionPaths.f1704e;
                this.f1704e = (f10 * (f11 - f12)) + f12;
            } else {
                if (Float.isNaN(motionKeyPosition.mPercentX)) {
                    float f13 = motionPaths2.f1704e;
                    float f14 = motionPaths.f1704e;
                    min = ((f13 - f14) * f3) + f14;
                } else {
                    min = Math.min(f5, f4) * motionKeyPosition.mPercentX;
                }
                this.f1704e = min;
                if (!Float.isNaN(motionKeyPosition.mPercentY)) {
                    f2 = motionKeyPosition.mPercentY;
                }
            }
            float f15 = motionPaths2.f1705f;
            float f16 = motionPaths.f1705f;
            f2 = (f3 * (f15 - f16)) + f16;
        } else {
            float f17 = Float.isNaN(motionKeyPosition.mPercentX) ? f3 : motionKeyPosition.mPercentX;
            float f18 = motionPaths2.f1704e;
            float f19 = motionPaths.f1704e;
            this.f1704e = (f17 * (f18 - f19)) + f19;
        }
        this.f1705f = f2;
        this.f1711l = motionPaths.f1711l;
        this.f1700a = Easing.getInterpolator(motionKeyPosition.mTransitionEasing);
        this.f1710k = motionKeyPosition.mPathMotionArc;
    }

    void l(int i2, int i3, MotionKeyPosition motionKeyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f2 = motionKeyPosition.mFramePosition / 100.0f;
        this.f1702c = f2;
        this.f1701b = motionKeyPosition.mDrawPath;
        float f3 = Float.isNaN(motionKeyPosition.mPercentWidth) ? f2 : motionKeyPosition.mPercentWidth;
        float f4 = Float.isNaN(motionKeyPosition.mPercentHeight) ? f2 : motionKeyPosition.mPercentHeight;
        float f5 = motionPaths2.f1706g;
        float f6 = motionPaths.f1706g;
        float f7 = motionPaths2.f1707h;
        float f8 = motionPaths.f1707h;
        this.f1703d = this.f1702c;
        float f9 = motionPaths.f1704e;
        float f10 = motionPaths.f1705f;
        float f11 = motionPaths2.f1704e + (f5 / 2.0f);
        float f12 = motionPaths2.f1705f + (f7 / 2.0f);
        float f13 = (f5 - f6) * f3;
        this.f1704e = (int) ((f9 + ((f11 - ((f6 / 2.0f) + f9)) * f2)) - (f13 / 2.0f));
        float f14 = (f7 - f8) * f4;
        this.f1705f = (int) ((f10 + ((f12 - (f10 + (f8 / 2.0f))) * f2)) - (f14 / 2.0f));
        this.f1706g = (int) (f6 + f13);
        this.f1707h = (int) (f8 + f14);
        this.f1715p = 2;
        if (!Float.isNaN(motionKeyPosition.mPercentX)) {
            this.f1704e = (int) (motionKeyPosition.mPercentX * ((int) (i2 - this.f1706g)));
        }
        if (!Float.isNaN(motionKeyPosition.mPercentY)) {
            this.f1705f = (int) (motionKeyPosition.mPercentY * ((int) (i3 - this.f1707h)));
        }
        this.f1711l = this.f1711l;
        this.f1700a = Easing.getInterpolator(motionKeyPosition.mTransitionEasing);
        this.f1710k = motionKeyPosition.mPathMotionArc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(float f2, float f3, float f4, float f5) {
        this.f1704e = f2;
        this.f1705f = f3;
        this.f1706g = f4;
        this.f1707h = f5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(float f2, MotionWidget motionWidget, int[] iArr, double[] dArr, double[] dArr2, double[] dArr3) {
        float f3;
        float f4;
        float f5 = this.f1704e;
        float f6 = this.f1705f;
        float f7 = this.f1706g;
        float f8 = this.f1707h;
        if (iArr.length != 0 && this.f1716q.length <= iArr[iArr.length - 1]) {
            int i2 = iArr[iArr.length - 1] + 1;
            this.f1716q = new double[i2];
            this.f1717r = new double[i2];
        }
        Arrays.fill(this.f1716q, Double.NaN);
        for (int i3 = 0; i3 < iArr.length; i3++) {
            this.f1716q[iArr[i3]] = dArr[i3];
            this.f1717r[iArr[i3]] = dArr2[i3];
        }
        float f9 = Float.NaN;
        int i4 = 0;
        float f10 = 0.0f;
        float f11 = 0.0f;
        float f12 = 0.0f;
        float f13 = 0.0f;
        while (true) {
            double[] dArr4 = this.f1716q;
            if (i4 >= dArr4.length) {
                break;
            }
            if (Double.isNaN(dArr4[i4]) && (dArr3 == null || dArr3[i4] == 0.0d)) {
                f4 = f9;
            } else {
                double d2 = dArr3 != null ? dArr3[i4] : 0.0d;
                if (!Double.isNaN(this.f1716q[i4])) {
                    d2 = this.f1716q[i4] + d2;
                }
                f4 = f9;
                float f14 = (float) d2;
                float f15 = (float) this.f1717r[i4];
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
        Motion motion = this.f1713n;
        if (motion != null) {
            float[] fArr = new float[2];
            float[] fArr2 = new float[2];
            motion.getCenter(f2, fArr, fArr2);
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
                dArr2[0] = sin2;
                dArr2[1] = cos2;
            }
            if (!Float.isNaN(f16)) {
                motionWidget.setRotationZ((float) (f16 + Math.toDegrees(Math.atan2(cos2, sin2))));
            }
            f5 = sin;
            f6 = cos;
        } else {
            f3 = f8;
            if (!Float.isNaN(f16)) {
                motionWidget.setRotationZ((float) (0.0f + f16 + Math.toDegrees(Math.atan2(f11 + (f13 / 2.0f), f10 + (f12 / 2.0f)))));
            }
        }
        float f21 = f5 + 0.5f;
        float f22 = f6 + 0.5f;
        motionWidget.layout((int) f21, (int) f22, (int) (f21 + f7), (int) (f22 + f3));
    }

    public void setupRelative(Motion motion, MotionPaths motionPaths) {
        double d2 = ((this.f1704e + (this.f1706g / 2.0f)) - motionPaths.f1704e) - (motionPaths.f1706g / 2.0f);
        double d3 = ((this.f1705f + (this.f1707h / 2.0f)) - motionPaths.f1705f) - (motionPaths.f1707h / 2.0f);
        this.f1713n = motion;
        this.f1704e = (float) Math.hypot(d3, d2);
        this.f1705f = (float) (Float.isNaN(this.f1712m) ? Math.atan2(d3, d2) + 1.5707963267948966d : Math.toRadians(this.f1712m));
    }
}
