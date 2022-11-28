package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzgy implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzlo f6797a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzq f6798b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzhc f6799c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgy(zzhc zzhcVar, zzlo zzloVar, zzq zzqVar) {
        this.f6799c = zzhcVar;
        this.f6797a = zzloVar;
        this.f6798b = zzqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzll zzllVar;
        zzll zzllVar2;
        zzll zzllVar3;
        zzllVar = this.f6799c.zza;
        zzllVar.a();
        if (this.f6797a.zza() == null) {
            zzllVar3 = this.f6799c.zza;
            zzllVar3.o(this.f6797a, this.f6798b);
            return;
        }
        zzllVar2 = this.f6799c.zza;
        zzllVar2.u(this.f6797a, this.f6798b);
    }
}
