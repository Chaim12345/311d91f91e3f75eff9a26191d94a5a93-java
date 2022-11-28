package androidx.camera.core.impl;

import android.os.SystemClock;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.impl.Observable;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public final class LiveDataObservable<T> implements Observable<T> {

    /* renamed from: a  reason: collision with root package name */
    final MutableLiveData f1128a = new MutableLiveData();
    @GuardedBy("mObservers")
    private final Map<Observable.Observer<T>, LiveDataObserverAdapter<T>> mObservers = new HashMap();

    /* loaded from: classes.dex */
    private static final class LiveDataObserverAdapter<T> implements Observer<Result<T>> {

        /* renamed from: a  reason: collision with root package name */
        final AtomicBoolean f1137a = new AtomicBoolean(true);

        /* renamed from: b  reason: collision with root package name */
        final Observable.Observer f1138b;

        /* renamed from: c  reason: collision with root package name */
        final Executor f1139c;

        LiveDataObserverAdapter(@NonNull Executor executor, @NonNull Observable.Observer observer) {
            this.f1139c = executor;
            this.f1138b = observer;
        }

        void a() {
            this.f1137a.set(false);
        }

        public void onChanged(@NonNull final Result<T> result) {
            this.f1139c.execute(new Runnable() { // from class: androidx.camera.core.impl.LiveDataObservable.LiveDataObserverAdapter.1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public void run() {
                    if (LiveDataObserverAdapter.this.f1137a.get()) {
                        if (result.completedSuccessfully()) {
                            LiveDataObserverAdapter.this.f1138b.onNewData(result.getValue());
                            return;
                        }
                        Preconditions.checkNotNull(result.getError());
                        LiveDataObserverAdapter.this.f1138b.onError(result.getError());
                    }
                }
            });
        }

        @Override // androidx.lifecycle.Observer
        public /* bridge */ /* synthetic */ void onChanged(@NonNull Object obj) {
            onChanged((Result) ((Result) obj));
        }
    }

    /* loaded from: classes.dex */
    public static final class Result<T> {
        @Nullable
        private Throwable mError;
        @Nullable
        private T mValue;

        private Result(@Nullable T t2, @Nullable Throwable th) {
            this.mValue = t2;
            this.mError = th;
        }

        static Result a(@NonNull Throwable th) {
            return new Result(null, (Throwable) Preconditions.checkNotNull(th));
        }

        static Result b(@Nullable Object obj) {
            return new Result(obj, null);
        }

        public boolean completedSuccessfully() {
            return this.mError == null;
        }

        @Nullable
        public Throwable getError() {
            return this.mError;
        }

        @Nullable
        public T getValue() {
            if (completedSuccessfully()) {
                return this.mValue;
            }
            throw new IllegalStateException("Result contains an error. Does not contain a value.");
        }

        @NonNull
        public String toString() {
            StringBuilder sb;
            Object obj;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("[Result: <");
            if (completedSuccessfully()) {
                sb = new StringBuilder();
                sb.append("Value: ");
                obj = this.mValue;
            } else {
                sb = new StringBuilder();
                sb.append("Error: ");
                obj = this.mError;
            }
            sb.append(obj);
            sb2.append(sb.toString());
            sb2.append(">]");
            return sb2.toString();
        }
    }

    @Override // androidx.camera.core.impl.Observable
    public void addObserver(@NonNull Executor executor, @NonNull Observable.Observer<T> observer) {
        synchronized (this.mObservers) {
            final LiveDataObserverAdapter<T> liveDataObserverAdapter = this.mObservers.get(observer);
            if (liveDataObserverAdapter != null) {
                liveDataObserverAdapter.a();
            }
            final LiveDataObserverAdapter<T> liveDataObserverAdapter2 = new LiveDataObserverAdapter<>(executor, observer);
            this.mObservers.put(observer, liveDataObserverAdapter2);
            CameraXExecutors.mainThreadExecutor().execute(new Runnable() { // from class: androidx.camera.core.impl.LiveDataObservable.2
                @Override // java.lang.Runnable
                public void run() {
                    LiveDataObservable.this.f1128a.removeObserver(liveDataObserverAdapter);
                    LiveDataObservable.this.f1128a.observeForever(liveDataObserverAdapter2);
                }
            });
        }
    }

    @Override // androidx.camera.core.impl.Observable
    @NonNull
    public ListenableFuture<T> fetchData() {
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver<T>() { // from class: androidx.camera.core.impl.LiveDataObservable.1
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            @Nullable
            public Object attachCompleter(@NonNull final CallbackToFutureAdapter.Completer<T> completer) {
                CameraXExecutors.mainThreadExecutor().execute(new Runnable() { // from class: androidx.camera.core.impl.LiveDataObservable.1.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.lang.Runnable
                    public void run() {
                        Result result = (Result) LiveDataObservable.this.f1128a.getValue();
                        if (result == null) {
                            completer.setException(new IllegalStateException("Observable has not yet been initialized with a value."));
                        } else if (result.completedSuccessfully()) {
                            completer.set(result.getValue());
                        } else {
                            Preconditions.checkNotNull(result.getError());
                            completer.setException(result.getError());
                        }
                    }
                });
                return LiveDataObservable.this + " [fetch@" + SystemClock.uptimeMillis() + "]";
            }
        });
    }

    @NonNull
    public LiveData<Result<T>> getLiveData() {
        return this.f1128a;
    }

    public void postError(@NonNull Throwable th) {
        this.f1128a.postValue(Result.a(th));
    }

    public void postValue(@Nullable T t2) {
        this.f1128a.postValue(Result.b(t2));
    }

    @Override // androidx.camera.core.impl.Observable
    public void removeObserver(@NonNull Observable.Observer<T> observer) {
        synchronized (this.mObservers) {
            final LiveDataObserverAdapter<T> remove = this.mObservers.remove(observer);
            if (remove != null) {
                remove.a();
                CameraXExecutors.mainThreadExecutor().execute(new Runnable() { // from class: androidx.camera.core.impl.LiveDataObservable.3
                    @Override // java.lang.Runnable
                    public void run() {
                        LiveDataObservable.this.f1128a.removeObserver(remove);
                    }
                });
            }
        }
    }
}
