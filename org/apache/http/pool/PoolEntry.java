package org.apache.http.pool;

import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.util.Args;
@Contract(threading = ThreadingBehavior.SAFE_CONDITIONAL)
/* loaded from: classes3.dex */
public abstract class PoolEntry<T, C> {
    private final C conn;
    private final long created;
    private long expiry;
    private final String id;
    private final T route;
    private volatile Object state;
    private long updated;
    private final long validityDeadline;

    public PoolEntry(String str, T t2, C c2) {
        this(str, t2, c2, 0L, TimeUnit.MILLISECONDS);
    }

    public PoolEntry(String str, T t2, C c2, long j2, TimeUnit timeUnit) {
        Args.notNull(t2, "Route");
        Args.notNull(c2, "Connection");
        Args.notNull(timeUnit, "Time unit");
        this.id = str;
        this.route = t2;
        this.conn = c2;
        long currentTimeMillis = System.currentTimeMillis();
        this.created = currentTimeMillis;
        this.updated = currentTimeMillis;
        int i2 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        long j3 = LongCompanionObject.MAX_VALUE;
        if (i2 > 0) {
            long millis = currentTimeMillis + timeUnit.toMillis(j2);
            if (millis > 0) {
                j3 = millis;
            }
        }
        this.validityDeadline = j3;
        this.expiry = this.validityDeadline;
    }

    public abstract void close();

    public C getConnection() {
        return this.conn;
    }

    public long getCreated() {
        return this.created;
    }

    public synchronized long getExpiry() {
        return this.expiry;
    }

    public String getId() {
        return this.id;
    }

    public T getRoute() {
        return this.route;
    }

    public Object getState() {
        return this.state;
    }

    public synchronized long getUpdated() {
        return this.updated;
    }

    @Deprecated
    public long getValidUnit() {
        return this.validityDeadline;
    }

    public long getValidityDeadline() {
        return this.validityDeadline;
    }

    public abstract boolean isClosed();

    public synchronized boolean isExpired(long j2) {
        return j2 >= this.expiry;
    }

    public void setState(Object obj) {
        this.state = obj;
    }

    public String toString() {
        return "[id:" + this.id + "][route:" + this.route + "][state:" + this.state + "]";
    }

    public synchronized void updateExpiry(long j2, TimeUnit timeUnit) {
        Args.notNull(timeUnit, "Time unit");
        long currentTimeMillis = System.currentTimeMillis();
        this.updated = currentTimeMillis;
        this.expiry = Math.min(j2 > 0 ? currentTimeMillis + timeUnit.toMillis(j2) : LongCompanionObject.MAX_VALUE, this.validityDeadline);
    }
}
