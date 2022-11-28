package com.psa.mym.mycitroenconnect.model.secondary_user;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SecondaryUserCommonResponse {
    @Nullable
    private String apiName;
    @SerializedName(AppConstants.ARG_MESSAGE)
    @NotNull
    private final String message;
    @SerializedName("statusCode")
    private final int statusCode;
    @SerializedName(FirebaseAnalytics.Param.SUCCESS)
    @NotNull
    private final String success;

    public SecondaryUserCommonResponse(@NotNull String message, int i2, @NotNull String success, @Nullable String str) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(success, "success");
        this.message = message;
        this.statusCode = i2;
        this.success = success;
        this.apiName = str;
    }

    public /* synthetic */ SecondaryUserCommonResponse(String str, int i2, String str2, String str3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i2, str2, (i3 & 8) != 0 ? null : str3);
    }

    public static /* synthetic */ SecondaryUserCommonResponse copy$default(SecondaryUserCommonResponse secondaryUserCommonResponse, String str, int i2, String str2, String str3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = secondaryUserCommonResponse.message;
        }
        if ((i3 & 2) != 0) {
            i2 = secondaryUserCommonResponse.statusCode;
        }
        if ((i3 & 4) != 0) {
            str2 = secondaryUserCommonResponse.success;
        }
        if ((i3 & 8) != 0) {
            str3 = secondaryUserCommonResponse.apiName;
        }
        return secondaryUserCommonResponse.copy(str, i2, str2, str3);
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

    @Nullable
    public final String component4() {
        return this.apiName;
    }

    @NotNull
    public final SecondaryUserCommonResponse copy(@NotNull String message, int i2, @NotNull String success, @Nullable String str) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(success, "success");
        return new SecondaryUserCommonResponse(message, i2, success, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SecondaryUserCommonResponse) {
            SecondaryUserCommonResponse secondaryUserCommonResponse = (SecondaryUserCommonResponse) obj;
            return Intrinsics.areEqual(this.message, secondaryUserCommonResponse.message) && this.statusCode == secondaryUserCommonResponse.statusCode && Intrinsics.areEqual(this.success, secondaryUserCommonResponse.success) && Intrinsics.areEqual(this.apiName, secondaryUserCommonResponse.apiName);
        }
        return false;
    }

    @Nullable
    public final String getApiName() {
        return this.apiName;
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
        int hashCode = ((((this.message.hashCode() * 31) + Integer.hashCode(this.statusCode)) * 31) + this.success.hashCode()) * 31;
        String str = this.apiName;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public final void setApiName(@Nullable String str) {
        this.apiName = str;
    }

    @NotNull
    public String toString() {
        return "SecondaryUserCommonResponse(message=" + this.message + ", statusCode=" + this.statusCode + ", success=" + this.success + ", apiName=" + this.apiName + ')';
    }
}
