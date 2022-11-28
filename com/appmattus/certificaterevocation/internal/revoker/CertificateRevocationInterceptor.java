package com.appmattus.certificaterevocation.internal.revoker;

import com.appmattus.certificaterevocation.CRLogger;
import com.appmattus.certificaterevocation.RevocationResult;
import com.appmattus.certificatetransparency.chaincleaner.CertificateChainCleanerFactory;
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
public final class CertificateRevocationInterceptor extends CertificateRevocationBase implements Interceptor {
    private final boolean failOnError;
    @Nullable
    private final CRLogger logger;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateRevocationInterceptor(@NotNull Set<CrlItem> crlSet, @Nullable CertificateChainCleanerFactory certificateChainCleanerFactory, @Nullable X509TrustManager x509TrustManager, boolean z, @Nullable CRLogger cRLogger) {
        super(crlSet, certificateChainCleanerFactory, x509TrustManager);
        Intrinsics.checkNotNullParameter(crlSet, "crlSet");
        this.failOnError = z;
        this.logger = cRLogger;
    }

    public /* synthetic */ CertificateRevocationInterceptor(Set set, CertificateChainCleanerFactory certificateChainCleanerFactory, X509TrustManager x509TrustManager, boolean z, CRLogger cRLogger, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(set, (i2 & 2) != 0 ? null : certificateChainCleanerFactory, x509TrustManager, (i2 & 8) != 0 ? true : z, (i2 & 16) != 0 ? null : cRLogger);
    }

    @Override // okhttp3.Interceptor
    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) {
        List<Certificate> emptyList;
        Handshake handshake;
        Intrinsics.checkNotNullParameter(chain, "chain");
        String host = chain.request().url().host();
        Connection connection = chain.connection();
        if (connection == null || (handshake = connection.handshake()) == null || (emptyList = handshake.peerCertificates()) == null) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
        }
        Connection connection2 = chain.connection();
        RevocationResult verifyCertificateRevocation = (connection2 != null ? connection2.socket() : null) instanceof SSLSocket ? verifyCertificateRevocation(host, emptyList) : RevocationResult.Success.InsecureConnection.INSTANCE;
        CRLogger cRLogger = this.logger;
        if (cRLogger != null) {
            cRLogger.log(host, verifyCertificateRevocation);
        }
        if ((verifyCertificateRevocation instanceof RevocationResult.Failure) && this.failOnError) {
            throw new SSLPeerUnverifiedException("Certificate revocation failed");
        }
        return chain.proceed(chain.request());
    }
}
