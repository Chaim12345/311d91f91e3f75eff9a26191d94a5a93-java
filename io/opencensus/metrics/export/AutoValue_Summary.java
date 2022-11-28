package io.opencensus.metrics.export;

import io.opencensus.metrics.export.Summary;
import java.util.Objects;
import javax.annotation.Nullable;
/* loaded from: classes3.dex */
final class AutoValue_Summary extends Summary {
    private final Long count;
    private final Summary.Snapshot snapshot;
    private final Double sum;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Summary(@Nullable Long l2, @Nullable Double d2, Summary.Snapshot snapshot) {
        this.count = l2;
        this.sum = d2;
        Objects.requireNonNull(snapshot, "Null snapshot");
        this.snapshot = snapshot;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Summary) {
            Summary summary = (Summary) obj;
            Long l2 = this.count;
            if (l2 != null ? l2.equals(summary.getCount()) : summary.getCount() == null) {
                Double d2 = this.sum;
                if (d2 != null ? d2.equals(summary.getSum()) : summary.getSum() == null) {
                    if (this.snapshot.equals(summary.getSnapshot())) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    @Override // io.opencensus.metrics.export.Summary
    @Nullable
    public Long getCount() {
        return this.count;
    }

    @Override // io.opencensus.metrics.export.Summary
    public Summary.Snapshot getSnapshot() {
        return this.snapshot;
    }

    @Override // io.opencensus.metrics.export.Summary
    @Nullable
    public Double getSum() {
        return this.sum;
    }

    public int hashCode() {
        Long l2 = this.count;
        int hashCode = ((l2 == null ? 0 : l2.hashCode()) ^ 1000003) * 1000003;
        Double d2 = this.sum;
        return ((hashCode ^ (d2 != null ? d2.hashCode() : 0)) * 1000003) ^ this.snapshot.hashCode();
    }

    public String toString() {
        return "Summary{count=" + this.count + ", sum=" + this.sum + ", snapshot=" + this.snapshot + "}";
    }
}
