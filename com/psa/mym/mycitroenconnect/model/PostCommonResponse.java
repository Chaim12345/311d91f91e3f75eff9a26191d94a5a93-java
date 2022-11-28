package com.psa.mym.mycitroenconnect.model;

import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class PostCommonResponse {
    @NotNull
    private String apiName;
    @SerializedName(AppConstants.ARG_MESSAGE)
    @NotNull
    private String message;
    @SerializedName("statusCode")
    private final int statusCode;
    @SerializedName("Success")
    @NotNull
    private String success;

    public PostCommonResponse() {
        this(null, 0, null, null, 15, null);
    }

    public PostCommonResponse(@NotNull String message, int i2, @NotNull String success, @NotNull String apiName) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        this.message = message;
        this.statusCode = i2;
        this.success = success;
        this.apiName = apiName;
    }

    public /* synthetic */ PostCommonResponse(String str, int i2, String str2, String str3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "" : str, (i3 & 2) != 0 ? 0 : i2, (i3 & 4) != 0 ? "" : str2, (i3 & 8) != 0 ? "" : str3);
    }

    public static /* synthetic */ PostCommonResponse copy$default(PostCommonResponse postCommonResponse, String str, int i2, String str2, String str3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = postCommonResponse.message;
        }
        if ((i3 & 2) != 0) {
            i2 = postCommonResponse.statusCode;
        }
        if ((i3 & 4) != 0) {
            str2 = postCommonResponse.success;
        }
        if ((i3 & 8) != 0) {
            str3 = postCommonResponse.apiName;
        }
        return postCommonResponse.copy(str, i2, str2, str3);
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
    public final String component4() {
        return this.apiName;
    }

    @NotNull
    public final PostCommonResponse copy(@NotNull String message, int i2, @NotNull String success, @NotNull String apiName) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        return new PostCommonResponse(message, i2, success, apiName);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PostCommonResponse) {
            PostCommonResponse postCommonResponse = (PostCommonResponse) obj;
            return Intrinsics.areEqual(this.message, postCommonResponse.message) && this.statusCode == postCommonResponse.statusCode && Intrinsics.areEqual(this.success, postCommonResponse.success) && Intrinsics.areEqual(this.apiName, postCommonResponse.apiName);
        }
        return false;
    }

    @NotNull
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
        return (((((this.message.hashCode() * 31) + Integer.hashCode(this.statusCode)) * 31) + this.success.hashCode()) * 31) + this.apiName.hashCode();
    }

    public final void setApiName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.apiName = str;
    }

    public final void setMessage(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.message = str;
    }

    public final void setSuccess(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.success = str;
    }

    @NotNull
    public String toString() {
        return "PostCommonResponse(message=" + this.message + ", statusCode=" + this.statusCode + ", success=" + this.success + ", apiName=" + this.apiName + ')';
    }
}
