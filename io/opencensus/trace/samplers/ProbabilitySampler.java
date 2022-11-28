package io.opencensus.trace.samplers;

import io.opencensus.internal.Utils;
import io.opencensus.trace.Sampler;
import io.opencensus.trace.Span;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.SpanId;
import io.opencensus.trace.TraceId;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import kotlin.jvm.internal.LongCompanionObject;
/* JADX INFO: Access modifiers changed from: package-private */
@Immutable
/* loaded from: classes3.dex */
public abstract class ProbabilitySampler extends Sampler {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProbabilitySampler a(double d2) {
        int i2 = (d2 > 0.0d ? 1 : (d2 == 0.0d ? 0 : -1));
        Utils.checkArgument(i2 >= 0 && d2 <= 1.0d, "probability must be in range [0.0, 1.0]");
        return new AutoValue_ProbabilitySampler(d2, i2 == 0 ? Long.MIN_VALUE : d2 == 1.0d ? LongCompanionObject.MAX_VALUE : (long) (9.223372036854776E18d * d2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract long b();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract double c();

    @Override // io.opencensus.trace.Sampler
    public final String getDescription() {
        return String.format("ProbabilitySampler{%.6f}", Double.valueOf(c()));
    }

    @Override // io.opencensus.trace.Sampler
    public final boolean shouldSample(@Nullable SpanContext spanContext, @Nullable Boolean bool, TraceId traceId, SpanId spanId, String str, @Nullable List<Span> list) {
        if (spanContext == null || !spanContext.getTraceOptions().isSampled()) {
            if (list != null) {
                for (Span span : list) {
                    if (span.getContext().getTraceOptions().isSampled()) {
                        return true;
                    }
                }
            }
            return Math.abs(traceId.getLowerLong()) < b();
        }
        return true;
    }
}
