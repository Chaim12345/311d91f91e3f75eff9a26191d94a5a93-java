package com.psa.mym.mycitroenconnect.model.dashboard;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisteredVehicleItem;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Parcelize
/* loaded from: classes3.dex */
public final class EmergencyDetailsItem implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<EmergencyDetailsItem> CREATOR = new Creator();
    @NotNull
    private transient ArrayList<RegisteredVehicleItem> carAccess;
    @Nullable
    private transient String contactCity;
    @SerializedName("contactNum")
    @NotNull
    private String contactNum;
    @Nullable
    private transient String contactStreet;
    @SerializedName("countryCode")
    @NotNull
    private String countryCode;
    @Nullable
    private transient String id;
    private transient boolean isChildContact;
    private transient boolean isEmergencyContact;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @NotNull
    private String name;
    @NotNull
    private transient String status;
    private transient int viewType;

    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<EmergencyDetailsItem> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final EmergencyDetailsItem createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            boolean z = parcel.readInt() != 0;
            boolean z2 = parcel.readInt() != 0;
            int readInt = parcel.readInt();
            String readString4 = parcel.readString();
            String readString5 = parcel.readString();
            String readString6 = parcel.readString();
            String readString7 = parcel.readString();
            int readInt2 = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt2);
            for (int i2 = 0; i2 != readInt2; i2++) {
                arrayList.add(RegisteredVehicleItem.CREATOR.createFromParcel(parcel));
            }
            return new EmergencyDetailsItem(readString, readString2, readString3, z, z2, readInt, readString4, readString5, readString6, readString7, arrayList);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final EmergencyDetailsItem[] newArray(int i2) {
            return new EmergencyDetailsItem[i2];
        }
    }

    public EmergencyDetailsItem() {
        this(null, null, null, false, false, 0, null, null, null, null, null, 2047, null);
    }

    public EmergencyDetailsItem(@NotNull String name, @NotNull String contactNum, @NotNull String countryCode, boolean z, boolean z2, int i2, @Nullable String str, @Nullable String str2, @Nullable String str3, @NotNull String status, @NotNull ArrayList<RegisteredVehicleItem> carAccess) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(contactNum, "contactNum");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(carAccess, "carAccess");
        this.name = name;
        this.contactNum = contactNum;
        this.countryCode = countryCode;
        this.isEmergencyContact = z;
        this.isChildContact = z2;
        this.viewType = i2;
        this.id = str;
        this.contactStreet = str2;
        this.contactCity = str3;
        this.status = status;
        this.carAccess = carAccess;
    }

    public /* synthetic */ EmergencyDetailsItem(String str, String str2, String str3, boolean z, boolean z2, int i2, String str4, String str5, String str6, String str7, ArrayList arrayList, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "" : str, (i3 & 2) != 0 ? "" : str2, (i3 & 4) != 0 ? "" : str3, (i3 & 8) != 0 ? false : z, (i3 & 16) == 0 ? z2 : false, (i3 & 32) != 0 ? -1 : i2, (i3 & 64) != 0 ? "" : str4, (i3 & 128) != 0 ? "" : str5, (i3 & 256) == 0 ? str6 : "", (i3 & 512) != 0 ? AppConstants.SECONDARY_USER_STATE_PENDING : str7, (i3 & 1024) != 0 ? new ArrayList() : arrayList);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final String component10() {
        return this.status;
    }

    @NotNull
    public final ArrayList<RegisteredVehicleItem> component11() {
        return this.carAccess;
    }

    @NotNull
    public final String component2() {
        return this.contactNum;
    }

    @NotNull
    public final String component3() {
        return this.countryCode;
    }

    public final boolean component4() {
        return this.isEmergencyContact;
    }

    public final boolean component5() {
        return this.isChildContact;
    }

    public final int component6() {
        return this.viewType;
    }

    @Nullable
    public final String component7() {
        return this.id;
    }

    @Nullable
    public final String component8() {
        return this.contactStreet;
    }

    @Nullable
    public final String component9() {
        return this.contactCity;
    }

    @NotNull
    public final EmergencyDetailsItem copy(@NotNull String name, @NotNull String contactNum, @NotNull String countryCode, boolean z, boolean z2, int i2, @Nullable String str, @Nullable String str2, @Nullable String str3, @NotNull String status, @NotNull ArrayList<RegisteredVehicleItem> carAccess) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(contactNum, "contactNum");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(carAccess, "carAccess");
        return new EmergencyDetailsItem(name, contactNum, countryCode, z, z2, i2, str, str2, str3, status, carAccess);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EmergencyDetailsItem) {
            EmergencyDetailsItem emergencyDetailsItem = (EmergencyDetailsItem) obj;
            return Intrinsics.areEqual(this.name, emergencyDetailsItem.name) && Intrinsics.areEqual(this.contactNum, emergencyDetailsItem.contactNum) && Intrinsics.areEqual(this.countryCode, emergencyDetailsItem.countryCode) && this.isEmergencyContact == emergencyDetailsItem.isEmergencyContact && this.isChildContact == emergencyDetailsItem.isChildContact && this.viewType == emergencyDetailsItem.viewType && Intrinsics.areEqual(this.id, emergencyDetailsItem.id) && Intrinsics.areEqual(this.contactStreet, emergencyDetailsItem.contactStreet) && Intrinsics.areEqual(this.contactCity, emergencyDetailsItem.contactCity) && Intrinsics.areEqual(this.status, emergencyDetailsItem.status) && Intrinsics.areEqual(this.carAccess, emergencyDetailsItem.carAccess);
        }
        return false;
    }

    @NotNull
    public final ArrayList<RegisteredVehicleItem> getCarAccess() {
        return this.carAccess;
    }

    @Nullable
    public final String getContactCity() {
        return this.contactCity;
    }

    @NotNull
    public final String getContactNum() {
        return this.contactNum;
    }

    @Nullable
    public final String getContactStreet() {
        return this.contactStreet;
    }

    @NotNull
    public final String getCountryCode() {
        return this.countryCode;
    }

    @Nullable
    public final String getId() {
        return this.id;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getStatus() {
        return this.status;
    }

    public final int getViewType() {
        return this.viewType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((this.name.hashCode() * 31) + this.contactNum.hashCode()) * 31) + this.countryCode.hashCode()) * 31;
        boolean z = this.isEmergencyContact;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        int i3 = (hashCode + i2) * 31;
        boolean z2 = this.isChildContact;
        int hashCode2 = (((i3 + (z2 ? 1 : z2 ? 1 : 0)) * 31) + Integer.hashCode(this.viewType)) * 31;
        String str = this.id;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.contactStreet;
        int hashCode4 = (hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.contactCity;
        return ((((hashCode4 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.status.hashCode()) * 31) + this.carAccess.hashCode();
    }

    public final boolean isChildContact() {
        return this.isChildContact;
    }

    public final boolean isEmergencyContact() {
        return this.isEmergencyContact;
    }

    public final void setCarAccess(@NotNull ArrayList<RegisteredVehicleItem> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.carAccess = arrayList;
    }

    public final void setChildContact(boolean z) {
        this.isChildContact = z;
    }

    public final void setContactCity(@Nullable String str) {
        this.contactCity = str;
    }

    public final void setContactNum(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.contactNum = str;
    }

    public final void setContactStreet(@Nullable String str) {
        this.contactStreet = str;
    }

    public final void setCountryCode(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.countryCode = str;
    }

    public final void setEmergencyContact(boolean z) {
        this.isEmergencyContact = z;
    }

    public final void setId(@Nullable String str) {
        this.id = str;
    }

    public final void setName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final void setStatus(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.status = str;
    }

    public final void setViewType(int i2) {
        this.viewType = i2;
    }

    @NotNull
    public String toString() {
        return "EmergencyDetailsItem(name=" + this.name + ", contactNum=" + this.contactNum + ", countryCode=" + this.countryCode + ", isEmergencyContact=" + this.isEmergencyContact + ", isChildContact=" + this.isChildContact + ", viewType=" + this.viewType + ", id=" + this.id + ", contactStreet=" + this.contactStreet + ", contactCity=" + this.contactCity + ", status=" + this.status + ", carAccess=" + this.carAccess + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.name);
        out.writeString(this.contactNum);
        out.writeString(this.countryCode);
        out.writeInt(this.isEmergencyContact ? 1 : 0);
        out.writeInt(this.isChildContact ? 1 : 0);
        out.writeInt(this.viewType);
        out.writeString(this.id);
        out.writeString(this.contactStreet);
        out.writeString(this.contactCity);
        out.writeString(this.status);
        ArrayList<RegisteredVehicleItem> arrayList = this.carAccess;
        out.writeInt(arrayList.size());
        Iterator<RegisteredVehicleItem> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().writeToParcel(out, i2);
        }
    }
}
