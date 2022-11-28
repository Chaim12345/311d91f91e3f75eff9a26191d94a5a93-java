package com.psa.mym.mycitroenconnect.model.accu_weather;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class Country {
    @SerializedName("EnglishName")
    @NotNull
    private final String englishName;
    @SerializedName("ID")
    @NotNull
    private final String iD;
    @SerializedName("LocalizedName")
    @NotNull
    private final String localizedName;

    public Country(@NotNull String englishName, @NotNull String iD, @NotNull String localizedName) {
        Intrinsics.checkNotNullParameter(englishName, "englishName");
        Intrinsics.checkNotNullParameter(iD, "iD");
        Intrinsics.checkNotNullParameter(localizedName, "localizedName");
        this.englishName = englishName;
        this.iD = iD;
        this.localizedName = localizedName;
    }

    public static /* synthetic */ Country copy$default(Country country, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = country.englishName;
        }
        if ((i2 & 2) != 0) {
            str2 = country.iD;
        }
        if ((i2 & 4) != 0) {
            str3 = country.localizedName;
        }
        return country.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.englishName;
    }

    @NotNull
    public final String component2() {
        return this.iD;
    }

    @NotNull
    public final String component3() {
        return this.localizedName;
    }

    @NotNull
    public final Country copy(@NotNull String englishName, @NotNull String iD, @NotNull String localizedName) {
        Intrinsics.checkNotNullParameter(englishName, "englishName");
        Intrinsics.checkNotNullParameter(iD, "iD");
        Intrinsics.checkNotNullParameter(localizedName, "localizedName");
        return new Country(englishName, iD, localizedName);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Country) {
            Country country = (Country) obj;
            return Intrinsics.areEqual(this.englishName, country.englishName) && Intrinsics.areEqual(this.iD, country.iD) && Intrinsics.areEqual(this.localizedName, country.localizedName);
        }
        return false;
    }

    @NotNull
    public final String getEnglishName() {
        return this.englishName;
    }

    @NotNull
    public final String getID() {
        return this.iD;
    }

    @NotNull
    public final String getLocalizedName() {
        return this.localizedName;
    }

    public int hashCode() {
        return (((this.englishName.hashCode() * 31) + this.iD.hashCode()) * 31) + this.localizedName.hashCode();
    }

    @NotNull
    public String toString() {
        return "Country(englishName=" + this.englishName + ", iD=" + this.iD + ", localizedName=" + this.localizedName + ')';
    }
}
