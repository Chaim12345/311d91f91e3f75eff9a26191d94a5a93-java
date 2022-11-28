package com.appmattus.certificatetransparency.internal.verifier;

import com.appmattus.certificatetransparency.CTLogger;
import com.appmattus.certificatetransparency.CTPolicy;
import com.appmattus.certificatetransparency.VerificationResult;
import com.appmattus.certificatetransparency.cache.DiskCache;
import com.appmattus.certificatetransparency.chaincleaner.CertificateChainCleanerFactory;
import com.appmattus.certificatetransparency.datasource.DataSource;
import com.appmattus.certificatetransparency.internal.verifier.model.Host;
import com.appmattus.certificatetransparency.loglist.LogListResult;
import com.appmattus.certificatetransparency.loglist.LogListService;
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
public final class CertificateTransparencyHostnameVerifier extends CertificateTransparencyBase implements HostnameVerifier {
    @NotNull
    private final HostnameVerifier delegate;
    private final boolean failOnError;
    @Nullable
    private final CTLogger logger;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateTransparencyHostnameVerifier(@NotNull HostnameVerifier delegate, @NotNull Set<Host> includeHosts, @NotNull Set<Host> excludeHosts, @Nullable CertificateChainCleanerFactory certificateChainCleanerFactory, @Nullable X509TrustManager x509TrustManager, @Nullable LogListService logListService, @Nullable DataSource<LogListResult> dataSource, @Nullable CTPolicy cTPolicy, @Nullable DiskCache diskCache, boolean z, @Nullable CTLogger cTLogger) {
        super(includeHosts, excludeHosts, certificateChainCleanerFactory, x509TrustManager, logListService, dataSource, cTPolicy, diskCache);
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(includeHosts, "includeHosts");
        Intrinsics.checkNotNullParameter(excludeHosts, "excludeHosts");
        this.delegate = delegate;
        this.failOnError = z;
        this.logger = cTLogger;
    }

    public /* synthetic */ CertificateTransparencyHostnameVerifier(HostnameVerifier hostnameVerifier, Set set, Set set2, CertificateChainCleanerFactory certificateChainCleanerFactory, X509TrustManager x509TrustManager, LogListService logListService, DataSource dataSource, CTPolicy cTPolicy, DiskCache diskCache, boolean z, CTLogger cTLogger, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(hostnameVerifier, set, set2, certificateChainCleanerFactory, x509TrustManager, logListService, dataSource, cTPolicy, diskCache, (i2 & 512) != 0 ? true : z, (i2 & 1024) != 0 ? null : cTLogger);
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
            VerificationResult verifyCertificateTransparency = verifyCertificateTransparency(host, list);
            CTLogger cTLogger = this.logger;
            if (cTLogger != null) {
                cTLogger.log(host, verifyCertificateTransparency);
            }
            return ((verifyCertificateTransparency instanceof VerificationResult.Failure) && this.failOnError) ? false : true;
        }
        return false;
    }
}
