package com.psa.mym.mycitroenconnect.model.onboarding;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Parcelize
/* loaded from: classes3.dex */
public final class RegisterUserResponse implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<RegisterUserResponse> CREATOR = new Creator();
    @Nullable
    private String fullHash;
    @Nullable
    private String isGuestUser;
    @Nullable
    private String message;
    @Nullable
    private String mobileNum;
    @Nullable
    private Integer statusCode;

    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<RegisterUserResponse> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final RegisterUserResponse createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new RegisterUserResponse(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final RegisterUserResponse[] newArray(int i2) {
            return new RegisterUserResponse[i2];
        }
    }

    public RegisterUserResponse() {
        this(null, null, null, null, null, 31, null);
    }

    public RegisterUserResponse(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Integer num, @Nullable String str4) {
        this.mobileNum = str;
        this.isGuestUser = str2;
        this.message = str3;
        this.statusCode = num;
        this.fullHash = str4;
    }

    public /* synthetic */ RegisterUserResponse(String str, String str2, String str3, Integer num, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : num, (i2 & 16) != 0 ? null : str4);
    }

    public static /* synthetic */ RegisterUserResponse copy$default(RegisterUserResponse registerUserResponse, String str, String str2, String str3, Integer num, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = registerUserResponse.mobileNum;
        }
        if ((i2 & 2) != 0) {
            str2 = registerUserResponse.isGuestUser;
        }
        String str5 = str2;
        if ((i2 & 4) != 0) {
            str3 = registerUserResponse.message;
        }
        String str6 = str3;
        if ((i2 & 8) != 0) {
            num = registerUserResponse.statusCode;
        }
        Integer num2 = num;
        if ((i2 & 16) != 0) {
            str4 = registerUserResponse.fullHash;
        }
        return registerUserResponse.copy(str, str5, str6, num2, str4);
    }

    @Nullable
    public final String component1() {
        return this.mobileNum;
    }

    @Nullable
    public final String component2() {
        return this.isGuestUser;
    }

    @Nullable
    public final String component3() {
        return this.message;
    }

    @Nullable
    public final Integer component4() {
        return this.statusCode;
    }

    @Nullable
    public final String component5() {
        return this.fullHash;
    }

    @NotNull
    public final RegisterUserResponse copy(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Integer num, @Nullable String str4) {
        return new RegisterUserResponse(str, str2, str3, num, str4);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RegisterUserResponse) {
            RegisterUserResponse registerUserResponse = (RegisterUserResponse) obj;
            return Intrinsics.areEqual(this.mobileNum, registerUserResponse.mobileNum) && Intrinsics.areEqual(this.isGuestUser, registerUserResponse.isGuestUser) && Intrinsics.areEqual(this.message, registerUserResponse.message) && Intrinsics.areEqual(this.statusCode, registerUserResponse.statusCode) && Intrinsics.areEqual(this.fullHash, registerUserResponse.fullHash);
        }
        return false;
    }

    @Nullable
    public final String getFullHash() {
        return this.fullHash;
    }

    @Nullable
    public final String getMessage() {
        return this.message;
    }

    @Nullable
    public final String getMobileNum() {
        return this.mobileNum;
    }

    @Nullable
    public final Integer getStatusCode() {
        return this.statusCode;
    }

    public int hashCode() {
        String str = this.mobileNum;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.isGuestUser;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.message;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Integer num = this.statusCode;
        int hashCode4 = (hashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        String str4 = this.fullHash;
        return hashCode4 + (str4 != null ? str4.hashCode() : 0);
    }

    @Nullable
    public final String isGuestUser() {
        return this.isGuestUser;
    }

    public final void setFullHash(@Nullable String str) {
        this.fullHash = str;
    }

    public final void setGuestUser(@Nullable String str) {
        this.isGuestUser = str;
    }

    public final void setMessage(@Nullable String str) {
        this.message = str;
    }

    public final void setMobileNum(@Nullable String str) {
        this.mobileNum = str;
    }

    public final void setStatusCode(@Nullable Integer num) {
        this.statusCode = num;
    }

    @NotNull
    public String toString() {
        return "RegisterUserResponse(mobileNum=" + this.mobileNum + ", isGuestUser=" + this.isGuestUser + ", message=" + this.message + ", statusCode=" + this.statusCode + ", fullHash=" + this.fullHash + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        int intValue;
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.mobileNum);
        out.writeString(this.isGuestUser);
        out.writeString(this.message);
        Integer num = this.statusCode;
        if (num == null) {
            intValue = 0;
        } else {
            out.writeInt(1);
            intValue = num.intValue();
        }
        out.writeInt(intValue);
        out.writeString(this.fullHash);
    }
}
