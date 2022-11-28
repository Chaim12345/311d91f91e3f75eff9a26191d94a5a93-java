package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzkr extends zzap {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzks f7009a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzkr(zzks zzksVar, zzhf zzhfVar) {
        super(zzhfVar);
        this.f7009a = zzksVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzap
    @WorkerThread
    public final void zzc() {
        zzks zzksVar = this.f7009a;
        zzksVar.f7012c.zzg();
        zzksVar.zzd(false, false, zzksVar.f7012c.f6809a.zzav().elapsedRealtime());
        zzksVar.f7012c.f6809a.zzd().zzf(zzksVar.f7012c.f6809a.zzav().elapsedRealtime());
    }
}
