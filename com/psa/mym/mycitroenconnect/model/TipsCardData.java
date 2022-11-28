package com.psa.mym.mycitroenconnect.model;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class TipsCardData {
    @NotNull
    private final String description;
    @NotNull
    private final String title;

    public TipsCardData(@NotNull String title, @NotNull String description) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(description, "description");
        this.title = title;
        this.description = description;
    }

    public static /* synthetic */ TipsCardData copy$default(TipsCardData tipsCardData, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = tipsCardData.title;
        }
        if ((i2 & 2) != 0) {
            str2 = tipsCardData.description;
        }
        return tipsCardData.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.title;
    }

    @NotNull
    public final String component2() {
        return this.description;
    }

    @NotNull
    public final TipsCardData copy(@NotNull String title, @NotNull String description) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(description, "description");
        return new TipsCardData(title, description);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TipsCardData) {
            TipsCardData tipsCardData = (TipsCardData) obj;
            return Intrinsics.areEqual(this.title, tipsCardData.title) && Intrinsics.areEqual(this.description, tipsCardData.description);
        }
        return false;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return (this.title.hashCode() * 31) + this.description.hashCode();
    }

    @NotNull
    public String toString() {
        return "TipsCardData(title=" + this.title + ", description=" + this.description + ')';
    }
}
