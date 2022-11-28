package io.opencensus.common;
/* loaded from: classes3.dex */
final class AutoValue_Duration extends Duration {
    private final int nanos;
    private final long seconds;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Duration(long j2, int i2) {
        this.seconds = j2;
        this.nanos = i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Duration) {
            Duration duration = (Duration) obj;
            return this.seconds == duration.getSeconds() && this.nanos == duration.getNanos();
        }
        return false;
    }

    @Override // io.opencensus.common.Duration
    public int getNanos() {
        return this.nanos;
    }

    @Override // io.opencensus.common.Duration
    public long getSeconds() {
        return this.seconds;
    }

    public int hashCode() {
        long j2 = this.seconds;
        return this.nanos ^ (((int) (1000003 ^ (j2 ^ (j2 >>> 32)))) * 1000003);
    }

    public String toString() {
        return "Duration{seconds=" + this.seconds + ", nanos=" + this.nanos + "}";
    }
}
