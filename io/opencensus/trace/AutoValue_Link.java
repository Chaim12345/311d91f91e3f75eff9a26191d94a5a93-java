package io.opencensus.trace;

import io.opencensus.trace.Link;
import java.util.Map;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class AutoValue_Link extends Link {
    private final Map<String, AttributeValue> attributes;
    private final SpanId spanId;
    private final TraceId traceId;
    private final Link.Type type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Link(TraceId traceId, SpanId spanId, Link.Type type, Map map) {
        Objects.requireNonNull(traceId, "Null traceId");
        this.traceId = traceId;
        Objects.requireNonNull(spanId, "Null spanId");
        this.spanId = spanId;
        Objects.requireNonNull(type, "Null type");
        this.type = type;
        Objects.requireNonNull(map, "Null attributes");
        this.attributes = map;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Link) {
            Link link = (Link) obj;
            return this.traceId.equals(link.getTraceId()) && this.spanId.equals(link.getSpanId()) && this.type.equals(link.getType()) && this.attributes.equals(link.getAttributes());
        }
        return false;
    }

    @Override // io.opencensus.trace.Link
    public Map<String, AttributeValue> getAttributes() {
        return this.attributes;
    }

    @Override // io.opencensus.trace.Link
    public SpanId getSpanId() {
        return this.spanId;
    }

    @Override // io.opencensus.trace.Link
    public TraceId getTraceId() {
        return this.traceId;
    }

    @Override // io.opencensus.trace.Link
    public Link.Type getType() {
        return this.type;
    }

    public int hashCode() {
        return ((((((this.traceId.hashCode() ^ 1000003) * 1000003) ^ this.spanId.hashCode()) * 1000003) ^ this.type.hashCode()) * 1000003) ^ this.attributes.hashCode();
    }

    public String toString() {
        return "Link{traceId=" + this.traceId + ", spanId=" + this.spanId + ", type=" + this.type + ", attributes=" + this.attributes + "}";
    }
}
