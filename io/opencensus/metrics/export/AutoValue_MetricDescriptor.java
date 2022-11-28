package io.opencensus.metrics.export;

import io.opencensus.metrics.LabelKey;
import io.opencensus.metrics.export.MetricDescriptor;
import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
final class AutoValue_MetricDescriptor extends MetricDescriptor {
    private final String description;
    private final List<LabelKey> labelKeys;
    private final String name;
    private final MetricDescriptor.Type type;
    private final String unit;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_MetricDescriptor(String str, String str2, String str3, MetricDescriptor.Type type, List list) {
        Objects.requireNonNull(str, "Null name");
        this.name = str;
        Objects.requireNonNull(str2, "Null description");
        this.description = str2;
        Objects.requireNonNull(str3, "Null unit");
        this.unit = str3;
        Objects.requireNonNull(type, "Null type");
        this.type = type;
        Objects.requireNonNull(list, "Null labelKeys");
        this.labelKeys = list;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MetricDescriptor) {
            MetricDescriptor metricDescriptor = (MetricDescriptor) obj;
            return this.name.equals(metricDescriptor.getName()) && this.description.equals(metricDescriptor.getDescription()) && this.unit.equals(metricDescriptor.getUnit()) && this.type.equals(metricDescriptor.getType()) && this.labelKeys.equals(metricDescriptor.getLabelKeys());
        }
        return false;
    }

    @Override // io.opencensus.metrics.export.MetricDescriptor
    public String getDescription() {
        return this.description;
    }

    @Override // io.opencensus.metrics.export.MetricDescriptor
    public List<LabelKey> getLabelKeys() {
        return this.labelKeys;
    }

    @Override // io.opencensus.metrics.export.MetricDescriptor
    public String getName() {
        return this.name;
    }

    @Override // io.opencensus.metrics.export.MetricDescriptor
    public MetricDescriptor.Type getType() {
        return this.type;
    }

    @Override // io.opencensus.metrics.export.MetricDescriptor
    public String getUnit() {
        return this.unit;
    }

    public int hashCode() {
        return ((((((((this.name.hashCode() ^ 1000003) * 1000003) ^ this.description.hashCode()) * 1000003) ^ this.unit.hashCode()) * 1000003) ^ this.type.hashCode()) * 1000003) ^ this.labelKeys.hashCode();
    }

    public String toString() {
        return "MetricDescriptor{name=" + this.name + ", description=" + this.description + ", unit=" + this.unit + ", type=" + this.type + ", labelKeys=" + this.labelKeys + "}";
    }
}
