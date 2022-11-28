package org.bouncycastle.math.field;

import java.math.BigInteger;
/* loaded from: classes4.dex */
public abstract class FiniteFields {

    /* renamed from: a  reason: collision with root package name */
    static final FiniteField f14351a = new PrimeField(BigInteger.valueOf(2));

    /* renamed from: b  reason: collision with root package name */
    static final FiniteField f14352b = new PrimeField(BigInteger.valueOf(3));

    public static PolynomialExtensionField getBinaryExtensionField(int[] iArr) {
        if (iArr[0] == 0) {
            for (int i2 = 1; i2 < iArr.length; i2++) {
                if (iArr[i2] <= iArr[i2 - 1]) {
                    throw new IllegalArgumentException("Polynomial exponents must be monotonically increasing");
                }
            }
            return new GenericPolynomialExtensionField(f14351a, new GF2Polynomial(iArr));
        }
        throw new IllegalArgumentException("Irreducible polynomials in GF(2) must have constant term");
    }

    public static FiniteField getPrimeField(BigInteger bigInteger) {
        int bitLength = bigInteger.bitLength();
        if (bigInteger.signum() <= 0 || bitLength < 2) {
            throw new IllegalArgumentException("'characteristic' must be >= 2");
        }
        if (bitLength < 3) {
            int intValue = bigInteger.intValue();
            if (intValue == 2) {
                return f14351a;
            }
            if (intValue == 3) {
                return f14352b;
            }
        }
        return new PrimeField(bigInteger);
    }
}
