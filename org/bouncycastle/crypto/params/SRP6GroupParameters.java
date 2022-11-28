package org.bouncycastle.crypto.params;

import java.math.BigInteger;
/* loaded from: classes3.dex */
public class SRP6GroupParameters {
    private BigInteger N;

    /* renamed from: g  reason: collision with root package name */
    private BigInteger f13504g;

    public SRP6GroupParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this.N = bigInteger;
        this.f13504g = bigInteger2;
    }

    public BigInteger getG() {
        return this.f13504g;
    }

    public BigInteger getN() {
        return this.N;
    }
}
