package org.bouncycastle.math.ec;
/* loaded from: classes3.dex */
public class ScaleYNegateXPointMap implements ECPointMap {

    /* renamed from: a  reason: collision with root package name */
    protected final ECFieldElement f14113a;

    public ScaleYNegateXPointMap(ECFieldElement eCFieldElement) {
        this.f14113a = eCFieldElement;
    }

    @Override // org.bouncycastle.math.ec.ECPointMap
    public ECPoint map(ECPoint eCPoint) {
        return eCPoint.scaleYNegateX(this.f14113a);
    }
}
