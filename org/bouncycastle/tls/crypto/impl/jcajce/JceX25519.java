package org.bouncycastle.tls.crypto.impl.jcajce;

import java.security.KeyPair;
import java.security.PublicKey;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsSecret;
/* loaded from: classes4.dex */
public class JceX25519 implements TlsAgreement {

    /* renamed from: a  reason: collision with root package name */
    protected final JceX25519Domain f15040a;

    /* renamed from: b  reason: collision with root package name */
    protected KeyPair f15041b;

    /* renamed from: c  reason: collision with root package name */
    protected PublicKey f15042c;

    public JceX25519(JceX25519Domain jceX25519Domain) {
        this.f15040a = jceX25519Domain;
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public TlsSecret calculateSecret() {
        return this.f15040a.calculateECDHAgreement(this.f15041b.getPrivate(), this.f15042c);
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public byte[] generateEphemeral() {
        KeyPair generateKeyPair = this.f15040a.generateKeyPair();
        this.f15041b = generateKeyPair;
        return this.f15040a.encodePublicKey(generateKeyPair.getPublic());
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public void receivePeerValue(byte[] bArr) {
        this.f15042c = this.f15040a.decodePublicKey(bArr);
    }
}
