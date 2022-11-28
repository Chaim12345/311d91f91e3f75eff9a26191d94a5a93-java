package com.psa.mym.mycitroenconnect.api.body.geo_fence;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class Coordinates {
    @SerializedName("gpsLat")
    @Nullable
    private Double gpsLat;
    @SerializedName("gpsLong")
    @Nullable
    private Double gpsLong;

    public Coordinates() {
        this(null, null, 3, null);
    }

    public Coordinates(@Nullable Double d2, @Nullable Double d3) {
        this.gpsLat = d2;
        this.gpsLong = d3;
    }

    public /* synthetic */ Coordinates(Double d2, Double d3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : d2, (i2 & 2) != 0 ? null : d3);
    }

    public static /* synthetic */ Coordinates copy$default(Coordinates coordinates, Double d2, Double d3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            d2 = coordinates.gpsLat;
        }
        if ((i2 & 2) != 0) {
            d3 = coordinates.gpsLong;
        }
        return coordinates.copy(d2, d3);
    }

    @Nullable
    public final Double component1() {
        return this.gpsLat;
    }

    @Nullable
    public final Double component2() {
        return this.gpsLong;
    }

    @NotNull
    public final Coordinates copy(@Nullable Double d2, @Nullable Double d3) {
        return new Coordinates(d2, d3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Coordinates) {
            Coordinates coordinates = (Coordinates) obj;
            return Intrinsics.areEqual((Object) this.gpsLat, (Object) coordinates.gpsLat) && Intrinsics.areEqual((Object) this.gpsLong, (Object) coordinates.gpsLong);
        }
        return false;
    }

    @Nullable
    public final Double getGpsLat() {
        return this.gpsLat;
    }

    @Nullable
    public final Double getGpsLong() {
        return this.gpsLong;
    }

    public int hashCode() {
        Double d2 = this.gpsLat;
        int hashCode = (d2 == null ? 0 : d2.hashCode()) * 31;
        Double d3 = this.gpsLong;
        return hashCode + (d3 != null ? d3.hashCode() : 0);
    }

    public final void setGpsLat(@Nullable Double d2) {
        this.gpsLat = d2;
    }

    public final void setGpsLong(@Nullable Double d2) {
        this.gpsLong = d2;
    }

    @NotNull
    public String toString() {
        return "Coordinates(gpsLat=" + this.gpsLat + ", gpsLong=" + this.gpsLong + ')';
    }
}
