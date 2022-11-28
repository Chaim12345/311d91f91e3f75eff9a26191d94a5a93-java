package org.bouncycastle.pqc.math.linearalgebra;

import java.lang.reflect.Array;
/* loaded from: classes4.dex */
public class GF2mMatrix extends Matrix {

    /* renamed from: c  reason: collision with root package name */
    protected GF2mField f14644c;

    /* renamed from: d  reason: collision with root package name */
    protected int[][] f14645d;

    public GF2mMatrix(GF2mField gF2mField, byte[] bArr) {
        this.f14644c = gF2mField;
        int i2 = 8;
        int i3 = 1;
        while (gF2mField.getDegree() > i2) {
            i3++;
            i2 += 8;
        }
        if (bArr.length < 5) {
            throw new IllegalArgumentException(" Error: given array is not encoded matrix over GF(2^m)");
        }
        int i4 = ((((bArr[3] & 255) << 24) ^ ((bArr[2] & 255) << 16)) ^ ((bArr[1] & 255) << 8)) ^ (bArr[0] & 255);
        this.f14659a = i4;
        int i5 = i3 * i4;
        if (i4 > 0) {
            int i6 = 4;
            if ((bArr.length - 4) % i5 == 0) {
                int length = (bArr.length - 4) / i5;
                this.f14660b = length;
                this.f14645d = (int[][]) Array.newInstance(int.class, i4, length);
                for (int i7 = 0; i7 < this.f14659a; i7++) {
                    for (int i8 = 0; i8 < this.f14660b; i8++) {
                        int i9 = 0;
                        while (i9 < i2) {
                            int[] iArr = this.f14645d[i7];
                            iArr[i8] = iArr[i8] ^ ((bArr[i6] & 255) << i9);
                            i9 += 8;
                            i6++;
                        }
                        if (!this.f14644c.isElementOfThisField(this.f14645d[i7][i8])) {
                            throw new IllegalArgumentException(" Error: given array is not encoded matrix over GF(2^m)");
                        }
                    }
                }
                return;
            }
        }
        throw new IllegalArgumentException(" Error: given array is not encoded matrix over GF(2^m)");
    }

    protected GF2mMatrix(GF2mField gF2mField, int[][] iArr) {
        this.f14644c = gF2mField;
        this.f14645d = iArr;
        this.f14659a = iArr.length;
        this.f14660b = iArr[0].length;
    }

    public GF2mMatrix(GF2mMatrix gF2mMatrix) {
        int i2 = gF2mMatrix.f14659a;
        this.f14659a = i2;
        this.f14660b = gF2mMatrix.f14660b;
        this.f14644c = gF2mMatrix.f14644c;
        this.f14645d = new int[i2];
        for (int i3 = 0; i3 < this.f14659a; i3++) {
            this.f14645d[i3] = IntUtils.clone(gF2mMatrix.f14645d[i3]);
        }
    }

    private void addToRow(int[] iArr, int[] iArr2) {
        for (int length = iArr2.length - 1; length >= 0; length--) {
            iArr2[length] = this.f14644c.add(iArr[length], iArr2[length]);
        }
    }

    private int[] multRowWithElement(int[] iArr, int i2) {
        int[] iArr2 = new int[iArr.length];
        for (int length = iArr.length - 1; length >= 0; length--) {
            iArr2[length] = this.f14644c.mult(iArr[length], i2);
        }
        return iArr2;
    }

    private void multRowWithElementThis(int[] iArr, int i2) {
        for (int length = iArr.length - 1; length >= 0; length--) {
            iArr[length] = this.f14644c.mult(iArr[length], i2);
        }
    }

    private static void swapColumns(int[][] iArr, int i2, int i3) {
        int[] iArr2 = iArr[i2];
        iArr[i2] = iArr[i3];
        iArr[i3] = iArr2;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Matrix computeInverse() {
        int i2;
        int i3 = this.f14659a;
        if (i3 == this.f14660b) {
            int[][] iArr = (int[][]) Array.newInstance(int.class, i3, i3);
            for (int i4 = this.f14659a - 1; i4 >= 0; i4--) {
                iArr[i4] = IntUtils.clone(this.f14645d[i4]);
            }
            int i5 = this.f14659a;
            int[][] iArr2 = (int[][]) Array.newInstance(int.class, i5, i5);
            for (int i6 = this.f14659a - 1; i6 >= 0; i6--) {
                iArr2[i6][i6] = 1;
            }
            for (int i7 = 0; i7 < this.f14659a; i7++) {
                if (iArr[i7][i7] == 0) {
                    int i8 = i7 + 1;
                    boolean z = false;
                    while (i8 < this.f14659a) {
                        if (iArr[i8][i7] != 0) {
                            swapColumns(iArr, i7, i8);
                            swapColumns(iArr2, i7, i8);
                            i8 = this.f14659a;
                            z = true;
                        }
                        i8++;
                    }
                    if (!z) {
                        throw new ArithmeticException("Matrix is not invertible.");
                    }
                }
                int inverse = this.f14644c.inverse(iArr[i7][i7]);
                multRowWithElementThis(iArr[i7], inverse);
                multRowWithElementThis(iArr2[i7], inverse);
                for (int i9 = 0; i9 < this.f14659a; i9++) {
                    if (i9 != i7 && (i2 = iArr[i9][i7]) != 0) {
                        int[] multRowWithElement = multRowWithElement(iArr[i7], i2);
                        int[] multRowWithElement2 = multRowWithElement(iArr2[i7], i2);
                        addToRow(multRowWithElement, iArr[i9]);
                        addToRow(multRowWithElement2, iArr2[i9]);
                    }
                }
            }
            return new GF2mMatrix(this.f14644c, iArr2);
        }
        throw new ArithmeticException("Matrix is not invertible.");
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof GF2mMatrix)) {
            GF2mMatrix gF2mMatrix = (GF2mMatrix) obj;
            if (this.f14644c.equals(gF2mMatrix.f14644c)) {
                int i2 = gF2mMatrix.f14659a;
                int i3 = this.f14660b;
                if (i2 == i3 && gF2mMatrix.f14660b == i3) {
                    for (int i4 = 0; i4 < this.f14659a; i4++) {
                        for (int i5 = 0; i5 < this.f14660b; i5++) {
                            if (this.f14645d[i4][i5] != gF2mMatrix.f14645d[i4][i5]) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public byte[] getEncoded() {
        int i2 = 8;
        int i3 = 1;
        while (this.f14644c.getDegree() > i2) {
            i3++;
            i2 += 8;
        }
        int i4 = this.f14659a;
        int i5 = this.f14660b * i4 * i3;
        int i6 = 4;
        byte[] bArr = new byte[i5 + 4];
        bArr[0] = (byte) (i4 & 255);
        bArr[1] = (byte) ((i4 >>> 8) & 255);
        bArr[2] = (byte) ((i4 >>> 16) & 255);
        bArr[3] = (byte) ((i4 >>> 24) & 255);
        for (int i7 = 0; i7 < this.f14659a; i7++) {
            for (int i8 = 0; i8 < this.f14660b; i8++) {
                int i9 = 0;
                while (i9 < i2) {
                    bArr[i6] = (byte) (this.f14645d[i7][i8] >>> i9);
                    i9 += 8;
                    i6++;
                }
            }
        }
        return bArr;
    }

    public int hashCode() {
        int hashCode = (((this.f14644c.hashCode() * 31) + this.f14659a) * 31) + this.f14660b;
        for (int i2 = 0; i2 < this.f14659a; i2++) {
            for (int i3 = 0; i3 < this.f14660b; i3++) {
                hashCode = (hashCode * 31) + this.f14645d[i2][i3];
            }
        }
        return hashCode;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public boolean isZero() {
        for (int i2 = 0; i2 < this.f14659a; i2++) {
            for (int i3 = 0; i3 < this.f14660b; i3++) {
                if (this.f14645d[i2][i3] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Vector leftMultiply(Vector vector) {
        throw new RuntimeException("Not implemented.");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Matrix rightMultiply(Matrix matrix) {
        throw new RuntimeException("Not implemented.");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Matrix rightMultiply(Permutation permutation) {
        throw new RuntimeException("Not implemented.");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Vector rightMultiply(Vector vector) {
        throw new RuntimeException("Not implemented.");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public String toString() {
        String str = this.f14659a + " x " + this.f14660b + " Matrix over " + this.f14644c.toString() + ": \n";
        for (int i2 = 0; i2 < this.f14659a; i2++) {
            for (int i3 = 0; i3 < this.f14660b; i3++) {
                str = str + this.f14644c.elementToStr(this.f14645d[i2][i3]) + " : ";
            }
            str = str + "\n";
        }
        return str;
    }
}
