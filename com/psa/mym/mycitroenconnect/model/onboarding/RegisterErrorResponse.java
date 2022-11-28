package com.psa.mym.mycitroenconnect.model.onboarding;

import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class RegisterErrorResponse {
    @SerializedName("isGuestUser")
    @NotNull
    private String isGuestUser;
    @SerializedName(AppConstants.ARG_MESSAGE)
    @NotNull
    private String message;
    @SerializedName("mobileNum")
    @NotNull
    private String mobileNum;
    @SerializedName("statusCode")
    @NotNull
    private String statusCode;

    public RegisterErrorResponse() {
        this(null, null, null, null, 15, null);
    }

    public RegisterErrorResponse(@NotNull String mobileNum, @NotNull String isGuestUser, @NotNull String message, @NotNull String statusCode) {
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        Intrinsics.checkNotNullParameter(isGuestUser, "isGuestUser");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(statusCode, "statusCode");
        this.mobileNum = mobileNum;
        this.isGuestUser = isGuestUser;
        this.message = message;
        this.statusCode = statusCode;
    }

    public /* synthetic */ RegisterErrorResponse(String str, String str2, String str3, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3, (i2 & 8) != 0 ? "" : str4);
    }

    public static /* synthetic */ RegisterErrorResponse copy$default(RegisterErrorResponse registerErrorResponse, String str, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = registerErrorResponse.mobileNum;
        }
        if ((i2 & 2) != 0) {
            str2 = registerErrorResponse.isGuestUser;
        }
        if ((i2 & 4) != 0) {
            str3 = registerErrorResponse.message;
        }
        if ((i2 & 8) != 0) {
            str4 = registerErrorResponse.statusCode;
        }
        return registerErrorResponse.copy(str, str2, str3, str4);
    }

    @NotNull
    public final String component1() {
        return this.mobileNum;
    }

    @NotNull
    public final String component2() {
        return this.isGuestUser;
    }

    @NotNull
    public final String component3() {
        return this.message;
    }

    @NotNull
    public final String component4() {
        return this.statusCode;
    }

    @NotNull
    public final RegisterErrorResponse copy(@NotNull String mobileNum, @NotNull String isGuestUser, @NotNull String message, @NotNull String statusCode) {
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        Intrinsics.checkNotNullParameter(isGuestUser, "isGuestUser");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(statusCode, "statusCode");
        return new RegisterErrorResponse(mobileNum, isGuestUser, message, statusCode);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RegisterErrorResponse) {
            RegisterErrorResponse registerErrorResponse = (RegisterErrorResponse) obj;
            return Intrinsics.areEqual(this.mobileNum, registerErrorResponse.mobileNum) && Intrinsics.areEqual(this.isGuestUser, registerErrorResponse.isGuestUser) && Intrinsics.areEqual(this.message, registerErrorResponse.message) && Intrinsics.areEqual(this.statusCode, registerErrorResponse.statusCode);
        }
        return false;
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
    public final String getStatusCode() {
        return this.statusCode;
    }

    public int hashCode() {
        return (((((this.mobileNum.hashCode() * 31) + this.isGuestUser.hashCode()) * 31) + this.message.hashCode()) * 31) + this.statusCode.hashCode();
    }

    @NotNull
    public final String isGuestUser() {
        return this.isGuestUser;
    }

    public final void setGuestUser(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.isGuestUser = str;
    }

    public final void setMessage(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.message = str;
    }

    public final void setMobileNum(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mobileNum = str;
    }

    public final void setStatusCode(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.statusCode = str;
    }

    @NotNull
    public String toString() {
        return "RegisterErrorResponse(mobileNum=" + this.mobileNum + ", isGuestUser=" + this.isGuestUser + ", message=" + this.message + ", statusCode=" + this.statusCode + ')';
    }
}
