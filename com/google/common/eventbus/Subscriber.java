package com.google.common.eventbus;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.j2objc.annotations.Weak;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class Subscriber {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    final Object f9123a;
    @Weak
    private EventBus bus;
    private final Executor executor;
    private final Method method;

    @VisibleForTesting
    /* loaded from: classes2.dex */
    static final class SynchronizedSubscriber extends Subscriber {
        private SynchronizedSubscriber(EventBus eventBus, Object obj, Method method) {
            super(eventBus, obj, method);
        }

        @Override // com.google.common.eventbus.Subscriber
        void e(Object obj) {
            synchronized (this) {
                super.e(obj);
            }
        }
    }

    private Subscriber(EventBus eventBus, Object obj, Method method) {
        this.bus = eventBus;
        this.f9123a = Preconditions.checkNotNull(obj);
        this.method = method;
        method.setAccessible(true);
        this.executor = eventBus.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Subscriber c(EventBus eventBus, Object obj, Method method) {
        return isDeclaredThreadSafe(method) ? new Subscriber(eventBus, obj, method) : new SynchronizedSubscriber(eventBus, obj, method);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SubscriberExceptionContext context(Object obj) {
        return new SubscriberExceptionContext(this.bus, obj, this.f9123a, this.method);
    }

    private static boolean isDeclaredThreadSafe(Method method) {
        return method.getAnnotation(AllowConcurrentEvents.class) != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void d(final Object obj) {
        this.executor.execute(new Runnable() { // from class: com.google.common.eventbus.Subscriber.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Subscriber.this.e(obj);
                } catch (InvocationTargetException e2) {
                    Subscriber.this.bus.b(e2.getCause(), Subscriber.this.context(obj));
                }
            }
        });
    }

    @VisibleForTesting
    void e(Object obj) {
        try {
            this.method.invoke(this.f9123a, Preconditions.checkNotNull(obj));
        } catch (IllegalAccessException e2) {
            throw new Error("Method became inaccessible: " + obj, e2);
        } catch (IllegalArgumentException e3) {
            throw new Error("Method rejected target/argument: " + obj, e3);
        } catch (InvocationTargetException e4) {
            if (!(e4.getCause() instanceof Error)) {
                throw e4;
            }
            throw ((Error) e4.getCause());
        }
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (obj instanceof Subscriber) {
            Subscriber subscriber = (Subscriber) obj;
            return this.f9123a == subscriber.f9123a && this.method.equals(subscriber.method);
        }
        return false;
    }

    public final int hashCode() {
        return ((this.method.hashCode() + 31) * 31) + System.identityHashCode(this.f9123a);
    }
}
