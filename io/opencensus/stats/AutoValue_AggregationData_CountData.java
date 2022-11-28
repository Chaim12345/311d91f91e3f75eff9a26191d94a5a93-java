package io.opencensus.stats;

import io.opencensus.stats.AggregationData;
/* loaded from: classes3.dex */
final class AutoValue_AggregationData_CountData extends AggregationData.CountData {
    private final long count;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AggregationData_CountData(long j2) {
        this.count = j2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof AggregationData.CountData) && this.count == ((AggregationData.CountData) obj).getCount();
    }

    @Override // io.opencensus.stats.AggregationData.CountData
    public long getCount() {
        return this.count;
    }

    public int hashCode() {
        long j2 = this.count;
        return (int) (1000003 ^ (j2 ^ (j2 >>> 32)));
    }

    public String toString() {
        return "CountData{count=" + this.count + "}";
    }
}
