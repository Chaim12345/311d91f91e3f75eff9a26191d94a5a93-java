package androidx.camera.core.internal.compat;

import android.media.ImageWriter;
import android.os.Build;
import android.view.Surface;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.util.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@RequiresApi(26)
/* loaded from: classes.dex */
final class ImageWriterCompatApi26Impl {
    private static final String TAG = "ImageWriterCompatApi26";
    private static Method sNewInstanceMethod;

    static {
        try {
            Class cls = Integer.TYPE;
            sNewInstanceMethod = ImageWriter.class.getMethod("newInstance", Surface.class, cls, cls);
        } catch (NoSuchMethodException unused) {
        }
    }

    private ImageWriterCompatApi26Impl() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static ImageWriter a(@NonNull Surface surface, @IntRange(from = 1) int i2, int i3) {
        Throwable th = null;
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                return (ImageWriter) Preconditions.checkNotNull(sNewInstanceMethod.invoke(null, surface, Integer.valueOf(i2), Integer.valueOf(i3)));
            } catch (IllegalAccessException | InvocationTargetException e2) {
                th = e2;
            }
        }
        throw new RuntimeException("Unable to invoke newInstance(Surface, int, int) via reflection.", th);
    }
}
