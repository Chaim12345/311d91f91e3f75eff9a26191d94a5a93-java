package com.psa.mym.mycitroenconnect.utils.http_errors;

import android.app.Activity;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.event_bus.GlobalBusUtil;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class HttpErrorUtil {
    @NotNull
    private Activity mActivity;
    private int resCode;

    public HttpErrorUtil(int i2, @NotNull Activity mActivity) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        this.resCode = i2;
        this.mActivity = mActivity;
    }

    public static /* synthetic */ String getErrorMessageFromErrorCode$default(HttpErrorUtil httpErrorUtil, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = null;
        }
        return httpErrorUtil.getErrorMessageFromErrorCode(str);
    }

    public static /* synthetic */ void handleErrorCode$default(HttpErrorUtil httpErrorUtil, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = null;
        }
        httpErrorUtil.handleErrorCode(str);
    }

    @NotNull
    public final String getErrorMessageFromErrorCode(@Nullable String str) {
        String string;
        String str2;
        int i2 = this.resCode;
        if (i2 == 400) {
            string = this.mActivity.getString(R.string.api_not_found);
            str2 = "mActivity.getString(R.string.api_not_found)";
        } else if (i2 == 401) {
            string = this.mActivity.getString(R.string.unauthorized);
            str2 = "mActivity.getString(R.string.unauthorized)";
        } else if (i2 != 408) {
            if (i2 == 412) {
                if (str != null) {
                    GlobalBusUtil.Companion.optBus().post(str);
                }
                return "";
            }
            if (i2 != 500) {
                switch (i2) {
                    case 403:
                        string = this.mActivity.getString(R.string.forbidden);
                        str2 = "mActivity.getString(R.string.forbidden)";
                        break;
                    case 404:
                        string = this.mActivity.getString(R.string.resource_not_found);
                        str2 = "mActivity.getString(R.string.resource_not_found)";
                        break;
                    case 405:
                        string = this.mActivity.getString(R.string.method_not_allowed);
                        str2 = "mActivity.getString(R.string.method_not_allowed)";
                        break;
                    default:
                        switch (i2) {
                            case 503:
                                break;
                            case 504:
                                string = this.mActivity.getString(R.string.gateway_timeout);
                                str2 = "mActivity.getString(R.string.gateway_timeout)";
                                break;
                            case 505:
                                string = this.mActivity.getString(R.string.http_not_supported);
                                str2 = "mActivity.getString(R.string.http_not_supported)";
                                break;
                            default:
                                String string2 = this.mActivity.getString(R.string.unknown_error_occurred);
                                Intrinsics.checkNotNullExpressionValue(string2, "mActivity.getString(R.st…g.unknown_error_occurred)");
                                Logger logger = Logger.INSTANCE;
                                logger.e("Error Code:" + this.resCode + " : errMsg: " + string2);
                                return string2;
                        }
                }
            }
            String string3 = this.mActivity.getString(R.string.err_server_not_reachable);
            Intrinsics.checkNotNullExpressionValue(string3, "mActivity.getString(R.st…err_server_not_reachable)");
            return string3;
        } else {
            string = this.mActivity.getString(R.string.request_time_out);
            str2 = "mActivity.getString(R.string.request_time_out)";
        }
        Intrinsics.checkNotNullExpressionValue(string, str2);
        return string;
    }

    public final void handleErrorCode(@Nullable String str) {
        Activity activity;
        String string;
        String str2;
        int i2 = this.resCode;
        if (i2 == 400) {
            activity = this.mActivity;
            string = activity.getString(R.string.api_not_found);
            str2 = "mActivity.getString(R.string.api_not_found)";
        } else if (i2 == 401) {
            activity = this.mActivity;
            string = activity.getString(R.string.unauthorized);
            str2 = "mActivity.getString(R.string.unauthorized)";
        } else if (i2 == 408) {
            activity = this.mActivity;
            string = activity.getString(R.string.request_time_out);
            str2 = "mActivity.getString(R.string.request_time_out)";
        } else if (i2 == 412) {
            if (str != null) {
                GlobalBusUtil.Companion.optBus().post(str);
                return;
            }
            return;
        } else {
            str2 = "mActivity.getString(R.st…err_server_not_reachable)";
            if (i2 != 500) {
                switch (i2) {
                    case 403:
                        activity = this.mActivity;
                        string = activity.getString(R.string.forbidden);
                        str2 = "mActivity.getString(R.string.forbidden)";
                        break;
                    case 404:
                        activity = this.mActivity;
                        string = activity.getString(R.string.resource_not_found);
                        str2 = "mActivity.getString(R.string.resource_not_found)";
                        break;
                    case 405:
                        activity = this.mActivity;
                        string = activity.getString(R.string.method_not_allowed);
                        str2 = "mActivity.getString(R.string.method_not_allowed)";
                        break;
                    default:
                        switch (i2) {
                            case 503:
                                break;
                            case 504:
                                activity = this.mActivity;
                                string = activity.getString(R.string.gateway_timeout);
                                str2 = "mActivity.getString(R.string.gateway_timeout)";
                                break;
                            case 505:
                                activity = this.mActivity;
                                string = activity.getString(R.string.http_not_supported);
                                str2 = "mActivity.getString(R.string.http_not_supported)";
                                break;
                            default:
                                Activity activity2 = this.mActivity;
                                String string2 = activity2.getString(R.string.unknown_error_occurred);
                                Intrinsics.checkNotNullExpressionValue(string2, "mActivity.getString(R.st…g.unknown_error_occurred)");
                                ExtensionsKt.showToast(activity2, string2);
                                Logger logger = Logger.INSTANCE;
                                logger.e("Error Code:" + this.resCode + " : errMsg: Unknown error occurred");
                                return;
                        }
                }
            }
            activity = this.mActivity;
            string = activity.getString(R.string.err_server_not_reachable);
        }
        Intrinsics.checkNotNullExpressionValue(string, str2);
        ExtensionsKt.showToast(activity, string);
    }
}
