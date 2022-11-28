package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraDevice;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class CaptureSessionRepository {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    final Executor f703a;

    /* renamed from: b  reason: collision with root package name */
    final Object f704b = new Object();
    @GuardedBy("mLock")

    /* renamed from: c  reason: collision with root package name */
    final Set f705c = new LinkedHashSet();
    @GuardedBy("mLock")

    /* renamed from: d  reason: collision with root package name */
    final Set f706d = new LinkedHashSet();
    @GuardedBy("mLock")

    /* renamed from: e  reason: collision with root package name */
    final Set f707e = new LinkedHashSet();
    private final CameraDevice.StateCallback mCameraStateCallback = new AnonymousClass1();

    /* renamed from: androidx.camera.camera2.internal.CaptureSessionRepository$1  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass1 extends CameraDevice.StateCallback {
        AnonymousClass1() {
        }

        private void cameraClosed() {
            List<SynchronizedCaptureSession> f2;
            synchronized (CaptureSessionRepository.this.f704b) {
                f2 = CaptureSessionRepository.this.f();
                CaptureSessionRepository.this.f707e.clear();
                CaptureSessionRepository.this.f705c.clear();
                CaptureSessionRepository.this.f706d.clear();
            }
            for (SynchronizedCaptureSession synchronizedCaptureSession : f2) {
                synchronizedCaptureSession.finishClose();
            }
        }

        private void forceOnClosedCaptureSessions() {
            final LinkedHashSet linkedHashSet = new LinkedHashSet();
            synchronized (CaptureSessionRepository.this.f704b) {
                linkedHashSet.addAll(CaptureSessionRepository.this.f707e);
                linkedHashSet.addAll(CaptureSessionRepository.this.f705c);
            }
            CaptureSessionRepository.this.f703a.execute(new Runnable() { // from class: androidx.camera.camera2.internal.l0
                @Override // java.lang.Runnable
                public final void run() {
                    CaptureSessionRepository.a(linkedHashSet);
                }
            });
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onClosed(@NonNull CameraDevice cameraDevice) {
            cameraClosed();
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            forceOnClosedCaptureSessions();
            cameraClosed();
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(@NonNull CameraDevice cameraDevice, int i2) {
            forceOnClosedCaptureSessions();
            cameraClosed();
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(@NonNull CameraDevice cameraDevice) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CaptureSessionRepository(@NonNull Executor executor) {
        this.f703a = executor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull Set set) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            SynchronizedCaptureSession synchronizedCaptureSession = (SynchronizedCaptureSession) it.next();
            synchronizedCaptureSession.getStateCallback().onClosed(synchronizedCaptureSession);
        }
    }

    private void forceFinishCloseStaleSessions(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        SynchronizedCaptureSession synchronizedCaptureSession2;
        Iterator it = f().iterator();
        while (it.hasNext() && (synchronizedCaptureSession2 = (SynchronizedCaptureSession) it.next()) != synchronizedCaptureSession) {
            synchronizedCaptureSession2.finishClose();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public CameraDevice.StateCallback b() {
        return this.mCameraStateCallback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public List c() {
        ArrayList arrayList;
        synchronized (this.f704b) {
            arrayList = new ArrayList(this.f705c);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public List d() {
        ArrayList arrayList;
        synchronized (this.f704b) {
            arrayList = new ArrayList(this.f706d);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public List e() {
        ArrayList arrayList;
        synchronized (this.f704b) {
            arrayList = new ArrayList(this.f707e);
        }
        return arrayList;
    }

    @NonNull
    List f() {
        ArrayList arrayList;
        synchronized (this.f704b) {
            arrayList = new ArrayList();
            arrayList.addAll(c());
            arrayList.addAll(e());
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        synchronized (this.f704b) {
            this.f705c.remove(synchronizedCaptureSession);
            this.f706d.remove(synchronizedCaptureSession);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        synchronized (this.f704b) {
            this.f706d.add(synchronizedCaptureSession);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        forceFinishCloseStaleSessions(synchronizedCaptureSession);
        synchronized (this.f704b) {
            this.f707e.remove(synchronizedCaptureSession);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        synchronized (this.f704b) {
            this.f705c.add(synchronizedCaptureSession);
            this.f707e.remove(synchronizedCaptureSession);
        }
        forceFinishCloseStaleSessions(synchronizedCaptureSession);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        synchronized (this.f704b) {
            this.f707e.add(synchronizedCaptureSession);
        }
    }
}
