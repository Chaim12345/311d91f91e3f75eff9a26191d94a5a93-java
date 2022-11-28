package io.opencensus.metrics.export;

import io.opencensus.metrics.export.Distribution;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
/* loaded from: classes3.dex */
final class AutoValue_Distribution extends Distribution {
    private final Distribution.BucketOptions bucketOptions;
    private final List<Distribution.Bucket> buckets;
    private final long count;
    private final double sum;
    private final double sumOfSquaredDeviations;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Distribution(long j2, double d2, double d3, @Nullable Distribution.BucketOptions bucketOptions, List list) {
        this.count = j2;
        this.sum = d2;
        this.sumOfSquaredDeviations = d3;
        this.bucketOptions = bucketOptions;
        Objects.requireNonNull(list, "Null buckets");
        this.buckets = list;
    }

    public boolean equals(Object obj) {
        Distribution.BucketOptions bucketOptions;
        if (obj == this) {
            return true;
        }
        if (obj instanceof Distribution) {
            Distribution distribution = (Distribution) obj;
            return this.count == distribution.getCount() && Double.doubleToLongBits(this.sum) == Double.doubleToLongBits(distribution.getSum()) && Double.doubleToLongBits(this.sumOfSquaredDeviations) == Double.doubleToLongBits(distribution.getSumOfSquaredDeviations()) && ((bucketOptions = this.bucketOptions) != null ? bucketOptions.equals(distribution.getBucketOptions()) : distribution.getBucketOptions() == null) && this.buckets.equals(distribution.getBuckets());
        }
        return false;
    }

    @Override // io.opencensus.metrics.export.Distribution
    @Nullable
    public Distribution.BucketOptions getBucketOptions() {
        return this.bucketOptions;
    }

    @Override // io.opencensus.metrics.export.Distribution
    public List<Distribution.Bucket> getBuckets() {
        return this.buckets;
    }

    @Override // io.opencensus.metrics.export.Distribution
    public long getCount() {
        return this.count;
    }

    @Override // io.opencensus.metrics.export.Distribution
    public double getSum() {
        return this.sum;
    }

    @Override // io.opencensus.metrics.export.Distribution
    public double getSumOfSquaredDeviations() {
        return this.sumOfSquaredDeviations;
    }

    public int hashCode() {
        long j2 = this.count;
        int doubleToLongBits = ((int) ((((int) ((((int) (1000003 ^ (j2 ^ (j2 >>> 32)))) * 1000003) ^ ((Double.doubleToLongBits(this.sum) >>> 32) ^ Double.doubleToLongBits(this.sum)))) * 1000003) ^ ((Double.doubleToLongBits(this.sumOfSquaredDeviations) >>> 32) ^ Double.doubleToLongBits(this.sumOfSquaredDeviations)))) * 1000003;
        Distribution.BucketOptions bucketOptions = this.bucketOptions;
        return this.buckets.hashCode() ^ ((doubleToLongBits ^ (bucketOptions == null ? 0 : bucketOptions.hashCode())) * 1000003);
    }

    public String toString() {
        return "Distribution{count=" + this.count + ", sum=" + this.sum + ", sumOfSquaredDeviations=" + this.sumOfSquaredDeviations + ", bucketOptions=" + this.bucketOptions + ", buckets=" + this.buckets + "}";
    }
}
