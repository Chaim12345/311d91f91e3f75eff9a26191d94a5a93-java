package org.bouncycastle.tls.crypto.impl.jcajce.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.tls.crypto.SRP6Group;
import org.bouncycastle.tls.crypto.TlsHash;
/* loaded from: classes4.dex */
public class SRP6Client {

    /* renamed from: a  reason: collision with root package name */
    protected BigInteger f15048a;

    /* renamed from: b  reason: collision with root package name */
    protected BigInteger f15049b;

    /* renamed from: c  reason: collision with root package name */
    protected BigInteger f15050c;

    /* renamed from: d  reason: collision with root package name */
    protected BigInteger f15051d;

    /* renamed from: e  reason: collision with root package name */
    protected BigInteger f15052e;

    /* renamed from: f  reason: collision with root package name */
    protected BigInteger f15053f;

    /* renamed from: g  reason: collision with root package name */
    protected BigInteger f15054g;

    /* renamed from: h  reason: collision with root package name */
    protected BigInteger f15055h;

    /* renamed from: i  reason: collision with root package name */
    protected BigInteger f15056i;

    /* renamed from: j  reason: collision with root package name */
    protected BigInteger f15057j;

    /* renamed from: k  reason: collision with root package name */
    protected BigInteger f15058k;

    /* renamed from: l  reason: collision with root package name */
    protected TlsHash f15059l;

    /* renamed from: m  reason: collision with root package name */
    protected SecureRandom f15060m;

    private BigInteger calculateS() {
        BigInteger calculateK = SRP6Util.calculateK(this.f15059l, this.f15048a, this.f15049b);
        return this.f15052e.subtract(this.f15049b.modPow(this.f15053f, this.f15048a).multiply(calculateK).mod(this.f15048a)).mod(this.f15048a).modPow(this.f15054g.multiply(this.f15053f).add(this.f15050c), this.f15048a);
    }

    protected BigInteger a() {
        return SRP6Util.generatePrivateValue(this.f15048a, this.f15049b, this.f15060m);
    }

    public BigInteger calculateClientEvidenceMessage() {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger bigInteger3 = this.f15051d;
        if (bigInteger3 == null || (bigInteger = this.f15052e) == null || (bigInteger2 = this.f15055h) == null) {
            throw new IllegalStateException("Impossible to compute M1: some data are missing from the previous operations (A,B,S)");
        }
        BigInteger calculateM1 = SRP6Util.calculateM1(this.f15059l, this.f15048a, bigInteger3, bigInteger, bigInteger2);
        this.f15056i = calculateM1;
        return calculateM1;
    }

    public BigInteger calculateSecret(BigInteger bigInteger) {
        BigInteger validatePublicValue = SRP6Util.validatePublicValue(this.f15048a, bigInteger);
        this.f15052e = validatePublicValue;
        this.f15054g = SRP6Util.calculateU(this.f15059l, this.f15048a, this.f15051d, validatePublicValue);
        BigInteger calculateS = calculateS();
        this.f15055h = calculateS;
        return calculateS;
    }

    public BigInteger calculateSessionKey() {
        BigInteger bigInteger = this.f15055h;
        if (bigInteger == null || this.f15056i == null || this.f15057j == null) {
            throw new IllegalStateException("Impossible to compute Key: some data are missing from the previous operations (S,M1,M2)");
        }
        BigInteger calculateKey = SRP6Util.calculateKey(this.f15059l, this.f15048a, bigInteger);
        this.f15058k = calculateKey;
        return calculateKey;
    }

    public BigInteger generateClientCredentials(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.f15053f = SRP6Util.calculateX(this.f15059l, this.f15048a, bArr, bArr2, bArr3);
        BigInteger a2 = a();
        this.f15050c = a2;
        BigInteger modPow = this.f15049b.modPow(a2, this.f15048a);
        this.f15051d = modPow;
        return modPow;
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, TlsHash tlsHash, SecureRandom secureRandom) {
        this.f15048a = bigInteger;
        this.f15049b = bigInteger2;
        this.f15059l = tlsHash;
        this.f15060m = secureRandom;
    }

    public void init(SRP6Group sRP6Group, TlsHash tlsHash, SecureRandom secureRandom) {
        init(sRP6Group.getN(), sRP6Group.getG(), tlsHash, secureRandom);
    }

    public boolean verifyServerEvidenceMessage(BigInteger bigInteger) {
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        BigInteger bigInteger4 = this.f15051d;
        if (bigInteger4 == null || (bigInteger2 = this.f15056i) == null || (bigInteger3 = this.f15055h) == null) {
            throw new IllegalStateException("Impossible to compute and verify M2: some data are missing from the previous operations (A,M1,S)");
        }
        if (SRP6Util.calculateM2(this.f15059l, this.f15048a, bigInteger4, bigInteger2, bigInteger3).equals(bigInteger)) {
            this.f15057j = bigInteger;
            return true;
        }
        return false;
    }
}
