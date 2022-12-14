package com.appmattus.certificatetransparency.internal.verifier;

import com.appmattus.certificatetransparency.chaincleaner.CertificateChainCleaner;
import com.appmattus.certificatetransparency.chaincleaner.CertificateChainCleanerFactory;
import java.security.KeyStore;
import java.util.NoSuchElementException;
import java.util.Objects;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class CertificateTransparencyBase$cleaner$2 extends Lambda implements Function0<CertificateChainCleaner> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ X509TrustManager f4634a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ CertificateTransparencyBase f4635b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateTransparencyBase$cleaner$2(X509TrustManager x509TrustManager, CertificateTransparencyBase certificateTransparencyBase) {
        super(0);
        this.f4634a = x509TrustManager;
        this.f4635b = certificateTransparencyBase;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final CertificateChainCleaner invoke() {
        CertificateChainCleanerFactory certificateChainCleanerFactory;
        CertificateChainCleaner certificateChainCleaner;
        X509TrustManager x509TrustManager = this.f4634a;
        if (x509TrustManager == null) {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            Intrinsics.checkNotNullExpressionValue(trustManagers, "getInstance(TrustManager???)\n        }.trustManagers");
            for (TrustManager trustManager : trustManagers) {
                if (trustManager instanceof X509TrustManager) {
                    Objects.requireNonNull(trustManager, "null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
                    x509TrustManager = (X509TrustManager) trustManager;
                }
            }
            throw new NoSuchElementException("Array contains no element matching the predicate.");
        }
        certificateChainCleanerFactory = this.f4635b.certificateChainCleanerFactory;
        return (certificateChainCleanerFactory == null || (certificateChainCleaner = certificateChainCleanerFactory.get(x509TrustManager)) == null) ? CertificateChainCleaner.Companion.get(x509TrustManager) : certificateChainCleaner;
    }
}
