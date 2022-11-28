package androidx.constraintlayout.core.motion.utils;

import androidx.constraintlayout.core.motion.MotionWidget;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
/* loaded from: classes.dex */
public abstract class KeyCycleOscillator {
    private static final String TAG = "KeyCycleOscillator";
    private CurveFit mCurveFit;
    private CycleOscillator mCycleOscillator;
    private String mType;
    private int mWaveShape = 0;
    private String mWaveString = null;
    public int mVariesBy = 0;

    /* renamed from: a  reason: collision with root package name */
    ArrayList f1768a = new ArrayList();

    /* loaded from: classes.dex */
    private static class CoreSpline extends KeyCycleOscillator {

        /* renamed from: b  reason: collision with root package name */
        String f1769b;

        /* renamed from: c  reason: collision with root package name */
        int f1770c;

        public CoreSpline(String str) {
            this.f1769b = str;
            this.f1770c = TypedValues.Cycle.getId(str);
        }

        @Override // androidx.constraintlayout.core.motion.utils.KeyCycleOscillator
        public void setProperty(MotionWidget motionWidget, float f2) {
            motionWidget.setValue(this.f1770c, get(f2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class CycleOscillator {
        private static final String TAG = "CycleOscillator";
        private final int OFFST;
        private final int PHASE;
        private final int VALUE;

        /* renamed from: a  reason: collision with root package name */
        Oscillator f1771a;

        /* renamed from: b  reason: collision with root package name */
        float[] f1772b;

        /* renamed from: c  reason: collision with root package name */
        double[] f1773c;

        /* renamed from: d  reason: collision with root package name */
        float[] f1774d;

        /* renamed from: e  reason: collision with root package name */
        float[] f1775e;

        /* renamed from: f  reason: collision with root package name */
        float[] f1776f;

        /* renamed from: g  reason: collision with root package name */
        CurveFit f1777g;

        /* renamed from: h  reason: collision with root package name */
        double[] f1778h;

        /* renamed from: i  reason: collision with root package name */
        double[] f1779i;
        private final int mVariesBy;

        CycleOscillator(int i2, String str, int i3, int i4) {
            Oscillator oscillator = new Oscillator();
            this.f1771a = oscillator;
            this.OFFST = 0;
            this.PHASE = 1;
            this.VALUE = 2;
            this.mVariesBy = i3;
            oscillator.setType(i2, str);
            this.f1772b = new float[i4];
            this.f1773c = new double[i4];
            this.f1774d = new float[i4];
            this.f1775e = new float[i4];
            this.f1776f = new float[i4];
            float[] fArr = new float[i4];
        }

        public double getLastPhase() {
            return this.f1778h[1];
        }

        public double getSlope(float f2) {
            CurveFit curveFit = this.f1777g;
            if (curveFit != null) {
                double d2 = f2;
                curveFit.getSlope(d2, this.f1779i);
                this.f1777g.getPos(d2, this.f1778h);
            } else {
                double[] dArr = this.f1779i;
                dArr[0] = 0.0d;
                dArr[1] = 0.0d;
                dArr[2] = 0.0d;
            }
            double d3 = f2;
            double value = this.f1771a.getValue(d3, this.f1778h[1]);
            double slope = this.f1771a.getSlope(d3, this.f1778h[1], this.f1779i[1]);
            double[] dArr2 = this.f1779i;
            return dArr2[0] + (value * dArr2[2]) + (slope * this.f1778h[2]);
        }

        public double getValues(float f2) {
            CurveFit curveFit = this.f1777g;
            if (curveFit != null) {
                curveFit.getPos(f2, this.f1778h);
            } else {
                double[] dArr = this.f1778h;
                dArr[0] = this.f1775e[0];
                dArr[1] = this.f1776f[0];
                dArr[2] = this.f1772b[0];
            }
            double[] dArr2 = this.f1778h;
            return dArr2[0] + (this.f1771a.getValue(f2, dArr2[1]) * this.f1778h[2]);
        }

        public void setPoint(int i2, int i3, float f2, float f3, float f4, float f5) {
            this.f1773c[i2] = i3 / 100.0d;
            this.f1774d[i2] = f2;
            this.f1775e[i2] = f3;
            this.f1776f[i2] = f4;
            this.f1772b[i2] = f5;
        }

        public void setup(float f2) {
            double[][] dArr = (double[][]) Array.newInstance(double.class, this.f1773c.length, 3);
            float[] fArr = this.f1772b;
            this.f1778h = new double[fArr.length + 2];
            this.f1779i = new double[fArr.length + 2];
            if (this.f1773c[0] > 0.0d) {
                this.f1771a.addPoint(0.0d, this.f1774d[0]);
            }
            double[] dArr2 = this.f1773c;
            int length = dArr2.length - 1;
            if (dArr2[length] < 1.0d) {
                this.f1771a.addPoint(1.0d, this.f1774d[length]);
            }
            for (int i2 = 0; i2 < dArr.length; i2++) {
                dArr[i2][0] = this.f1775e[i2];
                dArr[i2][1] = this.f1776f[i2];
                dArr[i2][2] = this.f1772b[i2];
                this.f1771a.addPoint(this.f1773c[i2], this.f1774d[i2]);
            }
            this.f1771a.normalize();
            double[] dArr3 = this.f1773c;
            this.f1777g = dArr3.length > 1 ? CurveFit.get(0, dArr3, dArr) : null;
        }
    }

    /* loaded from: classes.dex */
    private static class IntDoubleSort {
        private IntDoubleSort() {
        }

        private static int partition(int[] iArr, float[] fArr, int i2, int i3) {
            int i4 = iArr[i3];
            int i5 = i2;
            while (i2 < i3) {
                if (iArr[i2] <= i4) {
                    swap(iArr, fArr, i5, i2);
                    i5++;
                }
                i2++;
            }
            swap(iArr, fArr, i5, i3);
            return i5;
        }

        private static void swap(int[] iArr, float[] fArr, int i2, int i3) {
            int i4 = iArr[i2];
            iArr[i2] = iArr[i3];
            iArr[i3] = i4;
            float f2 = fArr[i2];
            fArr[i2] = fArr[i3];
            fArr[i3] = f2;
        }
    }

    /* loaded from: classes.dex */
    private static class IntFloatFloatSort {
        private IntFloatFloatSort() {
        }

        private static int partition(int[] iArr, float[] fArr, float[] fArr2, int i2, int i3) {
            int i4 = iArr[i3];
            int i5 = i2;
            while (i2 < i3) {
                if (iArr[i2] <= i4) {
                    swap(iArr, fArr, fArr2, i5, i2);
                    i5++;
                }
                i2++;
            }
            swap(iArr, fArr, fArr2, i5, i3);
            return i5;
        }

        private static void swap(int[] iArr, float[] fArr, float[] fArr2, int i2, int i3) {
            int i4 = iArr[i2];
            iArr[i2] = iArr[i3];
            iArr[i3] = i4;
            float f2 = fArr[i2];
            fArr[i2] = fArr[i3];
            fArr[i3] = f2;
            float f3 = fArr2[i2];
            fArr2[i2] = fArr2[i3];
            fArr2[i3] = f3;
        }
    }

    /* loaded from: classes.dex */
    public static class PathRotateSet extends KeyCycleOscillator {

        /* renamed from: b  reason: collision with root package name */
        String f1780b;

        /* renamed from: c  reason: collision with root package name */
        int f1781c;

        public PathRotateSet(String str) {
            this.f1780b = str;
            this.f1781c = TypedValues.Cycle.getId(str);
        }

        public void setPathRotate(MotionWidget motionWidget, float f2, double d2, double d3) {
            motionWidget.setRotationZ(get(f2) + ((float) Math.toDegrees(Math.atan2(d3, d2))));
        }

        @Override // androidx.constraintlayout.core.motion.utils.KeyCycleOscillator
        public void setProperty(MotionWidget motionWidget, float f2) {
            motionWidget.setValue(this.f1781c, get(f2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class WavePoint {

        /* renamed from: a  reason: collision with root package name */
        int f1782a;

        /* renamed from: b  reason: collision with root package name */
        float f1783b;

        /* renamed from: c  reason: collision with root package name */
        float f1784c;

        /* renamed from: d  reason: collision with root package name */
        float f1785d;

        /* renamed from: e  reason: collision with root package name */
        float f1786e;

        public WavePoint(int i2, float f2, float f3, float f4, float f5) {
            this.f1782a = i2;
            this.f1783b = f5;
            this.f1784c = f3;
            this.f1785d = f2;
            this.f1786e = f4;
        }
    }

    public static KeyCycleOscillator makeWidgetCycle(String str) {
        return str.equals("pathRotate") ? new PathRotateSet(str) : new CoreSpline(str);
    }

    protected void a(Object obj) {
    }

    public float get(float f2) {
        return (float) this.mCycleOscillator.getValues(f2);
    }

    public CurveFit getCurveFit() {
        return this.mCurveFit;
    }

    public float getSlope(float f2) {
        return (float) this.mCycleOscillator.getSlope(f2);
    }

    public void setPoint(int i2, int i3, String str, int i4, float f2, float f3, float f4, float f5) {
        this.f1768a.add(new WavePoint(i2, f2, f3, f4, f5));
        if (i4 != -1) {
            this.mVariesBy = i4;
        }
        this.mWaveShape = i3;
        this.mWaveString = str;
    }

    public void setPoint(int i2, int i3, String str, int i4, float f2, float f3, float f4, float f5, Object obj) {
        this.f1768a.add(new WavePoint(i2, f2, f3, f4, f5));
        if (i4 != -1) {
            this.mVariesBy = i4;
        }
        this.mWaveShape = i3;
        a(obj);
        this.mWaveString = str;
    }

    public void setProperty(MotionWidget motionWidget, float f2) {
    }

    public void setType(String str) {
        this.mType = str;
    }

    public void setup(float f2) {
        int size = this.f1768a.size();
        if (size == 0) {
            return;
        }
        Collections.sort(this.f1768a, new Comparator<WavePoint>(this) { // from class: androidx.constraintlayout.core.motion.utils.KeyCycleOscillator.1
            @Override // java.util.Comparator
            public int compare(WavePoint wavePoint, WavePoint wavePoint2) {
                return Integer.compare(wavePoint.f1782a, wavePoint2.f1782a);
            }
        });
        double[] dArr = new double[size];
        char c2 = 0;
        double[][] dArr2 = (double[][]) Array.newInstance(double.class, size, 3);
        this.mCycleOscillator = new CycleOscillator(this.mWaveShape, this.mWaveString, this.mVariesBy, size);
        Iterator it = this.f1768a.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            WavePoint wavePoint = (WavePoint) it.next();
            float f3 = wavePoint.f1785d;
            dArr[i2] = f3 * 0.01d;
            double[] dArr3 = dArr2[i2];
            float f4 = wavePoint.f1783b;
            dArr3[c2] = f4;
            double[] dArr4 = dArr2[i2];
            float f5 = wavePoint.f1784c;
            dArr4[1] = f5;
            double[] dArr5 = dArr2[i2];
            float f6 = wavePoint.f1786e;
            dArr5[2] = f6;
            this.mCycleOscillator.setPoint(i2, wavePoint.f1782a, f3, f5, f6, f4);
            i2++;
            c2 = 0;
        }
        this.mCycleOscillator.setup(f2);
        this.mCurveFit = CurveFit.get(0, dArr, dArr2);
    }

    public String toString() {
        String str = this.mType;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        Iterator it = this.f1768a.iterator();
        while (it.hasNext()) {
            WavePoint wavePoint = (WavePoint) it.next();
            str = str + "[" + wavePoint.f1782a + " , " + decimalFormat.format(wavePoint.f1783b) + "] ";
        }
        return str;
    }

    public boolean variesByPath() {
        return this.mVariesBy == 1;
    }
}
