package org.bouncycastle.crypto.params;

import org.bouncycastle.math.ec.ECPoint;
/* loaded from: classes3.dex */
public class ECPublicKeyParameters extends ECKeyParameters {

    /* renamed from: q  reason: collision with root package name */
    private final ECPoint f13483q;

    public ECPublicKeyParameters(ECPoint eCPoint, ECDomainParameters eCDomainParameters) {
        super(false, eCDomainParameters);
        this.f13483q = eCDomainParameters.validatePublicPoint(eCPoint);
    }

    public ECPoint getQ() {
        return this.f13483q;
    }
}
