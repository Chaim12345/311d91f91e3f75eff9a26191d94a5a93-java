package org.bouncycastle.math.ec;
/* loaded from: classes3.dex */
public class WNafPreCompInfo implements PreCompInfo {

    /* renamed from: a  reason: collision with root package name */
    volatile int f14115a = 4;

    /* renamed from: b  reason: collision with root package name */
    protected int f14116b = -1;

    /* renamed from: c  reason: collision with root package name */
    protected ECPoint[] f14117c = null;

    /* renamed from: d  reason: collision with root package name */
    protected ECPoint[] f14118d = null;

    /* renamed from: e  reason: collision with root package name */
    protected ECPoint f14119e = null;

    /* renamed from: f  reason: collision with root package name */
    protected int f14120f = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        int i2 = this.f14115a;
        if (i2 > 0) {
            int i3 = i2 - 1;
            this.f14115a = i3;
            return i3;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b() {
        return this.f14115a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(int i2) {
        this.f14115a = i2;
    }

    public int getConfWidth() {
        return this.f14116b;
    }

    public ECPoint[] getPreComp() {
        return this.f14117c;
    }

    public ECPoint[] getPreCompNeg() {
        return this.f14118d;
    }

    public ECPoint getTwice() {
        return this.f14119e;
    }

    public int getWidth() {
        return this.f14120f;
    }

    public boolean isPromoted() {
        return this.f14115a <= 0;
    }

    public void setConfWidth(int i2) {
        this.f14116b = i2;
    }

    public void setPreComp(ECPoint[] eCPointArr) {
        this.f14117c = eCPointArr;
    }

    public void setPreCompNeg(ECPoint[] eCPointArr) {
        this.f14118d = eCPointArr;
    }

    public void setTwice(ECPoint eCPoint) {
        this.f14119e = eCPoint;
    }

    public void setWidth(int i2) {
        this.f14120f = i2;
    }
}
