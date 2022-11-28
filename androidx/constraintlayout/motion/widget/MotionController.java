package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.motion.utils.VelocityMatrix;
import androidx.constraintlayout.motion.utils.ViewOscillator;
import androidx.constraintlayout.motion.utils.ViewSpline;
import androidx.constraintlayout.motion.utils.ViewState;
import androidx.constraintlayout.motion.utils.ViewTimeCycle;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes.dex */
public class MotionController {
    private static final boolean DEBUG = false;
    public static final int DRAW_PATH_AS_CONFIGURED = 4;
    public static final int DRAW_PATH_BASIC = 1;
    public static final int DRAW_PATH_CARTESIAN = 3;
    public static final int DRAW_PATH_NONE = 0;
    public static final int DRAW_PATH_RECTANGLE = 5;
    public static final int DRAW_PATH_RELATIVE = 2;
    public static final int DRAW_PATH_SCREEN = 6;
    private static final boolean FAVOR_FIXED_SIZE_VIEWS = false;
    public static final int HORIZONTAL_PATH_X = 2;
    public static final int HORIZONTAL_PATH_Y = 3;
    private static final int INTERPOLATOR_REFERENCE_ID = -2;
    private static final int INTERPOLATOR_UNDEFINED = -3;
    public static final int PATH_PERCENT = 0;
    public static final int PATH_PERPENDICULAR = 1;
    public static final int ROTATION_LEFT = 2;
    public static final int ROTATION_RIGHT = 1;
    private static final int SPLINE_STRING = -1;
    private static final String TAG = "MotionController";
    public static final int VERTICAL_PATH_X = 4;
    public static final int VERTICAL_PATH_Y = 5;

    /* renamed from: b  reason: collision with root package name */
    View f2069b;

    /* renamed from: c  reason: collision with root package name */
    int f2070c;

    /* renamed from: g  reason: collision with root package name */
    float f2074g;

    /* renamed from: h  reason: collision with root package name */
    float f2075h;
    private CurveFit mArcSpline;
    private int[] mAttributeInterpolatorCount;
    private String[] mAttributeNames;
    private HashMap<String, ViewSpline> mAttributesMap;
    private HashMap<String, ViewOscillator> mCycleMap;
    private double[] mInterpolateData;
    private int[] mInterpolateVariables;
    private double[] mInterpolateVelocity;
    private KeyTrigger[] mKeyTriggers;
    private boolean mNoMovement;
    private int mPathMotionArc;
    private Interpolator mQuantizeMotionInterpolator;
    private float mQuantizeMotionPhase;
    private int mQuantizeMotionSteps;
    private CurveFit[] mSpline;
    private HashMap<String, ViewTimeCycle> mTimeCycleAttributesMap;
    private int mTransformPivotTarget;
    private View mTransformPivotView;

    /* renamed from: a  reason: collision with root package name */
    Rect f2068a = new Rect();
    private int mCurveFitType = -1;
    private MotionPaths mStartMotionPath = new MotionPaths();
    private MotionPaths mEndMotionPath = new MotionPaths();
    private MotionConstrainedPoint mStartPoint = new MotionConstrainedPoint();
    private MotionConstrainedPoint mEndPoint = new MotionConstrainedPoint();

    /* renamed from: d  reason: collision with root package name */
    float f2071d = Float.NaN;

    /* renamed from: e  reason: collision with root package name */
    float f2072e = 0.0f;

    /* renamed from: f  reason: collision with root package name */
    float f2073f = 1.0f;
    private int MAX_DIMENSION = 4;
    private float[] mValuesBuff = new float[4];
    private ArrayList<MotionPaths> mMotionPaths = new ArrayList<>();
    private float[] mVelocity = new float[1];
    private ArrayList<Key> mKeyList = new ArrayList<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public MotionController(View view) {
        int i2 = Key.UNSET;
        this.mPathMotionArc = i2;
        this.mTransformPivotTarget = i2;
        this.mTransformPivotView = null;
        this.mQuantizeMotionSteps = i2;
        this.mQuantizeMotionPhase = Float.NaN;
        this.mQuantizeMotionInterpolator = null;
        this.mNoMovement = false;
        setView(view);
    }

    private float getAdjustedPosition(float f2, float[] fArr) {
        float f3 = 0.0f;
        if (fArr != null) {
            fArr[0] = 1.0f;
        } else {
            float f4 = this.f2073f;
            if (f4 != 1.0d) {
                float f5 = this.f2072e;
                if (f2 < f5) {
                    f2 = 0.0f;
                }
                if (f2 > f5 && f2 < 1.0d) {
                    f2 = Math.min((f2 - f5) * f4, 1.0f);
                }
            }
        }
        Easing easing = this.mStartMotionPath.f2131a;
        float f6 = Float.NaN;
        Iterator<MotionPaths> it = this.mMotionPaths.iterator();
        while (it.hasNext()) {
            MotionPaths next = it.next();
            Easing easing2 = next.f2131a;
            if (easing2 != null) {
                float f7 = next.f2133c;
                if (f7 < f2) {
                    easing = easing2;
                    f3 = f7;
                } else if (Float.isNaN(f6)) {
                    f6 = next.f2133c;
                }
            }
        }
        if (easing != null) {
            float f8 = (Float.isNaN(f6) ? 1.0f : f6) - f3;
            double d2 = (f2 - f3) / f8;
            f2 = (((float) easing.get(d2)) * f8) + f3;
            if (fArr != null) {
                fArr[0] = (float) easing.getDiff(d2);
            }
        }
        return f2;
    }

    private static Interpolator getInterpolator(Context context, int i2, String str, int i3) {
        if (i2 != -2) {
            if (i2 == -1) {
                final Easing interpolator = Easing.getInterpolator(str);
                return new Interpolator() { // from class: androidx.constraintlayout.motion.widget.MotionController.1
                    @Override // android.animation.TimeInterpolator
                    public float getInterpolation(float f2) {
                        return (float) Easing.this.get(f2);
                    }
                };
            } else if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 != 4) {
                            if (i2 != 5) {
                                return null;
                            }
                            return new OvershootInterpolator();
                        }
                        return new BounceInterpolator();
                    }
                    return new DecelerateInterpolator();
                }
                return new AccelerateInterpolator();
            } else {
                return new AccelerateDecelerateInterpolator();
            }
        }
        return AnimationUtils.loadInterpolator(context, i3);
    }

    private float getPreCycleDistance() {
        char c2;
        float f2;
        float f3;
        float[] fArr = new float[2];
        float f4 = 1.0f / 99;
        double d2 = 0.0d;
        double d3 = 0.0d;
        float f5 = 0.0f;
        int i2 = 0;
        while (i2 < 100) {
            float f6 = i2 * f4;
            double d4 = f6;
            Easing easing = this.mStartMotionPath.f2131a;
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            float f7 = Float.NaN;
            float f8 = 0.0f;
            while (it.hasNext()) {
                MotionPaths next = it.next();
                Easing easing2 = next.f2131a;
                if (easing2 != null) {
                    float f9 = next.f2133c;
                    if (f9 < f6) {
                        easing = easing2;
                        f8 = f9;
                    } else if (Float.isNaN(f7)) {
                        f7 = next.f2133c;
                    }
                }
            }
            if (easing != null) {
                if (Float.isNaN(f7)) {
                    f7 = 1.0f;
                }
                d4 = (((float) easing.get((f6 - f8) / f3)) * (f7 - f8)) + f8;
            }
            this.mSpline[0].getPos(d4, this.mInterpolateData);
            float f10 = f5;
            int i3 = i2;
            this.mStartMotionPath.c(d4, this.mInterpolateVariables, this.mInterpolateData, fArr, 0);
            if (i3 > 0) {
                c2 = 0;
                f2 = (float) (f10 + Math.hypot(d3 - fArr[1], d2 - fArr[0]));
            } else {
                c2 = 0;
                f2 = f10;
            }
            d2 = fArr[c2];
            i2 = i3 + 1;
            f5 = f2;
            d3 = fArr[1];
        }
        return f5;
    }

    private void insertKey(MotionPaths motionPaths) {
        int binarySearch = Collections.binarySearch(this.mMotionPaths, motionPaths);
        if (binarySearch == 0) {
            Log.e(TAG, " KeyPath position \"" + motionPaths.f2134d + "\" outside of range");
        }
        this.mMotionPaths.add((-binarySearch) - 1, motionPaths);
    }

    private void readView(MotionPaths motionPaths) {
        motionPaths.m((int) this.f2069b.getX(), (int) this.f2069b.getY(), this.f2069b.getWidth(), this.f2069b.getHeight());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ArrayList arrayList) {
        this.mKeyList.addAll(arrayList);
    }

    public void addKey(Key key) {
        this.mKeyList.add(key);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b(float[] fArr, int[] iArr) {
        if (fArr != null) {
            double[] timePoints = this.mSpline[0].getTimePoints();
            if (iArr != null) {
                Iterator<MotionPaths> it = this.mMotionPaths.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    iArr[i2] = it.next().f2146p;
                    i2++;
                }
            }
            int i3 = 0;
            for (int i4 = 0; i4 < timePoints.length; i4++) {
                this.mSpline[0].getPos(timePoints[i4], this.mInterpolateData);
                this.mStartMotionPath.c(timePoints[i4], this.mInterpolateVariables, this.mInterpolateData, fArr, i3);
                i3 += 2;
            }
            return i3 / 2;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(float[] fArr, int i2) {
        double d2;
        float f2;
        float f3 = 1.0f;
        float f4 = 1.0f / (i2 - 1);
        HashMap<String, ViewSpline> hashMap = this.mAttributesMap;
        ViewSpline viewSpline = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, ViewSpline> hashMap2 = this.mAttributesMap;
        ViewSpline viewSpline2 = hashMap2 == null ? null : hashMap2.get("translationY");
        HashMap<String, ViewOscillator> hashMap3 = this.mCycleMap;
        ViewOscillator viewOscillator = hashMap3 == null ? null : hashMap3.get("translationX");
        HashMap<String, ViewOscillator> hashMap4 = this.mCycleMap;
        ViewOscillator viewOscillator2 = hashMap4 != null ? hashMap4.get("translationY") : null;
        int i3 = 0;
        while (i3 < i2) {
            float f5 = i3 * f4;
            float f6 = this.f2073f;
            if (f6 != f3) {
                float f7 = this.f2072e;
                if (f5 < f7) {
                    f5 = 0.0f;
                }
                if (f5 > f7 && f5 < 1.0d) {
                    f5 = Math.min((f5 - f7) * f6, f3);
                }
            }
            float f8 = f5;
            double d3 = f8;
            Easing easing = this.mStartMotionPath.f2131a;
            float f9 = Float.NaN;
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            float f10 = 0.0f;
            while (it.hasNext()) {
                MotionPaths next = it.next();
                Easing easing2 = next.f2131a;
                double d4 = d3;
                if (easing2 != null) {
                    float f11 = next.f2133c;
                    if (f11 < f8) {
                        f10 = f11;
                        easing = easing2;
                    } else if (Float.isNaN(f9)) {
                        f9 = next.f2133c;
                    }
                }
                d3 = d4;
            }
            double d5 = d3;
            if (easing != null) {
                if (Float.isNaN(f9)) {
                    f9 = 1.0f;
                }
                d2 = (((float) easing.get((f8 - f10) / f2)) * (f9 - f10)) + f10;
            } else {
                d2 = d5;
            }
            this.mSpline[0].getPos(d2, this.mInterpolateData);
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr = this.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos(d2, dArr);
                }
            }
            int i4 = i3 * 2;
            int i5 = i3;
            this.mStartMotionPath.c(d2, this.mInterpolateVariables, this.mInterpolateData, fArr, i4);
            if (viewOscillator != null) {
                fArr[i4] = fArr[i4] + viewOscillator.get(f8);
            } else if (viewSpline != null) {
                fArr[i4] = fArr[i4] + viewSpline.get(f8);
            }
            if (viewOscillator2 != null) {
                int i6 = i4 + 1;
                fArr[i6] = fArr[i6] + viewOscillator2.get(f8);
            } else if (viewSpline2 != null) {
                int i7 = i4 + 1;
                fArr[i7] = fArr[i7] + viewSpline2.get(f8);
            }
            i3 = i5 + 1;
            f3 = 1.0f;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(float f2, float[] fArr, int i2) {
        this.mSpline[0].getPos(getAdjustedPosition(f2, null), this.mInterpolateData);
        this.mStartMotionPath.g(this.mInterpolateVariables, this.mInterpolateData, fArr, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(float[] fArr, int i2) {
        float f2 = 1.0f / (i2 - 1);
        for (int i3 = 0; i3 < i2; i3++) {
            this.mSpline[0].getPos(getAdjustedPosition(i3 * f2, null), this.mInterpolateData);
            this.mStartMotionPath.g(this.mInterpolateVariables, this.mInterpolateData, fArr, i3 * 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(boolean z) {
        if (!"button".equals(Debug.getName(this.f2069b)) || this.mKeyTriggers == null) {
            return;
        }
        int i2 = 0;
        while (true) {
            KeyTrigger[] keyTriggerArr = this.mKeyTriggers;
            if (i2 >= keyTriggerArr.length) {
                return;
            }
            keyTriggerArr[i2].conditionallyFire(z ? -100.0f : 100.0f, this.f2069b);
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g(String str, float[] fArr, int i2) {
        ViewSpline viewSpline = this.mAttributesMap.get(str);
        if (viewSpline == null) {
            return -1;
        }
        for (int i3 = 0; i3 < fArr.length; i3++) {
            fArr[i3] = viewSpline.get(i3 / (fArr.length - 1));
        }
        return fArr.length;
    }

    public int getAnimateRelativeTo() {
        return this.mStartMotionPath.f2142l;
    }

    public void getCenter(double d2, float[] fArr, float[] fArr2) {
        double[] dArr = new double[4];
        double[] dArr2 = new double[4];
        this.mSpline[0].getPos(d2, dArr);
        this.mSpline[0].getSlope(d2, dArr2);
        Arrays.fill(fArr2, 0.0f);
        this.mStartMotionPath.d(d2, this.mInterpolateVariables, dArr, fArr, dArr2, fArr2);
    }

    public float getCenterX() {
        return this.f2074g;
    }

    public float getCenterY() {
        return this.f2075h;
    }

    public int getDrawPath() {
        int i2 = this.mStartMotionPath.f2132b;
        Iterator<MotionPaths> it = this.mMotionPaths.iterator();
        while (it.hasNext()) {
            i2 = Math.max(i2, it.next().f2132b);
        }
        return Math.max(i2, this.mEndMotionPath.f2132b);
    }

    public float getFinalHeight() {
        return this.mEndMotionPath.f2138h;
    }

    public float getFinalWidth() {
        return this.mEndMotionPath.f2137g;
    }

    public float getFinalX() {
        return this.mEndMotionPath.f2135e;
    }

    public float getFinalY() {
        return this.mEndMotionPath.f2136f;
    }

    public int getKeyFrameInfo(int i2, int[] iArr) {
        float[] fArr = new float[2];
        Iterator<Key> it = this.mKeyList.iterator();
        int i3 = 0;
        int i4 = 0;
        while (it.hasNext()) {
            Key next = it.next();
            int i5 = next.f2044d;
            if (i5 == i2 || i2 != -1) {
                iArr[i4] = 0;
                int i6 = i4 + 1;
                iArr[i6] = i5;
                int i7 = i6 + 1;
                int i8 = next.f2041a;
                iArr[i7] = i8;
                double d2 = i8 / 100.0f;
                this.mSpline[0].getPos(d2, this.mInterpolateData);
                this.mStartMotionPath.c(d2, this.mInterpolateVariables, this.mInterpolateData, fArr, 0);
                int i9 = i7 + 1;
                iArr[i9] = Float.floatToIntBits(fArr[0]);
                int i10 = i9 + 1;
                iArr[i10] = Float.floatToIntBits(fArr[1]);
                if (next instanceof KeyPosition) {
                    KeyPosition keyPosition = (KeyPosition) next;
                    int i11 = i10 + 1;
                    iArr[i11] = keyPosition.f2056p;
                    int i12 = i11 + 1;
                    iArr[i12] = Float.floatToIntBits(keyPosition.f2052l);
                    i10 = i12 + 1;
                    iArr[i10] = Float.floatToIntBits(keyPosition.f2053m);
                }
                int i13 = i10 + 1;
                iArr[i4] = i13 - i4;
                i3++;
                i4 = i13;
            }
        }
        return i3;
    }

    public int getKeyFramePositions(int[] iArr, float[] fArr) {
        Iterator<Key> it = this.mKeyList.iterator();
        int i2 = 0;
        int i3 = 0;
        while (it.hasNext()) {
            Key next = it.next();
            int i4 = next.f2041a;
            iArr[i2] = (next.f2044d * 1000) + i4;
            double d2 = i4 / 100.0f;
            this.mSpline[0].getPos(d2, this.mInterpolateData);
            this.mStartMotionPath.c(d2, this.mInterpolateVariables, this.mInterpolateData, fArr, i3);
            i3 += 2;
            i2++;
        }
        return i2;
    }

    public float getStartHeight() {
        return this.mStartMotionPath.f2138h;
    }

    public float getStartWidth() {
        return this.mStartMotionPath.f2137g;
    }

    public float getStartX() {
        return this.mStartMotionPath.f2135e;
    }

    public float getStartY() {
        return this.mStartMotionPath.f2136f;
    }

    public int getTransformPivotTarget() {
        return this.mTransformPivotTarget;
    }

    public View getView() {
        return this.f2069b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(float f2, float f3, float f4, float[] fArr) {
        double[] dArr;
        float adjustedPosition = getAdjustedPosition(f2, this.mVelocity);
        CurveFit[] curveFitArr = this.mSpline;
        int i2 = 0;
        if (curveFitArr == null) {
            MotionPaths motionPaths = this.mEndMotionPath;
            float f5 = motionPaths.f2135e;
            MotionPaths motionPaths2 = this.mStartMotionPath;
            float f6 = f5 - motionPaths2.f2135e;
            float f7 = motionPaths.f2136f - motionPaths2.f2136f;
            float f8 = (motionPaths.f2137g - motionPaths2.f2137g) + f6;
            float f9 = (motionPaths.f2138h - motionPaths2.f2138h) + f7;
            fArr[0] = (f6 * (1.0f - f3)) + (f8 * f3);
            fArr[1] = (f7 * (1.0f - f4)) + (f9 * f4);
            return;
        }
        double d2 = adjustedPosition;
        curveFitArr[0].getSlope(d2, this.mInterpolateVelocity);
        this.mSpline[0].getPos(d2, this.mInterpolateData);
        float f10 = this.mVelocity[0];
        while (true) {
            dArr = this.mInterpolateVelocity;
            if (i2 >= dArr.length) {
                break;
            }
            dArr[i2] = dArr[i2] * f10;
            i2++;
        }
        CurveFit curveFit = this.mArcSpline;
        if (curveFit == null) {
            this.mStartMotionPath.n(f3, f4, fArr, this.mInterpolateVariables, dArr, this.mInterpolateData);
            return;
        }
        double[] dArr2 = this.mInterpolateData;
        if (dArr2.length > 0) {
            curveFit.getPos(d2, dArr2);
            this.mArcSpline.getSlope(d2, this.mInterpolateVelocity);
            this.mStartMotionPath.n(f3, f4, fArr, this.mInterpolateVariables, this.mInterpolateVelocity, this.mInterpolateData);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MotionPaths i(int i2) {
        return this.mMotionPaths.get(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float j(int i2, float f2, float f3) {
        MotionPaths motionPaths = this.mEndMotionPath;
        float f4 = motionPaths.f2135e;
        MotionPaths motionPaths2 = this.mStartMotionPath;
        float f5 = motionPaths2.f2135e;
        float f6 = f4 - f5;
        float f7 = motionPaths.f2136f;
        float f8 = motionPaths2.f2136f;
        float f9 = f7 - f8;
        float f10 = f5 + (motionPaths2.f2137g / 2.0f);
        float f11 = f8 + (motionPaths2.f2138h / 2.0f);
        float hypot = (float) Math.hypot(f6, f9);
        if (hypot < 1.0E-7d) {
            return Float.NaN;
        }
        float f12 = f2 - f10;
        float f13 = f3 - f11;
        if (((float) Math.hypot(f12, f13)) == 0.0f) {
            return 0.0f;
        }
        float f14 = (f12 * f6) + (f13 * f9);
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            if (i2 != 5) {
                                return 0.0f;
                            }
                            return f13 / f9;
                        }
                        return f12 / f9;
                    }
                    return f13 / f6;
                }
                return f12 / f6;
            }
            return (float) Math.sqrt((hypot * hypot) - (f14 * f14));
        }
        return f14 / hypot;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double[] k(double d2) {
        this.mSpline[0].getPos(d2, this.mInterpolateData);
        CurveFit curveFit = this.mArcSpline;
        if (curveFit != null) {
            double[] dArr = this.mInterpolateData;
            if (dArr.length > 0) {
                curveFit.getPos(d2, dArr);
            }
        }
        return this.mInterpolateData;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeyPositionBase l(int i2, int i3, float f2, float f3) {
        RectF rectF = new RectF();
        MotionPaths motionPaths = this.mStartMotionPath;
        float f4 = motionPaths.f2135e;
        rectF.left = f4;
        float f5 = motionPaths.f2136f;
        rectF.top = f5;
        rectF.right = f4 + motionPaths.f2137g;
        rectF.bottom = f5 + motionPaths.f2138h;
        RectF rectF2 = new RectF();
        MotionPaths motionPaths2 = this.mEndMotionPath;
        float f6 = motionPaths2.f2135e;
        rectF2.left = f6;
        float f7 = motionPaths2.f2136f;
        rectF2.top = f7;
        rectF2.right = f6 + motionPaths2.f2137g;
        rectF2.bottom = f7 + motionPaths2.f2138h;
        Iterator<Key> it = this.mKeyList.iterator();
        while (it.hasNext()) {
            Key next = it.next();
            if (next instanceof KeyPositionBase) {
                KeyPositionBase keyPositionBase = (KeyPositionBase) next;
                if (keyPositionBase.intersects(i2, i3, rectF, rectF2, f2, f3)) {
                    return keyPositionBase;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(float f2, int i2, int i3, float f3, float f4, float[] fArr) {
        float adjustedPosition = getAdjustedPosition(f2, this.mVelocity);
        HashMap<String, ViewSpline> hashMap = this.mAttributesMap;
        ViewSpline viewSpline = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, ViewSpline> hashMap2 = this.mAttributesMap;
        ViewSpline viewSpline2 = hashMap2 == null ? null : hashMap2.get("translationY");
        HashMap<String, ViewSpline> hashMap3 = this.mAttributesMap;
        ViewSpline viewSpline3 = hashMap3 == null ? null : hashMap3.get(Key.ROTATION);
        HashMap<String, ViewSpline> hashMap4 = this.mAttributesMap;
        ViewSpline viewSpline4 = hashMap4 == null ? null : hashMap4.get("scaleX");
        HashMap<String, ViewSpline> hashMap5 = this.mAttributesMap;
        ViewSpline viewSpline5 = hashMap5 == null ? null : hashMap5.get("scaleY");
        HashMap<String, ViewOscillator> hashMap6 = this.mCycleMap;
        ViewOscillator viewOscillator = hashMap6 == null ? null : hashMap6.get("translationX");
        HashMap<String, ViewOscillator> hashMap7 = this.mCycleMap;
        ViewOscillator viewOscillator2 = hashMap7 == null ? null : hashMap7.get("translationY");
        HashMap<String, ViewOscillator> hashMap8 = this.mCycleMap;
        ViewOscillator viewOscillator3 = hashMap8 == null ? null : hashMap8.get(Key.ROTATION);
        HashMap<String, ViewOscillator> hashMap9 = this.mCycleMap;
        ViewOscillator viewOscillator4 = hashMap9 == null ? null : hashMap9.get("scaleX");
        HashMap<String, ViewOscillator> hashMap10 = this.mCycleMap;
        ViewOscillator viewOscillator5 = hashMap10 != null ? hashMap10.get("scaleY") : null;
        VelocityMatrix velocityMatrix = new VelocityMatrix();
        velocityMatrix.clear();
        velocityMatrix.setRotationVelocity(viewSpline3, adjustedPosition);
        velocityMatrix.setTranslationVelocity(viewSpline, viewSpline2, adjustedPosition);
        velocityMatrix.setScaleVelocity(viewSpline4, viewSpline5, adjustedPosition);
        velocityMatrix.setRotationVelocity(viewOscillator3, adjustedPosition);
        velocityMatrix.setTranslationVelocity(viewOscillator, viewOscillator2, adjustedPosition);
        velocityMatrix.setScaleVelocity(viewOscillator4, viewOscillator5, adjustedPosition);
        CurveFit curveFit = this.mArcSpline;
        if (curveFit != null) {
            double[] dArr = this.mInterpolateData;
            if (dArr.length > 0) {
                double d2 = adjustedPosition;
                curveFit.getPos(d2, dArr);
                this.mArcSpline.getSlope(d2, this.mInterpolateVelocity);
                this.mStartMotionPath.n(f3, f4, fArr, this.mInterpolateVariables, this.mInterpolateVelocity, this.mInterpolateData);
            }
            velocityMatrix.applyTransform(f3, f4, i2, i3, fArr);
            return;
        }
        int i4 = 0;
        if (this.mSpline == null) {
            MotionPaths motionPaths = this.mEndMotionPath;
            float f5 = motionPaths.f2135e;
            MotionPaths motionPaths2 = this.mStartMotionPath;
            float f6 = f5 - motionPaths2.f2135e;
            float f7 = motionPaths.f2136f - motionPaths2.f2136f;
            ViewOscillator viewOscillator6 = viewOscillator4;
            float f8 = (motionPaths.f2138h - motionPaths2.f2138h) + f7;
            fArr[0] = (f6 * (1.0f - f3)) + (((motionPaths.f2137g - motionPaths2.f2137g) + f6) * f3);
            fArr[1] = (f7 * (1.0f - f4)) + (f8 * f4);
            velocityMatrix.clear();
            velocityMatrix.setRotationVelocity(viewSpline3, adjustedPosition);
            velocityMatrix.setTranslationVelocity(viewSpline, viewSpline2, adjustedPosition);
            velocityMatrix.setScaleVelocity(viewSpline4, viewSpline5, adjustedPosition);
            velocityMatrix.setRotationVelocity(viewOscillator3, adjustedPosition);
            velocityMatrix.setTranslationVelocity(viewOscillator, viewOscillator2, adjustedPosition);
            velocityMatrix.setScaleVelocity(viewOscillator6, viewOscillator5, adjustedPosition);
            velocityMatrix.applyTransform(f3, f4, i2, i3, fArr);
            return;
        }
        double adjustedPosition2 = getAdjustedPosition(adjustedPosition, this.mVelocity);
        this.mSpline[0].getSlope(adjustedPosition2, this.mInterpolateVelocity);
        this.mSpline[0].getPos(adjustedPosition2, this.mInterpolateData);
        float f9 = this.mVelocity[0];
        while (true) {
            double[] dArr2 = this.mInterpolateVelocity;
            if (i4 >= dArr2.length) {
                this.mStartMotionPath.n(f3, f4, fArr, this.mInterpolateVariables, dArr2, this.mInterpolateData);
                velocityMatrix.applyTransform(f3, f4, i2, i3, fArr);
                return;
            }
            dArr2[i4] = dArr2[i4] * f9;
            i4++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean n(View view, float f2, long j2, KeyCache keyCache) {
        ViewTimeCycle.PathRotate pathRotate;
        boolean z;
        int i2;
        double d2;
        View view2;
        float adjustedPosition = getAdjustedPosition(f2, null);
        int i3 = this.mQuantizeMotionSteps;
        if (i3 != Key.UNSET) {
            float f3 = 1.0f / i3;
            float floor = ((float) Math.floor(adjustedPosition / f3)) * f3;
            float f4 = (adjustedPosition % f3) / f3;
            if (!Float.isNaN(this.mQuantizeMotionPhase)) {
                f4 = (f4 + this.mQuantizeMotionPhase) % 1.0f;
            }
            Interpolator interpolator = this.mQuantizeMotionInterpolator;
            adjustedPosition = ((interpolator != null ? interpolator.getInterpolation(f4) : ((double) f4) > 0.5d ? 1.0f : 0.0f) * f3) + floor;
        }
        float f5 = adjustedPosition;
        HashMap<String, ViewSpline> hashMap = this.mAttributesMap;
        if (hashMap != null) {
            for (ViewSpline viewSpline : hashMap.values()) {
                viewSpline.setProperty(view, f5);
            }
        }
        HashMap<String, ViewTimeCycle> hashMap2 = this.mTimeCycleAttributesMap;
        if (hashMap2 != null) {
            pathRotate = null;
            boolean z2 = false;
            for (ViewTimeCycle viewTimeCycle : hashMap2.values()) {
                if (viewTimeCycle instanceof ViewTimeCycle.PathRotate) {
                    pathRotate = (ViewTimeCycle.PathRotate) viewTimeCycle;
                } else {
                    z2 |= viewTimeCycle.setProperty(view, f5, j2, keyCache);
                }
            }
            z = z2;
        } else {
            pathRotate = null;
            z = false;
        }
        CurveFit[] curveFitArr = this.mSpline;
        if (curveFitArr != null) {
            double d3 = f5;
            curveFitArr[0].getPos(d3, this.mInterpolateData);
            this.mSpline[0].getSlope(d3, this.mInterpolateVelocity);
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr = this.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos(d3, dArr);
                    this.mArcSpline.getSlope(d3, this.mInterpolateVelocity);
                }
            }
            if (this.mNoMovement) {
                d2 = d3;
            } else {
                d2 = d3;
                this.mStartMotionPath.o(f5, view, this.mInterpolateVariables, this.mInterpolateData, this.mInterpolateVelocity, null);
            }
            if (this.mTransformPivotTarget != Key.UNSET) {
                if (this.mTransformPivotView == null) {
                    this.mTransformPivotView = ((View) view.getParent()).findViewById(this.mTransformPivotTarget);
                }
                if (this.mTransformPivotView != null) {
                    float top = (view2.getTop() + this.mTransformPivotView.getBottom()) / 2.0f;
                    float left = (this.mTransformPivotView.getLeft() + this.mTransformPivotView.getRight()) / 2.0f;
                    if (view.getRight() - view.getLeft() > 0 && view.getBottom() - view.getTop() > 0) {
                        view.setPivotX(left - view.getLeft());
                        view.setPivotY(top - view.getTop());
                    }
                }
            }
            HashMap<String, ViewSpline> hashMap3 = this.mAttributesMap;
            if (hashMap3 != null) {
                for (ViewSpline viewSpline2 : hashMap3.values()) {
                    if (viewSpline2 instanceof ViewSpline.PathRotate) {
                        double[] dArr2 = this.mInterpolateVelocity;
                        if (dArr2.length > 1) {
                            ((ViewSpline.PathRotate) viewSpline2).setPathRotate(view, f5, dArr2[0], dArr2[1]);
                        }
                    }
                }
            }
            if (pathRotate != null) {
                double[] dArr3 = this.mInterpolateVelocity;
                i2 = 1;
                z |= pathRotate.setPathRotate(view, keyCache, f5, j2, dArr3[0], dArr3[1]);
            } else {
                i2 = 1;
            }
            int i4 = i2;
            while (true) {
                CurveFit[] curveFitArr2 = this.mSpline;
                if (i4 >= curveFitArr2.length) {
                    break;
                }
                curveFitArr2[i4].getPos(d2, this.mValuesBuff);
                ((ConstraintAttribute) this.mStartMotionPath.f2145o.get(this.mAttributeNames[i4 - 1])).setInterpolatedValue(view, this.mValuesBuff);
                i4++;
            }
            MotionConstrainedPoint motionConstrainedPoint = this.mStartPoint;
            if (motionConstrainedPoint.f2065a == 0) {
                if (f5 > 0.0f) {
                    if (f5 >= 1.0f) {
                        motionConstrainedPoint = this.mEndPoint;
                    } else if (this.mEndPoint.f2066b != motionConstrainedPoint.f2066b) {
                        view.setVisibility(0);
                    }
                }
                view.setVisibility(motionConstrainedPoint.f2066b);
            }
            if (this.mKeyTriggers != null) {
                int i5 = 0;
                while (true) {
                    KeyTrigger[] keyTriggerArr = this.mKeyTriggers;
                    if (i5 >= keyTriggerArr.length) {
                        break;
                    }
                    keyTriggerArr[i5].conditionallyFire(f5, view);
                    i5++;
                }
            }
        } else {
            i2 = 1;
            MotionPaths motionPaths = this.mStartMotionPath;
            float f6 = motionPaths.f2135e;
            MotionPaths motionPaths2 = this.mEndMotionPath;
            float f7 = f6 + ((motionPaths2.f2135e - f6) * f5);
            float f8 = motionPaths.f2136f;
            float f9 = f8 + ((motionPaths2.f2136f - f8) * f5);
            float f10 = motionPaths.f2137g;
            float f11 = motionPaths2.f2137g;
            float f12 = motionPaths.f2138h;
            float f13 = motionPaths2.f2138h;
            float f14 = f7 + 0.5f;
            int i6 = (int) f14;
            float f15 = f9 + 0.5f;
            int i7 = (int) f15;
            int i8 = (int) (f14 + ((f11 - f10) * f5) + f10);
            int i9 = (int) (f15 + ((f13 - f12) * f5) + f12);
            int i10 = i8 - i6;
            int i11 = i9 - i7;
            if (f11 != f10 || f13 != f12) {
                view.measure(View.MeasureSpec.makeMeasureSpec(i10, 1073741824), View.MeasureSpec.makeMeasureSpec(i11, 1073741824));
            }
            view.layout(i6, i7, i8, i9);
        }
        HashMap<String, ViewOscillator> hashMap4 = this.mCycleMap;
        if (hashMap4 != null) {
            for (ViewOscillator viewOscillator : hashMap4.values()) {
                if (viewOscillator instanceof ViewOscillator.PathRotateSet) {
                    double[] dArr4 = this.mInterpolateVelocity;
                    ((ViewOscillator.PathRotateSet) viewOscillator).setPathRotate(view, f5, dArr4[0], dArr4[i2]);
                } else {
                    viewOscillator.setProperty(view, f5);
                }
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(View view, KeyPositionBase keyPositionBase, float f2, float f3, String[] strArr, float[] fArr) {
        RectF rectF = new RectF();
        MotionPaths motionPaths = this.mStartMotionPath;
        float f4 = motionPaths.f2135e;
        rectF.left = f4;
        float f5 = motionPaths.f2136f;
        rectF.top = f5;
        rectF.right = f4 + motionPaths.f2137g;
        rectF.bottom = f5 + motionPaths.f2138h;
        RectF rectF2 = new RectF();
        MotionPaths motionPaths2 = this.mEndMotionPath;
        float f6 = motionPaths2.f2135e;
        rectF2.left = f6;
        float f7 = motionPaths2.f2136f;
        rectF2.top = f7;
        rectF2.right = f6 + motionPaths2.f2137g;
        rectF2.bottom = f7 + motionPaths2.f2138h;
        keyPositionBase.positionAttributes(view, rectF, rectF2, f2, f3, strArr, fArr);
    }

    void p(Rect rect, Rect rect2, int i2, int i3, int i4) {
        int i5;
        int width;
        int i6;
        int i7;
        int i8;
        if (i2 != 1) {
            if (i2 == 2) {
                i6 = rect.left + rect.right;
                i7 = rect.top;
                i8 = rect.bottom;
            } else if (i2 == 3) {
                i5 = rect.left + rect.right;
                width = ((rect.height() / 2) + rect.top) - (i5 / 2);
            } else if (i2 != 4) {
                return;
            } else {
                i6 = rect.left + rect.right;
                i7 = rect.bottom;
                i8 = rect.top;
            }
            rect2.left = i3 - (((i7 + i8) + rect.width()) / 2);
            rect2.top = (i6 - rect.height()) / 2;
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
        }
        i5 = rect.left + rect.right;
        width = ((rect.top + rect.bottom) - rect.width()) / 2;
        rect2.left = width;
        rect2.top = i4 - ((i5 + rect.height()) / 2);
        rect2.right = rect2.left + rect.width();
        rect2.bottom = rect2.top + rect.height();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q(View view) {
        MotionPaths motionPaths = this.mStartMotionPath;
        motionPaths.f2133c = 0.0f;
        motionPaths.f2134d = 0.0f;
        this.mNoMovement = true;
        motionPaths.m(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.mEndMotionPath.m(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.mStartPoint.setState(view);
        this.mEndPoint.setState(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r(Rect rect, ConstraintSet constraintSet, int i2, int i3) {
        int i4 = constraintSet.mRotate;
        if (i4 != 0) {
            p(rect, this.f2068a, i4, i2, i3);
            rect = this.f2068a;
        }
        MotionPaths motionPaths = this.mEndMotionPath;
        motionPaths.f2133c = 1.0f;
        motionPaths.f2134d = 1.0f;
        readView(motionPaths);
        this.mEndMotionPath.m(rect.left, rect.top, rect.width(), rect.height());
        this.mEndMotionPath.applyParameters(constraintSet.getParameters(this.f2070c));
        this.mEndPoint.setState(rect, constraintSet, i4, this.f2070c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s(View view) {
        MotionPaths motionPaths = this.mStartMotionPath;
        motionPaths.f2133c = 0.0f;
        motionPaths.f2134d = 0.0f;
        motionPaths.m(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.mStartPoint.setState(view);
    }

    public void setDrawPath(int i2) {
        this.mStartMotionPath.f2132b = i2;
    }

    public void setPathMotionArc(int i2) {
        this.mPathMotionArc = i2;
    }

    public void setStartState(ViewState viewState, View view, int i2, int i3, int i4) {
        int height;
        MotionPaths motionPaths = this.mStartMotionPath;
        motionPaths.f2133c = 0.0f;
        motionPaths.f2134d = 0.0f;
        Rect rect = new Rect();
        if (i2 != 1) {
            if (i2 == 2) {
                int i5 = viewState.left + viewState.right;
                rect.left = i4 - (((viewState.top + viewState.bottom) + viewState.width()) / 2);
                height = (i5 - viewState.height()) / 2;
            }
            this.mStartMotionPath.m(rect.left, rect.top, rect.width(), rect.height());
            this.mStartPoint.setState(rect, view, i2, viewState.rotation);
        }
        int i6 = viewState.left + viewState.right;
        rect.left = ((viewState.top + viewState.bottom) - viewState.width()) / 2;
        height = i3 - ((i6 + viewState.height()) / 2);
        rect.top = height;
        rect.right = rect.left + viewState.width();
        rect.bottom = rect.top + viewState.height();
        this.mStartMotionPath.m(rect.left, rect.top, rect.width(), rect.height());
        this.mStartPoint.setState(rect, view, i2, viewState.rotation);
    }

    public void setTransformPivotTarget(int i2) {
        this.mTransformPivotTarget = i2;
        this.mTransformPivotView = null;
    }

    public void setView(View view) {
        this.f2069b = view;
        this.f2070c = view.getId();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ((ConstraintLayout.LayoutParams) layoutParams).getConstraintTag();
        }
    }

    public void setup(int i2, int i3, float f2, long j2) {
        ArrayList arrayList;
        String[] strArr;
        ConstraintAttribute constraintAttribute;
        ViewTimeCycle makeSpline;
        ConstraintAttribute constraintAttribute2;
        Integer num;
        ViewSpline makeSpline2;
        ConstraintAttribute constraintAttribute3;
        new HashSet();
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        HashMap<String, Integer> hashMap = new HashMap<>();
        int i4 = this.mPathMotionArc;
        if (i4 != Key.UNSET) {
            this.mStartMotionPath.f2141k = i4;
        }
        this.mStartPoint.a(this.mEndPoint, hashSet2);
        ArrayList<Key> arrayList2 = this.mKeyList;
        if (arrayList2 != null) {
            Iterator<Key> it = arrayList2.iterator();
            arrayList = null;
            while (it.hasNext()) {
                Key next = it.next();
                if (next instanceof KeyPosition) {
                    KeyPosition keyPosition = (KeyPosition) next;
                    insertKey(new MotionPaths(i2, i3, keyPosition, this.mStartMotionPath, this.mEndMotionPath));
                    int i5 = keyPosition.f2057f;
                    if (i5 != Key.UNSET) {
                        this.mCurveFitType = i5;
                    }
                } else if (next instanceof KeyCycle) {
                    next.getAttributeNames(hashSet3);
                } else if (next instanceof KeyTimeCycle) {
                    next.getAttributeNames(hashSet);
                } else if (next instanceof KeyTrigger) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add((KeyTrigger) next);
                } else {
                    next.setInterpolation(hashMap);
                    next.getAttributeNames(hashSet2);
                }
            }
        } else {
            arrayList = null;
        }
        int i6 = 0;
        if (arrayList != null) {
            this.mKeyTriggers = (KeyTrigger[]) arrayList.toArray(new KeyTrigger[0]);
        }
        char c2 = 1;
        if (!hashSet2.isEmpty()) {
            this.mAttributesMap = new HashMap<>();
            Iterator it2 = hashSet2.iterator();
            while (it2.hasNext()) {
                String str = (String) it2.next();
                if (str.startsWith("CUSTOM,")) {
                    SparseArray sparseArray = new SparseArray();
                    String str2 = str.split(",")[c2];
                    Iterator<Key> it3 = this.mKeyList.iterator();
                    while (it3.hasNext()) {
                        Key next2 = it3.next();
                        HashMap hashMap2 = next2.f2045e;
                        if (hashMap2 != null && (constraintAttribute3 = (ConstraintAttribute) hashMap2.get(str2)) != null) {
                            sparseArray.append(next2.f2041a, constraintAttribute3);
                        }
                    }
                    makeSpline2 = ViewSpline.makeCustomSpline(str, sparseArray);
                } else {
                    makeSpline2 = ViewSpline.makeSpline(str);
                }
                if (makeSpline2 != null) {
                    makeSpline2.setType(str);
                    this.mAttributesMap.put(str, makeSpline2);
                }
                c2 = 1;
            }
            ArrayList<Key> arrayList3 = this.mKeyList;
            if (arrayList3 != null) {
                Iterator<Key> it4 = arrayList3.iterator();
                while (it4.hasNext()) {
                    Key next3 = it4.next();
                    if (next3 instanceof KeyAttributes) {
                        next3.addValues(this.mAttributesMap);
                    }
                }
            }
            this.mStartPoint.addValues(this.mAttributesMap, 0);
            this.mEndPoint.addValues(this.mAttributesMap, 100);
            for (String str3 : this.mAttributesMap.keySet()) {
                int intValue = (!hashMap.containsKey(str3) || (num = hashMap.get(str3)) == null) ? 0 : num.intValue();
                ViewSpline viewSpline = this.mAttributesMap.get(str3);
                if (viewSpline != null) {
                    viewSpline.setup(intValue);
                }
            }
        }
        if (!hashSet.isEmpty()) {
            if (this.mTimeCycleAttributesMap == null) {
                this.mTimeCycleAttributesMap = new HashMap<>();
            }
            Iterator it5 = hashSet.iterator();
            while (it5.hasNext()) {
                String str4 = (String) it5.next();
                if (!this.mTimeCycleAttributesMap.containsKey(str4)) {
                    if (str4.startsWith("CUSTOM,")) {
                        SparseArray sparseArray2 = new SparseArray();
                        String str5 = str4.split(",")[1];
                        Iterator<Key> it6 = this.mKeyList.iterator();
                        while (it6.hasNext()) {
                            Key next4 = it6.next();
                            HashMap hashMap3 = next4.f2045e;
                            if (hashMap3 != null && (constraintAttribute2 = (ConstraintAttribute) hashMap3.get(str5)) != null) {
                                sparseArray2.append(next4.f2041a, constraintAttribute2);
                            }
                        }
                        makeSpline = ViewTimeCycle.makeCustomSpline(str4, sparseArray2);
                    } else {
                        makeSpline = ViewTimeCycle.makeSpline(str4, j2);
                    }
                    if (makeSpline != null) {
                        makeSpline.setType(str4);
                        this.mTimeCycleAttributesMap.put(str4, makeSpline);
                    }
                }
            }
            ArrayList<Key> arrayList4 = this.mKeyList;
            if (arrayList4 != null) {
                Iterator<Key> it7 = arrayList4.iterator();
                while (it7.hasNext()) {
                    Key next5 = it7.next();
                    if (next5 instanceof KeyTimeCycle) {
                        ((KeyTimeCycle) next5).addTimeValues(this.mTimeCycleAttributesMap);
                    }
                }
            }
            for (String str6 : this.mTimeCycleAttributesMap.keySet()) {
                this.mTimeCycleAttributesMap.get(str6).setup(hashMap.containsKey(str6) ? hashMap.get(str6).intValue() : 0);
            }
        }
        int i7 = 2;
        int size = this.mMotionPaths.size() + 2;
        MotionPaths[] motionPathsArr = new MotionPaths[size];
        motionPathsArr[0] = this.mStartMotionPath;
        motionPathsArr[size - 1] = this.mEndMotionPath;
        if (this.mMotionPaths.size() > 0 && this.mCurveFitType == -1) {
            this.mCurveFitType = 0;
        }
        Iterator<MotionPaths> it8 = this.mMotionPaths.iterator();
        int i8 = 1;
        while (it8.hasNext()) {
            motionPathsArr[i8] = it8.next();
            i8++;
        }
        HashSet hashSet4 = new HashSet();
        for (String str7 : this.mEndMotionPath.f2145o.keySet()) {
            if (this.mStartMotionPath.f2145o.containsKey(str7)) {
                if (!hashSet2.contains("CUSTOM," + str7)) {
                    hashSet4.add(str7);
                }
            }
        }
        String[] strArr2 = (String[]) hashSet4.toArray(new String[0]);
        this.mAttributeNames = strArr2;
        this.mAttributeInterpolatorCount = new int[strArr2.length];
        int i9 = 0;
        while (true) {
            strArr = this.mAttributeNames;
            if (i9 >= strArr.length) {
                break;
            }
            String str8 = strArr[i9];
            this.mAttributeInterpolatorCount[i9] = 0;
            int i10 = 0;
            while (true) {
                if (i10 >= size) {
                    break;
                }
                if (motionPathsArr[i10].f2145o.containsKey(str8) && (constraintAttribute = (ConstraintAttribute) motionPathsArr[i10].f2145o.get(str8)) != null) {
                    int[] iArr = this.mAttributeInterpolatorCount;
                    iArr[i9] = iArr[i9] + constraintAttribute.numberOfInterpolatedValues();
                    break;
                }
                i10++;
            }
            i9++;
        }
        boolean z = motionPathsArr[0].f2141k != Key.UNSET;
        int length = 18 + strArr.length;
        boolean[] zArr = new boolean[length];
        for (int i11 = 1; i11 < size; i11++) {
            motionPathsArr[i11].a(motionPathsArr[i11 - 1], zArr, this.mAttributeNames, z);
        }
        int i12 = 0;
        for (int i13 = 1; i13 < length; i13++) {
            if (zArr[i13]) {
                i12++;
            }
        }
        this.mInterpolateVariables = new int[i12];
        int max = Math.max(2, i12);
        this.mInterpolateData = new double[max];
        this.mInterpolateVelocity = new double[max];
        int i14 = 0;
        for (int i15 = 1; i15 < length; i15++) {
            if (zArr[i15]) {
                this.mInterpolateVariables[i14] = i15;
                i14++;
            }
        }
        double[][] dArr = (double[][]) Array.newInstance(double.class, size, this.mInterpolateVariables.length);
        double[] dArr2 = new double[size];
        for (int i16 = 0; i16 < size; i16++) {
            motionPathsArr[i16].b(dArr[i16], this.mInterpolateVariables);
            dArr2[i16] = motionPathsArr[i16].f2133c;
        }
        int i17 = 0;
        while (true) {
            int[] iArr2 = this.mInterpolateVariables;
            if (i17 >= iArr2.length) {
                break;
            }
            if (iArr2[i17] < MotionPaths.f2130s.length) {
                String str9 = MotionPaths.f2130s[this.mInterpolateVariables[i17]] + " [";
                for (int i18 = 0; i18 < size; i18++) {
                    str9 = str9 + dArr[i18][i17];
                }
            }
            i17++;
        }
        this.mSpline = new CurveFit[this.mAttributeNames.length + 1];
        int i19 = 0;
        while (true) {
            String[] strArr3 = this.mAttributeNames;
            if (i19 >= strArr3.length) {
                break;
            }
            String str10 = strArr3[i19];
            int i20 = i6;
            int i21 = i20;
            double[] dArr3 = null;
            double[][] dArr4 = null;
            while (i20 < size) {
                if (motionPathsArr[i20].h(str10)) {
                    if (dArr4 == null) {
                        dArr3 = new double[size];
                        int[] iArr3 = new int[i7];
                        iArr3[1] = motionPathsArr[i20].f(str10);
                        iArr3[i6] = size;
                        dArr4 = (double[][]) Array.newInstance(double.class, iArr3);
                    }
                    dArr3[i21] = motionPathsArr[i20].f2133c;
                    motionPathsArr[i20].e(str10, dArr4[i21], 0);
                    i21++;
                }
                i20++;
                i7 = 2;
                i6 = 0;
            }
            i19++;
            this.mSpline[i19] = CurveFit.get(this.mCurveFitType, Arrays.copyOf(dArr3, i21), (double[][]) Arrays.copyOf(dArr4, i21));
            i7 = 2;
            i6 = 0;
        }
        this.mSpline[0] = CurveFit.get(this.mCurveFitType, dArr2, dArr);
        if (motionPathsArr[0].f2141k != Key.UNSET) {
            int[] iArr4 = new int[size];
            double[] dArr5 = new double[size];
            double[][] dArr6 = (double[][]) Array.newInstance(double.class, size, 2);
            for (int i22 = 0; i22 < size; i22++) {
                iArr4[i22] = motionPathsArr[i22].f2141k;
                dArr5[i22] = motionPathsArr[i22].f2133c;
                dArr6[i22][0] = motionPathsArr[i22].f2135e;
                dArr6[i22][1] = motionPathsArr[i22].f2136f;
            }
            this.mArcSpline = CurveFit.getArc(iArr4, dArr5, dArr6);
        }
        float f3 = Float.NaN;
        this.mCycleMap = new HashMap<>();
        if (this.mKeyList != null) {
            Iterator it9 = hashSet3.iterator();
            while (it9.hasNext()) {
                String str11 = (String) it9.next();
                ViewOscillator makeSpline3 = ViewOscillator.makeSpline(str11);
                if (makeSpline3 != null) {
                    if (makeSpline3.variesByPath() && Float.isNaN(f3)) {
                        f3 = getPreCycleDistance();
                    }
                    makeSpline3.setType(str11);
                    this.mCycleMap.put(str11, makeSpline3);
                }
            }
            Iterator<Key> it10 = this.mKeyList.iterator();
            while (it10.hasNext()) {
                Key next6 = it10.next();
                if (next6 instanceof KeyCycle) {
                    ((KeyCycle) next6).addCycleValues(this.mCycleMap);
                }
            }
            for (ViewOscillator viewOscillator : this.mCycleMap.values()) {
                viewOscillator.setup(f3);
            }
        }
    }

    public void setupRelative(MotionController motionController) {
        this.mStartMotionPath.setupRelative(motionController, motionController.mStartMotionPath);
        this.mEndMotionPath.setupRelative(motionController, motionController.mEndMotionPath);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void t(Rect rect, ConstraintSet constraintSet, int i2, int i3) {
        int i4 = constraintSet.mRotate;
        if (i4 != 0) {
            p(rect, this.f2068a, i4, i2, i3);
        }
        MotionPaths motionPaths = this.mStartMotionPath;
        motionPaths.f2133c = 0.0f;
        motionPaths.f2134d = 0.0f;
        readView(motionPaths);
        this.mStartMotionPath.m(rect.left, rect.top, rect.width(), rect.height());
        ConstraintSet.Constraint parameters = constraintSet.getParameters(this.f2070c);
        this.mStartMotionPath.applyParameters(parameters);
        this.f2071d = parameters.motion.mMotionStagger;
        this.mStartPoint.setState(rect, constraintSet, i4, this.f2070c);
        this.mTransformPivotTarget = parameters.transform.transformPivotTarget;
        ConstraintSet.Motion motion = parameters.motion;
        this.mQuantizeMotionSteps = motion.mQuantizeMotionSteps;
        this.mQuantizeMotionPhase = motion.mQuantizeMotionPhase;
        Context context = this.f2069b.getContext();
        ConstraintSet.Motion motion2 = parameters.motion;
        this.mQuantizeMotionInterpolator = getInterpolator(context, motion2.mQuantizeInterpolatorType, motion2.mQuantizeInterpolatorString, motion2.mQuantizeInterpolatorID);
    }

    public String toString() {
        return " start: x: " + this.mStartMotionPath.f2135e + " y: " + this.mStartMotionPath.f2136f + " end: x: " + this.mEndMotionPath.f2135e + " y: " + this.mEndMotionPath.f2136f;
    }
}
