package org.greenrobot.eventbus;

import android.os.Looper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.greenrobot.eventbus.Logger;
import org.greenrobot.eventbus.MainThreadSupport;
import org.greenrobot.eventbus.android.AndroidLogger;
import org.greenrobot.eventbus.meta.SubscriberInfoIndex;
/* loaded from: classes4.dex */
public class EventBusBuilder {
    private static final ExecutorService DEFAULT_EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    /* renamed from: e  reason: collision with root package name */
    boolean f15164e;

    /* renamed from: g  reason: collision with root package name */
    boolean f15166g;

    /* renamed from: h  reason: collision with root package name */
    boolean f15167h;

    /* renamed from: j  reason: collision with root package name */
    List f15169j;

    /* renamed from: k  reason: collision with root package name */
    List f15170k;

    /* renamed from: l  reason: collision with root package name */
    Logger f15171l;

    /* renamed from: m  reason: collision with root package name */
    MainThreadSupport f15172m;

    /* renamed from: a  reason: collision with root package name */
    boolean f15160a = true;

    /* renamed from: b  reason: collision with root package name */
    boolean f15161b = true;

    /* renamed from: c  reason: collision with root package name */
    boolean f15162c = true;

    /* renamed from: d  reason: collision with root package name */
    boolean f15163d = true;

    /* renamed from: f  reason: collision with root package name */
    boolean f15165f = true;

    /* renamed from: i  reason: collision with root package name */
    ExecutorService f15168i = DEFAULT_EXECUTOR_SERVICE;

    static Object a() {
        try {
            return Looper.getMainLooper();
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public EventBusBuilder addIndex(SubscriberInfoIndex subscriberInfoIndex) {
        if (this.f15170k == null) {
            this.f15170k = new ArrayList();
        }
        this.f15170k.add(subscriberInfoIndex);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Logger b() {
        Logger logger = this.f15171l;
        return logger != null ? logger : Logger.Default.get();
    }

    public EventBus build() {
        return new EventBus(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MainThreadSupport c() {
        Object a2;
        MainThreadSupport mainThreadSupport = this.f15172m;
        if (mainThreadSupport != null) {
            return mainThreadSupport;
        }
        if (!AndroidLogger.isAndroidLogAvailable() || (a2 = a()) == null) {
            return null;
        }
        return new MainThreadSupport.AndroidHandlerMainThreadSupport((Looper) a2);
    }

    public EventBusBuilder eventInheritance(boolean z) {
        this.f15165f = z;
        return this;
    }

    public EventBusBuilder executorService(ExecutorService executorService) {
        this.f15168i = executorService;
        return this;
    }

    public EventBusBuilder ignoreGeneratedIndex(boolean z) {
        this.f15166g = z;
        return this;
    }

    public EventBus installDefaultEventBus() {
        EventBus eventBus;
        synchronized (EventBus.class) {
            if (EventBus.f15152a != null) {
                throw new EventBusException("Default instance already exists. It may be only set once before it's used the first time to ensure consistent behavior.");
            }
            EventBus.f15152a = build();
            eventBus = EventBus.f15152a;
        }
        return eventBus;
    }

    public EventBusBuilder logNoSubscriberMessages(boolean z) {
        this.f15161b = z;
        return this;
    }

    public EventBusBuilder logSubscriberExceptions(boolean z) {
        this.f15160a = z;
        return this;
    }

    public EventBusBuilder logger(Logger logger) {
        this.f15171l = logger;
        return this;
    }

    public EventBusBuilder sendNoSubscriberEvent(boolean z) {
        this.f15163d = z;
        return this;
    }

    public EventBusBuilder sendSubscriberExceptionEvent(boolean z) {
        this.f15162c = z;
        return this;
    }

    public EventBusBuilder skipMethodVerificationFor(Class<?> cls) {
        if (this.f15169j == null) {
            this.f15169j = new ArrayList();
        }
        this.f15169j.add(cls);
        return this;
    }

    public EventBusBuilder strictMethodVerification(boolean z) {
        this.f15167h = z;
        return this;
    }

    public EventBusBuilder throwSubscriberException(boolean z) {
        this.f15164e = z;
        return this;
    }
}
