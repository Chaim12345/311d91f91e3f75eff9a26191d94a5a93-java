package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzjk implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzq f6938a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcf f6939b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzke f6940c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjk(zzke zzkeVar, zzq zzqVar, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        this.f6940c = zzkeVar;
        this.f6938a = zzqVar;
        this.f6939b = zzcfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzeq zzeqVar;
        String str = null;
        try {
            try {
                if (this.f6940c.f6809a.zzm().g().zzi(zzah.ANALYTICS_STORAGE)) {
                    zzke zzkeVar = this.f6940c;
                    zzeqVar = zzkeVar.zzb;
                    if (zzeqVar == null) {
                        zzkeVar.f6809a.zzay().zzd().zza("Failed to get app instance id");
                    } else {
                        Preconditions.checkNotNull(this.f6938a);
                        str = zzeqVar.zzd(this.f6938a);
                        if (str != null) {
                            this.f6940c.f6809a.zzq().l(str);
                            this.f6940c.f6809a.zzm().zze.zzb(str);
                        }
                        this.f6940c.zzQ();
                    }
                } else {
                    this.f6940c.f6809a.zzay().zzl().zza("Analytics storage consent denied; will not get app instance id");
                    this.f6940c.f6809a.zzq().l(null);
                    this.f6940c.f6809a.zzm().zze.zzb(null);
                }
            } catch (RemoteException e2) {
                this.f6940c.f6809a.zzay().zzd().zzb("Failed to get app instance id", e2);
            }
        } finally {
            this.f6940c.f6809a.zzv().zzV(this.f6939b, null);
        }
    }
}
