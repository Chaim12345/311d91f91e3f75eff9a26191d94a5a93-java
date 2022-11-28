package io.opencensus.metrics.export;

import io.opencensus.common.Timestamp;
import java.util.Objects;
/* loaded from: classes3.dex */
final class AutoValue_Point extends Point {
    private final Timestamp timestamp;
    private final Value value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Point(Value value, Timestamp timestamp) {
        Objects.requireNonNull(value, "Null value");
        this.value = value;
        Objects.requireNonNull(timestamp, "Null timestamp");
        this.timestamp = timestamp;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Point) {
            Point point = (Point) obj;
            return this.value.equals(point.getValue()) && this.timestamp.equals(point.getTimestamp());
        }
        return false;
    }

    @Override // io.opencensus.metrics.export.Point
    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    @Override // io.opencensus.metrics.export.Point
    public Value getValue() {
        return this.value;
    }

    public int hashCode() {
        return ((this.value.hashCode() ^ 1000003) * 1000003) ^ this.timestamp.hashCode();
    }

    public String toString() {
        return "Point{value=" + this.value + ", timestamp=" + this.timestamp + "}";
    }
}
