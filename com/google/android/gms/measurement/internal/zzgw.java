package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzgw implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzaw f6791a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f6792b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzhc f6793c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgw(zzhc zzhcVar, zzaw zzawVar, String str) {
        this.f6793c = zzhcVar;
        this.f6791a = zzawVar;
        this.f6792b = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzll zzllVar;
        zzll zzllVar2;
        zzllVar = this.f6793c.zza;
        zzllVar.a();
        zzllVar2 = this.f6793c.zza;
        zzllVar2.e(this.f6791a, this.f6792b);
    }
}
