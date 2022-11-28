package com.google.api.client.http;

import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import io.opencensus.contrib.http.util.HttpPropagationUtil;
import io.opencensus.trace.BlankSpan;
import io.opencensus.trace.EndSpanOptions;
import io.opencensus.trace.MessageEvent;
import io.opencensus.trace.Span;
import io.opencensus.trace.Status;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import io.opencensus.trace.propagation.TextFormat;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
@Beta
/* loaded from: classes2.dex */
public class OpenCensusUtils {
    @VisibleForTesting
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    static volatile TextFormat f8054a;
    @VisibleForTesting
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    static volatile TextFormat.Setter f8055b;
    private static final Logger logger = Logger.getLogger(OpenCensusUtils.class.getName());
    public static final String SPAN_NAME_HTTP_REQUEST_EXECUTE = "Sent." + HttpRequest.class.getName() + ".execute";
    private static final Tracer tracer = Tracing.getTracer();
    private static final AtomicLong idGenerator = new AtomicLong();
    private static volatile boolean isRecordEvent = true;

    static {
        f8054a = null;
        f8055b = null;
        try {
            f8054a = HttpPropagationUtil.getCloudTraceFormat();
            f8055b = new TextFormat.Setter<HttpHeaders>() { // from class: com.google.api.client.http.OpenCensusUtils.1
                @Override // io.opencensus.trace.propagation.TextFormat.Setter
                public void put(HttpHeaders httpHeaders, String str, String str2) {
                    httpHeaders.set(str, (Object) str2);
                }
            };
        } catch (Exception e2) {
            logger.log(Level.WARNING, "Cannot initialize default OpenCensus HTTP propagation text format.", (Throwable) e2);
        }
        try {
            Tracing.getExportComponent().getSampledSpanStore().registerSpanNamesForCollection(ImmutableList.of(SPAN_NAME_HTTP_REQUEST_EXECUTE));
        } catch (Exception e3) {
            logger.log(Level.WARNING, "Cannot register default OpenCensus span names for collection.", (Throwable) e3);
        }
    }

    private OpenCensusUtils() {
    }

    @VisibleForTesting
    static void a(Span span, long j2, MessageEvent.Type type) {
        Preconditions.checkArgument(span != null, "span should not be null.");
        if (j2 < 0) {
            j2 = 0;
        }
        span.addMessageEvent(MessageEvent.builder(type, idGenerator.getAndIncrement()).setUncompressedMessageSize(j2).build());
    }

    public static EndSpanOptions getEndSpanOptions(@Nullable Integer num) {
        Status status;
        EndSpanOptions.Builder builder = EndSpanOptions.builder();
        if (num != null) {
            if (HttpStatusCodes.isSuccess(num.intValue())) {
                status = Status.OK;
            } else {
                int intValue = num.intValue();
                if (intValue == 400) {
                    status = Status.INVALID_ARGUMENT;
                } else if (intValue == 401) {
                    status = Status.UNAUTHENTICATED;
                } else if (intValue == 403) {
                    status = Status.PERMISSION_DENIED;
                } else if (intValue == 404) {
                    status = Status.NOT_FOUND;
                } else if (intValue == 412) {
                    status = Status.FAILED_PRECONDITION;
                } else if (intValue == 500) {
                    status = Status.UNAVAILABLE;
                }
            }
            builder.setStatus(status);
            return builder.build();
        }
        status = Status.UNKNOWN;
        builder.setStatus(status);
        return builder.build();
    }

    public static Tracer getTracer() {
        return tracer;
    }

    public static boolean isRecordEvent() {
        return isRecordEvent;
    }

    public static void propagateTracingContext(Span span, HttpHeaders httpHeaders) {
        Preconditions.checkArgument(span != null, "span should not be null.");
        Preconditions.checkArgument(httpHeaders != null, "headers should not be null.");
        if (f8054a == null || f8055b == null || span.equals(BlankSpan.INSTANCE)) {
            return;
        }
        f8054a.inject(span.getContext(), httpHeaders, f8055b);
    }

    public static void recordReceivedMessageEvent(Span span, long j2) {
        a(span, j2, MessageEvent.Type.RECEIVED);
    }

    public static void recordSentMessageEvent(Span span, long j2) {
        a(span, j2, MessageEvent.Type.SENT);
    }

    public static void setIsRecordEvent(boolean z) {
        isRecordEvent = z;
    }

    public static void setPropagationTextFormat(@Nullable TextFormat textFormat) {
        f8054a = textFormat;
    }

    public static void setPropagationTextFormatSetter(@Nullable TextFormat.Setter setter) {
        f8055b = setter;
    }
}
