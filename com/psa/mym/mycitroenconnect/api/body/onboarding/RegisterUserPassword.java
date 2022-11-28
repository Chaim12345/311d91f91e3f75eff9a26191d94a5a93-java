package com.psa.mym.mycitroenconnect.api.body.onboarding;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class RegisterUserPassword {
    @NotNull
    private String countryCode;
    @NotNull
    private String mobileNum;
    @NotNull
    private String password;
    @NotNull
    private String tokenHash;

    public RegisterUserPassword() {
        this("", "", "", "");
    }

    public RegisterUserPassword(@NotNull String password, @NotNull String countryCode, @NotNull String mobileNum, @NotNull String tokenHash) {
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        Intrinsics.checkNotNullParameter(tokenHash, "tokenHash");
        this.password = password;
        this.countryCode = countryCode;
        this.mobileNum = mobileNum;
        this.tokenHash = tokenHash;
    }

    public static /* synthetic */ RegisterUserPassword copy$default(RegisterUserPassword registerUserPassword, String str, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = registerUserPassword.password;
        }
        if ((i2 & 2) != 0) {
            str2 = registerUserPassword.countryCode;
        }
        if ((i2 & 4) != 0) {
            str3 = registerUserPassword.mobileNum;
        }
        if ((i2 & 8) != 0) {
            str4 = registerUserPassword.tokenHash;
        }
        return registerUserPassword.copy(str, str2, str3, str4);
    }

    @NotNull
    public final String component1() {
        return this.password;
    }

    @NotNull
    public final String component2() {
        return this.countryCode;
    }

    @NotNull
    public final String component3() {
        return this.mobileNum;
    }

    @NotNull
    public final String component4() {
        return this.tokenHash;
    }

    @NotNull
    public final RegisterUserPassword copy(@NotNull String password, @NotNull String countryCode, @NotNull String mobileNum, @NotNull String tokenHash) {
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        Intrinsics.checkNotNullParameter(tokenHash, "tokenHash");
        return new RegisterUserPassword(password, countryCode, mobileNum, tokenHash);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RegisterUserPassword) {
            RegisterUserPassword registerUserPassword = (RegisterUserPassword) obj;
            return Intrinsics.areEqual(this.password, registerUserPassword.password) && Intrinsics.areEqual(this.countryCode, registerUserPassword.countryCode) && Intrinsics.areEqual(this.mobileNum, registerUserPassword.mobileNum) && Intrinsics.areEqual(this.tokenHash, registerUserPassword.tokenHash);
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
    public final String getPassword() {
        return this.password;
    }

    @NotNull
    public final String getTokenHash() {
        return this.tokenHash;
    }

    public int hashCode() {
        return (((((this.password.hashCode() * 31) + this.countryCode.hashCode()) * 31) + this.mobileNum.hashCode()) * 31) + this.tokenHash.hashCode();
    }

    public final void setCountryCode(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.countryCode = str;
    }

    public final void setMobileNum(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mobileNum = str;
    }

    public final void setPassword(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.password = str;
    }

    public final void setTokenHash(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tokenHash = str;
    }

    @NotNull
    public String toString() {
        return "RegisterUserPassword(password=" + this.password + ", countryCode=" + this.countryCode + ", mobileNum=" + this.mobileNum + ", tokenHash=" + this.tokenHash + ')';
    }
}
