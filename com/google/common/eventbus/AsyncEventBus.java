package com.google.common.eventbus;

import com.google.common.annotations.Beta;
import com.google.common.eventbus.EventBus;
import java.util.concurrent.Executor;
import org.apache.http.client.config.CookieSpecs;
@Beta
/* loaded from: classes2.dex */
public class AsyncEventBus extends EventBus {
    public AsyncEventBus(String str, Executor executor) {
        super(str, executor, Dispatcher.b(), EventBus.LoggingHandler.f9122a);
    }

    public AsyncEventBus(Executor executor) {
        super(CookieSpecs.DEFAULT, executor, Dispatcher.b(), EventBus.LoggingHandler.f9122a);
    }

    public AsyncEventBus(Executor executor, SubscriberExceptionHandler subscriberExceptionHandler) {
        super(CookieSpecs.DEFAULT, executor, Dispatcher.b(), subscriberExceptionHandler);
    }
}
