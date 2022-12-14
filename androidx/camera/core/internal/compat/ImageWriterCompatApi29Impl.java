package androidx.camera.core.internal.compat;

import android.media.ImageWriter;
import android.view.Surface;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
@RequiresApi(29)
/* loaded from: classes.dex */
final class ImageWriterCompatApi29Impl {
    private ImageWriterCompatApi29Impl() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static ImageWriter a(@NonNull Surface surface, @IntRange(from = 1) int i2, int i3) {
        return ImageWriter.newInstance(surface, i2, i3);
    }
}
