package io.opencensus.contrib.http.util;

import com.fasterxml.jackson.core.JsonPointer;
import com.google.common.base.Preconditions;
import com.google.common.primitives.UnsignedInts;
import com.google.common.primitives.UnsignedLongs;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.SpanId;
import io.opencensus.trace.TraceId;
import io.opencensus.trace.TraceOptions;
import io.opencensus.trace.Tracestate;
import io.opencensus.trace.propagation.SpanContextParseException;
import io.opencensus.trace.propagation.TextFormat;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
/* loaded from: classes3.dex */
final class CloudTraceFormat extends TextFormat {

    /* renamed from: a  reason: collision with root package name */
    static final List f10962a = Collections.singletonList("X-Cloud-Trace-Context");

    /* renamed from: b  reason: collision with root package name */
    static final TraceOptions f10963b = TraceOptions.builder().setIsSampled(true).build();

    /* renamed from: c  reason: collision with root package name */
    static final TraceOptions f10964c = TraceOptions.DEFAULT;

    /* renamed from: d  reason: collision with root package name */
    static final int f10965d = 3;
    private static final Tracestate TRACESTATE_DEFAULT = Tracestate.builder().build();

    private static SpanId longToSpanId(long j2) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.putLong(j2);
        return SpanId.fromBytes(allocate.array());
    }

    private static long spanIdToLong(SpanId spanId) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.put(spanId.getBytes());
        return allocate.getLong(0);
    }

    @Override // io.opencensus.trace.propagation.TextFormat
    public <C> SpanContext extract(C c2, TextFormat.Getter<C> getter) {
        Preconditions.checkNotNull(c2, "carrier");
        Preconditions.checkNotNull(getter, "getter");
        try {
            String str = getter.get(c2, "X-Cloud-Trace-Context");
            if (str == null || str.length() < 34) {
                throw new SpanContextParseException("Missing or too short header: X-Cloud-Trace-Context");
            }
            Preconditions.checkArgument(str.charAt(32) == '/', "Invalid TRACE_ID size");
            TraceId fromLowerBase16 = TraceId.fromLowerBase16(str.subSequence(0, 32));
            int indexOf = str.indexOf(";o=", 32);
            SpanId longToSpanId = longToSpanId(UnsignedLongs.parseUnsignedLong(str.subSequence(33, indexOf < 0 ? str.length() : indexOf).toString(), 10));
            TraceOptions traceOptions = f10964c;
            if (indexOf > 0 && (UnsignedInts.parseUnsignedInt(str.substring(indexOf + f10965d), 10) & 1) != 0) {
                traceOptions = f10963b;
            }
            return SpanContext.create(fromLowerBase16, longToSpanId, traceOptions, TRACESTATE_DEFAULT);
        } catch (IllegalArgumentException e2) {
            throw new SpanContextParseException("Invalid input", e2);
        }
    }

    @Override // io.opencensus.trace.propagation.TextFormat
    public List<String> fields() {
        return f10962a;
    }

    @Override // io.opencensus.trace.propagation.TextFormat
    public <C> void inject(SpanContext spanContext, C c2, TextFormat.Setter<C> setter) {
        Preconditions.checkNotNull(spanContext, "spanContext");
        Preconditions.checkNotNull(setter, "setter");
        Preconditions.checkNotNull(c2, "carrier");
        StringBuilder sb = new StringBuilder();
        sb.append(spanContext.getTraceId().toLowerBase16());
        sb.append(JsonPointer.SEPARATOR);
        sb.append(UnsignedLongs.toString(spanIdToLong(spanContext.getSpanId())));
        sb.append(";o=");
        sb.append(spanContext.getTraceOptions().isSampled() ? "1" : "0");
        setter.put(c2, "X-Cloud-Trace-Context", sb.toString());
    }
}
