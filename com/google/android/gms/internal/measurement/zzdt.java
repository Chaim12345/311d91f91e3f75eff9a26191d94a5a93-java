package com.google.android.gms.internal.measurement;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class zzdt implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final long f6026a;

    /* renamed from: b  reason: collision with root package name */
    final long f6027b;

    /* renamed from: c  reason: collision with root package name */
    final boolean f6028c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zzee f6029d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdt(zzee zzeeVar, boolean z) {
        this.f6029d = zzeeVar;
        this.f6026a = zzeeVar.f6047a.currentTimeMillis();
        this.f6027b = zzeeVar.f6047a.elapsedRealtime();
        this.f6028c = z;
    }

    protected void a() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z;
        z = this.f6029d.zzh;
        if (z) {
            a();
            return;
        }
        try {
            zza();
        } catch (Exception e2) {
            this.f6029d.zzS(e2, false, this.f6028c);
            a();
        }
    }

    abstract void zza();
}
