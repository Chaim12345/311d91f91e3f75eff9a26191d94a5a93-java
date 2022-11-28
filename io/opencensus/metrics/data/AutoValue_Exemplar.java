package io.opencensus.metrics.data;

import io.opencensus.common.Timestamp;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes3.dex */
final class AutoValue_Exemplar extends Exemplar {
    private final Map<String, AttachmentValue> attachments;
    private final Timestamp timestamp;
    private final double value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Exemplar(double d2, Timestamp timestamp, Map map) {
        this.value = d2;
        Objects.requireNonNull(timestamp, "Null timestamp");
        this.timestamp = timestamp;
        Objects.requireNonNull(map, "Null attachments");
        this.attachments = map;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Exemplar) {
            Exemplar exemplar = (Exemplar) obj;
            return Double.doubleToLongBits(this.value) == Double.doubleToLongBits(exemplar.getValue()) && this.timestamp.equals(exemplar.getTimestamp()) && this.attachments.equals(exemplar.getAttachments());
        }
        return false;
    }

    @Override // io.opencensus.metrics.data.Exemplar
    public Map<String, AttachmentValue> getAttachments() {
        return this.attachments;
    }

    @Override // io.opencensus.metrics.data.Exemplar
    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    @Override // io.opencensus.metrics.data.Exemplar
    public double getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.attachments.hashCode() ^ (((((int) (1000003 ^ ((Double.doubleToLongBits(this.value) >>> 32) ^ Double.doubleToLongBits(this.value)))) * 1000003) ^ this.timestamp.hashCode()) * 1000003);
    }

    public String toString() {
        return "Exemplar{value=" + this.value + ", timestamp=" + this.timestamp + ", attachments=" + this.attachments + "}";
    }
}
