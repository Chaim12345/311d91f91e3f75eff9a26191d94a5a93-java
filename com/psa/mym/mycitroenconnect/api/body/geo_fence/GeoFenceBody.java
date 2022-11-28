package com.psa.mym.mycitroenconnect.api.body.geo_fence;

import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class GeoFenceBody {
    @SerializedName("centre")
    @Nullable
    private Coordinates centre;
    @SerializedName("coordinates")
    @Nullable
    private ArrayList<Coordinates> coordinates;
    @SerializedName("daysOfWeek")
    @Nullable
    private ArrayList<String> daysOfWeek;
    @SerializedName("destinationLocation")
    @Nullable
    private Coordinates destinationLocation;
    @SerializedName("endTime")
    @Nullable
    private String endTime;
    @SerializedName("fenceGeometry")
    @Nullable
    private String fenceGeometry;
    @SerializedName("fenceMode")
    @Nullable
    private String fenceMode;
    @SerializedName("fenceName")
    @Nullable
    private String fenceName;
    @SerializedName("fenceStatus")
    @Nullable
    private String fenceStatus;
    @SerializedName("fenceType")
    @Nullable
    private String fenceType;
    @SerializedName("idlingLimit")
    @Nullable
    private String idlingLimit;
    @SerializedName("isInfinite")
    @Nullable
    private String isInfinite;
    @SerializedName(AppConstants.GEO_FENCE_MODE_RADIUS)
    @Nullable
    private Double radius;
    @SerializedName("sourceLocation")
    @Nullable
    private Coordinates sourceLocation;
    @SerializedName("speedLimit")
    @Nullable
    private String speedLimit;
    @SerializedName("startTime")
    @Nullable
    private String startTime;
    @SerializedName("timeMode")
    @Nullable
    private String timeMode;
    @SerializedName("transitionType")
    @Nullable
    private String transitionType;

    public GeoFenceBody() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 262143, null);
    }

    public GeoFenceBody(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable Coordinates coordinates, @Nullable ArrayList<Coordinates> arrayList, @Nullable Coordinates coordinates2, @Nullable Coordinates coordinates3, @Nullable Double d2, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable String str11, @Nullable ArrayList<String> arrayList2, @Nullable String str12) {
        this.fenceType = str;
        this.fenceGeometry = str2;
        this.fenceStatus = str3;
        this.transitionType = str4;
        this.centre = coordinates;
        this.coordinates = arrayList;
        this.sourceLocation = coordinates2;
        this.destinationLocation = coordinates3;
        this.radius = d2;
        this.speedLimit = str5;
        this.idlingLimit = str6;
        this.startTime = str7;
        this.endTime = str8;
        this.isInfinite = str9;
        this.fenceName = str10;
        this.fenceMode = str11;
        this.daysOfWeek = arrayList2;
        this.timeMode = str12;
    }

    public /* synthetic */ GeoFenceBody(String str, String str2, String str3, String str4, Coordinates coordinates, ArrayList arrayList, Coordinates coordinates2, Coordinates coordinates3, Double d2, String str5, String str6, String str7, String str8, String str9, String str10, String str11, ArrayList arrayList2, String str12, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : coordinates, (i2 & 32) != 0 ? null : arrayList, (i2 & 64) != 0 ? null : coordinates2, (i2 & 128) != 0 ? null : coordinates3, (i2 & 256) != 0 ? null : d2, (i2 & 512) != 0 ? null : str5, (i2 & 1024) != 0 ? null : str6, (i2 & 2048) != 0 ? null : str7, (i2 & 4096) != 0 ? null : str8, (i2 & 8192) != 0 ? "false" : str9, (i2 & 16384) != 0 ? null : str10, (i2 & 32768) != 0 ? null : str11, (i2 & 65536) != 0 ? null : arrayList2, (i2 & 131072) != 0 ? null : str12);
    }

    @Nullable
    public final String component1() {
        return this.fenceType;
    }

    @Nullable
    public final String component10() {
        return this.speedLimit;
    }

    @Nullable
    public final String component11() {
        return this.idlingLimit;
    }

    @Nullable
    public final String component12() {
        return this.startTime;
    }

    @Nullable
    public final String component13() {
        return this.endTime;
    }

    @Nullable
    public final String component14() {
        return this.isInfinite;
    }

    @Nullable
    public final String component15() {
        return this.fenceName;
    }

    @Nullable
    public final String component16() {
        return this.fenceMode;
    }

    @Nullable
    public final ArrayList<String> component17() {
        return this.daysOfWeek;
    }

    @Nullable
    public final String component18() {
        return this.timeMode;
    }

    @Nullable
    public final String component2() {
        return this.fenceGeometry;
    }

    @Nullable
    public final String component3() {
        return this.fenceStatus;
    }

    @Nullable
    public final String component4() {
        return this.transitionType;
    }

    @Nullable
    public final Coordinates component5() {
        return this.centre;
    }

    @Nullable
    public final ArrayList<Coordinates> component6() {
        return this.coordinates;
    }

    @Nullable
    public final Coordinates component7() {
        return this.sourceLocation;
    }

    @Nullable
    public final Coordinates component8() {
        return this.destinationLocation;
    }

    @Nullable
    public final Double component9() {
        return this.radius;
    }

    @NotNull
    public final GeoFenceBody copy(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable Coordinates coordinates, @Nullable ArrayList<Coordinates> arrayList, @Nullable Coordinates coordinates2, @Nullable Coordinates coordinates3, @Nullable Double d2, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable String str11, @Nullable ArrayList<String> arrayList2, @Nullable String str12) {
        return new GeoFenceBody(str, str2, str3, str4, coordinates, arrayList, coordinates2, coordinates3, d2, str5, str6, str7, str8, str9, str10, str11, arrayList2, str12);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GeoFenceBody) {
            GeoFenceBody geoFenceBody = (GeoFenceBody) obj;
            return Intrinsics.areEqual(this.fenceType, geoFenceBody.fenceType) && Intrinsics.areEqual(this.fenceGeometry, geoFenceBody.fenceGeometry) && Intrinsics.areEqual(this.fenceStatus, geoFenceBody.fenceStatus) && Intrinsics.areEqual(this.transitionType, geoFenceBody.transitionType) && Intrinsics.areEqual(this.centre, geoFenceBody.centre) && Intrinsics.areEqual(this.coordinates, geoFenceBody.coordinates) && Intrinsics.areEqual(this.sourceLocation, geoFenceBody.sourceLocation) && Intrinsics.areEqual(this.destinationLocation, geoFenceBody.destinationLocation) && Intrinsics.areEqual((Object) this.radius, (Object) geoFenceBody.radius) && Intrinsics.areEqual(this.speedLimit, geoFenceBody.speedLimit) && Intrinsics.areEqual(this.idlingLimit, geoFenceBody.idlingLimit) && Intrinsics.areEqual(this.startTime, geoFenceBody.startTime) && Intrinsics.areEqual(this.endTime, geoFenceBody.endTime) && Intrinsics.areEqual(this.isInfinite, geoFenceBody.isInfinite) && Intrinsics.areEqual(this.fenceName, geoFenceBody.fenceName) && Intrinsics.areEqual(this.fenceMode, geoFenceBody.fenceMode) && Intrinsics.areEqual(this.daysOfWeek, geoFenceBody.daysOfWeek) && Intrinsics.areEqual(this.timeMode, geoFenceBody.timeMode);
        }
        return false;
    }

    @Nullable
    public final Coordinates getCentre() {
        return this.centre;
    }

    @Nullable
    public final ArrayList<Coordinates> getCoordinates() {
        return this.coordinates;
    }

    @Nullable
    public final ArrayList<String> getDaysOfWeek() {
        return this.daysOfWeek;
    }

    @Nullable
    public final Coordinates getDestinationLocation() {
        return this.destinationLocation;
    }

    @Nullable
    public final String getEndTime() {
        return this.endTime;
    }

    @Nullable
    public final String getFenceGeometry() {
        return this.fenceGeometry;
    }

    @Nullable
    public final String getFenceMode() {
        return this.fenceMode;
    }

    @Nullable
    public final String getFenceName() {
        return this.fenceName;
    }

    @Nullable
    public final String getFenceStatus() {
        return this.fenceStatus;
    }

    @Nullable
    public final String getFenceType() {
        return this.fenceType;
    }

    @Nullable
    public final String getIdlingLimit() {
        return this.idlingLimit;
    }

    @Nullable
    public final Double getRadius() {
        return this.radius;
    }

    @Nullable
    public final Coordinates getSourceLocation() {
        return this.sourceLocation;
    }

    @Nullable
    public final String getSpeedLimit() {
        return this.speedLimit;
    }

    @Nullable
    public final String getStartTime() {
        return this.startTime;
    }

    @Nullable
    public final String getTimeMode() {
        return this.timeMode;
    }

    @Nullable
    public final String getTransitionType() {
        return this.transitionType;
    }

    public int hashCode() {
        String str = this.fenceType;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.fenceGeometry;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.fenceStatus;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.transitionType;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Coordinates coordinates = this.centre;
        int hashCode5 = (hashCode4 + (coordinates == null ? 0 : coordinates.hashCode())) * 31;
        ArrayList<Coordinates> arrayList = this.coordinates;
        int hashCode6 = (hashCode5 + (arrayList == null ? 0 : arrayList.hashCode())) * 31;
        Coordinates coordinates2 = this.sourceLocation;
        int hashCode7 = (hashCode6 + (coordinates2 == null ? 0 : coordinates2.hashCode())) * 31;
        Coordinates coordinates3 = this.destinationLocation;
        int hashCode8 = (hashCode7 + (coordinates3 == null ? 0 : coordinates3.hashCode())) * 31;
        Double d2 = this.radius;
        int hashCode9 = (hashCode8 + (d2 == null ? 0 : d2.hashCode())) * 31;
        String str5 = this.speedLimit;
        int hashCode10 = (hashCode9 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.idlingLimit;
        int hashCode11 = (hashCode10 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.startTime;
        int hashCode12 = (hashCode11 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.endTime;
        int hashCode13 = (hashCode12 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.isInfinite;
        int hashCode14 = (hashCode13 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.fenceName;
        int hashCode15 = (hashCode14 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.fenceMode;
        int hashCode16 = (hashCode15 + (str11 == null ? 0 : str11.hashCode())) * 31;
        ArrayList<String> arrayList2 = this.daysOfWeek;
        int hashCode17 = (hashCode16 + (arrayList2 == null ? 0 : arrayList2.hashCode())) * 31;
        String str12 = this.timeMode;
        return hashCode17 + (str12 != null ? str12.hashCode() : 0);
    }

    @Nullable
    public final String isInfinite() {
        return this.isInfinite;
    }

    public final void setCentre(@Nullable Coordinates coordinates) {
        this.centre = coordinates;
    }

    public final void setCoordinates(@Nullable ArrayList<Coordinates> arrayList) {
        this.coordinates = arrayList;
    }

    public final void setDaysOfWeek(@Nullable ArrayList<String> arrayList) {
        this.daysOfWeek = arrayList;
    }

    public final void setDestinationLocation(@Nullable Coordinates coordinates) {
        this.destinationLocation = coordinates;
    }

    public final void setEndTime(@Nullable String str) {
        this.endTime = str;
    }

    public final void setFenceGeometry(@Nullable String str) {
        this.fenceGeometry = str;
    }

    public final void setFenceMode(@Nullable String str) {
        this.fenceMode = str;
    }

    public final void setFenceName(@Nullable String str) {
        this.fenceName = str;
    }

    public final void setFenceStatus(@Nullable String str) {
        this.fenceStatus = str;
    }

    public final void setFenceType(@Nullable String str) {
        this.fenceType = str;
    }

    public final void setIdlingLimit(@Nullable String str) {
        this.idlingLimit = str;
    }

    public final void setInfinite(@Nullable String str) {
        this.isInfinite = str;
    }

    public final void setRadius(@Nullable Double d2) {
        this.radius = d2;
    }

    public final void setSourceLocation(@Nullable Coordinates coordinates) {
        this.sourceLocation = coordinates;
    }

    public final void setSpeedLimit(@Nullable String str) {
        this.speedLimit = str;
    }

    public final void setStartTime(@Nullable String str) {
        this.startTime = str;
    }

    public final void setTimeMode(@Nullable String str) {
        this.timeMode = str;
    }

    public final void setTransitionType(@Nullable String str) {
        this.transitionType = str;
    }

    @NotNull
    public String toString() {
        return "GeoFenceBody(fenceType=" + this.fenceType + ", fenceGeometry=" + this.fenceGeometry + ", fenceStatus=" + this.fenceStatus + ", transitionType=" + this.transitionType + ", centre=" + this.centre + ", coordinates=" + this.coordinates + ", sourceLocation=" + this.sourceLocation + ", destinationLocation=" + this.destinationLocation + ", radius=" + this.radius + ", speedLimit=" + this.speedLimit + ", idlingLimit=" + this.idlingLimit + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", isInfinite=" + this.isInfinite + ", fenceName=" + this.fenceName + ", fenceMode=" + this.fenceMode + ", daysOfWeek=" + this.daysOfWeek + ", timeMode=" + this.timeMode + ')';
    }
}
