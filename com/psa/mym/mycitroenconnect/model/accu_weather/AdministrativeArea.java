package com.psa.mym.mycitroenconnect.model.accu_weather;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class AdministrativeArea {
    @SerializedName("CountryID")
    @NotNull
    private final String countryID;
    @SerializedName("EnglishName")
    @NotNull
    private final String englishName;
    @SerializedName("EnglishType")
    @NotNull
    private final String englishType;
    @SerializedName("ID")
    @NotNull
    private final String iD;
    @SerializedName("Level")
    private final int level;
    @SerializedName("LocalizedName")
    @NotNull
    private final String localizedName;
    @SerializedName("LocalizedType")
    @NotNull
    private final String localizedType;

    public AdministrativeArea(@NotNull String countryID, @NotNull String englishName, @NotNull String englishType, @NotNull String iD, int i2, @NotNull String localizedName, @NotNull String localizedType) {
        Intrinsics.checkNotNullParameter(countryID, "countryID");
        Intrinsics.checkNotNullParameter(englishName, "englishName");
        Intrinsics.checkNotNullParameter(englishType, "englishType");
        Intrinsics.checkNotNullParameter(iD, "iD");
        Intrinsics.checkNotNullParameter(localizedName, "localizedName");
        Intrinsics.checkNotNullParameter(localizedType, "localizedType");
        this.countryID = countryID;
        this.englishName = englishName;
        this.englishType = englishType;
        this.iD = iD;
        this.level = i2;
        this.localizedName = localizedName;
        this.localizedType = localizedType;
    }

    public static /* synthetic */ AdministrativeArea copy$default(AdministrativeArea administrativeArea, String str, String str2, String str3, String str4, int i2, String str5, String str6, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = administrativeArea.countryID;
        }
        if ((i3 & 2) != 0) {
            str2 = administrativeArea.englishName;
        }
        String str7 = str2;
        if ((i3 & 4) != 0) {
            str3 = administrativeArea.englishType;
        }
        String str8 = str3;
        if ((i3 & 8) != 0) {
            str4 = administrativeArea.iD;
        }
        String str9 = str4;
        if ((i3 & 16) != 0) {
            i2 = administrativeArea.level;
        }
        int i4 = i2;
        if ((i3 & 32) != 0) {
            str5 = administrativeArea.localizedName;
        }
        String str10 = str5;
        if ((i3 & 64) != 0) {
            str6 = administrativeArea.localizedType;
        }
        return administrativeArea.copy(str, str7, str8, str9, i4, str10, str6);
    }

    @NotNull
    public final String component1() {
        return this.countryID;
    }

    @NotNull
    public final String component2() {
        return this.englishName;
    }

    @NotNull
    public final String component3() {
        return this.englishType;
    }

    @NotNull
    public final String component4() {
        return this.iD;
    }

    public final int component5() {
        return this.level;
    }

    @NotNull
    public final String component6() {
        return this.localizedName;
    }

    @NotNull
    public final String component7() {
        return this.localizedType;
    }

    @NotNull
    public final AdministrativeArea copy(@NotNull String countryID, @NotNull String englishName, @NotNull String englishType, @NotNull String iD, int i2, @NotNull String localizedName, @NotNull String localizedType) {
        Intrinsics.checkNotNullParameter(countryID, "countryID");
        Intrinsics.checkNotNullParameter(englishName, "englishName");
        Intrinsics.checkNotNullParameter(englishType, "englishType");
        Intrinsics.checkNotNullParameter(iD, "iD");
        Intrinsics.checkNotNullParameter(localizedName, "localizedName");
        Intrinsics.checkNotNullParameter(localizedType, "localizedType");
        return new AdministrativeArea(countryID, englishName, englishType, iD, i2, localizedName, localizedType);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AdministrativeArea) {
            AdministrativeArea administrativeArea = (AdministrativeArea) obj;
            return Intrinsics.areEqual(this.countryID, administrativeArea.countryID) && Intrinsics.areEqual(this.englishName, administrativeArea.englishName) && Intrinsics.areEqual(this.englishType, administrativeArea.englishType) && Intrinsics.areEqual(this.iD, administrativeArea.iD) && this.level == administrativeArea.level && Intrinsics.areEqual(this.localizedName, administrativeArea.localizedName) && Intrinsics.areEqual(this.localizedType, administrativeArea.localizedType);
        }
        return false;
    }

    @NotNull
    public final String getCountryID() {
        return this.countryID;
    }

    @NotNull
    public final String getEnglishName() {
        return this.englishName;
    }

    @NotNull
    public final String getEnglishType() {
        return this.englishType;
    }

    @NotNull
    public final String getID() {
        return this.iD;
    }

    public final int getLevel() {
        return this.level;
    }

    @NotNull
    public final String getLocalizedName() {
        return this.localizedName;
    }

    @NotNull
    public final String getLocalizedType() {
        return this.localizedType;
    }

    public int hashCode() {
        return (((((((((((this.countryID.hashCode() * 31) + this.englishName.hashCode()) * 31) + this.englishType.hashCode()) * 31) + this.iD.hashCode()) * 31) + Integer.hashCode(this.level)) * 31) + this.localizedName.hashCode()) * 31) + this.localizedType.hashCode();
    }

    @NotNull
    public String toString() {
        return "AdministrativeArea(countryID=" + this.countryID + ", englishName=" + this.englishName + ", englishType=" + this.englishType + ", iD=" + this.iD + ", level=" + this.level + ", localizedName=" + this.localizedName + ", localizedType=" + this.localizedType + ')';
    }
}
