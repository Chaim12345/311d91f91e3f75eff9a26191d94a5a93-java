package com.psa.mym.mycitroenconnect.model.trip;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Parcelize
/* loaded from: classes3.dex */
public final class OnGoingResponse implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<OnGoingResponse> CREATOR = new Creator();
    @SerializedName("acceleratorPosition")
    private double acceleratorPosition;
    @SerializedName("aggressiveAcceleration")
    @Nullable
    private Integer aggressiveAcceleration;
    @SerializedName("createdTimeStamp")
    @NotNull
    private String createdTimeStamp;
    @SerializedName("distanceTravelled")
    private double distanceTravelled;
    @SerializedName("dte")
    private double dte;
    @SerializedName("energyLevel")
    private double energyLevel;
    @SerializedName("hardBrake")
    private double hardBrake;
    @SerializedName("highSpeed")
    private double highSpeed;
    @SerializedName("idleEnergyConsumption")
    private double idleEnergyConsumption;
    @SerializedName("idleTime")
    private double idleTime;
    @SerializedName("ignitionStatus")
    @Nullable
    private String ignitionStatus;
    @SerializedName("location")
    @NotNull
    private String location;
    @SerializedName("mileage")
    private double mileage;
    @SerializedName("totalTimeTravelled")
    private double totalTimeTravelled;
    @SerializedName("tripCurrentStatus")
    @NotNull
    private String tripCurrentStatus;
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
    @SerializedName("tripType")
    @NotNull
    private String tripType;
    @SerializedName("updatedTimeStamp")
    @NotNull
    private String updatedTimeStamp;
    @SerializedName("vehicleId")
    @NotNull
    private String vehicleId;
    @SerializedName("vehicleSpeed")
    private double vehicleSpeed;
    private transient int viewType;

    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<OnGoingResponse> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final OnGoingResponse createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new OnGoingResponse(parcel.readDouble(), parcel.readString(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readString(), parcel.readDouble(), parcel.readDouble(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readDouble(), parcel.readString(), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readString(), parcel.readInt(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final OnGoingResponse[] newArray(int i2) {
            return new OnGoingResponse[i2];
        }
    }

    public OnGoingResponse() {
        this(0.0d, null, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, null, 0.0d, 0.0d, null, null, null, null, null, null, 0.0d, null, null, null, 0, null, 16777215, null);
    }

    public OnGoingResponse(double d2, @NotNull String createdTimeStamp, double d3, double d4, double d5, double d6, double d7, double d8, double d9, @NotNull String location, double d10, double d11, @NotNull String tripId, @NotNull String tripName, @NotNull String tripStartDate, @NotNull String tripType, @NotNull String updatedTimeStamp, @NotNull String vehicleId, double d12, @NotNull String tripCurrentStatus, @Nullable Integer num, @Nullable String str, int i2, @NotNull String tripSourceName) {
        Intrinsics.checkNotNullParameter(createdTimeStamp, "createdTimeStamp");
        Intrinsics.checkNotNullParameter(location, "location");
        Intrinsics.checkNotNullParameter(tripId, "tripId");
        Intrinsics.checkNotNullParameter(tripName, "tripName");
        Intrinsics.checkNotNullParameter(tripStartDate, "tripStartDate");
        Intrinsics.checkNotNullParameter(tripType, "tripType");
        Intrinsics.checkNotNullParameter(updatedTimeStamp, "updatedTimeStamp");
        Intrinsics.checkNotNullParameter(vehicleId, "vehicleId");
        Intrinsics.checkNotNullParameter(tripCurrentStatus, "tripCurrentStatus");
        Intrinsics.checkNotNullParameter(tripSourceName, "tripSourceName");
        this.acceleratorPosition = d2;
        this.createdTimeStamp = createdTimeStamp;
        this.distanceTravelled = d3;
        this.dte = d4;
        this.energyLevel = d5;
        this.hardBrake = d6;
        this.highSpeed = d7;
        this.idleEnergyConsumption = d8;
        this.idleTime = d9;
        this.location = location;
        this.mileage = d10;
        this.totalTimeTravelled = d11;
        this.tripId = tripId;
        this.tripName = tripName;
        this.tripStartDate = tripStartDate;
        this.tripType = tripType;
        this.updatedTimeStamp = updatedTimeStamp;
        this.vehicleId = vehicleId;
        this.vehicleSpeed = d12;
        this.tripCurrentStatus = tripCurrentStatus;
        this.aggressiveAcceleration = num;
        this.ignitionStatus = str;
        this.viewType = i2;
        this.tripSourceName = tripSourceName;
    }

    public /* synthetic */ OnGoingResponse(double d2, String str, double d3, double d4, double d5, double d6, double d7, double d8, double d9, String str2, double d10, double d11, String str3, String str4, String str5, String str6, String str7, String str8, double d12, String str9, Integer num, String str10, int i2, String str11, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0.0d : d2, (i3 & 2) != 0 ? "" : str, (i3 & 4) != 0 ? 0.0d : d3, (i3 & 8) != 0 ? 0.0d : d4, (i3 & 16) != 0 ? 0.0d : d5, (i3 & 32) != 0 ? 0.0d : d6, (i3 & 64) != 0 ? 0.0d : d7, (i3 & 128) != 0 ? 0.0d : d8, (i3 & 256) != 0 ? 0.0d : d9, (i3 & 512) != 0 ? "" : str2, (i3 & 1024) != 0 ? 0.0d : d10, (i3 & 2048) != 0 ? 0.0d : d11, (i3 & 4096) != 0 ? "" : str3, (i3 & 8192) != 0 ? "" : str4, (i3 & 16384) != 0 ? "" : str5, (i3 & 32768) != 0 ? "" : str6, (i3 & 65536) != 0 ? "" : str7, (i3 & 131072) != 0 ? "" : str8, (i3 & 262144) != 0 ? 0.0d : d12, (i3 & 524288) != 0 ? "" : str9, (i3 & 1048576) != 0 ? null : num, (i3 & 2097152) == 0 ? str10 : null, (i3 & 4194304) != 0 ? 0 : i2, (i3 & 8388608) != 0 ? "" : str11);
    }

    public static /* synthetic */ OnGoingResponse copy$default(OnGoingResponse onGoingResponse, double d2, String str, double d3, double d4, double d5, double d6, double d7, double d8, double d9, String str2, double d10, double d11, String str3, String str4, String str5, String str6, String str7, String str8, double d12, String str9, Integer num, String str10, int i2, String str11, int i3, Object obj) {
        double d13 = (i3 & 1) != 0 ? onGoingResponse.acceleratorPosition : d2;
        String str12 = (i3 & 2) != 0 ? onGoingResponse.createdTimeStamp : str;
        double d14 = (i3 & 4) != 0 ? onGoingResponse.distanceTravelled : d3;
        double d15 = (i3 & 8) != 0 ? onGoingResponse.dte : d4;
        double d16 = (i3 & 16) != 0 ? onGoingResponse.energyLevel : d5;
        double d17 = (i3 & 32) != 0 ? onGoingResponse.hardBrake : d6;
        double d18 = (i3 & 64) != 0 ? onGoingResponse.highSpeed : d7;
        double d19 = (i3 & 128) != 0 ? onGoingResponse.idleEnergyConsumption : d8;
        double d20 = (i3 & 256) != 0 ? onGoingResponse.idleTime : d9;
        return onGoingResponse.copy(d13, str12, d14, d15, d16, d17, d18, d19, d20, (i3 & 512) != 0 ? onGoingResponse.location : str2, (i3 & 1024) != 0 ? onGoingResponse.mileage : d10, (i3 & 2048) != 0 ? onGoingResponse.totalTimeTravelled : d11, (i3 & 4096) != 0 ? onGoingResponse.tripId : str3, (i3 & 8192) != 0 ? onGoingResponse.tripName : str4, (i3 & 16384) != 0 ? onGoingResponse.tripStartDate : str5, (i3 & 32768) != 0 ? onGoingResponse.tripType : str6, (i3 & 65536) != 0 ? onGoingResponse.updatedTimeStamp : str7, (i3 & 131072) != 0 ? onGoingResponse.vehicleId : str8, (i3 & 262144) != 0 ? onGoingResponse.vehicleSpeed : d12, (i3 & 524288) != 0 ? onGoingResponse.tripCurrentStatus : str9, (1048576 & i3) != 0 ? onGoingResponse.aggressiveAcceleration : num, (i3 & 2097152) != 0 ? onGoingResponse.ignitionStatus : str10, (i3 & 4194304) != 0 ? onGoingResponse.viewType : i2, (i3 & 8388608) != 0 ? onGoingResponse.tripSourceName : str11);
    }

    public final double component1() {
        return this.acceleratorPosition;
    }

    @NotNull
    public final String component10() {
        return this.location;
    }

    public final double component11() {
        return this.mileage;
    }

    public final double component12() {
        return this.totalTimeTravelled;
    }

    @NotNull
    public final String component13() {
        return this.tripId;
    }

    @NotNull
    public final String component14() {
        return this.tripName;
    }

    @NotNull
    public final String component15() {
        return this.tripStartDate;
    }

    @NotNull
    public final String component16() {
        return this.tripType;
    }

    @NotNull
    public final String component17() {
        return this.updatedTimeStamp;
    }

    @NotNull
    public final String component18() {
        return this.vehicleId;
    }

    public final double component19() {
        return this.vehicleSpeed;
    }

    @NotNull
    public final String component2() {
        return this.createdTimeStamp;
    }

    @NotNull
    public final String component20() {
        return this.tripCurrentStatus;
    }

    @Nullable
    public final Integer component21() {
        return this.aggressiveAcceleration;
    }

    @Nullable
    public final String component22() {
        return this.ignitionStatus;
    }

    public final int component23() {
        return this.viewType;
    }

    @NotNull
    public final String component24() {
        return this.tripSourceName;
    }

    public final double component3() {
        return this.distanceTravelled;
    }

    public final double component4() {
        return this.dte;
    }

    public final double component5() {
        return this.energyLevel;
    }

    public final double component6() {
        return this.hardBrake;
    }

    public final double component7() {
        return this.highSpeed;
    }

    public final double component8() {
        return this.idleEnergyConsumption;
    }

    public final double component9() {
        return this.idleTime;
    }

    @NotNull
    public final OnGoingResponse copy(double d2, @NotNull String createdTimeStamp, double d3, double d4, double d5, double d6, double d7, double d8, double d9, @NotNull String location, double d10, double d11, @NotNull String tripId, @NotNull String tripName, @NotNull String tripStartDate, @NotNull String tripType, @NotNull String updatedTimeStamp, @NotNull String vehicleId, double d12, @NotNull String tripCurrentStatus, @Nullable Integer num, @Nullable String str, int i2, @NotNull String tripSourceName) {
        Intrinsics.checkNotNullParameter(createdTimeStamp, "createdTimeStamp");
        Intrinsics.checkNotNullParameter(location, "location");
        Intrinsics.checkNotNullParameter(tripId, "tripId");
        Intrinsics.checkNotNullParameter(tripName, "tripName");
        Intrinsics.checkNotNullParameter(tripStartDate, "tripStartDate");
        Intrinsics.checkNotNullParameter(tripType, "tripType");
        Intrinsics.checkNotNullParameter(updatedTimeStamp, "updatedTimeStamp");
        Intrinsics.checkNotNullParameter(vehicleId, "vehicleId");
        Intrinsics.checkNotNullParameter(tripCurrentStatus, "tripCurrentStatus");
        Intrinsics.checkNotNullParameter(tripSourceName, "tripSourceName");
        return new OnGoingResponse(d2, createdTimeStamp, d3, d4, d5, d6, d7, d8, d9, location, d10, d11, tripId, tripName, tripStartDate, tripType, updatedTimeStamp, vehicleId, d12, tripCurrentStatus, num, str, i2, tripSourceName);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof OnGoingResponse) {
            OnGoingResponse onGoingResponse = (OnGoingResponse) obj;
            return Intrinsics.areEqual((Object) Double.valueOf(this.acceleratorPosition), (Object) Double.valueOf(onGoingResponse.acceleratorPosition)) && Intrinsics.areEqual(this.createdTimeStamp, onGoingResponse.createdTimeStamp) && Intrinsics.areEqual((Object) Double.valueOf(this.distanceTravelled), (Object) Double.valueOf(onGoingResponse.distanceTravelled)) && Intrinsics.areEqual((Object) Double.valueOf(this.dte), (Object) Double.valueOf(onGoingResponse.dte)) && Intrinsics.areEqual((Object) Double.valueOf(this.energyLevel), (Object) Double.valueOf(onGoingResponse.energyLevel)) && Intrinsics.areEqual((Object) Double.valueOf(this.hardBrake), (Object) Double.valueOf(onGoingResponse.hardBrake)) && Intrinsics.areEqual((Object) Double.valueOf(this.highSpeed), (Object) Double.valueOf(onGoingResponse.highSpeed)) && Intrinsics.areEqual((Object) Double.valueOf(this.idleEnergyConsumption), (Object) Double.valueOf(onGoingResponse.idleEnergyConsumption)) && Intrinsics.areEqual((Object) Double.valueOf(this.idleTime), (Object) Double.valueOf(onGoingResponse.idleTime)) && Intrinsics.areEqual(this.location, onGoingResponse.location) && Intrinsics.areEqual((Object) Double.valueOf(this.mileage), (Object) Double.valueOf(onGoingResponse.mileage)) && Intrinsics.areEqual((Object) Double.valueOf(this.totalTimeTravelled), (Object) Double.valueOf(onGoingResponse.totalTimeTravelled)) && Intrinsics.areEqual(this.tripId, onGoingResponse.tripId) && Intrinsics.areEqual(this.tripName, onGoingResponse.tripName) && Intrinsics.areEqual(this.tripStartDate, onGoingResponse.tripStartDate) && Intrinsics.areEqual(this.tripType, onGoingResponse.tripType) && Intrinsics.areEqual(this.updatedTimeStamp, onGoingResponse.updatedTimeStamp) && Intrinsics.areEqual(this.vehicleId, onGoingResponse.vehicleId) && Intrinsics.areEqual((Object) Double.valueOf(this.vehicleSpeed), (Object) Double.valueOf(onGoingResponse.vehicleSpeed)) && Intrinsics.areEqual(this.tripCurrentStatus, onGoingResponse.tripCurrentStatus) && Intrinsics.areEqual(this.aggressiveAcceleration, onGoingResponse.aggressiveAcceleration) && Intrinsics.areEqual(this.ignitionStatus, onGoingResponse.ignitionStatus) && this.viewType == onGoingResponse.viewType && Intrinsics.areEqual(this.tripSourceName, onGoingResponse.tripSourceName);
        }
        return false;
    }

    public final double getAcceleratorPosition() {
        return this.acceleratorPosition;
    }

    @Nullable
    public final Integer getAggressiveAcceleration() {
        return this.aggressiveAcceleration;
    }

    @NotNull
    public final String getCreatedTimeStamp() {
        return this.createdTimeStamp;
    }

    public final double getDistanceTravelled() {
        return this.distanceTravelled;
    }

    public final double getDte() {
        return this.dte;
    }

    public final double getEnergyLevel() {
        return this.energyLevel;
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

    @Nullable
    public final String getIgnitionStatus() {
        return this.ignitionStatus;
    }

    @NotNull
    public final String getLocation() {
        return this.location;
    }

    public final double getMileage() {
        return this.mileage;
    }

    public final double getTotalTimeTravelled() {
        return this.totalTimeTravelled;
    }

    @NotNull
    public final String getTripCurrentStatus() {
        return this.tripCurrentStatus;
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
    public final String getTripType() {
        return this.tripType;
    }

    @NotNull
    public final String getUpdatedTimeStamp() {
        return this.updatedTimeStamp;
    }

    @NotNull
    public final String getVehicleId() {
        return this.vehicleId;
    }

    public final double getVehicleSpeed() {
        return this.vehicleSpeed;
    }

    public final int getViewType() {
        return this.viewType;
    }

    public int hashCode() {
        int hashCode = ((((((((((((((((((((((((((((((((((((((Double.hashCode(this.acceleratorPosition) * 31) + this.createdTimeStamp.hashCode()) * 31) + Double.hashCode(this.distanceTravelled)) * 31) + Double.hashCode(this.dte)) * 31) + Double.hashCode(this.energyLevel)) * 31) + Double.hashCode(this.hardBrake)) * 31) + Double.hashCode(this.highSpeed)) * 31) + Double.hashCode(this.idleEnergyConsumption)) * 31) + Double.hashCode(this.idleTime)) * 31) + this.location.hashCode()) * 31) + Double.hashCode(this.mileage)) * 31) + Double.hashCode(this.totalTimeTravelled)) * 31) + this.tripId.hashCode()) * 31) + this.tripName.hashCode()) * 31) + this.tripStartDate.hashCode()) * 31) + this.tripType.hashCode()) * 31) + this.updatedTimeStamp.hashCode()) * 31) + this.vehicleId.hashCode()) * 31) + Double.hashCode(this.vehicleSpeed)) * 31) + this.tripCurrentStatus.hashCode()) * 31;
        Integer num = this.aggressiveAcceleration;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str = this.ignitionStatus;
        return ((((hashCode2 + (str != null ? str.hashCode() : 0)) * 31) + Integer.hashCode(this.viewType)) * 31) + this.tripSourceName.hashCode();
    }

    public final void setAcceleratorPosition(double d2) {
        this.acceleratorPosition = d2;
    }

    public final void setAggressiveAcceleration(@Nullable Integer num) {
        this.aggressiveAcceleration = num;
    }

    public final void setCreatedTimeStamp(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.createdTimeStamp = str;
    }

    public final void setDistanceTravelled(double d2) {
        this.distanceTravelled = d2;
    }

    public final void setDte(double d2) {
        this.dte = d2;
    }

    public final void setEnergyLevel(double d2) {
        this.energyLevel = d2;
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

    public final void setIgnitionStatus(@Nullable String str) {
        this.ignitionStatus = str;
    }

    public final void setLocation(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.location = str;
    }

    public final void setMileage(double d2) {
        this.mileage = d2;
    }

    public final void setTotalTimeTravelled(double d2) {
        this.totalTimeTravelled = d2;
    }

    public final void setTripCurrentStatus(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tripCurrentStatus = str;
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

    public final void setTripType(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tripType = str;
    }

    public final void setUpdatedTimeStamp(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.updatedTimeStamp = str;
    }

    public final void setVehicleId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vehicleId = str;
    }

    public final void setVehicleSpeed(double d2) {
        this.vehicleSpeed = d2;
    }

    public final void setViewType(int i2) {
        this.viewType = i2;
    }

    @NotNull
    public String toString() {
        return "OnGoingResponse(acceleratorPosition=" + this.acceleratorPosition + ", createdTimeStamp=" + this.createdTimeStamp + ", distanceTravelled=" + this.distanceTravelled + ", dte=" + this.dte + ", energyLevel=" + this.energyLevel + ", hardBrake=" + this.hardBrake + ", highSpeed=" + this.highSpeed + ", idleEnergyConsumption=" + this.idleEnergyConsumption + ", idleTime=" + this.idleTime + ", location=" + this.location + ", mileage=" + this.mileage + ", totalTimeTravelled=" + this.totalTimeTravelled + ", tripId=" + this.tripId + ", tripName=" + this.tripName + ", tripStartDate=" + this.tripStartDate + ", tripType=" + this.tripType + ", updatedTimeStamp=" + this.updatedTimeStamp + ", vehicleId=" + this.vehicleId + ", vehicleSpeed=" + this.vehicleSpeed + ", tripCurrentStatus=" + this.tripCurrentStatus + ", aggressiveAcceleration=" + this.aggressiveAcceleration + ", ignitionStatus=" + this.ignitionStatus + ", viewType=" + this.viewType + ", tripSourceName=" + this.tripSourceName + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        int intValue;
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeDouble(this.acceleratorPosition);
        out.writeString(this.createdTimeStamp);
        out.writeDouble(this.distanceTravelled);
        out.writeDouble(this.dte);
        out.writeDouble(this.energyLevel);
        out.writeDouble(this.hardBrake);
        out.writeDouble(this.highSpeed);
        out.writeDouble(this.idleEnergyConsumption);
        out.writeDouble(this.idleTime);
        out.writeString(this.location);
        out.writeDouble(this.mileage);
        out.writeDouble(this.totalTimeTravelled);
        out.writeString(this.tripId);
        out.writeString(this.tripName);
        out.writeString(this.tripStartDate);
        out.writeString(this.tripType);
        out.writeString(this.updatedTimeStamp);
        out.writeString(this.vehicleId);
        out.writeDouble(this.vehicleSpeed);
        out.writeString(this.tripCurrentStatus);
        Integer num = this.aggressiveAcceleration;
        if (num == null) {
            intValue = 0;
        } else {
            out.writeInt(1);
            intValue = num.intValue();
        }
        out.writeInt(intValue);
        out.writeString(this.ignitionStatus);
        out.writeInt(this.viewType);
        out.writeString(this.tripSourceName);
    }
}
