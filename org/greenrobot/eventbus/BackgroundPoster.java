package org.greenrobot.eventbus;

import java.util.logging.Level;
/* loaded from: classes4.dex */
final class BackgroundPoster implements Runnable, Poster {
    private final EventBus eventBus;
    private volatile boolean executorRunning;
    private final PendingPostQueue queue = new PendingPostQueue();

    /* JADX INFO: Access modifiers changed from: package-private */
    public BackgroundPoster(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override // org.greenrobot.eventbus.Poster
    public void enqueue(Subscription subscription, Object obj) {
        PendingPost a2 = PendingPost.a(subscription, obj);
        synchronized (this) {
            this.queue.a(a2);
            if (!this.executorRunning) {
                this.executorRunning = true;
                this.eventBus.b().execute(this);
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        while (true) {
            try {
                PendingPost c2 = this.queue.c(1000);
                if (c2 == null) {
                    synchronized (this) {
                        c2 = this.queue.b();
                        if (c2 == null) {
                            return;
                        }
                    }
                }
                this.eventBus.c(c2);
            } catch (InterruptedException e2) {
                Logger logger = this.eventBus.getLogger();
                Level level = Level.WARNING;
                logger.log(level, Thread.currentThread().getName() + " was interruppted", e2);
                return;
            } finally {
                this.executorRunning = false;
            }
        }
    }
}
