package io.opencensus.trace.export;

import io.opencensus.trace.Link;
import io.opencensus.trace.export.SpanData;
import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
final class AutoValue_SpanData_Links extends SpanData.Links {
    private final int droppedLinksCount;
    private final List<Link> links;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SpanData_Links(List list, int i2) {
        Objects.requireNonNull(list, "Null links");
        this.links = list;
        this.droppedLinksCount = i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SpanData.Links) {
            SpanData.Links links = (SpanData.Links) obj;
            return this.links.equals(links.getLinks()) && this.droppedLinksCount == links.getDroppedLinksCount();
        }
        return false;
    }

    @Override // io.opencensus.trace.export.SpanData.Links
    public int getDroppedLinksCount() {
        return this.droppedLinksCount;
    }

    @Override // io.opencensus.trace.export.SpanData.Links
    public List<Link> getLinks() {
        return this.links;
    }

    public int hashCode() {
        return ((this.links.hashCode() ^ 1000003) * 1000003) ^ this.droppedLinksCount;
    }

    public String toString() {
        return "Links{links=" + this.links + ", droppedLinksCount=" + this.droppedLinksCount + "}";
    }
}
