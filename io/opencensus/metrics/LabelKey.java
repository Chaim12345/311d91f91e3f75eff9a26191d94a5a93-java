package io.opencensus.metrics;

import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public abstract class LabelKey {
    public static LabelKey create(String str, String str2) {
        return new AutoValue_LabelKey(str, str2);
    }

    public abstract String getDescription();

    public abstract String getKey();
}
