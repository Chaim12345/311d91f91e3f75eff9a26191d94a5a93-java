package io.opencensus.trace;

import io.opencensus.trace.AttributeValue;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class AutoValue_AttributeValue_AttributeValueDouble extends AttributeValue.AttributeValueDouble {
    private final Double doubleValue;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AttributeValue_AttributeValueDouble(Double d2) {
        Objects.requireNonNull(d2, "Null doubleValue");
        this.doubleValue = d2;
    }

    @Override // io.opencensus.trace.AttributeValue.AttributeValueDouble
    Double b() {
        return this.doubleValue;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AttributeValue.AttributeValueDouble) {
            return this.doubleValue.equals(((AttributeValue.AttributeValueDouble) obj).b());
        }
        return false;
    }

    public int hashCode() {
        return this.doubleValue.hashCode() ^ 1000003;
    }

    public String toString() {
        return "AttributeValueDouble{doubleValue=" + this.doubleValue + "}";
    }
}
