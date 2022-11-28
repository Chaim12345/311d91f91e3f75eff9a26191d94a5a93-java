package com.google.maps.internal;

import com.google.maps.internal.ratelimiter.RateLimiter;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes2.dex */
public class RateLimitExecutorService implements ExecutorService, Runnable {
    private static final int DEFAULT_QUERIES_PER_SECOND = 50;
    private static final Logger LOG = LoggerFactory.getLogger(RateLimitExecutorService.class.getName());

    /* renamed from: a  reason: collision with root package name */
    final Thread f10329a;
    private final ExecutorService delegate;
    private final BlockingQueue<Runnable> queue;
    private final RateLimiter rateLimiter;

    public RateLimitExecutorService() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        this.delegate = new ThreadPoolExecutor(availableProcessors, Integer.MAX_VALUE, 60L, timeUnit, new SynchronousQueue(), threadFactory("Rate Limited Dispatcher", true));
        this.queue = new LinkedBlockingQueue();
        this.rateLimiter = RateLimiter.create(50.0d, 1L, timeUnit);
        setQueriesPerSecond(50);
        Thread thread = new Thread(this);
        this.f10329a = thread;
        thread.setDaemon(true);
        thread.setName("RateLimitExecutorDelayThread");
        thread.start();
    }

    private static ThreadFactory threadFactory(final String str, final boolean z) {
        return new ThreadFactory() { // from class: com.google.maps.internal.RateLimitExecutorService.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, str);
                thread.setDaemon(z);
                return thread;
            }
        };
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j2, TimeUnit timeUnit) {
        return this.delegate.awaitTermination(j2, timeUnit);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.queue.add(runnable);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) {
        return this.delegate.invokeAll(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j2, TimeUnit timeUnit) {
        return this.delegate.invokeAll(collection, j2, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection) {
        return (T) this.delegate.invokeAny(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j2, TimeUnit timeUnit) {
        return (T) this.delegate.invokeAny(collection, j2, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.delegate.isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return this.delegate.isTerminated();
    }

    @Override // java.lang.Runnable
    public void run() {
        while (!this.delegate.isShutdown()) {
            try {
                this.rateLimiter.acquire();
                Runnable take = this.queue.take();
                if (!this.delegate.isShutdown()) {
                    this.delegate.execute(take);
                }
            } catch (InterruptedException e2) {
                LOG.info("Interrupted", (Throwable) e2);
                return;
            }
        }
    }

    public void setQueriesPerSecond(int i2) {
        this.rateLimiter.setRate(i2);
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        this.delegate.shutdown();
        execute(new Runnable(this) { // from class: com.google.maps.internal.RateLimitExecutorService.2
            @Override // java.lang.Runnable
            public void run() {
            }
        });
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        List<Runnable> shutdownNow = this.delegate.shutdownNow();
        execute(new Runnable(this) { // from class: com.google.maps.internal.RateLimitExecutorService.3
            @Override // java.lang.Runnable
            public void run() {
            }
        });
        return shutdownNow;
    }

    @Override // java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        return this.delegate.submit(runnable);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable runnable, T t2) {
        return this.delegate.submit(runnable, t2);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Callable<T> callable) {
        return this.delegate.submit(callable);
    }
}
