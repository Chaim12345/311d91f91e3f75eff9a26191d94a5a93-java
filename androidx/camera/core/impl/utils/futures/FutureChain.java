package androidx.camera.core.impl.utils.futures;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class FutureChain<V> implements ListenableFuture<V> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    CallbackToFutureAdapter.Completer f1211a;
    @NonNull
    private final ListenableFuture<V> mDelegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FutureChain() {
        this.mDelegate = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver<V>() { // from class: androidx.camera.core.impl.utils.futures.FutureChain.1
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public Object attachCompleter(@NonNull CallbackToFutureAdapter.Completer<V> completer) {
                Preconditions.checkState(FutureChain.this.f1211a == null, "The result can only set once!");
                FutureChain.this.f1211a = completer;
                return "FutureChain[" + FutureChain.this + "]";
            }
        });
    }

    FutureChain(@NonNull ListenableFuture listenableFuture) {
        this.mDelegate = (ListenableFuture) Preconditions.checkNotNull(listenableFuture);
    }

    @NonNull
    public static <V> FutureChain<V> from(@NonNull ListenableFuture<V> listenableFuture) {
        return listenableFuture instanceof FutureChain ? (FutureChain) listenableFuture : new FutureChain<>(listenableFuture);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(@Nullable Object obj) {
        CallbackToFutureAdapter.Completer completer = this.f1211a;
        if (completer != null) {
            return completer.set(obj);
        }
        return false;
    }

    public final void addCallback(@NonNull FutureCallback<? super V> futureCallback, @NonNull Executor executor) {
        Futures.addCallback(this, futureCallback, executor);
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(@NonNull Runnable runnable, @NonNull Executor executor) {
        this.mDelegate.addListener(runnable, executor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(@NonNull Throwable th) {
        CallbackToFutureAdapter.Completer completer = this.f1211a;
        if (completer != null) {
            return completer.setException(th);
        }
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return this.mDelegate.cancel(z);
    }

    @Override // java.util.concurrent.Future
    @Nullable
    public V get() {
        return this.mDelegate.get();
    }

    @Override // java.util.concurrent.Future
    @Nullable
    public V get(long j2, @NonNull TimeUnit timeUnit) {
        return this.mDelegate.get(j2, timeUnit);
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.mDelegate.isCancelled();
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.mDelegate.isDone();
    }

    @NonNull
    public final <T> FutureChain<T> transform(@NonNull Function<? super V, T> function, @NonNull Executor executor) {
        return (FutureChain) Futures.transform(this, function, executor);
    }

    @NonNull
    public final <T> FutureChain<T> transformAsync(@NonNull AsyncFunction<? super V, T> asyncFunction, @NonNull Executor executor) {
        return (FutureChain) Futures.transformAsync(this, asyncFunction, executor);
    }
}
