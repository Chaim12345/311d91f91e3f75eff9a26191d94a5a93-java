package io.opencensus.trace;

import io.opencensus.trace.AttributeValue;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class AutoValue_AttributeValue_AttributeValueString extends AttributeValue.AttributeValueString {
    private final String stringValue;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AttributeValue_AttributeValueString(String str) {
        Objects.requireNonNull(str, "Null stringValue");
        this.stringValue = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.opencensus.trace.AttributeValue.AttributeValueString
    public String b() {
        return this.stringValue;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AttributeValue.AttributeValueString) {
            return this.stringValue.equals(((AttributeValue.AttributeValueString) obj).b());
        }
        return false;
    }

    public int hashCode() {
        return this.stringValue.hashCode() ^ 1000003;
    }

    public String toString() {
        return "AttributeValueString{stringValue=" + this.stringValue + "}";
    }
}
