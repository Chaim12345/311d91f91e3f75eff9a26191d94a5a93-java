package org.bouncycastle.pqc.math.linearalgebra;

import java.lang.reflect.Array;
import java.security.SecureRandom;
/* loaded from: classes4.dex */
public final class GoppaCode {

    /* loaded from: classes4.dex */
    public static class MaMaPe {

        /* renamed from: h  reason: collision with root package name */
        private GF2Matrix f14655h;

        /* renamed from: p  reason: collision with root package name */
        private Permutation f14656p;

        /* renamed from: s  reason: collision with root package name */
        private GF2Matrix f14657s;

        public MaMaPe(GF2Matrix gF2Matrix, GF2Matrix gF2Matrix2, Permutation permutation) {
            this.f14657s = gF2Matrix;
            this.f14655h = gF2Matrix2;
            this.f14656p = permutation;
        }

        public GF2Matrix getFirstMatrix() {
            return this.f14657s;
        }

        public Permutation getPermutation() {
            return this.f14656p;
        }

        public GF2Matrix getSecondMatrix() {
            return this.f14655h;
        }
    }

    /* loaded from: classes4.dex */
    public static class MatrixSet {

        /* renamed from: g  reason: collision with root package name */
        private GF2Matrix f14658g;
        private int[] setJ;

        public MatrixSet(GF2Matrix gF2Matrix, int[] iArr) {
            this.f14658g = gF2Matrix;
            this.setJ = iArr;
        }

        public GF2Matrix getG() {
            return this.f14658g;
        }

        public int[] getSetJ() {
            return this.setJ;
        }
    }

    private GoppaCode() {
    }

    public static MaMaPe computeSystematicForm(GF2Matrix gF2Matrix, SecureRandom secureRandom) {
        Permutation permutation;
        GF2Matrix gF2Matrix2;
        GF2Matrix leftSubMatrix;
        boolean z;
        int numColumns = gF2Matrix.getNumColumns();
        GF2Matrix gF2Matrix3 = null;
        do {
            permutation = new Permutation(numColumns, secureRandom);
            gF2Matrix2 = (GF2Matrix) gF2Matrix.rightMultiply(permutation);
            leftSubMatrix = gF2Matrix2.getLeftSubMatrix();
            z = true;
            try {
                gF2Matrix3 = (GF2Matrix) leftSubMatrix.computeInverse();
                continue;
            } catch (ArithmeticException unused) {
                z = false;
                continue;
            }
        } while (!z);
        return new MaMaPe(leftSubMatrix, ((GF2Matrix) gF2Matrix3.rightMultiply(gF2Matrix2)).getRightSubMatrix(), permutation);
    }

    public static GF2Matrix createCanonicalCheckMatrix(GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM) {
        int degree = gF2mField.getDegree();
        int i2 = 1 << degree;
        int degree2 = polynomialGF2mSmallM.getDegree();
        int i3 = 0;
        int[][] iArr = (int[][]) Array.newInstance(int.class, degree2, i2);
        int[][] iArr2 = (int[][]) Array.newInstance(int.class, degree2, i2);
        for (int i4 = 0; i4 < i2; i4++) {
            iArr2[0][i4] = gF2mField.inverse(polynomialGF2mSmallM.evaluateAt(i4));
        }
        for (int i5 = 1; i5 < degree2; i5++) {
            for (int i6 = 0; i6 < i2; i6++) {
                iArr2[i5][i6] = gF2mField.mult(iArr2[i5 - 1][i6], i6);
            }
        }
        int i7 = 0;
        while (i7 < degree2) {
            int i8 = i3;
            while (i8 < i2) {
                for (int i9 = i3; i9 <= i7; i9++) {
                    iArr[i7][i8] = gF2mField.add(iArr[i7][i8], gF2mField.mult(iArr2[i9][i8], polynomialGF2mSmallM.getCoefficient((degree2 + i9) - i7)));
                }
                i8++;
                i3 = 0;
            }
            i7++;
            i3 = 0;
        }
        int[][] iArr3 = (int[][]) Array.newInstance(int.class, degree2 * degree, (i2 + 31) >>> 5);
        for (int i10 = 0; i10 < i2; i10++) {
            int i11 = i10 >>> 5;
            int i12 = 1 << (i10 & 31);
            for (int i13 = 0; i13 < degree2; i13++) {
                int i14 = iArr[i13][i10];
                for (int i15 = 0; i15 < degree; i15++) {
                    if (((i14 >>> i15) & 1) != 0) {
                        int[] iArr4 = iArr3[(((i13 + 1) * degree) - i15) - 1];
                        iArr4[i11] = iArr4[i11] ^ i12;
                    }
                }
            }
        }
        return new GF2Matrix(i2, iArr3);
    }

    public static GF2Vector syndromeDecode(GF2Vector gF2Vector, GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM, PolynomialGF2mSmallM[] polynomialGF2mSmallMArr) {
        int degree = 1 << gF2mField.getDegree();
        GF2Vector gF2Vector2 = new GF2Vector(degree);
        if (!gF2Vector.isZero()) {
            PolynomialGF2mSmallM[] modPolynomialToFracton = new PolynomialGF2mSmallM(gF2Vector.toExtensionFieldVector(gF2mField)).modInverse(polynomialGF2mSmallM).addMonomial(1).modSquareRootMatrix(polynomialGF2mSmallMArr).modPolynomialToFracton(polynomialGF2mSmallM);
            PolynomialGF2mSmallM add = modPolynomialToFracton[0].multiply(modPolynomialToFracton[0]).add(modPolynomialToFracton[1].multiply(modPolynomialToFracton[1]).multWithMonomial(1));
            PolynomialGF2mSmallM multWithElement = add.multWithElement(gF2mField.inverse(add.getHeadCoefficient()));
            for (int i2 = 0; i2 < degree; i2++) {
                if (multWithElement.evaluateAt(i2) == 0) {
                    gF2Vector2.setBit(i2);
                }
            }
        }
        return gF2Vector2;
    }
}
