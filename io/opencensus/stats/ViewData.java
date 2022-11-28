package io.opencensus.stats;

import io.opencensus.common.Duration;
import io.opencensus.common.Function;
import io.opencensus.common.Functions;
import io.opencensus.common.Timestamp;
import io.opencensus.stats.Aggregation;
import io.opencensus.stats.AggregationData;
import io.opencensus.stats.Measure;
import io.opencensus.stats.View;
import io.opencensus.tags.TagValue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public abstract class ViewData {

    @Deprecated
    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class AggregationWindowData {

        @Deprecated
        @Immutable
        /* loaded from: classes3.dex */
        public static abstract class CumulativeData extends AggregationWindowData {
            /* JADX INFO: Access modifiers changed from: package-private */
            public CumulativeData() {
                super();
            }

            public static CumulativeData create(Timestamp timestamp, Timestamp timestamp2) {
                if (timestamp.compareTo(timestamp2) <= 0) {
                    return new AutoValue_ViewData_AggregationWindowData_CumulativeData(timestamp, timestamp2);
                }
                throw new IllegalArgumentException("Start time is later than end time.");
            }

            public abstract Timestamp getEnd();

            public abstract Timestamp getStart();

            @Override // io.opencensus.stats.ViewData.AggregationWindowData
            public final <T> T match(Function<? super CumulativeData, T> function, Function<? super IntervalData, T> function2, Function<? super AggregationWindowData, T> function3) {
                return function.apply(this);
            }
        }

        @Deprecated
        @Immutable
        /* loaded from: classes3.dex */
        public static abstract class IntervalData extends AggregationWindowData {
            /* JADX INFO: Access modifiers changed from: package-private */
            public IntervalData() {
                super();
            }

            public static IntervalData create(Timestamp timestamp) {
                return new AutoValue_ViewData_AggregationWindowData_IntervalData(timestamp);
            }

            public abstract Timestamp getEnd();

            @Override // io.opencensus.stats.ViewData.AggregationWindowData
            public final <T> T match(Function<? super CumulativeData, T> function, Function<? super IntervalData, T> function2, Function<? super AggregationWindowData, T> function3) {
                return function2.apply(this);
            }
        }

        private AggregationWindowData() {
        }

        public abstract <T> T match(Function<? super CumulativeData, T> function, Function<? super IntervalData, T> function2, Function<? super AggregationWindowData, T> function3);
    }

    private static void checkAggregation(final Aggregation aggregation, final AggregationData aggregationData, final Measure measure) {
        aggregation.match(new Function<Aggregation.Sum, Void>() { // from class: io.opencensus.stats.ViewData.5
            @Override // io.opencensus.common.Function
            public Void apply(Aggregation.Sum sum) {
                Measure.this.match(new Function<Measure.MeasureDouble, Void>() { // from class: io.opencensus.stats.ViewData.5.1
                    @Override // io.opencensus.common.Function
                    public Void apply(Measure.MeasureDouble measureDouble) {
                        AnonymousClass5 anonymousClass5 = AnonymousClass5.this;
                        AggregationData aggregationData2 = aggregationData;
                        ViewData.throwIfAggregationMismatch(aggregationData2 instanceof AggregationData.SumDataDouble, aggregation, aggregationData2);
                        return null;
                    }
                }, new Function<Measure.MeasureLong, Void>() { // from class: io.opencensus.stats.ViewData.5.2
                    @Override // io.opencensus.common.Function
                    public Void apply(Measure.MeasureLong measureLong) {
                        AnonymousClass5 anonymousClass5 = AnonymousClass5.this;
                        AggregationData aggregationData2 = aggregationData;
                        ViewData.throwIfAggregationMismatch(aggregationData2 instanceof AggregationData.SumDataLong, aggregation, aggregationData2);
                        return null;
                    }
                }, Functions.throwAssertionError());
                return null;
            }
        }, new Function<Aggregation.Count, Void>() { // from class: io.opencensus.stats.ViewData.6
            @Override // io.opencensus.common.Function
            public Void apply(Aggregation.Count count) {
                AggregationData aggregationData2 = AggregationData.this;
                ViewData.throwIfAggregationMismatch(aggregationData2 instanceof AggregationData.CountData, aggregation, aggregationData2);
                return null;
            }
        }, new Function<Aggregation.Distribution, Void>() { // from class: io.opencensus.stats.ViewData.7
            @Override // io.opencensus.common.Function
            public Void apply(Aggregation.Distribution distribution) {
                AggregationData aggregationData2 = AggregationData.this;
                ViewData.throwIfAggregationMismatch(aggregationData2 instanceof AggregationData.DistributionData, aggregation, aggregationData2);
                return null;
            }
        }, new Function<Aggregation.LastValue, Void>() { // from class: io.opencensus.stats.ViewData.8
            @Override // io.opencensus.common.Function
            public Void apply(Aggregation.LastValue lastValue) {
                Measure.this.match(new Function<Measure.MeasureDouble, Void>() { // from class: io.opencensus.stats.ViewData.8.1
                    @Override // io.opencensus.common.Function
                    public Void apply(Measure.MeasureDouble measureDouble) {
                        AnonymousClass8 anonymousClass8 = AnonymousClass8.this;
                        AggregationData aggregationData2 = aggregationData;
                        ViewData.throwIfAggregationMismatch(aggregationData2 instanceof AggregationData.LastValueDataDouble, aggregation, aggregationData2);
                        return null;
                    }
                }, new Function<Measure.MeasureLong, Void>() { // from class: io.opencensus.stats.ViewData.8.2
                    @Override // io.opencensus.common.Function
                    public Void apply(Measure.MeasureLong measureLong) {
                        AnonymousClass8 anonymousClass8 = AnonymousClass8.this;
                        AggregationData aggregationData2 = aggregationData;
                        ViewData.throwIfAggregationMismatch(aggregationData2 instanceof AggregationData.LastValueDataLong, aggregation, aggregationData2);
                        return null;
                    }
                }, Functions.throwAssertionError());
                return null;
            }
        }, new Function<Aggregation, Void>() { // from class: io.opencensus.stats.ViewData.9
            @Override // io.opencensus.common.Function
            public Void apply(Aggregation aggregation2) {
                if (aggregation2 instanceof Aggregation.Mean) {
                    AggregationData aggregationData2 = AggregationData.this;
                    ViewData.throwIfAggregationMismatch(aggregationData2 instanceof AggregationData.MeanData, aggregation, aggregationData2);
                    return null;
                }
                throw new AssertionError();
            }
        });
    }

    private static void checkWindow(View.AggregationWindow aggregationWindow, final AggregationWindowData aggregationWindowData) {
        aggregationWindow.match(new Function<View.AggregationWindow.Cumulative, Void>() { // from class: io.opencensus.stats.ViewData.3
            @Override // io.opencensus.common.Function
            public Void apply(View.AggregationWindow.Cumulative cumulative) {
                AggregationWindowData aggregationWindowData2 = AggregationWindowData.this;
                ViewData.throwIfWindowMismatch(aggregationWindowData2 instanceof AggregationWindowData.CumulativeData, cumulative, aggregationWindowData2);
                return null;
            }
        }, new Function<View.AggregationWindow.Interval, Void>() { // from class: io.opencensus.stats.ViewData.4
            @Override // io.opencensus.common.Function
            public Void apply(View.AggregationWindow.Interval interval) {
                AggregationWindowData aggregationWindowData2 = AggregationWindowData.this;
                ViewData.throwIfWindowMismatch(aggregationWindowData2 instanceof AggregationWindowData.IntervalData, interval, aggregationWindowData2);
                return null;
            }
        }, Functions.throwAssertionError());
    }

    public static ViewData create(View view, Map<? extends List<TagValue>, ? extends AggregationData> map, Timestamp timestamp, Timestamp timestamp2) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<? extends List<TagValue>, ? extends AggregationData> entry : map.entrySet()) {
            checkAggregation(view.getAggregation(), entry.getValue(), view.getMeasure());
            hashMap.put(Collections.unmodifiableList(new ArrayList(entry.getKey())), entry.getValue());
        }
        return createInternal(view, Collections.unmodifiableMap(hashMap), AggregationWindowData.CumulativeData.create(timestamp, timestamp2), timestamp, timestamp2);
    }

    @Deprecated
    public static ViewData create(final View view, Map<? extends List<TagValue>, ? extends AggregationData> map, AggregationWindowData aggregationWindowData) {
        checkWindow(view.getWindow(), aggregationWindowData);
        final HashMap hashMap = new HashMap();
        for (Map.Entry<? extends List<TagValue>, ? extends AggregationData> entry : map.entrySet()) {
            checkAggregation(view.getAggregation(), entry.getValue(), view.getMeasure());
            hashMap.put(Collections.unmodifiableList(new ArrayList(entry.getKey())), entry.getValue());
        }
        return (ViewData) aggregationWindowData.match(new Function<AggregationWindowData.CumulativeData, ViewData>() { // from class: io.opencensus.stats.ViewData.1
            @Override // io.opencensus.common.Function
            public ViewData apply(AggregationWindowData.CumulativeData cumulativeData) {
                return ViewData.createInternal(View.this, Collections.unmodifiableMap(hashMap), cumulativeData, cumulativeData.getStart(), cumulativeData.getEnd());
            }
        }, new Function<AggregationWindowData.IntervalData, ViewData>() { // from class: io.opencensus.stats.ViewData.2
            @Override // io.opencensus.common.Function
            public ViewData apply(AggregationWindowData.IntervalData intervalData) {
                Duration duration = ((View.AggregationWindow.Interval) View.this.getWindow()).getDuration();
                return ViewData.createInternal(View.this, Collections.unmodifiableMap(hashMap), intervalData, intervalData.getEnd().addDuration(Duration.create(-duration.getSeconds(), -duration.getNanos())), intervalData.getEnd());
            }
        }, Functions.throwAssertionError());
    }

    private static String createErrorMessageForAggregation(Aggregation aggregation, AggregationData aggregationData) {
        return "Aggregation and AggregationData types mismatch. Aggregation: " + aggregation.getClass().getSimpleName() + " AggregationData: " + aggregationData.getClass().getSimpleName();
    }

    private static String createErrorMessageForWindow(View.AggregationWindow aggregationWindow, AggregationWindowData aggregationWindowData) {
        return "AggregationWindow and AggregationWindowData types mismatch. AggregationWindow: " + aggregationWindow.getClass().getSimpleName() + " AggregationWindowData: " + aggregationWindowData.getClass().getSimpleName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ViewData createInternal(View view, Map<List<TagValue>, AggregationData> map, AggregationWindowData aggregationWindowData, Timestamp timestamp, Timestamp timestamp2) {
        return new AutoValue_ViewData(view, map, aggregationWindowData, timestamp, timestamp2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void throwIfAggregationMismatch(boolean z, Aggregation aggregation, AggregationData aggregationData) {
        if (!z) {
            throw new IllegalArgumentException(createErrorMessageForAggregation(aggregation, aggregationData));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void throwIfWindowMismatch(boolean z, View.AggregationWindow aggregationWindow, AggregationWindowData aggregationWindowData) {
        if (!z) {
            throw new IllegalArgumentException(createErrorMessageForWindow(aggregationWindow, aggregationWindowData));
        }
    }

    public abstract Map<List<TagValue>, AggregationData> getAggregationMap();

    public abstract Timestamp getEnd();

    public abstract Timestamp getStart();

    public abstract View getView();

    @Deprecated
    public abstract AggregationWindowData getWindowData();
}
