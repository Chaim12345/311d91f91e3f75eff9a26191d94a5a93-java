package com.appmattus.certificatetransparency.internal.verifier;

import com.appmattus.certificatetransparency.CTPolicy;
import com.appmattus.certificatetransparency.SctVerificationResult;
import com.appmattus.certificatetransparency.VerificationResult;
import com.appmattus.certificatetransparency.cache.DiskCache;
import com.appmattus.certificatetransparency.chaincleaner.CertificateChainCleaner;
import com.appmattus.certificatetransparency.chaincleaner.CertificateChainCleanerFactory;
import com.appmattus.certificatetransparency.datasource.DataSource;
import com.appmattus.certificatetransparency.internal.loglist.LogListJsonFailedLoadingWithException;
import com.appmattus.certificatetransparency.internal.loglist.NoLogServers;
import com.appmattus.certificatetransparency.internal.utils.Base64;
import com.appmattus.certificatetransparency.internal.utils.CertificateExtKt;
import com.appmattus.certificatetransparency.internal.utils.X509CertificateExtKt;
import com.appmattus.certificatetransparency.internal.verifier.model.Host;
import com.appmattus.certificatetransparency.internal.verifier.model.SignedCertificateTimestamp;
import com.appmattus.certificatetransparency.loglist.LogListDataSourceFactory;
import com.appmattus.certificatetransparency.loglist.LogListResult;
import com.appmattus.certificatetransparency.loglist.LogListService;
import com.appmattus.certificatetransparency.loglist.LogServer;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.X509TrustManager;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public class CertificateTransparencyBase {
    @Nullable
    private final CertificateChainCleanerFactory certificateChainCleanerFactory;
    @NotNull
    private final Lazy cleaner$delegate;
    @NotNull
    private final Set<Host> excludeHosts;
    @NotNull
    private final Set<Host> includeHosts;
    @NotNull
    private final DataSource<LogListResult> logListDataSource;
    @NotNull
    private final CTPolicy policy;

    public CertificateTransparencyBase() {
        this(null, null, null, null, null, null, null, null, 255, null);
    }

    public CertificateTransparencyBase(@NotNull Set<Host> includeHosts, @NotNull Set<Host> excludeHosts, @Nullable CertificateChainCleanerFactory certificateChainCleanerFactory, @Nullable X509TrustManager x509TrustManager, @Nullable LogListService logListService, @Nullable DataSource<LogListResult> dataSource, @Nullable CTPolicy cTPolicy, @Nullable DiskCache diskCache) {
        Lazy lazy;
        DataSource<LogListResult> dataSource2;
        Host host;
        Intrinsics.checkNotNullParameter(includeHosts, "includeHosts");
        Intrinsics.checkNotNullParameter(excludeHosts, "excludeHosts");
        this.includeHosts = includeHosts;
        this.excludeHosts = excludeHosts;
        this.certificateChainCleanerFactory = certificateChainCleanerFactory;
        Iterator<T> it = includeHosts.iterator();
        do {
            boolean z = true;
            if (!it.hasNext()) {
                if (!(dataSource == null || logListService == null)) {
                    throw new IllegalArgumentException("LogListService is ignored when overriding logListDataSource".toString());
                }
                if (dataSource != null && diskCache != null) {
                    z = false;
                }
                if (!z) {
                    throw new IllegalArgumentException("DiskCache is ignored when overriding logListDataSource".toString());
                }
                lazy = LazyKt__LazyJVMKt.lazy(new CertificateTransparencyBase$cleaner$2(x509TrustManager, this));
                this.cleaner$delegate = lazy;
                if (dataSource == null) {
                    LogListDataSourceFactory logListDataSourceFactory = LogListDataSourceFactory.INSTANCE;
                    dataSource2 = logListDataSourceFactory.createDataSource(logListService == null ? LogListDataSourceFactory.createLogListService$default(logListDataSourceFactory, null, null, 0L, x509TrustManager, 7, null) : logListService, diskCache);
                } else {
                    dataSource2 = dataSource;
                }
                this.logListDataSource = dataSource2;
                this.policy = cTPolicy == null ? new DefaultPolicy() : cTPolicy;
                return;
            }
            host = (Host) it.next();
            if (!(!host.getMatchAll())) {
                throw new IllegalArgumentException("Certificate transparency is enabled by default on all domain names".toString());
            }
        } while (!this.excludeHosts.contains(host));
        throw new IllegalArgumentException("Certificate transparency inclusions must not match exclude directly".toString());
    }

    public /* synthetic */ CertificateTransparencyBase(Set set, Set set2, CertificateChainCleanerFactory certificateChainCleanerFactory, X509TrustManager x509TrustManager, LogListService logListService, DataSource dataSource, CTPolicy cTPolicy, DiskCache diskCache, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? SetsKt__SetsKt.emptySet() : set, (i2 & 2) != 0 ? SetsKt__SetsKt.emptySet() : set2, (i2 & 4) != 0 ? null : certificateChainCleanerFactory, (i2 & 8) != 0 ? null : x509TrustManager, (i2 & 16) != 0 ? null : logListService, (i2 & 32) != 0 ? null : dataSource, (i2 & 64) != 0 ? null : cTPolicy, (i2 & 128) == 0 ? diskCache : null);
    }

    private final boolean enabledForCertificateTransparency(String str) {
        boolean z;
        boolean z2;
        Set<Host> set = this.excludeHosts;
        if (!(set instanceof Collection) || !set.isEmpty()) {
            for (Host host : set) {
                if (host.matches(str)) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        if (z) {
            Set<Host> set2 = this.includeHosts;
            if (!(set2 instanceof Collection) || !set2.isEmpty()) {
                for (Host host2 : set2) {
                    if (host2.matches(str)) {
                        z2 = true;
                        break;
                    }
                }
            }
            z2 = false;
            return z2;
        }
        return true;
    }

    private final CertificateChainCleaner getCleaner() {
        return (CertificateChainCleaner) this.cleaner$delegate.getValue();
    }

    private final VerificationResult hasValidSignedCertificateTimestamp(List<? extends X509Certificate> list) {
        LogListResult logListJsonFailedLoadingWithException;
        int collectionSizeOrDefault;
        int mapCapacity;
        int coerceAtLeast;
        int collectionSizeOrDefault2;
        int mapCapacity2;
        int coerceAtLeast2;
        int mapCapacity3;
        SctVerificationResult sctVerificationResult;
        Object runBlocking$default;
        try {
            runBlocking$default = BuildersKt__BuildersKt.runBlocking$default(null, new CertificateTransparencyBase$hasValidSignedCertificateTimestamp$result$1(this, null), 1, null);
            logListJsonFailedLoadingWithException = (LogListResult) runBlocking$default;
        } catch (Exception e2) {
            logListJsonFailedLoadingWithException = new LogListJsonFailedLoadingWithException(e2);
        }
        if (!(logListJsonFailedLoadingWithException instanceof LogListResult.Valid)) {
            if (logListJsonFailedLoadingWithException instanceof LogListResult.Invalid) {
                return new VerificationResult.Failure.LogServersFailed((LogListResult.Invalid) logListJsonFailedLoadingWithException);
            }
            if (logListJsonFailedLoadingWithException == null) {
                return new VerificationResult.Failure.LogServersFailed(NoLogServers.INSTANCE);
            }
            throw new NoWhenBranchMatchedException();
        }
        List<LogServer> servers = ((LogListResult.Valid) logListJsonFailedLoadingWithException).getServers();
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(servers, 10);
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(collectionSizeOrDefault);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (LogServer logServer : servers) {
            linkedHashMap.put(Base64.INSTANCE.toBase64String(logServer.getId()), new LogSignatureVerifier(logServer));
        }
        X509Certificate x509Certificate = list.get(0);
        if (CertificateExtKt.hasEmbeddedSct(x509Certificate)) {
            try {
                List<SignedCertificateTimestamp> signedCertificateTimestamps = X509CertificateExtKt.signedCertificateTimestamps(x509Certificate);
                collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(signedCertificateTimestamps, 10);
                mapCapacity2 = MapsKt__MapsJVMKt.mapCapacity(collectionSizeOrDefault2);
                coerceAtLeast2 = RangesKt___RangesKt.coerceAtLeast(mapCapacity2, 16);
                LinkedHashMap linkedHashMap2 = new LinkedHashMap(coerceAtLeast2);
                for (Object obj : signedCertificateTimestamps) {
                    linkedHashMap2.put(Base64.INSTANCE.toBase64String(((SignedCertificateTimestamp) obj).getId().getKeyId()), obj);
                }
                mapCapacity3 = MapsKt__MapsJVMKt.mapCapacity(linkedHashMap2.size());
                LinkedHashMap linkedHashMap3 = new LinkedHashMap(mapCapacity3);
                for (Object obj2 : linkedHashMap2.entrySet()) {
                    Object key = ((Map.Entry) obj2).getKey();
                    Map.Entry entry = (Map.Entry) obj2;
                    SignedCertificateTimestamp signedCertificateTimestamp = (SignedCertificateTimestamp) entry.getValue();
                    LogSignatureVerifier logSignatureVerifier = (LogSignatureVerifier) linkedHashMap.get((String) entry.getKey());
                    if (logSignatureVerifier == null || (sctVerificationResult = logSignatureVerifier.verifySignature(signedCertificateTimestamp, list)) == null) {
                        sctVerificationResult = SctVerificationResult.Invalid.NoTrustedLogServerFound.INSTANCE;
                    }
                    linkedHashMap3.put(key, sctVerificationResult);
                }
                return this.policy.policyVerificationResult(x509Certificate, linkedHashMap3);
            } catch (IOException e3) {
                return new VerificationResult.Failure.UnknownIoException(e3);
            }
        }
        return VerificationResult.Failure.NoScts.INSTANCE;
    }

    @NotNull
    public final VerificationResult verifyCertificateTransparency(@NotNull String host, @NotNull List<? extends Certificate> certificates) {
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(certificates, "certificates");
        if (enabledForCertificateTransparency(host)) {
            if (!certificates.isEmpty()) {
                CertificateChainCleaner cleaner = getCleaner();
                ArrayList arrayList = new ArrayList();
                for (Object obj : certificates) {
                    if (obj instanceof X509Certificate) {
                        arrayList.add(obj);
                    }
                }
                List<X509Certificate> clean = cleaner.clean(arrayList, host);
                if (!clean.isEmpty()) {
                    return hasValidSignedCertificateTimestamp(clean);
                }
            }
            return VerificationResult.Failure.NoCertificates.INSTANCE;
        }
        return new VerificationResult.Success.DisabledForHost(host);
    }
}
