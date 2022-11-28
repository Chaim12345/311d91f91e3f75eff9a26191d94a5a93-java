package io.opencensus.stats;

import io.opencensus.stats.View;
import javax.annotation.concurrent.Immutable;
@Deprecated
@Immutable
/* loaded from: classes3.dex */
final class AutoValue_View_AggregationWindow_Cumulative extends View.AggregationWindow.Cumulative {
    public boolean equals(Object obj) {
        return obj == this || (obj instanceof View.AggregationWindow.Cumulative);
    }

    public int hashCode() {
        return 1;
    }

    public String toString() {
        return "Cumulative{}";
    }
}
