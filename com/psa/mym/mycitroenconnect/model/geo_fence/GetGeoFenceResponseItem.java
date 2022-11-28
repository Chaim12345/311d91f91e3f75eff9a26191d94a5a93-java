package com.psa.mym.mycitroenconnect.model.geo_fence;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Parcelize
/* loaded from: classes3.dex */
public final class GetGeoFenceResponseItem implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<GetGeoFenceResponseItem> CREATOR = new Creator();
    @SerializedName("centre")
    @Nullable
    private LocationData centre;
    @SerializedName("coordinates")
    @Nullable
    private List<LocationData> coordinates;
    @SerializedName("createdTimeStamp")
    @Nullable
    private Long createdTimeStamp;
    @SerializedName("daysOfWeek")
    @Nullable
    private List<String> daysOfWeek;
    @SerializedName("destinationLocation")
    @Nullable
    private LocationData destinationLocation;
    @SerializedName("endTime")
    @Nullable
    private String endTime;
    @SerializedName("fenceGeometry")
    @Nullable
    private String fenceGeometry;
    @SerializedName("fenceId")
    @Nullable
    private String fenceId;
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
    private Integer idlingLimit;
    @SerializedName("isInfinite")
    @Nullable
    private String isInfinite;
    @SerializedName("nextoccurence")
    @Nullable
    private String nextOccurrence;
    @SerializedName(AppConstants.GEO_FENCE_MODE_RADIUS)
    @Nullable
    private Integer radius;
    @SerializedName("sourceLocation")
    @Nullable
    private LocationData sourceLocation;
    @SerializedName("speedLimit")
    @Nullable
    private Integer speedLimit;
    @SerializedName("startTime")
    @Nullable
    private String startTime;
    @SerializedName("timeMode")
    @Nullable
    private String timeMode;
    @SerializedName("transitionType")
    @Nullable
    private String transitionType;
    @SerializedName("updatedTimeStamp")
    @Nullable
    private Long updatedTimeStamp;
    @SerializedName("vinNo")
    @Nullable
    private String vinNo;

    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<GetGeoFenceResponseItem> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final GetGeoFenceResponseItem createFromParcel(@NotNull Parcel parcel) {
            ArrayList arrayList;
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            String readString5 = parcel.readString();
            String readString6 = parcel.readString();
            String readString7 = parcel.readString();
            String readString8 = parcel.readString();
            LocationData createFromParcel = parcel.readInt() == 0 ? null : LocationData.CREATOR.createFromParcel(parcel);
            Integer valueOf = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            if (parcel.readInt() == 0) {
                arrayList = null;
            } else {
                int readInt = parcel.readInt();
                arrayList = new ArrayList(readInt);
                for (int i2 = 0; i2 != readInt; i2++) {
                    arrayList.add(LocationData.CREATOR.createFromParcel(parcel));
                }
            }
            return new GetGeoFenceResponseItem(readString, readString2, readString3, readString4, readString5, readString6, readString7, readString8, createFromParcel, valueOf, arrayList, parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readString(), parcel.createStringArrayList(), parcel.readInt() == 0 ? null : LocationData.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : LocationData.CREATOR.createFromParcel(parcel), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong()), parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong()));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final GetGeoFenceResponseItem[] newArray(int i2) {
            return new GetGeoFenceResponseItem[i2];
        }
    }

    public GetGeoFenceResponseItem() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 8388607, null);
    }

    public GetGeoFenceResponseItem(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable LocationData locationData, @Nullable Integer num, @Nullable List<LocationData> list, @Nullable Integer num2, @Nullable Integer num3, @Nullable String str9, @Nullable List<String> list2, @Nullable LocationData locationData2, @Nullable LocationData locationData3, @Nullable String str10, @Nullable String str11, @Nullable String str12, @Nullable String str13, @Nullable Long l2, @Nullable Long l3) {
        this.vinNo = str;
        this.fenceName = str2;
        this.fenceId = str3;
        this.fenceType = str4;
        this.fenceMode = str5;
        this.fenceStatus = str6;
        this.fenceGeometry = str7;
        this.transitionType = str8;
        this.centre = locationData;
        this.radius = num;
        this.coordinates = list;
        this.speedLimit = num2;
        this.idlingLimit = num3;
        this.isInfinite = str9;
        this.daysOfWeek = list2;
        this.sourceLocation = locationData2;
        this.destinationLocation = locationData3;
        this.timeMode = str10;
        this.startTime = str11;
        this.endTime = str12;
        this.nextOccurrence = str13;
        this.createdTimeStamp = l2;
        this.updatedTimeStamp = l3;
    }

    public /* synthetic */ GetGeoFenceResponseItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, LocationData locationData, Integer num, List list, Integer num2, Integer num3, String str9, List list2, LocationData locationData2, LocationData locationData3, String str10, String str11, String str12, String str13, Long l2, Long l3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3, (i2 & 8) != 0 ? "" : str4, (i2 & 16) != 0 ? "" : str5, (i2 & 32) != 0 ? "" : str6, (i2 & 64) != 0 ? "" : str7, (i2 & 128) != 0 ? "" : str8, (i2 & 256) != 0 ? null : locationData, (i2 & 512) != 0 ? null : num, (i2 & 1024) != 0 ? null : list, (i2 & 2048) != 0 ? null : num2, (i2 & 4096) != 0 ? null : num3, (i2 & 8192) != 0 ? null : str9, (i2 & 16384) != 0 ? null : list2, (i2 & 32768) != 0 ? null : locationData2, (i2 & 65536) != 0 ? null : locationData3, (i2 & 131072) != 0 ? "" : str10, (i2 & 262144) != 0 ? "" : str11, (i2 & 524288) != 0 ? "" : str12, (i2 & 1048576) != 0 ? "" : str13, (i2 & 2097152) != 0 ? null : l2, (i2 & 4194304) != 0 ? null : l3);
    }

    @Nullable
    public final String component1() {
        return this.vinNo;
    }

    @Nullable
    public final Integer component10() {
        return this.radius;
    }

    @Nullable
    public final List<LocationData> component11() {
        return this.coordinates;
    }

    @Nullable
    public final Integer component12() {
        return this.speedLimit;
    }

    @Nullable
    public final Integer component13() {
        return this.idlingLimit;
    }

    @Nullable
    public final String component14() {
        return this.isInfinite;
    }

    @Nullable
    public final List<String> component15() {
        return this.daysOfWeek;
    }

    @Nullable
    public final LocationData component16() {
        return this.sourceLocation;
    }

    @Nullable
    public final LocationData component17() {
        return this.destinationLocation;
    }

    @Nullable
    public final String component18() {
        return this.timeMode;
    }

    @Nullable
    public final String component19() {
        return this.startTime;
    }

    @Nullable
    public final String component2() {
        return this.fenceName;
    }

    @Nullable
    public final String component20() {
        return this.endTime;
    }

    @Nullable
    public final String component21() {
        return this.nextOccurrence;
    }

    @Nullable
    public final Long component22() {
        return this.createdTimeStamp;
    }

    @Nullable
    public final Long component23() {
        return this.updatedTimeStamp;
    }

    @Nullable
    public final String component3() {
        return this.fenceId;
    }

    @Nullable
    public final String component4() {
        return this.fenceType;
    }

    @Nullable
    public final String component5() {
        return this.fenceMode;
    }

    @Nullable
    public final String component6() {
        return this.fenceStatus;
    }

    @Nullable
    public final String component7() {
        return this.fenceGeometry;
    }

    @Nullable
    public final String component8() {
        return this.transitionType;
    }

    @Nullable
    public final LocationData component9() {
        return this.centre;
    }

    @NotNull
    public final GetGeoFenceResponseItem copy(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable LocationData locationData, @Nullable Integer num, @Nullable List<LocationData> list, @Nullable Integer num2, @Nullable Integer num3, @Nullable String str9, @Nullable List<String> list2, @Nullable LocationData locationData2, @Nullable LocationData locationData3, @Nullable String str10, @Nullable String str11, @Nullable String str12, @Nullable String str13, @Nullable Long l2, @Nullable Long l3) {
        return new GetGeoFenceResponseItem(str, str2, str3, str4, str5, str6, str7, str8, locationData, num, list, num2, num3, str9, list2, locationData2, locationData3, str10, str11, str12, str13, l2, l3);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GetGeoFenceResponseItem) {
            GetGeoFenceResponseItem getGeoFenceResponseItem = (GetGeoFenceResponseItem) obj;
            return Intrinsics.areEqual(this.vinNo, getGeoFenceResponseItem.vinNo) && Intrinsics.areEqual(this.fenceName, getGeoFenceResponseItem.fenceName) && Intrinsics.areEqual(this.fenceId, getGeoFenceResponseItem.fenceId) && Intrinsics.areEqual(this.fenceType, getGeoFenceResponseItem.fenceType) && Intrinsics.areEqual(this.fenceMode, getGeoFenceResponseItem.fenceMode) && Intrinsics.areEqual(this.fenceStatus, getGeoFenceResponseItem.fenceStatus) && Intrinsics.areEqual(this.fenceGeometry, getGeoFenceResponseItem.fenceGeometry) && Intrinsics.areEqual(this.transitionType, getGeoFenceResponseItem.transitionType) && Intrinsics.areEqual(this.centre, getGeoFenceResponseItem.centre) && Intrinsics.areEqual(this.radius, getGeoFenceResponseItem.radius) && Intrinsics.areEqual(this.coordinates, getGeoFenceResponseItem.coordinates) && Intrinsics.areEqual(this.speedLimit, getGeoFenceResponseItem.speedLimit) && Intrinsics.areEqual(this.idlingLimit, getGeoFenceResponseItem.idlingLimit) && Intrinsics.areEqual(this.isInfinite, getGeoFenceResponseItem.isInfinite) && Intrinsics.areEqual(this.daysOfWeek, getGeoFenceResponseItem.daysOfWeek) && Intrinsics.areEqual(this.sourceLocation, getGeoFenceResponseItem.sourceLocation) && Intrinsics.areEqual(this.destinationLocation, getGeoFenceResponseItem.destinationLocation) && Intrinsics.areEqual(this.timeMode, getGeoFenceResponseItem.timeMode) && Intrinsics.areEqual(this.startTime, getGeoFenceResponseItem.startTime) && Intrinsics.areEqual(this.endTime, getGeoFenceResponseItem.endTime) && Intrinsics.areEqual(this.nextOccurrence, getGeoFenceResponseItem.nextOccurrence) && Intrinsics.areEqual(this.createdTimeStamp, getGeoFenceResponseItem.createdTimeStamp) && Intrinsics.areEqual(this.updatedTimeStamp, getGeoFenceResponseItem.updatedTimeStamp);
        }
        return false;
    }

    @Nullable
    public final LocationData getCentre() {
        return this.centre;
    }

    @Nullable
    public final List<LocationData> getCoordinates() {
        return this.coordinates;
    }

    @Nullable
    public final Long getCreatedTimeStamp() {
        return this.createdTimeStamp;
    }

    @Nullable
    public final List<String> getDaysOfWeek() {
        return this.daysOfWeek;
    }

    @Nullable
    public final LocationData getDestinationLocation() {
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
    public final String getFenceId() {
        return this.fenceId;
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
    public final Integer getIdlingLimit() {
        return this.idlingLimit;
    }

    @Nullable
    public final String getNextOccurrence() {
        return this.nextOccurrence;
    }

    @Nullable
    public final Integer getRadius() {
        return this.radius;
    }

    @Nullable
    public final LocationData getSourceLocation() {
        return this.sourceLocation;
    }

    @Nullable
    public final Integer getSpeedLimit() {
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

    @Nullable
    public final Long getUpdatedTimeStamp() {
        return this.updatedTimeStamp;
    }

    @Nullable
    public final String getVinNo() {
        return this.vinNo;
    }

    public int hashCode() {
        String str = this.vinNo;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.fenceName;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.fenceId;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.fenceType;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.fenceMode;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.fenceStatus;
        int hashCode6 = (hashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.fenceGeometry;
        int hashCode7 = (hashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.transitionType;
        int hashCode8 = (hashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        LocationData locationData = this.centre;
        int hashCode9 = (hashCode8 + (locationData == null ? 0 : locationData.hashCode())) * 31;
        Integer num = this.radius;
        int hashCode10 = (hashCode9 + (num == null ? 0 : num.hashCode())) * 31;
        List<LocationData> list = this.coordinates;
        int hashCode11 = (hashCode10 + (list == null ? 0 : list.hashCode())) * 31;
        Integer num2 = this.speedLimit;
        int hashCode12 = (hashCode11 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.idlingLimit;
        int hashCode13 = (hashCode12 + (num3 == null ? 0 : num3.hashCode())) * 31;
        String str9 = this.isInfinite;
        int hashCode14 = (hashCode13 + (str9 == null ? 0 : str9.hashCode())) * 31;
        List<String> list2 = this.daysOfWeek;
        int hashCode15 = (hashCode14 + (list2 == null ? 0 : list2.hashCode())) * 31;
        LocationData locationData2 = this.sourceLocation;
        int hashCode16 = (hashCode15 + (locationData2 == null ? 0 : locationData2.hashCode())) * 31;
        LocationData locationData3 = this.destinationLocation;
        int hashCode17 = (hashCode16 + (locationData3 == null ? 0 : locationData3.hashCode())) * 31;
        String str10 = this.timeMode;
        int hashCode18 = (hashCode17 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.startTime;
        int hashCode19 = (hashCode18 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.endTime;
        int hashCode20 = (hashCode19 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.nextOccurrence;
        int hashCode21 = (hashCode20 + (str13 == null ? 0 : str13.hashCode())) * 31;
        Long l2 = this.createdTimeStamp;
        int hashCode22 = (hashCode21 + (l2 == null ? 0 : l2.hashCode())) * 31;
        Long l3 = this.updatedTimeStamp;
        return hashCode22 + (l3 != null ? l3.hashCode() : 0);
    }

    @Nullable
    public final String isInfinite() {
        return this.isInfinite;
    }

    public final void setCentre(@Nullable LocationData locationData) {
        this.centre = locationData;
    }

    public final void setCoordinates(@Nullable List<LocationData> list) {
        this.coordinates = list;
    }

    public final void setCreatedTimeStamp(@Nullable Long l2) {
        this.createdTimeStamp = l2;
    }

    public final void setDaysOfWeek(@Nullable List<String> list) {
        this.daysOfWeek = list;
    }

    public final void setDestinationLocation(@Nullable LocationData locationData) {
        this.destinationLocation = locationData;
    }

    public final void setEndTime(@Nullable String str) {
        this.endTime = str;
    }

    public final void setFenceGeometry(@Nullable String str) {
        this.fenceGeometry = str;
    }

    public final void setFenceId(@Nullable String str) {
        this.fenceId = str;
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

    public final void setIdlingLimit(@Nullable Integer num) {
        this.idlingLimit = num;
    }

    public final void setInfinite(@Nullable String str) {
        this.isInfinite = str;
    }

    public final void setNextOccurrence(@Nullable String str) {
        this.nextOccurrence = str;
    }

    public final void setRadius(@Nullable Integer num) {
        this.radius = num;
    }

    public final void setSourceLocation(@Nullable LocationData locationData) {
        this.sourceLocation = locationData;
    }

    public final void setSpeedLimit(@Nullable Integer num) {
        this.speedLimit = num;
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

    public final void setUpdatedTimeStamp(@Nullable Long l2) {
        this.updatedTimeStamp = l2;
    }

    public final void setVinNo(@Nullable String str) {
        this.vinNo = str;
    }

    @NotNull
    public String toString() {
        return "GetGeoFenceResponseItem(vinNo=" + this.vinNo + ", fenceName=" + this.fenceName + ", fenceId=" + this.fenceId + ", fenceType=" + this.fenceType + ", fenceMode=" + this.fenceMode + ", fenceStatus=" + this.fenceStatus + ", fenceGeometry=" + this.fenceGeometry + ", transitionType=" + this.transitionType + ", centre=" + this.centre + ", radius=" + this.radius + ", coordinates=" + this.coordinates + ", speedLimit=" + this.speedLimit + ", idlingLimit=" + this.idlingLimit + ", isInfinite=" + this.isInfinite + ", daysOfWeek=" + this.daysOfWeek + ", sourceLocation=" + this.sourceLocation + ", destinationLocation=" + this.destinationLocation + ", timeMode=" + this.timeMode + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", nextOccurrence=" + this.nextOccurrence + ", createdTimeStamp=" + this.createdTimeStamp + ", updatedTimeStamp=" + this.updatedTimeStamp + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.vinNo);
        out.writeString(this.fenceName);
        out.writeString(this.fenceId);
        out.writeString(this.fenceType);
        out.writeString(this.fenceMode);
        out.writeString(this.fenceStatus);
        out.writeString(this.fenceGeometry);
        out.writeString(this.transitionType);
        LocationData locationData = this.centre;
        if (locationData == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            locationData.writeToParcel(out, i2);
        }
        Integer num = this.radius;
        if (num == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            out.writeInt(num.intValue());
        }
        List<LocationData> list = this.coordinates;
        if (list == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            out.writeInt(list.size());
            for (LocationData locationData2 : list) {
                locationData2.writeToParcel(out, i2);
            }
        }
        Integer num2 = this.speedLimit;
        if (num2 == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            out.writeInt(num2.intValue());
        }
        Integer num3 = this.idlingLimit;
        if (num3 == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            out.writeInt(num3.intValue());
        }
        out.writeString(this.isInfinite);
        out.writeStringList(this.daysOfWeek);
        LocationData locationData3 = this.sourceLocation;
        if (locationData3 == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            locationData3.writeToParcel(out, i2);
        }
        LocationData locationData4 = this.destinationLocation;
        if (locationData4 == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            locationData4.writeToParcel(out, i2);
        }
        out.writeString(this.timeMode);
        out.writeString(this.startTime);
        out.writeString(this.endTime);
        out.writeString(this.nextOccurrence);
        Long l2 = this.createdTimeStamp;
        if (l2 == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            out.writeLong(l2.longValue());
        }
        Long l3 = this.updatedTimeStamp;
        if (l3 == null) {
            out.writeInt(0);
            return;
        }
        out.writeInt(1);
        out.writeLong(l3.longValue());
    }
}
