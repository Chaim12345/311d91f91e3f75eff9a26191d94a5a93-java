package io.opencensus.tags;

import java.util.Iterator;
/* loaded from: classes3.dex */
public final class InternalUtils {
    private InternalUtils() {
    }

    public static Iterator<Tag> getTags(TagContext tagContext) {
        return tagContext.a();
    }
}
