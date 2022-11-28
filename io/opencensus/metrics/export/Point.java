package io.opencensus.metrics.export;

import io.opencensus.common.Timestamp;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public abstract class Point {
    public static Point create(Value value, Timestamp timestamp) {
        return new AutoValue_Point(value, timestamp);
    }

    public abstract Timestamp getTimestamp();

    public abstract Value getValue();
}
