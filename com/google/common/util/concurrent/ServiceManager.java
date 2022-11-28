package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Ordering;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.ListenerCallQueue;
import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.Service;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
@GwtIncompatible
/* loaded from: classes2.dex */
public final class ServiceManager implements ServiceManagerBridge {
    private final ImmutableList<Service> services;
    private final ServiceManagerState state;
    private static final Logger logger = Logger.getLogger(ServiceManager.class.getName());
    private static final ListenerCallQueue.Event<Listener> HEALTHY_EVENT = new ListenerCallQueue.Event<Listener>() { // from class: com.google.common.util.concurrent.ServiceManager.1
        @Override // com.google.common.util.concurrent.ListenerCallQueue.Event
        public void call(Listener listener) {
            listener.healthy();
        }

        public String toString() {
            return "healthy()";
        }
    };
    private static final ListenerCallQueue.Event<Listener> STOPPED_EVENT = new ListenerCallQueue.Event<Listener>() { // from class: com.google.common.util.concurrent.ServiceManager.2
        @Override // com.google.common.util.concurrent.ListenerCallQueue.Event
        public void call(Listener listener) {
            listener.stopped();
        }

        public String toString() {
            return "stopped()";
        }
    };

    /* loaded from: classes2.dex */
    private static final class EmptyServiceManagerWarning extends Throwable {
        private EmptyServiceManagerWarning() {
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Listener {
        public void failure(Service service) {
        }

        public void healthy() {
        }

        public void stopped() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class NoOpService extends AbstractService {
        private NoOpService() {
        }

        @Override // com.google.common.util.concurrent.AbstractService
        protected void c() {
            f();
        }

        @Override // com.google.common.util.concurrent.AbstractService
        protected void d() {
            g();
        }
    }

    /* loaded from: classes2.dex */
    private static final class ServiceListener extends Service.Listener {

        /* renamed from: a  reason: collision with root package name */
        final Service f9556a;

        /* renamed from: b  reason: collision with root package name */
        final WeakReference f9557b;

        ServiceListener(Service service, WeakReference weakReference) {
            this.f9556a = service;
            this.f9557b = weakReference;
        }

        @Override // com.google.common.util.concurrent.Service.Listener
        public void failed(Service.State state, Throwable th) {
            ServiceManagerState serviceManagerState = (ServiceManagerState) this.f9557b.get();
            if (serviceManagerState != null) {
                if (!(this.f9556a instanceof NoOpService)) {
                    Logger logger = ServiceManager.logger;
                    Level level = Level.SEVERE;
                    logger.log(level, "Service " + this.f9556a + " has failed in the " + state + " state.", th);
                }
                serviceManagerState.n(this.f9556a, state, Service.State.FAILED);
            }
        }

        @Override // com.google.common.util.concurrent.Service.Listener
        public void running() {
            ServiceManagerState serviceManagerState = (ServiceManagerState) this.f9557b.get();
            if (serviceManagerState != null) {
                serviceManagerState.n(this.f9556a, Service.State.STARTING, Service.State.RUNNING);
            }
        }

        @Override // com.google.common.util.concurrent.Service.Listener
        public void starting() {
            ServiceManagerState serviceManagerState = (ServiceManagerState) this.f9557b.get();
            if (serviceManagerState != null) {
                serviceManagerState.n(this.f9556a, Service.State.NEW, Service.State.STARTING);
                if (this.f9556a instanceof NoOpService) {
                    return;
                }
                ServiceManager.logger.log(Level.FINE, "Starting {0}.", this.f9556a);
            }
        }

        @Override // com.google.common.util.concurrent.Service.Listener
        public void stopping(Service.State state) {
            ServiceManagerState serviceManagerState = (ServiceManagerState) this.f9557b.get();
            if (serviceManagerState != null) {
                serviceManagerState.n(this.f9556a, state, Service.State.STOPPING);
            }
        }

        @Override // com.google.common.util.concurrent.Service.Listener
        public void terminated(Service.State state) {
            ServiceManagerState serviceManagerState = (ServiceManagerState) this.f9557b.get();
            if (serviceManagerState != null) {
                if (!(this.f9556a instanceof NoOpService)) {
                    ServiceManager.logger.log(Level.FINE, "Service {0} has terminated. Previous state was: {1}", new Object[]{this.f9556a, state});
                }
                serviceManagerState.n(this.f9556a, state, Service.State.TERMINATED);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class ServiceManagerState {

        /* renamed from: a  reason: collision with root package name */
        final Monitor f9558a = new Monitor();
        @GuardedBy("monitor")

        /* renamed from: b  reason: collision with root package name */
        final SetMultimap f9559b;
        @GuardedBy("monitor")

        /* renamed from: c  reason: collision with root package name */
        final Multiset f9560c;
        @GuardedBy("monitor")

        /* renamed from: d  reason: collision with root package name */
        final Map f9561d;
        @GuardedBy("monitor")

        /* renamed from: e  reason: collision with root package name */
        boolean f9562e;
        @GuardedBy("monitor")

        /* renamed from: f  reason: collision with root package name */
        boolean f9563f;

        /* renamed from: g  reason: collision with root package name */
        final int f9564g;

        /* renamed from: h  reason: collision with root package name */
        final Monitor.Guard f9565h;

        /* renamed from: i  reason: collision with root package name */
        final Monitor.Guard f9566i;

        /* renamed from: j  reason: collision with root package name */
        final ListenerCallQueue f9567j;

        /* loaded from: classes2.dex */
        final class AwaitHealthGuard extends Monitor.Guard {
            AwaitHealthGuard() {
                super(ServiceManagerState.this.f9558a);
            }

            @Override // com.google.common.util.concurrent.Monitor.Guard
            @GuardedBy("ServiceManagerState.this.monitor")
            public boolean isSatisfied() {
                int count = ServiceManagerState.this.f9560c.count(Service.State.RUNNING);
                ServiceManagerState serviceManagerState = ServiceManagerState.this;
                return count == serviceManagerState.f9564g || serviceManagerState.f9560c.contains(Service.State.STOPPING) || ServiceManagerState.this.f9560c.contains(Service.State.TERMINATED) || ServiceManagerState.this.f9560c.contains(Service.State.FAILED);
            }
        }

        /* loaded from: classes2.dex */
        final class StoppedGuard extends Monitor.Guard {
            StoppedGuard() {
                super(ServiceManagerState.this.f9558a);
            }

            @Override // com.google.common.util.concurrent.Monitor.Guard
            @GuardedBy("ServiceManagerState.this.monitor")
            public boolean isSatisfied() {
                return ServiceManagerState.this.f9560c.count(Service.State.TERMINATED) + ServiceManagerState.this.f9560c.count(Service.State.FAILED) == ServiceManagerState.this.f9564g;
            }
        }

        ServiceManagerState(ImmutableCollection immutableCollection) {
            SetMultimap build = MultimapBuilder.enumKeys(Service.State.class).linkedHashSetValues().build();
            this.f9559b = build;
            this.f9560c = build.keys();
            this.f9561d = Maps.newIdentityHashMap();
            this.f9565h = new AwaitHealthGuard();
            this.f9566i = new StoppedGuard();
            this.f9567j = new ListenerCallQueue();
            this.f9564g = immutableCollection.size();
            build.putAll(Service.State.NEW, immutableCollection);
        }

        void a(Listener listener, Executor executor) {
            this.f9567j.addListener(listener, executor);
        }

        void b() {
            this.f9558a.enterWhenUninterruptibly(this.f9565h);
            try {
                f();
            } finally {
                this.f9558a.leave();
            }
        }

        void c(long j2, TimeUnit timeUnit) {
            this.f9558a.enter();
            try {
                if (this.f9558a.waitForUninterruptibly(this.f9565h, j2, timeUnit)) {
                    f();
                    return;
                }
                throw new TimeoutException("Timeout waiting for the services to become healthy. The following services have not started: " + Multimaps.filterKeys(this.f9559b, Predicates.in(ImmutableSet.of(Service.State.NEW, Service.State.STARTING))));
            } finally {
                this.f9558a.leave();
            }
        }

        void d() {
            this.f9558a.enterWhenUninterruptibly(this.f9566i);
            this.f9558a.leave();
        }

        void e(long j2, TimeUnit timeUnit) {
            this.f9558a.enter();
            try {
                if (this.f9558a.waitForUninterruptibly(this.f9566i, j2, timeUnit)) {
                    return;
                }
                throw new TimeoutException("Timeout waiting for the services to stop. The following services have not stopped: " + Multimaps.filterKeys(this.f9559b, Predicates.not(Predicates.in(EnumSet.of(Service.State.TERMINATED, Service.State.FAILED)))));
            } finally {
                this.f9558a.leave();
            }
        }

        @GuardedBy("monitor")
        void f() {
            Multiset multiset = this.f9560c;
            Service.State state = Service.State.RUNNING;
            if (multiset.count(state) == this.f9564g) {
                return;
            }
            throw new IllegalStateException("Expected to be healthy after starting. The following services are not running: " + Multimaps.filterKeys(this.f9559b, Predicates.not(Predicates.equalTo(state))));
        }

        void g() {
            Preconditions.checkState(!this.f9558a.isOccupiedByCurrentThread(), "It is incorrect to execute listeners with the monitor held.");
            this.f9567j.dispatch();
        }

        void h(final Service service) {
            this.f9567j.enqueue(new ListenerCallQueue.Event<Listener>(this) { // from class: com.google.common.util.concurrent.ServiceManager.ServiceManagerState.2
                @Override // com.google.common.util.concurrent.ListenerCallQueue.Event
                public void call(Listener listener) {
                    listener.failure(service);
                }

                public String toString() {
                    return "failed({service=" + service + "})";
                }
            });
        }

        void i() {
            this.f9567j.enqueue(ServiceManager.HEALTHY_EVENT);
        }

        void j() {
            this.f9567j.enqueue(ServiceManager.STOPPED_EVENT);
        }

        void k() {
            this.f9558a.enter();
            try {
                if (!this.f9563f) {
                    this.f9562e = true;
                    return;
                }
                ArrayList newArrayList = Lists.newArrayList();
                UnmodifiableIterator it = l().values().iterator();
                while (it.hasNext()) {
                    Service service = (Service) it.next();
                    if (service.state() != Service.State.NEW) {
                        newArrayList.add(service);
                    }
                }
                throw new IllegalArgumentException("Services started transitioning asynchronously before the ServiceManager was constructed: " + newArrayList);
            } finally {
                this.f9558a.leave();
            }
        }

        ImmutableSetMultimap l() {
            ImmutableSetMultimap.Builder builder = ImmutableSetMultimap.builder();
            this.f9558a.enter();
            try {
                for (Map.Entry entry : this.f9559b.entries()) {
                    if (!(entry.getValue() instanceof NoOpService)) {
                        builder.put(entry);
                    }
                }
                this.f9558a.leave();
                return builder.build();
            } catch (Throwable th) {
                this.f9558a.leave();
                throw th;
            }
        }

        ImmutableMap m() {
            this.f9558a.enter();
            try {
                ArrayList newArrayListWithCapacity = Lists.newArrayListWithCapacity(this.f9561d.size());
                for (Map.Entry entry : this.f9561d.entrySet()) {
                    Service service = (Service) entry.getKey();
                    Stopwatch stopwatch = (Stopwatch) entry.getValue();
                    if (!stopwatch.isRunning() && !(service instanceof NoOpService)) {
                        newArrayListWithCapacity.add(Maps.immutableEntry(service, Long.valueOf(stopwatch.elapsed(TimeUnit.MILLISECONDS))));
                    }
                }
                this.f9558a.leave();
                Collections.sort(newArrayListWithCapacity, Ordering.natural().onResultOf(new Function<Map.Entry<Service, Long>, Long>(this) { // from class: com.google.common.util.concurrent.ServiceManager.ServiceManagerState.1
                    @Override // com.google.common.base.Function
                    public Long apply(Map.Entry<Service, Long> entry2) {
                        return entry2.getValue();
                    }
                }));
                return ImmutableMap.copyOf(newArrayListWithCapacity);
            } catch (Throwable th) {
                this.f9558a.leave();
                throw th;
            }
        }

        void n(Service service, Service.State state, Service.State state2) {
            Preconditions.checkNotNull(service);
            Preconditions.checkArgument(state != state2);
            this.f9558a.enter();
            try {
                this.f9563f = true;
                if (this.f9562e) {
                    Preconditions.checkState(this.f9559b.remove(state, service), "Service %s not at the expected location in the state map %s", service, state);
                    Preconditions.checkState(this.f9559b.put(state2, service), "Service %s in the state map unexpectedly at %s", service, state2);
                    Stopwatch stopwatch = (Stopwatch) this.f9561d.get(service);
                    if (stopwatch == null) {
                        stopwatch = Stopwatch.createStarted();
                        this.f9561d.put(service, stopwatch);
                    }
                    Service.State state3 = Service.State.RUNNING;
                    if (state2.compareTo(state3) >= 0 && stopwatch.isRunning()) {
                        stopwatch.stop();
                        if (!(service instanceof NoOpService)) {
                            ServiceManager.logger.log(Level.FINE, "Started {0} in {1}.", new Object[]{service, stopwatch});
                        }
                    }
                    Service.State state4 = Service.State.FAILED;
                    if (state2 == state4) {
                        h(service);
                    }
                    if (this.f9560c.count(state3) == this.f9564g) {
                        i();
                    } else if (this.f9560c.count(Service.State.TERMINATED) + this.f9560c.count(state4) == this.f9564g) {
                        j();
                    }
                }
            } finally {
                this.f9558a.leave();
                g();
            }
        }

        void o(Service service) {
            this.f9558a.enter();
            try {
                if (((Stopwatch) this.f9561d.get(service)) == null) {
                    this.f9561d.put(service, Stopwatch.createStarted());
                }
            } finally {
                this.f9558a.leave();
            }
        }
    }

    public ServiceManager(Iterable<? extends Service> iterable) {
        ImmutableList<Service> copyOf = ImmutableList.copyOf(iterable);
        if (copyOf.isEmpty()) {
            logger.log(Level.WARNING, "ServiceManager configured with no services.  Is your application configured properly?", (Throwable) new EmptyServiceManagerWarning());
            copyOf = ImmutableList.of(new NoOpService());
        }
        ServiceManagerState serviceManagerState = new ServiceManagerState(copyOf);
        this.state = serviceManagerState;
        this.services = copyOf;
        WeakReference weakReference = new WeakReference(serviceManagerState);
        UnmodifiableIterator<Service> it = copyOf.iterator();
        while (it.hasNext()) {
            Service next = it.next();
            next.addListener(new ServiceListener(next, weakReference), MoreExecutors.directExecutor());
            Preconditions.checkArgument(next.state() == Service.State.NEW, "Can only manage NEW services, %s", next);
        }
        this.state.k();
    }

    @Beta
    @Deprecated
    public void addListener(Listener listener) {
        this.state.a(listener, MoreExecutors.directExecutor());
    }

    public void addListener(Listener listener, Executor executor) {
        this.state.a(listener, executor);
    }

    public void awaitHealthy() {
        this.state.b();
    }

    public void awaitHealthy(long j2, TimeUnit timeUnit) {
        this.state.c(j2, timeUnit);
    }

    public void awaitStopped() {
        this.state.d();
    }

    public void awaitStopped(long j2, TimeUnit timeUnit) {
        this.state.e(j2, timeUnit);
    }

    public boolean isHealthy() {
        UnmodifiableIterator<Service> it = this.services.iterator();
        while (it.hasNext()) {
            if (!it.next().isRunning()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.common.util.concurrent.ServiceManagerBridge
    public ImmutableSetMultimap<Service.State, Service> servicesByState() {
        return this.state.l();
    }

    @CanIgnoreReturnValue
    public ServiceManager startAsync() {
        UnmodifiableIterator<Service> it = this.services.iterator();
        while (it.hasNext()) {
            Service next = it.next();
            Service.State state = next.state();
            Preconditions.checkState(state == Service.State.NEW, "Service %s is %s, cannot start it.", next, state);
        }
        UnmodifiableIterator<Service> it2 = this.services.iterator();
        while (it2.hasNext()) {
            Service next2 = it2.next();
            try {
                this.state.o(next2);
                next2.startAsync();
            } catch (IllegalStateException e2) {
                Logger logger2 = logger;
                Level level = Level.WARNING;
                logger2.log(level, "Unable to start Service " + next2, (Throwable) e2);
            }
        }
        return this;
    }

    public ImmutableMap<Service, Long> startupTimes() {
        return this.state.m();
    }

    @CanIgnoreReturnValue
    public ServiceManager stopAsync() {
        UnmodifiableIterator<Service> it = this.services.iterator();
        while (it.hasNext()) {
            it.next().stopAsync();
        }
        return this;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Class<?>) ServiceManager.class).add("services", Collections2.filter(this.services, Predicates.not(Predicates.instanceOf(NoOpService.class)))).toString();
    }
}
