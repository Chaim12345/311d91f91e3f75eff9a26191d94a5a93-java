package com.facebook.stetho.common.android;

import android.os.Handler;
import android.os.Looper;
import com.facebook.stetho.common.UncheckedCallable;
import com.facebook.stetho.common.Util;
/* loaded from: classes.dex */
public final class HandlerUtil {

    /* loaded from: classes.dex */
    private static abstract class WaitableRunnable<V> implements Runnable {
        private Exception mException;
        private boolean mIsDone;
        private V mValue;

        protected WaitableRunnable() {
        }

        private void join() {
            synchronized (this) {
                while (!this.mIsDone) {
                    try {
                        wait();
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }

        public V invoke(Handler handler) {
            if (handler.post(this)) {
                join();
                if (this.mException == null) {
                    return this.mValue;
                }
                throw new RuntimeException(this.mException);
            }
            throw new RuntimeException("Handler.post() returned false");
        }

        protected abstract V onRun();

        @Override // java.lang.Runnable
        public final void run() {
            try {
                try {
                    this.mValue = onRun();
                    this.mException = null;
                    synchronized (this) {
                        this.mIsDone = true;
                        notifyAll();
                    }
                } catch (Exception e2) {
                    this.mValue = null;
                    this.mException = e2;
                    synchronized (this) {
                        this.mIsDone = true;
                        notifyAll();
                    }
                }
            } catch (Throwable th) {
                synchronized (this) {
                    this.mIsDone = true;
                    notifyAll();
                    throw th;
                }
            }
        }
    }

    private HandlerUtil() {
    }

    public static boolean checkThreadAccess(Handler handler) {
        return Looper.myLooper() == handler.getLooper();
    }

    public static <V> V postAndWait(Handler handler, final UncheckedCallable<V> uncheckedCallable) {
        if (checkThreadAccess(handler)) {
            try {
                return uncheckedCallable.call();
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
        return new WaitableRunnable<V>() { // from class: com.facebook.stetho.common.android.HandlerUtil.1
            /* JADX WARN: Type inference failed for: r0v1, types: [V, java.lang.Object] */
            @Override // com.facebook.stetho.common.android.HandlerUtil.WaitableRunnable
            protected V onRun() {
                return UncheckedCallable.this.call();
            }
        }.invoke(handler);
    }

    public static void postAndWait(Handler handler, final Runnable runnable) {
        if (!checkThreadAccess(handler)) {
            new WaitableRunnable<Void>() { // from class: com.facebook.stetho.common.android.HandlerUtil.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.facebook.stetho.common.android.HandlerUtil.WaitableRunnable
                public Void onRun() {
                    runnable.run();
                    return null;
                }
            }.invoke(handler);
            return;
        }
        try {
            runnable.run();
        } catch (RuntimeException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static void verifyThreadAccess(Handler handler) {
        Util.throwIfNot(checkThreadAccess(handler));
    }
}
