package org.bouncycastle.tls;

import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.TlsSigner;
import org.bouncycastle.tls.crypto.TlsStreamSigner;
import org.bouncycastle.tls.crypto.impl.TlsImplUtils;
/* loaded from: classes4.dex */
public class DefaultTlsCredentialedSigner implements TlsCredentialedSigner {

    /* renamed from: a  reason: collision with root package name */
    protected TlsCryptoParameters f14751a;

    /* renamed from: b  reason: collision with root package name */
    protected Certificate f14752b;

    /* renamed from: c  reason: collision with root package name */
    protected SignatureAndHashAlgorithm f14753c;

    /* renamed from: d  reason: collision with root package name */
    protected TlsSigner f14754d;

    public DefaultTlsCredentialedSigner(TlsCryptoParameters tlsCryptoParameters, TlsSigner tlsSigner, Certificate certificate, SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        if (certificate == null) {
            throw new IllegalArgumentException("'certificate' cannot be null");
        }
        if (certificate.isEmpty()) {
            throw new IllegalArgumentException("'certificate' cannot be empty");
        }
        if (tlsSigner == null) {
            throw new IllegalArgumentException("'signer' cannot be null");
        }
        this.f14754d = tlsSigner;
        this.f14751a = tlsCryptoParameters;
        this.f14752b = certificate;
        this.f14753c = signatureAndHashAlgorithm;
    }

    protected SignatureAndHashAlgorithm a() {
        if (TlsImplUtils.isTLSv12(this.f14751a)) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = getSignatureAndHashAlgorithm();
            if (signatureAndHashAlgorithm != null) {
                return signatureAndHashAlgorithm;
            }
            throw new IllegalStateException("'signatureAndHashAlgorithm' cannot be null for (D)TLS 1.2+");
        }
        return null;
    }

    @Override // org.bouncycastle.tls.TlsCredentialedSigner
    public byte[] generateRawSignature(byte[] bArr) {
        return this.f14754d.generateRawSignature(a(), bArr);
    }

    @Override // org.bouncycastle.tls.TlsCredentials
    public Certificate getCertificate() {
        return this.f14752b;
    }

    @Override // org.bouncycastle.tls.TlsCredentialedSigner
    public SignatureAndHashAlgorithm getSignatureAndHashAlgorithm() {
        return this.f14753c;
    }

    @Override // org.bouncycastle.tls.TlsCredentialedSigner
    public TlsStreamSigner getStreamSigner() {
        return this.f14754d.getStreamSigner(a());
    }
}
