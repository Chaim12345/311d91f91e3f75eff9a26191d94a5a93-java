package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzig implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AtomicReference f6865a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzip f6866b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzig(zzip zzipVar, AtomicReference atomicReference) {
        this.f6866b = zzipVar;
        this.f6865a = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.f6865a) {
            this.f6865a.set(Integer.valueOf(this.f6866b.f6809a.zzf().zze(this.f6866b.f6809a.zzh().zzl(), zzen.zzM)));
            this.f6865a.notify();
        }
    }
}
