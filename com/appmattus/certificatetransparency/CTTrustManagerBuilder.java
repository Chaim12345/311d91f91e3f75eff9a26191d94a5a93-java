package com.appmattus.certificatetransparency;

import com.appmattus.certificatetransparency.cache.DiskCache;
import com.appmattus.certificatetransparency.chaincleaner.CertificateChainCleanerFactory;
import com.appmattus.certificatetransparency.datasource.DataSource;
import com.appmattus.certificatetransparency.internal.verifier.CertificateTransparencyTrustManager;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class CTTrustManagerBuilder {
    @Nullable
    private CertificateChainCleanerFactory certificateChainCleanerFactory;
    @NotNull
    private final X509TrustManager delegate;
    @Nullable
    private DiskCache diskCache;
    @NotNull
    private final Set<Host> excludeCommonNames;
    private boolean failOnError;
    @NotNull
    private final Set<Host> includeCommonNames;
    @Nullable
    private DataSource<LogListResult> logListDataSource;
    @Nullable
    private LogListService logListService;
    @Nullable
    private CTLogger logger;
    @Nullable
    private CTPolicy policy;

    public CTTrustManagerBuilder(@NotNull X509TrustManager delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
        this.includeCommonNames = new LinkedHashSet();
        this.excludeCommonNames = new LinkedHashSet();
        this.failOnError = true;
    }

    @NotNull
    public final X509TrustManager build() {
        Set set;
        Set set2;
        X509TrustManager x509TrustManager = this.delegate;
        set = CollectionsKt___CollectionsKt.toSet(this.includeCommonNames);
        set2 = CollectionsKt___CollectionsKt.toSet(this.excludeCommonNames);
        return new CertificateTransparencyTrustManager(x509TrustManager, set, set2, this.certificateChainCleanerFactory, this.logListService, this.logListDataSource, this.policy, this.diskCache, this.failOnError, this.logger);
    }

    public final /* synthetic */ void certificateChainCleanerFactory(Function0 init) {
        Intrinsics.checkNotNullParameter(init, "init");
        setCertificateChainCleanerFactory((CertificateChainCleanerFactory) init.invoke());
    }

    @NotNull
    public final CTTrustManagerBuilder excludeCommonName(@NotNull String... pattern) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        for (String str : pattern) {
            this.excludeCommonNames.add(new Host(str));
        }
        return this;
    }

    @NotNull
    public final X509TrustManager getDelegate() {
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
    public final CTTrustManagerBuilder includeCommonName(@NotNull String... pattern) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        for (String str : pattern) {
            this.includeCommonNames.add(new Host(str));
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
    public final CTTrustManagerBuilder setCertificateChainCleanerFactory(@NotNull CertificateChainCleanerFactory certificateChainCleanerFactory) {
        Intrinsics.checkNotNullParameter(certificateChainCleanerFactory, "certificateChainCleanerFactory");
        this.certificateChainCleanerFactory = certificateChainCleanerFactory;
        return this;
    }

    @NotNull
    public final CTTrustManagerBuilder setDiskCache(@NotNull DiskCache diskCache) {
        Intrinsics.checkNotNullParameter(diskCache, "diskCache");
        this.diskCache = diskCache;
        return this;
    }

    /* renamed from: setDiskCache  reason: collision with other method in class */
    public final /* synthetic */ void m22setDiskCache(DiskCache diskCache) {
        this.diskCache = diskCache;
    }

    @NotNull
    public final CTTrustManagerBuilder setFailOnError(boolean z) {
        this.failOnError = z;
        return this;
    }

    /* renamed from: setFailOnError  reason: collision with other method in class */
    public final /* synthetic */ void m23setFailOnError(boolean z) {
        this.failOnError = z;
    }

    @NotNull
    public final CTTrustManagerBuilder setLogListDataSource(@NotNull DataSource<LogListResult> logListDataSource) {
        Intrinsics.checkNotNullParameter(logListDataSource, "logListDataSource");
        this.logListDataSource = logListDataSource;
        return this;
    }

    @NotNull
    public final CTTrustManagerBuilder setLogListService(@NotNull LogListService logListService) {
        Intrinsics.checkNotNullParameter(logListService, "logListService");
        this.logListService = logListService;
        return this;
    }

    @NotNull
    public final CTTrustManagerBuilder setLogger(@NotNull CTLogger logger) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.logger = logger;
        return this;
    }

    /* renamed from: setLogger  reason: collision with other method in class */
    public final /* synthetic */ void m24setLogger(CTLogger cTLogger) {
        this.logger = cTLogger;
    }

    @NotNull
    public final CTTrustManagerBuilder setPolicy(@NotNull CTPolicy policy) {
        Intrinsics.checkNotNullParameter(policy, "policy");
        this.policy = policy;
        return this;
    }

    /* renamed from: setPolicy  reason: collision with other method in class */
    public final /* synthetic */ void m25setPolicy(CTPolicy cTPolicy) {
        this.policy = cTPolicy;
    }

    public final /* synthetic */ void unaryMinus(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        excludeCommonName(str);
    }

    public final /* synthetic */ void unaryMinus(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            excludeCommonName((String) it.next());
        }
    }

    public final /* synthetic */ void unaryPlus(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        includeCommonName(str);
    }

    public final /* synthetic */ void unaryPlus(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            includeCommonName((String) it.next());
        }
    }
}
