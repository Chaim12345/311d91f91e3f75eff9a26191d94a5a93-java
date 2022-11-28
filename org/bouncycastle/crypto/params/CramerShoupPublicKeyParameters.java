package org.bouncycastle.crypto.params;

import java.math.BigInteger;
/* loaded from: classes3.dex */
public class CramerShoupPublicKeyParameters extends CramerShoupKeyParameters {

    /* renamed from: c  reason: collision with root package name */
    private BigInteger f13466c;

    /* renamed from: d  reason: collision with root package name */
    private BigInteger f13467d;

    /* renamed from: h  reason: collision with root package name */
    private BigInteger f13468h;

    public CramerShoupPublicKeyParameters(CramerShoupParameters cramerShoupParameters, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        super(false, cramerShoupParameters);
        this.f13466c = bigInteger;
        this.f13467d = bigInteger2;
        this.f13468h = bigInteger3;
    }

    @Override // org.bouncycastle.crypto.params.CramerShoupKeyParameters
    public boolean equals(Object obj) {
        if (obj instanceof CramerShoupPublicKeyParameters) {
            CramerShoupPublicKeyParameters cramerShoupPublicKeyParameters = (CramerShoupPublicKeyParameters) obj;
            return cramerShoupPublicKeyParameters.getC().equals(this.f13466c) && cramerShoupPublicKeyParameters.getD().equals(this.f13467d) && cramerShoupPublicKeyParameters.getH().equals(this.f13468h) && super.equals(obj);
        }
        return false;
    }

    public BigInteger getC() {
        return this.f13466c;
    }

    public BigInteger getD() {
        return this.f13467d;
    }

    public BigInteger getH() {
        return this.f13468h;
    }

    @Override // org.bouncycastle.crypto.params.CramerShoupKeyParameters
    public int hashCode() {
        return ((this.f13466c.hashCode() ^ this.f13467d.hashCode()) ^ this.f13468h.hashCode()) ^ super.hashCode();
    }
}
