package org.bouncycastle.tls.crypto.impl.jcajce;

import java.security.KeyPair;
import java.security.PublicKey;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsSecret;
/* loaded from: classes4.dex */
public class JceX448 implements TlsAgreement {

    /* renamed from: a  reason: collision with root package name */
    protected final JceX448Domain f15044a;

    /* renamed from: b  reason: collision with root package name */
    protected KeyPair f15045b;

    /* renamed from: c  reason: collision with root package name */
    protected PublicKey f15046c;

    public JceX448(JceX448Domain jceX448Domain) {
        this.f15044a = jceX448Domain;
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public TlsSecret calculateSecret() {
        return this.f15044a.calculateECDHAgreement(this.f15045b.getPrivate(), this.f15046c);
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public byte[] generateEphemeral() {
        KeyPair generateKeyPair = this.f15044a.generateKeyPair();
        this.f15045b = generateKeyPair;
        return this.f15044a.encodePublicKey(generateKeyPair.getPublic());
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public void receivePeerValue(byte[] bArr) {
        this.f15046c = this.f15044a.decodePublicKey(bArr);
    }
}
