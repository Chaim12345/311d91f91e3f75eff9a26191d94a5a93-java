package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes3.dex */
public class ElGamalParameters implements CipherParameters {

    /* renamed from: g  reason: collision with root package name */
    private BigInteger f13484g;

    /* renamed from: l  reason: collision with root package name */
    private int f13485l;

    /* renamed from: p  reason: collision with root package name */
    private BigInteger f13486p;

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, 0);
    }

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2, int i2) {
        this.f13484g = bigInteger2;
        this.f13486p = bigInteger;
        this.f13485l = i2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ElGamalParameters) {
            ElGamalParameters elGamalParameters = (ElGamalParameters) obj;
            return elGamalParameters.getP().equals(this.f13486p) && elGamalParameters.getG().equals(this.f13484g) && elGamalParameters.getL() == this.f13485l;
        }
        return false;
    }

    public BigInteger getG() {
        return this.f13484g;
    }

    public int getL() {
        return this.f13485l;
    }

    public BigInteger getP() {
        return this.f13486p;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG().hashCode()) + this.f13485l;
    }
}
