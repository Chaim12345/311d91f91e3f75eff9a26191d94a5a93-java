package com.psa.mym.mycitroenconnect.model.onboarding;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Parcelize
/* loaded from: classes3.dex */
public final class UpdateUserProfileResponse implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<UpdateUserProfileResponse> CREATOR = new Creator();
    @SerializedName("address")
    @Nullable
    private String address;
    @SerializedName("bloodGroup")
    @Nullable
    private String bloodGroup;
    @SerializedName("city")
    @Nullable
    private String city;
    @SerializedName("dateOfBirth")
    @Nullable
    private String dateOfBirth;
    @SerializedName("email")
    @Nullable
    private String email;
    @SerializedName("fullName")
    @Nullable
    private String fullName;
    @SerializedName("gender")
    @Nullable
    private String gender;
    @SerializedName("profilePic")
    @Nullable
    private String profilePic;
    @SerializedName("registeredVehicle")
    @Nullable
    private List<RegisteredVehicleItem> registeredVehicle;
    @SerializedName("userName")
    @Nullable
    private String userName;
    @SerializedName("userPreference")
    @Nullable
    private List<UserPreferenceItem> userPreference;

    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<UpdateUserProfileResponse> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final UpdateUserProfileResponse createFromParcel(@NotNull Parcel parcel) {
            ArrayList arrayList;
            ArrayList arrayList2;
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            if (parcel.readInt() == 0) {
                arrayList = null;
            } else {
                int readInt = parcel.readInt();
                arrayList = new ArrayList(readInt);
                for (int i2 = 0; i2 != readInt; i2++) {
                    arrayList.add(UserPreferenceItem.CREATOR.createFromParcel(parcel));
                }
            }
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            if (parcel.readInt() == 0) {
                arrayList2 = null;
            } else {
                int readInt2 = parcel.readInt();
                arrayList2 = new ArrayList(readInt2);
                for (int i3 = 0; i3 != readInt2; i3++) {
                    arrayList2.add(RegisteredVehicleItem.CREATOR.createFromParcel(parcel));
                }
            }
            return new UpdateUserProfileResponse(readString, readString2, arrayList, readString3, readString4, arrayList2, parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final UpdateUserProfileResponse[] newArray(int i2) {
            return new UpdateUserProfileResponse[i2];
        }
    }

    public UpdateUserProfileResponse() {
        this(null, null, null, null, null, null, null, null, null, null, null, 2047, null);
    }

    public UpdateUserProfileResponse(@Nullable String str, @Nullable String str2, @Nullable List<UserPreferenceItem> list, @Nullable String str3, @Nullable String str4, @Nullable List<RegisteredVehicleItem> list2, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9) {
        this.bloodGroup = str;
        this.address = str2;
        this.userPreference = list;
        this.gender = str3;
        this.city = str4;
        this.registeredVehicle = list2;
        this.profilePic = str5;
        this.fullName = str6;
        this.dateOfBirth = str7;
        this.userName = str8;
        this.email = str9;
    }

    public /* synthetic */ UpdateUserProfileResponse(String str, String str2, List list, String str3, String str4, List list2, String str5, String str6, String str7, String str8, String str9, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : list, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? null : str4, (i2 & 32) != 0 ? null : list2, (i2 & 64) != 0 ? null : str5, (i2 & 128) != 0 ? null : str6, (i2 & 256) != 0 ? null : str7, (i2 & 512) != 0 ? null : str8, (i2 & 1024) == 0 ? str9 : null);
    }

    @Nullable
    public final String component1() {
        return this.bloodGroup;
    }

    @Nullable
    public final String component10() {
        return this.userName;
    }

    @Nullable
    public final String component11() {
        return this.email;
    }

    @Nullable
    public final String component2() {
        return this.address;
    }

    @Nullable
    public final List<UserPreferenceItem> component3() {
        return this.userPreference;
    }

    @Nullable
    public final String component4() {
        return this.gender;
    }

    @Nullable
    public final String component5() {
        return this.city;
    }

    @Nullable
    public final List<RegisteredVehicleItem> component6() {
        return this.registeredVehicle;
    }

    @Nullable
    public final String component7() {
        return this.profilePic;
    }

    @Nullable
    public final String component8() {
        return this.fullName;
    }

    @Nullable
    public final String component9() {
        return this.dateOfBirth;
    }

    @NotNull
    public final UpdateUserProfileResponse copy(@Nullable String str, @Nullable String str2, @Nullable List<UserPreferenceItem> list, @Nullable String str3, @Nullable String str4, @Nullable List<RegisteredVehicleItem> list2, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9) {
        return new UpdateUserProfileResponse(str, str2, list, str3, str4, list2, str5, str6, str7, str8, str9);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UpdateUserProfileResponse) {
            UpdateUserProfileResponse updateUserProfileResponse = (UpdateUserProfileResponse) obj;
            return Intrinsics.areEqual(this.bloodGroup, updateUserProfileResponse.bloodGroup) && Intrinsics.areEqual(this.address, updateUserProfileResponse.address) && Intrinsics.areEqual(this.userPreference, updateUserProfileResponse.userPreference) && Intrinsics.areEqual(this.gender, updateUserProfileResponse.gender) && Intrinsics.areEqual(this.city, updateUserProfileResponse.city) && Intrinsics.areEqual(this.registeredVehicle, updateUserProfileResponse.registeredVehicle) && Intrinsics.areEqual(this.profilePic, updateUserProfileResponse.profilePic) && Intrinsics.areEqual(this.fullName, updateUserProfileResponse.fullName) && Intrinsics.areEqual(this.dateOfBirth, updateUserProfileResponse.dateOfBirth) && Intrinsics.areEqual(this.userName, updateUserProfileResponse.userName) && Intrinsics.areEqual(this.email, updateUserProfileResponse.email);
        }
        return false;
    }

    @Nullable
    public final String getAddress() {
        return this.address;
    }

    @Nullable
    public final String getBloodGroup() {
        return this.bloodGroup;
    }

    @Nullable
    public final String getCity() {
        return this.city;
    }

    @Nullable
    public final String getDateOfBirth() {
        return this.dateOfBirth;
    }

    @Nullable
    public final String getEmail() {
        return this.email;
    }

    @Nullable
    public final String getFullName() {
        return this.fullName;
    }

    @Nullable
    public final String getGender() {
        return this.gender;
    }

    @Nullable
    public final String getProfilePic() {
        return this.profilePic;
    }

    @Nullable
    public final List<RegisteredVehicleItem> getRegisteredVehicle() {
        return this.registeredVehicle;
    }

    @Nullable
    public final String getUserName() {
        return this.userName;
    }

    @Nullable
    public final List<UserPreferenceItem> getUserPreference() {
        return this.userPreference;
    }

    public int hashCode() {
        String str = this.bloodGroup;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.address;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        List<UserPreferenceItem> list = this.userPreference;
        int hashCode3 = (hashCode2 + (list == null ? 0 : list.hashCode())) * 31;
        String str3 = this.gender;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.city;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        List<RegisteredVehicleItem> list2 = this.registeredVehicle;
        int hashCode6 = (hashCode5 + (list2 == null ? 0 : list2.hashCode())) * 31;
        String str5 = this.profilePic;
        int hashCode7 = (hashCode6 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.fullName;
        int hashCode8 = (hashCode7 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.dateOfBirth;
        int hashCode9 = (hashCode8 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.userName;
        int hashCode10 = (hashCode9 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.email;
        return hashCode10 + (str9 != null ? str9.hashCode() : 0);
    }

    public final void setAddress(@Nullable String str) {
        this.address = str;
    }

    public final void setBloodGroup(@Nullable String str) {
        this.bloodGroup = str;
    }

    public final void setCity(@Nullable String str) {
        this.city = str;
    }

    public final void setDateOfBirth(@Nullable String str) {
        this.dateOfBirth = str;
    }

    public final void setEmail(@Nullable String str) {
        this.email = str;
    }

    public final void setFullName(@Nullable String str) {
        this.fullName = str;
    }

    public final void setGender(@Nullable String str) {
        this.gender = str;
    }

    public final void setProfilePic(@Nullable String str) {
        this.profilePic = str;
    }

    public final void setRegisteredVehicle(@Nullable List<RegisteredVehicleItem> list) {
        this.registeredVehicle = list;
    }

    public final void setUserName(@Nullable String str) {
        this.userName = str;
    }

    public final void setUserPreference(@Nullable List<UserPreferenceItem> list) {
        this.userPreference = list;
    }

    @NotNull
    public String toString() {
        return "UpdateUserProfileResponse(bloodGroup=" + this.bloodGroup + ", address=" + this.address + ", userPreference=" + this.userPreference + ", gender=" + this.gender + ", city=" + this.city + ", registeredVehicle=" + this.registeredVehicle + ", profilePic=" + this.profilePic + ", fullName=" + this.fullName + ", dateOfBirth=" + this.dateOfBirth + ", userName=" + this.userName + ", email=" + this.email + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.bloodGroup);
        out.writeString(this.address);
        List<UserPreferenceItem> list = this.userPreference;
        if (list == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            out.writeInt(list.size());
            for (UserPreferenceItem userPreferenceItem : list) {
                userPreferenceItem.writeToParcel(out, i2);
            }
        }
        out.writeString(this.gender);
        out.writeString(this.city);
        List<RegisteredVehicleItem> list2 = this.registeredVehicle;
        if (list2 == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            out.writeInt(list2.size());
            for (RegisteredVehicleItem registeredVehicleItem : list2) {
                registeredVehicleItem.writeToParcel(out, i2);
            }
        }
        out.writeString(this.profilePic);
        out.writeString(this.fullName);
        out.writeString(this.dateOfBirth);
        out.writeString(this.userName);
        out.writeString(this.email);
    }
}
