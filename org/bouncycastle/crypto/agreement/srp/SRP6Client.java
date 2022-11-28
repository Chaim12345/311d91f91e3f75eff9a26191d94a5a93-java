package org.bouncycastle.crypto.agreement.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.SRP6GroupParameters;
/* loaded from: classes3.dex */
public class SRP6Client {

    /* renamed from: a  reason: collision with root package name */
    protected BigInteger f13262a;

    /* renamed from: b  reason: collision with root package name */
    protected BigInteger f13263b;

    /* renamed from: c  reason: collision with root package name */
    protected BigInteger f13264c;

    /* renamed from: d  reason: collision with root package name */
    protected BigInteger f13265d;

    /* renamed from: e  reason: collision with root package name */
    protected BigInteger f13266e;

    /* renamed from: f  reason: collision with root package name */
    protected BigInteger f13267f;

    /* renamed from: g  reason: collision with root package name */
    protected BigInteger f13268g;

    /* renamed from: h  reason: collision with root package name */
    protected BigInteger f13269h;

    /* renamed from: i  reason: collision with root package name */
    protected BigInteger f13270i;

    /* renamed from: j  reason: collision with root package name */
    protected BigInteger f13271j;

    /* renamed from: k  reason: collision with root package name */
    protected BigInteger f13272k;

    /* renamed from: l  reason: collision with root package name */
    protected Digest f13273l;

    /* renamed from: m  reason: collision with root package name */
    protected SecureRandom f13274m;

    private BigInteger calculateS() {
        BigInteger calculateK = SRP6Util.calculateK(this.f13273l, this.f13262a, this.f13263b);
        return this.f13266e.subtract(this.f13263b.modPow(this.f13267f, this.f13262a).multiply(calculateK).mod(this.f13262a)).mod(this.f13262a).modPow(this.f13268g.multiply(this.f13267f).add(this.f13264c), this.f13262a);
    }

    protected BigInteger a() {
        return SRP6Util.generatePrivateValue(this.f13273l, this.f13262a, this.f13263b, this.f13274m);
    }

    public BigInteger calculateClientEvidenceMessage() {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger bigInteger3 = this.f13265d;
        if (bigInteger3 == null || (bigInteger = this.f13266e) == null || (bigInteger2 = this.f13269h) == null) {
            throw new CryptoException("Impossible to compute M1: some data are missing from the previous operations (A,B,S)");
        }
        BigInteger calculateM1 = SRP6Util.calculateM1(this.f13273l, this.f13262a, bigInteger3, bigInteger, bigInteger2);
        this.f13270i = calculateM1;
        return calculateM1;
    }

    public BigInteger calculateSecret(BigInteger bigInteger) {
        BigInteger validatePublicValue = SRP6Util.validatePublicValue(this.f13262a, bigInteger);
        this.f13266e = validatePublicValue;
        this.f13268g = SRP6Util.calculateU(this.f13273l, this.f13262a, this.f13265d, validatePublicValue);
        BigInteger calculateS = calculateS();
        this.f13269h = calculateS;
        return calculateS;
    }

    public BigInteger calculateSessionKey() {
        BigInteger bigInteger = this.f13269h;
        if (bigInteger == null || this.f13270i == null || this.f13271j == null) {
            throw new CryptoException("Impossible to compute Key: some data are missing from the previous operations (S,M1,M2)");
        }
        BigInteger calculateKey = SRP6Util.calculateKey(this.f13273l, this.f13262a, bigInteger);
        this.f13272k = calculateKey;
        return calculateKey;
    }

    public BigInteger generateClientCredentials(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.f13267f = SRP6Util.calculateX(this.f13273l, this.f13262a, bArr, bArr2, bArr3);
        BigInteger a2 = a();
        this.f13264c = a2;
        BigInteger modPow = this.f13263b.modPow(a2, this.f13262a);
        this.f13265d = modPow;
        return modPow;
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, Digest digest, SecureRandom secureRandom) {
        this.f13262a = bigInteger;
        this.f13263b = bigInteger2;
        this.f13273l = digest;
        this.f13274m = secureRandom;
    }

    public void init(SRP6GroupParameters sRP6GroupParameters, Digest digest, SecureRandom secureRandom) {
        init(sRP6GroupParameters.getN(), sRP6GroupParameters.getG(), digest, secureRandom);
    }

    public boolean verifyServerEvidenceMessage(BigInteger bigInteger) {
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        BigInteger bigInteger4 = this.f13265d;
        if (bigInteger4 == null || (bigInteger2 = this.f13270i) == null || (bigInteger3 = this.f13269h) == null) {
            throw new CryptoException("Impossible to compute and verify M2: some data are missing from the previous operations (A,M1,S)");
        }
        if (SRP6Util.calculateM2(this.f13273l, this.f13262a, bigInteger4, bigInteger2, bigInteger3).equals(bigInteger)) {
            this.f13271j = bigInteger;
            return true;
        }
        return false;
    }
}
