package pl.droidsonroids.gif;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class InvalidationHandler extends Handler {
    private final WeakReference<GifDrawable> mDrawableRef;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InvalidationHandler(GifDrawable gifDrawable) {
        super(Looper.getMainLooper());
        this.mDrawableRef = new WeakReference<>(gifDrawable);
    }

    @Override // android.os.Handler
    public void handleMessage(@NonNull Message message) {
        GifDrawable gifDrawable = this.mDrawableRef.get();
        if (gifDrawable == null) {
            return;
        }
        if (message.what == -1) {
            gifDrawable.invalidateSelf();
            return;
        }
        Iterator it = gifDrawable.f15255g.iterator();
        while (it.hasNext()) {
            ((AnimationListener) it.next()).onAnimationCompleted(message.what);
        }
    }
}
