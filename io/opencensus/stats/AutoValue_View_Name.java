package io.opencensus.stats;

import io.opencensus.stats.View;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class AutoValue_View_Name extends View.Name {
    private final String asString;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_View_Name(String str) {
        Objects.requireNonNull(str, "Null asString");
        this.asString = str;
    }

    @Override // io.opencensus.stats.View.Name
    public String asString() {
        return this.asString;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof View.Name) {
            return this.asString.equals(((View.Name) obj).asString());
        }
        return false;
    }

    public int hashCode() {
        return this.asString.hashCode() ^ 1000003;
    }

    public String toString() {
        return "Name{asString=" + this.asString + "}";
    }
}
