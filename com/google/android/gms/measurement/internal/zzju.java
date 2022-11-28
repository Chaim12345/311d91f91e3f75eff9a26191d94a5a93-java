package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzju implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzq f6962a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ boolean f6963b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzac f6964c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zzke f6965d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzju(zzke zzkeVar, boolean z, zzq zzqVar, boolean z2, zzac zzacVar, zzac zzacVar2) {
        this.f6965d = zzkeVar;
        this.f6962a = zzqVar;
        this.f6963b = z2;
        this.f6964c = zzacVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzeq zzeqVar;
        zzke zzkeVar = this.f6965d;
        zzeqVar = zzkeVar.zzb;
        if (zzeqVar == null) {
            zzkeVar.f6809a.zzay().zzd().zza("Discarding data. Failed to send conditional user property to service");
            return;
        }
        Preconditions.checkNotNull(this.f6962a);
        this.f6965d.e(zzeqVar, this.f6963b ? null : this.f6964c, this.f6962a);
        this.f6965d.zzQ();
    }
}
