package io.opencensus.trace;

import androidx.core.app.NotificationCompat;
import io.opencensus.internal.Utils;
import io.opencensus.trace.internal.BaseMessageEventUtils;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
/* loaded from: classes3.dex */
public abstract class Span {
    private final SpanContext context;
    private final Set<Options> options;
    private static final Map<String, AttributeValue> EMPTY_ATTRIBUTES = Collections.emptyMap();
    private static final Set<Options> DEFAULT_OPTIONS = Collections.unmodifiableSet(EnumSet.noneOf(Options.class));

    /* loaded from: classes3.dex */
    public enum Kind {
        SERVER,
        CLIENT
    }

    /* loaded from: classes3.dex */
    public enum Options {
        RECORD_EVENTS
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Span(SpanContext spanContext, @Nullable EnumSet enumSet) {
        this.context = (SpanContext) Utils.checkNotNull(spanContext, "context");
        Set<Options> unmodifiableSet = enumSet == null ? DEFAULT_OPTIONS : Collections.unmodifiableSet(EnumSet.copyOf(enumSet));
        this.options = unmodifiableSet;
        Utils.checkArgument(!spanContext.getTraceOptions().isSampled() || unmodifiableSet.contains(Options.RECORD_EVENTS), "Span is sampled, but does not have RECORD_EVENTS set.");
    }

    public abstract void addAnnotation(Annotation annotation);

    public final void addAnnotation(String str) {
        Utils.checkNotNull(str, "description");
        addAnnotation(str, EMPTY_ATTRIBUTES);
    }

    public abstract void addAnnotation(String str, Map<String, AttributeValue> map);

    @Deprecated
    public void addAttributes(Map<String, AttributeValue> map) {
        putAttributes(map);
    }

    public abstract void addLink(Link link);

    public void addMessageEvent(MessageEvent messageEvent) {
        Utils.checkNotNull(messageEvent, "messageEvent");
        addNetworkEvent(BaseMessageEventUtils.asNetworkEvent(messageEvent));
    }

    @Deprecated
    public void addNetworkEvent(NetworkEvent networkEvent) {
        addMessageEvent(BaseMessageEventUtils.asMessageEvent(networkEvent));
    }

    public final void end() {
        end(EndSpanOptions.DEFAULT);
    }

    public abstract void end(EndSpanOptions endSpanOptions);

    public final SpanContext getContext() {
        return this.context;
    }

    public final Set<Options> getOptions() {
        return this.options;
    }

    public void putAttribute(String str, AttributeValue attributeValue) {
        Utils.checkNotNull(str, "key");
        Utils.checkNotNull(attributeValue, "value");
        putAttributes(Collections.singletonMap(str, attributeValue));
    }

    public void putAttributes(Map<String, AttributeValue> map) {
        Utils.checkNotNull(map, "attributes");
        addAttributes(map);
    }

    public void setStatus(Status status) {
        Utils.checkNotNull(status, NotificationCompat.CATEGORY_STATUS);
    }
}
