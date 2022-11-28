package com.psa.mym.mycitroenconnect.model.trip;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class EnergyUsageResponseItem {
    @SerializedName("date")
    @NotNull
    private String date;
    @SerializedName("totalEnergyConsumption")
    private double totalEnergyConsumption;
    @SerializedName("totalIdleEnergyConsumption")
    private double totalIdleEnergyConsumption;

    public EnergyUsageResponseItem() {
        this(null, 0.0d, 0.0d, 7, null);
    }

    public EnergyUsageResponseItem(@NotNull String date, double d2, double d3) {
        Intrinsics.checkNotNullParameter(date, "date");
        this.date = date;
        this.totalEnergyConsumption = d2;
        this.totalIdleEnergyConsumption = d3;
    }

    public /* synthetic */ EnergyUsageResponseItem(String str, double d2, double d3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? 0.0d : d2, (i2 & 4) != 0 ? 0.0d : d3);
    }

    public static /* synthetic */ EnergyUsageResponseItem copy$default(EnergyUsageResponseItem energyUsageResponseItem, String str, double d2, double d3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = energyUsageResponseItem.date;
        }
        if ((i2 & 2) != 0) {
            d2 = energyUsageResponseItem.totalEnergyConsumption;
        }
        double d4 = d2;
        if ((i2 & 4) != 0) {
            d3 = energyUsageResponseItem.totalIdleEnergyConsumption;
        }
        return energyUsageResponseItem.copy(str, d4, d3);
    }

    @NotNull
    public final String component1() {
        return this.date;
    }

    public final double component2() {
        return this.totalEnergyConsumption;
    }

    public final double component3() {
        return this.totalIdleEnergyConsumption;
    }

    @NotNull
    public final EnergyUsageResponseItem copy(@NotNull String date, double d2, double d3) {
        Intrinsics.checkNotNullParameter(date, "date");
        return new EnergyUsageResponseItem(date, d2, d3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EnergyUsageResponseItem) {
            EnergyUsageResponseItem energyUsageResponseItem = (EnergyUsageResponseItem) obj;
            return Intrinsics.areEqual(this.date, energyUsageResponseItem.date) && Intrinsics.areEqual((Object) Double.valueOf(this.totalEnergyConsumption), (Object) Double.valueOf(energyUsageResponseItem.totalEnergyConsumption)) && Intrinsics.areEqual((Object) Double.valueOf(this.totalIdleEnergyConsumption), (Object) Double.valueOf(energyUsageResponseItem.totalIdleEnergyConsumption));
        }
        return false;
    }

    @NotNull
    public final String getDate() {
        return this.date;
    }

    public final double getTotalEnergyConsumption() {
        return this.totalEnergyConsumption;
    }

    public final double getTotalIdleEnergyConsumption() {
        return this.totalIdleEnergyConsumption;
    }

    public int hashCode() {
        return (((this.date.hashCode() * 31) + Double.hashCode(this.totalEnergyConsumption)) * 31) + Double.hashCode(this.totalIdleEnergyConsumption);
    }

    public final void setDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.date = str;
    }

    public final void setTotalEnergyConsumption(double d2) {
        this.totalEnergyConsumption = d2;
    }

    public final void setTotalIdleEnergyConsumption(double d2) {
        this.totalIdleEnergyConsumption = d2;
    }

    @NotNull
    public String toString() {
        return "EnergyUsageResponseItem(date=" + this.date + ", totalEnergyConsumption=" + this.totalEnergyConsumption + ", totalIdleEnergyConsumption=" + this.totalIdleEnergyConsumption + ')';
    }
}
