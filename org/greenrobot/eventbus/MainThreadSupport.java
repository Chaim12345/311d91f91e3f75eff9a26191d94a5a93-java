package org.greenrobot.eventbus;

import android.os.Looper;
/* loaded from: classes4.dex */
public interface MainThreadSupport {

    /* loaded from: classes4.dex */
    public static class AndroidHandlerMainThreadSupport implements MainThreadSupport {
        private final Looper looper;

        public AndroidHandlerMainThreadSupport(Looper looper) {
            this.looper = looper;
        }

        @Override // org.greenrobot.eventbus.MainThreadSupport
        public Poster createPoster(EventBus eventBus) {
            return new HandlerPoster(eventBus, this.looper, 10);
        }

        @Override // org.greenrobot.eventbus.MainThreadSupport
        public boolean isMainThread() {
            return this.looper == Looper.myLooper();
        }
    }

    Poster createPoster(EventBus eventBus);

    boolean isMainThread();
}
