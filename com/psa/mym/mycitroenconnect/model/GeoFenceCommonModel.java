package com.psa.mym.mycitroenconnect.model;

import android.location.Location;
import com.google.android.gms.maps.model.LatLng;
import com.psa.mym.mycitroenconnect.api.body.geo_fence.GeoFenceBody;
import com.psa.mym.mycitroenconnect.model.geo_fence.GeoFenceType;
import com.psa.mym.mycitroenconnect.model.geo_fence.GetGeoFenceResponseItem;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class GeoFenceCommonModel {
    @Nullable
    private LatLng carLatLng;
    @Nullable
    private Location currentLocation;
    private int fenceCreationMode;
    @NotNull
    private GeoFenceBody geoFenceBody;
    @Nullable
    private GetGeoFenceResponseItem geoFenceGetResponse;
    @Nullable
    private GeoFenceType geoFenceType;

    public GeoFenceCommonModel() {
        this(null, null, null, null, null, 0, 63, null);
    }

    public GeoFenceCommonModel(@Nullable GeoFenceType geoFenceType, @Nullable Location location, @Nullable LatLng latLng, @NotNull GeoFenceBody geoFenceBody, @Nullable GetGeoFenceResponseItem getGeoFenceResponseItem, int i2) {
        Intrinsics.checkNotNullParameter(geoFenceBody, "geoFenceBody");
        this.geoFenceType = geoFenceType;
        this.currentLocation = location;
        this.carLatLng = latLng;
        this.geoFenceBody = geoFenceBody;
        this.geoFenceGetResponse = getGeoFenceResponseItem;
        this.fenceCreationMode = i2;
    }

    public /* synthetic */ GeoFenceCommonModel(GeoFenceType geoFenceType, Location location, LatLng latLng, GeoFenceBody geoFenceBody, GetGeoFenceResponseItem getGeoFenceResponseItem, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : geoFenceType, (i3 & 2) != 0 ? null : location, (i3 & 4) != 0 ? null : latLng, (i3 & 8) != 0 ? new GeoFenceBody(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 262143, null) : geoFenceBody, (i3 & 16) == 0 ? getGeoFenceResponseItem : null, (i3 & 32) != 0 ? 5 : i2);
    }

    public static /* synthetic */ GeoFenceCommonModel copy$default(GeoFenceCommonModel geoFenceCommonModel, GeoFenceType geoFenceType, Location location, LatLng latLng, GeoFenceBody geoFenceBody, GetGeoFenceResponseItem getGeoFenceResponseItem, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            geoFenceType = geoFenceCommonModel.geoFenceType;
        }
        if ((i3 & 2) != 0) {
            location = geoFenceCommonModel.currentLocation;
        }
        Location location2 = location;
        if ((i3 & 4) != 0) {
            latLng = geoFenceCommonModel.carLatLng;
        }
        LatLng latLng2 = latLng;
        if ((i3 & 8) != 0) {
            geoFenceBody = geoFenceCommonModel.geoFenceBody;
        }
        GeoFenceBody geoFenceBody2 = geoFenceBody;
        if ((i3 & 16) != 0) {
            getGeoFenceResponseItem = geoFenceCommonModel.geoFenceGetResponse;
        }
        GetGeoFenceResponseItem getGeoFenceResponseItem2 = getGeoFenceResponseItem;
        if ((i3 & 32) != 0) {
            i2 = geoFenceCommonModel.fenceCreationMode;
        }
        return geoFenceCommonModel.copy(geoFenceType, location2, latLng2, geoFenceBody2, getGeoFenceResponseItem2, i2);
    }

    @Nullable
    public final GeoFenceType component1() {
        return this.geoFenceType;
    }

    @Nullable
    public final Location component2() {
        return this.currentLocation;
    }

    @Nullable
    public final LatLng component3() {
        return this.carLatLng;
    }

    @NotNull
    public final GeoFenceBody component4() {
        return this.geoFenceBody;
    }

    @Nullable
    public final GetGeoFenceResponseItem component5() {
        return this.geoFenceGetResponse;
    }

    public final int component6() {
        return this.fenceCreationMode;
    }

    @NotNull
    public final GeoFenceCommonModel copy(@Nullable GeoFenceType geoFenceType, @Nullable Location location, @Nullable LatLng latLng, @NotNull GeoFenceBody geoFenceBody, @Nullable GetGeoFenceResponseItem getGeoFenceResponseItem, int i2) {
        Intrinsics.checkNotNullParameter(geoFenceBody, "geoFenceBody");
        return new GeoFenceCommonModel(geoFenceType, location, latLng, geoFenceBody, getGeoFenceResponseItem, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GeoFenceCommonModel) {
            GeoFenceCommonModel geoFenceCommonModel = (GeoFenceCommonModel) obj;
            return Intrinsics.areEqual(this.geoFenceType, geoFenceCommonModel.geoFenceType) && Intrinsics.areEqual(this.currentLocation, geoFenceCommonModel.currentLocation) && Intrinsics.areEqual(this.carLatLng, geoFenceCommonModel.carLatLng) && Intrinsics.areEqual(this.geoFenceBody, geoFenceCommonModel.geoFenceBody) && Intrinsics.areEqual(this.geoFenceGetResponse, geoFenceCommonModel.geoFenceGetResponse) && this.fenceCreationMode == geoFenceCommonModel.fenceCreationMode;
        }
        return false;
    }

    @Nullable
    public final LatLng getCarLatLng() {
        return this.carLatLng;
    }

    @Nullable
    public final Location getCurrentLocation() {
        return this.currentLocation;
    }

    public final int getFenceCreationMode() {
        return this.fenceCreationMode;
    }

    @NotNull
    public final GeoFenceBody getGeoFenceBody() {
        return this.geoFenceBody;
    }

    @Nullable
    public final GetGeoFenceResponseItem getGeoFenceGetResponse() {
        return this.geoFenceGetResponse;
    }

    @Nullable
    public final GeoFenceType getGeoFenceType() {
        return this.geoFenceType;
    }

    public int hashCode() {
        GeoFenceType geoFenceType = this.geoFenceType;
        int hashCode = (geoFenceType == null ? 0 : geoFenceType.hashCode()) * 31;
        Location location = this.currentLocation;
        int hashCode2 = (hashCode + (location == null ? 0 : location.hashCode())) * 31;
        LatLng latLng = this.carLatLng;
        int hashCode3 = (((hashCode2 + (latLng == null ? 0 : latLng.hashCode())) * 31) + this.geoFenceBody.hashCode()) * 31;
        GetGeoFenceResponseItem getGeoFenceResponseItem = this.geoFenceGetResponse;
        return ((hashCode3 + (getGeoFenceResponseItem != null ? getGeoFenceResponseItem.hashCode() : 0)) * 31) + Integer.hashCode(this.fenceCreationMode);
    }

    public final void setCarLatLng(@Nullable LatLng latLng) {
        this.carLatLng = latLng;
    }

    public final void setCurrentLocation(@Nullable Location location) {
        this.currentLocation = location;
    }

    public final void setFenceCreationMode(int i2) {
        this.fenceCreationMode = i2;
    }

    public final void setGeoFenceBody(@NotNull GeoFenceBody geoFenceBody) {
        Intrinsics.checkNotNullParameter(geoFenceBody, "<set-?>");
        this.geoFenceBody = geoFenceBody;
    }

    public final void setGeoFenceGetResponse(@Nullable GetGeoFenceResponseItem getGeoFenceResponseItem) {
        this.geoFenceGetResponse = getGeoFenceResponseItem;
    }

    public final void setGeoFenceType(@Nullable GeoFenceType geoFenceType) {
        this.geoFenceType = geoFenceType;
    }

    @NotNull
    public String toString() {
        return "GeoFenceCommonModel(geoFenceType=" + this.geoFenceType + ", currentLocation=" + this.currentLocation + ", carLatLng=" + this.carLatLng + ", geoFenceBody=" + this.geoFenceBody + ", geoFenceGetResponse=" + this.geoFenceGetResponse + ", fenceCreationMode=" + this.fenceCreationMode + ')';
    }
}
