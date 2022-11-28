package io.opencensus.trace;

import java.util.Map;
import java.util.Objects;
/* loaded from: classes3.dex */
final class AutoValue_Annotation extends Annotation {
    private final Map<String, AttributeValue> attributes;
    private final String description;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Annotation(String str, Map map) {
        Objects.requireNonNull(str, "Null description");
        this.description = str;
        Objects.requireNonNull(map, "Null attributes");
        this.attributes = map;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Annotation) {
            Annotation annotation = (Annotation) obj;
            return this.description.equals(annotation.getDescription()) && this.attributes.equals(annotation.getAttributes());
        }
        return false;
    }

    @Override // io.opencensus.trace.Annotation
    public Map<String, AttributeValue> getAttributes() {
        return this.attributes;
    }

    @Override // io.opencensus.trace.Annotation
    public String getDescription() {
        return this.description;
    }

    public int hashCode() {
        return ((this.description.hashCode() ^ 1000003) * 1000003) ^ this.attributes.hashCode();
    }

    public String toString() {
        return "Annotation{description=" + this.description + ", attributes=" + this.attributes + "}";
    }
}
