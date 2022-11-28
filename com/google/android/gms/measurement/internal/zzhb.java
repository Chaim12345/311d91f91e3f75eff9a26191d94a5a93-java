package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzpp;
/* loaded from: classes2.dex */
final class zzhb implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f6804a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f6805b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ String f6806c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ long f6807d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzhc f6808e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhb(zzhc zzhcVar, String str, String str2, String str3, long j2) {
        this.f6808e = zzhcVar;
        this.f6804a = str;
        this.f6805b = str2;
        this.f6806c = str3;
        this.f6807d = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzll zzllVar;
        zzll zzllVar2;
        zzll zzllVar3;
        zzll zzllVar4;
        zzll zzllVar5;
        zzpp.zzc();
        zzllVar = this.f6808e.zza;
        if (zzllVar.zzg().zzs(null, zzen.zzat)) {
            String str = this.f6804a;
            if (str == null) {
                zzllVar5 = this.f6808e.zza;
                zzllVar5.zzQ(this.f6805b, null);
                return;
            }
            zziw zziwVar = new zziw(this.f6806c, str, this.f6807d);
            zzllVar4 = this.f6808e.zza;
            zzllVar4.zzQ(this.f6805b, zziwVar);
            return;
        }
        String str2 = this.f6804a;
        if (str2 == null) {
            zzllVar3 = this.f6808e.zza;
            zzllVar3.E().zzs().zzy(this.f6805b, null);
            return;
        }
        zziw zziwVar2 = new zziw(this.f6806c, str2, this.f6807d);
        zzllVar2 = this.f6808e.zza;
        zzllVar2.E().zzs().zzy(this.f6805b, zziwVar2);
    }
}
