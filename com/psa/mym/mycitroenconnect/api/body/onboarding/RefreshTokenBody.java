package com.psa.mym.mycitroenconnect.api.body.onboarding;

import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.BuildConfig;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class RefreshTokenBody {
    @SerializedName("clientId")
    @NotNull
    private String clientId;
    @SerializedName("clientSecret")
    @NotNull
    private String clientSecret;
    @SerializedName("token")
    @NotNull
    private String token;

    public RefreshTokenBody() {
        this(null, null, null, 7, null);
    }

    public RefreshTokenBody(@NotNull String token, @NotNull String clientId, @NotNull String clientSecret) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(clientSecret, "clientSecret");
        this.token = token;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.clientId = BuildConfig.CLIENTID;
        this.clientSecret = BuildConfig.CLIENTSECRET;
    }

    public /* synthetic */ RefreshTokenBody(String str, String str2, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3);
    }

    public static /* synthetic */ RefreshTokenBody copy$default(RefreshTokenBody refreshTokenBody, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = refreshTokenBody.token;
        }
        if ((i2 & 2) != 0) {
            str2 = refreshTokenBody.clientId;
        }
        if ((i2 & 4) != 0) {
            str3 = refreshTokenBody.clientSecret;
        }
        return refreshTokenBody.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.token;
    }

    @NotNull
    public final String component2() {
        return this.clientId;
    }

    @NotNull
    public final String component3() {
        return this.clientSecret;
    }

    @NotNull
    public final RefreshTokenBody copy(@NotNull String token, @NotNull String clientId, @NotNull String clientSecret) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(clientSecret, "clientSecret");
        return new RefreshTokenBody(token, clientId, clientSecret);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RefreshTokenBody) {
            RefreshTokenBody refreshTokenBody = (RefreshTokenBody) obj;
            return Intrinsics.areEqual(this.token, refreshTokenBody.token) && Intrinsics.areEqual(this.clientId, refreshTokenBody.clientId) && Intrinsics.areEqual(this.clientSecret, refreshTokenBody.clientSecret);
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
    public final String getToken() {
        return this.token;
    }

    public int hashCode() {
        return (((this.token.hashCode() * 31) + this.clientId.hashCode()) * 31) + this.clientSecret.hashCode();
    }

    public final void setClientId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.clientId = str;
    }

    public final void setClientSecret(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.clientSecret = str;
    }

    public final void setToken(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.token = str;
    }

    @NotNull
    public String toString() {
        return "RefreshTokenBody(token=" + this.token + ", clientId=" + this.clientId + ", clientSecret=" + this.clientSecret + ')';
    }
}
