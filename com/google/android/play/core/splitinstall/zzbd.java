package com.google.android.play.core.splitinstall;

import android.content.Context;
import com.google.android.play.core.internal.zzcs;
/* loaded from: classes2.dex */
public final class zzbd implements zzcs {
    private final zzcs zza;

    public zzbd(zzcs zzcsVar) {
        this.zza = zzcsVar;
    }

    @Override // com.google.android.play.core.internal.zzcs
    public final /* bridge */ /* synthetic */ Object zza() {
        Context zzb = ((zzad) this.zza).zzb();
        return new zzbc(zzb, zzb.getPackageName());
    }
}
