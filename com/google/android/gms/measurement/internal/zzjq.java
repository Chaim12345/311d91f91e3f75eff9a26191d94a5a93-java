package com.google.android.gms.measurement.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzjq extends zzap {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzke f6953a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzjq(zzke zzkeVar, zzhf zzhfVar) {
        super(zzhfVar);
        this.f6953a = zzkeVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzap
    public final void zzc() {
        this.f6953a.f6809a.zzay().zzk().zza("Tasks have been queued for a long time");
    }
}
