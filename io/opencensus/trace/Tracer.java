package io.opencensus.trace;

import com.google.errorprone.annotations.MustBeClosed;
import io.opencensus.common.Scope;
import io.opencensus.internal.Utils;
import io.opencensus.trace.SpanBuilder;
import java.util.concurrent.Callable;
import javax.annotation.Nullable;
/* loaded from: classes3.dex */
public abstract class Tracer {
    private static final NoopTracer noopTracer = new NoopTracer();

    /* loaded from: classes3.dex */
    private static final class NoopTracer extends Tracer {
        private NoopTracer() {
        }

        @Override // io.opencensus.trace.Tracer
        public SpanBuilder spanBuilderWithExplicitParent(String str, @Nullable Span span) {
            return SpanBuilder.NoopSpanBuilder.a(str, span);
        }

        @Override // io.opencensus.trace.Tracer
        public SpanBuilder spanBuilderWithRemoteParent(String str, @Nullable SpanContext spanContext) {
            return SpanBuilder.NoopSpanBuilder.b(str, spanContext);
        }
    }

    protected Tracer() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Tracer a() {
        return noopTracer;
    }

    public final Span getCurrentSpan() {
        Span b2 = CurrentSpanUtils.b();
        return b2 != null ? b2 : BlankSpan.INSTANCE;
    }

    public final SpanBuilder spanBuilder(String str) {
        return spanBuilderWithExplicitParent(str, CurrentSpanUtils.b());
    }

    public abstract SpanBuilder spanBuilderWithExplicitParent(String str, @Nullable Span span);

    public abstract SpanBuilder spanBuilderWithRemoteParent(String str, @Nullable SpanContext spanContext);

    @MustBeClosed
    public final Scope withSpan(Span span) {
        return CurrentSpanUtils.c((Span) Utils.checkNotNull(span, "span"), false);
    }

    public final Runnable withSpan(Span span, Runnable runnable) {
        return CurrentSpanUtils.d(span, false, runnable);
    }

    public final <C> Callable<C> withSpan(Span span, Callable<C> callable) {
        return CurrentSpanUtils.e(span, false, callable);
    }
}
