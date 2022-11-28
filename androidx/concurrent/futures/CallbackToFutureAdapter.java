package androidx.concurrent.futures;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public final class CallbackToFutureAdapter {

    /* loaded from: classes.dex */
    public static final class Completer<T> {

        /* renamed from: a  reason: collision with root package name */
        Object f1642a;
        private boolean attemptedSetting;

        /* renamed from: b  reason: collision with root package name */
        SafeFuture f1643b;
        private ResolvableFuture<Void> cancellationFuture = ResolvableFuture.create();

        Completer() {
        }

        private void setCompletedNormally() {
            this.f1642a = null;
            this.f1643b = null;
            this.cancellationFuture = null;
        }

        void a() {
            this.f1642a = null;
            this.f1643b = null;
            this.cancellationFuture.set(null);
        }

        public void addCancellationListener(@NonNull Runnable runnable, @NonNull Executor executor) {
            ResolvableFuture<Void> resolvableFuture = this.cancellationFuture;
            if (resolvableFuture != null) {
                resolvableFuture.addListener(runnable, executor);
            }
        }

        protected void finalize() {
            ResolvableFuture<Void> resolvableFuture;
            SafeFuture safeFuture = this.f1643b;
            if (safeFuture != null && !safeFuture.isDone()) {
                safeFuture.c(new FutureGarbageCollectedException("The completer object was garbage collected - this future would otherwise never complete. The tag was: " + this.f1642a));
            }
            if (this.attemptedSetting || (resolvableFuture = this.cancellationFuture) == null) {
                return;
            }
            resolvableFuture.set(null);
        }

        public boolean set(T t2) {
            boolean z = true;
            this.attemptedSetting = true;
            SafeFuture safeFuture = this.f1643b;
            z = (safeFuture == null || !safeFuture.b(t2)) ? false : false;
            if (z) {
                setCompletedNormally();
            }
            return z;
        }

        public boolean setCancelled() {
            boolean z = true;
            this.attemptedSetting = true;
            SafeFuture safeFuture = this.f1643b;
            z = (safeFuture == null || !safeFuture.a(true)) ? false : false;
            if (z) {
                setCompletedNormally();
            }
            return z;
        }

        public boolean setException(@NonNull Throwable th) {
            boolean z = true;
            this.attemptedSetting = true;
            SafeFuture safeFuture = this.f1643b;
            z = (safeFuture == null || !safeFuture.c(th)) ? false : false;
            if (z) {
                setCompletedNormally();
            }
            return z;
        }
    }

    /* loaded from: classes.dex */
    static final class FutureGarbageCollectedException extends Throwable {
        FutureGarbageCollectedException(String str) {
            super(str);
        }

        @Override // java.lang.Throwable
        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }

    /* loaded from: classes.dex */
    public interface Resolver<T> {
        @Nullable
        Object attachCompleter(@NonNull Completer<T> completer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class SafeFuture<T> implements ListenableFuture<T> {

        /* renamed from: a  reason: collision with root package name */
        final WeakReference f1644a;
        private final AbstractResolvableFuture<T> delegate = (AbstractResolvableFuture<T>) new AbstractResolvableFuture<Object>() { // from class: androidx.concurrent.futures.CallbackToFutureAdapter.SafeFuture.1
            @Override // androidx.concurrent.futures.AbstractResolvableFuture
            protected String f() {
                Completer completer = (Completer) SafeFuture.this.f1644a.get();
                if (completer == null) {
                    return "Completer object has been garbage collected, future will fail soon";
                }
                return "tag=[" + completer.f1642a + "]";
            }
        };

        SafeFuture(Completer completer) {
            this.f1644a = new WeakReference(completer);
        }

        boolean a(boolean z) {
            return this.delegate.cancel(z);
        }

        @Override // com.google.common.util.concurrent.ListenableFuture
        public void addListener(@NonNull Runnable runnable, @NonNull Executor executor) {
            this.delegate.addListener(runnable, executor);
        }

        boolean b(Object obj) {
            return this.delegate.set(obj);
        }

        boolean c(Throwable th) {
            return this.delegate.setException(th);
        }

        @Override // java.util.concurrent.Future
        public boolean cancel(boolean z) {
            Completer completer = (Completer) this.f1644a.get();
            boolean cancel = this.delegate.cancel(z);
            if (cancel && completer != null) {
                completer.a();
            }
            return cancel;
        }

        @Override // java.util.concurrent.Future
        public T get() {
            return this.delegate.get();
        }

        @Override // java.util.concurrent.Future
        public T get(long j2, @NonNull TimeUnit timeUnit) {
            return this.delegate.get(j2, timeUnit);
        }

        @Override // java.util.concurrent.Future
        public boolean isCancelled() {
            return this.delegate.isCancelled();
        }

        @Override // java.util.concurrent.Future
        public boolean isDone() {
            return this.delegate.isDone();
        }

        public String toString() {
            return this.delegate.toString();
        }
    }

    private CallbackToFutureAdapter() {
    }

    @NonNull
    public static <T> ListenableFuture<T> getFuture(@NonNull Resolver<T> resolver) {
        Completer<T> completer = new Completer<>();
        SafeFuture safeFuture = new SafeFuture(completer);
        completer.f1643b = safeFuture;
        completer.f1642a = resolver.getClass();
        try {
            Object attachCompleter = resolver.attachCompleter(completer);
            if (attachCompleter != null) {
                completer.f1642a = attachCompleter;
            }
        } catch (Exception e2) {
            safeFuture.c(e2);
        }
        return safeFuture;
    }
}
