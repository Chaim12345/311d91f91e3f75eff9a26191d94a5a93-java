package com.psa.mym.mycitroenconnect.api.retrofit;

import android.content.Context;
import android.os.Build;
import com.chuckerteam.chucker.api.ChuckerCollector;
import com.chuckerteam.chucker.api.ChuckerInterceptor;
import com.chuckerteam.chucker.api.RetentionManager;
import com.psa.mym.mycitroenconnect.api.retrofit.ProfileOkHttpClientInstance;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.TlsVersion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class ProfileOkHttpClientInstance {

    /* loaded from: classes.dex */
    public static final class Builder {
        @NotNull
        private ApiHolder apiHolder;
        @Nullable
        private Context context;
        @NotNull
        private HashMap<String, String> headers;

        public Builder(@Nullable Context context, @NotNull ApiHolder apiHolder) {
            Intrinsics.checkNotNullParameter(apiHolder, "apiHolder");
            this.context = context;
            this.apiHolder = apiHolder;
            this.headers = new HashMap<>();
        }

        private final OkHttpClient.Builder createOkhttpClientBuilderWithTlsConfig() {
            Lazy lazy;
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            lazy = LazyKt__LazyJVMKt.lazy(ProfileOkHttpClientInstance$Builder$createOkhttpClientBuilderWithTlsConfig$1$trustManager$2.INSTANCE);
            if (Build.VERSION.SDK_INT < 22) {
                try {
                    SSLContext sSLContext = SSLContext.getInstance(TlsVersion.TLS_1_2.javaName());
                    sSLContext.init(null, null, null);
                    SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
                    Intrinsics.checkNotNullExpressionValue(socketFactory, "sc.socketFactory");
                    builder.sslSocketFactory(new TlsSocketFactory(socketFactory), m47createOkhttpClientBuilderWithTlsConfig$lambda1$lambda0(lazy));
                    ConnectionSpec.Builder builder2 = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS);
                    String[] allowed_tls_versions = TlsSocketFactory.Companion.getALLOWED_TLS_VERSIONS();
                    ConnectionSpec build = builder2.tlsVersions((String[]) Arrays.copyOf(allowed_tls_versions, allowed_tls_versions.length)).build();
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(build);
                    arrayList.add(ConnectionSpec.COMPATIBLE_TLS);
                    arrayList.add(ConnectionSpec.CLEARTEXT);
                    builder.connectionSpecs(arrayList);
                } catch (Exception e2) {
                    Logger.INSTANCE.e("OkHttpTLSCompat: Error while setting TLS 1.1 and 1.2", e2);
                }
            }
            return builder;
        }

        /* renamed from: createOkhttpClientBuilderWithTlsConfig$lambda-1$lambda-0  reason: not valid java name */
        private static final X509TrustManager m47createOkhttpClientBuilderWithTlsConfig$lambda1$lambda0(Lazy<? extends X509TrustManager> lazy) {
            return lazy.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: getUnsafeOkHttpClient$lambda-3  reason: not valid java name */
        public static final boolean m48getUnsafeOkHttpClient$lambda3(String str, SSLSession sSLSession) {
            return true;
        }

        @NotNull
        public final Builder addHeader(@NotNull String key, @NotNull String value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            this.headers.put(key, value);
            return this;
        }

        @NotNull
        public final OkHttpClient build() {
            Set emptySet;
            Context context = this.context;
            Intrinsics.checkNotNull(context);
            ProfileTokenAuthenticator profileTokenAuthenticator = new ProfileTokenAuthenticator(context, this.apiHolder);
            OkHttpClient.Builder addInterceptor = createOkhttpClientBuilderWithTlsConfig().addInterceptor(new Interceptor() { // from class: com.psa.mym.mycitroenconnect.api.retrofit.ProfileOkHttpClientInstance$Builder$build$$inlined$-addInterceptor$1
                @Override // okhttp3.Interceptor
                @NotNull
                public final Response intercept(@NotNull Interceptor.Chain chain) {
                    Context context2;
                    Intrinsics.checkNotNullParameter(chain, "chain");
                    Request.Builder addHeader = chain.request().newBuilder().addHeader("Content-Type", "application/json");
                    SharedPref.Companion companion = SharedPref.Companion;
                    context2 = ProfileOkHttpClientInstance.Builder.this.context;
                    Intrinsics.checkNotNull(context2);
                    String secondaryUserToken = companion.getSecondaryUserToken(context2);
                    Logger logger = Logger.INSTANCE;
                    logger.e("Token: " + secondaryUserToken);
                    if (secondaryUserToken != null) {
                        addHeader.addHeader("Authorization", "Bearer " + secondaryUserToken);
                    }
                    return chain.proceed(addHeader.build());
                }
            }).addInterceptor(ApiClient.INSTANCE.getHttpLoggingInterceptor());
            Context context2 = this.context;
            Intrinsics.checkNotNull(context2);
            ChuckerInterceptor.Builder builder = new ChuckerInterceptor.Builder(context2);
            Context context3 = this.context;
            Intrinsics.checkNotNull(context3);
            ChuckerInterceptor.Builder maxContentLength = builder.collector(new ChuckerCollector(context3, true, RetentionManager.Period.ONE_DAY)).maxContentLength(250000L);
            emptySet = SetsKt__SetsKt.emptySet();
            OkHttpClient.Builder addInterceptor2 = addInterceptor.addInterceptor(maxContentLength.redactHeaders(emptySet).alwaysReadResponseBody(false).build());
            TimeUnit timeUnit = TimeUnit.SECONDS;
            OkHttpClient.Builder readTimeout = addInterceptor2.connectTimeout(60L, timeUnit).writeTimeout(60L, timeUnit).readTimeout(60L, timeUnit);
            readTimeout.authenticator(profileTokenAuthenticator);
            return readTimeout.build();
        }

        @NotNull
        public final OkHttpClient.Builder getUnsafeOkHttpClient() {
            Set emptySet;
            try {
                TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.psa.mym.mycitroenconnect.api.retrofit.ProfileOkHttpClientInstance$Builder$getUnsafeOkHttpClient$trustAllCerts$1
                    @Override // javax.net.ssl.X509TrustManager
                    public void checkClientTrusted(@Nullable X509Certificate[] x509CertificateArr, @Nullable String str) {
                    }

                    @Override // javax.net.ssl.X509TrustManager
                    public void checkServerTrusted(@Nullable X509Certificate[] x509CertificateArr, @Nullable String str) {
                    }

                    @Override // javax.net.ssl.X509TrustManager
                    @NotNull
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }};
                SSLContext sSLContext = SSLContext.getInstance("SSL");
                sSLContext.init(null, trustManagerArr, new SecureRandom());
                SSLSocketFactory sslSocketFactory = sSLContext.getSocketFactory();
                Context context = this.context;
                Intrinsics.checkNotNull(context);
                ProfileTokenAuthenticator profileTokenAuthenticator = new ProfileTokenAuthenticator(context, this.apiHolder);
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                Intrinsics.checkNotNullExpressionValue(sslSocketFactory, "sslSocketFactory");
                OkHttpClient.Builder sslSocketFactory2 = builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustManagerArr[0]);
                OkHttpClient.Builder addInterceptor = sslSocketFactory2.hostnameVerifier(c.f10361a).addInterceptor(new Interceptor() { // from class: com.psa.mym.mycitroenconnect.api.retrofit.ProfileOkHttpClientInstance$Builder$getUnsafeOkHttpClient$$inlined$-addInterceptor$1
                    @Override // okhttp3.Interceptor
                    @NotNull
                    public final Response intercept(@NotNull Interceptor.Chain chain) {
                        Context context2;
                        Intrinsics.checkNotNullParameter(chain, "chain");
                        Request.Builder addHeader = chain.request().newBuilder().addHeader("Content-Type", "application/json");
                        SharedPref.Companion companion = SharedPref.Companion;
                        context2 = ProfileOkHttpClientInstance.Builder.this.context;
                        Intrinsics.checkNotNull(context2);
                        String secondaryUserToken = companion.getSecondaryUserToken(context2);
                        Logger logger = Logger.INSTANCE;
                        logger.e("Token: " + secondaryUserToken);
                        if (secondaryUserToken != null) {
                            addHeader.addHeader("Authorization", "Bearer " + secondaryUserToken);
                        }
                        return chain.proceed(addHeader.build());
                    }
                });
                TimeUnit timeUnit = TimeUnit.SECONDS;
                addInterceptor.connectTimeout(60L, timeUnit).writeTimeout(60L, timeUnit).readTimeout(60L, timeUnit);
                if (!Intrinsics.areEqual("preprod", "preprod") || !Intrinsics.areEqual("preprod", AppConstants.FLAVOR_PROD)) {
                    sslSocketFactory2.addInterceptor(ApiClient.INSTANCE.getHttpLoggingInterceptor());
                    Context context2 = this.context;
                    Intrinsics.checkNotNull(context2);
                    ChuckerInterceptor.Builder builder2 = new ChuckerInterceptor.Builder(context2);
                    Context context3 = this.context;
                    Intrinsics.checkNotNull(context3);
                    ChuckerInterceptor.Builder maxContentLength = builder2.collector(new ChuckerCollector(context3, true, RetentionManager.Period.ONE_DAY)).maxContentLength(250000L);
                    emptySet = SetsKt__SetsKt.emptySet();
                    sslSocketFactory2.addInterceptor(maxContentLength.redactHeaders(emptySet).alwaysReadResponseBody(false).build());
                }
                sslSocketFactory2.authenticator(profileTokenAuthenticator);
                return sslSocketFactory2;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
    }
}
