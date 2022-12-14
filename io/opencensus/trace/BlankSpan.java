package io.opencensus.trace;

import androidx.core.app.NotificationCompat;
import io.opencensus.internal.Utils;
import java.util.Map;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public final class BlankSpan extends Span {
    public static final BlankSpan INSTANCE = new BlankSpan();

    private BlankSpan() {
        super(SpanContext.INVALID, null);
    }

    @Override // io.opencensus.trace.Span
    public void addAnnotation(Annotation annotation) {
        Utils.checkNotNull(annotation, "annotation");
    }

    @Override // io.opencensus.trace.Span
    public void addAnnotation(String str, Map<String, AttributeValue> map) {
        Utils.checkNotNull(str, "description");
        Utils.checkNotNull(map, "attributes");
    }

    @Override // io.opencensus.trace.Span
    public void addLink(Link link) {
        Utils.checkNotNull(link, "link");
    }

    @Override // io.opencensus.trace.Span
    public void addMessageEvent(MessageEvent messageEvent) {
        Utils.checkNotNull(messageEvent, "messageEvent");
    }

    @Override // io.opencensus.trace.Span
    @Deprecated
    public void addNetworkEvent(NetworkEvent networkEvent) {
    }

    @Override // io.opencensus.trace.Span
    public void end(EndSpanOptions endSpanOptions) {
        Utils.checkNotNull(endSpanOptions, "options");
    }

    @Override // io.opencensus.trace.Span
    public void putAttribute(String str, AttributeValue attributeValue) {
        Utils.checkNotNull(str, "key");
        Utils.checkNotNull(attributeValue, "value");
    }

    @Override // io.opencensus.trace.Span
    public void putAttributes(Map<String, AttributeValue> map) {
        Utils.checkNotNull(map, "attributes");
    }

    @Override // io.opencensus.trace.Span
    public void setStatus(Status status) {
        Utils.checkNotNull(status, NotificationCompat.CATEGORY_STATUS);
    }

    public String toString() {
        return "BlankSpan";
    }
}
