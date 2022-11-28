package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;
/* loaded from: classes2.dex */
final class zzgp implements Callable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f6770a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f6771b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ String f6772c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zzhc f6773d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgp(zzhc zzhcVar, String str, String str2, String str3) {
        this.f6773d = zzhcVar;
        this.f6770a = str;
        this.f6771b = str2;
        this.f6772c = str3;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() {
        zzll zzllVar;
        zzll zzllVar2;
        zzllVar = this.f6773d.zza;
        zzllVar.a();
        zzllVar2 = this.f6773d.zza;
        return zzllVar2.zzi().zzv(this.f6770a, this.f6771b, this.f6772c);
    }
}
