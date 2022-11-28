package com.google.android.gms.measurement.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzjo extends zzap {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzke f6948a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzjo(zzke zzkeVar, zzhf zzhfVar) {
        super(zzhfVar);
        this.f6948a = zzkeVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzap
    public final void zzc() {
        zzke zzkeVar = this.f6948a;
        zzkeVar.zzg();
        if (zzkeVar.zzL()) {
            zzkeVar.f6809a.zzay().zzj().zza("Inactivity, disconnecting from the service");
            zzkeVar.zzs();
        }
    }
}
