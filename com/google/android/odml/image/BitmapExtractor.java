package com.google.android.odml.image;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
public final class BitmapExtractor {
    private BitmapExtractor() {
    }

    @NonNull
    public static Bitmap extract(@NonNull MlImage mlImage) {
        zzg a2 = mlImage.a();
        if (a2.zzb().getStorageType() == 1) {
            return ((zze) a2).zza();
        }
        throw new IllegalArgumentException("Extracting Bitmap from an MlImage created by objects other than Bitmap is not supported");
    }
}
