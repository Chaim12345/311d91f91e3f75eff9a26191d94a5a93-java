package com.google.android.gms.internal.mlkit_common;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
/* loaded from: classes.dex */
public final class zziq {
    private final String zza;
    private final String zzb;
    private final zzio zzc;
    private final String zzd;
    private final String zze;
    private final zzin zzf;
    private final Long zzg;
    private final Boolean zzh;
    private final Boolean zzi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zziq(zzim zzimVar, zzip zzipVar) {
        String str;
        zzio zzioVar;
        String str2;
        zzin zzinVar;
        str = zzimVar.zza;
        this.zza = str;
        this.zzb = null;
        zzioVar = zzimVar.zzb;
        this.zzc = zzioVar;
        this.zzd = null;
        str2 = zzimVar.zzc;
        this.zze = str2;
        zzinVar = zzimVar.zzd;
        this.zzf = zzinVar;
        this.zzg = null;
        this.zzh = null;
        this.zzi = null;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zziq) {
            zziq zziqVar = (zziq) obj;
            return Objects.equal(this.zza, zziqVar.zza) && Objects.equal(null, null) && Objects.equal(this.zzc, zziqVar.zzc) && Objects.equal(null, null) && Objects.equal(this.zze, zziqVar.zze) && Objects.equal(this.zzf, zziqVar.zzf) && Objects.equal(null, null) && Objects.equal(null, null) && Objects.equal(null, null);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, null, this.zzc, null, this.zze, this.zzf, null, null, null);
    }

    @Nullable
    @zzbi(zza = 6)
    public final zzin zza() {
        return this.zzf;
    }

    @Nullable
    @zzbi(zza = 3)
    public final zzio zzb() {
        return this.zzc;
    }

    @Nullable
    @zzbi(zza = 5)
    public final String zzc() {
        return this.zze;
    }

    @Nullable
    @zzbi(zza = 1)
    public final String zzd() {
        return this.zza;
    }
}
