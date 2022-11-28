package io.opencensus.metrics.export;

import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
final class AutoValue_Metric extends Metric {
    private final MetricDescriptor metricDescriptor;
    private final List<TimeSeries> timeSeriesList;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Metric(MetricDescriptor metricDescriptor, List list) {
        Objects.requireNonNull(metricDescriptor, "Null metricDescriptor");
        this.metricDescriptor = metricDescriptor;
        Objects.requireNonNull(list, "Null timeSeriesList");
        this.timeSeriesList = list;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Metric) {
            Metric metric = (Metric) obj;
            return this.metricDescriptor.equals(metric.getMetricDescriptor()) && this.timeSeriesList.equals(metric.getTimeSeriesList());
        }
        return false;
    }

    @Override // io.opencensus.metrics.export.Metric
    public MetricDescriptor getMetricDescriptor() {
        return this.metricDescriptor;
    }

    @Override // io.opencensus.metrics.export.Metric
    public List<TimeSeries> getTimeSeriesList() {
        return this.timeSeriesList;
    }

    public int hashCode() {
        return ((this.metricDescriptor.hashCode() ^ 1000003) * 1000003) ^ this.timeSeriesList.hashCode();
    }

    public String toString() {
        return "Metric{metricDescriptor=" + this.metricDescriptor + ", timeSeriesList=" + this.timeSeriesList + "}";
    }
}
