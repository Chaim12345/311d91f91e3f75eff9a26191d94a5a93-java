package com.psa.mym.mycitroenconnect.model.onboarding;

import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class AddVehicleResponse {
    @SerializedName(AppConstants.ARG_MESSAGE)
    @NotNull
    private final String message;
    @SerializedName("statusCode")
    private final int statusCode;

    public AddVehicleResponse(@NotNull String message, int i2) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.message = message;
        this.statusCode = i2;
    }

    public static /* synthetic */ AddVehicleResponse copy$default(AddVehicleResponse addVehicleResponse, String str, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = addVehicleResponse.message;
        }
        if ((i3 & 2) != 0) {
            i2 = addVehicleResponse.statusCode;
        }
        return addVehicleResponse.copy(str, i2);
    }

    @NotNull
    public final String component1() {
        return this.message;
    }

    public final int component2() {
        return this.statusCode;
    }

    @NotNull
    public final AddVehicleResponse copy(@NotNull String message, int i2) {
        Intrinsics.checkNotNullParameter(message, "message");
        return new AddVehicleResponse(message, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AddVehicleResponse) {
            AddVehicleResponse addVehicleResponse = (AddVehicleResponse) obj;
            return Intrinsics.areEqual(this.message, addVehicleResponse.message) && this.statusCode == addVehicleResponse.statusCode;
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
        return "AddVehicleResponse(message=" + this.message + ", statusCode=" + this.statusCode + ')';
    }
}
