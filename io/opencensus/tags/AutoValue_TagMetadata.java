package io.opencensus.tags;

import io.opencensus.tags.TagMetadata;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class AutoValue_TagMetadata extends TagMetadata {
    private final TagMetadata.TagTtl tagTtl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_TagMetadata(TagMetadata.TagTtl tagTtl) {
        Objects.requireNonNull(tagTtl, "Null tagTtl");
        this.tagTtl = tagTtl;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TagMetadata) {
            return this.tagTtl.equals(((TagMetadata) obj).getTagTtl());
        }
        return false;
    }

    @Override // io.opencensus.tags.TagMetadata
    public TagMetadata.TagTtl getTagTtl() {
        return this.tagTtl;
    }

    public int hashCode() {
        return this.tagTtl.hashCode() ^ 1000003;
    }

    public String toString() {
        return "TagMetadata{tagTtl=" + this.tagTtl + "}";
    }
}
