package io.opencensus.trace.export;

import io.opencensus.internal.Utils;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
@ThreadSafe
/* loaded from: classes3.dex */
public abstract class RunningSpanStore {
    private static final RunningSpanStore NOOP_RUNNING_SPAN_STORE = new NoopRunningSpanStore();

    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class Filter {
        public static Filter create(String str, int i2) {
            Utils.checkArgument(i2 >= 0, "Negative maxSpansToReturn.");
            return new AutoValue_RunningSpanStore_Filter(str, i2);
        }

        public abstract int getMaxSpansToReturn();

        public abstract String getSpanName();
    }

    /* loaded from: classes3.dex */
    private static final class NoopRunningSpanStore extends RunningSpanStore {
        private static final Summary EMPTY_SUMMARY = Summary.create(Collections.emptyMap());

        private NoopRunningSpanStore() {
        }

        @Override // io.opencensus.trace.export.RunningSpanStore
        public Collection<SpanData> getRunningSpans(Filter filter) {
            Utils.checkNotNull(filter, "filter");
            return Collections.emptyList();
        }

        @Override // io.opencensus.trace.export.RunningSpanStore
        public Summary getSummary() {
            return EMPTY_SUMMARY;
        }

        @Override // io.opencensus.trace.export.RunningSpanStore
        public void setMaxNumberOfSpans(int i2) {
            Utils.checkArgument(i2 >= 0, "Invalid negative maxNumberOfElements");
        }
    }

    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class PerSpanNameSummary {
        public static PerSpanNameSummary create(int i2) {
            Utils.checkArgument(i2 >= 0, "Negative numRunningSpans.");
            return new AutoValue_RunningSpanStore_PerSpanNameSummary(i2);
        }

        public abstract int getNumRunningSpans();
    }

    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class Summary {
        public static Summary create(Map<String, PerSpanNameSummary> map) {
            return new AutoValue_RunningSpanStore_Summary(Collections.unmodifiableMap(new HashMap((Map) Utils.checkNotNull(map, "perSpanNameSummary"))));
        }

        public abstract Map<String, PerSpanNameSummary> getPerSpanNameSummary();
    }

    protected RunningSpanStore() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RunningSpanStore a() {
        return NOOP_RUNNING_SPAN_STORE;
    }

    public abstract Collection<SpanData> getRunningSpans(Filter filter);

    public abstract Summary getSummary();

    public abstract void setMaxNumberOfSpans(int i2);
}
