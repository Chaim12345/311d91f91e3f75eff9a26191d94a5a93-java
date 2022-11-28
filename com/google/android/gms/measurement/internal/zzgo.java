package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;
/* loaded from: classes2.dex */
final class zzgo implements Callable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f6766a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f6767b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ String f6768c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zzhc f6769d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgo(zzhc zzhcVar, String str, String str2, String str3) {
        this.f6769d = zzhcVar;
        this.f6766a = str;
        this.f6767b = str2;
        this.f6768c = str3;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() {
        zzll zzllVar;
        zzll zzllVar2;
        zzllVar = this.f6769d.zza;
        zzllVar.a();
        zzllVar2 = this.f6769d.zza;
        return zzllVar2.zzi().zzv(this.f6766a, this.f6767b, this.f6768c);
    }
}
