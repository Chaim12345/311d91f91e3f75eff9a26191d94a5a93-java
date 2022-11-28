package io.opencensus.trace;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.errorprone.annotations.MustBeClosed;
import io.opencensus.common.Scope;
import io.opencensus.internal.Utils;
import io.opencensus.trace.Span;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.Nullable;
/* loaded from: classes3.dex */
public abstract class SpanBuilder {

    /* loaded from: classes3.dex */
    static final class NoopSpanBuilder extends SpanBuilder {
        private NoopSpanBuilder(String str) {
            Utils.checkNotNull(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static NoopSpanBuilder a(String str, @Nullable Span span) {
            return new NoopSpanBuilder(str);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static NoopSpanBuilder b(String str, @Nullable SpanContext spanContext) {
            return new NoopSpanBuilder(str);
        }

        @Override // io.opencensus.trace.SpanBuilder
        public SpanBuilder setParentLinks(List<Span> list) {
            return this;
        }

        @Override // io.opencensus.trace.SpanBuilder
        public SpanBuilder setRecordEvents(boolean z) {
            return this;
        }

        @Override // io.opencensus.trace.SpanBuilder
        public SpanBuilder setSampler(@Nullable Sampler sampler) {
            return this;
        }

        @Override // io.opencensus.trace.SpanBuilder
        public SpanBuilder setSpanKind(@Nullable Span.Kind kind) {
            return this;
        }

        @Override // io.opencensus.trace.SpanBuilder
        public Span startSpan() {
            return BlankSpan.INSTANCE;
        }
    }

    public abstract SpanBuilder setParentLinks(List<Span> list);

    public abstract SpanBuilder setRecordEvents(boolean z);

    public abstract SpanBuilder setSampler(Sampler sampler);

    public SpanBuilder setSpanKind(@Nullable Span.Kind kind) {
        return this;
    }

    @MustBeClosed
    public final Scope startScopedSpan() {
        return CurrentSpanUtils.c(startSpan(), true);
    }

    public abstract Span startSpan();

    public final <V> V startSpanAndCall(Callable<V> callable) {
        return (V) CurrentSpanUtils.e(startSpan(), true, callable).call();
    }

    public final void startSpanAndRun(Runnable runnable) {
        CurrentSpanUtils.d(startSpan(), true, runnable).run();
    }
}
