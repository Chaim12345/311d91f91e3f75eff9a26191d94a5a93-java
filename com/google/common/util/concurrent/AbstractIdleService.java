package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.Service;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class AbstractIdleService implements Service {
    private final Service delegate;
    private final Supplier<String> threadNameSupplier;

    /* loaded from: classes2.dex */
    private final class DelegateService extends AbstractService {
        private DelegateService() {
        }

        @Override // com.google.common.util.concurrent.AbstractService
        protected final void c() {
            MoreExecutors.d(AbstractIdleService.this.b(), AbstractIdleService.this.threadNameSupplier).execute(new Runnable() { // from class: com.google.common.util.concurrent.AbstractIdleService.DelegateService.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        AbstractIdleService.this.e();
                        DelegateService.this.f();
                    } catch (Throwable th) {
                        DelegateService.this.e(th);
                    }
                }
            });
        }

        @Override // com.google.common.util.concurrent.AbstractService
        protected final void d() {
            MoreExecutors.d(AbstractIdleService.this.b(), AbstractIdleService.this.threadNameSupplier).execute(new Runnable() { // from class: com.google.common.util.concurrent.AbstractIdleService.DelegateService.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        AbstractIdleService.this.d();
                        DelegateService.this.g();
                    } catch (Throwable th) {
                        DelegateService.this.e(th);
                    }
                }
            });
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public String toString() {
            return AbstractIdleService.this.toString();
        }
    }

    /* loaded from: classes2.dex */
    private final class ThreadNameSupplier implements Supplier<String> {
        private ThreadNameSupplier() {
        }

        @Override // com.google.common.base.Supplier
        public String get() {
            return AbstractIdleService.this.c() + " " + AbstractIdleService.this.state();
        }
    }

    @Override // com.google.common.util.concurrent.Service
    public final void addListener(Service.Listener listener, Executor executor) {
        this.delegate.addListener(listener, executor);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning() {
        this.delegate.awaitRunning();
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning(long j2, TimeUnit timeUnit) {
        this.delegate.awaitRunning(j2, timeUnit);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated() {
        this.delegate.awaitTerminated();
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated(long j2, TimeUnit timeUnit) {
        this.delegate.awaitTerminated(j2, timeUnit);
    }

    protected Executor b() {
        return new Executor() { // from class: com.google.common.util.concurrent.AbstractIdleService.1
            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                MoreExecutors.b((String) AbstractIdleService.this.threadNameSupplier.get(), runnable).start();
            }
        };
    }

    protected String c() {
        return getClass().getSimpleName();
    }

    protected abstract void d();

    protected abstract void e();

    @Override // com.google.common.util.concurrent.Service
    public final Throwable failureCause() {
        return this.delegate.failureCause();
    }

    @Override // com.google.common.util.concurrent.Service
    public final boolean isRunning() {
        return this.delegate.isRunning();
    }

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
    public final Service startAsync() {
        this.delegate.startAsync();
        return this;
    }

    @Override // com.google.common.util.concurrent.Service
    public final Service.State state() {
        return this.delegate.state();
    }

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
    public final Service stopAsync() {
        this.delegate.stopAsync();
        return this;
    }

    public String toString() {
        return c() + " [" + state() + "]";
    }
}
