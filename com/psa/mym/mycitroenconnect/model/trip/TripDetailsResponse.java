package com.psa.mym.mycitroenconnect.model.trip;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class TripDetailsResponse {
    @SerializedName("aggressiveAcceleration")
    private double aggressiveAcceleration;
    @SerializedName("avgVehicleSpeed")
    private double avgVehicleSpeed;
    @SerializedName("distanceTravelled")
    private double distanceTravelled;
    @SerializedName("hardBrake")
    private double hardBrake;
    @SerializedName("highSpeed")
    private double highSpeed;
    @SerializedName("idleEnergyConsumption")
    private double idleEnergyConsumption;
    @SerializedName("idleTime")
    private double idleTime;
    @SerializedName("maxVehicleSpeed")
    private double maxVehicleSpeed;
    @SerializedName("mileage")
    private double mileage;
    @SerializedName("noOfIdleInstance")
    private int noOfIdleInstance;
    @SerializedName("totalEnergyConsumption")
    private double totalEnergyConsumption;
    @SerializedName("totalTimeTravelled")
    private double totalTimeTravelled;
    @SerializedName("tripEndDate")
    @NotNull
    private String tripEndDate;
    @SerializedName("tripId")
    @NotNull
    private String tripId;
    @SerializedName("tripName")
    @NotNull
    private String tripName;
    @SerializedName("tripRoute")
    @NotNull
    private List<String> tripRoute;
    @SerializedName("tripStartDate")
    @NotNull
    private String tripStartDate;
    @SerializedName("tripType")
    @NotNull
    private String tripType;
    @SerializedName("vehicleId")
    @NotNull
    private String vehicleId;

    public TripDetailsResponse() {
        this(0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0, 0.0d, 0.0d, null, null, null, null, null, null, null, 524287, null);
    }

    public TripDetailsResponse(double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, int i2, double d11, double d12, @NotNull String tripEndDate, @NotNull String tripId, @NotNull String tripName, @NotNull List<String> tripRoute, @NotNull String tripStartDate, @NotNull String tripType, @NotNull String vehicleId) {
        Intrinsics.checkNotNullParameter(tripEndDate, "tripEndDate");
        Intrinsics.checkNotNullParameter(tripId, "tripId");
        Intrinsics.checkNotNullParameter(tripName, "tripName");
        Intrinsics.checkNotNullParameter(tripRoute, "tripRoute");
        Intrinsics.checkNotNullParameter(tripStartDate, "tripStartDate");
        Intrinsics.checkNotNullParameter(tripType, "tripType");
        Intrinsics.checkNotNullParameter(vehicleId, "vehicleId");
        this.aggressiveAcceleration = d2;
        this.avgVehicleSpeed = d3;
        this.distanceTravelled = d4;
        this.hardBrake = d5;
        this.highSpeed = d6;
        this.idleEnergyConsumption = d7;
        this.idleTime = d8;
        this.maxVehicleSpeed = d9;
        this.mileage = d10;
        this.noOfIdleInstance = i2;
        this.totalEnergyConsumption = d11;
        this.totalTimeTravelled = d12;
        this.tripEndDate = tripEndDate;
        this.tripId = tripId;
        this.tripName = tripName;
        this.tripRoute = tripRoute;
        this.tripStartDate = tripStartDate;
        this.tripType = tripType;
        this.vehicleId = vehicleId;
    }

    public /* synthetic */ TripDetailsResponse(double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, int i2, double d11, double d12, String str, String str2, String str3, List list, String str4, String str5, String str6, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0.0d : d2, (i3 & 2) != 0 ? 0.0d : d3, (i3 & 4) != 0 ? 0.0d : d4, (i3 & 8) != 0 ? 0.0d : d5, (i3 & 16) != 0 ? 0.0d : d6, (i3 & 32) != 0 ? 0.0d : d7, (i3 & 64) != 0 ? 0.0d : d8, (i3 & 128) != 0 ? 0.0d : d9, (i3 & 256) != 0 ? 0.0d : d10, (i3 & 512) != 0 ? 0 : i2, (i3 & 1024) != 0 ? 0.0d : d11, (i3 & 2048) != 0 ? 0.0d : d12, (i3 & 4096) != 0 ? "" : str, (i3 & 8192) != 0 ? "" : str2, (i3 & 16384) != 0 ? "" : str3, (i3 & 32768) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list, (i3 & 65536) != 0 ? "" : str4, (i3 & 131072) != 0 ? "" : str5, (i3 & 262144) != 0 ? "" : str6);
    }

    public final double component1() {
        return this.aggressiveAcceleration;
    }

    public final int component10() {
        return this.noOfIdleInstance;
    }

    public final double component11() {
        return this.totalEnergyConsumption;
    }

    public final double component12() {
        return this.totalTimeTravelled;
    }

    @NotNull
    public final String component13() {
        return this.tripEndDate;
    }

    @NotNull
    public final String component14() {
        return this.tripId;
    }

    @NotNull
    public final String component15() {
        return this.tripName;
    }

    @NotNull
    public final List<String> component16() {
        return this.tripRoute;
    }

    @NotNull
    public final String component17() {
        return this.tripStartDate;
    }

    @NotNull
    public final String component18() {
        return this.tripType;
    }

    @NotNull
    public final String component19() {
        return this.vehicleId;
    }

    public final double component2() {
        return this.avgVehicleSpeed;
    }

    public final double component3() {
        return this.distanceTravelled;
    }

    public final double component4() {
        return this.hardBrake;
    }

    public final double component5() {
        return this.highSpeed;
    }

    public final double component6() {
        return this.idleEnergyConsumption;
    }

    public final double component7() {
        return this.idleTime;
    }

    public final double component8() {
        return this.maxVehicleSpeed;
    }

    public final double component9() {
        return this.mileage;
    }

    @NotNull
    public final TripDetailsResponse copy(double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, int i2, double d11, double d12, @NotNull String tripEndDate, @NotNull String tripId, @NotNull String tripName, @NotNull List<String> tripRoute, @NotNull String tripStartDate, @NotNull String tripType, @NotNull String vehicleId) {
        Intrinsics.checkNotNullParameter(tripEndDate, "tripEndDate");
        Intrinsics.checkNotNullParameter(tripId, "tripId");
        Intrinsics.checkNotNullParameter(tripName, "tripName");
        Intrinsics.checkNotNullParameter(tripRoute, "tripRoute");
        Intrinsics.checkNotNullParameter(tripStartDate, "tripStartDate");
        Intrinsics.checkNotNullParameter(tripType, "tripType");
        Intrinsics.checkNotNullParameter(vehicleId, "vehicleId");
        return new TripDetailsResponse(d2, d3, d4, d5, d6, d7, d8, d9, d10, i2, d11, d12, tripEndDate, tripId, tripName, tripRoute, tripStartDate, tripType, vehicleId);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TripDetailsResponse) {
            TripDetailsResponse tripDetailsResponse = (TripDetailsResponse) obj;
            return Intrinsics.areEqual((Object) Double.valueOf(this.aggressiveAcceleration), (Object) Double.valueOf(tripDetailsResponse.aggressiveAcceleration)) && Intrinsics.areEqual((Object) Double.valueOf(this.avgVehicleSpeed), (Object) Double.valueOf(tripDetailsResponse.avgVehicleSpeed)) && Intrinsics.areEqual((Object) Double.valueOf(this.distanceTravelled), (Object) Double.valueOf(tripDetailsResponse.distanceTravelled)) && Intrinsics.areEqual((Object) Double.valueOf(this.hardBrake), (Object) Double.valueOf(tripDetailsResponse.hardBrake)) && Intrinsics.areEqual((Object) Double.valueOf(this.highSpeed), (Object) Double.valueOf(tripDetailsResponse.highSpeed)) && Intrinsics.areEqual((Object) Double.valueOf(this.idleEnergyConsumption), (Object) Double.valueOf(tripDetailsResponse.idleEnergyConsumption)) && Intrinsics.areEqual((Object) Double.valueOf(this.idleTime), (Object) Double.valueOf(tripDetailsResponse.idleTime)) && Intrinsics.areEqual((Object) Double.valueOf(this.maxVehicleSpeed), (Object) Double.valueOf(tripDetailsResponse.maxVehicleSpeed)) && Intrinsics.areEqual((Object) Double.valueOf(this.mileage), (Object) Double.valueOf(tripDetailsResponse.mileage)) && this.noOfIdleInstance == tripDetailsResponse.noOfIdleInstance && Intrinsics.areEqual((Object) Double.valueOf(this.totalEnergyConsumption), (Object) Double.valueOf(tripDetailsResponse.totalEnergyConsumption)) && Intrinsics.areEqual((Object) Double.valueOf(this.totalTimeTravelled), (Object) Double.valueOf(tripDetailsResponse.totalTimeTravelled)) && Intrinsics.areEqual(this.tripEndDate, tripDetailsResponse.tripEndDate) && Intrinsics.areEqual(this.tripId, tripDetailsResponse.tripId) && Intrinsics.areEqual(this.tripName, tripDetailsResponse.tripName) && Intrinsics.areEqual(this.tripRoute, tripDetailsResponse.tripRoute) && Intrinsics.areEqual(this.tripStartDate, tripDetailsResponse.tripStartDate) && Intrinsics.areEqual(this.tripType, tripDetailsResponse.tripType) && Intrinsics.areEqual(this.vehicleId, tripDetailsResponse.vehicleId);
        }
        return false;
    }

    public final double getAggressiveAcceleration() {
        return this.aggressiveAcceleration;
    }

    public final double getAvgVehicleSpeed() {
        return this.avgVehicleSpeed;
    }

    public final double getDistanceTravelled() {
        return this.distanceTravelled;
    }

    public final double getHardBrake() {
        return this.hardBrake;
    }

    public final double getHighSpeed() {
        return this.highSpeed;
    }

    public final double getIdleEnergyConsumption() {
        return this.idleEnergyConsumption;
    }

    public final double getIdleTime() {
        return this.idleTime;
    }

    public final double getMaxVehicleSpeed() {
        return this.maxVehicleSpeed;
    }

    public final double getMileage() {
        return this.mileage;
    }

    public final int getNoOfIdleInstance() {
        return this.noOfIdleInstance;
    }

    public final double getTotalEnergyConsumption() {
        return this.totalEnergyConsumption;
    }

    public final double getTotalTimeTravelled() {
        return this.totalTimeTravelled;
    }

    @NotNull
    public final String getTripEndDate() {
        return this.tripEndDate;
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
    public final List<String> getTripRoute() {
        return this.tripRoute;
    }

    @NotNull
    public final String getTripStartDate() {
        return this.tripStartDate;
    }

    @NotNull
    public final String getTripType() {
        return this.tripType;
    }

    @NotNull
    public final String getVehicleId() {
        return this.vehicleId;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((Double.hashCode(this.aggressiveAcceleration) * 31) + Double.hashCode(this.avgVehicleSpeed)) * 31) + Double.hashCode(this.distanceTravelled)) * 31) + Double.hashCode(this.hardBrake)) * 31) + Double.hashCode(this.highSpeed)) * 31) + Double.hashCode(this.idleEnergyConsumption)) * 31) + Double.hashCode(this.idleTime)) * 31) + Double.hashCode(this.maxVehicleSpeed)) * 31) + Double.hashCode(this.mileage)) * 31) + Integer.hashCode(this.noOfIdleInstance)) * 31) + Double.hashCode(this.totalEnergyConsumption)) * 31) + Double.hashCode(this.totalTimeTravelled)) * 31) + this.tripEndDate.hashCode()) * 31) + this.tripId.hashCode()) * 31) + this.tripName.hashCode()) * 31) + this.tripRoute.hashCode()) * 31) + this.tripStartDate.hashCode()) * 31) + this.tripType.hashCode()) * 31) + this.vehicleId.hashCode();
    }

    public final void setAggressiveAcceleration(double d2) {
        this.aggressiveAcceleration = d2;
    }

    public final void setAvgVehicleSpeed(double d2) {
        this.avgVehicleSpeed = d2;
    }

    public final void setDistanceTravelled(double d2) {
        this.distanceTravelled = d2;
    }

    public final void setHardBrake(double d2) {
        this.hardBrake = d2;
    }

    public final void setHighSpeed(double d2) {
        this.highSpeed = d2;
    }

    public final void setIdleEnergyConsumption(double d2) {
        this.idleEnergyConsumption = d2;
    }

    public final void setIdleTime(double d2) {
        this.idleTime = d2;
    }

    public final void setMaxVehicleSpeed(double d2) {
        this.maxVehicleSpeed = d2;
    }

    public final void setMileage(double d2) {
        this.mileage = d2;
    }

    public final void setNoOfIdleInstance(int i2) {
        this.noOfIdleInstance = i2;
    }

    public final void setTotalEnergyConsumption(double d2) {
        this.totalEnergyConsumption = d2;
    }

    public final void setTotalTimeTravelled(double d2) {
        this.totalTimeTravelled = d2;
    }

    public final void setTripEndDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tripEndDate = str;
    }

    public final void setTripId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tripId = str;
    }

    public final void setTripName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tripName = str;
    }

    public final void setTripRoute(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.tripRoute = list;
    }

    public final void setTripStartDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tripStartDate = str;
    }

    public final void setTripType(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tripType = str;
    }

    public final void setVehicleId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vehicleId = str;
    }

    @NotNull
    public String toString() {
        return "TripDetailsResponse(aggressiveAcceleration=" + this.aggressiveAcceleration + ", avgVehicleSpeed=" + this.avgVehicleSpeed + ", distanceTravelled=" + this.distanceTravelled + ", hardBrake=" + this.hardBrake + ", highSpeed=" + this.highSpeed + ", idleEnergyConsumption=" + this.idleEnergyConsumption + ", idleTime=" + this.idleTime + ", maxVehicleSpeed=" + this.maxVehicleSpeed + ", mileage=" + this.mileage + ", noOfIdleInstance=" + this.noOfIdleInstance + ", totalEnergyConsumption=" + this.totalEnergyConsumption + ", totalTimeTravelled=" + this.totalTimeTravelled + ", tripEndDate=" + this.tripEndDate + ", tripId=" + this.tripId + ", tripName=" + this.tripName + ", tripRoute=" + this.tripRoute + ", tripStartDate=" + this.tripStartDate + ", tripType=" + this.tripType + ", vehicleId=" + this.vehicleId + ')';
    }
}
