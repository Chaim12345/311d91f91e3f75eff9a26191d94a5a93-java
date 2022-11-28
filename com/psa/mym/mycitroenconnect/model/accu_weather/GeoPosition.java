package com.psa.mym.mycitroenconnect.model.accu_weather;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class GeoPosition {
    @SerializedName("Elevation")
    @NotNull
    private final Elevation elevation;
    @SerializedName("Latitude")
    private final double latitude;
    @SerializedName("Longitude")
    private final double longitude;

    public GeoPosition(@NotNull Elevation elevation, double d2, double d3) {
        Intrinsics.checkNotNullParameter(elevation, "elevation");
        this.elevation = elevation;
        this.latitude = d2;
        this.longitude = d3;
    }

    public static /* synthetic */ GeoPosition copy$default(GeoPosition geoPosition, Elevation elevation, double d2, double d3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            elevation = geoPosition.elevation;
        }
        if ((i2 & 2) != 0) {
            d2 = geoPosition.latitude;
        }
        double d4 = d2;
        if ((i2 & 4) != 0) {
            d3 = geoPosition.longitude;
        }
        return geoPosition.copy(elevation, d4, d3);
    }

    @NotNull
    public final Elevation component1() {
        return this.elevation;
    }

    public final double component2() {
        return this.latitude;
    }

    public final double component3() {
        return this.longitude;
    }

    @NotNull
    public final GeoPosition copy(@NotNull Elevation elevation, double d2, double d3) {
        Intrinsics.checkNotNullParameter(elevation, "elevation");
        return new GeoPosition(elevation, d2, d3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GeoPosition) {
            GeoPosition geoPosition = (GeoPosition) obj;
            return Intrinsics.areEqual(this.elevation, geoPosition.elevation) && Intrinsics.areEqual((Object) Double.valueOf(this.latitude), (Object) Double.valueOf(geoPosition.latitude)) && Intrinsics.areEqual((Object) Double.valueOf(this.longitude), (Object) Double.valueOf(geoPosition.longitude));
        }
        return false;
    }

    @NotNull
    public final Elevation getElevation() {
        return this.elevation;
    }

    public final double getLatitude() {
        return this.latitude;
    }

    public final double getLongitude() {
        return this.longitude;
    }

    public int hashCode() {
        return (((this.elevation.hashCode() * 31) + Double.hashCode(this.latitude)) * 31) + Double.hashCode(this.longitude);
    }

    @NotNull
    public String toString() {
        return "GeoPosition(elevation=" + this.elevation + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ')';
    }
}
