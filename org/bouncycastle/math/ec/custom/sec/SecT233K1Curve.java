package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.AbstractECLookupTable;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECLookupTable;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.WTauNafMultiplier;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes4.dex */
public class SecT233K1Curve extends ECCurve.AbstractF2m {
    private static final ECFieldElement[] SECT233K1_AFFINE_ZS = {new SecT233FieldElement(ECConstants.ONE)};
    private static final int SECT233K1_DEFAULT_COORDS = 6;

    /* renamed from: i  reason: collision with root package name */
    protected SecT233K1Point f14269i;

    public SecT233K1Curve() {
        super(233, 74, 0, 0);
        this.f14269i = new SecT233K1Point(this, null, null);
        this.f14065b = fromBigInteger(BigInteger.valueOf(0L));
        this.f14066c = fromBigInteger(BigInteger.valueOf(1L));
        this.f14067d = new BigInteger(1, Hex.decodeStrict("8000000000000000000000000000069D5BB915BCD46EFB1AD5F173ABDF"));
        this.f14068e = BigInteger.valueOf(4L);
        this.f14069f = 6;
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    protected ECCurve c() {
        return new SecT233K1Curve();
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int i2, final int i3) {
        final long[] jArr = new long[i3 * 4 * 2];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            ECPoint eCPoint = eCPointArr[i2 + i5];
            Nat256.copy64(((SecT233FieldElement) eCPoint.getRawXCoord()).f14268a, 0, jArr, i4);
            int i6 = i4 + 4;
            Nat256.copy64(((SecT233FieldElement) eCPoint.getRawYCoord()).f14268a, 0, jArr, i6);
            i4 = i6 + 4;
        }
        return new AbstractECLookupTable() { // from class: org.bouncycastle.math.ec.custom.sec.SecT233K1Curve.1
            private ECPoint createPoint(long[] jArr2, long[] jArr3) {
                return SecT233K1Curve.this.f(new SecT233FieldElement(jArr2), new SecT233FieldElement(jArr3), SecT233K1Curve.SECT233K1_AFFINE_ZS);
            }

            @Override // org.bouncycastle.math.ec.ECLookupTable
            public int getSize() {
                return i3;
            }

            @Override // org.bouncycastle.math.ec.ECLookupTable
            public ECPoint lookup(int i7) {
                long[] create64 = Nat256.create64();
                long[] create642 = Nat256.create64();
                int i8 = 0;
                for (int i9 = 0; i9 < i3; i9++) {
                    long j2 = ((i9 ^ i7) - 1) >> 31;
                    for (int i10 = 0; i10 < 4; i10++) {
                        long j3 = create64[i10];
                        long[] jArr2 = jArr;
                        create64[i10] = j3 ^ (jArr2[i8 + i10] & j2);
                        create642[i10] = create642[i10] ^ (jArr2[(i8 + 4) + i10] & j2);
                    }
                    i8 += 8;
                }
                return createPoint(create64, create642);
            }

            @Override // org.bouncycastle.math.ec.AbstractECLookupTable, org.bouncycastle.math.ec.ECLookupTable
            public ECPoint lookupVar(int i7) {
                long[] create64 = Nat256.create64();
                long[] create642 = Nat256.create64();
                int i8 = i7 * 4 * 2;
                for (int i9 = 0; i9 < 4; i9++) {
                    long[] jArr2 = jArr;
                    create64[i9] = jArr2[i8 + i9];
                    create642[i9] = jArr2[i8 + 4 + i9];
                }
                return createPoint(create64, create642);
            }
        };
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    protected ECMultiplier d() {
        return new WTauNafMultiplier();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.ec.ECCurve
    public ECPoint e(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return new SecT233K1Point(this, eCFieldElement, eCFieldElement2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.ec.ECCurve
    public ECPoint f(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        return new SecT233K1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecT233FieldElement(bigInteger);
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public int getFieldSize() {
        return 233;
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public ECPoint getInfinity() {
        return this.f14269i;
    }

    public int getK1() {
        return 74;
    }

    public int getK2() {
        return 0;
    }

    public int getK3() {
        return 0;
    }

    public int getM() {
        return 233;
    }

    @Override // org.bouncycastle.math.ec.ECCurve.AbstractF2m
    public boolean isKoblitz() {
        return true;
    }

    public boolean isTrinomial() {
        return true;
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public boolean supportsCoordinateSystem(int i2) {
        return i2 == 6;
    }
}
