package org.bouncycastle.tls.crypto.impl.bc;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsSecret;
/* loaded from: classes4.dex */
public class BcTlsECDH implements TlsAgreement {

    /* renamed from: a  reason: collision with root package name */
    protected final BcTlsECDomain f14976a;

    /* renamed from: b  reason: collision with root package name */
    protected AsymmetricCipherKeyPair f14977b;

    /* renamed from: c  reason: collision with root package name */
    protected ECPublicKeyParameters f14978c;

    public BcTlsECDH(BcTlsECDomain bcTlsECDomain) {
        this.f14976a = bcTlsECDomain;
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public TlsSecret calculateSecret() {
        return this.f14976a.calculateECDHAgreement((ECPrivateKeyParameters) this.f14977b.getPrivate(), this.f14978c);
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public byte[] generateEphemeral() {
        AsymmetricCipherKeyPair generateKeyPair = this.f14976a.generateKeyPair();
        this.f14977b = generateKeyPair;
        return this.f14976a.encodePublicKey((ECPublicKeyParameters) generateKeyPair.getPublic());
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public void receivePeerValue(byte[] bArr) {
        this.f14978c = this.f14976a.decodePublicKey(bArr);
    }
}
