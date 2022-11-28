package com.psa.mym.mycitroenconnect.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FailResponse {
    @NotNull
    private String apiName;
    @NotNull
    private String message;
    private int statusCode;

    public FailResponse() {
        this(null, 0, null, 7, null);
    }

    public FailResponse(@NotNull String message, int i2, @NotNull String apiName) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        this.message = message;
        this.statusCode = i2;
        this.apiName = apiName;
    }

    public /* synthetic */ FailResponse(String str, int i2, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "" : str, (i3 & 2) != 0 ? 0 : i2, (i3 & 4) != 0 ? "" : str2);
    }

    public static /* synthetic */ FailResponse copy$default(FailResponse failResponse, String str, int i2, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = failResponse.message;
        }
        if ((i3 & 2) != 0) {
            i2 = failResponse.statusCode;
        }
        if ((i3 & 4) != 0) {
            str2 = failResponse.apiName;
        }
        return failResponse.copy(str, i2, str2);
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
        return this.apiName;
    }

    @NotNull
    public final FailResponse copy(@NotNull String message, int i2, @NotNull String apiName) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        return new FailResponse(message, i2, apiName);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FailResponse) {
            FailResponse failResponse = (FailResponse) obj;
            return Intrinsics.areEqual(this.message, failResponse.message) && this.statusCode == failResponse.statusCode && Intrinsics.areEqual(this.apiName, failResponse.apiName);
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

    public int hashCode() {
        return (((this.message.hashCode() * 31) + Integer.hashCode(this.statusCode)) * 31) + this.apiName.hashCode();
    }

    public final void setApiName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.apiName = str;
    }

    public final void setMessage(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.message = str;
    }

    public final void setStatusCode(int i2) {
        this.statusCode = i2;
    }

    @NotNull
    public String toString() {
        return "FailResponse(message=" + this.message + ", statusCode=" + this.statusCode + ", apiName=" + this.apiName + ')';
    }
}
