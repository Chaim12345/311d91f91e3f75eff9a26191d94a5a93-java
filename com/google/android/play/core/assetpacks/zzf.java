package com.google.android.play.core.assetpacks;

import com.google.android.play.core.tasks.OnFailureListener;
/* loaded from: classes2.dex */
public final /* synthetic */ class zzf implements OnFailureListener {
    public static final /* synthetic */ zzf zza = new zzf();

    private /* synthetic */ zzf() {
    }

    @Override // com.google.android.play.core.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        zzl.zza.zze(String.format("Could not sync active asset packs. %s", exc), new Object[0]);
    }
}
