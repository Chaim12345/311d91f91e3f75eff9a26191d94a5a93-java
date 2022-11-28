package io.opencensus.trace.export;

import io.opencensus.trace.export.SampledSpanStore;
import java.util.Objects;
/* loaded from: classes3.dex */
final class AutoValue_SampledSpanStore_LatencyFilter extends SampledSpanStore.LatencyFilter {
    private final long latencyLowerNs;
    private final long latencyUpperNs;
    private final int maxSpansToReturn;
    private final String spanName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SampledSpanStore_LatencyFilter(String str, long j2, long j3, int i2) {
        Objects.requireNonNull(str, "Null spanName");
        this.spanName = str;
        this.latencyLowerNs = j2;
        this.latencyUpperNs = j3;
        this.maxSpansToReturn = i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SampledSpanStore.LatencyFilter) {
            SampledSpanStore.LatencyFilter latencyFilter = (SampledSpanStore.LatencyFilter) obj;
            return this.spanName.equals(latencyFilter.getSpanName()) && this.latencyLowerNs == latencyFilter.getLatencyLowerNs() && this.latencyUpperNs == latencyFilter.getLatencyUpperNs() && this.maxSpansToReturn == latencyFilter.getMaxSpansToReturn();
        }
        return false;
    }

    @Override // io.opencensus.trace.export.SampledSpanStore.LatencyFilter
    public long getLatencyLowerNs() {
        return this.latencyLowerNs;
    }

    @Override // io.opencensus.trace.export.SampledSpanStore.LatencyFilter
    public long getLatencyUpperNs() {
        return this.latencyUpperNs;
    }

    @Override // io.opencensus.trace.export.SampledSpanStore.LatencyFilter
    public int getMaxSpansToReturn() {
        return this.maxSpansToReturn;
    }

    @Override // io.opencensus.trace.export.SampledSpanStore.LatencyFilter
    public String getSpanName() {
        return this.spanName;
    }

    public int hashCode() {
        long j2 = this.latencyLowerNs;
        long j3 = this.latencyUpperNs;
        return (((int) ((((int) (((this.spanName.hashCode() ^ 1000003) * 1000003) ^ (j2 ^ (j2 >>> 32)))) * 1000003) ^ (j3 ^ (j3 >>> 32)))) * 1000003) ^ this.maxSpansToReturn;
    }

    public String toString() {
        return "LatencyFilter{spanName=" + this.spanName + ", latencyLowerNs=" + this.latencyLowerNs + ", latencyUpperNs=" + this.latencyUpperNs + ", maxSpansToReturn=" + this.maxSpansToReturn + "}";
    }
}
