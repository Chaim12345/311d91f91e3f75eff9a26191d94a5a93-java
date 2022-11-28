package org.apache.http.pool;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.pool.PoolEntry;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;
@Contract(threading = ThreadingBehavior.SAFE_CONDITIONAL)
/* loaded from: classes3.dex */
public abstract class AbstractConnPool<T, C, E extends PoolEntry<T, C>> implements ConnPool<T, E>, ConnPoolControl<T> {
    private final LinkedList<E> available;
    private final Condition condition;
    private final ConnFactory<T, C> connFactory;
    private volatile int defaultMaxPerRoute;
    private volatile boolean isShutDown;
    private final Set<E> leased;
    private final Lock lock;
    private final Map<T, Integer> maxPerRoute;
    private volatile int maxTotal;
    private final LinkedList<Future<E>> pending;
    private final Map<T, RouteSpecificPool<T, C, E>> routeToPool;
    private volatile int validateAfterInactivity;

    public AbstractConnPool(ConnFactory<T, C> connFactory, int i2, int i3) {
        this.connFactory = (ConnFactory) Args.notNull(connFactory, "Connection factory");
        this.defaultMaxPerRoute = Args.positive(i2, "Max per route value");
        this.maxTotal = Args.positive(i3, "Max total value");
        ReentrantLock reentrantLock = new ReentrantLock();
        this.lock = reentrantLock;
        this.condition = reentrantLock.newCondition();
        this.routeToPool = new HashMap();
        this.leased = new HashSet();
        this.available = new LinkedList<>();
        this.pending = new LinkedList<>();
        this.maxPerRoute = new HashMap();
    }

    private int getMax(T t2) {
        Integer num = this.maxPerRoute.get(t2);
        return num != null ? num.intValue() : this.defaultMaxPerRoute;
    }

    private RouteSpecificPool<T, C, E> getPool(final T t2) {
        RouteSpecificPool<T, C, E> routeSpecificPool = this.routeToPool.get(t2);
        if (routeSpecificPool == null) {
            RouteSpecificPool<T, C, E> routeSpecificPool2 = (RouteSpecificPool<T, C, E>) new RouteSpecificPool<T, C, E>(t2) { // from class: org.apache.http.pool.AbstractConnPool.1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // org.apache.http.pool.RouteSpecificPool
                protected E createEntry(C c2) {
                    return (E) AbstractConnPool.this.createEntry(t2, c2);
                }
            };
            this.routeToPool.put(t2, routeSpecificPool2);
            return routeSpecificPool2;
        }
        return routeSpecificPool;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public E getPoolEntryBlocking(T t2, Object obj, long j2, TimeUnit timeUnit, Future<E> future) {
        E e2;
        Date date = j2 > 0 ? new Date(System.currentTimeMillis() + timeUnit.toMillis(j2)) : null;
        this.lock.lock();
        try {
            RouteSpecificPool pool = getPool(t2);
            while (true) {
                boolean z = true;
                Asserts.check(!this.isShutDown, "Connection pool shut down");
                if (future.isCancelled()) {
                    throw new ExecutionException(operationAborted());
                }
                while (true) {
                    e2 = (E) pool.getFree(obj);
                    if (e2 == null) {
                        break;
                    }
                    if (e2.isExpired(System.currentTimeMillis())) {
                        e2.close();
                    }
                    if (!e2.isClosed()) {
                        break;
                    }
                    this.available.remove(e2);
                    pool.free(e2, false);
                }
                if (e2 != null) {
                    this.available.remove(e2);
                    this.leased.add(e2);
                    onReuse(e2);
                    return e2;
                }
                int max = getMax(t2);
                int max2 = Math.max(0, (pool.getAllocatedCount() + 1) - max);
                if (max2 > 0) {
                    for (int i2 = 0; i2 < max2; i2++) {
                        PoolEntry lastUsed = pool.getLastUsed();
                        if (lastUsed == null) {
                            break;
                        }
                        lastUsed.close();
                        this.available.remove(lastUsed);
                        pool.remove(lastUsed);
                    }
                }
                if (pool.getAllocatedCount() < max) {
                    int max3 = Math.max(this.maxTotal - this.leased.size(), 0);
                    if (max3 > 0) {
                        if (this.available.size() > max3 - 1 && !this.available.isEmpty()) {
                            E removeLast = this.available.removeLast();
                            removeLast.close();
                            getPool(removeLast.getRoute()).remove(removeLast);
                        }
                        E e3 = (E) pool.add(this.connFactory.create(t2));
                        this.leased.add(e3);
                        return e3;
                    }
                }
                pool.queue(future);
                this.pending.add(future);
                if (date != null) {
                    z = this.condition.awaitUntil(date);
                } else {
                    this.condition.await();
                }
                if (future.isCancelled()) {
                    throw new ExecutionException(operationAborted());
                }
                pool.unqueue(future);
                this.pending.remove(future);
                if (!z && date != null && date.getTime() <= System.currentTimeMillis()) {
                    throw new TimeoutException("Timeout waiting for connection");
                }
            }
        } finally {
            this.lock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Exception operationAborted() {
        return new CancellationException("Operation aborted");
    }

    private void purgePoolMap() {
        Iterator<Map.Entry<T, RouteSpecificPool<T, C, E>>> it = this.routeToPool.entrySet().iterator();
        while (it.hasNext()) {
            RouteSpecificPool<T, C, E> value = it.next().getValue();
            if (value.getPendingCount() + value.getAllocatedCount() == 0) {
                it.remove();
            }
        }
    }

    public void closeExpired() {
        final long currentTimeMillis = System.currentTimeMillis();
        enumAvailable(new PoolEntryCallback<T, C>() { // from class: org.apache.http.pool.AbstractConnPool.4
            @Override // org.apache.http.pool.PoolEntryCallback
            public void process(PoolEntry<T, C> poolEntry) {
                if (poolEntry.isExpired(currentTimeMillis)) {
                    poolEntry.close();
                }
            }
        });
    }

    public void closeIdle(long j2, TimeUnit timeUnit) {
        Args.notNull(timeUnit, "Time unit");
        long millis = timeUnit.toMillis(j2);
        if (millis < 0) {
            millis = 0;
        }
        final long currentTimeMillis = System.currentTimeMillis() - millis;
        enumAvailable(new PoolEntryCallback<T, C>() { // from class: org.apache.http.pool.AbstractConnPool.3
            @Override // org.apache.http.pool.PoolEntryCallback
            public void process(PoolEntry<T, C> poolEntry) {
                if (poolEntry.getUpdated() <= currentTimeMillis) {
                    poolEntry.close();
                }
            }
        });
    }

    protected abstract E createEntry(T t2, C c2);

    /* JADX INFO: Access modifiers changed from: protected */
    public void enumAvailable(PoolEntryCallback<T, C> poolEntryCallback) {
        this.lock.lock();
        try {
            Iterator<E> it = this.available.iterator();
            while (it.hasNext()) {
                E next = it.next();
                poolEntryCallback.process(next);
                if (next.isClosed()) {
                    getPool(next.getRoute()).remove(next);
                    it.remove();
                }
            }
            purgePoolMap();
        } finally {
            this.lock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void enumLeased(PoolEntryCallback<T, C> poolEntryCallback) {
        this.lock.lock();
        try {
            for (E e2 : this.leased) {
                poolEntryCallback.process(e2);
            }
        } finally {
            this.lock.unlock();
        }
    }

    @Override // org.apache.http.pool.ConnPoolControl
    public int getDefaultMaxPerRoute() {
        this.lock.lock();
        try {
            return this.defaultMaxPerRoute;
        } finally {
            this.lock.unlock();
        }
    }

    @Override // org.apache.http.pool.ConnPoolControl
    public int getMaxPerRoute(T t2) {
        Args.notNull(t2, "Route");
        this.lock.lock();
        try {
            return getMax(t2);
        } finally {
            this.lock.unlock();
        }
    }

    @Override // org.apache.http.pool.ConnPoolControl
    public int getMaxTotal() {
        this.lock.lock();
        try {
            return this.maxTotal;
        } finally {
            this.lock.unlock();
        }
    }

    public Set<T> getRoutes() {
        this.lock.lock();
        try {
            return new HashSet(this.routeToPool.keySet());
        } finally {
            this.lock.unlock();
        }
    }

    @Override // org.apache.http.pool.ConnPoolControl
    public PoolStats getStats(T t2) {
        Args.notNull(t2, "Route");
        this.lock.lock();
        try {
            RouteSpecificPool<T, C, E> pool = getPool(t2);
            return new PoolStats(pool.getLeasedCount(), pool.getPendingCount(), pool.getAvailableCount(), getMax(t2));
        } finally {
            this.lock.unlock();
        }
    }

    @Override // org.apache.http.pool.ConnPoolControl
    public PoolStats getTotalStats() {
        this.lock.lock();
        try {
            return new PoolStats(this.leased.size(), this.pending.size(), this.available.size(), this.maxTotal);
        } finally {
            this.lock.unlock();
        }
    }

    public int getValidateAfterInactivity() {
        return this.validateAfterInactivity;
    }

    public boolean isShutdown() {
        return this.isShutDown;
    }

    public Future<E> lease(T t2, Object obj) {
        return lease(t2, obj, null);
    }

    @Override // org.apache.http.pool.ConnPool
    public Future<E> lease(final T t2, final Object obj, final FutureCallback<E> futureCallback) {
        Args.notNull(t2, "Route");
        Asserts.check(!this.isShutDown, "Connection pool shut down");
        return (Future<E>) new Future<E>() { // from class: org.apache.http.pool.AbstractConnPool.2
            private final AtomicBoolean cancelled = new AtomicBoolean(false);
            private final AtomicBoolean done = new AtomicBoolean(false);
            private final AtomicReference<E> entryRef = new AtomicReference<>(null);

            @Override // java.util.concurrent.Future
            public boolean cancel(boolean z) {
                if (this.done.compareAndSet(false, true)) {
                    this.cancelled.set(true);
                    AbstractConnPool.this.lock.lock();
                    try {
                        AbstractConnPool.this.condition.signalAll();
                        AbstractConnPool.this.lock.unlock();
                        FutureCallback futureCallback2 = futureCallback;
                        if (futureCallback2 != null) {
                            futureCallback2.cancelled();
                        }
                        return true;
                    } catch (Throwable th) {
                        AbstractConnPool.this.lock.unlock();
                        throw th;
                    }
                }
                return false;
            }

            @Override // java.util.concurrent.Future
            public E get() {
                try {
                    return (E) get(0L, TimeUnit.MILLISECONDS);
                } catch (TimeoutException e2) {
                    throw new ExecutionException(e2);
                }
            }

            @Override // java.util.concurrent.Future
            public E get(long j2, TimeUnit timeUnit) {
                FutureCallback futureCallback2;
                E e2;
                while (true) {
                    synchronized (this) {
                        try {
                            E e3 = this.entryRef.get();
                            if (e3 != null) {
                                return e3;
                            }
                            if (this.done.get()) {
                                throw new ExecutionException(AbstractConnPool.operationAborted());
                            }
                            e2 = (E) AbstractConnPool.this.getPoolEntryBlocking(t2, obj, j2, timeUnit, this);
                            if (AbstractConnPool.this.validateAfterInactivity <= 0 || e2.getUpdated() + AbstractConnPool.this.validateAfterInactivity > System.currentTimeMillis() || AbstractConnPool.this.validate(e2)) {
                                break;
                            }
                            e2.close();
                            AbstractConnPool.this.release((AbstractConnPool) e2, false);
                        } catch (IOException e4) {
                            if (this.done.compareAndSet(false, true) && (futureCallback2 = futureCallback) != null) {
                                futureCallback2.failed(e4);
                            }
                            throw new ExecutionException(e4);
                        }
                    }
                }
                if (!this.done.compareAndSet(false, true)) {
                    AbstractConnPool.this.release((AbstractConnPool) e2, true);
                    throw new ExecutionException(AbstractConnPool.operationAborted());
                }
                this.entryRef.set(e2);
                this.done.set(true);
                AbstractConnPool.this.onLease(e2);
                FutureCallback futureCallback3 = futureCallback;
                if (futureCallback3 != null) {
                    futureCallback3.completed(e2);
                }
                return e2;
            }

            @Override // java.util.concurrent.Future
            public boolean isCancelled() {
                return this.cancelled.get();
            }

            @Override // java.util.concurrent.Future
            public boolean isDone() {
                return this.done.get();
            }
        };
    }

    protected void onLease(E e2) {
    }

    protected void onRelease(E e2) {
    }

    protected void onReuse(E e2) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.http.pool.ConnPool
    public /* bridge */ /* synthetic */ void release(Object obj, boolean z) {
        release((AbstractConnPool<T, C, E>) ((PoolEntry) obj), z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void release(E e2, boolean z) {
        this.lock.lock();
        try {
            if (this.leased.remove(e2)) {
                RouteSpecificPool pool = getPool(e2.getRoute());
                pool.free(e2, z);
                if (!z || this.isShutDown) {
                    e2.close();
                } else {
                    this.available.addFirst(e2);
                }
                onRelease(e2);
                Future<E> nextPending = pool.nextPending();
                if (nextPending != null) {
                    this.pending.remove(nextPending);
                } else {
                    nextPending = this.pending.poll();
                }
                if (nextPending != null) {
                    this.condition.signalAll();
                }
            }
        } finally {
            this.lock.unlock();
        }
    }

    @Override // org.apache.http.pool.ConnPoolControl
    public void setDefaultMaxPerRoute(int i2) {
        Args.positive(i2, "Max per route value");
        this.lock.lock();
        try {
            this.defaultMaxPerRoute = i2;
        } finally {
            this.lock.unlock();
        }
    }

    @Override // org.apache.http.pool.ConnPoolControl
    public void setMaxPerRoute(T t2, int i2) {
        Args.notNull(t2, "Route");
        this.lock.lock();
        try {
            if (i2 > -1) {
                this.maxPerRoute.put(t2, Integer.valueOf(i2));
            } else {
                this.maxPerRoute.remove(t2);
            }
        } finally {
            this.lock.unlock();
        }
    }

    @Override // org.apache.http.pool.ConnPoolControl
    public void setMaxTotal(int i2) {
        Args.positive(i2, "Max value");
        this.lock.lock();
        try {
            this.maxTotal = i2;
        } finally {
            this.lock.unlock();
        }
    }

    public void setValidateAfterInactivity(int i2) {
        this.validateAfterInactivity = i2;
    }

    public void shutdown() {
        if (this.isShutDown) {
            return;
        }
        this.isShutDown = true;
        this.lock.lock();
        try {
            Iterator<E> it = this.available.iterator();
            while (it.hasNext()) {
                it.next().close();
            }
            for (E e2 : this.leased) {
                e2.close();
            }
            for (RouteSpecificPool<T, C, E> routeSpecificPool : this.routeToPool.values()) {
                routeSpecificPool.shutdown();
            }
            this.routeToPool.clear();
            this.leased.clear();
            this.available.clear();
        } finally {
            this.lock.unlock();
        }
    }

    public String toString() {
        this.lock.lock();
        try {
            return "[leased: " + this.leased + "][available: " + this.available + "][pending: " + this.pending + "]";
        } finally {
            this.lock.unlock();
        }
    }

    protected boolean validate(E e2) {
        return true;
    }
}
