package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.ec.AbstractECLookupTable;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECLookupTable;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes4.dex */
public class SecP256R1Curve extends ECCurve.AbstractFp {
    private static final int SECP256R1_DEFAULT_COORDS = 2;

    /* renamed from: i  reason: collision with root package name */
    protected SecP256R1Point f14208i;

    /* renamed from: q  reason: collision with root package name */
    public static final BigInteger f14207q = SecP256R1FieldElement.Q;
    private static final ECFieldElement[] SECP256R1_AFFINE_ZS = {new SecP256R1FieldElement(ECConstants.ONE)};

    public SecP256R1Curve() {
        super(f14207q);
        this.f14208i = new SecP256R1Point(this, null, null);
        this.f14065b = fromBigInteger(new BigInteger(1, Hex.decodeStrict("FFFFFFFF00000001000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFC")));
        this.f14066c = fromBigInteger(new BigInteger(1, Hex.decodeStrict("5AC635D8AA3A93E7B3EBBD55769886BC651D06B0CC53B0F63BCE3C3E27D2604B")));
        this.f14067d = new BigInteger(1, Hex.decodeStrict("FFFFFFFF00000000FFFFFFFFFFFFFFFFBCE6FAADA7179E84F3B9CAC2FC632551"));
        this.f14068e = BigInteger.valueOf(1L);
        this.f14069f = 2;
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    protected ECCurve c() {
        return new SecP256R1Curve();
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int i2, final int i3) {
        final int[] iArr = new int[i3 * 8 * 2];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            ECPoint eCPoint = eCPointArr[i2 + i5];
            Nat256.copy(((SecP256R1FieldElement) eCPoint.getRawXCoord()).f14213a, 0, iArr, i4);
            int i6 = i4 + 8;
            Nat256.copy(((SecP256R1FieldElement) eCPoint.getRawYCoord()).f14213a, 0, iArr, i6);
            i4 = i6 + 8;
        }
        return new AbstractECLookupTable() { // from class: org.bouncycastle.math.ec.custom.sec.SecP256R1Curve.1
            private ECPoint createPoint(int[] iArr2, int[] iArr3) {
                return SecP256R1Curve.this.f(new SecP256R1FieldElement(iArr2), new SecP256R1FieldElement(iArr3), SecP256R1Curve.SECP256R1_AFFINE_ZS);
            }

            @Override // org.bouncycastle.math.ec.ECLookupTable
            public int getSize() {
                return i3;
            }

            @Override // org.bouncycastle.math.ec.ECLookupTable
            public ECPoint lookup(int i7) {
                int[] create = Nat256.create();
                int[] create2 = Nat256.create();
                int i8 = 0;
                for (int i9 = 0; i9 < i3; i9++) {
                    int i10 = ((i9 ^ i7) - 1) >> 31;
                    for (int i11 = 0; i11 < 8; i11++) {
                        int i12 = create[i11];
                        int[] iArr2 = iArr;
                        create[i11] = i12 ^ (iArr2[i8 + i11] & i10);
                        create2[i11] = create2[i11] ^ (iArr2[(i8 + 8) + i11] & i10);
                    }
                    i8 += 16;
                }
                return createPoint(create, create2);
            }

            @Override // org.bouncycastle.math.ec.AbstractECLookupTable, org.bouncycastle.math.ec.ECLookupTable
            public ECPoint lookupVar(int i7) {
                int[] create = Nat256.create();
                int[] create2 = Nat256.create();
                int i8 = i7 * 8 * 2;
                for (int i9 = 0; i9 < 8; i9++) {
                    int[] iArr2 = iArr;
                    create[i9] = iArr2[i8 + i9];
                    create2[i9] = iArr2[i8 + 8 + i9];
                }
                return createPoint(create, create2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.ec.ECCurve
    public ECPoint e(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return new SecP256R1Point(this, eCFieldElement, eCFieldElement2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.ec.ECCurve
    public ECPoint f(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        return new SecP256R1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecP256R1FieldElement(bigInteger);
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public int getFieldSize() {
        return f14207q.bitLength();
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public ECPoint getInfinity() {
        return this.f14208i;
    }

    public BigInteger getQ() {
        return f14207q;
    }

    @Override // org.bouncycastle.math.ec.ECCurve.AbstractFp, org.bouncycastle.math.ec.ECCurve
    public ECFieldElement randomFieldElement(SecureRandom secureRandom) {
        int[] create = Nat256.create();
        SecP256R1Field.random(secureRandom, create);
        return new SecP256R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.ec.ECCurve.AbstractFp, org.bouncycastle.math.ec.ECCurve
    public ECFieldElement randomFieldElementMult(SecureRandom secureRandom) {
        int[] create = Nat256.create();
        SecP256R1Field.randomMult(secureRandom, create);
        return new SecP256R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public boolean supportsCoordinateSystem(int i2) {
        return i2 == 2;
    }
}
