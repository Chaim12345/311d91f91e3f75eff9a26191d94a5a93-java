package pl.droidsonroids.gif;

import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
/* loaded from: classes4.dex */
public class GifTexImage2D {
    private final GifInfoHandle mGifInfoHandle;

    public GifTexImage2D(InputSource inputSource, @Nullable GifOptions gifOptions) {
        gifOptions = gifOptions == null ? new GifOptions() : gifOptions;
        GifInfoHandle c2 = inputSource.c();
        this.mGifInfoHandle = c2;
        c2.I(gifOptions.f15265a, gifOptions.f15266b);
        c2.s();
    }

    protected final void finalize() {
        try {
            recycle();
        } finally {
            super.finalize();
        }
    }

    public int getCurrentFrameIndex() {
        return this.mGifInfoHandle.d();
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

    public int getNumberOfFrames() {
        return this.mGifInfoHandle.m();
    }

    public int getWidth() {
        return this.mGifInfoHandle.p();
    }

    public void glTexImage2D(int i2, int i3) {
        this.mGifInfoHandle.q(i2, i3);
    }

    public void glTexSubImage2D(int i2, int i3) {
        this.mGifInfoHandle.r(i2, i3);
    }

    public void recycle() {
        GifInfoHandle gifInfoHandle = this.mGifInfoHandle;
        if (gifInfoHandle != null) {
            gifInfoHandle.y();
        }
    }

    public void seekToFrame(@IntRange(from = 0) int i2) {
        this.mGifInfoHandle.F(i2);
    }

    public void setSpeed(@FloatRange(from = 0.0d, fromInclusive = false) float f2) {
        this.mGifInfoHandle.J(f2);
    }

    public void startDecoderThread() {
        this.mGifInfoHandle.K();
    }

    public void stopDecoderThread() {
        this.mGifInfoHandle.L();
    }
}
