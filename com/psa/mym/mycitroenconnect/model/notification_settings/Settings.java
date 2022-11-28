package com.psa.mym.mycitroenconnect.model.notification_settings;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class Settings {
    @SerializedName("alertStatus")
    @NotNull
    private String alertStatus;
    @SerializedName("alertType")
    @NotNull
    private String alertType;
    @SerializedName("endTimeStamp")
    private long endTimeStamp;
    @SerializedName("snoozePriod")
    @NotNull
    private String snoozePeriod;
    @SerializedName("startTimeStamp")
    private long startTimeStamp;

    public Settings(@NotNull String alertType, @NotNull String alertStatus, @NotNull String snoozePeriod, long j2, long j3) {
        Intrinsics.checkNotNullParameter(alertType, "alertType");
        Intrinsics.checkNotNullParameter(alertStatus, "alertStatus");
        Intrinsics.checkNotNullParameter(snoozePeriod, "snoozePeriod");
        this.alertType = alertType;
        this.alertStatus = alertStatus;
        this.snoozePeriod = snoozePeriod;
        this.startTimeStamp = j2;
        this.endTimeStamp = j3;
    }

    public static /* synthetic */ Settings copy$default(Settings settings, String str, String str2, String str3, long j2, long j3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = settings.alertType;
        }
        if ((i2 & 2) != 0) {
            str2 = settings.alertStatus;
        }
        String str4 = str2;
        if ((i2 & 4) != 0) {
            str3 = settings.snoozePeriod;
        }
        String str5 = str3;
        if ((i2 & 8) != 0) {
            j2 = settings.startTimeStamp;
        }
        long j4 = j2;
        if ((i2 & 16) != 0) {
            j3 = settings.endTimeStamp;
        }
        return settings.copy(str, str4, str5, j4, j3);
    }

    @NotNull
    public final String component1() {
        return this.alertType;
    }

    @NotNull
    public final String component2() {
        return this.alertStatus;
    }

    @NotNull
    public final String component3() {
        return this.snoozePeriod;
    }

    public final long component4() {
        return this.startTimeStamp;
    }

    public final long component5() {
        return this.endTimeStamp;
    }

    @NotNull
    public final Settings copy(@NotNull String alertType, @NotNull String alertStatus, @NotNull String snoozePeriod, long j2, long j3) {
        Intrinsics.checkNotNullParameter(alertType, "alertType");
        Intrinsics.checkNotNullParameter(alertStatus, "alertStatus");
        Intrinsics.checkNotNullParameter(snoozePeriod, "snoozePeriod");
        return new Settings(alertType, alertStatus, snoozePeriod, j2, j3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Settings) {
            Settings settings = (Settings) obj;
            return Intrinsics.areEqual(this.alertType, settings.alertType) && Intrinsics.areEqual(this.alertStatus, settings.alertStatus) && Intrinsics.areEqual(this.snoozePeriod, settings.snoozePeriod) && this.startTimeStamp == settings.startTimeStamp && this.endTimeStamp == settings.endTimeStamp;
        }
        return false;
    }

    @NotNull
    public final String getAlertStatus() {
        return this.alertStatus;
    }

    @NotNull
    public final String getAlertType() {
        return this.alertType;
    }

    public final long getEndTimeStamp() {
        return this.endTimeStamp;
    }

    @NotNull
    public final String getSnoozePeriod() {
        return this.snoozePeriod;
    }

    public final long getStartTimeStamp() {
        return this.startTimeStamp;
    }

    public int hashCode() {
        return (((((((this.alertType.hashCode() * 31) + this.alertStatus.hashCode()) * 31) + this.snoozePeriod.hashCode()) * 31) + Long.hashCode(this.startTimeStamp)) * 31) + Long.hashCode(this.endTimeStamp);
    }

    public final void setAlertStatus(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.alertStatus = str;
    }

    public final void setAlertType(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.alertType = str;
    }

    public final void setEndTimeStamp(long j2) {
        this.endTimeStamp = j2;
    }

    public final void setSnoozePeriod(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.snoozePeriod = str;
    }

    public final void setStartTimeStamp(long j2) {
        this.startTimeStamp = j2;
    }

    @NotNull
    public String toString() {
        return "Settings(alertType=" + this.alertType + ", alertStatus=" + this.alertStatus + ", snoozePeriod=" + this.snoozePeriod + ", startTimeStamp=" + this.startTimeStamp + ", endTimeStamp=" + this.endTimeStamp + ')';
    }
}
