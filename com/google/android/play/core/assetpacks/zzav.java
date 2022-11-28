package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import java.util.List;
/* loaded from: classes2.dex */
final class zzav extends zzal {
    private final List zzc;
    private final zzco zzd;
    private final zzeb zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzav(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar, zzco zzcoVar, zzeb zzebVar, List list) {
        super(zzawVar, zziVar);
        this.zzd = zzcoVar;
        this.zze = zzebVar;
        this.zzc = list;
    }

    @Override // com.google.android.play.core.assetpacks.zzal, com.google.android.play.core.internal.zzw
    public final void zzn(int i2, Bundle bundle) {
        super.zzn(i2, bundle);
        this.f7793a.zze(AssetPackStates.zzc(bundle, this.zzd, this.zze, this.zzc));
    }
}
