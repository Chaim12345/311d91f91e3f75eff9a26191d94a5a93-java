package com.psa.mym.mycitroenconnect.model.onboarding;

import com.psa.mym.mycitroenconnect.model.Token;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class VerifyOTPResponse {
    @NotNull
    private String accessToken;
    @NotNull
    private String apiFrom;
    @NotNull
    private String message;
    private int statusCode;
    @NotNull
    private String success;
    @NotNull
    private Token token;
    @NotNull
    private String tokenHash;

    public VerifyOTPResponse(@NotNull String message, int i2, @NotNull String success, @NotNull String accessToken, @NotNull String tokenHash, @NotNull String apiFrom, @NotNull Token token) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        Intrinsics.checkNotNullParameter(tokenHash, "tokenHash");
        Intrinsics.checkNotNullParameter(apiFrom, "apiFrom");
        Intrinsics.checkNotNullParameter(token, "token");
        this.message = message;
        this.statusCode = i2;
        this.success = success;
        this.accessToken = accessToken;
        this.tokenHash = tokenHash;
        this.apiFrom = apiFrom;
        this.token = token;
    }

    public /* synthetic */ VerifyOTPResponse(String str, int i2, String str2, String str3, String str4, String str5, Token token, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "" : str, (i3 & 2) != 0 ? 0 : i2, (i3 & 4) != 0 ? "" : str2, (i3 & 8) != 0 ? "" : str3, (i3 & 16) != 0 ? "" : str4, (i3 & 32) != 0 ? "" : str5, token);
    }

    public static /* synthetic */ VerifyOTPResponse copy$default(VerifyOTPResponse verifyOTPResponse, String str, int i2, String str2, String str3, String str4, String str5, Token token, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = verifyOTPResponse.message;
        }
        if ((i3 & 2) != 0) {
            i2 = verifyOTPResponse.statusCode;
        }
        int i4 = i2;
        if ((i3 & 4) != 0) {
            str2 = verifyOTPResponse.success;
        }
        String str6 = str2;
        if ((i3 & 8) != 0) {
            str3 = verifyOTPResponse.accessToken;
        }
        String str7 = str3;
        if ((i3 & 16) != 0) {
            str4 = verifyOTPResponse.tokenHash;
        }
        String str8 = str4;
        if ((i3 & 32) != 0) {
            str5 = verifyOTPResponse.apiFrom;
        }
        String str9 = str5;
        if ((i3 & 64) != 0) {
            token = verifyOTPResponse.token;
        }
        return verifyOTPResponse.copy(str, i4, str6, str7, str8, str9, token);
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
        return this.success;
    }

    @NotNull
    public final String component4() {
        return this.accessToken;
    }

    @NotNull
    public final String component5() {
        return this.tokenHash;
    }

    @NotNull
    public final String component6() {
        return this.apiFrom;
    }

    @NotNull
    public final Token component7() {
        return this.token;
    }

    @NotNull
    public final VerifyOTPResponse copy(@NotNull String message, int i2, @NotNull String success, @NotNull String accessToken, @NotNull String tokenHash, @NotNull String apiFrom, @NotNull Token token) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        Intrinsics.checkNotNullParameter(tokenHash, "tokenHash");
        Intrinsics.checkNotNullParameter(apiFrom, "apiFrom");
        Intrinsics.checkNotNullParameter(token, "token");
        return new VerifyOTPResponse(message, i2, success, accessToken, tokenHash, apiFrom, token);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof VerifyOTPResponse) {
            VerifyOTPResponse verifyOTPResponse = (VerifyOTPResponse) obj;
            return Intrinsics.areEqual(this.message, verifyOTPResponse.message) && this.statusCode == verifyOTPResponse.statusCode && Intrinsics.areEqual(this.success, verifyOTPResponse.success) && Intrinsics.areEqual(this.accessToken, verifyOTPResponse.accessToken) && Intrinsics.areEqual(this.tokenHash, verifyOTPResponse.tokenHash) && Intrinsics.areEqual(this.apiFrom, verifyOTPResponse.apiFrom) && Intrinsics.areEqual(this.token, verifyOTPResponse.token);
        }
        return false;
    }

    @NotNull
    public final String getAccessToken() {
        return this.accessToken;
    }

    @NotNull
    public final String getApiFrom() {
        return this.apiFrom;
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    @NotNull
    public final String getSuccess() {
        return this.success;
    }

    @NotNull
    public final Token getToken() {
        return this.token;
    }

    @NotNull
    public final String getTokenHash() {
        return this.tokenHash;
    }

    public int hashCode() {
        return (((((((((((this.message.hashCode() * 31) + Integer.hashCode(this.statusCode)) * 31) + this.success.hashCode()) * 31) + this.accessToken.hashCode()) * 31) + this.tokenHash.hashCode()) * 31) + this.apiFrom.hashCode()) * 31) + this.token.hashCode();
    }

    public final void setAccessToken(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.accessToken = str;
    }

    public final void setApiFrom(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.apiFrom = str;
    }

    public final void setMessage(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.message = str;
    }

    public final void setStatusCode(int i2) {
        this.statusCode = i2;
    }

    public final void setSuccess(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.success = str;
    }

    public final void setToken(@NotNull Token token) {
        Intrinsics.checkNotNullParameter(token, "<set-?>");
        this.token = token;
    }

    public final void setTokenHash(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tokenHash = str;
    }

    @NotNull
    public String toString() {
        return "VerifyOTPResponse(message=" + this.message + ", statusCode=" + this.statusCode + ", success=" + this.success + ", accessToken=" + this.accessToken + ", tokenHash=" + this.tokenHash + ", apiFrom=" + this.apiFrom + ", token=" + this.token + ')';
    }
}
