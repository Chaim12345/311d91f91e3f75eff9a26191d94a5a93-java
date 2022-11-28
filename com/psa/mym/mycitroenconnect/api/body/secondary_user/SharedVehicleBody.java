package com.psa.mym.mycitroenconnect.api.body.secondary_user;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class SharedVehicleBody {
    @SerializedName("accountId")
    @NotNull
    private String accountId;
    @SerializedName("carModelName")
    @NotNull
    private String carModelName;
    @SerializedName("ownerName")
    @NotNull
    private String ownerName;
    @SerializedName("vehicleNumber")
    @NotNull
    private String vehicleNumber;
    @SerializedName("vehicleRegNo")
    @NotNull
    private String vehicleRegNo;
    @SerializedName("vinNum")
    @NotNull
    private String vinNum;

    public SharedVehicleBody() {
        this(null, null, null, null, null, null, 63, null);
    }

    public SharedVehicleBody(@NotNull String accountId, @NotNull String carModelName, @NotNull String ownerName, @NotNull String vehicleRegNo, @NotNull String vinNum, @NotNull String vehicleNumber) {
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(carModelName, "carModelName");
        Intrinsics.checkNotNullParameter(ownerName, "ownerName");
        Intrinsics.checkNotNullParameter(vehicleRegNo, "vehicleRegNo");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Intrinsics.checkNotNullParameter(vehicleNumber, "vehicleNumber");
        this.accountId = accountId;
        this.carModelName = carModelName;
        this.ownerName = ownerName;
        this.vehicleRegNo = vehicleRegNo;
        this.vinNum = vinNum;
        this.vehicleNumber = vehicleNumber;
    }

    public /* synthetic */ SharedVehicleBody(String str, String str2, String str3, String str4, String str5, String str6, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3, (i2 & 8) != 0 ? "" : str4, (i2 & 16) != 0 ? "" : str5, (i2 & 32) != 0 ? "" : str6);
    }

    public static /* synthetic */ SharedVehicleBody copy$default(SharedVehicleBody sharedVehicleBody, String str, String str2, String str3, String str4, String str5, String str6, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = sharedVehicleBody.accountId;
        }
        if ((i2 & 2) != 0) {
            str2 = sharedVehicleBody.carModelName;
        }
        String str7 = str2;
        if ((i2 & 4) != 0) {
            str3 = sharedVehicleBody.ownerName;
        }
        String str8 = str3;
        if ((i2 & 8) != 0) {
            str4 = sharedVehicleBody.vehicleRegNo;
        }
        String str9 = str4;
        if ((i2 & 16) != 0) {
            str5 = sharedVehicleBody.vinNum;
        }
        String str10 = str5;
        if ((i2 & 32) != 0) {
            str6 = sharedVehicleBody.vehicleNumber;
        }
        return sharedVehicleBody.copy(str, str7, str8, str9, str10, str6);
    }

    @NotNull
    public final String component1() {
        return this.accountId;
    }

    @NotNull
    public final String component2() {
        return this.carModelName;
    }

    @NotNull
    public final String component3() {
        return this.ownerName;
    }

    @NotNull
    public final String component4() {
        return this.vehicleRegNo;
    }

    @NotNull
    public final String component5() {
        return this.vinNum;
    }

    @NotNull
    public final String component6() {
        return this.vehicleNumber;
    }

    @NotNull
    public final SharedVehicleBody copy(@NotNull String accountId, @NotNull String carModelName, @NotNull String ownerName, @NotNull String vehicleRegNo, @NotNull String vinNum, @NotNull String vehicleNumber) {
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(carModelName, "carModelName");
        Intrinsics.checkNotNullParameter(ownerName, "ownerName");
        Intrinsics.checkNotNullParameter(vehicleRegNo, "vehicleRegNo");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Intrinsics.checkNotNullParameter(vehicleNumber, "vehicleNumber");
        return new SharedVehicleBody(accountId, carModelName, ownerName, vehicleRegNo, vinNum, vehicleNumber);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SharedVehicleBody) {
            SharedVehicleBody sharedVehicleBody = (SharedVehicleBody) obj;
            return Intrinsics.areEqual(this.accountId, sharedVehicleBody.accountId) && Intrinsics.areEqual(this.carModelName, sharedVehicleBody.carModelName) && Intrinsics.areEqual(this.ownerName, sharedVehicleBody.ownerName) && Intrinsics.areEqual(this.vehicleRegNo, sharedVehicleBody.vehicleRegNo) && Intrinsics.areEqual(this.vinNum, sharedVehicleBody.vinNum) && Intrinsics.areEqual(this.vehicleNumber, sharedVehicleBody.vehicleNumber);
        }
        return false;
    }

    @NotNull
    public final String getAccountId() {
        return this.accountId;
    }

    @NotNull
    public final String getCarModelName() {
        return this.carModelName;
    }

    @NotNull
    public final String getOwnerName() {
        return this.ownerName;
    }

    @NotNull
    public final String getVehicleNumber() {
        return this.vehicleNumber;
    }

    @NotNull
    public final String getVehicleRegNo() {
        return this.vehicleRegNo;
    }

    @NotNull
    public final String getVinNum() {
        return this.vinNum;
    }

    public int hashCode() {
        return (((((((((this.accountId.hashCode() * 31) + this.carModelName.hashCode()) * 31) + this.ownerName.hashCode()) * 31) + this.vehicleRegNo.hashCode()) * 31) + this.vinNum.hashCode()) * 31) + this.vehicleNumber.hashCode();
    }

    public final void setAccountId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.accountId = str;
    }

    public final void setCarModelName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.carModelName = str;
    }

    public final void setOwnerName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.ownerName = str;
    }

    public final void setVehicleNumber(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vehicleNumber = str;
    }

    public final void setVehicleRegNo(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vehicleRegNo = str;
    }

    public final void setVinNum(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vinNum = str;
    }

    @NotNull
    public String toString() {
        return "SharedVehicleBody(accountId=" + this.accountId + ", carModelName=" + this.carModelName + ", ownerName=" + this.ownerName + ", vehicleRegNo=" + this.vehicleRegNo + ", vinNum=" + this.vinNum + ", vehicleNumber=" + this.vehicleNumber + ')';
    }
}
