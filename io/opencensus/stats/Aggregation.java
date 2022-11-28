package io.opencensus.stats;

import io.opencensus.common.Function;
import io.opencensus.internal.Utils;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public abstract class Aggregation {

    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class Count extends Aggregation {
        private static final Count INSTANCE = new AutoValue_Aggregation_Count();

        /* JADX INFO: Access modifiers changed from: package-private */
        public Count() {
            super();
        }

        public static Count create() {
            return INSTANCE;
        }

        @Override // io.opencensus.stats.Aggregation
        public final <T> T match(Function<? super Sum, T> function, Function<? super Count, T> function2, Function<? super Distribution, T> function3, Function<? super LastValue, T> function4, Function<? super Aggregation, T> function5) {
            return function2.apply(this);
        }
    }

    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class Distribution extends Aggregation {
        /* JADX INFO: Access modifiers changed from: package-private */
        public Distribution() {
            super();
        }

        public static Distribution create(BucketBoundaries bucketBoundaries) {
            Utils.checkNotNull(bucketBoundaries, "bucketBoundaries");
            return new AutoValue_Aggregation_Distribution(bucketBoundaries);
        }

        public abstract BucketBoundaries getBucketBoundaries();

        @Override // io.opencensus.stats.Aggregation
        public final <T> T match(Function<? super Sum, T> function, Function<? super Count, T> function2, Function<? super Distribution, T> function3, Function<? super LastValue, T> function4, Function<? super Aggregation, T> function5) {
            return function3.apply(this);
        }
    }

    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class LastValue extends Aggregation {
        private static final LastValue INSTANCE = new AutoValue_Aggregation_LastValue();

        /* JADX INFO: Access modifiers changed from: package-private */
        public LastValue() {
            super();
        }

        public static LastValue create() {
            return INSTANCE;
        }

        @Override // io.opencensus.stats.Aggregation
        public final <T> T match(Function<? super Sum, T> function, Function<? super Count, T> function2, Function<? super Distribution, T> function3, Function<? super LastValue, T> function4, Function<? super Aggregation, T> function5) {
            return function4.apply(this);
        }
    }

    @Deprecated
    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class Mean extends Aggregation {
        private static final Mean INSTANCE = new AutoValue_Aggregation_Mean();

        /* JADX INFO: Access modifiers changed from: package-private */
        public Mean() {
            super();
        }

        public static Mean create() {
            return INSTANCE;
        }

        @Override // io.opencensus.stats.Aggregation
        public final <T> T match(Function<? super Sum, T> function, Function<? super Count, T> function2, Function<? super Distribution, T> function3, Function<? super LastValue, T> function4, Function<? super Aggregation, T> function5) {
            return function5.apply(this);
        }
    }

    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class Sum extends Aggregation {
        private static final Sum INSTANCE = new AutoValue_Aggregation_Sum();

        /* JADX INFO: Access modifiers changed from: package-private */
        public Sum() {
            super();
        }

        public static Sum create() {
            return INSTANCE;
        }

        @Override // io.opencensus.stats.Aggregation
        public final <T> T match(Function<? super Sum, T> function, Function<? super Count, T> function2, Function<? super Distribution, T> function3, Function<? super LastValue, T> function4, Function<? super Aggregation, T> function5) {
            return function.apply(this);
        }
    }

    private Aggregation() {
    }

    public abstract <T> T match(Function<? super Sum, T> function, Function<? super Count, T> function2, Function<? super Distribution, T> function3, Function<? super LastValue, T> function4, Function<? super Aggregation, T> function5);
}
