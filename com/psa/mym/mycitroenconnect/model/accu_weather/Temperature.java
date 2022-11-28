package com.psa.mym.mycitroenconnect.model.accu_weather;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class Temperature {
    @SerializedName("Imperial")
    @NotNull
    private final Imperial imperial;
    @SerializedName("Metric")
    @NotNull
    private final Metric metric;

    public Temperature(@NotNull Imperial imperial, @NotNull Metric metric) {
        Intrinsics.checkNotNullParameter(imperial, "imperial");
        Intrinsics.checkNotNullParameter(metric, "metric");
        this.imperial = imperial;
        this.metric = metric;
    }

    public static /* synthetic */ Temperature copy$default(Temperature temperature, Imperial imperial, Metric metric, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            imperial = temperature.imperial;
        }
        if ((i2 & 2) != 0) {
            metric = temperature.metric;
        }
        return temperature.copy(imperial, metric);
    }

    @NotNull
    public final Imperial component1() {
        return this.imperial;
    }

    @NotNull
    public final Metric component2() {
        return this.metric;
    }

    @NotNull
    public final Temperature copy(@NotNull Imperial imperial, @NotNull Metric metric) {
        Intrinsics.checkNotNullParameter(imperial, "imperial");
        Intrinsics.checkNotNullParameter(metric, "metric");
        return new Temperature(imperial, metric);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Temperature) {
            Temperature temperature = (Temperature) obj;
            return Intrinsics.areEqual(this.imperial, temperature.imperial) && Intrinsics.areEqual(this.metric, temperature.metric);
        }
        return false;
    }

    @NotNull
    public final Imperial getImperial() {
        return this.imperial;
    }

    @NotNull
    public final Metric getMetric() {
        return this.metric;
    }

    public int hashCode() {
        return (this.imperial.hashCode() * 31) + this.metric.hashCode();
    }

    @NotNull
    public String toString() {
        return "Temperature(imperial=" + this.imperial + ", metric=" + this.metric + ')';
    }
}
