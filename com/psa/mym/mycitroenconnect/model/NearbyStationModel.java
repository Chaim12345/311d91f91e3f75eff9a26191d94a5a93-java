package com.psa.mym.mycitroenconnect.model;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Parcelize
/* loaded from: classes3.dex */
public final class NearbyStationModel implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<NearbyStationModel> CREATOR = new Creator();
    @NotNull
    private String stationCharge;
    @NotNull
    private String stationDistance;
    @NotNull
    private String stationName;
    @NotNull
    private String stationSubLocation;
    @NotNull
    private String stationTime;
    @NotNull
    private String stationUnit;

    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<NearbyStationModel> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final NearbyStationModel createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new NearbyStationModel(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final NearbyStationModel[] newArray(int i2) {
            return new NearbyStationModel[i2];
        }
    }

    public NearbyStationModel() {
        this(null, null, null, null, null, null, 63, null);
    }

    public NearbyStationModel(@NotNull String stationName, @NotNull String stationDistance, @NotNull String stationCharge, @NotNull String stationUnit, @NotNull String stationSubLocation, @NotNull String stationTime) {
        Intrinsics.checkNotNullParameter(stationName, "stationName");
        Intrinsics.checkNotNullParameter(stationDistance, "stationDistance");
        Intrinsics.checkNotNullParameter(stationCharge, "stationCharge");
        Intrinsics.checkNotNullParameter(stationUnit, "stationUnit");
        Intrinsics.checkNotNullParameter(stationSubLocation, "stationSubLocation");
        Intrinsics.checkNotNullParameter(stationTime, "stationTime");
        this.stationName = stationName;
        this.stationDistance = stationDistance;
        this.stationCharge = stationCharge;
        this.stationUnit = stationUnit;
        this.stationSubLocation = stationSubLocation;
        this.stationTime = stationTime;
    }

    public /* synthetic */ NearbyStationModel(String str, String str2, String str3, String str4, String str5, String str6, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3, (i2 & 8) != 0 ? "" : str4, (i2 & 16) != 0 ? "" : str5, (i2 & 32) != 0 ? "" : str6);
    }

    public static /* synthetic */ NearbyStationModel copy$default(NearbyStationModel nearbyStationModel, String str, String str2, String str3, String str4, String str5, String str6, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = nearbyStationModel.stationName;
        }
        if ((i2 & 2) != 0) {
            str2 = nearbyStationModel.stationDistance;
        }
        String str7 = str2;
        if ((i2 & 4) != 0) {
            str3 = nearbyStationModel.stationCharge;
        }
        String str8 = str3;
        if ((i2 & 8) != 0) {
            str4 = nearbyStationModel.stationUnit;
        }
        String str9 = str4;
        if ((i2 & 16) != 0) {
            str5 = nearbyStationModel.stationSubLocation;
        }
        String str10 = str5;
        if ((i2 & 32) != 0) {
            str6 = nearbyStationModel.stationTime;
        }
        return nearbyStationModel.copy(str, str7, str8, str9, str10, str6);
    }

    @NotNull
    public final String component1() {
        return this.stationName;
    }

    @NotNull
    public final String component2() {
        return this.stationDistance;
    }

    @NotNull
    public final String component3() {
        return this.stationCharge;
    }

    @NotNull
    public final String component4() {
        return this.stationUnit;
    }

    @NotNull
    public final String component5() {
        return this.stationSubLocation;
    }

    @NotNull
    public final String component6() {
        return this.stationTime;
    }

    @NotNull
    public final NearbyStationModel copy(@NotNull String stationName, @NotNull String stationDistance, @NotNull String stationCharge, @NotNull String stationUnit, @NotNull String stationSubLocation, @NotNull String stationTime) {
        Intrinsics.checkNotNullParameter(stationName, "stationName");
        Intrinsics.checkNotNullParameter(stationDistance, "stationDistance");
        Intrinsics.checkNotNullParameter(stationCharge, "stationCharge");
        Intrinsics.checkNotNullParameter(stationUnit, "stationUnit");
        Intrinsics.checkNotNullParameter(stationSubLocation, "stationSubLocation");
        Intrinsics.checkNotNullParameter(stationTime, "stationTime");
        return new NearbyStationModel(stationName, stationDistance, stationCharge, stationUnit, stationSubLocation, stationTime);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NearbyStationModel) {
            NearbyStationModel nearbyStationModel = (NearbyStationModel) obj;
            return Intrinsics.areEqual(this.stationName, nearbyStationModel.stationName) && Intrinsics.areEqual(this.stationDistance, nearbyStationModel.stationDistance) && Intrinsics.areEqual(this.stationCharge, nearbyStationModel.stationCharge) && Intrinsics.areEqual(this.stationUnit, nearbyStationModel.stationUnit) && Intrinsics.areEqual(this.stationSubLocation, nearbyStationModel.stationSubLocation) && Intrinsics.areEqual(this.stationTime, nearbyStationModel.stationTime);
        }
        return false;
    }

    @NotNull
    public final String getStationCharge() {
        return this.stationCharge;
    }

    @NotNull
    public final String getStationDistance() {
        return this.stationDistance;
    }

    @NotNull
    public final String getStationName() {
        return this.stationName;
    }

    @NotNull
    public final String getStationSubLocation() {
        return this.stationSubLocation;
    }

    @NotNull
    public final String getStationTime() {
        return this.stationTime;
    }

    @NotNull
    public final String getStationUnit() {
        return this.stationUnit;
    }

    public int hashCode() {
        return (((((((((this.stationName.hashCode() * 31) + this.stationDistance.hashCode()) * 31) + this.stationCharge.hashCode()) * 31) + this.stationUnit.hashCode()) * 31) + this.stationSubLocation.hashCode()) * 31) + this.stationTime.hashCode();
    }

    public final void setStationCharge(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.stationCharge = str;
    }

    public final void setStationDistance(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.stationDistance = str;
    }

    public final void setStationName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.stationName = str;
    }

    public final void setStationSubLocation(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.stationSubLocation = str;
    }

    public final void setStationTime(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.stationTime = str;
    }

    public final void setStationUnit(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.stationUnit = str;
    }

    @NotNull
    public String toString() {
        return "NearbyStationModel(stationName=" + this.stationName + ", stationDistance=" + this.stationDistance + ", stationCharge=" + this.stationCharge + ", stationUnit=" + this.stationUnit + ", stationSubLocation=" + this.stationSubLocation + ", stationTime=" + this.stationTime + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.stationName);
        out.writeString(this.stationDistance);
        out.writeString(this.stationCharge);
        out.writeString(this.stationUnit);
        out.writeString(this.stationSubLocation);
        out.writeString(this.stationTime);
    }
}
