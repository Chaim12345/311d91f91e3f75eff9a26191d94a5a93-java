package org.bouncycastle.tls;
/* loaded from: classes4.dex */
public interface TlsAuthentication {
    TlsCredentials getClientCredentials(CertificateRequest certificateRequest);

    void notifyServerCertificate(TlsServerCertificate tlsServerCertificate);
}
