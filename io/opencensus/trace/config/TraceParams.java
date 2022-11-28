package io.opencensus.trace.config;

import io.opencensus.internal.Utils;
import io.opencensus.trace.Sampler;
import io.opencensus.trace.config.AutoValue_TraceParams;
import io.opencensus.trace.samplers.Samplers;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public abstract class TraceParams {
    public static final TraceParams DEFAULT;
    private static final double DEFAULT_PROBABILITY = 1.0E-4d;
    private static final Sampler DEFAULT_SAMPLER;
    private static final int DEFAULT_SPAN_MAX_NUM_ANNOTATIONS = 32;
    private static final int DEFAULT_SPAN_MAX_NUM_ATTRIBUTES = 32;
    private static final int DEFAULT_SPAN_MAX_NUM_LINKS = 32;
    private static final int DEFAULT_SPAN_MAX_NUM_MESSAGE_EVENTS = 128;

    /* loaded from: classes3.dex */
    public static abstract class Builder {
        abstract TraceParams a();

        public TraceParams build() {
            TraceParams a2 = a();
            Utils.checkArgument(a2.getMaxNumberOfAttributes() > 0, "maxNumberOfAttributes");
            Utils.checkArgument(a2.getMaxNumberOfAnnotations() > 0, "maxNumberOfAnnotations");
            Utils.checkArgument(a2.getMaxNumberOfMessageEvents() > 0, "maxNumberOfMessageEvents");
            Utils.checkArgument(a2.getMaxNumberOfLinks() > 0, "maxNumberOfLinks");
            return a2;
        }

        public abstract Builder setMaxNumberOfAnnotations(int i2);

        public abstract Builder setMaxNumberOfAttributes(int i2);

        public abstract Builder setMaxNumberOfLinks(int i2);

        public abstract Builder setMaxNumberOfMessageEvents(int i2);

        @Deprecated
        public Builder setMaxNumberOfNetworkEvents(int i2) {
            return setMaxNumberOfMessageEvents(i2);
        }

        public abstract Builder setSampler(Sampler sampler);
    }

    static {
        Sampler probabilitySampler = Samplers.probabilitySampler(DEFAULT_PROBABILITY);
        DEFAULT_SAMPLER = probabilitySampler;
        DEFAULT = builder().setSampler(probabilitySampler).setMaxNumberOfAttributes(32).setMaxNumberOfAnnotations(32).setMaxNumberOfMessageEvents(128).setMaxNumberOfLinks(32).build();
    }

    private static Builder builder() {
        return new AutoValue_TraceParams.Builder();
    }

    public abstract int getMaxNumberOfAnnotations();

    public abstract int getMaxNumberOfAttributes();

    public abstract int getMaxNumberOfLinks();

    public abstract int getMaxNumberOfMessageEvents();

    @Deprecated
    public int getMaxNumberOfNetworkEvents() {
        return getMaxNumberOfMessageEvents();
    }

    public abstract Sampler getSampler();

    public abstract Builder toBuilder();
}
