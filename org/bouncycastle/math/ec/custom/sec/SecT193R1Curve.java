package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.AbstractECLookupTable;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECLookupTable;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.tls.CipherSuite;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes4.dex */
public class SecT193R1Curve extends ECCurve.AbstractF2m {
    private static final ECFieldElement[] SECT193R1_AFFINE_ZS = {new SecT193FieldElement(ECConstants.ONE)};
    private static final int SECT193R1_DEFAULT_COORDS = 6;

    /* renamed from: i  reason: collision with root package name */
    protected SecT193R1Point f14260i;

    public SecT193R1Curve() {
        super(CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, 15, 0, 0);
        this.f14260i = new SecT193R1Point(this, null, null);
        this.f14065b = fromBigInteger(new BigInteger(1, Hex.decodeStrict("0017858FEB7A98975169E171F77B4087DE098AC8A911DF7B01")));
        this.f14066c = fromBigInteger(new BigInteger(1, Hex.decodeStrict("00FDFB49BFE6C3A89FACADAA7A1E5BBC7CC1C2E5D831478814")));
        this.f14067d = new BigInteger(1, Hex.decodeStrict("01000000000000000000000000C7F34A778F443ACC920EBA49"));
        this.f14068e = BigInteger.valueOf(2L);
        this.f14069f = 6;
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    protected ECCurve c() {
        return new SecT193R1Curve();
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int i2, final int i3) {
        final long[] jArr = new long[i3 * 4 * 2];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            ECPoint eCPoint = eCPointArr[i2 + i5];
            Nat256.copy64(((SecT193FieldElement) eCPoint.getRawXCoord()).f14259a, 0, jArr, i4);
            int i6 = i4 + 4;
            Nat256.copy64(((SecT193FieldElement) eCPoint.getRawYCoord()).f14259a, 0, jArr, i6);
            i4 = i6 + 4;
        }
        return new AbstractECLookupTable() { // from class: org.bouncycastle.math.ec.custom.sec.SecT193R1Curve.1
            private ECPoint createPoint(long[] jArr2, long[] jArr3) {
                return SecT193R1Curve.this.f(new SecT193FieldElement(jArr2), new SecT193FieldElement(jArr3), SecT193R1Curve.SECT193R1_AFFINE_ZS);
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.ec.ECCurve
    public ECPoint e(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return new SecT193R1Point(this, eCFieldElement, eCFieldElement2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.ec.ECCurve
    public ECPoint f(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        return new SecT193R1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecT193FieldElement(bigInteger);
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public int getFieldSize() {
        return CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256;
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public ECPoint getInfinity() {
        return this.f14260i;
    }

    public int getK1() {
        return 15;
    }

    public int getK2() {
        return 0;
    }

    public int getK3() {
        return 0;
    }

    public int getM() {
        return CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256;
    }

    @Override // org.bouncycastle.math.ec.ECCurve.AbstractF2m
    public boolean isKoblitz() {
        return false;
    }

    public boolean isTrinomial() {
        return true;
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public boolean supportsCoordinateSystem(int i2) {
        return i2 == 6;
    }
}
