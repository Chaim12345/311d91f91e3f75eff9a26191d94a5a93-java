package io.opencensus.metrics.export;

import io.opencensus.internal.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public abstract class Summary {

    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class Snapshot {

        @Immutable
        /* loaded from: classes3.dex */
        public static abstract class ValueAtPercentile {
            public static ValueAtPercentile create(double d2, double d3) {
                Utils.checkArgument(0.0d < d2 && d2 <= 100.0d, "percentile must be in the interval (0.0, 100.0]");
                Utils.checkArgument(d3 >= 0.0d, "value must be non-negative");
                return new AutoValue_Summary_Snapshot_ValueAtPercentile(d2, d3);
            }

            public abstract double getPercentile();

            public abstract double getValue();
        }

        public static Snapshot create(@Nullable Long l2, @Nullable Double d2, List<ValueAtPercentile> list) {
            Summary.checkCountAndSum(l2, d2);
            Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "valueAtPercentiles"), "valueAtPercentile");
            return new AutoValue_Summary_Snapshot(l2, d2, Collections.unmodifiableList(new ArrayList(list)));
        }

        @Nullable
        public abstract Long getCount();

        @Nullable
        public abstract Double getSum();

        public abstract List<ValueAtPercentile> getValueAtPercentiles();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkCountAndSum(@Nullable Long l2, @Nullable Double d2) {
        boolean z = false;
        Utils.checkArgument(l2 == null || l2.longValue() >= 0, "count must be non-negative.");
        Utils.checkArgument(d2 == null || d2.doubleValue() >= 0.0d, "sum must be non-negative.");
        if (l2 == null || l2.longValue() != 0) {
            return;
        }
        if (d2 == null || d2.doubleValue() == 0.0d) {
            z = true;
        }
        Utils.checkArgument(z, "sum must be 0 if count is 0.");
    }

    public static Summary create(@Nullable Long l2, @Nullable Double d2, Snapshot snapshot) {
        checkCountAndSum(l2, d2);
        Utils.checkNotNull(snapshot, "snapshot");
        return new AutoValue_Summary(l2, d2, snapshot);
    }

    @Nullable
    public abstract Long getCount();

    public abstract Snapshot getSnapshot();

    @Nullable
    public abstract Double getSum();
}
