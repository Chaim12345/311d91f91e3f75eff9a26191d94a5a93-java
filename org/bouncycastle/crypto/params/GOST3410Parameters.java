package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes3.dex */
public class GOST3410Parameters implements CipherParameters {

    /* renamed from: a  reason: collision with root package name */
    private BigInteger f13487a;

    /* renamed from: p  reason: collision with root package name */
    private BigInteger f13488p;

    /* renamed from: q  reason: collision with root package name */
    private BigInteger f13489q;
    private GOST3410ValidationParameters validation;

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f13488p = bigInteger;
        this.f13489q = bigInteger2;
        this.f13487a = bigInteger3;
    }

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, GOST3410ValidationParameters gOST3410ValidationParameters) {
        this.f13487a = bigInteger3;
        this.f13488p = bigInteger;
        this.f13489q = bigInteger2;
        this.validation = gOST3410ValidationParameters;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GOST3410Parameters) {
            GOST3410Parameters gOST3410Parameters = (GOST3410Parameters) obj;
            return gOST3410Parameters.getP().equals(this.f13488p) && gOST3410Parameters.getQ().equals(this.f13489q) && gOST3410Parameters.getA().equals(this.f13487a);
        }
        return false;
    }

    public BigInteger getA() {
        return this.f13487a;
    }

    public BigInteger getP() {
        return this.f13488p;
    }

    public BigInteger getQ() {
        return this.f13489q;
    }

    public GOST3410ValidationParameters getValidationParameters() {
        return this.validation;
    }

    public int hashCode() {
        return (this.f13488p.hashCode() ^ this.f13489q.hashCode()) ^ this.f13487a.hashCode();
    }
}
