package io.opencensus.metrics;

import io.opencensus.internal.Utils;
import java.util.List;
import javax.annotation.concurrent.ThreadSafe;
@ThreadSafe
/* loaded from: classes3.dex */
public abstract class LongGauge {

    /* loaded from: classes3.dex */
    public static abstract class LongPoint {
        public abstract void add(long j2);

        public abstract void set(long j2);
    }

    /* loaded from: classes3.dex */
    private static final class NoopLongGauge extends LongGauge {
        private final int labelKeysSize;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static final class NoopLongPoint extends LongPoint {
            private static final NoopLongPoint INSTANCE = new NoopLongPoint();

            private NoopLongPoint() {
            }

            @Override // io.opencensus.metrics.LongGauge.LongPoint
            public void add(long j2) {
            }

            @Override // io.opencensus.metrics.LongGauge.LongPoint
            public void set(long j2) {
            }
        }

        NoopLongGauge(String str, String str2, String str3, List list) {
            this.labelKeysSize = list.size();
        }

        static NoopLongGauge b(String str, String str2, String str3, List list) {
            return new NoopLongGauge(str, str2, str3, list);
        }

        @Override // io.opencensus.metrics.LongGauge
        public void clear() {
        }

        @Override // io.opencensus.metrics.LongGauge
        public NoopLongPoint getDefaultTimeSeries() {
            return NoopLongPoint.INSTANCE;
        }

        @Override // io.opencensus.metrics.LongGauge
        public /* bridge */ /* synthetic */ LongPoint getOrCreateTimeSeries(List list) {
            return getOrCreateTimeSeries((List<LabelValue>) list);
        }

        @Override // io.opencensus.metrics.LongGauge
        public NoopLongPoint getOrCreateTimeSeries(List<LabelValue> list) {
            Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "labelValues"), "labelValue");
            Utils.checkArgument(this.labelKeysSize == list.size(), "Label Keys and Label Values don't have same size.");
            return NoopLongPoint.INSTANCE;
        }

        @Override // io.opencensus.metrics.LongGauge
        public void removeTimeSeries(List<LabelValue> list) {
            Utils.checkNotNull(list, "labelValues");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LongGauge a(String str, String str2, String str3, List list) {
        return NoopLongGauge.b(str, str2, str3, list);
    }

    public abstract void clear();

    public abstract LongPoint getDefaultTimeSeries();

    public abstract LongPoint getOrCreateTimeSeries(List<LabelValue> list);

    public abstract void removeTimeSeries(List<LabelValue> list);
}
