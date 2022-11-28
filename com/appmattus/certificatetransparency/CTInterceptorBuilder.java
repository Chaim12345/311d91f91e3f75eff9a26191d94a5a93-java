package com.appmattus.certificatetransparency;

import com.appmattus.certificatetransparency.cache.DiskCache;
import com.appmattus.certificatetransparency.chaincleaner.CertificateChainCleanerFactory;
import com.appmattus.certificatetransparency.datasource.DataSource;
import com.appmattus.certificatetransparency.internal.verifier.CertificateTransparencyInterceptor;
import com.appmattus.certificatetransparency.internal.verifier.model.Host;
import com.appmattus.certificatetransparency.loglist.LogListResult;
import com.appmattus.certificatetransparency.loglist.LogListService;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.X509TrustManager;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class CTInterceptorBuilder {
    @Nullable
    private CertificateChainCleanerFactory certificateChainCleanerFactory;
    @Nullable
    private DiskCache diskCache;
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
    @NotNull
    private final Set<Host> includeHosts = new LinkedHashSet();
    @NotNull
    private final Set<Host> excludeHosts = new LinkedHashSet();
    private boolean failOnError = true;

    @NotNull
    public final Interceptor build() {
        Set set;
        Set set2;
        set = CollectionsKt___CollectionsKt.toSet(this.includeHosts);
        set2 = CollectionsKt___CollectionsKt.toSet(this.excludeHosts);
        return new CertificateTransparencyInterceptor(set, set2, this.certificateChainCleanerFactory, this.trustManager, this.logListService, this.logListDataSource, this.policy, this.diskCache, this.failOnError, this.logger);
    }

    public final /* synthetic */ void certificateChainCleanerFactory(Function0 init) {
        Intrinsics.checkNotNullParameter(init, "init");
        setCertificateChainCleanerFactory((CertificateChainCleanerFactory) init.invoke());
    }

    @NotNull
    public final CTInterceptorBuilder excludeHost(@NotNull String... pattern) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        for (String str : pattern) {
            this.excludeHosts.add(new Host(str));
        }
        return this;
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
    public final CTInterceptorBuilder includeHost(@NotNull String pattern) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        this.includeHosts.add(new Host(pattern));
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
    public final CTInterceptorBuilder setCertificateChainCleanerFactory(@NotNull CertificateChainCleanerFactory certificateChainCleanerFactory) {
        Intrinsics.checkNotNullParameter(certificateChainCleanerFactory, "certificateChainCleanerFactory");
        this.certificateChainCleanerFactory = certificateChainCleanerFactory;
        return this;
    }

    @NotNull
    public final CTInterceptorBuilder setDiskCache(@NotNull DiskCache diskCache) {
        Intrinsics.checkNotNullParameter(diskCache, "diskCache");
        this.diskCache = diskCache;
        return this;
    }

    /* renamed from: setDiskCache  reason: collision with other method in class */
    public final /* synthetic */ void m18setDiskCache(DiskCache diskCache) {
        this.diskCache = diskCache;
    }

    @NotNull
    public final CTInterceptorBuilder setFailOnError(boolean z) {
        this.failOnError = z;
        return this;
    }

    /* renamed from: setFailOnError  reason: collision with other method in class */
    public final /* synthetic */ void m19setFailOnError(boolean z) {
        this.failOnError = z;
    }

    @NotNull
    public final CTInterceptorBuilder setLogListDataSource(@NotNull DataSource<LogListResult> logListDataSource) {
        Intrinsics.checkNotNullParameter(logListDataSource, "logListDataSource");
        this.logListDataSource = logListDataSource;
        return this;
    }

    @NotNull
    public final CTInterceptorBuilder setLogListService(@NotNull LogListService logListService) {
        Intrinsics.checkNotNullParameter(logListService, "logListService");
        this.logListService = logListService;
        return this;
    }

    @NotNull
    public final CTInterceptorBuilder setLogger(@NotNull CTLogger logger) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.logger = logger;
        return this;
    }

    /* renamed from: setLogger  reason: collision with other method in class */
    public final /* synthetic */ void m20setLogger(CTLogger cTLogger) {
        this.logger = cTLogger;
    }

    @NotNull
    public final CTInterceptorBuilder setPolicy(@NotNull CTPolicy policy) {
        Intrinsics.checkNotNullParameter(policy, "policy");
        this.policy = policy;
        return this;
    }

    /* renamed from: setPolicy  reason: collision with other method in class */
    public final /* synthetic */ void m21setPolicy(CTPolicy cTPolicy) {
        this.policy = cTPolicy;
    }

    @NotNull
    public final CTInterceptorBuilder setTrustManager(@NotNull X509TrustManager trustManager) {
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
