package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;
/* loaded from: classes2.dex */
final class zzgr implements Callable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f6778a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f6779b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ String f6780c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zzhc f6781d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgr(zzhc zzhcVar, String str, String str2, String str3) {
        this.f6781d = zzhcVar;
        this.f6778a = str;
        this.f6779b = str2;
        this.f6780c = str3;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() {
        zzll zzllVar;
        zzll zzllVar2;
        zzllVar = this.f6781d.zza;
        zzllVar.a();
        zzllVar2 = this.f6781d.zza;
        return zzllVar2.zzi().zzs(this.f6778a, this.f6779b, this.f6780c);
    }
}
