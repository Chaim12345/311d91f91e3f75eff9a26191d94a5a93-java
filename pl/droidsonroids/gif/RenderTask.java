package pl.droidsonroids.gif;

import android.os.SystemClock;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class RenderTask extends SafeRunnable {
    /* JADX INFO: Access modifiers changed from: package-private */
    public RenderTask(GifDrawable gifDrawable) {
        super(gifDrawable);
    }

    @Override // pl.droidsonroids.gif.SafeRunnable
    public void doWork() {
        GifDrawable gifDrawable = this.f15278a;
        long z = gifDrawable.f15254f.z(gifDrawable.f15253e);
        if (z >= 0) {
            this.f15278a.f15251c = SystemClock.uptimeMillis() + z;
            if (this.f15278a.isVisible() && this.f15278a.f15250b) {
                GifDrawable gifDrawable2 = this.f15278a;
                if (!gifDrawable2.f15256h) {
                    gifDrawable2.f15249a.remove(this);
                    GifDrawable gifDrawable3 = this.f15278a;
                    gifDrawable3.f15258j = gifDrawable3.f15249a.schedule(this, z, TimeUnit.MILLISECONDS);
                }
            }
            if (!this.f15278a.f15255g.isEmpty() && this.f15278a.getCurrentFrameIndex() == this.f15278a.f15254f.m() - 1) {
                GifDrawable gifDrawable4 = this.f15278a;
                gifDrawable4.f15257i.sendEmptyMessageAtTime(gifDrawable4.getCurrentLoop(), this.f15278a.f15251c);
            }
        } else {
            GifDrawable gifDrawable5 = this.f15278a;
            gifDrawable5.f15251c = Long.MIN_VALUE;
            gifDrawable5.f15250b = false;
        }
        if (!this.f15278a.isVisible() || this.f15278a.f15257i.hasMessages(-1)) {
            return;
        }
        this.f15278a.f15257i.sendEmptyMessageAtTime(-1, 0L);
    }
}
