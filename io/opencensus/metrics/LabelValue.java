package io.opencensus.metrics;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public abstract class LabelValue {
    public static LabelValue create(@Nullable String str) {
        return new AutoValue_LabelValue(str);
    }

    @Nullable
    public abstract String getValue();
}
