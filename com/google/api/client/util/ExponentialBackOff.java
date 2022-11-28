package com.google.api.client.util;
/* loaded from: classes2.dex */
public class ExponentialBackOff implements BackOff {
    public static final int DEFAULT_INITIAL_INTERVAL_MILLIS = 500;
    public static final int DEFAULT_MAX_ELAPSED_TIME_MILLIS = 900000;
    public static final int DEFAULT_MAX_INTERVAL_MILLIS = 60000;
    public static final double DEFAULT_MULTIPLIER = 1.5d;
    public static final double DEFAULT_RANDOMIZATION_FACTOR = 0.5d;

    /* renamed from: a  reason: collision with root package name */
    long f8083a;
    private int currentIntervalMillis;
    private final int initialIntervalMillis;
    private final int maxElapsedTimeMillis;
    private final int maxIntervalMillis;
    private final double multiplier;
    private final NanoClock nanoClock;
    private final double randomizationFactor;

    /* loaded from: classes2.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        int f8084a = 500;

        /* renamed from: b  reason: collision with root package name */
        double f8085b = 0.5d;

        /* renamed from: c  reason: collision with root package name */
        double f8086c = 1.5d;

        /* renamed from: d  reason: collision with root package name */
        int f8087d = 60000;

        /* renamed from: e  reason: collision with root package name */
        int f8088e = 900000;

        /* renamed from: f  reason: collision with root package name */
        NanoClock f8089f = NanoClock.SYSTEM;

        public ExponentialBackOff build() {
            return new ExponentialBackOff(this);
        }

        public final int getInitialIntervalMillis() {
            return this.f8084a;
        }

        public final int getMaxElapsedTimeMillis() {
            return this.f8088e;
        }

        public final int getMaxIntervalMillis() {
            return this.f8087d;
        }

        public final double getMultiplier() {
            return this.f8086c;
        }

        public final NanoClock getNanoClock() {
            return this.f8089f;
        }

        public final double getRandomizationFactor() {
            return this.f8085b;
        }

        public Builder setInitialIntervalMillis(int i2) {
            this.f8084a = i2;
            return this;
        }

        public Builder setMaxElapsedTimeMillis(int i2) {
            this.f8088e = i2;
            return this;
        }

        public Builder setMaxIntervalMillis(int i2) {
            this.f8087d = i2;
            return this;
        }

        public Builder setMultiplier(double d2) {
            this.f8086c = d2;
            return this;
        }

        public Builder setNanoClock(NanoClock nanoClock) {
            this.f8089f = (NanoClock) Preconditions.checkNotNull(nanoClock);
            return this;
        }

        public Builder setRandomizationFactor(double d2) {
            this.f8085b = d2;
            return this;
        }
    }

    public ExponentialBackOff() {
        this(new Builder());
    }

    protected ExponentialBackOff(Builder builder) {
        int i2 = builder.f8084a;
        this.initialIntervalMillis = i2;
        double d2 = builder.f8085b;
        this.randomizationFactor = d2;
        double d3 = builder.f8086c;
        this.multiplier = d3;
        int i3 = builder.f8087d;
        this.maxIntervalMillis = i3;
        int i4 = builder.f8088e;
        this.maxElapsedTimeMillis = i4;
        this.nanoClock = builder.f8089f;
        Preconditions.checkArgument(i2 > 0);
        Preconditions.checkArgument(0.0d <= d2 && d2 < 1.0d);
        Preconditions.checkArgument(d3 >= 1.0d);
        Preconditions.checkArgument(i3 >= i2);
        Preconditions.checkArgument(i4 > 0);
        reset();
    }

    static int a(double d2, double d3, int i2) {
        double d4 = i2;
        double d5 = d2 * d4;
        double d6 = d4 - d5;
        return (int) (d6 + (d3 * (((d4 + d5) - d6) + 1.0d)));
    }

    private void incrementCurrentInterval() {
        int i2 = this.currentIntervalMillis;
        int i3 = this.maxIntervalMillis;
        double d2 = this.multiplier;
        if (i2 >= i3 / d2) {
            this.currentIntervalMillis = i3;
        } else {
            this.currentIntervalMillis = (int) (i2 * d2);
        }
    }

    public final int getCurrentIntervalMillis() {
        return this.currentIntervalMillis;
    }

    public final long getElapsedTimeMillis() {
        return (this.nanoClock.nanoTime() - this.f8083a) / 1000000;
    }

    public final int getInitialIntervalMillis() {
        return this.initialIntervalMillis;
    }

    public final int getMaxElapsedTimeMillis() {
        return this.maxElapsedTimeMillis;
    }

    public final int getMaxIntervalMillis() {
        return this.maxIntervalMillis;
    }

    public final double getMultiplier() {
        return this.multiplier;
    }

    public final double getRandomizationFactor() {
        return this.randomizationFactor;
    }

    @Override // com.google.api.client.util.BackOff
    public long nextBackOffMillis() {
        if (getElapsedTimeMillis() > this.maxElapsedTimeMillis) {
            return -1L;
        }
        int a2 = a(this.randomizationFactor, Math.random(), this.currentIntervalMillis);
        incrementCurrentInterval();
        return a2;
    }

    @Override // com.google.api.client.util.BackOff
    public final void reset() {
        this.currentIntervalMillis = this.initialIntervalMillis;
        this.f8083a = this.nanoClock.nanoTime();
    }
}
