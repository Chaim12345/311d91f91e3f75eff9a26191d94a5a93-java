package org.bouncycastle.math.ec;
/* loaded from: classes3.dex */
public class FixedPointPreCompInfo implements PreCompInfo {

    /* renamed from: a  reason: collision with root package name */
    protected ECPoint f14103a = null;

    /* renamed from: b  reason: collision with root package name */
    protected ECLookupTable f14104b = null;

    /* renamed from: c  reason: collision with root package name */
    protected int f14105c = -1;

    public ECLookupTable getLookupTable() {
        return this.f14104b;
    }

    public ECPoint getOffset() {
        return this.f14103a;
    }

    public int getWidth() {
        return this.f14105c;
    }

    public void setLookupTable(ECLookupTable eCLookupTable) {
        this.f14104b = eCLookupTable;
    }

    public void setOffset(ECPoint eCPoint) {
        this.f14103a = eCPoint;
    }

    public void setWidth(int i2) {
        this.f14105c = i2;
    }
}
