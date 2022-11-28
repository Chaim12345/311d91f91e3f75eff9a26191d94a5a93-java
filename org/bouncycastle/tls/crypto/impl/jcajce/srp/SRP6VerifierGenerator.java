package org.bouncycastle.tls.crypto.impl.jcajce.srp;

import java.math.BigInteger;
import org.bouncycastle.tls.crypto.SRP6Group;
import org.bouncycastle.tls.crypto.TlsHash;
/* loaded from: classes4.dex */
public class SRP6VerifierGenerator {

    /* renamed from: a  reason: collision with root package name */
    protected BigInteger f15074a;

    /* renamed from: b  reason: collision with root package name */
    protected BigInteger f15075b;

    /* renamed from: c  reason: collision with root package name */
    protected TlsHash f15076c;

    public BigInteger generateVerifier(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return this.f15075b.modPow(SRP6Util.calculateX(this.f15076c, this.f15074a, bArr, bArr2, bArr3), this.f15074a);
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, TlsHash tlsHash) {
        this.f15074a = bigInteger;
        this.f15075b = bigInteger2;
        this.f15076c = tlsHash;
    }

    public void init(SRP6Group sRP6Group, TlsHash tlsHash) {
        this.f15074a = sRP6Group.getN();
        this.f15075b = sRP6Group.getG();
        this.f15076c = tlsHash;
    }
}
