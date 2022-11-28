package io.opencensus.metrics.export;

import io.opencensus.common.Timestamp;
import io.opencensus.metrics.LabelValue;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class AutoValue_TimeSeries extends TimeSeries {
    private final List<LabelValue> labelValues;
    private final List<Point> points;
    private final Timestamp startTimestamp;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_TimeSeries(List list, List list2, @Nullable Timestamp timestamp) {
        Objects.requireNonNull(list, "Null labelValues");
        this.labelValues = list;
        Objects.requireNonNull(list2, "Null points");
        this.points = list2;
        this.startTimestamp = timestamp;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TimeSeries) {
            TimeSeries timeSeries = (TimeSeries) obj;
            if (this.labelValues.equals(timeSeries.getLabelValues()) && this.points.equals(timeSeries.getPoints())) {
                Timestamp timestamp = this.startTimestamp;
                Timestamp startTimestamp = timeSeries.getStartTimestamp();
                if (timestamp == null) {
                    if (startTimestamp == null) {
                        return true;
                    }
                } else if (timestamp.equals(startTimestamp)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // io.opencensus.metrics.export.TimeSeries
    public List<LabelValue> getLabelValues() {
        return this.labelValues;
    }

    @Override // io.opencensus.metrics.export.TimeSeries
    public List<Point> getPoints() {
        return this.points;
    }

    @Override // io.opencensus.metrics.export.TimeSeries
    @Nullable
    public Timestamp getStartTimestamp() {
        return this.startTimestamp;
    }

    public int hashCode() {
        int hashCode = (((this.labelValues.hashCode() ^ 1000003) * 1000003) ^ this.points.hashCode()) * 1000003;
        Timestamp timestamp = this.startTimestamp;
        return hashCode ^ (timestamp == null ? 0 : timestamp.hashCode());
    }

    public String toString() {
        return "TimeSeries{labelValues=" + this.labelValues + ", points=" + this.points + ", startTimestamp=" + this.startTimestamp + "}";
    }
}
