package org.greenrobot.eventbus;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
/* loaded from: classes4.dex */
public class HandlerPoster extends Handler implements Poster {
    private final EventBus eventBus;
    private boolean handlerActive;
    private final int maxMillisInsideHandleMessage;
    private final PendingPostQueue queue;

    /* JADX INFO: Access modifiers changed from: protected */
    public HandlerPoster(EventBus eventBus, Looper looper, int i2) {
        super(looper);
        this.eventBus = eventBus;
        this.maxMillisInsideHandleMessage = i2;
        this.queue = new PendingPostQueue();
    }

    @Override // org.greenrobot.eventbus.Poster
    public void enqueue(Subscription subscription, Object obj) {
        PendingPost a2 = PendingPost.a(subscription, obj);
        synchronized (this) {
            this.queue.a(a2);
            if (!this.handlerActive) {
                this.handlerActive = true;
                if (!sendMessage(obtainMessage())) {
                    throw new EventBusException("Could not send handler message");
                }
            }
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        try {
            long uptimeMillis = SystemClock.uptimeMillis();
            do {
                PendingPost b2 = this.queue.b();
                if (b2 == null) {
                    synchronized (this) {
                        b2 = this.queue.b();
                        if (b2 == null) {
                            this.handlerActive = false;
                            return;
                        }
                    }
                }
                this.eventBus.c(b2);
            } while (SystemClock.uptimeMillis() - uptimeMillis < this.maxMillisInsideHandleMessage);
            if (!sendMessage(obtainMessage())) {
                throw new EventBusException("Could not send handler message");
            }
            this.handlerActive = true;
        } finally {
            this.handlerActive = false;
        }
    }
}
