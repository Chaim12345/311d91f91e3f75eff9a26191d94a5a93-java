package com.google.mlkit.vision.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_vision_common.zzkc;
import com.google.android.gms.internal.mlkit_vision_common.zzke;
import com.google.mlkit.common.sdkinternal.MLTaskInput;
import com.google.mlkit.vision.common.internal.ImageConvertUtils;
import com.google.mlkit.vision.common.internal.ImageUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes2.dex */
public class InputImage implements MLTaskInput {
    @KeepForSdk
    public static final int IMAGE_FORMAT_BITMAP = -1;
    public static final int IMAGE_FORMAT_NV21 = 17;
    public static final int IMAGE_FORMAT_YUV_420_888 = 35;
    public static final int IMAGE_FORMAT_YV12 = 842094169;
    @Nullable
    private volatile Bitmap zza;
    @Nullable
    private volatile ByteBuffer zzb;
    @Nullable
    private volatile zzb zzc;
    private final int zzd;
    private final int zze;
    private final int zzf;
    @ImageFormat
    private final int zzg;
    @Nullable
    private final Matrix zzh;

    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes2.dex */
    public @interface ImageFormat {
    }

    private InputImage(@NonNull Bitmap bitmap, int i2) {
        this.zza = (Bitmap) Preconditions.checkNotNull(bitmap);
        this.zzd = bitmap.getWidth();
        this.zze = bitmap.getHeight();
        this.zzf = i2;
        this.zzg = -1;
        this.zzh = null;
    }

    private InputImage(@NonNull Image image, int i2, int i3, int i4, @Nullable Matrix matrix) {
        Preconditions.checkNotNull(image);
        this.zzc = new zzb(image);
        this.zzd = i2;
        this.zze = i3;
        this.zzf = i4;
        this.zzg = 35;
        this.zzh = matrix;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private InputImage(@NonNull ByteBuffer byteBuffer, int i2, int i3, int i4, @ImageFormat int i5) {
        boolean z;
        if (i5 != 842094169) {
            if (i5 != 17) {
                z = false;
                Preconditions.checkArgument(z);
                this.zzb = (ByteBuffer) Preconditions.checkNotNull(byteBuffer);
                Preconditions.checkArgument(byteBuffer.limit() > i2 * i3, "Image dimension, ByteBuffer size and format don't match. Please check if the ByteBuffer is in the decalred format.");
                byteBuffer.rewind();
                this.zzd = i2;
                this.zze = i3;
                this.zzf = i4;
                this.zzg = i5;
                this.zzh = null;
            }
            i5 = 17;
        }
        z = true;
        Preconditions.checkArgument(z);
        this.zzb = (ByteBuffer) Preconditions.checkNotNull(byteBuffer);
        Preconditions.checkArgument(byteBuffer.limit() > i2 * i3, "Image dimension, ByteBuffer size and format don't match. Please check if the ByteBuffer is in the decalred format.");
        byteBuffer.rewind();
        this.zzd = i2;
        this.zze = i3;
        this.zzf = i4;
        this.zzg = i5;
        this.zzh = null;
    }

    @NonNull
    public static InputImage fromBitmap(@NonNull Bitmap bitmap, int i2) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        InputImage inputImage = new InputImage(bitmap, i2);
        zzb(-1, 1, elapsedRealtime, bitmap.getHeight(), bitmap.getWidth(), bitmap.getAllocationByteCount(), i2);
        return inputImage;
    }

    @NonNull
    public static InputImage fromByteArray(@NonNull byte[] bArr, int i2, int i3, int i4, @ImageFormat int i5) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        InputImage inputImage = new InputImage(ByteBuffer.wrap((byte[]) Preconditions.checkNotNull(bArr)), i2, i3, i4, i5);
        zzb(i5, 2, elapsedRealtime, i3, i2, bArr.length, i4);
        return inputImage;
    }

    @NonNull
    public static InputImage fromByteBuffer(@NonNull ByteBuffer byteBuffer, int i2, int i3, int i4, @ImageFormat int i5) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        InputImage inputImage = new InputImage(byteBuffer, i2, i3, i4, i5);
        zzb(i5, 3, elapsedRealtime, i3, i2, byteBuffer.limit(), i4);
        return inputImage;
    }

    @NonNull
    public static InputImage fromFilePath(@NonNull Context context, @NonNull Uri uri) {
        Preconditions.checkNotNull(context, "Please provide a valid Context");
        Preconditions.checkNotNull(uri, "Please provide a valid imageUri");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Bitmap zza = ImageUtils.getInstance().zza(context.getContentResolver(), uri);
        InputImage inputImage = new InputImage(zza, 0);
        zzb(-1, 4, elapsedRealtime, zza.getHeight(), zza.getWidth(), zza.getAllocationByteCount(), 0);
        return inputImage;
    }

    @NonNull
    public static InputImage fromMediaImage(@NonNull Image image, int i2) {
        return zza(image, i2, null);
    }

    @NonNull
    @KeepForSdk
    public static InputImage fromMediaImage(@NonNull Image image, int i2, @NonNull Matrix matrix) {
        Preconditions.checkArgument(image.getFormat() == 35, "Only YUV_420_888 is supported now");
        return zza(image, i2, matrix);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static InputImage zza(@NonNull Image image, int i2, @Nullable Matrix matrix) {
        boolean z;
        InputImage inputImage;
        int limit;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Preconditions.checkNotNull(image, "Please provide a valid image");
        boolean z2 = true;
        if (i2 != 0 && i2 != 90 && i2 != 180) {
            if (i2 != 270) {
                z = false;
                Preconditions.checkArgument(z, "Invalid rotation. Only 0, 90, 180, 270 are supported currently.");
                if (image.getFormat() != 256 && image.getFormat() != 35) {
                    z2 = false;
                }
                Preconditions.checkArgument(z2, "Only JPEG and YUV_420_888 are supported now");
                Image.Plane[] planes = image.getPlanes();
                if (image.getFormat() != 256) {
                    limit = image.getPlanes()[0].getBuffer().limit();
                    inputImage = new InputImage(ImageConvertUtils.getInstance().convertJpegToUpRightBitmap(image, i2), 0);
                } else {
                    for (Image.Plane plane : planes) {
                        if (plane.getBuffer() != null) {
                            plane.getBuffer().rewind();
                        }
                    }
                    inputImage = new InputImage(image, image.getWidth(), image.getHeight(), i2, matrix);
                    limit = (image.getPlanes()[0].getBuffer().limit() * 3) / 2;
                }
                int i3 = limit;
                InputImage inputImage2 = inputImage;
                zzb(image.getFormat(), 5, elapsedRealtime, image.getHeight(), image.getWidth(), i3, i2);
                return inputImage2;
            }
            i2 = 270;
        }
        z = true;
        Preconditions.checkArgument(z, "Invalid rotation. Only 0, 90, 180, 270 are supported currently.");
        if (image.getFormat() != 256) {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "Only JPEG and YUV_420_888 are supported now");
        Image.Plane[] planes2 = image.getPlanes();
        if (image.getFormat() != 256) {
        }
        int i32 = limit;
        InputImage inputImage22 = inputImage;
        zzb(image.getFormat(), 5, elapsedRealtime, image.getHeight(), image.getWidth(), i32, i2);
        return inputImage22;
    }

    private static void zzb(int i2, int i3, long j2, int i4, int i5, int i6, int i7) {
        zzke.zza(zzkc.zzb("vision-common"), i2, i3, j2, i4, i5, i6, i7);
    }

    @Nullable
    @KeepForSdk
    public Bitmap getBitmapInternal() {
        return this.zza;
    }

    @Nullable
    @KeepForSdk
    public ByteBuffer getByteBuffer() {
        return this.zzb;
    }

    @Nullable
    @KeepForSdk
    public Matrix getCoordinatesMatrix() {
        return this.zzh;
    }

    @KeepForSdk
    @ImageFormat
    public int getFormat() {
        return this.zzg;
    }

    @KeepForSdk
    public int getHeight() {
        return this.zze;
    }

    @Nullable
    @KeepForSdk
    public Image getMediaImage() {
        if (this.zzc == null) {
            return null;
        }
        return this.zzc.a();
    }

    @Nullable
    @KeepForSdk
    public Image.Plane[] getPlanes() {
        if (this.zzc == null) {
            return null;
        }
        return this.zzc.b();
    }

    @KeepForSdk
    public int getRotationDegrees() {
        return this.zzf;
    }

    @KeepForSdk
    public int getWidth() {
        return this.zzd;
    }
}
