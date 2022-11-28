package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;
/* loaded from: classes.dex */
public class TypedBundle {
    private static final int INITIAL_BOOLEAN = 4;
    private static final int INITIAL_FLOAT = 10;
    private static final int INITIAL_INT = 10;
    private static final int INITIAL_STRING = 5;

    /* renamed from: a  reason: collision with root package name */
    int[] f1838a = new int[10];

    /* renamed from: b  reason: collision with root package name */
    int[] f1839b = new int[10];

    /* renamed from: c  reason: collision with root package name */
    int f1840c = 0;

    /* renamed from: d  reason: collision with root package name */
    int[] f1841d = new int[10];

    /* renamed from: e  reason: collision with root package name */
    float[] f1842e = new float[10];

    /* renamed from: f  reason: collision with root package name */
    int f1843f = 0;

    /* renamed from: g  reason: collision with root package name */
    int[] f1844g = new int[5];

    /* renamed from: h  reason: collision with root package name */
    String[] f1845h = new String[5];

    /* renamed from: i  reason: collision with root package name */
    int f1846i = 0;

    /* renamed from: j  reason: collision with root package name */
    int[] f1847j = new int[4];

    /* renamed from: k  reason: collision with root package name */
    boolean[] f1848k = new boolean[4];

    /* renamed from: l  reason: collision with root package name */
    int f1849l = 0;

    public void add(int i2, float f2) {
        int i3 = this.f1843f;
        int[] iArr = this.f1841d;
        if (i3 >= iArr.length) {
            this.f1841d = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.f1842e;
            this.f1842e = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.f1841d;
        int i4 = this.f1843f;
        iArr2[i4] = i2;
        float[] fArr2 = this.f1842e;
        this.f1843f = i4 + 1;
        fArr2[i4] = f2;
    }

    public void add(int i2, int i3) {
        int i4 = this.f1840c;
        int[] iArr = this.f1838a;
        if (i4 >= iArr.length) {
            this.f1838a = Arrays.copyOf(iArr, iArr.length * 2);
            int[] iArr2 = this.f1839b;
            this.f1839b = Arrays.copyOf(iArr2, iArr2.length * 2);
        }
        int[] iArr3 = this.f1838a;
        int i5 = this.f1840c;
        iArr3[i5] = i2;
        int[] iArr4 = this.f1839b;
        this.f1840c = i5 + 1;
        iArr4[i5] = i3;
    }

    public void add(int i2, String str) {
        int i3 = this.f1846i;
        int[] iArr = this.f1844g;
        if (i3 >= iArr.length) {
            this.f1844g = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.f1845h;
            this.f1845h = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
        }
        int[] iArr2 = this.f1844g;
        int i4 = this.f1846i;
        iArr2[i4] = i2;
        String[] strArr2 = this.f1845h;
        this.f1846i = i4 + 1;
        strArr2[i4] = str;
    }

    public void add(int i2, boolean z) {
        int i3 = this.f1849l;
        int[] iArr = this.f1847j;
        if (i3 >= iArr.length) {
            this.f1847j = Arrays.copyOf(iArr, iArr.length * 2);
            boolean[] zArr = this.f1848k;
            this.f1848k = Arrays.copyOf(zArr, zArr.length * 2);
        }
        int[] iArr2 = this.f1847j;
        int i4 = this.f1849l;
        iArr2[i4] = i2;
        boolean[] zArr2 = this.f1848k;
        this.f1849l = i4 + 1;
        zArr2[i4] = z;
    }

    public void addIfNotNull(int i2, String str) {
        if (str != null) {
            add(i2, str);
        }
    }

    public void applyDelta(TypedBundle typedBundle) {
        for (int i2 = 0; i2 < this.f1840c; i2++) {
            typedBundle.add(this.f1838a[i2], this.f1839b[i2]);
        }
        for (int i3 = 0; i3 < this.f1843f; i3++) {
            typedBundle.add(this.f1841d[i3], this.f1842e[i3]);
        }
        for (int i4 = 0; i4 < this.f1846i; i4++) {
            typedBundle.add(this.f1844g[i4], this.f1845h[i4]);
        }
        for (int i5 = 0; i5 < this.f1849l; i5++) {
            typedBundle.add(this.f1847j[i5], this.f1848k[i5]);
        }
    }

    public void applyDelta(TypedValues typedValues) {
        for (int i2 = 0; i2 < this.f1840c; i2++) {
            typedValues.setValue(this.f1838a[i2], this.f1839b[i2]);
        }
        for (int i3 = 0; i3 < this.f1843f; i3++) {
            typedValues.setValue(this.f1841d[i3], this.f1842e[i3]);
        }
        for (int i4 = 0; i4 < this.f1846i; i4++) {
            typedValues.setValue(this.f1844g[i4], this.f1845h[i4]);
        }
        for (int i5 = 0; i5 < this.f1849l; i5++) {
            typedValues.setValue(this.f1847j[i5], this.f1848k[i5]);
        }
    }

    public void clear() {
        this.f1849l = 0;
        this.f1846i = 0;
        this.f1843f = 0;
        this.f1840c = 0;
    }

    public int getInteger(int i2) {
        for (int i3 = 0; i3 < this.f1840c; i3++) {
            if (this.f1838a[i3] == i2) {
                return this.f1839b[i3];
            }
        }
        return -1;
    }
}
