package org.bouncycastle.tls.crypto.impl.bc;

import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsECDomain;
/* loaded from: classes4.dex */
public class BcX25519Domain implements TlsECDomain {

    /* renamed from: a  reason: collision with root package name */
    protected final BcTlsCrypto f14991a;

    public BcX25519Domain(BcTlsCrypto bcTlsCrypto) {
        this.f14991a = bcTlsCrypto;
    }

    @Override // org.bouncycastle.tls.crypto.TlsECDomain
    public TlsAgreement createECDH() {
        return new BcX25519(this.f14991a);
    }
}
