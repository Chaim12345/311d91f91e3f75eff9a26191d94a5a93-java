package com.google.android.play.core.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzak extends zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzah f7858a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzas f7859b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzak(zzas zzasVar, com.google.android.play.core.tasks.zzi zziVar, zzah zzahVar) {
        super(zziVar);
        this.f7859b = zzasVar;
        this.f7858a = zzahVar;
    }

    @Override // com.google.android.play.core.internal.zzah
    public final void zza() {
        zzas.k(this.f7859b, this.f7858a);
    }
}
