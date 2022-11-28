package com.psa.mym.mycitroenconnect.model.onboarding;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Parcelize
/* loaded from: classes3.dex */
public final class SetPinResponse implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<SetPinResponse> CREATOR = new Creator();
    @SerializedName(AppConstants.ARG_MESSAGE)
    @NotNull
    private String message;
    @SerializedName("mobileNumber")
    @NotNull
    private String mobileNumber;
    @SerializedName("statusCode")
    private int statusCode;

    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<SetPinResponse> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final SetPinResponse createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new SetPinResponse(parcel.readString(), parcel.readString(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final SetPinResponse[] newArray(int i2) {
            return new SetPinResponse[i2];
        }
    }

    public SetPinResponse(@NotNull String mobileNumber, @NotNull String message, int i2) {
        Intrinsics.checkNotNullParameter(mobileNumber, "mobileNumber");
        Intrinsics.checkNotNullParameter(message, "message");
        this.mobileNumber = mobileNumber;
        this.message = message;
        this.statusCode = i2;
    }

    public static /* synthetic */ SetPinResponse copy$default(SetPinResponse setPinResponse, String str, String str2, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = setPinResponse.mobileNumber;
        }
        if ((i3 & 2) != 0) {
            str2 = setPinResponse.message;
        }
        if ((i3 & 4) != 0) {
            i2 = setPinResponse.statusCode;
        }
        return setPinResponse.copy(str, str2, i2);
    }

    @NotNull
    public final String component1() {
        return this.mobileNumber;
    }

    @NotNull
    public final String component2() {
        return this.message;
    }

    public final int component3() {
        return this.statusCode;
    }

    @NotNull
    public final SetPinResponse copy(@NotNull String mobileNumber, @NotNull String message, int i2) {
        Intrinsics.checkNotNullParameter(mobileNumber, "mobileNumber");
        Intrinsics.checkNotNullParameter(message, "message");
        return new SetPinResponse(mobileNumber, message, i2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SetPinResponse) {
            SetPinResponse setPinResponse = (SetPinResponse) obj;
            return Intrinsics.areEqual(this.mobileNumber, setPinResponse.mobileNumber) && Intrinsics.areEqual(this.message, setPinResponse.message) && this.statusCode == setPinResponse.statusCode;
        }
        return false;
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    @NotNull
    public final String getMobileNumber() {
        return this.mobileNumber;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public int hashCode() {
        return (((this.mobileNumber.hashCode() * 31) + this.message.hashCode()) * 31) + Integer.hashCode(this.statusCode);
    }

    public final void setMessage(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.message = str;
    }

    public final void setMobileNumber(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mobileNumber = str;
    }

    public final void setStatusCode(int i2) {
        this.statusCode = i2;
    }

    @NotNull
    public String toString() {
        return "SetPinResponse(mobileNumber=" + this.mobileNumber + ", message=" + this.message + ", statusCode=" + this.statusCode + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.mobileNumber);
        out.writeString(this.message);
        out.writeInt(this.statusCode);
    }
}
