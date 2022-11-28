package com.psa.mym.mycitroenconnect.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ValidationErrorResponse {
    @SerializedName("errorCode")
    private int errorCode;
    @SerializedName("errorList")
    @NotNull
    private List<String> errorList;

    public ValidationErrorResponse() {
        this(0, null, 3, null);
    }

    public ValidationErrorResponse(int i2, @NotNull List<String> errorList) {
        Intrinsics.checkNotNullParameter(errorList, "errorList");
        this.errorCode = i2;
        this.errorList = errorList;
    }

    public /* synthetic */ ValidationErrorResponse(int i2, List list, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i2, (i3 & 2) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ValidationErrorResponse copy$default(ValidationErrorResponse validationErrorResponse, int i2, List list, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = validationErrorResponse.errorCode;
        }
        if ((i3 & 2) != 0) {
            list = validationErrorResponse.errorList;
        }
        return validationErrorResponse.copy(i2, list);
    }

    public final int component1() {
        return this.errorCode;
    }

    @NotNull
    public final List<String> component2() {
        return this.errorList;
    }

    @NotNull
    public final ValidationErrorResponse copy(int i2, @NotNull List<String> errorList) {
        Intrinsics.checkNotNullParameter(errorList, "errorList");
        return new ValidationErrorResponse(i2, errorList);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ValidationErrorResponse) {
            ValidationErrorResponse validationErrorResponse = (ValidationErrorResponse) obj;
            return this.errorCode == validationErrorResponse.errorCode && Intrinsics.areEqual(this.errorList, validationErrorResponse.errorList);
        }
        return false;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    @NotNull
    public final List<String> getErrorList() {
        return this.errorList;
    }

    public int hashCode() {
        return (Integer.hashCode(this.errorCode) * 31) + this.errorList.hashCode();
    }

    public final void setErrorCode(int i2) {
        this.errorCode = i2;
    }

    public final void setErrorList(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.errorList = list;
    }

    @NotNull
    public String toString() {
        return "ValidationErrorResponse(errorCode=" + this.errorCode + ", errorList=" + this.errorList + ')';
    }
}
