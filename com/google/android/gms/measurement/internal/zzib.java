package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzib implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AtomicReference f6853a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzip f6854b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzib(zzip zzipVar, AtomicReference atomicReference) {
        this.f6854b = zzipVar;
        this.f6853a = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.f6853a) {
            this.f6853a.set(Boolean.valueOf(this.f6854b.f6809a.zzf().zzs(this.f6854b.f6809a.zzh().zzl(), zzen.zzJ)));
            this.f6853a.notify();
        }
    }
}
