package io.opencensus.metrics.export;
/* loaded from: classes3.dex */
public abstract class ExportComponent {

    /* loaded from: classes3.dex */
    private static final class NoopExportComponent extends ExportComponent {
        private static final MetricProducerManager METRIC_PRODUCER_MANAGER = MetricProducerManager.a();

        private NoopExportComponent() {
        }

        @Override // io.opencensus.metrics.export.ExportComponent
        public MetricProducerManager getMetricProducerManager() {
            return METRIC_PRODUCER_MANAGER;
        }
    }

    public static ExportComponent newNoopExportComponent() {
        return new NoopExportComponent();
    }

    public abstract MetricProducerManager getMetricProducerManager();
}
