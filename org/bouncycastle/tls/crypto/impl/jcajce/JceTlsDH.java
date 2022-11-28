package org.bouncycastle.tls.crypto.impl.jcajce;

import java.security.KeyPair;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsSecret;
/* loaded from: classes4.dex */
public class JceTlsDH implements TlsAgreement {

    /* renamed from: a  reason: collision with root package name */
    protected final JceTlsDHDomain f15027a;

    /* renamed from: b  reason: collision with root package name */
    protected KeyPair f15028b;

    /* renamed from: c  reason: collision with root package name */
    protected DHPublicKey f15029c;

    public JceTlsDH(JceTlsDHDomain jceTlsDHDomain) {
        this.f15027a = jceTlsDHDomain;
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public TlsSecret calculateSecret() {
        return this.f15027a.calculateDHAgreement((DHPrivateKey) this.f15028b.getPrivate(), this.f15029c);
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public byte[] generateEphemeral() {
        KeyPair generateKeyPair = this.f15027a.generateKeyPair();
        this.f15028b = generateKeyPair;
        return this.f15027a.encodePublicKey((DHPublicKey) generateKeyPair.getPublic());
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public void receivePeerValue(byte[] bArr) {
        this.f15029c = this.f15027a.decodePublicKey(bArr);
    }
}
