package org.bouncycastle.math.ec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.endo.EndoUtil;
import org.bouncycastle.math.ec.endo.GLVEndomorphism;
/* loaded from: classes3.dex */
public class GLVMultiplier extends AbstractECMultiplier {

    /* renamed from: a  reason: collision with root package name */
    protected final ECCurve f14108a;

    /* renamed from: b  reason: collision with root package name */
    protected final GLVEndomorphism f14109b;

    public GLVMultiplier(ECCurve eCCurve, GLVEndomorphism gLVEndomorphism) {
        if (eCCurve == null || eCCurve.getOrder() == null) {
            throw new IllegalArgumentException("Need curve with known group order");
        }
        this.f14108a = eCCurve;
        this.f14109b = gLVEndomorphism;
    }

    @Override // org.bouncycastle.math.ec.AbstractECMultiplier
    protected ECPoint b(ECPoint eCPoint, BigInteger bigInteger) {
        if (this.f14108a.equals(eCPoint.getCurve())) {
            BigInteger[] decomposeScalar = this.f14109b.decomposeScalar(bigInteger.mod(eCPoint.getCurve().getOrder()));
            BigInteger bigInteger2 = decomposeScalar[0];
            BigInteger bigInteger3 = decomposeScalar[1];
            return this.f14109b.hasEfficientPointMap() ? ECAlgorithms.d(this.f14109b, eCPoint, bigInteger2, bigInteger3) : ECAlgorithms.c(eCPoint, bigInteger2, EndoUtil.mapPoint(this.f14109b, eCPoint), bigInteger3);
        }
        throw new IllegalStateException();
    }
}
