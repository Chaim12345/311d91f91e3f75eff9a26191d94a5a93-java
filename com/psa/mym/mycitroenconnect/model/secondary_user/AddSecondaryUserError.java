package com.psa.mym.mycitroenconnect.model.secondary_user;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class AddSecondaryUserError {
    @SerializedName(AppConstants.ARG_MESSAGE)
    @NotNull
    private Message message;
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName(FirebaseAnalytics.Param.SUCCESS)
    @NotNull
    private String success;

    public AddSecondaryUserError() {
        this(null, null, 0, 7, null);
    }

    public AddSecondaryUserError(@NotNull String success, @NotNull Message message, int i2) {
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(message, "message");
        this.success = success;
        this.message = message;
        this.statusCode = i2;
    }

    public /* synthetic */ AddSecondaryUserError(String str, Message message, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "" : str, (i3 & 2) != 0 ? new Message(null, 1, null) : message, (i3 & 4) != 0 ? -1 : i2);
    }

    public static /* synthetic */ AddSecondaryUserError copy$default(AddSecondaryUserError addSecondaryUserError, String str, Message message, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = addSecondaryUserError.success;
        }
        if ((i3 & 2) != 0) {
            message = addSecondaryUserError.message;
        }
        if ((i3 & 4) != 0) {
            i2 = addSecondaryUserError.statusCode;
        }
        return addSecondaryUserError.copy(str, message, i2);
    }

    @NotNull
    public final String component1() {
        return this.success;
    }

    @NotNull
    public final Message component2() {
        return this.message;
    }

    public final int component3() {
        return this.statusCode;
    }

    @NotNull
    public final AddSecondaryUserError copy(@NotNull String success, @NotNull Message message, int i2) {
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(message, "message");
        return new AddSecondaryUserError(success, message, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AddSecondaryUserError) {
            AddSecondaryUserError addSecondaryUserError = (AddSecondaryUserError) obj;
            return Intrinsics.areEqual(this.success, addSecondaryUserError.success) && Intrinsics.areEqual(this.message, addSecondaryUserError.message) && this.statusCode == addSecondaryUserError.statusCode;
        }
        return false;
    }

    @NotNull
    public final Message getMessage() {
        return this.message;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    @NotNull
    public final String getSuccess() {
        return this.success;
    }

    public int hashCode() {
        return (((this.success.hashCode() * 31) + this.message.hashCode()) * 31) + Integer.hashCode(this.statusCode);
    }

    public final void setMessage(@NotNull Message message) {
        Intrinsics.checkNotNullParameter(message, "<set-?>");
        this.message = message;
    }

    public final void setStatusCode(int i2) {
        this.statusCode = i2;
    }

    public final void setSuccess(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.success = str;
    }

    @NotNull
    public String toString() {
        return "AddSecondaryUserError(success=" + this.success + ", message=" + this.message + ", statusCode=" + this.statusCode + ')';
    }
}
