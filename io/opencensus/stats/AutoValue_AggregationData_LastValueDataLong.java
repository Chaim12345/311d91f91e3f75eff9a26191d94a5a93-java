package io.opencensus.stats;

import io.opencensus.stats.AggregationData;
/* loaded from: classes3.dex */
final class AutoValue_AggregationData_LastValueDataLong extends AggregationData.LastValueDataLong {
    private final long lastValue;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AggregationData_LastValueDataLong(long j2) {
        this.lastValue = j2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof AggregationData.LastValueDataLong) && this.lastValue == ((AggregationData.LastValueDataLong) obj).getLastValue();
    }

    @Override // io.opencensus.stats.AggregationData.LastValueDataLong
    public long getLastValue() {
        return this.lastValue;
    }

    public int hashCode() {
        long j2 = this.lastValue;
        return (int) (1000003 ^ (j2 ^ (j2 >>> 32)));
    }

    public String toString() {
        return "LastValueDataLong{lastValue=" + this.lastValue + "}";
    }
}
