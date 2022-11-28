package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
/* loaded from: classes2.dex */
public final class zzdy {
    private final zzkj zza;
    private final Boolean zzc;
    private final zzmy zze;
    private final zzcc zzf;
    private final zzcc zzg;
    private final Boolean zzb = null;
    private final zzjy zzd = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzdy(zzdw zzdwVar, zzdx zzdxVar) {
        this.zza = zzdw.c(zzdwVar);
        this.zzc = zzdw.e(zzdwVar);
        this.zze = zzdw.d(zzdwVar);
        this.zzf = zzdw.a(zzdwVar);
        this.zzg = zzdw.b(zzdwVar);
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzdy) {
            zzdy zzdyVar = (zzdy) obj;
            return Objects.equal(this.zza, zzdyVar.zza) && Objects.equal(null, null) && Objects.equal(this.zzc, zzdyVar.zzc) && Objects.equal(null, null) && Objects.equal(this.zze, zzdyVar.zze) && Objects.equal(this.zzf, zzdyVar.zzf) && Objects.equal(this.zzg, zzdyVar.zzg);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, null, this.zzc, null, this.zze, this.zzf, this.zzg);
    }

    @Nullable
    @zzdj(zza = 6)
    public final zzcc zza() {
        return this.zzf;
    }

    @Nullable
    @zzdj(zza = 7)
    public final zzcc zzb() {
        return this.zzg;
    }

    @Nullable
    @zzdj(zza = 1)
    public final zzkj zzc() {
        return this.zza;
    }

    @Nullable
    @zzdj(zza = 5)
    public final zzmy zzd() {
        return this.zze;
    }

    @Nullable
    @zzdj(zza = 3)
    public final Boolean zze() {
        return this.zzc;
    }
}
