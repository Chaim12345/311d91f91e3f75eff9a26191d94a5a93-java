package org.bouncycastle.tls.crypto.impl.jcajce;

import java.security.KeyPair;
import java.security.PublicKey;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsSecret;
/* loaded from: classes4.dex */
public class JceTlsECDH implements TlsAgreement {

    /* renamed from: a  reason: collision with root package name */
    protected final JceTlsECDomain f15033a;

    /* renamed from: b  reason: collision with root package name */
    protected KeyPair f15034b;

    /* renamed from: c  reason: collision with root package name */
    protected PublicKey f15035c;

    public JceTlsECDH(JceTlsECDomain jceTlsECDomain) {
        this.f15033a = jceTlsECDomain;
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public TlsSecret calculateSecret() {
        return this.f15033a.calculateECDHAgreement(this.f15034b.getPrivate(), this.f15035c);
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public byte[] generateEphemeral() {
        KeyPair generateKeyPair = this.f15033a.generateKeyPair();
        this.f15034b = generateKeyPair;
        return this.f15033a.encodePublicKey(generateKeyPair.getPublic());
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public void receivePeerValue(byte[] bArr) {
        this.f15035c = this.f15033a.decodePublicKey(bArr);
    }
}
