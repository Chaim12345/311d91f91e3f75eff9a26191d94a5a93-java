package com.google.mlkit.vision.common.internal;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.vision.common.InputImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
@KeepForSdk
/* loaded from: classes2.dex */
public class ImageUtils {
    private static final GmsLogger zza = new GmsLogger("MLKitImageUtils", "");
    private static ImageUtils zzb = new ImageUtils();

    private ImageUtils() {
    }

    @NonNull
    @KeepForSdk
    public static ImageUtils getInstance() {
        return zzb;
    }

    @NonNull
    @KeepForSdk
    public IObjectWrapper getImageDataWrapper(@NonNull InputImage inputImage) {
        Object obj;
        int format = inputImage.getFormat();
        if (format != -1) {
            if (format != 17) {
                if (format == 35) {
                    obj = inputImage.getMediaImage();
                } else if (format != 842094169) {
                    int format2 = inputImage.getFormat();
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Unsupported image format: ");
                    sb.append(format2);
                    throw new MlKitException(sb.toString(), 3);
                }
            }
            obj = (ByteBuffer) Preconditions.checkNotNull(inputImage.getByteBuffer());
        } else {
            obj = (Bitmap) Preconditions.checkNotNull(inputImage.getBitmapInternal());
        }
        return ObjectWrapper.wrap(obj);
    }

    @KeepForSdk
    public int getMobileVisionImageFormat(@NonNull InputImage inputImage) {
        return inputImage.getFormat();
    }

    @KeepForSdk
    public int getMobileVisionImageSize(@NonNull InputImage inputImage) {
        if (inputImage.getFormat() == -1) {
            return ((Bitmap) Preconditions.checkNotNull(inputImage.getBitmapInternal())).getAllocationByteCount();
        }
        if (inputImage.getFormat() == 17 || inputImage.getFormat() == 842094169) {
            return ((ByteBuffer) Preconditions.checkNotNull(inputImage.getByteBuffer())).limit();
        }
        if (inputImage.getFormat() != 35) {
            return 0;
        }
        return (((Image.Plane[]) Preconditions.checkNotNull(inputImage.getPlanes()))[0].getBuffer().limit() * 3) / 2;
    }

    @Nullable
    @KeepForSdk
    public Matrix getUprightRotationMatrix(int i2, int i3, int i4) {
        if (i4 == 0) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postTranslate((-i2) / 2.0f, (-i3) / 2.0f);
        matrix.postRotate(i4 * 90);
        int i5 = i4 % 2;
        int i6 = i5 != 0 ? i3 : i2;
        if (i5 == 0) {
            i2 = i3;
        }
        matrix.postTranslate(i6 / 2.0f, i2 / 2.0f);
        return matrix;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0051 A[Catch: FileNotFoundException -> 0x00af, TryCatch #1 {FileNotFoundException -> 0x00af, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:29:0x0058, B:30:0x006d, B:42:0x0098, B:44:0x00a2, B:32:0x0072, B:33:0x0076, B:34:0x0079, B:35:0x007d, B:36:0x0081, B:37:0x0085, B:38:0x0089, B:40:0x0090, B:28:0x0051, B:25:0x003f, B:46:0x00a7, B:47:0x00ae), top: B:52:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0072 A[Catch: FileNotFoundException -> 0x00af, TryCatch #1 {FileNotFoundException -> 0x00af, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:29:0x0058, B:30:0x006d, B:42:0x0098, B:44:0x00a2, B:32:0x0072, B:33:0x0076, B:34:0x0079, B:35:0x007d, B:36:0x0081, B:37:0x0085, B:38:0x0089, B:40:0x0090, B:28:0x0051, B:25:0x003f, B:46:0x00a7, B:47:0x00ae), top: B:52:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0076 A[Catch: FileNotFoundException -> 0x00af, TryCatch #1 {FileNotFoundException -> 0x00af, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:29:0x0058, B:30:0x006d, B:42:0x0098, B:44:0x00a2, B:32:0x0072, B:33:0x0076, B:34:0x0079, B:35:0x007d, B:36:0x0081, B:37:0x0085, B:38:0x0089, B:40:0x0090, B:28:0x0051, B:25:0x003f, B:46:0x00a7, B:47:0x00ae), top: B:52:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x007d A[Catch: FileNotFoundException -> 0x00af, TryCatch #1 {FileNotFoundException -> 0x00af, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:29:0x0058, B:30:0x006d, B:42:0x0098, B:44:0x00a2, B:32:0x0072, B:33:0x0076, B:34:0x0079, B:35:0x007d, B:36:0x0081, B:37:0x0085, B:38:0x0089, B:40:0x0090, B:28:0x0051, B:25:0x003f, B:46:0x00a7, B:47:0x00ae), top: B:52:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0081 A[Catch: FileNotFoundException -> 0x00af, TryCatch #1 {FileNotFoundException -> 0x00af, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:29:0x0058, B:30:0x006d, B:42:0x0098, B:44:0x00a2, B:32:0x0072, B:33:0x0076, B:34:0x0079, B:35:0x007d, B:36:0x0081, B:37:0x0085, B:38:0x0089, B:40:0x0090, B:28:0x0051, B:25:0x003f, B:46:0x00a7, B:47:0x00ae), top: B:52:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0085 A[Catch: FileNotFoundException -> 0x00af, TryCatch #1 {FileNotFoundException -> 0x00af, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:29:0x0058, B:30:0x006d, B:42:0x0098, B:44:0x00a2, B:32:0x0072, B:33:0x0076, B:34:0x0079, B:35:0x007d, B:36:0x0081, B:37:0x0085, B:38:0x0089, B:40:0x0090, B:28:0x0051, B:25:0x003f, B:46:0x00a7, B:47:0x00ae), top: B:52:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0089 A[Catch: FileNotFoundException -> 0x00af, TryCatch #1 {FileNotFoundException -> 0x00af, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:29:0x0058, B:30:0x006d, B:42:0x0098, B:44:0x00a2, B:32:0x0072, B:33:0x0076, B:34:0x0079, B:35:0x007d, B:36:0x0081, B:37:0x0085, B:38:0x0089, B:40:0x0090, B:28:0x0051, B:25:0x003f, B:46:0x00a7, B:47:0x00ae), top: B:52:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0090 A[Catch: FileNotFoundException -> 0x00af, TryCatch #1 {FileNotFoundException -> 0x00af, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:29:0x0058, B:30:0x006d, B:42:0x0098, B:44:0x00a2, B:32:0x0072, B:33:0x0076, B:34:0x0079, B:35:0x007d, B:36:0x0081, B:37:0x0085, B:38:0x0089, B:40:0x0090, B:28:0x0051, B:25:0x003f, B:46:0x00a7, B:47:0x00ae), top: B:52:0x0002 }] */
    @NonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Bitmap zza(@NonNull ContentResolver contentResolver, @NonNull Uri uri) {
        ExifInterface exifInterface;
        Matrix matrix;
        Bitmap createBitmap;
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri);
            if (bitmap == null) {
                throw new IOException("The image Uri could not be resolved.");
            }
            int i2 = 0;
            if (FirebaseAnalytics.Param.CONTENT.equals(uri.getScheme()) || "file".equals(uri.getScheme())) {
                try {
                    InputStream openInputStream = contentResolver.openInputStream(uri);
                    exifInterface = openInputStream != null ? new ExifInterface(openInputStream) : null;
                    if (openInputStream != null) {
                        try {
                            openInputStream.close();
                        } catch (IOException e2) {
                            e = e2;
                            zza.e("MLKitImageUtils", "failed to open file to read rotation meta data: ".concat(String.valueOf(uri)), e);
                            if (exifInterface == null) {
                            }
                            Matrix matrix2 = new Matrix();
                            int width = bitmap.getWidth();
                            int height = bitmap.getHeight();
                            switch (i2) {
                            }
                            return matrix == null ? bitmap : bitmap;
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    exifInterface = null;
                }
                if (exifInterface == null) {
                    i2 = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
                }
            }
            Matrix matrix22 = new Matrix();
            int width2 = bitmap.getWidth();
            int height2 = bitmap.getHeight();
            switch (i2) {
                case 2:
                    matrix22 = new Matrix();
                    matrix22.postScale(-1.0f, 1.0f);
                    matrix = matrix22;
                    break;
                case 3:
                    matrix22.postRotate(180.0f);
                    matrix = matrix22;
                    break;
                case 4:
                    matrix22.postScale(1.0f, -1.0f);
                    matrix = matrix22;
                    break;
                case 5:
                    matrix22.postRotate(90.0f);
                    matrix22.postScale(-1.0f, 1.0f);
                    matrix = matrix22;
                    break;
                case 6:
                    matrix22.postRotate(90.0f);
                    matrix = matrix22;
                    break;
                case 7:
                    matrix22.postRotate(-90.0f);
                    matrix22.postScale(-1.0f, 1.0f);
                    matrix = matrix22;
                    break;
                case 8:
                    matrix22.postRotate(-90.0f);
                    matrix = matrix22;
                    break;
                default:
                    matrix = null;
                    break;
            }
            if (matrix == null && bitmap != (createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width2, height2, matrix, true))) {
                bitmap.recycle();
                return createBitmap;
            }
        } catch (FileNotFoundException e4) {
            zza.e("MLKitImageUtils", "Could not open file: ".concat(String.valueOf(uri)), e4);
            throw e4;
        }
    }
}
