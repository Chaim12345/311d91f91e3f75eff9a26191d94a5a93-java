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
import java.lang.reflect.Method;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.net.ssl.X509TrustManager;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class CertificateTransparencyTrustManager extends CertificateTransparencyBase implements X509TrustManager {
    @Nullable
    private final Method checkServerTrustedMethod;
    @NotNull
    private final X509TrustManager delegate;
    private final boolean failOnError;
    @Nullable
    private final Method isSameTrustConfigurationMethod;
    @Nullable
    private final CTLogger logger;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateTransparencyTrustManager(@NotNull X509TrustManager delegate, @NotNull Set<Host> includeHosts, @NotNull Set<Host> excludeHosts, @Nullable CertificateChainCleanerFactory certificateChainCleanerFactory, @Nullable LogListService logListService, @Nullable DataSource<LogListResult> dataSource, @Nullable CTPolicy cTPolicy, @Nullable DiskCache diskCache, boolean z, @Nullable CTLogger cTLogger) {
        super(includeHosts, excludeHosts, certificateChainCleanerFactory, delegate, logListService, dataSource, cTPolicy, diskCache);
        Method method;
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(includeHosts, "includeHosts");
        Intrinsics.checkNotNullParameter(excludeHosts, "excludeHosts");
        this.delegate = delegate;
        this.failOnError = z;
        this.logger = cTLogger;
        Method method2 = null;
        try {
            method = delegate.getClass().getDeclaredMethod("checkServerTrusted", X509Certificate[].class, String.class, String.class);
        } catch (NoSuchMethodException unused) {
            method = null;
        }
        this.checkServerTrustedMethod = method;
        try {
            method2 = this.delegate.getClass().getDeclaredMethod("isSameTrustConfiguration", String.class, String.class);
        } catch (NoSuchMethodException unused2) {
        }
        this.isSameTrustConfigurationMethod = method2;
    }

    public /* synthetic */ CertificateTransparencyTrustManager(X509TrustManager x509TrustManager, Set set, Set set2, CertificateChainCleanerFactory certificateChainCleanerFactory, LogListService logListService, DataSource dataSource, CTPolicy cTPolicy, DiskCache diskCache, boolean z, CTLogger cTLogger, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(x509TrustManager, set, set2, certificateChainCleanerFactory, logListService, dataSource, cTPolicy, diskCache, (i2 & 256) != 0 ? true : z, (i2 & 512) != 0 ? null : cTLogger);
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(@NotNull X509Certificate[] chain, @NotNull String authType) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(authType, "authType");
        this.delegate.checkClientTrusted(chain, authType);
    }

    @NotNull
    public final List<X509Certificate> checkServerTrusted(@NotNull X509Certificate[] chain, @NotNull String authType, @NotNull String host) {
        List<? extends Certificate> list;
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(authType, "authType");
        Intrinsics.checkNotNullParameter(host, "host");
        Method method = this.checkServerTrustedMethod;
        Intrinsics.checkNotNull(method);
        Object invoke = method.invoke(this.delegate, chain, authType, host);
        Objects.requireNonNull(invoke, "null cannot be cast to non-null type kotlin.collections.List<java.security.cert.X509Certificate>");
        List<X509Certificate> list2 = (List) invoke;
        list = CollectionsKt___CollectionsKt.toList(list2);
        VerificationResult verifyCertificateTransparency = verifyCertificateTransparency(host, list);
        CTLogger cTLogger = this.logger;
        if (cTLogger != null) {
            cTLogger.log(host, verifyCertificateTransparency);
        }
        if ((verifyCertificateTransparency instanceof VerificationResult.Failure) && this.failOnError) {
            throw new CertificateException("Certificate transparency failed");
        }
        return list2;
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(@NotNull X509Certificate[] chain, @NotNull String authType) {
        Object first;
        List<? extends Certificate> list;
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(authType, "authType");
        this.delegate.checkServerTrusted(chain, authType);
        first = ArraysKt___ArraysKt.first(chain);
        String obj = new X500Name(((X509Certificate) first).getSubjectX500Principal().getName()).getRDNs(BCStyle.CN)[0].getFirst().getValue().toString();
        list = ArraysKt___ArraysKt.toList(chain);
        VerificationResult verifyCertificateTransparency = verifyCertificateTransparency(obj, list);
        CTLogger cTLogger = this.logger;
        if (cTLogger != null) {
            cTLogger.log(obj, verifyCertificateTransparency);
        }
        if ((verifyCertificateTransparency instanceof VerificationResult.Failure) && this.failOnError) {
            throw new CertificateException("Certificate transparency failed");
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    @NotNull
    public X509Certificate[] getAcceptedIssuers() {
        X509Certificate[] acceptedIssuers = this.delegate.getAcceptedIssuers();
        Intrinsics.checkNotNullExpressionValue(acceptedIssuers, "delegate.acceptedIssuers");
        return acceptedIssuers;
    }

    public final boolean isSameTrustConfiguration(@Nullable String str, @Nullable String str2) {
        Method method = this.isSameTrustConfigurationMethod;
        Intrinsics.checkNotNull(method);
        Object invoke = method.invoke(str, str2);
        Objects.requireNonNull(invoke, "null cannot be cast to non-null type kotlin.Boolean");
        return ((Boolean) invoke).booleanValue();
    }
}
