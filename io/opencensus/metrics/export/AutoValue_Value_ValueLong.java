package io.opencensus.metrics.export;

import io.opencensus.metrics.export.Value;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class AutoValue_Value_ValueLong extends Value.ValueLong {
    private final long value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Value_ValueLong(long j2) {
        this.value = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.opencensus.metrics.export.Value.ValueLong
    public long b() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof Value.ValueLong) && this.value == ((Value.ValueLong) obj).b();
    }

    public int hashCode() {
        long j2 = this.value;
        return (int) (1000003 ^ (j2 ^ (j2 >>> 32)));
    }

    public String toString() {
        return "ValueLong{value=" + this.value + "}";
    }
}
