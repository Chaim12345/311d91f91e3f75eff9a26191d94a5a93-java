package io.opencensus.contrib.http;

import com.google.common.base.Preconditions;
import com.google.firebase.messaging.Constants;
import io.opencensus.contrib.http.util.HttpMeasureConstants;
import io.opencensus.stats.Stats;
import io.opencensus.stats.StatsRecorder;
import io.opencensus.tags.TagContextBuilder;
import io.opencensus.tags.TagKey;
import io.opencensus.tags.TagMetadata;
import io.opencensus.tags.TagValue;
import io.opencensus.tags.Tagger;
import io.opencensus.tags.Tags;
import io.opencensus.trace.Span;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.propagation.TextFormat;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
/* loaded from: classes3.dex */
public class HttpClientHandler<Q, P, C> extends AbstractHttpHandler<Q, P> {
    private final TextFormat.Setter<C> setter;
    private final StatsRecorder statsRecorder;
    private final Tagger tagger;
    private final TextFormat textFormat;
    private final Tracer tracer;

    public HttpClientHandler(Tracer tracer, HttpExtractor<Q, P> httpExtractor, TextFormat textFormat, TextFormat.Setter<C> setter) {
        super(httpExtractor);
        Preconditions.checkNotNull(setter, "setter");
        Preconditions.checkNotNull(textFormat, "textFormat");
        Preconditions.checkNotNull(tracer, "tracer");
        this.setter = setter;
        this.textFormat = textFormat;
        this.tracer = tracer;
        this.statsRecorder = Stats.getStatsRecorder();
        this.tagger = Tags.getTagger();
    }

    private void recordStats(HttpRequestContext httpRequestContext, @Nullable Q q2, int i2) {
        double millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - httpRequestContext.f10955a);
        String method = q2 == null ? "" : this.f10953a.getMethod(q2);
        String host = q2 == null ? "null_request" : this.f10953a.getHost(q2);
        TagContextBuilder builder = this.tagger.toBuilder(httpRequestContext.f10961g);
        TagKey tagKey = HttpMeasureConstants.HTTP_CLIENT_HOST;
        if (host == null) {
            host = "null_host";
        }
        TagValue create = TagValue.create(host);
        TagMetadata tagMetadata = HttpRequestContext.f10954h;
        this.statsRecorder.newMeasureMap().put(HttpMeasureConstants.HTTP_CLIENT_ROUNDTRIP_LATENCY, millis).put(HttpMeasureConstants.HTTP_CLIENT_SENT_BYTES, httpRequestContext.f10957c.get()).put(HttpMeasureConstants.HTTP_CLIENT_RECEIVED_BYTES, httpRequestContext.f10958d.get()).record(builder.put(tagKey, create, tagMetadata).put(HttpMeasureConstants.HTTP_CLIENT_METHOD, TagValue.create(method != null ? method : ""), tagMetadata).put(HttpMeasureConstants.HTTP_CLIENT_STATUS, TagValue.create(i2 == 0 ? Constants.IPC_BUNDLE_KEY_SEND_ERROR : Integer.toString(i2)), tagMetadata).build());
    }

    @Override // io.opencensus.contrib.http.AbstractHttpHandler
    public /* bridge */ /* synthetic */ Span getSpanFromContext(HttpRequestContext httpRequestContext) {
        return super.getSpanFromContext(httpRequestContext);
    }

    public void handleEnd(HttpRequestContext httpRequestContext, @Nullable Q q2, @Nullable P p2, @Nullable Throwable th) {
        Preconditions.checkNotNull(httpRequestContext, "context");
        int statusCode = this.f10953a.getStatusCode(p2);
        recordStats(httpRequestContext, q2, statusCode);
        e(httpRequestContext.f10956b, statusCode, th);
    }

    public HttpRequestContext handleStart(@Nullable Span span, C c2, Q q2) {
        Preconditions.checkNotNull(c2, "carrier");
        Preconditions.checkNotNull(q2, "request");
        if (span == null) {
            span = this.tracer.getCurrentSpan();
        }
        Span startSpan = this.tracer.spanBuilderWithExplicitParent(c(q2, this.f10953a), span).setSpanKind(Span.Kind.CLIENT).startSpan();
        if (startSpan.getOptions().contains(Span.Options.RECORD_EVENTS)) {
            a(startSpan, q2, this.f10953a);
        }
        SpanContext context = startSpan.getContext();
        if (!context.equals(SpanContext.INVALID)) {
            this.textFormat.inject(context, c2, this.setter);
        }
        return b(startSpan, this.tagger.getCurrentTagContext());
    }
}
