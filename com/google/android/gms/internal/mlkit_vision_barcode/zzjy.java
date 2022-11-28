package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
/* loaded from: classes2.dex */
public final class zzjy {
    private final zzjw zza;
    private final Integer zzb;
    private final Integer zzc;
    private final Boolean zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzjy(zzjv zzjvVar, zzjx zzjxVar) {
        zzjw zzjwVar;
        Integer num;
        zzjwVar = zzjvVar.zza;
        this.zza = zzjwVar;
        num = zzjvVar.zzb;
        this.zzb = num;
        this.zzc = null;
        this.zzd = null;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzjy) {
            zzjy zzjyVar = (zzjy) obj;
            return Objects.equal(this.zza, zzjyVar.zza) && Objects.equal(this.zzb, zzjyVar.zzb) && Objects.equal(null, null) && Objects.equal(null, null);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, null, null);
    }

    @Nullable
    @zzdj(zza = 1)
    public final zzjw zza() {
        return this.zza;
    }

    @Nullable
    @zzdj(zza = 2)
    public final Integer zzb() {
        return this.zzb;
    }
}
