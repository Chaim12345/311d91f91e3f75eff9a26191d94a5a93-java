package io.opencensus.tags;

import java.util.Objects;
/* loaded from: classes3.dex */
final class AutoValue_TagValue extends TagValue {
    private final String asString;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_TagValue(String str) {
        Objects.requireNonNull(str, "Null asString");
        this.asString = str;
    }

    @Override // io.opencensus.tags.TagValue
    public String asString() {
        return this.asString;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TagValue) {
            return this.asString.equals(((TagValue) obj).asString());
        }
        return false;
    }

    public int hashCode() {
        return this.asString.hashCode() ^ 1000003;
    }

    public String toString() {
        return "TagValue{asString=" + this.asString + "}";
    }
}
