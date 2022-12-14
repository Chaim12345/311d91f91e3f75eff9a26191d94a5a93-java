package io.opencensus.trace.export;

import io.opencensus.common.Timestamp;
import io.opencensus.internal.Utils;
import io.opencensus.trace.Annotation;
import io.opencensus.trace.AttributeValue;
import io.opencensus.trace.BaseMessageEvent;
import io.opencensus.trace.Link;
import io.opencensus.trace.MessageEvent;
import io.opencensus.trace.NetworkEvent;
import io.opencensus.trace.Span;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.SpanId;
import io.opencensus.trace.Status;
import io.opencensus.trace.internal.BaseMessageEventUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public abstract class SpanData {

    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class Attributes {
        public static Attributes create(Map<String, AttributeValue> map, int i2) {
            return new AutoValue_SpanData_Attributes(Collections.unmodifiableMap(new HashMap((Map) Utils.checkNotNull(map, "attributeMap"))), i2);
        }

        public abstract Map<String, AttributeValue> getAttributeMap();

        public abstract int getDroppedAttributesCount();
    }

    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class Links {
        public static Links create(List<Link> list, int i2) {
            return new AutoValue_SpanData_Links(Collections.unmodifiableList(new ArrayList((Collection) Utils.checkNotNull(list, "links"))), i2);
        }

        public abstract int getDroppedLinksCount();

        public abstract List<Link> getLinks();
    }

    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class TimedEvent<T> {
        public static <T> TimedEvent<T> create(Timestamp timestamp, T t2) {
            return new AutoValue_SpanData_TimedEvent(timestamp, t2);
        }

        public abstract T getEvent();

        public abstract Timestamp getTimestamp();
    }

    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class TimedEvents<T> {
        public static <T> TimedEvents<T> create(List<TimedEvent<T>> list, int i2) {
            return new AutoValue_SpanData_TimedEvents(Collections.unmodifiableList(new ArrayList((Collection) Utils.checkNotNull(list, "events"))), i2);
        }

        public abstract int getDroppedEventsCount();

        public abstract List<TimedEvent<T>> getEvents();
    }

    @Deprecated
    public static SpanData create(SpanContext spanContext, @Nullable SpanId spanId, @Nullable Boolean bool, String str, Timestamp timestamp, Attributes attributes, TimedEvents<Annotation> timedEvents, TimedEvents<? extends BaseMessageEvent> timedEvents2, Links links, @Nullable Integer num, @Nullable Status status, @Nullable Timestamp timestamp2) {
        return create(spanContext, spanId, bool, str, null, timestamp, attributes, timedEvents, timedEvents2, links, num, status, timestamp2);
    }

    public static SpanData create(SpanContext spanContext, @Nullable SpanId spanId, @Nullable Boolean bool, String str, @Nullable Span.Kind kind, Timestamp timestamp, Attributes attributes, TimedEvents<Annotation> timedEvents, TimedEvents<? extends BaseMessageEvent> timedEvents2, Links links, @Nullable Integer num, @Nullable Status status, @Nullable Timestamp timestamp2) {
        Utils.checkNotNull(timedEvents2, "messageOrNetworkEvents");
        ArrayList arrayList = new ArrayList();
        for (TimedEvent<? extends BaseMessageEvent> timedEvent : timedEvents2.getEvents()) {
            BaseMessageEvent event = timedEvent.getEvent();
            if (!(event instanceof MessageEvent)) {
                timedEvent = TimedEvent.create(timedEvent.getTimestamp(), BaseMessageEventUtils.asMessageEvent(event));
            }
            arrayList.add(timedEvent);
        }
        return new AutoValue_SpanData(spanContext, spanId, bool, str, kind, timestamp, attributes, timedEvents, TimedEvents.create(arrayList, timedEvents2.getDroppedEventsCount()), links, num, status, timestamp2);
    }

    public abstract TimedEvents<Annotation> getAnnotations();

    public abstract Attributes getAttributes();

    @Nullable
    public abstract Integer getChildSpanCount();

    public abstract SpanContext getContext();

    @Nullable
    public abstract Timestamp getEndTimestamp();

    @Nullable
    public abstract Boolean getHasRemoteParent();

    @Nullable
    public abstract Span.Kind getKind();

    public abstract Links getLinks();

    public abstract TimedEvents<MessageEvent> getMessageEvents();

    public abstract String getName();

    @Deprecated
    public TimedEvents<NetworkEvent> getNetworkEvents() {
        TimedEvents<MessageEvent> messageEvents = getMessageEvents();
        ArrayList arrayList = new ArrayList();
        for (TimedEvent<MessageEvent> timedEvent : messageEvents.getEvents()) {
            arrayList.add(TimedEvent.create(timedEvent.getTimestamp(), BaseMessageEventUtils.asNetworkEvent(timedEvent.getEvent())));
        }
        return TimedEvents.create(arrayList, messageEvents.getDroppedEventsCount());
    }

    @Nullable
    public abstract SpanId getParentSpanId();

    public abstract Timestamp getStartTimestamp();

    @Nullable
    public abstract Status getStatus();
}
