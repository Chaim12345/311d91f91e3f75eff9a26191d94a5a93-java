package pl.droidsonroids.gif;

import android.graphics.Bitmap;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes4.dex */
public class GifDecoder {
    private final GifInfoHandle mGifInfoHandle;

    public GifDecoder(@NonNull InputSource inputSource) {
        this(inputSource, null);
    }

    public GifDecoder(@NonNull InputSource inputSource, @Nullable GifOptions gifOptions) {
        GifInfoHandle c2 = inputSource.c();
        this.mGifInfoHandle = c2;
        if (gifOptions != null) {
            c2.I(gifOptions.f15265a, gifOptions.f15266b);
        }
    }

    private void checkBuffer(Bitmap bitmap) {
        if (bitmap.isRecycled()) {
            throw new IllegalArgumentException("Bitmap is recycled");
        }
        if (bitmap.getWidth() < this.mGifInfoHandle.p() || bitmap.getHeight() < this.mGifInfoHandle.i()) {
            throw new IllegalArgumentException("Bitmap ia too small, size must be greater than or equal to GIF size");
        }
        if (bitmap.getConfig() == Bitmap.Config.ARGB_8888) {
            return;
        }
        throw new IllegalArgumentException("Only Config.ARGB_8888 is supported. Current bitmap config: " + bitmap.getConfig());
    }

    public long getAllocationByteCount() {
        return this.mGifInfoHandle.b();
    }

    public String getComment() {
        return this.mGifInfoHandle.c();
    }

    public int getDuration() {
        return this.mGifInfoHandle.g();
    }

    public int getFrameDuration(@IntRange(from = 0) int i2) {
        return this.mGifInfoHandle.h(i2);
    }

    public int getHeight() {
        return this.mGifInfoHandle.i();
    }

    public int getLoopCount() {
        return this.mGifInfoHandle.j();
    }

    public int getNumberOfFrames() {
        return this.mGifInfoHandle.m();
    }

    public long getSourceLength() {
        return this.mGifInfoHandle.o();
    }

    public int getWidth() {
        return this.mGifInfoHandle.p();
    }

    public boolean isAnimated() {
        return this.mGifInfoHandle.m() > 1 && getDuration() > 0;
    }

    public void recycle() {
        this.mGifInfoHandle.y();
    }

    public void seekToFrame(@IntRange(from = 0, to = 2147483647L) int i2, @NonNull Bitmap bitmap) {
        checkBuffer(bitmap);
        this.mGifInfoHandle.E(i2, bitmap);
    }

    public void seekToTime(@IntRange(from = 0, to = 2147483647L) int i2, @NonNull Bitmap bitmap) {
        checkBuffer(bitmap);
        this.mGifInfoHandle.G(i2, bitmap);
    }
}
