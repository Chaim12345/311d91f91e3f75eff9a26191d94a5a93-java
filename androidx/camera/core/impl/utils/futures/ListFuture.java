package androidx.camera.core.impl.utils.futures;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ListFuture<V> implements ListenableFuture<List<V>> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    List f1220a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    List f1221b;

    /* renamed from: c  reason: collision with root package name */
    CallbackToFutureAdapter.Completer f1222c;
    private final boolean mAllMustSucceed;
    @NonNull
    private final AtomicInteger mRemaining;
    @NonNull
    private final ListenableFuture<List<V>> mResult = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver<List<V>>() { // from class: androidx.camera.core.impl.utils.futures.ListFuture.1
        @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
        public Object attachCompleter(@NonNull CallbackToFutureAdapter.Completer<List<V>> completer) {
            Preconditions.checkState(ListFuture.this.f1222c == null, "The result can only set once!");
            ListFuture.this.f1222c = completer;
            return "ListFuture[" + this + "]";
        }
    });

    /* JADX INFO: Access modifiers changed from: package-private */
    public ListFuture(@NonNull List list, boolean z, @NonNull Executor executor) {
        this.f1220a = (List) Preconditions.checkNotNull(list);
        this.f1221b = new ArrayList(list.size());
        this.mAllMustSucceed = z;
        this.mRemaining = new AtomicInteger(list.size());
        init(executor);
    }

    private void callAllGets() {
        List<ListenableFuture> list = this.f1220a;
        if (list == null || isDone()) {
            return;
        }
        for (ListenableFuture listenableFuture : list) {
            while (!listenableFuture.isDone()) {
                try {
                    listenableFuture.get();
                } catch (Error e2) {
                    throw e2;
                } catch (InterruptedException e3) {
                    throw e3;
                } catch (Throwable unused) {
                    if (this.mAllMustSucceed) {
                        return;
                    }
                }
            }
        }
    }

    private void init(@NonNull Executor executor) {
        addListener(new Runnable() { // from class: androidx.camera.core.impl.utils.futures.ListFuture.2
            @Override // java.lang.Runnable
            public void run() {
                ListFuture listFuture = ListFuture.this;
                listFuture.f1221b = null;
                listFuture.f1220a = null;
            }
        }, CameraXExecutors.directExecutor());
        if (this.f1220a.isEmpty()) {
            this.f1222c.set(new ArrayList(this.f1221b));
            return;
        }
        for (int i2 = 0; i2 < this.f1220a.size(); i2++) {
            this.f1221b.add(null);
        }
        List list = this.f1220a;
        for (final int i3 = 0; i3 < list.size(); i3++) {
            final ListenableFuture listenableFuture = (ListenableFuture) list.get(i3);
            listenableFuture.addListener(new Runnable() { // from class: androidx.camera.core.impl.utils.futures.ListFuture.3
                @Override // java.lang.Runnable
                public void run() {
                    ListFuture.this.a(i3, listenableFuture);
                }
            }, executor);
        }
    }

    void a(int i2, @NonNull Future future) {
        CallbackToFutureAdapter.Completer completer;
        ArrayList arrayList;
        int decrementAndGet;
        List list = this.f1221b;
        if (isDone() || list == null) {
            Preconditions.checkState(this.mAllMustSucceed, "Future was done before all dependencies completed");
            return;
        }
        try {
            try {
                try {
                    try {
                        Preconditions.checkState(future.isDone(), "Tried to set value from future which is not done");
                        list.set(i2, Futures.getUninterruptibly(future));
                        decrementAndGet = this.mRemaining.decrementAndGet();
                        Preconditions.checkState(decrementAndGet >= 0, "Less than 0 remaining futures");
                    } catch (ExecutionException e2) {
                        if (this.mAllMustSucceed) {
                            this.f1222c.setException(e2.getCause());
                        }
                        int decrementAndGet2 = this.mRemaining.decrementAndGet();
                        Preconditions.checkState(decrementAndGet2 >= 0, "Less than 0 remaining futures");
                        if (decrementAndGet2 != 0) {
                            return;
                        }
                        List list2 = this.f1221b;
                        if (list2 != null) {
                            completer = this.f1222c;
                            arrayList = new ArrayList(list2);
                        }
                    }
                } catch (RuntimeException e3) {
                    if (this.mAllMustSucceed) {
                        this.f1222c.setException(e3);
                    }
                    int decrementAndGet3 = this.mRemaining.decrementAndGet();
                    Preconditions.checkState(decrementAndGet3 >= 0, "Less than 0 remaining futures");
                    if (decrementAndGet3 != 0) {
                        return;
                    }
                    List list3 = this.f1221b;
                    if (list3 != null) {
                        completer = this.f1222c;
                        arrayList = new ArrayList(list3);
                    }
                }
            } catch (Error e4) {
                this.f1222c.setException(e4);
                int decrementAndGet4 = this.mRemaining.decrementAndGet();
                Preconditions.checkState(decrementAndGet4 >= 0, "Less than 0 remaining futures");
                if (decrementAndGet4 != 0) {
                    return;
                }
                List list4 = this.f1221b;
                if (list4 != null) {
                    completer = this.f1222c;
                    arrayList = new ArrayList(list4);
                }
            } catch (CancellationException unused) {
                if (this.mAllMustSucceed) {
                    cancel(false);
                }
                int decrementAndGet5 = this.mRemaining.decrementAndGet();
                Preconditions.checkState(decrementAndGet5 >= 0, "Less than 0 remaining futures");
                if (decrementAndGet5 != 0) {
                    return;
                }
                List list5 = this.f1221b;
                if (list5 != null) {
                    completer = this.f1222c;
                    arrayList = new ArrayList(list5);
                }
            }
            if (decrementAndGet == 0) {
                List list6 = this.f1221b;
                if (list6 != null) {
                    completer = this.f1222c;
                    arrayList = new ArrayList(list6);
                    completer.set(arrayList);
                    return;
                }
                Preconditions.checkState(isDone());
            }
        } catch (Throwable th) {
            int decrementAndGet6 = this.mRemaining.decrementAndGet();
            Preconditions.checkState(decrementAndGet6 >= 0, "Less than 0 remaining futures");
            if (decrementAndGet6 == 0) {
                List list7 = this.f1221b;
                if (list7 != null) {
                    this.f1222c.set(new ArrayList(list7));
                } else {
                    Preconditions.checkState(isDone());
                }
            }
            throw th;
        }
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(@NonNull Runnable runnable, @NonNull Executor executor) {
        this.mResult.addListener(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        List<ListenableFuture> list = this.f1220a;
        if (list != null) {
            for (ListenableFuture listenableFuture : list) {
                listenableFuture.cancel(z);
            }
        }
        return this.mResult.cancel(z);
    }

    @Override // java.util.concurrent.Future
    @Nullable
    public List<V> get() {
        callAllGets();
        return this.mResult.get();
    }

    @Override // java.util.concurrent.Future
    public List<V> get(long j2, @NonNull TimeUnit timeUnit) {
        return this.mResult.get(j2, timeUnit);
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.mResult.isCancelled();
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.mResult.isDone();
    }
}
