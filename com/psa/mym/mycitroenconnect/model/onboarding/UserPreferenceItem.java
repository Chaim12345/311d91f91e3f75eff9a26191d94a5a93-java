package com.psa.mym.mycitroenconnect.model.onboarding;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Parcelize
/* loaded from: classes3.dex */
public final class UserPreferenceItem implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<UserPreferenceItem> CREATOR = new Creator();
    @SerializedName("email")
    @Nullable
    private String email;
    @SerializedName("phone")
    @Nullable
    private String phone;
    @SerializedName("sms")
    @Nullable
    private String sms;
    @SerializedName("whatsApp")
    @Nullable
    private String whatsapp;

    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<UserPreferenceItem> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final UserPreferenceItem createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new UserPreferenceItem(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final UserPreferenceItem[] newArray(int i2) {
            return new UserPreferenceItem[i2];
        }
    }

    public UserPreferenceItem() {
        this(null, null, null, null, 15, null);
    }

    public UserPreferenceItem(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        this.phone = str;
        this.sms = str2;
        this.email = str3;
        this.whatsapp = str4;
    }

    public /* synthetic */ UserPreferenceItem(String str, String str2, String str3, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4);
    }

    public static /* synthetic */ UserPreferenceItem copy$default(UserPreferenceItem userPreferenceItem, String str, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = userPreferenceItem.phone;
        }
        if ((i2 & 2) != 0) {
            str2 = userPreferenceItem.sms;
        }
        if ((i2 & 4) != 0) {
            str3 = userPreferenceItem.email;
        }
        if ((i2 & 8) != 0) {
            str4 = userPreferenceItem.whatsapp;
        }
        return userPreferenceItem.copy(str, str2, str3, str4);
    }

    @Nullable
    public final String component1() {
        return this.phone;
    }

    @Nullable
    public final String component2() {
        return this.sms;
    }

    @Nullable
    public final String component3() {
        return this.email;
    }

    @Nullable
    public final String component4() {
        return this.whatsapp;
    }

    @NotNull
    public final UserPreferenceItem copy(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        return new UserPreferenceItem(str, str2, str3, str4);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UserPreferenceItem) {
            UserPreferenceItem userPreferenceItem = (UserPreferenceItem) obj;
            return Intrinsics.areEqual(this.phone, userPreferenceItem.phone) && Intrinsics.areEqual(this.sms, userPreferenceItem.sms) && Intrinsics.areEqual(this.email, userPreferenceItem.email) && Intrinsics.areEqual(this.whatsapp, userPreferenceItem.whatsapp);
        }
        return false;
    }

    @Nullable
    public final String getEmail() {
        return this.email;
    }

    @Nullable
    public final String getPhone() {
        return this.phone;
    }

    @Nullable
    public final String getSms() {
        return this.sms;
    }

    @Nullable
    public final String getWhatsapp() {
        return this.whatsapp;
    }

    public int hashCode() {
        String str = this.phone;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.sms;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.email;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.whatsapp;
        return hashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public final void setEmail(@Nullable String str) {
        this.email = str;
    }

    public final void setPhone(@Nullable String str) {
        this.phone = str;
    }

    public final void setSms(@Nullable String str) {
        this.sms = str;
    }

    public final void setWhatsapp(@Nullable String str) {
        this.whatsapp = str;
    }

    @NotNull
    public String toString() {
        return "UserPreferenceItem(phone=" + this.phone + ", sms=" + this.sms + ", email=" + this.email + ", whatsapp=" + this.whatsapp + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.phone);
        out.writeString(this.sms);
        out.writeString(this.email);
        out.writeString(this.whatsapp);
    }
}
