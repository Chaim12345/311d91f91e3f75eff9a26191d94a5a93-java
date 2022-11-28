package org.bouncycastle.tls.crypto;
/* loaded from: classes4.dex */
public class TlsDHConfig {

    /* renamed from: a  reason: collision with root package name */
    protected final DHGroup f14917a;

    /* renamed from: b  reason: collision with root package name */
    protected final int f14918b;

    /* renamed from: c  reason: collision with root package name */
    protected final boolean f14919c;

    public TlsDHConfig(int i2, boolean z) {
        this.f14917a = null;
        this.f14918b = i2;
        this.f14919c = z;
    }

    public TlsDHConfig(DHGroup dHGroup) {
        this.f14917a = dHGroup;
        this.f14918b = -1;
        this.f14919c = false;
    }

    public DHGroup getExplicitGroup() {
        return this.f14917a;
    }

    public int getNamedGroup() {
        return this.f14918b;
    }

    public boolean isPadded() {
        return this.f14919c;
    }
}
