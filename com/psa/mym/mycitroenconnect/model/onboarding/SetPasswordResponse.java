package com.psa.mym.mycitroenconnect.model.onboarding;

import com.psa.mym.mycitroenconnect.model.Token;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SetPasswordResponse {
    @NotNull
    private String mobileNum;
    private int statusCode;
    @NotNull
    private Token token;

    public SetPasswordResponse(@NotNull Token token, int i2, @NotNull String mobileNum) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        this.token = token;
        this.statusCode = i2;
        this.mobileNum = mobileNum;
    }

    public static /* synthetic */ SetPasswordResponse copy$default(SetPasswordResponse setPasswordResponse, Token token, int i2, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            token = setPasswordResponse.token;
        }
        if ((i3 & 2) != 0) {
            i2 = setPasswordResponse.statusCode;
        }
        if ((i3 & 4) != 0) {
            str = setPasswordResponse.mobileNum;
        }
        return setPasswordResponse.copy(token, i2, str);
    }

    @NotNull
    public final Token component1() {
        return this.token;
    }

    public final int component2() {
        return this.statusCode;
    }

    @NotNull
    public final String component3() {
        return this.mobileNum;
    }

    @NotNull
    public final SetPasswordResponse copy(@NotNull Token token, int i2, @NotNull String mobileNum) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(mobileNum, "mobileNum");
        return new SetPasswordResponse(token, i2, mobileNum);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SetPasswordResponse) {
            SetPasswordResponse setPasswordResponse = (SetPasswordResponse) obj;
            return Intrinsics.areEqual(this.token, setPasswordResponse.token) && this.statusCode == setPasswordResponse.statusCode && Intrinsics.areEqual(this.mobileNum, setPasswordResponse.mobileNum);
        }
        return false;
    }

    @NotNull
    public final String getMobileNum() {
        return this.mobileNum;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    @NotNull
    public final Token getToken() {
        return this.token;
    }

    public int hashCode() {
        return (((this.token.hashCode() * 31) + Integer.hashCode(this.statusCode)) * 31) + this.mobileNum.hashCode();
    }

    public final void setMobileNum(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mobileNum = str;
    }

    public final void setStatusCode(int i2) {
        this.statusCode = i2;
    }

    public final void setToken(@NotNull Token token) {
        Intrinsics.checkNotNullParameter(token, "<set-?>");
        this.token = token;
    }

    @NotNull
    public String toString() {
        return "SetPasswordResponse(token=" + this.token + ", statusCode=" + this.statusCode + ", mobileNum=" + this.mobileNum + ')';
    }
}
