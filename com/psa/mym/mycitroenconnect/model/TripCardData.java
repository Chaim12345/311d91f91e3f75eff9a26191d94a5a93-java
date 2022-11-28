package com.psa.mym.mycitroenconnect.model;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class TripCardData {
    private int icon;
    @NotNull
    private String title;
    @NotNull
    private String value;
    @NotNull
    private String valueSuffix;

    public TripCardData(@NotNull String title, @NotNull String value, @NotNull String valueSuffix, int i2) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(valueSuffix, "valueSuffix");
        this.title = title;
        this.value = value;
        this.valueSuffix = valueSuffix;
        this.icon = i2;
    }

    public static /* synthetic */ TripCardData copy$default(TripCardData tripCardData, String str, String str2, String str3, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = tripCardData.title;
        }
        if ((i3 & 2) != 0) {
            str2 = tripCardData.value;
        }
        if ((i3 & 4) != 0) {
            str3 = tripCardData.valueSuffix;
        }
        if ((i3 & 8) != 0) {
            i2 = tripCardData.icon;
        }
        return tripCardData.copy(str, str2, str3, i2);
    }

    @NotNull
    public final String component1() {
        return this.title;
    }

    @NotNull
    public final String component2() {
        return this.value;
    }

    @NotNull
    public final String component3() {
        return this.valueSuffix;
    }

    public final int component4() {
        return this.icon;
    }

    @NotNull
    public final TripCardData copy(@NotNull String title, @NotNull String value, @NotNull String valueSuffix, int i2) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(valueSuffix, "valueSuffix");
        return new TripCardData(title, value, valueSuffix, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TripCardData) {
            TripCardData tripCardData = (TripCardData) obj;
            return Intrinsics.areEqual(this.title, tripCardData.title) && Intrinsics.areEqual(this.value, tripCardData.value) && Intrinsics.areEqual(this.valueSuffix, tripCardData.valueSuffix) && this.icon == tripCardData.icon;
        }
        return false;
    }

    public final int getIcon() {
        return this.icon;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }

    @NotNull
    public final String getValueSuffix() {
        return this.valueSuffix;
    }

    public int hashCode() {
        return (((((this.title.hashCode() * 31) + this.value.hashCode()) * 31) + this.valueSuffix.hashCode()) * 31) + Integer.hashCode(this.icon);
    }

    public final void setIcon(int i2) {
        this.icon = i2;
    }

    public final void setTitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final void setValue(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.value = str;
    }

    public final void setValueSuffix(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.valueSuffix = str;
    }

    @NotNull
    public String toString() {
        return "TripCardData(title=" + this.title + ", value=" + this.value + ", valueSuffix=" + this.valueSuffix + ", icon=" + this.icon + ')';
    }
}
