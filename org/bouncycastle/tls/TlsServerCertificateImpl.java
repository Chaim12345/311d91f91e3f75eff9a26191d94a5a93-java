package org.bouncycastle.tls;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class TlsServerCertificateImpl implements TlsServerCertificate {

    /* renamed from: a  reason: collision with root package name */
    protected Certificate f14898a;

    /* renamed from: b  reason: collision with root package name */
    protected CertificateStatus f14899b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TlsServerCertificateImpl(Certificate certificate, CertificateStatus certificateStatus) {
        this.f14898a = certificate;
        this.f14899b = certificateStatus;
    }

    @Override // org.bouncycastle.tls.TlsServerCertificate
    public Certificate getCertificate() {
        return this.f14898a;
    }

    @Override // org.bouncycastle.tls.TlsServerCertificate
    public CertificateStatus getCertificateStatus() {
        return this.f14899b;
    }
}
