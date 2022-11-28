package org.bouncycastle.tls.crypto.impl.bc;

import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsECDomain;
/* loaded from: classes4.dex */
public class BcX448Domain implements TlsECDomain {

    /* renamed from: a  reason: collision with root package name */
    protected final BcTlsCrypto f14995a;

    public BcX448Domain(BcTlsCrypto bcTlsCrypto) {
        this.f14995a = bcTlsCrypto;
    }

    @Override // org.bouncycastle.tls.crypto.TlsECDomain
    public TlsAgreement createECDH() {
        return new BcX448(this.f14995a);
    }
}
