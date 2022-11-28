package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzjy implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzeq f6982a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzkd f6983b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjy(zzkd zzkdVar, zzeq zzeqVar) {
        this.f6983b = zzkdVar;
        this.f6982a = zzeqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.f6983b) {
            this.f6983b.zzb = false;
            if (!this.f6983b.f6995a.zzL()) {
                this.f6983b.f6995a.f6809a.zzay().zzj().zza("Connected to service");
                this.f6983b.f6995a.j(this.f6982a);
            }
        }
    }
}
