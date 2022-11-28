package com.google.android.play.core.splitinstall;

import android.content.Context;
import com.google.android.play.core.internal.zzcs;
/* loaded from: classes2.dex */
public final class zzt implements zzcs {
    private final zzcs zza;

    public zzt(zzcs zzcsVar) {
        this.zza = zzcsVar;
    }

    @Override // com.google.android.play.core.internal.zzcs
    public final /* bridge */ /* synthetic */ Object zza() {
        return new zzs((Context) this.zza.zza());
    }
}
