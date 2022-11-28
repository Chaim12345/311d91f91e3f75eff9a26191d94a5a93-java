package org.bouncycastle.pqc.math.linearalgebra;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.Random;
import org.apache.commons.codec.language.bm.Rule;
/* loaded from: classes4.dex */
public class GF2nONBField extends GF2nField {
    private static final int MAXLONG = 64;

    /* renamed from: f  reason: collision with root package name */
    int[][] f14653f;
    private int mBit;
    private int mLength;
    private int mType;

    public GF2nONBField(int i2, SecureRandom secureRandom) {
        super(secureRandom);
        if (i2 < 3) {
            throw new IllegalArgumentException("k must be at least 3");
        }
        this.f14649b = i2;
        int i3 = i2 / 64;
        this.mLength = i3;
        int i4 = i2 & 63;
        this.mBit = i4;
        if (i4 == 0) {
            this.mBit = 64;
        } else {
            this.mLength = i3 + 1;
        }
        computeType();
        if (this.mType >= 3) {
            throw new RuntimeException("\nThe type of this field is " + this.mType);
        }
        this.f14653f = (int[][]) Array.newInstance(int.class, this.f14649b, 2);
        for (int i5 = 0; i5 < this.f14649b; i5++) {
            int[][] iArr = this.f14653f;
            iArr[i5][0] = -1;
            iArr[i5][1] = -1;
        }
        computeMultMatrix();
        b();
        this.f14651d = new java.util.Vector();
        this.f14652e = new java.util.Vector();
    }

    private void computeMultMatrix() {
        int i2;
        int i3 = this.mType;
        if ((i3 & 7) == 0) {
            throw new RuntimeException("bisher nur fuer Gausssche Normalbasen implementiert");
        }
        int i4 = (this.f14649b * i3) + 1;
        int[] iArr = new int[i4];
        int elementOfOrder = i3 == 1 ? 1 : i3 == 2 ? i4 - 1 : elementOfOrder(i3, i4);
        int i5 = 1;
        int i6 = 0;
        while (true) {
            i2 = this.mType;
            if (i6 >= i2) {
                break;
            }
            int i7 = i5;
            for (int i8 = 0; i8 < this.f14649b; i8++) {
                iArr[i7] = i8;
                i7 = (i7 << 1) % i4;
                if (i7 < 0) {
                    i7 += i4;
                }
            }
            i5 = (i5 * elementOfOrder) % i4;
            if (i5 < 0) {
                i5 += i4;
            }
            i6++;
        }
        if (i2 != 1) {
            if (i2 != 2) {
                throw new RuntimeException("only type 1 or type 2 implemented");
            }
            int i9 = 1;
            while (i9 < i4 - 1) {
                int[][] iArr2 = this.f14653f;
                int i10 = i9 + 1;
                if (iArr2[iArr[i10]][0] == -1) {
                    iArr2[iArr[i10]][0] = iArr[i4 - i9];
                } else {
                    iArr2[iArr[i10]][1] = iArr[i4 - i9];
                }
                i9 = i10;
            }
            return;
        }
        int i11 = 1;
        while (i11 < i4 - 1) {
            int[][] iArr3 = this.f14653f;
            int i12 = i11 + 1;
            if (iArr3[iArr[i12]][0] == -1) {
                iArr3[iArr[i12]][0] = iArr[i4 - i11];
            } else {
                iArr3[iArr[i12]][1] = iArr[i4 - i11];
            }
            i11 = i12;
        }
        int i13 = this.f14649b >> 1;
        for (int i14 = 1; i14 <= i13; i14++) {
            int[][] iArr4 = this.f14653f;
            int i15 = i14 - 1;
            if (iArr4[i15][0] == -1) {
                iArr4[i15][0] = (i13 + i14) - 1;
            } else {
                iArr4[i15][1] = (i13 + i14) - 1;
            }
            int i16 = (i13 + i14) - 1;
            if (iArr4[i16][0] == -1) {
                iArr4[i16][0] = i15;
            } else {
                iArr4[i16][1] = i15;
            }
        }
    }

    private void computeType() {
        if ((this.f14649b & 7) == 0) {
            throw new RuntimeException("The extension degree is divisible by 8!");
        }
        this.mType = 1;
        int i2 = 0;
        while (i2 != 1) {
            int i3 = (this.mType * this.f14649b) + 1;
            if (IntegerFunctions.isPrime(i3)) {
                int order = IntegerFunctions.order(2, i3);
                int i4 = this.mType;
                int i5 = this.f14649b;
                i2 = IntegerFunctions.gcd((i4 * i5) / order, i5);
            }
            this.mType++;
        }
        int i6 = this.mType - 1;
        this.mType = i6;
        if (i6 == 1) {
            int i7 = (this.f14649b << 1) + 1;
            if (IntegerFunctions.isPrime(i7)) {
                int order2 = IntegerFunctions.order(2, i7);
                int i8 = this.f14649b;
                if (IntegerFunctions.gcd((i8 << 1) / order2, i8) == 1) {
                    this.mType++;
                }
            }
        }
    }

    private int elementOfOrder(int i2, int i3) {
        int order;
        Random random = new Random();
        int i4 = 0;
        while (i4 == 0) {
            int i5 = i3 - 1;
            i4 = random.nextInt() % i5;
            if (i4 < 0) {
                i4 += i5;
            }
        }
        while (true) {
            order = IntegerFunctions.order(i4, i3);
            if (order % i2 == 0 && order != 0) {
                break;
            }
            while (i4 == 0) {
                int i6 = i3 - 1;
                i4 = random.nextInt() % i6;
                if (i4 < 0) {
                    i4 += i6;
                }
            }
        }
        int i7 = i4;
        for (int i8 = 2; i8 <= i2 / order; i8++) {
            i7 *= i4;
        }
        return i7;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nField
    protected void a(GF2nField gF2nField) {
        GF2nElement c2;
        int i2 = this.f14649b;
        if (i2 != gF2nField.f14649b) {
            throw new IllegalArgumentException("GF2nField.computeCOBMatrix: B1 has a different degree and thus cannot be coverted to!");
        }
        GF2Polynomial[] gF2PolynomialArr = new GF2Polynomial[i2];
        for (int i3 = 0; i3 < this.f14649b; i3++) {
            gF2PolynomialArr[i3] = new GF2Polynomial(this.f14649b);
        }
        do {
            c2 = gF2nField.c(this.f14650c);
        } while (c2.isZero());
        GF2nElement[] gF2nElementArr = new GF2nPolynomialElement[this.f14649b];
        gF2nElementArr[0] = (GF2nElement) c2.clone();
        for (int i4 = 1; i4 < this.f14649b; i4++) {
            gF2nElementArr[i4] = gF2nElementArr[i4 - 1].square();
        }
        for (int i5 = 0; i5 < this.f14649b; i5++) {
            for (int i6 = 0; i6 < this.f14649b; i6++) {
                if (gF2nElementArr[i5].b(i6)) {
                    int i7 = this.f14649b;
                    gF2PolynomialArr[(i7 - i6) - 1].setBit((i7 - i5) - 1);
                }
            }
        }
        this.f14651d.addElement(gF2nField);
        this.f14652e.addElement(gF2PolynomialArr);
        gF2nField.f14651d.addElement(this);
        gF2nField.f14652e.addElement(d(gF2PolynomialArr));
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nField
    protected void b() {
        GF2Polynomial gF2Polynomial;
        int i2 = this.mType;
        if (i2 == 1) {
            gF2Polynomial = new GF2Polynomial(this.f14649b + 1, Rule.ALL);
        } else if (i2 != 2) {
            return;
        } else {
            GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.f14649b + 1, "ONE");
            GF2Polynomial gF2Polynomial3 = new GF2Polynomial(this.f14649b + 1, "X");
            gF2Polynomial3.addToThis(gF2Polynomial2);
            GF2Polynomial gF2Polynomial4 = gF2Polynomial2;
            gF2Polynomial = gF2Polynomial3;
            int i3 = 1;
            while (i3 < this.f14649b) {
                GF2Polynomial shiftLeft = gF2Polynomial.shiftLeft();
                shiftLeft.addToThis(gF2Polynomial4);
                i3++;
                gF2Polynomial4 = gF2Polynomial;
                gF2Polynomial = shiftLeft;
            }
        }
        this.f14650c = gF2Polynomial;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nField
    protected GF2nElement c(GF2Polynomial gF2Polynomial) {
        GF2nPolynomial gcd;
        int degree;
        int degree2;
        GF2nPolynomial gF2nPolynomial = new GF2nPolynomial(gF2Polynomial, this);
        while (gF2nPolynomial.getDegree() > 1) {
            while (true) {
                GF2nONBElement gF2nONBElement = new GF2nONBElement(this, this.f14648a);
                GF2nPolynomial gF2nPolynomial2 = new GF2nPolynomial(2, GF2nONBElement.ZERO(this));
                gF2nPolynomial2.set(1, gF2nONBElement);
                GF2nPolynomial gF2nPolynomial3 = new GF2nPolynomial(gF2nPolynomial2);
                for (int i2 = 1; i2 <= this.f14649b - 1; i2++) {
                    gF2nPolynomial3 = gF2nPolynomial3.multiplyAndReduce(gF2nPolynomial3, gF2nPolynomial).add(gF2nPolynomial2);
                }
                gcd = gF2nPolynomial3.gcd(gF2nPolynomial);
                degree = gcd.getDegree();
                degree2 = gF2nPolynomial.getDegree();
                if (degree != 0 && degree != degree2) {
                    break;
                }
            }
            gF2nPolynomial = (degree << 1) > degree2 ? gF2nPolynomial.quotient(gcd) : new GF2nPolynomial(gcd);
        }
        return gF2nPolynomial.at(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int e() {
        return this.mBit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f() {
        return this.mLength;
    }
}
