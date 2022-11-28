package io.opencensus.metrics.export;

import io.opencensus.internal.Utils;
import io.opencensus.metrics.export.MetricDescriptor;
import io.opencensus.metrics.export.Value;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public abstract class Metric {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.opencensus.metrics.export.Metric$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f10971a;

        static {
            int[] iArr = new int[MetricDescriptor.Type.values().length];
            f10971a = iArr;
            try {
                iArr[MetricDescriptor.Type.GAUGE_INT64.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10971a[MetricDescriptor.Type.CUMULATIVE_INT64.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10971a[MetricDescriptor.Type.CUMULATIVE_DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10971a[MetricDescriptor.Type.GAUGE_DOUBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f10971a[MetricDescriptor.Type.GAUGE_DISTRIBUTION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f10971a[MetricDescriptor.Type.CUMULATIVE_DISTRIBUTION.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f10971a[MetricDescriptor.Type.SUMMARY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private static void checkTypeMatch(MetricDescriptor.Type type, List<TimeSeries> list) {
        for (TimeSeries timeSeries : list) {
            for (Point point : timeSeries.getPoints()) {
                Value value = point.getValue();
                String simpleName = value.getClass().getSuperclass() != null ? value.getClass().getSuperclass().getSimpleName() : "";
                switch (AnonymousClass1.f10971a[type.ordinal()]) {
                    case 1:
                    case 2:
                        Utils.checkArgument(value instanceof Value.ValueLong, "Type mismatch: %s, %s.", type, simpleName);
                        break;
                    case 3:
                    case 4:
                        Utils.checkArgument(value instanceof Value.ValueDouble, "Type mismatch: %s, %s.", type, simpleName);
                        break;
                    case 5:
                    case 6:
                        Utils.checkArgument(value instanceof Value.ValueDistribution, "Type mismatch: %s, %s.", type, simpleName);
                        break;
                    case 7:
                        Utils.checkArgument(value instanceof Value.ValueSummary, "Type mismatch: %s, %s.", type, simpleName);
                        break;
                }
            }
        }
    }

    public static Metric create(MetricDescriptor metricDescriptor, List<TimeSeries> list) {
        Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "timeSeriesList"), "timeSeries");
        return createInternal(metricDescriptor, Collections.unmodifiableList(new ArrayList(list)));
    }

    private static Metric createInternal(MetricDescriptor metricDescriptor, List<TimeSeries> list) {
        Utils.checkNotNull(metricDescriptor, "metricDescriptor");
        checkTypeMatch(metricDescriptor.getType(), list);
        return new AutoValue_Metric(metricDescriptor, list);
    }

    public static Metric createWithOneTimeSeries(MetricDescriptor metricDescriptor, TimeSeries timeSeries) {
        return createInternal(metricDescriptor, Collections.singletonList(Utils.checkNotNull(timeSeries, "timeSeries")));
    }

    public abstract MetricDescriptor getMetricDescriptor();

    public abstract List<TimeSeries> getTimeSeriesList();
}
