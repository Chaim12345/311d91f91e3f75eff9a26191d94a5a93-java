package io.opencensus.common;

import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public abstract class ServerStats {
    public static ServerStats create(long j2, long j3, byte b2) {
        if (j2 < 0) {
            throw new IllegalArgumentException("'getLbLatencyNs' is less than zero: " + j2);
        } else if (j3 >= 0) {
            return new AutoValue_ServerStats(j2, j3, b2);
        } else {
            throw new IllegalArgumentException("'getServiceLatencyNs' is less than zero: " + j3);
        }
    }

    public abstract long getLbLatencyNs();

    public abstract long getServiceLatencyNs();

    public abstract byte getTraceOption();
}
