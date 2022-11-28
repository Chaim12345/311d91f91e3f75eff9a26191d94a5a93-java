package com.google.maps.internal.ratelimiter;
/* loaded from: classes2.dex */
public abstract class Ticker {
    private static final Ticker SYSTEM_TICKER = new Ticker() { // from class: com.google.maps.internal.ratelimiter.Ticker.1
        @Override // com.google.maps.internal.ratelimiter.Ticker
        public long read() {
            return Platform.b();
        }
    };

    protected Ticker() {
    }

    public static Ticker systemTicker() {
        return SYSTEM_TICKER;
    }

    public abstract long read();
}
