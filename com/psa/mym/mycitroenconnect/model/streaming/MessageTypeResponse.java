package com.psa.mym.mycitroenconnect.model.streaming;

import androidx.core.app.FrameMetricsAggregator;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class MessageTypeResponse {
    @SerializedName("absStatus")
    @NotNull
    private Object absStatus;
    @SerializedName("acSwitchStatus")
    @Nullable
    private String acSwitchStatus;
    @SerializedName("airConditioningStatus")
    @NotNull
    private Object airConditioningStatus;
    @SerializedName("batteryCharging")
    private double batteryCharging;
    @SerializedName("bccmToTboxPartialWakeUpStatus")
    @NotNull
    private Object bccmToTboxPartialWakeUpStatus;
    @SerializedName("bonnetStatus")
    @Nullable
    private String bonnetStatus;
    @SerializedName("bootStatus")
    @Nullable
    private String bootStatus;
    @SerializedName("breakPedalSwitchEvent")
    @NotNull
    private String breakPedalSwitchEvent;
    @SerializedName("chargingStatus")
    @Nullable
    private String chargingStatus;
    @SerializedName("clutchCompressorStatus")
    @NotNull
    private Object clutchCompressorStatus;
    @SerializedName("coarseAngle")
    private double coarseAngle;
    @SerializedName("dayNightEvent")
    @NotNull
    private String dayNightEvent;
    @SerializedName("doorCloseEvent")
    @NotNull
    private String doorCloseEvent;
    @SerializedName("doorOpenEvent")
    @NotNull
    private String doorOpenEvent;
    @SerializedName("dte")
    private int dte;
    @SerializedName("engineEvent")
    @NotNull
    private String engineEvent;
    @SerializedName("espStatus")
    @NotNull
    private Object espStatus;
    @SerializedName("gpsLat")
    private double gpsLat;
    @SerializedName("gpstLong")
    private double gpstLong;
    @SerializedName("harshAccelerationStatus")
    @NotNull
    private Object harshAccelerationStatus;
    @SerializedName("harshBrakingStatus")
    @NotNull
    private Object harshBrakingStatus;
    @SerializedName("hmiChargingStatus")
    @NotNull
    private Object hmiChargingStatus;
    @SerializedName("ignitionStatus")
    @Nullable
    private String ignitionStatus;
    @SerializedName("mainWakeUpStatus")
    @NotNull
    private Object mainWakeUpStatus;
    @SerializedName("obcPlugStateEvent")
    @NotNull
    private String obcPlugStateEvent;
    @SerializedName("parkBreakEvent")
    @NotNull
    private String parkBreakEvent;
    @SerializedName("prndStatus")
    @NotNull
    private Object prndStatus;
    @SerializedName("readyToStartStatus")
    @NotNull
    private Object readyToStartStatus;
    @SerializedName("remoteLockUnlockStatus")
    @NotNull
    private Object remoteLockUnlockStatus;
    @SerializedName("reverseEventStatus")
    @NotNull
    private Object reverseEventStatus;
    @SerializedName("seatBeltBuckledEvent")
    @NotNull
    private String seatBeltBuckledEvent;
    @SerializedName("seatBeltOpenEvent")
    @NotNull
    private String seatBeltOpenEvent;
    @SerializedName("signalTimeStamp")
    private long signalTimeStamp;
    @SerializedName("speed")
    private double speed;
    @SerializedName("statusFlag")
    private int statusFlag;
    @SerializedName("tboxFullSleepStatus")
    @NotNull
    private Object tboxFullSleepStatus;
    @SerializedName("tboxPartialSleepStatus")
    @NotNull
    private Object tboxPartialSleepStatus;
    @SerializedName("tboxPartialWakeUpStatus")
    @NotNull
    private Object tboxPartialWakeUpStatus;
    @SerializedName("tboxSignalStrength")
    @Nullable
    private Double tboxSignalStrength;
    @SerializedName("timeToFullChargeInMins")
    @Nullable
    private String timeToFullCharge;
    @SerializedName("vehicleLockingStatus")
    @Nullable
    private String vehicleLockingStatus;

    public MessageTypeResponse() {
        this(null, null, null, null, null, null, 0.0d, 0.0d, null, null, null, null, 0L, 0.0d, null, 0.0d, 0, 0, 0.0d, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, -1, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    public MessageTypeResponse(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @NotNull String doorCloseEvent, @NotNull String doorOpenEvent, double d2, double d3, @Nullable String str5, @NotNull String obcPlugStateEvent, @NotNull String seatBeltBuckledEvent, @NotNull String seatBeltOpenEvent, long j2, double d4, @Nullable String str6, double d5, int i2, int i3, double d6, @NotNull Object absStatus, @NotNull Object airConditioningStatus, @NotNull Object bccmToTboxPartialWakeUpStatus, @NotNull String breakPedalSwitchEvent, @NotNull Object clutchCompressorStatus, @NotNull String dayNightEvent, @NotNull String engineEvent, @NotNull Object espStatus, @NotNull Object harshAccelerationStatus, @NotNull Object harshBrakingStatus, @NotNull Object hmiChargingStatus, @NotNull Object mainWakeUpStatus, @NotNull String parkBreakEvent, @NotNull Object prndStatus, @NotNull Object readyToStartStatus, @NotNull Object remoteLockUnlockStatus, @NotNull Object reverseEventStatus, @NotNull Object tboxFullSleepStatus, @NotNull Object tboxPartialSleepStatus, @NotNull Object tboxPartialWakeUpStatus, @Nullable Double d7, @Nullable String str7) {
        Intrinsics.checkNotNullParameter(doorCloseEvent, "doorCloseEvent");
        Intrinsics.checkNotNullParameter(doorOpenEvent, "doorOpenEvent");
        Intrinsics.checkNotNullParameter(obcPlugStateEvent, "obcPlugStateEvent");
        Intrinsics.checkNotNullParameter(seatBeltBuckledEvent, "seatBeltBuckledEvent");
        Intrinsics.checkNotNullParameter(seatBeltOpenEvent, "seatBeltOpenEvent");
        Intrinsics.checkNotNullParameter(absStatus, "absStatus");
        Intrinsics.checkNotNullParameter(airConditioningStatus, "airConditioningStatus");
        Intrinsics.checkNotNullParameter(bccmToTboxPartialWakeUpStatus, "bccmToTboxPartialWakeUpStatus");
        Intrinsics.checkNotNullParameter(breakPedalSwitchEvent, "breakPedalSwitchEvent");
        Intrinsics.checkNotNullParameter(clutchCompressorStatus, "clutchCompressorStatus");
        Intrinsics.checkNotNullParameter(dayNightEvent, "dayNightEvent");
        Intrinsics.checkNotNullParameter(engineEvent, "engineEvent");
        Intrinsics.checkNotNullParameter(espStatus, "espStatus");
        Intrinsics.checkNotNullParameter(harshAccelerationStatus, "harshAccelerationStatus");
        Intrinsics.checkNotNullParameter(harshBrakingStatus, "harshBrakingStatus");
        Intrinsics.checkNotNullParameter(hmiChargingStatus, "hmiChargingStatus");
        Intrinsics.checkNotNullParameter(mainWakeUpStatus, "mainWakeUpStatus");
        Intrinsics.checkNotNullParameter(parkBreakEvent, "parkBreakEvent");
        Intrinsics.checkNotNullParameter(prndStatus, "prndStatus");
        Intrinsics.checkNotNullParameter(readyToStartStatus, "readyToStartStatus");
        Intrinsics.checkNotNullParameter(remoteLockUnlockStatus, "remoteLockUnlockStatus");
        Intrinsics.checkNotNullParameter(reverseEventStatus, "reverseEventStatus");
        Intrinsics.checkNotNullParameter(tboxFullSleepStatus, "tboxFullSleepStatus");
        Intrinsics.checkNotNullParameter(tboxPartialSleepStatus, "tboxPartialSleepStatus");
        Intrinsics.checkNotNullParameter(tboxPartialWakeUpStatus, "tboxPartialWakeUpStatus");
        this.acSwitchStatus = str;
        this.bonnetStatus = str2;
        this.bootStatus = str3;
        this.chargingStatus = str4;
        this.doorCloseEvent = doorCloseEvent;
        this.doorOpenEvent = doorOpenEvent;
        this.gpsLat = d2;
        this.gpstLong = d3;
        this.ignitionStatus = str5;
        this.obcPlugStateEvent = obcPlugStateEvent;
        this.seatBeltBuckledEvent = seatBeltBuckledEvent;
        this.seatBeltOpenEvent = seatBeltOpenEvent;
        this.signalTimeStamp = j2;
        this.speed = d4;
        this.vehicleLockingStatus = str6;
        this.batteryCharging = d5;
        this.dte = i2;
        this.statusFlag = i3;
        this.coarseAngle = d6;
        this.absStatus = absStatus;
        this.airConditioningStatus = airConditioningStatus;
        this.bccmToTboxPartialWakeUpStatus = bccmToTboxPartialWakeUpStatus;
        this.breakPedalSwitchEvent = breakPedalSwitchEvent;
        this.clutchCompressorStatus = clutchCompressorStatus;
        this.dayNightEvent = dayNightEvent;
        this.engineEvent = engineEvent;
        this.espStatus = espStatus;
        this.harshAccelerationStatus = harshAccelerationStatus;
        this.harshBrakingStatus = harshBrakingStatus;
        this.hmiChargingStatus = hmiChargingStatus;
        this.mainWakeUpStatus = mainWakeUpStatus;
        this.parkBreakEvent = parkBreakEvent;
        this.prndStatus = prndStatus;
        this.readyToStartStatus = readyToStartStatus;
        this.remoteLockUnlockStatus = remoteLockUnlockStatus;
        this.reverseEventStatus = reverseEventStatus;
        this.tboxFullSleepStatus = tboxFullSleepStatus;
        this.tboxPartialSleepStatus = tboxPartialSleepStatus;
        this.tboxPartialWakeUpStatus = tboxPartialWakeUpStatus;
        this.tboxSignalStrength = d7;
        this.timeToFullCharge = str7;
    }

    public /* synthetic */ MessageTypeResponse(String str, String str2, String str3, String str4, String str5, String str6, double d2, double d3, String str7, String str8, String str9, String str10, long j2, double d4, String str11, double d5, int i2, int i3, double d6, Object obj, Object obj2, Object obj3, String str12, Object obj4, String str13, String str14, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, String str15, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Double d7, String str16, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? null : str, (i4 & 2) != 0 ? null : str2, (i4 & 4) != 0 ? null : str3, (i4 & 8) != 0 ? "" : str4, (i4 & 16) != 0 ? "" : str5, (i4 & 32) != 0 ? "" : str6, (i4 & 64) != 0 ? 0.0d : d2, (i4 & 128) != 0 ? 0.0d : d3, (i4 & 256) != 0 ? null : str7, (i4 & 512) != 0 ? "" : str8, (i4 & 1024) != 0 ? "" : str9, (i4 & 2048) != 0 ? "" : str10, (i4 & 4096) != 0 ? 0L : j2, (i4 & 8192) != 0 ? 0.0d : d4, (i4 & 16384) != 0 ? null : str11, (i4 & 32768) != 0 ? 0.0d : d5, (i4 & 65536) != 0 ? 0 : i2, (i4 & 131072) == 0 ? i3 : 0, (i4 & 262144) != 0 ? 0.0d : d6, (i4 & 524288) != 0 ? new Object() : obj, (i4 & 1048576) != 0 ? new Object() : obj2, (i4 & 2097152) != 0 ? new Object() : obj3, (i4 & 4194304) != 0 ? "" : str12, (i4 & 8388608) != 0 ? new Object() : obj4, (i4 & 16777216) != 0 ? "" : str13, (i4 & MediaHttpDownloader.MAXIMUM_CHUNK_SIZE) != 0 ? "" : str14, (i4 & 67108864) != 0 ? new Object() : obj5, (i4 & 134217728) != 0 ? new Object() : obj6, (i4 & 268435456) != 0 ? new Object() : obj7, (i4 & PKIFailureInfo.duplicateCertReq) != 0 ? new Object() : obj8, (i4 & 1073741824) != 0 ? new Object() : obj9, (i4 & Integer.MIN_VALUE) != 0 ? "" : str15, (i5 & 1) != 0 ? new Object() : obj10, (i5 & 2) != 0 ? new Object() : obj11, (i5 & 4) != 0 ? new Object() : obj12, (i5 & 8) != 0 ? new Object() : obj13, (i5 & 16) != 0 ? new Object() : obj14, (i5 & 32) != 0 ? new Object() : obj15, (i5 & 64) != 0 ? new Object() : obj16, (i5 & 128) != 0 ? null : d7, (i5 & 256) != 0 ? null : str16);
    }

    public static /* synthetic */ MessageTypeResponse copy$default(MessageTypeResponse messageTypeResponse, String str, String str2, String str3, String str4, String str5, String str6, double d2, double d3, String str7, String str8, String str9, String str10, long j2, double d4, String str11, double d5, int i2, int i3, double d6, Object obj, Object obj2, Object obj3, String str12, Object obj4, String str13, String str14, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, String str15, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Double d7, String str16, int i4, int i5, Object obj17) {
        String str17 = (i4 & 1) != 0 ? messageTypeResponse.acSwitchStatus : str;
        String str18 = (i4 & 2) != 0 ? messageTypeResponse.bonnetStatus : str2;
        String str19 = (i4 & 4) != 0 ? messageTypeResponse.bootStatus : str3;
        String str20 = (i4 & 8) != 0 ? messageTypeResponse.chargingStatus : str4;
        String str21 = (i4 & 16) != 0 ? messageTypeResponse.doorCloseEvent : str5;
        String str22 = (i4 & 32) != 0 ? messageTypeResponse.doorOpenEvent : str6;
        double d8 = (i4 & 64) != 0 ? messageTypeResponse.gpsLat : d2;
        double d9 = (i4 & 128) != 0 ? messageTypeResponse.gpstLong : d3;
        String str23 = (i4 & 256) != 0 ? messageTypeResponse.ignitionStatus : str7;
        String str24 = (i4 & 512) != 0 ? messageTypeResponse.obcPlugStateEvent : str8;
        return messageTypeResponse.copy(str17, str18, str19, str20, str21, str22, d8, d9, str23, str24, (i4 & 1024) != 0 ? messageTypeResponse.seatBeltBuckledEvent : str9, (i4 & 2048) != 0 ? messageTypeResponse.seatBeltOpenEvent : str10, (i4 & 4096) != 0 ? messageTypeResponse.signalTimeStamp : j2, (i4 & 8192) != 0 ? messageTypeResponse.speed : d4, (i4 & 16384) != 0 ? messageTypeResponse.vehicleLockingStatus : str11, (32768 & i4) != 0 ? messageTypeResponse.batteryCharging : d5, (i4 & 65536) != 0 ? messageTypeResponse.dte : i2, (131072 & i4) != 0 ? messageTypeResponse.statusFlag : i3, (i4 & 262144) != 0 ? messageTypeResponse.coarseAngle : d6, (i4 & 524288) != 0 ? messageTypeResponse.absStatus : obj, (1048576 & i4) != 0 ? messageTypeResponse.airConditioningStatus : obj2, (i4 & 2097152) != 0 ? messageTypeResponse.bccmToTboxPartialWakeUpStatus : obj3, (i4 & 4194304) != 0 ? messageTypeResponse.breakPedalSwitchEvent : str12, (i4 & 8388608) != 0 ? messageTypeResponse.clutchCompressorStatus : obj4, (i4 & 16777216) != 0 ? messageTypeResponse.dayNightEvent : str13, (i4 & MediaHttpDownloader.MAXIMUM_CHUNK_SIZE) != 0 ? messageTypeResponse.engineEvent : str14, (i4 & 67108864) != 0 ? messageTypeResponse.espStatus : obj5, (i4 & 134217728) != 0 ? messageTypeResponse.harshAccelerationStatus : obj6, (i4 & 268435456) != 0 ? messageTypeResponse.harshBrakingStatus : obj7, (i4 & PKIFailureInfo.duplicateCertReq) != 0 ? messageTypeResponse.hmiChargingStatus : obj8, (i4 & 1073741824) != 0 ? messageTypeResponse.mainWakeUpStatus : obj9, (i4 & Integer.MIN_VALUE) != 0 ? messageTypeResponse.parkBreakEvent : str15, (i5 & 1) != 0 ? messageTypeResponse.prndStatus : obj10, (i5 & 2) != 0 ? messageTypeResponse.readyToStartStatus : obj11, (i5 & 4) != 0 ? messageTypeResponse.remoteLockUnlockStatus : obj12, (i5 & 8) != 0 ? messageTypeResponse.reverseEventStatus : obj13, (i5 & 16) != 0 ? messageTypeResponse.tboxFullSleepStatus : obj14, (i5 & 32) != 0 ? messageTypeResponse.tboxPartialSleepStatus : obj15, (i5 & 64) != 0 ? messageTypeResponse.tboxPartialWakeUpStatus : obj16, (i5 & 128) != 0 ? messageTypeResponse.tboxSignalStrength : d7, (i5 & 256) != 0 ? messageTypeResponse.timeToFullCharge : str16);
    }

    @Nullable
    public final String component1() {
        return this.acSwitchStatus;
    }

    @NotNull
    public final String component10() {
        return this.obcPlugStateEvent;
    }

    @NotNull
    public final String component11() {
        return this.seatBeltBuckledEvent;
    }

    @NotNull
    public final String component12() {
        return this.seatBeltOpenEvent;
    }

    public final long component13() {
        return this.signalTimeStamp;
    }

    public final double component14() {
        return this.speed;
    }

    @Nullable
    public final String component15() {
        return this.vehicleLockingStatus;
    }

    public final double component16() {
        return this.batteryCharging;
    }

    public final int component17() {
        return this.dte;
    }

    public final int component18() {
        return this.statusFlag;
    }

    public final double component19() {
        return this.coarseAngle;
    }

    @Nullable
    public final String component2() {
        return this.bonnetStatus;
    }

    @NotNull
    public final Object component20() {
        return this.absStatus;
    }

    @NotNull
    public final Object component21() {
        return this.airConditioningStatus;
    }

    @NotNull
    public final Object component22() {
        return this.bccmToTboxPartialWakeUpStatus;
    }

    @NotNull
    public final String component23() {
        return this.breakPedalSwitchEvent;
    }

    @NotNull
    public final Object component24() {
        return this.clutchCompressorStatus;
    }

    @NotNull
    public final String component25() {
        return this.dayNightEvent;
    }

    @NotNull
    public final String component26() {
        return this.engineEvent;
    }

    @NotNull
    public final Object component27() {
        return this.espStatus;
    }

    @NotNull
    public final Object component28() {
        return this.harshAccelerationStatus;
    }

    @NotNull
    public final Object component29() {
        return this.harshBrakingStatus;
    }

    @Nullable
    public final String component3() {
        return this.bootStatus;
    }

    @NotNull
    public final Object component30() {
        return this.hmiChargingStatus;
    }

    @NotNull
    public final Object component31() {
        return this.mainWakeUpStatus;
    }

    @NotNull
    public final String component32() {
        return this.parkBreakEvent;
    }

    @NotNull
    public final Object component33() {
        return this.prndStatus;
    }

    @NotNull
    public final Object component34() {
        return this.readyToStartStatus;
    }

    @NotNull
    public final Object component35() {
        return this.remoteLockUnlockStatus;
    }

    @NotNull
    public final Object component36() {
        return this.reverseEventStatus;
    }

    @NotNull
    public final Object component37() {
        return this.tboxFullSleepStatus;
    }

    @NotNull
    public final Object component38() {
        return this.tboxPartialSleepStatus;
    }

    @NotNull
    public final Object component39() {
        return this.tboxPartialWakeUpStatus;
    }

    @Nullable
    public final String component4() {
        return this.chargingStatus;
    }

    @Nullable
    public final Double component40() {
        return this.tboxSignalStrength;
    }

    @Nullable
    public final String component41() {
        return this.timeToFullCharge;
    }

    @NotNull
    public final String component5() {
        return this.doorCloseEvent;
    }

    @NotNull
    public final String component6() {
        return this.doorOpenEvent;
    }

    public final double component7() {
        return this.gpsLat;
    }

    public final double component8() {
        return this.gpstLong;
    }

    @Nullable
    public final String component9() {
        return this.ignitionStatus;
    }

    @NotNull
    public final MessageTypeResponse copy(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @NotNull String doorCloseEvent, @NotNull String doorOpenEvent, double d2, double d3, @Nullable String str5, @NotNull String obcPlugStateEvent, @NotNull String seatBeltBuckledEvent, @NotNull String seatBeltOpenEvent, long j2, double d4, @Nullable String str6, double d5, int i2, int i3, double d6, @NotNull Object absStatus, @NotNull Object airConditioningStatus, @NotNull Object bccmToTboxPartialWakeUpStatus, @NotNull String breakPedalSwitchEvent, @NotNull Object clutchCompressorStatus, @NotNull String dayNightEvent, @NotNull String engineEvent, @NotNull Object espStatus, @NotNull Object harshAccelerationStatus, @NotNull Object harshBrakingStatus, @NotNull Object hmiChargingStatus, @NotNull Object mainWakeUpStatus, @NotNull String parkBreakEvent, @NotNull Object prndStatus, @NotNull Object readyToStartStatus, @NotNull Object remoteLockUnlockStatus, @NotNull Object reverseEventStatus, @NotNull Object tboxFullSleepStatus, @NotNull Object tboxPartialSleepStatus, @NotNull Object tboxPartialWakeUpStatus, @Nullable Double d7, @Nullable String str7) {
        Intrinsics.checkNotNullParameter(doorCloseEvent, "doorCloseEvent");
        Intrinsics.checkNotNullParameter(doorOpenEvent, "doorOpenEvent");
        Intrinsics.checkNotNullParameter(obcPlugStateEvent, "obcPlugStateEvent");
        Intrinsics.checkNotNullParameter(seatBeltBuckledEvent, "seatBeltBuckledEvent");
        Intrinsics.checkNotNullParameter(seatBeltOpenEvent, "seatBeltOpenEvent");
        Intrinsics.checkNotNullParameter(absStatus, "absStatus");
        Intrinsics.checkNotNullParameter(airConditioningStatus, "airConditioningStatus");
        Intrinsics.checkNotNullParameter(bccmToTboxPartialWakeUpStatus, "bccmToTboxPartialWakeUpStatus");
        Intrinsics.checkNotNullParameter(breakPedalSwitchEvent, "breakPedalSwitchEvent");
        Intrinsics.checkNotNullParameter(clutchCompressorStatus, "clutchCompressorStatus");
        Intrinsics.checkNotNullParameter(dayNightEvent, "dayNightEvent");
        Intrinsics.checkNotNullParameter(engineEvent, "engineEvent");
        Intrinsics.checkNotNullParameter(espStatus, "espStatus");
        Intrinsics.checkNotNullParameter(harshAccelerationStatus, "harshAccelerationStatus");
        Intrinsics.checkNotNullParameter(harshBrakingStatus, "harshBrakingStatus");
        Intrinsics.checkNotNullParameter(hmiChargingStatus, "hmiChargingStatus");
        Intrinsics.checkNotNullParameter(mainWakeUpStatus, "mainWakeUpStatus");
        Intrinsics.checkNotNullParameter(parkBreakEvent, "parkBreakEvent");
        Intrinsics.checkNotNullParameter(prndStatus, "prndStatus");
        Intrinsics.checkNotNullParameter(readyToStartStatus, "readyToStartStatus");
        Intrinsics.checkNotNullParameter(remoteLockUnlockStatus, "remoteLockUnlockStatus");
        Intrinsics.checkNotNullParameter(reverseEventStatus, "reverseEventStatus");
        Intrinsics.checkNotNullParameter(tboxFullSleepStatus, "tboxFullSleepStatus");
        Intrinsics.checkNotNullParameter(tboxPartialSleepStatus, "tboxPartialSleepStatus");
        Intrinsics.checkNotNullParameter(tboxPartialWakeUpStatus, "tboxPartialWakeUpStatus");
        return new MessageTypeResponse(str, str2, str3, str4, doorCloseEvent, doorOpenEvent, d2, d3, str5, obcPlugStateEvent, seatBeltBuckledEvent, seatBeltOpenEvent, j2, d4, str6, d5, i2, i3, d6, absStatus, airConditioningStatus, bccmToTboxPartialWakeUpStatus, breakPedalSwitchEvent, clutchCompressorStatus, dayNightEvent, engineEvent, espStatus, harshAccelerationStatus, harshBrakingStatus, hmiChargingStatus, mainWakeUpStatus, parkBreakEvent, prndStatus, readyToStartStatus, remoteLockUnlockStatus, reverseEventStatus, tboxFullSleepStatus, tboxPartialSleepStatus, tboxPartialWakeUpStatus, d7, str7);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MessageTypeResponse) {
            MessageTypeResponse messageTypeResponse = (MessageTypeResponse) obj;
            return Intrinsics.areEqual(this.acSwitchStatus, messageTypeResponse.acSwitchStatus) && Intrinsics.areEqual(this.bonnetStatus, messageTypeResponse.bonnetStatus) && Intrinsics.areEqual(this.bootStatus, messageTypeResponse.bootStatus) && Intrinsics.areEqual(this.chargingStatus, messageTypeResponse.chargingStatus) && Intrinsics.areEqual(this.doorCloseEvent, messageTypeResponse.doorCloseEvent) && Intrinsics.areEqual(this.doorOpenEvent, messageTypeResponse.doorOpenEvent) && Intrinsics.areEqual((Object) Double.valueOf(this.gpsLat), (Object) Double.valueOf(messageTypeResponse.gpsLat)) && Intrinsics.areEqual((Object) Double.valueOf(this.gpstLong), (Object) Double.valueOf(messageTypeResponse.gpstLong)) && Intrinsics.areEqual(this.ignitionStatus, messageTypeResponse.ignitionStatus) && Intrinsics.areEqual(this.obcPlugStateEvent, messageTypeResponse.obcPlugStateEvent) && Intrinsics.areEqual(this.seatBeltBuckledEvent, messageTypeResponse.seatBeltBuckledEvent) && Intrinsics.areEqual(this.seatBeltOpenEvent, messageTypeResponse.seatBeltOpenEvent) && this.signalTimeStamp == messageTypeResponse.signalTimeStamp && Intrinsics.areEqual((Object) Double.valueOf(this.speed), (Object) Double.valueOf(messageTypeResponse.speed)) && Intrinsics.areEqual(this.vehicleLockingStatus, messageTypeResponse.vehicleLockingStatus) && Intrinsics.areEqual((Object) Double.valueOf(this.batteryCharging), (Object) Double.valueOf(messageTypeResponse.batteryCharging)) && this.dte == messageTypeResponse.dte && this.statusFlag == messageTypeResponse.statusFlag && Intrinsics.areEqual((Object) Double.valueOf(this.coarseAngle), (Object) Double.valueOf(messageTypeResponse.coarseAngle)) && Intrinsics.areEqual(this.absStatus, messageTypeResponse.absStatus) && Intrinsics.areEqual(this.airConditioningStatus, messageTypeResponse.airConditioningStatus) && Intrinsics.areEqual(this.bccmToTboxPartialWakeUpStatus, messageTypeResponse.bccmToTboxPartialWakeUpStatus) && Intrinsics.areEqual(this.breakPedalSwitchEvent, messageTypeResponse.breakPedalSwitchEvent) && Intrinsics.areEqual(this.clutchCompressorStatus, messageTypeResponse.clutchCompressorStatus) && Intrinsics.areEqual(this.dayNightEvent, messageTypeResponse.dayNightEvent) && Intrinsics.areEqual(this.engineEvent, messageTypeResponse.engineEvent) && Intrinsics.areEqual(this.espStatus, messageTypeResponse.espStatus) && Intrinsics.areEqual(this.harshAccelerationStatus, messageTypeResponse.harshAccelerationStatus) && Intrinsics.areEqual(this.harshBrakingStatus, messageTypeResponse.harshBrakingStatus) && Intrinsics.areEqual(this.hmiChargingStatus, messageTypeResponse.hmiChargingStatus) && Intrinsics.areEqual(this.mainWakeUpStatus, messageTypeResponse.mainWakeUpStatus) && Intrinsics.areEqual(this.parkBreakEvent, messageTypeResponse.parkBreakEvent) && Intrinsics.areEqual(this.prndStatus, messageTypeResponse.prndStatus) && Intrinsics.areEqual(this.readyToStartStatus, messageTypeResponse.readyToStartStatus) && Intrinsics.areEqual(this.remoteLockUnlockStatus, messageTypeResponse.remoteLockUnlockStatus) && Intrinsics.areEqual(this.reverseEventStatus, messageTypeResponse.reverseEventStatus) && Intrinsics.areEqual(this.tboxFullSleepStatus, messageTypeResponse.tboxFullSleepStatus) && Intrinsics.areEqual(this.tboxPartialSleepStatus, messageTypeResponse.tboxPartialSleepStatus) && Intrinsics.areEqual(this.tboxPartialWakeUpStatus, messageTypeResponse.tboxPartialWakeUpStatus) && Intrinsics.areEqual((Object) this.tboxSignalStrength, (Object) messageTypeResponse.tboxSignalStrength) && Intrinsics.areEqual(this.timeToFullCharge, messageTypeResponse.timeToFullCharge);
        }
        return false;
    }

    @NotNull
    public final Object getAbsStatus() {
        return this.absStatus;
    }

    @Nullable
    public final String getAcSwitchStatus() {
        return this.acSwitchStatus;
    }

    @NotNull
    public final Object getAirConditioningStatus() {
        return this.airConditioningStatus;
    }

    public final double getBatteryCharging() {
        return this.batteryCharging;
    }

    @NotNull
    public final Object getBccmToTboxPartialWakeUpStatus() {
        return this.bccmToTboxPartialWakeUpStatus;
    }

    @Nullable
    public final String getBonnetStatus() {
        return this.bonnetStatus;
    }

    @Nullable
    public final String getBootStatus() {
        return this.bootStatus;
    }

    @NotNull
    public final String getBreakPedalSwitchEvent() {
        return this.breakPedalSwitchEvent;
    }

    @Nullable
    public final String getChargingStatus() {
        return this.chargingStatus;
    }

    @NotNull
    public final Object getClutchCompressorStatus() {
        return this.clutchCompressorStatus;
    }

    public final double getCoarseAngle() {
        return this.coarseAngle;
    }

    @NotNull
    public final String getDayNightEvent() {
        return this.dayNightEvent;
    }

    @NotNull
    public final String getDoorCloseEvent() {
        return this.doorCloseEvent;
    }

    @NotNull
    public final String getDoorOpenEvent() {
        return this.doorOpenEvent;
    }

    public final int getDte() {
        return this.dte;
    }

    @NotNull
    public final String getEngineEvent() {
        return this.engineEvent;
    }

    @NotNull
    public final Object getEspStatus() {
        return this.espStatus;
    }

    public final double getGpsLat() {
        return this.gpsLat;
    }

    public final double getGpstLong() {
        return this.gpstLong;
    }

    @NotNull
    public final Object getHarshAccelerationStatus() {
        return this.harshAccelerationStatus;
    }

    @NotNull
    public final Object getHarshBrakingStatus() {
        return this.harshBrakingStatus;
    }

    @NotNull
    public final Object getHmiChargingStatus() {
        return this.hmiChargingStatus;
    }

    @Nullable
    public final String getIgnitionStatus() {
        return this.ignitionStatus;
    }

    @NotNull
    public final Object getMainWakeUpStatus() {
        return this.mainWakeUpStatus;
    }

    @NotNull
    public final String getObcPlugStateEvent() {
        return this.obcPlugStateEvent;
    }

    @NotNull
    public final String getParkBreakEvent() {
        return this.parkBreakEvent;
    }

    @NotNull
    public final Object getPrndStatus() {
        return this.prndStatus;
    }

    @NotNull
    public final Object getReadyToStartStatus() {
        return this.readyToStartStatus;
    }

    @NotNull
    public final Object getRemoteLockUnlockStatus() {
        return this.remoteLockUnlockStatus;
    }

    @NotNull
    public final Object getReverseEventStatus() {
        return this.reverseEventStatus;
    }

    @NotNull
    public final String getSeatBeltBuckledEvent() {
        return this.seatBeltBuckledEvent;
    }

    @NotNull
    public final String getSeatBeltOpenEvent() {
        return this.seatBeltOpenEvent;
    }

    public final long getSignalTimeStamp() {
        return this.signalTimeStamp;
    }

    public final double getSpeed() {
        return this.speed;
    }

    public final int getStatusFlag() {
        return this.statusFlag;
    }

    @NotNull
    public final Object getTboxFullSleepStatus() {
        return this.tboxFullSleepStatus;
    }

    @NotNull
    public final Object getTboxPartialSleepStatus() {
        return this.tboxPartialSleepStatus;
    }

    @NotNull
    public final Object getTboxPartialWakeUpStatus() {
        return this.tboxPartialWakeUpStatus;
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

    public int hashCode() {
        String str = this.acSwitchStatus;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.bonnetStatus;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.bootStatus;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.chargingStatus;
        int hashCode4 = (((((((((hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31) + this.doorCloseEvent.hashCode()) * 31) + this.doorOpenEvent.hashCode()) * 31) + Double.hashCode(this.gpsLat)) * 31) + Double.hashCode(this.gpstLong)) * 31;
        String str5 = this.ignitionStatus;
        int hashCode5 = (((((((((((hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31) + this.obcPlugStateEvent.hashCode()) * 31) + this.seatBeltBuckledEvent.hashCode()) * 31) + this.seatBeltOpenEvent.hashCode()) * 31) + Long.hashCode(this.signalTimeStamp)) * 31) + Double.hashCode(this.speed)) * 31;
        String str6 = this.vehicleLockingStatus;
        int hashCode6 = (((((((((((((((((((((((((((((((((((((((((((((((((hashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31) + Double.hashCode(this.batteryCharging)) * 31) + Integer.hashCode(this.dte)) * 31) + Integer.hashCode(this.statusFlag)) * 31) + Double.hashCode(this.coarseAngle)) * 31) + this.absStatus.hashCode()) * 31) + this.airConditioningStatus.hashCode()) * 31) + this.bccmToTboxPartialWakeUpStatus.hashCode()) * 31) + this.breakPedalSwitchEvent.hashCode()) * 31) + this.clutchCompressorStatus.hashCode()) * 31) + this.dayNightEvent.hashCode()) * 31) + this.engineEvent.hashCode()) * 31) + this.espStatus.hashCode()) * 31) + this.harshAccelerationStatus.hashCode()) * 31) + this.harshBrakingStatus.hashCode()) * 31) + this.hmiChargingStatus.hashCode()) * 31) + this.mainWakeUpStatus.hashCode()) * 31) + this.parkBreakEvent.hashCode()) * 31) + this.prndStatus.hashCode()) * 31) + this.readyToStartStatus.hashCode()) * 31) + this.remoteLockUnlockStatus.hashCode()) * 31) + this.reverseEventStatus.hashCode()) * 31) + this.tboxFullSleepStatus.hashCode()) * 31) + this.tboxPartialSleepStatus.hashCode()) * 31) + this.tboxPartialWakeUpStatus.hashCode()) * 31;
        Double d2 = this.tboxSignalStrength;
        int hashCode7 = (hashCode6 + (d2 == null ? 0 : d2.hashCode())) * 31;
        String str7 = this.timeToFullCharge;
        return hashCode7 + (str7 != null ? str7.hashCode() : 0);
    }

    public final void setAbsStatus(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.absStatus = obj;
    }

    public final void setAcSwitchStatus(@Nullable String str) {
        this.acSwitchStatus = str;
    }

    public final void setAirConditioningStatus(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.airConditioningStatus = obj;
    }

    public final void setBatteryCharging(double d2) {
        this.batteryCharging = d2;
    }

    public final void setBccmToTboxPartialWakeUpStatus(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.bccmToTboxPartialWakeUpStatus = obj;
    }

    public final void setBonnetStatus(@Nullable String str) {
        this.bonnetStatus = str;
    }

    public final void setBootStatus(@Nullable String str) {
        this.bootStatus = str;
    }

    public final void setBreakPedalSwitchEvent(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.breakPedalSwitchEvent = str;
    }

    public final void setChargingStatus(@Nullable String str) {
        this.chargingStatus = str;
    }

    public final void setClutchCompressorStatus(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.clutchCompressorStatus = obj;
    }

    public final void setCoarseAngle(double d2) {
        this.coarseAngle = d2;
    }

    public final void setDayNightEvent(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.dayNightEvent = str;
    }

    public final void setDoorCloseEvent(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.doorCloseEvent = str;
    }

    public final void setDoorOpenEvent(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.doorOpenEvent = str;
    }

    public final void setDte(int i2) {
        this.dte = i2;
    }

    public final void setEngineEvent(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.engineEvent = str;
    }

    public final void setEspStatus(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.espStatus = obj;
    }

    public final void setGpsLat(double d2) {
        this.gpsLat = d2;
    }

    public final void setGpstLong(double d2) {
        this.gpstLong = d2;
    }

    public final void setHarshAccelerationStatus(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.harshAccelerationStatus = obj;
    }

    public final void setHarshBrakingStatus(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.harshBrakingStatus = obj;
    }

    public final void setHmiChargingStatus(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.hmiChargingStatus = obj;
    }

    public final void setIgnitionStatus(@Nullable String str) {
        this.ignitionStatus = str;
    }

    public final void setMainWakeUpStatus(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.mainWakeUpStatus = obj;
    }

    public final void setObcPlugStateEvent(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.obcPlugStateEvent = str;
    }

    public final void setParkBreakEvent(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.parkBreakEvent = str;
    }

    public final void setPrndStatus(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.prndStatus = obj;
    }

    public final void setReadyToStartStatus(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.readyToStartStatus = obj;
    }

    public final void setRemoteLockUnlockStatus(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.remoteLockUnlockStatus = obj;
    }

    public final void setReverseEventStatus(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.reverseEventStatus = obj;
    }

    public final void setSeatBeltBuckledEvent(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.seatBeltBuckledEvent = str;
    }

    public final void setSeatBeltOpenEvent(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.seatBeltOpenEvent = str;
    }

    public final void setSignalTimeStamp(long j2) {
        this.signalTimeStamp = j2;
    }

    public final void setSpeed(double d2) {
        this.speed = d2;
    }

    public final void setStatusFlag(int i2) {
        this.statusFlag = i2;
    }

    public final void setTboxFullSleepStatus(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.tboxFullSleepStatus = obj;
    }

    public final void setTboxPartialSleepStatus(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.tboxPartialSleepStatus = obj;
    }

    public final void setTboxPartialWakeUpStatus(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.tboxPartialWakeUpStatus = obj;
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

    @NotNull
    public String toString() {
        return "MessageTypeResponse(acSwitchStatus=" + this.acSwitchStatus + ", bonnetStatus=" + this.bonnetStatus + ", bootStatus=" + this.bootStatus + ", chargingStatus=" + this.chargingStatus + ", doorCloseEvent=" + this.doorCloseEvent + ", doorOpenEvent=" + this.doorOpenEvent + ", gpsLat=" + this.gpsLat + ", gpstLong=" + this.gpstLong + ", ignitionStatus=" + this.ignitionStatus + ", obcPlugStateEvent=" + this.obcPlugStateEvent + ", seatBeltBuckledEvent=" + this.seatBeltBuckledEvent + ", seatBeltOpenEvent=" + this.seatBeltOpenEvent + ", signalTimeStamp=" + this.signalTimeStamp + ", speed=" + this.speed + ", vehicleLockingStatus=" + this.vehicleLockingStatus + ", batteryCharging=" + this.batteryCharging + ", dte=" + this.dte + ", statusFlag=" + this.statusFlag + ", coarseAngle=" + this.coarseAngle + ", absStatus=" + this.absStatus + ", airConditioningStatus=" + this.airConditioningStatus + ", bccmToTboxPartialWakeUpStatus=" + this.bccmToTboxPartialWakeUpStatus + ", breakPedalSwitchEvent=" + this.breakPedalSwitchEvent + ", clutchCompressorStatus=" + this.clutchCompressorStatus + ", dayNightEvent=" + this.dayNightEvent + ", engineEvent=" + this.engineEvent + ", espStatus=" + this.espStatus + ", harshAccelerationStatus=" + this.harshAccelerationStatus + ", harshBrakingStatus=" + this.harshBrakingStatus + ", hmiChargingStatus=" + this.hmiChargingStatus + ", mainWakeUpStatus=" + this.mainWakeUpStatus + ", parkBreakEvent=" + this.parkBreakEvent + ", prndStatus=" + this.prndStatus + ", readyToStartStatus=" + this.readyToStartStatus + ", remoteLockUnlockStatus=" + this.remoteLockUnlockStatus + ", reverseEventStatus=" + this.reverseEventStatus + ", tboxFullSleepStatus=" + this.tboxFullSleepStatus + ", tboxPartialSleepStatus=" + this.tboxPartialSleepStatus + ", tboxPartialWakeUpStatus=" + this.tboxPartialWakeUpStatus + ", tboxSignalStrength=" + this.tboxSignalStrength + ", timeToFullCharge=" + this.timeToFullCharge + ')';
    }
}
