package com.psa.mym.mycitroenconnect.api.retrofit;

import android.content.Context;
import com.psa.mym.mycitroenconnect.model.Token;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCars;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class FallBackMechanismRequest {
    @NotNull
    public static final FallBackMechanismRequest INSTANCE = new FallBackMechanismRequest();

    private FallBackMechanismRequest() {
    }

    @Nullable
    public final Request getPermissionAPICallBackRequest$app_preprodQa(@NotNull Context context, @NotNull ApiHolder apiHolder, @NotNull Response response, boolean z) {
        boolean isBlank;
        String primaryUserAuthToken;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(apiHolder, "apiHolder");
        Intrinsics.checkNotNullParameter(response, "response");
        Logger logger = Logger.INSTANCE;
        logger.e("Permission API Called");
        StringBuilder sb = new StringBuilder();
        sb.append("Token: ");
        SharedPref.Companion companion = SharedPref.Companion;
        sb.append(companion.getPrimaryUserToken(context));
        logger.e(sb.toString());
        String mobileNumber = companion.getMobileNumber(context);
        String str = null;
        if (mobileNumber.length() > 0) {
            isBlank = StringsKt__StringsJVMKt.isBlank(mobileNumber);
            if ((!isBlank) && mobileNumber.length() > 4) {
                ApiInterface apiInterface = apiHolder.get();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("91");
                String substring = mobileNumber.substring(3, mobileNumber.length());
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                sb2.append(substring);
                retrofit2.Response<MyCars> execute = apiInterface.getMyCarList(sb2.toString()).execute();
                Intrinsics.checkNotNullExpressionValue(execute, "apiHolder\n              …               .execute()");
                logger.e("Permission API Response Code: " + execute.code());
                logger.e("Permission API Response Body: " + execute.body());
                int code = execute.code();
                if (code == 200 || code == 201) {
                    MyCars body = execute.body();
                    Intrinsics.checkNotNull(body);
                    MyCars myCars = body;
                    if (myCars.size() > 0) {
                        String userType = myCars.get(0).getUserType();
                        if (userType != null) {
                            str = userType.toLowerCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                        }
                        if (Intrinsics.areEqual(str, "g")) {
                            Token token = myCars.get(0).getToken();
                            if (token != null) {
                                companion.setSecondaryUserTokenDetails(context, token);
                            }
                            myCars.remove(0);
                        }
                    }
                    companion.setVinTokenDetails(context, myCars);
                    companion.setTokenDetails(context, companion.getVinNumber(context));
                    primaryUserAuthToken = z ? companion.getPrimaryUserAuthToken(context) : companion.getSecondaryUserAuthToken(context);
                    logger.e("New Token is: " + primaryUserAuthToken);
                } else if (code == 400) {
                    AppUtil.Companion.showRefreshTokenErrorDialog(context);
                } else if (code != 401) {
                    primaryUserAuthToken = z ? companion.getPrimaryUserAuthToken(context) : companion.getSecondaryUserAuthToken(context);
                } else {
                    companion.logoutFromApp(context);
                }
                return response.request().newBuilder().header("Authorization", primaryUserAuthToken).build();
            }
        }
        return null;
    }
}
