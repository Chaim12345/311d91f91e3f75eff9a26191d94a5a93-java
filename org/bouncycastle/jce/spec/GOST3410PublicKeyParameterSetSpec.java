package org.bouncycastle.jce.spec;

import java.math.BigInteger;
/* loaded from: classes3.dex */
public class GOST3410PublicKeyParameterSetSpec {

    /* renamed from: a  reason: collision with root package name */
    private BigInteger f13846a;

    /* renamed from: p  reason: collision with root package name */
    private BigInteger f13847p;

    /* renamed from: q  reason: collision with root package name */
    private BigInteger f13848q;

    public GOST3410PublicKeyParameterSetSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f13847p = bigInteger;
        this.f13848q = bigInteger2;
        this.f13846a = bigInteger3;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GOST3410PublicKeyParameterSetSpec) {
            GOST3410PublicKeyParameterSetSpec gOST3410PublicKeyParameterSetSpec = (GOST3410PublicKeyParameterSetSpec) obj;
            return this.f13846a.equals(gOST3410PublicKeyParameterSetSpec.f13846a) && this.f13847p.equals(gOST3410PublicKeyParameterSetSpec.f13847p) && this.f13848q.equals(gOST3410PublicKeyParameterSetSpec.f13848q);
        }
        return false;
    }

    public BigInteger getA() {
        return this.f13846a;
    }

    public BigInteger getP() {
        return this.f13847p;
    }

    public BigInteger getQ() {
        return this.f13848q;
    }

    public int hashCode() {
        return (this.f13846a.hashCode() ^ this.f13847p.hashCode()) ^ this.f13848q.hashCode();
    }
}
