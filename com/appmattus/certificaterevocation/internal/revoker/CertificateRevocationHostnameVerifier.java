package com.appmattus.certificaterevocation.internal.revoker;

import com.appmattus.certificaterevocation.CRLogger;
import com.appmattus.certificaterevocation.RevocationResult;
import com.appmattus.certificatetransparency.chaincleaner.CertificateChainCleanerFactory;
import java.security.cert.Certificate;
import java.util.List;
import java.util.Set;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class CertificateRevocationHostnameVerifier extends CertificateRevocationBase implements HostnameVerifier {
    @NotNull
    private final HostnameVerifier delegate;
    private final boolean failOnError;
    @Nullable
    private final CRLogger logger;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateRevocationHostnameVerifier(@NotNull HostnameVerifier delegate, @NotNull Set<CrlItem> crlSet, @Nullable CertificateChainCleanerFactory certificateChainCleanerFactory, @Nullable X509TrustManager x509TrustManager, boolean z, @Nullable CRLogger cRLogger) {
        super(crlSet, certificateChainCleanerFactory, x509TrustManager);
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(crlSet, "crlSet");
        this.delegate = delegate;
        this.failOnError = z;
        this.logger = cRLogger;
    }

    public /* synthetic */ CertificateRevocationHostnameVerifier(HostnameVerifier hostnameVerifier, Set set, CertificateChainCleanerFactory certificateChainCleanerFactory, X509TrustManager x509TrustManager, boolean z, CRLogger cRLogger, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(hostnameVerifier, set, (i2 & 4) != 0 ? null : certificateChainCleanerFactory, x509TrustManager, (i2 & 16) != 0 ? true : z, (i2 & 32) != 0 ? null : cRLogger);
    }

    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(@NotNull String host, @NotNull SSLSession sslSession) {
        List<? extends Certificate> list;
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(sslSession, "sslSession");
        if (this.delegate.verify(host, sslSession)) {
            Certificate[] peerCertificates = sslSession.getPeerCertificates();
            Intrinsics.checkNotNullExpressionValue(peerCertificates, "sslSession.peerCertificates");
            list = ArraysKt___ArraysKt.toList(peerCertificates);
            RevocationResult verifyCertificateRevocation = verifyCertificateRevocation(host, list);
            CRLogger cRLogger = this.logger;
            if (cRLogger != null) {
                cRLogger.log(host, verifyCertificateRevocation);
            }
            return ((verifyCertificateRevocation instanceof RevocationResult.Failure) && this.failOnError) ? false : true;
        }
        return false;
    }
}
