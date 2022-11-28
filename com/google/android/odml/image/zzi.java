package com.google.android.odml.image;

import android.media.Image;
import android.os.Build;
import androidx.annotation.RequiresApi;
/* JADX INFO: Access modifiers changed from: package-private */
@RequiresApi(19)
/* loaded from: classes2.dex */
public final class zzi implements zzg {
    private final Image zza;
    private final ImageProperties zzb;

    public zzi(Image image) {
        int i2;
        this.zza = image;
        zzb zzbVar = new zzb();
        zzbVar.b(3);
        int format = image.getFormat();
        if (Build.VERSION.SDK_INT >= 23) {
            if (format == 42) {
                i2 = 1;
            } else if (format == 41) {
                i2 = 2;
            }
            zzbVar.a(i2);
            this.zzb = zzbVar.c();
        }
        i2 = format != 35 ? format != 256 ? 0 : 9 : 7;
        zzbVar.a(i2);
        this.zzb = zzbVar.c();
    }

    public final Image zza() {
        return this.zza;
    }

    @Override // com.google.android.odml.image.zzg
    public final ImageProperties zzb() {
        return this.zzb;
    }

    @Override // com.google.android.odml.image.zzg
    public final void zzc() {
        this.zza.close();
    }
}
