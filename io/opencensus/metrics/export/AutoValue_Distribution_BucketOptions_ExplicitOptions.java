package io.opencensus.metrics.export;

import io.opencensus.metrics.export.Distribution;
import java.util.List;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class AutoValue_Distribution_BucketOptions_ExplicitOptions extends Distribution.BucketOptions.ExplicitOptions {
    private final List<Double> bucketBoundaries;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Distribution_BucketOptions_ExplicitOptions(List list) {
        Objects.requireNonNull(list, "Null bucketBoundaries");
        this.bucketBoundaries = list;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Distribution.BucketOptions.ExplicitOptions) {
            return this.bucketBoundaries.equals(((Distribution.BucketOptions.ExplicitOptions) obj).getBucketBoundaries());
        }
        return false;
    }

    @Override // io.opencensus.metrics.export.Distribution.BucketOptions.ExplicitOptions
    public List<Double> getBucketBoundaries() {
        return this.bucketBoundaries;
    }

    public int hashCode() {
        return this.bucketBoundaries.hashCode() ^ 1000003;
    }

    public String toString() {
        return "ExplicitOptions{bucketBoundaries=" + this.bucketBoundaries + "}";
    }
}
