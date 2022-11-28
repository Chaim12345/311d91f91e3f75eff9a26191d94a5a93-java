package com.google.common.eventbus;

import com.google.common.annotations.Beta;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.config.CookieSpecs;
@Beta
/* loaded from: classes2.dex */
public class EventBus {
    private static final Logger logger = Logger.getLogger(EventBus.class.getName());
    private final Dispatcher dispatcher;
    private final SubscriberExceptionHandler exceptionHandler;
    private final Executor executor;
    private final String identifier;
    private final SubscriberRegistry subscribers;

    /* loaded from: classes2.dex */
    static final class LoggingHandler implements SubscriberExceptionHandler {

        /* renamed from: a  reason: collision with root package name */
        static final LoggingHandler f9122a = new LoggingHandler();

        LoggingHandler() {
        }

        private static Logger logger(SubscriberExceptionContext subscriberExceptionContext) {
            return Logger.getLogger(EventBus.class.getName() + "." + subscriberExceptionContext.getEventBus().identifier());
        }

        private static String message(SubscriberExceptionContext subscriberExceptionContext) {
            Method subscriberMethod = subscriberExceptionContext.getSubscriberMethod();
            return "Exception thrown by subscriber method " + subscriberMethod.getName() + '(' + subscriberMethod.getParameterTypes()[0].getName() + ") on subscriber " + subscriberExceptionContext.getSubscriber() + " when dispatching event: " + subscriberExceptionContext.getEvent();
        }

        @Override // com.google.common.eventbus.SubscriberExceptionHandler
        public void handleException(Throwable th, SubscriberExceptionContext subscriberExceptionContext) {
            Logger logger = logger(subscriberExceptionContext);
            Level level = Level.SEVERE;
            if (logger.isLoggable(level)) {
                logger.log(level, message(subscriberExceptionContext), th);
            }
        }
    }

    public EventBus() {
        this(CookieSpecs.DEFAULT);
    }

    public EventBus(SubscriberExceptionHandler subscriberExceptionHandler) {
        this(CookieSpecs.DEFAULT, MoreExecutors.directExecutor(), Dispatcher.c(), subscriberExceptionHandler);
    }

    public EventBus(String str) {
        this(str, MoreExecutors.directExecutor(), Dispatcher.c(), LoggingHandler.f9122a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EventBus(String str, Executor executor, Dispatcher dispatcher, SubscriberExceptionHandler subscriberExceptionHandler) {
        this.subscribers = new SubscriberRegistry(this);
        this.identifier = (String) Preconditions.checkNotNull(str);
        this.executor = (Executor) Preconditions.checkNotNull(executor);
        this.dispatcher = (Dispatcher) Preconditions.checkNotNull(dispatcher);
        this.exceptionHandler = (SubscriberExceptionHandler) Preconditions.checkNotNull(subscriberExceptionHandler);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Executor a() {
        return this.executor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(Throwable th, SubscriberExceptionContext subscriberExceptionContext) {
        Preconditions.checkNotNull(th);
        Preconditions.checkNotNull(subscriberExceptionContext);
        try {
            this.exceptionHandler.handleException(th, subscriberExceptionContext);
        } catch (Throwable th2) {
            logger.log(Level.SEVERE, String.format(Locale.ROOT, "Exception %s thrown while handling exception: %s", th2, th), th2);
        }
    }

    public final String identifier() {
        return this.identifier;
    }

    public void post(Object obj) {
        Iterator c2 = this.subscribers.c(obj);
        if (c2.hasNext()) {
            this.dispatcher.a(obj, c2);
        } else if (obj instanceof DeadEvent) {
        } else {
            post(new DeadEvent(this, obj));
        }
    }

    public void register(Object obj) {
        this.subscribers.d(obj);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(this.identifier).toString();
    }

    public void unregister(Object obj) {
        this.subscribers.e(obj);
    }
}
