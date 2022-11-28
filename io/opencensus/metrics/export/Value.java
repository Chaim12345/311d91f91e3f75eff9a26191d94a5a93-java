package io.opencensus.metrics.export;

import io.opencensus.common.Function;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public abstract class Value {

    @Immutable
    /* loaded from: classes3.dex */
    static abstract class ValueDistribution extends Value {
        static ValueDistribution a(Distribution distribution) {
            return new AutoValue_Value_ValueDistribution(distribution);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Distribution b();

        @Override // io.opencensus.metrics.export.Value
        public final <T> T match(Function<? super Double, T> function, Function<? super Long, T> function2, Function<? super Distribution, T> function3, Function<? super Summary, T> function4, Function<? super Value, T> function5) {
            return function3.apply(b());
        }
    }

    @Immutable
    /* loaded from: classes3.dex */
    static abstract class ValueDouble extends Value {
        static ValueDouble a(double d2) {
            return new AutoValue_Value_ValueDouble(d2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract double b();

        @Override // io.opencensus.metrics.export.Value
        public final <T> T match(Function<? super Double, T> function, Function<? super Long, T> function2, Function<? super Distribution, T> function3, Function<? super Summary, T> function4, Function<? super Value, T> function5) {
            return function.apply(Double.valueOf(b()));
        }
    }

    @Immutable
    /* loaded from: classes3.dex */
    static abstract class ValueLong extends Value {
        static ValueLong a(long j2) {
            return new AutoValue_Value_ValueLong(j2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract long b();

        @Override // io.opencensus.metrics.export.Value
        public final <T> T match(Function<? super Double, T> function, Function<? super Long, T> function2, Function<? super Distribution, T> function3, Function<? super Summary, T> function4, Function<? super Value, T> function5) {
            return function2.apply(Long.valueOf(b()));
        }
    }

    @Immutable
    /* loaded from: classes3.dex */
    static abstract class ValueSummary extends Value {
        static ValueSummary a(Summary summary) {
            return new AutoValue_Value_ValueSummary(summary);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Summary b();

        @Override // io.opencensus.metrics.export.Value
        public final <T> T match(Function<? super Double, T> function, Function<? super Long, T> function2, Function<? super Distribution, T> function3, Function<? super Summary, T> function4, Function<? super Value, T> function5) {
            return function4.apply(b());
        }
    }

    Value() {
    }

    public static Value distributionValue(Distribution distribution) {
        return ValueDistribution.a(distribution);
    }

    public static Value doubleValue(double d2) {
        return ValueDouble.a(d2);
    }

    public static Value longValue(long j2) {
        return ValueLong.a(j2);
    }

    public static Value summaryValue(Summary summary) {
        return ValueSummary.a(summary);
    }

    public abstract <T> T match(Function<? super Double, T> function, Function<? super Long, T> function2, Function<? super Distribution, T> function3, Function<? super Summary, T> function4, Function<? super Value, T> function5);
}
