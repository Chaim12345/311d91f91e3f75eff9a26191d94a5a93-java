package androidx.constraintlayout.core.motion.utils;

import androidx.constraintlayout.core.motion.CustomAttribute;
import androidx.constraintlayout.core.motion.CustomVariable;
import java.io.PrintStream;
import java.util.Arrays;
/* loaded from: classes.dex */
public class KeyFrameArray {

    /* loaded from: classes.dex */
    public static class CustomArray {
        private static final int EMPTY = 999;

        /* renamed from: a  reason: collision with root package name */
        int[] f1787a = new int[101];

        /* renamed from: b  reason: collision with root package name */
        CustomAttribute[] f1788b = new CustomAttribute[101];

        /* renamed from: c  reason: collision with root package name */
        int f1789c;

        public CustomArray() {
            clear();
        }

        public void append(int i2, CustomAttribute customAttribute) {
            if (this.f1788b[i2] != null) {
                remove(i2);
            }
            this.f1788b[i2] = customAttribute;
            int[] iArr = this.f1787a;
            int i3 = this.f1789c;
            this.f1789c = i3 + 1;
            iArr[i3] = i2;
            Arrays.sort(iArr);
        }

        public void clear() {
            Arrays.fill(this.f1787a, 999);
            Arrays.fill(this.f1788b, (Object) null);
            this.f1789c = 0;
        }

        public void dump() {
            PrintStream printStream = System.out;
            printStream.println("V: " + Arrays.toString(Arrays.copyOf(this.f1787a, this.f1789c)));
            System.out.print("K: [");
            int i2 = 0;
            while (i2 < this.f1789c) {
                PrintStream printStream2 = System.out;
                StringBuilder sb = new StringBuilder();
                sb.append(i2 == 0 ? "" : ", ");
                sb.append(valueAt(i2));
                printStream2.print(sb.toString());
                i2++;
            }
            System.out.println("]");
        }

        public int keyAt(int i2) {
            return this.f1787a[i2];
        }

        public void remove(int i2) {
            this.f1788b[i2] = null;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                int i5 = this.f1789c;
                if (i3 >= i5) {
                    this.f1789c = i5 - 1;
                    return;
                }
                int[] iArr = this.f1787a;
                if (i2 == iArr[i3]) {
                    iArr[i3] = 999;
                    i4++;
                }
                if (i3 != i4) {
                    iArr[i3] = iArr[i4];
                }
                i4++;
                i3++;
            }
        }

        public int size() {
            return this.f1789c;
        }

        public CustomAttribute valueAt(int i2) {
            return this.f1788b[this.f1787a[i2]];
        }
    }

    /* loaded from: classes.dex */
    public static class CustomVar {
        private static final int EMPTY = 999;

        /* renamed from: a  reason: collision with root package name */
        int[] f1790a = new int[101];

        /* renamed from: b  reason: collision with root package name */
        CustomVariable[] f1791b = new CustomVariable[101];

        /* renamed from: c  reason: collision with root package name */
        int f1792c;

        public CustomVar() {
            clear();
        }

        public void append(int i2, CustomVariable customVariable) {
            if (this.f1791b[i2] != null) {
                remove(i2);
            }
            this.f1791b[i2] = customVariable;
            int[] iArr = this.f1790a;
            int i3 = this.f1792c;
            this.f1792c = i3 + 1;
            iArr[i3] = i2;
            Arrays.sort(iArr);
        }

        public void clear() {
            Arrays.fill(this.f1790a, 999);
            Arrays.fill(this.f1791b, (Object) null);
            this.f1792c = 0;
        }

        public void dump() {
            PrintStream printStream = System.out;
            printStream.println("V: " + Arrays.toString(Arrays.copyOf(this.f1790a, this.f1792c)));
            System.out.print("K: [");
            int i2 = 0;
            while (i2 < this.f1792c) {
                PrintStream printStream2 = System.out;
                StringBuilder sb = new StringBuilder();
                sb.append(i2 == 0 ? "" : ", ");
                sb.append(valueAt(i2));
                printStream2.print(sb.toString());
                i2++;
            }
            System.out.println("]");
        }

        public int keyAt(int i2) {
            return this.f1790a[i2];
        }

        public void remove(int i2) {
            this.f1791b[i2] = null;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                int i5 = this.f1792c;
                if (i3 >= i5) {
                    this.f1792c = i5 - 1;
                    return;
                }
                int[] iArr = this.f1790a;
                if (i2 == iArr[i3]) {
                    iArr[i3] = 999;
                    i4++;
                }
                if (i3 != i4) {
                    iArr[i3] = iArr[i4];
                }
                i4++;
                i3++;
            }
        }

        public int size() {
            return this.f1792c;
        }

        public CustomVariable valueAt(int i2) {
            return this.f1791b[this.f1790a[i2]];
        }
    }

    /* loaded from: classes.dex */
    static class FloatArray {
        private static final int EMPTY = 999;

        /* renamed from: a  reason: collision with root package name */
        int[] f1793a = new int[101];

        /* renamed from: b  reason: collision with root package name */
        float[][] f1794b = new float[101];

        /* renamed from: c  reason: collision with root package name */
        int f1795c;

        public FloatArray() {
            clear();
        }

        public void append(int i2, float[] fArr) {
            if (this.f1794b[i2] != null) {
                remove(i2);
            }
            this.f1794b[i2] = fArr;
            int[] iArr = this.f1793a;
            int i3 = this.f1795c;
            this.f1795c = i3 + 1;
            iArr[i3] = i2;
            Arrays.sort(iArr);
        }

        public void clear() {
            Arrays.fill(this.f1793a, 999);
            Arrays.fill(this.f1794b, (Object) null);
            this.f1795c = 0;
        }

        public void dump() {
            PrintStream printStream = System.out;
            printStream.println("V: " + Arrays.toString(Arrays.copyOf(this.f1793a, this.f1795c)));
            System.out.print("K: [");
            int i2 = 0;
            while (i2 < this.f1795c) {
                PrintStream printStream2 = System.out;
                StringBuilder sb = new StringBuilder();
                sb.append(i2 == 0 ? "" : ", ");
                sb.append(Arrays.toString(valueAt(i2)));
                printStream2.print(sb.toString());
                i2++;
            }
            System.out.println("]");
        }

        public int keyAt(int i2) {
            return this.f1793a[i2];
        }

        public void remove(int i2) {
            this.f1794b[i2] = null;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                int i5 = this.f1795c;
                if (i3 >= i5) {
                    this.f1795c = i5 - 1;
                    return;
                }
                int[] iArr = this.f1793a;
                if (i2 == iArr[i3]) {
                    iArr[i3] = 999;
                    i4++;
                }
                if (i3 != i4) {
                    iArr[i3] = iArr[i4];
                }
                i4++;
                i3++;
            }
        }

        public int size() {
            return this.f1795c;
        }

        public float[] valueAt(int i2) {
            return this.f1794b[this.f1793a[i2]];
        }
    }
}
