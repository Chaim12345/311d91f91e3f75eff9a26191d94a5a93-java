package com.psa.mym.mycitroenconnect.api.body.dashboard;

import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.model.dashboard.AlertDetailsItem;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class EmergencyAlertBody {
    @SerializedName("alertDetails")
    @Nullable
    private AlertDetailsItem alertDetails;
    @SerializedName("alertTime")
    @NotNull
    private String alertTime;
    @SerializedName("alertType")
    @NotNull
    private String alertType;
    @SerializedName("vehicleId")
    @NotNull
    private String vehicleId;

    public EmergencyAlertBody() {
        this(null, null, null, null, 15, null);
    }

    public EmergencyAlertBody(@NotNull String vehicleId, @NotNull String alertType, @Nullable AlertDetailsItem alertDetailsItem, @NotNull String alertTime) {
        Intrinsics.checkNotNullParameter(vehicleId, "vehicleId");
        Intrinsics.checkNotNullParameter(alertType, "alertType");
        Intrinsics.checkNotNullParameter(alertTime, "alertTime");
        this.vehicleId = vehicleId;
        this.alertType = alertType;
        this.alertDetails = alertDetailsItem;
        this.alertTime = alertTime;
    }

    public /* synthetic */ EmergencyAlertBody(String str, String str2, AlertDetailsItem alertDetailsItem, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? null : alertDetailsItem, (i2 & 8) != 0 ? "" : str3);
    }

    public static /* synthetic */ EmergencyAlertBody copy$default(EmergencyAlertBody emergencyAlertBody, String str, String str2, AlertDetailsItem alertDetailsItem, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = emergencyAlertBody.vehicleId;
        }
        if ((i2 & 2) != 0) {
            str2 = emergencyAlertBody.alertType;
        }
        if ((i2 & 4) != 0) {
            alertDetailsItem = emergencyAlertBody.alertDetails;
        }
        if ((i2 & 8) != 0) {
            str3 = emergencyAlertBody.alertTime;
        }
        return emergencyAlertBody.copy(str, str2, alertDetailsItem, str3);
    }

    @NotNull
    public final String component1() {
        return this.vehicleId;
    }

    @NotNull
    public final String component2() {
        return this.alertType;
    }

    @Nullable
    public final AlertDetailsItem component3() {
        return this.alertDetails;
    }

    @NotNull
    public final String component4() {
        return this.alertTime;
    }

    @NotNull
    public final EmergencyAlertBody copy(@NotNull String vehicleId, @NotNull String alertType, @Nullable AlertDetailsItem alertDetailsItem, @NotNull String alertTime) {
        Intrinsics.checkNotNullParameter(vehicleId, "vehicleId");
        Intrinsics.checkNotNullParameter(alertType, "alertType");
        Intrinsics.checkNotNullParameter(alertTime, "alertTime");
        return new EmergencyAlertBody(vehicleId, alertType, alertDetailsItem, alertTime);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EmergencyAlertBody) {
            EmergencyAlertBody emergencyAlertBody = (EmergencyAlertBody) obj;
            return Intrinsics.areEqual(this.vehicleId, emergencyAlertBody.vehicleId) && Intrinsics.areEqual(this.alertType, emergencyAlertBody.alertType) && Intrinsics.areEqual(this.alertDetails, emergencyAlertBody.alertDetails) && Intrinsics.areEqual(this.alertTime, emergencyAlertBody.alertTime);
        }
        return false;
    }

    @Nullable
    public final AlertDetailsItem getAlertDetails() {
        return this.alertDetails;
    }

    @NotNull
    public final String getAlertTime() {
        return this.alertTime;
    }

    @NotNull
    public final String getAlertType() {
        return this.alertType;
    }

    @NotNull
    public final String getVehicleId() {
        return this.vehicleId;
    }

    public int hashCode() {
        int hashCode = ((this.vehicleId.hashCode() * 31) + this.alertType.hashCode()) * 31;
        AlertDetailsItem alertDetailsItem = this.alertDetails;
        return ((hashCode + (alertDetailsItem == null ? 0 : alertDetailsItem.hashCode())) * 31) + this.alertTime.hashCode();
    }

    public final void setAlertDetails(@Nullable AlertDetailsItem alertDetailsItem) {
        this.alertDetails = alertDetailsItem;
    }

    public final void setAlertTime(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.alertTime = str;
    }

    public final void setAlertType(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.alertType = str;
    }

    public final void setVehicleId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vehicleId = str;
    }

    @NotNull
    public String toString() {
        return "EmergencyAlertBody(vehicleId=" + this.vehicleId + ", alertType=" + this.alertType + ", alertDetails=" + this.alertDetails + ", alertTime=" + this.alertTime + ')';
    }
}
