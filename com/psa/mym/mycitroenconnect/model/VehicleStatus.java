package com.psa.mym.mycitroenconnect.model;

import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class VehicleStatus {
    @SerializedName("acSwitchStatus")
    @Nullable
    private String acSwitchStatus;
    @SerializedName("avgFuelEfficiency")
    @Nullable
    private Double avgFuelEfficiency;
    @SerializedName("batteryCharging")
    @Nullable
    private Double batteryCharging;
    @SerializedName("bonnetStatus")
    @Nullable
    private String bonnetStatus;
    @SerializedName("bootStatus")
    @Nullable
    private String bootStatus;
    @SerializedName("chargingStatus")
    @Nullable
    private String chargingStatus;
    @SerializedName("coDriverDoorEvent")
    @Nullable
    private String coDriverDoorEvent;
    @SerializedName("coDriverSeatBeltEvent")
    @Nullable
    private String coDriverSeatBeltEvent;
    @SerializedName("coarseAngle")
    @Nullable
    private Double coarseAngle;
    @SerializedName("deviceConnectedState")
    @Nullable
    private String deviceConnectedState;
    @SerializedName("disconnectionType")
    @Nullable
    private String disconnectionType;
    @SerializedName("doorCloseEvent")
    @Nullable
    private String doorCloseEvent;
    @SerializedName("doorOpenEvent")
    @Nullable
    private String doorOpenEvent;
    @SerializedName("driverDoorEvent")
    @Nullable
    private String driverDoorEvent;
    @SerializedName("driverSeatBeltEvent")
    @Nullable
    private String driverSeatBeltEvent;
    @SerializedName("dte")
    @Nullable
    private Double dte;
    @SerializedName("fuelLevel")
    @Nullable
    private Double fuelLevel;
    @SerializedName("gpsLat")
    @Nullable
    private Double gpsLat;
    @SerializedName("gpstLong")
    @Nullable
    private Double gpsLong;
    @SerializedName("ignitionStatus")
    @Nullable
    private String ignitionStatus;
    @SerializedName("obcPlugStateEvent")
    @Nullable
    private String obcPlugStateEvent;
    @SerializedName("odometer")
    @Nullable
    private Double odometer;
    @SerializedName("rearLeftDoorEvent")
    @Nullable
    private String rearLeftDoorEvent;
    @SerializedName("rearRightDoorEvent")
    @Nullable
    private String rearRightDoorEvent;
    @SerializedName("seatBeltBuckledEvent")
    @Nullable
    private String seatBeltBuckledEvent;
    @SerializedName("seatBeltOpenEvent")
    @Nullable
    private String seatBeltOpenEvent;
    @SerializedName("signalTimeStamp")
    @Nullable
    private Long signalTimeStamp;
    @SerializedName("speed")
    @Nullable
    private Double speed;
    @SerializedName("statusFlag")
    @Nullable
    private Integer statusFlag;
    @SerializedName("tboxSignalStrength")
    @Nullable
    private Double tboxSignalStrength;
    @SerializedName("timeToFullChargeInMins")
    @Nullable
    private String timeToFullCharge;
    @SerializedName("vehicleLockingStatus")
    @Nullable
    private String vehicleLockingStatus;
    @SerializedName("vehicleStateHealth")
    @Nullable
    private HashMap<String, String> vehicleStateHealth;

    public VehicleStatus() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, -1, 1, null);
    }

    public VehicleStatus(@Nullable Long l2, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable String str11, @Nullable Double d2, @Nullable Double d3, @Nullable Double d4, @Nullable Double d5, @Nullable Double d6, @Nullable Double d7, @Nullable Double d8, @Nullable Integer num, @Nullable Double d9, @Nullable String str12, @Nullable String str13, @Nullable String str14, @Nullable String str15, @Nullable String str16, @Nullable String str17, @Nullable String str18, @Nullable String str19, @Nullable Double d10, @Nullable Double d11, @Nullable String str20, @Nullable HashMap<String, String> hashMap) {
        this.signalTimeStamp = l2;
        this.seatBeltOpenEvent = str;
        this.seatBeltBuckledEvent = str2;
        this.doorOpenEvent = str3;
        this.doorCloseEvent = str4;
        this.obcPlugStateEvent = str5;
        this.acSwitchStatus = str6;
        this.vehicleLockingStatus = str7;
        this.bonnetStatus = str8;
        this.bootStatus = str9;
        this.ignitionStatus = str10;
        this.chargingStatus = str11;
        this.gpsLat = d2;
        this.gpsLong = d3;
        this.speed = d4;
        this.dte = d5;
        this.batteryCharging = d6;
        this.odometer = d7;
        this.tboxSignalStrength = d8;
        this.statusFlag = num;
        this.coarseAngle = d9;
        this.deviceConnectedState = str12;
        this.disconnectionType = str13;
        this.driverSeatBeltEvent = str14;
        this.coDriverSeatBeltEvent = str15;
        this.driverDoorEvent = str16;
        this.coDriverDoorEvent = str17;
        this.rearLeftDoorEvent = str18;
        this.rearRightDoorEvent = str19;
        this.fuelLevel = d10;
        this.avgFuelEfficiency = d11;
        this.timeToFullCharge = str20;
        this.vehicleStateHealth = hashMap;
    }

    public /* synthetic */ VehicleStatus(Long l2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, Double d2, Double d3, Double d4, Double d5, Double d6, Double d7, Double d8, Integer num, Double d9, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, Double d10, Double d11, String str20, HashMap hashMap, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : l2, (i2 & 2) != 0 ? null : str, (i2 & 4) != 0 ? null : str2, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? null : str4, (i2 & 32) != 0 ? null : str5, (i2 & 64) != 0 ? null : str6, (i2 & 128) != 0 ? null : str7, (i2 & 256) != 0 ? null : str8, (i2 & 512) != 0 ? null : str9, (i2 & 1024) != 0 ? null : str10, (i2 & 2048) != 0 ? null : str11, (i2 & 4096) != 0 ? null : d2, (i2 & 8192) != 0 ? null : d3, (i2 & 16384) != 0 ? null : d4, (i2 & 32768) != 0 ? null : d5, (i2 & 65536) != 0 ? null : d6, (i2 & 131072) != 0 ? null : d7, (i2 & 262144) != 0 ? null : d8, (i2 & 524288) != 0 ? null : num, (i2 & 1048576) != 0 ? null : d9, (i2 & 2097152) != 0 ? null : str12, (i2 & 4194304) != 0 ? null : str13, (i2 & 8388608) != 0 ? null : str14, (i2 & 16777216) != 0 ? null : str15, (i2 & MediaHttpDownloader.MAXIMUM_CHUNK_SIZE) != 0 ? null : str16, (i2 & 67108864) != 0 ? null : str17, (i2 & 134217728) != 0 ? null : str18, (i2 & 268435456) != 0 ? null : str19, (i2 & PKIFailureInfo.duplicateCertReq) != 0 ? null : d10, (i2 & 1073741824) != 0 ? null : d11, (i2 & Integer.MIN_VALUE) != 0 ? null : str20, (i3 & 1) != 0 ? null : hashMap);
    }

    @Nullable
    public final Long component1() {
        return this.signalTimeStamp;
    }

    @Nullable
    public final String component10() {
        return this.bootStatus;
    }

    @Nullable
    public final String component11() {
        return this.ignitionStatus;
    }

    @Nullable
    public final String component12() {
        return this.chargingStatus;
    }

    @Nullable
    public final Double component13() {
        return this.gpsLat;
    }

    @Nullable
    public final Double component14() {
        return this.gpsLong;
    }

    @Nullable
    public final Double component15() {
        return this.speed;
    }

    @Nullable
    public final Double component16() {
        return this.dte;
    }

    @Nullable
    public final Double component17() {
        return this.batteryCharging;
    }

    @Nullable
    public final Double component18() {
        return this.odometer;
    }

    @Nullable
    public final Double component19() {
        return this.tboxSignalStrength;
    }

    @Nullable
    public final String component2() {
        return this.seatBeltOpenEvent;
    }

    @Nullable
    public final Integer component20() {
        return this.statusFlag;
    }

    @Nullable
    public final Double component21() {
        return this.coarseAngle;
    }

    @Nullable
    public final String component22() {
        return this.deviceConnectedState;
    }

    @Nullable
    public final String component23() {
        return this.disconnectionType;
    }

    @Nullable
    public final String component24() {
        return this.driverSeatBeltEvent;
    }

    @Nullable
    public final String component25() {
        return this.coDriverSeatBeltEvent;
    }

    @Nullable
    public final String component26() {
        return this.driverDoorEvent;
    }

    @Nullable
    public final String component27() {
        return this.coDriverDoorEvent;
    }

    @Nullable
    public final String component28() {
        return this.rearLeftDoorEvent;
    }

    @Nullable
    public final String component29() {
        return this.rearRightDoorEvent;
    }

    @Nullable
    public final String component3() {
        return this.seatBeltBuckledEvent;
    }

    @Nullable
    public final Double component30() {
        return this.fuelLevel;
    }

    @Nullable
    public final Double component31() {
        return this.avgFuelEfficiency;
    }

    @Nullable
    public final String component32() {
        return this.timeToFullCharge;
    }

    @Nullable
    public final HashMap<String, String> component33() {
        return this.vehicleStateHealth;
    }

    @Nullable
    public final String component4() {
        return this.doorOpenEvent;
    }

    @Nullable
    public final String component5() {
        return this.doorCloseEvent;
    }

    @Nullable
    public final String component6() {
        return this.obcPlugStateEvent;
    }

    @Nullable
    public final String component7() {
        return this.acSwitchStatus;
    }

    @Nullable
    public final String component8() {
        return this.vehicleLockingStatus;
    }

    @Nullable
    public final String component9() {
        return this.bonnetStatus;
    }

    @NotNull
    public final VehicleStatus copy(@Nullable Long l2, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable String str11, @Nullable Double d2, @Nullable Double d3, @Nullable Double d4, @Nullable Double d5, @Nullable Double d6, @Nullable Double d7, @Nullable Double d8, @Nullable Integer num, @Nullable Double d9, @Nullable String str12, @Nullable String str13, @Nullable String str14, @Nullable String str15, @Nullable String str16, @Nullable String str17, @Nullable String str18, @Nullable String str19, @Nullable Double d10, @Nullable Double d11, @Nullable String str20, @Nullable HashMap<String, String> hashMap) {
        return new VehicleStatus(l2, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, d2, d3, d4, d5, d6, d7, d8, num, d9, str12, str13, str14, str15, str16, str17, str18, str19, d10, d11, str20, hashMap);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof VehicleStatus) {
            VehicleStatus vehicleStatus = (VehicleStatus) obj;
            return Intrinsics.areEqual(this.signalTimeStamp, vehicleStatus.signalTimeStamp) && Intrinsics.areEqual(this.seatBeltOpenEvent, vehicleStatus.seatBeltOpenEvent) && Intrinsics.areEqual(this.seatBeltBuckledEvent, vehicleStatus.seatBeltBuckledEvent) && Intrinsics.areEqual(this.doorOpenEvent, vehicleStatus.doorOpenEvent) && Intrinsics.areEqual(this.doorCloseEvent, vehicleStatus.doorCloseEvent) && Intrinsics.areEqual(this.obcPlugStateEvent, vehicleStatus.obcPlugStateEvent) && Intrinsics.areEqual(this.acSwitchStatus, vehicleStatus.acSwitchStatus) && Intrinsics.areEqual(this.vehicleLockingStatus, vehicleStatus.vehicleLockingStatus) && Intrinsics.areEqual(this.bonnetStatus, vehicleStatus.bonnetStatus) && Intrinsics.areEqual(this.bootStatus, vehicleStatus.bootStatus) && Intrinsics.areEqual(this.ignitionStatus, vehicleStatus.ignitionStatus) && Intrinsics.areEqual(this.chargingStatus, vehicleStatus.chargingStatus) && Intrinsics.areEqual((Object) this.gpsLat, (Object) vehicleStatus.gpsLat) && Intrinsics.areEqual((Object) this.gpsLong, (Object) vehicleStatus.gpsLong) && Intrinsics.areEqual((Object) this.speed, (Object) vehicleStatus.speed) && Intrinsics.areEqual((Object) this.dte, (Object) vehicleStatus.dte) && Intrinsics.areEqual((Object) this.batteryCharging, (Object) vehicleStatus.batteryCharging) && Intrinsics.areEqual((Object) this.odometer, (Object) vehicleStatus.odometer) && Intrinsics.areEqual((Object) this.tboxSignalStrength, (Object) vehicleStatus.tboxSignalStrength) && Intrinsics.areEqual(this.statusFlag, vehicleStatus.statusFlag) && Intrinsics.areEqual((Object) this.coarseAngle, (Object) vehicleStatus.coarseAngle) && Intrinsics.areEqual(this.deviceConnectedState, vehicleStatus.deviceConnectedState) && Intrinsics.areEqual(this.disconnectionType, vehicleStatus.disconnectionType) && Intrinsics.areEqual(this.driverSeatBeltEvent, vehicleStatus.driverSeatBeltEvent) && Intrinsics.areEqual(this.coDriverSeatBeltEvent, vehicleStatus.coDriverSeatBeltEvent) && Intrinsics.areEqual(this.driverDoorEvent, vehicleStatus.driverDoorEvent) && Intrinsics.areEqual(this.coDriverDoorEvent, vehicleStatus.coDriverDoorEvent) && Intrinsics.areEqual(this.rearLeftDoorEvent, vehicleStatus.rearLeftDoorEvent) && Intrinsics.areEqual(this.rearRightDoorEvent, vehicleStatus.rearRightDoorEvent) && Intrinsics.areEqual((Object) this.fuelLevel, (Object) vehicleStatus.fuelLevel) && Intrinsics.areEqual((Object) this.avgFuelEfficiency, (Object) vehicleStatus.avgFuelEfficiency) && Intrinsics.areEqual(this.timeToFullCharge, vehicleStatus.timeToFullCharge) && Intrinsics.areEqual(this.vehicleStateHealth, vehicleStatus.vehicleStateHealth);
        }
        return false;
    }

    @Nullable
    public final String getAcSwitchStatus() {
        return this.acSwitchStatus;
    }

    @Nullable
    public final Double getAvgFuelEfficiency() {
        return this.avgFuelEfficiency;
    }

    @Nullable
    public final Double getBatteryCharging() {
        return this.batteryCharging;
    }

    @Nullable
    public final String getBonnetStatus() {
        return this.bonnetStatus;
    }

    @Nullable
    public final String getBootStatus() {
        return this.bootStatus;
    }

    @Nullable
    public final String getChargingStatus() {
        return this.chargingStatus;
    }

    @Nullable
    public final String getCoDriverDoorEvent() {
        return this.coDriverDoorEvent;
    }

    @Nullable
    public final String getCoDriverSeatBeltEvent() {
        return this.coDriverSeatBeltEvent;
    }

    @Nullable
    public final Double getCoarseAngle() {
        return this.coarseAngle;
    }

    @Nullable
    public final String getDeviceConnectedState() {
        return this.deviceConnectedState;
    }

    @Nullable
    public final String getDisconnectionType() {
        return this.disconnectionType;
    }

    @Nullable
    public final String getDoorCloseEvent() {
        return this.doorCloseEvent;
    }

    @Nullable
    public final String getDoorOpenEvent() {
        return this.doorOpenEvent;
    }

    @Nullable
    public final String getDriverDoorEvent() {
        return this.driverDoorEvent;
    }

    @Nullable
    public final String getDriverSeatBeltEvent() {
        return this.driverSeatBeltEvent;
    }

    @Nullable
    public final Double getDte() {
        return this.dte;
    }

    @Nullable
    public final Double getFuelLevel() {
        return this.fuelLevel;
    }

    @Nullable
    public final Double getGpsLat() {
        return this.gpsLat;
    }

    @Nullable
    public final Double getGpsLong() {
        return this.gpsLong;
    }

    @Nullable
    public final String getIgnitionStatus() {
        return this.ignitionStatus;
    }

    @Nullable
    public final String getObcPlugStateEvent() {
        return this.obcPlugStateEvent;
    }

    @Nullable
    public final Double getOdometer() {
        return this.odometer;
    }

    @Nullable
    public final String getRearLeftDoorEvent() {
        return this.rearLeftDoorEvent;
    }

    @Nullable
    public final String getRearRightDoorEvent() {
        return this.rearRightDoorEvent;
    }

    @Nullable
    public final String getSeatBeltBuckledEvent() {
        return this.seatBeltBuckledEvent;
    }

    @Nullable
    public final String getSeatBeltOpenEvent() {
        return this.seatBeltOpenEvent;
    }

    @Nullable
    public final Long getSignalTimeStamp() {
        return this.signalTimeStamp;
    }

    @Nullable
    public final Double getSpeed() {
        return this.speed;
    }

    @Nullable
    public final Integer getStatusFlag() {
        return this.statusFlag;
    }

    @Nullable
    public final Double getTboxSignalStrength() {
        return this.tboxSignalStrength;
    }

    @Nullable
    public final String getTimeToFullCharge() {
        return this.timeToFullCharge;
    }

    @Nullable
    public final String getVehicleLockingStatus() {
        return this.vehicleLockingStatus;
    }

    @Nullable
    public final HashMap<String, String> getVehicleStateHealth() {
        return this.vehicleStateHealth;
    }

    public int hashCode() {
        Long l2 = this.signalTimeStamp;
        int hashCode = (l2 == null ? 0 : l2.hashCode()) * 31;
        String str = this.seatBeltOpenEvent;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.seatBeltBuckledEvent;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.doorOpenEvent;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.doorCloseEvent;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.obcPlugStateEvent;
        int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.acSwitchStatus;
        int hashCode7 = (hashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.vehicleLockingStatus;
        int hashCode8 = (hashCode7 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.bonnetStatus;
        int hashCode9 = (hashCode8 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.bootStatus;
        int hashCode10 = (hashCode9 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.ignitionStatus;
        int hashCode11 = (hashCode10 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.chargingStatus;
        int hashCode12 = (hashCode11 + (str11 == null ? 0 : str11.hashCode())) * 31;
        Double d2 = this.gpsLat;
        int hashCode13 = (hashCode12 + (d2 == null ? 0 : d2.hashCode())) * 31;
        Double d3 = this.gpsLong;
        int hashCode14 = (hashCode13 + (d3 == null ? 0 : d3.hashCode())) * 31;
        Double d4 = this.speed;
        int hashCode15 = (hashCode14 + (d4 == null ? 0 : d4.hashCode())) * 31;
        Double d5 = this.dte;
        int hashCode16 = (hashCode15 + (d5 == null ? 0 : d5.hashCode())) * 31;
        Double d6 = this.batteryCharging;
        int hashCode17 = (hashCode16 + (d6 == null ? 0 : d6.hashCode())) * 31;
        Double d7 = this.odometer;
        int hashCode18 = (hashCode17 + (d7 == null ? 0 : d7.hashCode())) * 31;
        Double d8 = this.tboxSignalStrength;
        int hashCode19 = (hashCode18 + (d8 == null ? 0 : d8.hashCode())) * 31;
        Integer num = this.statusFlag;
        int hashCode20 = (hashCode19 + (num == null ? 0 : num.hashCode())) * 31;
        Double d9 = this.coarseAngle;
        int hashCode21 = (hashCode20 + (d9 == null ? 0 : d9.hashCode())) * 31;
        String str12 = this.deviceConnectedState;
        int hashCode22 = (hashCode21 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.disconnectionType;
        int hashCode23 = (hashCode22 + (str13 == null ? 0 : str13.hashCode())) * 31;
        String str14 = this.driverSeatBeltEvent;
        int hashCode24 = (hashCode23 + (str14 == null ? 0 : str14.hashCode())) * 31;
        String str15 = this.coDriverSeatBeltEvent;
        int hashCode25 = (hashCode24 + (str15 == null ? 0 : str15.hashCode())) * 31;
        String str16 = this.driverDoorEvent;
        int hashCode26 = (hashCode25 + (str16 == null ? 0 : str16.hashCode())) * 31;
        String str17 = this.coDriverDoorEvent;
        int hashCode27 = (hashCode26 + (str17 == null ? 0 : str17.hashCode())) * 31;
        String str18 = this.rearLeftDoorEvent;
        int hashCode28 = (hashCode27 + (str18 == null ? 0 : str18.hashCode())) * 31;
        String str19 = this.rearRightDoorEvent;
        int hashCode29 = (hashCode28 + (str19 == null ? 0 : str19.hashCode())) * 31;
        Double d10 = this.fuelLevel;
        int hashCode30 = (hashCode29 + (d10 == null ? 0 : d10.hashCode())) * 31;
        Double d11 = this.avgFuelEfficiency;
        int hashCode31 = (hashCode30 + (d11 == null ? 0 : d11.hashCode())) * 31;
        String str20 = this.timeToFullCharge;
        int hashCode32 = (hashCode31 + (str20 == null ? 0 : str20.hashCode())) * 31;
        HashMap<String, String> hashMap = this.vehicleStateHealth;
        return hashCode32 + (hashMap != null ? hashMap.hashCode() : 0);
    }

    public final void setAcSwitchStatus(@Nullable String str) {
        this.acSwitchStatus = str;
    }

    public final void setAvgFuelEfficiency(@Nullable Double d2) {
        this.avgFuelEfficiency = d2;
    }

    public final void setBatteryCharging(@Nullable Double d2) {
        this.batteryCharging = d2;
    }

    public final void setBonnetStatus(@Nullable String str) {
        this.bonnetStatus = str;
    }

    public final void setBootStatus(@Nullable String str) {
        this.bootStatus = str;
    }

    public final void setChargingStatus(@Nullable String str) {
        this.chargingStatus = str;
    }

    public final void setCoDriverDoorEvent(@Nullable String str) {
        this.coDriverDoorEvent = str;
    }

    public final void setCoDriverSeatBeltEvent(@Nullable String str) {
        this.coDriverSeatBeltEvent = str;
    }

    public final void setCoarseAngle(@Nullable Double d2) {
        this.coarseAngle = d2;
    }

    public final void setDeviceConnectedState(@Nullable String str) {
        this.deviceConnectedState = str;
    }

    public final void setDisconnectionType(@Nullable String str) {
        this.disconnectionType = str;
    }

    public final void setDoorCloseEvent(@Nullable String str) {
        this.doorCloseEvent = str;
    }

    public final void setDoorOpenEvent(@Nullable String str) {
        this.doorOpenEvent = str;
    }

    public final void setDriverDoorEvent(@Nullable String str) {
        this.driverDoorEvent = str;
    }

    public final void setDriverSeatBeltEvent(@Nullable String str) {
        this.driverSeatBeltEvent = str;
    }

    public final void setDte(@Nullable Double d2) {
        this.dte = d2;
    }

    public final void setFuelLevel(@Nullable Double d2) {
        this.fuelLevel = d2;
    }

    public final void setGpsLat(@Nullable Double d2) {
        this.gpsLat = d2;
    }

    public final void setGpsLong(@Nullable Double d2) {
        this.gpsLong = d2;
    }

    public final void setIgnitionStatus(@Nullable String str) {
        this.ignitionStatus = str;
    }

    public final void setObcPlugStateEvent(@Nullable String str) {
        this.obcPlugStateEvent = str;
    }

    public final void setOdometer(@Nullable Double d2) {
        this.odometer = d2;
    }

    public final void setRearLeftDoorEvent(@Nullable String str) {
        this.rearLeftDoorEvent = str;
    }

    public final void setRearRightDoorEvent(@Nullable String str) {
        this.rearRightDoorEvent = str;
    }

    public final void setSeatBeltBuckledEvent(@Nullable String str) {
        this.seatBeltBuckledEvent = str;
    }

    public final void setSeatBeltOpenEvent(@Nullable String str) {
        this.seatBeltOpenEvent = str;
    }

    public final void setSignalTimeStamp(@Nullable Long l2) {
        this.signalTimeStamp = l2;
    }

    public final void setSpeed(@Nullable Double d2) {
        this.speed = d2;
    }

    public final void setStatusFlag(@Nullable Integer num) {
        this.statusFlag = num;
    }

    public final void setTboxSignalStrength(@Nullable Double d2) {
        this.tboxSignalStrength = d2;
    }

    public final void setTimeToFullCharge(@Nullable String str) {
        this.timeToFullCharge = str;
    }

    public final void setVehicleLockingStatus(@Nullable String str) {
        this.vehicleLockingStatus = str;
    }

    public final void setVehicleStateHealth(@Nullable HashMap<String, String> hashMap) {
        this.vehicleStateHealth = hashMap;
    }

    @NotNull
    public String toString() {
        return "VehicleStatus(signalTimeStamp=" + this.signalTimeStamp + ", seatBeltOpenEvent=" + this.seatBeltOpenEvent + ", seatBeltBuckledEvent=" + this.seatBeltBuckledEvent + ", doorOpenEvent=" + this.doorOpenEvent + ", doorCloseEvent=" + this.doorCloseEvent + ", obcPlugStateEvent=" + this.obcPlugStateEvent + ", acSwitchStatus=" + this.acSwitchStatus + ", vehicleLockingStatus=" + this.vehicleLockingStatus + ", bonnetStatus=" + this.bonnetStatus + ", bootStatus=" + this.bootStatus + ", ignitionStatus=" + this.ignitionStatus + ", chargingStatus=" + this.chargingStatus + ", gpsLat=" + this.gpsLat + ", gpsLong=" + this.gpsLong + ", speed=" + this.speed + ", dte=" + this.dte + ", batteryCharging=" + this.batteryCharging + ", odometer=" + this.odometer + ", tboxSignalStrength=" + this.tboxSignalStrength + ", statusFlag=" + this.statusFlag + ", coarseAngle=" + this.coarseAngle + ", deviceConnectedState=" + this.deviceConnectedState + ", disconnectionType=" + this.disconnectionType + ", driverSeatBeltEvent=" + this.driverSeatBeltEvent + ", coDriverSeatBeltEvent=" + this.coDriverSeatBeltEvent + ", driverDoorEvent=" + this.driverDoorEvent + ", coDriverDoorEvent=" + this.coDriverDoorEvent + ", rearLeftDoorEvent=" + this.rearLeftDoorEvent + ", rearRightDoorEvent=" + this.rearRightDoorEvent + ", fuelLevel=" + this.fuelLevel + ", avgFuelEfficiency=" + this.avgFuelEfficiency + ", timeToFullCharge=" + this.timeToFullCharge + ", vehicleStateHealth=" + this.vehicleStateHealth + ')';
    }
}
