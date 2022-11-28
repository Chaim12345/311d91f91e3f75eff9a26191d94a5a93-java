package io.opencensus.metrics;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import io.opencensus.common.ToLongFunction;
import io.opencensus.internal.Utils;
import java.util.List;
import javax.annotation.concurrent.ThreadSafe;
@ThreadSafe
/* loaded from: classes3.dex */
public abstract class DerivedLongGauge {

    /* loaded from: classes3.dex */
    private static final class NoopDerivedLongGauge extends DerivedLongGauge {
        private final int labelKeysSize;

        NoopDerivedLongGauge(String str, String str2, String str3, List list) {
            Utils.checkNotNull(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
            Utils.checkNotNull(str2, "description");
            Utils.checkNotNull(str3, "unit");
            Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "labelKeys"), "labelKey");
            this.labelKeysSize = list.size();
        }

        static NoopDerivedLongGauge b(String str, String str2, String str3, List list) {
            return new NoopDerivedLongGauge(str, str2, str3, list);
        }

        @Override // io.opencensus.metrics.DerivedLongGauge
        public void clear() {
        }

        @Override // io.opencensus.metrics.DerivedLongGauge
        public <T> void createTimeSeries(List<LabelValue> list, T t2, ToLongFunction<T> toLongFunction) {
            Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "labelValues"), "labelValue");
            Utils.checkArgument(this.labelKeysSize == list.size(), "Label Keys and Label Values don't have same size.");
            Utils.checkNotNull(toLongFunction, "function");
        }

        @Override // io.opencensus.metrics.DerivedLongGauge
        public void removeTimeSeries(List<LabelValue> list) {
            Utils.checkNotNull(list, "labelValues");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DerivedLongGauge a(String str, String str2, String str3, List list) {
        return NoopDerivedLongGauge.b(str, str2, str3, list);
    }

    public abstract void clear();

    public abstract <T> void createTimeSeries(List<LabelValue> list, T t2, ToLongFunction<T> toLongFunction);

    public abstract void removeTimeSeries(List<LabelValue> list);
}
