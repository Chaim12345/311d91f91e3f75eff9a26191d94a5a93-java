package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzkj implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzll f6999a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Runnable f7000b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkj(zzkl zzklVar, zzll zzllVar, Runnable runnable) {
        this.f6999a = zzllVar;
        this.f7000b = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f6999a.a();
        this.f6999a.I(this.f7000b);
        this.f6999a.v();
    }
}
