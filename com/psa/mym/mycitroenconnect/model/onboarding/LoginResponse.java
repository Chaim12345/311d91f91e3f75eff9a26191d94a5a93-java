package com.psa.mym.mycitroenconnect.model.onboarding;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Parcelize
/* loaded from: classes3.dex */
public final class LoginResponse implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<LoginResponse> CREATOR = new Creator();
    @SerializedName("fullHash")
    @NotNull
    private final String fullhash;
    @SerializedName(AppConstants.ARG_MESSAGE)
    @NotNull
    private final String message;
    @SerializedName("mobileNum")
    @NotNull
    private final String mobileNum;
    @SerializedName("statusCode")
    private final int statusCode;

    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<LoginResponse> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final LoginResponse createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new LoginResponse(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final LoginResponse[] newArray(int i2) {
            return new LoginResponse[i2];
        }
    }

    public LoginResponse(@NotNull String mobileNum, @NotNull String message, int i2, @NotNull String fullhash) {
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(fullhash, "fullhash");
        this.mobileNum = mobileNum;
        this.message = message;
        this.statusCode = i2;
        this.fullhash = fullhash;
    }

    public static /* synthetic */ LoginResponse copy$default(LoginResponse loginResponse, String str, String str2, int i2, String str3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = loginResponse.mobileNum;
        }
        if ((i3 & 2) != 0) {
            str2 = loginResponse.message;
        }
        if ((i3 & 4) != 0) {
            i2 = loginResponse.statusCode;
        }
        if ((i3 & 8) != 0) {
            str3 = loginResponse.fullhash;
        }
        return loginResponse.copy(str, str2, i2, str3);
    }

    @NotNull
    public final String component1() {
        return this.mobileNum;
    }

    @NotNull
    public final String component2() {
        return this.message;
    }

    public final int component3() {
        return this.statusCode;
    }

    @NotNull
    public final String component4() {
        return this.fullhash;
    }

    @NotNull
    public final LoginResponse copy(@NotNull String mobileNum, @NotNull String message, int i2, @NotNull String fullhash) {
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(fullhash, "fullhash");
        return new LoginResponse(mobileNum, message, i2, fullhash);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LoginResponse) {
            LoginResponse loginResponse = (LoginResponse) obj;
            return Intrinsics.areEqual(this.mobileNum, loginResponse.mobileNum) && Intrinsics.areEqual(this.message, loginResponse.message) && this.statusCode == loginResponse.statusCode && Intrinsics.areEqual(this.fullhash, loginResponse.fullhash);
        }
        return false;
    }

    @NotNull
    public final String getFullhash() {
        return this.fullhash;
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    @NotNull
    public final String getMobileNum() {
        return this.mobileNum;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public int hashCode() {
        return (((((this.mobileNum.hashCode() * 31) + this.message.hashCode()) * 31) + Integer.hashCode(this.statusCode)) * 31) + this.fullhash.hashCode();
    }

    @NotNull
    public String toString() {
        return "LoginResponse(mobileNum=" + this.mobileNum + ", message=" + this.message + ", statusCode=" + this.statusCode + ", fullhash=" + this.fullhash + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.mobileNum);
        out.writeString(this.message);
        out.writeInt(this.statusCode);
        out.writeString(this.fullhash);
    }
}
