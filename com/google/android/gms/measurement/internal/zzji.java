package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzji implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzq f6933a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzke f6934b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzji(zzke zzkeVar, zzq zzqVar) {
        this.f6934b = zzkeVar;
        this.f6933a = zzqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzeq zzeqVar;
        zzke zzkeVar = this.f6934b;
        zzeqVar = zzkeVar.zzb;
        if (zzeqVar == null) {
            zzkeVar.f6809a.zzay().zzd().zza("Failed to reset data on the service: not connected to service");
            return;
        }
        try {
            Preconditions.checkNotNull(this.f6933a);
            zzeqVar.zzm(this.f6933a);
        } catch (RemoteException e2) {
            this.f6934b.f6809a.zzay().zzd().zzb("Failed to reset data on the service: remote exception", e2);
        }
        this.f6934b.zzQ();
    }
}
