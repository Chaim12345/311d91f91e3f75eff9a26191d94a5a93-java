package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzkc implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzkd f6994a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkc(zzkd zzkdVar) {
        this.f6994a = zzkdVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f6994a.f6995a.zzb = null;
        this.f6994a.f6995a.zzP();
    }
}
