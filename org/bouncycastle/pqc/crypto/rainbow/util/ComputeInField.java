package org.bouncycastle.pqc.crypto.rainbow.util;

import java.lang.reflect.Array;
/* loaded from: classes4.dex */
public class ComputeInField {
    private short[][] A;

    /* renamed from: a  reason: collision with root package name */
    short[] f14565a;

    private void computeZerosAbove() {
        for (int length = this.A.length - 1; length > 0; length--) {
            for (int i2 = length - 1; i2 >= 0; i2--) {
                short[][] sArr = this.A;
                short s2 = sArr[i2][length];
                short invElem = GF2Field.invElem(sArr[length][length]);
                if (invElem == 0) {
                    throw new RuntimeException("The matrix is not invertible");
                }
                int i3 = length;
                while (true) {
                    short[][] sArr2 = this.A;
                    if (i3 < sArr2.length * 2) {
                        short multElem = GF2Field.multElem(s2, GF2Field.multElem(sArr2[length][i3], invElem));
                        short[][] sArr3 = this.A;
                        sArr3[i2][i3] = GF2Field.addElem(sArr3[i2][i3], multElem);
                        i3++;
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0058, code lost:
        r0 = r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void computeZerosUnder(boolean z) {
        int length = z ? this.A.length * 2 : this.A.length + 1;
        int i2 = 0;
        while (i2 < this.A.length - 1) {
            int i3 = i2 + 1;
            int i4 = i3;
            while (true) {
                short[][] sArr = this.A;
                if (i4 < sArr.length) {
                    short s2 = sArr[i4][i2];
                    short invElem = GF2Field.invElem(sArr[i2][i2]);
                    if (invElem == 0) {
                        throw new IllegalStateException("Matrix not invertible! We have to choose another one!");
                    }
                    for (int i5 = i2; i5 < length; i5++) {
                        short multElem = GF2Field.multElem(s2, GF2Field.multElem(this.A[i2][i5], invElem));
                        short[][] sArr2 = this.A;
                        sArr2[i4][i5] = GF2Field.addElem(sArr2[i4][i5], multElem);
                    }
                    i4++;
                }
            }
        }
    }

    private void substitute() {
        short[][] sArr;
        short invElem = GF2Field.invElem(this.A[sArr.length - 1][sArr.length - 1]);
        if (invElem == 0) {
            throw new IllegalStateException("The equation system is not solvable");
        }
        short[] sArr2 = this.f14565a;
        short[][] sArr3 = this.A;
        sArr2[sArr3.length - 1] = GF2Field.multElem(sArr3[sArr3.length - 1][sArr3.length], invElem);
        for (int length = this.A.length - 2; length >= 0; length--) {
            short[][] sArr4 = this.A;
            short s2 = sArr4[length][sArr4.length];
            for (int length2 = sArr4.length - 1; length2 > length; length2--) {
                s2 = GF2Field.addElem(s2, GF2Field.multElem(this.A[length][length2], this.f14565a[length2]));
            }
            short invElem2 = GF2Field.invElem(this.A[length][length]);
            if (invElem2 == 0) {
                throw new IllegalStateException("Not solvable equation system");
            }
            this.f14565a[length] = GF2Field.multElem(s2, invElem2);
        }
    }

    public short[][] addSquareMatrix(short[][] sArr, short[][] sArr2) {
        if (sArr.length == sArr2.length && sArr[0].length == sArr2[0].length) {
            short[][] sArr3 = (short[][]) Array.newInstance(short.class, sArr.length, sArr.length);
            for (int i2 = 0; i2 < sArr.length; i2++) {
                for (int i3 = 0; i3 < sArr2.length; i3++) {
                    sArr3[i2][i3] = GF2Field.addElem(sArr[i2][i3], sArr2[i2][i3]);
                }
            }
            return sArr3;
        }
        throw new RuntimeException("Addition is not possible!");
    }

    public short[] addVect(short[] sArr, short[] sArr2) {
        if (sArr.length == sArr2.length) {
            int length = sArr.length;
            short[] sArr3 = new short[length];
            for (int i2 = 0; i2 < length; i2++) {
                sArr3[i2] = GF2Field.addElem(sArr[i2], sArr2[i2]);
            }
            return sArr3;
        }
        throw new RuntimeException("Multiplication is not possible!");
    }

    public short[][] inverse(short[][] sArr) {
        try {
            int i2 = 0;
            this.A = (short[][]) Array.newInstance(short.class, sArr.length, sArr.length * 2);
            if (sArr.length != sArr[0].length) {
                throw new RuntimeException("The matrix is not invertible. Please choose another one!");
            }
            for (int i3 = 0; i3 < sArr.length; i3++) {
                for (int i4 = 0; i4 < sArr.length; i4++) {
                    this.A[i3][i4] = sArr[i3][i4];
                }
                for (int length = sArr.length; length < sArr.length * 2; length++) {
                    this.A[i3][length] = 0;
                }
                short[][] sArr2 = this.A;
                sArr2[i3][sArr2.length + i3] = 1;
            }
            computeZerosUnder(true);
            int i5 = 0;
            while (true) {
                short[][] sArr3 = this.A;
                if (i5 >= sArr3.length) {
                    break;
                }
                short invElem = GF2Field.invElem(sArr3[i5][i5]);
                int i6 = i5;
                while (true) {
                    short[][] sArr4 = this.A;
                    if (i6 < sArr4.length * 2) {
                        sArr4[i5][i6] = GF2Field.multElem(sArr4[i5][i6], invElem);
                        i6++;
                    }
                }
                i5++;
            }
            computeZerosAbove();
            short[][] sArr5 = this.A;
            short[][] sArr6 = (short[][]) Array.newInstance(short.class, sArr5.length, sArr5.length);
            while (true) {
                short[][] sArr7 = this.A;
                if (i2 >= sArr7.length) {
                    return sArr6;
                }
                int length2 = sArr7.length;
                while (true) {
                    short[][] sArr8 = this.A;
                    if (length2 < sArr8.length * 2) {
                        sArr6[i2][length2 - sArr8.length] = sArr8[i2][length2];
                        length2++;
                    }
                }
                i2++;
            }
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public short[][] multMatrix(short s2, short[][] sArr) {
        short[][] sArr2 = (short[][]) Array.newInstance(short.class, sArr.length, sArr[0].length);
        for (int i2 = 0; i2 < sArr.length; i2++) {
            for (int i3 = 0; i3 < sArr[0].length; i3++) {
                sArr2[i2][i3] = GF2Field.multElem(s2, sArr[i2][i3]);
            }
        }
        return sArr2;
    }

    public short[] multVect(short s2, short[] sArr) {
        int length = sArr.length;
        short[] sArr2 = new short[length];
        for (int i2 = 0; i2 < length; i2++) {
            sArr2[i2] = GF2Field.multElem(s2, sArr[i2]);
        }
        return sArr2;
    }

    public short[][] multVects(short[] sArr, short[] sArr2) {
        if (sArr.length == sArr2.length) {
            short[][] sArr3 = (short[][]) Array.newInstance(short.class, sArr.length, sArr2.length);
            for (int i2 = 0; i2 < sArr.length; i2++) {
                for (int i3 = 0; i3 < sArr2.length; i3++) {
                    sArr3[i2][i3] = GF2Field.multElem(sArr[i2], sArr2[i3]);
                }
            }
            return sArr3;
        }
        throw new RuntimeException("Multiplication is not possible!");
    }

    public short[] multiplyMatrix(short[][] sArr, short[] sArr2) {
        if (sArr[0].length == sArr2.length) {
            short[] sArr3 = new short[sArr.length];
            for (int i2 = 0; i2 < sArr.length; i2++) {
                for (int i3 = 0; i3 < sArr2.length; i3++) {
                    sArr3[i2] = GF2Field.addElem(sArr3[i2], GF2Field.multElem(sArr[i2][i3], sArr2[i3]));
                }
            }
            return sArr3;
        }
        throw new RuntimeException("Multiplication is not possible!");
    }

    public short[][] multiplyMatrix(short[][] sArr, short[][] sArr2) {
        if (sArr[0].length == sArr2.length) {
            this.A = (short[][]) Array.newInstance(short.class, sArr.length, sArr2[0].length);
            for (int i2 = 0; i2 < sArr.length; i2++) {
                for (int i3 = 0; i3 < sArr2.length; i3++) {
                    for (int i4 = 0; i4 < sArr2[0].length; i4++) {
                        short multElem = GF2Field.multElem(sArr[i2][i3], sArr2[i3][i4]);
                        short[][] sArr3 = this.A;
                        sArr3[i2][i4] = GF2Field.addElem(sArr3[i2][i4], multElem);
                    }
                }
            }
            return this.A;
        }
        throw new RuntimeException("Multiplication is not possible!");
    }

    public short[] solveEquation(short[][] sArr, short[] sArr2) {
        if (sArr.length != sArr2.length) {
            return null;
        }
        try {
            this.A = (short[][]) Array.newInstance(short.class, sArr.length, sArr.length + 1);
            this.f14565a = new short[sArr.length];
            for (int i2 = 0; i2 < sArr.length; i2++) {
                for (int i3 = 0; i3 < sArr[0].length; i3++) {
                    this.A[i2][i3] = sArr[i2][i3];
                }
            }
            for (int i4 = 0; i4 < sArr2.length; i4++) {
                short[][] sArr3 = this.A;
                sArr3[i4][sArr2.length] = GF2Field.addElem(sArr2[i4], sArr3[i4][sArr2.length]);
            }
            computeZerosUnder(false);
            substitute();
            return this.f14565a;
        } catch (RuntimeException unused) {
            return null;
        }
    }
}
