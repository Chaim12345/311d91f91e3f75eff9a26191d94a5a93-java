package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzih implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AtomicReference f6867a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzip f6868b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzih(zzip zzipVar, AtomicReference atomicReference) {
        this.f6868b = zzipVar;
        this.f6867a = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.f6867a) {
            this.f6867a.set(Double.valueOf(this.f6868b.f6809a.zzf().zza(this.f6868b.f6809a.zzh().zzl(), zzen.zzN)));
            this.f6867a.notify();
        }
    }
}
