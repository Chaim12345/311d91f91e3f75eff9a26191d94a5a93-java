package org.bouncycastle.math.ec;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Random;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.endo.ECEndomorphism;
import org.bouncycastle.math.ec.endo.GLVEndomorphism;
import org.bouncycastle.math.field.FiniteField;
import org.bouncycastle.math.field.FiniteFields;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Integers;
/* loaded from: classes3.dex */
public abstract class ECCurve {
    public static final int COORD_AFFINE = 0;
    public static final int COORD_HOMOGENEOUS = 1;
    public static final int COORD_JACOBIAN = 2;
    public static final int COORD_JACOBIAN_CHUDNOVSKY = 3;
    public static final int COORD_JACOBIAN_MODIFIED = 4;
    public static final int COORD_LAMBDA_AFFINE = 5;
    public static final int COORD_LAMBDA_PROJECTIVE = 6;
    public static final int COORD_SKEWED = 7;

    /* renamed from: a  reason: collision with root package name */
    protected FiniteField f14064a;

    /* renamed from: b  reason: collision with root package name */
    protected ECFieldElement f14065b;

    /* renamed from: c  reason: collision with root package name */
    protected ECFieldElement f14066c;

    /* renamed from: d  reason: collision with root package name */
    protected BigInteger f14067d;

    /* renamed from: e  reason: collision with root package name */
    protected BigInteger f14068e;

    /* renamed from: f  reason: collision with root package name */
    protected int f14069f = 0;

    /* renamed from: g  reason: collision with root package name */
    protected ECEndomorphism f14070g = null;

    /* renamed from: h  reason: collision with root package name */
    protected ECMultiplier f14071h = null;

    /* loaded from: classes3.dex */
    public static abstract class AbstractF2m extends ECCurve {
        private BigInteger[] si;

        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractF2m(int i2, int i3, int i4, int i5) {
            super(buildField(i2, i3, i4, i5));
            this.si = null;
        }

        private static FiniteField buildField(int i2, int i3, int i4, int i5) {
            if (i3 != 0) {
                if (i4 == 0) {
                    if (i5 == 0) {
                        return FiniteFields.getBinaryExtensionField(new int[]{0, i3, i2});
                    }
                    throw new IllegalArgumentException("k3 must be 0 if k2 == 0");
                } else if (i4 > i3) {
                    if (i5 > i4) {
                        return FiniteFields.getBinaryExtensionField(new int[]{0, i3, i4, i5, i2});
                    }
                    throw new IllegalArgumentException("k3 must be > k2");
                } else {
                    throw new IllegalArgumentException("k2 must be > k1");
                }
            }
            throw new IllegalArgumentException("k1 must be > 0");
        }

        private static BigInteger implRandomFieldElementMult(SecureRandom secureRandom, int i2) {
            BigInteger createRandomBigInteger;
            do {
                createRandomBigInteger = BigIntegers.createRandomBigInteger(i2, secureRandom);
            } while (createRandomBigInteger.signum() <= 0);
            return createRandomBigInteger;
        }

        public static BigInteger inverse(int i2, int[] iArr, BigInteger bigInteger) {
            return new LongArray(bigInteger).modInverse(i2, iArr).toBigInteger();
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public ECPoint createPoint(BigInteger bigInteger, BigInteger bigInteger2) {
            ECFieldElement fromBigInteger = fromBigInteger(bigInteger);
            ECFieldElement fromBigInteger2 = fromBigInteger(bigInteger2);
            int coordinateSystem = getCoordinateSystem();
            if (coordinateSystem == 5 || coordinateSystem == 6) {
                if (!fromBigInteger.isZero()) {
                    fromBigInteger2 = fromBigInteger2.divide(fromBigInteger).add(fromBigInteger);
                } else if (!fromBigInteger2.square().equals(getB())) {
                    throw new IllegalArgumentException();
                }
            }
            return e(fromBigInteger, fromBigInteger2);
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        protected ECPoint g(int i2, BigInteger bigInteger) {
            ECFieldElement eCFieldElement;
            ECFieldElement fromBigInteger = fromBigInteger(bigInteger);
            if (fromBigInteger.isZero()) {
                eCFieldElement = getB().sqrt();
            } else {
                ECFieldElement i3 = i(fromBigInteger.square().invert().multiply(getB()).add(getA()).add(fromBigInteger));
                if (i3 != null) {
                    if (i3.testBitZero() != (i2 == 1)) {
                        i3 = i3.addOne();
                    }
                    int coordinateSystem = getCoordinateSystem();
                    eCFieldElement = (coordinateSystem == 5 || coordinateSystem == 6) ? i3.add(fromBigInteger) : i3.multiply(fromBigInteger);
                } else {
                    eCFieldElement = null;
                }
            }
            if (eCFieldElement != null) {
                return e(fromBigInteger, eCFieldElement);
            }
            throw new IllegalArgumentException("Invalid point compression");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public synchronized BigInteger[] h() {
            if (this.si == null) {
                this.si = Tnaf.getSi(this);
            }
            return this.si;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public ECFieldElement i(ECFieldElement eCFieldElement) {
            ECFieldElement eCFieldElement2;
            ECFieldElement.AbstractF2m abstractF2m = (ECFieldElement.AbstractF2m) eCFieldElement;
            boolean hasFastTrace = abstractF2m.hasFastTrace();
            if (!hasFastTrace || abstractF2m.trace() == 0) {
                int fieldSize = getFieldSize();
                if ((fieldSize & 1) != 0) {
                    ECFieldElement halfTrace = abstractF2m.halfTrace();
                    if (hasFastTrace || halfTrace.square().add(halfTrace).add(eCFieldElement).isZero()) {
                        return halfTrace;
                    }
                    return null;
                } else if (eCFieldElement.isZero()) {
                    return eCFieldElement;
                } else {
                    ECFieldElement fromBigInteger = fromBigInteger(ECConstants.ZERO);
                    Random random = new Random();
                    do {
                        ECFieldElement fromBigInteger2 = fromBigInteger(new BigInteger(fieldSize, random));
                        ECFieldElement eCFieldElement3 = eCFieldElement;
                        eCFieldElement2 = fromBigInteger;
                        for (int i2 = 1; i2 < fieldSize; i2++) {
                            ECFieldElement square = eCFieldElement3.square();
                            eCFieldElement2 = eCFieldElement2.square().add(square.multiply(fromBigInteger2));
                            eCFieldElement3 = square.add(eCFieldElement);
                        }
                        if (!eCFieldElement3.isZero()) {
                            return null;
                        }
                    } while (eCFieldElement2.square().add(eCFieldElement2).isZero());
                    return eCFieldElement2;
                }
            }
            return null;
        }

        public boolean isKoblitz() {
            return this.f14067d != null && this.f14068e != null && this.f14066c.isOne() && (this.f14065b.isZero() || this.f14065b.isOne());
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public boolean isValidFieldElement(BigInteger bigInteger) {
            return bigInteger != null && bigInteger.signum() >= 0 && bigInteger.bitLength() <= getFieldSize();
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public ECFieldElement randomFieldElement(SecureRandom secureRandom) {
            return fromBigInteger(BigIntegers.createRandomBigInteger(getFieldSize(), secureRandom));
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public ECFieldElement randomFieldElementMult(SecureRandom secureRandom) {
            int fieldSize = getFieldSize();
            return fromBigInteger(implRandomFieldElementMult(secureRandom, fieldSize)).multiply(fromBigInteger(implRandomFieldElementMult(secureRandom, fieldSize)));
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class AbstractFp extends ECCurve {
        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractFp(BigInteger bigInteger) {
            super(FiniteFields.getPrimeField(bigInteger));
        }

        private static BigInteger implRandomFieldElement(SecureRandom secureRandom, BigInteger bigInteger) {
            BigInteger createRandomBigInteger;
            do {
                createRandomBigInteger = BigIntegers.createRandomBigInteger(bigInteger.bitLength(), secureRandom);
            } while (createRandomBigInteger.compareTo(bigInteger) >= 0);
            return createRandomBigInteger;
        }

        private static BigInteger implRandomFieldElementMult(SecureRandom secureRandom, BigInteger bigInteger) {
            while (true) {
                BigInteger createRandomBigInteger = BigIntegers.createRandomBigInteger(bigInteger.bitLength(), secureRandom);
                if (createRandomBigInteger.signum() > 0 && createRandomBigInteger.compareTo(bigInteger) < 0) {
                    return createRandomBigInteger;
                }
            }
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        protected ECPoint g(int i2, BigInteger bigInteger) {
            ECFieldElement fromBigInteger = fromBigInteger(bigInteger);
            ECFieldElement sqrt = fromBigInteger.square().add(this.f14065b).multiply(fromBigInteger).add(this.f14066c).sqrt();
            if (sqrt != null) {
                if (sqrt.testBitZero() != (i2 == 1)) {
                    sqrt = sqrt.negate();
                }
                return e(fromBigInteger, sqrt);
            }
            throw new IllegalArgumentException("Invalid point compression");
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public boolean isValidFieldElement(BigInteger bigInteger) {
            return bigInteger != null && bigInteger.signum() >= 0 && bigInteger.compareTo(getField().getCharacteristic()) < 0;
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public ECFieldElement randomFieldElement(SecureRandom secureRandom) {
            BigInteger characteristic = getField().getCharacteristic();
            return fromBigInteger(implRandomFieldElement(secureRandom, characteristic)).multiply(fromBigInteger(implRandomFieldElement(secureRandom, characteristic)));
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public ECFieldElement randomFieldElementMult(SecureRandom secureRandom) {
            BigInteger characteristic = getField().getCharacteristic();
            return fromBigInteger(implRandomFieldElementMult(secureRandom, characteristic)).multiply(fromBigInteger(implRandomFieldElementMult(secureRandom, characteristic)));
        }
    }

    /* loaded from: classes3.dex */
    public class Config {

        /* renamed from: a  reason: collision with root package name */
        protected int f14076a;

        /* renamed from: b  reason: collision with root package name */
        protected ECEndomorphism f14077b;

        /* renamed from: c  reason: collision with root package name */
        protected ECMultiplier f14078c;

        Config(int i2, ECEndomorphism eCEndomorphism, ECMultiplier eCMultiplier) {
            this.f14076a = i2;
            this.f14077b = eCEndomorphism;
            this.f14078c = eCMultiplier;
        }

        public ECCurve create() {
            if (ECCurve.this.supportsCoordinateSystem(this.f14076a)) {
                ECCurve c2 = ECCurve.this.c();
                if (c2 != ECCurve.this) {
                    synchronized (c2) {
                        c2.f14069f = this.f14076a;
                        c2.f14070g = this.f14077b;
                        c2.f14071h = this.f14078c;
                    }
                    return c2;
                }
                throw new IllegalStateException("implementation returned current curve");
            }
            throw new IllegalStateException("unsupported coordinate system");
        }

        public Config setCoordinateSystem(int i2) {
            this.f14076a = i2;
            return this;
        }

        public Config setEndomorphism(ECEndomorphism eCEndomorphism) {
            this.f14077b = eCEndomorphism;
            return this;
        }

        public Config setMultiplier(ECMultiplier eCMultiplier) {
            this.f14078c = eCMultiplier;
            return this;
        }
    }

    /* loaded from: classes3.dex */
    public static class F2m extends AbstractF2m {
        private static final int F2M_DEFAULT_COORDS = 6;
        private ECPoint.F2m infinity;
        private int k1;
        private int k2;
        private int k3;

        /* renamed from: m  reason: collision with root package name */
        private int f14080m;

        public F2m(int i2, int i3, int i4, int i5, BigInteger bigInteger, BigInteger bigInteger2) {
            this(i2, i3, i4, i5, bigInteger, bigInteger2, (BigInteger) null, (BigInteger) null);
        }

        public F2m(int i2, int i3, int i4, int i5, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
            super(i2, i3, i4, i5);
            this.f14080m = i2;
            this.k1 = i3;
            this.k2 = i4;
            this.k3 = i5;
            this.f14067d = bigInteger3;
            this.f14068e = bigInteger4;
            this.infinity = new ECPoint.F2m(this, null, null);
            this.f14065b = fromBigInteger(bigInteger);
            this.f14066c = fromBigInteger(bigInteger2);
            this.f14069f = 6;
        }

        protected F2m(int i2, int i3, int i4, int i5, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, BigInteger bigInteger, BigInteger bigInteger2) {
            super(i2, i3, i4, i5);
            this.f14080m = i2;
            this.k1 = i3;
            this.k2 = i4;
            this.k3 = i5;
            this.f14067d = bigInteger;
            this.f14068e = bigInteger2;
            this.infinity = new ECPoint.F2m(this, null, null);
            this.f14065b = eCFieldElement;
            this.f14066c = eCFieldElement2;
            this.f14069f = 6;
        }

        public F2m(int i2, int i3, BigInteger bigInteger, BigInteger bigInteger2) {
            this(i2, i3, 0, 0, bigInteger, bigInteger2, (BigInteger) null, (BigInteger) null);
        }

        public F2m(int i2, int i3, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
            this(i2, i3, 0, 0, bigInteger, bigInteger2, bigInteger3, bigInteger4);
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        protected ECCurve c() {
            return new F2m(this.f14080m, this.k1, this.k2, this.k3, this.f14065b, this.f14066c, this.f14067d, this.f14068e);
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int i2, final int i3) {
            final int i4 = (this.f14080m + 63) >>> 6;
            final int[] iArr = isTrinomial() ? new int[]{this.k1} : new int[]{this.k1, this.k2, this.k3};
            final long[] jArr = new long[i3 * i4 * 2];
            int i5 = 0;
            for (int i6 = 0; i6 < i3; i6++) {
                ECPoint eCPoint = eCPointArr[i2 + i6];
                ((ECFieldElement.F2m) eCPoint.getRawXCoord()).f14089a.a(jArr, i5);
                int i7 = i5 + i4;
                ((ECFieldElement.F2m) eCPoint.getRawYCoord()).f14089a.a(jArr, i7);
                i5 = i7 + i4;
            }
            return new AbstractECLookupTable() { // from class: org.bouncycastle.math.ec.ECCurve.F2m.1
                private ECPoint createPoint(long[] jArr2, long[] jArr3) {
                    return F2m.this.e(new ECFieldElement.F2m(F2m.this.f14080m, iArr, new LongArray(jArr2)), new ECFieldElement.F2m(F2m.this.f14080m, iArr, new LongArray(jArr3)));
                }

                @Override // org.bouncycastle.math.ec.ECLookupTable
                public int getSize() {
                    return i3;
                }

                @Override // org.bouncycastle.math.ec.ECLookupTable
                public ECPoint lookup(int i8) {
                    int i9;
                    long[] create64 = Nat.create64(i4);
                    long[] create642 = Nat.create64(i4);
                    int i10 = 0;
                    for (int i11 = 0; i11 < i3; i11++) {
                        long j2 = ((i11 ^ i8) - 1) >> 31;
                        int i12 = 0;
                        while (true) {
                            i9 = i4;
                            if (i12 < i9) {
                                long j3 = create64[i12];
                                long[] jArr2 = jArr;
                                create64[i12] = j3 ^ (jArr2[i10 + i12] & j2);
                                create642[i12] = create642[i12] ^ (jArr2[(i9 + i10) + i12] & j2);
                                i12++;
                            }
                        }
                        i10 += i9 * 2;
                    }
                    return createPoint(create64, create642);
                }

                @Override // org.bouncycastle.math.ec.AbstractECLookupTable, org.bouncycastle.math.ec.ECLookupTable
                public ECPoint lookupVar(int i8) {
                    long[] create64 = Nat.create64(i4);
                    long[] create642 = Nat.create64(i4);
                    int i9 = i8 * i4 * 2;
                    int i10 = 0;
                    while (true) {
                        int i11 = i4;
                        if (i10 >= i11) {
                            return createPoint(create64, create642);
                        }
                        long[] jArr2 = jArr;
                        create64[i10] = jArr2[i9 + i10];
                        create642[i10] = jArr2[i11 + i9 + i10];
                        i10++;
                    }
                }
            };
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        protected ECMultiplier d() {
            return isKoblitz() ? new WTauNafMultiplier() : super.d();
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        protected ECPoint e(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            return new ECPoint.F2m(this, eCFieldElement, eCFieldElement2);
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        protected ECPoint f(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            return new ECPoint.F2m(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public ECFieldElement fromBigInteger(BigInteger bigInteger) {
            return new ECFieldElement.F2m(this.f14080m, this.k1, this.k2, this.k3, bigInteger);
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public int getFieldSize() {
            return this.f14080m;
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public ECPoint getInfinity() {
            return this.infinity;
        }

        public int getK1() {
            return this.k1;
        }

        public int getK2() {
            return this.k2;
        }

        public int getK3() {
            return this.k3;
        }

        public int getM() {
            return this.f14080m;
        }

        public boolean isTrinomial() {
            return this.k2 == 0 && this.k3 == 0;
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public boolean supportsCoordinateSystem(int i2) {
            return i2 == 0 || i2 == 1 || i2 == 6;
        }
    }

    /* loaded from: classes3.dex */
    public static class Fp extends AbstractFp {
        private static final int FP_DEFAULT_COORDS = 4;

        /* renamed from: i  reason: collision with root package name */
        BigInteger f14086i;

        /* renamed from: j  reason: collision with root package name */
        BigInteger f14087j;

        /* renamed from: k  reason: collision with root package name */
        ECPoint.Fp f14088k;

        public Fp(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            this(bigInteger, bigInteger2, bigInteger3, null, null);
        }

        public Fp(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5) {
            super(bigInteger);
            this.f14086i = bigInteger;
            this.f14087j = ECFieldElement.Fp.a(bigInteger);
            this.f14088k = new ECPoint.Fp(this, null, null);
            this.f14065b = fromBigInteger(bigInteger2);
            this.f14066c = fromBigInteger(bigInteger3);
            this.f14067d = bigInteger4;
            this.f14068e = bigInteger5;
            this.f14069f = 4;
        }

        protected Fp(BigInteger bigInteger, BigInteger bigInteger2, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, BigInteger bigInteger3, BigInteger bigInteger4) {
            super(bigInteger);
            this.f14086i = bigInteger;
            this.f14087j = bigInteger2;
            this.f14088k = new ECPoint.Fp(this, null, null);
            this.f14065b = eCFieldElement;
            this.f14066c = eCFieldElement2;
            this.f14067d = bigInteger3;
            this.f14068e = bigInteger4;
            this.f14069f = 4;
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        protected ECCurve c() {
            return new Fp(this.f14086i, this.f14087j, this.f14065b, this.f14066c, this.f14067d, this.f14068e);
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        protected ECPoint e(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            return new ECPoint.Fp(this, eCFieldElement, eCFieldElement2);
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        protected ECPoint f(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            return new ECPoint.Fp(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public ECFieldElement fromBigInteger(BigInteger bigInteger) {
            return new ECFieldElement.Fp(this.f14086i, this.f14087j, bigInteger);
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public int getFieldSize() {
            return this.f14086i.bitLength();
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public ECPoint getInfinity() {
            return this.f14088k;
        }

        public BigInteger getQ() {
            return this.f14086i;
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public ECPoint importPoint(ECPoint eCPoint) {
            int coordinateSystem;
            return (this == eCPoint.getCurve() || getCoordinateSystem() != 2 || eCPoint.isInfinity() || !((coordinateSystem = eCPoint.getCurve().getCoordinateSystem()) == 2 || coordinateSystem == 3 || coordinateSystem == 4)) ? super.importPoint(eCPoint) : new ECPoint.Fp(this, fromBigInteger(eCPoint.f14096b.toBigInteger()), fromBigInteger(eCPoint.f14097c.toBigInteger()), new ECFieldElement[]{fromBigInteger(eCPoint.f14098d[0].toBigInteger())});
        }

        @Override // org.bouncycastle.math.ec.ECCurve
        public boolean supportsCoordinateSystem(int i2) {
            return i2 == 0 || i2 == 1 || i2 == 2 || i2 == 4;
        }
    }

    protected ECCurve(FiniteField finiteField) {
        this.f14064a = finiteField;
    }

    public static int[] getAllCoordinateSystems() {
        return new int[]{0, 1, 2, 3, 4, 5, 6, 7};
    }

    protected void a(ECPoint eCPoint) {
        if (eCPoint == null || this != eCPoint.getCurve()) {
            throw new IllegalArgumentException("'point' must be non-null and on this curve");
        }
    }

    protected void b(ECPoint[] eCPointArr, int i2, int i3) {
        if (eCPointArr == null) {
            throw new IllegalArgumentException("'points' cannot be null");
        }
        if (i2 < 0 || i3 < 0 || i2 > eCPointArr.length - i3) {
            throw new IllegalArgumentException("invalid range specified for 'points'");
        }
        for (int i4 = 0; i4 < i3; i4++) {
            ECPoint eCPoint = eCPointArr[i2 + i4];
            if (eCPoint != null && this != eCPoint.getCurve()) {
                throw new IllegalArgumentException("'points' entries must be null or on this curve");
            }
        }
    }

    protected abstract ECCurve c();

    public synchronized Config configure() {
        return new Config(this.f14069f, this.f14070g, this.f14071h);
    }

    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int i2, final int i3) {
        final int fieldSize = (getFieldSize() + 7) >>> 3;
        final byte[] bArr = new byte[i3 * fieldSize * 2];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            ECPoint eCPoint = eCPointArr[i2 + i5];
            byte[] byteArray = eCPoint.getRawXCoord().toBigInteger().toByteArray();
            byte[] byteArray2 = eCPoint.getRawYCoord().toBigInteger().toByteArray();
            int i6 = 1;
            int i7 = byteArray.length > fieldSize ? 1 : 0;
            int length = byteArray.length - i7;
            if (byteArray2.length <= fieldSize) {
                i6 = 0;
            }
            int length2 = byteArray2.length - i6;
            int i8 = i4 + fieldSize;
            System.arraycopy(byteArray, i7, bArr, i8 - length, length);
            i4 = i8 + fieldSize;
            System.arraycopy(byteArray2, i6, bArr, i4 - length2, length2);
        }
        return new AbstractECLookupTable() { // from class: org.bouncycastle.math.ec.ECCurve.1
            private ECPoint createPoint(byte[] bArr2, byte[] bArr3) {
                ECCurve eCCurve = ECCurve.this;
                return eCCurve.e(eCCurve.fromBigInteger(new BigInteger(1, bArr2)), ECCurve.this.fromBigInteger(new BigInteger(1, bArr3)));
            }

            @Override // org.bouncycastle.math.ec.ECLookupTable
            public int getSize() {
                return i3;
            }

            @Override // org.bouncycastle.math.ec.ECLookupTable
            public ECPoint lookup(int i9) {
                int i10;
                int i11 = fieldSize;
                byte[] bArr2 = new byte[i11];
                byte[] bArr3 = new byte[i11];
                int i12 = 0;
                for (int i13 = 0; i13 < i3; i13++) {
                    int i14 = ((i13 ^ i9) - 1) >> 31;
                    int i15 = 0;
                    while (true) {
                        i10 = fieldSize;
                        if (i15 < i10) {
                            byte b2 = bArr2[i15];
                            byte[] bArr4 = bArr;
                            bArr2[i15] = (byte) (b2 ^ (bArr4[i12 + i15] & i14));
                            bArr3[i15] = (byte) ((bArr4[(i10 + i12) + i15] & i14) ^ bArr3[i15]);
                            i15++;
                        }
                    }
                    i12 += i10 * 2;
                }
                return createPoint(bArr2, bArr3);
            }

            @Override // org.bouncycastle.math.ec.AbstractECLookupTable, org.bouncycastle.math.ec.ECLookupTable
            public ECPoint lookupVar(int i9) {
                int i10 = fieldSize;
                byte[] bArr2 = new byte[i10];
                byte[] bArr3 = new byte[i10];
                int i11 = i9 * i10 * 2;
                int i12 = 0;
                while (true) {
                    int i13 = fieldSize;
                    if (i12 >= i13) {
                        return createPoint(bArr2, bArr3);
                    }
                    byte[] bArr4 = bArr;
                    bArr2[i12] = bArr4[i11 + i12];
                    bArr3[i12] = bArr4[i13 + i11 + i12];
                    i12++;
                }
            }
        };
    }

    public ECPoint createPoint(BigInteger bigInteger, BigInteger bigInteger2) {
        return e(fromBigInteger(bigInteger), fromBigInteger(bigInteger2));
    }

    protected ECMultiplier d() {
        ECEndomorphism eCEndomorphism = this.f14070g;
        return eCEndomorphism instanceof GLVEndomorphism ? new GLVMultiplier(this, (GLVEndomorphism) eCEndomorphism) : new WNafL2RMultiplier();
    }

    public ECPoint decodePoint(byte[] bArr) {
        ECPoint infinity;
        int fieldSize = (getFieldSize() + 7) / 8;
        byte b2 = bArr[0];
        if (b2 != 0) {
            if (b2 == 2 || b2 == 3) {
                if (bArr.length != fieldSize + 1) {
                    throw new IllegalArgumentException("Incorrect length for compressed encoding");
                }
                infinity = g(b2 & 1, BigIntegers.fromUnsignedByteArray(bArr, 1, fieldSize));
                if (!infinity.h(true, true)) {
                    throw new IllegalArgumentException("Invalid point");
                }
            } else if (b2 != 4) {
                if (b2 != 6 && b2 != 7) {
                    throw new IllegalArgumentException("Invalid point encoding 0x" + Integer.toString(b2, 16));
                } else if (bArr.length != (fieldSize * 2) + 1) {
                    throw new IllegalArgumentException("Incorrect length for hybrid encoding");
                } else {
                    BigInteger fromUnsignedByteArray = BigIntegers.fromUnsignedByteArray(bArr, 1, fieldSize);
                    BigInteger fromUnsignedByteArray2 = BigIntegers.fromUnsignedByteArray(bArr, fieldSize + 1, fieldSize);
                    if (fromUnsignedByteArray2.testBit(0) != (b2 == 7)) {
                        throw new IllegalArgumentException("Inconsistent Y coordinate in hybrid encoding");
                    }
                    infinity = validatePoint(fromUnsignedByteArray, fromUnsignedByteArray2);
                }
            } else if (bArr.length != (fieldSize * 2) + 1) {
                throw new IllegalArgumentException("Incorrect length for uncompressed encoding");
            } else {
                infinity = validatePoint(BigIntegers.fromUnsignedByteArray(bArr, 1, fieldSize), BigIntegers.fromUnsignedByteArray(bArr, fieldSize + 1, fieldSize));
            }
        } else if (bArr.length != 1) {
            throw new IllegalArgumentException("Incorrect length for infinity encoding");
        } else {
            infinity = getInfinity();
        }
        if (b2 == 0 || !infinity.isInfinity()) {
            return infinity;
        }
        throw new IllegalArgumentException("Invalid infinity encoding");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract ECPoint e(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2);

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof ECCurve) && equals((ECCurve) obj));
    }

    public boolean equals(ECCurve eCCurve) {
        return this == eCCurve || (eCCurve != null && getField().equals(eCCurve.getField()) && getA().toBigInteger().equals(eCCurve.getA().toBigInteger()) && getB().toBigInteger().equals(eCCurve.getB().toBigInteger()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract ECPoint f(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr);

    public abstract ECFieldElement fromBigInteger(BigInteger bigInteger);

    protected abstract ECPoint g(int i2, BigInteger bigInteger);

    public ECFieldElement getA() {
        return this.f14065b;
    }

    public ECFieldElement getB() {
        return this.f14066c;
    }

    public BigInteger getCofactor() {
        return this.f14068e;
    }

    public int getCoordinateSystem() {
        return this.f14069f;
    }

    public ECEndomorphism getEndomorphism() {
        return this.f14070g;
    }

    public FiniteField getField() {
        return this.f14064a;
    }

    public abstract int getFieldSize();

    public abstract ECPoint getInfinity();

    public ECMultiplier getMultiplier() {
        if (this.f14071h == null) {
            this.f14071h = d();
        }
        return this.f14071h;
    }

    public BigInteger getOrder() {
        return this.f14067d;
    }

    public PreCompInfo getPreCompInfo(ECPoint eCPoint, String str) {
        Hashtable hashtable;
        PreCompInfo preCompInfo;
        a(eCPoint);
        synchronized (eCPoint) {
            hashtable = eCPoint.f14099e;
        }
        if (hashtable == null) {
            return null;
        }
        synchronized (hashtable) {
            preCompInfo = (PreCompInfo) hashtable.get(str);
        }
        return preCompInfo;
    }

    public int hashCode() {
        return (getField().hashCode() ^ Integers.rotateLeft(getA().toBigInteger().hashCode(), 8)) ^ Integers.rotateLeft(getB().toBigInteger().hashCode(), 16);
    }

    public ECPoint importPoint(ECPoint eCPoint) {
        if (this == eCPoint.getCurve()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return getInfinity();
        }
        ECPoint normalize = eCPoint.normalize();
        return createPoint(normalize.getXCoord().toBigInteger(), normalize.getYCoord().toBigInteger());
    }

    public abstract boolean isValidFieldElement(BigInteger bigInteger);

    public void normalizeAll(ECPoint[] eCPointArr) {
        normalizeAll(eCPointArr, 0, eCPointArr.length, null);
    }

    public void normalizeAll(ECPoint[] eCPointArr, int i2, int i3, ECFieldElement eCFieldElement) {
        b(eCPointArr, i2, i3);
        int coordinateSystem = getCoordinateSystem();
        if (coordinateSystem == 0 || coordinateSystem == 5) {
            if (eCFieldElement != null) {
                throw new IllegalArgumentException("'iso' not valid for affine coordinates");
            }
            return;
        }
        ECFieldElement[] eCFieldElementArr = new ECFieldElement[i3];
        int[] iArr = new int[i3];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = i2 + i5;
            ECPoint eCPoint = eCPointArr[i6];
            if (eCPoint != null && (eCFieldElement != null || !eCPoint.isNormalized())) {
                eCFieldElementArr[i4] = eCPoint.getZCoord(0);
                iArr[i4] = i6;
                i4++;
            }
        }
        if (i4 == 0) {
            return;
        }
        ECAlgorithms.montgomeryTrick(eCFieldElementArr, 0, i4, eCFieldElement);
        for (int i7 = 0; i7 < i4; i7++) {
            int i8 = iArr[i7];
            eCPointArr[i8] = eCPointArr[i8].j(eCFieldElementArr[i7]);
        }
    }

    public PreCompInfo precompute(ECPoint eCPoint, String str, PreCompCallback preCompCallback) {
        Hashtable hashtable;
        PreCompInfo precompute;
        a(eCPoint);
        synchronized (eCPoint) {
            hashtable = eCPoint.f14099e;
            if (hashtable == null) {
                hashtable = new Hashtable(4);
                eCPoint.f14099e = hashtable;
            }
        }
        synchronized (hashtable) {
            PreCompInfo preCompInfo = (PreCompInfo) hashtable.get(str);
            precompute = preCompCallback.precompute(preCompInfo);
            if (precompute != preCompInfo) {
                hashtable.put(str, precompute);
            }
        }
        return precompute;
    }

    public abstract ECFieldElement randomFieldElement(SecureRandom secureRandom);

    public abstract ECFieldElement randomFieldElementMult(SecureRandom secureRandom);

    public boolean supportsCoordinateSystem(int i2) {
        return i2 == 0;
    }

    public ECPoint validatePoint(BigInteger bigInteger, BigInteger bigInteger2) {
        ECPoint createPoint = createPoint(bigInteger, bigInteger2);
        if (createPoint.isValid()) {
            return createPoint;
        }
        throw new IllegalArgumentException("Invalid point coordinates");
    }
}
