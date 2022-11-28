package com.psa.mym.mycitroenconnect.model.dashboard;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class DashboardDetailsResponse {
    @SerializedName("batteryEnergy")
    private int batteryEnergy;
    @SerializedName("batterySerialNo")
    @NotNull
    private String batterySerialNo;
    @SerializedName("ChargingType")
    private int chargingType;
    @SerializedName("createdTimeStamp")
    @NotNull
    private String createdTimeStamp;
    @SerializedName("DistanceToEmpty")
    private int distanceToEmpty;
    @SerializedName("PlugState")
    private int plugState;
    @SerializedName("RechargeHMIState")
    private int rechargeHmiState;
    @SerializedName("RestBattEnergy")
    private int restBattEnergy;
    @SerializedName("SOHCapacity")
    private int sohCapacity;
    @SerializedName("stateOfCharge")
    private int stateOfCharge;
    @SerializedName("Status")
    private char status;
    @SerializedName("TimeToFullCharge")
    private int timeToFullCharge;
    @SerializedName("updatedTimeStamp")
    @NotNull
    private String updatedTimeStamp;

    public DashboardDetailsResponse() {
        this(null, 0, 0, 0, 0, 0, 0, 0, 0, 0, (char) 0, null, null, 8191, null);
    }

    public DashboardDetailsResponse(@NotNull String batterySerialNo, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, char c2, @NotNull String createdTimeStamp, @NotNull String updatedTimeStamp) {
        Intrinsics.checkNotNullParameter(batterySerialNo, "batterySerialNo");
        Intrinsics.checkNotNullParameter(createdTimeStamp, "createdTimeStamp");
        Intrinsics.checkNotNullParameter(updatedTimeStamp, "updatedTimeStamp");
        this.batterySerialNo = batterySerialNo;
        this.stateOfCharge = i2;
        this.sohCapacity = i3;
        this.chargingType = i4;
        this.batteryEnergy = i5;
        this.restBattEnergy = i6;
        this.timeToFullCharge = i7;
        this.rechargeHmiState = i8;
        this.distanceToEmpty = i9;
        this.plugState = i10;
        this.status = c2;
        this.createdTimeStamp = createdTimeStamp;
        this.updatedTimeStamp = updatedTimeStamp;
    }

    public /* synthetic */ DashboardDetailsResponse(String str, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, char c2, String str2, String str3, int i11, DefaultConstructorMarker defaultConstructorMarker) {
        this((i11 & 1) != 0 ? "" : str, (i11 & 2) != 0 ? 0 : i2, (i11 & 4) != 0 ? 0 : i3, (i11 & 8) != 0 ? 0 : i4, (i11 & 16) != 0 ? 0 : i5, (i11 & 32) != 0 ? 0 : i6, (i11 & 64) != 0 ? 0 : i7, (i11 & 128) != 0 ? 0 : i8, (i11 & 256) != 0 ? 0 : i9, (i11 & 512) == 0 ? i10 : 0, (i11 & 1024) != 0 ? 'A' : c2, (i11 & 2048) != 0 ? "" : str2, (i11 & 4096) == 0 ? str3 : "");
    }

    @NotNull
    public final String component1() {
        return this.batterySerialNo;
    }

    public final int component10() {
        return this.plugState;
    }

    public final char component11() {
        return this.status;
    }

    @NotNull
    public final String component12() {
        return this.createdTimeStamp;
    }

    @NotNull
    public final String component13() {
        return this.updatedTimeStamp;
    }

    public final int component2() {
        return this.stateOfCharge;
    }

    public final int component3() {
        return this.sohCapacity;
    }

    public final int component4() {
        return this.chargingType;
    }

    public final int component5() {
        return this.batteryEnergy;
    }

    public final int component6() {
        return this.restBattEnergy;
    }

    public final int component7() {
        return this.timeToFullCharge;
    }

    public final int component8() {
        return this.rechargeHmiState;
    }

    public final int component9() {
        return this.distanceToEmpty;
    }

    @NotNull
    public final DashboardDetailsResponse copy(@NotNull String batterySerialNo, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, char c2, @NotNull String createdTimeStamp, @NotNull String updatedTimeStamp) {
        Intrinsics.checkNotNullParameter(batterySerialNo, "batterySerialNo");
        Intrinsics.checkNotNullParameter(createdTimeStamp, "createdTimeStamp");
        Intrinsics.checkNotNullParameter(updatedTimeStamp, "updatedTimeStamp");
        return new DashboardDetailsResponse(batterySerialNo, i2, i3, i4, i5, i6, i7, i8, i9, i10, c2, createdTimeStamp, updatedTimeStamp);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DashboardDetailsResponse) {
            DashboardDetailsResponse dashboardDetailsResponse = (DashboardDetailsResponse) obj;
            return Intrinsics.areEqual(this.batterySerialNo, dashboardDetailsResponse.batterySerialNo) && this.stateOfCharge == dashboardDetailsResponse.stateOfCharge && this.sohCapacity == dashboardDetailsResponse.sohCapacity && this.chargingType == dashboardDetailsResponse.chargingType && this.batteryEnergy == dashboardDetailsResponse.batteryEnergy && this.restBattEnergy == dashboardDetailsResponse.restBattEnergy && this.timeToFullCharge == dashboardDetailsResponse.timeToFullCharge && this.rechargeHmiState == dashboardDetailsResponse.rechargeHmiState && this.distanceToEmpty == dashboardDetailsResponse.distanceToEmpty && this.plugState == dashboardDetailsResponse.plugState && this.status == dashboardDetailsResponse.status && Intrinsics.areEqual(this.createdTimeStamp, dashboardDetailsResponse.createdTimeStamp) && Intrinsics.areEqual(this.updatedTimeStamp, dashboardDetailsResponse.updatedTimeStamp);
        }
        return false;
    }

    public final int getBatteryEnergy() {
        return this.batteryEnergy;
    }

    @NotNull
    public final String getBatterySerialNo() {
        return this.batterySerialNo;
    }

    public final int getChargingType() {
        return this.chargingType;
    }

    @NotNull
    public final String getCreatedTimeStamp() {
        return this.createdTimeStamp;
    }

    public final int getDistanceToEmpty() {
        return this.distanceToEmpty;
    }

    public final int getPlugState() {
        return this.plugState;
    }

    public final int getRechargeHmiState() {
        return this.rechargeHmiState;
    }

    public final int getRestBattEnergy() {
        return this.restBattEnergy;
    }

    public final int getSohCapacity() {
        return this.sohCapacity;
    }

    public final int getStateOfCharge() {
        return this.stateOfCharge;
    }

    public final char getStatus() {
        return this.status;
    }

    public final int getTimeToFullCharge() {
        return this.timeToFullCharge;
    }

    @NotNull
    public final String getUpdatedTimeStamp() {
        return this.updatedTimeStamp;
    }

    public int hashCode() {
        return (((((((((((((((((((((((this.batterySerialNo.hashCode() * 31) + Integer.hashCode(this.stateOfCharge)) * 31) + Integer.hashCode(this.sohCapacity)) * 31) + Integer.hashCode(this.chargingType)) * 31) + Integer.hashCode(this.batteryEnergy)) * 31) + Integer.hashCode(this.restBattEnergy)) * 31) + Integer.hashCode(this.timeToFullCharge)) * 31) + Integer.hashCode(this.rechargeHmiState)) * 31) + Integer.hashCode(this.distanceToEmpty)) * 31) + Integer.hashCode(this.plugState)) * 31) + Character.hashCode(this.status)) * 31) + this.createdTimeStamp.hashCode()) * 31) + this.updatedTimeStamp.hashCode();
    }

    public final void setBatteryEnergy(int i2) {
        this.batteryEnergy = i2;
    }

    public final void setBatterySerialNo(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.batterySerialNo = str;
    }

    public final void setChargingType(int i2) {
        this.chargingType = i2;
    }

    public final void setCreatedTimeStamp(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.createdTimeStamp = str;
    }

    public final void setDistanceToEmpty(int i2) {
        this.distanceToEmpty = i2;
    }

    public final void setPlugState(int i2) {
        this.plugState = i2;
    }

    public final void setRechargeHmiState(int i2) {
        this.rechargeHmiState = i2;
    }

    public final void setRestBattEnergy(int i2) {
        this.restBattEnergy = i2;
    }

    public final void setSohCapacity(int i2) {
        this.sohCapacity = i2;
    }

    public final void setStateOfCharge(int i2) {
        this.stateOfCharge = i2;
    }

    public final void setStatus(char c2) {
        this.status = c2;
    }

    public final void setTimeToFullCharge(int i2) {
        this.timeToFullCharge = i2;
    }

    public final void setUpdatedTimeStamp(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.updatedTimeStamp = str;
    }

    @NotNull
    public String toString() {
        return "DashboardDetailsResponse(batterySerialNo=" + this.batterySerialNo + ", stateOfCharge=" + this.stateOfCharge + ", sohCapacity=" + this.sohCapacity + ", chargingType=" + this.chargingType + ", batteryEnergy=" + this.batteryEnergy + ", restBattEnergy=" + this.restBattEnergy + ", timeToFullCharge=" + this.timeToFullCharge + ", rechargeHmiState=" + this.rechargeHmiState + ", distanceToEmpty=" + this.distanceToEmpty + ", plugState=" + this.plugState + ", status=" + this.status + ", createdTimeStamp=" + this.createdTimeStamp + ", updatedTimeStamp=" + this.updatedTimeStamp + ')';
    }
}
