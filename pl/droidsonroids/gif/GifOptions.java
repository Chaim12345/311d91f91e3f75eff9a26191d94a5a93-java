package pl.droidsonroids.gif;

import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
/* loaded from: classes4.dex */
public class GifOptions {

    /* renamed from: a  reason: collision with root package name */
    char f15265a;

    /* renamed from: b  reason: collision with root package name */
    boolean f15266b;

    public GifOptions() {
        reset();
    }

    private void reset() {
        this.f15265a = (char) 1;
        this.f15266b = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(@Nullable GifOptions gifOptions) {
        if (gifOptions == null) {
            reset();
            return;
        }
        this.f15266b = gifOptions.f15266b;
        this.f15265a = gifOptions.f15265a;
    }

    public void setInIsOpaque(boolean z) {
        this.f15266b = z;
    }

    public void setInSampleSize(@IntRange(from = 1, to = 65535) int i2) {
        if (i2 < 1 || i2 > 65535) {
            this.f15265a = (char) 1;
        } else {
            this.f15265a = (char) i2;
        }
    }
}
