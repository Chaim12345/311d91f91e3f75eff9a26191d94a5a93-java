package com.psa.mym.mycitroenconnect.model.secondary_user;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.DrawableRes;
import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
@Parcelize
/* loaded from: classes3.dex */
public final class SharedVehicle implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<SharedVehicle> CREATOR = new Creator();
    private transient int carImage;
    @SerializedName("carModelName")
    @Nullable
    private final String carModelName;
    @SerializedName("countryCode")
    @Nullable
    private String countryCode;
    @SerializedName("mobileNum")
    @Nullable
    private String mobileNum;
    @SerializedName("ownerName")
    @Nullable
    private final String ownerName;
    @SerializedName("vehicleRegNo")
    @Nullable
    private final String vehicleRegNo;
    @SerializedName("vinNum")
    @Nullable
    private final String vinNum;

    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<SharedVehicle> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final SharedVehicle createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new SharedVehicle(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final SharedVehicle[] newArray(int i2) {
            return new SharedVehicle[i2];
        }
    }

    public SharedVehicle() {
        this(null, null, null, null, null, null, 0, 127, null);
    }

    public SharedVehicle(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @DrawableRes int i2) {
        this.carModelName = str;
        this.ownerName = str2;
        this.vehicleRegNo = str3;
        this.vinNum = str4;
        this.countryCode = str5;
        this.mobileNum = str6;
        this.carImage = i2;
    }

    public /* synthetic */ SharedVehicle(String str, String str2, String str3, String str4, String str5, String str6, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "" : str, (i3 & 2) != 0 ? "" : str2, (i3 & 4) != 0 ? "" : str3, (i3 & 8) != 0 ? "" : str4, (i3 & 16) != 0 ? "" : str5, (i3 & 32) == 0 ? str6 : "", (i3 & 64) != 0 ? R.drawable.citroen_nav_bar_car : i2);
    }

    public static /* synthetic */ SharedVehicle copy$default(SharedVehicle sharedVehicle, String str, String str2, String str3, String str4, String str5, String str6, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = sharedVehicle.carModelName;
        }
        if ((i3 & 2) != 0) {
            str2 = sharedVehicle.ownerName;
        }
        String str7 = str2;
        if ((i3 & 4) != 0) {
            str3 = sharedVehicle.vehicleRegNo;
        }
        String str8 = str3;
        if ((i3 & 8) != 0) {
            str4 = sharedVehicle.vinNum;
        }
        String str9 = str4;
        if ((i3 & 16) != 0) {
            str5 = sharedVehicle.countryCode;
        }
        String str10 = str5;
        if ((i3 & 32) != 0) {
            str6 = sharedVehicle.mobileNum;
        }
        String str11 = str6;
        if ((i3 & 64) != 0) {
            i2 = sharedVehicle.carImage;
        }
        return sharedVehicle.copy(str, str7, str8, str9, str10, str11, i2);
    }

    @Nullable
    public final String component1() {
        return this.carModelName;
    }

    @Nullable
    public final String component2() {
        return this.ownerName;
    }

    @Nullable
    public final String component3() {
        return this.vehicleRegNo;
    }

    @Nullable
    public final String component4() {
        return this.vinNum;
    }

    @Nullable
    public final String component5() {
        return this.countryCode;
    }

    @Nullable
    public final String component6() {
        return this.mobileNum;
    }

    public final int component7() {
        return this.carImage;
    }

    @NotNull
    public final SharedVehicle copy(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @DrawableRes int i2) {
        return new SharedVehicle(str, str2, str3, str4, str5, str6, i2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SharedVehicle) {
            SharedVehicle sharedVehicle = (SharedVehicle) obj;
            return Intrinsics.areEqual(this.carModelName, sharedVehicle.carModelName) && Intrinsics.areEqual(this.ownerName, sharedVehicle.ownerName) && Intrinsics.areEqual(this.vehicleRegNo, sharedVehicle.vehicleRegNo) && Intrinsics.areEqual(this.vinNum, sharedVehicle.vinNum) && Intrinsics.areEqual(this.countryCode, sharedVehicle.countryCode) && Intrinsics.areEqual(this.mobileNum, sharedVehicle.mobileNum) && this.carImage == sharedVehicle.carImage;
        }
        return false;
    }

    public final int getCarImage() {
        return this.carImage;
    }

    @Nullable
    public final String getCarModelName() {
        return this.carModelName;
    }

    @Nullable
    public final String getCountryCode() {
        return this.countryCode;
    }

    @Nullable
    public final String getMobileNum() {
        return this.mobileNum;
    }

    @Nullable
    public final String getOwnerName() {
        return this.ownerName;
    }

    @Nullable
    public final String getVehicleRegNo() {
        return this.vehicleRegNo;
    }

    @Nullable
    public final String getVinNum() {
        return this.vinNum;
    }

    public int hashCode() {
        String str = this.carModelName;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.ownerName;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.vehicleRegNo;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.vinNum;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.countryCode;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.mobileNum;
        return ((hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31) + Integer.hashCode(this.carImage);
    }

    public final void setCarImage(int i2) {
        this.carImage = i2;
    }

    public final void setCountryCode(@Nullable String str) {
        this.countryCode = str;
    }

    public final void setMobileNum(@Nullable String str) {
        this.mobileNum = str;
    }

    @NotNull
    public String toString() {
        return "SharedVehicle(carModelName=" + this.carModelName + ", ownerName=" + this.ownerName + ", vehicleRegNo=" + this.vehicleRegNo + ", vinNum=" + this.vinNum + ", countryCode=" + this.countryCode + ", mobileNum=" + this.mobileNum + ", carImage=" + this.carImage + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.carModelName);
        out.writeString(this.ownerName);
        out.writeString(this.vehicleRegNo);
        out.writeString(this.vinNum);
        out.writeString(this.countryCode);
        out.writeString(this.mobileNum);
        out.writeInt(this.carImage);
    }
}
