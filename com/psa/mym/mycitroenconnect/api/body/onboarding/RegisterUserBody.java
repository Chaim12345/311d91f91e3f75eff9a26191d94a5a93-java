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
public final class RegisterUserBody implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<RegisterUserBody> CREATOR = new Creator();
    @SerializedName("countryCode")
    @NotNull
    private String countryCode;
    @SerializedName("mobileNum")
    @NotNull
    private String mobileNum;
    @SerializedName("userName")
    @NotNull
    private String mobileNumber;

    /* loaded from: classes.dex */
    public static final class Creator implements Parcelable.Creator<RegisterUserBody> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final RegisterUserBody createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new RegisterUserBody(parcel.readString(), parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final RegisterUserBody[] newArray(int i2) {
            return new RegisterUserBody[i2];
        }
    }

    public RegisterUserBody() {
        this("", "", "");
    }

    public RegisterUserBody(@NotNull String mobileNum, @NotNull String countryCode, @NotNull String mobileNumber) {
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(mobileNumber, "mobileNumber");
        this.mobileNum = mobileNum;
        this.countryCode = countryCode;
        this.mobileNumber = mobileNumber;
    }

    public static /* synthetic */ RegisterUserBody copy$default(RegisterUserBody registerUserBody, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = registerUserBody.mobileNum;
        }
        if ((i2 & 2) != 0) {
            str2 = registerUserBody.countryCode;
        }
        if ((i2 & 4) != 0) {
            str3 = registerUserBody.mobileNumber;
        }
        return registerUserBody.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.mobileNum;
    }

    @NotNull
    public final String component2() {
        return this.countryCode;
    }

    @NotNull
    public final String component3() {
        return this.mobileNumber;
    }

    @NotNull
    public final RegisterUserBody copy(@NotNull String mobileNum, @NotNull String countryCode, @NotNull String mobileNumber) {
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(mobileNumber, "mobileNumber");
        return new RegisterUserBody(mobileNum, countryCode, mobileNumber);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RegisterUserBody) {
            RegisterUserBody registerUserBody = (RegisterUserBody) obj;
            return Intrinsics.areEqual(this.mobileNum, registerUserBody.mobileNum) && Intrinsics.areEqual(this.countryCode, registerUserBody.countryCode) && Intrinsics.areEqual(this.mobileNumber, registerUserBody.mobileNumber);
        }
        return false;
    }

    @NotNull
    public final String getCountryCode() {
        return this.countryCode;
    }

    @NotNull
    public final String getMobileNum() {
        return this.mobileNum;
    }

    @NotNull
    public final String getMobileNumber() {
        return this.mobileNumber;
    }

    public int hashCode() {
        return (((this.mobileNum.hashCode() * 31) + this.countryCode.hashCode()) * 31) + this.mobileNumber.hashCode();
    }

    public final void setCountryCode(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.countryCode = str;
    }

    public final void setMobileNum(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mobileNum = str;
    }

    public final void setMobileNumber(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mobileNumber = str;
    }

    @NotNull
    public String toString() {
        return "RegisterUserBody(mobileNum=" + this.mobileNum + ", countryCode=" + this.countryCode + ", mobileNumber=" + this.mobileNumber + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.mobileNum);
        out.writeString(this.countryCode);
        out.writeString(this.mobileNumber);
    }
}
