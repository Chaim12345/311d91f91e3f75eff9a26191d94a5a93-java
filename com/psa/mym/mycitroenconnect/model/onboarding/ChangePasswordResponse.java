package com.psa.mym.mycitroenconnect.model.onboarding;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Parcelize
/* loaded from: classes3.dex */
public final class ChangePasswordResponse implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<ChangePasswordResponse> CREATOR = new Creator();
    @NotNull
    private String fullHash;
    @NotNull
    private String message;
    private int statusCode;
    @NotNull
    private String userName;

    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<ChangePasswordResponse> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final ChangePasswordResponse createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new ChangePasswordResponse(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final ChangePasswordResponse[] newArray(int i2) {
            return new ChangePasswordResponse[i2];
        }
    }

    public ChangePasswordResponse() {
        this("", "", "", -1);
    }

    public ChangePasswordResponse(@NotNull String message, @NotNull String userName, @NotNull String fullHash, int i2) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(userName, "userName");
        Intrinsics.checkNotNullParameter(fullHash, "fullHash");
        this.message = message;
        this.userName = userName;
        this.fullHash = fullHash;
        this.statusCode = i2;
    }

    public static /* synthetic */ ChangePasswordResponse copy$default(ChangePasswordResponse changePasswordResponse, String str, String str2, String str3, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = changePasswordResponse.message;
        }
        if ((i3 & 2) != 0) {
            str2 = changePasswordResponse.userName;
        }
        if ((i3 & 4) != 0) {
            str3 = changePasswordResponse.fullHash;
        }
        if ((i3 & 8) != 0) {
            i2 = changePasswordResponse.statusCode;
        }
        return changePasswordResponse.copy(str, str2, str3, i2);
    }

    @NotNull
    public final String component1() {
        return this.message;
    }

    @NotNull
    public final String component2() {
        return this.userName;
    }

    @NotNull
    public final String component3() {
        return this.fullHash;
    }

    public final int component4() {
        return this.statusCode;
    }

    @NotNull
    public final ChangePasswordResponse copy(@NotNull String message, @NotNull String userName, @NotNull String fullHash, int i2) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(userName, "userName");
        Intrinsics.checkNotNullParameter(fullHash, "fullHash");
        return new ChangePasswordResponse(message, userName, fullHash, i2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ChangePasswordResponse) {
            ChangePasswordResponse changePasswordResponse = (ChangePasswordResponse) obj;
            return Intrinsics.areEqual(this.message, changePasswordResponse.message) && Intrinsics.areEqual(this.userName, changePasswordResponse.userName) && Intrinsics.areEqual(this.fullHash, changePasswordResponse.fullHash) && this.statusCode == changePasswordResponse.statusCode;
        }
        return false;
    }

    @NotNull
    public final String getFullHash() {
        return this.fullHash;
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    @NotNull
    public final String getUserName() {
        return this.userName;
    }

    public int hashCode() {
        return (((((this.message.hashCode() * 31) + this.userName.hashCode()) * 31) + this.fullHash.hashCode()) * 31) + Integer.hashCode(this.statusCode);
    }

    public final void setFullHash(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.fullHash = str;
    }

    public final void setMessage(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.message = str;
    }

    public final void setStatusCode(int i2) {
        this.statusCode = i2;
    }

    public final void setUserName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userName = str;
    }

    @NotNull
    public String toString() {
        return "ChangePasswordResponse(message=" + this.message + ", userName=" + this.userName + ", fullHash=" + this.fullHash + ", statusCode=" + this.statusCode + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.message);
        out.writeString(this.userName);
        out.writeString(this.fullHash);
        out.writeInt(this.statusCode);
    }
}
