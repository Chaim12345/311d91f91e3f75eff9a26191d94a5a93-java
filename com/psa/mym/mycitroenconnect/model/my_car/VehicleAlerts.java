package com.psa.mym.mycitroenconnect.model.my_car;

import androidx.annotation.ColorRes;
import com.psa.mym.mycitroenconnect.model.notification.Notification;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class VehicleAlerts {
    private int alertCounts;
    @NotNull
    private String alertLabel;
    @NotNull
    private final List<Notification> alerts;
    private int backgroundColor;
    private int countTextColor;
    private boolean isExpanded;
    @Nullable
    private String notifPriority;

    public VehicleAlerts(int i2, @ColorRes int i3, @ColorRes int i4, @NotNull String alertLabel, @NotNull List<Notification> alerts, boolean z, @Nullable String str) {
        Intrinsics.checkNotNullParameter(alertLabel, "alertLabel");
        Intrinsics.checkNotNullParameter(alerts, "alerts");
        this.alertCounts = i2;
        this.backgroundColor = i3;
        this.countTextColor = i4;
        this.alertLabel = alertLabel;
        this.alerts = alerts;
        this.isExpanded = z;
        this.notifPriority = str;
    }

    public /* synthetic */ VehicleAlerts(int i2, int i3, int i4, String str, List list, boolean z, String str2, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2, i3, i4, str, list, (i5 & 32) != 0 ? false : z, (i5 & 64) != 0 ? "" : str2);
    }

    public static /* synthetic */ VehicleAlerts copy$default(VehicleAlerts vehicleAlerts, int i2, int i3, int i4, String str, List list, boolean z, String str2, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i2 = vehicleAlerts.alertCounts;
        }
        if ((i5 & 2) != 0) {
            i3 = vehicleAlerts.backgroundColor;
        }
        int i6 = i3;
        if ((i5 & 4) != 0) {
            i4 = vehicleAlerts.countTextColor;
        }
        int i7 = i4;
        if ((i5 & 8) != 0) {
            str = vehicleAlerts.alertLabel;
        }
        String str3 = str;
        List<Notification> list2 = list;
        if ((i5 & 16) != 0) {
            list2 = vehicleAlerts.alerts;
        }
        List list3 = list2;
        if ((i5 & 32) != 0) {
            z = vehicleAlerts.isExpanded;
        }
        boolean z2 = z;
        if ((i5 & 64) != 0) {
            str2 = vehicleAlerts.notifPriority;
        }
        return vehicleAlerts.copy(i2, i6, i7, str3, list3, z2, str2);
    }

    public final int component1() {
        return this.alertCounts;
    }

    public final int component2() {
        return this.backgroundColor;
    }

    public final int component3() {
        return this.countTextColor;
    }

    @NotNull
    public final String component4() {
        return this.alertLabel;
    }

    @NotNull
    public final List<Notification> component5() {
        return this.alerts;
    }

    public final boolean component6() {
        return this.isExpanded;
    }

    @Nullable
    public final String component7() {
        return this.notifPriority;
    }

    @NotNull
    public final VehicleAlerts copy(int i2, @ColorRes int i3, @ColorRes int i4, @NotNull String alertLabel, @NotNull List<Notification> alerts, boolean z, @Nullable String str) {
        Intrinsics.checkNotNullParameter(alertLabel, "alertLabel");
        Intrinsics.checkNotNullParameter(alerts, "alerts");
        return new VehicleAlerts(i2, i3, i4, alertLabel, alerts, z, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof VehicleAlerts) {
            VehicleAlerts vehicleAlerts = (VehicleAlerts) obj;
            return this.alertCounts == vehicleAlerts.alertCounts && this.backgroundColor == vehicleAlerts.backgroundColor && this.countTextColor == vehicleAlerts.countTextColor && Intrinsics.areEqual(this.alertLabel, vehicleAlerts.alertLabel) && Intrinsics.areEqual(this.alerts, vehicleAlerts.alerts) && this.isExpanded == vehicleAlerts.isExpanded && Intrinsics.areEqual(this.notifPriority, vehicleAlerts.notifPriority);
        }
        return false;
    }

    public final int getAlertCounts() {
        return this.alertCounts;
    }

    @NotNull
    public final String getAlertLabel() {
        return this.alertLabel;
    }

    @NotNull
    public final List<Notification> getAlerts() {
        return this.alerts;
    }

    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    public final int getCountTextColor() {
        return this.countTextColor;
    }

    @Nullable
    public final String getNotifPriority() {
        return this.notifPriority;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((((((Integer.hashCode(this.alertCounts) * 31) + Integer.hashCode(this.backgroundColor)) * 31) + Integer.hashCode(this.countTextColor)) * 31) + this.alertLabel.hashCode()) * 31) + this.alerts.hashCode()) * 31;
        boolean z = this.isExpanded;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        int i3 = (hashCode + i2) * 31;
        String str = this.notifPriority;
        return i3 + (str == null ? 0 : str.hashCode());
    }

    public final boolean isExpanded() {
        return this.isExpanded;
    }

    public final void setAlertCounts(int i2) {
        this.alertCounts = i2;
    }

    public final void setAlertLabel(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.alertLabel = str;
    }

    public final void setBackgroundColor(int i2) {
        this.backgroundColor = i2;
    }

    public final void setCountTextColor(int i2) {
        this.countTextColor = i2;
    }

    public final void setExpanded(boolean z) {
        this.isExpanded = z;
    }

    public final void setNotifPriority(@Nullable String str) {
        this.notifPriority = str;
    }

    @NotNull
    public String toString() {
        return "VehicleAlerts(alertCounts=" + this.alertCounts + ", backgroundColor=" + this.backgroundColor + ", countTextColor=" + this.countTextColor + ", alertLabel=" + this.alertLabel + ", alerts=" + this.alerts + ", isExpanded=" + this.isExpanded + ", notifPriority=" + this.notifPriority + ')';
    }
}
