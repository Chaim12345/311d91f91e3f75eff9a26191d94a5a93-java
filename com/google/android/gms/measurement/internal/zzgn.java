package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzgn implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzac f6764a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzhc f6765b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgn(zzhc zzhcVar, zzac zzacVar) {
        this.f6765b = zzhcVar;
        this.f6764a = zzacVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzll zzllVar;
        zzll zzllVar2;
        zzll zzllVar3;
        zzllVar = this.f6765b.zza;
        zzllVar.a();
        if (this.f6764a.zzc.zza() == null) {
            zzllVar3 = this.f6765b.zza;
            zzllVar3.m(this.f6764a);
            return;
        }
        zzllVar2 = this.f6765b.zza;
        zzllVar2.r(this.f6764a);
    }
}
