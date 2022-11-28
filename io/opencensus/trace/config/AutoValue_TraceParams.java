package io.opencensus.trace.config;

import io.opencensus.trace.Sampler;
import io.opencensus.trace.config.TraceParams;
import java.util.Objects;
/* loaded from: classes3.dex */
final class AutoValue_TraceParams extends TraceParams {
    private final int maxNumberOfAnnotations;
    private final int maxNumberOfAttributes;
    private final int maxNumberOfLinks;
    private final int maxNumberOfMessageEvents;
    private final Sampler sampler;

    /* loaded from: classes3.dex */
    static final class Builder extends TraceParams.Builder {
        private Integer maxNumberOfAnnotations;
        private Integer maxNumberOfAttributes;
        private Integer maxNumberOfLinks;
        private Integer maxNumberOfMessageEvents;
        private Sampler sampler;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder() {
        }

        private Builder(TraceParams traceParams) {
            this.sampler = traceParams.getSampler();
            this.maxNumberOfAttributes = Integer.valueOf(traceParams.getMaxNumberOfAttributes());
            this.maxNumberOfAnnotations = Integer.valueOf(traceParams.getMaxNumberOfAnnotations());
            this.maxNumberOfMessageEvents = Integer.valueOf(traceParams.getMaxNumberOfMessageEvents());
            this.maxNumberOfLinks = Integer.valueOf(traceParams.getMaxNumberOfLinks());
        }

        @Override // io.opencensus.trace.config.TraceParams.Builder
        TraceParams a() {
            String str = "";
            if (this.sampler == null) {
                str = " sampler";
            }
            if (this.maxNumberOfAttributes == null) {
                str = str + " maxNumberOfAttributes";
            }
            if (this.maxNumberOfAnnotations == null) {
                str = str + " maxNumberOfAnnotations";
            }
            if (this.maxNumberOfMessageEvents == null) {
                str = str + " maxNumberOfMessageEvents";
            }
            if (this.maxNumberOfLinks == null) {
                str = str + " maxNumberOfLinks";
            }
            if (str.isEmpty()) {
                return new AutoValue_TraceParams(this.sampler, this.maxNumberOfAttributes.intValue(), this.maxNumberOfAnnotations.intValue(), this.maxNumberOfMessageEvents.intValue(), this.maxNumberOfLinks.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // io.opencensus.trace.config.TraceParams.Builder
        public TraceParams.Builder setMaxNumberOfAnnotations(int i2) {
            this.maxNumberOfAnnotations = Integer.valueOf(i2);
            return this;
        }

        @Override // io.opencensus.trace.config.TraceParams.Builder
        public TraceParams.Builder setMaxNumberOfAttributes(int i2) {
            this.maxNumberOfAttributes = Integer.valueOf(i2);
            return this;
        }

        @Override // io.opencensus.trace.config.TraceParams.Builder
        public TraceParams.Builder setMaxNumberOfLinks(int i2) {
            this.maxNumberOfLinks = Integer.valueOf(i2);
            return this;
        }

        @Override // io.opencensus.trace.config.TraceParams.Builder
        public TraceParams.Builder setMaxNumberOfMessageEvents(int i2) {
            this.maxNumberOfMessageEvents = Integer.valueOf(i2);
            return this;
        }

        @Override // io.opencensus.trace.config.TraceParams.Builder
        public TraceParams.Builder setSampler(Sampler sampler) {
            Objects.requireNonNull(sampler, "Null sampler");
            this.sampler = sampler;
            return this;
        }
    }

    private AutoValue_TraceParams(Sampler sampler, int i2, int i3, int i4, int i5) {
        this.sampler = sampler;
        this.maxNumberOfAttributes = i2;
        this.maxNumberOfAnnotations = i3;
        this.maxNumberOfMessageEvents = i4;
        this.maxNumberOfLinks = i5;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TraceParams) {
            TraceParams traceParams = (TraceParams) obj;
            return this.sampler.equals(traceParams.getSampler()) && this.maxNumberOfAttributes == traceParams.getMaxNumberOfAttributes() && this.maxNumberOfAnnotations == traceParams.getMaxNumberOfAnnotations() && this.maxNumberOfMessageEvents == traceParams.getMaxNumberOfMessageEvents() && this.maxNumberOfLinks == traceParams.getMaxNumberOfLinks();
        }
        return false;
    }

    @Override // io.opencensus.trace.config.TraceParams
    public int getMaxNumberOfAnnotations() {
        return this.maxNumberOfAnnotations;
    }

    @Override // io.opencensus.trace.config.TraceParams
    public int getMaxNumberOfAttributes() {
        return this.maxNumberOfAttributes;
    }

    @Override // io.opencensus.trace.config.TraceParams
    public int getMaxNumberOfLinks() {
        return this.maxNumberOfLinks;
    }

    @Override // io.opencensus.trace.config.TraceParams
    public int getMaxNumberOfMessageEvents() {
        return this.maxNumberOfMessageEvents;
    }

    @Override // io.opencensus.trace.config.TraceParams
    public Sampler getSampler() {
        return this.sampler;
    }

    public int hashCode() {
        return ((((((((this.sampler.hashCode() ^ 1000003) * 1000003) ^ this.maxNumberOfAttributes) * 1000003) ^ this.maxNumberOfAnnotations) * 1000003) ^ this.maxNumberOfMessageEvents) * 1000003) ^ this.maxNumberOfLinks;
    }

    @Override // io.opencensus.trace.config.TraceParams
    public TraceParams.Builder toBuilder() {
        return new Builder(this);
    }

    public String toString() {
        return "TraceParams{sampler=" + this.sampler + ", maxNumberOfAttributes=" + this.maxNumberOfAttributes + ", maxNumberOfAnnotations=" + this.maxNumberOfAnnotations + ", maxNumberOfMessageEvents=" + this.maxNumberOfMessageEvents + ", maxNumberOfLinks=" + this.maxNumberOfLinks + "}";
    }
}
