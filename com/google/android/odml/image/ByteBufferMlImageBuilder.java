package com.google.android.odml.image;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import java.nio.ByteBuffer;
/* loaded from: classes2.dex */
public class ByteBufferMlImageBuilder {
    private final ByteBuffer zza;
    private final int zzb;
    private final int zzc;
    private final int zzd;
    private int zze = 0;
    private Rect zzf;

    public ByteBufferMlImageBuilder(@NonNull ByteBuffer byteBuffer, int i2, int i3, int i4) {
        this.zza = byteBuffer;
        this.zzb = i2;
        this.zzc = i3;
        this.zzd = i4;
        this.zzf = new Rect(0, 0, i2, i3);
    }

    @NonNull
    public MlImage build() {
        return new MlImage(new zzf(this.zza, this.zzd), this.zze, this.zzf, 0L, this.zzb, this.zzc);
    }

    @NonNull
    public ByteBufferMlImageBuilder setRotation(int i2) {
        MlImage.c(i2);
        this.zze = i2;
        return this;
    }
}
