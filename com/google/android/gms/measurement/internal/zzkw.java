package com.google.android.gms.measurement.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzkw extends zzap {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzkx f7017a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzkw(zzkx zzkxVar, zzhf zzhfVar) {
        super(zzhfVar);
        this.f7017a = zzkxVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzap
    public final void zzc() {
        this.f7017a.zza();
        this.f7017a.f6809a.zzay().zzj().zza("Starting upload from DelayedRunnable");
        this.f7017a.f7018b.v();
    }
}
