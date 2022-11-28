package com.android.volley;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.volley.AsyncCache;
import com.android.volley.AsyncNetwork;
import com.android.volley.Cache;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class AsyncRequestQueue extends RequestQueue {
    private static final int DEFAULT_BLOCKING_THREAD_POOL_SIZE = 4;
    @Nullable
    private final AsyncCache mAsyncCache;
    private ExecutorService mBlockingExecutor;
    private final Object mCacheInitializationLock;
    private ExecutorFactory mExecutorFactory;
    private volatile boolean mIsCacheInitialized;
    private final AsyncNetwork mNetwork;
    private ExecutorService mNonBlockingExecutor;
    private ScheduledExecutorService mNonBlockingScheduledExecutor;
    private final List<Request<?>> mRequestsAwaitingCacheInitialization;
    private final WaitingRequestManager mWaitingRequestManager;

    /* loaded from: classes.dex */
    public static class Builder {
        private final AsyncNetwork mNetwork;
        @Nullable
        private AsyncCache mAsyncCache = null;
        @Nullable
        private Cache mCache = null;
        @Nullable
        private ExecutorFactory mExecutorFactory = null;
        @Nullable
        private ResponseDelivery mResponseDelivery = null;

        public Builder(AsyncNetwork asyncNetwork) {
            if (asyncNetwork == null) {
                throw new IllegalArgumentException("Network cannot be null");
            }
            this.mNetwork = asyncNetwork;
        }

        private ExecutorFactory getDefaultExecutorFactory() {
            return new ExecutorFactory(this) { // from class: com.android.volley.AsyncRequestQueue.Builder.1
                private ThreadPoolExecutor getNewThreadPoolExecutor(int i2, String str, BlockingQueue<Runnable> blockingQueue) {
                    return new ThreadPoolExecutor(0, i2, 60L, TimeUnit.SECONDS, blockingQueue, getThreadFactory(str));
                }

                private ThreadFactory getThreadFactory(final String str) {
                    return new ThreadFactory(this) { // from class: com.android.volley.AsyncRequestQueue.Builder.1.1
                        @Override // java.util.concurrent.ThreadFactory
                        public Thread newThread(@NonNull Runnable runnable) {
                            Thread newThread = Executors.defaultThreadFactory().newThread(runnable);
                            newThread.setName("Volley-" + str);
                            return newThread;
                        }
                    };
                }

                @Override // com.android.volley.AsyncRequestQueue.ExecutorFactory
                public ExecutorService createBlockingExecutor(BlockingQueue<Runnable> blockingQueue) {
                    return getNewThreadPoolExecutor(4, "BlockingExecutor", blockingQueue);
                }

                @Override // com.android.volley.AsyncRequestQueue.ExecutorFactory
                public ExecutorService createNonBlockingExecutor(BlockingQueue<Runnable> blockingQueue) {
                    return getNewThreadPoolExecutor(1, "Non-BlockingExecutor", blockingQueue);
                }

                @Override // com.android.volley.AsyncRequestQueue.ExecutorFactory
                public ScheduledExecutorService createNonBlockingScheduledExecutor() {
                    return new ScheduledThreadPoolExecutor(0, getThreadFactory("ScheduledExecutor"));
                }
            };
        }

        public AsyncRequestQueue build() {
            Cache cache = this.mCache;
            if (cache == null && this.mAsyncCache == null) {
                throw new IllegalArgumentException("You must set one of the cache objects");
            }
            if (cache == null) {
                this.mCache = new ThrowingCache();
            }
            if (this.mResponseDelivery == null) {
                this.mResponseDelivery = new ExecutorDelivery(new Handler(Looper.getMainLooper()));
            }
            if (this.mExecutorFactory == null) {
                this.mExecutorFactory = getDefaultExecutorFactory();
            }
            return new AsyncRequestQueue(this.mCache, this.mNetwork, this.mAsyncCache, this.mResponseDelivery, this.mExecutorFactory);
        }

        public Builder setAsyncCache(AsyncCache asyncCache) {
            this.mAsyncCache = asyncCache;
            return this;
        }

        public Builder setCache(Cache cache) {
            this.mCache = cache;
            return this;
        }

        public Builder setExecutorFactory(ExecutorFactory executorFactory) {
            this.mExecutorFactory = executorFactory;
            return this;
        }

        public Builder setResponseDelivery(ResponseDelivery responseDelivery) {
            this.mResponseDelivery = responseDelivery;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CacheParseTask<T> extends RequestTask<T> {

        /* renamed from: b  reason: collision with root package name */
        Cache.Entry f4490b;

        /* renamed from: c  reason: collision with root package name */
        long f4491c;

        CacheParseTask(Request request, Cache.Entry entry, long j2) {
            super(request);
            this.f4490b = entry;
            this.f4491c = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f4513a.addMarker("cache-hit");
            Request request = this.f4513a;
            Cache.Entry entry = this.f4490b;
            Response<?> parseNetworkResponse = request.parseNetworkResponse(new NetworkResponse(200, entry.data, false, 0L, entry.allResponseHeaders));
            this.f4513a.addMarker("cache-hit-parsed");
            if (this.f4490b.b(this.f4491c)) {
                this.f4513a.addMarker("cache-hit-refresh-needed");
                this.f4513a.setCacheEntry(this.f4490b);
                parseNetworkResponse.intermediate = true;
                if (!AsyncRequestQueue.this.mWaitingRequestManager.a(this.f4513a)) {
                    AsyncRequestQueue.this.getResponseDelivery().postResponse(this.f4513a, parseNetworkResponse, new Runnable() { // from class: com.android.volley.AsyncRequestQueue.CacheParseTask.1
                        @Override // java.lang.Runnable
                        public void run() {
                            CacheParseTask cacheParseTask = CacheParseTask.this;
                            AsyncRequestQueue.this.d(cacheParseTask.f4513a);
                        }
                    });
                    return;
                }
            }
            AsyncRequestQueue.this.getResponseDelivery().postResponse(this.f4513a, parseNetworkResponse);
        }
    }

    /* loaded from: classes.dex */
    private class CachePutTask<T> extends RequestTask<T> {

        /* renamed from: b  reason: collision with root package name */
        Response f4494b;

        CachePutTask(Request request, Response response) {
            super(request);
            this.f4494b = response;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (AsyncRequestQueue.this.mAsyncCache != null) {
                AsyncRequestQueue.this.mAsyncCache.put(this.f4513a.getCacheKey(), this.f4494b.cacheEntry, new AsyncCache.OnWriteCompleteCallback() { // from class: com.android.volley.AsyncRequestQueue.CachePutTask.1
                    @Override // com.android.volley.AsyncCache.OnWriteCompleteCallback
                    public void onWriteComplete() {
                        CachePutTask cachePutTask = CachePutTask.this;
                        AsyncRequestQueue.this.finishRequest(cachePutTask.f4513a, cachePutTask.f4494b, true);
                    }
                });
                return;
            }
            AsyncRequestQueue.this.getCache().put(this.f4513a.getCacheKey(), this.f4494b.cacheEntry);
            AsyncRequestQueue.this.finishRequest(this.f4513a, this.f4494b, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CacheTask<T> extends RequestTask<T> {
        CacheTask(Request request) {
            super(request);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f4513a.isCanceled()) {
                this.f4513a.finish("cache-discard-canceled");
                return;
            }
            this.f4513a.addMarker("cache-queue-take");
            if (AsyncRequestQueue.this.mAsyncCache != null) {
                AsyncRequestQueue.this.mAsyncCache.get(this.f4513a.getCacheKey(), new AsyncCache.OnGetCompleteCallback() { // from class: com.android.volley.AsyncRequestQueue.CacheTask.1
                    @Override // com.android.volley.AsyncCache.OnGetCompleteCallback
                    public void onGetComplete(Cache.Entry entry) {
                        CacheTask cacheTask = CacheTask.this;
                        AsyncRequestQueue.this.handleEntry(entry, cacheTask.f4513a);
                    }
                });
                return;
            }
            AsyncRequestQueue.this.handleEntry(AsyncRequestQueue.this.getCache().get(this.f4513a.getCacheKey()), this.f4513a);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class ExecutorFactory {
        public abstract ExecutorService createBlockingExecutor(BlockingQueue<Runnable> blockingQueue);

        public abstract ExecutorService createNonBlockingExecutor(BlockingQueue<Runnable> blockingQueue);

        public abstract ScheduledExecutorService createNonBlockingScheduledExecutor();
    }

    /* loaded from: classes.dex */
    private class NetworkParseTask<T> extends RequestTask<T> {

        /* renamed from: b  reason: collision with root package name */
        NetworkResponse f4499b;

        NetworkParseTask(Request request, NetworkResponse networkResponse) {
            super(request);
            this.f4499b = networkResponse;
        }

        @Override // java.lang.Runnable
        public void run() {
            ExecutorService executorService;
            CachePutTask cachePutTask;
            Response parseNetworkResponse = this.f4513a.parseNetworkResponse(this.f4499b);
            this.f4513a.addMarker("network-parse-complete");
            if (!this.f4513a.shouldCache() || parseNetworkResponse.cacheEntry == null) {
                AsyncRequestQueue.this.finishRequest(this.f4513a, parseNetworkResponse, false);
                return;
            }
            if (AsyncRequestQueue.this.mAsyncCache != null) {
                executorService = AsyncRequestQueue.this.mNonBlockingExecutor;
                cachePutTask = new CachePutTask(this.f4513a, parseNetworkResponse);
            } else {
                executorService = AsyncRequestQueue.this.mBlockingExecutor;
                cachePutTask = new CachePutTask(this.f4513a, parseNetworkResponse);
            }
            executorService.execute(cachePutTask);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class NetworkTask<T> extends RequestTask<T> {
        NetworkTask(Request request) {
            super(request);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f4513a.isCanceled()) {
                this.f4513a.finish("network-discard-cancelled");
                this.f4513a.notifyListenerResponseNotUsable();
                return;
            }
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            this.f4513a.addMarker("network-queue-take");
            AsyncRequestQueue.this.mNetwork.performRequest(this.f4513a, new AsyncNetwork.OnRequestComplete() { // from class: com.android.volley.AsyncRequestQueue.NetworkTask.1
                @Override // com.android.volley.AsyncNetwork.OnRequestComplete
                public void onError(VolleyError volleyError) {
                    volleyError.a(SystemClock.elapsedRealtime() - elapsedRealtime);
                    ExecutorService executorService = AsyncRequestQueue.this.mBlockingExecutor;
                    NetworkTask networkTask = NetworkTask.this;
                    executorService.execute(new ParseErrorTask(networkTask.f4513a, volleyError));
                }

                @Override // com.android.volley.AsyncNetwork.OnRequestComplete
                public void onSuccess(NetworkResponse networkResponse) {
                    NetworkTask.this.f4513a.addMarker("network-http-complete");
                    if (networkResponse.notModified && NetworkTask.this.f4513a.hasHadResponseDelivered()) {
                        NetworkTask.this.f4513a.finish("not-modified");
                        NetworkTask.this.f4513a.notifyListenerResponseNotUsable();
                        return;
                    }
                    ExecutorService executorService = AsyncRequestQueue.this.mBlockingExecutor;
                    NetworkTask networkTask = NetworkTask.this;
                    executorService.execute(new NetworkParseTask(networkTask.f4513a, networkResponse));
                }
            });
        }
    }

    /* loaded from: classes.dex */
    private class ParseErrorTask<T> extends RequestTask<T> {

        /* renamed from: b  reason: collision with root package name */
        VolleyError f4504b;

        ParseErrorTask(Request request, VolleyError volleyError) {
            super(request);
            this.f4504b = volleyError;
        }

        @Override // java.lang.Runnable
        public void run() {
            AsyncRequestQueue.this.getResponseDelivery().postError(this.f4513a, this.f4513a.parseNetworkError(this.f4504b));
            this.f4513a.notifyListenerResponseNotUsable();
        }
    }

    /* loaded from: classes.dex */
    private static class ThrowingCache implements Cache {
        private ThrowingCache() {
        }

        @Override // com.android.volley.Cache
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // com.android.volley.Cache
        public Cache.Entry get(String str) {
            throw new UnsupportedOperationException();
        }

        @Override // com.android.volley.Cache
        public void initialize() {
            throw new UnsupportedOperationException();
        }

        @Override // com.android.volley.Cache
        public void invalidate(String str, boolean z) {
            throw new UnsupportedOperationException();
        }

        @Override // com.android.volley.Cache
        public void put(String str, Cache.Entry entry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.android.volley.Cache
        public void remove(String str) {
            throw new UnsupportedOperationException();
        }
    }

    private AsyncRequestQueue(Cache cache, AsyncNetwork asyncNetwork, @Nullable AsyncCache asyncCache, ResponseDelivery responseDelivery, ExecutorFactory executorFactory) {
        super(cache, asyncNetwork, 0, responseDelivery);
        this.mWaitingRequestManager = new WaitingRequestManager(this);
        this.mRequestsAwaitingCacheInitialization = new ArrayList();
        this.mIsCacheInitialized = false;
        this.mCacheInitializationLock = new Object[0];
        this.mAsyncCache = asyncCache;
        this.mNetwork = asyncNetwork;
        this.mExecutorFactory = executorFactory;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishRequest(Request<?> request, Response<?> response, boolean z) {
        if (z) {
            request.addMarker("network-cache-written");
        }
        request.markDelivered();
        getResponseDelivery().postResponse(request, response);
        request.notifyListenerResponseReceived(response);
    }

    private static PriorityBlockingQueue<Runnable> getBlockingQueue() {
        return new PriorityBlockingQueue<>(11, new Comparator<Runnable>() { // from class: com.android.volley.AsyncRequestQueue.3
            @Override // java.util.Comparator
            public int compare(Runnable runnable, Runnable runnable2) {
                if (!(runnable instanceof RequestTask)) {
                    return runnable2 instanceof RequestTask ? -1 : 0;
                } else if (runnable2 instanceof RequestTask) {
                    return ((RequestTask) runnable).compareTo((RequestTask) runnable2);
                } else {
                    return 1;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleEntry(Cache.Entry entry, Request<?> request) {
        if (entry == null) {
            request.addMarker("cache-miss");
            if (this.mWaitingRequestManager.a(request)) {
                return;
            }
            d(request);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (!entry.a(currentTimeMillis)) {
            this.mBlockingExecutor.execute(new CacheParseTask(request, entry, currentTimeMillis));
            return;
        }
        request.addMarker("cache-hit-expired");
        request.setCacheEntry(entry);
        if (this.mWaitingRequestManager.a(request)) {
            return;
        }
        d(request);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCacheInitializationComplete() {
        ArrayList<Request> arrayList;
        synchronized (this.mCacheInitializationLock) {
            arrayList = new ArrayList(this.mRequestsAwaitingCacheInitialization);
            this.mRequestsAwaitingCacheInitialization.clear();
            this.mIsCacheInitialized = true;
        }
        for (Request request : arrayList) {
            a(request);
        }
    }

    @Override // com.android.volley.RequestQueue
    void a(Request request) {
        ExecutorService executorService;
        CacheTask cacheTask;
        if (!this.mIsCacheInitialized) {
            synchronized (this.mCacheInitializationLock) {
                if (!this.mIsCacheInitialized) {
                    this.mRequestsAwaitingCacheInitialization.add(request);
                    return;
                }
            }
        }
        if (!request.shouldCache()) {
            d(request);
            return;
        }
        if (this.mAsyncCache != null) {
            executorService = this.mNonBlockingExecutor;
            cacheTask = new CacheTask(request);
        } else {
            executorService = this.mBlockingExecutor;
            cacheTask = new CacheTask(request);
        }
        executorService.execute(cacheTask);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.volley.RequestQueue
    public void d(Request request) {
        this.mNonBlockingExecutor.execute(new NetworkTask(request));
    }

    @Override // com.android.volley.RequestQueue
    public void start() {
        ExecutorService executorService;
        Runnable runnable;
        stop();
        this.mNonBlockingExecutor = this.mExecutorFactory.createNonBlockingExecutor(getBlockingQueue());
        this.mBlockingExecutor = this.mExecutorFactory.createBlockingExecutor(getBlockingQueue());
        this.mNonBlockingScheduledExecutor = this.mExecutorFactory.createNonBlockingScheduledExecutor();
        this.mNetwork.setBlockingExecutor(this.mBlockingExecutor);
        this.mNetwork.setNonBlockingExecutor(this.mNonBlockingExecutor);
        this.mNetwork.setNonBlockingScheduledExecutor(this.mNonBlockingScheduledExecutor);
        if (this.mAsyncCache != null) {
            executorService = this.mNonBlockingExecutor;
            runnable = new Runnable() { // from class: com.android.volley.AsyncRequestQueue.1
                @Override // java.lang.Runnable
                public void run() {
                    AsyncRequestQueue.this.mAsyncCache.initialize(new AsyncCache.OnWriteCompleteCallback() { // from class: com.android.volley.AsyncRequestQueue.1.1
                        @Override // com.android.volley.AsyncCache.OnWriteCompleteCallback
                        public void onWriteComplete() {
                            AsyncRequestQueue.this.onCacheInitializationComplete();
                        }
                    });
                }
            };
        } else {
            executorService = this.mBlockingExecutor;
            runnable = new Runnable() { // from class: com.android.volley.AsyncRequestQueue.2
                @Override // java.lang.Runnable
                public void run() {
                    AsyncRequestQueue.this.getCache().initialize();
                    AsyncRequestQueue.this.mNonBlockingExecutor.execute(new Runnable() { // from class: com.android.volley.AsyncRequestQueue.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AsyncRequestQueue.this.onCacheInitializationComplete();
                        }
                    });
                }
            };
        }
        executorService.execute(runnable);
    }

    @Override // com.android.volley.RequestQueue
    public void stop() {
        ExecutorService executorService = this.mNonBlockingExecutor;
        if (executorService != null) {
            executorService.shutdownNow();
            this.mNonBlockingExecutor = null;
        }
        ExecutorService executorService2 = this.mBlockingExecutor;
        if (executorService2 != null) {
            executorService2.shutdownNow();
            this.mBlockingExecutor = null;
        }
        ScheduledExecutorService scheduledExecutorService = this.mNonBlockingScheduledExecutor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.mNonBlockingScheduledExecutor = null;
        }
    }
}
