package io.opencensus.trace.export;

import io.opencensus.internal.Utils;
import io.opencensus.trace.Status;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import kotlin.jvm.internal.LongCompanionObject;
@ThreadSafe
/* loaded from: classes3.dex */
public abstract class SampledSpanStore {

    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class ErrorFilter {
        public static ErrorFilter create(String str, @Nullable Status.CanonicalCode canonicalCode, int i2) {
            if (canonicalCode != null) {
                Utils.checkArgument(canonicalCode != Status.CanonicalCode.OK, "Invalid canonical code.");
            }
            Utils.checkArgument(i2 >= 0, "Negative maxSpansToReturn.");
            return new AutoValue_SampledSpanStore_ErrorFilter(str, canonicalCode, i2);
        }

        @Nullable
        public abstract Status.CanonicalCode getCanonicalCode();

        public abstract int getMaxSpansToReturn();

        public abstract String getSpanName();
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum ZERO_MICROSx10 uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:391)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:320)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* loaded from: classes3.dex */
    public static final class LatencyBucketBoundaries {
        private static final /* synthetic */ LatencyBucketBoundaries[] $VALUES;
        public static final LatencyBucketBoundaries MICROSx100_MILLIx1;
        public static final LatencyBucketBoundaries MICROSx10_MICROSx100;
        public static final LatencyBucketBoundaries MILLIx100_SECONDx1;
        public static final LatencyBucketBoundaries MILLIx10_MILLIx100;
        public static final LatencyBucketBoundaries MILLIx1_MILLIx10;
        public static final LatencyBucketBoundaries SECONDx100_MAX;
        public static final LatencyBucketBoundaries SECONDx10_SECONDx100;
        public static final LatencyBucketBoundaries SECONDx1_SECONDx10;
        public static final LatencyBucketBoundaries ZERO_MICROSx10;
        private final long latencyLowerNs;
        private final long latencyUpperNs;

        static {
            TimeUnit timeUnit = TimeUnit.MICROSECONDS;
            LatencyBucketBoundaries latencyBucketBoundaries = new LatencyBucketBoundaries("ZERO_MICROSx10", 0, 0L, timeUnit.toNanos(10L));
            ZERO_MICROSx10 = latencyBucketBoundaries;
            LatencyBucketBoundaries latencyBucketBoundaries2 = new LatencyBucketBoundaries("MICROSx10_MICROSx100", 1, timeUnit.toNanos(10L), timeUnit.toNanos(100L));
            MICROSx10_MICROSx100 = latencyBucketBoundaries2;
            long nanos = timeUnit.toNanos(100L);
            TimeUnit timeUnit2 = TimeUnit.MILLISECONDS;
            LatencyBucketBoundaries latencyBucketBoundaries3 = new LatencyBucketBoundaries("MICROSx100_MILLIx1", 2, nanos, timeUnit2.toNanos(1L));
            MICROSx100_MILLIx1 = latencyBucketBoundaries3;
            LatencyBucketBoundaries latencyBucketBoundaries4 = new LatencyBucketBoundaries("MILLIx1_MILLIx10", 3, timeUnit2.toNanos(1L), timeUnit2.toNanos(10L));
            MILLIx1_MILLIx10 = latencyBucketBoundaries4;
            LatencyBucketBoundaries latencyBucketBoundaries5 = new LatencyBucketBoundaries("MILLIx10_MILLIx100", 4, timeUnit2.toNanos(10L), timeUnit2.toNanos(100L));
            MILLIx10_MILLIx100 = latencyBucketBoundaries5;
            long nanos2 = timeUnit2.toNanos(100L);
            TimeUnit timeUnit3 = TimeUnit.SECONDS;
            LatencyBucketBoundaries latencyBucketBoundaries6 = new LatencyBucketBoundaries("MILLIx100_SECONDx1", 5, nanos2, timeUnit3.toNanos(1L));
            MILLIx100_SECONDx1 = latencyBucketBoundaries6;
            LatencyBucketBoundaries latencyBucketBoundaries7 = new LatencyBucketBoundaries("SECONDx1_SECONDx10", 6, timeUnit3.toNanos(1L), timeUnit3.toNanos(10L));
            SECONDx1_SECONDx10 = latencyBucketBoundaries7;
            LatencyBucketBoundaries latencyBucketBoundaries8 = new LatencyBucketBoundaries("SECONDx10_SECONDx100", 7, timeUnit3.toNanos(10L), timeUnit3.toNanos(100L));
            SECONDx10_SECONDx100 = latencyBucketBoundaries8;
            LatencyBucketBoundaries latencyBucketBoundaries9 = new LatencyBucketBoundaries("SECONDx100_MAX", 8, timeUnit3.toNanos(100L), LongCompanionObject.MAX_VALUE);
            SECONDx100_MAX = latencyBucketBoundaries9;
            $VALUES = new LatencyBucketBoundaries[]{latencyBucketBoundaries, latencyBucketBoundaries2, latencyBucketBoundaries3, latencyBucketBoundaries4, latencyBucketBoundaries5, latencyBucketBoundaries6, latencyBucketBoundaries7, latencyBucketBoundaries8, latencyBucketBoundaries9};
        }

        private LatencyBucketBoundaries(String str, int i2, long j2, long j3) {
            this.latencyLowerNs = j2;
            this.latencyUpperNs = j3;
        }

        public static LatencyBucketBoundaries valueOf(String str) {
            return (LatencyBucketBoundaries) Enum.valueOf(LatencyBucketBoundaries.class, str);
        }

        public static LatencyBucketBoundaries[] values() {
            return (LatencyBucketBoundaries[]) $VALUES.clone();
        }

        public long getLatencyLowerNs() {
            return this.latencyLowerNs;
        }

        public long getLatencyUpperNs() {
            return this.latencyUpperNs;
        }
    }

    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class LatencyFilter {
        public static LatencyFilter create(String str, long j2, long j3, int i2) {
            Utils.checkArgument(i2 >= 0, "Negative maxSpansToReturn.");
            Utils.checkArgument(j2 >= 0, "Negative latencyLowerNs");
            Utils.checkArgument(j3 >= 0, "Negative latencyUpperNs");
            return new AutoValue_SampledSpanStore_LatencyFilter(str, j2, j3, i2);
        }

        public abstract long getLatencyLowerNs();

        public abstract long getLatencyUpperNs();

        public abstract int getMaxSpansToReturn();

        public abstract String getSpanName();
    }

    @ThreadSafe
    /* loaded from: classes3.dex */
    private static final class NoopSampledSpanStore extends SampledSpanStore {
        private static final PerSpanNameSummary EMPTY_PER_SPAN_NAME_SUMMARY = PerSpanNameSummary.create(Collections.emptyMap(), Collections.emptyMap());
        @GuardedBy("registeredSpanNames")
        private final Set<String> registeredSpanNames;

        private NoopSampledSpanStore() {
            this.registeredSpanNames = new HashSet();
        }

        @Override // io.opencensus.trace.export.SampledSpanStore
        public Collection<SpanData> getErrorSampledSpans(ErrorFilter errorFilter) {
            Utils.checkNotNull(errorFilter, "errorFilter");
            return Collections.emptyList();
        }

        @Override // io.opencensus.trace.export.SampledSpanStore
        public Collection<SpanData> getLatencySampledSpans(LatencyFilter latencyFilter) {
            Utils.checkNotNull(latencyFilter, "latencyFilter");
            return Collections.emptyList();
        }

        @Override // io.opencensus.trace.export.SampledSpanStore
        public Set<String> getRegisteredSpanNamesForCollection() {
            Set<String> unmodifiableSet;
            synchronized (this.registeredSpanNames) {
                unmodifiableSet = Collections.unmodifiableSet(new HashSet(this.registeredSpanNames));
            }
            return unmodifiableSet;
        }

        @Override // io.opencensus.trace.export.SampledSpanStore
        public Summary getSummary() {
            HashMap hashMap = new HashMap();
            synchronized (this.registeredSpanNames) {
                for (String str : this.registeredSpanNames) {
                    hashMap.put(str, EMPTY_PER_SPAN_NAME_SUMMARY);
                }
            }
            return Summary.create(hashMap);
        }

        @Override // io.opencensus.trace.export.SampledSpanStore
        public void registerSpanNamesForCollection(Collection<String> collection) {
            Utils.checkNotNull(collection, "spanNames");
            synchronized (this.registeredSpanNames) {
                this.registeredSpanNames.addAll(collection);
            }
        }

        @Override // io.opencensus.trace.export.SampledSpanStore
        public void unregisterSpanNamesForCollection(Collection<String> collection) {
            Utils.checkNotNull(collection, "spanNames");
            synchronized (this.registeredSpanNames) {
                this.registeredSpanNames.removeAll(collection);
            }
        }
    }

    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class PerSpanNameSummary {
        public static PerSpanNameSummary create(Map<LatencyBucketBoundaries, Integer> map, Map<Status.CanonicalCode, Integer> map2) {
            return new AutoValue_SampledSpanStore_PerSpanNameSummary(Collections.unmodifiableMap(new HashMap((Map) Utils.checkNotNull(map, "numbersOfLatencySampledSpans"))), Collections.unmodifiableMap(new HashMap((Map) Utils.checkNotNull(map2, "numbersOfErrorSampledSpans"))));
        }

        public abstract Map<Status.CanonicalCode, Integer> getNumbersOfErrorSampledSpans();

        public abstract Map<LatencyBucketBoundaries, Integer> getNumbersOfLatencySampledSpans();
    }

    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class Summary {
        public static Summary create(Map<String, PerSpanNameSummary> map) {
            return new AutoValue_SampledSpanStore_Summary(Collections.unmodifiableMap(new HashMap((Map) Utils.checkNotNull(map, "perSpanNameSummary"))));
        }

        public abstract Map<String, PerSpanNameSummary> getPerSpanNameSummary();
    }

    protected SampledSpanStore() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SampledSpanStore a() {
        return new NoopSampledSpanStore();
    }

    public abstract Collection<SpanData> getErrorSampledSpans(ErrorFilter errorFilter);

    public abstract Collection<SpanData> getLatencySampledSpans(LatencyFilter latencyFilter);

    public abstract Set<String> getRegisteredSpanNamesForCollection();

    public abstract Summary getSummary();

    @Deprecated
    public abstract void registerSpanNamesForCollection(Collection<String> collection);

    @Deprecated
    public abstract void unregisterSpanNamesForCollection(Collection<String> collection);
}
