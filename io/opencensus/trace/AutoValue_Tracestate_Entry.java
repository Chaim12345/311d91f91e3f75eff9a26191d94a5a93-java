package io.opencensus.trace;

import io.opencensus.trace.Tracestate;
import java.util.Objects;
/* loaded from: classes3.dex */
final class AutoValue_Tracestate_Entry extends Tracestate.Entry {
    private final String key;
    private final String value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Tracestate_Entry(String str, String str2) {
        Objects.requireNonNull(str, "Null key");
        this.key = str;
        Objects.requireNonNull(str2, "Null value");
        this.value = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Tracestate.Entry) {
            Tracestate.Entry entry = (Tracestate.Entry) obj;
            return this.key.equals(entry.getKey()) && this.value.equals(entry.getValue());
        }
        return false;
    }

    @Override // io.opencensus.trace.Tracestate.Entry
    public String getKey() {
        return this.key;
    }

    @Override // io.opencensus.trace.Tracestate.Entry
    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        return ((this.key.hashCode() ^ 1000003) * 1000003) ^ this.value.hashCode();
    }

    public String toString() {
        return "Entry{key=" + this.key + ", value=" + this.value + "}";
    }
}
