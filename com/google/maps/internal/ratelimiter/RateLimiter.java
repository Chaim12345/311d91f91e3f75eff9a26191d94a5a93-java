package com.google.maps.internal.ratelimiter;

import com.google.maps.internal.ratelimiter.SmoothRateLimiter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public abstract class RateLimiter {
    private volatile Object mutexDoNotUseDirectly;
    private final SleepingStopwatch stopwatch;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static abstract class SleepingStopwatch {
        protected SleepingStopwatch() {
        }

        public static SleepingStopwatch createFromSystemTimer() {
            return new SleepingStopwatch() { // from class: com.google.maps.internal.ratelimiter.RateLimiter.SleepingStopwatch.1

                /* renamed from: a  reason: collision with root package name */
                final Stopwatch f10332a = Stopwatch.createStarted();

                @Override // com.google.maps.internal.ratelimiter.RateLimiter.SleepingStopwatch
                protected long a() {
                    return this.f10332a.elapsed(TimeUnit.MICROSECONDS);
                }

                @Override // com.google.maps.internal.ratelimiter.RateLimiter.SleepingStopwatch
                protected void b(long j2) {
                    if (j2 > 0) {
                        RateLimiter.sleepUninterruptibly(j2, TimeUnit.MICROSECONDS);
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

    static RateLimiter b(double d2, long j2, TimeUnit timeUnit, double d3, SleepingStopwatch sleepingStopwatch) {
        SmoothRateLimiter.SmoothWarmingUp smoothWarmingUp = new SmoothRateLimiter.SmoothWarmingUp(sleepingStopwatch, j2, timeUnit, d3);
        smoothWarmingUp.setRate(d2);
        return smoothWarmingUp;
    }

    static RateLimiter c(double d2, SleepingStopwatch sleepingStopwatch) {
        SmoothRateLimiter.SmoothBursty smoothBursty = new SmoothRateLimiter.SmoothBursty(sleepingStopwatch, 1.0d);
        smoothBursty.setRate(d2);
        return smoothBursty;
    }

    private boolean canAcquire(long j2, long j3) {
        return f(j2) - j3 <= j2;
    }

    private static void checkPermits(int i2) {
        Preconditions.checkArgument(i2 > 0, "Requested permits (%s) must be positive", Integer.valueOf(i2));
    }

    public static RateLimiter create(double d2) {
        return c(d2, SleepingStopwatch.createFromSystemTimer());
    }

    public static RateLimiter create(double d2, long j2, TimeUnit timeUnit) {
        Preconditions.checkArgument(j2 >= 0, "warmupPeriod must not be negative: %s", Long.valueOf(j2));
        return b(d2, j2, timeUnit, 3.0d, SleepingStopwatch.createFromSystemTimer());
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

    /* JADX INFO: Access modifiers changed from: private */
    public static void sleepUninterruptibly(long j2, TimeUnit timeUnit) {
        boolean z = false;
        try {
            long nanos = timeUnit.toNanos(j2);
            long nanoTime = System.nanoTime() + nanos;
            while (true) {
                try {
                    TimeUnit.NANOSECONDS.sleep(nanos);
                    break;
                } catch (InterruptedException unused) {
                    z = true;
                    nanos = nanoTime - System.nanoTime();
                }
            }
        } finally {
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public double acquire() {
        return acquire(1);
    }

    public double acquire(int i2) {
        long g2 = g(i2);
        this.stopwatch.b(g2);
        return (g2 * 1.0d) / TimeUnit.SECONDS.toMicros(1L);
    }

    abstract double d();

    abstract void e(double d2, long j2);

    abstract long f(long j2);

    final long g(int i2) {
        long h2;
        checkPermits(i2);
        synchronized (mutex()) {
            h2 = h(i2, this.stopwatch.a());
        }
        return h2;
    }

    public final double getRate() {
        double d2;
        synchronized (mutex()) {
            d2 = d();
        }
        return d2;
    }

    final long h(int i2, long j2) {
        return Math.max(i(i2, j2) - j2, 0L);
    }

    abstract long i(int i2, long j2);

    public final void setRate(double d2) {
        Preconditions.checkArgument(d2 > 0.0d && !Double.isNaN(d2), "rate must be positive", new Object[0]);
        synchronized (mutex()) {
            e(d2, this.stopwatch.a());
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
                this.stopwatch.b(h(i2, a2));
                return true;
            }
            return false;
        }
    }

    public boolean tryAcquire(long j2, TimeUnit timeUnit) {
        return tryAcquire(1, j2, timeUnit);
    }
}
