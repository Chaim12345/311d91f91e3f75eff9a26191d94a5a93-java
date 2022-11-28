package com.psa.mym.mycitroenconnect.api.body.onboarding;

import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.BuildConfig;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class GetTokenBody {
    @SerializedName("clientId")
    @NotNull
    private String clientId;
    @SerializedName("clientSecret")
    @NotNull
    private String clientSecret;
    @SerializedName("countryCode")
    @NotNull
    private String countryCode;
    @SerializedName("userName")
    @NotNull
    private String userName;

    public GetTokenBody() {
        this(null, null, null, null, 15, null);
    }

    public GetTokenBody(@NotNull String userName, @NotNull String countryCode, @NotNull String clientId, @NotNull String clientSecret) {
        Intrinsics.checkNotNullParameter(userName, "userName");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(clientSecret, "clientSecret");
        this.userName = userName;
        this.countryCode = countryCode;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.clientId = BuildConfig.CLIENTID;
        this.clientSecret = BuildConfig.CLIENTSECRET;
    }

    public /* synthetic */ GetTokenBody(String str, String str2, String str3, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3, (i2 & 8) != 0 ? "" : str4);
    }

    public static /* synthetic */ GetTokenBody copy$default(GetTokenBody getTokenBody, String str, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = getTokenBody.userName;
        }
        if ((i2 & 2) != 0) {
            str2 = getTokenBody.countryCode;
        }
        if ((i2 & 4) != 0) {
            str3 = getTokenBody.clientId;
        }
        if ((i2 & 8) != 0) {
            str4 = getTokenBody.clientSecret;
        }
        return getTokenBody.copy(str, str2, str3, str4);
    }

    @NotNull
    public final String component1() {
        return this.userName;
    }

    @NotNull
    public final String component2() {
        return this.countryCode;
    }

    @NotNull
    public final String component3() {
        return this.clientId;
    }

    @NotNull
    public final String component4() {
        return this.clientSecret;
    }

    @NotNull
    public final GetTokenBody copy(@NotNull String userName, @NotNull String countryCode, @NotNull String clientId, @NotNull String clientSecret) {
        Intrinsics.checkNotNullParameter(userName, "userName");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(clientSecret, "clientSecret");
        return new GetTokenBody(userName, countryCode, clientId, clientSecret);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GetTokenBody) {
            GetTokenBody getTokenBody = (GetTokenBody) obj;
            return Intrinsics.areEqual(this.userName, getTokenBody.userName) && Intrinsics.areEqual(this.countryCode, getTokenBody.countryCode) && Intrinsics.areEqual(this.clientId, getTokenBody.clientId) && Intrinsics.areEqual(this.clientSecret, getTokenBody.clientSecret);
        }
        return false;
    }

    @NotNull
    public final String getClientId() {
        return this.clientId;
    }

    @NotNull
    public final String getClientSecret() {
        return this.clientSecret;
    }

    @NotNull
    public final String getCountryCode() {
        return this.countryCode;
    }

    @NotNull
    public final String getUserName() {
        return this.userName;
    }

    public int hashCode() {
        return (((((this.userName.hashCode() * 31) + this.countryCode.hashCode()) * 31) + this.clientId.hashCode()) * 31) + this.clientSecret.hashCode();
    }

    public final void setClientId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.clientId = str;
    }

    public final void setClientSecret(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.clientSecret = str;
    }

    public final void setCountryCode(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.countryCode = str;
    }

    public final void setUserName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userName = str;
    }

    @NotNull
    public String toString() {
        return "GetTokenBody(userName=" + this.userName + ", countryCode=" + this.countryCode + ", clientId=" + this.clientId + ", clientSecret=" + this.clientSecret + ')';
    }
}
