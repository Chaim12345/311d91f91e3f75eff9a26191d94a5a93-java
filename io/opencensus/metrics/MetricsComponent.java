package io.opencensus.metrics;

import io.opencensus.metrics.export.ExportComponent;
/* loaded from: classes3.dex */
public abstract class MetricsComponent {

    /* loaded from: classes3.dex */
    private static final class NoopMetricsComponent extends MetricsComponent {
        private static final ExportComponent EXPORT_COMPONENT = ExportComponent.newNoopExportComponent();
        private static final MetricRegistry METRIC_REGISTRY = MetricRegistry.a();

        private NoopMetricsComponent() {
        }

        @Override // io.opencensus.metrics.MetricsComponent
        public ExportComponent getExportComponent() {
            return EXPORT_COMPONENT;
        }

        @Override // io.opencensus.metrics.MetricsComponent
        public MetricRegistry getMetricRegistry() {
            return METRIC_REGISTRY;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MetricsComponent a() {
        return new NoopMetricsComponent();
    }

    public abstract ExportComponent getExportComponent();

    public abstract MetricRegistry getMetricRegistry();
}
