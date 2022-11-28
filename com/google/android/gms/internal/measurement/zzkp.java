package com.google.android.gms.internal.measurement;
/* loaded from: classes.dex */
public class zzkp {
    private static final zzjo zzb = zzjo.zza();

    /* renamed from: a  reason: collision with root package name */
    protected volatile zzlj f6101a;
    private volatile zzjb zzc;

    protected final void a(zzlj zzljVar) {
        if (this.f6101a != null) {
            return;
        }
        synchronized (this) {
            if (this.f6101a == null) {
                try {
                    this.f6101a = zzljVar;
                    this.zzc = zzjb.zzb;
                } catch (zzkm unused) {
                    this.f6101a = zzljVar;
                    this.zzc = zzjb.zzb;
                }
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzkp) {
            zzkp zzkpVar = (zzkp) obj;
            zzlj zzljVar = this.f6101a;
            zzlj zzljVar2 = zzkpVar.f6101a;
            if (zzljVar == null && zzljVar2 == null) {
                return zzb().equals(zzkpVar.zzb());
            }
            if (zzljVar == null || zzljVar2 == null) {
                if (zzljVar != null) {
                    zzkpVar.a(zzljVar.zzbR());
                    return zzljVar.equals(zzkpVar.f6101a);
                }
                a(zzljVar2.zzbR());
                return this.f6101a.equals(zzljVar2);
            }
            return zzljVar.equals(zzljVar2);
        }
        return false;
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zziy) this.zzc).f6093a.length;
        }
        if (this.f6101a != null) {
            return this.f6101a.zzbz();
        }
        return 0;
    }

    public final zzjb zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            if (this.zzc != null) {
                return this.zzc;
            }
            this.zzc = this.f6101a == null ? zzjb.zzb : this.f6101a.zzbv();
            return this.zzc;
        }
    }
}
