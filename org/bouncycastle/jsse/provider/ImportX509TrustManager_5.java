package org.bouncycastle.jsse.provider;

import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509TrustManager;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jsse.BCX509ExtendedTrustManager;
import org.bouncycastle.tls.TlsUtils;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ImportX509TrustManager_5 extends BCX509ExtendedTrustManager implements ImportX509TrustManager {

    /* renamed from: a  reason: collision with root package name */
    final boolean f13884a;

    /* renamed from: b  reason: collision with root package name */
    final JcaJceHelper f13885b;

    /* renamed from: c  reason: collision with root package name */
    final X509TrustManager f13886c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImportX509TrustManager_5(boolean z, JcaJceHelper jcaJceHelper, X509TrustManager x509TrustManager) {
        this.f13884a = z;
        this.f13885b = jcaJceHelper;
        this.f13886c = x509TrustManager;
    }

    private void checkAdditionalTrust(X509Certificate[] x509CertificateArr, String str, TransportData transportData, boolean z) {
        checkAlgorithmConstraints(x509CertificateArr, str, transportData, z);
        ProvX509TrustManager.b(x509CertificateArr, transportData, z);
    }

    private void checkAlgorithmConstraints(X509Certificate[] x509CertificateArr, String str, TransportData transportData, boolean z) {
        try {
            ProvAlgorithmChecker.b(this.f13884a, this.f13885b, TransportData.c(transportData, false), getTrustedCerts(), x509CertificateArr, ProvX509TrustManager.d(z), ProvX509TrustManager.e(z, str));
        } catch (GeneralSecurityException e2) {
            throw new CertificateException("Certificates do not conform to algorithm constraints", e2);
        }
    }

    private static X509Certificate[] checkChain(X509Certificate[] x509CertificateArr) {
        if (TlsUtils.isNullOrEmpty(x509CertificateArr)) {
            throw new IllegalArgumentException("'chain' must be a chain of at least one certificate");
        }
        return x509CertificateArr;
    }

    private static X509Certificate[] copyChain(X509Certificate[] x509CertificateArr) {
        return (X509Certificate[]) checkChain(x509CertificateArr).clone();
    }

    private Set<X509Certificate> getTrustedCerts() {
        X509Certificate[] acceptedIssuers = getAcceptedIssuers();
        if (TlsUtils.isNullOrEmpty(acceptedIssuers)) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet();
        for (X509Certificate x509Certificate : acceptedIssuers) {
            if (x509Certificate != null) {
                hashSet.add(x509Certificate);
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        this.f13886c.checkClientTrusted(copyChain(x509CertificateArr), str);
        checkAdditionalTrust(x509CertificateArr, str, null, false);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedTrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) {
        this.f13886c.checkClientTrusted(copyChain(x509CertificateArr), str);
        checkAdditionalTrust(x509CertificateArr, str, TransportData.a(socket), false);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedTrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) {
        this.f13886c.checkClientTrusted(copyChain(x509CertificateArr), str);
        checkAdditionalTrust(x509CertificateArr, str, TransportData.b(sSLEngine), false);
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        this.f13886c.checkServerTrusted(copyChain(x509CertificateArr), str);
        checkAdditionalTrust(x509CertificateArr, str, null, true);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedTrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) {
        this.f13886c.checkServerTrusted(copyChain(x509CertificateArr), str);
        checkAdditionalTrust(x509CertificateArr, str, TransportData.a(socket), true);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedTrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) {
        this.f13886c.checkServerTrusted(copyChain(x509CertificateArr), str);
        checkAdditionalTrust(x509CertificateArr, str, TransportData.b(sSLEngine), true);
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        return this.f13886c.getAcceptedIssuers();
    }

    @Override // org.bouncycastle.jsse.provider.ImportX509TrustManager
    public X509TrustManager unwrap() {
        return this.f13886c;
    }
}
