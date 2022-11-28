package com.google.android.odml.image;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import java.io.Closeable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;
/* loaded from: classes2.dex */
public class MlImage implements Closeable {
    public static final int IMAGE_FORMAT_ALPHA = 8;
    public static final int IMAGE_FORMAT_JPEG = 9;
    public static final int IMAGE_FORMAT_NV12 = 3;
    public static final int IMAGE_FORMAT_NV21 = 4;
    public static final int IMAGE_FORMAT_RGB = 2;
    public static final int IMAGE_FORMAT_RGBA = 1;
    public static final int IMAGE_FORMAT_UNKNOWN = 0;
    public static final int IMAGE_FORMAT_YUV_420_888 = 7;
    public static final int IMAGE_FORMAT_YV12 = 5;
    public static final int IMAGE_FORMAT_YV21 = 6;
    public static final int STORAGE_TYPE_BITMAP = 1;
    public static final int STORAGE_TYPE_BYTEBUFFER = 2;
    public static final int STORAGE_TYPE_MEDIA_IMAGE = 3;
    private final zzg zza;
    private final int zzb;
    private final Rect zzc;
    private final int zzd;
    private final int zze;
    private int zzf;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ImageFormat {
    }

    /* loaded from: classes2.dex */
    public static final class Internal {
        private final MlImage zza;

        /* synthetic */ Internal(MlImage mlImage, zzj zzjVar) {
            this.zza = mlImage;
        }

        public void acquire() {
            this.zza.zzd();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface StorageType {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MlImage(zzg zzgVar, int i2, Rect rect, long j2, int i3, int i4) {
        this.zza = zzgVar;
        this.zzb = i2;
        Rect rect2 = new Rect();
        this.zzc = rect2;
        rect2.set(rect);
        this.zzd = i3;
        this.zze = i4;
        this.zzf = 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void c(int i2) {
        if (i2 == 0 || i2 == 90 || i2 == 180 || i2 == 270) {
            return;
        }
        StringBuilder sb = new StringBuilder(68);
        sb.append("Rotation value ");
        sb.append(i2);
        sb.append(" is not valid. Use only 0, 90, 180 or 270.");
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void zzd() {
        this.zzf++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzg a() {
        return this.zza;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        int i2 = this.zzf - 1;
        this.zzf = i2;
        if (i2 == 0) {
            this.zza.zzc();
        }
    }

    @NonNull
    public List<ImageProperties> getContainedImageProperties() {
        return Collections.singletonList(this.zza.zzb());
    }

    public int getHeight() {
        return this.zze;
    }

    @NonNull
    public Internal getInternal() {
        return new Internal(this, null);
    }

    public int getRotation() {
        return this.zzb;
    }

    public int getWidth() {
        return this.zzd;
    }
}
