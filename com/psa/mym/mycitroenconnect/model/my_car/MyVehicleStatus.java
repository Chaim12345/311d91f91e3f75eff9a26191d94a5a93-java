package com.psa.mym.mycitroenconnect.model.my_car;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class MyVehicleStatus {
    private final int icon;
    private final int iconBackgroundColor;
    @NotNull
    private final String status;
    @NotNull
    private final String statusLabel;

    public MyVehicleStatus(@NotNull String statusLabel, @NotNull String status, @ColorRes int i2, @DrawableRes int i3) {
        Intrinsics.checkNotNullParameter(statusLabel, "statusLabel");
        Intrinsics.checkNotNullParameter(status, "status");
        this.statusLabel = statusLabel;
        this.status = status;
        this.iconBackgroundColor = i2;
        this.icon = i3;
    }

    public static /* synthetic */ MyVehicleStatus copy$default(MyVehicleStatus myVehicleStatus, String str, String str2, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = myVehicleStatus.statusLabel;
        }
        if ((i4 & 2) != 0) {
            str2 = myVehicleStatus.status;
        }
        if ((i4 & 4) != 0) {
            i2 = myVehicleStatus.iconBackgroundColor;
        }
        if ((i4 & 8) != 0) {
            i3 = myVehicleStatus.icon;
        }
        return myVehicleStatus.copy(str, str2, i2, i3);
    }

    @NotNull
    public final String component1() {
        return this.statusLabel;
    }

    @NotNull
    public final String component2() {
        return this.status;
    }

    public final int component3() {
        return this.iconBackgroundColor;
    }

    public final int component4() {
        return this.icon;
    }

    @NotNull
    public final MyVehicleStatus copy(@NotNull String statusLabel, @NotNull String status, @ColorRes int i2, @DrawableRes int i3) {
        Intrinsics.checkNotNullParameter(statusLabel, "statusLabel");
        Intrinsics.checkNotNullParameter(status, "status");
        return new MyVehicleStatus(statusLabel, status, i2, i3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MyVehicleStatus) {
            MyVehicleStatus myVehicleStatus = (MyVehicleStatus) obj;
            return Intrinsics.areEqual(this.statusLabel, myVehicleStatus.statusLabel) && Intrinsics.areEqual(this.status, myVehicleStatus.status) && this.iconBackgroundColor == myVehicleStatus.iconBackgroundColor && this.icon == myVehicleStatus.icon;
        }
        return false;
    }

    public final int getIcon() {
        return this.icon;
    }

    public final int getIconBackgroundColor() {
        return this.iconBackgroundColor;
    }

    @NotNull
    public final String getStatus() {
        return this.status;
    }

    @NotNull
    public final String getStatusLabel() {
        return this.statusLabel;
    }

    public int hashCode() {
        return (((((this.statusLabel.hashCode() * 31) + this.status.hashCode()) * 31) + Integer.hashCode(this.iconBackgroundColor)) * 31) + Integer.hashCode(this.icon);
    }

    @NotNull
    public String toString() {
        return "MyVehicleStatus(statusLabel=" + this.statusLabel + ", status=" + this.status + ", iconBackgroundColor=" + this.iconBackgroundColor + ", icon=" + this.icon + ')';
    }
}
