package io.opencensus.stats;

import io.opencensus.stats.Measure;
import io.opencensus.stats.Measurement;
import java.util.Objects;
/* loaded from: classes3.dex */
final class AutoValue_Measurement_MeasurementLong extends Measurement.MeasurementLong {
    private final Measure.MeasureLong measure;
    private final long value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Measurement_MeasurementLong(Measure.MeasureLong measureLong, long j2) {
        Objects.requireNonNull(measureLong, "Null measure");
        this.measure = measureLong;
        this.value = j2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Measurement.MeasurementLong) {
            Measurement.MeasurementLong measurementLong = (Measurement.MeasurementLong) obj;
            return this.measure.equals(measurementLong.getMeasure()) && this.value == measurementLong.getValue();
        }
        return false;
    }

    @Override // io.opencensus.stats.Measurement.MeasurementLong, io.opencensus.stats.Measurement
    public Measure.MeasureLong getMeasure() {
        return this.measure;
    }

    @Override // io.opencensus.stats.Measurement.MeasurementLong
    public long getValue() {
        return this.value;
    }

    public int hashCode() {
        long j2 = this.value;
        return (int) (((this.measure.hashCode() ^ 1000003) * 1000003) ^ (j2 ^ (j2 >>> 32)));
    }

    public String toString() {
        return "MeasurementLong{measure=" + this.measure + ", value=" + this.value + "}";
    }
}
