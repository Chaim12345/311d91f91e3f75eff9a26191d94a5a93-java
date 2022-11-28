package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;
/* loaded from: classes3.dex */
public class ElGamalParameterSpec implements AlgorithmParameterSpec {

    /* renamed from: g  reason: collision with root package name */
    private BigInteger f13841g;

    /* renamed from: p  reason: collision with root package name */
    private BigInteger f13842p;

    public ElGamalParameterSpec(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f13842p = bigInteger;
        this.f13841g = bigInteger2;
    }

    public BigInteger getG() {
        return this.f13841g;
    }

    public BigInteger getP() {
        return this.f13842p;
    }
}
