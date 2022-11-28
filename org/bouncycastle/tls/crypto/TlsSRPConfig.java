package org.bouncycastle.tls.crypto;

import java.math.BigInteger;
/* loaded from: classes4.dex */
public class TlsSRPConfig {

    /* renamed from: a  reason: collision with root package name */
    protected BigInteger[] f14923a;

    public BigInteger[] getExplicitNG() {
        return (BigInteger[]) this.f14923a.clone();
    }

    public void setExplicitNG(BigInteger[] bigIntegerArr) {
        this.f14923a = (BigInteger[]) bigIntegerArr.clone();
    }
}
