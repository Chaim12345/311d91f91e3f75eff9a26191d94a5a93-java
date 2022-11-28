package com.psa.mym.mycitroenconnect.api.retrofit;

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
/* loaded from: classes.dex */
final class ProfileOkHttpClientInstance$Builder$createOkhttpClientBuilderWithTlsConfig$1$trustManager$2 extends Lambda implements Function0<X509TrustManager> {
    public static final ProfileOkHttpClientInstance$Builder$createOkhttpClientBuilderWithTlsConfig$1$trustManager$2 INSTANCE = new ProfileOkHttpClientInstance$Builder$createOkhttpClientBuilderWithTlsConfig$1$trustManager$2();

    ProfileOkHttpClientInstance$Builder$createOkhttpClientBuilderWithTlsConfig$1$trustManager$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final X509TrustManager invoke() {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        Intrinsics.checkNotNullExpressionValue(trustManagers, "trustManagerFactory\n    …           .trustManagers");
        for (TrustManager trustManager : trustManagers) {
            if (trustManager instanceof X509TrustManager) {
                Objects.requireNonNull(trustManager, "null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
                return (X509TrustManager) trustManager;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }
}
