package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.util.concurrent.AggregateFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible
/* loaded from: classes2.dex */
public final class CombinedFuture<V> extends AggregateFuture<Object, V> {
    private CombinedFuture<V>.CombinedFutureInterruptibleTask<?> task;

    /* loaded from: classes2.dex */
    private final class AsyncCallableInterruptibleTask extends CombinedFuture<V>.CombinedFutureInterruptibleTask<ListenableFuture<V>> {
        private final AsyncCallable<V> callable;

        AsyncCallableInterruptibleTask(AsyncCallable asyncCallable, Executor executor) {
            super(executor);
            this.callable = (AsyncCallable) Preconditions.checkNotNull(asyncCallable);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        String e() {
            return this.callable.toString();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.InterruptibleTask
        /* renamed from: h */
        public ListenableFuture d() {
            this.f9490a = false;
            return (ListenableFuture) Preconditions.checkNotNull(this.callable.call(), "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", this.callable);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.CombinedFuture.CombinedFutureInterruptibleTask
        /* renamed from: i */
        public void g(ListenableFuture listenableFuture) {
            CombinedFuture.this.setFuture(listenableFuture);
        }
    }

    /* loaded from: classes2.dex */
    private final class CallableInterruptibleTask extends CombinedFuture<V>.CombinedFutureInterruptibleTask<V> {
        private final Callable<V> callable;

        CallableInterruptibleTask(Callable callable, Executor executor) {
            super(executor);
            this.callable = (Callable) Preconditions.checkNotNull(callable);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        Object d() {
            this.f9490a = false;
            return this.callable.call();
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        String e() {
            return this.callable.toString();
        }

        @Override // com.google.common.util.concurrent.CombinedFuture.CombinedFutureInterruptibleTask
        void g(Object obj) {
            CombinedFuture.this.set(obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public abstract class CombinedFutureInterruptibleTask<T> extends InterruptibleTask<T> {

        /* renamed from: a  reason: collision with root package name */
        boolean f9490a = true;
        private final Executor listenerExecutor;

        CombinedFutureInterruptibleTask(Executor executor) {
            this.listenerExecutor = (Executor) Preconditions.checkNotNull(executor);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        final void a(Object obj, Throwable th) {
            CombinedFuture combinedFuture;
            CombinedFuture.this.task = null;
            if (th == null) {
                g(obj);
                return;
            }
            if (th instanceof ExecutionException) {
                combinedFuture = CombinedFuture.this;
                th = th.getCause();
            } else if (th instanceof CancellationException) {
                CombinedFuture.this.cancel(false);
                return;
            } else {
                combinedFuture = CombinedFuture.this;
            }
            combinedFuture.setException(th);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        final boolean c() {
            return CombinedFuture.this.isDone();
        }

        final void f() {
            try {
                this.listenerExecutor.execute(this);
            } catch (RejectedExecutionException e2) {
                if (this.f9490a) {
                    CombinedFuture.this.setException(e2);
                }
            }
        }

        abstract void g(Object obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CombinedFuture(ImmutableCollection immutableCollection, boolean z, Executor executor, AsyncCallable asyncCallable) {
        super(immutableCollection, z, false);
        this.task = new AsyncCallableInterruptibleTask(asyncCallable, executor);
        C();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CombinedFuture(ImmutableCollection immutableCollection, boolean z, Executor executor, Callable callable) {
        super(immutableCollection, z, false);
        this.task = new CallableInterruptibleTask(callable, executor);
        C();
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    void A(int i2, @NullableDecl Object obj) {
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    void B() {
        CombinedFuture<V>.CombinedFutureInterruptibleTask<?> combinedFutureInterruptibleTask = this.task;
        if (combinedFutureInterruptibleTask != null) {
            combinedFutureInterruptibleTask.f();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.util.concurrent.AggregateFuture
    public void D(AggregateFuture.ReleaseResourcesReason releaseResourcesReason) {
        super.D(releaseResourcesReason);
        if (releaseResourcesReason == AggregateFuture.ReleaseResourcesReason.OUTPUT_FUTURE_DONE) {
            this.task = null;
        }
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    protected void m() {
        CombinedFuture<V>.CombinedFutureInterruptibleTask<?> combinedFutureInterruptibleTask = this.task;
        if (combinedFutureInterruptibleTask != null) {
            combinedFutureInterruptibleTask.b();
        }
    }
}
