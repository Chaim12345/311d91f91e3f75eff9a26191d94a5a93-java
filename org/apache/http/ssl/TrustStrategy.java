package org.apache.http.ssl;

import java.security.cert.X509Certificate;
/* loaded from: classes3.dex */
public interface TrustStrategy {
    boolean isTrusted(X509Certificate[] x509CertificateArr, String str);
}
