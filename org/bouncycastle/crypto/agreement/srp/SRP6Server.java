package org.bouncycastle.crypto.agreement.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.SRP6GroupParameters;
/* loaded from: classes3.dex */
public class SRP6Server {

    /* renamed from: a  reason: collision with root package name */
    protected BigInteger f13275a;

    /* renamed from: b  reason: collision with root package name */
    protected BigInteger f13276b;

    /* renamed from: c  reason: collision with root package name */
    protected BigInteger f13277c;

    /* renamed from: d  reason: collision with root package name */
    protected SecureRandom f13278d;

    /* renamed from: e  reason: collision with root package name */
    protected Digest f13279e;

    /* renamed from: f  reason: collision with root package name */
    protected BigInteger f13280f;

    /* renamed from: g  reason: collision with root package name */
    protected BigInteger f13281g;

    /* renamed from: h  reason: collision with root package name */
    protected BigInteger f13282h;

    /* renamed from: i  reason: collision with root package name */
    protected BigInteger f13283i;

    /* renamed from: j  reason: collision with root package name */
    protected BigInteger f13284j;

    /* renamed from: k  reason: collision with root package name */
    protected BigInteger f13285k;

    /* renamed from: l  reason: collision with root package name */
    protected BigInteger f13286l;

    /* renamed from: m  reason: collision with root package name */
    protected BigInteger f13287m;

    private BigInteger calculateS() {
        return this.f13277c.modPow(this.f13283i, this.f13275a).multiply(this.f13280f).mod(this.f13275a).modPow(this.f13281g, this.f13275a);
    }

    protected BigInteger a() {
        return SRP6Util.generatePrivateValue(this.f13279e, this.f13275a, this.f13276b, this.f13278d);
    }

    public BigInteger calculateSecret(BigInteger bigInteger) {
        BigInteger validatePublicValue = SRP6Util.validatePublicValue(this.f13275a, bigInteger);
        this.f13280f = validatePublicValue;
        this.f13283i = SRP6Util.calculateU(this.f13279e, this.f13275a, validatePublicValue, this.f13282h);
        BigInteger calculateS = calculateS();
        this.f13284j = calculateS;
        return calculateS;
    }

    public BigInteger calculateServerEvidenceMessage() {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger bigInteger3 = this.f13280f;
        if (bigInteger3 == null || (bigInteger = this.f13285k) == null || (bigInteger2 = this.f13284j) == null) {
            throw new CryptoException("Impossible to compute M2: some data are missing from the previous operations (A,M1,S)");
        }
        BigInteger calculateM2 = SRP6Util.calculateM2(this.f13279e, this.f13275a, bigInteger3, bigInteger, bigInteger2);
        this.f13286l = calculateM2;
        return calculateM2;
    }

    public BigInteger calculateSessionKey() {
        BigInteger bigInteger = this.f13284j;
        if (bigInteger == null || this.f13285k == null || this.f13286l == null) {
            throw new CryptoException("Impossible to compute Key: some data are missing from the previous operations (S,M1,M2)");
        }
        BigInteger calculateKey = SRP6Util.calculateKey(this.f13279e, this.f13275a, bigInteger);
        this.f13287m = calculateKey;
        return calculateKey;
    }

    public BigInteger generateServerCredentials() {
        BigInteger calculateK = SRP6Util.calculateK(this.f13279e, this.f13275a, this.f13276b);
        this.f13281g = a();
        BigInteger mod = calculateK.multiply(this.f13277c).mod(this.f13275a).add(this.f13276b.modPow(this.f13281g, this.f13275a)).mod(this.f13275a);
        this.f13282h = mod;
        return mod;
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, Digest digest, SecureRandom secureRandom) {
        this.f13275a = bigInteger;
        this.f13276b = bigInteger2;
        this.f13277c = bigInteger3;
        this.f13278d = secureRandom;
        this.f13279e = digest;
    }

    public void init(SRP6GroupParameters sRP6GroupParameters, BigInteger bigInteger, Digest digest, SecureRandom secureRandom) {
        init(sRP6GroupParameters.getN(), sRP6GroupParameters.getG(), bigInteger, digest, secureRandom);
    }

    public boolean verifyClientEvidenceMessage(BigInteger bigInteger) {
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        BigInteger bigInteger4 = this.f13280f;
        if (bigInteger4 == null || (bigInteger2 = this.f13282h) == null || (bigInteger3 = this.f13284j) == null) {
            throw new CryptoException("Impossible to compute and verify M1: some data are missing from the previous operations (A,B,S)");
        }
        if (SRP6Util.calculateM1(this.f13279e, this.f13275a, bigInteger4, bigInteger2, bigInteger3).equals(bigInteger)) {
            this.f13285k = bigInteger;
            return true;
        }
        return false;
    }
}
