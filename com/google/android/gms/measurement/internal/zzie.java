package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzie implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AtomicReference f6861a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzip f6862b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzie(zzip zzipVar, AtomicReference atomicReference) {
        this.f6862b = zzipVar;
        this.f6861a = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.f6861a) {
            this.f6861a.set(this.f6862b.f6809a.zzf().zzo(this.f6862b.f6809a.zzh().zzl(), zzen.zzK));
            this.f6861a.notify();
        }
    }
}
