package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;
/* loaded from: classes4.dex */
public abstract class GF2nField {

    /* renamed from: a  reason: collision with root package name */
    protected final SecureRandom f14648a;

    /* renamed from: b  reason: collision with root package name */
    protected int f14649b;

    /* renamed from: c  reason: collision with root package name */
    protected GF2Polynomial f14650c;

    /* renamed from: d  reason: collision with root package name */
    protected java.util.Vector f14651d;

    /* renamed from: e  reason: collision with root package name */
    protected java.util.Vector f14652e;

    /* JADX INFO: Access modifiers changed from: protected */
    public GF2nField(SecureRandom secureRandom) {
        this.f14648a = secureRandom;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void a(GF2nField gF2nField);

    protected abstract void b();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract GF2nElement c(GF2Polynomial gF2Polynomial);

    public final GF2nElement convert(GF2nElement gF2nElement, GF2nField gF2nField) {
        if (gF2nField == this || this.f14650c.equals(gF2nField.f14650c)) {
            return (GF2nElement) gF2nElement.clone();
        }
        if (this.f14649b == gF2nField.f14649b) {
            int indexOf = this.f14651d.indexOf(gF2nField);
            if (indexOf == -1) {
                a(gF2nField);
                indexOf = this.f14651d.indexOf(gF2nField);
            }
            GF2Polynomial[] gF2PolynomialArr = (GF2Polynomial[]) this.f14652e.elementAt(indexOf);
            GF2nElement gF2nElement2 = (GF2nElement) gF2nElement.clone();
            if (gF2nElement2 instanceof GF2nONBElement) {
                ((GF2nONBElement) gF2nElement2).c();
            }
            GF2Polynomial gF2Polynomial = new GF2Polynomial(this.f14649b, gF2nElement2.toFlexiBigInt());
            gF2Polynomial.expandN(this.f14649b);
            GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.f14649b);
            for (int i2 = 0; i2 < this.f14649b; i2++) {
                if (gF2Polynomial.vectorMult(gF2PolynomialArr[i2])) {
                    gF2Polynomial2.setBit((this.f14649b - 1) - i2);
                }
            }
            if (gF2nField instanceof GF2nPolynomialField) {
                return new GF2nPolynomialElement((GF2nPolynomialField) gF2nField, gF2Polynomial2);
            }
            if (gF2nField instanceof GF2nONBField) {
                GF2nONBElement gF2nONBElement = new GF2nONBElement((GF2nONBField) gF2nField, gF2Polynomial2.toFlexiBigInt());
                gF2nONBElement.c();
                return gF2nONBElement;
            }
            throw new RuntimeException("GF2nField.convert: B1 must be an instance of GF2nPolynomialField or GF2nONBField!");
        }
        throw new RuntimeException("GF2nField.convert: B1 has a different degree and thus cannot be coverted to!");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final GF2Polynomial[] d(GF2Polynomial[] gF2PolynomialArr) {
        GF2Polynomial[] gF2PolynomialArr2 = new GF2Polynomial[gF2PolynomialArr.length];
        GF2Polynomial[] gF2PolynomialArr3 = new GF2Polynomial[gF2PolynomialArr.length];
        int i2 = 0;
        for (int i3 = 0; i3 < this.f14649b; i3++) {
            gF2PolynomialArr2[i3] = new GF2Polynomial(gF2PolynomialArr[i3]);
            gF2PolynomialArr3[i3] = new GF2Polynomial(this.f14649b);
            gF2PolynomialArr3[i3].setBit((this.f14649b - 1) - i3);
        }
        while (true) {
            int i4 = this.f14649b;
            if (i2 >= i4 - 1) {
                for (int i5 = i4 - 1; i5 > 0; i5--) {
                    for (int i6 = i5 - 1; i6 >= 0; i6--) {
                        if (gF2PolynomialArr2[i6].testBit((this.f14649b - 1) - i5)) {
                            gF2PolynomialArr2[i6].addToThis(gF2PolynomialArr2[i5]);
                            gF2PolynomialArr3[i6].addToThis(gF2PolynomialArr3[i5]);
                        }
                    }
                }
                return gF2PolynomialArr3;
            }
            int i7 = i2;
            while (true) {
                int i8 = this.f14649b;
                if (i7 >= i8 || gF2PolynomialArr2[i7].testBit((i8 - 1) - i2)) {
                    break;
                }
                i7++;
            }
            if (i7 >= this.f14649b) {
                throw new RuntimeException("GF2nField.invertMatrix: Matrix cannot be inverted!");
            }
            if (i2 != i7) {
                GF2Polynomial gF2Polynomial = gF2PolynomialArr2[i2];
                gF2PolynomialArr2[i2] = gF2PolynomialArr2[i7];
                gF2PolynomialArr2[i7] = gF2Polynomial;
                GF2Polynomial gF2Polynomial2 = gF2PolynomialArr3[i2];
                gF2PolynomialArr3[i2] = gF2PolynomialArr3[i7];
                gF2PolynomialArr3[i7] = gF2Polynomial2;
            }
            int i9 = i2 + 1;
            int i10 = i9;
            while (true) {
                int i11 = this.f14649b;
                if (i10 < i11) {
                    if (gF2PolynomialArr2[i10].testBit((i11 - 1) - i2)) {
                        gF2PolynomialArr2[i10].addToThis(gF2PolynomialArr2[i2]);
                        gF2PolynomialArr3[i10].addToThis(gF2PolynomialArr3[i2]);
                    }
                    i10++;
                }
            }
            i2 = i9;
        }
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GF2nField)) {
            return false;
        }
        GF2nField gF2nField = (GF2nField) obj;
        if (gF2nField.f14649b == this.f14649b && this.f14650c.equals(gF2nField.f14650c)) {
            if (!(this instanceof GF2nPolynomialField) || (gF2nField instanceof GF2nPolynomialField)) {
                return !(this instanceof GF2nONBField) || (gF2nField instanceof GF2nONBField);
            }
            return false;
        }
        return false;
    }

    public final int getDegree() {
        return this.f14649b;
    }

    public final GF2Polynomial getFieldPolynomial() {
        if (this.f14650c == null) {
            b();
        }
        return new GF2Polynomial(this.f14650c);
    }

    public int hashCode() {
        return this.f14649b + this.f14650c.hashCode();
    }
}
