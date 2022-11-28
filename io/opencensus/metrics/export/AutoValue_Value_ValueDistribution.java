package io.opencensus.metrics.export;

import io.opencensus.metrics.export.Value;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class AutoValue_Value_ValueDistribution extends Value.ValueDistribution {
    private final Distribution value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Value_ValueDistribution(Distribution distribution) {
        Objects.requireNonNull(distribution, "Null value");
        this.value = distribution;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.opencensus.metrics.export.Value.ValueDistribution
    public Distribution b() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Value.ValueDistribution) {
            return this.value.equals(((Value.ValueDistribution) obj).b());
        }
        return false;
    }

    public int hashCode() {
        return this.value.hashCode() ^ 1000003;
    }

    public String toString() {
        return "ValueDistribution{value=" + this.value + "}";
    }
}
