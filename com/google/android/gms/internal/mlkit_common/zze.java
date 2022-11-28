package com.google.android.gms.internal.mlkit_common;

import java.util.Objects;
/* loaded from: classes.dex */
public final class zze {
    private final zzaj zza = zzam.zzg();
    private Boolean zzb;

    private zze() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zze(zzd zzdVar) {
    }

    public final zze zza() {
        zzab.zzd(this.zzb == null, "A SourcePolicy can only set internal() or external() once.");
        this.zzb = Boolean.FALSE;
        return this;
    }

    public final zze zzb() {
        zzab.zzd(this.zzb == null, "A SourcePolicy can only set internal() or external() once.");
        this.zzb = Boolean.TRUE;
        return this;
    }

    public final zzg zzc() {
        Objects.requireNonNull(this.zzb, "Must call internal() or external() when building a SourcePolicy.");
        return new zzg(this.zzb.booleanValue(), false, this.zza.zzc(), null);
    }
}
