package org.greenrobot.eventbus;
/* loaded from: classes4.dex */
class AsyncPoster implements Runnable, Poster {
    private final EventBus eventBus;
    private final PendingPostQueue queue = new PendingPostQueue();

    /* JADX INFO: Access modifiers changed from: package-private */
    public AsyncPoster(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override // org.greenrobot.eventbus.Poster
    public void enqueue(Subscription subscription, Object obj) {
        this.queue.a(PendingPost.a(subscription, obj));
        this.eventBus.b().execute(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        PendingPost b2 = this.queue.b();
        if (b2 == null) {
            throw new IllegalStateException("No pending post available");
        }
        this.eventBus.c(b2);
    }
}
