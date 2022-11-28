package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzjp implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzaw f6949a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f6950b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcf f6951c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zzke f6952d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjp(zzke zzkeVar, zzaw zzawVar, String str, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        this.f6952d = zzkeVar;
        this.f6949a = zzawVar;
        this.f6950b = str;
        this.f6951c = zzcfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzeq zzeqVar;
        byte[] bArr = null;
        try {
            try {
                zzke zzkeVar = this.f6952d;
                zzeqVar = zzkeVar.zzb;
                if (zzeqVar == null) {
                    zzkeVar.f6809a.zzay().zzd().zza("Discarding data. Failed to send event to service to bundle");
                } else {
                    bArr = zzeqVar.zzu(this.f6949a, this.f6950b);
                    this.f6952d.zzQ();
                }
            } catch (RemoteException e2) {
                this.f6952d.f6809a.zzay().zzd().zzb("Failed to send event to the service to bundle", e2);
            }
        } finally {
            this.f6952d.f6809a.zzv().zzS(this.f6951c, bArr);
        }
    }
}
