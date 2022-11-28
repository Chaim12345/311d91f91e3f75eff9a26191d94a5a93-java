package io.opencensus.metrics;

import io.opencensus.internal.Utils;
import io.opencensus.metrics.AutoValue_MetricOptions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public abstract class MetricOptions {

    /* loaded from: classes3.dex */
    public static abstract class Builder {
        abstract MetricOptions a();

        abstract Map b();

        public MetricOptions build() {
            setLabelKeys(Collections.unmodifiableList(new ArrayList(c())));
            setConstantLabels(Collections.unmodifiableMap(new LinkedHashMap(b())));
            MetricOptions a2 = a();
            Utils.checkListElementNotNull(a2.getLabelKeys(), "labelKeys elements");
            Utils.checkMapElementNotNull(a2.getConstantLabels(), "constantLabels elements");
            HashSet hashSet = new HashSet();
            for (LabelKey labelKey : a2.getLabelKeys()) {
                if (hashSet.contains(labelKey.getKey())) {
                    throw new IllegalArgumentException("Invalid LabelKey in labelKeys");
                }
                hashSet.add(labelKey.getKey());
            }
            for (Map.Entry<LabelKey, LabelValue> entry : a2.getConstantLabels().entrySet()) {
                if (hashSet.contains(entry.getKey().getKey())) {
                    throw new IllegalArgumentException("Invalid LabelKey in constantLabels");
                }
                hashSet.add(entry.getKey().getKey());
            }
            return a2;
        }

        abstract List c();

        public abstract Builder setConstantLabels(Map<LabelKey, LabelValue> map);

        public abstract Builder setDescription(String str);

        public abstract Builder setLabelKeys(List<LabelKey> list);

        public abstract Builder setUnit(String str);
    }

    public static Builder builder() {
        return new AutoValue_MetricOptions.Builder().setDescription("").setUnit("1").setLabelKeys(Collections.emptyList()).setConstantLabels(Collections.emptyMap());
    }

    public abstract Map<LabelKey, LabelValue> getConstantLabels();

    public abstract String getDescription();

    public abstract List<LabelKey> getLabelKeys();

    public abstract String getUnit();
}
