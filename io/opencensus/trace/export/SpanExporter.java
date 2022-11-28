package io.opencensus.trace.export;

import java.util.Collection;
import javax.annotation.concurrent.ThreadSafe;
@ThreadSafe
/* loaded from: classes3.dex */
public abstract class SpanExporter {
    private static final SpanExporter NOOP_SPAN_EXPORTER = new NoopSpanExporter();

    /* loaded from: classes3.dex */
    public static abstract class Handler {
        public abstract void export(Collection<SpanData> collection);
    }

    /* loaded from: classes3.dex */
    private static final class NoopSpanExporter extends SpanExporter {
        private NoopSpanExporter() {
        }

        @Override // io.opencensus.trace.export.SpanExporter
        public void registerHandler(String str, Handler handler) {
        }

        @Override // io.opencensus.trace.export.SpanExporter
        public void unregisterHandler(String str) {
        }
    }

    public static SpanExporter getNoopSpanExporter() {
        return NOOP_SPAN_EXPORTER;
    }

    public abstract void registerHandler(String str, Handler handler);

    public abstract void unregisterHandler(String str);
}
