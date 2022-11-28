package com.psa.mym.mycitroenconnect.api.body.secondary_user;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class SecondaryUserBody {
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
    private final SharedVehicleNew sharedVehicle;
    @SerializedName("userFullName")
    @NotNull
    private String userFullName;

    public SecondaryUserBody() {
        this(null, null, null, null, null, 31, null);
    }

    public SecondaryUserBody(@NotNull String countryCode, @NotNull String mobileNum, @NotNull String accountId, @NotNull String userFullName, @NotNull SharedVehicleNew sharedVehicle) {
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(userFullName, "userFullName");
        Intrinsics.checkNotNullParameter(sharedVehicle, "sharedVehicle");
        this.countryCode = countryCode;
        this.mobileNum = mobileNum;
        this.accountId = accountId;
        this.userFullName = userFullName;
        this.sharedVehicle = sharedVehicle;
    }

    public /* synthetic */ SecondaryUserBody(String str, String str2, String str3, String str4, SharedVehicleNew sharedVehicleNew, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3, (i2 & 8) == 0 ? str4 : "", (i2 & 16) != 0 ? new SharedVehicleNew(null, 1, null) : sharedVehicleNew);
    }

    public static /* synthetic */ SecondaryUserBody copy$default(SecondaryUserBody secondaryUserBody, String str, String str2, String str3, String str4, SharedVehicleNew sharedVehicleNew, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = secondaryUserBody.countryCode;
        }
        if ((i2 & 2) != 0) {
            str2 = secondaryUserBody.mobileNum;
        }
        String str5 = str2;
        if ((i2 & 4) != 0) {
            str3 = secondaryUserBody.accountId;
        }
        String str6 = str3;
        if ((i2 & 8) != 0) {
            str4 = secondaryUserBody.userFullName;
        }
        String str7 = str4;
        if ((i2 & 16) != 0) {
            sharedVehicleNew = secondaryUserBody.sharedVehicle;
        }
        return secondaryUserBody.copy(str, str5, str6, str7, sharedVehicleNew);
    }

    @NotNull
    public final String component1() {
        return this.countryCode;
    }

    @NotNull
    public final String component2() {
        return this.mobileNum;
    }

    @NotNull
    public final String component3() {
        return this.accountId;
    }

    @NotNull
    public final String component4() {
        return this.userFullName;
    }

    @NotNull
    public final SharedVehicleNew component5() {
        return this.sharedVehicle;
    }

    @NotNull
    public final SecondaryUserBody copy(@NotNull String countryCode, @NotNull String mobileNum, @NotNull String accountId, @NotNull String userFullName, @NotNull SharedVehicleNew sharedVehicle) {
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(userFullName, "userFullName");
        Intrinsics.checkNotNullParameter(sharedVehicle, "sharedVehicle");
        return new SecondaryUserBody(countryCode, mobileNum, accountId, userFullName, sharedVehicle);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SecondaryUserBody) {
            SecondaryUserBody secondaryUserBody = (SecondaryUserBody) obj;
            return Intrinsics.areEqual(this.countryCode, secondaryUserBody.countryCode) && Intrinsics.areEqual(this.mobileNum, secondaryUserBody.mobileNum) && Intrinsics.areEqual(this.accountId, secondaryUserBody.accountId) && Intrinsics.areEqual(this.userFullName, secondaryUserBody.userFullName) && Intrinsics.areEqual(this.sharedVehicle, secondaryUserBody.sharedVehicle);
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
    public final SharedVehicleNew getSharedVehicle() {
        return this.sharedVehicle;
    }

    @NotNull
    public final String getUserFullName() {
        return this.userFullName;
    }

    public int hashCode() {
        return (((((((this.countryCode.hashCode() * 31) + this.mobileNum.hashCode()) * 31) + this.accountId.hashCode()) * 31) + this.userFullName.hashCode()) * 31) + this.sharedVehicle.hashCode();
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
        return "SecondaryUserBody(countryCode=" + this.countryCode + ", mobileNum=" + this.mobileNum + ", accountId=" + this.accountId + ", userFullName=" + this.userFullName + ", sharedVehicle=" + this.sharedVehicle + ')';
    }
}
