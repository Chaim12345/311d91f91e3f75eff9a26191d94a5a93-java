package org.bouncycastle.math.ec;
/* loaded from: classes3.dex */
public class ScaleXPointMap implements ECPointMap {

    /* renamed from: a  reason: collision with root package name */
    protected final ECFieldElement f14112a;

    public ScaleXPointMap(ECFieldElement eCFieldElement) {
        this.f14112a = eCFieldElement;
    }

    @Override // org.bouncycastle.math.ec.ECPointMap
    public ECPoint map(ECPoint eCPoint) {
        return eCPoint.scaleX(this.f14112a);
    }
}
