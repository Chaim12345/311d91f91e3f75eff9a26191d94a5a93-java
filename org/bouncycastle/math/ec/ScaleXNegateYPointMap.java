package org.bouncycastle.math.ec;
/* loaded from: classes3.dex */
public class ScaleXNegateYPointMap implements ECPointMap {

    /* renamed from: a  reason: collision with root package name */
    protected final ECFieldElement f14111a;

    public ScaleXNegateYPointMap(ECFieldElement eCFieldElement) {
        this.f14111a = eCFieldElement;
    }

    @Override // org.bouncycastle.math.ec.ECPointMap
    public ECPoint map(ECPoint eCPoint) {
        return eCPoint.scaleXNegateY(this.f14111a);
    }
}
