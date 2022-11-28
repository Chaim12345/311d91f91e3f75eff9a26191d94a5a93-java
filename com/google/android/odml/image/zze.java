package com.google.android.odml.image;

import android.graphics.Bitmap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zze implements zzg {
    private final Bitmap zza;
    private final ImageProperties zzb;

    public zze(Bitmap bitmap) {
        this.zza = bitmap;
        zzb zzbVar = new zzb();
        int i2 = zzd.f7737a[bitmap.getConfig().ordinal()];
        zzbVar.a(i2 != 1 ? i2 != 2 ? 0 : 1 : 8);
        zzbVar.b(1);
        this.zzb = zzbVar.c();
    }

    public final Bitmap zza() {
        return this.zza;
    }

    @Override // com.google.android.odml.image.zzg
    public final ImageProperties zzb() {
        return this.zzb;
    }

    @Override // com.google.android.odml.image.zzg
    public final void zzc() {
        this.zza.recycle();
    }
}
