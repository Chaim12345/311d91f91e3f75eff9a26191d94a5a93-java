package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.arch.core.executor.ArchTaskExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public abstract class ComputableLiveData<T> {

    /* renamed from: a  reason: collision with root package name */
    final Executor f3160a;

    /* renamed from: b  reason: collision with root package name */
    final LiveData f3161b;

    /* renamed from: c  reason: collision with root package name */
    final AtomicBoolean f3162c;

    /* renamed from: d  reason: collision with root package name */
    final AtomicBoolean f3163d;
    @VisibleForTesting

    /* renamed from: e  reason: collision with root package name */
    final Runnable f3164e;
    @VisibleForTesting

    /* renamed from: f  reason: collision with root package name */
    final Runnable f3165f;

    public ComputableLiveData() {
        this(ArchTaskExecutor.getIOThreadExecutor());
    }

    public ComputableLiveData(@NonNull Executor executor) {
        this.f3162c = new AtomicBoolean(true);
        this.f3163d = new AtomicBoolean(false);
        this.f3164e = new Runnable() { // from class: androidx.lifecycle.ComputableLiveData.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            @WorkerThread
            public void run() {
                do {
                    boolean z = false;
                    if (ComputableLiveData.this.f3163d.compareAndSet(false, true)) {
                        Object obj = null;
                        boolean z2 = false;
                        while (ComputableLiveData.this.f3162c.compareAndSet(true, false)) {
                            try {
                                obj = ComputableLiveData.this.a();
                                z2 = true;
                            } catch (Throwable th) {
                                ComputableLiveData.this.f3163d.set(false);
                                throw th;
                            }
                        }
                        if (z2) {
                            ComputableLiveData.this.f3161b.postValue(obj);
                        }
                        ComputableLiveData.this.f3163d.set(false);
                        z = z2;
                    }
                    if (!z) {
                        return;
                    }
                } while (ComputableLiveData.this.f3162c.get());
            }
        };
        this.f3165f = new Runnable() { // from class: androidx.lifecycle.ComputableLiveData.3
            @Override // java.lang.Runnable
            @MainThread
            public void run() {
                boolean hasActiveObservers = ComputableLiveData.this.f3161b.hasActiveObservers();
                if (ComputableLiveData.this.f3162c.compareAndSet(false, true) && hasActiveObservers) {
                    ComputableLiveData computableLiveData = ComputableLiveData.this;
                    computableLiveData.f3160a.execute(computableLiveData.f3164e);
                }
            }
        };
        this.f3160a = executor;
        this.f3161b = new LiveData<T>() { // from class: androidx.lifecycle.ComputableLiveData.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.lifecycle.LiveData
            public void onActive() {
                ComputableLiveData computableLiveData = ComputableLiveData.this;
                computableLiveData.f3160a.execute(computableLiveData.f3164e);
            }
        };
    }

    @WorkerThread
    protected abstract Object a();

    @NonNull
    public LiveData<T> getLiveData() {
        return this.f3161b;
    }

    public void invalidate() {
        ArchTaskExecutor.getInstance().executeOnMainThread(this.f3165f);
    }
}
