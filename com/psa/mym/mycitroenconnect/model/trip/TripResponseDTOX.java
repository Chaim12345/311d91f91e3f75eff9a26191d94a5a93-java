package com.psa.mym.mycitroenconnect.model.trip;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class TripResponseDTOX {
    @SerializedName("idleEnergyConsumption")
    private double idleEnergyConsumption;
    @SerializedName("idleTime")
    private double idleTime;
    @SerializedName("totalTimeTravelled")
    private double totalTimeTravelled;
    @SerializedName("tripId")
    @NotNull
    private String tripId;
    @SerializedName("tripName")
    @NotNull
    private String tripName;
    @SerializedName("tripStartDate")
    @NotNull
    private String tripStartDate;
    @SerializedName("vehicleId")
    @NotNull
    private String vehicleId;

    public TripResponseDTOX() {
        this(0.0d, 0.0d, 0.0d, null, null, null, null, 127, null);
    }

    public TripResponseDTOX(double d2, double d3, double d4, @NotNull String tripId, @NotNull String tripName, @NotNull String tripStartDate, @NotNull String vehicleId) {
        Intrinsics.checkNotNullParameter(tripId, "tripId");
        Intrinsics.checkNotNullParameter(tripName, "tripName");
        Intrinsics.checkNotNullParameter(tripStartDate, "tripStartDate");
        Intrinsics.checkNotNullParameter(vehicleId, "vehicleId");
        this.idleEnergyConsumption = d2;
        this.idleTime = d3;
        this.totalTimeTravelled = d4;
        this.tripId = tripId;
        this.tripName = tripName;
        this.tripStartDate = tripStartDate;
        this.vehicleId = vehicleId;
    }

    public /* synthetic */ TripResponseDTOX(double d2, double d3, double d4, String str, String str2, String str3, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0.0d : d2, (i2 & 2) != 0 ? 0.0d : d3, (i2 & 4) == 0 ? d4 : 0.0d, (i2 & 8) != 0 ? "" : str, (i2 & 16) != 0 ? "" : str2, (i2 & 32) != 0 ? "" : str3, (i2 & 64) == 0 ? str4 : "");
    }

    public final double component1() {
        return this.idleEnergyConsumption;
    }

    public final double component2() {
        return this.idleTime;
    }

    public final double component3() {
        return this.totalTimeTravelled;
    }

    @NotNull
    public final String component4() {
        return this.tripId;
    }

    @NotNull
    public final String component5() {
        return this.tripName;
    }

    @NotNull
    public final String component6() {
        return this.tripStartDate;
    }

    @NotNull
    public final String component7() {
        return this.vehicleId;
    }

    @NotNull
    public final TripResponseDTOX copy(double d2, double d3, double d4, @NotNull String tripId, @NotNull String tripName, @NotNull String tripStartDate, @NotNull String vehicleId) {
        Intrinsics.checkNotNullParameter(tripId, "tripId");
        Intrinsics.checkNotNullParameter(tripName, "tripName");
        Intrinsics.checkNotNullParameter(tripStartDate, "tripStartDate");
        Intrinsics.checkNotNullParameter(vehicleId, "vehicleId");
        return new TripResponseDTOX(d2, d3, d4, tripId, tripName, tripStartDate, vehicleId);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TripResponseDTOX) {
            TripResponseDTOX tripResponseDTOX = (TripResponseDTOX) obj;
            return Intrinsics.areEqual((Object) Double.valueOf(this.idleEnergyConsumption), (Object) Double.valueOf(tripResponseDTOX.idleEnergyConsumption)) && Intrinsics.areEqual((Object) Double.valueOf(this.idleTime), (Object) Double.valueOf(tripResponseDTOX.idleTime)) && Intrinsics.areEqual((Object) Double.valueOf(this.totalTimeTravelled), (Object) Double.valueOf(tripResponseDTOX.totalTimeTravelled)) && Intrinsics.areEqual(this.tripId, tripResponseDTOX.tripId) && Intrinsics.areEqual(this.tripName, tripResponseDTOX.tripName) && Intrinsics.areEqual(this.tripStartDate, tripResponseDTOX.tripStartDate) && Intrinsics.areEqual(this.vehicleId, tripResponseDTOX.vehicleId);
        }
        return false;
    }

    public final double getIdleEnergyConsumption() {
        return this.idleEnergyConsumption;
    }

    public final double getIdleTime() {
        return this.idleTime;
    }

    public final double getTotalTimeTravelled() {
        return this.totalTimeTravelled;
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
    public final String getTripStartDate() {
        return this.tripStartDate;
    }

    @NotNull
    public final String getVehicleId() {
        return this.vehicleId;
    }

    public int hashCode() {
        return (((((((((((Double.hashCode(this.idleEnergyConsumption) * 31) + Double.hashCode(this.idleTime)) * 31) + Double.hashCode(this.totalTimeTravelled)) * 31) + this.tripId.hashCode()) * 31) + this.tripName.hashCode()) * 31) + this.tripStartDate.hashCode()) * 31) + this.vehicleId.hashCode();
    }

    public final void setIdleEnergyConsumption(double d2) {
        this.idleEnergyConsumption = d2;
    }

    public final void setIdleTime(double d2) {
        this.idleTime = d2;
    }

    public final void setTotalTimeTravelled(double d2) {
        this.totalTimeTravelled = d2;
    }

    public final void setTripId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tripId = str;
    }

    public final void setTripName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tripName = str;
    }

    public final void setTripStartDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tripStartDate = str;
    }

    public final void setVehicleId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vehicleId = str;
    }

    @NotNull
    public String toString() {
        return "TripResponseDTOX(idleEnergyConsumption=" + this.idleEnergyConsumption + ", idleTime=" + this.idleTime + ", totalTimeTravelled=" + this.totalTimeTravelled + ", tripId=" + this.tripId + ", tripName=" + this.tripName + ", tripStartDate=" + this.tripStartDate + ", vehicleId=" + this.vehicleId + ')';
    }
}
