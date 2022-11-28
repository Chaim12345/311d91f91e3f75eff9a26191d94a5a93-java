package org.bouncycastle.tls.crypto.impl.bc;

import java.util.Objects;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.crypto.TlsSigner;
import org.bouncycastle.tls.crypto.TlsStreamSigner;
/* loaded from: classes4.dex */
public abstract class BcTlsSigner implements TlsSigner {

    /* renamed from: a  reason: collision with root package name */
    protected final BcTlsCrypto f14984a;

    /* renamed from: b  reason: collision with root package name */
    protected final AsymmetricKeyParameter f14985b;

    /* JADX INFO: Access modifiers changed from: protected */
    public BcTlsSigner(BcTlsCrypto bcTlsCrypto, AsymmetricKeyParameter asymmetricKeyParameter) {
        Objects.requireNonNull(bcTlsCrypto, "'crypto' cannot be null");
        Objects.requireNonNull(asymmetricKeyParameter, "'privateKey' cannot be null");
        if (!asymmetricKeyParameter.isPrivate()) {
            throw new IllegalArgumentException("'privateKey' must be private");
        }
        this.f14984a = bcTlsCrypto;
        this.f14985b = asymmetricKeyParameter;
    }

    @Override // org.bouncycastle.tls.crypto.TlsSigner
    public TlsStreamSigner getStreamSigner(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        return null;
    }
}
