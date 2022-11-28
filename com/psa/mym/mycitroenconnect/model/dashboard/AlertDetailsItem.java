package com.psa.mym.mycitroenconnect.model.dashboard;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class AlertDetailsItem {
    @SerializedName("gpsAlt")
    @NotNull
    private String gpsAlt;
    @SerializedName("gpsLat")
    @NotNull
    private String gpsLat;
    @SerializedName("gpsLong")
    @NotNull
    private String gpsLong;

    public AlertDetailsItem() {
        this(null, null, null, 7, null);
    }

    public AlertDetailsItem(@NotNull String gpsLat, @NotNull String gpsLong, @NotNull String gpsAlt) {
        Intrinsics.checkNotNullParameter(gpsLat, "gpsLat");
        Intrinsics.checkNotNullParameter(gpsLong, "gpsLong");
        Intrinsics.checkNotNullParameter(gpsAlt, "gpsAlt");
        this.gpsLat = gpsLat;
        this.gpsLong = gpsLong;
        this.gpsAlt = gpsAlt;
    }

    public /* synthetic */ AlertDetailsItem(String str, String str2, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3);
    }

    public static /* synthetic */ AlertDetailsItem copy$default(AlertDetailsItem alertDetailsItem, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = alertDetailsItem.gpsLat;
        }
        if ((i2 & 2) != 0) {
            str2 = alertDetailsItem.gpsLong;
        }
        if ((i2 & 4) != 0) {
            str3 = alertDetailsItem.gpsAlt;
        }
        return alertDetailsItem.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.gpsLat;
    }

    @NotNull
    public final String component2() {
        return this.gpsLong;
    }

    @NotNull
    public final String component3() {
        return this.gpsAlt;
    }

    @NotNull
    public final AlertDetailsItem copy(@NotNull String gpsLat, @NotNull String gpsLong, @NotNull String gpsAlt) {
        Intrinsics.checkNotNullParameter(gpsLat, "gpsLat");
        Intrinsics.checkNotNullParameter(gpsLong, "gpsLong");
        Intrinsics.checkNotNullParameter(gpsAlt, "gpsAlt");
        return new AlertDetailsItem(gpsLat, gpsLong, gpsAlt);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AlertDetailsItem) {
            AlertDetailsItem alertDetailsItem = (AlertDetailsItem) obj;
            return Intrinsics.areEqual(this.gpsLat, alertDetailsItem.gpsLat) && Intrinsics.areEqual(this.gpsLong, alertDetailsItem.gpsLong) && Intrinsics.areEqual(this.gpsAlt, alertDetailsItem.gpsAlt);
        }
        return false;
    }

    @NotNull
    public final String getGpsAlt() {
        return this.gpsAlt;
    }

    @NotNull
    public final String getGpsLat() {
        return this.gpsLat;
    }

    @NotNull
    public final String getGpsLong() {
        return this.gpsLong;
    }

    public int hashCode() {
        return (((this.gpsLat.hashCode() * 31) + this.gpsLong.hashCode()) * 31) + this.gpsAlt.hashCode();
    }

    public final void setGpsAlt(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.gpsAlt = str;
    }

    public final void setGpsLat(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.gpsLat = str;
    }

    public final void setGpsLong(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.gpsLong = str;
    }

    @NotNull
    public String toString() {
        return "AlertDetailsItem(gpsLat=" + this.gpsLat + ", gpsLong=" + this.gpsLong + ", gpsAlt=" + this.gpsAlt + ')';
    }
}
