package io.opencensus.metrics.export;

import io.opencensus.metrics.export.Summary;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
/* loaded from: classes3.dex */
final class AutoValue_Summary_Snapshot extends Summary.Snapshot {
    private final Long count;
    private final Double sum;
    private final List<Summary.Snapshot.ValueAtPercentile> valueAtPercentiles;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Summary_Snapshot(@Nullable Long l2, @Nullable Double d2, List list) {
        this.count = l2;
        this.sum = d2;
        Objects.requireNonNull(list, "Null valueAtPercentiles");
        this.valueAtPercentiles = list;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Summary.Snapshot) {
            Summary.Snapshot snapshot = (Summary.Snapshot) obj;
            Long l2 = this.count;
            if (l2 != null ? l2.equals(snapshot.getCount()) : snapshot.getCount() == null) {
                Double d2 = this.sum;
                if (d2 != null ? d2.equals(snapshot.getSum()) : snapshot.getSum() == null) {
                    if (this.valueAtPercentiles.equals(snapshot.getValueAtPercentiles())) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    @Override // io.opencensus.metrics.export.Summary.Snapshot
    @Nullable
    public Long getCount() {
        return this.count;
    }

    @Override // io.opencensus.metrics.export.Summary.Snapshot
    @Nullable
    public Double getSum() {
        return this.sum;
    }

    @Override // io.opencensus.metrics.export.Summary.Snapshot
    public List<Summary.Snapshot.ValueAtPercentile> getValueAtPercentiles() {
        return this.valueAtPercentiles;
    }

    public int hashCode() {
        Long l2 = this.count;
        int hashCode = ((l2 == null ? 0 : l2.hashCode()) ^ 1000003) * 1000003;
        Double d2 = this.sum;
        return ((hashCode ^ (d2 != null ? d2.hashCode() : 0)) * 1000003) ^ this.valueAtPercentiles.hashCode();
    }

    public String toString() {
        return "Snapshot{count=" + this.count + ", sum=" + this.sum + ", valueAtPercentiles=" + this.valueAtPercentiles + "}";
    }
}
