package org.bouncycastle.tls.crypto.impl.bc;

import java.util.Objects;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.tls.DigitallySigned;
import org.bouncycastle.tls.crypto.TlsStreamVerifier;
import org.bouncycastle.tls.crypto.TlsVerifier;
/* loaded from: classes4.dex */
public abstract class BcTlsVerifier implements TlsVerifier {

    /* renamed from: a  reason: collision with root package name */
    protected final BcTlsCrypto f14986a;

    /* renamed from: b  reason: collision with root package name */
    protected final AsymmetricKeyParameter f14987b;

    /* JADX INFO: Access modifiers changed from: protected */
    public BcTlsVerifier(BcTlsCrypto bcTlsCrypto, AsymmetricKeyParameter asymmetricKeyParameter) {
        Objects.requireNonNull(bcTlsCrypto, "'crypto' cannot be null");
        Objects.requireNonNull(asymmetricKeyParameter, "'publicKey' cannot be null");
        if (asymmetricKeyParameter.isPrivate()) {
            throw new IllegalArgumentException("'publicKey' must be public");
        }
        this.f14986a = bcTlsCrypto;
        this.f14987b = asymmetricKeyParameter;
    }

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public TlsStreamVerifier getStreamVerifier(DigitallySigned digitallySigned) {
        return null;
    }
}
