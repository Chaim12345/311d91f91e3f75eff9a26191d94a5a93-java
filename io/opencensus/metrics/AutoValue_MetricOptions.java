package io.opencensus.metrics;

import io.opencensus.metrics.MetricOptions;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes3.dex */
final class AutoValue_MetricOptions extends MetricOptions {
    private final Map<LabelKey, LabelValue> constantLabels;
    private final String description;
    private final List<LabelKey> labelKeys;
    private final String unit;

    /* loaded from: classes3.dex */
    static final class Builder extends MetricOptions.Builder {
        private Map<LabelKey, LabelValue> constantLabels;
        private String description;
        private List<LabelKey> labelKeys;
        private String unit;

        @Override // io.opencensus.metrics.MetricOptions.Builder
        MetricOptions a() {
            String str = "";
            if (this.description == null) {
                str = " description";
            }
            if (this.unit == null) {
                str = str + " unit";
            }
            if (this.labelKeys == null) {
                str = str + " labelKeys";
            }
            if (this.constantLabels == null) {
                str = str + " constantLabels";
            }
            if (str.isEmpty()) {
                return new AutoValue_MetricOptions(this.description, this.unit, this.labelKeys, this.constantLabels);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // io.opencensus.metrics.MetricOptions.Builder
        Map b() {
            Map<LabelKey, LabelValue> map = this.constantLabels;
            if (map != null) {
                return map;
            }
            throw new IllegalStateException("Property \"constantLabels\" has not been set");
        }

        @Override // io.opencensus.metrics.MetricOptions.Builder
        List c() {
            List<LabelKey> list = this.labelKeys;
            if (list != null) {
                return list;
            }
            throw new IllegalStateException("Property \"labelKeys\" has not been set");
        }

        @Override // io.opencensus.metrics.MetricOptions.Builder
        public MetricOptions.Builder setConstantLabels(Map<LabelKey, LabelValue> map) {
            Objects.requireNonNull(map, "Null constantLabels");
            this.constantLabels = map;
            return this;
        }

        @Override // io.opencensus.metrics.MetricOptions.Builder
        public MetricOptions.Builder setDescription(String str) {
            Objects.requireNonNull(str, "Null description");
            this.description = str;
            return this;
        }

        @Override // io.opencensus.metrics.MetricOptions.Builder
        public MetricOptions.Builder setLabelKeys(List<LabelKey> list) {
            Objects.requireNonNull(list, "Null labelKeys");
            this.labelKeys = list;
            return this;
        }

        @Override // io.opencensus.metrics.MetricOptions.Builder
        public MetricOptions.Builder setUnit(String str) {
            Objects.requireNonNull(str, "Null unit");
            this.unit = str;
            return this;
        }
    }

    private AutoValue_MetricOptions(String str, String str2, List<LabelKey> list, Map<LabelKey, LabelValue> map) {
        this.description = str;
        this.unit = str2;
        this.labelKeys = list;
        this.constantLabels = map;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MetricOptions) {
            MetricOptions metricOptions = (MetricOptions) obj;
            return this.description.equals(metricOptions.getDescription()) && this.unit.equals(metricOptions.getUnit()) && this.labelKeys.equals(metricOptions.getLabelKeys()) && this.constantLabels.equals(metricOptions.getConstantLabels());
        }
        return false;
    }

    @Override // io.opencensus.metrics.MetricOptions
    public Map<LabelKey, LabelValue> getConstantLabels() {
        return this.constantLabels;
    }

    @Override // io.opencensus.metrics.MetricOptions
    public String getDescription() {
        return this.description;
    }

    @Override // io.opencensus.metrics.MetricOptions
    public List<LabelKey> getLabelKeys() {
        return this.labelKeys;
    }

    @Override // io.opencensus.metrics.MetricOptions
    public String getUnit() {
        return this.unit;
    }

    public int hashCode() {
        return ((((((this.description.hashCode() ^ 1000003) * 1000003) ^ this.unit.hashCode()) * 1000003) ^ this.labelKeys.hashCode()) * 1000003) ^ this.constantLabels.hashCode();
    }

    public String toString() {
        return "MetricOptions{description=" + this.description + ", unit=" + this.unit + ", labelKeys=" + this.labelKeys + ", constantLabels=" + this.constantLabels + "}";
    }
}
