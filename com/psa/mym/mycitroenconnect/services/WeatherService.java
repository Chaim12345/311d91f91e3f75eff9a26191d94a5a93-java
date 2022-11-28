package com.psa.mym.mycitroenconnect.services;

import android.app.Activity;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.accu_weather.WeatherLocationResponse;
import com.psa.mym.mycitroenconnect.model.accu_weather.WeatherResponse;
import com.psa.mym.mycitroenconnect.utils.Logger;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class WeatherService extends BaseService {
    public final void getCityKey(@NotNull final Activity mActivity, double d2, double d3) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        WeatherAPI weatherApiInterface = WeatherApiClient.INSTANCE.getWeatherApiInterface();
        StringBuilder sb = new StringBuilder();
        sb.append(d2);
        sb.append(AbstractJsonLexerKt.COMMA);
        sb.append(d3);
        weatherApiInterface.getAccuweatherCity(sb.toString()).enqueue(new Callback<WeatherLocationResponse>() { // from class: com.psa.mym.mycitroenconnect.services.WeatherService$getCityKey$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<WeatherLocationResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                Logger logger = Logger.INSTANCE;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Weather Failure: ");
                t2.printStackTrace();
                sb2.append(Unit.INSTANCE);
                logger.e(sb2.toString());
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<WeatherLocationResponse> call, @NotNull Response<WeatherLocationResponse> response) {
                WeatherLocationResponse errorResponse;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.code() == 200) {
                    WeatherLocationResponse body = response.body();
                    Intrinsics.checkNotNull(body);
                    errorResponse = body;
                } else {
                    String string = mActivity.getString(R.string.no_data_available);
                    Intrinsics.checkNotNullExpressionValue(string, "mActivity.getString(R.string.no_data_available)");
                    errorResponse = new ErrorResponse(string, response.code(), "weather");
                }
                WeatherService.this.postResponse(errorResponse, false);
            }
        });
    }

    public final void getCurrentWeatherCondition(@NotNull final Activity mActivity, @NotNull String key) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(key, "key");
        WeatherApiClient.INSTANCE.getWeatherApiInterface().getAccuWeatherData(key).enqueue(new Callback<List<? extends WeatherResponse>>() { // from class: com.psa.mym.mycitroenconnect.services.WeatherService$getCurrentWeatherCondition$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<List<? extends WeatherResponse>> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                Logger logger = Logger.INSTANCE;
                StringBuilder sb = new StringBuilder();
                sb.append("Weather Failure: ");
                t2.printStackTrace();
                sb.append(Unit.INSTANCE);
                logger.e(sb.toString());
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<List<? extends WeatherResponse>> call, @NotNull Response<List<? extends WeatherResponse>> response) {
                WeatherResponse errorResponse;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.code() == 200) {
                    List<? extends WeatherResponse> body = response.body();
                    Intrinsics.checkNotNull(body);
                    errorResponse = body.get(0);
                    Logger logger = Logger.INSTANCE;
                    logger.e("Weather: " + errorResponse);
                } else {
                    String string = mActivity.getString(R.string.no_data_available);
                    Intrinsics.checkNotNullExpressionValue(string, "mActivity.getString(R.string.no_data_available)");
                    errorResponse = new ErrorResponse(string, response.code(), "weather");
                }
                WeatherService.this.postResponse(errorResponse, false);
            }
        });
    }
}
