package com.google.android.gms.common.api.internal;

import java.util.concurrent.locks.Lock;
/* loaded from: classes.dex */
final class zav implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zaaa f5712a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zav(zaaa zaaaVar) {
        this.f5712a = zaaaVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Lock lock;
        Lock lock2;
        lock = this.f5712a.zam;
        lock.lock();
        try {
            zaaa.j(this.f5712a);
        } finally {
            lock2 = this.f5712a.zam;
            lock2.unlock();
        }
    }
}
