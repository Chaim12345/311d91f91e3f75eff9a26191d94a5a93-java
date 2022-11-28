package com.psa.mym.mycitroenconnect.model.my_car;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public final class VehicleHealthDetails {
    @NotNull
    private final JSONObject vehicleStateHealthObj;

    public VehicleHealthDetails(@NotNull JSONObject vehicleStateHealthObj) {
        Intrinsics.checkNotNullParameter(vehicleStateHealthObj, "vehicleStateHealthObj");
        this.vehicleStateHealthObj = vehicleStateHealthObj;
    }

    public static /* synthetic */ VehicleHealthDetails copy$default(VehicleHealthDetails vehicleHealthDetails, JSONObject jSONObject, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            jSONObject = vehicleHealthDetails.vehicleStateHealthObj;
        }
        return vehicleHealthDetails.copy(jSONObject);
    }

    @NotNull
    public final JSONObject component1() {
        return this.vehicleStateHealthObj;
    }

    @NotNull
    public final VehicleHealthDetails copy(@NotNull JSONObject vehicleStateHealthObj) {
        Intrinsics.checkNotNullParameter(vehicleStateHealthObj, "vehicleStateHealthObj");
        return new VehicleHealthDetails(vehicleStateHealthObj);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof VehicleHealthDetails) && Intrinsics.areEqual(this.vehicleStateHealthObj, ((VehicleHealthDetails) obj).vehicleStateHealthObj);
    }

    @NotNull
    public final JSONObject getVehicleStateHealthObj() {
        return this.vehicleStateHealthObj;
    }

    public int hashCode() {
        return this.vehicleStateHealthObj.hashCode();
    }

    @NotNull
    public String toString() {
        return "VehicleHealthDetails(vehicleStateHealthObj=" + this.vehicleStateHealthObj + ')';
    }
}
