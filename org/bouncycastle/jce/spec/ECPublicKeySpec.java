package org.bouncycastle.jce.spec;

import org.bouncycastle.math.ec.ECPoint;
/* loaded from: classes3.dex */
public class ECPublicKeySpec extends ECKeySpec {

    /* renamed from: q  reason: collision with root package name */
    private ECPoint f13840q;

    public ECPublicKeySpec(ECPoint eCPoint, ECParameterSpec eCParameterSpec) {
        super(eCParameterSpec);
        this.f13840q = eCPoint.getCurve() != null ? eCPoint.normalize() : eCPoint;
    }

    public ECPoint getQ() {
        return this.f13840q;
    }
}
