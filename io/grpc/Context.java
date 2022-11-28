package io.grpc;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
@CheckReturnValue
/* loaded from: classes3.dex */
public class Context {
    private static final PersistentHashArrayMappedTrie<Key<?>, Object> EMPTY_ENTRIES;
    public static final Context ROOT;

    /* renamed from: d  reason: collision with root package name */
    static final Logger f10932d = Logger.getLogger(Context.class.getName());

    /* renamed from: a  reason: collision with root package name */
    final CancellableContext f10933a;

    /* renamed from: b  reason: collision with root package name */
    final PersistentHashArrayMappedTrie f10934b;

    /* renamed from: c  reason: collision with root package name */
    final int f10935c;
    private ArrayList<ExecutableListener> listeners;
    private CancellationListener parentListener;

    /* loaded from: classes3.dex */
    @interface CanIgnoreReturnValue {
    }

    /* loaded from: classes3.dex */
    public static final class CancellableContext extends Context implements Closeable {
        private Throwable cancellationCause;
        private boolean cancelled;
        private final Deadline deadline;
        private ScheduledFuture<?> pendingDeadline;
        private final Context uncancellableSurrogate;

        private CancellableContext(Context context) {
            super(context.f10934b);
            this.deadline = context.getDeadline();
            this.uncancellableSurrogate = new Context(this.f10934b);
        }

        private CancellableContext(Context context, Deadline deadline, ScheduledExecutorService scheduledExecutorService) {
            super(context.f10934b);
            Deadline deadline2 = context.getDeadline();
            if (deadline2 != null && deadline2.compareTo(deadline) <= 0) {
                deadline = deadline2;
            } else if (deadline.isExpired()) {
                cancel(new TimeoutException("context timed out"));
            } else {
                this.pendingDeadline = deadline.runOnExpiration(new Runnable() { // from class: io.grpc.Context.CancellableContext.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            CancellableContext.this.cancel(new TimeoutException("context timed out"));
                        } catch (Throwable th) {
                            Context.f10932d.log(Level.SEVERE, "Cancel threw an exception, which should not happen", th);
                        }
                    }
                }, scheduledExecutorService);
            }
            this.deadline = deadline;
            this.uncancellableSurrogate = new Context(this.f10934b);
        }

        @Override // io.grpc.Context
        boolean a() {
            return true;
        }

        @Override // io.grpc.Context
        public Context attach() {
            return this.uncancellableSurrogate.attach();
        }

        @CanIgnoreReturnValue
        public boolean cancel(Throwable th) {
            boolean z;
            synchronized (this) {
                z = true;
                if (this.cancelled) {
                    z = false;
                } else {
                    this.cancelled = true;
                    ScheduledFuture<?> scheduledFuture = this.pendingDeadline;
                    if (scheduledFuture != null) {
                        scheduledFuture.cancel(false);
                        this.pendingDeadline = null;
                    }
                    this.cancellationCause = th;
                }
            }
            if (z) {
                e();
            }
            return z;
        }

        @Override // io.grpc.Context
        public Throwable cancellationCause() {
            if (isCancelled()) {
                return this.cancellationCause;
            }
            return null;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            cancel(null);
        }

        @Override // io.grpc.Context
        public void detach(Context context) {
            this.uncancellableSurrogate.detach(context);
        }

        public void detachAndCancel(Context context, Throwable th) {
            try {
                detach(context);
            } finally {
                cancel(th);
            }
        }

        @Override // io.grpc.Context
        public Deadline getDeadline() {
            return this.deadline;
        }

        @Override // io.grpc.Context
        public boolean isCancelled() {
            synchronized (this) {
                if (this.cancelled) {
                    return true;
                }
                if (super.isCancelled()) {
                    cancel(super.cancellationCause());
                    return true;
                }
                return false;
            }
        }

        @Override // io.grpc.Context
        @Deprecated
        public boolean isCurrent() {
            return this.uncancellableSurrogate.isCurrent();
        }
    }

    /* loaded from: classes3.dex */
    public interface CancellationListener {
        void cancelled(Context context);
    }

    /* loaded from: classes3.dex */
    @interface CheckReturnValue {
    }

    /* loaded from: classes3.dex */
    private enum DirectExecutor implements Executor {
        INSTANCE;

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            runnable.run();
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Context.DirectExecutor";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public final class ExecutableListener implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final CancellationListener f10944a;
        private final Executor executor;

        ExecutableListener(Executor executor, CancellationListener cancellationListener) {
            this.executor = executor;
            this.f10944a = cancellationListener;
        }

        void a() {
            try {
                this.executor.execute(this);
            } catch (Throwable th) {
                Context.f10932d.log(Level.INFO, "Exception notifying context listener", th);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f10944a.cancelled(Context.this);
        }
    }

    /* loaded from: classes3.dex */
    public static final class Key<T> {
        private final T defaultValue;
        private final String name;

        Key(String str) {
            this(str, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        Key(String str, Object obj) {
            this.name = (String) Context.c(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
            this.defaultValue = obj;
        }

        public T get() {
            return get(Context.current());
        }

        public T get(Context context) {
            T t2 = (T) context.d(this);
            return t2 == null ? this.defaultValue : t2;
        }

        public String toString() {
            return this.name;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class LazyStorage {

        /* renamed from: a  reason: collision with root package name */
        static final Storage f10946a;

        static {
            AtomicReference atomicReference = new AtomicReference();
            f10946a = createStorage(atomicReference);
            Throwable th = (Throwable) atomicReference.get();
            if (th != null) {
                Context.f10932d.log(Level.FINE, "Storage override doesn't exist. Using default", th);
            }
        }

        private LazyStorage() {
        }

        private static Storage createStorage(AtomicReference<? super ClassNotFoundException> atomicReference) {
            try {
                return (Storage) Class.forName("io.grpc.override.ContextStorageOverride").asSubclass(Storage.class).getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (ClassNotFoundException e2) {
                atomicReference.set(e2);
                return new ThreadLocalContextStorage();
            } catch (Exception e3) {
                throw new RuntimeException("Storage override failed to initialize", e3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public final class ParentListener implements CancellationListener {
        private ParentListener() {
        }

        @Override // io.grpc.Context.CancellationListener
        public void cancelled(Context context) {
            Context context2 = Context.this;
            if (context2 instanceof CancellableContext) {
                ((CancellableContext) context2).cancel(context.cancellationCause());
            } else {
                context2.e();
            }
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Storage {
        @Deprecated
        public void attach(Context context) {
            throw new UnsupportedOperationException("Deprecated. Do not call.");
        }

        public abstract Context current();

        public abstract void detach(Context context, Context context2);

        public Context doAttach(Context context) {
            Context current = current();
            attach(context);
            return current;
        }
    }

    static {
        PersistentHashArrayMappedTrie<Key<?>, Object> persistentHashArrayMappedTrie = new PersistentHashArrayMappedTrie<>();
        EMPTY_ENTRIES = persistentHashArrayMappedTrie;
        ROOT = new Context((Context) null, persistentHashArrayMappedTrie);
    }

    private Context(Context context, PersistentHashArrayMappedTrie<Key<?>, Object> persistentHashArrayMappedTrie) {
        this.parentListener = new ParentListener();
        this.f10933a = b(context);
        this.f10934b = persistentHashArrayMappedTrie;
        int i2 = context == null ? 0 : context.f10935c + 1;
        this.f10935c = i2;
        validateGeneration(i2);
    }

    private Context(PersistentHashArrayMappedTrie<Key<?>, Object> persistentHashArrayMappedTrie, int i2) {
        this.parentListener = new ParentListener();
        this.f10933a = null;
        this.f10934b = persistentHashArrayMappedTrie;
        this.f10935c = i2;
        validateGeneration(i2);
    }

    static CancellableContext b(Context context) {
        if (context == null) {
            return null;
        }
        return context instanceof CancellableContext ? (CancellableContext) context : context.f10933a;
    }

    @CanIgnoreReturnValue
    static Object c(Object obj, Object obj2) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(String.valueOf(obj2));
    }

    public static Context current() {
        Context current = f().current();
        return current == null ? ROOT : current;
    }

    public static Executor currentContextExecutor(final Executor executor) {
        return new Executor() { // from class: io.grpc.Context.1CurrentContextExecutor
            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                executor.execute(Context.current().wrap(runnable));
            }
        };
    }

    static Storage f() {
        return LazyStorage.f10946a;
    }

    public static <T> Key<T> key(String str) {
        return new Key<>(str);
    }

    public static <T> Key<T> keyWithDefault(String str, T t2) {
        return new Key<>(str, t2);
    }

    private static void validateGeneration(int i2) {
        if (i2 == 1000) {
            f10932d.log(Level.SEVERE, "Context ancestry chain length is abnormally long. This suggests an error in application code. Length exceeded: 1000", (Throwable) new Exception());
        }
    }

    boolean a() {
        return this.f10933a != null;
    }

    public void addListener(CancellationListener cancellationListener, Executor executor) {
        c(cancellationListener, "cancellationListener");
        c(executor, "executor");
        if (a()) {
            ExecutableListener executableListener = new ExecutableListener(executor, cancellationListener);
            synchronized (this) {
                if (isCancelled()) {
                    executableListener.a();
                } else {
                    ArrayList<ExecutableListener> arrayList = this.listeners;
                    if (arrayList == null) {
                        ArrayList<ExecutableListener> arrayList2 = new ArrayList<>();
                        this.listeners = arrayList2;
                        arrayList2.add(executableListener);
                        CancellableContext cancellableContext = this.f10933a;
                        if (cancellableContext != null) {
                            cancellableContext.addListener(this.parentListener, DirectExecutor.INSTANCE);
                        }
                    } else {
                        arrayList.add(executableListener);
                    }
                }
            }
        }
    }

    public Context attach() {
        Context doAttach = f().doAttach(this);
        return doAttach == null ? ROOT : doAttach;
    }

    @CanIgnoreReturnValue
    public <V> V call(Callable<V> callable) {
        Context attach = attach();
        try {
            return callable.call();
        } finally {
            detach(attach);
        }
    }

    public Throwable cancellationCause() {
        CancellableContext cancellableContext = this.f10933a;
        if (cancellableContext == null) {
            return null;
        }
        return cancellableContext.cancellationCause();
    }

    Object d(Key key) {
        return this.f10934b.get(key);
    }

    public void detach(Context context) {
        c(context, "toAttach");
        f().detach(this, context);
    }

    void e() {
        if (a()) {
            synchronized (this) {
                ArrayList<ExecutableListener> arrayList = this.listeners;
                if (arrayList == null) {
                    return;
                }
                this.listeners = null;
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    if (!(arrayList.get(i2).f10944a instanceof ParentListener)) {
                        arrayList.get(i2).a();
                    }
                }
                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                    if (arrayList.get(i3).f10944a instanceof ParentListener) {
                        arrayList.get(i3).a();
                    }
                }
                CancellableContext cancellableContext = this.f10933a;
                if (cancellableContext != null) {
                    cancellableContext.removeListener(this.parentListener);
                }
            }
        }
    }

    public Executor fixedContextExecutor(final Executor executor) {
        return new Executor() { // from class: io.grpc.Context.1FixedContextExecutor
            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                executor.execute(Context.this.wrap(runnable));
            }
        };
    }

    public Context fork() {
        return new Context(this.f10934b, this.f10935c + 1);
    }

    public Deadline getDeadline() {
        CancellableContext cancellableContext = this.f10933a;
        if (cancellableContext == null) {
            return null;
        }
        return cancellableContext.getDeadline();
    }

    public boolean isCancelled() {
        CancellableContext cancellableContext = this.f10933a;
        if (cancellableContext == null) {
            return false;
        }
        return cancellableContext.isCancelled();
    }

    boolean isCurrent() {
        return current() == this;
    }

    public void removeListener(CancellationListener cancellationListener) {
        if (a()) {
            synchronized (this) {
                ArrayList<ExecutableListener> arrayList = this.listeners;
                if (arrayList != null) {
                    int size = arrayList.size() - 1;
                    while (true) {
                        if (size < 0) {
                            break;
                        } else if (this.listeners.get(size).f10944a == cancellationListener) {
                            this.listeners.remove(size);
                            break;
                        } else {
                            size--;
                        }
                    }
                    if (this.listeners.isEmpty()) {
                        CancellableContext cancellableContext = this.f10933a;
                        if (cancellableContext != null) {
                            cancellableContext.removeListener(this.parentListener);
                        }
                        this.listeners = null;
                    }
                }
            }
        }
    }

    public void run(Runnable runnable) {
        Context attach = attach();
        try {
            runnable.run();
        } finally {
            detach(attach);
        }
    }

    public CancellableContext withCancellation() {
        return new CancellableContext();
    }

    public CancellableContext withDeadline(Deadline deadline, ScheduledExecutorService scheduledExecutorService) {
        c(deadline, "deadline");
        c(scheduledExecutorService, "scheduler");
        return new CancellableContext(deadline, scheduledExecutorService);
    }

    public CancellableContext withDeadlineAfter(long j2, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        return withDeadline(Deadline.after(j2, timeUnit), scheduledExecutorService);
    }

    public <V> Context withValue(Key<V> key, V v) {
        return new Context(this, this.f10934b.put(key, v));
    }

    public <V1, V2> Context withValues(Key<V1> key, V1 v1, Key<V2> key2, V2 v2) {
        return new Context(this, this.f10934b.put(key, v1).put(key2, v2));
    }

    public <V1, V2, V3> Context withValues(Key<V1> key, V1 v1, Key<V2> key2, V2 v2, Key<V3> key3, V3 v3) {
        return new Context(this, this.f10934b.put(key, v1).put(key2, v2).put(key3, v3));
    }

    public <V1, V2, V3, V4> Context withValues(Key<V1> key, V1 v1, Key<V2> key2, V2 v2, Key<V3> key3, V3 v3, Key<V4> key4, V4 v4) {
        return new Context(this, this.f10934b.put(key, v1).put(key2, v2).put(key3, v3).put(key4, v4));
    }

    public Runnable wrap(final Runnable runnable) {
        return new Runnable() { // from class: io.grpc.Context.1
            @Override // java.lang.Runnable
            public void run() {
                Context attach = Context.this.attach();
                try {
                    runnable.run();
                } finally {
                    Context.this.detach(attach);
                }
            }
        };
    }

    public <C> Callable<C> wrap(final Callable<C> callable) {
        return new Callable<C>() { // from class: io.grpc.Context.2
            /* JADX WARN: Type inference failed for: r1v2, types: [C, java.lang.Object] */
            @Override // java.util.concurrent.Callable
            public C call() {
                Context attach = Context.this.attach();
                try {
                    return callable.call();
                } finally {
                    Context.this.detach(attach);
                }
            }
        };
    }
}
