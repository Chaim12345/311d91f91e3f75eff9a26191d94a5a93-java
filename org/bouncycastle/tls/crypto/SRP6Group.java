package org.bouncycastle.tls.crypto;

import java.math.BigInteger;
/* loaded from: classes4.dex */
public class SRP6Group {
    private BigInteger N;

    /* renamed from: g  reason: collision with root package name */
    private BigInteger f14916g;

    public SRP6Group(BigInteger bigInteger, BigInteger bigInteger2) {
        this.N = bigInteger;
        this.f14916g = bigInteger2;
    }

    public BigInteger getG() {
        return this.f14916g;
    }

    public BigInteger getN() {
        return this.N;
    }
}
