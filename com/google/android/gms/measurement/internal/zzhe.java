package com.google.android.gms.measurement.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class zzhe extends zzhd {
    private boolean zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhe(zzgk zzgkVar) {
        super(zzgkVar);
        this.f6809a.d();
    }

    protected void a() {
    }

    protected abstract boolean b();

    /* JADX INFO: Access modifiers changed from: protected */
    public final void c() {
        if (!d()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean d() {
        return this.zza;
    }

    public final void zzv() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        if (b()) {
            return;
        }
        this.f6809a.b();
        this.zza = true;
    }

    public final void zzw() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        a();
        this.f6809a.b();
        this.zza = true;
    }
}
