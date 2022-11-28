package io.opencensus.trace.propagation;

import io.opencensus.internal.Utils;
import io.opencensus.trace.SpanContext;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
/* loaded from: classes3.dex */
public abstract class TextFormat {
    private static final NoopTextFormat NOOP_TEXT_FORMAT = new NoopTextFormat();

    /* loaded from: classes3.dex */
    public static abstract class Getter<C> {
        @Nullable
        public abstract String get(C c2, String str);
    }

    /* loaded from: classes3.dex */
    private static final class NoopTextFormat extends TextFormat {
        private NoopTextFormat() {
        }

        @Override // io.opencensus.trace.propagation.TextFormat
        public <C> SpanContext extract(C c2, Getter<C> getter) {
            Utils.checkNotNull(c2, "carrier");
            Utils.checkNotNull(getter, "getter");
            return SpanContext.INVALID;
        }

        @Override // io.opencensus.trace.propagation.TextFormat
        public List<String> fields() {
            return Collections.emptyList();
        }

        @Override // io.opencensus.trace.propagation.TextFormat
        public <C> void inject(SpanContext spanContext, C c2, Setter<C> setter) {
            Utils.checkNotNull(spanContext, "spanContext");
            Utils.checkNotNull(c2, "carrier");
            Utils.checkNotNull(setter, "setter");
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Setter<C> {
        public abstract void put(C c2, String str, String str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TextFormat a() {
        return NOOP_TEXT_FORMAT;
    }

    public abstract <C> SpanContext extract(C c2, Getter<C> getter);

    public abstract List<String> fields();

    public abstract <C> void inject(SpanContext spanContext, C c2, Setter<C> setter);
}
