package com.google.android.odml.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
public class BitmapMlImageBuilder {
    private final Bitmap zza;
    private int zzb;
    private Rect zzc;

    public BitmapMlImageBuilder(@NonNull Context context, @NonNull Uri uri) {
        this(MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri));
    }

    public BitmapMlImageBuilder(@NonNull Bitmap bitmap) {
        this.zza = bitmap;
        this.zzb = 0;
        this.zzc = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
    }

    @NonNull
    public MlImage build() {
        return new MlImage(new zze(this.zza), this.zzb, this.zzc, 0L, this.zza.getWidth(), this.zza.getHeight());
    }

    @NonNull
    public BitmapMlImageBuilder setRotation(int i2) {
        MlImage.c(i2);
        this.zzb = i2;
        return this;
    }
}
