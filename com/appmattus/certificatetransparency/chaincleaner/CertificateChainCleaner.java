package com.appmattus.certificatetransparency.chaincleaner;

import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.X509TrustManager;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public interface CertificateChainCleaner {
    @NotNull
    public static final Companion Companion = Companion.f4564a;

    /* loaded from: classes.dex */
    public static final class Companion {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ Companion f4564a = new Companion();
        @NotNull
        private static final Lazy<CertificateChainCleanerFactory> androidCertificateChainCleanerFactory$delegate = LazyKt.lazy(CertificateChainCleaner$Companion$androidCertificateChainCleanerFactory$2.INSTANCE);

        private Companion() {
        }

        private final CertificateChainCleanerFactory getAndroidCertificateChainCleanerFactory() {
            return androidCertificateChainCleanerFactory$delegate.getValue();
        }

        @NotNull
        public final CertificateChainCleaner get(@NotNull X509TrustManager trustManager) {
            CertificateChainCleaner certificateChainCleaner;
            Intrinsics.checkNotNullParameter(trustManager, "trustManager");
            CertificateChainCleanerFactory androidCertificateChainCleanerFactory = getAndroidCertificateChainCleanerFactory();
            return (androidCertificateChainCleanerFactory == null || (certificateChainCleaner = androidCertificateChainCleanerFactory.get(trustManager)) == null) ? new BasicCertificateChainCleaner(trustManager) : certificateChainCleaner;
        }
    }

    @NotNull
    List<X509Certificate> clean(@NotNull List<? extends X509Certificate> list, @NotNull String str);
}
