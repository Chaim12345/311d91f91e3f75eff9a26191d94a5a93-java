package io.opencensus.stats;

import io.opencensus.stats.Measure;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class AutoValue_Measure_MeasureDouble extends Measure.MeasureDouble {
    private final String description;
    private final String name;
    private final String unit;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Measure_MeasureDouble(String str, String str2, String str3) {
        Objects.requireNonNull(str, "Null name");
        this.name = str;
        Objects.requireNonNull(str2, "Null description");
        this.description = str2;
        Objects.requireNonNull(str3, "Null unit");
        this.unit = str3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Measure.MeasureDouble) {
            Measure.MeasureDouble measureDouble = (Measure.MeasureDouble) obj;
            return this.name.equals(measureDouble.getName()) && this.description.equals(measureDouble.getDescription()) && this.unit.equals(measureDouble.getUnit());
        }
        return false;
    }

    @Override // io.opencensus.stats.Measure.MeasureDouble, io.opencensus.stats.Measure
    public String getDescription() {
        return this.description;
    }

    @Override // io.opencensus.stats.Measure.MeasureDouble, io.opencensus.stats.Measure
    public String getName() {
        return this.name;
    }

    @Override // io.opencensus.stats.Measure.MeasureDouble, io.opencensus.stats.Measure
    public String getUnit() {
        return this.unit;
    }

    public int hashCode() {
        return ((((this.name.hashCode() ^ 1000003) * 1000003) ^ this.description.hashCode()) * 1000003) ^ this.unit.hashCode();
    }

    public String toString() {
        return "MeasureDouble{name=" + this.name + ", description=" + this.description + ", unit=" + this.unit + "}";
    }
}
