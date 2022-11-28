package io.opencensus.stats;

import io.opencensus.stats.AggregationData;
import javax.annotation.concurrent.Immutable;
@Deprecated
@Immutable
/* loaded from: classes3.dex */
final class AutoValue_AggregationData_MeanData extends AggregationData.MeanData {
    private final long count;
    private final double mean;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AggregationData_MeanData(double d2, long j2) {
        this.mean = d2;
        this.count = j2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AggregationData.MeanData) {
            AggregationData.MeanData meanData = (AggregationData.MeanData) obj;
            return Double.doubleToLongBits(this.mean) == Double.doubleToLongBits(meanData.getMean()) && this.count == meanData.getCount();
        }
        return false;
    }

    @Override // io.opencensus.stats.AggregationData.MeanData
    public long getCount() {
        return this.count;
    }

    @Override // io.opencensus.stats.AggregationData.MeanData
    public double getMean() {
        return this.mean;
    }

    public int hashCode() {
        long j2 = this.count;
        return (int) ((((int) (1000003 ^ ((Double.doubleToLongBits(this.mean) >>> 32) ^ Double.doubleToLongBits(this.mean)))) * 1000003) ^ (j2 ^ (j2 >>> 32)));
    }

    public String toString() {
        return "MeanData{mean=" + this.mean + ", count=" + this.count + "}";
    }
}
