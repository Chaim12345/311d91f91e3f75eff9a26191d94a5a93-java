package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class zzf extends zze {
    private boolean zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzf(zzgk zzgkVar) {
        super(zzgkVar);
        this.f6809a.d();
    }

    @WorkerThread
    protected void a() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean b() {
        return this.zza;
    }

    protected abstract boolean c();

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zza() {
        if (!b()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzb() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        if (c()) {
            return;
        }
        this.f6809a.b();
        this.zza = true;
    }

    public final void zzc() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        a();
        this.f6809a.b();
        this.zza = true;
    }
}
