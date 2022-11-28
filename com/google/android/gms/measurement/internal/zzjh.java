package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzjh implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AtomicReference f6929a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzq f6930b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ boolean f6931c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zzke f6932d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjh(zzke zzkeVar, AtomicReference atomicReference, zzq zzqVar, boolean z) {
        this.f6932d = zzkeVar;
        this.f6929a = atomicReference;
        this.f6930b = zzqVar;
        this.f6931c = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AtomicReference atomicReference;
        zzke zzkeVar;
        zzeq zzeqVar;
        synchronized (this.f6929a) {
            try {
                zzkeVar = this.f6932d;
                zzeqVar = zzkeVar.zzb;
            } catch (RemoteException e2) {
                this.f6932d.f6809a.zzay().zzd().zzb("Failed to get all user properties; remote exception", e2);
                atomicReference = this.f6929a;
            }
            if (zzeqVar == null) {
                zzkeVar.f6809a.zzay().zzd().zza("Failed to get all user properties; not connected to service");
                this.f6929a.notify();
                return;
            }
            Preconditions.checkNotNull(this.f6930b);
            this.f6929a.set(zzeqVar.zze(this.f6930b, this.f6931c));
            this.f6932d.zzQ();
            atomicReference = this.f6929a;
            atomicReference.notify();
        }
    }
}
