package com.psa.mym.mycitroenconnect.model.dashboard;

import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class EmergencyContactResponse {
    @SerializedName(AppConstants.ARG_MESSAGE)
    @NotNull
    private final String message;
    @SerializedName("statusCode")
    private final int statusCode;

    public EmergencyContactResponse(@NotNull String message, int i2) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.message = message;
        this.statusCode = i2;
    }

    public static /* synthetic */ EmergencyContactResponse copy$default(EmergencyContactResponse emergencyContactResponse, String str, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = emergencyContactResponse.message;
        }
        if ((i3 & 2) != 0) {
            i2 = emergencyContactResponse.statusCode;
        }
        return emergencyContactResponse.copy(str, i2);
    }

    @NotNull
    public final String component1() {
        return this.message;
    }

    public final int component2() {
        return this.statusCode;
    }

    @NotNull
    public final EmergencyContactResponse copy(@NotNull String message, int i2) {
        Intrinsics.checkNotNullParameter(message, "message");
        return new EmergencyContactResponse(message, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EmergencyContactResponse) {
            EmergencyContactResponse emergencyContactResponse = (EmergencyContactResponse) obj;
            return Intrinsics.areEqual(this.message, emergencyContactResponse.message) && this.statusCode == emergencyContactResponse.statusCode;
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

    @NotNull
    public String toString() {
        return "EmergencyContactResponse(message=" + this.message + ", statusCode=" + this.statusCode + ')';
    }
}
