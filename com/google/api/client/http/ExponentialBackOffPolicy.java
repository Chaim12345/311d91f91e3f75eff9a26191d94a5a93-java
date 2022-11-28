package com.google.api.client.http;

import com.google.api.client.util.Beta;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.client.util.NanoClock;
@Beta
@Deprecated
/* loaded from: classes2.dex */
public class ExponentialBackOffPolicy implements BackOffPolicy {
    public static final int DEFAULT_INITIAL_INTERVAL_MILLIS = 500;
    public static final int DEFAULT_MAX_ELAPSED_TIME_MILLIS = 900000;
    public static final int DEFAULT_MAX_INTERVAL_MILLIS = 60000;
    public static final double DEFAULT_MULTIPLIER = 1.5d;
    public static final double DEFAULT_RANDOMIZATION_FACTOR = 0.5d;
    private final ExponentialBackOff exponentialBackOff;

    @Beta
    @Deprecated
    /* loaded from: classes2.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        final ExponentialBackOff.Builder f8038a = new ExponentialBackOff.Builder();

        protected Builder() {
        }

        public ExponentialBackOffPolicy build() {
            return new ExponentialBackOffPolicy(this);
        }

        public final int getInitialIntervalMillis() {
            return this.f8038a.getInitialIntervalMillis();
        }

        public final int getMaxElapsedTimeMillis() {
            return this.f8038a.getMaxElapsedTimeMillis();
        }

        public final int getMaxIntervalMillis() {
            return this.f8038a.getMaxIntervalMillis();
        }

        public final double getMultiplier() {
            return this.f8038a.getMultiplier();
        }

        public final NanoClock getNanoClock() {
            return this.f8038a.getNanoClock();
        }

        public final double getRandomizationFactor() {
            return this.f8038a.getRandomizationFactor();
        }

        public Builder setInitialIntervalMillis(int i2) {
            this.f8038a.setInitialIntervalMillis(i2);
            return this;
        }

        public Builder setMaxElapsedTimeMillis(int i2) {
            this.f8038a.setMaxElapsedTimeMillis(i2);
            return this;
        }

        public Builder setMaxIntervalMillis(int i2) {
            this.f8038a.setMaxIntervalMillis(i2);
            return this;
        }

        public Builder setMultiplier(double d2) {
            this.f8038a.setMultiplier(d2);
            return this;
        }

        public Builder setNanoClock(NanoClock nanoClock) {
            this.f8038a.setNanoClock(nanoClock);
            return this;
        }

        public Builder setRandomizationFactor(double d2) {
            this.f8038a.setRandomizationFactor(d2);
            return this;
        }
    }

    public ExponentialBackOffPolicy() {
        this(new Builder());
    }

    protected ExponentialBackOffPolicy(Builder builder) {
        this.exponentialBackOff = builder.f8038a.build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public final int getCurrentIntervalMillis() {
        return this.exponentialBackOff.getCurrentIntervalMillis();
    }

    public final long getElapsedTimeMillis() {
        return this.exponentialBackOff.getElapsedTimeMillis();
    }

    public final int getInitialIntervalMillis() {
        return this.exponentialBackOff.getInitialIntervalMillis();
    }

    public final int getMaxElapsedTimeMillis() {
        return this.exponentialBackOff.getMaxElapsedTimeMillis();
    }

    public final int getMaxIntervalMillis() {
        return this.exponentialBackOff.getMaxIntervalMillis();
    }

    public final double getMultiplier() {
        return this.exponentialBackOff.getMultiplier();
    }

    @Override // com.google.api.client.http.BackOffPolicy
    public long getNextBackOffMillis() {
        return this.exponentialBackOff.nextBackOffMillis();
    }

    public final double getRandomizationFactor() {
        return this.exponentialBackOff.getRandomizationFactor();
    }

    @Override // com.google.api.client.http.BackOffPolicy
    public boolean isBackOffRequired(int i2) {
        return i2 == 500 || i2 == 503;
    }

    @Override // com.google.api.client.http.BackOffPolicy
    public final void reset() {
        this.exponentialBackOff.reset();
    }
}
