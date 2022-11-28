package com.google.mlkit.vision.common.internal;

import com.google.android.gms.tasks.OnFailureListener;
/* loaded from: classes2.dex */
public final /* synthetic */ class zzb implements OnFailureListener {
    public static final /* synthetic */ zzb zza = new zzb();

    private /* synthetic */ zzb() {
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        MobileVisionBase.zzb.e("MobileVisionBase", "Error preloading model resource", exc);
    }
}
