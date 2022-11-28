package io.opencensus.common;
/* loaded from: classes3.dex */
final class AutoValue_Timestamp extends Timestamp {
    private final int nanos;
    private final long seconds;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Timestamp(long j2, int i2) {
        this.seconds = j2;
        this.nanos = i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Timestamp) {
            Timestamp timestamp = (Timestamp) obj;
            return this.seconds == timestamp.getSeconds() && this.nanos == timestamp.getNanos();
        }
        return false;
    }

    @Override // io.opencensus.common.Timestamp
    public int getNanos() {
        return this.nanos;
    }

    @Override // io.opencensus.common.Timestamp
    public long getSeconds() {
        return this.seconds;
    }

    public int hashCode() {
        long j2 = this.seconds;
        return this.nanos ^ (((int) (1000003 ^ (j2 ^ (j2 >>> 32)))) * 1000003);
    }

    public String toString() {
        return "Timestamp{seconds=" + this.seconds + ", nanos=" + this.nanos + "}";
    }
}
