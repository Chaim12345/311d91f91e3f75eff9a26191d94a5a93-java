package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes2.dex */
public class zzet {
    private static final zzdn zzb = zzdn.zza();

    /* renamed from: a  reason: collision with root package name */
    protected volatile zzfl f6426a;
    private volatile zzdb zzc;

    protected final void a(zzfl zzflVar) {
        if (this.f6426a != null) {
            return;
        }
        synchronized (this) {
            if (this.f6426a == null) {
                try {
                    this.f6426a = zzflVar;
                    this.zzc = zzdb.zzb;
                } catch (zzen unused) {
                    this.f6426a = zzflVar;
                    this.zzc = zzdb.zzb;
                }
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzet) {
            zzet zzetVar = (zzet) obj;
            zzfl zzflVar = this.f6426a;
            zzfl zzflVar2 = zzetVar.f6426a;
            if (zzflVar == null && zzflVar2 == null) {
                return zzb().equals(zzetVar.zzb());
            }
            if (zzflVar == null || zzflVar2 == null) {
                if (zzflVar != null) {
                    zzetVar.a(zzflVar.zzX());
                    return zzflVar.equals(zzetVar.f6426a);
                }
                a(zzflVar2.zzX());
                return this.f6426a.equals(zzflVar2);
            }
            return zzflVar.equals(zzflVar2);
        }
        return false;
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zzcz) this.zzc).f6411a.length;
        }
        if (this.f6426a != null) {
            return this.f6426a.zzE();
        }
        return 0;
    }

    public final zzdb zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            if (this.zzc != null) {
                return this.zzc;
            }
            this.zzc = this.f6426a == null ? zzdb.zzb : this.f6426a.zzC();
            return this.zzc;
        }
    }

    public final zzfl zzc(zzfl zzflVar) {
        zzfl zzflVar2 = this.f6426a;
        this.zzc = null;
        this.f6426a = zzflVar;
        return zzflVar2;
    }
}
