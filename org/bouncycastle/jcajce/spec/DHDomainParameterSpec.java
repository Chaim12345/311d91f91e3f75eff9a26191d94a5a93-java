package org.bouncycastle.jcajce.spec;

import java.math.BigInteger;
import javax.crypto.spec.DHParameterSpec;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.params.DHValidationParameters;
/* loaded from: classes3.dex */
public class DHDomainParameterSpec extends DHParameterSpec {

    /* renamed from: j  reason: collision with root package name */
    private final BigInteger f13797j;

    /* renamed from: m  reason: collision with root package name */
    private final int f13798m;

    /* renamed from: q  reason: collision with root package name */
    private final BigInteger f13799q;
    private DHValidationParameters validationParameters;

    public DHDomainParameterSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this(bigInteger, bigInteger2, bigInteger3, null, 0);
    }

    public DHDomainParameterSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i2) {
        this(bigInteger, bigInteger2, bigInteger3, null, i2);
    }

    public DHDomainParameterSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, int i2) {
        this(bigInteger, bigInteger2, bigInteger3, bigInteger4, 0, i2);
    }

    public DHDomainParameterSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, int i2, int i3) {
        super(bigInteger, bigInteger3, i3);
        this.f13799q = bigInteger2;
        this.f13797j = bigInteger4;
        this.f13798m = i2;
    }

    public DHDomainParameterSpec(DHParameters dHParameters) {
        this(dHParameters.getP(), dHParameters.getQ(), dHParameters.getG(), dHParameters.getJ(), dHParameters.getM(), dHParameters.getL());
        this.validationParameters = dHParameters.getValidationParameters();
    }

    public DHParameters getDomainParameters() {
        return new DHParameters(getP(), getG(), this.f13799q, this.f13798m, getL(), this.f13797j, this.validationParameters);
    }

    public BigInteger getJ() {
        return this.f13797j;
    }

    public int getM() {
        return this.f13798m;
    }

    public BigInteger getQ() {
        return this.f13799q;
    }
}
