package com.psa.mym.mycitroenconnect.model.accu_weather;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SupplementalAdminArea {
    @SerializedName("EnglishName")
    @NotNull
    private final String englishName;
    @SerializedName("Level")
    private final int level;
    @SerializedName("LocalizedName")
    @NotNull
    private final String localizedName;

    public SupplementalAdminArea(@NotNull String englishName, int i2, @NotNull String localizedName) {
        Intrinsics.checkNotNullParameter(englishName, "englishName");
        Intrinsics.checkNotNullParameter(localizedName, "localizedName");
        this.englishName = englishName;
        this.level = i2;
        this.localizedName = localizedName;
    }

    public static /* synthetic */ SupplementalAdminArea copy$default(SupplementalAdminArea supplementalAdminArea, String str, int i2, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = supplementalAdminArea.englishName;
        }
        if ((i3 & 2) != 0) {
            i2 = supplementalAdminArea.level;
        }
        if ((i3 & 4) != 0) {
            str2 = supplementalAdminArea.localizedName;
        }
        return supplementalAdminArea.copy(str, i2, str2);
    }

    @NotNull
    public final String component1() {
        return this.englishName;
    }

    public final int component2() {
        return this.level;
    }

    @NotNull
    public final String component3() {
        return this.localizedName;
    }

    @NotNull
    public final SupplementalAdminArea copy(@NotNull String englishName, int i2, @NotNull String localizedName) {
        Intrinsics.checkNotNullParameter(englishName, "englishName");
        Intrinsics.checkNotNullParameter(localizedName, "localizedName");
        return new SupplementalAdminArea(englishName, i2, localizedName);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SupplementalAdminArea) {
            SupplementalAdminArea supplementalAdminArea = (SupplementalAdminArea) obj;
            return Intrinsics.areEqual(this.englishName, supplementalAdminArea.englishName) && this.level == supplementalAdminArea.level && Intrinsics.areEqual(this.localizedName, supplementalAdminArea.localizedName);
        }
        return false;
    }

    @NotNull
    public final String getEnglishName() {
        return this.englishName;
    }

    public final int getLevel() {
        return this.level;
    }

    @NotNull
    public final String getLocalizedName() {
        return this.localizedName;
    }

    public int hashCode() {
        return (((this.englishName.hashCode() * 31) + Integer.hashCode(this.level)) * 31) + this.localizedName.hashCode();
    }

    @NotNull
    public String toString() {
        return "SupplementalAdminArea(englishName=" + this.englishName + ", level=" + this.level + ", localizedName=" + this.localizedName + ')';
    }
}
