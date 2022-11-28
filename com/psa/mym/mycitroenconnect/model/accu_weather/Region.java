package com.psa.mym.mycitroenconnect.model.accu_weather;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class Region {
    @SerializedName("EnglishName")
    @NotNull
    private final String englishName;
    @SerializedName("ID")
    @NotNull
    private final String iD;
    @SerializedName("LocalizedName")
    @NotNull
    private final String localizedName;

    public Region(@NotNull String englishName, @NotNull String iD, @NotNull String localizedName) {
        Intrinsics.checkNotNullParameter(englishName, "englishName");
        Intrinsics.checkNotNullParameter(iD, "iD");
        Intrinsics.checkNotNullParameter(localizedName, "localizedName");
        this.englishName = englishName;
        this.iD = iD;
        this.localizedName = localizedName;
    }

    public static /* synthetic */ Region copy$default(Region region, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = region.englishName;
        }
        if ((i2 & 2) != 0) {
            str2 = region.iD;
        }
        if ((i2 & 4) != 0) {
            str3 = region.localizedName;
        }
        return region.copy(str, str2, str3);
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
    public final Region copy(@NotNull String englishName, @NotNull String iD, @NotNull String localizedName) {
        Intrinsics.checkNotNullParameter(englishName, "englishName");
        Intrinsics.checkNotNullParameter(iD, "iD");
        Intrinsics.checkNotNullParameter(localizedName, "localizedName");
        return new Region(englishName, iD, localizedName);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Region) {
            Region region = (Region) obj;
            return Intrinsics.areEqual(this.englishName, region.englishName) && Intrinsics.areEqual(this.iD, region.iD) && Intrinsics.areEqual(this.localizedName, region.localizedName);
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
        return "Region(englishName=" + this.englishName + ", iD=" + this.iD + ", localizedName=" + this.localizedName + ')';
    }
}
