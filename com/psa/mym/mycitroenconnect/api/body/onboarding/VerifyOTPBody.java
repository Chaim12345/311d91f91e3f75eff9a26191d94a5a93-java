package com.psa.mym.mycitroenconnect.api.body.onboarding;

import androidx.core.app.FrameMetricsAggregator;
import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.BuildConfig;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class VerifyOTPBody {
    @NotNull
    private String apiFrom;
    @SerializedName("clientId")
    @Nullable
    private String clientId;
    @SerializedName("clientSecret")
    @Nullable
    private String clientSecret;
    @SerializedName("countryCode")
    @Nullable
    private String countryCode;
    @SerializedName("fullHash")
    @Nullable
    private String fullHash;
    @SerializedName("fullhash")
    @Nullable
    private String fullhash;
    @SerializedName("otp")
    @Nullable
    private String otp;
    @SerializedName("userName")
    @Nullable
    private String userName;
    @SerializedName("vinNum")
    @Nullable
    private String vinNum;

    public VerifyOTPBody() {
        this(null, null, null, null, null, null, null, null, null, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    public VerifyOTPBody(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @NotNull String apiFrom) {
        Intrinsics.checkNotNullParameter(apiFrom, "apiFrom");
        this.countryCode = str;
        this.otp = str2;
        this.clientId = str3;
        this.clientSecret = str4;
        this.fullhash = str5;
        this.userName = str6;
        this.vinNum = str7;
        this.fullHash = str8;
        this.apiFrom = apiFrom;
        this.clientId = BuildConfig.CLIENTID;
        this.clientSecret = BuildConfig.CLIENTSECRET;
    }

    public /* synthetic */ VerifyOTPBody(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : str5, (i2 & 32) != 0 ? null : str6, (i2 & 64) != 0 ? null : str7, (i2 & 128) == 0 ? str8 : null, (i2 & 256) != 0 ? "" : str9);
    }

    @Nullable
    public final String component1() {
        return this.countryCode;
    }

    @Nullable
    public final String component2() {
        return this.otp;
    }

    @Nullable
    public final String component3() {
        return this.clientId;
    }

    @Nullable
    public final String component4() {
        return this.clientSecret;
    }

    @Nullable
    public final String component5() {
        return this.fullhash;
    }

    @Nullable
    public final String component6() {
        return this.userName;
    }

    @Nullable
    public final String component7() {
        return this.vinNum;
    }

    @Nullable
    public final String component8() {
        return this.fullHash;
    }

    @NotNull
    public final String component9() {
        return this.apiFrom;
    }

    @NotNull
    public final VerifyOTPBody copy(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @NotNull String apiFrom) {
        Intrinsics.checkNotNullParameter(apiFrom, "apiFrom");
        return new VerifyOTPBody(str, str2, str3, str4, str5, str6, str7, str8, apiFrom);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof VerifyOTPBody) {
            VerifyOTPBody verifyOTPBody = (VerifyOTPBody) obj;
            return Intrinsics.areEqual(this.countryCode, verifyOTPBody.countryCode) && Intrinsics.areEqual(this.otp, verifyOTPBody.otp) && Intrinsics.areEqual(this.clientId, verifyOTPBody.clientId) && Intrinsics.areEqual(this.clientSecret, verifyOTPBody.clientSecret) && Intrinsics.areEqual(this.fullhash, verifyOTPBody.fullhash) && Intrinsics.areEqual(this.userName, verifyOTPBody.userName) && Intrinsics.areEqual(this.vinNum, verifyOTPBody.vinNum) && Intrinsics.areEqual(this.fullHash, verifyOTPBody.fullHash) && Intrinsics.areEqual(this.apiFrom, verifyOTPBody.apiFrom);
        }
        return false;
    }

    @NotNull
    public final String getApiFrom() {
        return this.apiFrom;
    }

    @Nullable
    public final String getClientId() {
        return this.clientId;
    }

    @Nullable
    public final String getClientSecret() {
        return this.clientSecret;
    }

    @Nullable
    public final String getCountryCode() {
        return this.countryCode;
    }

    @Nullable
    public final String getFullHash() {
        return this.fullHash;
    }

    @Nullable
    public final String getFullhash() {
        return this.fullhash;
    }

    @Nullable
    public final String getOtp() {
        return this.otp;
    }

    @Nullable
    public final String getUserName() {
        return this.userName;
    }

    @Nullable
    public final String getVinNum() {
        return this.vinNum;
    }

    public int hashCode() {
        String str = this.countryCode;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.otp;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.clientId;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.clientSecret;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.fullhash;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.userName;
        int hashCode6 = (hashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.vinNum;
        int hashCode7 = (hashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.fullHash;
        return ((hashCode7 + (str8 != null ? str8.hashCode() : 0)) * 31) + this.apiFrom.hashCode();
    }

    public final void setApiFrom(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.apiFrom = str;
    }

    public final void setClientId(@Nullable String str) {
        this.clientId = str;
    }

    public final void setClientSecret(@Nullable String str) {
        this.clientSecret = str;
    }

    public final void setCountryCode(@Nullable String str) {
        this.countryCode = str;
    }

    public final void setFullHash(@Nullable String str) {
        this.fullHash = str;
    }

    public final void setFullhash(@Nullable String str) {
        this.fullhash = str;
    }

    public final void setOtp(@Nullable String str) {
        this.otp = str;
    }

    public final void setUserName(@Nullable String str) {
        this.userName = str;
    }

    public final void setVinNum(@Nullable String str) {
        this.vinNum = str;
    }

    @NotNull
    public String toString() {
        return "VerifyOTPBody(countryCode=" + this.countryCode + ", otp=" + this.otp + ", clientId=" + this.clientId + ", clientSecret=" + this.clientSecret + ", fullhash=" + this.fullhash + ", userName=" + this.userName + ", vinNum=" + this.vinNum + ", fullHash=" + this.fullHash + ", apiFrom=" + this.apiFrom + ')';
    }
}
