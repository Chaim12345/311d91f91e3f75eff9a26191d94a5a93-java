package androidx.camera.view;

import android.content.Context;
import android.view.OrientationEventListener;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.view.RotationProvider;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class RotationProvider {
    @NonNull
    @GuardedBy("mLock")
    @VisibleForTesting

    /* renamed from: b  reason: collision with root package name */
    final OrientationEventListener f1390b;
    @Nullable
    @GuardedBy("mLock")

    /* renamed from: c  reason: collision with root package name */
    Executor f1391c;
    @Nullable
    @GuardedBy("mLock")

    /* renamed from: d  reason: collision with root package name */
    Listener f1392d;

    /* renamed from: a  reason: collision with root package name */
    final Object f1389a = new Object();
    @VisibleForTesting

    /* renamed from: e  reason: collision with root package name */
    boolean f1393e = false;

    /* renamed from: androidx.camera.view.RotationProvider$1  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass1 extends OrientationEventListener {
        private static final int INVALID_SURFACE_ROTATION = -1;
        private int mRotation;

        AnonymousClass1(Context context) {
            super(context);
            this.mRotation = -1;
        }

        @Override // android.view.OrientationEventListener
        public void onOrientationChanged(int i2) {
            final int a2;
            Executor executor;
            final Listener listener;
            if (i2 == -1 || this.mRotation == (a2 = RotationProvider.a(i2))) {
                return;
            }
            this.mRotation = a2;
            synchronized (RotationProvider.this.f1389a) {
                RotationProvider rotationProvider = RotationProvider.this;
                executor = rotationProvider.f1391c;
                listener = rotationProvider.f1392d;
            }
            if (executor == null || listener == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.camera.view.m
                @Override // java.lang.Runnable
                public final void run() {
                    RotationProvider.Listener.this.onRotationChanged(a2);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public interface Listener {
        void onRotationChanged(int i2);
    }

    public RotationProvider(@NonNull Context context) {
        this.f1390b = new AnonymousClass1(context);
    }

    @VisibleForTesting
    static int a(int i2) {
        if (i2 >= 315 || i2 < 45) {
            return 0;
        }
        if (i2 >= 225) {
            return 1;
        }
        return i2 >= 135 ? 2 : 3;
    }

    public void clearListener() {
        synchronized (this.f1389a) {
            this.f1390b.disable();
            this.f1391c = null;
            this.f1392d = null;
        }
    }

    public boolean setListener(@NonNull Listener listener) {
        return setListener(CameraXExecutors.mainThreadExecutor(), listener);
    }

    public boolean setListener(@NonNull Executor executor, @NonNull Listener listener) {
        synchronized (this.f1389a) {
            if (this.f1390b.canDetectOrientation() || this.f1393e) {
                this.f1391c = executor;
                this.f1392d = listener;
                this.f1390b.enable();
                return true;
            }
            return false;
        }
    }
}
