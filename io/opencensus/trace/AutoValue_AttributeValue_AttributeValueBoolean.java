package io.opencensus.trace;

import io.opencensus.trace.AttributeValue;
import java.util.Objects;
/* loaded from: classes3.dex */
final class AutoValue_AttributeValue_AttributeValueBoolean extends AttributeValue.AttributeValueBoolean {
    private final Boolean booleanValue;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AttributeValue_AttributeValueBoolean(Boolean bool) {
        Objects.requireNonNull(bool, "Null booleanValue");
        this.booleanValue = bool;
    }

    @Override // io.opencensus.trace.AttributeValue.AttributeValueBoolean
    Boolean b() {
        return this.booleanValue;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AttributeValue.AttributeValueBoolean) {
            return this.booleanValue.equals(((AttributeValue.AttributeValueBoolean) obj).b());
        }
        return false;
    }

    public int hashCode() {
        return this.booleanValue.hashCode() ^ 1000003;
    }

    public String toString() {
        return "AttributeValueBoolean{booleanValue=" + this.booleanValue + "}";
    }
}
