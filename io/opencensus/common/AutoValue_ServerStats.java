package io.opencensus.common;
/* loaded from: classes3.dex */
final class AutoValue_ServerStats extends ServerStats {
    private final long lbLatencyNs;
    private final long serviceLatencyNs;
    private final byte traceOption;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_ServerStats(long j2, long j3, byte b2) {
        this.lbLatencyNs = j2;
        this.serviceLatencyNs = j3;
        this.traceOption = b2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ServerStats) {
            ServerStats serverStats = (ServerStats) obj;
            return this.lbLatencyNs == serverStats.getLbLatencyNs() && this.serviceLatencyNs == serverStats.getServiceLatencyNs() && this.traceOption == serverStats.getTraceOption();
        }
        return false;
    }

    @Override // io.opencensus.common.ServerStats
    public long getLbLatencyNs() {
        return this.lbLatencyNs;
    }

    @Override // io.opencensus.common.ServerStats
    public long getServiceLatencyNs() {
        return this.serviceLatencyNs;
    }

    @Override // io.opencensus.common.ServerStats
    public byte getTraceOption() {
        return this.traceOption;
    }

    public int hashCode() {
        long j2 = this.lbLatencyNs;
        long j3 = this.serviceLatencyNs;
        return this.traceOption ^ (((int) ((((int) (1000003 ^ (j2 ^ (j2 >>> 32)))) * 1000003) ^ (j3 ^ (j3 >>> 32)))) * 1000003);
    }

    public String toString() {
        return "ServerStats{lbLatencyNs=" + this.lbLatencyNs + ", serviceLatencyNs=" + this.serviceLatencyNs + ", traceOption=" + ((int) this.traceOption) + "}";
    }
}
