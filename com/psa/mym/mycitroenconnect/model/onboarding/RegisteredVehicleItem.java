package com.psa.mym.mycitroenconnect.model.onboarding;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Parcelize
/* loaded from: classes3.dex */
public final class RegisteredVehicleItem implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<RegisteredVehicleItem> CREATOR = new Creator();
    @SerializedName("carModelName")
    @Nullable
    private String carModelName;
    @Nullable
    private transient String inviteStatus;
    private boolean isAccessible;
    private transient boolean isVehicleSelected;
    private boolean isViewOnly;
    @Nullable
    private transient Integer vehicleImage;
    @SerializedName("vehicleNumber")
    @Nullable
    private String vehicleNumber;
    @SerializedName("vehicleType")
    @NotNull
    private String vehicleType;
    private transient int viewType;
    @SerializedName("vinNum")
    @Nullable
    private String vinNum;

    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<RegisteredVehicleItem> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final RegisteredVehicleItem createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new RegisteredVehicleItem(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readInt() != 0, parcel.readInt(), parcel.readInt() != 0, parcel.readInt() != 0, parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final RegisteredVehicleItem[] newArray(int i2) {
            return new RegisteredVehicleItem[i2];
        }
    }

    public RegisteredVehicleItem() {
        this(null, null, null, null, null, false, 0, false, false, null, 1023, null);
    }

    public RegisteredVehicleItem(@Nullable String str, @Nullable String str2, @Nullable String str3, @NotNull String vehicleType, @Nullable Integer num, boolean z, int i2, boolean z2, boolean z3, @Nullable String str4) {
        Intrinsics.checkNotNullParameter(vehicleType, "vehicleType");
        this.carModelName = str;
        this.vehicleNumber = str2;
        this.vinNum = str3;
        this.vehicleType = vehicleType;
        this.vehicleImage = num;
        this.isVehicleSelected = z;
        this.viewType = i2;
        this.isAccessible = z2;
        this.isViewOnly = z3;
        this.inviteStatus = str4;
    }

    public /* synthetic */ RegisteredVehicleItem(String str, String str2, String str3, String str4, Integer num, boolean z, int i2, boolean z2, boolean z3, String str5, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : str, (i3 & 2) != 0 ? null : str2, (i3 & 4) == 0 ? str3 : null, (i3 & 8) != 0 ? "" : str4, (i3 & 16) != 0 ? 0 : num, (i3 & 32) != 0 ? false : z, (i3 & 64) != 0 ? 1 : i2, (i3 & 128) != 0 ? false : z2, (i3 & 256) == 0 ? z3 : false, (i3 & 512) != 0 ? AppConstants.SECONDARY_USER_STATE_PENDING : str5);
    }

    @Nullable
    public final String component1() {
        return this.carModelName;
    }

    @Nullable
    public final String component10() {
        return this.inviteStatus;
    }

    @Nullable
    public final String component2() {
        return this.vehicleNumber;
    }

    @Nullable
    public final String component3() {
        return this.vinNum;
    }

    @NotNull
    public final String component4() {
        return this.vehicleType;
    }

    @Nullable
    public final Integer component5() {
        return this.vehicleImage;
    }

    public final boolean component6() {
        return this.isVehicleSelected;
    }

    public final int component7() {
        return this.viewType;
    }

    public final boolean component8() {
        return this.isAccessible;
    }

    public final boolean component9() {
        return this.isViewOnly;
    }

    @NotNull
    public final RegisteredVehicleItem copy(@Nullable String str, @Nullable String str2, @Nullable String str3, @NotNull String vehicleType, @Nullable Integer num, boolean z, int i2, boolean z2, boolean z3, @Nullable String str4) {
        Intrinsics.checkNotNullParameter(vehicleType, "vehicleType");
        return new RegisteredVehicleItem(str, str2, str3, vehicleType, num, z, i2, z2, z3, str4);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RegisteredVehicleItem) {
            RegisteredVehicleItem registeredVehicleItem = (RegisteredVehicleItem) obj;
            return Intrinsics.areEqual(this.carModelName, registeredVehicleItem.carModelName) && Intrinsics.areEqual(this.vehicleNumber, registeredVehicleItem.vehicleNumber) && Intrinsics.areEqual(this.vinNum, registeredVehicleItem.vinNum) && Intrinsics.areEqual(this.vehicleType, registeredVehicleItem.vehicleType) && Intrinsics.areEqual(this.vehicleImage, registeredVehicleItem.vehicleImage) && this.isVehicleSelected == registeredVehicleItem.isVehicleSelected && this.viewType == registeredVehicleItem.viewType && this.isAccessible == registeredVehicleItem.isAccessible && this.isViewOnly == registeredVehicleItem.isViewOnly && Intrinsics.areEqual(this.inviteStatus, registeredVehicleItem.inviteStatus);
        }
        return false;
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
    public final Integer getVehicleImage() {
        return this.vehicleImage;
    }

    @Nullable
    public final String getVehicleNumber() {
        return this.vehicleNumber;
    }

    @NotNull
    public final String getVehicleType() {
        return this.vehicleType;
    }

    public final int getViewType() {
        return this.viewType;
    }

    @Nullable
    public final String getVinNum() {
        return this.vinNum;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.carModelName;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.vehicleNumber;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.vinNum;
        int hashCode3 = (((hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.vehicleType.hashCode()) * 31;
        Integer num = this.vehicleImage;
        int hashCode4 = (hashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        boolean z = this.isVehicleSelected;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        int hashCode5 = (((hashCode4 + i2) * 31) + Integer.hashCode(this.viewType)) * 31;
        boolean z2 = this.isAccessible;
        int i3 = z2;
        if (z2 != 0) {
            i3 = 1;
        }
        int i4 = (hashCode5 + i3) * 31;
        boolean z3 = this.isViewOnly;
        int i5 = (i4 + (z3 ? 1 : z3 ? 1 : 0)) * 31;
        String str4 = this.inviteStatus;
        return i5 + (str4 != null ? str4.hashCode() : 0);
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

    public final void setCarModelName(@Nullable String str) {
        this.carModelName = str;
    }

    public final void setInviteStatus(@Nullable String str) {
        this.inviteStatus = str;
    }

    public final void setVehicleImage(@Nullable Integer num) {
        this.vehicleImage = num;
    }

    public final void setVehicleNumber(@Nullable String str) {
        this.vehicleNumber = str;
    }

    public final void setVehicleSelected(boolean z) {
        this.isVehicleSelected = z;
    }

    public final void setVehicleType(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vehicleType = str;
    }

    public final void setViewOnly(boolean z) {
        this.isViewOnly = z;
    }

    public final void setViewType(int i2) {
        this.viewType = i2;
    }

    public final void setVinNum(@Nullable String str) {
        this.vinNum = str;
    }

    @NotNull
    public String toString() {
        return "RegisteredVehicleItem(carModelName=" + this.carModelName + ", vehicleNumber=" + this.vehicleNumber + ", vinNum=" + this.vinNum + ", vehicleType=" + this.vehicleType + ", vehicleImage=" + this.vehicleImage + ", isVehicleSelected=" + this.isVehicleSelected + ", viewType=" + this.viewType + ", isAccessible=" + this.isAccessible + ", isViewOnly=" + this.isViewOnly + ", inviteStatus=" + this.inviteStatus + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        int intValue;
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.carModelName);
        out.writeString(this.vehicleNumber);
        out.writeString(this.vinNum);
        out.writeString(this.vehicleType);
        Integer num = this.vehicleImage;
        if (num == null) {
            intValue = 0;
        } else {
            out.writeInt(1);
            intValue = num.intValue();
        }
        out.writeInt(intValue);
        out.writeInt(this.isVehicleSelected ? 1 : 0);
        out.writeInt(this.viewType);
        out.writeInt(this.isAccessible ? 1 : 0);
        out.writeInt(this.isViewOnly ? 1 : 0);
        out.writeString(this.inviteStatus);
    }
}
