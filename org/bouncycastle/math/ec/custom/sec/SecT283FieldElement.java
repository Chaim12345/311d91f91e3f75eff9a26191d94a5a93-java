package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat320;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class SecT283FieldElement extends ECFieldElement.AbstractF2m {

    /* renamed from: a  reason: collision with root package name */
    protected long[] f14282a;

    public SecT283FieldElement() {
        this.f14282a = Nat320.create64();
    }

    public SecT283FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 283) {
            throw new IllegalArgumentException("x value invalid for SecT283FieldElement");
        }
        this.f14282a = SecT283Field.fromBigInteger(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SecT283FieldElement(long[] jArr) {
        this.f14282a = jArr;
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        long[] create64 = Nat320.create64();
        SecT283Field.add(this.f14282a, ((SecT283FieldElement) eCFieldElement).f14282a, create64);
        return new SecT283FieldElement(create64);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement addOne() {
        long[] create64 = Nat320.create64();
        SecT283Field.addOne(this.f14282a, create64);
        return new SecT283FieldElement(create64);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        return multiply(eCFieldElement.invert());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecT283FieldElement) {
            return Nat320.eq64(this.f14282a, ((SecT283FieldElement) obj).f14282a);
        }
        return false;
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public String getFieldName() {
        return "SecT283Field";
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public int getFieldSize() {
        return 283;
    }

    public int getK1() {
        return 5;
    }

    public int getK2() {
        return 7;
    }

    public int getK3() {
        return 12;
    }

    public int getM() {
        return 283;
    }

    public int getRepresentation() {
        return 3;
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement.AbstractF2m
    public ECFieldElement halfTrace() {
        long[] create64 = Nat320.create64();
        SecT283Field.halfTrace(this.f14282a, create64);
        return new SecT283FieldElement(create64);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement.AbstractF2m
    public boolean hasFastTrace() {
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(this.f14282a, 0, 5) ^ 2831275;
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement invert() {
        long[] create64 = Nat320.create64();
        SecT283Field.invert(this.f14282a, create64);
        return new SecT283FieldElement(create64);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public boolean isOne() {
        return Nat320.isOne64(this.f14282a);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public boolean isZero() {
        return Nat320.isZero64(this.f14282a);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        long[] create64 = Nat320.create64();
        SecT283Field.multiply(this.f14282a, ((SecT283FieldElement) eCFieldElement).f14282a, create64);
        return new SecT283FieldElement(create64);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        long[] jArr = this.f14282a;
        long[] jArr2 = ((SecT283FieldElement) eCFieldElement).f14282a;
        long[] jArr3 = ((SecT283FieldElement) eCFieldElement2).f14282a;
        long[] jArr4 = ((SecT283FieldElement) eCFieldElement3).f14282a;
        long[] create64 = Nat.create64(9);
        SecT283Field.multiplyAddToExt(jArr, jArr2, create64);
        SecT283Field.multiplyAddToExt(jArr3, jArr4, create64);
        long[] create642 = Nat320.create64();
        SecT283Field.reduce(create64, create642);
        return new SecT283FieldElement(create642);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement negate() {
        return this;
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement sqrt() {
        long[] create64 = Nat320.create64();
        SecT283Field.sqrt(this.f14282a, create64);
        return new SecT283FieldElement(create64);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement square() {
        long[] create64 = Nat320.create64();
        SecT283Field.square(this.f14282a, create64);
        return new SecT283FieldElement(create64);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return squarePlusProduct(eCFieldElement, eCFieldElement2);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        long[] jArr = this.f14282a;
        long[] jArr2 = ((SecT283FieldElement) eCFieldElement).f14282a;
        long[] jArr3 = ((SecT283FieldElement) eCFieldElement2).f14282a;
        long[] create64 = Nat.create64(9);
        SecT283Field.squareAddToExt(jArr, create64);
        SecT283Field.multiplyAddToExt(jArr2, jArr3, create64);
        long[] create642 = Nat320.create64();
        SecT283Field.reduce(create64, create642);
        return new SecT283FieldElement(create642);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement squarePow(int i2) {
        if (i2 < 1) {
            return this;
        }
        long[] create64 = Nat320.create64();
        SecT283Field.squareN(this.f14282a, i2, create64);
        return new SecT283FieldElement(create64);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        return add(eCFieldElement);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public boolean testBitZero() {
        return (this.f14282a[0] & 1) != 0;
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat320.toBigInteger64(this.f14282a);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement.AbstractF2m
    public int trace() {
        return SecT283Field.trace(this.f14282a);
    }
}
