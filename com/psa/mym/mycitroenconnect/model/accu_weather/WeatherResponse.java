package com.psa.mym.mycitroenconnect.model.accu_weather;

import com.google.common.net.HttpHeaders;
import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class WeatherResponse {
    @SerializedName("EpochTime")
    private final int epochTime;
    @SerializedName("HasPrecipitation")
    private final boolean hasPrecipitation;
    @SerializedName("IsDayTime")
    private final boolean isDayTime;
    @SerializedName(HttpHeaders.LINK)
    @NotNull
    private final String link;
    @SerializedName("LocalObservationDateTime")
    @NotNull
    private final String localObservationDateTime;
    @SerializedName("MobileLink")
    @NotNull
    private final String mobileLink;
    @SerializedName("PrecipitationType")
    @NotNull
    private final Object precipitationType;
    @SerializedName("Temperature")
    @NotNull
    private final Temperature temperature;
    @SerializedName("WeatherIcon")
    private final int weatherIcon;
    @SerializedName("WeatherText")
    @NotNull
    private final String weatherText;

    public WeatherResponse(int i2, boolean z, boolean z2, @NotNull String link, @NotNull String localObservationDateTime, @NotNull String mobileLink, @NotNull Object precipitationType, @NotNull Temperature temperature, int i3, @NotNull String weatherText) {
        Intrinsics.checkNotNullParameter(link, "link");
        Intrinsics.checkNotNullParameter(localObservationDateTime, "localObservationDateTime");
        Intrinsics.checkNotNullParameter(mobileLink, "mobileLink");
        Intrinsics.checkNotNullParameter(precipitationType, "precipitationType");
        Intrinsics.checkNotNullParameter(temperature, "temperature");
        Intrinsics.checkNotNullParameter(weatherText, "weatherText");
        this.epochTime = i2;
        this.hasPrecipitation = z;
        this.isDayTime = z2;
        this.link = link;
        this.localObservationDateTime = localObservationDateTime;
        this.mobileLink = mobileLink;
        this.precipitationType = precipitationType;
        this.temperature = temperature;
        this.weatherIcon = i3;
        this.weatherText = weatherText;
    }

    public final int component1() {
        return this.epochTime;
    }

    @NotNull
    public final String component10() {
        return this.weatherText;
    }

    public final boolean component2() {
        return this.hasPrecipitation;
    }

    public final boolean component3() {
        return this.isDayTime;
    }

    @NotNull
    public final String component4() {
        return this.link;
    }

    @NotNull
    public final String component5() {
        return this.localObservationDateTime;
    }

    @NotNull
    public final String component6() {
        return this.mobileLink;
    }

    @NotNull
    public final Object component7() {
        return this.precipitationType;
    }

    @NotNull
    public final Temperature component8() {
        return this.temperature;
    }

    public final int component9() {
        return this.weatherIcon;
    }

    @NotNull
    public final WeatherResponse copy(int i2, boolean z, boolean z2, @NotNull String link, @NotNull String localObservationDateTime, @NotNull String mobileLink, @NotNull Object precipitationType, @NotNull Temperature temperature, int i3, @NotNull String weatherText) {
        Intrinsics.checkNotNullParameter(link, "link");
        Intrinsics.checkNotNullParameter(localObservationDateTime, "localObservationDateTime");
        Intrinsics.checkNotNullParameter(mobileLink, "mobileLink");
        Intrinsics.checkNotNullParameter(precipitationType, "precipitationType");
        Intrinsics.checkNotNullParameter(temperature, "temperature");
        Intrinsics.checkNotNullParameter(weatherText, "weatherText");
        return new WeatherResponse(i2, z, z2, link, localObservationDateTime, mobileLink, precipitationType, temperature, i3, weatherText);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WeatherResponse) {
            WeatherResponse weatherResponse = (WeatherResponse) obj;
            return this.epochTime == weatherResponse.epochTime && this.hasPrecipitation == weatherResponse.hasPrecipitation && this.isDayTime == weatherResponse.isDayTime && Intrinsics.areEqual(this.link, weatherResponse.link) && Intrinsics.areEqual(this.localObservationDateTime, weatherResponse.localObservationDateTime) && Intrinsics.areEqual(this.mobileLink, weatherResponse.mobileLink) && Intrinsics.areEqual(this.precipitationType, weatherResponse.precipitationType) && Intrinsics.areEqual(this.temperature, weatherResponse.temperature) && this.weatherIcon == weatherResponse.weatherIcon && Intrinsics.areEqual(this.weatherText, weatherResponse.weatherText);
        }
        return false;
    }

    public final int getEpochTime() {
        return this.epochTime;
    }

    public final boolean getHasPrecipitation() {
        return this.hasPrecipitation;
    }

    @NotNull
    public final String getLink() {
        return this.link;
    }

    @NotNull
    public final String getLocalObservationDateTime() {
        return this.localObservationDateTime;
    }

    @NotNull
    public final String getMobileLink() {
        return this.mobileLink;
    }

    @NotNull
    public final Object getPrecipitationType() {
        return this.precipitationType;
    }

    @NotNull
    public final Temperature getTemperature() {
        return this.temperature;
    }

    public final int getWeatherIcon() {
        return this.weatherIcon;
    }

    @NotNull
    public final String getWeatherText() {
        return this.weatherText;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = Integer.hashCode(this.epochTime) * 31;
        boolean z = this.hasPrecipitation;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        int i3 = (hashCode + i2) * 31;
        boolean z2 = this.isDayTime;
        return ((((((((((((((i3 + (z2 ? 1 : z2 ? 1 : 0)) * 31) + this.link.hashCode()) * 31) + this.localObservationDateTime.hashCode()) * 31) + this.mobileLink.hashCode()) * 31) + this.precipitationType.hashCode()) * 31) + this.temperature.hashCode()) * 31) + Integer.hashCode(this.weatherIcon)) * 31) + this.weatherText.hashCode();
    }

    public final boolean isDayTime() {
        return this.isDayTime;
    }

    @NotNull
    public String toString() {
        return "WeatherResponse(epochTime=" + this.epochTime + ", hasPrecipitation=" + this.hasPrecipitation + ", isDayTime=" + this.isDayTime + ", link=" + this.link + ", localObservationDateTime=" + this.localObservationDateTime + ", mobileLink=" + this.mobileLink + ", precipitationType=" + this.precipitationType + ", temperature=" + this.temperature + ", weatherIcon=" + this.weatherIcon + ", weatherText=" + this.weatherText + ')';
    }
}
