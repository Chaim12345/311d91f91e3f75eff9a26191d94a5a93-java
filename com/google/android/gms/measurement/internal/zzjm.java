package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzjm implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zziw f6943a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzke f6944b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjm(zzke zzkeVar, zziw zziwVar) {
        this.f6944b = zzkeVar;
        this.f6943a = zziwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzeq zzeqVar;
        zzke zzkeVar = this.f6944b;
        zzeqVar = zzkeVar.zzb;
        if (zzeqVar == null) {
            zzkeVar.f6809a.zzay().zzd().zza("Failed to send current screen to service");
            return;
        }
        try {
            zziw zziwVar = this.f6943a;
            if (zziwVar == null) {
                zzeqVar.zzq(0L, null, null, zzkeVar.f6809a.zzau().getPackageName());
            } else {
                zzeqVar.zzq(zziwVar.zzc, zziwVar.zza, zziwVar.zzb, zzkeVar.f6809a.zzau().getPackageName());
            }
            this.f6944b.zzQ();
        } catch (RemoteException e2) {
            this.f6944b.f6809a.zzay().zzd().zzb("Failed to send current screen to the service", e2);
        }
    }
}
