package androidx.camera.camera2.internal.compat;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Handler;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.camera.camera2.internal.compat.CameraDeviceCompat;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.core.util.Preconditions;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
@RequiresApi(21)
/* loaded from: classes.dex */
public class CameraManagerCompatBaseImpl implements CameraManagerCompat.CameraManagerCompatImpl {

    /* renamed from: a  reason: collision with root package name */
    final CameraManager f828a;

    /* renamed from: b  reason: collision with root package name */
    final Object f829b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class CameraManagerCompatParamsApi21 {
        @GuardedBy("mWrapperMap")

        /* renamed from: a  reason: collision with root package name */
        final Map f830a = new HashMap();

        /* renamed from: b  reason: collision with root package name */
        final Handler f831b;

        CameraManagerCompatParamsApi21(@NonNull Handler handler) {
            this.f831b = handler;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CameraManagerCompatBaseImpl(@NonNull Context context, @Nullable Object obj) {
        this.f828a = (CameraManager) context.getSystemService("camera");
        this.f829b = obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CameraManagerCompatBaseImpl a(@NonNull Context context, @NonNull Handler handler) {
        return new CameraManagerCompatBaseImpl(context, new CameraManagerCompatParamsApi21(handler));
    }

    @Override // androidx.camera.camera2.internal.compat.CameraManagerCompat.CameraManagerCompatImpl
    @NonNull
    public CameraCharacteristics getCameraCharacteristics(@NonNull String str) {
        try {
            return this.f828a.getCameraCharacteristics(str);
        } catch (CameraAccessException e2) {
            throw CameraAccessExceptionCompat.toCameraAccessExceptionCompat(e2);
        }
    }

    @Override // androidx.camera.camera2.internal.compat.CameraManagerCompat.CameraManagerCompatImpl
    @NonNull
    public String[] getCameraIdList() {
        try {
            return this.f828a.getCameraIdList();
        } catch (CameraAccessException e2) {
            throw CameraAccessExceptionCompat.toCameraAccessExceptionCompat(e2);
        }
    }

    @Override // androidx.camera.camera2.internal.compat.CameraManagerCompat.CameraManagerCompatImpl
    @NonNull
    public CameraManager getCameraManager() {
        return this.f828a;
    }

    @Override // androidx.camera.camera2.internal.compat.CameraManagerCompat.CameraManagerCompatImpl
    @RequiresPermission("android.permission.CAMERA")
    public void openCamera(@NonNull String str, @NonNull Executor executor, @NonNull CameraDevice.StateCallback stateCallback) {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(stateCallback);
        try {
            this.f828a.openCamera(str, new CameraDeviceCompat.StateCallbackExecutorWrapper(executor, stateCallback), ((CameraManagerCompatParamsApi21) this.f829b).f831b);
        } catch (CameraAccessException e2) {
            throw CameraAccessExceptionCompat.toCameraAccessExceptionCompat(e2);
        }
    }

    @Override // androidx.camera.camera2.internal.compat.CameraManagerCompat.CameraManagerCompatImpl
    public void registerAvailabilityCallback(@NonNull Executor executor, @NonNull CameraManager.AvailabilityCallback availabilityCallback) {
        if (executor == null) {
            throw new IllegalArgumentException("executor was null");
        }
        CameraManagerCompat.AvailabilityCallbackExecutorWrapper availabilityCallbackExecutorWrapper = null;
        CameraManagerCompatParamsApi21 cameraManagerCompatParamsApi21 = (CameraManagerCompatParamsApi21) this.f829b;
        if (availabilityCallback != null) {
            synchronized (cameraManagerCompatParamsApi21.f830a) {
                availabilityCallbackExecutorWrapper = (CameraManagerCompat.AvailabilityCallbackExecutorWrapper) cameraManagerCompatParamsApi21.f830a.get(availabilityCallback);
                if (availabilityCallbackExecutorWrapper == null) {
                    availabilityCallbackExecutorWrapper = new CameraManagerCompat.AvailabilityCallbackExecutorWrapper(executor, availabilityCallback);
                    cameraManagerCompatParamsApi21.f830a.put(availabilityCallback, availabilityCallbackExecutorWrapper);
                }
            }
        }
        this.f828a.registerAvailabilityCallback(availabilityCallbackExecutorWrapper, cameraManagerCompatParamsApi21.f831b);
    }

    @Override // androidx.camera.camera2.internal.compat.CameraManagerCompat.CameraManagerCompatImpl
    public void unregisterAvailabilityCallback(@NonNull CameraManager.AvailabilityCallback availabilityCallback) {
        CameraManagerCompat.AvailabilityCallbackExecutorWrapper availabilityCallbackExecutorWrapper;
        if (availabilityCallback != null) {
            CameraManagerCompatParamsApi21 cameraManagerCompatParamsApi21 = (CameraManagerCompatParamsApi21) this.f829b;
            synchronized (cameraManagerCompatParamsApi21.f830a) {
                availabilityCallbackExecutorWrapper = (CameraManagerCompat.AvailabilityCallbackExecutorWrapper) cameraManagerCompatParamsApi21.f830a.remove(availabilityCallback);
            }
        } else {
            availabilityCallbackExecutorWrapper = null;
        }
        if (availabilityCallbackExecutorWrapper != null) {
            availabilityCallbackExecutorWrapper.a();
        }
        this.f828a.unregisterAvailabilityCallback(availabilityCallbackExecutorWrapper);
    }
}
