package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.math.LongMath;
import com.google.common.util.concurrent.RateLimiter;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class SmoothRateLimiter extends RateLimiter {

    /* renamed from: a  reason: collision with root package name */
    double f9579a;

    /* renamed from: b  reason: collision with root package name */
    double f9580b;

    /* renamed from: c  reason: collision with root package name */
    double f9581c;
    private long nextFreeTicketMicros;

    /* loaded from: classes2.dex */
    static final class SmoothBursty extends SmoothRateLimiter {

        /* renamed from: d  reason: collision with root package name */
        final double f9582d;

        /* JADX INFO: Access modifiers changed from: package-private */
        public SmoothBursty(RateLimiter.SleepingStopwatch sleepingStopwatch, double d2) {
            super(sleepingStopwatch);
            this.f9582d = d2;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        double i() {
            return this.f9581c;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        void j(double d2, double d3) {
            double d4 = this.f9580b;
            double d5 = this.f9582d * d2;
            this.f9580b = d5;
            if (d4 == Double.POSITIVE_INFINITY) {
                this.f9579a = d5;
            } else {
                this.f9579a = d4 != 0.0d ? (this.f9579a * d5) / d4 : 0.0d;
            }
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        long l(double d2, double d3) {
            return 0L;
        }
    }

    /* loaded from: classes2.dex */
    static final class SmoothWarmingUp extends SmoothRateLimiter {
        private double coldFactor;
        private double slope;
        private double thresholdPermits;
        private final long warmupPeriodMicros;

        /* JADX INFO: Access modifiers changed from: package-private */
        public SmoothWarmingUp(RateLimiter.SleepingStopwatch sleepingStopwatch, long j2, TimeUnit timeUnit, double d2) {
            super(sleepingStopwatch);
            this.warmupPeriodMicros = timeUnit.toMicros(j2);
            this.coldFactor = d2;
        }

        private double permitsToTime(double d2) {
            return this.f9581c + (d2 * this.slope);
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        double i() {
            return this.warmupPeriodMicros / this.f9580b;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        void j(double d2, double d3) {
            double d4 = this.f9580b;
            double d5 = this.coldFactor * d3;
            long j2 = this.warmupPeriodMicros;
            double d6 = (j2 * 0.5d) / d3;
            this.thresholdPermits = d6;
            double d7 = ((j2 * 2.0d) / (d3 + d5)) + d6;
            this.f9580b = d7;
            this.slope = (d5 - d3) / (d7 - d6);
            if (d4 == Double.POSITIVE_INFINITY) {
                this.f9579a = 0.0d;
                return;
            }
            if (d4 != 0.0d) {
                d7 = (this.f9579a * d7) / d4;
            }
            this.f9579a = d7;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        long l(double d2, double d3) {
            long j2;
            double d4 = d2 - this.thresholdPermits;
            if (d4 > 0.0d) {
                double min = Math.min(d4, d3);
                j2 = (long) (((permitsToTime(d4) + permitsToTime(d4 - min)) * min) / 2.0d);
                d3 -= min;
            } else {
                j2 = 0;
            }
            return j2 + ((long) (this.f9581c * d3));
        }
    }

    private SmoothRateLimiter(RateLimiter.SleepingStopwatch sleepingStopwatch) {
        super(sleepingStopwatch);
        this.nextFreeTicketMicros = 0L;
    }

    @Override // com.google.common.util.concurrent.RateLimiter
    final double c() {
        return TimeUnit.SECONDS.toMicros(1L) / this.f9581c;
    }

    @Override // com.google.common.util.concurrent.RateLimiter
    final void d(double d2, long j2) {
        k(j2);
        double micros = TimeUnit.SECONDS.toMicros(1L) / d2;
        this.f9581c = micros;
        j(d2, micros);
    }

    @Override // com.google.common.util.concurrent.RateLimiter
    final long e(long j2) {
        return this.nextFreeTicketMicros;
    }

    @Override // com.google.common.util.concurrent.RateLimiter
    final long h(int i2, long j2) {
        k(j2);
        long j3 = this.nextFreeTicketMicros;
        double d2 = i2;
        double min = Math.min(d2, this.f9579a);
        this.nextFreeTicketMicros = LongMath.saturatedAdd(this.nextFreeTicketMicros, l(this.f9579a, min) + ((long) ((d2 - min) * this.f9581c)));
        this.f9579a -= min;
        return j3;
    }

    abstract double i();

    abstract void j(double d2, double d3);

    void k(long j2) {
        long j3 = this.nextFreeTicketMicros;
        if (j2 > j3) {
            this.f9579a = Math.min(this.f9580b, this.f9579a + ((j2 - j3) / i()));
            this.nextFreeTicketMicros = j2;
        }
    }

    abstract long l(double d2, double d3);
}
