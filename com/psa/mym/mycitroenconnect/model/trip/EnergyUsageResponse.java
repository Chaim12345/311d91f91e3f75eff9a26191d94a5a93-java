package com.psa.mym.mycitroenconnect.model.trip;

import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class EnergyUsageResponse {
    @NotNull
    private ArrayList<EnergyUsageResponseItem> energyUsageResponse;

    public EnergyUsageResponse() {
        this(null, 1, null);
    }

    public EnergyUsageResponse(@NotNull ArrayList<EnergyUsageResponseItem> energyUsageResponse) {
        Intrinsics.checkNotNullParameter(energyUsageResponse, "energyUsageResponse");
        this.energyUsageResponse = energyUsageResponse;
    }

    public /* synthetic */ EnergyUsageResponse(ArrayList arrayList, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? new ArrayList() : arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ EnergyUsageResponse copy$default(EnergyUsageResponse energyUsageResponse, ArrayList arrayList, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            arrayList = energyUsageResponse.energyUsageResponse;
        }
        return energyUsageResponse.copy(arrayList);
    }

    @NotNull
    public final ArrayList<EnergyUsageResponseItem> component1() {
        return this.energyUsageResponse;
    }

    @NotNull
    public final EnergyUsageResponse copy(@NotNull ArrayList<EnergyUsageResponseItem> energyUsageResponse) {
        Intrinsics.checkNotNullParameter(energyUsageResponse, "energyUsageResponse");
        return new EnergyUsageResponse(energyUsageResponse);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof EnergyUsageResponse) && Intrinsics.areEqual(this.energyUsageResponse, ((EnergyUsageResponse) obj).energyUsageResponse);
    }

    @NotNull
    public final ArrayList<EnergyUsageResponseItem> getEnergyUsageResponse() {
        return this.energyUsageResponse;
    }

    public int hashCode() {
        return this.energyUsageResponse.hashCode();
    }

    public final void setEnergyUsageResponse(@NotNull ArrayList<EnergyUsageResponseItem> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.energyUsageResponse = arrayList;
    }

    @NotNull
    public String toString() {
        return "EnergyUsageResponse(energyUsageResponse=" + this.energyUsageResponse + ')';
    }
}
