package com.psa.mym.mycitroenconnect.api.body.onboarding;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class AddVehicleBody {
    @SerializedName("countryCode")
    @NotNull
    private String countryCode;
    @SerializedName("userName")
    @NotNull
    private String userName;
    @SerializedName("vinNum")
    @NotNull
    private String vinNum;

    public AddVehicleBody() {
        this(null, null, null, 7, null);
    }

    public AddVehicleBody(@NotNull String userName, @NotNull String countryCode, @NotNull String vinNum) {
        Intrinsics.checkNotNullParameter(userName, "userName");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        this.userName = userName;
        this.countryCode = countryCode;
        this.vinNum = vinNum;
    }

    public /* synthetic */ AddVehicleBody(String str, String str2, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3);
    }

    public static /* synthetic */ AddVehicleBody copy$default(AddVehicleBody addVehicleBody, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = addVehicleBody.userName;
        }
        if ((i2 & 2) != 0) {
            str2 = addVehicleBody.countryCode;
        }
        if ((i2 & 4) != 0) {
            str3 = addVehicleBody.vinNum;
        }
        return addVehicleBody.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.userName;
    }

    @NotNull
    public final String component2() {
        return this.countryCode;
    }

    @NotNull
    public final String component3() {
        return this.vinNum;
    }

    @NotNull
    public final AddVehicleBody copy(@NotNull String userName, @NotNull String countryCode, @NotNull String vinNum) {
        Intrinsics.checkNotNullParameter(userName, "userName");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        return new AddVehicleBody(userName, countryCode, vinNum);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AddVehicleBody) {
            AddVehicleBody addVehicleBody = (AddVehicleBody) obj;
            return Intrinsics.areEqual(this.userName, addVehicleBody.userName) && Intrinsics.areEqual(this.countryCode, addVehicleBody.countryCode) && Intrinsics.areEqual(this.vinNum, addVehicleBody.vinNum);
        }
        return false;
    }

    @NotNull
    public final String getCountryCode() {
        return this.countryCode;
    }

    @NotNull
    public final String getUserName() {
        return this.userName;
    }

    @NotNull
    public final String getVinNum() {
        return this.vinNum;
    }

    public int hashCode() {
        return (((this.userName.hashCode() * 31) + this.countryCode.hashCode()) * 31) + this.vinNum.hashCode();
    }

    public final void setCountryCode(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.countryCode = str;
    }

    public final void setUserName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userName = str;
    }

    public final void setVinNum(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vinNum = str;
    }

    @NotNull
    public String toString() {
        return "AddVehicleBody(userName=" + this.userName + ", countryCode=" + this.countryCode + ", vinNum=" + this.vinNum + ')';
    }
}
