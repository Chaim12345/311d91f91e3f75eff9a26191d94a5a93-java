package org.bouncycastle.crypto.agreement.srp;

import java.math.BigInteger;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.SRP6GroupParameters;
/* loaded from: classes3.dex */
public class SRP6VerifierGenerator {

    /* renamed from: a  reason: collision with root package name */
    protected BigInteger f13288a;

    /* renamed from: b  reason: collision with root package name */
    protected BigInteger f13289b;

    /* renamed from: c  reason: collision with root package name */
    protected Digest f13290c;

    public BigInteger generateVerifier(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return this.f13289b.modPow(SRP6Util.calculateX(this.f13290c, this.f13288a, bArr, bArr2, bArr3), this.f13288a);
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, Digest digest) {
        this.f13288a = bigInteger;
        this.f13289b = bigInteger2;
        this.f13290c = digest;
    }

    public void init(SRP6GroupParameters sRP6GroupParameters, Digest digest) {
        this.f13288a = sRP6GroupParameters.getN();
        this.f13289b = sRP6GroupParameters.getG();
        this.f13290c = digest;
    }
}
