package com.google.maps.internal.ratelimiter;

import com.google.maps.internal.ratelimiter.RateLimiter;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class SmoothRateLimiter extends RateLimiter {

    /* renamed from: a  reason: collision with root package name */
    double f10333a;

    /* renamed from: b  reason: collision with root package name */
    double f10334b;

    /* renamed from: c  reason: collision with root package name */
    double f10335c;
    private long nextFreeTicketMicros;

    /* loaded from: classes2.dex */
    static final class SmoothBursty extends SmoothRateLimiter {

        /* renamed from: d  reason: collision with root package name */
        final double f10336d;

        /* JADX INFO: Access modifiers changed from: package-private */
        public SmoothBursty(RateLimiter.SleepingStopwatch sleepingStopwatch, double d2) {
            super(sleepingStopwatch);
            this.f10336d = d2;
        }

        @Override // com.google.maps.internal.ratelimiter.SmoothRateLimiter
        double j() {
            return this.f10335c;
        }

        @Override // com.google.maps.internal.ratelimiter.SmoothRateLimiter
        void k(double d2, double d3) {
            double d4 = this.f10334b;
            double d5 = this.f10336d * d2;
            this.f10334b = d5;
            if (d4 == Double.POSITIVE_INFINITY) {
                this.f10333a = d5;
            } else {
                this.f10333a = d4 != 0.0d ? (this.f10333a * d5) / d4 : 0.0d;
            }
        }

        @Override // com.google.maps.internal.ratelimiter.SmoothRateLimiter
        long m(double d2, double d3) {
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
            return this.f10335c + (d2 * this.slope);
        }

        @Override // com.google.maps.internal.ratelimiter.SmoothRateLimiter
        double j() {
            return this.warmupPeriodMicros / this.f10334b;
        }

        @Override // com.google.maps.internal.ratelimiter.SmoothRateLimiter
        void k(double d2, double d3) {
            double d4 = this.f10334b;
            double d5 = this.coldFactor * d3;
            long j2 = this.warmupPeriodMicros;
            double d6 = (j2 * 0.5d) / d3;
            this.thresholdPermits = d6;
            double d7 = ((j2 * 2.0d) / (d3 + d5)) + d6;
            this.f10334b = d7;
            this.slope = (d5 - d3) / (d7 - d6);
            if (d4 == Double.POSITIVE_INFINITY) {
                this.f10333a = 0.0d;
                return;
            }
            if (d4 != 0.0d) {
                d7 = (this.f10333a * d7) / d4;
            }
            this.f10333a = d7;
        }

        @Override // com.google.maps.internal.ratelimiter.SmoothRateLimiter
        long m(double d2, double d3) {
            long j2;
            double d4 = d2 - this.thresholdPermits;
            if (d4 > 0.0d) {
                double min = Math.min(d4, d3);
                j2 = (long) (((permitsToTime(d4) + permitsToTime(d4 - min)) * min) / 2.0d);
                d3 -= min;
            } else {
                j2 = 0;
            }
            return j2 + ((long) (this.f10335c * d3));
        }
    }

    private SmoothRateLimiter(RateLimiter.SleepingStopwatch sleepingStopwatch) {
        super(sleepingStopwatch);
        this.nextFreeTicketMicros = 0L;
    }

    @Override // com.google.maps.internal.ratelimiter.RateLimiter
    final double d() {
        return TimeUnit.SECONDS.toMicros(1L) / this.f10335c;
    }

    @Override // com.google.maps.internal.ratelimiter.RateLimiter
    final void e(double d2, long j2) {
        l(j2);
        double micros = TimeUnit.SECONDS.toMicros(1L) / d2;
        this.f10335c = micros;
        k(d2, micros);
    }

    @Override // com.google.maps.internal.ratelimiter.RateLimiter
    final long f(long j2) {
        return this.nextFreeTicketMicros;
    }

    @Override // com.google.maps.internal.ratelimiter.RateLimiter
    final long i(int i2, long j2) {
        l(j2);
        long j3 = this.nextFreeTicketMicros;
        double d2 = i2;
        double min = Math.min(d2, this.f10333a);
        this.nextFreeTicketMicros = LongMath.saturatedAdd(this.nextFreeTicketMicros, m(this.f10333a, min) + ((long) ((d2 - min) * this.f10335c)));
        this.f10333a -= min;
        return j3;
    }

    abstract double j();

    abstract void k(double d2, double d3);

    void l(long j2) {
        long j3 = this.nextFreeTicketMicros;
        if (j2 > j3) {
            this.f10333a = Math.min(this.f10334b, this.f10333a + ((j2 - j3) / j()));
            this.nextFreeTicketMicros = j2;
        }
    }

    abstract long m(double d2, double d3);
}
