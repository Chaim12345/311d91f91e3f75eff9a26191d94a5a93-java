package io.grpc;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.language.Soundex;
/* loaded from: classes3.dex */
public final class Deadline implements Comparable<Deadline> {
    private static final long MAX_OFFSET;
    private static final long MIN_OFFSET;
    private static final long NANOS_PER_SECOND;
    private static final SystemTicker SYSTEM_TICKER = new SystemTicker();
    private final long deadlineNanos;
    private volatile boolean expired;
    private final Ticker ticker;

    /* loaded from: classes3.dex */
    private static class SystemTicker extends Ticker {
        private SystemTicker() {
        }

        @Override // io.grpc.Deadline.Ticker
        public long read() {
            return System.nanoTime();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static abstract class Ticker {
        Ticker() {
        }

        public abstract long read();
    }

    static {
        long nanos = TimeUnit.DAYS.toNanos(36500L);
        MAX_OFFSET = nanos;
        MIN_OFFSET = -nanos;
        NANOS_PER_SECOND = TimeUnit.SECONDS.toNanos(1L);
    }

    private Deadline(Ticker ticker, long j2, long j3, boolean z) {
        this.ticker = ticker;
        long min = Math.min(MAX_OFFSET, Math.max(MIN_OFFSET, j3));
        this.deadlineNanos = j2 + min;
        this.expired = z && min <= 0;
    }

    private Deadline(Ticker ticker, long j2, boolean z) {
        this(ticker, ticker.read(), j2, z);
    }

    static Deadline a(long j2, TimeUnit timeUnit, Ticker ticker) {
        checkNotNull(timeUnit, "units");
        return new Deadline(ticker, timeUnit.toNanos(j2), true);
    }

    public static Deadline after(long j2, TimeUnit timeUnit) {
        return a(j2, timeUnit, SYSTEM_TICKER);
    }

    private static <T> T checkNotNull(T t2, Object obj) {
        if (t2 != null) {
            return t2;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    @Override // java.lang.Comparable
    public int compareTo(Deadline deadline) {
        int i2 = ((this.deadlineNanos - deadline.deadlineNanos) > 0L ? 1 : ((this.deadlineNanos - deadline.deadlineNanos) == 0L ? 0 : -1));
        if (i2 < 0) {
            return -1;
        }
        return i2 > 0 ? 1 : 0;
    }

    public boolean isBefore(Deadline deadline) {
        return this.deadlineNanos - deadline.deadlineNanos < 0;
    }

    public boolean isExpired() {
        if (!this.expired) {
            if (this.deadlineNanos - this.ticker.read() > 0) {
                return false;
            }
            this.expired = true;
        }
        return true;
    }

    public Deadline minimum(Deadline deadline) {
        return isBefore(deadline) ? this : deadline;
    }

    public Deadline offset(long j2, TimeUnit timeUnit) {
        return j2 == 0 ? this : new Deadline(this.ticker, this.deadlineNanos, timeUnit.toNanos(j2), isExpired());
    }

    public ScheduledFuture<?> runOnExpiration(Runnable runnable, ScheduledExecutorService scheduledExecutorService) {
        checkNotNull(runnable, "task");
        checkNotNull(scheduledExecutorService, "scheduler");
        return scheduledExecutorService.schedule(runnable, this.deadlineNanos - this.ticker.read(), TimeUnit.NANOSECONDS);
    }

    public long timeRemaining(TimeUnit timeUnit) {
        long read = this.ticker.read();
        if (!this.expired && this.deadlineNanos - read <= 0) {
            this.expired = true;
        }
        return timeUnit.convert(this.deadlineNanos - read, TimeUnit.NANOSECONDS);
    }

    public String toString() {
        long timeRemaining = timeRemaining(TimeUnit.NANOSECONDS);
        long abs = Math.abs(timeRemaining);
        long j2 = NANOS_PER_SECOND;
        long j3 = abs / j2;
        long abs2 = Math.abs(timeRemaining) % j2;
        StringBuilder sb = new StringBuilder();
        if (timeRemaining < 0) {
            sb.append(Soundex.SILENT_MARKER);
        }
        sb.append(j3);
        if (abs2 > 0) {
            sb.append(String.format(".%09d", Long.valueOf(abs2)));
        }
        sb.append("s from now");
        return sb.toString();
    }
}
