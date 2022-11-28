package org.bouncycastle.crypto.params;

import java.math.BigInteger;
/* loaded from: classes3.dex */
public class RSAPrivateCrtKeyParameters extends RSAKeyParameters {
    private BigInteger dP;
    private BigInteger dQ;

    /* renamed from: e  reason: collision with root package name */
    private BigInteger f13501e;

    /* renamed from: p  reason: collision with root package name */
    private BigInteger f13502p;

    /* renamed from: q  reason: collision with root package name */
    private BigInteger f13503q;
    private BigInteger qInv;

    public RSAPrivateCrtKeyParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigInteger bigInteger6, BigInteger bigInteger7, BigInteger bigInteger8) {
        super(true, bigInteger, bigInteger3);
        this.f13501e = bigInteger2;
        this.f13502p = bigInteger4;
        this.f13503q = bigInteger5;
        this.dP = bigInteger6;
        this.dQ = bigInteger7;
        this.qInv = bigInteger8;
    }

    public BigInteger getDP() {
        return this.dP;
    }

    public BigInteger getDQ() {
        return this.dQ;
    }

    public BigInteger getP() {
        return this.f13502p;
    }

    public BigInteger getPublicExponent() {
        return this.f13501e;
    }

    public BigInteger getQ() {
        return this.f13503q;
    }

    public BigInteger getQInv() {
        return this.qInv;
    }
}
