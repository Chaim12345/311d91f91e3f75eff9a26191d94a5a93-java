package io.opencensus.stats;

import io.opencensus.stats.Aggregation;
import javax.annotation.concurrent.Immutable;
@Deprecated
@Immutable
/* loaded from: classes3.dex */
final class AutoValue_Aggregation_Mean extends Aggregation.Mean {
    public boolean equals(Object obj) {
        return obj == this || (obj instanceof Aggregation.Mean);
    }

    public int hashCode() {
        return 1;
    }

    public String toString() {
        return "Mean{}";
    }
}
