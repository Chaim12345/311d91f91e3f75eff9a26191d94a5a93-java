package androidx.camera.view.transform;

import android.content.ContentResolver;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.camera.core.impl.utils.Exif;
import androidx.camera.view.TransformExperimental;
import androidx.camera.view.TransformUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
@TransformExperimental
/* loaded from: classes.dex */
public final class FileTransformFactory {
    private boolean mUsingExifOrientation;

    @NonNull
    public OutputTransform getOutputTransform(@NonNull ContentResolver contentResolver, @NonNull Uri uri) {
        InputStream openInputStream = contentResolver.openInputStream(uri);
        try {
            OutputTransform outputTransform = getOutputTransform(openInputStream);
            if (openInputStream != null) {
                openInputStream.close();
            }
            return outputTransform;
        } catch (Throwable th) {
            if (openInputStream != null) {
                try {
                    openInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @NonNull
    public OutputTransform getOutputTransform(@NonNull File file) {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            OutputTransform outputTransform = getOutputTransform(fileInputStream);
            fileInputStream.close();
            return outputTransform;
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @NonNull
    public OutputTransform getOutputTransform(@NonNull InputStream inputStream) {
        Exif createFromInputStream = Exif.createFromInputStream(inputStream);
        Rect rect = new Rect(0, 0, createFromInputStream.getWidth(), createFromInputStream.getHeight());
        Matrix normalizedToBuffer = TransformUtils.getNormalizedToBuffer(rect);
        if (this.mUsingExifOrientation) {
            normalizedToBuffer.postConcat(TransformUtils.getExifTransform(createFromInputStream.getOrientation(), createFromInputStream.getWidth(), createFromInputStream.getHeight()));
        }
        return new OutputTransform(normalizedToBuffer, TransformUtils.rectToSize(rect));
    }

    public boolean isUsingExifOrientation() {
        return this.mUsingExifOrientation;
    }

    public void setUsingExifOrientation(boolean z) {
        this.mUsingExifOrientation = z;
    }
}
