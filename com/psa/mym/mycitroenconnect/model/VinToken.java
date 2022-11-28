package com.psa.mym.mycitroenconnect.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class VinToken {
    @Nullable
    private String carModelName;
    @Nullable
    private String createTime;
    @Nullable
    private Token token;
    @Nullable
    private String tokenExpireTime;
    @Nullable
    private String userType;
    @Nullable
    private String vehicleRegNo;
    @Nullable
    private String vehicleType;
    @Nullable
    private String vinNumber;

    public VinToken() {
        this(null, null, null, null, null, null, null, null, 255, null);
    }

    public VinToken(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable Token token) {
        this.vinNumber = str;
        this.carModelName = str2;
        this.vehicleRegNo = str3;
        this.vehicleType = str4;
        this.userType = str5;
        this.createTime = str6;
        this.tokenExpireTime = str7;
        this.token = token;
    }

    public /* synthetic */ VinToken(String str, String str2, String str3, String str4, String str5, String str6, String str7, Token token, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3, (i2 & 8) != 0 ? "" : str4, (i2 & 16) != 0 ? "" : str5, (i2 & 32) != 0 ? "" : str6, (i2 & 64) == 0 ? str7 : "", (i2 & 128) != 0 ? null : token);
    }

    @Nullable
    public final String component1() {
        return this.vinNumber;
    }

    @Nullable
    public final String component2() {
        return this.carModelName;
    }

    @Nullable
    public final String component3() {
        return this.vehicleRegNo;
    }

    @Nullable
    public final String component4() {
        return this.vehicleType;
    }

    @Nullable
    public final String component5() {
        return this.userType;
    }

    @Nullable
    public final String component6() {
        return this.createTime;
    }

    @Nullable
    public final String component7() {
        return this.tokenExpireTime;
    }

    @Nullable
    public final Token component8() {
        return this.token;
    }

    @NotNull
    public final VinToken copy(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable Token token) {
        return new VinToken(str, str2, str3, str4, str5, str6, str7, token);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof VinToken) {
            VinToken vinToken = (VinToken) obj;
            return Intrinsics.areEqual(this.vinNumber, vinToken.vinNumber) && Intrinsics.areEqual(this.carModelName, vinToken.carModelName) && Intrinsics.areEqual(this.vehicleRegNo, vinToken.vehicleRegNo) && Intrinsics.areEqual(this.vehicleType, vinToken.vehicleType) && Intrinsics.areEqual(this.userType, vinToken.userType) && Intrinsics.areEqual(this.createTime, vinToken.createTime) && Intrinsics.areEqual(this.tokenExpireTime, vinToken.tokenExpireTime) && Intrinsics.areEqual(this.token, vinToken.token);
        }
        return false;
    }

    @Nullable
    public final String getCarModelName() {
        return this.carModelName;
    }

    @Nullable
    public final String getCreateTime() {
        return this.createTime;
    }

    @Nullable
    public final Token getToken() {
        return this.token;
    }

    @Nullable
    public final String getTokenExpireTime() {
        return this.tokenExpireTime;
    }

    @Nullable
    public final String getUserType() {
        return this.userType;
    }

    @Nullable
    public final String getVehicleRegNo() {
        return this.vehicleRegNo;
    }

    @Nullable
    public final String getVehicleType() {
        return this.vehicleType;
    }

    @Nullable
    public final String getVinNumber() {
        return this.vinNumber;
    }

    public int hashCode() {
        String str = this.vinNumber;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.carModelName;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.vehicleRegNo;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.vehicleType;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.userType;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.createTime;
        int hashCode6 = (hashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.tokenExpireTime;
        int hashCode7 = (hashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        Token token = this.token;
        return hashCode7 + (token != null ? token.hashCode() : 0);
    }

    public final void setCarModelName(@Nullable String str) {
        this.carModelName = str;
    }

    public final void setCreateTime(@Nullable String str) {
        this.createTime = str;
    }

    public final void setToken(@Nullable Token token) {
        this.token = token;
    }

    public final void setTokenExpireTime(@Nullable String str) {
        this.tokenExpireTime = str;
    }

    public final void setUserType(@Nullable String str) {
        this.userType = str;
    }

    public final void setVehicleRegNo(@Nullable String str) {
        this.vehicleRegNo = str;
    }

    public final void setVehicleType(@Nullable String str) {
        this.vehicleType = str;
    }

    public final void setVinNumber(@Nullable String str) {
        this.vinNumber = str;
    }

    @NotNull
    public String toString() {
        return "VinToken(vinNumber=" + this.vinNumber + ", carModelName=" + this.carModelName + ", vehicleRegNo=" + this.vehicleRegNo + ", vehicleType=" + this.vehicleType + ", userType=" + this.userType + ", createTime=" + this.createTime + ", tokenExpireTime=" + this.tokenExpireTime + ", token=" + this.token + ')';
    }
}
