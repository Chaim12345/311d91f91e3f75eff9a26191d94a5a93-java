package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzif implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AtomicReference f6863a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzip f6864b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzif(zzip zzipVar, AtomicReference atomicReference) {
        this.f6864b = zzipVar;
        this.f6863a = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.f6863a) {
            this.f6863a.set(Long.valueOf(this.f6864b.f6809a.zzf().zzi(this.f6864b.f6809a.zzh().zzl(), zzen.zzL)));
            this.f6863a.notify();
        }
    }
}
