package org.bouncycastle.tls.crypto.impl;

import org.bouncycastle.tls.crypto.TlsCrypto;
import org.bouncycastle.tls.crypto.TlsSecret;
/* loaded from: classes4.dex */
public abstract class AbstractTlsCrypto implements TlsCrypto {
    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsSecret adoptSecret(TlsSecret tlsSecret) {
        if (tlsSecret instanceof AbstractTlsSecret) {
            return createSecret(((AbstractTlsSecret) tlsSecret).b());
        }
        throw new IllegalArgumentException("unrecognized TlsSecret - cannot copy data: " + tlsSecret.getClass().getName());
    }
}
