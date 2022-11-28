package com.appmattus.certificaterevocation;

import com.appmattus.certificaterevocation.internal.revoker.CertificateRevocationInterceptor;
import com.appmattus.certificaterevocation.internal.revoker.CrlItem;
import com.appmattus.certificatetransparency.chaincleaner.CertificateChainCleanerFactory;
import com.appmattus.certificatetransparency.internal.utils.Base64;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class CRInterceptorBuilder {
    @Nullable
    private CertificateChainCleanerFactory certificateChainCleanerFactory;
    @NotNull
    private final Set<CrlItem> crlSet = new LinkedHashSet();
    private boolean failOnError = true;
    @Nullable
    private CRLogger logger;
    @Nullable
    private X509TrustManager trustManager;

    @NotNull
    public final CRInterceptorBuilder addCrl(@NotNull String issuerDistinguishedName, @NotNull List<String> serialNumbers) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(issuerDistinguishedName, "issuerDistinguishedName");
        Intrinsics.checkNotNullParameter(serialNumbers, "serialNumbers");
        X500Principal x500Principal = new X500Principal(Base64.INSTANCE.decode(issuerDistinguishedName));
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(serialNumbers, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (String str : serialNumbers) {
            arrayList.add(new BigInteger(Base64.INSTANCE.decode(str)));
        }
        this.crlSet.add(new CrlItem(x500Principal, arrayList));
        return this;
    }

    @NotNull
    public final Interceptor build() {
        Set set;
        set = CollectionsKt___CollectionsKt.toSet(this.crlSet);
        return new CertificateRevocationInterceptor(set, this.certificateChainCleanerFactory, this.trustManager, this.failOnError, this.logger);
    }

    public final /* synthetic */ void certificateChainCleanerFactory(Function0 init) {
        Intrinsics.checkNotNullParameter(init, "init");
        setCertificateChainCleanerFactory((CertificateChainCleanerFactory) init.invoke());
    }

    public final /* synthetic */ boolean getFailOnError() {
        return this.failOnError;
    }

    public final /* synthetic */ CRLogger getLogger() {
        return this.logger;
    }

    @NotNull
    public final CRInterceptorBuilder setCertificateChainCleanerFactory(@NotNull CertificateChainCleanerFactory certificateChainCleanerFactory) {
        Intrinsics.checkNotNullParameter(certificateChainCleanerFactory, "certificateChainCleanerFactory");
        this.certificateChainCleanerFactory = certificateChainCleanerFactory;
        return this;
    }

    @NotNull
    public final CRInterceptorBuilder setFailOnError(boolean z) {
        this.failOnError = z;
        return this;
    }

    /* renamed from: setFailOnError  reason: collision with other method in class */
    public final /* synthetic */ void m12setFailOnError(boolean z) {
        this.failOnError = z;
    }

    @NotNull
    public final CRInterceptorBuilder setLogger(@NotNull CRLogger logger) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.logger = logger;
        return this;
    }

    /* renamed from: setLogger  reason: collision with other method in class */
    public final /* synthetic */ void m13setLogger(CRLogger cRLogger) {
        this.logger = cRLogger;
    }

    @NotNull
    public final CRInterceptorBuilder setTrustManager(@NotNull X509TrustManager trustManager) {
        Intrinsics.checkNotNullParameter(trustManager, "trustManager");
        this.trustManager = trustManager;
        return this;
    }

    public final /* synthetic */ void trustManager(Function0 init) {
        Intrinsics.checkNotNullParameter(init, "init");
        setTrustManager((X509TrustManager) init.invoke());
    }
}
