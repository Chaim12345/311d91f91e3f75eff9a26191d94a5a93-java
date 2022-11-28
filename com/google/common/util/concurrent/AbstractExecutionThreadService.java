package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.Service;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class AbstractExecutionThreadService implements Service {
    private static final Logger logger = Logger.getLogger(AbstractExecutionThreadService.class.getName());
    private final Service delegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.common.util.concurrent.AbstractExecutionThreadService$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass1 extends AbstractService {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AbstractExecutionThreadService f9410a;

        @Override // com.google.common.util.concurrent.AbstractService
        protected final void c() {
            MoreExecutors.d(this.f9410a.b(), new Supplier<String>() { // from class: com.google.common.util.concurrent.AbstractExecutionThreadService.1.1
                @Override // com.google.common.base.Supplier
                public String get() {
                    return AnonymousClass1.this.f9410a.d();
                }
            }).execute(new Runnable() { // from class: com.google.common.util.concurrent.AbstractExecutionThreadService.1.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        AnonymousClass1.this.f9410a.f();
                        AnonymousClass1.this.f();
                        if (AnonymousClass1.this.isRunning()) {
                            AnonymousClass1.this.f9410a.c();
                        }
                        AnonymousClass1.this.f9410a.e();
                        AnonymousClass1.this.g();
                    } catch (Throwable th) {
                        AnonymousClass1.this.e(th);
                    }
                }
            });
        }

        @Override // com.google.common.util.concurrent.AbstractService
        protected void d() {
            this.f9410a.g();
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public String toString() {
            return this.f9410a.toString();
        }
    }

    static /* synthetic */ Logger a() {
        return logger;
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
        return new Executor() { // from class: com.google.common.util.concurrent.AbstractExecutionThreadService.2
            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                MoreExecutors.b(AbstractExecutionThreadService.this.d(), runnable).start();
            }
        };
    }

    protected abstract void c();

    protected String d() {
        return getClass().getSimpleName();
    }

    protected void e() {
    }

    protected void f() {
    }

    @Override // com.google.common.util.concurrent.Service
    public final Throwable failureCause() {
        return this.delegate.failureCause();
    }

    @Beta
    protected void g() {
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
        return d() + " [" + state() + "]";
    }
}
