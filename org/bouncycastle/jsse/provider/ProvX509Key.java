package org.bouncycastle.jsse.provider;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Objects;
import javax.net.ssl.X509KeyManager;
import org.bouncycastle.jsse.BCX509Key;
import org.bouncycastle.tls.TlsUtils;
/* loaded from: classes3.dex */
class ProvX509Key implements BCX509Key {
    private final X509Certificate[] certificateChain;
    private final String keyType;
    private final PrivateKey privateKey;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvX509Key(String str, PrivateKey privateKey, X509Certificate[] x509CertificateArr) {
        this.keyType = str;
        this.privateKey = privateKey;
        this.certificateChain = x509CertificateArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProvX509Key a(X509KeyManager x509KeyManager, String str, String str2) {
        PrivateKey privateKey;
        Objects.requireNonNull(x509KeyManager, "'x509KeyManager' cannot be null");
        if (str == null || str2 == null || (privateKey = x509KeyManager.getPrivateKey(str2)) == null) {
            return null;
        }
        X509Certificate[] certificateChain = x509KeyManager.getCertificateChain(str2);
        if (TlsUtils.isNullOrEmpty(certificateChain)) {
            return null;
        }
        X509Certificate[] x509CertificateArr = (X509Certificate[]) certificateChain.clone();
        if (JsseUtils.f(x509CertificateArr)) {
            return null;
        }
        return new ProvX509Key(str, privateKey, x509CertificateArr);
    }

    @Override // org.bouncycastle.jsse.BCX509Key
    public X509Certificate[] getCertificateChain() {
        return (X509Certificate[]) this.certificateChain.clone();
    }

    @Override // org.bouncycastle.jsse.BCX509Key
    public String getKeyType() {
        return this.keyType;
    }

    @Override // org.bouncycastle.jsse.BCX509Key
    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }
}
