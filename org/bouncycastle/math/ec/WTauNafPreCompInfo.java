package org.bouncycastle.math.ec;

import org.bouncycastle.math.ec.ECPoint;
/* loaded from: classes3.dex */
public class WTauNafPreCompInfo implements PreCompInfo {

    /* renamed from: a  reason: collision with root package name */
    protected ECPoint.AbstractF2m[] f14131a = null;

    public ECPoint.AbstractF2m[] getPreComp() {
        return this.f14131a;
    }

    public void setPreComp(ECPoint.AbstractF2m[] abstractF2mArr) {
        this.f14131a = abstractF2mArr;
    }
}
