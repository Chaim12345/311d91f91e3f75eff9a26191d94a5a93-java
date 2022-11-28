package com.google.android.play.core.assetpacks;

import android.os.Bundle;
/* loaded from: classes2.dex */
final class zzau extends zzal {
    private final zzco zzc;
    private final zzeb zzd;
    private final zzbe zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzau(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar, zzco zzcoVar, zzeb zzebVar, zzbe zzbeVar) {
        super(zzawVar, zziVar);
        this.zzc = zzcoVar;
        this.zzd = zzebVar;
        this.zze = zzbeVar;
    }

    @Override // com.google.android.play.core.assetpacks.zzal, com.google.android.play.core.internal.zzw
    public final void zzm(Bundle bundle, Bundle bundle2) {
        super.zzm(bundle, bundle2);
        this.f7793a.zze(AssetPackStates.zzb(bundle, this.zzc, this.zzd, this.zze));
    }
}
