package org.bouncycastle.math.ec;
/* loaded from: classes3.dex */
public class ScaleYPointMap implements ECPointMap {

    /* renamed from: a  reason: collision with root package name */
    protected final ECFieldElement f14114a;

    public ScaleYPointMap(ECFieldElement eCFieldElement) {
        this.f14114a = eCFieldElement;
    }

    @Override // org.bouncycastle.math.ec.ECPointMap
    public ECPoint map(ECPoint eCPoint) {
        return eCPoint.scaleY(this.f14114a);
    }
}
