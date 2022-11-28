package io.opencensus.metrics.data;

import javax.annotation.concurrent.Immutable;
/* loaded from: classes3.dex */
public abstract class AttachmentValue {

    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class AttachmentValueString extends AttachmentValue {
        public static AttachmentValueString create(String str) {
            return new AutoValue_AttachmentValue_AttachmentValueString(str);
        }
    }

    public abstract String getValue();
}
