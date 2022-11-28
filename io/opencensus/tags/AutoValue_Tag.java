package io.opencensus.tags;

import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class AutoValue_Tag extends Tag {
    private final TagKey key;
    private final TagMetadata tagMetadata;
    private final TagValue value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Tag(TagKey tagKey, TagValue tagValue, TagMetadata tagMetadata) {
        Objects.requireNonNull(tagKey, "Null key");
        this.key = tagKey;
        Objects.requireNonNull(tagValue, "Null value");
        this.value = tagValue;
        Objects.requireNonNull(tagMetadata, "Null tagMetadata");
        this.tagMetadata = tagMetadata;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Tag) {
            Tag tag = (Tag) obj;
            return this.key.equals(tag.getKey()) && this.value.equals(tag.getValue()) && this.tagMetadata.equals(tag.getTagMetadata());
        }
        return false;
    }

    @Override // io.opencensus.tags.Tag
    public TagKey getKey() {
        return this.key;
    }

    @Override // io.opencensus.tags.Tag
    public TagMetadata getTagMetadata() {
        return this.tagMetadata;
    }

    @Override // io.opencensus.tags.Tag
    public TagValue getValue() {
        return this.value;
    }

    public int hashCode() {
        return ((((this.key.hashCode() ^ 1000003) * 1000003) ^ this.value.hashCode()) * 1000003) ^ this.tagMetadata.hashCode();
    }

    public String toString() {
        return "Tag{key=" + this.key + ", value=" + this.value + ", tagMetadata=" + this.tagMetadata + "}";
    }
}
