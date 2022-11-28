package com.google.mlkit.vision.common.internal;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.Image;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.mlkit_vision_common.zzkc;
import com.google.android.gms.internal.mlkit_vision_common.zzke;
import com.google.android.odml.image.BitmapExtractor;
import com.google.android.odml.image.ByteBufferExtractor;
import com.google.android.odml.image.ImageProperties;
import com.google.android.odml.image.MediaImageExtractor;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.vision.common.InputImage;
import java.nio.ByteBuffer;
import java.util.List;
@KeepForSdk
/* loaded from: classes2.dex */
public class CommonConvertUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00b5 A[RETURN] */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static InputImage a(@NonNull MlImage mlImage) {
        int i2;
        Integer valueOf;
        ImageProperties imageProperties = mlImage.getContainedImageProperties().get(0);
        int storageType = imageProperties.getStorageType();
        if (storageType == 1) {
            Bitmap extract = BitmapExtractor.extract(mlImage);
            zzb(-1, 1, SystemClock.elapsedRealtime(), mlImage.getHeight(), mlImage.getWidth(), extract.getAllocationByteCount(), mlImage.getRotation());
            return InputImage.fromBitmap(extract, mlImage.getRotation());
        } else if (storageType != 2) {
            if (storageType != 3) {
                return null;
            }
            Image extract2 = MediaImageExtractor.extract(mlImage);
            zzb(extract2.getFormat(), 5, SystemClock.elapsedRealtime(), mlImage.getHeight(), mlImage.getWidth(), extract2.getFormat() == 256 ? extract2.getPlanes()[0].getBuffer().limit() : (extract2.getPlanes()[0].getBuffer().limit() * 3) / 2, mlImage.getRotation());
            return InputImage.fromMediaImage(extract2, mlImage.getRotation());
        } else {
            ByteBuffer extract3 = ByteBufferExtractor.extract(mlImage);
            int imageFormat = imageProperties.getImageFormat();
            if (imageFormat == 4) {
                i2 = 17;
            } else if (imageFormat != 5) {
                valueOf = null;
                if (valueOf == null) {
                    zzb(valueOf.intValue(), 3, SystemClock.elapsedRealtime(), mlImage.getHeight(), mlImage.getWidth(), extract3.limit(), mlImage.getRotation());
                    return InputImage.fromByteBuffer(extract3, mlImage.getWidth(), mlImage.getHeight(), mlImage.getRotation(), valueOf.intValue());
                }
                return null;
            } else {
                i2 = InputImage.IMAGE_FORMAT_YV12;
            }
            valueOf = Integer.valueOf(i2);
            if (valueOf == null) {
            }
        }
    }

    @KeepForSdk
    public static int convertToAndroidImageFormat(@InputImage.ImageFormat int i2) {
        int i3 = 17;
        if (i2 != 17) {
            i3 = 35;
            if (i2 != 35) {
                i3 = InputImage.IMAGE_FORMAT_YV12;
                if (i2 != 842094169) {
                    return 0;
                }
            }
        }
        return i3;
    }

    @KeepForSdk
    public static int convertToMVRotation(int i2) {
        if (i2 != 0) {
            if (i2 != 90) {
                if (i2 != 180) {
                    if (i2 == 270) {
                        return 3;
                    }
                    StringBuilder sb = new StringBuilder(29);
                    sb.append("Invalid rotation: ");
                    sb.append(i2);
                    throw new IllegalArgumentException(sb.toString());
                }
                return 2;
            }
            return 1;
        }
        return 0;
    }

    @KeepForSdk
    public static void transformPointArray(@NonNull Point[] pointArr, @NonNull Matrix matrix) {
        int length = pointArr.length;
        float[] fArr = new float[length + length];
        for (int i2 = 0; i2 < pointArr.length; i2++) {
            int i3 = i2 + i2;
            fArr[i3] = pointArr[i2].x;
            fArr[i3 + 1] = pointArr[i2].y;
        }
        matrix.mapPoints(fArr);
        for (int i4 = 0; i4 < pointArr.length; i4++) {
            int i5 = i4 + i4;
            pointArr[i4].set((int) fArr[i5], (int) fArr[i5 + 1]);
        }
    }

    @KeepForSdk
    public static void transformPointF(@NonNull PointF pointF, @NonNull Matrix matrix) {
        float[] fArr = {pointF.x, pointF.y};
        matrix.mapPoints(fArr);
        pointF.set(fArr[0], fArr[1]);
    }

    @KeepForSdk
    public static void transformPointList(@NonNull List<PointF> list, @NonNull Matrix matrix) {
        int size = list.size();
        float[] fArr = new float[size + size];
        for (int i2 = 0; i2 < list.size(); i2++) {
            int i3 = i2 + i2;
            fArr[i3] = list.get(i2).x;
            fArr[i3 + 1] = list.get(i2).y;
        }
        matrix.mapPoints(fArr);
        for (int i4 = 0; i4 < list.size(); i4++) {
            int i5 = i4 + i4;
            list.get(i4).set(fArr[i5], fArr[i5 + 1]);
        }
    }

    @KeepForSdk
    public static void transformRect(@NonNull Rect rect, @NonNull Matrix matrix) {
        RectF rectF = new RectF(rect);
        matrix.mapRect(rectF);
        rect.set((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
    }

    private static void zzb(int i2, int i3, long j2, int i4, int i5, int i6, int i7) {
        zzke.zzb(zzkc.zzb("vision-common"), i2, i3, j2, i4, i5, i6, i7);
    }
}
