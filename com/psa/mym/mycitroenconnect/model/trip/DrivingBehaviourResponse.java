package com.psa.mym.mycitroenconnect.model.trip;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class DrivingBehaviourResponse {
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
    @SerializedName("idleTime")
    private double idleTime;
    @SerializedName("mileage")
    private double mileage;
    @SerializedName("noOfTrips")
    private int noOfTrips;
    @SerializedName("totalTimeTravelled")
    private double totalTimeTravelled;
    @SerializedName("vehicleId")
    @NotNull
    private String vehicleId;

    public DrivingBehaviourResponse() {
        this(0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0, 0.0d, null, 1023, null);
    }

    public DrivingBehaviourResponse(double d2, double d3, double d4, double d5, double d6, double d7, double d8, int i2, double d9, @NotNull String vehicleId) {
        Intrinsics.checkNotNullParameter(vehicleId, "vehicleId");
        this.aggressiveAcceleration = d2;
        this.avgVehicleSpeed = d3;
        this.distanceTravelled = d4;
        this.hardBrake = d5;
        this.highSpeed = d6;
        this.idleTime = d7;
        this.mileage = d8;
        this.noOfTrips = i2;
        this.totalTimeTravelled = d9;
        this.vehicleId = vehicleId;
    }

    public /* synthetic */ DrivingBehaviourResponse(double d2, double d3, double d4, double d5, double d6, double d7, double d8, int i2, double d9, String str, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0.0d : d2, (i3 & 2) != 0 ? 0.0d : d3, (i3 & 4) != 0 ? 0.0d : d4, (i3 & 8) != 0 ? 0.0d : d5, (i3 & 16) != 0 ? 0.0d : d6, (i3 & 32) != 0 ? 0.0d : d7, (i3 & 64) != 0 ? 0.0d : d8, (i3 & 128) != 0 ? 0 : i2, (i3 & 256) != 0 ? 0.0d : d9, (i3 & 512) != 0 ? "" : str);
    }

    public final double component1() {
        return this.aggressiveAcceleration;
    }

    @NotNull
    public final String component10() {
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
        return this.idleTime;
    }

    public final double component7() {
        return this.mileage;
    }

    public final int component8() {
        return this.noOfTrips;
    }

    public final double component9() {
        return this.totalTimeTravelled;
    }

    @NotNull
    public final DrivingBehaviourResponse copy(double d2, double d3, double d4, double d5, double d6, double d7, double d8, int i2, double d9, @NotNull String vehicleId) {
        Intrinsics.checkNotNullParameter(vehicleId, "vehicleId");
        return new DrivingBehaviourResponse(d2, d3, d4, d5, d6, d7, d8, i2, d9, vehicleId);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DrivingBehaviourResponse) {
            DrivingBehaviourResponse drivingBehaviourResponse = (DrivingBehaviourResponse) obj;
            return Intrinsics.areEqual((Object) Double.valueOf(this.aggressiveAcceleration), (Object) Double.valueOf(drivingBehaviourResponse.aggressiveAcceleration)) && Intrinsics.areEqual((Object) Double.valueOf(this.avgVehicleSpeed), (Object) Double.valueOf(drivingBehaviourResponse.avgVehicleSpeed)) && Intrinsics.areEqual((Object) Double.valueOf(this.distanceTravelled), (Object) Double.valueOf(drivingBehaviourResponse.distanceTravelled)) && Intrinsics.areEqual((Object) Double.valueOf(this.hardBrake), (Object) Double.valueOf(drivingBehaviourResponse.hardBrake)) && Intrinsics.areEqual((Object) Double.valueOf(this.highSpeed), (Object) Double.valueOf(drivingBehaviourResponse.highSpeed)) && Intrinsics.areEqual((Object) Double.valueOf(this.idleTime), (Object) Double.valueOf(drivingBehaviourResponse.idleTime)) && Intrinsics.areEqual((Object) Double.valueOf(this.mileage), (Object) Double.valueOf(drivingBehaviourResponse.mileage)) && this.noOfTrips == drivingBehaviourResponse.noOfTrips && Intrinsics.areEqual((Object) Double.valueOf(this.totalTimeTravelled), (Object) Double.valueOf(drivingBehaviourResponse.totalTimeTravelled)) && Intrinsics.areEqual(this.vehicleId, drivingBehaviourResponse.vehicleId);
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

    public final double getIdleTime() {
        return this.idleTime;
    }

    public final double getMileage() {
        return this.mileage;
    }

    public final int getNoOfTrips() {
        return this.noOfTrips;
    }

    public final double getTotalTimeTravelled() {
        return this.totalTimeTravelled;
    }

    @NotNull
    public final String getVehicleId() {
        return this.vehicleId;
    }

    public int hashCode() {
        return (((((((((((((((((Double.hashCode(this.aggressiveAcceleration) * 31) + Double.hashCode(this.avgVehicleSpeed)) * 31) + Double.hashCode(this.distanceTravelled)) * 31) + Double.hashCode(this.hardBrake)) * 31) + Double.hashCode(this.highSpeed)) * 31) + Double.hashCode(this.idleTime)) * 31) + Double.hashCode(this.mileage)) * 31) + Integer.hashCode(this.noOfTrips)) * 31) + Double.hashCode(this.totalTimeTravelled)) * 31) + this.vehicleId.hashCode();
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

    public final void setIdleTime(double d2) {
        this.idleTime = d2;
    }

    public final void setMileage(double d2) {
        this.mileage = d2;
    }

    public final void setNoOfTrips(int i2) {
        this.noOfTrips = i2;
    }

    public final void setTotalTimeTravelled(double d2) {
        this.totalTimeTravelled = d2;
    }

    public final void setVehicleId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vehicleId = str;
    }

    @NotNull
    public String toString() {
        return "DrivingBehaviourResponse(aggressiveAcceleration=" + this.aggressiveAcceleration + ", avgVehicleSpeed=" + this.avgVehicleSpeed + ", distanceTravelled=" + this.distanceTravelled + ", hardBrake=" + this.hardBrake + ", highSpeed=" + this.highSpeed + ", idleTime=" + this.idleTime + ", mileage=" + this.mileage + ", noOfTrips=" + this.noOfTrips + ", totalTimeTravelled=" + this.totalTimeTravelled + ", vehicleId=" + this.vehicleId + ')';
    }
}
