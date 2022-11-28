package com.google.android.play.core.splitinstall;

import com.google.android.play.core.internal.zzcq;
import com.google.android.play.core.internal.zzcs;
/* loaded from: classes2.dex */
public final class zzm implements zzcs {
    private final zzcs zza;
    private final zzcs zzb;
    private final zzcs zzc;

    public zzm(zzcs zzcsVar, zzcs zzcsVar2, zzcs zzcsVar3) {
        this.zza = zzcsVar;
        this.zzb = zzcsVar2;
        this.zzc = zzcsVar3;
    }

    @Override // com.google.android.play.core.internal.zzcs
    public final /* bridge */ /* synthetic */ Object zza() {
        return new zzl(zzcq.zzb(this.zza), zzcq.zzb(this.zzb), zzcq.zzb(this.zzc));
    }
}
