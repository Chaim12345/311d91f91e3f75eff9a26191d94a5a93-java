package io.opencensus.metrics.data;

import io.opencensus.metrics.data.AttachmentValue;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class AutoValue_AttachmentValue_AttachmentValueString extends AttachmentValue.AttachmentValueString {
    private final String value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AttachmentValue_AttachmentValueString(String str) {
        Objects.requireNonNull(str, "Null value");
        this.value = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AttachmentValue.AttachmentValueString) {
            return this.value.equals(((AttachmentValue.AttachmentValueString) obj).getValue());
        }
        return false;
    }

    @Override // io.opencensus.metrics.data.AttachmentValue
    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode() ^ 1000003;
    }

    public String toString() {
        return "AttachmentValueString{value=" + this.value + "}";
    }
}
