package io.opencensus.metrics;

import java.util.Objects;
/* loaded from: classes3.dex */
final class AutoValue_LabelKey extends LabelKey {
    private final String description;
    private final String key;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_LabelKey(String str, String str2) {
        Objects.requireNonNull(str, "Null key");
        this.key = str;
        Objects.requireNonNull(str2, "Null description");
        this.description = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof LabelKey) {
            LabelKey labelKey = (LabelKey) obj;
            return this.key.equals(labelKey.getKey()) && this.description.equals(labelKey.getDescription());
        }
        return false;
    }

    @Override // io.opencensus.metrics.LabelKey
    public String getDescription() {
        return this.description;
    }

    @Override // io.opencensus.metrics.LabelKey
    public String getKey() {
        return this.key;
    }

    public int hashCode() {
        return ((this.key.hashCode() ^ 1000003) * 1000003) ^ this.description.hashCode();
    }

    public String toString() {
        return "LabelKey{key=" + this.key + ", description=" + this.description + "}";
    }
}
