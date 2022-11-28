package com.psa.mym.mycitroenconnect.api.retrofit;

import androidx.annotation.Nullable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class ApiHolder {
    public ApiInterface apiInterface;

    @Nullable
    @NotNull
    public final ApiInterface get() {
        return getApiInterface();
    }

    @NotNull
    public final ApiInterface getApiInterface() {
        ApiInterface apiInterface = this.apiInterface;
        if (apiInterface != null) {
            return apiInterface;
        }
        Intrinsics.throwUninitializedPropertyAccessException("apiInterface");
        return null;
    }

    public final void set(@NotNull ApiInterface apiInterface) {
        Intrinsics.checkNotNullParameter(apiInterface, "apiInterface");
        setApiInterface(apiInterface);
    }

    public final void setApiInterface(@NotNull ApiInterface apiInterface) {
        Intrinsics.checkNotNullParameter(apiInterface, "<set-?>");
        this.apiInterface = apiInterface;
    }
}
