package io.opencensus.trace.samplers;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class AutoValue_ProbabilitySampler extends ProbabilitySampler {
    private final long idUpperBound;
    private final double probability;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_ProbabilitySampler(double d2, long j2) {
        this.probability = d2;
        this.idUpperBound = j2;
    }

    @Override // io.opencensus.trace.samplers.ProbabilitySampler
    long b() {
        return this.idUpperBound;
    }

    @Override // io.opencensus.trace.samplers.ProbabilitySampler
    double c() {
        return this.probability;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ProbabilitySampler) {
            ProbabilitySampler probabilitySampler = (ProbabilitySampler) obj;
            return Double.doubleToLongBits(this.probability) == Double.doubleToLongBits(probabilitySampler.c()) && this.idUpperBound == probabilitySampler.b();
        }
        return false;
    }

    public int hashCode() {
        long j2 = this.idUpperBound;
        return (int) ((((int) (1000003 ^ ((Double.doubleToLongBits(this.probability) >>> 32) ^ Double.doubleToLongBits(this.probability)))) * 1000003) ^ (j2 ^ (j2 >>> 32)));
    }

    public String toString() {
        return "ProbabilitySampler{probability=" + this.probability + ", idUpperBound=" + this.idUpperBound + "}";
    }
}
