package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzil implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ boolean f6884a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzip f6885b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzil(zzip zzipVar, boolean z) {
        this.f6885b = zzipVar;
        this.f6884a = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean zzJ = this.f6885b.f6809a.zzJ();
        boolean zzI = this.f6885b.f6809a.zzI();
        this.f6885b.f6809a.e(this.f6884a);
        if (zzI == this.f6884a) {
            this.f6885b.f6809a.zzay().zzj().zzb("Default data collection state already set to", Boolean.valueOf(this.f6884a));
        }
        if (this.f6885b.f6809a.zzJ() == zzJ || this.f6885b.f6809a.zzJ() != this.f6885b.f6809a.zzI()) {
            this.f6885b.f6809a.zzay().zzl().zzc("Default data collection is different than actual status", Boolean.valueOf(this.f6884a), Boolean.valueOf(zzJ));
        }
        this.f6885b.zzae();
    }
}
