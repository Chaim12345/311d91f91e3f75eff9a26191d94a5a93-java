package org.bouncycastle.math.ec;

import java.math.BigInteger;
import java.util.Hashtable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
/* loaded from: classes3.dex */
public abstract class ECPoint {

    /* renamed from: f  reason: collision with root package name */
    protected static final ECFieldElement[] f14094f = new ECFieldElement[0];

    /* renamed from: a  reason: collision with root package name */
    protected ECCurve f14095a;

    /* renamed from: b  reason: collision with root package name */
    protected ECFieldElement f14096b;

    /* renamed from: c  reason: collision with root package name */
    protected ECFieldElement f14097c;

    /* renamed from: d  reason: collision with root package name */
    protected ECFieldElement[] f14098d;

    /* renamed from: e  reason: collision with root package name */
    protected Hashtable f14099e;

    /* loaded from: classes3.dex */
    public static abstract class AbstractF2m extends ECPoint {
        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractF2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractF2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        protected boolean k() {
            ECFieldElement multiplyPlusProduct;
            ECFieldElement squarePlusProduct;
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement = this.f14096b;
            ECFieldElement a2 = curve.getA();
            ECFieldElement b2 = curve.getB();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem != 6) {
                ECFieldElement eCFieldElement2 = this.f14097c;
                ECFieldElement multiply = eCFieldElement2.add(eCFieldElement).multiply(eCFieldElement2);
                if (coordinateSystem != 0) {
                    if (coordinateSystem != 1) {
                        throw new IllegalStateException("unsupported coordinate system");
                    }
                    ECFieldElement eCFieldElement3 = this.f14098d[0];
                    if (!eCFieldElement3.isOne()) {
                        ECFieldElement multiply2 = eCFieldElement3.multiply(eCFieldElement3.square());
                        multiply = multiply.multiply(eCFieldElement3);
                        a2 = a2.multiply(eCFieldElement3);
                        b2 = b2.multiply(multiply2);
                    }
                }
                return multiply.equals(eCFieldElement.add(a2).multiply(eCFieldElement.square()).add(b2));
            }
            ECFieldElement eCFieldElement4 = this.f14098d[0];
            boolean isOne = eCFieldElement4.isOne();
            if (eCFieldElement.isZero()) {
                ECFieldElement square = this.f14097c.square();
                if (!isOne) {
                    b2 = b2.multiply(eCFieldElement4.square());
                }
                return square.equals(b2);
            }
            ECFieldElement eCFieldElement5 = this.f14097c;
            ECFieldElement square2 = eCFieldElement.square();
            if (isOne) {
                multiplyPlusProduct = eCFieldElement5.square().add(eCFieldElement5).add(a2);
                squarePlusProduct = square2.square().add(b2);
            } else {
                ECFieldElement square3 = eCFieldElement4.square();
                ECFieldElement square4 = square3.square();
                multiplyPlusProduct = eCFieldElement5.add(eCFieldElement4).multiplyPlusProduct(eCFieldElement5, a2, square3);
                squarePlusProduct = square2.squarePlusProduct(b2, square4);
            }
            return multiplyPlusProduct.multiply(square2).equals(squarePlusProduct);
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        protected boolean l() {
            BigInteger cofactor = this.f14095a.getCofactor();
            if (ECConstants.TWO.equals(cofactor)) {
                return ((ECFieldElement.AbstractF2m) normalize().getAffineXCoord()).trace() != 0;
            } else if (ECConstants.FOUR.equals(cofactor)) {
                ECPoint normalize = normalize();
                ECFieldElement affineXCoord = normalize.getAffineXCoord();
                ECCurve eCCurve = this.f14095a;
                ECFieldElement i2 = ((ECCurve.AbstractF2m) eCCurve).i(affineXCoord.add(eCCurve.getA()));
                if (i2 == null) {
                    return false;
                }
                return ((ECFieldElement.AbstractF2m) affineXCoord.multiply(i2).add(normalize.getAffineYCoord())).trace() == 0;
            } else {
                return super.l();
            }
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint scaleX(ECFieldElement eCFieldElement) {
            if (isInfinity()) {
                return this;
            }
            int e2 = e();
            if (e2 == 5) {
                ECFieldElement rawXCoord = getRawXCoord();
                return getCurve().f(rawXCoord, getRawYCoord().add(rawXCoord).divide(eCFieldElement).add(rawXCoord.multiply(eCFieldElement)), g());
            } else if (e2 != 6) {
                return super.scaleX(eCFieldElement);
            } else {
                ECFieldElement rawXCoord2 = getRawXCoord();
                ECFieldElement rawYCoord = getRawYCoord();
                ECFieldElement eCFieldElement2 = g()[0];
                ECFieldElement multiply = rawXCoord2.multiply(eCFieldElement.square());
                return getCurve().f(multiply, rawYCoord.add(rawXCoord2).add(multiply), new ECFieldElement[]{eCFieldElement2.multiply(eCFieldElement)});
            }
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint scaleXNegateY(ECFieldElement eCFieldElement) {
            return scaleX(eCFieldElement);
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint scaleY(ECFieldElement eCFieldElement) {
            if (isInfinity()) {
                return this;
            }
            int e2 = e();
            if (e2 == 5 || e2 == 6) {
                ECFieldElement rawXCoord = getRawXCoord();
                return getCurve().f(rawXCoord, getRawYCoord().add(rawXCoord).multiply(eCFieldElement).add(rawXCoord), g());
            }
            return super.scaleY(eCFieldElement);
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint scaleYNegateX(ECFieldElement eCFieldElement) {
            return scaleY(eCFieldElement);
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint subtract(ECPoint eCPoint) {
            return eCPoint.isInfinity() ? this : add(eCPoint.negate());
        }

        public AbstractF2m tau() {
            ECPoint e2;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement = this.f14096b;
            if (coordinateSystem != 0) {
                if (coordinateSystem != 1) {
                    if (coordinateSystem != 5) {
                        if (coordinateSystem != 6) {
                            throw new IllegalStateException("unsupported coordinate system");
                        }
                    }
                }
                e2 = curve.f(eCFieldElement.square(), this.f14097c.square(), new ECFieldElement[]{this.f14098d[0].square()});
                return (AbstractF2m) e2;
            }
            e2 = curve.e(eCFieldElement.square(), this.f14097c.square());
            return (AbstractF2m) e2;
        }

        public AbstractF2m tauPow(int i2) {
            ECPoint e2;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement = this.f14096b;
            if (coordinateSystem != 0) {
                if (coordinateSystem != 1) {
                    if (coordinateSystem != 5) {
                        if (coordinateSystem != 6) {
                            throw new IllegalStateException("unsupported coordinate system");
                        }
                    }
                }
                e2 = curve.f(eCFieldElement.squarePow(i2), this.f14097c.squarePow(i2), new ECFieldElement[]{this.f14098d[0].squarePow(i2)});
                return (AbstractF2m) e2;
            }
            e2 = curve.e(eCFieldElement.squarePow(i2), this.f14097c.squarePow(i2));
            return (AbstractF2m) e2;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class AbstractFp extends ECPoint {
        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractFp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractFp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        protected boolean d() {
            return getAffineYCoord().testBitZero();
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        protected boolean k() {
            ECFieldElement eCFieldElement = this.f14096b;
            ECFieldElement eCFieldElement2 = this.f14097c;
            ECFieldElement a2 = this.f14095a.getA();
            ECFieldElement b2 = this.f14095a.getB();
            ECFieldElement square = eCFieldElement2.square();
            int e2 = e();
            if (e2 != 0) {
                if (e2 == 1) {
                    ECFieldElement eCFieldElement3 = this.f14098d[0];
                    if (!eCFieldElement3.isOne()) {
                        ECFieldElement square2 = eCFieldElement3.square();
                        ECFieldElement multiply = eCFieldElement3.multiply(square2);
                        square = square.multiply(eCFieldElement3);
                        a2 = a2.multiply(square2);
                        b2 = b2.multiply(multiply);
                    }
                } else if (e2 != 2 && e2 != 3 && e2 != 4) {
                    throw new IllegalStateException("unsupported coordinate system");
                } else {
                    ECFieldElement eCFieldElement4 = this.f14098d[0];
                    if (!eCFieldElement4.isOne()) {
                        ECFieldElement square3 = eCFieldElement4.square();
                        ECFieldElement square4 = square3.square();
                        ECFieldElement multiply2 = square3.multiply(square4);
                        a2 = a2.multiply(square4);
                        b2 = b2.multiply(multiply2);
                    }
                }
            }
            return square.equals(eCFieldElement.square().add(a2).multiply(eCFieldElement).add(b2));
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint subtract(ECPoint eCPoint) {
            return eCPoint.isInfinity() ? this : add(eCPoint.negate());
        }
    }

    /* loaded from: classes3.dex */
    public static class F2m extends AbstractF2m {
        /* JADX INFO: Access modifiers changed from: package-private */
        public F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint add(ECPoint eCPoint) {
            ECFieldElement eCFieldElement;
            ECFieldElement eCFieldElement2;
            ECFieldElement eCFieldElement3;
            ECFieldElement eCFieldElement4;
            ECFieldElement eCFieldElement5;
            ECFieldElement eCFieldElement6;
            if (isInfinity()) {
                return eCPoint;
            }
            if (eCPoint.isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement7 = this.f14096b;
            ECFieldElement eCFieldElement8 = eCPoint.f14096b;
            if (coordinateSystem == 0) {
                ECFieldElement eCFieldElement9 = this.f14097c;
                ECFieldElement eCFieldElement10 = eCPoint.f14097c;
                ECFieldElement add = eCFieldElement7.add(eCFieldElement8);
                ECFieldElement add2 = eCFieldElement9.add(eCFieldElement10);
                if (add.isZero()) {
                    return add2.isZero() ? twice() : curve.getInfinity();
                }
                ECFieldElement divide = add2.divide(add);
                ECFieldElement add3 = divide.square().add(divide).add(add).add(curve.getA());
                return new F2m(curve, add3, divide.multiply(eCFieldElement7.add(add3)).add(add3).add(eCFieldElement9));
            } else if (coordinateSystem == 1) {
                ECFieldElement eCFieldElement11 = this.f14097c;
                ECFieldElement eCFieldElement12 = this.f14098d[0];
                ECFieldElement eCFieldElement13 = eCPoint.f14097c;
                ECFieldElement eCFieldElement14 = eCPoint.f14098d[0];
                boolean isOne = eCFieldElement14.isOne();
                ECFieldElement add4 = eCFieldElement12.multiply(eCFieldElement13).add(isOne ? eCFieldElement11 : eCFieldElement11.multiply(eCFieldElement14));
                ECFieldElement add5 = eCFieldElement12.multiply(eCFieldElement8).add(isOne ? eCFieldElement7 : eCFieldElement7.multiply(eCFieldElement14));
                if (add5.isZero()) {
                    return add4.isZero() ? twice() : curve.getInfinity();
                }
                ECFieldElement square = add5.square();
                ECFieldElement multiply = square.multiply(add5);
                if (!isOne) {
                    eCFieldElement12 = eCFieldElement12.multiply(eCFieldElement14);
                }
                ECFieldElement add6 = add4.add(add5);
                ECFieldElement add7 = add6.multiplyPlusProduct(add4, square, curve.getA()).multiply(eCFieldElement12).add(multiply);
                ECFieldElement multiply2 = add5.multiply(add7);
                if (!isOne) {
                    square = square.multiply(eCFieldElement14);
                }
                return new F2m(curve, multiply2, add4.multiplyPlusProduct(eCFieldElement7, add5, eCFieldElement11).multiplyPlusProduct(square, add6, add7), new ECFieldElement[]{multiply.multiply(eCFieldElement12)});
            } else if (coordinateSystem == 6) {
                if (eCFieldElement7.isZero()) {
                    return eCFieldElement8.isZero() ? curve.getInfinity() : eCPoint.add(this);
                }
                ECFieldElement eCFieldElement15 = this.f14097c;
                ECFieldElement eCFieldElement16 = this.f14098d[0];
                ECFieldElement eCFieldElement17 = eCPoint.f14097c;
                ECFieldElement eCFieldElement18 = eCPoint.f14098d[0];
                boolean isOne2 = eCFieldElement16.isOne();
                if (isOne2) {
                    eCFieldElement = eCFieldElement8;
                    eCFieldElement2 = eCFieldElement17;
                } else {
                    eCFieldElement = eCFieldElement8.multiply(eCFieldElement16);
                    eCFieldElement2 = eCFieldElement17.multiply(eCFieldElement16);
                }
                boolean isOne3 = eCFieldElement18.isOne();
                if (isOne3) {
                    eCFieldElement3 = eCFieldElement15;
                } else {
                    eCFieldElement7 = eCFieldElement7.multiply(eCFieldElement18);
                    eCFieldElement3 = eCFieldElement15.multiply(eCFieldElement18);
                }
                ECFieldElement add8 = eCFieldElement3.add(eCFieldElement2);
                ECFieldElement add9 = eCFieldElement7.add(eCFieldElement);
                if (add9.isZero()) {
                    return add8.isZero() ? twice() : curve.getInfinity();
                }
                if (eCFieldElement8.isZero()) {
                    ECPoint normalize = normalize();
                    ECFieldElement xCoord = normalize.getXCoord();
                    ECFieldElement yCoord = normalize.getYCoord();
                    ECFieldElement divide2 = yCoord.add(eCFieldElement17).divide(xCoord);
                    eCFieldElement4 = divide2.square().add(divide2).add(xCoord).add(curve.getA());
                    if (eCFieldElement4.isZero()) {
                        return new F2m(curve, eCFieldElement4, curve.getB().sqrt());
                    }
                    eCFieldElement6 = divide2.multiply(xCoord.add(eCFieldElement4)).add(eCFieldElement4).add(yCoord).divide(eCFieldElement4).add(eCFieldElement4);
                    eCFieldElement5 = curve.fromBigInteger(ECConstants.ONE);
                } else {
                    ECFieldElement square2 = add9.square();
                    ECFieldElement multiply3 = add8.multiply(eCFieldElement7);
                    ECFieldElement multiply4 = add8.multiply(eCFieldElement);
                    ECFieldElement multiply5 = multiply3.multiply(multiply4);
                    if (multiply5.isZero()) {
                        return new F2m(curve, multiply5, curve.getB().sqrt());
                    }
                    ECFieldElement multiply6 = add8.multiply(square2);
                    ECFieldElement multiply7 = !isOne3 ? multiply6.multiply(eCFieldElement18) : multiply6;
                    ECFieldElement squarePlusProduct = multiply4.add(square2).squarePlusProduct(multiply7, eCFieldElement15.add(eCFieldElement16));
                    if (!isOne2) {
                        multiply7 = multiply7.multiply(eCFieldElement16);
                    }
                    eCFieldElement4 = multiply5;
                    eCFieldElement5 = multiply7;
                    eCFieldElement6 = squarePlusProduct;
                }
                return new F2m(curve, eCFieldElement4, eCFieldElement6, new ECFieldElement[]{eCFieldElement5});
            } else {
                throw new IllegalStateException("unsupported coordinate system");
            }
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        protected ECPoint c() {
            return new F2m(null, getAffineXCoord(), getAffineYCoord());
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        protected boolean d() {
            ECFieldElement rawXCoord = getRawXCoord();
            if (rawXCoord.isZero()) {
                return false;
            }
            ECFieldElement rawYCoord = getRawYCoord();
            int e2 = e();
            return (e2 == 5 || e2 == 6) ? rawYCoord.testBitZero() != rawXCoord.testBitZero() : rawYCoord.divide(rawXCoord).testBitZero();
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECFieldElement getYCoord() {
            int e2 = e();
            if (e2 == 5 || e2 == 6) {
                ECFieldElement eCFieldElement = this.f14096b;
                ECFieldElement eCFieldElement2 = this.f14097c;
                if (isInfinity() || eCFieldElement.isZero()) {
                    return eCFieldElement2;
                }
                ECFieldElement multiply = eCFieldElement2.add(eCFieldElement).multiply(eCFieldElement);
                if (6 == e2) {
                    ECFieldElement eCFieldElement3 = this.f14098d[0];
                    return !eCFieldElement3.isOne() ? multiply.divide(eCFieldElement3) : multiply;
                }
                return multiply;
            }
            return this.f14097c;
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint negate() {
            if (isInfinity()) {
                return this;
            }
            ECFieldElement eCFieldElement = this.f14096b;
            if (eCFieldElement.isZero()) {
                return this;
            }
            int e2 = e();
            if (e2 != 0) {
                if (e2 == 1) {
                    return new F2m(this.f14095a, eCFieldElement, this.f14097c.add(eCFieldElement), new ECFieldElement[]{this.f14098d[0]});
                } else if (e2 != 5) {
                    if (e2 == 6) {
                        ECFieldElement eCFieldElement2 = this.f14097c;
                        ECFieldElement eCFieldElement3 = this.f14098d[0];
                        return new F2m(this.f14095a, eCFieldElement, eCFieldElement2.add(eCFieldElement3), new ECFieldElement[]{eCFieldElement3});
                    }
                    throw new IllegalStateException("unsupported coordinate system");
                } else {
                    return new F2m(this.f14095a, eCFieldElement, this.f14097c.addOne());
                }
            }
            return new F2m(this.f14095a, eCFieldElement, this.f14097c.add(eCFieldElement));
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint twice() {
            ECFieldElement add;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement = this.f14096b;
            if (eCFieldElement.isZero()) {
                return curve.getInfinity();
            }
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem == 0) {
                ECFieldElement add2 = this.f14097c.divide(eCFieldElement).add(eCFieldElement);
                ECFieldElement add3 = add2.square().add(add2).add(curve.getA());
                return new F2m(curve, add3, eCFieldElement.squarePlusProduct(add3, add2.addOne()));
            } else if (coordinateSystem == 1) {
                ECFieldElement eCFieldElement2 = this.f14097c;
                ECFieldElement eCFieldElement3 = this.f14098d[0];
                boolean isOne = eCFieldElement3.isOne();
                ECFieldElement multiply = isOne ? eCFieldElement : eCFieldElement.multiply(eCFieldElement3);
                if (!isOne) {
                    eCFieldElement2 = eCFieldElement2.multiply(eCFieldElement3);
                }
                ECFieldElement square = eCFieldElement.square();
                ECFieldElement add4 = square.add(eCFieldElement2);
                ECFieldElement square2 = multiply.square();
                ECFieldElement add5 = add4.add(multiply);
                ECFieldElement multiplyPlusProduct = add5.multiplyPlusProduct(add4, square2, curve.getA());
                return new F2m(curve, multiply.multiply(multiplyPlusProduct), square.square().multiplyPlusProduct(multiply, multiplyPlusProduct, add5), new ECFieldElement[]{multiply.multiply(square2)});
            } else if (coordinateSystem == 6) {
                ECFieldElement eCFieldElement4 = this.f14097c;
                ECFieldElement eCFieldElement5 = this.f14098d[0];
                boolean isOne2 = eCFieldElement5.isOne();
                ECFieldElement multiply2 = isOne2 ? eCFieldElement4 : eCFieldElement4.multiply(eCFieldElement5);
                ECFieldElement square3 = isOne2 ? eCFieldElement5 : eCFieldElement5.square();
                ECFieldElement a2 = curve.getA();
                ECFieldElement multiply3 = isOne2 ? a2 : a2.multiply(square3);
                ECFieldElement add6 = eCFieldElement4.square().add(multiply2).add(multiply3);
                if (add6.isZero()) {
                    return new F2m(curve, add6, curve.getB().sqrt());
                }
                ECFieldElement square4 = add6.square();
                ECFieldElement multiply4 = isOne2 ? add6 : add6.multiply(square3);
                ECFieldElement b2 = curve.getB();
                if (b2.bitLength() < (curve.getFieldSize() >> 1)) {
                    ECFieldElement square5 = eCFieldElement4.add(eCFieldElement).square();
                    add = square5.add(add6).add(square3).multiply(square5).add(b2.isOne() ? multiply3.add(square3).square() : multiply3.squarePlusProduct(b2, square3.square())).add(square4);
                    if (!a2.isZero()) {
                        if (!a2.isOne()) {
                            add = add.add(a2.addOne().multiply(multiply4));
                        }
                        return new F2m(curve, square4, add, new ECFieldElement[]{multiply4});
                    }
                } else {
                    if (!isOne2) {
                        eCFieldElement = eCFieldElement.multiply(eCFieldElement5);
                    }
                    add = eCFieldElement.squarePlusProduct(add6, multiply2).add(square4);
                }
                add = add.add(multiply4);
                return new F2m(curve, square4, add, new ECFieldElement[]{multiply4});
            } else {
                throw new IllegalStateException("unsupported coordinate system");
            }
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint twicePlus(ECPoint eCPoint) {
            if (isInfinity()) {
                return eCPoint;
            }
            if (eCPoint.isInfinity()) {
                return twice();
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement = this.f14096b;
            if (eCFieldElement.isZero()) {
                return eCPoint;
            }
            if (curve.getCoordinateSystem() != 6) {
                return twice().add(eCPoint);
            }
            ECFieldElement eCFieldElement2 = eCPoint.f14096b;
            ECFieldElement eCFieldElement3 = eCPoint.f14098d[0];
            if (eCFieldElement2.isZero() || !eCFieldElement3.isOne()) {
                return twice().add(eCPoint);
            }
            ECFieldElement eCFieldElement4 = this.f14097c;
            ECFieldElement eCFieldElement5 = this.f14098d[0];
            ECFieldElement eCFieldElement6 = eCPoint.f14097c;
            ECFieldElement square = eCFieldElement.square();
            ECFieldElement square2 = eCFieldElement4.square();
            ECFieldElement square3 = eCFieldElement5.square();
            ECFieldElement add = curve.getA().multiply(square3).add(square2).add(eCFieldElement4.multiply(eCFieldElement5));
            ECFieldElement addOne = eCFieldElement6.addOne();
            ECFieldElement multiplyPlusProduct = curve.getA().add(addOne).multiply(square3).add(square2).multiplyPlusProduct(add, square, square3);
            ECFieldElement multiply = eCFieldElement2.multiply(square3);
            ECFieldElement square4 = multiply.add(add).square();
            if (square4.isZero()) {
                return multiplyPlusProduct.isZero() ? eCPoint.twice() : curve.getInfinity();
            } else if (multiplyPlusProduct.isZero()) {
                return new F2m(curve, multiplyPlusProduct, curve.getB().sqrt());
            } else {
                ECFieldElement multiply2 = multiplyPlusProduct.square().multiply(multiply);
                ECFieldElement multiply3 = multiplyPlusProduct.multiply(square4).multiply(square3);
                return new F2m(curve, multiply2, multiplyPlusProduct.add(square4).square().multiplyPlusProduct(add, addOne, multiply3), new ECFieldElement[]{multiply3});
            }
        }
    }

    /* loaded from: classes3.dex */
    public static class Fp extends AbstractFp {
        /* JADX INFO: Access modifiers changed from: package-private */
        public Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        /* JADX WARN: Removed duplicated region for block: B:61:0x0128  */
        /* JADX WARN: Removed duplicated region for block: B:62:0x0136  */
        @Override // org.bouncycastle.math.ec.ECPoint
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public ECPoint add(ECPoint eCPoint) {
            ECFieldElement square;
            ECFieldElement multiplyMinusProduct;
            ECFieldElement multiply;
            ECFieldElement eCFieldElement;
            if (isInfinity()) {
                return eCPoint;
            }
            if (eCPoint.isInfinity()) {
                return this;
            }
            if (this == eCPoint) {
                return twice();
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement2 = this.f14096b;
            ECFieldElement eCFieldElement3 = this.f14097c;
            ECFieldElement eCFieldElement4 = eCPoint.f14096b;
            ECFieldElement eCFieldElement5 = eCPoint.f14097c;
            if (coordinateSystem == 0) {
                ECFieldElement subtract = eCFieldElement4.subtract(eCFieldElement2);
                ECFieldElement subtract2 = eCFieldElement5.subtract(eCFieldElement3);
                if (subtract.isZero()) {
                    return subtract2.isZero() ? twice() : curve.getInfinity();
                }
                ECFieldElement divide = subtract2.divide(subtract);
                ECFieldElement subtract3 = divide.square().subtract(eCFieldElement2).subtract(eCFieldElement4);
                return new Fp(curve, subtract3, divide.multiply(eCFieldElement2.subtract(subtract3)).subtract(eCFieldElement3));
            } else if (coordinateSystem == 1) {
                ECFieldElement eCFieldElement6 = this.f14098d[0];
                ECFieldElement eCFieldElement7 = eCPoint.f14098d[0];
                boolean isOne = eCFieldElement6.isOne();
                boolean isOne2 = eCFieldElement7.isOne();
                if (!isOne) {
                    eCFieldElement5 = eCFieldElement5.multiply(eCFieldElement6);
                }
                if (!isOne2) {
                    eCFieldElement3 = eCFieldElement3.multiply(eCFieldElement7);
                }
                ECFieldElement subtract4 = eCFieldElement5.subtract(eCFieldElement3);
                if (!isOne) {
                    eCFieldElement4 = eCFieldElement4.multiply(eCFieldElement6);
                }
                if (!isOne2) {
                    eCFieldElement2 = eCFieldElement2.multiply(eCFieldElement7);
                }
                ECFieldElement subtract5 = eCFieldElement4.subtract(eCFieldElement2);
                if (subtract5.isZero()) {
                    return subtract4.isZero() ? twice() : curve.getInfinity();
                }
                if (isOne) {
                    eCFieldElement6 = eCFieldElement7;
                } else if (!isOne2) {
                    eCFieldElement6 = eCFieldElement6.multiply(eCFieldElement7);
                }
                ECFieldElement square2 = subtract5.square();
                ECFieldElement multiply2 = square2.multiply(subtract5);
                ECFieldElement multiply3 = square2.multiply(eCFieldElement2);
                ECFieldElement subtract6 = subtract4.square().multiply(eCFieldElement6).subtract(multiply2).subtract(s(multiply3));
                return new Fp(curve, subtract5.multiply(subtract6), multiply3.subtract(subtract6).multiplyMinusProduct(subtract4, eCFieldElement3, multiply2), new ECFieldElement[]{multiply2.multiply(eCFieldElement6)});
            } else if (coordinateSystem == 2 || coordinateSystem == 4) {
                ECFieldElement eCFieldElement8 = this.f14098d[0];
                ECFieldElement eCFieldElement9 = eCPoint.f14098d[0];
                boolean isOne3 = eCFieldElement8.isOne();
                if (isOne3 || !eCFieldElement8.equals(eCFieldElement9)) {
                    if (!isOne3) {
                        ECFieldElement square3 = eCFieldElement8.square();
                        eCFieldElement4 = square3.multiply(eCFieldElement4);
                        eCFieldElement5 = square3.multiply(eCFieldElement8).multiply(eCFieldElement5);
                    }
                    boolean isOne4 = eCFieldElement9.isOne();
                    if (!isOne4) {
                        ECFieldElement square4 = eCFieldElement9.square();
                        eCFieldElement2 = square4.multiply(eCFieldElement2);
                        eCFieldElement3 = square4.multiply(eCFieldElement9).multiply(eCFieldElement3);
                    }
                    ECFieldElement subtract7 = eCFieldElement2.subtract(eCFieldElement4);
                    ECFieldElement subtract8 = eCFieldElement3.subtract(eCFieldElement5);
                    if (subtract7.isZero()) {
                        return subtract8.isZero() ? twice() : curve.getInfinity();
                    }
                    square = subtract7.square();
                    ECFieldElement multiply4 = square.multiply(subtract7);
                    ECFieldElement multiply5 = square.multiply(eCFieldElement2);
                    ECFieldElement subtract9 = subtract8.square().add(multiply4).subtract(s(multiply5));
                    multiplyMinusProduct = multiply5.subtract(subtract9).multiplyMinusProduct(subtract8, multiply4, eCFieldElement3);
                    ECFieldElement multiply6 = !isOne3 ? subtract7.multiply(eCFieldElement8) : subtract7;
                    multiply = !isOne4 ? multiply6.multiply(eCFieldElement9) : multiply6;
                    if (multiply == subtract7) {
                        eCFieldElement = subtract9;
                        return new Fp(curve, eCFieldElement, multiplyMinusProduct, coordinateSystem != 4 ? new ECFieldElement[]{multiply, m(multiply, square)} : new ECFieldElement[]{multiply});
                    }
                    eCFieldElement = subtract9;
                } else {
                    ECFieldElement subtract10 = eCFieldElement2.subtract(eCFieldElement4);
                    ECFieldElement subtract11 = eCFieldElement3.subtract(eCFieldElement5);
                    if (subtract10.isZero()) {
                        return subtract11.isZero() ? twice() : curve.getInfinity();
                    }
                    ECFieldElement square5 = subtract10.square();
                    ECFieldElement multiply7 = eCFieldElement2.multiply(square5);
                    ECFieldElement multiply8 = eCFieldElement4.multiply(square5);
                    ECFieldElement multiply9 = multiply7.subtract(multiply8).multiply(eCFieldElement3);
                    eCFieldElement = subtract11.square().subtract(multiply7).subtract(multiply8);
                    multiplyMinusProduct = multiply7.subtract(eCFieldElement).multiply(subtract11).subtract(multiply9);
                    multiply = subtract10.multiply(eCFieldElement8);
                }
                square = null;
                return new Fp(curve, eCFieldElement, multiplyMinusProduct, coordinateSystem != 4 ? new ECFieldElement[]{multiply, m(multiply, square)} : new ECFieldElement[]{multiply});
            } else {
                throw new IllegalStateException("unsupported coordinate system");
            }
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        protected ECPoint c() {
            return new Fp(null, getAffineXCoord(), getAffineYCoord());
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECFieldElement getZCoord(int i2) {
            return (i2 == 1 && 4 == e()) ? p() : super.getZCoord(i2);
        }

        protected ECFieldElement m(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            ECFieldElement a2 = getCurve().getA();
            if (a2.isZero() || eCFieldElement.isOne()) {
                return a2;
            }
            if (eCFieldElement2 == null) {
                eCFieldElement2 = eCFieldElement.square();
            }
            ECFieldElement square = eCFieldElement2.square();
            ECFieldElement negate = a2.negate();
            return negate.bitLength() < a2.bitLength() ? square.multiply(negate).negate() : square.multiply(a2);
        }

        protected ECFieldElement n(ECFieldElement eCFieldElement) {
            return o(s(eCFieldElement));
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint negate() {
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            return curve.getCoordinateSystem() != 0 ? new Fp(curve, this.f14096b, this.f14097c.negate(), this.f14098d) : new Fp(curve, this.f14096b, this.f14097c.negate());
        }

        protected ECFieldElement o(ECFieldElement eCFieldElement) {
            return s(s(eCFieldElement));
        }

        protected ECFieldElement p() {
            ECFieldElement[] eCFieldElementArr = this.f14098d;
            ECFieldElement eCFieldElement = eCFieldElementArr[1];
            if (eCFieldElement == null) {
                ECFieldElement m2 = m(eCFieldElementArr[0], null);
                eCFieldElementArr[1] = m2;
                return m2;
            }
            return eCFieldElement;
        }

        protected ECFieldElement q(ECFieldElement eCFieldElement) {
            return s(eCFieldElement).add(eCFieldElement);
        }

        protected Fp r(boolean z) {
            ECFieldElement eCFieldElement = this.f14096b;
            ECFieldElement eCFieldElement2 = this.f14097c;
            ECFieldElement eCFieldElement3 = this.f14098d[0];
            ECFieldElement p2 = p();
            ECFieldElement add = q(eCFieldElement.square()).add(p2);
            ECFieldElement s2 = s(eCFieldElement2);
            ECFieldElement multiply = s2.multiply(eCFieldElement2);
            ECFieldElement s3 = s(eCFieldElement.multiply(multiply));
            ECFieldElement subtract = add.square().subtract(s(s3));
            ECFieldElement s4 = s(multiply.square());
            ECFieldElement subtract2 = add.multiply(s3.subtract(subtract)).subtract(s4);
            ECFieldElement s5 = z ? s(s4.multiply(p2)) : null;
            if (!eCFieldElement3.isOne()) {
                s2 = s2.multiply(eCFieldElement3);
            }
            return new Fp(getCurve(), subtract, subtract2, new ECFieldElement[]{s2, s5});
        }

        protected ECFieldElement s(ECFieldElement eCFieldElement) {
            return eCFieldElement.add(eCFieldElement);
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint threeTimes() {
            if (isInfinity()) {
                return this;
            }
            ECFieldElement eCFieldElement = this.f14097c;
            if (eCFieldElement.isZero()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem != 0) {
                return coordinateSystem != 4 ? twice().add(this) : r(false).add(this);
            }
            ECFieldElement eCFieldElement2 = this.f14096b;
            ECFieldElement s2 = s(eCFieldElement);
            ECFieldElement square = s2.square();
            ECFieldElement add = q(eCFieldElement2.square()).add(getCurve().getA());
            ECFieldElement subtract = q(eCFieldElement2).multiply(square).subtract(add.square());
            if (subtract.isZero()) {
                return getCurve().getInfinity();
            }
            ECFieldElement invert = subtract.multiply(s2).invert();
            ECFieldElement multiply = subtract.multiply(invert).multiply(add);
            ECFieldElement subtract2 = square.square().multiply(invert).subtract(multiply);
            ECFieldElement add2 = subtract2.subtract(multiply).multiply(multiply.add(subtract2)).add(eCFieldElement2);
            return new Fp(curve, add2, eCFieldElement2.subtract(add2).multiply(subtract2).subtract(eCFieldElement));
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint timesPow2(int i2) {
            ECFieldElement square;
            if (i2 >= 0) {
                if (i2 == 0 || isInfinity()) {
                    return this;
                }
                if (i2 == 1) {
                    return twice();
                }
                ECCurve curve = getCurve();
                ECFieldElement eCFieldElement = this.f14097c;
                if (eCFieldElement.isZero()) {
                    return curve.getInfinity();
                }
                int coordinateSystem = curve.getCoordinateSystem();
                ECFieldElement a2 = curve.getA();
                ECFieldElement eCFieldElement2 = this.f14096b;
                ECFieldElement[] eCFieldElementArr = this.f14098d;
                ECFieldElement fromBigInteger = eCFieldElementArr.length < 1 ? curve.fromBigInteger(ECConstants.ONE) : eCFieldElementArr[0];
                if (!fromBigInteger.isOne() && coordinateSystem != 0) {
                    if (coordinateSystem == 1) {
                        square = fromBigInteger.square();
                        eCFieldElement2 = eCFieldElement2.multiply(fromBigInteger);
                        eCFieldElement = eCFieldElement.multiply(square);
                    } else if (coordinateSystem == 2) {
                        square = null;
                    } else if (coordinateSystem != 4) {
                        throw new IllegalStateException("unsupported coordinate system");
                    } else {
                        a2 = p();
                    }
                    a2 = m(fromBigInteger, square);
                }
                int i3 = 0;
                while (i3 < i2) {
                    if (eCFieldElement.isZero()) {
                        return curve.getInfinity();
                    }
                    ECFieldElement q2 = q(eCFieldElement2.square());
                    ECFieldElement s2 = s(eCFieldElement);
                    ECFieldElement multiply = s2.multiply(eCFieldElement);
                    ECFieldElement s3 = s(eCFieldElement2.multiply(multiply));
                    ECFieldElement s4 = s(multiply.square());
                    if (!a2.isZero()) {
                        q2 = q2.add(a2);
                        a2 = s(s4.multiply(a2));
                    }
                    ECFieldElement subtract = q2.square().subtract(s(s3));
                    eCFieldElement = q2.multiply(s3.subtract(subtract)).subtract(s4);
                    fromBigInteger = fromBigInteger.isOne() ? s2 : s2.multiply(fromBigInteger);
                    i3++;
                    eCFieldElement2 = subtract;
                }
                if (coordinateSystem == 0) {
                    ECFieldElement invert = fromBigInteger.invert();
                    ECFieldElement square2 = invert.square();
                    return new Fp(curve, eCFieldElement2.multiply(square2), eCFieldElement.multiply(square2.multiply(invert)));
                } else if (coordinateSystem != 1) {
                    if (coordinateSystem != 2) {
                        if (coordinateSystem == 4) {
                            return new Fp(curve, eCFieldElement2, eCFieldElement, new ECFieldElement[]{fromBigInteger, a2});
                        }
                        throw new IllegalStateException("unsupported coordinate system");
                    }
                    return new Fp(curve, eCFieldElement2, eCFieldElement, new ECFieldElement[]{fromBigInteger});
                } else {
                    return new Fp(curve, eCFieldElement2.multiply(fromBigInteger), eCFieldElement, new ECFieldElement[]{fromBigInteger.multiply(fromBigInteger.square())});
                }
            }
            throw new IllegalArgumentException("'e' cannot be negative");
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint twice() {
            ECFieldElement eCFieldElement;
            ECFieldElement multiply;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement2 = this.f14097c;
            if (eCFieldElement2.isZero()) {
                return curve.getInfinity();
            }
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement3 = this.f14096b;
            if (coordinateSystem == 0) {
                ECFieldElement divide = q(eCFieldElement3.square()).add(getCurve().getA()).divide(s(eCFieldElement2));
                ECFieldElement subtract = divide.square().subtract(s(eCFieldElement3));
                return new Fp(curve, subtract, divide.multiply(eCFieldElement3.subtract(subtract)).subtract(eCFieldElement2));
            } else if (coordinateSystem == 1) {
                ECFieldElement eCFieldElement4 = this.f14098d[0];
                boolean isOne = eCFieldElement4.isOne();
                ECFieldElement a2 = curve.getA();
                if (!a2.isZero() && !isOne) {
                    a2 = a2.multiply(eCFieldElement4.square());
                }
                ECFieldElement add = a2.add(q(eCFieldElement3.square()));
                ECFieldElement multiply2 = isOne ? eCFieldElement2 : eCFieldElement2.multiply(eCFieldElement4);
                ECFieldElement square = isOne ? eCFieldElement2.square() : multiply2.multiply(eCFieldElement2);
                ECFieldElement o2 = o(eCFieldElement3.multiply(square));
                ECFieldElement subtract2 = add.square().subtract(s(o2));
                ECFieldElement s2 = s(multiply2);
                ECFieldElement multiply3 = subtract2.multiply(s2);
                ECFieldElement s3 = s(square);
                return new Fp(curve, multiply3, o2.subtract(subtract2).multiply(add).subtract(s(s3.square())), new ECFieldElement[]{s(isOne ? s(s3) : s2.square()).multiply(multiply2)});
            } else if (coordinateSystem != 2) {
                if (coordinateSystem == 4) {
                    return r(true);
                }
                throw new IllegalStateException("unsupported coordinate system");
            } else {
                ECFieldElement eCFieldElement5 = this.f14098d[0];
                boolean isOne2 = eCFieldElement5.isOne();
                ECFieldElement square2 = eCFieldElement2.square();
                ECFieldElement square3 = square2.square();
                ECFieldElement a3 = curve.getA();
                ECFieldElement negate = a3.negate();
                if (negate.toBigInteger().equals(BigInteger.valueOf(3L))) {
                    ECFieldElement square4 = isOne2 ? eCFieldElement5 : eCFieldElement5.square();
                    eCFieldElement = q(eCFieldElement3.add(square4).multiply(eCFieldElement3.subtract(square4)));
                    multiply = square2.multiply(eCFieldElement3);
                } else {
                    ECFieldElement q2 = q(eCFieldElement3.square());
                    if (!isOne2) {
                        if (a3.isZero()) {
                            eCFieldElement = q2;
                        } else {
                            ECFieldElement square5 = eCFieldElement5.square().square();
                            if (negate.bitLength() < a3.bitLength()) {
                                eCFieldElement = q2.subtract(square5.multiply(negate));
                            } else {
                                a3 = square5.multiply(a3);
                            }
                        }
                        multiply = eCFieldElement3.multiply(square2);
                    }
                    eCFieldElement = q2.add(a3);
                    multiply = eCFieldElement3.multiply(square2);
                }
                ECFieldElement o3 = o(multiply);
                ECFieldElement subtract3 = eCFieldElement.square().subtract(s(o3));
                ECFieldElement subtract4 = o3.subtract(subtract3).multiply(eCFieldElement).subtract(n(square3));
                ECFieldElement s4 = s(eCFieldElement2);
                if (!isOne2) {
                    s4 = s4.multiply(eCFieldElement5);
                }
                return new Fp(curve, subtract3, subtract4, new ECFieldElement[]{s4});
            }
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint twicePlus(ECPoint eCPoint) {
            if (this == eCPoint) {
                return threeTimes();
            }
            if (isInfinity()) {
                return eCPoint;
            }
            if (eCPoint.isInfinity()) {
                return twice();
            }
            ECFieldElement eCFieldElement = this.f14097c;
            if (eCFieldElement.isZero()) {
                return eCPoint;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem != 0) {
                return coordinateSystem != 4 ? twice().add(eCPoint) : r(false).add(eCPoint);
            }
            ECFieldElement eCFieldElement2 = this.f14096b;
            ECFieldElement eCFieldElement3 = eCPoint.f14096b;
            ECFieldElement eCFieldElement4 = eCPoint.f14097c;
            ECFieldElement subtract = eCFieldElement3.subtract(eCFieldElement2);
            ECFieldElement subtract2 = eCFieldElement4.subtract(eCFieldElement);
            if (subtract.isZero()) {
                return subtract2.isZero() ? threeTimes() : this;
            }
            ECFieldElement square = subtract.square();
            ECFieldElement subtract3 = square.multiply(s(eCFieldElement2).add(eCFieldElement3)).subtract(subtract2.square());
            if (subtract3.isZero()) {
                return curve.getInfinity();
            }
            ECFieldElement invert = subtract3.multiply(subtract).invert();
            ECFieldElement multiply = subtract3.multiply(invert).multiply(subtract2);
            ECFieldElement subtract4 = s(eCFieldElement).multiply(square).multiply(subtract).multiply(invert).subtract(multiply);
            ECFieldElement add = subtract4.subtract(multiply).multiply(multiply.add(subtract4)).add(eCFieldElement3);
            return new Fp(curve, add, eCFieldElement2.subtract(add).multiply(subtract4).subtract(eCFieldElement));
        }
    }

    protected ECPoint(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, f(eCCurve));
    }

    protected ECPoint(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        this.f14099e = null;
        this.f14095a = eCCurve;
        this.f14096b = eCFieldElement;
        this.f14097c = eCFieldElement2;
        this.f14098d = eCFieldElementArr;
    }

    protected static ECFieldElement[] f(ECCurve eCCurve) {
        int coordinateSystem = eCCurve == null ? 0 : eCCurve.getCoordinateSystem();
        if (coordinateSystem == 0 || coordinateSystem == 5) {
            return f14094f;
        }
        ECFieldElement fromBigInteger = eCCurve.fromBigInteger(ECConstants.ONE);
        if (coordinateSystem != 1 && coordinateSystem != 2) {
            if (coordinateSystem == 3) {
                return new ECFieldElement[]{fromBigInteger, fromBigInteger, fromBigInteger};
            }
            if (coordinateSystem == 4) {
                return new ECFieldElement[]{fromBigInteger, eCCurve.getA()};
            }
            if (coordinateSystem != 6) {
                throw new IllegalArgumentException("unknown coordinate system");
            }
        }
        return new ECFieldElement[]{fromBigInteger};
    }

    protected void a() {
        if (!isNormalized()) {
            throw new IllegalStateException("point not in normal form");
        }
    }

    public abstract ECPoint add(ECPoint eCPoint);

    protected ECPoint b(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return getCurve().e(getRawXCoord().multiply(eCFieldElement), getRawYCoord().multiply(eCFieldElement2));
    }

    protected abstract ECPoint c();

    protected abstract boolean d();

    protected int e() {
        ECCurve eCCurve = this.f14095a;
        if (eCCurve == null) {
            return 0;
        }
        return eCCurve.getCoordinateSystem();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ECPoint) {
            return equals((ECPoint) obj);
        }
        return false;
    }

    public boolean equals(ECPoint eCPoint) {
        ECPoint eCPoint2;
        if (eCPoint == null) {
            return false;
        }
        ECCurve curve = getCurve();
        ECCurve curve2 = eCPoint.getCurve();
        boolean z = curve == null;
        boolean z2 = curve2 == null;
        boolean isInfinity = isInfinity();
        boolean isInfinity2 = eCPoint.isInfinity();
        if (isInfinity || isInfinity2) {
            if (isInfinity && isInfinity2) {
                return z || z2 || curve.equals(curve2);
            }
            return false;
        }
        if (!z || !z2) {
            if (!z) {
                if (z2) {
                    eCPoint2 = normalize();
                } else if (!curve.equals(curve2)) {
                    return false;
                } else {
                    ECPoint[] eCPointArr = {this, curve.importPoint(eCPoint)};
                    curve.normalizeAll(eCPointArr);
                    eCPoint2 = eCPointArr[0];
                    eCPoint = eCPointArr[1];
                }
                return eCPoint2.getXCoord().equals(eCPoint.getXCoord()) && eCPoint2.getYCoord().equals(eCPoint.getYCoord());
            }
            eCPoint = eCPoint.normalize();
        }
        eCPoint2 = this;
        if (eCPoint2.getXCoord().equals(eCPoint.getXCoord())) {
            return false;
        }
    }

    protected final ECFieldElement[] g() {
        return this.f14098d;
    }

    public ECFieldElement getAffineXCoord() {
        a();
        return getXCoord();
    }

    public ECFieldElement getAffineYCoord() {
        a();
        return getYCoord();
    }

    public ECCurve getCurve() {
        return this.f14095a;
    }

    public final ECPoint getDetachedPoint() {
        return normalize().c();
    }

    public byte[] getEncoded(boolean z) {
        if (isInfinity()) {
            return new byte[1];
        }
        ECPoint normalize = normalize();
        byte[] encoded = normalize.getXCoord().getEncoded();
        if (z) {
            byte[] bArr = new byte[encoded.length + 1];
            bArr[0] = (byte) (normalize.d() ? 3 : 2);
            System.arraycopy(encoded, 0, bArr, 1, encoded.length);
            return bArr;
        }
        byte[] encoded2 = normalize.getYCoord().getEncoded();
        byte[] bArr2 = new byte[encoded.length + encoded2.length + 1];
        bArr2[0] = 4;
        System.arraycopy(encoded, 0, bArr2, 1, encoded.length);
        System.arraycopy(encoded2, 0, bArr2, encoded.length + 1, encoded2.length);
        return bArr2;
    }

    public final ECFieldElement getRawXCoord() {
        return this.f14096b;
    }

    public final ECFieldElement getRawYCoord() {
        return this.f14097c;
    }

    public ECFieldElement getXCoord() {
        return this.f14096b;
    }

    public ECFieldElement getYCoord() {
        return this.f14097c;
    }

    public ECFieldElement getZCoord(int i2) {
        if (i2 >= 0) {
            ECFieldElement[] eCFieldElementArr = this.f14098d;
            if (i2 < eCFieldElementArr.length) {
                return eCFieldElementArr[i2];
            }
        }
        return null;
    }

    public ECFieldElement[] getZCoords() {
        ECFieldElement[] eCFieldElementArr = this.f14098d;
        int length = eCFieldElementArr.length;
        if (length == 0) {
            return f14094f;
        }
        ECFieldElement[] eCFieldElementArr2 = new ECFieldElement[length];
        System.arraycopy(eCFieldElementArr, 0, eCFieldElementArr2, 0, length);
        return eCFieldElementArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean h(final boolean z, final boolean z2) {
        if (isInfinity()) {
            return true;
        }
        return !((ValidityPrecompInfo) getCurve().precompute(this, "bc_validity", new PreCompCallback() { // from class: org.bouncycastle.math.ec.ECPoint.1
            @Override // org.bouncycastle.math.ec.PreCompCallback
            public PreCompInfo precompute(PreCompInfo preCompInfo) {
                ValidityPrecompInfo validityPrecompInfo = preCompInfo instanceof ValidityPrecompInfo ? (ValidityPrecompInfo) preCompInfo : null;
                if (validityPrecompInfo == null) {
                    validityPrecompInfo = new ValidityPrecompInfo();
                }
                if (validityPrecompInfo.b()) {
                    return validityPrecompInfo;
                }
                if (!validityPrecompInfo.a()) {
                    if (!z && !ECPoint.this.k()) {
                        validityPrecompInfo.e();
                        return validityPrecompInfo;
                    }
                    validityPrecompInfo.d();
                }
                if (z2 && !validityPrecompInfo.c()) {
                    if (!ECPoint.this.l()) {
                        validityPrecompInfo.e();
                        return validityPrecompInfo;
                    }
                    validityPrecompInfo.f();
                }
                return validityPrecompInfo;
            }
        })).b();
    }

    public int hashCode() {
        ECCurve curve = getCurve();
        int i2 = curve == null ? 0 : ~curve.hashCode();
        if (isInfinity()) {
            return i2;
        }
        ECPoint normalize = normalize();
        return (i2 ^ (normalize.getXCoord().hashCode() * 17)) ^ (normalize.getYCoord().hashCode() * 257);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean i() {
        return h(false, false);
    }

    public boolean isInfinity() {
        if (this.f14096b != null && this.f14097c != null) {
            ECFieldElement[] eCFieldElementArr = this.f14098d;
            if (eCFieldElementArr.length <= 0 || !eCFieldElementArr[0].isZero()) {
                return false;
            }
        }
        return true;
    }

    public boolean isNormalized() {
        int e2 = e();
        return e2 == 0 || e2 == 5 || isInfinity() || this.f14098d[0].isOne();
    }

    public boolean isValid() {
        return h(false, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ECPoint j(ECFieldElement eCFieldElement) {
        int e2 = e();
        if (e2 != 1) {
            if (e2 == 2 || e2 == 3 || e2 == 4) {
                ECFieldElement square = eCFieldElement.square();
                return b(square, square.multiply(eCFieldElement));
            } else if (e2 != 6) {
                throw new IllegalStateException("not a projective coordinate system");
            }
        }
        return b(eCFieldElement, eCFieldElement);
    }

    protected abstract boolean k();

    protected boolean l() {
        BigInteger order;
        return ECConstants.ONE.equals(this.f14095a.getCofactor()) || (order = this.f14095a.getOrder()) == null || ECAlgorithms.referenceMultiply(this, order).isInfinity();
    }

    public ECPoint multiply(BigInteger bigInteger) {
        return getCurve().getMultiplier().multiply(this, bigInteger);
    }

    public abstract ECPoint negate();

    public ECPoint normalize() {
        int e2;
        if (isInfinity() || (e2 = e()) == 0 || e2 == 5) {
            return this;
        }
        ECFieldElement zCoord = getZCoord(0);
        if (zCoord.isOne()) {
            return this;
        }
        if (this.f14095a != null) {
            ECFieldElement randomFieldElementMult = this.f14095a.randomFieldElementMult(CryptoServicesRegistrar.getSecureRandom());
            return j(zCoord.multiply(randomFieldElementMult).invert().multiply(randomFieldElementMult));
        }
        throw new IllegalStateException("Detached points must be in affine coordinates");
    }

    public ECPoint scaleX(ECFieldElement eCFieldElement) {
        return isInfinity() ? this : getCurve().f(getRawXCoord().multiply(eCFieldElement), getRawYCoord(), g());
    }

    public ECPoint scaleXNegateY(ECFieldElement eCFieldElement) {
        return isInfinity() ? this : getCurve().f(getRawXCoord().multiply(eCFieldElement), getRawYCoord().negate(), g());
    }

    public ECPoint scaleY(ECFieldElement eCFieldElement) {
        return isInfinity() ? this : getCurve().f(getRawXCoord(), getRawYCoord().multiply(eCFieldElement), g());
    }

    public ECPoint scaleYNegateX(ECFieldElement eCFieldElement) {
        return isInfinity() ? this : getCurve().f(getRawXCoord().negate(), getRawYCoord().multiply(eCFieldElement), g());
    }

    public abstract ECPoint subtract(ECPoint eCPoint);

    public ECPoint threeTimes() {
        return twicePlus(this);
    }

    public ECPoint timesPow2(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("'e' cannot be negative");
        }
        ECPoint eCPoint = this;
        while (true) {
            i2--;
            if (i2 < 0) {
                return eCPoint;
            }
            eCPoint = eCPoint.twice();
        }
    }

    public String toString() {
        if (isInfinity()) {
            return "INF";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        stringBuffer.append(getRawXCoord());
        stringBuffer.append(AbstractJsonLexerKt.COMMA);
        stringBuffer.append(getRawYCoord());
        for (int i2 = 0; i2 < this.f14098d.length; i2++) {
            stringBuffer.append(AbstractJsonLexerKt.COMMA);
            stringBuffer.append(this.f14098d[i2]);
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }

    public abstract ECPoint twice();

    public ECPoint twicePlus(ECPoint eCPoint) {
        return twice().add(eCPoint);
    }
}
