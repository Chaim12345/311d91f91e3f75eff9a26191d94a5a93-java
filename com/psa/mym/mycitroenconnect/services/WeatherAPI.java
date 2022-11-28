package com.psa.mym.mycitroenconnect.services;

import com.psa.mym.mycitroenconnect.model.accu_weather.WeatherLocationResponse;
import com.psa.mym.mycitroenconnect.model.accu_weather.WeatherResponse;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
/* loaded from: classes3.dex */
public interface WeatherAPI {
    @GET("currentconditions/v1/{key}")
    @NotNull
    Call<List<WeatherResponse>> getAccuWeatherData(@Path("key") @Nullable String str);

    @GET("locations/v1/cities/geoposition/search")
    @NotNull
    Call<WeatherLocationResponse> getAccuweatherCity(@Nullable @Query("q") String str);
}
