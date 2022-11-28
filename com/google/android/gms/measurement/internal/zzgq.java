package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;
/* loaded from: classes2.dex */
final class zzgq implements Callable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f6774a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f6775b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ String f6776c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zzhc f6777d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgq(zzhc zzhcVar, String str, String str2, String str3) {
        this.f6777d = zzhcVar;
        this.f6774a = str;
        this.f6775b = str2;
        this.f6776c = str3;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() {
        zzll zzllVar;
        zzll zzllVar2;
        zzllVar = this.f6777d.zza;
        zzllVar.a();
        zzllVar2 = this.f6777d.zza;
        return zzllVar2.zzi().zzs(this.f6774a, this.f6775b, this.f6776c);
    }
}
