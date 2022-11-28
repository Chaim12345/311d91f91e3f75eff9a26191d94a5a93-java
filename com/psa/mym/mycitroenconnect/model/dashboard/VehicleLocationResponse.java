package com.psa.mym.mycitroenconnect.model.dashboard;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class VehicleLocationResponse {
    @SerializedName("coordinate")
    @NotNull
    private Coordinate coordinate;

    public VehicleLocationResponse() {
        this(null, 1, null);
    }

    public VehicleLocationResponse(@NotNull Coordinate coordinate) {
        Intrinsics.checkNotNullParameter(coordinate, "coordinate");
        this.coordinate = coordinate;
    }

    public /* synthetic */ VehicleLocationResponse(Coordinate coordinate, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? new Coordinate(0.0d, 0.0d, 3, null) : coordinate);
    }

    public static /* synthetic */ VehicleLocationResponse copy$default(VehicleLocationResponse vehicleLocationResponse, Coordinate coordinate, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            coordinate = vehicleLocationResponse.coordinate;
        }
        return vehicleLocationResponse.copy(coordinate);
    }

    @NotNull
    public final Coordinate component1() {
        return this.coordinate;
    }

    @NotNull
    public final VehicleLocationResponse copy(@NotNull Coordinate coordinate) {
        Intrinsics.checkNotNullParameter(coordinate, "coordinate");
        return new VehicleLocationResponse(coordinate);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof VehicleLocationResponse) && Intrinsics.areEqual(this.coordinate, ((VehicleLocationResponse) obj).coordinate);
    }

    @NotNull
    public final Coordinate getCoordinate() {
        return this.coordinate;
    }

    public int hashCode() {
        return this.coordinate.hashCode();
    }

    public final void setCoordinate(@NotNull Coordinate coordinate) {
        Intrinsics.checkNotNullParameter(coordinate, "<set-?>");
        this.coordinate = coordinate;
    }

    @NotNull
    public String toString() {
        return "VehicleLocationResponse(coordinate=" + this.coordinate + ')';
    }
}
