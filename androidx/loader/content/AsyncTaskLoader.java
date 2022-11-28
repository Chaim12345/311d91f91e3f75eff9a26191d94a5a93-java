package androidx.loader.content;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.os.OperationCanceledException;
import androidx.core.util.TimeUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public abstract class AsyncTaskLoader<D> extends Loader<D> {

    /* renamed from: j  reason: collision with root package name */
    volatile LoadTask f3278j;

    /* renamed from: k  reason: collision with root package name */
    volatile LoadTask f3279k;

    /* renamed from: l  reason: collision with root package name */
    long f3280l;

    /* renamed from: m  reason: collision with root package name */
    long f3281m;
    private final Executor mExecutor;

    /* renamed from: n  reason: collision with root package name */
    Handler f3282n;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class LoadTask extends ModernAsyncTask<Void, Void, D> implements Runnable {

        /* renamed from: c  reason: collision with root package name */
        boolean f3283c;
        private final CountDownLatch mDone = new CountDownLatch(1);

        LoadTask() {
        }

        @Override // androidx.loader.content.ModernAsyncTask
        protected void d(Object obj) {
            try {
                AsyncTaskLoader.this.g(this, obj);
            } finally {
                this.mDone.countDown();
            }
        }

        @Override // androidx.loader.content.ModernAsyncTask
        protected void e(Object obj) {
            try {
                AsyncTaskLoader.this.h(this, obj);
            } finally {
                this.mDone.countDown();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.loader.content.ModernAsyncTask
        /* renamed from: j */
        public Object a(Void... voidArr) {
            try {
                return AsyncTaskLoader.this.j();
            } catch (OperationCanceledException e2) {
                if (isCancelled()) {
                    return null;
                }
                throw e2;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f3283c = false;
            AsyncTaskLoader.this.i();
        }

        public void waitForLoader() {
            try {
                this.mDone.await();
            } catch (InterruptedException unused) {
            }
        }
    }

    public AsyncTaskLoader(@NonNull Context context) {
        this(context, ModernAsyncTask.THREAD_POOL_EXECUTOR);
    }

    private AsyncTaskLoader(@NonNull Context context, @NonNull Executor executor) {
        super(context);
        this.f3281m = -10000L;
        this.mExecutor = executor;
    }

    @Override // androidx.loader.content.Loader
    protected boolean b() {
        if (this.f3278j != null) {
            if (!this.f3295e) {
                this.f3298h = true;
            }
            if (this.f3279k != null) {
                if (this.f3278j.f3283c) {
                    this.f3278j.f3283c = false;
                    this.f3282n.removeCallbacks(this.f3278j);
                }
                this.f3278j = null;
                return false;
            } else if (this.f3278j.f3283c) {
                this.f3278j.f3283c = false;
                this.f3282n.removeCallbacks(this.f3278j);
                this.f3278j = null;
                return false;
            } else {
                boolean cancel = this.f3278j.cancel(false);
                if (cancel) {
                    this.f3279k = this.f3278j;
                    cancelLoadInBackground();
                }
                this.f3278j = null;
                return cancel;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.loader.content.Loader
    public void c() {
        super.c();
        cancelLoad();
        this.f3278j = new LoadTask();
        i();
    }

    public void cancelLoadInBackground() {
    }

    @Override // androidx.loader.content.Loader
    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        if (this.f3278j != null) {
            printWriter.print(str);
            printWriter.print("mTask=");
            printWriter.print(this.f3278j);
            printWriter.print(" waiting=");
            printWriter.println(this.f3278j.f3283c);
        }
        if (this.f3279k != null) {
            printWriter.print(str);
            printWriter.print("mCancellingTask=");
            printWriter.print(this.f3279k);
            printWriter.print(" waiting=");
            printWriter.println(this.f3279k.f3283c);
        }
        if (this.f3280l != 0) {
            printWriter.print(str);
            printWriter.print("mUpdateThrottle=");
            TimeUtils.formatDuration(this.f3280l, printWriter);
            printWriter.print(" mLastLoadCompleteTime=");
            TimeUtils.formatDuration(this.f3281m, SystemClock.uptimeMillis(), printWriter);
            printWriter.println();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    void g(LoadTask loadTask, Object obj) {
        onCanceled(obj);
        if (this.f3279k == loadTask) {
            rollbackContentChanged();
            this.f3281m = SystemClock.uptimeMillis();
            this.f3279k = null;
            deliverCancellation();
            i();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    void h(LoadTask loadTask, Object obj) {
        if (this.f3278j != loadTask) {
            g(loadTask, obj);
        } else if (isAbandoned()) {
            onCanceled(obj);
        } else {
            commitContentChanged();
            this.f3281m = SystemClock.uptimeMillis();
            this.f3278j = null;
            deliverResult(obj);
        }
    }

    void i() {
        if (this.f3279k != null || this.f3278j == null) {
            return;
        }
        if (this.f3278j.f3283c) {
            this.f3278j.f3283c = false;
            this.f3282n.removeCallbacks(this.f3278j);
        }
        if (this.f3280l <= 0 || SystemClock.uptimeMillis() >= this.f3281m + this.f3280l) {
            this.f3278j.executeOnExecutor(this.mExecutor, null);
            return;
        }
        this.f3278j.f3283c = true;
        this.f3282n.postAtTime(this.f3278j, this.f3281m + this.f3280l);
    }

    public boolean isLoadInBackgroundCanceled() {
        return this.f3279k != null;
    }

    @Nullable
    protected Object j() {
        return loadInBackground();
    }

    @Nullable
    public abstract D loadInBackground();

    public void onCanceled(@Nullable D d2) {
    }

    public void setUpdateThrottle(long j2) {
        this.f3280l = j2;
        if (j2 != 0) {
            this.f3282n = new Handler();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void waitForLoader() {
        LoadTask loadTask = this.f3278j;
        if (loadTask != null) {
            loadTask.waitForLoader();
        }
    }
}
