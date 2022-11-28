package com.google.android.gms.internal.mlkit_common;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
/* loaded from: classes.dex */
public final class zziu {
    private final zziq zza;
    private final zzis zzb;
    private final zzis zzc;
    private final Boolean zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zziu(zzir zzirVar, zzit zzitVar) {
        zziq zziqVar;
        zziqVar = zzirVar.zza;
        this.zza = zziqVar;
        this.zzb = null;
        this.zzc = null;
        this.zzd = null;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof zziu) && Objects.equal(this.zza, ((zziu) obj).zza) && Objects.equal(null, null) && Objects.equal(null, null) && Objects.equal(null, null);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, null, null, null);
    }

    @Nullable
    @zzbi(zza = 1)
    public final zziq zza() {
        return this.zza;
    }
}
