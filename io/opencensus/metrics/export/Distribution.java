package io.opencensus.metrics.export;

import io.opencensus.common.Function;
import io.opencensus.internal.Utils;
import io.opencensus.metrics.data.Exemplar;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public abstract class Distribution {

    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class Bucket {
        public static Bucket create(long j2) {
            Utils.checkArgument(j2 >= 0, "bucket count should be non-negative.");
            return new AutoValue_Distribution_Bucket(j2, null);
        }

        public static Bucket create(long j2, Exemplar exemplar) {
            Utils.checkArgument(j2 >= 0, "bucket count should be non-negative.");
            Utils.checkNotNull(exemplar, "exemplar");
            return new AutoValue_Distribution_Bucket(j2, exemplar);
        }

        public abstract long getCount();

        @Nullable
        public abstract Exemplar getExemplar();
    }

    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class BucketOptions {

        @Immutable
        /* loaded from: classes3.dex */
        public static abstract class ExplicitOptions extends BucketOptions {
            /* JADX INFO: Access modifiers changed from: package-private */
            public ExplicitOptions() {
                super();
            }

            private static void checkBucketBoundsAreSorted(List<Double> list) {
                if (list.size() >= 1) {
                    double doubleValue = ((Double) Utils.checkNotNull(list.get(0), "bucketBoundary")).doubleValue();
                    Utils.checkArgument(doubleValue > 0.0d, "bucket boundary should be > 0");
                    int i2 = 1;
                    while (i2 < list.size()) {
                        double doubleValue2 = ((Double) Utils.checkNotNull(list.get(i2), "bucketBoundary")).doubleValue();
                        Utils.checkArgument(doubleValue < doubleValue2, "bucket boundaries not sorted.");
                        i2++;
                        doubleValue = doubleValue2;
                    }
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static ExplicitOptions create(List<Double> list) {
                Utils.checkNotNull(list, "bucketBoundaries");
                List unmodifiableList = Collections.unmodifiableList(new ArrayList(list));
                checkBucketBoundsAreSorted(unmodifiableList);
                return new AutoValue_Distribution_BucketOptions_ExplicitOptions(unmodifiableList);
            }

            public abstract List<Double> getBucketBoundaries();

            @Override // io.opencensus.metrics.export.Distribution.BucketOptions
            public final <T> T match(Function<? super ExplicitOptions, T> function, Function<? super BucketOptions, T> function2) {
                return function.apply(this);
            }
        }

        private BucketOptions() {
        }

        public static BucketOptions explicitOptions(List<Double> list) {
            return ExplicitOptions.create(list);
        }

        public abstract <T> T match(Function<? super ExplicitOptions, T> function, Function<? super BucketOptions, T> function2);
    }

    public static Distribution create(long j2, double d2, double d3, BucketOptions bucketOptions, List<Bucket> list) {
        int i2 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        Utils.checkArgument(i2 >= 0, "count should be non-negative.");
        int i3 = (d3 > 0.0d ? 1 : (d3 == 0.0d ? 0 : -1));
        Utils.checkArgument(i3 >= 0, "sum of squared deviations should be non-negative.");
        if (i2 == 0) {
            Utils.checkArgument(d2 == 0.0d, "sum should be 0 if count is 0.");
            Utils.checkArgument(i3 == 0, "sum of squared deviations should be 0 if count is 0.");
        }
        Utils.checkNotNull(bucketOptions, "bucketOptions");
        List unmodifiableList = Collections.unmodifiableList(new ArrayList((Collection) Utils.checkNotNull(list, "buckets")));
        Utils.checkListElementNotNull(unmodifiableList, "bucket");
        return new AutoValue_Distribution(j2, d2, d3, bucketOptions, unmodifiableList);
    }

    @Nullable
    public abstract BucketOptions getBucketOptions();

    public abstract List<Bucket> getBuckets();

    public abstract long getCount();

    public abstract double getSum();

    public abstract double getSumOfSquaredDeviations();
}
