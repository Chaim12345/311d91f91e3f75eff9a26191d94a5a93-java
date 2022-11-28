package com.google.android.gms.internal.measurement;
/* loaded from: classes.dex */
final class zzln implements zzlu {
    private final zzlj zza;
    private final zzml zzb;
    private final boolean zzc;
    private final zzjp zzd;

    private zzln(zzml zzmlVar, zzjp zzjpVar, zzlj zzljVar) {
        this.zzb = zzmlVar;
        this.zzc = zzjpVar.c(zzljVar);
        this.zzd = zzjpVar;
        this.zza = zzljVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzln a(zzml zzmlVar, zzjp zzjpVar, zzlj zzljVar) {
        return new zzln(zzmlVar, zzjpVar, zzljVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final int zza(Object obj) {
        zzml zzmlVar = this.zzb;
        int b2 = zzmlVar.b(zzmlVar.c(obj));
        if (this.zzc) {
            this.zzd.a(obj);
            throw null;
        }
        return b2;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final int zzb(Object obj) {
        int hashCode = this.zzb.c(obj).hashCode();
        if (this.zzc) {
            this.zzd.a(obj);
            throw null;
        }
        return hashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final Object zze() {
        return this.zza.zzbI().zzaG();
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zzf(Object obj) {
        this.zzb.g(obj);
        this.zzd.b(obj);
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zzg(Object obj, Object obj2) {
        zzlw.d(this.zzb, obj, obj2);
        if (this.zzc) {
            zzlw.c(this.zzd, obj, obj2);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zzh(Object obj, byte[] bArr, int i2, int i3, zzio zzioVar) {
        zzkc zzkcVar = (zzkc) obj;
        if (zzkcVar.zzc == zzmm.zzc()) {
            zzkcVar.zzc = zzmm.b();
        }
        zzjz zzjzVar = (zzjz) obj;
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zzi(Object obj, zznd zzndVar) {
        this.zzd.a(obj);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final boolean zzj(Object obj, Object obj2) {
        if (this.zzb.c(obj).equals(this.zzb.c(obj2))) {
            if (this.zzc) {
                this.zzd.a(obj);
                this.zzd.a(obj2);
                throw null;
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final boolean zzk(Object obj) {
        this.zzd.a(obj);
        throw null;
    }
}
