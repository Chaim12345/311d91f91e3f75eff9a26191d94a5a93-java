package com.psa.mym.mycitroenconnect.model.accu_weather;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class WeatherLocationResponse {
    @SerializedName("AdministrativeArea")
    @NotNull
    private final AdministrativeArea administrativeArea;
    @SerializedName("Country")
    @NotNull
    private final Country country;
    @SerializedName("DataSets")
    @NotNull
    private final List<String> dataSets;
    @SerializedName("EnglishName")
    @NotNull
    private final String englishName;
    @SerializedName("GeoPosition")
    @NotNull
    private final GeoPosition geoPosition;
    @SerializedName("IsAlias")
    private final boolean isAlias;
    @SerializedName("Key")
    @NotNull
    private final String key;
    @SerializedName("LocalizedName")
    @NotNull
    private final String localizedName;
    @SerializedName("PrimaryPostalCode")
    @NotNull
    private final String primaryPostalCode;
    @SerializedName("Rank")
    private final int rank;
    @SerializedName("Region")
    @NotNull
    private final Region region;
    @SerializedName("SupplementalAdminAreas")
    @NotNull
    private final List<SupplementalAdminArea> supplementalAdminAreas;
    @SerializedName("TimeZone")
    @NotNull
    private final TimeZone timeZone;
    @SerializedName("Type")
    @NotNull
    private final String type;
    @SerializedName("Version")
    private final int version;

    public WeatherLocationResponse(@NotNull AdministrativeArea administrativeArea, @NotNull Country country, @NotNull List<String> dataSets, @NotNull String englishName, @NotNull GeoPosition geoPosition, boolean z, @NotNull String key, @NotNull String localizedName, @NotNull String primaryPostalCode, int i2, @NotNull Region region, @NotNull List<SupplementalAdminArea> supplementalAdminAreas, @NotNull TimeZone timeZone, @NotNull String type, int i3) {
        Intrinsics.checkNotNullParameter(administrativeArea, "administrativeArea");
        Intrinsics.checkNotNullParameter(country, "country");
        Intrinsics.checkNotNullParameter(dataSets, "dataSets");
        Intrinsics.checkNotNullParameter(englishName, "englishName");
        Intrinsics.checkNotNullParameter(geoPosition, "geoPosition");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(localizedName, "localizedName");
        Intrinsics.checkNotNullParameter(primaryPostalCode, "primaryPostalCode");
        Intrinsics.checkNotNullParameter(region, "region");
        Intrinsics.checkNotNullParameter(supplementalAdminAreas, "supplementalAdminAreas");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        Intrinsics.checkNotNullParameter(type, "type");
        this.administrativeArea = administrativeArea;
        this.country = country;
        this.dataSets = dataSets;
        this.englishName = englishName;
        this.geoPosition = geoPosition;
        this.isAlias = z;
        this.key = key;
        this.localizedName = localizedName;
        this.primaryPostalCode = primaryPostalCode;
        this.rank = i2;
        this.region = region;
        this.supplementalAdminAreas = supplementalAdminAreas;
        this.timeZone = timeZone;
        this.type = type;
        this.version = i3;
    }

    @NotNull
    public final AdministrativeArea component1() {
        return this.administrativeArea;
    }

    public final int component10() {
        return this.rank;
    }

    @NotNull
    public final Region component11() {
        return this.region;
    }

    @NotNull
    public final List<SupplementalAdminArea> component12() {
        return this.supplementalAdminAreas;
    }

    @NotNull
    public final TimeZone component13() {
        return this.timeZone;
    }

    @NotNull
    public final String component14() {
        return this.type;
    }

    public final int component15() {
        return this.version;
    }

    @NotNull
    public final Country component2() {
        return this.country;
    }

    @NotNull
    public final List<String> component3() {
        return this.dataSets;
    }

    @NotNull
    public final String component4() {
        return this.englishName;
    }

    @NotNull
    public final GeoPosition component5() {
        return this.geoPosition;
    }

    public final boolean component6() {
        return this.isAlias;
    }

    @NotNull
    public final String component7() {
        return this.key;
    }

    @NotNull
    public final String component8() {
        return this.localizedName;
    }

    @NotNull
    public final String component9() {
        return this.primaryPostalCode;
    }

    @NotNull
    public final WeatherLocationResponse copy(@NotNull AdministrativeArea administrativeArea, @NotNull Country country, @NotNull List<String> dataSets, @NotNull String englishName, @NotNull GeoPosition geoPosition, boolean z, @NotNull String key, @NotNull String localizedName, @NotNull String primaryPostalCode, int i2, @NotNull Region region, @NotNull List<SupplementalAdminArea> supplementalAdminAreas, @NotNull TimeZone timeZone, @NotNull String type, int i3) {
        Intrinsics.checkNotNullParameter(administrativeArea, "administrativeArea");
        Intrinsics.checkNotNullParameter(country, "country");
        Intrinsics.checkNotNullParameter(dataSets, "dataSets");
        Intrinsics.checkNotNullParameter(englishName, "englishName");
        Intrinsics.checkNotNullParameter(geoPosition, "geoPosition");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(localizedName, "localizedName");
        Intrinsics.checkNotNullParameter(primaryPostalCode, "primaryPostalCode");
        Intrinsics.checkNotNullParameter(region, "region");
        Intrinsics.checkNotNullParameter(supplementalAdminAreas, "supplementalAdminAreas");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        Intrinsics.checkNotNullParameter(type, "type");
        return new WeatherLocationResponse(administrativeArea, country, dataSets, englishName, geoPosition, z, key, localizedName, primaryPostalCode, i2, region, supplementalAdminAreas, timeZone, type, i3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WeatherLocationResponse) {
            WeatherLocationResponse weatherLocationResponse = (WeatherLocationResponse) obj;
            return Intrinsics.areEqual(this.administrativeArea, weatherLocationResponse.administrativeArea) && Intrinsics.areEqual(this.country, weatherLocationResponse.country) && Intrinsics.areEqual(this.dataSets, weatherLocationResponse.dataSets) && Intrinsics.areEqual(this.englishName, weatherLocationResponse.englishName) && Intrinsics.areEqual(this.geoPosition, weatherLocationResponse.geoPosition) && this.isAlias == weatherLocationResponse.isAlias && Intrinsics.areEqual(this.key, weatherLocationResponse.key) && Intrinsics.areEqual(this.localizedName, weatherLocationResponse.localizedName) && Intrinsics.areEqual(this.primaryPostalCode, weatherLocationResponse.primaryPostalCode) && this.rank == weatherLocationResponse.rank && Intrinsics.areEqual(this.region, weatherLocationResponse.region) && Intrinsics.areEqual(this.supplementalAdminAreas, weatherLocationResponse.supplementalAdminAreas) && Intrinsics.areEqual(this.timeZone, weatherLocationResponse.timeZone) && Intrinsics.areEqual(this.type, weatherLocationResponse.type) && this.version == weatherLocationResponse.version;
        }
        return false;
    }

    @NotNull
    public final AdministrativeArea getAdministrativeArea() {
        return this.administrativeArea;
    }

    @NotNull
    public final Country getCountry() {
        return this.country;
    }

    @NotNull
    public final List<String> getDataSets() {
        return this.dataSets;
    }

    @NotNull
    public final String getEnglishName() {
        return this.englishName;
    }

    @NotNull
    public final GeoPosition getGeoPosition() {
        return this.geoPosition;
    }

    @NotNull
    public final String getKey() {
        return this.key;
    }

    @NotNull
    public final String getLocalizedName() {
        return this.localizedName;
    }

    @NotNull
    public final String getPrimaryPostalCode() {
        return this.primaryPostalCode;
    }

    public final int getRank() {
        return this.rank;
    }

    @NotNull
    public final Region getRegion() {
        return this.region;
    }

    @NotNull
    public final List<SupplementalAdminArea> getSupplementalAdminAreas() {
        return this.supplementalAdminAreas;
    }

    @NotNull
    public final TimeZone getTimeZone() {
        return this.timeZone;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }

    public final int getVersion() {
        return this.version;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((((((this.administrativeArea.hashCode() * 31) + this.country.hashCode()) * 31) + this.dataSets.hashCode()) * 31) + this.englishName.hashCode()) * 31) + this.geoPosition.hashCode()) * 31;
        boolean z = this.isAlias;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        return ((((((((((((((((((hashCode + i2) * 31) + this.key.hashCode()) * 31) + this.localizedName.hashCode()) * 31) + this.primaryPostalCode.hashCode()) * 31) + Integer.hashCode(this.rank)) * 31) + this.region.hashCode()) * 31) + this.supplementalAdminAreas.hashCode()) * 31) + this.timeZone.hashCode()) * 31) + this.type.hashCode()) * 31) + Integer.hashCode(this.version);
    }

    public final boolean isAlias() {
        return this.isAlias;
    }

    @NotNull
    public String toString() {
        return "WeatherLocationResponse(administrativeArea=" + this.administrativeArea + ", country=" + this.country + ", dataSets=" + this.dataSets + ", englishName=" + this.englishName + ", geoPosition=" + this.geoPosition + ", isAlias=" + this.isAlias + ", key=" + this.key + ", localizedName=" + this.localizedName + ", primaryPostalCode=" + this.primaryPostalCode + ", rank=" + this.rank + ", region=" + this.region + ", supplementalAdminAreas=" + this.supplementalAdminAreas + ", timeZone=" + this.timeZone + ", type=" + this.type + ", version=" + this.version + ')';
    }
}
