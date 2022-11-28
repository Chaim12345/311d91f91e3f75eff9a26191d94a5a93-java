package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;
import org.apache.http.message.TokenParser;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class GF2Vector extends Vector {
    private int[] v;

    public GF2Vector(int i2) {
        if (i2 < 0) {
            throw new ArithmeticException("Negative length.");
        }
        this.f14664a = i2;
        this.v = new int[(i2 + 31) >> 5];
    }

    public GF2Vector(int i2, int i3, SecureRandom secureRandom) {
        if (i3 > i2) {
            throw new ArithmeticException("The hamming weight is greater than the length of vector.");
        }
        this.f14664a = i2;
        this.v = new int[(i2 + 31) >> 5];
        int[] iArr = new int[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            iArr[i4] = i4;
        }
        for (int i5 = 0; i5 < i3; i5++) {
            int a2 = RandUtils.a(secureRandom, i2);
            setBit(iArr[a2]);
            i2--;
            iArr[a2] = iArr[i2];
        }
    }

    public GF2Vector(int i2, SecureRandom secureRandom) {
        this.f14664a = i2;
        int i3 = (i2 + 31) >> 5;
        this.v = new int[i3];
        int i4 = i3 - 1;
        for (int i5 = i4; i5 >= 0; i5--) {
            this.v[i5] = secureRandom.nextInt();
        }
        int i6 = i2 & 31;
        if (i6 != 0) {
            int[] iArr = this.v;
            iArr[i4] = ((1 << i6) - 1) & iArr[i4];
        }
    }

    public GF2Vector(int i2, int[] iArr) {
        if (i2 < 0) {
            throw new ArithmeticException("negative length");
        }
        this.f14664a = i2;
        int i3 = (i2 + 31) >> 5;
        if (iArr.length != i3) {
            throw new ArithmeticException("length mismatch");
        }
        int[] clone = IntUtils.clone(iArr);
        this.v = clone;
        int i4 = i2 & 31;
        if (i4 != 0) {
            int i5 = i3 - 1;
            clone[i5] = ((1 << i4) - 1) & clone[i5];
        }
    }

    public GF2Vector(GF2Vector gF2Vector) {
        this.f14664a = gF2Vector.f14664a;
        this.v = IntUtils.clone(gF2Vector.v);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GF2Vector(int[] iArr, int i2) {
        this.v = iArr;
        this.f14664a = i2;
    }

    public static GF2Vector OS2VP(int i2, byte[] bArr) {
        if (i2 >= 0) {
            if (bArr.length <= ((i2 + 7) >> 3)) {
                return new GF2Vector(i2, LittleEndianConversions.toIntArray(bArr));
            }
            throw new ArithmeticException("length mismatch");
        }
        throw new ArithmeticException("negative length");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public Vector add(Vector vector) {
        if (vector instanceof GF2Vector) {
            GF2Vector gF2Vector = (GF2Vector) vector;
            if (this.f14664a == gF2Vector.f14664a) {
                int[] clone = IntUtils.clone(gF2Vector.v);
                for (int length = clone.length - 1; length >= 0; length--) {
                    clone[length] = clone[length] ^ this.v[length];
                }
                return new GF2Vector(this.f14664a, clone);
            }
            throw new ArithmeticException("length mismatch");
        }
        throw new ArithmeticException("vector is not defined over GF(2)");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public boolean equals(Object obj) {
        if (obj instanceof GF2Vector) {
            GF2Vector gF2Vector = (GF2Vector) obj;
            return this.f14664a == gF2Vector.f14664a && IntUtils.equals(this.v, gF2Vector.v);
        }
        return false;
    }

    public GF2Vector extractLeftVector(int i2) {
        int i3 = this.f14664a;
        if (i2 <= i3) {
            if (i2 == i3) {
                return new GF2Vector(this);
            }
            GF2Vector gF2Vector = new GF2Vector(i2);
            int i4 = i2 >> 5;
            int i5 = i2 & 31;
            System.arraycopy(this.v, 0, gF2Vector.v, 0, i4);
            if (i5 != 0) {
                gF2Vector.v[i4] = ((1 << i5) - 1) & this.v[i4];
            }
            return gF2Vector;
        }
        throw new ArithmeticException("invalid length");
    }

    public GF2Vector extractRightVector(int i2) {
        int i3;
        int i4 = this.f14664a;
        if (i2 <= i4) {
            if (i2 == i4) {
                return new GF2Vector(this);
            }
            GF2Vector gF2Vector = new GF2Vector(i2);
            int i5 = this.f14664a;
            int i6 = (i5 - i2) >> 5;
            int i7 = (i5 - i2) & 31;
            int i8 = (i2 + 31) >> 5;
            int i9 = 0;
            if (i7 != 0) {
                while (true) {
                    i3 = i8 - 1;
                    if (i9 >= i3) {
                        break;
                    }
                    int[] iArr = gF2Vector.v;
                    int[] iArr2 = this.v;
                    int i10 = i6 + 1;
                    iArr[i9] = (iArr2[i6] >>> i7) | (iArr2[i10] << (32 - i7));
                    i9++;
                    i6 = i10;
                }
                int[] iArr3 = gF2Vector.v;
                int[] iArr4 = this.v;
                int i11 = i6 + 1;
                iArr3[i3] = iArr4[i6] >>> i7;
                if (i11 < iArr4.length) {
                    iArr3[i3] = (iArr4[i11] << (32 - i7)) | iArr3[i3];
                }
            } else {
                System.arraycopy(this.v, i6, gF2Vector.v, 0, i8);
            }
            return gF2Vector;
        }
        throw new ArithmeticException("invalid length");
    }

    public GF2Vector extractVector(int[] iArr) {
        int length = iArr.length;
        if (iArr[length - 1] <= this.f14664a) {
            GF2Vector gF2Vector = new GF2Vector(length);
            for (int i2 = 0; i2 < length; i2++) {
                if ((this.v[iArr[i2] >> 5] & (1 << (iArr[i2] & 31))) != 0) {
                    int[] iArr2 = gF2Vector.v;
                    int i3 = i2 >> 5;
                    iArr2[i3] = (1 << (i2 & 31)) | iArr2[i3];
                }
            }
            return gF2Vector;
        }
        throw new ArithmeticException("invalid index set");
    }

    public int getBit(int i2) {
        if (i2 < this.f14664a) {
            int i3 = i2 >> 5;
            int i4 = i2 & 31;
            return (this.v[i3] & (1 << i4)) >>> i4;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public byte[] getEncoded() {
        return LittleEndianConversions.toByteArray(this.v, (this.f14664a + 7) >> 3);
    }

    public int getHammingWeight() {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = this.v;
            if (i2 >= iArr.length) {
                return i3;
            }
            int i4 = iArr[i2];
            for (int i5 = 0; i5 < 32; i5++) {
                if ((i4 & 1) != 0) {
                    i3++;
                }
                i4 >>>= 1;
            }
            i2++;
        }
    }

    public int[] getVecArray() {
        return this.v;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public int hashCode() {
        return (this.f14664a * 31) + Arrays.hashCode(this.v);
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public boolean isZero() {
        for (int length = this.v.length - 1; length >= 0; length--) {
            if (this.v[length] != 0) {
                return false;
            }
        }
        return true;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public Vector multiply(Permutation permutation) {
        int[] vector = permutation.getVector();
        int i2 = this.f14664a;
        if (i2 == vector.length) {
            GF2Vector gF2Vector = new GF2Vector(i2);
            for (int i3 = 0; i3 < vector.length; i3++) {
                if ((this.v[vector[i3] >> 5] & (1 << (vector[i3] & 31))) != 0) {
                    int[] iArr = gF2Vector.v;
                    int i4 = i3 >> 5;
                    iArr[i4] = (1 << (i3 & 31)) | iArr[i4];
                }
            }
            return gF2Vector;
        }
        throw new ArithmeticException("length mismatch");
    }

    public void setBit(int i2) {
        if (i2 >= this.f14664a) {
            throw new IndexOutOfBoundsException();
        }
        int[] iArr = this.v;
        int i3 = i2 >> 5;
        iArr[i3] = (1 << (i2 & 31)) | iArr[i3];
    }

    public GF2mVector toExtensionFieldVector(GF2mField gF2mField) {
        int degree = gF2mField.getDegree();
        int i2 = this.f14664a;
        if (i2 % degree == 0) {
            int i3 = i2 / degree;
            int[] iArr = new int[i3];
            int i4 = 0;
            for (int i5 = i3 - 1; i5 >= 0; i5--) {
                for (int degree2 = gF2mField.getDegree() - 1; degree2 >= 0; degree2--) {
                    if (((this.v[i4 >>> 5] >>> (i4 & 31)) & 1) == 1) {
                        iArr[i5] = iArr[i5] ^ (1 << degree2);
                    }
                    i4++;
                }
            }
            return new GF2mVector(gF2mField, iArr);
        }
        throw new ArithmeticException("conversion is impossible");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < this.f14664a; i2++) {
            if (i2 != 0 && (i2 & 31) == 0) {
                stringBuffer.append(TokenParser.SP);
            }
            stringBuffer.append((this.v[i2 >> 5] & (1 << (i2 & 31))) == 0 ? '0' : '1');
        }
        return stringBuffer.toString();
    }
}
