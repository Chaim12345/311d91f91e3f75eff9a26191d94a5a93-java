package androidx.constraintlayout.core.motion.utils;

import androidx.constraintlayout.core.motion.CustomAttribute;
import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.MotionWidget;
import androidx.constraintlayout.core.motion.utils.KeyFrameArray;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
/* loaded from: classes.dex */
public abstract class TimeCycleSplineSet {
    private static final String TAG = "SplineSet";

    /* renamed from: k  reason: collision with root package name */
    protected static float f1817k = 6.2831855f;

    /* renamed from: a  reason: collision with root package name */
    protected CurveFit f1818a;

    /* renamed from: e  reason: collision with root package name */
    protected int f1822e;

    /* renamed from: f  reason: collision with root package name */
    protected String f1823f;

    /* renamed from: i  reason: collision with root package name */
    protected long f1826i;

    /* renamed from: b  reason: collision with root package name */
    protected int f1819b = 0;

    /* renamed from: c  reason: collision with root package name */
    protected int[] f1820c = new int[10];

    /* renamed from: d  reason: collision with root package name */
    protected float[][] f1821d = (float[][]) Array.newInstance(float.class, 10, 3);

    /* renamed from: g  reason: collision with root package name */
    protected float[] f1824g = new float[3];

    /* renamed from: h  reason: collision with root package name */
    protected boolean f1825h = false;

    /* renamed from: j  reason: collision with root package name */
    protected float f1827j = Float.NaN;

    /* loaded from: classes.dex */
    public static class CustomSet extends TimeCycleSplineSet {

        /* renamed from: l  reason: collision with root package name */
        String f1828l;

        /* renamed from: m  reason: collision with root package name */
        KeyFrameArray.CustomArray f1829m;

        /* renamed from: n  reason: collision with root package name */
        KeyFrameArray.FloatArray f1830n = new KeyFrameArray.FloatArray();

        /* renamed from: o  reason: collision with root package name */
        float[] f1831o;

        /* renamed from: p  reason: collision with root package name */
        float[] f1832p;

        public CustomSet(String str, KeyFrameArray.CustomArray customArray) {
            this.f1828l = str.split(",")[1];
            this.f1829m = customArray;
        }

        @Override // androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet
        public void setPoint(int i2, float f2, float f3, int i3, float f4) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute,...)");
        }

        public void setPoint(int i2, CustomAttribute customAttribute, float f2, int i3, float f3) {
            this.f1829m.append(i2, customAttribute);
            this.f1830n.append(i2, new float[]{f2, f3});
            this.f1819b = Math.max(this.f1819b, i3);
        }

        public boolean setProperty(MotionWidget motionWidget, float f2, long j2, KeyCache keyCache) {
            this.f1818a.getPos(f2, this.f1831o);
            float[] fArr = this.f1831o;
            float f3 = fArr[fArr.length - 2];
            float f4 = fArr[fArr.length - 1];
            long j3 = j2 - this.f1826i;
            if (Float.isNaN(this.f1827j)) {
                float floatValue = keyCache.getFloatValue(motionWidget, this.f1828l, 0);
                this.f1827j = floatValue;
                if (Float.isNaN(floatValue)) {
                    this.f1827j = 0.0f;
                }
            }
            float f5 = (float) ((this.f1827j + ((j3 * 1.0E-9d) * f3)) % 1.0d);
            this.f1827j = f5;
            this.f1826i = j2;
            float a2 = a(f5);
            this.f1825h = false;
            int i2 = 0;
            while (true) {
                float[] fArr2 = this.f1832p;
                if (i2 >= fArr2.length) {
                    break;
                }
                boolean z = this.f1825h;
                float[] fArr3 = this.f1831o;
                this.f1825h = z | (((double) fArr3[i2]) != 0.0d);
                fArr2[i2] = (fArr3[i2] * a2) + f4;
                i2++;
            }
            this.f1829m.valueAt(0).setInterpolatedValue(motionWidget, this.f1832p);
            if (f3 != 0.0f) {
                this.f1825h = true;
            }
            return this.f1825h;
        }

        @Override // androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet
        public void setup(int i2) {
            float[] fArr;
            int size = this.f1829m.size();
            int numberOfInterpolatedValues = this.f1829m.valueAt(0).numberOfInterpolatedValues();
            double[] dArr = new double[size];
            int i3 = numberOfInterpolatedValues + 2;
            this.f1831o = new float[i3];
            this.f1832p = new float[numberOfInterpolatedValues];
            double[][] dArr2 = (double[][]) Array.newInstance(double.class, size, i3);
            for (int i4 = 0; i4 < size; i4++) {
                int keyAt = this.f1829m.keyAt(i4);
                CustomAttribute valueAt = this.f1829m.valueAt(i4);
                float[] valueAt2 = this.f1830n.valueAt(i4);
                dArr[i4] = keyAt * 0.01d;
                valueAt.getValuesToInterpolate(this.f1831o);
                int i5 = 0;
                while (true) {
                    if (i5 < this.f1831o.length) {
                        dArr2[i4][i5] = fArr[i5];
                        i5++;
                    }
                }
                dArr2[i4][numberOfInterpolatedValues] = valueAt2[0];
                dArr2[i4][numberOfInterpolatedValues + 1] = valueAt2[1];
            }
            this.f1818a = CurveFit.get(i2, dArr, dArr2);
        }
    }

    /* loaded from: classes.dex */
    public static class CustomVarSet extends TimeCycleSplineSet {

        /* renamed from: l  reason: collision with root package name */
        String f1833l;

        /* renamed from: m  reason: collision with root package name */
        KeyFrameArray.CustomVar f1834m;

        /* renamed from: n  reason: collision with root package name */
        KeyFrameArray.FloatArray f1835n = new KeyFrameArray.FloatArray();

        /* renamed from: o  reason: collision with root package name */
        float[] f1836o;

        /* renamed from: p  reason: collision with root package name */
        float[] f1837p;

        public CustomVarSet(String str, KeyFrameArray.CustomVar customVar) {
            this.f1833l = str.split(",")[1];
            this.f1834m = customVar;
        }

        @Override // androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet
        public void setPoint(int i2, float f2, float f3, int i3, float f4) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute,...)");
        }

        public void setPoint(int i2, CustomVariable customVariable, float f2, int i3, float f3) {
            this.f1834m.append(i2, customVariable);
            this.f1835n.append(i2, new float[]{f2, f3});
            this.f1819b = Math.max(this.f1819b, i3);
        }

        public boolean setProperty(MotionWidget motionWidget, float f2, long j2, KeyCache keyCache) {
            this.f1818a.getPos(f2, this.f1836o);
            float[] fArr = this.f1836o;
            float f3 = fArr[fArr.length - 2];
            float f4 = fArr[fArr.length - 1];
            long j3 = j2 - this.f1826i;
            if (Float.isNaN(this.f1827j)) {
                float floatValue = keyCache.getFloatValue(motionWidget, this.f1833l, 0);
                this.f1827j = floatValue;
                if (Float.isNaN(floatValue)) {
                    this.f1827j = 0.0f;
                }
            }
            float f5 = (float) ((this.f1827j + ((j3 * 1.0E-9d) * f3)) % 1.0d);
            this.f1827j = f5;
            this.f1826i = j2;
            float a2 = a(f5);
            this.f1825h = false;
            int i2 = 0;
            while (true) {
                float[] fArr2 = this.f1837p;
                if (i2 >= fArr2.length) {
                    break;
                }
                boolean z = this.f1825h;
                float[] fArr3 = this.f1836o;
                this.f1825h = z | (((double) fArr3[i2]) != 0.0d);
                fArr2[i2] = (fArr3[i2] * a2) + f4;
                i2++;
            }
            this.f1834m.valueAt(0).setInterpolatedValue(motionWidget, this.f1837p);
            if (f3 != 0.0f) {
                this.f1825h = true;
            }
            return this.f1825h;
        }

        @Override // androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet
        public void setup(int i2) {
            float[] fArr;
            int size = this.f1834m.size();
            int numberOfInterpolatedValues = this.f1834m.valueAt(0).numberOfInterpolatedValues();
            double[] dArr = new double[size];
            int i3 = numberOfInterpolatedValues + 2;
            this.f1836o = new float[i3];
            this.f1837p = new float[numberOfInterpolatedValues];
            double[][] dArr2 = (double[][]) Array.newInstance(double.class, size, i3);
            for (int i4 = 0; i4 < size; i4++) {
                int keyAt = this.f1834m.keyAt(i4);
                CustomVariable valueAt = this.f1834m.valueAt(i4);
                float[] valueAt2 = this.f1835n.valueAt(i4);
                dArr[i4] = keyAt * 0.01d;
                valueAt.getValuesToInterpolate(this.f1836o);
                int i5 = 0;
                while (true) {
                    if (i5 < this.f1836o.length) {
                        dArr2[i4][i5] = fArr[i5];
                        i5++;
                    }
                }
                dArr2[i4][numberOfInterpolatedValues] = valueAt2[0];
                dArr2[i4][numberOfInterpolatedValues + 1] = valueAt2[1];
            }
            this.f1818a = CurveFit.get(i2, dArr, dArr2);
        }
    }

    /* loaded from: classes.dex */
    protected static class Sort {
        static void a(int[] iArr, float[][] fArr, int i2, int i3) {
            int[] iArr2 = new int[iArr.length + 10];
            iArr2[0] = i3;
            iArr2[1] = i2;
            int i4 = 2;
            while (i4 > 0) {
                int i5 = i4 - 1;
                int i6 = iArr2[i5];
                i4 = i5 - 1;
                int i7 = iArr2[i4];
                if (i6 < i7) {
                    int partition = partition(iArr, fArr, i6, i7);
                    int i8 = i4 + 1;
                    iArr2[i4] = partition - 1;
                    int i9 = i8 + 1;
                    iArr2[i8] = i6;
                    int i10 = i9 + 1;
                    iArr2[i9] = i7;
                    i4 = i10 + 1;
                    iArr2[i10] = partition + 1;
                }
            }
        }

        private static int partition(int[] iArr, float[][] fArr, int i2, int i3) {
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

        private static void swap(int[] iArr, float[][] fArr, int i2, int i3) {
            int i4 = iArr[i2];
            iArr[i2] = iArr[i3];
            iArr[i3] = i4;
            float[] fArr2 = fArr[i2];
            fArr[i2] = fArr[i3];
            fArr[i3] = fArr2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float a(float f2) {
        float abs;
        switch (this.f1819b) {
            case 1:
                return Math.signum(f2 * f1817k);
            case 2:
                abs = Math.abs(f2);
                break;
            case 3:
                return (((f2 * 2.0f) + 1.0f) % 2.0f) - 1.0f;
            case 4:
                abs = ((f2 * 2.0f) + 1.0f) % 2.0f;
                break;
            case 5:
                return (float) Math.cos(f2 * f1817k);
            case 6:
                float abs2 = 1.0f - Math.abs(((f2 * 4.0f) % 4.0f) - 2.0f);
                abs = abs2 * abs2;
                break;
            default:
                return (float) Math.sin(f2 * f1817k);
        }
        return 1.0f - abs;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(long j2) {
        this.f1826i = j2;
    }

    public CurveFit getCurveFit() {
        return this.f1818a;
    }

    public void setPoint(int i2, float f2, float f3, int i3, float f4) {
        int[] iArr = this.f1820c;
        int i4 = this.f1822e;
        iArr[i4] = i2;
        float[][] fArr = this.f1821d;
        fArr[i4][0] = f2;
        fArr[i4][1] = f3;
        fArr[i4][2] = f4;
        this.f1819b = Math.max(this.f1819b, i3);
        this.f1822e++;
    }

    public void setType(String str) {
        this.f1823f = str;
    }

    public void setup(int i2) {
        int i3;
        int i4 = this.f1822e;
        if (i4 == 0) {
            System.err.println("Error no points added to " + this.f1823f);
            return;
        }
        Sort.a(this.f1820c, this.f1821d, 0, i4 - 1);
        int i5 = 1;
        int i6 = 0;
        while (true) {
            int[] iArr = this.f1820c;
            if (i5 >= iArr.length) {
                break;
            }
            if (iArr[i5] != iArr[i5 - 1]) {
                i6++;
            }
            i5++;
        }
        if (i6 == 0) {
            i6 = 1;
        }
        double[] dArr = new double[i6];
        double[][] dArr2 = (double[][]) Array.newInstance(double.class, i6, 3);
        int i7 = 0;
        for (i3 = 0; i3 < this.f1822e; i3 = i3 + 1) {
            if (i3 > 0) {
                int[] iArr2 = this.f1820c;
                i3 = iArr2[i3] == iArr2[i3 - 1] ? i3 + 1 : 0;
            }
            dArr[i7] = this.f1820c[i3] * 0.01d;
            double[] dArr3 = dArr2[i7];
            float[][] fArr = this.f1821d;
            dArr3[0] = fArr[i3][0];
            dArr2[i7][1] = fArr[i3][1];
            dArr2[i7][2] = fArr[i3][2];
            i7++;
        }
        this.f1818a = CurveFit.get(i2, dArr, dArr2);
    }

    public String toString() {
        String str = this.f1823f;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i2 = 0; i2 < this.f1822e; i2++) {
            str = str + "[" + this.f1820c[i2] + " , " + decimalFormat.format(this.f1821d[i2]) + "] ";
        }
        return str;
    }
}
