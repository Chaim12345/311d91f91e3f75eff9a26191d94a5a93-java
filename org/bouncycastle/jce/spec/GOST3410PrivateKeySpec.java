package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.KeySpec;
/* loaded from: classes3.dex */
public class GOST3410PrivateKeySpec implements KeySpec {

    /* renamed from: a  reason: collision with root package name */
    private BigInteger f13843a;

    /* renamed from: p  reason: collision with root package name */
    private BigInteger f13844p;

    /* renamed from: q  reason: collision with root package name */
    private BigInteger f13845q;
    private BigInteger x;

    public GOST3410PrivateKeySpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.x = bigInteger;
        this.f13844p = bigInteger2;
        this.f13845q = bigInteger3;
        this.f13843a = bigInteger4;
    }

    public BigInteger getA() {
        return this.f13843a;
    }

    public BigInteger getP() {
        return this.f13844p;
    }

    public BigInteger getQ() {
        return this.f13845q;
    }

    public BigInteger getX() {
        return this.x;
    }
}
