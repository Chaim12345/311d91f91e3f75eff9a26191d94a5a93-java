package com.psa.mym.mycitroenconnect.api.body.secondary_user;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class CarAccessBody {
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

    public CarAccessBody() {
        this(null, null, null, null, null, 31, null);
    }

    public CarAccessBody(@NotNull String mobileNum, @NotNull String countryCode, @NotNull String accountId, @NotNull String userFullName, @NotNull SharedVehicleNew sharedVehicle) {
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(userFullName, "userFullName");
        Intrinsics.checkNotNullParameter(sharedVehicle, "sharedVehicle");
        this.mobileNum = mobileNum;
        this.countryCode = countryCode;
        this.accountId = accountId;
        this.userFullName = userFullName;
        this.sharedVehicle = sharedVehicle;
    }

    public /* synthetic */ CarAccessBody(String str, String str2, String str3, String str4, SharedVehicleNew sharedVehicleNew, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3, (i2 & 8) == 0 ? str4 : "", (i2 & 16) != 0 ? new SharedVehicleNew(null, 1, null) : sharedVehicleNew);
    }

    public static /* synthetic */ CarAccessBody copy$default(CarAccessBody carAccessBody, String str, String str2, String str3, String str4, SharedVehicleNew sharedVehicleNew, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = carAccessBody.mobileNum;
        }
        if ((i2 & 2) != 0) {
            str2 = carAccessBody.countryCode;
        }
        String str5 = str2;
        if ((i2 & 4) != 0) {
            str3 = carAccessBody.accountId;
        }
        String str6 = str3;
        if ((i2 & 8) != 0) {
            str4 = carAccessBody.userFullName;
        }
        String str7 = str4;
        if ((i2 & 16) != 0) {
            sharedVehicleNew = carAccessBody.sharedVehicle;
        }
        return carAccessBody.copy(str, str5, str6, str7, sharedVehicleNew);
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
    public final String component4() {
        return this.userFullName;
    }

    @NotNull
    public final SharedVehicleNew component5() {
        return this.sharedVehicle;
    }

    @NotNull
    public final CarAccessBody copy(@NotNull String mobileNum, @NotNull String countryCode, @NotNull String accountId, @NotNull String userFullName, @NotNull SharedVehicleNew sharedVehicle) {
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(userFullName, "userFullName");
        Intrinsics.checkNotNullParameter(sharedVehicle, "sharedVehicle");
        return new CarAccessBody(mobileNum, countryCode, accountId, userFullName, sharedVehicle);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CarAccessBody) {
            CarAccessBody carAccessBody = (CarAccessBody) obj;
            return Intrinsics.areEqual(this.mobileNum, carAccessBody.mobileNum) && Intrinsics.areEqual(this.countryCode, carAccessBody.countryCode) && Intrinsics.areEqual(this.accountId, carAccessBody.accountId) && Intrinsics.areEqual(this.userFullName, carAccessBody.userFullName) && Intrinsics.areEqual(this.sharedVehicle, carAccessBody.sharedVehicle);
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
        return (((((((this.mobileNum.hashCode() * 31) + this.countryCode.hashCode()) * 31) + this.accountId.hashCode()) * 31) + this.userFullName.hashCode()) * 31) + this.sharedVehicle.hashCode();
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
        return "CarAccessBody(mobileNum=" + this.mobileNum + ", countryCode=" + this.countryCode + ", accountId=" + this.accountId + ", userFullName=" + this.userFullName + ", sharedVehicle=" + this.sharedVehicle + ')';
    }
}
