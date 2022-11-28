package androidx.room;

import android.annotation.SuppressLint;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.LiveData;
import androidx.room.InvalidationTracker;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class RoomTrackingLiveData<T> extends LiveData<T> {

    /* renamed from: a  reason: collision with root package name */
    final RoomDatabase f3943a;

    /* renamed from: b  reason: collision with root package name */
    final boolean f3944b;

    /* renamed from: c  reason: collision with root package name */
    final Callable f3945c;

    /* renamed from: d  reason: collision with root package name */
    final InvalidationTracker.Observer f3946d;

    /* renamed from: e  reason: collision with root package name */
    final AtomicBoolean f3947e = new AtomicBoolean(true);

    /* renamed from: f  reason: collision with root package name */
    final AtomicBoolean f3948f = new AtomicBoolean(false);

    /* renamed from: g  reason: collision with root package name */
    final AtomicBoolean f3949g = new AtomicBoolean(false);

    /* renamed from: h  reason: collision with root package name */
    final Runnable f3950h = new Runnable() { // from class: androidx.room.RoomTrackingLiveData.1
        @Override // java.lang.Runnable
        @WorkerThread
        public void run() {
            boolean z;
            if (RoomTrackingLiveData.this.f3949g.compareAndSet(false, true)) {
                RoomTrackingLiveData.this.f3943a.getInvalidationTracker().addWeakObserver(RoomTrackingLiveData.this.f3946d);
            }
            do {
                if (RoomTrackingLiveData.this.f3948f.compareAndSet(false, true)) {
                    Object obj = null;
                    z = false;
                    while (RoomTrackingLiveData.this.f3947e.compareAndSet(true, false)) {
                        try {
                            try {
                                obj = RoomTrackingLiveData.this.f3945c.call();
                                z = true;
                            } catch (Exception e2) {
                                throw new RuntimeException("Exception while computing database live data.", e2);
                            }
                        } finally {
                            RoomTrackingLiveData.this.f3948f.set(false);
                        }
                    }
                    if (z) {
                        RoomTrackingLiveData.this.postValue(obj);
                    }
                } else {
                    z = false;
                }
                if (!z) {
                    return;
                }
            } while (RoomTrackingLiveData.this.f3947e.get());
        }
    };

    /* renamed from: i  reason: collision with root package name */
    final Runnable f3951i = new Runnable() { // from class: androidx.room.RoomTrackingLiveData.2
        @Override // java.lang.Runnable
        @MainThread
        public void run() {
            boolean hasActiveObservers = RoomTrackingLiveData.this.hasActiveObservers();
            if (RoomTrackingLiveData.this.f3947e.compareAndSet(false, true) && hasActiveObservers) {
                RoomTrackingLiveData.this.b().execute(RoomTrackingLiveData.this.f3950h);
            }
        }
    };
    private final InvalidationLiveDataContainer mContainer;

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"RestrictedApi"})
    public RoomTrackingLiveData(RoomDatabase roomDatabase, InvalidationLiveDataContainer invalidationLiveDataContainer, boolean z, Callable callable, String[] strArr) {
        this.f3943a = roomDatabase;
        this.f3944b = z;
        this.f3945c = callable;
        this.mContainer = invalidationLiveDataContainer;
        this.f3946d = new InvalidationTracker.Observer(strArr) { // from class: androidx.room.RoomTrackingLiveData.3
            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                ArchTaskExecutor.getInstance().executeOnMainThread(RoomTrackingLiveData.this.f3951i);
            }
        };
    }

    Executor b() {
        return this.f3944b ? this.f3943a.getTransactionExecutor() : this.f3943a.getQueryExecutor();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.lifecycle.LiveData
    public void onActive() {
        super.onActive();
        this.mContainer.b(this);
        b().execute(this.f3950h);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.lifecycle.LiveData
    public void onInactive() {
        super.onInactive();
        this.mContainer.c(this);
    }
}
