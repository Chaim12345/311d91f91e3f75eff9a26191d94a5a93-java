package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzjs implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzq f6956a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzke f6957b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjs(zzke zzkeVar, zzq zzqVar) {
        this.f6957b = zzkeVar;
        this.f6956a = zzqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzeq zzeqVar;
        zzke zzkeVar = this.f6957b;
        zzeqVar = zzkeVar.zzb;
        if (zzeqVar == null) {
            zzkeVar.f6809a.zzay().zzd().zza("Failed to send consent settings to service");
            return;
        }
        try {
            Preconditions.checkNotNull(this.f6956a);
            zzeqVar.zzp(this.f6956a);
            this.f6957b.zzQ();
        } catch (RemoteException e2) {
            this.f6957b.f6809a.zzay().zzd().zzb("Failed to send consent settings to the service", e2);
        }
    }
}
