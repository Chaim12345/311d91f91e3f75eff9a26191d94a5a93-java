package io.opencensus.trace;

import io.opencensus.trace.AttributeValue;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class AutoValue_AttributeValue_AttributeValueLong extends AttributeValue.AttributeValueLong {
    private final Long longValue;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AttributeValue_AttributeValueLong(Long l2) {
        Objects.requireNonNull(l2, "Null longValue");
        this.longValue = l2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.opencensus.trace.AttributeValue.AttributeValueLong
    public Long b() {
        return this.longValue;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AttributeValue.AttributeValueLong) {
            return this.longValue.equals(((AttributeValue.AttributeValueLong) obj).b());
        }
        return false;
    }

    public int hashCode() {
        return this.longValue.hashCode() ^ 1000003;
    }

    public String toString() {
        return "AttributeValueLong{longValue=" + this.longValue + "}";
    }
}
