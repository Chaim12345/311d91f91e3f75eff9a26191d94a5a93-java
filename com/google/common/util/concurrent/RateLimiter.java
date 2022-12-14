package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.SmoothRateLimiter;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class RateLimiter {
    @NullableDecl
    private volatile Object mutexDoNotUseDirectly;
    private final SleepingStopwatch stopwatch;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static abstract class SleepingStopwatch {
        protected SleepingStopwatch() {
        }

        public static SleepingStopwatch createFromSystemTimer() {
            return new SleepingStopwatch() { // from class: com.google.common.util.concurrent.RateLimiter.SleepingStopwatch.1

                /* renamed from: a  reason: collision with root package name */
                final Stopwatch f9553a = Stopwatch.createStarted();

                @Override // com.google.common.util.concurrent.RateLimiter.SleepingStopwatch
                protected long a() {
                    return this.f9553a.elapsed(TimeUnit.MICROSECONDS);
                }

                @Override // com.google.common.util.concurrent.RateLimiter.SleepingStopwatch
                protected void b(long j2) {
                    if (j2 > 0) {
                        Uninterruptibles.sleepUninterruptibly(j2, TimeUnit.MICROSECONDS);
                    }
                }
            };
        }

        protected abstract long a();

        protected abstract void b(long j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RateLimiter(SleepingStopwatch sleepingStopwatch) {
        this.stopwatch = (SleepingStopwatch) Preconditions.checkNotNull(sleepingStopwatch);
    }

    @VisibleForTesting
    static RateLimiter a(double d2, long j2, TimeUnit timeUnit, double d3, SleepingStopwatch sleepingStopwatch) {
        SmoothRateLimiter.SmoothWarmingUp smoothWarmingUp = new SmoothRateLimiter.SmoothWarmingUp(sleepingStopwatch, j2, timeUnit, d3);
        smoothWarmingUp.setRate(d2);
        return smoothWarmingUp;
    }

    @VisibleForTesting
    static RateLimiter b(double d2, SleepingStopwatch sleepingStopwatch) {
        SmoothRateLimiter.SmoothBursty smoothBursty = new SmoothRateLimiter.SmoothBursty(sleepingStopwatch, 1.0d);
        smoothBursty.setRate(d2);
        return smoothBursty;
    }

    private boolean canAcquire(long j2, long j3) {
        return e(j2) - j3 <= j2;
    }

    private static void checkPermits(int i2) {
        Preconditions.checkArgument(i2 > 0, "Requested permits (%s) must be positive", i2);
    }

    public static RateLimiter create(double d2) {
        return b(d2, SleepingStopwatch.createFromSystemTimer());
    }

    public static RateLimiter create(double d2, long j2, TimeUnit timeUnit) {
        Preconditions.checkArgument(j2 >= 0, "warmupPeriod must not be negative: %s", j2);
        return a(d2, j2, timeUnit, 3.0d, SleepingStopwatch.createFromSystemTimer());
    }

    private Object mutex() {
        Object obj = this.mutexDoNotUseDirectly;
        if (obj == null) {
            synchronized (this) {
                obj = this.mutexDoNotUseDirectly;
                if (obj == null) {
                    obj = new Object();
                    this.mutexDoNotUseDirectly = obj;
                }
            }
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public double acquire() {
        return acquire(1);
    }

    @CanIgnoreReturnValue
    public double acquire(int i2) {
        long f2 = f(i2);
        this.stopwatch.b(f2);
        return (f2 * 1.0d) / TimeUnit.SECONDS.toMicros(1L);
    }

    abstract double c();

    abstract void d(double d2, long j2);

    abstract long e(long j2);

    final long f(int i2) {
        long g2;
        checkPermits(i2);
        synchronized (mutex()) {
            g2 = g(i2, this.stopwatch.a());
        }
        return g2;
    }

    final long g(int i2, long j2) {
        return Math.max(h(i2, j2) - j2, 0L);
    }

    public final double getRate() {
        double c2;
        synchronized (mutex()) {
            c2 = c();
        }
        return c2;
    }

    abstract long h(int i2, long j2);

    public final void setRate(double d2) {
        Preconditions.checkArgument(d2 > 0.0d && !Double.isNaN(d2), "rate must be positive");
        synchronized (mutex()) {
            d(d2, this.stopwatch.a());
        }
    }

    public String toString() {
        return String.format(Locale.ROOT, "RateLimiter[stableRate=%3.1fqps]", Double.valueOf(getRate()));
    }

    public boolean tryAcquire() {
        return tryAcquire(1, 0L, TimeUnit.MICROSECONDS);
    }

    public boolean tryAcquire(int i2) {
        return tryAcquire(i2, 0L, TimeUnit.MICROSECONDS);
    }

    public boolean tryAcquire(int i2, long j2, TimeUnit timeUnit) {
        long max = Math.max(timeUnit.toMicros(j2), 0L);
        checkPermits(i2);
        synchronized (mutex()) {
            long a2 = this.stopwatch.a();
            if (canAcquire(a2, max)) {
                this.stopwatch.b(g(i2, a2));
                return true;
            }
            return false;
        }
    }

    public boolean tryAcquire(long j2, TimeUnit timeUnit) {
        return tryAcquire(1, j2, timeUnit);
    }
}
