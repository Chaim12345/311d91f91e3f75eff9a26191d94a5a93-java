package io.opencensus.stats;

import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
final class AutoValue_BucketBoundaries extends BucketBoundaries {
    private final List<Double> boundaries;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_BucketBoundaries(List list) {
        Objects.requireNonNull(list, "Null boundaries");
        this.boundaries = list;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BucketBoundaries) {
            return this.boundaries.equals(((BucketBoundaries) obj).getBoundaries());
        }
        return false;
    }

    @Override // io.opencensus.stats.BucketBoundaries
    public List<Double> getBoundaries() {
        return this.boundaries;
    }

    public int hashCode() {
        return this.boundaries.hashCode() ^ 1000003;
    }

    public String toString() {
        return "BucketBoundaries{boundaries=" + this.boundaries + "}";
    }
}
