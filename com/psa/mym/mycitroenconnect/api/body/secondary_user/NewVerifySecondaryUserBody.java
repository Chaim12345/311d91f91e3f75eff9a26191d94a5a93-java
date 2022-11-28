package com.psa.mym.mycitroenconnect.api.body.secondary_user;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class NewVerifySecondaryUserBody {
    @SerializedName("accountId")
    @NotNull
    private final String accountId;
    @SerializedName("countryCode")
    @NotNull
    private final String countryCode;
    @SerializedName("mobileNum")
    @NotNull
    private final String mobileNum;
    @SerializedName("sharedVehicle")
    @NotNull
    private final SharedVehicleBody sharedVehicleBody;

    public NewVerifySecondaryUserBody(@NotNull String accountId, @NotNull String countryCode, @NotNull String mobileNum, @NotNull SharedVehicleBody sharedVehicleBody) {
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        Intrinsics.checkNotNullParameter(sharedVehicleBody, "sharedVehicleBody");
        this.accountId = accountId;
        this.countryCode = countryCode;
        this.mobileNum = mobileNum;
        this.sharedVehicleBody = sharedVehicleBody;
    }

    public static /* synthetic */ NewVerifySecondaryUserBody copy$default(NewVerifySecondaryUserBody newVerifySecondaryUserBody, String str, String str2, String str3, SharedVehicleBody sharedVehicleBody, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = newVerifySecondaryUserBody.accountId;
        }
        if ((i2 & 2) != 0) {
            str2 = newVerifySecondaryUserBody.countryCode;
        }
        if ((i2 & 4) != 0) {
            str3 = newVerifySecondaryUserBody.mobileNum;
        }
        if ((i2 & 8) != 0) {
            sharedVehicleBody = newVerifySecondaryUserBody.sharedVehicleBody;
        }
        return newVerifySecondaryUserBody.copy(str, str2, str3, sharedVehicleBody);
    }

    @NotNull
    public final String component1() {
        return this.accountId;
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
    public final SharedVehicleBody component4() {
        return this.sharedVehicleBody;
    }

    @NotNull
    public final NewVerifySecondaryUserBody copy(@NotNull String accountId, @NotNull String countryCode, @NotNull String mobileNum, @NotNull SharedVehicleBody sharedVehicleBody) {
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        Intrinsics.checkNotNullParameter(sharedVehicleBody, "sharedVehicleBody");
        return new NewVerifySecondaryUserBody(accountId, countryCode, mobileNum, sharedVehicleBody);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NewVerifySecondaryUserBody) {
            NewVerifySecondaryUserBody newVerifySecondaryUserBody = (NewVerifySecondaryUserBody) obj;
            return Intrinsics.areEqual(this.accountId, newVerifySecondaryUserBody.accountId) && Intrinsics.areEqual(this.countryCode, newVerifySecondaryUserBody.countryCode) && Intrinsics.areEqual(this.mobileNum, newVerifySecondaryUserBody.mobileNum) && Intrinsics.areEqual(this.sharedVehicleBody, newVerifySecondaryUserBody.sharedVehicleBody);
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
        return (((((this.accountId.hashCode() * 31) + this.countryCode.hashCode()) * 31) + this.mobileNum.hashCode()) * 31) + this.sharedVehicleBody.hashCode();
    }

    @NotNull
    public String toString() {
        return "NewVerifySecondaryUserBody(accountId=" + this.accountId + ", countryCode=" + this.countryCode + ", mobileNum=" + this.mobileNum + ", sharedVehicleBody=" + this.sharedVehicleBody + ')';
    }
}
