package com.psa.mym.mycitroenconnect.model.geo_fence;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Parcelize
/* loaded from: classes3.dex */
public final class PostGeoFenceResponse implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<PostGeoFenceResponse> CREATOR = new Creator();
    @Nullable
    private String apiName;
    @SerializedName("fenceId")
    @Nullable
    private String fenceId;
    @SerializedName("fenceName")
    @Nullable
    private String fenceName;
    @SerializedName("fenceStatus")
    @Nullable
    private String fenceStatus;
    @SerializedName(AppConstants.ARG_MESSAGE)
    @Nullable
    private String message;

    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<PostGeoFenceResponse> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final PostGeoFenceResponse createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new PostGeoFenceResponse(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final PostGeoFenceResponse[] newArray(int i2) {
            return new PostGeoFenceResponse[i2];
        }
    }

    public PostGeoFenceResponse() {
        this(null, null, null, null, null, 31, null);
    }

    public PostGeoFenceResponse(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        this.fenceName = str;
        this.message = str2;
        this.fenceId = str3;
        this.fenceStatus = str4;
        this.apiName = str5;
    }

    public /* synthetic */ PostGeoFenceResponse(String str, String str2, String str3, String str4, String str5, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : str5);
    }

    public static /* synthetic */ PostGeoFenceResponse copy$default(PostGeoFenceResponse postGeoFenceResponse, String str, String str2, String str3, String str4, String str5, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = postGeoFenceResponse.fenceName;
        }
        if ((i2 & 2) != 0) {
            str2 = postGeoFenceResponse.message;
        }
        String str6 = str2;
        if ((i2 & 4) != 0) {
            str3 = postGeoFenceResponse.fenceId;
        }
        String str7 = str3;
        if ((i2 & 8) != 0) {
            str4 = postGeoFenceResponse.fenceStatus;
        }
        String str8 = str4;
        if ((i2 & 16) != 0) {
            str5 = postGeoFenceResponse.apiName;
        }
        return postGeoFenceResponse.copy(str, str6, str7, str8, str5);
    }

    @Nullable
    public final String component1() {
        return this.fenceName;
    }

    @Nullable
    public final String component2() {
        return this.message;
    }

    @Nullable
    public final String component3() {
        return this.fenceId;
    }

    @Nullable
    public final String component4() {
        return this.fenceStatus;
    }

    @Nullable
    public final String component5() {
        return this.apiName;
    }

    @NotNull
    public final PostGeoFenceResponse copy(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        return new PostGeoFenceResponse(str, str2, str3, str4, str5);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PostGeoFenceResponse) {
            PostGeoFenceResponse postGeoFenceResponse = (PostGeoFenceResponse) obj;
            return Intrinsics.areEqual(this.fenceName, postGeoFenceResponse.fenceName) && Intrinsics.areEqual(this.message, postGeoFenceResponse.message) && Intrinsics.areEqual(this.fenceId, postGeoFenceResponse.fenceId) && Intrinsics.areEqual(this.fenceStatus, postGeoFenceResponse.fenceStatus) && Intrinsics.areEqual(this.apiName, postGeoFenceResponse.apiName);
        }
        return false;
    }

    @Nullable
    public final String getApiName() {
        return this.apiName;
    }

    @Nullable
    public final String getFenceId() {
        return this.fenceId;
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
    public final String getMessage() {
        return this.message;
    }

    public int hashCode() {
        String str = this.fenceName;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.message;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.fenceId;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.fenceStatus;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.apiName;
        return hashCode4 + (str5 != null ? str5.hashCode() : 0);
    }

    public final void setApiName(@Nullable String str) {
        this.apiName = str;
    }

    public final void setFenceId(@Nullable String str) {
        this.fenceId = str;
    }

    public final void setFenceName(@Nullable String str) {
        this.fenceName = str;
    }

    public final void setFenceStatus(@Nullable String str) {
        this.fenceStatus = str;
    }

    public final void setMessage(@Nullable String str) {
        this.message = str;
    }

    @NotNull
    public String toString() {
        return "PostGeoFenceResponse(fenceName=" + this.fenceName + ", message=" + this.message + ", fenceId=" + this.fenceId + ", fenceStatus=" + this.fenceStatus + ", apiName=" + this.apiName + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.fenceName);
        out.writeString(this.message);
        out.writeString(this.fenceId);
        out.writeString(this.fenceStatus);
        out.writeString(this.apiName);
    }
}
