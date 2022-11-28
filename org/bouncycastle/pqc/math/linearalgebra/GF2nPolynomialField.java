package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;
/* loaded from: classes4.dex */
public class GF2nPolynomialField extends GF2nField {

    /* renamed from: f  reason: collision with root package name */
    GF2Polynomial[] f14654f;
    private boolean isPentanomial;
    private boolean isTrinomial;
    private int[] pc;
    private int tc;

    public GF2nPolynomialField(int i2, SecureRandom secureRandom) {
        super(secureRandom);
        this.isTrinomial = false;
        this.isPentanomial = false;
        this.pc = new int[3];
        if (i2 < 3) {
            throw new IllegalArgumentException("k must be at least 3");
        }
        this.f14649b = i2;
        b();
        computeSquaringMatrix();
        this.f14651d = new java.util.Vector();
        this.f14652e = new java.util.Vector();
    }

    public GF2nPolynomialField(int i2, SecureRandom secureRandom, GF2Polynomial gF2Polynomial) {
        super(secureRandom);
        this.isTrinomial = false;
        this.isPentanomial = false;
        this.pc = new int[3];
        if (i2 < 3) {
            throw new IllegalArgumentException("degree must be at least 3");
        }
        if (gF2Polynomial.getLength() != i2 + 1) {
            throw new RuntimeException();
        }
        if (!gF2Polynomial.isIrreducible()) {
            throw new RuntimeException();
        }
        this.f14649b = i2;
        this.f14650c = gF2Polynomial;
        computeSquaringMatrix();
        int i3 = 2;
        for (int i4 = 1; i4 < this.f14650c.getLength() - 1; i4++) {
            if (this.f14650c.testBit(i4)) {
                i3++;
                if (i3 == 3) {
                    this.tc = i4;
                }
                if (i3 <= 5) {
                    this.pc[i3 - 3] = i4;
                }
            }
        }
        if (i3 == 3) {
            this.isTrinomial = true;
        }
        if (i3 == 5) {
            this.isPentanomial = true;
        }
        this.f14651d = new java.util.Vector();
        this.f14652e = new java.util.Vector();
    }

    public GF2nPolynomialField(int i2, SecureRandom secureRandom, boolean z) {
        super(secureRandom);
        this.isTrinomial = false;
        this.isPentanomial = false;
        this.pc = new int[3];
        if (i2 < 3) {
            throw new IllegalArgumentException("k must be at least 3");
        }
        this.f14649b = i2;
        if (z) {
            b();
        } else {
            e();
        }
        computeSquaringMatrix();
        this.f14651d = new java.util.Vector();
        this.f14652e = new java.util.Vector();
    }

    private void computeSquaringMatrix() {
        int i2 = this.f14649b;
        GF2Polynomial[] gF2PolynomialArr = new GF2Polynomial[i2 - 1];
        this.f14654f = new GF2Polynomial[i2];
        int i3 = 0;
        while (true) {
            GF2Polynomial[] gF2PolynomialArr2 = this.f14654f;
            if (i3 >= gF2PolynomialArr2.length) {
                break;
            }
            gF2PolynomialArr2[i3] = new GF2Polynomial(this.f14649b, "ZERO");
            i3++;
        }
        for (int i4 = 0; i4 < this.f14649b - 1; i4++) {
            gF2PolynomialArr[i4] = new GF2Polynomial(1, "ONE").shiftLeft(this.f14649b + i4).remainder(this.f14650c);
        }
        for (int i5 = 1; i5 <= Math.abs(this.f14649b >> 1); i5++) {
            int i6 = 1;
            while (true) {
                int i7 = this.f14649b;
                if (i6 <= i7) {
                    if (gF2PolynomialArr[i7 - (i5 << 1)].testBit(i7 - i6)) {
                        this.f14654f[i6 - 1].setBit(this.f14649b - i5);
                    }
                    i6++;
                }
            }
        }
        int abs = Math.abs(this.f14649b >> 1) + 1;
        while (true) {
            int i8 = this.f14649b;
            if (abs > i8) {
                return;
            }
            this.f14654f[((abs << 1) - i8) - 1].setBit(i8 - abs);
            abs++;
        }
    }

    private boolean testPentanomials() {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.f14649b + 1);
        this.f14650c = gF2Polynomial;
        gF2Polynomial.setBit(0);
        this.f14650c.setBit(this.f14649b);
        boolean z = false;
        int i2 = 1;
        while (i2 <= this.f14649b - 3 && !z) {
            this.f14650c.setBit(i2);
            int i3 = i2 + 1;
            int i4 = i3;
            while (i4 <= this.f14649b - 2 && !z) {
                this.f14650c.setBit(i4);
                int i5 = i4 + 1;
                for (int i6 = i5; i6 <= this.f14649b - 1 && !z; i6++) {
                    this.f14650c.setBit(i6);
                    if (((((this.f14649b & 1) != 0) | ((i2 & 1) != 0) | ((i4 & 1) != 0)) || ((i6 & 1) != 0)) && (z = this.f14650c.isIrreducible())) {
                        this.isPentanomial = true;
                        int[] iArr = this.pc;
                        iArr[0] = i2;
                        iArr[1] = i4;
                        iArr[2] = i6;
                        return z;
                    }
                    this.f14650c.resetBit(i6);
                }
                this.f14650c.resetBit(i4);
                i4 = i5;
            }
            this.f14650c.resetBit(i2);
            i2 = i3;
        }
        return z;
    }

    private boolean testRandom() {
        this.f14650c = new GF2Polynomial(this.f14649b + 1);
        do {
            this.f14650c.randomize();
            this.f14650c.setBit(this.f14649b);
            this.f14650c.setBit(0);
        } while (!this.f14650c.isIrreducible());
        return true;
    }

    private boolean testTrinomials() {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.f14649b + 1);
        this.f14650c = gF2Polynomial;
        boolean z = false;
        gF2Polynomial.setBit(0);
        this.f14650c.setBit(this.f14649b);
        for (int i2 = 1; i2 < this.f14649b && !z; i2++) {
            this.f14650c.setBit(i2);
            boolean isIrreducible = this.f14650c.isIrreducible();
            if (isIrreducible) {
                this.isTrinomial = true;
                this.tc = i2;
                return isIrreducible;
            }
            this.f14650c.resetBit(i2);
            z = this.f14650c.isIrreducible();
        }
        return z;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nField
    protected void a(GF2nField gF2nField) {
        GF2nElement c2;
        GF2nElement[] gF2nElementArr;
        int i2;
        int i3 = this.f14649b;
        if (i3 != gF2nField.f14649b) {
            throw new IllegalArgumentException("GF2nPolynomialField.computeCOBMatrix: B1 has a different degree and thus cannot be coverted to!");
        }
        boolean z = gF2nField instanceof GF2nONBField;
        if (z) {
            gF2nField.a(this);
            return;
        }
        GF2Polynomial[] gF2PolynomialArr = new GF2Polynomial[i3];
        for (int i4 = 0; i4 < this.f14649b; i4++) {
            gF2PolynomialArr[i4] = new GF2Polynomial(this.f14649b);
        }
        do {
            c2 = gF2nField.c(this.f14650c);
        } while (c2.isZero());
        if (c2 instanceof GF2nONBElement) {
            int i5 = this.f14649b;
            gF2nElementArr = new GF2nONBElement[i5];
            gF2nElementArr[i5 - 1] = GF2nONBElement.ONE((GF2nONBField) gF2nField);
        } else {
            int i6 = this.f14649b;
            gF2nElementArr = new GF2nPolynomialElement[i6];
            gF2nElementArr[i6 - 1] = GF2nPolynomialElement.ONE((GF2nPolynomialField) gF2nField);
        }
        int i7 = this.f14649b;
        gF2nElementArr[i7 - 2] = c2;
        for (int i8 = i7 - 3; i8 >= 0; i8--) {
            gF2nElementArr[i8] = (GF2nElement) gF2nElementArr[i8 + 1].multiply(c2);
        }
        if (z) {
            for (int i9 = 0; i9 < this.f14649b; i9++) {
                int i10 = 0;
                while (true) {
                    if (i10 < this.f14649b) {
                        if (gF2nElementArr[i9].b((i2 - i10) - 1)) {
                            int i11 = this.f14649b;
                            gF2PolynomialArr[(i11 - i10) - 1].setBit((i11 - i9) - 1);
                        }
                        i10++;
                    }
                }
            }
        } else {
            for (int i12 = 0; i12 < this.f14649b; i12++) {
                for (int i13 = 0; i13 < this.f14649b; i13++) {
                    if (gF2nElementArr[i12].b(i13)) {
                        int i14 = this.f14649b;
                        gF2PolynomialArr[(i14 - i13) - 1].setBit((i14 - i12) - 1);
                    }
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
        if (testTrinomials() || testPentanomials()) {
            return;
        }
        testRandom();
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nField
    protected GF2nElement c(GF2Polynomial gF2Polynomial) {
        GF2nPolynomial gcd;
        int degree;
        int degree2;
        GF2nPolynomial gF2nPolynomial = new GF2nPolynomial(gF2Polynomial, this);
        while (gF2nPolynomial.getDegree() > 1) {
            while (true) {
                GF2nPolynomialElement gF2nPolynomialElement = new GF2nPolynomialElement(this, this.f14648a);
                GF2nPolynomial gF2nPolynomial2 = new GF2nPolynomial(2, GF2nPolynomialElement.ZERO(this));
                gF2nPolynomial2.set(1, gF2nPolynomialElement);
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

    protected void e() {
        if (testTrinomials() || testPentanomials()) {
            return;
        }
        testRandom();
    }

    public int[] getPc() {
        if (this.isPentanomial) {
            int[] iArr = new int[3];
            System.arraycopy(this.pc, 0, iArr, 0, 3);
            return iArr;
        }
        throw new RuntimeException();
    }

    public GF2Polynomial getSquaringVector(int i2) {
        return new GF2Polynomial(this.f14654f[i2]);
    }

    public int getTc() {
        if (this.isTrinomial) {
            return this.tc;
        }
        throw new RuntimeException();
    }

    public boolean isPentanomial() {
        return this.isPentanomial;
    }

    public boolean isTrinomial() {
        return this.isTrinomial;
    }
}
