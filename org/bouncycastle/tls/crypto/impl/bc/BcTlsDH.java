package org.bouncycastle.tls.crypto.impl.bc;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.DHPrivateKeyParameters;
import org.bouncycastle.crypto.params.DHPublicKeyParameters;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsSecret;
/* loaded from: classes4.dex */
public class BcTlsDH implements TlsAgreement {

    /* renamed from: a  reason: collision with root package name */
    protected final BcTlsDHDomain f14970a;

    /* renamed from: b  reason: collision with root package name */
    protected AsymmetricCipherKeyPair f14971b;

    /* renamed from: c  reason: collision with root package name */
    protected DHPublicKeyParameters f14972c;

    public BcTlsDH(BcTlsDHDomain bcTlsDHDomain) {
        this.f14970a = bcTlsDHDomain;
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public TlsSecret calculateSecret() {
        return this.f14970a.calculateDHAgreement((DHPrivateKeyParameters) this.f14971b.getPrivate(), this.f14972c);
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public byte[] generateEphemeral() {
        AsymmetricCipherKeyPair generateKeyPair = this.f14970a.generateKeyPair();
        this.f14971b = generateKeyPair;
        return this.f14970a.encodePublicKey((DHPublicKeyParameters) generateKeyPair.getPublic());
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public void receivePeerValue(byte[] bArr) {
        this.f14972c = this.f14970a.decodePublicKey(bArr);
    }
}
