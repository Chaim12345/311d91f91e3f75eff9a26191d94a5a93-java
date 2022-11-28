package com.psa.mym.mycitroenconnect.api.retrofit;

import android.content.Context;
import com.psa.mym.mycitroenconnect.App;
import com.psa.mym.mycitroenconnect.model.Token;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCars;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class ProfileTokenAuthenticator implements Authenticator {
    @NotNull
    private ApiHolder apiHolder;
    @NotNull
    private Context context;

    public ProfileTokenAuthenticator(@NotNull Context context, @NotNull ApiHolder apiHolder) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(apiHolder, "apiHolder");
        this.context = context;
        this.apiHolder = apiHolder;
    }

    @Override // okhttp3.Authenticator
    @Nullable
    public Request authenticate(@Nullable Route route, @NotNull Response response) {
        boolean isBlank;
        Token token;
        Context context;
        Intrinsics.checkNotNullParameter(response, "response");
        synchronized (this) {
            if (ExtensionsKt.getResponseCount(response) >= 5) {
                return FallBackMechanismRequest.INSTANCE.getPermissionAPICallBackRequest$app_preprodQa(this.context, this.apiHolder, response, false);
            }
            SharedPref.Companion companion = SharedPref.Companion;
            String mobileNumber = companion.getMobileNumber(this.context);
            Logger logger = Logger.INSTANCE;
            logger.e("Token: " + companion.getSecondaryUserAuthToken(this.context));
            if (!companion.isValidSecondaryUserToken(this.context) && mobileNumber != null) {
                if (mobileNumber.length() > 0) {
                    isBlank = StringsKt__StringsJVMKt.isBlank(mobileNumber);
                    if ((!isBlank) && mobileNumber.length() > 5) {
                        ApiInterface apiInterface = this.apiHolder.get();
                        StringBuilder sb = new StringBuilder();
                        sb.append("91");
                        String substring = mobileNumber.substring(3, mobileNumber.length());
                        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb.append(substring);
                        retrofit2.Response<MyCars> execute = apiInterface.getMyCarList(sb.toString()).execute();
                        Intrinsics.checkNotNullExpressionValue(execute, "apiHolder\n              …               .execute()");
                        logger.e("Response Code: " + execute.code());
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Response Body: ");
                        MyCars body = execute.body();
                        if (body == null) {
                            body = "";
                        } else {
                            Intrinsics.checkNotNullExpressionValue(body, "retrofitResponse.body() ?: \"\"");
                        }
                        sb2.append(body);
                        logger.e(sb2.toString());
                        int code = execute.code();
                        String str = null;
                        if (code != 200 && code != 201) {
                            if (code == 400) {
                                context = this.context;
                            } else if (code != 401) {
                                return response.request().newBuilder().header("Authorization", companion.getSecondaryUserAuthToken(this.context)).build();
                            } else {
                                context = this.context;
                            }
                            companion.logoutFromApp(context);
                            return null;
                        }
                        MyCars body2 = execute.body();
                        Intrinsics.checkNotNull(body2);
                        MyCars myCars = body2;
                        if (myCars.size() > 0) {
                            String userType = myCars.get(0).getUserType();
                            if (userType != null) {
                                str = userType.toLowerCase(Locale.ROOT);
                                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                            }
                            if (Intrinsics.areEqual(str, "g") && (token = myCars.get(0).getToken()) != null) {
                                companion.setSecondaryUserTokenDetails(App.Companion.getInstance(), token);
                            }
                        }
                        return response.request().newBuilder().header("Authorization", companion.getSecondaryUserAuthToken(this.context)).build();
                    }
                }
            }
            return response.request().newBuilder().header("Authorization", companion.getSecondaryUserAuthToken(this.context)).build();
        }
    }
}
