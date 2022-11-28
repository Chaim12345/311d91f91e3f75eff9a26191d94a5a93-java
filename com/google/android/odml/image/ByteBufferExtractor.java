package com.google.android.odml.image;

import androidx.annotation.NonNull;
import java.nio.ByteBuffer;
/* loaded from: classes2.dex */
public class ByteBufferExtractor {
    private ByteBufferExtractor() {
    }

    @NonNull
    public static ByteBuffer extract(@NonNull MlImage mlImage) {
        zzg a2 = mlImage.a();
        if (a2.zzb().getStorageType() == 2) {
            return ((zzf) a2).zza().asReadOnlyBuffer();
        }
        throw new IllegalArgumentException("Extract ByteBuffer from an MlImage created by objects other than Bytebuffer is not supported");
    }
}
