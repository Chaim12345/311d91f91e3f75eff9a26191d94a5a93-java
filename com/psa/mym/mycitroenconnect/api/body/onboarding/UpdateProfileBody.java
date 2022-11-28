package com.psa.mym.mycitroenconnect.api.body.onboarding;

import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisteredVehicleItem;
import com.psa.mym.mycitroenconnect.model.onboarding.UserPreferenceItem;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class UpdateProfileBody {
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
    private ArrayList<RegisteredVehicleItem> registeredVehicle;
    @SerializedName("userName")
    @Nullable
    private String userName;
    @SerializedName("userPreference")
    @Nullable
    private ArrayList<UserPreferenceItem> userPreference;

    public UpdateProfileBody() {
        this(null, null, null, null, null, null, null, null, null, null, null, 2047, null);
    }

    public UpdateProfileBody(@Nullable String str, @Nullable String str2, @Nullable ArrayList<UserPreferenceItem> arrayList, @Nullable String str3, @Nullable String str4, @Nullable ArrayList<RegisteredVehicleItem> arrayList2, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9) {
        this.bloodGroup = str;
        this.address = str2;
        this.userPreference = arrayList;
        this.gender = str3;
        this.city = str4;
        this.registeredVehicle = arrayList2;
        this.profilePic = str5;
        this.fullName = str6;
        this.dateOfBirth = str7;
        this.userName = str8;
        this.email = str9;
    }

    public /* synthetic */ UpdateProfileBody(String str, String str2, ArrayList arrayList, String str3, String str4, ArrayList arrayList2, String str5, String str6, String str7, String str8, String str9, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : arrayList, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? null : str4, (i2 & 32) != 0 ? null : arrayList2, (i2 & 64) != 0 ? null : str5, (i2 & 128) != 0 ? null : str6, (i2 & 256) != 0 ? null : str7, (i2 & 512) != 0 ? null : str8, (i2 & 1024) == 0 ? str9 : null);
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
    public final ArrayList<UserPreferenceItem> component3() {
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
    public final ArrayList<RegisteredVehicleItem> component6() {
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
    public final UpdateProfileBody copy(@Nullable String str, @Nullable String str2, @Nullable ArrayList<UserPreferenceItem> arrayList, @Nullable String str3, @Nullable String str4, @Nullable ArrayList<RegisteredVehicleItem> arrayList2, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9) {
        return new UpdateProfileBody(str, str2, arrayList, str3, str4, arrayList2, str5, str6, str7, str8, str9);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UpdateProfileBody) {
            UpdateProfileBody updateProfileBody = (UpdateProfileBody) obj;
            return Intrinsics.areEqual(this.bloodGroup, updateProfileBody.bloodGroup) && Intrinsics.areEqual(this.address, updateProfileBody.address) && Intrinsics.areEqual(this.userPreference, updateProfileBody.userPreference) && Intrinsics.areEqual(this.gender, updateProfileBody.gender) && Intrinsics.areEqual(this.city, updateProfileBody.city) && Intrinsics.areEqual(this.registeredVehicle, updateProfileBody.registeredVehicle) && Intrinsics.areEqual(this.profilePic, updateProfileBody.profilePic) && Intrinsics.areEqual(this.fullName, updateProfileBody.fullName) && Intrinsics.areEqual(this.dateOfBirth, updateProfileBody.dateOfBirth) && Intrinsics.areEqual(this.userName, updateProfileBody.userName) && Intrinsics.areEqual(this.email, updateProfileBody.email);
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
    public final ArrayList<RegisteredVehicleItem> getRegisteredVehicle() {
        return this.registeredVehicle;
    }

    @Nullable
    public final String getUserName() {
        return this.userName;
    }

    @Nullable
    public final ArrayList<UserPreferenceItem> getUserPreference() {
        return this.userPreference;
    }

    public int hashCode() {
        String str = this.bloodGroup;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.address;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        ArrayList<UserPreferenceItem> arrayList = this.userPreference;
        int hashCode3 = (hashCode2 + (arrayList == null ? 0 : arrayList.hashCode())) * 31;
        String str3 = this.gender;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.city;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        ArrayList<RegisteredVehicleItem> arrayList2 = this.registeredVehicle;
        int hashCode6 = (hashCode5 + (arrayList2 == null ? 0 : arrayList2.hashCode())) * 31;
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

    public final void setRegisteredVehicle(@Nullable ArrayList<RegisteredVehicleItem> arrayList) {
        this.registeredVehicle = arrayList;
    }

    public final void setUserName(@Nullable String str) {
        this.userName = str;
    }

    public final void setUserPreference(@Nullable ArrayList<UserPreferenceItem> arrayList) {
        this.userPreference = arrayList;
    }

    @NotNull
    public String toString() {
        return "UpdateProfileBody(bloodGroup=" + this.bloodGroup + ", address=" + this.address + ", userPreference=" + this.userPreference + ", gender=" + this.gender + ", city=" + this.city + ", registeredVehicle=" + this.registeredVehicle + ", profilePic=" + this.profilePic + ", fullName=" + this.fullName + ", dateOfBirth=" + this.dateOfBirth + ", userName=" + this.userName + ", email=" + this.email + ')';
    }
}
