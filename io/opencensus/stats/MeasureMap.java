package io.opencensus.stats;

import io.opencensus.internal.Utils;
import io.opencensus.metrics.data.AttachmentValue;
import io.opencensus.stats.Measure;
import io.opencensus.tags.TagContext;
import javax.annotation.concurrent.NotThreadSafe;
@NotThreadSafe
/* loaded from: classes3.dex */
public abstract class MeasureMap {
    public abstract MeasureMap put(Measure.MeasureDouble measureDouble, double d2);

    public abstract MeasureMap put(Measure.MeasureLong measureLong, long j2);

    public MeasureMap putAttachment(String str, AttachmentValue attachmentValue) {
        Utils.checkNotNull(str, "key");
        Utils.checkNotNull(attachmentValue, "value");
        return this;
    }

    @Deprecated
    public MeasureMap putAttachment(String str, String str2) {
        return putAttachment(str, AttachmentValue.AttachmentValueString.create(str2));
    }

    public abstract void record();

    public abstract void record(TagContext tagContext);
}
