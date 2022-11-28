package com.google.android.gms.common.api.internal;

import androidx.annotation.WorkerThread;
import java.util.concurrent.locks.Lock;
/* loaded from: classes.dex */
abstract class zaav implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zaaw f5647a;

    @Override // java.lang.Runnable
    @WorkerThread
    public final void run() {
        Lock lock;
        Lock lock2;
        zabi zabiVar;
        lock = this.f5647a.zab;
        lock.lock();
        try {
            try {
                if (!Thread.interrupted()) {
                    zaa();
                }
            } catch (RuntimeException e2) {
                zabiVar = this.f5647a.zaa;
                zabiVar.g(e2);
            }
        } finally {
            lock2 = this.f5647a.zab;
            lock2.unlock();
        }
    }

    @WorkerThread
    protected abstract void zaa();
}
