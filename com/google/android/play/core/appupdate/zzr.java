package com.google.android.play.core.appupdate;

import com.google.android.play.core.internal.zzcs;
/* loaded from: classes2.dex */
public final class zzr implements zzcs {
    private final zzcs zza;
    private final zzcs zzb;

    public zzr(zzcs zzcsVar, zzcs zzcsVar2) {
        this.zza = zzcsVar;
        this.zzb = zzcsVar2;
    }

    @Override // com.google.android.play.core.internal.zzcs
    public final /* bridge */ /* synthetic */ Object zza() {
        return new zzq(((zzj) this.zza).zzb(), (zzs) this.zzb.zza());
    }
}
