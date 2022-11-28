package com.psa.mym.mycitroenconnect.services;

import android.app.Activity;
import android.content.Context;
import com.google.gson.Gson;
import com.psa.mym.mycitroenconnect.model.CommonErrorResponse;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.FailResponse;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.event_bus.GlobalBusUtil;
import com.psa.mym.mycitroenconnect.utils.http_errors.HttpErrorUtil;
import java.io.IOException;
import java.net.SocketTimeoutException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public class BaseService {
    public static /* synthetic */ void postCommonErrorResponse$default(BaseService baseService, String str, String str2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: postCommonErrorResponse");
        }
        if ((i2 & 2) != 0) {
            str2 = "";
        }
        baseService.postCommonErrorResponse(str, str2);
    }

    public static /* synthetic */ void postErrorResponse$default(BaseService baseService, String str, int i2, String str2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: postErrorResponse");
        }
        if ((i3 & 4) != 0) {
            str2 = "";
        }
        baseService.postErrorResponse(str, i2, str2);
    }

    public static /* synthetic */ void postHttpErrorResponse$default(BaseService baseService, Activity activity, int i2, String str, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: postHttpErrorResponse");
        }
        if ((i3 & 4) != 0) {
            str = "";
        }
        baseService.postHttpErrorResponse(activity, i2, str);
    }

    public static /* synthetic */ void postResponse$default(BaseService baseService, Object obj, boolean z, int i2, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: postResponse");
        }
        if ((i2 & 2) != 0) {
            z = true;
        }
        baseService.postResponse(obj, z);
    }

    public final void postCommonErrorResponse(@NotNull String msg, @NotNull String api) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(api, "api");
        CommonErrorResponse registerErrorResponse = (CommonErrorResponse) new Gson().fromJson(msg, (Class<Object>) CommonErrorResponse.class);
        registerErrorResponse.setApiName(api);
        Intrinsics.checkNotNullExpressionValue(registerErrorResponse, "registerErrorResponse");
        postResponse$default(this, registerErrorResponse, false, 2, null);
    }

    public final void postErrorResponse(@NotNull String msg, int i2, @NotNull String api) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(api, "api");
        Object fromJson = new Gson().fromJson(msg, (Class<Object>) String.class);
        Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(\n       …:class.java\n            )");
        postResponse$default(this, new ErrorResponse((String) fromJson, i2, api), false, 2, null);
    }

    public final void postHttpErrorResponse(@NotNull Activity activity, int i2, @NotNull String api) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(api, "api");
        postResponse$default(this, new ErrorResponse(HttpErrorUtil.getErrorMessageFromErrorCode$default(new HttpErrorUtil(i2, activity), null, 1, null), i2, api), false, 2, null);
    }

    public final void postResponse(@NotNull Object event, boolean z) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (z) {
            AppUtil.Companion.dismissDialog();
        }
        GlobalBusUtil.Companion.optBus().post(event);
    }

    public final void showFailureToast(@NotNull Context context, @NotNull Throwable t2) {
        String string;
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(t2, "t");
        AppUtil.Companion.dismissDialog();
        if (t2 instanceof SocketTimeoutException) {
            string = context.getString(R.string.time_out);
            str = "context.getString(R.string.time_out)";
        } else if (!(t2 instanceof IOException)) {
            return;
        } else {
            string = context.getString(R.string.internet_issue);
            str = "context.getString(R.string.internet_issue)";
        }
        Intrinsics.checkNotNullExpressionValue(string, str);
        ExtensionsKt.showToast(context, string);
    }

    public final void showHttpErrorToast(int i2, @NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        AppUtil.Companion.dismissDialog();
        HttpErrorUtil.handleErrorCode$default(new HttpErrorUtil(i2, activity), null, 1, null);
    }

    public final void showResponseUnsuccessful(int i2, @NotNull String errorBody, @NotNull String apiName, @NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(errorBody, "errorBody");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (i2 != 400) {
            showHttpErrorToast(i2, activity);
            return;
        }
        try {
            FailResponse errorResponse = (FailResponse) new Gson().fromJson(errorBody, (Class<Object>) FailResponse.class);
            errorResponse.setApiName(apiName);
            Intrinsics.checkNotNullExpressionValue(errorResponse, "errorResponse");
            postResponse$default(this, errorResponse, false, 2, null);
        } catch (Exception unused) {
            postErrorResponse(errorBody, i2, apiName);
        }
    }
}
