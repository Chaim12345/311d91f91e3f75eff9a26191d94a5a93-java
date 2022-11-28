package io.opencensus.metrics;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import io.opencensus.internal.Utils;
import java.util.List;
/* loaded from: classes3.dex */
public abstract class MetricRegistry {

    /* loaded from: classes3.dex */
    private static final class NoopMetricRegistry extends MetricRegistry {
        private NoopMetricRegistry() {
        }

        @Override // io.opencensus.metrics.MetricRegistry
        public DerivedDoubleCumulative addDerivedDoubleCumulative(String str, MetricOptions metricOptions) {
            return DerivedDoubleCumulative.a((String) Utils.checkNotNull(str, AppMeasurementSdk.ConditionalUserProperty.NAME), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }

        @Override // io.opencensus.metrics.MetricRegistry
        public DerivedDoubleGauge addDerivedDoubleGauge(String str, MetricOptions metricOptions) {
            return DerivedDoubleGauge.a((String) Utils.checkNotNull(str, AppMeasurementSdk.ConditionalUserProperty.NAME), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }

        @Override // io.opencensus.metrics.MetricRegistry
        public DerivedLongCumulative addDerivedLongCumulative(String str, MetricOptions metricOptions) {
            return DerivedLongCumulative.a((String) Utils.checkNotNull(str, AppMeasurementSdk.ConditionalUserProperty.NAME), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }

        @Override // io.opencensus.metrics.MetricRegistry
        public DerivedLongGauge addDerivedLongGauge(String str, MetricOptions metricOptions) {
            return DerivedLongGauge.a((String) Utils.checkNotNull(str, AppMeasurementSdk.ConditionalUserProperty.NAME), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }

        @Override // io.opencensus.metrics.MetricRegistry
        public DoubleCumulative addDoubleCumulative(String str, MetricOptions metricOptions) {
            return DoubleCumulative.a((String) Utils.checkNotNull(str, AppMeasurementSdk.ConditionalUserProperty.NAME), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }

        @Override // io.opencensus.metrics.MetricRegistry
        public DoubleGauge addDoubleGauge(String str, MetricOptions metricOptions) {
            return DoubleGauge.a((String) Utils.checkNotNull(str, AppMeasurementSdk.ConditionalUserProperty.NAME), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }

        @Override // io.opencensus.metrics.MetricRegistry
        public LongCumulative addLongCumulative(String str, MetricOptions metricOptions) {
            return LongCumulative.a((String) Utils.checkNotNull(str, AppMeasurementSdk.ConditionalUserProperty.NAME), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }

        @Override // io.opencensus.metrics.MetricRegistry
        public LongGauge addLongGauge(String str, MetricOptions metricOptions) {
            return LongGauge.a((String) Utils.checkNotNull(str, AppMeasurementSdk.ConditionalUserProperty.NAME), metricOptions.getDescription(), metricOptions.getUnit(), metricOptions.getLabelKeys());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MetricRegistry a() {
        return new NoopMetricRegistry();
    }

    public abstract DerivedDoubleCumulative addDerivedDoubleCumulative(String str, MetricOptions metricOptions);

    public abstract DerivedDoubleGauge addDerivedDoubleGauge(String str, MetricOptions metricOptions);

    @Deprecated
    public DerivedDoubleGauge addDerivedDoubleGauge(String str, String str2, String str3, List<LabelKey> list) {
        return addDerivedDoubleGauge(str, MetricOptions.builder().setDescription(str2).setUnit(str3).setLabelKeys(list).build());
    }

    public abstract DerivedLongCumulative addDerivedLongCumulative(String str, MetricOptions metricOptions);

    public abstract DerivedLongGauge addDerivedLongGauge(String str, MetricOptions metricOptions);

    @Deprecated
    public DerivedLongGauge addDerivedLongGauge(String str, String str2, String str3, List<LabelKey> list) {
        return addDerivedLongGauge(str, MetricOptions.builder().setDescription(str2).setUnit(str3).setLabelKeys(list).build());
    }

    public abstract DoubleCumulative addDoubleCumulative(String str, MetricOptions metricOptions);

    public abstract DoubleGauge addDoubleGauge(String str, MetricOptions metricOptions);

    @Deprecated
    public DoubleGauge addDoubleGauge(String str, String str2, String str3, List<LabelKey> list) {
        return addDoubleGauge(str, MetricOptions.builder().setDescription(str2).setUnit(str3).setLabelKeys(list).build());
    }

    public abstract LongCumulative addLongCumulative(String str, MetricOptions metricOptions);

    public abstract LongGauge addLongGauge(String str, MetricOptions metricOptions);

    @Deprecated
    public LongGauge addLongGauge(String str, String str2, String str3, List<LabelKey> list) {
        return addLongGauge(str, MetricOptions.builder().setDescription(str2).setUnit(str3).setLabelKeys(list).build());
    }
}
