package com.google.android.gms.measurement.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class zzkz extends zzky {
    private boolean zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkz(zzll zzllVar) {
        super(zzllVar);
        this.f7018b.l();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a() {
        if (!b()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean b() {
        return this.zza;
    }

    protected abstract boolean c();

    public final void zzX() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        c();
        this.f7018b.g();
        this.zza = true;
    }
}
