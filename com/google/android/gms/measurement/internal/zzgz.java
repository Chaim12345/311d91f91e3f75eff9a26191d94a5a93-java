package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;
/* loaded from: classes2.dex */
final class zzgz implements Callable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f6800a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzhc f6801b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgz(zzhc zzhcVar, String str) {
        this.f6801b = zzhcVar;
        this.f6800a = str;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() {
        zzll zzllVar;
        zzll zzllVar2;
        zzllVar = this.f6801b.zza;
        zzllVar.a();
        zzllVar2 = this.f6801b.zza;
        return zzllVar2.zzi().zzu(this.f6800a);
    }
}
