package com.psa.mym.mycitroenconnect.model.dashboard;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class DashboardResponse {
    @SerializedName("batterySerialNo")
    @NotNull
    private String batterySerialNo;
    @SerializedName("chargingType")
    @NotNull
    private String chargingType;
    @SerializedName("createdTimeStamp")
    @NotNull
    private String createdTimeStamp;
    @SerializedName("distanceToEmpty")
    private int distanceToEmpty;
    @SerializedName("fuelLevel")
    private double fuelLevel;
    @SerializedName("plugState")
    @NotNull
    private String plugState;
    @SerializedName("stateOfCharge")
    private int stateOfCharge;
    @SerializedName("updatedTimeStamp")
    @NotNull
    private String updatedTimeStamp;

    public DashboardResponse() {
        this(null, null, null, 0, 0.0d, null, 0, null, 255, null);
    }

    public DashboardResponse(@NotNull String batterySerialNo, @NotNull String chargingType, @NotNull String createdTimeStamp, int i2, double d2, @NotNull String plugState, int i3, @NotNull String updatedTimeStamp) {
        Intrinsics.checkNotNullParameter(batterySerialNo, "batterySerialNo");
        Intrinsics.checkNotNullParameter(chargingType, "chargingType");
        Intrinsics.checkNotNullParameter(createdTimeStamp, "createdTimeStamp");
        Intrinsics.checkNotNullParameter(plugState, "plugState");
        Intrinsics.checkNotNullParameter(updatedTimeStamp, "updatedTimeStamp");
        this.batterySerialNo = batterySerialNo;
        this.chargingType = chargingType;
        this.createdTimeStamp = createdTimeStamp;
        this.distanceToEmpty = i2;
        this.fuelLevel = d2;
        this.plugState = plugState;
        this.stateOfCharge = i3;
        this.updatedTimeStamp = updatedTimeStamp;
    }

    public /* synthetic */ DashboardResponse(String str, String str2, String str3, int i2, double d2, String str4, int i3, String str5, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? "" : str, (i4 & 2) != 0 ? "" : str2, (i4 & 4) != 0 ? "" : str3, (i4 & 8) != 0 ? 0 : i2, (i4 & 16) != 0 ? 0.0d : d2, (i4 & 32) != 0 ? "" : str4, (i4 & 64) == 0 ? i3 : 0, (i4 & 128) == 0 ? str5 : "");
    }

    @NotNull
    public final String component1() {
        return this.batterySerialNo;
    }

    @NotNull
    public final String component2() {
        return this.chargingType;
    }

    @NotNull
    public final String component3() {
        return this.createdTimeStamp;
    }

    public final int component4() {
        return this.distanceToEmpty;
    }

    public final double component5() {
        return this.fuelLevel;
    }

    @NotNull
    public final String component6() {
        return this.plugState;
    }

    public final int component7() {
        return this.stateOfCharge;
    }

    @NotNull
    public final String component8() {
        return this.updatedTimeStamp;
    }

    @NotNull
    public final DashboardResponse copy(@NotNull String batterySerialNo, @NotNull String chargingType, @NotNull String createdTimeStamp, int i2, double d2, @NotNull String plugState, int i3, @NotNull String updatedTimeStamp) {
        Intrinsics.checkNotNullParameter(batterySerialNo, "batterySerialNo");
        Intrinsics.checkNotNullParameter(chargingType, "chargingType");
        Intrinsics.checkNotNullParameter(createdTimeStamp, "createdTimeStamp");
        Intrinsics.checkNotNullParameter(plugState, "plugState");
        Intrinsics.checkNotNullParameter(updatedTimeStamp, "updatedTimeStamp");
        return new DashboardResponse(batterySerialNo, chargingType, createdTimeStamp, i2, d2, plugState, i3, updatedTimeStamp);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DashboardResponse) {
            DashboardResponse dashboardResponse = (DashboardResponse) obj;
            return Intrinsics.areEqual(this.batterySerialNo, dashboardResponse.batterySerialNo) && Intrinsics.areEqual(this.chargingType, dashboardResponse.chargingType) && Intrinsics.areEqual(this.createdTimeStamp, dashboardResponse.createdTimeStamp) && this.distanceToEmpty == dashboardResponse.distanceToEmpty && Intrinsics.areEqual((Object) Double.valueOf(this.fuelLevel), (Object) Double.valueOf(dashboardResponse.fuelLevel)) && Intrinsics.areEqual(this.plugState, dashboardResponse.plugState) && this.stateOfCharge == dashboardResponse.stateOfCharge && Intrinsics.areEqual(this.updatedTimeStamp, dashboardResponse.updatedTimeStamp);
        }
        return false;
    }

    @NotNull
    public final String getBatterySerialNo() {
        return this.batterySerialNo;
    }

    @NotNull
    public final String getChargingType() {
        return this.chargingType;
    }

    @NotNull
    public final String getCreatedTimeStamp() {
        return this.createdTimeStamp;
    }

    public final int getDistanceToEmpty() {
        return this.distanceToEmpty;
    }

    public final double getFuelLevel() {
        return this.fuelLevel;
    }

    @NotNull
    public final String getPlugState() {
        return this.plugState;
    }

    public final int getStateOfCharge() {
        return this.stateOfCharge;
    }

    @NotNull
    public final String getUpdatedTimeStamp() {
        return this.updatedTimeStamp;
    }

    public int hashCode() {
        return (((((((((((((this.batterySerialNo.hashCode() * 31) + this.chargingType.hashCode()) * 31) + this.createdTimeStamp.hashCode()) * 31) + Integer.hashCode(this.distanceToEmpty)) * 31) + Double.hashCode(this.fuelLevel)) * 31) + this.plugState.hashCode()) * 31) + Integer.hashCode(this.stateOfCharge)) * 31) + this.updatedTimeStamp.hashCode();
    }

    public final void setBatterySerialNo(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.batterySerialNo = str;
    }

    public final void setChargingType(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.chargingType = str;
    }

    public final void setCreatedTimeStamp(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.createdTimeStamp = str;
    }

    public final void setDistanceToEmpty(int i2) {
        this.distanceToEmpty = i2;
    }

    public final void setFuelLevel(double d2) {
        this.fuelLevel = d2;
    }

    public final void setPlugState(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.plugState = str;
    }

    public final void setStateOfCharge(int i2) {
        this.stateOfCharge = i2;
    }

    public final void setUpdatedTimeStamp(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.updatedTimeStamp = str;
    }

    @NotNull
    public String toString() {
        return "DashboardResponse(batterySerialNo=" + this.batterySerialNo + ", chargingType=" + this.chargingType + ", createdTimeStamp=" + this.createdTimeStamp + ", distanceToEmpty=" + this.distanceToEmpty + ", fuelLevel=" + this.fuelLevel + ", plugState=" + this.plugState + ", stateOfCharge=" + this.stateOfCharge + ", updatedTimeStamp=" + this.updatedTimeStamp + ')';
    }
}
