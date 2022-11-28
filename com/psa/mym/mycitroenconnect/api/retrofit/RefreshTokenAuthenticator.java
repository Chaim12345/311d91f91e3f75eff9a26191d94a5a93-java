package com.psa.mym.mycitroenconnect.api.retrofit;

import android.content.Context;
import com.psa.mym.mycitroenconnect.api.body.onboarding.RefreshTokenBody;
import com.psa.mym.mycitroenconnect.model.Token;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class RefreshTokenAuthenticator implements Authenticator {
    @NotNull
    private ApiHolder apiHolder;
    @NotNull
    private Context context;

    public RefreshTokenAuthenticator(@NotNull Context context, @NotNull ApiHolder apiHolder) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(apiHolder, "apiHolder");
        this.context = context;
        this.apiHolder = apiHolder;
    }

    @Override // okhttp3.Authenticator
    @Nullable
    public Request authenticate(@Nullable Route route, @NotNull Response response) {
        Context context;
        Intrinsics.checkNotNullParameter(response, "response");
        Logger logger = Logger.INSTANCE;
        StringBuilder sb = new StringBuilder();
        sb.append("Token: ");
        SharedPref.Companion companion = SharedPref.Companion;
        sb.append(companion.getPrimaryUserAuthToken(this.context));
        logger.e(sb.toString());
        logger.e("IS valid token? " + companion.isValidToken(this.context));
        synchronized (this) {
            if (!companion.isValidToken(this.context)) {
                if (ExtensionsKt.getResponseCount(response) >= 5) {
                    return FallBackMechanismRequest.INSTANCE.getPermissionAPICallBackRequest$app_preprodQa(this.context, this.apiHolder, response, true);
                }
                RefreshTokenBody refreshTokenBody = new RefreshTokenBody(null, null, null, 7, null);
                refreshTokenBody.setToken(companion.getPrimaryUserRefreshToken(this.context));
                retrofit2.Response<Token> execute = this.apiHolder.get().getRefreshToken(refreshTokenBody).execute();
                Intrinsics.checkNotNullExpressionValue(execute, "apiHolder\n              …               .execute()");
                logger.e("Refresh Token Response Code: " + execute.code());
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Refresh Token Response Body: ");
                Token body = execute.body();
                if (body == null) {
                    body = "";
                } else {
                    Intrinsics.checkNotNullExpressionValue(body, "refreshTokenResponse.body() ?: \"\"");
                }
                sb2.append(body);
                logger.e(sb2.toString());
                int code = execute.code();
                if (code != 200) {
                    if (code == 400) {
                        context = this.context;
                    } else if (code != 401) {
                        return response.request().newBuilder().header("Authorization", companion.getPrimaryUserAuthToken(this.context)).build();
                    } else {
                        context = this.context;
                    }
                    companion.logoutFromApp(context);
                } else {
                    Token it = execute.body();
                    if (it != null) {
                        Context context2 = this.context;
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                        companion.setPrimaryUserTokenDetails(context2, it);
                        companion.updateVinTokenDetails(this.context, it);
                        return response.request().newBuilder().header("Authorization", companion.getPrimaryUserAuthToken(this.context)).build();
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
            return null;
        }
    }
}
