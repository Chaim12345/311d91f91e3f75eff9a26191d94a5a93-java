package com.psa.mym.mycitroenconnect.model.onboarding;

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
public final class SendOtpResponse implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<SendOtpResponse> CREATOR = new Creator();
    @SerializedName("fullHash")
    @NotNull
    private String fullHash;
    @SerializedName(AppConstants.ARG_MESSAGE)
    @NotNull
    private String message;
    @SerializedName("mobileNum")
    @NotNull
    private String mobileNum;
    @SerializedName("OTP")
    @NotNull
    private String oTP;
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("userName")
    @NotNull
    private String username;

    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<SendOtpResponse> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final SendOtpResponse createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new SendOtpResponse(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final SendOtpResponse[] newArray(int i2) {
            return new SendOtpResponse[i2];
        }
    }

    public SendOtpResponse() {
        this(null, null, null, null, 0, null, 63, null);
    }

    public SendOtpResponse(@NotNull String fullHash, @NotNull String message, @NotNull String mobileNum, @NotNull String oTP, int i2, @NotNull String username) {
        Intrinsics.checkNotNullParameter(fullHash, "fullHash");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        Intrinsics.checkNotNullParameter(oTP, "oTP");
        Intrinsics.checkNotNullParameter(username, "username");
        this.fullHash = fullHash;
        this.message = message;
        this.mobileNum = mobileNum;
        this.oTP = oTP;
        this.statusCode = i2;
        this.username = username;
    }

    public /* synthetic */ SendOtpResponse(String str, String str2, String str3, String str4, int i2, String str5, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "" : str, (i3 & 2) != 0 ? "" : str2, (i3 & 4) != 0 ? "" : str3, (i3 & 8) != 0 ? "" : str4, (i3 & 16) != 0 ? 0 : i2, (i3 & 32) != 0 ? "" : str5);
    }

    public static /* synthetic */ SendOtpResponse copy$default(SendOtpResponse sendOtpResponse, String str, String str2, String str3, String str4, int i2, String str5, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = sendOtpResponse.fullHash;
        }
        if ((i3 & 2) != 0) {
            str2 = sendOtpResponse.message;
        }
        String str6 = str2;
        if ((i3 & 4) != 0) {
            str3 = sendOtpResponse.mobileNum;
        }
        String str7 = str3;
        if ((i3 & 8) != 0) {
            str4 = sendOtpResponse.oTP;
        }
        String str8 = str4;
        if ((i3 & 16) != 0) {
            i2 = sendOtpResponse.statusCode;
        }
        int i4 = i2;
        if ((i3 & 32) != 0) {
            str5 = sendOtpResponse.username;
        }
        return sendOtpResponse.copy(str, str6, str7, str8, i4, str5);
    }

    @NotNull
    public final String component1() {
        return this.fullHash;
    }

    @NotNull
    public final String component2() {
        return this.message;
    }

    @NotNull
    public final String component3() {
        return this.mobileNum;
    }

    @NotNull
    public final String component4() {
        return this.oTP;
    }

    public final int component5() {
        return this.statusCode;
    }

    @NotNull
    public final String component6() {
        return this.username;
    }

    @NotNull
    public final SendOtpResponse copy(@NotNull String fullHash, @NotNull String message, @NotNull String mobileNum, @NotNull String oTP, int i2, @NotNull String username) {
        Intrinsics.checkNotNullParameter(fullHash, "fullHash");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        Intrinsics.checkNotNullParameter(oTP, "oTP");
        Intrinsics.checkNotNullParameter(username, "username");
        return new SendOtpResponse(fullHash, message, mobileNum, oTP, i2, username);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SendOtpResponse) {
            SendOtpResponse sendOtpResponse = (SendOtpResponse) obj;
            return Intrinsics.areEqual(this.fullHash, sendOtpResponse.fullHash) && Intrinsics.areEqual(this.message, sendOtpResponse.message) && Intrinsics.areEqual(this.mobileNum, sendOtpResponse.mobileNum) && Intrinsics.areEqual(this.oTP, sendOtpResponse.oTP) && this.statusCode == sendOtpResponse.statusCode && Intrinsics.areEqual(this.username, sendOtpResponse.username);
        }
        return false;
    }

    @NotNull
    public final String getFullHash() {
        return this.fullHash;
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    @NotNull
    public final String getMobileNum() {
        return this.mobileNum;
    }

    @NotNull
    public final String getOTP() {
        return this.oTP;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    @NotNull
    public final String getUsername() {
        return this.username;
    }

    public int hashCode() {
        return (((((((((this.fullHash.hashCode() * 31) + this.message.hashCode()) * 31) + this.mobileNum.hashCode()) * 31) + this.oTP.hashCode()) * 31) + Integer.hashCode(this.statusCode)) * 31) + this.username.hashCode();
    }

    public final void setFullHash(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.fullHash = str;
    }

    public final void setMessage(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.message = str;
    }

    public final void setMobileNum(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mobileNum = str;
    }

    public final void setOTP(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.oTP = str;
    }

    public final void setStatusCode(int i2) {
        this.statusCode = i2;
    }

    public final void setUsername(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.username = str;
    }

    @NotNull
    public String toString() {
        return "SendOtpResponse(fullHash=" + this.fullHash + ", message=" + this.message + ", mobileNum=" + this.mobileNum + ", oTP=" + this.oTP + ", statusCode=" + this.statusCode + ", username=" + this.username + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.fullHash);
        out.writeString(this.message);
        out.writeString(this.mobileNum);
        out.writeString(this.oTP);
        out.writeInt(this.statusCode);
        out.writeString(this.username);
    }
}
