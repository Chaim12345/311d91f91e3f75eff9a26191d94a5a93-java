package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes2.dex */
final class zzgt implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzq f6784a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzhc f6785b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgt(zzhc zzhcVar, zzq zzqVar) {
        this.f6785b = zzhcVar;
        this.f6784a = zzqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzll zzllVar;
        zzll zzllVar2;
        zzllVar = this.f6785b.zza;
        zzllVar.a();
        zzllVar2 = this.f6785b.zza;
        zzq zzqVar = this.f6784a;
        zzllVar2.zzaz().zzg();
        zzllVar2.b();
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzllVar2.B(zzqVar);
    }
}
