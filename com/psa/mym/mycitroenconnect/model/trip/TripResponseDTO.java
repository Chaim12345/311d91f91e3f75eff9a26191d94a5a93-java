package com.psa.mym.mycitroenconnect.model.trip;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class TripResponseDTO {
    @SerializedName("distanceTravelled")
    private double distanceTravelled;
    @SerializedName("totalTimeTravelled")
    private double totalTimeTravelled;
    @NotNull
    private transient String tripDestName;
    @SerializedName("tripEndDate")
    @NotNull
    private String tripEndDate;
    @SerializedName("tripEndLocation")
    @NotNull
    private String tripEndLocation;
    @SerializedName("tripId")
    @NotNull
    private String tripId;
    @SerializedName("tripName")
    @NotNull
    private String tripName;
    @NotNull
    private transient String tripSourceName;
    @SerializedName("tripStartDate")
    @NotNull
    private String tripStartDate;
    @SerializedName("tripStartLocation")
    @NotNull
    private String tripStartLocation;
    @SerializedName("vehicleId")
    @NotNull
    private String vehicleId;
    private transient int viewType;

    public TripResponseDTO() {
        this(0.0d, 0.0d, null, null, null, null, null, null, null, 0, null, null, 4095, null);
    }

    public TripResponseDTO(double d2, double d3, @NotNull String tripEndDate, @NotNull String tripEndLocation, @NotNull String tripId, @NotNull String tripName, @NotNull String tripStartDate, @NotNull String tripStartLocation, @NotNull String vehicleId, int i2, @NotNull String tripSourceName, @NotNull String tripDestName) {
        Intrinsics.checkNotNullParameter(tripEndDate, "tripEndDate");
        Intrinsics.checkNotNullParameter(tripEndLocation, "tripEndLocation");
        Intrinsics.checkNotNullParameter(tripId, "tripId");
        Intrinsics.checkNotNullParameter(tripName, "tripName");
        Intrinsics.checkNotNullParameter(tripStartDate, "tripStartDate");
        Intrinsics.checkNotNullParameter(tripStartLocation, "tripStartLocation");
        Intrinsics.checkNotNullParameter(vehicleId, "vehicleId");
        Intrinsics.checkNotNullParameter(tripSourceName, "tripSourceName");
        Intrinsics.checkNotNullParameter(tripDestName, "tripDestName");
        this.distanceTravelled = d2;
        this.totalTimeTravelled = d3;
        this.tripEndDate = tripEndDate;
        this.tripEndLocation = tripEndLocation;
        this.tripId = tripId;
        this.tripName = tripName;
        this.tripStartDate = tripStartDate;
        this.tripStartLocation = tripStartLocation;
        this.vehicleId = vehicleId;
        this.viewType = i2;
        this.tripSourceName = tripSourceName;
        this.tripDestName = tripDestName;
    }

    public /* synthetic */ TripResponseDTO(double d2, double d3, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2, String str8, String str9, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0.0d : d2, (i3 & 2) == 0 ? d3 : 0.0d, (i3 & 4) != 0 ? "" : str, (i3 & 8) != 0 ? "" : str2, (i3 & 16) != 0 ? "" : str3, (i3 & 32) != 0 ? "" : str4, (i3 & 64) != 0 ? "" : str5, (i3 & 128) != 0 ? "" : str6, (i3 & 256) != 0 ? "" : str7, (i3 & 512) != 0 ? 1 : i2, (i3 & 1024) != 0 ? "" : str8, (i3 & 2048) == 0 ? str9 : "");
    }

    public final double component1() {
        return this.distanceTravelled;
    }

    public final int component10() {
        return this.viewType;
    }

    @NotNull
    public final String component11() {
        return this.tripSourceName;
    }

    @NotNull
    public final String component12() {
        return this.tripDestName;
    }

    public final double component2() {
        return this.totalTimeTravelled;
    }

    @NotNull
    public final String component3() {
        return this.tripEndDate;
    }

    @NotNull
    public final String component4() {
        return this.tripEndLocation;
    }

    @NotNull
    public final String component5() {
        return this.tripId;
    }

    @NotNull
    public final String component6() {
        return this.tripName;
    }

    @NotNull
    public final String component7() {
        return this.tripStartDate;
    }

    @NotNull
    public final String component8() {
        return this.tripStartLocation;
    }

    @NotNull
    public final String component9() {
        return this.vehicleId;
    }

    @NotNull
    public final TripResponseDTO copy(double d2, double d3, @NotNull String tripEndDate, @NotNull String tripEndLocation, @NotNull String tripId, @NotNull String tripName, @NotNull String tripStartDate, @NotNull String tripStartLocation, @NotNull String vehicleId, int i2, @NotNull String tripSourceName, @NotNull String tripDestName) {
        Intrinsics.checkNotNullParameter(tripEndDate, "tripEndDate");
        Intrinsics.checkNotNullParameter(tripEndLocation, "tripEndLocation");
        Intrinsics.checkNotNullParameter(tripId, "tripId");
        Intrinsics.checkNotNullParameter(tripName, "tripName");
        Intrinsics.checkNotNullParameter(tripStartDate, "tripStartDate");
        Intrinsics.checkNotNullParameter(tripStartLocation, "tripStartLocation");
        Intrinsics.checkNotNullParameter(vehicleId, "vehicleId");
        Intrinsics.checkNotNullParameter(tripSourceName, "tripSourceName");
        Intrinsics.checkNotNullParameter(tripDestName, "tripDestName");
        return new TripResponseDTO(d2, d3, tripEndDate, tripEndLocation, tripId, tripName, tripStartDate, tripStartLocation, vehicleId, i2, tripSourceName, tripDestName);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TripResponseDTO) {
            TripResponseDTO tripResponseDTO = (TripResponseDTO) obj;
            return Intrinsics.areEqual((Object) Double.valueOf(this.distanceTravelled), (Object) Double.valueOf(tripResponseDTO.distanceTravelled)) && Intrinsics.areEqual((Object) Double.valueOf(this.totalTimeTravelled), (Object) Double.valueOf(tripResponseDTO.totalTimeTravelled)) && Intrinsics.areEqual(this.tripEndDate, tripResponseDTO.tripEndDate) && Intrinsics.areEqual(this.tripEndLocation, tripResponseDTO.tripEndLocation) && Intrinsics.areEqual(this.tripId, tripResponseDTO.tripId) && Intrinsics.areEqual(this.tripName, tripResponseDTO.tripName) && Intrinsics.areEqual(this.tripStartDate, tripResponseDTO.tripStartDate) && Intrinsics.areEqual(this.tripStartLocation, tripResponseDTO.tripStartLocation) && Intrinsics.areEqual(this.vehicleId, tripResponseDTO.vehicleId) && this.viewType == tripResponseDTO.viewType && Intrinsics.areEqual(this.tripSourceName, tripResponseDTO.tripSourceName) && Intrinsics.areEqual(this.tripDestName, tripResponseDTO.tripDestName);
        }
        return false;
    }

    public final double getDistanceTravelled() {
        return this.distanceTravelled;
    }

    public final double getTotalTimeTravelled() {
        return this.totalTimeTravelled;
    }

    @NotNull
    public final String getTripDestName() {
        return this.tripDestName;
    }

    @NotNull
    public final String getTripEndDate() {
        return this.tripEndDate;
    }

    @NotNull
    public final String getTripEndLocation() {
        return this.tripEndLocation;
    }

    @NotNull
    public final String getTripId() {
        return this.tripId;
    }

    @NotNull
    public final String getTripName() {
        return this.tripName;
    }

    @NotNull
    public final String getTripSourceName() {
        return this.tripSourceName;
    }

    @NotNull
    public final String getTripStartDate() {
        return this.tripStartDate;
    }

    @NotNull
    public final String getTripStartLocation() {
        return this.tripStartLocation;
    }

    @NotNull
    public final String getVehicleId() {
        return this.vehicleId;
    }

    public final int getViewType() {
        return this.viewType;
    }

    public int hashCode() {
        return (((((((((((((((((((((Double.hashCode(this.distanceTravelled) * 31) + Double.hashCode(this.totalTimeTravelled)) * 31) + this.tripEndDate.hashCode()) * 31) + this.tripEndLocation.hashCode()) * 31) + this.tripId.hashCode()) * 31) + this.tripName.hashCode()) * 31) + this.tripStartDate.hashCode()) * 31) + this.tripStartLocation.hashCode()) * 31) + this.vehicleId.hashCode()) * 31) + Integer.hashCode(this.viewType)) * 31) + this.tripSourceName.hashCode()) * 31) + this.tripDestName.hashCode();
    }

    public final void setDistanceTravelled(double d2) {
        this.distanceTravelled = d2;
    }

    public final void setTotalTimeTravelled(double d2) {
        this.totalTimeTravelled = d2;
    }

    public final void setTripDestName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tripDestName = str;
    }

    public final void setTripEndDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tripEndDate = str;
    }

    public final void setTripEndLocation(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tripEndLocation = str;
    }

    public final void setTripId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tripId = str;
    }

    public final void setTripName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tripName = str;
    }

    public final void setTripSourceName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tripSourceName = str;
    }

    public final void setTripStartDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tripStartDate = str;
    }

    public final void setTripStartLocation(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tripStartLocation = str;
    }

    public final void setVehicleId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vehicleId = str;
    }

    public final void setViewType(int i2) {
        this.viewType = i2;
    }

    @NotNull
    public String toString() {
        return "TripResponseDTO(distanceTravelled=" + this.distanceTravelled + ", totalTimeTravelled=" + this.totalTimeTravelled + ", tripEndDate=" + this.tripEndDate + ", tripEndLocation=" + this.tripEndLocation + ", tripId=" + this.tripId + ", tripName=" + this.tripName + ", tripStartDate=" + this.tripStartDate + ", tripStartLocation=" + this.tripStartLocation + ", vehicleId=" + this.vehicleId + ", viewType=" + this.viewType + ", tripSourceName=" + this.tripSourceName + ", tripDestName=" + this.tripDestName + ')';
    }
}
