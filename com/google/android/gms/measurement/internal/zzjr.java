package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzjr implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzq f6954a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzke f6955b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjr(zzke zzkeVar, zzq zzqVar) {
        this.f6955b = zzkeVar;
        this.f6954a = zzqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzeq zzeqVar;
        zzke zzkeVar = this.f6955b;
        zzeqVar = zzkeVar.zzb;
        if (zzeqVar == null) {
            zzkeVar.f6809a.zzay().zzd().zza("Failed to send measurementEnabled to service");
            return;
        }
        try {
            Preconditions.checkNotNull(this.f6954a);
            zzeqVar.zzs(this.f6954a);
            this.f6955b.zzQ();
        } catch (RemoteException e2) {
            this.f6955b.f6809a.zzay().zzd().zzb("Failed to send measurementEnabled to the service", e2);
        }
    }
}
