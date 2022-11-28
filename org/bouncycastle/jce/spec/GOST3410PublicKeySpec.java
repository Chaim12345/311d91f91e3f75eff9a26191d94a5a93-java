package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.KeySpec;
/* loaded from: classes3.dex */
public class GOST3410PublicKeySpec implements KeySpec {

    /* renamed from: a  reason: collision with root package name */
    private BigInteger f13849a;

    /* renamed from: p  reason: collision with root package name */
    private BigInteger f13850p;

    /* renamed from: q  reason: collision with root package name */
    private BigInteger f13851q;
    private BigInteger y;

    public GOST3410PublicKeySpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.y = bigInteger;
        this.f13850p = bigInteger2;
        this.f13851q = bigInteger3;
        this.f13849a = bigInteger4;
    }

    public BigInteger getA() {
        return this.f13849a;
    }

    public BigInteger getP() {
        return this.f13850p;
    }

    public BigInteger getQ() {
        return this.f13851q;
    }

    public BigInteger getY() {
        return this.y;
    }
}
