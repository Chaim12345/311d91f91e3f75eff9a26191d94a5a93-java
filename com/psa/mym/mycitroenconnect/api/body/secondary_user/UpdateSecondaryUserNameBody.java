package com.psa.mym.mycitroenconnect.api.body.secondary_user;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class UpdateSecondaryUserNameBody {
    @SerializedName("accountId")
    @NotNull
    private String accountId;
    @SerializedName("countryCode")
    @NotNull
    private String countryCode;
    @SerializedName("mobileNum")
    @NotNull
    private String mobileNum;
    @SerializedName("userFullName")
    @NotNull
    private String userFullName;

    public UpdateSecondaryUserNameBody() {
        this(null, null, null, null, 15, null);
    }

    public UpdateSecondaryUserNameBody(@NotNull String userFullName, @NotNull String countryCode, @NotNull String accountId, @NotNull String mobileNum) {
        Intrinsics.checkNotNullParameter(userFullName, "userFullName");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        this.userFullName = userFullName;
        this.countryCode = countryCode;
        this.accountId = accountId;
        this.mobileNum = mobileNum;
    }

    public /* synthetic */ UpdateSecondaryUserNameBody(String str, String str2, String str3, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3, (i2 & 8) != 0 ? "" : str4);
    }

    public static /* synthetic */ UpdateSecondaryUserNameBody copy$default(UpdateSecondaryUserNameBody updateSecondaryUserNameBody, String str, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = updateSecondaryUserNameBody.userFullName;
        }
        if ((i2 & 2) != 0) {
            str2 = updateSecondaryUserNameBody.countryCode;
        }
        if ((i2 & 4) != 0) {
            str3 = updateSecondaryUserNameBody.accountId;
        }
        if ((i2 & 8) != 0) {
            str4 = updateSecondaryUserNameBody.mobileNum;
        }
        return updateSecondaryUserNameBody.copy(str, str2, str3, str4);
    }

    @NotNull
    public final String component1() {
        return this.userFullName;
    }

    @NotNull
    public final String component2() {
        return this.countryCode;
    }

    @NotNull
    public final String component3() {
        return this.accountId;
    }

    @NotNull
    public final String component4() {
        return this.mobileNum;
    }

    @NotNull
    public final UpdateSecondaryUserNameBody copy(@NotNull String userFullName, @NotNull String countryCode, @NotNull String accountId, @NotNull String mobileNum) {
        Intrinsics.checkNotNullParameter(userFullName, "userFullName");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        return new UpdateSecondaryUserNameBody(userFullName, countryCode, accountId, mobileNum);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UpdateSecondaryUserNameBody) {
            UpdateSecondaryUserNameBody updateSecondaryUserNameBody = (UpdateSecondaryUserNameBody) obj;
            return Intrinsics.areEqual(this.userFullName, updateSecondaryUserNameBody.userFullName) && Intrinsics.areEqual(this.countryCode, updateSecondaryUserNameBody.countryCode) && Intrinsics.areEqual(this.accountId, updateSecondaryUserNameBody.accountId) && Intrinsics.areEqual(this.mobileNum, updateSecondaryUserNameBody.mobileNum);
        }
        return false;
    }

    @NotNull
    public final String getAccountId() {
        return this.accountId;
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
    public final String getUserFullName() {
        return this.userFullName;
    }

    public int hashCode() {
        return (((((this.userFullName.hashCode() * 31) + this.countryCode.hashCode()) * 31) + this.accountId.hashCode()) * 31) + this.mobileNum.hashCode();
    }

    public final void setAccountId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.accountId = str;
    }

    public final void setCountryCode(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.countryCode = str;
    }

    public final void setMobileNum(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mobileNum = str;
    }

    public final void setUserFullName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userFullName = str;
    }

    @NotNull
    public String toString() {
        return "UpdateSecondaryUserNameBody(userFullName=" + this.userFullName + ", countryCode=" + this.countryCode + ", accountId=" + this.accountId + ", mobileNum=" + this.mobileNum + ')';
    }
}
