package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzka implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzeq f6991a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzkd f6992b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzka(zzkd zzkdVar, zzeq zzeqVar) {
        this.f6992b = zzkdVar;
        this.f6991a = zzeqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.f6992b) {
            this.f6992b.zzb = false;
            if (!this.f6992b.f6995a.zzL()) {
                this.f6992b.f6995a.f6809a.zzay().zzc().zza("Connected to remote service");
                this.f6992b.f6995a.j(this.f6991a);
            }
        }
    }
}
