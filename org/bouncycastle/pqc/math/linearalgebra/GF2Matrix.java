package org.bouncycastle.pqc.math.linearalgebra;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.apache.http.message.TokenParser;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class GF2Matrix extends Matrix {
    private int length;
    private int[][] matrix;

    public GF2Matrix(int i2, char c2) {
        this(i2, c2, new SecureRandom());
    }

    public GF2Matrix(int i2, char c2, SecureRandom secureRandom) {
        if (i2 <= 0) {
            throw new ArithmeticException("Size of matrix is non-positive.");
        }
        if (c2 == 'I') {
            assignUnitMatrix(i2);
        } else if (c2 == 'L') {
            assignRandomLowerTriangularMatrix(i2, secureRandom);
        } else if (c2 == 'R') {
            assignRandomRegularMatrix(i2, secureRandom);
        } else if (c2 == 'U') {
            assignRandomUpperTriangularMatrix(i2, secureRandom);
        } else if (c2 != 'Z') {
            throw new ArithmeticException("Unknown matrix type.");
        } else {
            assignZeroMatrix(i2, i2);
        }
    }

    private GF2Matrix(int i2, int i3) {
        if (i3 <= 0 || i2 <= 0) {
            throw new ArithmeticException("size of matrix is non-positive");
        }
        assignZeroMatrix(i2, i3);
    }

    public GF2Matrix(int i2, int[][] iArr) {
        if (iArr[0].length != ((i2 + 31) >> 5)) {
            throw new ArithmeticException("Int array does not match given number of columns.");
        }
        this.f14660b = i2;
        this.f14659a = iArr.length;
        this.length = iArr[0].length;
        int i3 = i2 & 31;
        int i4 = i3 == 0 ? -1 : (1 << i3) - 1;
        for (int i5 = 0; i5 < this.f14659a; i5++) {
            int[] iArr2 = iArr[i5];
            int i6 = this.length - 1;
            iArr2[i6] = iArr2[i6] & i4;
        }
        this.matrix = iArr;
    }

    public GF2Matrix(GF2Matrix gF2Matrix) {
        this.f14660b = gF2Matrix.getNumColumns();
        this.f14659a = gF2Matrix.getNumRows();
        this.length = gF2Matrix.length;
        this.matrix = new int[gF2Matrix.matrix.length];
        int i2 = 0;
        while (true) {
            int[][] iArr = this.matrix;
            if (i2 >= iArr.length) {
                return;
            }
            iArr[i2] = IntUtils.clone(gF2Matrix.matrix[i2]);
            i2++;
        }
    }

    public GF2Matrix(byte[] bArr) {
        if (bArr.length < 9) {
            throw new ArithmeticException("given array is not an encoded matrix over GF(2)");
        }
        this.f14659a = LittleEndianConversions.OS2IP(bArr, 0);
        int OS2IP = LittleEndianConversions.OS2IP(bArr, 4);
        this.f14660b = OS2IP;
        int i2 = this.f14659a;
        int i3 = ((OS2IP + 7) >>> 3) * i2;
        if (i2 > 0) {
            int i4 = 8;
            if (i3 == bArr.length - 8) {
                int i5 = (OS2IP + 31) >>> 5;
                this.length = i5;
                this.matrix = (int[][]) Array.newInstance(int.class, i2, i5);
                int i6 = this.f14660b;
                int i7 = i6 >> 5;
                int i8 = i6 & 31;
                for (int i9 = 0; i9 < this.f14659a; i9++) {
                    int i10 = 0;
                    while (i10 < i7) {
                        this.matrix[i9][i10] = LittleEndianConversions.OS2IP(bArr, i4);
                        i10++;
                        i4 += 4;
                    }
                    int i11 = 0;
                    while (i11 < i8) {
                        int[] iArr = this.matrix[i9];
                        iArr[i7] = ((bArr[i4] & 255) << i11) ^ iArr[i7];
                        i11 += 8;
                        i4++;
                    }
                }
                return;
            }
        }
        throw new ArithmeticException("given array is not an encoded matrix over GF(2)");
    }

    private static void addToRow(int[] iArr, int[] iArr2, int i2) {
        for (int length = iArr2.length - 1; length >= i2; length--) {
            iArr2[length] = iArr[length] ^ iArr2[length];
        }
    }

    private void assignRandomLowerTriangularMatrix(int i2, SecureRandom secureRandom) {
        this.f14659a = i2;
        this.f14660b = i2;
        int i3 = (i2 + 31) >>> 5;
        this.length = i3;
        this.matrix = (int[][]) Array.newInstance(int.class, i2, i3);
        for (int i4 = 0; i4 < this.f14659a; i4++) {
            int i5 = i4 >>> 5;
            int i6 = i4 & 31;
            int i7 = 31 - i6;
            int i8 = 1 << i6;
            for (int i9 = 0; i9 < i5; i9++) {
                this.matrix[i4][i9] = secureRandom.nextInt();
            }
            this.matrix[i4][i5] = i8 | (secureRandom.nextInt() >>> i7);
            while (true) {
                i5++;
                if (i5 < this.length) {
                    this.matrix[i4][i5] = 0;
                }
            }
        }
    }

    private void assignRandomRegularMatrix(int i2, SecureRandom secureRandom) {
        this.f14659a = i2;
        this.f14660b = i2;
        int i3 = (i2 + 31) >>> 5;
        this.length = i3;
        this.matrix = (int[][]) Array.newInstance(int.class, i2, i3);
        GF2Matrix gF2Matrix = (GF2Matrix) new GF2Matrix(i2, Matrix.MATRIX_TYPE_RANDOM_LT, secureRandom).rightMultiply(new GF2Matrix(i2, Matrix.MATRIX_TYPE_RANDOM_UT, secureRandom));
        int[] vector = new Permutation(i2, secureRandom).getVector();
        for (int i4 = 0; i4 < i2; i4++) {
            System.arraycopy(gF2Matrix.matrix[i4], 0, this.matrix[vector[i4]], 0, this.length);
        }
    }

    private void assignRandomUpperTriangularMatrix(int i2, SecureRandom secureRandom) {
        int i3;
        this.f14659a = i2;
        this.f14660b = i2;
        int i4 = (i2 + 31) >>> 5;
        this.length = i4;
        this.matrix = (int[][]) Array.newInstance(int.class, i2, i4);
        int i5 = i2 & 31;
        int i6 = i5 == 0 ? -1 : (1 << i5) - 1;
        for (int i7 = 0; i7 < this.f14659a; i7++) {
            int i8 = i7 >>> 5;
            int i9 = i7 & 31;
            int i10 = 1 << i9;
            for (int i11 = 0; i11 < i8; i11++) {
                this.matrix[i7][i11] = 0;
            }
            this.matrix[i7][i8] = (secureRandom.nextInt() << i9) | i10;
            while (true) {
                i8++;
                i3 = this.length;
                if (i8 < i3) {
                    this.matrix[i7][i8] = secureRandom.nextInt();
                }
            }
            int[] iArr = this.matrix[i7];
            int i12 = i3 - 1;
            iArr[i12] = iArr[i12] & i6;
        }
    }

    private void assignUnitMatrix(int i2) {
        this.f14659a = i2;
        this.f14660b = i2;
        int i3 = (i2 + 31) >>> 5;
        this.length = i3;
        int[] iArr = {i2, i3};
        this.matrix = (int[][]) Array.newInstance(int.class, iArr);
        for (int i4 = 0; i4 < this.f14659a; i4++) {
            for (int i5 = 0; i5 < this.length; i5++) {
                this.matrix[i4][i5] = 0;
            }
        }
        for (int i6 = 0; i6 < this.f14659a; i6++) {
            this.matrix[i6][i6 >>> 5] = 1 << (i6 & 31);
        }
    }

    private void assignZeroMatrix(int i2, int i3) {
        this.f14659a = i2;
        this.f14660b = i3;
        int i4 = (i3 + 31) >>> 5;
        this.length = i4;
        this.matrix = (int[][]) Array.newInstance(int.class, i2, i4);
        for (int i5 = 0; i5 < this.f14659a; i5++) {
            for (int i6 = 0; i6 < this.length; i6++) {
                this.matrix[i5][i6] = 0;
            }
        }
    }

    public static GF2Matrix[] createRandomRegularMatrixAndItsInverse(int i2, SecureRandom secureRandom) {
        GF2Matrix[] gF2MatrixArr = new GF2Matrix[2];
        int i3 = (i2 + 31) >> 5;
        GF2Matrix gF2Matrix = new GF2Matrix(i2, Matrix.MATRIX_TYPE_RANDOM_LT, secureRandom);
        GF2Matrix gF2Matrix2 = new GF2Matrix(i2, Matrix.MATRIX_TYPE_RANDOM_UT, secureRandom);
        GF2Matrix gF2Matrix3 = (GF2Matrix) gF2Matrix.rightMultiply(gF2Matrix2);
        Permutation permutation = new Permutation(i2, secureRandom);
        int[] vector = permutation.getVector();
        int i4 = 0;
        int[][] iArr = (int[][]) Array.newInstance(int.class, i2, i3);
        for (int i5 = 0; i5 < i2; i5++) {
            System.arraycopy(gF2Matrix3.matrix[vector[i5]], 0, iArr[i5], 0, i3);
        }
        gF2MatrixArr[0] = new GF2Matrix(i2, iArr);
        GF2Matrix gF2Matrix4 = new GF2Matrix(i2, 'I');
        int i6 = 0;
        while (i6 < i2) {
            int i7 = i6 >>> 5;
            int i8 = 1 << (i6 & 31);
            int i9 = i6 + 1;
            int i10 = i9;
            while (i10 < i2) {
                if ((gF2Matrix.matrix[i10][i7] & i8) != 0) {
                    for (int i11 = i4; i11 <= i7; i11++) {
                        int[][] iArr2 = gF2Matrix4.matrix;
                        int[] iArr3 = iArr2[i10];
                        iArr3[i11] = iArr3[i11] ^ iArr2[i6][i11];
                    }
                }
                i10++;
                i4 = 0;
            }
            i6 = i9;
        }
        GF2Matrix gF2Matrix5 = new GF2Matrix(i2, 'I');
        for (int i12 = i2 - 1; i12 >= 0; i12--) {
            int i13 = i12 >>> 5;
            int i14 = 1 << (i12 & 31);
            for (int i15 = i12 - 1; i15 >= 0; i15--) {
                if ((gF2Matrix2.matrix[i15][i13] & i14) != 0) {
                    for (int i16 = i13; i16 < i3; i16++) {
                        int[][] iArr4 = gF2Matrix5.matrix;
                        int[] iArr5 = iArr4[i15];
                        iArr5[i16] = iArr4[i12][i16] ^ iArr5[i16];
                    }
                }
            }
        }
        gF2MatrixArr[1] = (GF2Matrix) gF2Matrix5.rightMultiply(gF2Matrix4.rightMultiply(permutation));
        return gF2MatrixArr;
    }

    private static void swapRows(int[][] iArr, int i2, int i3) {
        int[] iArr2 = iArr[i2];
        iArr[i2] = iArr[i3];
        iArr[i3] = iArr2;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Matrix computeInverse() {
        int i2 = this.f14659a;
        if (i2 == this.f14660b) {
            int[][] iArr = (int[][]) Array.newInstance(int.class, i2, this.length);
            for (int i3 = this.f14659a - 1; i3 >= 0; i3--) {
                iArr[i3] = IntUtils.clone(this.matrix[i3]);
            }
            int[][] iArr2 = (int[][]) Array.newInstance(int.class, this.f14659a, this.length);
            for (int i4 = this.f14659a - 1; i4 >= 0; i4--) {
                iArr2[i4][i4 >> 5] = 1 << (i4 & 31);
            }
            for (int i5 = 0; i5 < this.f14659a; i5++) {
                int i6 = i5 >> 5;
                int i7 = 1 << (i5 & 31);
                if ((iArr[i5][i6] & i7) == 0) {
                    int i8 = i5 + 1;
                    boolean z = false;
                    while (i8 < this.f14659a) {
                        if ((iArr[i8][i6] & i7) != 0) {
                            swapRows(iArr, i5, i8);
                            swapRows(iArr2, i5, i8);
                            i8 = this.f14659a;
                            z = true;
                        }
                        i8++;
                    }
                    if (!z) {
                        throw new ArithmeticException("Matrix is not invertible.");
                    }
                }
                for (int i9 = this.f14659a - 1; i9 >= 0; i9--) {
                    if (i9 != i5 && (iArr[i9][i6] & i7) != 0) {
                        addToRow(iArr[i5], iArr[i9], i6);
                        addToRow(iArr2[i5], iArr2[i9], 0);
                    }
                }
            }
            return new GF2Matrix(this.f14660b, iArr2);
        }
        throw new ArithmeticException("Matrix is not invertible.");
    }

    public Matrix computeTranspose() {
        int[][] iArr = (int[][]) Array.newInstance(int.class, this.f14660b, (this.f14659a + 31) >>> 5);
        int i2 = 0;
        while (true) {
            int i3 = this.f14659a;
            if (i2 >= i3) {
                return new GF2Matrix(i3, iArr);
            }
            for (int i4 = 0; i4 < this.f14660b; i4++) {
                int i5 = i2 >>> 5;
                int i6 = i2 & 31;
                if (((this.matrix[i2][i4 >>> 5] >>> (i4 & 31)) & 1) == 1) {
                    int[] iArr2 = iArr[i4];
                    iArr2[i5] = (1 << i6) | iArr2[i5];
                }
            }
            i2++;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof GF2Matrix) {
            GF2Matrix gF2Matrix = (GF2Matrix) obj;
            if (this.f14659a == gF2Matrix.f14659a && this.f14660b == gF2Matrix.f14660b && this.length == gF2Matrix.length) {
                for (int i2 = 0; i2 < this.f14659a; i2++) {
                    if (!IntUtils.equals(this.matrix[i2], gF2Matrix.matrix[i2])) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public GF2Matrix extendLeftCompactForm() {
        int i2 = this.f14660b;
        int i3 = this.f14659a;
        GF2Matrix gF2Matrix = new GF2Matrix(i3, i2 + i3);
        int i4 = this.f14659a;
        int i5 = (i4 - 1) + this.f14660b;
        int i6 = i4 - 1;
        while (i6 >= 0) {
            System.arraycopy(this.matrix[i6], 0, gF2Matrix.matrix[i6], 0, this.length);
            int[] iArr = gF2Matrix.matrix[i6];
            int i7 = i5 >> 5;
            iArr[i7] = iArr[i7] | (1 << (i5 & 31));
            i6--;
            i5--;
        }
        return gF2Matrix;
    }

    public GF2Matrix extendRightCompactForm() {
        int i2;
        int i3 = this.f14659a;
        GF2Matrix gF2Matrix = new GF2Matrix(i3, this.f14660b + i3);
        int i4 = this.f14659a;
        int i5 = i4 >> 5;
        int i6 = i4 & 31;
        for (int i7 = i4 - 1; i7 >= 0; i7--) {
            int[][] iArr = gF2Matrix.matrix;
            int[] iArr2 = iArr[i7];
            int i8 = i7 >> 5;
            iArr2[i8] = iArr2[i8] | (1 << (i7 & 31));
            int i9 = 0;
            if (i6 != 0) {
                int i10 = i5;
                while (true) {
                    i2 = this.length;
                    if (i9 >= i2 - 1) {
                        break;
                    }
                    int i11 = this.matrix[i7][i9];
                    int[][] iArr3 = gF2Matrix.matrix;
                    int[] iArr4 = iArr3[i7];
                    int i12 = i10 + 1;
                    iArr4[i10] = iArr4[i10] | (i11 << i6);
                    int[] iArr5 = iArr3[i7];
                    iArr5[i12] = (i11 >>> (32 - i6)) | iArr5[i12];
                    i9++;
                    i10 = i12;
                }
                int i13 = this.matrix[i7][i2 - 1];
                int[][] iArr6 = gF2Matrix.matrix;
                int[] iArr7 = iArr6[i7];
                int i14 = i10 + 1;
                iArr7[i10] = iArr7[i10] | (i13 << i6);
                if (i14 < gF2Matrix.length) {
                    int[] iArr8 = iArr6[i7];
                    iArr8[i14] = (i13 >>> (32 - i6)) | iArr8[i14];
                }
            } else {
                System.arraycopy(this.matrix[i7], 0, iArr[i7], i5, this.length);
            }
        }
        return gF2Matrix;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public byte[] getEncoded() {
        int i2 = this.f14659a;
        int i3 = 8;
        byte[] bArr = new byte[(((this.f14660b + 7) >>> 3) * i2) + 8];
        LittleEndianConversions.I2OSP(i2, bArr, 0);
        LittleEndianConversions.I2OSP(this.f14660b, bArr, 4);
        int i4 = this.f14660b;
        int i5 = i4 >>> 5;
        int i6 = i4 & 31;
        for (int i7 = 0; i7 < this.f14659a; i7++) {
            int i8 = 0;
            while (i8 < i5) {
                LittleEndianConversions.I2OSP(this.matrix[i7][i8], bArr, i3);
                i8++;
                i3 += 4;
            }
            int i9 = 0;
            while (i9 < i6) {
                bArr[i3] = (byte) ((this.matrix[i7][i5] >>> i9) & 255);
                i9 += 8;
                i3++;
            }
        }
        return bArr;
    }

    public double getHammingWeight() {
        int i2 = this.f14660b & 31;
        int i3 = this.length;
        if (i2 != 0) {
            i3--;
        }
        double d2 = 0.0d;
        double d3 = 0.0d;
        for (int i4 = 0; i4 < this.f14659a; i4++) {
            for (int i5 = 0; i5 < i3; i5++) {
                int i6 = this.matrix[i4][i5];
                for (int i7 = 0; i7 < 32; i7++) {
                    d2 += (i6 >>> i7) & 1;
                    d3 += 1.0d;
                }
            }
            int i8 = this.matrix[i4][this.length - 1];
            for (int i9 = 0; i9 < i2; i9++) {
                d2 += (i8 >>> i9) & 1;
                d3 += 1.0d;
            }
        }
        return d2 / d3;
    }

    public int[][] getIntArray() {
        return this.matrix;
    }

    public GF2Matrix getLeftSubMatrix() {
        int i2 = this.f14660b;
        int i3 = this.f14659a;
        if (i2 > i3) {
            int i4 = (i3 + 31) >> 5;
            int[][] iArr = (int[][]) Array.newInstance(int.class, i3, i4);
            int i5 = this.f14659a;
            int i6 = (1 << (i5 & 31)) - 1;
            if (i6 == 0) {
                i6 = -1;
            }
            for (int i7 = i5 - 1; i7 >= 0; i7--) {
                System.arraycopy(this.matrix[i7], 0, iArr[i7], 0, i4);
                int[] iArr2 = iArr[i7];
                int i8 = i4 - 1;
                iArr2[i8] = iArr2[i8] & i6;
            }
            return new GF2Matrix(this.f14659a, iArr);
        }
        throw new ArithmeticException("empty submatrix");
    }

    public int getLength() {
        return this.length;
    }

    public GF2Matrix getRightSubMatrix() {
        int i2;
        int i3 = this.f14660b;
        int i4 = this.f14659a;
        if (i3 > i4) {
            int i5 = i4 >> 5;
            int i6 = i4 & 31;
            GF2Matrix gF2Matrix = new GF2Matrix(i4, i3 - i4);
            for (int i7 = this.f14659a - 1; i7 >= 0; i7--) {
                int i8 = 0;
                if (i6 != 0) {
                    int i9 = i5;
                    while (true) {
                        i2 = gF2Matrix.length;
                        if (i8 >= i2 - 1) {
                            break;
                        }
                        int[] iArr = gF2Matrix.matrix[i7];
                        int[][] iArr2 = this.matrix;
                        int i10 = i9 + 1;
                        iArr[i8] = (iArr2[i7][i9] >>> i6) | (iArr2[i7][i10] << (32 - i6));
                        i8++;
                        i9 = i10;
                    }
                    int[][] iArr3 = gF2Matrix.matrix;
                    int[][] iArr4 = this.matrix;
                    int i11 = i9 + 1;
                    iArr3[i7][i2 - 1] = iArr4[i7][i9] >>> i6;
                    if (i11 < this.length) {
                        int[] iArr5 = iArr3[i7];
                        int i12 = i2 - 1;
                        iArr5[i12] = iArr5[i12] | (iArr4[i7][i11] << (32 - i6));
                    }
                } else {
                    System.arraycopy(this.matrix[i7], i5, gF2Matrix.matrix[i7], 0, gF2Matrix.length);
                }
            }
            return gF2Matrix;
        }
        throw new ArithmeticException("empty submatrix");
    }

    public int[] getRow(int i2) {
        return this.matrix[i2];
    }

    public int hashCode() {
        int i2 = (((this.f14659a * 31) + this.f14660b) * 31) + this.length;
        for (int i3 = 0; i3 < this.f14659a; i3++) {
            i2 = (i2 * 31) + Arrays.hashCode(this.matrix[i3]);
        }
        return i2;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public boolean isZero() {
        for (int i2 = 0; i2 < this.f14659a; i2++) {
            for (int i3 = 0; i3 < this.length; i3++) {
                if (this.matrix[i2][i3] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public Matrix leftMultiply(Permutation permutation) {
        int[] vector = permutation.getVector();
        int length = vector.length;
        int i2 = this.f14659a;
        if (length == i2) {
            int[][] iArr = new int[i2];
            for (int i3 = i2 - 1; i3 >= 0; i3--) {
                iArr[i3] = IntUtils.clone(this.matrix[vector[i3]]);
            }
            return new GF2Matrix(this.f14659a, iArr);
        }
        throw new ArithmeticException("length mismatch");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Vector leftMultiply(Vector vector) {
        if (vector instanceof GF2Vector) {
            if (vector.f14664a == this.f14659a) {
                int[] vecArray = ((GF2Vector) vector).getVecArray();
                int[] iArr = new int[this.length];
                int i2 = this.f14659a;
                int i3 = i2 >> 5;
                int i4 = 1 << (i2 & 31);
                int i5 = 0;
                for (int i6 = 0; i6 < i3; i6++) {
                    int i7 = 1;
                    do {
                        if ((vecArray[i6] & i7) != 0) {
                            for (int i8 = 0; i8 < this.length; i8++) {
                                iArr[i8] = iArr[i8] ^ this.matrix[i5][i8];
                            }
                        }
                        i5++;
                        i7 <<= 1;
                    } while (i7 != 0);
                }
                for (int i9 = 1; i9 != i4; i9 <<= 1) {
                    if ((vecArray[i3] & i9) != 0) {
                        for (int i10 = 0; i10 < this.length; i10++) {
                            iArr[i10] = iArr[i10] ^ this.matrix[i5][i10];
                        }
                    }
                    i5++;
                }
                return new GF2Vector(iArr, this.f14660b);
            }
            throw new ArithmeticException("length mismatch");
        }
        throw new ArithmeticException("vector is not defined over GF(2)");
    }

    public Vector leftMultiplyLeftCompactForm(Vector vector) {
        if (vector instanceof GF2Vector) {
            if (vector.f14664a == this.f14659a) {
                int[] vecArray = ((GF2Vector) vector).getVecArray();
                int i2 = this.f14659a;
                int[] iArr = new int[((this.f14660b + i2) + 31) >>> 5];
                int i3 = i2 >>> 5;
                int i4 = 0;
                for (int i5 = 0; i5 < i3; i5++) {
                    int i6 = 1;
                    do {
                        if ((vecArray[i5] & i6) != 0) {
                            for (int i7 = 0; i7 < this.length; i7++) {
                                iArr[i7] = iArr[i7] ^ this.matrix[i4][i7];
                            }
                            int i8 = this.f14660b;
                            int i9 = (i8 + i4) >>> 5;
                            iArr[i9] = (1 << ((i8 + i4) & 31)) | iArr[i9];
                        }
                        i4++;
                        i6 <<= 1;
                    } while (i6 != 0);
                }
                int i10 = 1 << (this.f14659a & 31);
                for (int i11 = 1; i11 != i10; i11 <<= 1) {
                    if ((vecArray[i3] & i11) != 0) {
                        for (int i12 = 0; i12 < this.length; i12++) {
                            iArr[i12] = iArr[i12] ^ this.matrix[i4][i12];
                        }
                        int i13 = this.f14660b;
                        int i14 = (i13 + i4) >>> 5;
                        iArr[i14] = (1 << ((i13 + i4) & 31)) | iArr[i14];
                    }
                    i4++;
                }
                return new GF2Vector(iArr, this.f14659a + this.f14660b);
            }
            throw new ArithmeticException("length mismatch");
        }
        throw new ArithmeticException("vector is not defined over GF(2)");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Matrix rightMultiply(Matrix matrix) {
        if (matrix instanceof GF2Matrix) {
            if (matrix.f14659a == this.f14660b) {
                GF2Matrix gF2Matrix = (GF2Matrix) matrix;
                GF2Matrix gF2Matrix2 = new GF2Matrix(this.f14659a, matrix.f14660b);
                int i2 = this.f14660b & 31;
                int i3 = this.length;
                if (i2 != 0) {
                    i3--;
                }
                for (int i4 = 0; i4 < this.f14659a; i4++) {
                    int i5 = 0;
                    for (int i6 = 0; i6 < i3; i6++) {
                        int i7 = this.matrix[i4][i6];
                        for (int i8 = 0; i8 < 32; i8++) {
                            if (((1 << i8) & i7) != 0) {
                                for (int i9 = 0; i9 < gF2Matrix.length; i9++) {
                                    int[] iArr = gF2Matrix2.matrix[i4];
                                    iArr[i9] = iArr[i9] ^ gF2Matrix.matrix[i5][i9];
                                }
                            }
                            i5++;
                        }
                    }
                    int i10 = this.matrix[i4][this.length - 1];
                    for (int i11 = 0; i11 < i2; i11++) {
                        if (((1 << i11) & i10) != 0) {
                            for (int i12 = 0; i12 < gF2Matrix.length; i12++) {
                                int[] iArr2 = gF2Matrix2.matrix[i4];
                                iArr2[i12] = iArr2[i12] ^ gF2Matrix.matrix[i5][i12];
                            }
                        }
                        i5++;
                    }
                }
                return gF2Matrix2;
            }
            throw new ArithmeticException("length mismatch");
        }
        throw new ArithmeticException("matrix is not defined over GF(2)");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Matrix rightMultiply(Permutation permutation) {
        int[] vector = permutation.getVector();
        int length = vector.length;
        int i2 = this.f14660b;
        if (length == i2) {
            GF2Matrix gF2Matrix = new GF2Matrix(this.f14659a, i2);
            for (int i3 = this.f14660b - 1; i3 >= 0; i3--) {
                int i4 = i3 >>> 5;
                int i5 = i3 & 31;
                int i6 = vector[i3] >>> 5;
                int i7 = vector[i3] & 31;
                for (int i8 = this.f14659a - 1; i8 >= 0; i8--) {
                    int[] iArr = gF2Matrix.matrix[i8];
                    iArr[i4] = iArr[i4] | (((this.matrix[i8][i6] >>> i7) & 1) << i5);
                }
            }
            return gF2Matrix;
        }
        throw new ArithmeticException("length mismatch");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Vector rightMultiply(Vector vector) {
        if (!(vector instanceof GF2Vector)) {
            throw new ArithmeticException("vector is not defined over GF(2)");
        }
        if (vector.f14664a != this.f14660b) {
            throw new ArithmeticException("length mismatch");
        }
        int[] vecArray = ((GF2Vector) vector).getVecArray();
        int[] iArr = new int[(this.f14659a + 31) >>> 5];
        int i2 = 0;
        while (true) {
            int i3 = this.f14659a;
            if (i2 >= i3) {
                return new GF2Vector(iArr, i3);
            }
            int i4 = 0;
            for (int i5 = 0; i5 < this.length; i5++) {
                i4 ^= this.matrix[i2][i5] & vecArray[i5];
            }
            int i6 = 0;
            for (int i7 = 0; i7 < 32; i7++) {
                i6 ^= (i4 >>> i7) & 1;
            }
            if (i6 == 1) {
                int i8 = i2 >>> 5;
                iArr[i8] = iArr[i8] | (1 << (i2 & 31));
            }
            i2++;
        }
    }

    public Vector rightMultiplyRightCompactForm(Vector vector) {
        int i2;
        if (!(vector instanceof GF2Vector)) {
            throw new ArithmeticException("vector is not defined over GF(2)");
        }
        if (vector.f14664a != this.f14660b + this.f14659a) {
            throw new ArithmeticException("length mismatch");
        }
        int[] vecArray = ((GF2Vector) vector).getVecArray();
        int i3 = this.f14659a;
        int[] iArr = new int[(i3 + 31) >>> 5];
        int i4 = i3 >> 5;
        int i5 = i3 & 31;
        int i6 = 0;
        while (true) {
            int i7 = this.f14659a;
            if (i6 >= i7) {
                return new GF2Vector(iArr, i7);
            }
            int i8 = i6 >> 5;
            int i9 = i6 & 31;
            int i10 = (vecArray[i8] >>> i9) & 1;
            int i11 = i4;
            int i12 = 0;
            if (i5 != 0) {
                while (true) {
                    i2 = this.length;
                    if (i12 >= i2 - 1) {
                        break;
                    }
                    int i13 = i11 + 1;
                    i10 ^= ((vecArray[i11] >>> i5) | (vecArray[i13] << (32 - i5))) & this.matrix[i6][i12];
                    i12++;
                    i11 = i13;
                }
                int i14 = i11 + 1;
                int i15 = vecArray[i11] >>> i5;
                if (i14 < vecArray.length) {
                    i15 |= vecArray[i14] << (32 - i5);
                }
                i10 ^= this.matrix[i6][i2 - 1] & i15;
            } else {
                while (i12 < this.length) {
                    i10 ^= vecArray[i11] & this.matrix[i6][i12];
                    i12++;
                    i11++;
                }
            }
            int i16 = 0;
            for (int i17 = 0; i17 < 32; i17++) {
                i16 ^= i10 & 1;
                i10 >>>= 1;
            }
            if (i16 == 1) {
                iArr[i8] = iArr[i8] | (1 << i9);
            }
            i6++;
        }
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public String toString() {
        int i2 = this.f14660b & 31;
        int i3 = this.length;
        if (i2 != 0) {
            i3--;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i4 = 0; i4 < this.f14659a; i4++) {
            stringBuffer.append(i4 + ": ");
            for (int i5 = 0; i5 < i3; i5++) {
                int i6 = this.matrix[i4][i5];
                for (int i7 = 0; i7 < 32; i7++) {
                    if (((i6 >>> i7) & 1) == 0) {
                        stringBuffer.append('0');
                    } else {
                        stringBuffer.append('1');
                    }
                }
                stringBuffer.append(TokenParser.SP);
            }
            int i8 = this.matrix[i4][this.length - 1];
            for (int i9 = 0; i9 < i2; i9++) {
                if (((i8 >>> i9) & 1) == 0) {
                    stringBuffer.append('0');
                } else {
                    stringBuffer.append('1');
                }
            }
            stringBuffer.append('\n');
        }
        return stringBuffer.toString();
    }
}
