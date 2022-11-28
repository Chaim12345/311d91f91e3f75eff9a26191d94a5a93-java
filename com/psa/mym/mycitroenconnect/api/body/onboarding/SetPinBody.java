package com.psa.mym.mycitroenconnect.api.body.onboarding;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Parcelize
/* loaded from: classes.dex */
public final class SetPinBody implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<SetPinBody> CREATOR = new Creator();
    @SerializedName("appPin")
    @NotNull
    private String appPin;
    @SerializedName("confirmPin")
    @NotNull
    private String confirmPin;
    @SerializedName("countryCode")
    @NotNull
    private String countryCode;
    @SerializedName("isGuestUser")
    @NotNull
    private String isGuestUser;
    @SerializedName("mobileNum")
    @NotNull
    private String mobileNum;

    /* loaded from: classes.dex */
    public static final class Creator implements Parcelable.Creator<SetPinBody> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final SetPinBody createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new SetPinBody(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final SetPinBody[] newArray(int i2) {
            return new SetPinBody[i2];
        }
    }

    public SetPinBody(@NotNull String appPin, @NotNull String confirmPin, @NotNull String mobileNum, @NotNull String isGuestUser, @NotNull String countryCode) {
        Intrinsics.checkNotNullParameter(appPin, "appPin");
        Intrinsics.checkNotNullParameter(confirmPin, "confirmPin");
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        Intrinsics.checkNotNullParameter(isGuestUser, "isGuestUser");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        this.appPin = appPin;
        this.confirmPin = confirmPin;
        this.mobileNum = mobileNum;
        this.isGuestUser = isGuestUser;
        this.countryCode = countryCode;
    }

    public static /* synthetic */ SetPinBody copy$default(SetPinBody setPinBody, String str, String str2, String str3, String str4, String str5, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = setPinBody.appPin;
        }
        if ((i2 & 2) != 0) {
            str2 = setPinBody.confirmPin;
        }
        String str6 = str2;
        if ((i2 & 4) != 0) {
            str3 = setPinBody.mobileNum;
        }
        String str7 = str3;
        if ((i2 & 8) != 0) {
            str4 = setPinBody.isGuestUser;
        }
        String str8 = str4;
        if ((i2 & 16) != 0) {
            str5 = setPinBody.countryCode;
        }
        return setPinBody.copy(str, str6, str7, str8, str5);
    }

    @NotNull
    public final String component1() {
        return this.appPin;
    }

    @NotNull
    public final String component2() {
        return this.confirmPin;
    }

    @NotNull
    public final String component3() {
        return this.mobileNum;
    }

    @NotNull
    public final String component4() {
        return this.isGuestUser;
    }

    @NotNull
    public final String component5() {
        return this.countryCode;
    }

    @NotNull
    public final SetPinBody copy(@NotNull String appPin, @NotNull String confirmPin, @NotNull String mobileNum, @NotNull String isGuestUser, @NotNull String countryCode) {
        Intrinsics.checkNotNullParameter(appPin, "appPin");
        Intrinsics.checkNotNullParameter(confirmPin, "confirmPin");
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        Intrinsics.checkNotNullParameter(isGuestUser, "isGuestUser");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        return new SetPinBody(appPin, confirmPin, mobileNum, isGuestUser, countryCode);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SetPinBody) {
            SetPinBody setPinBody = (SetPinBody) obj;
            return Intrinsics.areEqual(this.appPin, setPinBody.appPin) && Intrinsics.areEqual(this.confirmPin, setPinBody.confirmPin) && Intrinsics.areEqual(this.mobileNum, setPinBody.mobileNum) && Intrinsics.areEqual(this.isGuestUser, setPinBody.isGuestUser) && Intrinsics.areEqual(this.countryCode, setPinBody.countryCode);
        }
        return false;
    }

    @NotNull
    public final String getAppPin() {
        return this.appPin;
    }

    @NotNull
    public final String getConfirmPin() {
        return this.confirmPin;
    }

    @NotNull
    public final String getCountryCode() {
        return this.countryCode;
    }

    @NotNull
    public final String getMobileNum() {
        return this.mobileNum;
    }

    public int hashCode() {
        return (((((((this.appPin.hashCode() * 31) + this.confirmPin.hashCode()) * 31) + this.mobileNum.hashCode()) * 31) + this.isGuestUser.hashCode()) * 31) + this.countryCode.hashCode();
    }

    @NotNull
    public final String isGuestUser() {
        return this.isGuestUser;
    }

    public final void setAppPin(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.appPin = str;
    }

    public final void setConfirmPin(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.confirmPin = str;
    }

    public final void setCountryCode(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.countryCode = str;
    }

    public final void setGuestUser(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.isGuestUser = str;
    }

    public final void setMobileNum(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mobileNum = str;
    }

    @NotNull
    public String toString() {
        return "SetPinBody(appPin=" + this.appPin + ", confirmPin=" + this.confirmPin + ", mobileNum=" + this.mobileNum + ", isGuestUser=" + this.isGuestUser + ", countryCode=" + this.countryCode + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.appPin);
        out.writeString(this.confirmPin);
        out.writeString(this.mobileNum);
        out.writeString(this.isGuestUser);
        out.writeString(this.countryCode);
    }
}
