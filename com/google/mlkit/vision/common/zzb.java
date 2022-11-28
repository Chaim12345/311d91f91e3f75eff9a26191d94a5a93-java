package com.google.mlkit.vision.common;

import android.media.Image;
/* loaded from: classes2.dex */
final class zzb {
    private final Image zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzb(Image image) {
        this.zza = image;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Image a() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Image.Plane[] b() {
        return this.zza.getPlanes();
    }
}
