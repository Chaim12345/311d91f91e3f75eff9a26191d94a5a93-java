package io.opencensus.trace.samplers;

import io.opencensus.trace.Sampler;
/* loaded from: classes3.dex */
public final class Samplers {
    private static final Sampler ALWAYS_SAMPLE = new AlwaysSampleSampler();
    private static final Sampler NEVER_SAMPLE = new NeverSampleSampler();

    private Samplers() {
    }

    public static Sampler alwaysSample() {
        return ALWAYS_SAMPLE;
    }

    public static Sampler neverSample() {
        return NEVER_SAMPLE;
    }

    public static Sampler probabilitySampler(double d2) {
        return ProbabilitySampler.a(d2);
    }
}
