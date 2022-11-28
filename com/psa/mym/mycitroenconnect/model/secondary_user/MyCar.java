package com.psa.mym.mycitroenconnect.model.secondary_user;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.model.Token;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
@Parcelize
/* loaded from: classes3.dex */
public final class MyCar implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<MyCar> CREATOR = new Creator();
    @SerializedName("accountId")
    @Nullable
    private final String accountId;
    @SerializedName("carModelName")
    @Nullable
    private final String carModelName;
    @SerializedName("invite_status")
    @Nullable
    private final String inviteStatus;
    private boolean isAccessible;
    private transient boolean isVehicleSelected;
    private boolean isViewOnly;
    @SerializedName("ownerName")
    @Nullable
    private final String ownerName;
    @SerializedName("token")
    @Nullable
    private final Token token;
    @SerializedName("userType")
    @Nullable
    private String userType;
    @Nullable
    private transient Integer vehicleImage;
    @SerializedName("vehicleRegNo")
    @Nullable
    private final String vehicleRegNo;
    @SerializedName("vehicleType")
    @Nullable
    private final String vehicleType;
    @SerializedName("vinNum")
    @Nullable
    private final String vinNum;

    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<MyCar> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final MyCar createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new MyCar(parcel.readInt() == 0 ? null : Token.CREATOR.createFromParcel(parcel), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt() != 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final MyCar[] newArray(int i2) {
            return new MyCar[i2];
        }
    }

    public MyCar() {
        this(null, null, null, null, null, null, null, null, null, null, false, false, false, 8191, null);
    }

    public MyCar(@Nullable Token token, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable Integer num, boolean z, boolean z2, boolean z3) {
        this.token = token;
        this.accountId = str;
        this.carModelName = str2;
        this.inviteStatus = str3;
        this.ownerName = str4;
        this.vehicleRegNo = str5;
        this.vinNum = str6;
        this.vehicleType = str7;
        this.userType = str8;
        this.vehicleImage = num;
        this.isVehicleSelected = z;
        this.isAccessible = z2;
        this.isViewOnly = z3;
    }

    public /* synthetic */ MyCar(Token token, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Integer num, boolean z, boolean z2, boolean z3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : token, (i2 & 2) != 0 ? "" : str, (i2 & 4) != 0 ? "" : str2, (i2 & 8) != 0 ? "" : str3, (i2 & 16) != 0 ? "" : str4, (i2 & 32) != 0 ? "" : str5, (i2 & 64) != 0 ? "" : str6, (i2 & 128) != 0 ? "" : str7, (i2 & 256) == 0 ? str8 : "", (i2 & 512) != 0 ? Integer.valueOf((int) R.drawable.citroen_nav_bar_car) : num, (i2 & 1024) != 0 ? false : z, (i2 & 2048) != 0 ? false : z2, (i2 & 4096) == 0 ? z3 : false);
    }

    @Nullable
    public final Token component1() {
        return this.token;
    }

    @Nullable
    public final Integer component10() {
        return this.vehicleImage;
    }

    public final boolean component11() {
        return this.isVehicleSelected;
    }

    public final boolean component12() {
        return this.isAccessible;
    }

    public final boolean component13() {
        return this.isViewOnly;
    }

    @Nullable
    public final String component2() {
        return this.accountId;
    }

    @Nullable
    public final String component3() {
        return this.carModelName;
    }

    @Nullable
    public final String component4() {
        return this.inviteStatus;
    }

    @Nullable
    public final String component5() {
        return this.ownerName;
    }

    @Nullable
    public final String component6() {
        return this.vehicleRegNo;
    }

    @Nullable
    public final String component7() {
        return this.vinNum;
    }

    @Nullable
    public final String component8() {
        return this.vehicleType;
    }

    @Nullable
    public final String component9() {
        return this.userType;
    }

    @NotNull
    public final MyCar copy(@Nullable Token token, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable Integer num, boolean z, boolean z2, boolean z3) {
        return new MyCar(token, str, str2, str3, str4, str5, str6, str7, str8, num, z, z2, z3);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MyCar) {
            MyCar myCar = (MyCar) obj;
            return Intrinsics.areEqual(this.token, myCar.token) && Intrinsics.areEqual(this.accountId, myCar.accountId) && Intrinsics.areEqual(this.carModelName, myCar.carModelName) && Intrinsics.areEqual(this.inviteStatus, myCar.inviteStatus) && Intrinsics.areEqual(this.ownerName, myCar.ownerName) && Intrinsics.areEqual(this.vehicleRegNo, myCar.vehicleRegNo) && Intrinsics.areEqual(this.vinNum, myCar.vinNum) && Intrinsics.areEqual(this.vehicleType, myCar.vehicleType) && Intrinsics.areEqual(this.userType, myCar.userType) && Intrinsics.areEqual(this.vehicleImage, myCar.vehicleImage) && this.isVehicleSelected == myCar.isVehicleSelected && this.isAccessible == myCar.isAccessible && this.isViewOnly == myCar.isViewOnly;
        }
        return false;
    }

    @Nullable
    public final String getAccountId() {
        return this.accountId;
    }

    @Nullable
    public final String getCarModelName() {
        return this.carModelName;
    }

    @Nullable
    public final String getInviteStatus() {
        return this.inviteStatus;
    }

    @Nullable
    public final String getOwnerName() {
        return this.ownerName;
    }

    @Nullable
    public final Token getToken() {
        return this.token;
    }

    @Nullable
    public final String getUserType() {
        return this.userType;
    }

    @Nullable
    public final Integer getVehicleImage() {
        return this.vehicleImage;
    }

    @Nullable
    public final String getVehicleRegNo() {
        return this.vehicleRegNo;
    }

    @Nullable
    public final String getVehicleType() {
        return this.vehicleType;
    }

    @Nullable
    public final String getVinNum() {
        return this.vinNum;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        Token token = this.token;
        int hashCode = (token == null ? 0 : token.hashCode()) * 31;
        String str = this.accountId;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.carModelName;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.inviteStatus;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.ownerName;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.vehicleRegNo;
        int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.vinNum;
        int hashCode7 = (hashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.vehicleType;
        int hashCode8 = (hashCode7 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.userType;
        int hashCode9 = (hashCode8 + (str8 == null ? 0 : str8.hashCode())) * 31;
        Integer num = this.vehicleImage;
        int hashCode10 = (hashCode9 + (num != null ? num.hashCode() : 0)) * 31;
        boolean z = this.isVehicleSelected;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        int i3 = (hashCode10 + i2) * 31;
        boolean z2 = this.isAccessible;
        int i4 = z2;
        if (z2 != 0) {
            i4 = 1;
        }
        int i5 = (i3 + i4) * 31;
        boolean z3 = this.isViewOnly;
        return i5 + (z3 ? 1 : z3 ? 1 : 0);
    }

    public final boolean isAccessible() {
        return this.isAccessible;
    }

    public final boolean isVehicleSelected() {
        return this.isVehicleSelected;
    }

    public final boolean isViewOnly() {
        return this.isViewOnly;
    }

    public final void setAccessible(boolean z) {
        this.isAccessible = z;
    }

    public final void setUserType(@Nullable String str) {
        this.userType = str;
    }

    public final void setVehicleImage(@Nullable Integer num) {
        this.vehicleImage = num;
    }

    public final void setVehicleSelected(boolean z) {
        this.isVehicleSelected = z;
    }

    public final void setViewOnly(boolean z) {
        this.isViewOnly = z;
    }

    @NotNull
    public String toString() {
        return "MyCar(token=" + this.token + ", accountId=" + this.accountId + ", carModelName=" + this.carModelName + ", inviteStatus=" + this.inviteStatus + ", ownerName=" + this.ownerName + ", vehicleRegNo=" + this.vehicleRegNo + ", vinNum=" + this.vinNum + ", vehicleType=" + this.vehicleType + ", userType=" + this.userType + ", vehicleImage=" + this.vehicleImage + ", isVehicleSelected=" + this.isVehicleSelected + ", isAccessible=" + this.isAccessible + ", isViewOnly=" + this.isViewOnly + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        Token token = this.token;
        if (token == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            token.writeToParcel(out, i2);
        }
        out.writeString(this.accountId);
        out.writeString(this.carModelName);
        out.writeString(this.inviteStatus);
        out.writeString(this.ownerName);
        out.writeString(this.vehicleRegNo);
        out.writeString(this.vinNum);
        out.writeString(this.vehicleType);
        out.writeString(this.userType);
        Integer num = this.vehicleImage;
        if (num == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            out.writeInt(num.intValue());
        }
        out.writeInt(this.isVehicleSelected ? 1 : 0);
        out.writeInt(this.isAccessible ? 1 : 0);
        out.writeInt(this.isViewOnly ? 1 : 0);
    }
}
