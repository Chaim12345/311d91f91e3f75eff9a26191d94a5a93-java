package androidx.camera.core;

import android.media.ImageReader;
import androidx.annotation.NonNull;
import androidx.camera.core.impl.ImageReaderProxy;
/* loaded from: classes.dex */
final class ImageReaderProxys {
    private ImageReaderProxys() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static ImageReaderProxy a(int i2, int i3, int i4, int i5) {
        return new AndroidImageReaderProxy(ImageReader.newInstance(i2, i3, i4, i5));
    }
}
