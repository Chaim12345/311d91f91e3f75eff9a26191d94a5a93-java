package com.psa.mym.mycitroenconnect.model.secondary_user;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Parcelize
/* loaded from: classes3.dex */
public final class SecondaryUser implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<SecondaryUser> CREATOR = new Creator();
    @SerializedName("inviteStatus")
    @NotNull
    private String inviteStatus;
    @SerializedName("userFullName")
    @NotNull
    private String secondaryUserFullName;
    @SerializedName("secondaryUsername")
    @NotNull
    private String secondaryUsername;
    @SerializedName("sharedVehicle")
    @NotNull
    private List<SharedVehicle> sharedVehicle;

    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<SecondaryUser> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final SecondaryUser createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            for (int i2 = 0; i2 != readInt; i2++) {
                arrayList.add(SharedVehicle.CREATOR.createFromParcel(parcel));
            }
            return new SecondaryUser(readString, readString2, readString3, arrayList);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final SecondaryUser[] newArray(int i2) {
            return new SecondaryUser[i2];
        }
    }

    public SecondaryUser(@NotNull String inviteStatus, @NotNull String secondaryUsername, @NotNull String secondaryUserFullName, @NotNull List<SharedVehicle> sharedVehicle) {
        Intrinsics.checkNotNullParameter(inviteStatus, "inviteStatus");
        Intrinsics.checkNotNullParameter(secondaryUsername, "secondaryUsername");
        Intrinsics.checkNotNullParameter(secondaryUserFullName, "secondaryUserFullName");
        Intrinsics.checkNotNullParameter(sharedVehicle, "sharedVehicle");
        this.inviteStatus = inviteStatus;
        this.secondaryUsername = secondaryUsername;
        this.secondaryUserFullName = secondaryUserFullName;
        this.sharedVehicle = sharedVehicle;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SecondaryUser copy$default(SecondaryUser secondaryUser, String str, String str2, String str3, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = secondaryUser.inviteStatus;
        }
        if ((i2 & 2) != 0) {
            str2 = secondaryUser.secondaryUsername;
        }
        if ((i2 & 4) != 0) {
            str3 = secondaryUser.secondaryUserFullName;
        }
        if ((i2 & 8) != 0) {
            list = secondaryUser.sharedVehicle;
        }
        return secondaryUser.copy(str, str2, str3, list);
    }

    @NotNull
    public final String component1() {
        return this.inviteStatus;
    }

    @NotNull
    public final String component2() {
        return this.secondaryUsername;
    }

    @NotNull
    public final String component3() {
        return this.secondaryUserFullName;
    }

    @NotNull
    public final List<SharedVehicle> component4() {
        return this.sharedVehicle;
    }

    @NotNull
    public final SecondaryUser copy(@NotNull String inviteStatus, @NotNull String secondaryUsername, @NotNull String secondaryUserFullName, @NotNull List<SharedVehicle> sharedVehicle) {
        Intrinsics.checkNotNullParameter(inviteStatus, "inviteStatus");
        Intrinsics.checkNotNullParameter(secondaryUsername, "secondaryUsername");
        Intrinsics.checkNotNullParameter(secondaryUserFullName, "secondaryUserFullName");
        Intrinsics.checkNotNullParameter(sharedVehicle, "sharedVehicle");
        return new SecondaryUser(inviteStatus, secondaryUsername, secondaryUserFullName, sharedVehicle);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SecondaryUser) {
            SecondaryUser secondaryUser = (SecondaryUser) obj;
            return Intrinsics.areEqual(this.inviteStatus, secondaryUser.inviteStatus) && Intrinsics.areEqual(this.secondaryUsername, secondaryUser.secondaryUsername) && Intrinsics.areEqual(this.secondaryUserFullName, secondaryUser.secondaryUserFullName) && Intrinsics.areEqual(this.sharedVehicle, secondaryUser.sharedVehicle);
        }
        return false;
    }

    @NotNull
    public final String getInviteStatus() {
        return this.inviteStatus;
    }

    @NotNull
    public final String getSecondaryUserFullName() {
        return this.secondaryUserFullName;
    }

    @NotNull
    public final String getSecondaryUsername() {
        return this.secondaryUsername;
    }

    @NotNull
    public final List<SharedVehicle> getSharedVehicle() {
        return this.sharedVehicle;
    }

    public int hashCode() {
        return (((((this.inviteStatus.hashCode() * 31) + this.secondaryUsername.hashCode()) * 31) + this.secondaryUserFullName.hashCode()) * 31) + this.sharedVehicle.hashCode();
    }

    public final void setInviteStatus(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.inviteStatus = str;
    }

    public final void setSecondaryUserFullName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.secondaryUserFullName = str;
    }

    public final void setSecondaryUsername(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.secondaryUsername = str;
    }

    public final void setSharedVehicle(@NotNull List<SharedVehicle> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.sharedVehicle = list;
    }

    @NotNull
    public String toString() {
        return "SecondaryUser(inviteStatus=" + this.inviteStatus + ", secondaryUsername=" + this.secondaryUsername + ", secondaryUserFullName=" + this.secondaryUserFullName + ", sharedVehicle=" + this.sharedVehicle + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.inviteStatus);
        out.writeString(this.secondaryUsername);
        out.writeString(this.secondaryUserFullName);
        List<SharedVehicle> list = this.sharedVehicle;
        out.writeInt(list.size());
        for (SharedVehicle sharedVehicle : list) {
            sharedVehicle.writeToParcel(out, i2);
        }
    }
}
