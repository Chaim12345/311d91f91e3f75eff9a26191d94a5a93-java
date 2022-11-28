package com.psa.mym.mycitroenconnect.model;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class DeleteCarResponse {
    @SerializedName(AppConstants.ARG_MESSAGE)
    @NotNull
    private String message;
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName(FirebaseAnalytics.Param.SUCCESS)
    @NotNull
    private String success;

    public DeleteCarResponse() {
        this(null, 0, null, 7, null);
    }

    public DeleteCarResponse(@NotNull String message, int i2, @NotNull String success) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(success, "success");
        this.message = message;
        this.statusCode = i2;
        this.success = success;
    }

    public /* synthetic */ DeleteCarResponse(String str, int i2, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "" : str, (i3 & 2) != 0 ? 0 : i2, (i3 & 4) != 0 ? "" : str2);
    }

    public static /* synthetic */ DeleteCarResponse copy$default(DeleteCarResponse deleteCarResponse, String str, int i2, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = deleteCarResponse.message;
        }
        if ((i3 & 2) != 0) {
            i2 = deleteCarResponse.statusCode;
        }
        if ((i3 & 4) != 0) {
            str2 = deleteCarResponse.success;
        }
        return deleteCarResponse.copy(str, i2, str2);
    }

    @NotNull
    public final String component1() {
        return this.message;
    }

    public final int component2() {
        return this.statusCode;
    }

    @NotNull
    public final String component3() {
        return this.success;
    }

    @NotNull
    public final DeleteCarResponse copy(@NotNull String message, int i2, @NotNull String success) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(success, "success");
        return new DeleteCarResponse(message, i2, success);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DeleteCarResponse) {
            DeleteCarResponse deleteCarResponse = (DeleteCarResponse) obj;
            return Intrinsics.areEqual(this.message, deleteCarResponse.message) && this.statusCode == deleteCarResponse.statusCode && Intrinsics.areEqual(this.success, deleteCarResponse.success);
        }
        return false;
    }

    @NotNull
    public final String getMessage() {
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
        return (((this.message.hashCode() * 31) + Integer.hashCode(this.statusCode)) * 31) + this.success.hashCode();
    }

    public final void setMessage(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.message = str;
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
        return "DeleteCarResponse(message=" + this.message + ", statusCode=" + this.statusCode + ", success=" + this.success + ')';
    }
}
