package io.opencensus.trace;

import io.opencensus.trace.Tracestate;
import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
final class AutoValue_Tracestate extends Tracestate {
    private final List<Tracestate.Entry> entries;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Tracestate(List list) {
        Objects.requireNonNull(list, "Null entries");
        this.entries = list;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Tracestate) {
            return this.entries.equals(((Tracestate) obj).getEntries());
        }
        return false;
    }

    @Override // io.opencensus.trace.Tracestate
    public List<Tracestate.Entry> getEntries() {
        return this.entries;
    }

    public int hashCode() {
        return this.entries.hashCode() ^ 1000003;
    }

    public String toString() {
        return "Tracestate{entries=" + this.entries + "}";
    }
}
