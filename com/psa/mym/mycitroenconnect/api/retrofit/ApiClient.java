package com.psa.mym.mycitroenconnect.api.retrofit;

import android.content.Context;
import com.appmattus.certificatetransparency.CTInterceptorBuilder;
import com.psa.mym.mycitroenconnect.api.retrofit.OkHttpClientInstance;
import com.psa.mym.mycitroenconnect.api.retrofit.ProfileOkHttpClientInstance;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.io.ByteArrayInputStream;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.tls.HandshakeCertificates;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/* loaded from: classes.dex */
public final class ApiClient {
    @NotNull
    public static final ApiClient INSTANCE = new ApiClient();
    @NotNull
    private static final String caCert = "-----BEGIN CERTIFICATE-----\nMIIFrzCCA5egAwIBAgIUQNBp4ASVL2LTWBVET++59BoH0M0wDQYJKoZIhvcNAQEL\nBQAwZzELMAkGA1UEBhMCSU4xEjAQBgNVBAgMCVRhbWlsbmFkdTEQMA4GA1UEBwwH\nQ2hlbm5haTETMBEGA1UECgwKVGF0YSBFbHhzaTEMMAoGA1UECwwDTUNWMQ8wDQYD\nVQQDDAZ0ZWxwc2EwHhcNMjEwNTAxMDAyNzIwWhcNMzEwNDI5MDAyNzIwWjBnMQsw\nCQYDVQQGEwJJTjESMBAGA1UECAwJVGFtaWxuYWR1MRAwDgYDVQQHDAdDaGVubmFp\nMRMwEQYDVQQKDApUYXRhIEVseHNpMQwwCgYDVQQLDANNQ1YxDzANBgNVBAMMBnRl\nbHBzYTCCAiIwDQYJKoZIhvcNAQEBBQADggIPADCCAgoCggIBAPTJC7k6qxSfiGhD\nHwCHAd4yYWgaAV/H2lXW4M4VCEFlt0YaZuHq0OhROY12xUIyO2g/zltAPmPwCImK\nghZ4JJ6m3w4P6bznIrwq/IrxCUt6wMZZfXrMhdS8napEErgWvnaHWch7FVHMAZIw\nz6k/pLYwAi7Mie4LI2Sn+Chy7+iOyRWQxSOFU0iRdPul7gSdJS5Y0n0AznHUIiO/\n7TcdJamZ/PpU6GnUKEsjokzylekTwq2T/fXyWuedIfMeCZvKD1zH+S+UhdS3cdzt\nUqTd5H1kmNVdF3mB7PJMG2GU5OGnxPb3g/2L0BkEOy+6Yhq0KJQDZZZWVx/Q+2LV\nswJi0yivrYberiGSpE6kUTdjToZ/01ey74/cnZC2Cpc2zdVewKCoFGiK4MIx8EXj\ntmHUb5fKdcI57vvVtTZsDWxGBCU3tJrN7QQjwv1pD0drd/vhhfTCa5pZd/CCg8F8\nZoJNEQu9DCyLAXqsPT9s9F/N4DO/UJQSkS/It/8mvRGFM6NjI8LP9HcIpRq3+IrR\nZDW7Dk6TfGzlN3mJS4QGFuBQMjzD5wZYnUBgd02ud7DDp/a+9bu7Im/Cf9KRXw0F\nNY1kGPoVguyY1TA3+sjV5RzC/H1NGWnl+q0xc38Fp5mVifCMuizoa3tiNVMxWD6J\nZaKfSOnWy3ywa+l7uPwjPj1xMLz/AgMBAAGjUzBRMB0GA1UdDgQWBBTPLvQ0my1D\niDhJWBkkDwtYxzgdWTAfBgNVHSMEGDAWgBTPLvQ0my1DiDhJWBkkDwtYxzgdWTAP\nBgNVHRMBAf8EBTADAQH/MA0GCSqGSIb3DQEBCwUAA4ICAQDg1vbufZbQR/k6v209\n1+oJ95/ZrbUu8PsKIANbLnO+TPgOvRop4PCiIUeeqWTEoJ8bpD1sjyKtRdIg1mwX\nJW3SjcRX1wEUdszEa6r0YEZZZDilup5q1OyvjNuv/XwvFXQr94rH/ZTr02uNAWxX\nXGmdMd5F3cFRJ6mB5p50l7U9UD4kI6Cka29HFBXOlu//3rYvE9nrelATes/cma6U\nx8zmtlvBGjzJ+UQ8p6outY01lZUcC4gG6X0G2ziTqlGzc/fOYcF2PSHs02Un7fR+\nn90JpGLo++9CNPxpS+gd68AW7vsp3cSNJ7TRwSjJZDibDimBT8qNmu0qMP/CnVKo\nF5S0hQgYmpW0ZaM+2S9eYGuONQ9hDMG5oJEjKpZrFOlbL/veKNF+re9+1C4Id/T3\ndsAxR0Mx9yJAoqvmc5zK/R8q3eYBA/A8T5E5RsnPAxWXmRuxcln7b2sM3eMseLNf\nkVgU9wS9asd6f7vk16mIuX08UiueQFuxse+/yOOyoc8uQYUb/U/Y0AbeV67la21a\npyKC/gLKphQ+Wg4uIW1wxtF6DYC4CJweriMWBgw/7jtx0JngUSGWFzw49l9oaWbr\nuOXEen5yWwZ0dqQa3fEaisRGWCkML4t6wAQYCMJMR2pxf0wd7Pdh4UBUKni3DaA5\nyzE5bkuZTtoLF1vytjPogo8ENw==\n-----END CERTIFICATE-----";
    @Nullable
    private static Retrofit retrofit;

    private ApiClient() {
    }

    private final X509Certificate convertStringToX509Cert(String str) {
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        Certificate generateCertificate = CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(bytes));
        Objects.requireNonNull(generateCertificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
        return (X509Certificate) generateCertificate;
    }

    @NotNull
    public static final Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl("https://lb1.cvip-preprod.citroen.in:40543/").client(INSTANCE.getUnsafeOkHttpClient()).addConverterFactory(GsonConverterFactory.create()).build();
        }
        Retrofit retrofit3 = retrofit;
        Intrinsics.checkNotNull(retrofit3);
        return retrofit3;
    }

    @JvmStatic
    public static /* synthetic */ void getClient$annotations() {
    }

    @NotNull
    public static final Retrofit getSafeRetrofit() {
        Retrofit build = new Retrofit.Builder().baseUrl("https://lb1.cvip-preprod.citroen.in:40543/").addConverterFactory(GsonConverterFactory.create()).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n              …\n                .build()");
        return build;
    }

    @JvmStatic
    public static /* synthetic */ void getSafeRetrofit$annotations() {
    }

    private final OkHttpClient getUnsafeOkHttpClient() {
        try {
            TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.psa.mym.mycitroenconnect.api.retrofit.ApiClient$getUnsafeOkHttpClient$trustAllCerts$1
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
            HandshakeCertificates build = new HandshakeCertificates.Builder().addTrustedCertificate(convertStringToX509Cert(caCert)).build();
            OkHttpClient.Builder addInterceptor = new OkHttpClient.Builder().addInterceptor(getHttpLoggingInterceptor());
            TimeUnit timeUnit = TimeUnit.SECONDS;
            OkHttpClient.Builder readTimeout = addInterceptor.connectTimeout(60L, timeUnit).writeTimeout(60L, timeUnit).readTimeout(60L, timeUnit);
            if (!Intrinsics.areEqual("preprod", "preprod") && !Intrinsics.areEqual("preprod", AppConstants.FLAVOR_PROD)) {
                Intrinsics.checkNotNullExpressionValue(sslSocketFactory, "sslSocketFactory");
                readTimeout.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustManagerArr[0]).sslSocketFactory(build.sslSocketFactory(), build.trustManager()).hostnameVerifier(a.f10359a);
                return readTimeout.build();
            }
            readTimeout.addNetworkInterceptor(new CTInterceptorBuilder().excludeHost("lb1.cvip-preprod.citroen.in").excludeHost("lb1.cvip.citroen.in").build());
            return readTimeout.build();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getUnsafeOkHttpClient$lambda-0  reason: not valid java name */
    public static final boolean m44getUnsafeOkHttpClient$lambda0(String str, SSLSession sSLSession) {
        return true;
    }

    @NotNull
    public final ApiInterface getApiInterfaceWithToken(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ApiHolder apiHolder = new ApiHolder();
        OkHttpClientInstance.Builder builder = new OkHttpClientInstance.Builder(context, apiHolder);
        Object create = new Retrofit.Builder().baseUrl("https://lb1.cvip-preprod.citroen.in:40543/").addConverterFactory(GsonConverterFactory.create()).client(builder.addHeader("Authorization", "Bearer " + SharedPref.Companion.getPrimaryUserToken(context)).getUnsafeOkHttpClient().build()).build().create(ApiInterface.class);
        Intrinsics.checkNotNullExpressionValue(create, "Builder()\n            .b…ApiInterface::class.java)");
        ApiInterface apiInterface = (ApiInterface) create;
        apiHolder.set(apiInterface);
        return apiInterface;
    }

    @NotNull
    public final String getCaCert() {
        return caCert;
    }

    @NotNull
    public final HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(null, 1, null);
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.NONE);
        return httpLoggingInterceptor;
    }

    @NotNull
    public final ApiInterface getProfileApiInterfaceWithToken(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ApiHolder apiHolder = new ApiHolder();
        String secondaryUserToken = SharedPref.Companion.getSecondaryUserToken(context);
        ProfileOkHttpClientInstance.Builder builder = new ProfileOkHttpClientInstance.Builder(context, apiHolder);
        Object create = new Retrofit.Builder().baseUrl("https://lb1.cvip-preprod.citroen.in:40543/").addConverterFactory(GsonConverterFactory.create()).client(builder.addHeader("Authorization", "Bearer " + secondaryUserToken).getUnsafeOkHttpClient().build()).build().create(ApiInterface.class);
        Intrinsics.checkNotNullExpressionValue(create, "Builder()\n            .b…ApiInterface::class.java)");
        ApiInterface apiInterface = (ApiInterface) create;
        apiHolder.set(apiInterface);
        return apiInterface;
    }
}
