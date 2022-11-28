package androidx.concurrent.futures;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public abstract class AbstractResolvableFuture<V> implements ListenableFuture<V> {
    private static final Object NULL;
    private static final long SPIN_THRESHOLD_NANOS = 1000;

    /* renamed from: e  reason: collision with root package name */
    static final AtomicHelper f1618e;
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    volatile Object f1619a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    volatile Listener f1620b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    volatile Waiter f1621c;

    /* renamed from: d  reason: collision with root package name */
    static final boolean f1617d = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    private static final Logger log = Logger.getLogger(AbstractResolvableFuture.class.getName());

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class AtomicHelper {
        private AtomicHelper() {
        }

        abstract boolean a(AbstractResolvableFuture abstractResolvableFuture, Listener listener, Listener listener2);

        abstract boolean b(AbstractResolvableFuture abstractResolvableFuture, Object obj, Object obj2);

        abstract boolean c(AbstractResolvableFuture abstractResolvableFuture, Waiter waiter, Waiter waiter2);

        abstract void d(Waiter waiter, Waiter waiter2);

        abstract void e(Waiter waiter, Thread thread);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Cancellation {

        /* renamed from: c  reason: collision with root package name */
        static final Cancellation f1622c;

        /* renamed from: d  reason: collision with root package name */
        static final Cancellation f1623d;

        /* renamed from: a  reason: collision with root package name */
        final boolean f1624a;
        @Nullable

        /* renamed from: b  reason: collision with root package name */
        final Throwable f1625b;

        static {
            if (AbstractResolvableFuture.f1617d) {
                f1623d = null;
                f1622c = null;
                return;
            }
            f1623d = new Cancellation(false, null);
            f1622c = new Cancellation(true, null);
        }

        Cancellation(boolean z, @Nullable Throwable th) {
            this.f1624a = z;
            this.f1625b = th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Failure {

        /* renamed from: b  reason: collision with root package name */
        static final Failure f1626b = new Failure(new Throwable("Failure occurred while trying to finish a future.") { // from class: androidx.concurrent.futures.AbstractResolvableFuture.Failure.1
            @Override // java.lang.Throwable
            public synchronized Throwable fillInStackTrace() {
                return this;
            }
        });

        /* renamed from: a  reason: collision with root package name */
        final Throwable f1627a;

        Failure(Throwable th) {
            this.f1627a = (Throwable) AbstractResolvableFuture.b(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Listener {

        /* renamed from: d  reason: collision with root package name */
        static final Listener f1628d = new Listener(null, null);

        /* renamed from: a  reason: collision with root package name */
        final Runnable f1629a;

        /* renamed from: b  reason: collision with root package name */
        final Executor f1630b;
        @Nullable

        /* renamed from: c  reason: collision with root package name */
        Listener f1631c;

        Listener(Runnable runnable, Executor executor) {
            this.f1629a = runnable;
            this.f1630b = executor;
        }
    }

    /* loaded from: classes.dex */
    private static final class SafeAtomicHelper extends AtomicHelper {

        /* renamed from: a  reason: collision with root package name */
        final AtomicReferenceFieldUpdater f1632a;

        /* renamed from: b  reason: collision with root package name */
        final AtomicReferenceFieldUpdater f1633b;

        /* renamed from: c  reason: collision with root package name */
        final AtomicReferenceFieldUpdater f1634c;

        /* renamed from: d  reason: collision with root package name */
        final AtomicReferenceFieldUpdater f1635d;

        /* renamed from: e  reason: collision with root package name */
        final AtomicReferenceFieldUpdater f1636e;

        SafeAtomicHelper(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
            super();
            this.f1632a = atomicReferenceFieldUpdater;
            this.f1633b = atomicReferenceFieldUpdater2;
            this.f1634c = atomicReferenceFieldUpdater3;
            this.f1635d = atomicReferenceFieldUpdater4;
            this.f1636e = atomicReferenceFieldUpdater5;
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        boolean a(AbstractResolvableFuture abstractResolvableFuture, Listener listener, Listener listener2) {
            return this.f1635d.compareAndSet(abstractResolvableFuture, listener, listener2);
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        boolean b(AbstractResolvableFuture abstractResolvableFuture, Object obj, Object obj2) {
            return this.f1636e.compareAndSet(abstractResolvableFuture, obj, obj2);
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        boolean c(AbstractResolvableFuture abstractResolvableFuture, Waiter waiter, Waiter waiter2) {
            return this.f1634c.compareAndSet(abstractResolvableFuture, waiter, waiter2);
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        void d(Waiter waiter, Waiter waiter2) {
            this.f1633b.lazySet(waiter, waiter2);
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        void e(Waiter waiter, Thread thread) {
            this.f1632a.lazySet(waiter, thread);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class SetFuture<V> implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final AbstractResolvableFuture f1637a;

        /* renamed from: b  reason: collision with root package name */
        final ListenableFuture f1638b;

        SetFuture(AbstractResolvableFuture abstractResolvableFuture, ListenableFuture listenableFuture) {
            this.f1637a = abstractResolvableFuture;
            this.f1638b = listenableFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f1637a.f1619a != this) {
                return;
            }
            if (AbstractResolvableFuture.f1618e.b(this.f1637a, this, AbstractResolvableFuture.d(this.f1638b))) {
                AbstractResolvableFuture.c(this.f1637a);
            }
        }
    }

    /* loaded from: classes.dex */
    private static final class SynchronizedHelper extends AtomicHelper {
        SynchronizedHelper() {
            super();
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        boolean a(AbstractResolvableFuture abstractResolvableFuture, Listener listener, Listener listener2) {
            synchronized (abstractResolvableFuture) {
                if (abstractResolvableFuture.f1620b == listener) {
                    abstractResolvableFuture.f1620b = listener2;
                    return true;
                }
                return false;
            }
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        boolean b(AbstractResolvableFuture abstractResolvableFuture, Object obj, Object obj2) {
            synchronized (abstractResolvableFuture) {
                if (abstractResolvableFuture.f1619a == obj) {
                    abstractResolvableFuture.f1619a = obj2;
                    return true;
                }
                return false;
            }
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        boolean c(AbstractResolvableFuture abstractResolvableFuture, Waiter waiter, Waiter waiter2) {
            synchronized (abstractResolvableFuture) {
                if (abstractResolvableFuture.f1621c == waiter) {
                    abstractResolvableFuture.f1621c = waiter2;
                    return true;
                }
                return false;
            }
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        void d(Waiter waiter, Waiter waiter2) {
            waiter.f1641b = waiter2;
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        void e(Waiter waiter, Thread thread) {
            waiter.f1640a = thread;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Waiter {

        /* renamed from: c  reason: collision with root package name */
        static final Waiter f1639c = new Waiter(false);
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        volatile Thread f1640a;
        @Nullable

        /* renamed from: b  reason: collision with root package name */
        volatile Waiter f1641b;

        Waiter() {
            AbstractResolvableFuture.f1618e.e(this, Thread.currentThread());
        }

        Waiter(boolean z) {
        }

        void a(Waiter waiter) {
            AbstractResolvableFuture.f1618e.d(this, waiter);
        }

        void b() {
            Thread thread = this.f1640a;
            if (thread != null) {
                this.f1640a = null;
                LockSupport.unpark(thread);
            }
        }
    }

    static {
        AtomicHelper synchronizedHelper;
        try {
            synchronizedHelper = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(Waiter.class, Thread.class, "a"), AtomicReferenceFieldUpdater.newUpdater(Waiter.class, Waiter.class, "b"), AtomicReferenceFieldUpdater.newUpdater(AbstractResolvableFuture.class, Waiter.class, "c"), AtomicReferenceFieldUpdater.newUpdater(AbstractResolvableFuture.class, Listener.class, "b"), AtomicReferenceFieldUpdater.newUpdater(AbstractResolvableFuture.class, Object.class, "a"));
            th = null;
        } catch (Throwable th) {
            th = th;
            synchronizedHelper = new SynchronizedHelper();
        }
        f1618e = synchronizedHelper;
        if (th != null) {
            log.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
        NULL = new Object();
    }

    private void addDoneString(StringBuilder sb) {
        String str = "]";
        try {
            Object uninterruptibly = getUninterruptibly(this);
            sb.append("SUCCESS, result=[");
            sb.append(userObjectToString(uninterruptibly));
            sb.append("]");
        } catch (CancellationException unused) {
            str = "CANCELLED";
            sb.append(str);
        } catch (RuntimeException e2) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e2.getClass());
            str = " thrown from get()]";
            sb.append(str);
        } catch (ExecutionException e3) {
            sb.append("FAILURE, cause=[");
            sb.append(e3.getCause());
            sb.append(str);
        }
    }

    @NonNull
    static Object b(@Nullable Object obj) {
        Objects.requireNonNull(obj);
        return obj;
    }

    static void c(AbstractResolvableFuture abstractResolvableFuture) {
        Listener listener = null;
        while (true) {
            abstractResolvableFuture.releaseWaiters();
            abstractResolvableFuture.a();
            Listener clearListeners = abstractResolvableFuture.clearListeners(listener);
            while (clearListeners != null) {
                listener = clearListeners.f1631c;
                Runnable runnable = clearListeners.f1629a;
                if (runnable instanceof SetFuture) {
                    SetFuture setFuture = (SetFuture) runnable;
                    abstractResolvableFuture = setFuture.f1637a;
                    if (abstractResolvableFuture.f1619a == setFuture) {
                        if (f1618e.b(abstractResolvableFuture, setFuture, d(setFuture.f1638b))) {
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    executeListener(runnable, clearListeners.f1630b);
                }
                clearListeners = listener;
            }
            return;
        }
    }

    private static CancellationException cancellationExceptionWithCause(@Nullable String str, @Nullable Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }

    private Listener clearListeners(Listener listener) {
        Listener listener2;
        do {
            listener2 = this.f1620b;
        } while (!f1618e.a(this, listener2, Listener.f1628d));
        Listener listener3 = listener;
        Listener listener4 = listener2;
        while (listener4 != null) {
            Listener listener5 = listener4.f1631c;
            listener4.f1631c = listener3;
            listener3 = listener4;
            listener4 = listener5;
        }
        return listener3;
    }

    static Object d(ListenableFuture listenableFuture) {
        if (listenableFuture instanceof AbstractResolvableFuture) {
            Object obj = ((AbstractResolvableFuture) listenableFuture).f1619a;
            if (obj instanceof Cancellation) {
                Cancellation cancellation = (Cancellation) obj;
                return cancellation.f1624a ? cancellation.f1625b != null ? new Cancellation(false, cancellation.f1625b) : Cancellation.f1623d : obj;
            }
            return obj;
        }
        boolean isCancelled = listenableFuture.isCancelled();
        if ((!f1617d) && isCancelled) {
            return Cancellation.f1623d;
        }
        try {
            Object uninterruptibly = getUninterruptibly(listenableFuture);
            return uninterruptibly == null ? NULL : uninterruptibly;
        } catch (CancellationException e2) {
            if (isCancelled) {
                return new Cancellation(false, e2);
            }
            return new Failure(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: " + listenableFuture, e2));
        } catch (ExecutionException e3) {
            return new Failure(e3.getCause());
        } catch (Throwable th) {
            return new Failure(th);
        }
    }

    private static void executeListener(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e2) {
            Logger logger = log;
            Level level = Level.SEVERE;
            logger.log(level, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private V getDoneValue(Object obj) {
        if (obj instanceof Cancellation) {
            throw cancellationExceptionWithCause("Task was cancelled.", ((Cancellation) obj).f1625b);
        }
        if (obj instanceof Failure) {
            throw new ExecutionException(((Failure) obj).f1627a);
        }
        if (obj == NULL) {
            return null;
        }
        return obj;
    }

    private static <V> V getUninterruptibly(Future<V> future) {
        V v;
        boolean z = false;
        while (true) {
            try {
                v = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return v;
    }

    private void releaseWaiters() {
        Waiter waiter;
        do {
            waiter = this.f1621c;
        } while (!f1618e.c(this, waiter, Waiter.f1639c));
        while (waiter != null) {
            waiter.b();
            waiter = waiter.f1641b;
        }
    }

    private void removeWaiter(Waiter waiter) {
        waiter.f1640a = null;
        while (true) {
            Waiter waiter2 = this.f1621c;
            if (waiter2 == Waiter.f1639c) {
                return;
            }
            Waiter waiter3 = null;
            while (waiter2 != null) {
                Waiter waiter4 = waiter2.f1641b;
                if (waiter2.f1640a != null) {
                    waiter3 = waiter2;
                } else if (waiter3 != null) {
                    waiter3.f1641b = waiter4;
                    if (waiter3.f1640a == null) {
                        break;
                    }
                } else if (!f1618e.c(this, waiter2, waiter4)) {
                    break;
                }
                waiter2 = waiter4;
            }
            return;
        }
    }

    private String userObjectToString(Object obj) {
        return obj == this ? "this future" : String.valueOf(obj);
    }

    protected void a() {
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable runnable, Executor executor) {
        b(runnable);
        b(executor);
        Listener listener = this.f1620b;
        if (listener == Listener.f1628d) {
            executeListener(runnable, executor);
        }
        Listener listener2 = new Listener(runnable, executor);
        do {
            listener2.f1631c = listener;
            if (f1618e.a(this, listener, listener2)) {
                return;
            }
            listener = this.f1620b;
        } while (listener != Listener.f1628d);
        executeListener(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        Object obj = this.f1619a;
        if (!(obj == null) && !(obj instanceof SetFuture)) {
            return false;
        }
        Cancellation cancellation = f1617d ? new Cancellation(z, new CancellationException("Future.cancel() was called.")) : z ? Cancellation.f1622c : Cancellation.f1623d;
        AbstractResolvableFuture<V> abstractResolvableFuture = this;
        boolean z2 = false;
        while (true) {
            if (f1618e.b(abstractResolvableFuture, obj, cancellation)) {
                if (z) {
                    abstractResolvableFuture.e();
                }
                c(abstractResolvableFuture);
                if (!(obj instanceof SetFuture)) {
                    return true;
                }
                ListenableFuture listenableFuture = ((SetFuture) obj).f1638b;
                if (!(listenableFuture instanceof AbstractResolvableFuture)) {
                    listenableFuture.cancel(z);
                    return true;
                }
                abstractResolvableFuture = (AbstractResolvableFuture) listenableFuture;
                obj = abstractResolvableFuture.f1619a;
                if (!(obj == null) && !(obj instanceof SetFuture)) {
                    return true;
                }
                z2 = true;
            } else {
                obj = abstractResolvableFuture.f1619a;
                if (!(obj instanceof SetFuture)) {
                    return z2;
                }
            }
        }
    }

    protected void e() {
    }

    @Nullable
    protected String f() {
        Object obj = this.f1619a;
        if (obj instanceof SetFuture) {
            return "setFuture=[" + userObjectToString(((SetFuture) obj).f1638b) + "]";
        } else if (this instanceof ScheduledFuture) {
            return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
        } else {
            return null;
        }
    }

    @Override // java.util.concurrent.Future
    public final V get() {
        Object obj;
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj2 = this.f1619a;
        if ((obj2 != null) && (!(obj2 instanceof SetFuture))) {
            return getDoneValue(obj2);
        }
        Waiter waiter = this.f1621c;
        if (waiter != Waiter.f1639c) {
            Waiter waiter2 = new Waiter();
            do {
                waiter2.a(waiter);
                if (f1618e.c(this, waiter, waiter2)) {
                    do {
                        LockSupport.park(this);
                        if (Thread.interrupted()) {
                            removeWaiter(waiter2);
                            throw new InterruptedException();
                        }
                        obj = this.f1619a;
                    } while (!((obj != null) & (!(obj instanceof SetFuture))));
                    return getDoneValue(obj);
                }
                waiter = this.f1621c;
            } while (waiter != Waiter.f1639c);
            return getDoneValue(this.f1619a);
        }
        return getDoneValue(this.f1619a);
    }

    @Override // java.util.concurrent.Future
    public final V get(long j2, TimeUnit timeUnit) {
        Locale locale;
        long nanos = timeUnit.toNanos(j2);
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj = this.f1619a;
        if ((obj != null) && (!(obj instanceof SetFuture))) {
            return getDoneValue(obj);
        }
        long nanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
        if (nanos >= SPIN_THRESHOLD_NANOS) {
            Waiter waiter = this.f1621c;
            if (waiter != Waiter.f1639c) {
                Waiter waiter2 = new Waiter();
                do {
                    waiter2.a(waiter);
                    if (f1618e.c(this, waiter, waiter2)) {
                        do {
                            LockSupport.parkNanos(this, nanos);
                            if (Thread.interrupted()) {
                                removeWaiter(waiter2);
                                throw new InterruptedException();
                            }
                            Object obj2 = this.f1619a;
                            if ((obj2 != null) && (!(obj2 instanceof SetFuture))) {
                                return getDoneValue(obj2);
                            }
                            nanos = nanoTime - System.nanoTime();
                        } while (nanos >= SPIN_THRESHOLD_NANOS);
                        removeWaiter(waiter2);
                    } else {
                        waiter = this.f1621c;
                    }
                } while (waiter != Waiter.f1639c);
                return getDoneValue(this.f1619a);
            }
            return getDoneValue(this.f1619a);
        }
        while (nanos > 0) {
            Object obj3 = this.f1619a;
            if ((obj3 != null) && (!(obj3 instanceof SetFuture))) {
                return getDoneValue(obj3);
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            nanos = nanoTime - System.nanoTime();
        }
        String abstractResolvableFuture = toString();
        String lowerCase = timeUnit.toString().toLowerCase(Locale.ROOT);
        String str = "Waited " + j2 + " " + timeUnit.toString().toLowerCase(locale);
        if (nanos + SPIN_THRESHOLD_NANOS < 0) {
            String str2 = str + " (plus ";
            long j3 = -nanos;
            long convert = timeUnit.convert(j3, TimeUnit.NANOSECONDS);
            long nanos2 = j3 - timeUnit.toNanos(convert);
            int i2 = (convert > 0L ? 1 : (convert == 0L ? 0 : -1));
            boolean z = i2 == 0 || nanos2 > SPIN_THRESHOLD_NANOS;
            if (i2 > 0) {
                String str3 = str2 + convert + " " + lowerCase;
                if (z) {
                    str3 = str3 + ",";
                }
                str2 = str3 + " ";
            }
            if (z) {
                str2 = str2 + nanos2 + " nanoseconds ";
            }
            str = str2 + "delay)";
        }
        if (isDone()) {
            throw new TimeoutException(str + " but future completed as timeout expired");
        }
        throw new TimeoutException(str + " for " + abstractResolvableFuture);
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.f1619a instanceof Cancellation;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        Object obj = this.f1619a;
        return (!(obj instanceof SetFuture)) & (obj != null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean set(@Nullable Object obj) {
        if (obj == null) {
            obj = NULL;
        }
        if (f1618e.b(this, null, obj)) {
            c(this);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean setException(Throwable th) {
        if (f1618e.b(this, null, new Failure((Throwable) b(th)))) {
            c(this);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean setFuture(ListenableFuture listenableFuture) {
        Failure failure;
        b(listenableFuture);
        Object obj = this.f1619a;
        if (obj == null) {
            if (listenableFuture.isDone()) {
                if (f1618e.b(this, null, d(listenableFuture))) {
                    c(this);
                    return true;
                }
                return false;
            }
            SetFuture setFuture = new SetFuture(this, listenableFuture);
            if (f1618e.b(this, null, setFuture)) {
                try {
                    listenableFuture.addListener(setFuture, DirectExecutor.INSTANCE);
                } catch (Throwable th) {
                    try {
                        failure = new Failure(th);
                    } catch (Throwable unused) {
                        failure = Failure.f1626b;
                    }
                    f1618e.b(this, setFuture, failure);
                }
                return true;
            }
            obj = this.f1619a;
        }
        if (obj instanceof Cancellation) {
            listenableFuture.cancel(((Cancellation) obj).f1624a);
        }
        return false;
    }

    public String toString() {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (!isCancelled()) {
            if (!isDone()) {
                try {
                    str = f();
                } catch (RuntimeException e2) {
                    str = "Exception thrown from implementation: " + e2.getClass();
                }
                if (str != null && !str.isEmpty()) {
                    sb.append("PENDING, info=[");
                    sb.append(str);
                    sb.append("]");
                    sb.append("]");
                    return sb.toString();
                }
                str2 = isDone() ? "PENDING" : "PENDING";
            }
            addDoneString(sb);
            sb.append("]");
            return sb.toString();
        }
        str2 = "CANCELLED";
        sb.append(str2);
        sb.append("]");
        return sb.toString();
    }
}
