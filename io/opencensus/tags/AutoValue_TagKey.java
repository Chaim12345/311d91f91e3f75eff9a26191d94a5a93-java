package io.opencensus.tags;

import java.util.Objects;
/* loaded from: classes3.dex */
final class AutoValue_TagKey extends TagKey {
    private final String name;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_TagKey(String str) {
        Objects.requireNonNull(str, "Null name");
        this.name = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TagKey) {
            return this.name.equals(((TagKey) obj).getName());
        }
        return false;
    }

    @Override // io.opencensus.tags.TagKey
    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return this.name.hashCode() ^ 1000003;
    }

    public String toString() {
        return "TagKey{name=" + this.name + "}";
    }
}
