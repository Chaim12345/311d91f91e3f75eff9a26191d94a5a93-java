package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzjl implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzq f6941a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzke f6942b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjl(zzke zzkeVar, zzq zzqVar) {
        this.f6942b = zzkeVar;
        this.f6941a = zzqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzeq zzeqVar;
        zzke zzkeVar = this.f6942b;
        zzeqVar = zzkeVar.zzb;
        if (zzeqVar == null) {
            zzkeVar.f6809a.zzay().zzd().zza("Discarding data. Failed to send app launch");
            return;
        }
        try {
            Preconditions.checkNotNull(this.f6941a);
            zzeqVar.zzj(this.f6941a);
            this.f6942b.f6809a.zzi().zzm();
            this.f6942b.e(zzeqVar, null, this.f6941a);
            this.f6942b.zzQ();
        } catch (RemoteException e2) {
            this.f6942b.f6809a.zzay().zzd().zzb("Failed to send app launch to the service", e2);
        }
    }
}
