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
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Connection;
import okhttp3.Handshake;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class CertificateTransparencyInterceptor extends CertificateTransparencyBase implements Interceptor {
    private final boolean failOnError;
    @Nullable
    private final CTLogger logger;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateTransparencyInterceptor(@NotNull Set<Host> includeHosts, @NotNull Set<Host> excludeHosts, @Nullable CertificateChainCleanerFactory certificateChainCleanerFactory, @Nullable X509TrustManager x509TrustManager, @Nullable LogListService logListService, @Nullable DataSource<LogListResult> dataSource, @Nullable CTPolicy cTPolicy, @Nullable DiskCache diskCache, boolean z, @Nullable CTLogger cTLogger) {
        super(includeHosts, excludeHosts, certificateChainCleanerFactory, x509TrustManager, logListService, dataSource, cTPolicy, diskCache);
        Intrinsics.checkNotNullParameter(includeHosts, "includeHosts");
        Intrinsics.checkNotNullParameter(excludeHosts, "excludeHosts");
        this.failOnError = z;
        this.logger = cTLogger;
    }

    public /* synthetic */ CertificateTransparencyInterceptor(Set set, Set set2, CertificateChainCleanerFactory certificateChainCleanerFactory, X509TrustManager x509TrustManager, LogListService logListService, DataSource dataSource, CTPolicy cTPolicy, DiskCache diskCache, boolean z, CTLogger cTLogger, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(set, set2, certificateChainCleanerFactory, x509TrustManager, logListService, dataSource, cTPolicy, (i2 & 128) != 0 ? null : diskCache, (i2 & 256) != 0 ? true : z, (i2 & 512) != 0 ? null : cTLogger);
    }

    @Override // okhttp3.Interceptor
    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) {
        List<Certificate> emptyList;
        Intrinsics.checkNotNullParameter(chain, "chain");
        String host = chain.request().url().host();
        Connection connection = chain.connection();
        if (connection != null) {
            Handshake handshake = connection.handshake();
            if (handshake == null || (emptyList = handshake.peerCertificates()) == null) {
                emptyList = CollectionsKt__CollectionsKt.emptyList();
            }
            VerificationResult verifyCertificateTransparency = connection.socket() instanceof SSLSocket ? verifyCertificateTransparency(host, emptyList) : new VerificationResult.Success.InsecureConnection(host);
            CTLogger cTLogger = this.logger;
            if (cTLogger != null) {
                cTLogger.log(host, verifyCertificateTransparency);
            }
            if ((verifyCertificateTransparency instanceof VerificationResult.Failure) && this.failOnError) {
                throw new SSLPeerUnverifiedException("Certificate transparency failed");
            }
            return chain.proceed(chain.request());
        }
        throw new IllegalStateException("No connection found. Verify interceptor is added using addNetworkInterceptor");
    }
}
