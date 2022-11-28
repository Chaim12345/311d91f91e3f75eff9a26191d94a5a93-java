package com.psa.mym.mycitroenconnect.services;

import androidx.annotation.NonNull;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class AccuWeatherApiKeyInterceptor implements Interceptor {
    @NotNull
    private final String apiKey;

    public AccuWeatherApiKeyInterceptor(@NotNull String apiKey) {
        Intrinsics.checkNotNullParameter(apiKey, "apiKey");
        this.apiKey = apiKey;
    }

    @Override // okhttp3.Interceptor
    @NonNull
    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        return chain.proceed(chain.request().newBuilder().url(chain.request().url().newBuilder().addQueryParameter("apikey", this.apiKey).build()).build());
    }
}
