package io.opencensus.metrics;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import io.opencensus.internal.Utils;
import java.util.List;
import javax.annotation.concurrent.ThreadSafe;
@ThreadSafe
/* loaded from: classes3.dex */
public abstract class DoubleGauge {

    /* loaded from: classes3.dex */
    public static abstract class DoublePoint {
        public abstract void add(double d2);

        public abstract void set(double d2);
    }

    /* loaded from: classes3.dex */
    private static final class NoopDoubleGauge extends DoubleGauge {
        private final int labelKeysSize;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static final class NoopDoublePoint extends DoublePoint {
            private static final NoopDoublePoint INSTANCE = new NoopDoublePoint();

            private NoopDoublePoint() {
            }

            @Override // io.opencensus.metrics.DoubleGauge.DoublePoint
            public void add(double d2) {
            }

            @Override // io.opencensus.metrics.DoubleGauge.DoublePoint
            public void set(double d2) {
            }
        }

        NoopDoubleGauge(String str, String str2, String str3, List list) {
            Utils.checkNotNull(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
            Utils.checkNotNull(str2, "description");
            Utils.checkNotNull(str3, "unit");
            Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "labelKeys"), "labelKey");
            this.labelKeysSize = list.size();
        }

        static NoopDoubleGauge b(String str, String str2, String str3, List list) {
            return new NoopDoubleGauge(str, str2, str3, list);
        }

        @Override // io.opencensus.metrics.DoubleGauge
        public void clear() {
        }

        @Override // io.opencensus.metrics.DoubleGauge
        public NoopDoublePoint getDefaultTimeSeries() {
            return NoopDoublePoint.INSTANCE;
        }

        @Override // io.opencensus.metrics.DoubleGauge
        public /* bridge */ /* synthetic */ DoublePoint getOrCreateTimeSeries(List list) {
            return getOrCreateTimeSeries((List<LabelValue>) list);
        }

        @Override // io.opencensus.metrics.DoubleGauge
        public NoopDoublePoint getOrCreateTimeSeries(List<LabelValue> list) {
            Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "labelValues"), "labelValue");
            Utils.checkArgument(this.labelKeysSize == list.size(), "Label Keys and Label Values don't have same size.");
            return NoopDoublePoint.INSTANCE;
        }

        @Override // io.opencensus.metrics.DoubleGauge
        public void removeTimeSeries(List<LabelValue> list) {
            Utils.checkNotNull(list, "labelValues");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DoubleGauge a(String str, String str2, String str3, List list) {
        return NoopDoubleGauge.b(str, str2, str3, list);
    }

    public abstract void clear();

    public abstract DoublePoint getDefaultTimeSeries();

    public abstract DoublePoint getOrCreateTimeSeries(List<LabelValue> list);

    public abstract void removeTimeSeries(List<LabelValue> list);
}
