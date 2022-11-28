package com.psa.mym.mycitroenconnect.api.body.onboarding;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class CreateUserBody {
    @SerializedName("countryCode")
    @Nullable
    private String countryCode;
    @SerializedName("mobileNum")
    @Nullable
    private String mobileNum;

    public CreateUserBody() {
        this(null, null, 3, null);
    }

    public CreateUserBody(@Nullable String str, @Nullable String str2) {
        this.mobileNum = str;
        this.countryCode = str2;
    }

    public /* synthetic */ CreateUserBody(String str, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2);
    }

    public static /* synthetic */ CreateUserBody copy$default(CreateUserBody createUserBody, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = createUserBody.mobileNum;
        }
        if ((i2 & 2) != 0) {
            str2 = createUserBody.countryCode;
        }
        return createUserBody.copy(str, str2);
    }

    @Nullable
    public final String component1() {
        return this.mobileNum;
    }

    @Nullable
    public final String component2() {
        return this.countryCode;
    }

    @NotNull
    public final CreateUserBody copy(@Nullable String str, @Nullable String str2) {
        return new CreateUserBody(str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CreateUserBody) {
            CreateUserBody createUserBody = (CreateUserBody) obj;
            return Intrinsics.areEqual(this.mobileNum, createUserBody.mobileNum) && Intrinsics.areEqual(this.countryCode, createUserBody.countryCode);
        }
        return false;
    }

    @Nullable
    public final String getCountryCode() {
        return this.countryCode;
    }

    @Nullable
    public final String getMobileNum() {
        return this.mobileNum;
    }

    public int hashCode() {
        String str = this.mobileNum;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.countryCode;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public final void setCountryCode(@Nullable String str) {
        this.countryCode = str;
    }

    public final void setMobileNum(@Nullable String str) {
        this.mobileNum = str;
    }

    @NotNull
    public String toString() {
        return "CreateUserBody(mobileNum=" + this.mobileNum + ", countryCode=" + this.countryCode + ')';
    }
}
