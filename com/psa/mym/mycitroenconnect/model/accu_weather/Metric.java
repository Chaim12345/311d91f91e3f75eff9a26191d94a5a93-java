package com.psa.mym.mycitroenconnect.model.accu_weather;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class Metric {
    @SerializedName("Unit")
    @NotNull
    private final String unit;
    @SerializedName("UnitType")
    private final int unitType;
    @SerializedName("Value")
    private final double value;

    public Metric(@NotNull String unit, int i2, double d2) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        this.unit = unit;
        this.unitType = i2;
        this.value = d2;
    }

    public static /* synthetic */ Metric copy$default(Metric metric, String str, int i2, double d2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = metric.unit;
        }
        if ((i3 & 2) != 0) {
            i2 = metric.unitType;
        }
        if ((i3 & 4) != 0) {
            d2 = metric.value;
        }
        return metric.copy(str, i2, d2);
    }

    @NotNull
    public final String component1() {
        return this.unit;
    }

    public final int component2() {
        return this.unitType;
    }

    public final double component3() {
        return this.value;
    }

    @NotNull
    public final Metric copy(@NotNull String unit, int i2, double d2) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return new Metric(unit, i2, d2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Metric) {
            Metric metric = (Metric) obj;
            return Intrinsics.areEqual(this.unit, metric.unit) && this.unitType == metric.unitType && Intrinsics.areEqual((Object) Double.valueOf(this.value), (Object) Double.valueOf(metric.value));
        }
        return false;
    }

    @NotNull
    public final String getUnit() {
        return this.unit;
    }

    public final int getUnitType() {
        return this.unitType;
    }

    public final double getValue() {
        return this.value;
    }

    public int hashCode() {
        return (((this.unit.hashCode() * 31) + Integer.hashCode(this.unitType)) * 31) + Double.hashCode(this.value);
    }

    @NotNull
    public String toString() {
        return "Metric(unit=" + this.unit + ", unitType=" + this.unitType + ", value=" + this.value + ')';
    }
}
