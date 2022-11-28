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
import io.opencensus.trace.Link;
import io.opencensus.trace.Span;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.propagation.SpanContextParseException;
import io.opencensus.trace.propagation.TextFormat;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
/* loaded from: classes3.dex */
public class HttpServerHandler<Q, P, C> extends AbstractHttpHandler<Q, P> {
    private final TextFormat.Getter<C> getter;
    private final Boolean publicEndpoint;
    private final StatsRecorder statsRecorder;
    private final Tagger tagger;
    private final TextFormat textFormat;
    private final Tracer tracer;

    public HttpServerHandler(Tracer tracer, HttpExtractor<Q, P> httpExtractor, TextFormat textFormat, TextFormat.Getter<C> getter, Boolean bool) {
        super(httpExtractor);
        Preconditions.checkNotNull(tracer, "tracer");
        Preconditions.checkNotNull(textFormat, "textFormat");
        Preconditions.checkNotNull(getter, "getter");
        Preconditions.checkNotNull(bool, "publicEndpoint");
        this.tracer = tracer;
        this.textFormat = textFormat;
        this.getter = getter;
        this.publicEndpoint = bool;
        this.statsRecorder = Stats.getStatsRecorder();
        this.tagger = Tags.getTagger();
    }

    private void recordStats(HttpRequestContext httpRequestContext, Q q2, int i2) {
        double millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - httpRequestContext.f10955a);
        String method = this.f10953a.getMethod(q2);
        String route = this.f10953a.getRoute(q2);
        TagContextBuilder builder = this.tagger.toBuilder(httpRequestContext.f10961g);
        TagKey tagKey = HttpMeasureConstants.HTTP_SERVER_METHOD;
        if (method == null) {
            method = "";
        }
        TagValue create = TagValue.create(method);
        TagMetadata tagMetadata = HttpRequestContext.f10954h;
        TagContextBuilder put = builder.put(tagKey, create, tagMetadata);
        TagKey tagKey2 = HttpMeasureConstants.HTTP_SERVER_ROUTE;
        if (route == null) {
            route = "";
        }
        this.statsRecorder.newMeasureMap().put(HttpMeasureConstants.HTTP_SERVER_LATENCY, millis).put(HttpMeasureConstants.HTTP_SERVER_RECEIVED_BYTES, httpRequestContext.f10958d.get()).put(HttpMeasureConstants.HTTP_SERVER_SENT_BYTES, httpRequestContext.f10957c.get()).record(put.put(tagKey2, TagValue.create(route), tagMetadata).put(HttpMeasureConstants.HTTP_SERVER_STATUS, TagValue.create(i2 == 0 ? Constants.IPC_BUNDLE_KEY_SEND_ERROR : Integer.toString(i2)), tagMetadata).build());
    }

    @Override // io.opencensus.contrib.http.AbstractHttpHandler
    public /* bridge */ /* synthetic */ Span getSpanFromContext(HttpRequestContext httpRequestContext) {
        return super.getSpanFromContext(httpRequestContext);
    }

    public void handleEnd(HttpRequestContext httpRequestContext, Q q2, @Nullable P p2, @Nullable Throwable th) {
        Preconditions.checkNotNull(httpRequestContext, "context");
        Preconditions.checkNotNull(q2, "request");
        int statusCode = this.f10953a.getStatusCode(p2);
        recordStats(httpRequestContext, q2, statusCode);
        e(httpRequestContext.f10956b, statusCode, th);
    }

    public HttpRequestContext handleStart(C c2, Q q2) {
        SpanContext spanContext;
        Preconditions.checkNotNull(c2, "carrier");
        Preconditions.checkNotNull(q2, "request");
        String c3 = c(q2, this.f10953a);
        try {
            spanContext = this.textFormat.extract(c2, this.getter);
        } catch (SpanContextParseException unused) {
            spanContext = null;
        }
        Span startSpan = ((spanContext == null || this.publicEndpoint.booleanValue()) ? this.tracer.spanBuilder(c3) : this.tracer.spanBuilderWithRemoteParent(c3, spanContext)).setSpanKind(Span.Kind.SERVER).startSpan();
        if (this.publicEndpoint.booleanValue() && spanContext != null) {
            startSpan.addLink(Link.fromSpanContext(spanContext, Link.Type.PARENT_LINKED_SPAN));
        }
        if (startSpan.getOptions().contains(Span.Options.RECORD_EVENTS)) {
            a(startSpan, q2, this.f10953a);
        }
        return b(startSpan, this.tagger.getCurrentTagContext());
    }
}
