package com.psa.mym.mycitroenconnect.api.body.fcm;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class RegisterFCMBody {
    @SerializedName("appVersion")
    @Nullable
    private String appVersion;
    @SerializedName("fcmToken")
    @Nullable
    private String fcmToken;
    @SerializedName("isGuest")
    @Nullable
    private String isGuest;
    @SerializedName("mobileId")
    @Nullable
    private String mobileId;
    @SerializedName("platform")
    @NotNull
    private String platform;
    @SerializedName("userName")
    @Nullable
    private String userName;
    @SerializedName("vehicleId")
    @Nullable
    private List<String> vehicleId;

    public RegisterFCMBody() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    public RegisterFCMBody(@Nullable List<String> list, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @NotNull String platform) {
        Intrinsics.checkNotNullParameter(platform, "platform");
        this.vehicleId = list;
        this.userName = str;
        this.mobileId = str2;
        this.fcmToken = str3;
        this.appVersion = str4;
        this.isGuest = str5;
        this.platform = platform;
    }

    public /* synthetic */ RegisterFCMBody(List list, String str, String str2, String str3, String str4, String str5, String str6, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : list, (i2 & 2) != 0 ? null : str, (i2 & 4) != 0 ? null : str2, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? null : str4, (i2 & 32) == 0 ? str5 : null, (i2 & 64) != 0 ? "android" : str6);
    }

    public static /* synthetic */ RegisterFCMBody copy$default(RegisterFCMBody registerFCMBody, List list, String str, String str2, String str3, String str4, String str5, String str6, int i2, Object obj) {
        List<String> list2 = list;
        if ((i2 & 1) != 0) {
            list2 = registerFCMBody.vehicleId;
        }
        if ((i2 & 2) != 0) {
            str = registerFCMBody.userName;
        }
        String str7 = str;
        if ((i2 & 4) != 0) {
            str2 = registerFCMBody.mobileId;
        }
        String str8 = str2;
        if ((i2 & 8) != 0) {
            str3 = registerFCMBody.fcmToken;
        }
        String str9 = str3;
        if ((i2 & 16) != 0) {
            str4 = registerFCMBody.appVersion;
        }
        String str10 = str4;
        if ((i2 & 32) != 0) {
            str5 = registerFCMBody.isGuest;
        }
        String str11 = str5;
        if ((i2 & 64) != 0) {
            str6 = registerFCMBody.platform;
        }
        return registerFCMBody.copy(list2, str7, str8, str9, str10, str11, str6);
    }

    @Nullable
    public final List<String> component1() {
        return this.vehicleId;
    }

    @Nullable
    public final String component2() {
        return this.userName;
    }

    @Nullable
    public final String component3() {
        return this.mobileId;
    }

    @Nullable
    public final String component4() {
        return this.fcmToken;
    }

    @Nullable
    public final String component5() {
        return this.appVersion;
    }

    @Nullable
    public final String component6() {
        return this.isGuest;
    }

    @NotNull
    public final String component7() {
        return this.platform;
    }

    @NotNull
    public final RegisterFCMBody copy(@Nullable List<String> list, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @NotNull String platform) {
        Intrinsics.checkNotNullParameter(platform, "platform");
        return new RegisterFCMBody(list, str, str2, str3, str4, str5, platform);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RegisterFCMBody) {
            RegisterFCMBody registerFCMBody = (RegisterFCMBody) obj;
            return Intrinsics.areEqual(this.vehicleId, registerFCMBody.vehicleId) && Intrinsics.areEqual(this.userName, registerFCMBody.userName) && Intrinsics.areEqual(this.mobileId, registerFCMBody.mobileId) && Intrinsics.areEqual(this.fcmToken, registerFCMBody.fcmToken) && Intrinsics.areEqual(this.appVersion, registerFCMBody.appVersion) && Intrinsics.areEqual(this.isGuest, registerFCMBody.isGuest) && Intrinsics.areEqual(this.platform, registerFCMBody.platform);
        }
        return false;
    }

    @Nullable
    public final String getAppVersion() {
        return this.appVersion;
    }

    @Nullable
    public final String getFcmToken() {
        return this.fcmToken;
    }

    @Nullable
    public final String getMobileId() {
        return this.mobileId;
    }

    @NotNull
    public final String getPlatform() {
        return this.platform;
    }

    @Nullable
    public final String getUserName() {
        return this.userName;
    }

    @Nullable
    public final List<String> getVehicleId() {
        return this.vehicleId;
    }

    public int hashCode() {
        List<String> list = this.vehicleId;
        int hashCode = (list == null ? 0 : list.hashCode()) * 31;
        String str = this.userName;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.mobileId;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.fcmToken;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.appVersion;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.isGuest;
        return ((hashCode5 + (str5 != null ? str5.hashCode() : 0)) * 31) + this.platform.hashCode();
    }

    @Nullable
    public final String isGuest() {
        return this.isGuest;
    }

    public final void setAppVersion(@Nullable String str) {
        this.appVersion = str;
    }

    public final void setFcmToken(@Nullable String str) {
        this.fcmToken = str;
    }

    public final void setGuest(@Nullable String str) {
        this.isGuest = str;
    }

    public final void setMobileId(@Nullable String str) {
        this.mobileId = str;
    }

    public final void setPlatform(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.platform = str;
    }

    public final void setUserName(@Nullable String str) {
        this.userName = str;
    }

    public final void setVehicleId(@Nullable List<String> list) {
        this.vehicleId = list;
    }

    @NotNull
    public String toString() {
        return "RegisterFCMBody(vehicleId=" + this.vehicleId + ", userName=" + this.userName + ", mobileId=" + this.mobileId + ", fcmToken=" + this.fcmToken + ", appVersion=" + this.appVersion + ", isGuest=" + this.isGuest + ", platform=" + this.platform + ')';
    }
}
