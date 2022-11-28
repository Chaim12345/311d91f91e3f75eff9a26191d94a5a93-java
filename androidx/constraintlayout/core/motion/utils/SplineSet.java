package androidx.constraintlayout.core.motion.utils;

import androidx.constraintlayout.core.motion.CustomAttribute;
import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.MotionWidget;
import androidx.constraintlayout.core.motion.utils.KeyFrameArray;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.state.WidgetFrame;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.Arrays;
/* loaded from: classes.dex */
public abstract class SplineSet {
    private static final String TAG = "SplineSet";

    /* renamed from: a  reason: collision with root package name */
    protected CurveFit f1807a;

    /* renamed from: b  reason: collision with root package name */
    protected int[] f1808b = new int[10];

    /* renamed from: c  reason: collision with root package name */
    protected float[] f1809c = new float[10];
    private int count;
    private String mType;

    /* loaded from: classes.dex */
    private static class CoreSpline extends SplineSet {

        /* renamed from: d  reason: collision with root package name */
        String f1810d;

        public CoreSpline(String str, long j2) {
            this.f1810d = str;
        }

        @Override // androidx.constraintlayout.core.motion.utils.SplineSet
        public void setProperty(TypedValues typedValues, float f2) {
            typedValues.setValue(typedValues.getId(this.f1810d), get(f2));
        }
    }

    /* loaded from: classes.dex */
    public static class CustomSet extends SplineSet {

        /* renamed from: d  reason: collision with root package name */
        KeyFrameArray.CustomArray f1811d;

        /* renamed from: e  reason: collision with root package name */
        float[] f1812e;

        public CustomSet(String str, KeyFrameArray.CustomArray customArray) {
            String str2 = str.split(",")[1];
            this.f1811d = customArray;
        }

        @Override // androidx.constraintlayout.core.motion.utils.SplineSet
        public void setPoint(int i2, float f2) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute)");
        }

        public void setPoint(int i2, CustomAttribute customAttribute) {
            this.f1811d.append(i2, customAttribute);
        }

        public void setProperty(WidgetFrame widgetFrame, float f2) {
            this.f1807a.getPos(f2, this.f1812e);
            this.f1811d.valueAt(0).setInterpolatedValue(widgetFrame, this.f1812e);
        }

        @Override // androidx.constraintlayout.core.motion.utils.SplineSet
        public void setup(int i2) {
            float[] fArr;
            int size = this.f1811d.size();
            int numberOfInterpolatedValues = this.f1811d.valueAt(0).numberOfInterpolatedValues();
            double[] dArr = new double[size];
            this.f1812e = new float[numberOfInterpolatedValues];
            double[][] dArr2 = (double[][]) Array.newInstance(double.class, size, numberOfInterpolatedValues);
            for (int i3 = 0; i3 < size; i3++) {
                int keyAt = this.f1811d.keyAt(i3);
                CustomAttribute valueAt = this.f1811d.valueAt(i3);
                dArr[i3] = keyAt * 0.01d;
                valueAt.getValuesToInterpolate(this.f1812e);
                int i4 = 0;
                while (true) {
                    if (i4 < this.f1812e.length) {
                        dArr2[i3][i4] = fArr[i4];
                        i4++;
                    }
                }
            }
            this.f1807a = CurveFit.get(i2, dArr, dArr2);
        }
    }

    /* loaded from: classes.dex */
    public static class CustomSpline extends SplineSet {

        /* renamed from: d  reason: collision with root package name */
        KeyFrameArray.CustomVar f1813d;

        /* renamed from: e  reason: collision with root package name */
        float[] f1814e;

        public CustomSpline(String str, KeyFrameArray.CustomVar customVar) {
            String str2 = str.split(",")[1];
            this.f1813d = customVar;
        }

        @Override // androidx.constraintlayout.core.motion.utils.SplineSet
        public void setPoint(int i2, float f2) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute)");
        }

        public void setPoint(int i2, CustomVariable customVariable) {
            this.f1813d.append(i2, customVariable);
        }

        public void setProperty(MotionWidget motionWidget, float f2) {
            this.f1807a.getPos(f2, this.f1814e);
            this.f1813d.valueAt(0).setInterpolatedValue(motionWidget, this.f1814e);
        }

        @Override // androidx.constraintlayout.core.motion.utils.SplineSet
        public void setProperty(TypedValues typedValues, float f2) {
            setProperty((MotionWidget) typedValues, f2);
        }

        @Override // androidx.constraintlayout.core.motion.utils.SplineSet
        public void setup(int i2) {
            float[] fArr;
            int size = this.f1813d.size();
            int numberOfInterpolatedValues = this.f1813d.valueAt(0).numberOfInterpolatedValues();
            double[] dArr = new double[size];
            this.f1814e = new float[numberOfInterpolatedValues];
            double[][] dArr2 = (double[][]) Array.newInstance(double.class, size, numberOfInterpolatedValues);
            for (int i3 = 0; i3 < size; i3++) {
                int keyAt = this.f1813d.keyAt(i3);
                CustomVariable valueAt = this.f1813d.valueAt(i3);
                dArr[i3] = keyAt * 0.01d;
                valueAt.getValuesToInterpolate(this.f1814e);
                int i4 = 0;
                while (true) {
                    if (i4 < this.f1814e.length) {
                        dArr2[i3][i4] = fArr[i4];
                        i4++;
                    }
                }
            }
            this.f1807a = CurveFit.get(i2, dArr, dArr2);
        }
    }

    /* loaded from: classes.dex */
    private static class Sort {
        private Sort() {
        }

        static void a(int[] iArr, float[] fArr, int i2, int i3) {
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

    public static SplineSet makeCustomSpline(String str, KeyFrameArray.CustomArray customArray) {
        return new CustomSet(str, customArray);
    }

    public static SplineSet makeCustomSplineSet(String str, KeyFrameArray.CustomVar customVar) {
        return new CustomSpline(str, customVar);
    }

    public static SplineSet makeSpline(String str, long j2) {
        return new CoreSpline(str, j2);
    }

    public float get(float f2) {
        return (float) this.f1807a.getPos(f2, 0);
    }

    public CurveFit getCurveFit() {
        return this.f1807a;
    }

    public float getSlope(float f2) {
        return (float) this.f1807a.getSlope(f2, 0);
    }

    public void setPoint(int i2, float f2) {
        int[] iArr = this.f1808b;
        if (iArr.length < this.count + 1) {
            this.f1808b = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.f1809c;
            this.f1809c = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.f1808b;
        int i3 = this.count;
        iArr2[i3] = i2;
        this.f1809c[i3] = f2;
        this.count = i3 + 1;
    }

    public void setProperty(TypedValues typedValues, float f2) {
        typedValues.setValue(TypedValues.Attributes.getId(this.mType), get(f2));
    }

    public void setType(String str) {
        this.mType = str;
    }

    public void setup(int i2) {
        int i3;
        int i4 = this.count;
        if (i4 == 0) {
            return;
        }
        Sort.a(this.f1808b, this.f1809c, 0, i4 - 1);
        int i5 = 1;
        for (int i6 = 1; i6 < this.count; i6++) {
            int[] iArr = this.f1808b;
            if (iArr[i6 - 1] != iArr[i6]) {
                i5++;
            }
        }
        double[] dArr = new double[i5];
        double[][] dArr2 = (double[][]) Array.newInstance(double.class, i5, 1);
        int i7 = 0;
        for (i3 = 0; i3 < this.count; i3 = i3 + 1) {
            if (i3 > 0) {
                int[] iArr2 = this.f1808b;
                i3 = iArr2[i3] == iArr2[i3 - 1] ? i3 + 1 : 0;
            }
            dArr[i7] = this.f1808b[i3] * 0.01d;
            dArr2[i7][0] = this.f1809c[i3];
            i7++;
        }
        this.f1807a = CurveFit.get(i2, dArr, dArr2);
    }

    public String toString() {
        String str = this.mType;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i2 = 0; i2 < this.count; i2++) {
            str = str + "[" + this.f1808b[i2] + " , " + decimalFormat.format(this.f1809c[i2]) + "] ";
        }
        return str;
    }
}
