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
public final class UserProfileResponse implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<UserProfileResponse> CREATOR = new Creator();
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
    @SerializedName("mobile")
    @Nullable
    private String mobile;
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
    public static final class Creator implements Parcelable.Creator<UserProfileResponse> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final UserProfileResponse createFromParcel(@NotNull Parcel parcel) {
            ArrayList arrayList;
            ArrayList arrayList2;
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            String readString5 = parcel.readString();
            String readString6 = parcel.readString();
            String readString7 = parcel.readString();
            String readString8 = parcel.readString();
            if (parcel.readInt() == 0) {
                arrayList = null;
            } else {
                int readInt = parcel.readInt();
                arrayList = new ArrayList(readInt);
                for (int i2 = 0; i2 != readInt; i2++) {
                    arrayList.add(RegisteredVehicleItem.CREATOR.createFromParcel(parcel));
                }
            }
            String readString9 = parcel.readString();
            if (parcel.readInt() == 0) {
                arrayList2 = null;
            } else {
                int readInt2 = parcel.readInt();
                arrayList2 = new ArrayList(readInt2);
                for (int i3 = 0; i3 != readInt2; i3++) {
                    arrayList2.add(UserPreferenceItem.CREATOR.createFromParcel(parcel));
                }
            }
            return new UserProfileResponse(readString, readString2, readString3, readString4, readString5, readString6, readString7, readString8, arrayList, readString9, arrayList2, parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final UserProfileResponse[] newArray(int i2) {
            return new UserProfileResponse[i2];
        }
    }

    public UserProfileResponse() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, 4095, null);
    }

    public UserProfileResponse(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable List<RegisteredVehicleItem> list, @Nullable String str9, @Nullable List<UserPreferenceItem> list2, @Nullable String str10) {
        this.userName = str;
        this.email = str2;
        this.fullName = str3;
        this.gender = str4;
        this.bloodGroup = str5;
        this.dateOfBirth = str6;
        this.address = str7;
        this.city = str8;
        this.registeredVehicle = list;
        this.mobile = str9;
        this.userPreference = list2;
        this.profilePic = str10;
    }

    public /* synthetic */ UserProfileResponse(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, List list, String str9, List list2, String str10, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : str5, (i2 & 32) != 0 ? null : str6, (i2 & 64) != 0 ? null : str7, (i2 & 128) != 0 ? null : str8, (i2 & 256) != 0 ? null : list, (i2 & 512) != 0 ? null : str9, (i2 & 1024) != 0 ? null : list2, (i2 & 2048) == 0 ? str10 : null);
    }

    @Nullable
    public final String component1() {
        return this.userName;
    }

    @Nullable
    public final String component10() {
        return this.mobile;
    }

    @Nullable
    public final List<UserPreferenceItem> component11() {
        return this.userPreference;
    }

    @Nullable
    public final String component12() {
        return this.profilePic;
    }

    @Nullable
    public final String component2() {
        return this.email;
    }

    @Nullable
    public final String component3() {
        return this.fullName;
    }

    @Nullable
    public final String component4() {
        return this.gender;
    }

    @Nullable
    public final String component5() {
        return this.bloodGroup;
    }

    @Nullable
    public final String component6() {
        return this.dateOfBirth;
    }

    @Nullable
    public final String component7() {
        return this.address;
    }

    @Nullable
    public final String component8() {
        return this.city;
    }

    @Nullable
    public final List<RegisteredVehicleItem> component9() {
        return this.registeredVehicle;
    }

    @NotNull
    public final UserProfileResponse copy(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable List<RegisteredVehicleItem> list, @Nullable String str9, @Nullable List<UserPreferenceItem> list2, @Nullable String str10) {
        return new UserProfileResponse(str, str2, str3, str4, str5, str6, str7, str8, list, str9, list2, str10);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UserProfileResponse) {
            UserProfileResponse userProfileResponse = (UserProfileResponse) obj;
            return Intrinsics.areEqual(this.userName, userProfileResponse.userName) && Intrinsics.areEqual(this.email, userProfileResponse.email) && Intrinsics.areEqual(this.fullName, userProfileResponse.fullName) && Intrinsics.areEqual(this.gender, userProfileResponse.gender) && Intrinsics.areEqual(this.bloodGroup, userProfileResponse.bloodGroup) && Intrinsics.areEqual(this.dateOfBirth, userProfileResponse.dateOfBirth) && Intrinsics.areEqual(this.address, userProfileResponse.address) && Intrinsics.areEqual(this.city, userProfileResponse.city) && Intrinsics.areEqual(this.registeredVehicle, userProfileResponse.registeredVehicle) && Intrinsics.areEqual(this.mobile, userProfileResponse.mobile) && Intrinsics.areEqual(this.userPreference, userProfileResponse.userPreference) && Intrinsics.areEqual(this.profilePic, userProfileResponse.profilePic);
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
    public final String getMobile() {
        return this.mobile;
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
        String str = this.userName;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.email;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.fullName;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.gender;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.bloodGroup;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.dateOfBirth;
        int hashCode6 = (hashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.address;
        int hashCode7 = (hashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.city;
        int hashCode8 = (hashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        List<RegisteredVehicleItem> list = this.registeredVehicle;
        int hashCode9 = (hashCode8 + (list == null ? 0 : list.hashCode())) * 31;
        String str9 = this.mobile;
        int hashCode10 = (hashCode9 + (str9 == null ? 0 : str9.hashCode())) * 31;
        List<UserPreferenceItem> list2 = this.userPreference;
        int hashCode11 = (hashCode10 + (list2 == null ? 0 : list2.hashCode())) * 31;
        String str10 = this.profilePic;
        return hashCode11 + (str10 != null ? str10.hashCode() : 0);
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

    public final void setMobile(@Nullable String str) {
        this.mobile = str;
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
        return "UserProfileResponse(userName=" + this.userName + ", email=" + this.email + ", fullName=" + this.fullName + ", gender=" + this.gender + ", bloodGroup=" + this.bloodGroup + ", dateOfBirth=" + this.dateOfBirth + ", address=" + this.address + ", city=" + this.city + ", registeredVehicle=" + this.registeredVehicle + ", mobile=" + this.mobile + ", userPreference=" + this.userPreference + ", profilePic=" + this.profilePic + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.userName);
        out.writeString(this.email);
        out.writeString(this.fullName);
        out.writeString(this.gender);
        out.writeString(this.bloodGroup);
        out.writeString(this.dateOfBirth);
        out.writeString(this.address);
        out.writeString(this.city);
        List<RegisteredVehicleItem> list = this.registeredVehicle;
        if (list == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            out.writeInt(list.size());
            for (RegisteredVehicleItem registeredVehicleItem : list) {
                registeredVehicleItem.writeToParcel(out, i2);
            }
        }
        out.writeString(this.mobile);
        List<UserPreferenceItem> list2 = this.userPreference;
        if (list2 == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            out.writeInt(list2.size());
            for (UserPreferenceItem userPreferenceItem : list2) {
                userPreferenceItem.writeToParcel(out, i2);
            }
        }
        out.writeString(this.profilePic);
    }
}
