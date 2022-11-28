package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzjj implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AtomicReference f6935a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzq f6936b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzke f6937c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjj(zzke zzkeVar, AtomicReference atomicReference, zzq zzqVar) {
        this.f6937c = zzkeVar;
        this.f6935a = atomicReference;
        this.f6936b = zzqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AtomicReference atomicReference;
        zzeq zzeqVar;
        synchronized (this.f6935a) {
            try {
            } catch (RemoteException e2) {
                this.f6937c.f6809a.zzay().zzd().zzb("Failed to get app instance id", e2);
                atomicReference = this.f6935a;
            }
            if (!this.f6937c.f6809a.zzm().g().zzi(zzah.ANALYTICS_STORAGE)) {
                this.f6937c.f6809a.zzay().zzl().zza("Analytics storage consent denied; will not get app instance id");
                this.f6937c.f6809a.zzq().l(null);
                this.f6937c.f6809a.zzm().zze.zzb(null);
                this.f6935a.set(null);
                this.f6935a.notify();
                return;
            }
            zzke zzkeVar = this.f6937c;
            zzeqVar = zzkeVar.zzb;
            if (zzeqVar == null) {
                zzkeVar.f6809a.zzay().zzd().zza("Failed to get app instance id");
                this.f6935a.notify();
                return;
            }
            Preconditions.checkNotNull(this.f6936b);
            this.f6935a.set(zzeqVar.zzd(this.f6936b));
            String str = (String) this.f6935a.get();
            if (str != null) {
                this.f6937c.f6809a.zzq().l(str);
                this.f6937c.f6809a.zzm().zze.zzb(str);
            }
            this.f6937c.zzQ();
            atomicReference = this.f6935a;
            atomicReference.notify();
        }
    }
}
