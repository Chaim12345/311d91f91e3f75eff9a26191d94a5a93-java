package com.psa.mym.mycitroenconnect.model.geo_fence;

import com.google.android.gms.maps.model.LatLng;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class MapPolygonData {
    @NotNull
    private LatLng latLng;
    private double latitude;
    private double longitude;
    @NotNull
    private String snippet;

    public MapPolygonData(@NotNull String snippet, double d2, double d3, @NotNull LatLng latLng) {
        Intrinsics.checkNotNullParameter(snippet, "snippet");
        Intrinsics.checkNotNullParameter(latLng, "latLng");
        this.snippet = snippet;
        this.latitude = d2;
        this.longitude = d3;
        this.latLng = latLng;
    }

    public static /* synthetic */ MapPolygonData copy$default(MapPolygonData mapPolygonData, String str, double d2, double d3, LatLng latLng, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = mapPolygonData.snippet;
        }
        if ((i2 & 2) != 0) {
            d2 = mapPolygonData.latitude;
        }
        double d4 = d2;
        if ((i2 & 4) != 0) {
            d3 = mapPolygonData.longitude;
        }
        double d5 = d3;
        if ((i2 & 8) != 0) {
            latLng = mapPolygonData.latLng;
        }
        return mapPolygonData.copy(str, d4, d5, latLng);
    }

    @NotNull
    public final String component1() {
        return this.snippet;
    }

    public final double component2() {
        return this.latitude;
    }

    public final double component3() {
        return this.longitude;
    }

    @NotNull
    public final LatLng component4() {
        return this.latLng;
    }

    @NotNull
    public final MapPolygonData copy(@NotNull String snippet, double d2, double d3, @NotNull LatLng latLng) {
        Intrinsics.checkNotNullParameter(snippet, "snippet");
        Intrinsics.checkNotNullParameter(latLng, "latLng");
        return new MapPolygonData(snippet, d2, d3, latLng);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MapPolygonData) {
            MapPolygonData mapPolygonData = (MapPolygonData) obj;
            return Intrinsics.areEqual(this.snippet, mapPolygonData.snippet) && Intrinsics.areEqual((Object) Double.valueOf(this.latitude), (Object) Double.valueOf(mapPolygonData.latitude)) && Intrinsics.areEqual((Object) Double.valueOf(this.longitude), (Object) Double.valueOf(mapPolygonData.longitude)) && Intrinsics.areEqual(this.latLng, mapPolygonData.latLng);
        }
        return false;
    }

    @NotNull
    public final LatLng getLatLng() {
        return this.latLng;
    }

    public final double getLatitude() {
        return this.latitude;
    }

    public final double getLongitude() {
        return this.longitude;
    }

    @NotNull
    public final String getSnippet() {
        return this.snippet;
    }

    public int hashCode() {
        return (((((this.snippet.hashCode() * 31) + Double.hashCode(this.latitude)) * 31) + Double.hashCode(this.longitude)) * 31) + this.latLng.hashCode();
    }

    public final void setLatLng(@NotNull LatLng latLng) {
        Intrinsics.checkNotNullParameter(latLng, "<set-?>");
        this.latLng = latLng;
    }

    public final void setLatitude(double d2) {
        this.latitude = d2;
    }

    public final void setLongitude(double d2) {
        this.longitude = d2;
    }

    public final void setSnippet(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.snippet = str;
    }

    @NotNull
    public String toString() {
        return "MapPolygonData(snippet=" + this.snippet + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", latLng=" + this.latLng + ')';
    }
}
