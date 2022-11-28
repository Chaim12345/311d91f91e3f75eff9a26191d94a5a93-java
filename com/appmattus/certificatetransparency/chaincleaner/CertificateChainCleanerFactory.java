package com.appmattus.certificatetransparency.chaincleaner;

import javax.net.ssl.X509TrustManager;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public interface CertificateChainCleanerFactory {
    @NotNull
    CertificateChainCleaner get(@NotNull X509TrustManager x509TrustManager);
}
