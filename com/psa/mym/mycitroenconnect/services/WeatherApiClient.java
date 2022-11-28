package com.psa.mym.mycitroenconnect.services;

import com.psa.mym.mycitroenconnect.BuildConfig;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/* loaded from: classes3.dex */
public final class WeatherApiClient {
    @NotNull
    private static final String BASE_ACCU_WEATHER_DATA_SERVICE = "http://dataservice.accuweather.com/";
    @NotNull
    public static final WeatherApiClient INSTANCE = new WeatherApiClient();

    private WeatherApiClient() {
    }

    private final HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(null, 1, null);
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.NONE);
        return httpLoggingInterceptor;
    }

    private final OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        return builder.connectTimeout(10000L, timeUnit).readTimeout(10000L, timeUnit).writeTimeout(10000L, timeUnit).addInterceptor(getHttpLoggingInterceptor()).addInterceptor(new AccuWeatherApiKeyInterceptor(BuildConfig.ACCU_WEATHER_API_KEY)).build();
    }

    @NotNull
    public final WeatherAPI getWeatherApiInterface() {
        Object create = new Retrofit.Builder().baseUrl(BASE_ACCU_WEATHER_DATA_SERVICE).client(getOkHttpClient()).addConverterFactory(GsonConverterFactory.create()).build().create(WeatherAPI.class);
        Intrinsics.checkNotNullExpressionValue(create, "Builder()\n            .b…e(WeatherAPI::class.java)");
        return (WeatherAPI) create;
    }
}
