package com.appmattus.certificatetransparency;

import com.appmattus.certificatetransparency.cache.DiskCache;
import com.appmattus.certificatetransparency.chaincleaner.CertificateChainCleanerFactory;
import com.appmattus.certificatetransparency.datasource.DataSource;
import com.appmattus.certificatetransparency.internal.verifier.CertificateTransparencyHostnameVerifier;
import com.appmattus.certificatetransparency.internal.verifier.model.Host;
import com.appmattus.certificatetransparency.loglist.LogListResult;
import com.appmattus.certificatetransparency.loglist.LogListService;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.X509TrustManager;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class CTHostnameVerifierBuilder {
    @Nullable
    private CertificateChainCleanerFactory certificateChainCleanerFactory;
    @NotNull
    private final HostnameVerifier delegate;
    @Nullable
    private DiskCache diskCache;
    @NotNull
    private final Set<Host> excludeHosts;
    private boolean failOnError;
    @NotNull
    private final Set<Host> includeHosts;
    @Nullable
    private DataSource<LogListResult> logListDataSource;
    @Nullable
    private LogListService logListService;
    @Nullable
    private CTLogger logger;
    @Nullable
    private CTPolicy policy;
    @Nullable
    private X509TrustManager trustManager;

    public CTHostnameVerifierBuilder(@NotNull HostnameVerifier delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
        this.includeHosts = new LinkedHashSet();
        this.excludeHosts = new LinkedHashSet();
        this.failOnError = true;
    }

    @NotNull
    public final HostnameVerifier build() {
        Set set;
        Set set2;
        HostnameVerifier hostnameVerifier = this.delegate;
        set = CollectionsKt___CollectionsKt.toSet(this.includeHosts);
        set2 = CollectionsKt___CollectionsKt.toSet(this.excludeHosts);
        return new CertificateTransparencyHostnameVerifier(hostnameVerifier, set, set2, this.certificateChainCleanerFactory, this.trustManager, this.logListService, this.logListDataSource, this.policy, this.diskCache, this.failOnError, this.logger);
    }

    public final /* synthetic */ void certificateChainCleanerFactory(Function0 init) {
        Intrinsics.checkNotNullParameter(init, "init");
        setCertificateChainCleanerFactory((CertificateChainCleanerFactory) init.invoke());
    }

    @NotNull
    public final CTHostnameVerifierBuilder excludeHost(@NotNull String... pattern) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        for (String str : pattern) {
            this.excludeHosts.add(new Host(str));
        }
        return this;
    }

    @NotNull
    public final HostnameVerifier getDelegate() {
        return this.delegate;
    }

    public final /* synthetic */ DiskCache getDiskCache() {
        return this.diskCache;
    }

    public final /* synthetic */ boolean getFailOnError() {
        return this.failOnError;
    }

    public final /* synthetic */ CTLogger getLogger() {
        return this.logger;
    }

    public final /* synthetic */ CTPolicy getPolicy() {
        return this.policy;
    }

    @NotNull
    public final CTHostnameVerifierBuilder includeHost(@NotNull String... pattern) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        for (String str : pattern) {
            this.includeHosts.add(new Host(str));
        }
        return this;
    }

    public final /* synthetic */ void logListDataSource(Function0 init) {
        Intrinsics.checkNotNullParameter(init, "init");
        setLogListDataSource((DataSource) init.invoke());
    }

    public final /* synthetic */ void logListService(Function0 init) {
        Intrinsics.checkNotNullParameter(init, "init");
        setLogListService((LogListService) init.invoke());
    }

    @NotNull
    public final CTHostnameVerifierBuilder setCertificateChainCleanerFactory(@NotNull CertificateChainCleanerFactory certificateChainCleanerFactory) {
        Intrinsics.checkNotNullParameter(certificateChainCleanerFactory, "certificateChainCleanerFactory");
        this.certificateChainCleanerFactory = certificateChainCleanerFactory;
        return this;
    }

    @NotNull
    public final CTHostnameVerifierBuilder setDiskCache(@NotNull DiskCache diskCache) {
        Intrinsics.checkNotNullParameter(diskCache, "diskCache");
        this.diskCache = diskCache;
        return this;
    }

    /* renamed from: setDiskCache  reason: collision with other method in class */
    public final /* synthetic */ void m14setDiskCache(DiskCache diskCache) {
        this.diskCache = diskCache;
    }

    @NotNull
    public final CTHostnameVerifierBuilder setFailOnError(boolean z) {
        this.failOnError = z;
        return this;
    }

    /* renamed from: setFailOnError  reason: collision with other method in class */
    public final /* synthetic */ void m15setFailOnError(boolean z) {
        this.failOnError = z;
    }

    @NotNull
    public final CTHostnameVerifierBuilder setLogListDataSource(@NotNull DataSource<LogListResult> logListDataSource) {
        Intrinsics.checkNotNullParameter(logListDataSource, "logListDataSource");
        this.logListDataSource = logListDataSource;
        return this;
    }

    @NotNull
    public final CTHostnameVerifierBuilder setLogListService(@NotNull LogListService logListService) {
        Intrinsics.checkNotNullParameter(logListService, "logListService");
        this.logListService = logListService;
        return this;
    }

    @NotNull
    public final CTHostnameVerifierBuilder setLogger(@NotNull CTLogger logger) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.logger = logger;
        return this;
    }

    /* renamed from: setLogger  reason: collision with other method in class */
    public final /* synthetic */ void m16setLogger(CTLogger cTLogger) {
        this.logger = cTLogger;
    }

    @NotNull
    public final CTHostnameVerifierBuilder setPolicy(@NotNull CTPolicy policy) {
        Intrinsics.checkNotNullParameter(policy, "policy");
        this.policy = policy;
        return this;
    }

    /* renamed from: setPolicy  reason: collision with other method in class */
    public final /* synthetic */ void m17setPolicy(CTPolicy cTPolicy) {
        this.policy = cTPolicy;
    }

    @NotNull
    public final CTHostnameVerifierBuilder setTrustManager(@NotNull X509TrustManager trustManager) {
        Intrinsics.checkNotNullParameter(trustManager, "trustManager");
        this.trustManager = trustManager;
        return this;
    }

    public final /* synthetic */ void trustManager(Function0 init) {
        Intrinsics.checkNotNullParameter(init, "init");
        setTrustManager((X509TrustManager) init.invoke());
    }

    public final /* synthetic */ void unaryMinus(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        excludeHost(str);
    }

    public final /* synthetic */ void unaryMinus(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            excludeHost((String) it.next());
        }
    }

    public final /* synthetic */ void unaryPlus(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        includeHost(str);
    }

    public final /* synthetic */ void unaryPlus(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            includeHost((String) it.next());
        }
    }
}
