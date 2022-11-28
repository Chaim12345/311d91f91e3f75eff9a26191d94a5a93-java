package com.psa.mym.mycitroenconnect.model.accu_weather;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class Imperial {
    @SerializedName("Unit")
    @NotNull
    private final String unit;
    @SerializedName("UnitType")
    private final int unitType;
    @SerializedName("Value")
    private final double value;

    public Imperial(@NotNull String unit, int i2, double d2) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        this.unit = unit;
        this.unitType = i2;
        this.value = d2;
    }

    public static /* synthetic */ Imperial copy$default(Imperial imperial, String str, int i2, double d2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = imperial.unit;
        }
        if ((i3 & 2) != 0) {
            i2 = imperial.unitType;
        }
        if ((i3 & 4) != 0) {
            d2 = imperial.value;
        }
        return imperial.copy(str, i2, d2);
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
    public final Imperial copy(@NotNull String unit, int i2, double d2) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return new Imperial(unit, i2, d2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Imperial) {
            Imperial imperial = (Imperial) obj;
            return Intrinsics.areEqual(this.unit, imperial.unit) && this.unitType == imperial.unitType && Intrinsics.areEqual((Object) Double.valueOf(this.value), (Object) Double.valueOf(imperial.value));
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
        return "Imperial(unit=" + this.unit + ", unitType=" + this.unitType + ", value=" + this.value + ')';
    }
}
