package org.bouncycastle.crypto.params;

import java.math.BigInteger;
/* loaded from: classes3.dex */
public class NaccacheSternKeyParameters extends AsymmetricKeyParameter {

    /* renamed from: b  reason: collision with root package name */
    int f13498b;

    /* renamed from: g  reason: collision with root package name */
    private BigInteger f13499g;

    /* renamed from: n  reason: collision with root package name */
    private BigInteger f13500n;

    public NaccacheSternKeyParameters(boolean z, BigInteger bigInteger, BigInteger bigInteger2, int i2) {
        super(z);
        this.f13499g = bigInteger;
        this.f13500n = bigInteger2;
        this.f13498b = i2;
    }

    public BigInteger getG() {
        return this.f13499g;
    }

    public int getLowerSigmaBound() {
        return this.f13498b;
    }

    public BigInteger getModulus() {
        return this.f13500n;
    }
}
