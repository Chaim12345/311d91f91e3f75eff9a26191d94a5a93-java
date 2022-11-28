package com.psa.mym.mycitroenconnect.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class CommonErrorResponse {
    @SerializedName("Errors")
    @NotNull
    private List<Errors> Errors;
    @NotNull
    private transient String apiName;

    public CommonErrorResponse(@NotNull List<Errors> Errors, @NotNull String apiName) {
        Intrinsics.checkNotNullParameter(Errors, "Errors");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        this.Errors = Errors;
        this.apiName = apiName;
    }

    public /* synthetic */ CommonErrorResponse(List list, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i2 & 2) != 0 ? "" : str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CommonErrorResponse copy$default(CommonErrorResponse commonErrorResponse, List list, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = commonErrorResponse.Errors;
        }
        if ((i2 & 2) != 0) {
            str = commonErrorResponse.apiName;
        }
        return commonErrorResponse.copy(list, str);
    }

    @NotNull
    public final List<Errors> component1() {
        return this.Errors;
    }

    @NotNull
    public final String component2() {
        return this.apiName;
    }

    @NotNull
    public final CommonErrorResponse copy(@NotNull List<Errors> Errors, @NotNull String apiName) {
        Intrinsics.checkNotNullParameter(Errors, "Errors");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        return new CommonErrorResponse(Errors, apiName);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CommonErrorResponse) {
            CommonErrorResponse commonErrorResponse = (CommonErrorResponse) obj;
            return Intrinsics.areEqual(this.Errors, commonErrorResponse.Errors) && Intrinsics.areEqual(this.apiName, commonErrorResponse.apiName);
        }
        return false;
    }

    @NotNull
    public final String getApiName() {
        return this.apiName;
    }

    @NotNull
    public final List<Errors> getErrors() {
        return this.Errors;
    }

    public int hashCode() {
        return (this.Errors.hashCode() * 31) + this.apiName.hashCode();
    }

    public final void setApiName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.apiName = str;
    }

    public final void setErrors(@NotNull List<Errors> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.Errors = list;
    }

    @NotNull
    public String toString() {
        return "CommonErrorResponse(Errors=" + this.Errors + ", apiName=" + this.apiName + ')';
    }
}
