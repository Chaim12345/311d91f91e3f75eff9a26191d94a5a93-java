package com.psa.mym.mycitroenconnect.model.trip;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FuelReportResponse {
    @SerializedName("distanceTravelled")
    private double distanceTravelled;
    @SerializedName("highEfficieny")
    private double highEfficieny;
    @SerializedName("idleEnergyConsumption")
    private double idleEnergyConsumption;
    @SerializedName("lowEfficieny")
    private double lowEfficieny;
    @SerializedName("mileage")
    private double mileage;
    @SerializedName("noOfEngineStart")
    private int noOfEngineStart;
    @SerializedName("noOfIdleInstance")
    private int noOfIdleInstance;
    @SerializedName("noOfTrips")
    private int noOfTrips;
    @SerializedName("totalEnergyConsumption")
    private double totalEnergyConsumption;
    @SerializedName("totalTimeTravelled")
    private double totalTimeTravelled;
    @SerializedName("vehicleId")
    @NotNull
    private String vehicleId;

    public FuelReportResponse() {
        this(0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0, 0, 0, 0.0d, null, 2047, null);
    }

    public FuelReportResponse(double d2, double d3, double d4, double d5, double d6, double d7, int i2, int i3, int i4, double d8, @NotNull String vehicleId) {
        Intrinsics.checkNotNullParameter(vehicleId, "vehicleId");
        this.distanceTravelled = d2;
        this.highEfficieny = d3;
        this.idleEnergyConsumption = d4;
        this.totalEnergyConsumption = d5;
        this.lowEfficieny = d6;
        this.mileage = d7;
        this.noOfIdleInstance = i2;
        this.noOfEngineStart = i3;
        this.noOfTrips = i4;
        this.totalTimeTravelled = d8;
        this.vehicleId = vehicleId;
    }

    public /* synthetic */ FuelReportResponse(double d2, double d3, double d4, double d5, double d6, double d7, int i2, int i3, int i4, double d8, String str, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? 0.0d : d2, (i5 & 2) != 0 ? 0.0d : d3, (i5 & 4) != 0 ? 0.0d : d4, (i5 & 8) != 0 ? 0.0d : d5, (i5 & 16) != 0 ? 0.0d : d6, (i5 & 32) != 0 ? 0.0d : d7, (i5 & 64) != 0 ? 0 : i2, (i5 & 128) != 0 ? 0 : i3, (i5 & 256) == 0 ? i4 : 0, (i5 & 512) != 0 ? 0.0d : d8, (i5 & 1024) != 0 ? "" : str);
    }

    public final double component1() {
        return this.distanceTravelled;
    }

    public final double component10() {
        return this.totalTimeTravelled;
    }

    @NotNull
    public final String component11() {
        return this.vehicleId;
    }

    public final double component2() {
        return this.highEfficieny;
    }

    public final double component3() {
        return this.idleEnergyConsumption;
    }

    public final double component4() {
        return this.totalEnergyConsumption;
    }

    public final double component5() {
        return this.lowEfficieny;
    }

    public final double component6() {
        return this.mileage;
    }

    public final int component7() {
        return this.noOfIdleInstance;
    }

    public final int component8() {
        return this.noOfEngineStart;
    }

    public final int component9() {
        return this.noOfTrips;
    }

    @NotNull
    public final FuelReportResponse copy(double d2, double d3, double d4, double d5, double d6, double d7, int i2, int i3, int i4, double d8, @NotNull String vehicleId) {
        Intrinsics.checkNotNullParameter(vehicleId, "vehicleId");
        return new FuelReportResponse(d2, d3, d4, d5, d6, d7, i2, i3, i4, d8, vehicleId);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FuelReportResponse) {
            FuelReportResponse fuelReportResponse = (FuelReportResponse) obj;
            return Intrinsics.areEqual((Object) Double.valueOf(this.distanceTravelled), (Object) Double.valueOf(fuelReportResponse.distanceTravelled)) && Intrinsics.areEqual((Object) Double.valueOf(this.highEfficieny), (Object) Double.valueOf(fuelReportResponse.highEfficieny)) && Intrinsics.areEqual((Object) Double.valueOf(this.idleEnergyConsumption), (Object) Double.valueOf(fuelReportResponse.idleEnergyConsumption)) && Intrinsics.areEqual((Object) Double.valueOf(this.totalEnergyConsumption), (Object) Double.valueOf(fuelReportResponse.totalEnergyConsumption)) && Intrinsics.areEqual((Object) Double.valueOf(this.lowEfficieny), (Object) Double.valueOf(fuelReportResponse.lowEfficieny)) && Intrinsics.areEqual((Object) Double.valueOf(this.mileage), (Object) Double.valueOf(fuelReportResponse.mileage)) && this.noOfIdleInstance == fuelReportResponse.noOfIdleInstance && this.noOfEngineStart == fuelReportResponse.noOfEngineStart && this.noOfTrips == fuelReportResponse.noOfTrips && Intrinsics.areEqual((Object) Double.valueOf(this.totalTimeTravelled), (Object) Double.valueOf(fuelReportResponse.totalTimeTravelled)) && Intrinsics.areEqual(this.vehicleId, fuelReportResponse.vehicleId);
        }
        return false;
    }

    public final double getDistanceTravelled() {
        return this.distanceTravelled;
    }

    public final double getHighEfficieny() {
        return this.highEfficieny;
    }

    public final double getIdleEnergyConsumption() {
        return this.idleEnergyConsumption;
    }

    public final double getLowEfficieny() {
        return this.lowEfficieny;
    }

    public final double getMileage() {
        return this.mileage;
    }

    public final int getNoOfEngineStart() {
        return this.noOfEngineStart;
    }

    public final int getNoOfIdleInstance() {
        return this.noOfIdleInstance;
    }

    public final int getNoOfTrips() {
        return this.noOfTrips;
    }

    public final double getTotalEnergyConsumption() {
        return this.totalEnergyConsumption;
    }

    public final double getTotalTimeTravelled() {
        return this.totalTimeTravelled;
    }

    @NotNull
    public final String getVehicleId() {
        return this.vehicleId;
    }

    public int hashCode() {
        return (((((((((((((((((((Double.hashCode(this.distanceTravelled) * 31) + Double.hashCode(this.highEfficieny)) * 31) + Double.hashCode(this.idleEnergyConsumption)) * 31) + Double.hashCode(this.totalEnergyConsumption)) * 31) + Double.hashCode(this.lowEfficieny)) * 31) + Double.hashCode(this.mileage)) * 31) + Integer.hashCode(this.noOfIdleInstance)) * 31) + Integer.hashCode(this.noOfEngineStart)) * 31) + Integer.hashCode(this.noOfTrips)) * 31) + Double.hashCode(this.totalTimeTravelled)) * 31) + this.vehicleId.hashCode();
    }

    public final void setDistanceTravelled(double d2) {
        this.distanceTravelled = d2;
    }

    public final void setHighEfficieny(double d2) {
        this.highEfficieny = d2;
    }

    public final void setIdleEnergyConsumption(double d2) {
        this.idleEnergyConsumption = d2;
    }

    public final void setLowEfficieny(double d2) {
        this.lowEfficieny = d2;
    }

    public final void setMileage(double d2) {
        this.mileage = d2;
    }

    public final void setNoOfEngineStart(int i2) {
        this.noOfEngineStart = i2;
    }

    public final void setNoOfIdleInstance(int i2) {
        this.noOfIdleInstance = i2;
    }

    public final void setNoOfTrips(int i2) {
        this.noOfTrips = i2;
    }

    public final void setTotalEnergyConsumption(double d2) {
        this.totalEnergyConsumption = d2;
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
        return "FuelReportResponse(distanceTravelled=" + this.distanceTravelled + ", highEfficieny=" + this.highEfficieny + ", idleEnergyConsumption=" + this.idleEnergyConsumption + ", totalEnergyConsumption=" + this.totalEnergyConsumption + ", lowEfficieny=" + this.lowEfficieny + ", mileage=" + this.mileage + ", noOfIdleInstance=" + this.noOfIdleInstance + ", noOfEngineStart=" + this.noOfEngineStart + ", noOfTrips=" + this.noOfTrips + ", totalTimeTravelled=" + this.totalTimeTravelled + ", vehicleId=" + this.vehicleId + ')';
    }
}
