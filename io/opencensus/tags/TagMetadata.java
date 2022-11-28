package io.opencensus.tags;

import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public abstract class TagMetadata {

    /* loaded from: classes3.dex */
    public enum TagTtl {
        NO_PROPAGATION(0),
        UNLIMITED_PROPAGATION(-1);
        
        private final int hops;

        TagTtl(int i2) {
            this.hops = i2;
        }
    }

    public static TagMetadata create(TagTtl tagTtl) {
        return new AutoValue_TagMetadata(tagTtl);
    }

    public abstract TagTtl getTagTtl();
}
