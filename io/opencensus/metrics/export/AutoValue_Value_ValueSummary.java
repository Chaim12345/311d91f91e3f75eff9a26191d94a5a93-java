package io.opencensus.metrics.export;

import io.opencensus.metrics.export.Value;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class AutoValue_Value_ValueSummary extends Value.ValueSummary {
    private final Summary value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Value_ValueSummary(Summary summary) {
        Objects.requireNonNull(summary, "Null value");
        this.value = summary;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.opencensus.metrics.export.Value.ValueSummary
    public Summary b() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Value.ValueSummary) {
            return this.value.equals(((Value.ValueSummary) obj).b());
        }
        return false;
    }

    public int hashCode() {
        return this.value.hashCode() ^ 1000003;
    }

    public String toString() {
        return "ValueSummary{value=" + this.value + "}";
    }
}
