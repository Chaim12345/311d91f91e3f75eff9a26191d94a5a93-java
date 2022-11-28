package androidx.car.app;

import android.view.Surface;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public final class SurfaceContainer {
    @Keep
    private final int mDpi;
    @Keep
    private final int mHeight;
    @Nullable
    @Keep
    private final Surface mSurface;
    @Keep
    private final int mWidth;

    private SurfaceContainer() {
        this.mSurface = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDpi = 0;
    }

    public SurfaceContainer(@Nullable Surface surface, int i2, int i3, int i4) {
        this.mSurface = surface;
        this.mWidth = i2;
        this.mHeight = i3;
        this.mDpi = i4;
    }

    public int getDpi() {
        return this.mDpi;
    }

    public int getHeight() {
        return this.mHeight;
    }

    @Nullable
    public Surface getSurface() {
        return this.mSurface;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public String toString() {
        return "[" + this.mSurface + ", " + this.mWidth + "x" + this.mHeight + ", dpi: " + this.mDpi + "]";
    }
}
