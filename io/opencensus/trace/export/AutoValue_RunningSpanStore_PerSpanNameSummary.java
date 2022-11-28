package io.opencensus.trace.export;

import io.opencensus.trace.export.RunningSpanStore;
/* loaded from: classes3.dex */
final class AutoValue_RunningSpanStore_PerSpanNameSummary extends RunningSpanStore.PerSpanNameSummary {
    private final int numRunningSpans;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_RunningSpanStore_PerSpanNameSummary(int i2) {
        this.numRunningSpans = i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof RunningSpanStore.PerSpanNameSummary) && this.numRunningSpans == ((RunningSpanStore.PerSpanNameSummary) obj).getNumRunningSpans();
    }

    @Override // io.opencensus.trace.export.RunningSpanStore.PerSpanNameSummary
    public int getNumRunningSpans() {
        return this.numRunningSpans;
    }

    public int hashCode() {
        return this.numRunningSpans ^ 1000003;
    }

    public String toString() {
        return "PerSpanNameSummary{numRunningSpans=" + this.numRunningSpans + "}";
    }
}
