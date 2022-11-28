package io.opencensus.trace.export;

import io.opencensus.common.Timestamp;
import io.opencensus.trace.export.SpanData;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class AutoValue_SpanData_TimedEvent<T> extends SpanData.TimedEvent<T> {
    private final T event;
    private final Timestamp timestamp;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public AutoValue_SpanData_TimedEvent(Timestamp timestamp, Object obj) {
        Objects.requireNonNull(timestamp, "Null timestamp");
        this.timestamp = timestamp;
        Objects.requireNonNull(obj, "Null event");
        this.event = obj;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SpanData.TimedEvent) {
            SpanData.TimedEvent timedEvent = (SpanData.TimedEvent) obj;
            return this.timestamp.equals(timedEvent.getTimestamp()) && this.event.equals(timedEvent.getEvent());
        }
        return false;
    }

    @Override // io.opencensus.trace.export.SpanData.TimedEvent
    public T getEvent() {
        return this.event;
    }

    @Override // io.opencensus.trace.export.SpanData.TimedEvent
    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        return ((this.timestamp.hashCode() ^ 1000003) * 1000003) ^ this.event.hashCode();
    }

    public String toString() {
        return "TimedEvent{timestamp=" + this.timestamp + ", event=" + this.event + "}";
    }
}
