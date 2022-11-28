package org.bouncycastle.math.ec;

import java.math.BigInteger;
/* loaded from: classes3.dex */
public abstract class AbstractECMultiplier implements ECMultiplier {
    protected ECPoint a(ECPoint eCPoint) {
        return ECAlgorithms.a(eCPoint);
    }

    protected abstract ECPoint b(ECPoint eCPoint, BigInteger bigInteger);

    @Override // org.bouncycastle.math.ec.ECMultiplier
    public ECPoint multiply(ECPoint eCPoint, BigInteger bigInteger) {
        int signum = bigInteger.signum();
        if (signum == 0 || eCPoint.isInfinity()) {
            return eCPoint.getCurve().getInfinity();
        }
        ECPoint b2 = b(eCPoint, bigInteger.abs());
        if (signum <= 0) {
            b2 = b2.negate();
        }
        return a(b2);
    }
}
