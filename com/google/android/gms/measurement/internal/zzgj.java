package com.google.android.gms.measurement.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzgj implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzhn f6756a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzgk f6757b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgj(zzgk zzgkVar, zzhn zzhnVar) {
        this.f6757b = zzgkVar;
        this.f6756a = zzhnVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzgk.a(this.f6757b, this.f6756a);
        this.f6757b.f(this.f6756a.f6817g);
    }
}
