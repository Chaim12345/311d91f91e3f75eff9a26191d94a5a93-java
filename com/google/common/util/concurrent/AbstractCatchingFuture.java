package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.internal.InternalFutureFailureAccess;
import com.google.common.util.concurrent.internal.InternalFutures;
import com.google.errorprone.annotations.ForOverride;
import java.lang.Throwable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible
/* loaded from: classes2.dex */
public abstract class AbstractCatchingFuture<V, X extends Throwable, F, T> extends FluentFuture.TrustedFuture<V> implements Runnable {
    @NullableDecl

    /* renamed from: a  reason: collision with root package name */
    ListenableFuture f9407a;
    @NullableDecl

    /* renamed from: b  reason: collision with root package name */
    Class f9408b;
    @NullableDecl

    /* renamed from: c  reason: collision with root package name */
    Object f9409c;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class AsyncCatchingFuture<V, X extends Throwable> extends AbstractCatchingFuture<V, X, AsyncFunction<? super X, ? extends V>, ListenableFuture<? extends V>> {
        AsyncCatchingFuture(ListenableFuture listenableFuture, Class cls, AsyncFunction asyncFunction) {
            super(listenableFuture, cls, asyncFunction);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        /* renamed from: u */
        public ListenableFuture s(AsyncFunction asyncFunction, Throwable th) {
            ListenableFuture apply = asyncFunction.apply(th);
            Preconditions.checkNotNull(apply, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", asyncFunction);
            return apply;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        /* renamed from: v */
        public void t(ListenableFuture listenableFuture) {
            setFuture(listenableFuture);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class CatchingFuture<V, X extends Throwable> extends AbstractCatchingFuture<V, X, Function<? super X, ? extends V>, V> {
        CatchingFuture(ListenableFuture listenableFuture, Class cls, Function function) {
            super(listenableFuture, cls, function);
        }

        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        void t(@NullableDecl Object obj) {
            set(obj);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        @NullableDecl
        /* renamed from: u */
        public Object s(Function function, Throwable th) {
            return function.apply(th);
        }
    }

    AbstractCatchingFuture(ListenableFuture listenableFuture, Class cls, Object obj) {
        this.f9407a = (ListenableFuture) Preconditions.checkNotNull(listenableFuture);
        this.f9408b = (Class) Preconditions.checkNotNull(cls);
        this.f9409c = Preconditions.checkNotNull(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ListenableFuture q(ListenableFuture listenableFuture, Class cls, Function function, Executor executor) {
        CatchingFuture catchingFuture = new CatchingFuture(listenableFuture, cls, function);
        listenableFuture.addListener(catchingFuture, MoreExecutors.c(executor, catchingFuture));
        return catchingFuture;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ListenableFuture r(ListenableFuture listenableFuture, Class cls, AsyncFunction asyncFunction, Executor executor) {
        AsyncCatchingFuture asyncCatchingFuture = new AsyncCatchingFuture(listenableFuture, cls, asyncFunction);
        listenableFuture.addListener(asyncCatchingFuture, MoreExecutors.c(executor, asyncCatchingFuture));
        return asyncCatchingFuture;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void l() {
        n(this.f9407a);
        this.f9407a = null;
        this.f9408b = null;
        this.f9409c = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.AbstractFuture
    public String o() {
        String str;
        ListenableFuture listenableFuture = this.f9407a;
        Class cls = this.f9408b;
        Object obj = this.f9409c;
        String o2 = super.o();
        if (listenableFuture != null) {
            str = "inputFuture=[" + listenableFuture + "], ";
        } else {
            str = "";
        }
        if (cls == null || obj == null) {
            if (o2 != null) {
                return str + o2;
            }
            return null;
        }
        return str + "exceptionType=[" + cls + "], fallback=[" + obj + "]";
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0076  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void run() {
        Object obj;
        ListenableFuture listenableFuture = this.f9407a;
        Class cls = this.f9408b;
        Object obj2 = this.f9409c;
        if (((obj2 == null) || ((listenableFuture == null) | (cls == null))) || isCancelled()) {
            return;
        }
        this.f9407a = null;
        try {
            th = listenableFuture instanceof InternalFutureFailureAccess ? InternalFutures.tryInternalFastPathGetFailure((InternalFutureFailureAccess) listenableFuture) : null;
        } catch (ExecutionException e2) {
            Throwable cause = e2.getCause();
            if (cause == null) {
                cause = new NullPointerException("Future type " + listenableFuture.getClass() + " threw " + e2.getClass() + " without a cause");
            }
            th = cause;
        } catch (Throwable th) {
            th = th;
        }
        if (th == null) {
            obj = Futures.getDone(listenableFuture);
            if (th != null) {
                set(obj);
                return;
            } else if (!Platform.a(th, cls)) {
                setFuture(listenableFuture);
                return;
            } else {
                try {
                    Object s2 = s(obj2, th);
                    this.f9408b = null;
                    this.f9409c = null;
                    t(s2);
                    return;
                } catch (Throwable th2) {
                    try {
                        setException(th2);
                        return;
                    } finally {
                        this.f9408b = null;
                        this.f9409c = null;
                    }
                }
            }
        }
        obj = null;
        if (th != null) {
        }
    }

    @NullableDecl
    @ForOverride
    abstract Object s(Object obj, Throwable th);

    @ForOverride
    abstract void t(@NullableDecl Object obj);
}
