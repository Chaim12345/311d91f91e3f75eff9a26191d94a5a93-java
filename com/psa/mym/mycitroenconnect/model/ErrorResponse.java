package com.psa.mym.mycitroenconnect.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public class ErrorResponse {
    @NotNull
    private String apiName;
    @NotNull
    private String msg;
    private int statusCode;

    public ErrorResponse() {
        this(null, 0, null, 7, null);
    }

    public ErrorResponse(@NotNull String msg, int i2, @NotNull String apiName) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        this.msg = msg;
        this.statusCode = i2;
        this.apiName = apiName;
    }

    public /* synthetic */ ErrorResponse(String str, int i2, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "" : str, (i3 & 2) != 0 ? 0 : i2, (i3 & 4) != 0 ? "" : str2);
    }

    @NotNull
    public final String getApiName() {
        return this.apiName;
    }

    @NotNull
    public final String getMsg() {
        return this.msg;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public final void setApiName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.apiName = str;
    }

    public final void setMsg(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.msg = str;
    }

    public final void setStatusCode(int i2) {
        this.statusCode = i2;
    }
}
