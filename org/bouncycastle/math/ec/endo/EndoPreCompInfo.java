package org.bouncycastle.math.ec.endo;

import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.PreCompInfo;
/* loaded from: classes4.dex */
public class EndoPreCompInfo implements PreCompInfo {

    /* renamed from: a  reason: collision with root package name */
    protected ECEndomorphism f14311a;

    /* renamed from: b  reason: collision with root package name */
    protected ECPoint f14312b;

    public ECEndomorphism getEndomorphism() {
        return this.f14311a;
    }

    public ECPoint getMappedPoint() {
        return this.f14312b;
    }

    public void setEndomorphism(ECEndomorphism eCEndomorphism) {
        this.f14311a = eCEndomorphism;
    }

    public void setMappedPoint(ECPoint eCPoint) {
        this.f14312b = eCPoint;
    }
}
