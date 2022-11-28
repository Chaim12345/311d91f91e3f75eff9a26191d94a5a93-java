package io.opencensus.trace;

import io.opencensus.trace.EndSpanOptions;
import javax.annotation.Nullable;
/* loaded from: classes3.dex */
final class AutoValue_EndSpanOptions extends EndSpanOptions {
    private final boolean sampleToLocalSpanStore;
    private final Status status;

    /* loaded from: classes3.dex */
    static final class Builder extends EndSpanOptions.Builder {
        private Boolean sampleToLocalSpanStore;
        private Status status;

        @Override // io.opencensus.trace.EndSpanOptions.Builder
        public EndSpanOptions build() {
            String str = "";
            if (this.sampleToLocalSpanStore == null) {
                str = " sampleToLocalSpanStore";
            }
            if (str.isEmpty()) {
                return new AutoValue_EndSpanOptions(this.sampleToLocalSpanStore.booleanValue(), this.status);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // io.opencensus.trace.EndSpanOptions.Builder
        public EndSpanOptions.Builder setSampleToLocalSpanStore(boolean z) {
            this.sampleToLocalSpanStore = Boolean.valueOf(z);
            return this;
        }

        @Override // io.opencensus.trace.EndSpanOptions.Builder
        public EndSpanOptions.Builder setStatus(@Nullable Status status) {
            this.status = status;
            return this;
        }
    }

    private AutoValue_EndSpanOptions(boolean z, @Nullable Status status) {
        this.sampleToLocalSpanStore = z;
        this.status = status;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof EndSpanOptions) {
            EndSpanOptions endSpanOptions = (EndSpanOptions) obj;
            if (this.sampleToLocalSpanStore == endSpanOptions.getSampleToLocalSpanStore()) {
                Status status = this.status;
                Status status2 = endSpanOptions.getStatus();
                if (status == null) {
                    if (status2 == null) {
                        return true;
                    }
                } else if (status.equals(status2)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // io.opencensus.trace.EndSpanOptions
    public boolean getSampleToLocalSpanStore() {
        return this.sampleToLocalSpanStore;
    }

    @Override // io.opencensus.trace.EndSpanOptions
    @Nullable
    public Status getStatus() {
        return this.status;
    }

    public int hashCode() {
        int i2 = ((this.sampleToLocalSpanStore ? 1231 : 1237) ^ 1000003) * 1000003;
        Status status = this.status;
        return i2 ^ (status == null ? 0 : status.hashCode());
    }

    public String toString() {
        return "EndSpanOptions{sampleToLocalSpanStore=" + this.sampleToLocalSpanStore + ", status=" + this.status + "}";
    }
}
