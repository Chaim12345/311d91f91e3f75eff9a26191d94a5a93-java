package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes3.dex */
public class DSAParameters implements CipherParameters {

    /* renamed from: g  reason: collision with root package name */
    private BigInteger f13477g;

    /* renamed from: p  reason: collision with root package name */
    private BigInteger f13478p;

    /* renamed from: q  reason: collision with root package name */
    private BigInteger f13479q;
    private DSAValidationParameters validation;

    public DSAParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f13477g = bigInteger3;
        this.f13478p = bigInteger;
        this.f13479q = bigInteger2;
    }

    public DSAParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, DSAValidationParameters dSAValidationParameters) {
        this.f13477g = bigInteger3;
        this.f13478p = bigInteger;
        this.f13479q = bigInteger2;
        this.validation = dSAValidationParameters;
    }

    public boolean equals(Object obj) {
        if (obj instanceof DSAParameters) {
            DSAParameters dSAParameters = (DSAParameters) obj;
            return dSAParameters.getP().equals(this.f13478p) && dSAParameters.getQ().equals(this.f13479q) && dSAParameters.getG().equals(this.f13477g);
        }
        return false;
    }

    public BigInteger getG() {
        return this.f13477g;
    }

    public BigInteger getP() {
        return this.f13478p;
    }

    public BigInteger getQ() {
        return this.f13479q;
    }

    public DSAValidationParameters getValidationParameters() {
        return this.validation;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getQ().hashCode()) ^ getG().hashCode();
    }
}
