package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzjn implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzq f6945a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Bundle f6946b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzke f6947c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjn(zzke zzkeVar, zzq zzqVar, Bundle bundle) {
        this.f6947c = zzkeVar;
        this.f6945a = zzqVar;
        this.f6946b = bundle;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzeq zzeqVar;
        zzke zzkeVar = this.f6947c;
        zzeqVar = zzkeVar.zzb;
        if (zzeqVar == null) {
            zzkeVar.f6809a.zzay().zzd().zza("Failed to send default event parameters to service");
            return;
        }
        try {
            Preconditions.checkNotNull(this.f6945a);
            zzeqVar.zzr(this.f6946b, this.f6945a);
        } catch (RemoteException e2) {
            this.f6947c.f6809a.zzay().zzd().zzb("Failed to send default event parameters to service", e2);
        }
    }
}
