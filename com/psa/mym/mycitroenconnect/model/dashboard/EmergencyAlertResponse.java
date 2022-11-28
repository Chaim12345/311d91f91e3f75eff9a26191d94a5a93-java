package com.psa.mym.mycitroenconnect.model.dashboard;

import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class EmergencyAlertResponse {
    @SerializedName(AppConstants.ARG_MESSAGE)
    @NotNull
    private String message;
    @SerializedName("statusCode")
    private final int statusCode;

    public EmergencyAlertResponse() {
        this(null, 0, 3, null);
    }

    public EmergencyAlertResponse(@NotNull String message, int i2) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.message = message;
        this.statusCode = i2;
    }

    public /* synthetic */ EmergencyAlertResponse(String str, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "" : str, (i3 & 2) != 0 ? 0 : i2);
    }

    public static /* synthetic */ EmergencyAlertResponse copy$default(EmergencyAlertResponse emergencyAlertResponse, String str, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = emergencyAlertResponse.message;
        }
        if ((i3 & 2) != 0) {
            i2 = emergencyAlertResponse.statusCode;
        }
        return emergencyAlertResponse.copy(str, i2);
    }

    @NotNull
    public final String component1() {
        return this.message;
    }

    public final int component2() {
        return this.statusCode;
    }

    @NotNull
    public final EmergencyAlertResponse copy(@NotNull String message, int i2) {
        Intrinsics.checkNotNullParameter(message, "message");
        return new EmergencyAlertResponse(message, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EmergencyAlertResponse) {
            EmergencyAlertResponse emergencyAlertResponse = (EmergencyAlertResponse) obj;
            return Intrinsics.areEqual(this.message, emergencyAlertResponse.message) && this.statusCode == emergencyAlertResponse.statusCode;
        }
        return false;
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public int hashCode() {
        return (this.message.hashCode() * 31) + Integer.hashCode(this.statusCode);
    }

    public final void setMessage(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.message = str;
    }

    @NotNull
    public String toString() {
        return "EmergencyAlertResponse(message=" + this.message + ", statusCode=" + this.statusCode + ')';
    }
}
