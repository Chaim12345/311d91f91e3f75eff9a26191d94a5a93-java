package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzjg implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzq f6925a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ boolean f6926b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzlo f6927c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zzke f6928d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjg(zzke zzkeVar, zzq zzqVar, boolean z, zzlo zzloVar) {
        this.f6928d = zzkeVar;
        this.f6925a = zzqVar;
        this.f6926b = z;
        this.f6927c = zzloVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzeq zzeqVar;
        zzke zzkeVar = this.f6928d;
        zzeqVar = zzkeVar.zzb;
        if (zzeqVar == null) {
            zzkeVar.f6809a.zzay().zzd().zza("Discarding data. Failed to set user property");
            return;
        }
        Preconditions.checkNotNull(this.f6925a);
        this.f6928d.e(zzeqVar, this.f6926b ? null : this.f6927c, this.f6925a);
        this.f6928d.zzQ();
    }
}
