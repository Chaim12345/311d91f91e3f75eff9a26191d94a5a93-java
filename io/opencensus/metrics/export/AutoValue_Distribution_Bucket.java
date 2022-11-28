package io.opencensus.metrics.export;

import io.opencensus.metrics.data.Exemplar;
import io.opencensus.metrics.export.Distribution;
import javax.annotation.Nullable;
/* loaded from: classes3.dex */
final class AutoValue_Distribution_Bucket extends Distribution.Bucket {
    private final long count;
    private final Exemplar exemplar;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Distribution_Bucket(long j2, @Nullable Exemplar exemplar) {
        this.count = j2;
        this.exemplar = exemplar;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Distribution.Bucket) {
            Distribution.Bucket bucket = (Distribution.Bucket) obj;
            if (this.count == bucket.getCount()) {
                Exemplar exemplar = this.exemplar;
                Exemplar exemplar2 = bucket.getExemplar();
                if (exemplar == null) {
                    if (exemplar2 == null) {
                        return true;
                    }
                } else if (exemplar.equals(exemplar2)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // io.opencensus.metrics.export.Distribution.Bucket
    public long getCount() {
        return this.count;
    }

    @Override // io.opencensus.metrics.export.Distribution.Bucket
    @Nullable
    public Exemplar getExemplar() {
        return this.exemplar;
    }

    public int hashCode() {
        long j2 = this.count;
        int i2 = ((int) (1000003 ^ (j2 ^ (j2 >>> 32)))) * 1000003;
        Exemplar exemplar = this.exemplar;
        return (exemplar == null ? 0 : exemplar.hashCode()) ^ i2;
    }

    public String toString() {
        return "Bucket{count=" + this.count + ", exemplar=" + this.exemplar + "}";
    }
}
