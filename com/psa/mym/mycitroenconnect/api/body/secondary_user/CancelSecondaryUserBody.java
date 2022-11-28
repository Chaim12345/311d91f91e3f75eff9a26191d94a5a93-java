package com.psa.mym.mycitroenconnect.api.body.secondary_user;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class CancelSecondaryUserBody {
    @SerializedName("accountId")
    @NotNull
    private String accountId;
    @SerializedName("countryCode")
    @NotNull
    private String countryCode;
    @SerializedName("mobileNum")
    @NotNull
    private String mobileNum;
    @SerializedName("sharedVehicle")
    @NotNull
    private SharedVehicleBody sharedVehicleBody;

    public CancelSecondaryUserBody(@NotNull String mobileNum, @NotNull String countryCode, @NotNull String accountId, @NotNull SharedVehicleBody sharedVehicleBody) {
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(sharedVehicleBody, "sharedVehicleBody");
        this.mobileNum = mobileNum;
        this.countryCode = countryCode;
        this.accountId = accountId;
        this.sharedVehicleBody = sharedVehicleBody;
    }

    public static /* synthetic */ CancelSecondaryUserBody copy$default(CancelSecondaryUserBody cancelSecondaryUserBody, String str, String str2, String str3, SharedVehicleBody sharedVehicleBody, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = cancelSecondaryUserBody.mobileNum;
        }
        if ((i2 & 2) != 0) {
            str2 = cancelSecondaryUserBody.countryCode;
        }
        if ((i2 & 4) != 0) {
            str3 = cancelSecondaryUserBody.accountId;
        }
        if ((i2 & 8) != 0) {
            sharedVehicleBody = cancelSecondaryUserBody.sharedVehicleBody;
        }
        return cancelSecondaryUserBody.copy(str, str2, str3, sharedVehicleBody);
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
        return this.accountId;
    }

    @NotNull
    public final SharedVehicleBody component4() {
        return this.sharedVehicleBody;
    }

    @NotNull
    public final CancelSecondaryUserBody copy(@NotNull String mobileNum, @NotNull String countryCode, @NotNull String accountId, @NotNull SharedVehicleBody sharedVehicleBody) {
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(sharedVehicleBody, "sharedVehicleBody");
        return new CancelSecondaryUserBody(mobileNum, countryCode, accountId, sharedVehicleBody);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CancelSecondaryUserBody) {
            CancelSecondaryUserBody cancelSecondaryUserBody = (CancelSecondaryUserBody) obj;
            return Intrinsics.areEqual(this.mobileNum, cancelSecondaryUserBody.mobileNum) && Intrinsics.areEqual(this.countryCode, cancelSecondaryUserBody.countryCode) && Intrinsics.areEqual(this.accountId, cancelSecondaryUserBody.accountId) && Intrinsics.areEqual(this.sharedVehicleBody, cancelSecondaryUserBody.sharedVehicleBody);
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
    public final SharedVehicleBody getSharedVehicleBody() {
        return this.sharedVehicleBody;
    }

    public int hashCode() {
        return (((((this.mobileNum.hashCode() * 31) + this.countryCode.hashCode()) * 31) + this.accountId.hashCode()) * 31) + this.sharedVehicleBody.hashCode();
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

    public final void setSharedVehicleBody(@NotNull SharedVehicleBody sharedVehicleBody) {
        Intrinsics.checkNotNullParameter(sharedVehicleBody, "<set-?>");
        this.sharedVehicleBody = sharedVehicleBody;
    }

    @NotNull
    public String toString() {
        return "CancelSecondaryUserBody(mobileNum=" + this.mobileNum + ", countryCode=" + this.countryCode + ", accountId=" + this.accountId + ", sharedVehicleBody=" + this.sharedVehicleBody + ')';
    }
}
