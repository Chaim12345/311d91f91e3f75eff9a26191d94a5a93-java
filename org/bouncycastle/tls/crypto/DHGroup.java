package org.bouncycastle.tls.crypto;

import java.math.BigInteger;
/* loaded from: classes4.dex */
public class DHGroup {

    /* renamed from: g  reason: collision with root package name */
    private final BigInteger f14912g;

    /* renamed from: l  reason: collision with root package name */
    private final int f14913l;

    /* renamed from: p  reason: collision with root package name */
    private final BigInteger f14914p;

    /* renamed from: q  reason: collision with root package name */
    private final BigInteger f14915q;

    public DHGroup(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i2) {
        this.f14914p = bigInteger;
        this.f14912g = bigInteger3;
        this.f14915q = bigInteger2;
        this.f14913l = i2;
    }

    public BigInteger getG() {
        return this.f14912g;
    }

    public int getL() {
        return this.f14913l;
    }

    public BigInteger getP() {
        return this.f14914p;
    }

    public BigInteger getQ() {
        return this.f14915q;
    }
}
