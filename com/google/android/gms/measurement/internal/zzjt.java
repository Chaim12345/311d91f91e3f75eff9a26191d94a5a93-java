package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzjt implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzq f6958a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ boolean f6959b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzaw f6960c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zzke f6961d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjt(zzke zzkeVar, boolean z, zzq zzqVar, boolean z2, zzaw zzawVar, String str) {
        this.f6961d = zzkeVar;
        this.f6958a = zzqVar;
        this.f6959b = z2;
        this.f6960c = zzawVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzeq zzeqVar;
        zzke zzkeVar = this.f6961d;
        zzeqVar = zzkeVar.zzb;
        if (zzeqVar == null) {
            zzkeVar.f6809a.zzay().zzd().zza("Discarding data. Failed to send event to service");
            return;
        }
        Preconditions.checkNotNull(this.f6958a);
        this.f6961d.e(zzeqVar, this.f6959b ? null : this.f6960c, this.f6958a);
        this.f6961d.zzQ();
    }
}
