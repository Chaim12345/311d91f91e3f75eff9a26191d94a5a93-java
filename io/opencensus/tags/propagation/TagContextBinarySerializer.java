package io.opencensus.tags.propagation;

import io.opencensus.tags.TagContext;
/* loaded from: classes3.dex */
public abstract class TagContextBinarySerializer {
    public abstract TagContext fromByteArray(byte[] bArr);

    public abstract byte[] toByteArray(TagContext tagContext);
}
