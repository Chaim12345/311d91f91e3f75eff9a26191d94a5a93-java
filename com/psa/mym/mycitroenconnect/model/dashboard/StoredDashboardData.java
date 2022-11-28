package com.psa.mym.mycitroenconnect.model.dashboard;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Parcelize
/* loaded from: classes3.dex */
public final class StoredDashboardData implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<StoredDashboardData> CREATOR = new Creator();
    @NotNull
    private String acSwitchStatus;
    @NotNull
    private String carAddress;
    private double carLat;
    private double carLong;
    @NotNull
    private String distanceToEmpty;
    @NotNull
    private String fuelLevel;
    @NotNull
    private String ignitionStatus;
    @NotNull
    private String stateOfCharge;
    private double tboxSignalStrength;
    @NotNull
    private String temperature;
    @NotNull
    private String updatedTimeStamp;
    @NotNull
    private String vehicleLockedStatus;
    private int weatherIcon;

    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<StoredDashboardData> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final StoredDashboardData createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new StoredDashboardData(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readString(), parcel.readString(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final StoredDashboardData[] newArray(int i2) {
            return new StoredDashboardData[i2];
        }
    }

    public StoredDashboardData() {
        this(null, null, null, null, null, null, null, 0.0d, 0.0d, 0.0d, null, null, 0, 8191, null);
    }

    public StoredDashboardData(@NotNull String distanceToEmpty, @NotNull String fuelLevel, @NotNull String stateOfCharge, @NotNull String updatedTimeStamp, @NotNull String ignitionStatus, @NotNull String acSwitchStatus, @NotNull String vehicleLockedStatus, double d2, double d3, double d4, @NotNull String carAddress, @NotNull String temperature, int i2) {
        Intrinsics.checkNotNullParameter(distanceToEmpty, "distanceToEmpty");
        Intrinsics.checkNotNullParameter(fuelLevel, "fuelLevel");
        Intrinsics.checkNotNullParameter(stateOfCharge, "stateOfCharge");
        Intrinsics.checkNotNullParameter(updatedTimeStamp, "updatedTimeStamp");
        Intrinsics.checkNotNullParameter(ignitionStatus, "ignitionStatus");
        Intrinsics.checkNotNullParameter(acSwitchStatus, "acSwitchStatus");
        Intrinsics.checkNotNullParameter(vehicleLockedStatus, "vehicleLockedStatus");
        Intrinsics.checkNotNullParameter(carAddress, "carAddress");
        Intrinsics.checkNotNullParameter(temperature, "temperature");
        this.distanceToEmpty = distanceToEmpty;
        this.fuelLevel = fuelLevel;
        this.stateOfCharge = stateOfCharge;
        this.updatedTimeStamp = updatedTimeStamp;
        this.ignitionStatus = ignitionStatus;
        this.acSwitchStatus = acSwitchStatus;
        this.vehicleLockedStatus = vehicleLockedStatus;
        this.tboxSignalStrength = d2;
        this.carLat = d3;
        this.carLong = d4;
        this.carAddress = carAddress;
        this.temperature = temperature;
        this.weatherIcon = i2;
    }

    public /* synthetic */ StoredDashboardData(String str, String str2, String str3, String str4, String str5, String str6, String str7, double d2, double d3, double d4, String str8, String str9, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "" : str, (i3 & 2) != 0 ? "" : str2, (i3 & 4) != 0 ? "" : str3, (i3 & 8) != 0 ? "" : str4, (i3 & 16) != 0 ? "" : str5, (i3 & 32) != 0 ? "" : str6, (i3 & 64) != 0 ? "" : str7, (i3 & 128) != 0 ? 0.0d : d2, (i3 & 256) != 0 ? 0.0d : d3, (i3 & 512) == 0 ? d4 : 0.0d, (i3 & 1024) != 0 ? "" : str8, (i3 & 2048) != 0 ? "" : str9, (i3 & 4096) != 0 ? -1 : i2);
    }

    @NotNull
    public final String component1() {
        return this.distanceToEmpty;
    }

    public final double component10() {
        return this.carLong;
    }

    @NotNull
    public final String component11() {
        return this.carAddress;
    }

    @NotNull
    public final String component12() {
        return this.temperature;
    }

    public final int component13() {
        return this.weatherIcon;
    }

    @NotNull
    public final String component2() {
        return this.fuelLevel;
    }

    @NotNull
    public final String component3() {
        return this.stateOfCharge;
    }

    @NotNull
    public final String component4() {
        return this.updatedTimeStamp;
    }

    @NotNull
    public final String component5() {
        return this.ignitionStatus;
    }

    @NotNull
    public final String component6() {
        return this.acSwitchStatus;
    }

    @NotNull
    public final String component7() {
        return this.vehicleLockedStatus;
    }

    public final double component8() {
        return this.tboxSignalStrength;
    }

    public final double component9() {
        return this.carLat;
    }

    @NotNull
    public final StoredDashboardData copy(@NotNull String distanceToEmpty, @NotNull String fuelLevel, @NotNull String stateOfCharge, @NotNull String updatedTimeStamp, @NotNull String ignitionStatus, @NotNull String acSwitchStatus, @NotNull String vehicleLockedStatus, double d2, double d3, double d4, @NotNull String carAddress, @NotNull String temperature, int i2) {
        Intrinsics.checkNotNullParameter(distanceToEmpty, "distanceToEmpty");
        Intrinsics.checkNotNullParameter(fuelLevel, "fuelLevel");
        Intrinsics.checkNotNullParameter(stateOfCharge, "stateOfCharge");
        Intrinsics.checkNotNullParameter(updatedTimeStamp, "updatedTimeStamp");
        Intrinsics.checkNotNullParameter(ignitionStatus, "ignitionStatus");
        Intrinsics.checkNotNullParameter(acSwitchStatus, "acSwitchStatus");
        Intrinsics.checkNotNullParameter(vehicleLockedStatus, "vehicleLockedStatus");
        Intrinsics.checkNotNullParameter(carAddress, "carAddress");
        Intrinsics.checkNotNullParameter(temperature, "temperature");
        return new StoredDashboardData(distanceToEmpty, fuelLevel, stateOfCharge, updatedTimeStamp, ignitionStatus, acSwitchStatus, vehicleLockedStatus, d2, d3, d4, carAddress, temperature, i2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof StoredDashboardData) {
            StoredDashboardData storedDashboardData = (StoredDashboardData) obj;
            return Intrinsics.areEqual(this.distanceToEmpty, storedDashboardData.distanceToEmpty) && Intrinsics.areEqual(this.fuelLevel, storedDashboardData.fuelLevel) && Intrinsics.areEqual(this.stateOfCharge, storedDashboardData.stateOfCharge) && Intrinsics.areEqual(this.updatedTimeStamp, storedDashboardData.updatedTimeStamp) && Intrinsics.areEqual(this.ignitionStatus, storedDashboardData.ignitionStatus) && Intrinsics.areEqual(this.acSwitchStatus, storedDashboardData.acSwitchStatus) && Intrinsics.areEqual(this.vehicleLockedStatus, storedDashboardData.vehicleLockedStatus) && Intrinsics.areEqual((Object) Double.valueOf(this.tboxSignalStrength), (Object) Double.valueOf(storedDashboardData.tboxSignalStrength)) && Intrinsics.areEqual((Object) Double.valueOf(this.carLat), (Object) Double.valueOf(storedDashboardData.carLat)) && Intrinsics.areEqual((Object) Double.valueOf(this.carLong), (Object) Double.valueOf(storedDashboardData.carLong)) && Intrinsics.areEqual(this.carAddress, storedDashboardData.carAddress) && Intrinsics.areEqual(this.temperature, storedDashboardData.temperature) && this.weatherIcon == storedDashboardData.weatherIcon;
        }
        return false;
    }

    @NotNull
    public final String getAcSwitchStatus() {
        return this.acSwitchStatus;
    }

    @NotNull
    public final String getCarAddress() {
        return this.carAddress;
    }

    public final double getCarLat() {
        return this.carLat;
    }

    public final double getCarLong() {
        return this.carLong;
    }

    @NotNull
    public final String getDistanceToEmpty() {
        return this.distanceToEmpty;
    }

    @NotNull
    public final String getFuelLevel() {
        return this.fuelLevel;
    }

    @NotNull
    public final String getIgnitionStatus() {
        return this.ignitionStatus;
    }

    @NotNull
    public final String getStateOfCharge() {
        return this.stateOfCharge;
    }

    public final double getTboxSignalStrength() {
        return this.tboxSignalStrength;
    }

    @NotNull
    public final String getTemperature() {
        return this.temperature;
    }

    @NotNull
    public final String getUpdatedTimeStamp() {
        return this.updatedTimeStamp;
    }

    @NotNull
    public final String getVehicleLockedStatus() {
        return this.vehicleLockedStatus;
    }

    public final int getWeatherIcon() {
        return this.weatherIcon;
    }

    public int hashCode() {
        return (((((((((((((((((((((((this.distanceToEmpty.hashCode() * 31) + this.fuelLevel.hashCode()) * 31) + this.stateOfCharge.hashCode()) * 31) + this.updatedTimeStamp.hashCode()) * 31) + this.ignitionStatus.hashCode()) * 31) + this.acSwitchStatus.hashCode()) * 31) + this.vehicleLockedStatus.hashCode()) * 31) + Double.hashCode(this.tboxSignalStrength)) * 31) + Double.hashCode(this.carLat)) * 31) + Double.hashCode(this.carLong)) * 31) + this.carAddress.hashCode()) * 31) + this.temperature.hashCode()) * 31) + Integer.hashCode(this.weatherIcon);
    }

    public final void setAcSwitchStatus(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.acSwitchStatus = str;
    }

    public final void setCarAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.carAddress = str;
    }

    public final void setCarLat(double d2) {
        this.carLat = d2;
    }

    public final void setCarLong(double d2) {
        this.carLong = d2;
    }

    public final void setDistanceToEmpty(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.distanceToEmpty = str;
    }

    public final void setFuelLevel(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.fuelLevel = str;
    }

    public final void setIgnitionStatus(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.ignitionStatus = str;
    }

    public final void setStateOfCharge(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.stateOfCharge = str;
    }

    public final void setTboxSignalStrength(double d2) {
        this.tboxSignalStrength = d2;
    }

    public final void setTemperature(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.temperature = str;
    }

    public final void setUpdatedTimeStamp(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.updatedTimeStamp = str;
    }

    public final void setVehicleLockedStatus(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vehicleLockedStatus = str;
    }

    public final void setWeatherIcon(int i2) {
        this.weatherIcon = i2;
    }

    @NotNull
    public String toString() {
        return "StoredDashboardData(distanceToEmpty=" + this.distanceToEmpty + ", fuelLevel=" + this.fuelLevel + ", stateOfCharge=" + this.stateOfCharge + ", updatedTimeStamp=" + this.updatedTimeStamp + ", ignitionStatus=" + this.ignitionStatus + ", acSwitchStatus=" + this.acSwitchStatus + ", vehicleLockedStatus=" + this.vehicleLockedStatus + ", tboxSignalStrength=" + this.tboxSignalStrength + ", carLat=" + this.carLat + ", carLong=" + this.carLong + ", carAddress=" + this.carAddress + ", temperature=" + this.temperature + ", weatherIcon=" + this.weatherIcon + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.distanceToEmpty);
        out.writeString(this.fuelLevel);
        out.writeString(this.stateOfCharge);
        out.writeString(this.updatedTimeStamp);
        out.writeString(this.ignitionStatus);
        out.writeString(this.acSwitchStatus);
        out.writeString(this.vehicleLockedStatus);
        out.writeDouble(this.tboxSignalStrength);
        out.writeDouble(this.carLat);
        out.writeDouble(this.carLong);
        out.writeString(this.carAddress);
        out.writeString(this.temperature);
        out.writeInt(this.weatherIcon);
    }
}
