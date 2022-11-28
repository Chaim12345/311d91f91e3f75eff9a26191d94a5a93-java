package com.psa.mym.mycitroenconnect.api.body.onboarding;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class LoginBody {
    @SerializedName("countryCode")
    @NotNull
    private String countryCode;
    @SerializedName("mobileNum")
    @NotNull
    private String mobileNum;
    @SerializedName("password")
    @NotNull
    private String userPassword;

    public LoginBody(@NotNull String mobileNum, @NotNull String countryCode, @NotNull String userPassword) {
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(userPassword, "userPassword");
        this.mobileNum = mobileNum;
        this.countryCode = countryCode;
        this.userPassword = userPassword;
    }

    public static /* synthetic */ LoginBody copy$default(LoginBody loginBody, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = loginBody.mobileNum;
        }
        if ((i2 & 2) != 0) {
            str2 = loginBody.countryCode;
        }
        if ((i2 & 4) != 0) {
            str3 = loginBody.userPassword;
        }
        return loginBody.copy(str, str2, str3);
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
        return this.userPassword;
    }

    @NotNull
    public final LoginBody copy(@NotNull String mobileNum, @NotNull String countryCode, @NotNull String userPassword) {
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(userPassword, "userPassword");
        return new LoginBody(mobileNum, countryCode, userPassword);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LoginBody) {
            LoginBody loginBody = (LoginBody) obj;
            return Intrinsics.areEqual(this.mobileNum, loginBody.mobileNum) && Intrinsics.areEqual(this.countryCode, loginBody.countryCode) && Intrinsics.areEqual(this.userPassword, loginBody.userPassword);
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
    public final String getUserPassword() {
        return this.userPassword;
    }

    public int hashCode() {
        return (((this.mobileNum.hashCode() * 31) + this.countryCode.hashCode()) * 31) + this.userPassword.hashCode();
    }

    public final void setCountryCode(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.countryCode = str;
    }

    public final void setMobileNum(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mobileNum = str;
    }

    public final void setUserPassword(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userPassword = str;
    }

    @NotNull
    public String toString() {
        return "LoginBody(mobileNum=" + this.mobileNum + ", countryCode=" + this.countryCode + ", userPassword=" + this.userPassword + ')';
    }
}
