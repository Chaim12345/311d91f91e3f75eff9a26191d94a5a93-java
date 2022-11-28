package com.psa.mym.mycitroenconnect.model.onboarding;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Parcelize
/* loaded from: classes3.dex */
public final class ChangePassRequest implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<ChangePassRequest> CREATOR = new Creator();
    @NotNull
    private String currentPassword;
    @NotNull
    private String newPassword;
    @NotNull
    private String userName;

    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<ChangePassRequest> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final ChangePassRequest createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new ChangePassRequest(parcel.readString(), parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final ChangePassRequest[] newArray(int i2) {
            return new ChangePassRequest[i2];
        }
    }

    public ChangePassRequest() {
        this("", "", "");
    }

    public ChangePassRequest(@NotNull String userName, @NotNull String currentPassword, @NotNull String newPassword) {
        Intrinsics.checkNotNullParameter(userName, "userName");
        Intrinsics.checkNotNullParameter(currentPassword, "currentPassword");
        Intrinsics.checkNotNullParameter(newPassword, "newPassword");
        this.userName = userName;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public static /* synthetic */ ChangePassRequest copy$default(ChangePassRequest changePassRequest, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = changePassRequest.userName;
        }
        if ((i2 & 2) != 0) {
            str2 = changePassRequest.currentPassword;
        }
        if ((i2 & 4) != 0) {
            str3 = changePassRequest.newPassword;
        }
        return changePassRequest.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.userName;
    }

    @NotNull
    public final String component2() {
        return this.currentPassword;
    }

    @NotNull
    public final String component3() {
        return this.newPassword;
    }

    @NotNull
    public final ChangePassRequest copy(@NotNull String userName, @NotNull String currentPassword, @NotNull String newPassword) {
        Intrinsics.checkNotNullParameter(userName, "userName");
        Intrinsics.checkNotNullParameter(currentPassword, "currentPassword");
        Intrinsics.checkNotNullParameter(newPassword, "newPassword");
        return new ChangePassRequest(userName, currentPassword, newPassword);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ChangePassRequest) {
            ChangePassRequest changePassRequest = (ChangePassRequest) obj;
            return Intrinsics.areEqual(this.userName, changePassRequest.userName) && Intrinsics.areEqual(this.currentPassword, changePassRequest.currentPassword) && Intrinsics.areEqual(this.newPassword, changePassRequest.newPassword);
        }
        return false;
    }

    @NotNull
    public final String getCurrentPassword() {
        return this.currentPassword;
    }

    @NotNull
    public final String getNewPassword() {
        return this.newPassword;
    }

    @NotNull
    public final String getUserName() {
        return this.userName;
    }

    public int hashCode() {
        return (((this.userName.hashCode() * 31) + this.currentPassword.hashCode()) * 31) + this.newPassword.hashCode();
    }

    public final void setCurrentPassword(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.currentPassword = str;
    }

    public final void setNewPassword(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.newPassword = str;
    }

    public final void setUserName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userName = str;
    }

    @NotNull
    public String toString() {
        return "ChangePassRequest(userName=" + this.userName + ", currentPassword=" + this.currentPassword + ", newPassword=" + this.newPassword + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.userName);
        out.writeString(this.currentPassword);
        out.writeString(this.newPassword);
    }
}
