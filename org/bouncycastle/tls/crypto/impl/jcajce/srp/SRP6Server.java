package org.bouncycastle.tls.crypto.impl.jcajce.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.tls.crypto.SRP6Group;
import org.bouncycastle.tls.crypto.TlsHash;
/* loaded from: classes4.dex */
public class SRP6Server {

    /* renamed from: a  reason: collision with root package name */
    protected BigInteger f15061a;

    /* renamed from: b  reason: collision with root package name */
    protected BigInteger f15062b;

    /* renamed from: c  reason: collision with root package name */
    protected BigInteger f15063c;

    /* renamed from: d  reason: collision with root package name */
    protected SecureRandom f15064d;

    /* renamed from: e  reason: collision with root package name */
    protected TlsHash f15065e;

    /* renamed from: f  reason: collision with root package name */
    protected BigInteger f15066f;

    /* renamed from: g  reason: collision with root package name */
    protected BigInteger f15067g;

    /* renamed from: h  reason: collision with root package name */
    protected BigInteger f15068h;

    /* renamed from: i  reason: collision with root package name */
    protected BigInteger f15069i;

    /* renamed from: j  reason: collision with root package name */
    protected BigInteger f15070j;

    /* renamed from: k  reason: collision with root package name */
    protected BigInteger f15071k;

    /* renamed from: l  reason: collision with root package name */
    protected BigInteger f15072l;

    /* renamed from: m  reason: collision with root package name */
    protected BigInteger f15073m;

    private BigInteger calculateS() {
        return this.f15063c.modPow(this.f15069i, this.f15061a).multiply(this.f15066f).mod(this.f15061a).modPow(this.f15067g, this.f15061a);
    }

    protected BigInteger a() {
        return SRP6Util.generatePrivateValue(this.f15061a, this.f15062b, this.f15064d);
    }

    public BigInteger calculateSecret(BigInteger bigInteger) {
        BigInteger validatePublicValue = SRP6Util.validatePublicValue(this.f15061a, bigInteger);
        this.f15066f = validatePublicValue;
        this.f15069i = SRP6Util.calculateU(this.f15065e, this.f15061a, validatePublicValue, this.f15068h);
        BigInteger calculateS = calculateS();
        this.f15070j = calculateS;
        return calculateS;
    }

    public BigInteger calculateServerEvidenceMessage() {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger bigInteger3 = this.f15066f;
        if (bigInteger3 == null || (bigInteger = this.f15071k) == null || (bigInteger2 = this.f15070j) == null) {
            throw new IllegalStateException("Impossible to compute M2: some data are missing from the previous operations (A,M1,S)");
        }
        BigInteger calculateM2 = SRP6Util.calculateM2(this.f15065e, this.f15061a, bigInteger3, bigInteger, bigInteger2);
        this.f15072l = calculateM2;
        return calculateM2;
    }

    public BigInteger calculateSessionKey() {
        BigInteger bigInteger = this.f15070j;
        if (bigInteger == null || this.f15071k == null || this.f15072l == null) {
            throw new IllegalStateException("Impossible to compute Key: some data are missing from the previous operations (S,M1,M2)");
        }
        BigInteger calculateKey = SRP6Util.calculateKey(this.f15065e, this.f15061a, bigInteger);
        this.f15073m = calculateKey;
        return calculateKey;
    }

    public BigInteger generateServerCredentials() {
        BigInteger calculateK = SRP6Util.calculateK(this.f15065e, this.f15061a, this.f15062b);
        this.f15067g = a();
        BigInteger mod = calculateK.multiply(this.f15063c).mod(this.f15061a).add(this.f15062b.modPow(this.f15067g, this.f15061a)).mod(this.f15061a);
        this.f15068h = mod;
        return mod;
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, TlsHash tlsHash, SecureRandom secureRandom) {
        this.f15061a = bigInteger;
        this.f15062b = bigInteger2;
        this.f15063c = bigInteger3;
        this.f15064d = secureRandom;
        this.f15065e = tlsHash;
    }

    public void init(SRP6Group sRP6Group, BigInteger bigInteger, TlsHash tlsHash, SecureRandom secureRandom) {
        init(sRP6Group.getN(), sRP6Group.getG(), bigInteger, tlsHash, secureRandom);
    }

    public boolean verifyClientEvidenceMessage(BigInteger bigInteger) {
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        BigInteger bigInteger4 = this.f15066f;
        if (bigInteger4 == null || (bigInteger2 = this.f15068h) == null || (bigInteger3 = this.f15070j) == null) {
            throw new IllegalStateException("Impossible to compute and verify M1: some data are missing from the previous operations (A,B,S)");
        }
        if (SRP6Util.calculateM1(this.f15065e, this.f15061a, bigInteger4, bigInteger2, bigInteger3).equals(bigInteger)) {
            this.f15071k = bigInteger;
            return true;
        }
        return false;
    }
}
