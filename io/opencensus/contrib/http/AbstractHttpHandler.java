package io.opencensus.contrib.http;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.opencensus.contrib.http.util.HttpTraceAttributeConstants;
import io.opencensus.contrib.http.util.HttpTraceUtil;
import io.opencensus.tags.TagContext;
import io.opencensus.trace.AttributeValue;
import io.opencensus.trace.MessageEvent;
import io.opencensus.trace.Span;
import javax.annotation.Nullable;
/* loaded from: classes3.dex */
abstract class AbstractHttpHandler<Q, P> {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    final HttpExtractor f10953a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractHttpHandler(HttpExtractor httpExtractor) {
        Preconditions.checkNotNull(httpExtractor, "extractor");
        this.f10953a = httpExtractor;
    }

    static void d(Span span, long j2, MessageEvent.Type type, long j3, long j4) {
        span.addMessageEvent(MessageEvent.builder(type, j2).setUncompressedMessageSize(j3).setCompressedMessageSize(j4).build());
    }

    private static void putAttributeIfNotEmptyOrNull(Span span, String str, @Nullable String str2) {
        if (str2 == null || str2.isEmpty()) {
            return;
        }
        span.putAttribute(str, AttributeValue.stringAttributeValue(str2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(Span span, Object obj, HttpExtractor httpExtractor) {
        putAttributeIfNotEmptyOrNull(span, HttpTraceAttributeConstants.HTTP_USER_AGENT, httpExtractor.getUserAgent(obj));
        putAttributeIfNotEmptyOrNull(span, HttpTraceAttributeConstants.HTTP_HOST, httpExtractor.getHost(obj));
        putAttributeIfNotEmptyOrNull(span, HttpTraceAttributeConstants.HTTP_METHOD, httpExtractor.getMethod(obj));
        putAttributeIfNotEmptyOrNull(span, HttpTraceAttributeConstants.HTTP_PATH, httpExtractor.getPath(obj));
        putAttributeIfNotEmptyOrNull(span, "http.route", httpExtractor.getRoute(obj));
        putAttributeIfNotEmptyOrNull(span, HttpTraceAttributeConstants.HTTP_URL, httpExtractor.getUrl(obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpRequestContext b(Span span, TagContext tagContext) {
        return new HttpRequestContext(span, tagContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String c(Object obj, HttpExtractor httpExtractor) {
        String path = httpExtractor.getPath(obj);
        if (path == null) {
            path = "/";
        }
        if (path.startsWith("/")) {
            return path;
        }
        return "/" + path;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(Span span, int i2, @Nullable Throwable th) {
        if (span.getOptions().contains(Span.Options.RECORD_EVENTS)) {
            span.putAttribute(HttpTraceAttributeConstants.HTTP_STATUS_CODE, AttributeValue.longAttributeValue(i2));
            span.setStatus(HttpTraceUtil.parseResponseStatus(i2, th));
        }
        span.end();
    }

    public Span getSpanFromContext(HttpRequestContext httpRequestContext) {
        Preconditions.checkNotNull(httpRequestContext, "context");
        return httpRequestContext.f10956b;
    }

    public final void handleMessageReceived(HttpRequestContext httpRequestContext, long j2) {
        Preconditions.checkNotNull(httpRequestContext, "context");
        httpRequestContext.f10958d.addAndGet(j2);
        if (httpRequestContext.f10956b.getOptions().contains(Span.Options.RECORD_EVENTS)) {
            d(httpRequestContext.f10956b, httpRequestContext.f10960f.addAndGet(1L), MessageEvent.Type.RECEIVED, j2, 0L);
        }
    }

    public final void handleMessageSent(HttpRequestContext httpRequestContext, long j2) {
        Preconditions.checkNotNull(httpRequestContext, "context");
        httpRequestContext.f10957c.addAndGet(j2);
        if (httpRequestContext.f10956b.getOptions().contains(Span.Options.RECORD_EVENTS)) {
            d(httpRequestContext.f10956b, httpRequestContext.f10959e.addAndGet(1L), MessageEvent.Type.SENT, j2, 0L);
        }
    }
}
