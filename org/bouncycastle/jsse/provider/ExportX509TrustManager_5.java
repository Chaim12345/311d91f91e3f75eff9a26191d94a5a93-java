package org.bouncycastle.jsse.provider;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
import org.bouncycastle.jsse.BCX509ExtendedTrustManager;
/* loaded from: classes3.dex */
class ExportX509TrustManager_5 implements X509TrustManager, ExportX509TrustManager {

    /* renamed from: a  reason: collision with root package name */
    final BCX509ExtendedTrustManager f13878a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExportX509TrustManager_5(BCX509ExtendedTrustManager bCX509ExtendedTrustManager) {
        this.f13878a = bCX509ExtendedTrustManager;
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        this.f13878a.checkClientTrusted(x509CertificateArr, str);
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        this.f13878a.checkServerTrusted(x509CertificateArr, str);
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        return this.f13878a.getAcceptedIssuers();
    }

    @Override // org.bouncycastle.jsse.provider.ExportX509TrustManager
    public BCX509ExtendedTrustManager unwrap() {
        return this.f13878a;
    }
}
