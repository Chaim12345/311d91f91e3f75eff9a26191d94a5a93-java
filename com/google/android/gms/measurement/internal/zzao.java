package com.google.android.gms.measurement.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzao implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzhf f6690a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzap f6691b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzao(zzap zzapVar, zzhf zzhfVar) {
        this.f6691b = zzapVar;
        this.f6690a = zzhfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f6690a.zzaw();
        if (zzab.zza()) {
            this.f6690a.zzaz().zzp(this);
            return;
        }
        boolean zze = this.f6691b.zze();
        this.f6691b.zzd = 0L;
        if (zze) {
            this.f6691b.zzc();
        }
    }
}
