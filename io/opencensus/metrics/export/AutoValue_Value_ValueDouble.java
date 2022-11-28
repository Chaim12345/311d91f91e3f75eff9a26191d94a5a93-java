package io.opencensus.metrics.export;

import io.opencensus.metrics.export.Value;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class AutoValue_Value_ValueDouble extends Value.ValueDouble {
    private final double value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Value_ValueDouble(double d2) {
        this.value = d2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.opencensus.metrics.export.Value.ValueDouble
    public double b() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof Value.ValueDouble) && Double.doubleToLongBits(this.value) == Double.doubleToLongBits(((Value.ValueDouble) obj).b());
    }

    public int hashCode() {
        return (int) (1000003 ^ ((Double.doubleToLongBits(this.value) >>> 32) ^ Double.doubleToLongBits(this.value)));
    }

    public String toString() {
        return "ValueDouble{value=" + this.value + "}";
    }
}
