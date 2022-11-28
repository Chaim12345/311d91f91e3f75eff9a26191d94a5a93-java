package org.bouncycastle.math.ec;

import java.math.BigInteger;
import java.util.Random;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Integers;
/* loaded from: classes3.dex */
public abstract class ECFieldElement implements ECConstants {

    /* loaded from: classes3.dex */
    public static abstract class AbstractF2m extends ECFieldElement {
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v1, types: [org.bouncycastle.math.ec.ECFieldElement] */
        /* JADX WARN: Type inference failed for: r3v3 */
        /* JADX WARN: Type inference failed for: r4v3, types: [org.bouncycastle.math.ec.ECFieldElement] */
        public ECFieldElement halfTrace() {
            int fieldSize = getFieldSize();
            if ((fieldSize & 1) != 0) {
                int i2 = (fieldSize + 1) >>> 1;
                int numberOfLeadingZeros = 31 - Integers.numberOfLeadingZeros(i2);
                ECFieldElement eCFieldElement = this;
                int i3 = 1;
                while (numberOfLeadingZeros > 0) {
                    eCFieldElement = eCFieldElement.squarePow(i3 << 1).add(eCFieldElement);
                    numberOfLeadingZeros--;
                    i3 = i2 >>> numberOfLeadingZeros;
                    if ((i3 & 1) != 0) {
                        eCFieldElement = eCFieldElement.squarePow(2).add(this);
                    }
                }
                return eCFieldElement;
            }
            throw new IllegalStateException("Half-trace only defined for odd m");
        }

        public boolean hasFastTrace() {
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v1, types: [org.bouncycastle.math.ec.ECFieldElement] */
        /* JADX WARN: Type inference failed for: r3v3 */
        /* JADX WARN: Type inference failed for: r4v2, types: [org.bouncycastle.math.ec.ECFieldElement] */
        public int trace() {
            int fieldSize = getFieldSize();
            int numberOfLeadingZeros = 31 - Integers.numberOfLeadingZeros(fieldSize);
            ECFieldElement eCFieldElement = this;
            int i2 = 1;
            while (numberOfLeadingZeros > 0) {
                eCFieldElement = eCFieldElement.squarePow(i2).add(eCFieldElement);
                numberOfLeadingZeros--;
                i2 = fieldSize >>> numberOfLeadingZeros;
                if ((i2 & 1) != 0) {
                    eCFieldElement = eCFieldElement.square().add(this);
                }
            }
            if (eCFieldElement.isZero()) {
                return 0;
            }
            if (eCFieldElement.isOne()) {
                return 1;
            }
            throw new IllegalStateException("Internal error in trace calculation");
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class AbstractFp extends ECFieldElement {
    }

    /* loaded from: classes3.dex */
    public static class F2m extends AbstractF2m {
        public static final int GNB = 1;
        public static final int PPB = 3;
        public static final int TPB = 2;

        /* renamed from: a  reason: collision with root package name */
        LongArray f14089a;
        private int[] ks;

        /* renamed from: m  reason: collision with root package name */
        private int f14090m;
        private int representation;

        /* JADX INFO: Access modifiers changed from: package-private */
        public F2m(int i2, int i3, int i4, int i5, BigInteger bigInteger) {
            if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > i2) {
                throw new IllegalArgumentException("x value invalid in F2m field element");
            }
            if (i4 == 0 && i5 == 0) {
                this.representation = 2;
                this.ks = new int[]{i3};
            } else if (i4 >= i5) {
                throw new IllegalArgumentException("k2 must be smaller than k3");
            } else {
                if (i4 <= 0) {
                    throw new IllegalArgumentException("k2 must be larger than 0");
                }
                this.representation = 3;
                this.ks = new int[]{i3, i4, i5};
            }
            this.f14090m = i2;
            this.f14089a = new LongArray(bigInteger);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public F2m(int i2, int[] iArr, LongArray longArray) {
            this.f14090m = i2;
            this.representation = iArr.length == 1 ? 2 : 3;
            this.ks = iArr;
            this.f14089a = longArray;
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement add(ECFieldElement eCFieldElement) {
            LongArray longArray = (LongArray) this.f14089a.clone();
            longArray.addShiftedByWords(((F2m) eCFieldElement).f14089a, 0);
            return new F2m(this.f14090m, this.ks, longArray);
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement addOne() {
            return new F2m(this.f14090m, this.ks, this.f14089a.addOne());
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public int bitLength() {
            return this.f14089a.degree();
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement divide(ECFieldElement eCFieldElement) {
            return multiply(eCFieldElement.invert());
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof F2m) {
                F2m f2m = (F2m) obj;
                return this.f14090m == f2m.f14090m && this.representation == f2m.representation && Arrays.areEqual(this.ks, f2m.ks) && this.f14089a.equals(f2m.f14089a);
            }
            return false;
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public String getFieldName() {
            return "F2m";
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public int getFieldSize() {
            return this.f14090m;
        }

        public int getK1() {
            return this.ks[0];
        }

        public int getK2() {
            int[] iArr = this.ks;
            if (iArr.length >= 2) {
                return iArr[1];
            }
            return 0;
        }

        public int getK3() {
            int[] iArr = this.ks;
            if (iArr.length >= 3) {
                return iArr[2];
            }
            return 0;
        }

        public int getM() {
            return this.f14090m;
        }

        public int getRepresentation() {
            return this.representation;
        }

        public int hashCode() {
            return (this.f14089a.hashCode() ^ this.f14090m) ^ Arrays.hashCode(this.ks);
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement invert() {
            int i2 = this.f14090m;
            int[] iArr = this.ks;
            return new F2m(i2, iArr, this.f14089a.modInverse(i2, iArr));
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public boolean isOne() {
            return this.f14089a.isOne();
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public boolean isZero() {
            return this.f14089a.isZero();
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement multiply(ECFieldElement eCFieldElement) {
            int i2 = this.f14090m;
            int[] iArr = this.ks;
            return new F2m(i2, iArr, this.f14089a.modMultiply(((F2m) eCFieldElement).f14089a, i2, iArr));
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            return multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            LongArray longArray = this.f14089a;
            LongArray longArray2 = ((F2m) eCFieldElement).f14089a;
            LongArray longArray3 = ((F2m) eCFieldElement2).f14089a;
            LongArray longArray4 = ((F2m) eCFieldElement3).f14089a;
            LongArray multiply = longArray.multiply(longArray2, this.f14090m, this.ks);
            LongArray multiply2 = longArray3.multiply(longArray4, this.f14090m, this.ks);
            if (multiply == longArray || multiply == longArray2) {
                multiply = (LongArray) multiply.clone();
            }
            multiply.addShiftedByWords(multiply2, 0);
            multiply.reduce(this.f14090m, this.ks);
            return new F2m(this.f14090m, this.ks, multiply);
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement negate() {
            return this;
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement sqrt() {
            return (this.f14089a.isZero() || this.f14089a.isOne()) ? this : squarePow(this.f14090m - 1);
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement square() {
            int i2 = this.f14090m;
            int[] iArr = this.ks;
            return new F2m(i2, iArr, this.f14089a.modSquare(i2, iArr));
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            return squarePlusProduct(eCFieldElement, eCFieldElement2);
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            LongArray longArray = this.f14089a;
            LongArray longArray2 = ((F2m) eCFieldElement).f14089a;
            LongArray longArray3 = ((F2m) eCFieldElement2).f14089a;
            LongArray square = longArray.square(this.f14090m, this.ks);
            LongArray multiply = longArray2.multiply(longArray3, this.f14090m, this.ks);
            if (square == longArray) {
                square = (LongArray) square.clone();
            }
            square.addShiftedByWords(multiply, 0);
            square.reduce(this.f14090m, this.ks);
            return new F2m(this.f14090m, this.ks, square);
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement squarePow(int i2) {
            if (i2 < 1) {
                return this;
            }
            int i3 = this.f14090m;
            int[] iArr = this.ks;
            return new F2m(i3, iArr, this.f14089a.modSquareN(i2, i3, iArr));
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement subtract(ECFieldElement eCFieldElement) {
            return add(eCFieldElement);
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public boolean testBitZero() {
            return this.f14089a.testBitZero();
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public BigInteger toBigInteger() {
            return this.f14089a.toBigInteger();
        }
    }

    /* loaded from: classes3.dex */
    public static class Fp extends AbstractFp {

        /* renamed from: a  reason: collision with root package name */
        BigInteger f14091a;

        /* renamed from: b  reason: collision with root package name */
        BigInteger f14092b;

        /* renamed from: c  reason: collision with root package name */
        BigInteger f14093c;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Fp(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            if (bigInteger3 == null || bigInteger3.signum() < 0 || bigInteger3.compareTo(bigInteger) >= 0) {
                throw new IllegalArgumentException("x value invalid in Fp field element");
            }
            this.f14091a = bigInteger;
            this.f14092b = bigInteger2;
            this.f14093c = bigInteger3;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static BigInteger a(BigInteger bigInteger) {
            int bitLength = bigInteger.bitLength();
            if (bitLength < 96 || bigInteger.shiftRight(bitLength - 64).longValue() != -1) {
                return null;
            }
            return ECConstants.ONE.shiftLeft(bitLength).subtract(bigInteger);
        }

        private ECFieldElement checkSqrt(ECFieldElement eCFieldElement) {
            if (eCFieldElement.square().equals(this)) {
                return eCFieldElement;
            }
            return null;
        }

        private BigInteger[] lucasSequence(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            int bitLength = bigInteger3.bitLength();
            int lowestSetBit = bigInteger3.getLowestSetBit();
            BigInteger bigInteger4 = ECConstants.ONE;
            BigInteger bigInteger5 = bigInteger;
            BigInteger bigInteger6 = bigInteger4;
            BigInteger bigInteger7 = ECConstants.TWO;
            BigInteger bigInteger8 = bigInteger6;
            for (int i2 = bitLength - 1; i2 >= lowestSetBit + 1; i2--) {
                bigInteger4 = f(bigInteger4, bigInteger8);
                if (bigInteger3.testBit(i2)) {
                    bigInteger8 = f(bigInteger4, bigInteger2);
                    bigInteger6 = f(bigInteger6, bigInteger5);
                    bigInteger7 = g(bigInteger5.multiply(bigInteger7).subtract(bigInteger.multiply(bigInteger4)));
                    bigInteger5 = g(bigInteger5.multiply(bigInteger5).subtract(bigInteger8.shiftLeft(1)));
                } else {
                    BigInteger g2 = g(bigInteger6.multiply(bigInteger7).subtract(bigInteger4));
                    BigInteger g3 = g(bigInteger5.multiply(bigInteger7).subtract(bigInteger.multiply(bigInteger4)));
                    bigInteger7 = g(bigInteger7.multiply(bigInteger7).subtract(bigInteger4.shiftLeft(1)));
                    bigInteger5 = g3;
                    bigInteger6 = g2;
                    bigInteger8 = bigInteger4;
                }
            }
            BigInteger f2 = f(bigInteger4, bigInteger8);
            BigInteger f3 = f(f2, bigInteger2);
            BigInteger g4 = g(bigInteger6.multiply(bigInteger7).subtract(f2));
            BigInteger g5 = g(bigInteger5.multiply(bigInteger7).subtract(bigInteger.multiply(f2)));
            BigInteger f4 = f(f2, f3);
            for (int i3 = 1; i3 <= lowestSetBit; i3++) {
                g4 = f(g4, g5);
                g5 = g(g5.multiply(g5).subtract(f4.shiftLeft(1)));
                f4 = f(f4, f4);
            }
            return new BigInteger[]{g4, g5};
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement add(ECFieldElement eCFieldElement) {
            return new Fp(this.f14091a, this.f14092b, b(this.f14093c, eCFieldElement.toBigInteger()));
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement addOne() {
            BigInteger add = this.f14093c.add(ECConstants.ONE);
            if (add.compareTo(this.f14091a) == 0) {
                add = ECConstants.ZERO;
            }
            return new Fp(this.f14091a, this.f14092b, add);
        }

        protected BigInteger b(BigInteger bigInteger, BigInteger bigInteger2) {
            BigInteger add = bigInteger.add(bigInteger2);
            return add.compareTo(this.f14091a) >= 0 ? add.subtract(this.f14091a) : add;
        }

        protected BigInteger c(BigInteger bigInteger) {
            BigInteger shiftLeft = bigInteger.shiftLeft(1);
            return shiftLeft.compareTo(this.f14091a) >= 0 ? shiftLeft.subtract(this.f14091a) : shiftLeft;
        }

        protected BigInteger d(BigInteger bigInteger) {
            if (bigInteger.testBit(0)) {
                bigInteger = this.f14091a.subtract(bigInteger);
            }
            return bigInteger.shiftRight(1);
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement divide(ECFieldElement eCFieldElement) {
            return new Fp(this.f14091a, this.f14092b, f(this.f14093c, e(eCFieldElement.toBigInteger())));
        }

        protected BigInteger e(BigInteger bigInteger) {
            return BigIntegers.modOddInverse(this.f14091a, bigInteger);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Fp) {
                Fp fp = (Fp) obj;
                return this.f14091a.equals(fp.f14091a) && this.f14093c.equals(fp.f14093c);
            }
            return false;
        }

        protected BigInteger f(BigInteger bigInteger, BigInteger bigInteger2) {
            return g(bigInteger.multiply(bigInteger2));
        }

        protected BigInteger g(BigInteger bigInteger) {
            if (this.f14092b != null) {
                boolean z = bigInteger.signum() < 0;
                if (z) {
                    bigInteger = bigInteger.abs();
                }
                int bitLength = this.f14091a.bitLength();
                boolean equals = this.f14092b.equals(ECConstants.ONE);
                while (bigInteger.bitLength() > bitLength + 1) {
                    BigInteger shiftRight = bigInteger.shiftRight(bitLength);
                    BigInteger subtract = bigInteger.subtract(shiftRight.shiftLeft(bitLength));
                    if (!equals) {
                        shiftRight = shiftRight.multiply(this.f14092b);
                    }
                    bigInteger = shiftRight.add(subtract);
                }
                while (bigInteger.compareTo(this.f14091a) >= 0) {
                    bigInteger = bigInteger.subtract(this.f14091a);
                }
                return (!z || bigInteger.signum() == 0) ? bigInteger : this.f14091a.subtract(bigInteger);
            }
            return bigInteger.mod(this.f14091a);
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public String getFieldName() {
            return "Fp";
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public int getFieldSize() {
            return this.f14091a.bitLength();
        }

        public BigInteger getQ() {
            return this.f14091a;
        }

        protected BigInteger h(BigInteger bigInteger, BigInteger bigInteger2) {
            BigInteger subtract = bigInteger.subtract(bigInteger2);
            return subtract.signum() < 0 ? subtract.add(this.f14091a) : subtract;
        }

        public int hashCode() {
            return this.f14091a.hashCode() ^ this.f14093c.hashCode();
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement invert() {
            return new Fp(this.f14091a, this.f14092b, e(this.f14093c));
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement multiply(ECFieldElement eCFieldElement) {
            return new Fp(this.f14091a, this.f14092b, f(this.f14093c, eCFieldElement.toBigInteger()));
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            BigInteger bigInteger = this.f14093c;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            BigInteger bigInteger4 = eCFieldElement3.toBigInteger();
            return new Fp(this.f14091a, this.f14092b, g(bigInteger.multiply(bigInteger2).subtract(bigInteger3.multiply(bigInteger4))));
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            BigInteger bigInteger = this.f14093c;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            BigInteger bigInteger4 = eCFieldElement3.toBigInteger();
            return new Fp(this.f14091a, this.f14092b, g(bigInteger.multiply(bigInteger2).add(bigInteger3.multiply(bigInteger4))));
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement negate() {
            if (this.f14093c.signum() == 0) {
                return this;
            }
            BigInteger bigInteger = this.f14091a;
            return new Fp(bigInteger, this.f14092b, bigInteger.subtract(this.f14093c));
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement sqrt() {
            if (isZero() || isOne()) {
                return this;
            }
            if (!this.f14091a.testBit(0)) {
                throw new RuntimeException("not done yet");
            }
            if (this.f14091a.testBit(1)) {
                BigInteger add = this.f14091a.shiftRight(2).add(ECConstants.ONE);
                BigInteger bigInteger = this.f14091a;
                return checkSqrt(new Fp(bigInteger, this.f14092b, this.f14093c.modPow(add, bigInteger)));
            } else if (this.f14091a.testBit(2)) {
                BigInteger modPow = this.f14093c.modPow(this.f14091a.shiftRight(3), this.f14091a);
                BigInteger f2 = f(modPow, this.f14093c);
                if (f(f2, modPow).equals(ECConstants.ONE)) {
                    return checkSqrt(new Fp(this.f14091a, this.f14092b, f2));
                }
                return checkSqrt(new Fp(this.f14091a, this.f14092b, f(f2, ECConstants.TWO.modPow(this.f14091a.shiftRight(2), this.f14091a))));
            } else {
                BigInteger shiftRight = this.f14091a.shiftRight(1);
                BigInteger modPow2 = this.f14093c.modPow(shiftRight, this.f14091a);
                BigInteger bigInteger2 = ECConstants.ONE;
                if (!modPow2.equals(bigInteger2)) {
                    return null;
                }
                BigInteger bigInteger3 = this.f14093c;
                BigInteger c2 = c(c(bigInteger3));
                BigInteger add2 = shiftRight.add(bigInteger2);
                BigInteger subtract = this.f14091a.subtract(bigInteger2);
                Random random = new Random();
                while (true) {
                    BigInteger bigInteger4 = new BigInteger(this.f14091a.bitLength(), random);
                    if (bigInteger4.compareTo(this.f14091a) < 0 && g(bigInteger4.multiply(bigInteger4).subtract(c2)).modPow(shiftRight, this.f14091a).equals(subtract)) {
                        BigInteger[] lucasSequence = lucasSequence(bigInteger4, bigInteger3, add2);
                        BigInteger bigInteger5 = lucasSequence[0];
                        BigInteger bigInteger6 = lucasSequence[1];
                        if (f(bigInteger6, bigInteger6).equals(c2)) {
                            return new Fp(this.f14091a, this.f14092b, d(bigInteger6));
                        }
                        if (!bigInteger5.equals(ECConstants.ONE) && !bigInteger5.equals(subtract)) {
                            return null;
                        }
                    }
                }
            }
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement square() {
            BigInteger bigInteger = this.f14091a;
            BigInteger bigInteger2 = this.f14092b;
            BigInteger bigInteger3 = this.f14093c;
            return new Fp(bigInteger, bigInteger2, f(bigInteger3, bigInteger3));
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            BigInteger bigInteger = this.f14093c;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            return new Fp(this.f14091a, this.f14092b, g(bigInteger.multiply(bigInteger).subtract(bigInteger2.multiply(bigInteger3))));
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            BigInteger bigInteger = this.f14093c;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            return new Fp(this.f14091a, this.f14092b, g(bigInteger.multiply(bigInteger).add(bigInteger2.multiply(bigInteger3))));
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public ECFieldElement subtract(ECFieldElement eCFieldElement) {
            return new Fp(this.f14091a, this.f14092b, h(this.f14093c, eCFieldElement.toBigInteger()));
        }

        @Override // org.bouncycastle.math.ec.ECFieldElement
        public BigInteger toBigInteger() {
            return this.f14093c;
        }
    }

    public abstract ECFieldElement add(ECFieldElement eCFieldElement);

    public abstract ECFieldElement addOne();

    public int bitLength() {
        return toBigInteger().bitLength();
    }

    public abstract ECFieldElement divide(ECFieldElement eCFieldElement);

    public byte[] getEncoded() {
        return BigIntegers.asUnsignedByteArray((getFieldSize() + 7) / 8, toBigInteger());
    }

    public abstract String getFieldName();

    public abstract int getFieldSize();

    public abstract ECFieldElement invert();

    public boolean isOne() {
        return bitLength() == 1;
    }

    public boolean isZero() {
        return toBigInteger().signum() == 0;
    }

    public abstract ECFieldElement multiply(ECFieldElement eCFieldElement);

    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiply(eCFieldElement).subtract(eCFieldElement2.multiply(eCFieldElement3));
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiply(eCFieldElement).add(eCFieldElement2.multiply(eCFieldElement3));
    }

    public abstract ECFieldElement negate();

    public abstract ECFieldElement sqrt();

    public abstract ECFieldElement square();

    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return square().subtract(eCFieldElement.multiply(eCFieldElement2));
    }

    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return square().add(eCFieldElement.multiply(eCFieldElement2));
    }

    public ECFieldElement squarePow(int i2) {
        ECFieldElement eCFieldElement = this;
        for (int i3 = 0; i3 < i2; i3++) {
            eCFieldElement = eCFieldElement.square();
        }
        return eCFieldElement;
    }

    public abstract ECFieldElement subtract(ECFieldElement eCFieldElement);

    public boolean testBitZero() {
        return toBigInteger().testBit(0);
    }

    public abstract BigInteger toBigInteger();

    public String toString() {
        return toBigInteger().toString(16);
    }
}
