package pl.droidsonroids.gif;

import java.lang.Thread;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public abstract class SafeRunnable implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final GifDrawable f15278a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SafeRunnable(GifDrawable gifDrawable) {
        this.f15278a = gifDrawable;
    }

    abstract void doWork();

    @Override // java.lang.Runnable
    public final void run() {
        try {
            if (this.f15278a.isRecycled()) {
                return;
            }
            doWork();
        } catch (Throwable th) {
            Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            if (defaultUncaughtExceptionHandler != null) {
                defaultUncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
            }
            throw th;
        }
    }
}
