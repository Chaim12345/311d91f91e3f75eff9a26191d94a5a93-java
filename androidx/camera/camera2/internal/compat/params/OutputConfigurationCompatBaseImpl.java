package androidx.camera.camera2.internal.compat.params;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Size;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat;
import androidx.camera.core.Logger;
import androidx.core.util.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
@RequiresApi(21)
/* loaded from: classes.dex */
public class OutputConfigurationCompatBaseImpl implements OutputConfigurationCompat.OutputConfigurationCompatImpl {

    /* renamed from: a  reason: collision with root package name */
    final Object f837a;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class OutputConfigurationParamsApi21 {
        private static final String DETECT_SURFACE_TYPE_METHOD = "detectSurfaceType";
        private static final String GET_GENERATION_ID_METHOD = "getGenerationId";
        private static final String GET_SURFACE_SIZE_METHOD = "getSurfaceSize";
        private static final String LEGACY_CAMERA_DEVICE_CLASS = "android.hardware.camera2.legacy.LegacyCameraDevice";

        /* renamed from: a  reason: collision with root package name */
        final List f838a;

        /* renamed from: b  reason: collision with root package name */
        final Size f839b;

        /* renamed from: c  reason: collision with root package name */
        final int f840c;

        /* renamed from: d  reason: collision with root package name */
        final int f841d;
        @Nullable

        /* renamed from: e  reason: collision with root package name */
        String f842e;

        /* renamed from: f  reason: collision with root package name */
        boolean f843f = false;

        OutputConfigurationParamsApi21(@NonNull Surface surface) {
            Preconditions.checkNotNull(surface, "Surface must not be null");
            this.f838a = Collections.singletonList(surface);
            this.f839b = getSurfaceSize(surface);
            this.f840c = getSurfaceFormat(surface);
            this.f841d = getSurfaceGenerationId(surface);
        }

        @SuppressLint({"BlockedPrivateApi"})
        private static int getSurfaceFormat(@NonNull Surface surface) {
            try {
                Method declaredMethod = Class.forName(LEGACY_CAMERA_DEVICE_CLASS).getDeclaredMethod(DETECT_SURFACE_TYPE_METHOD, Surface.class);
                if (Build.VERSION.SDK_INT < 22) {
                    declaredMethod.setAccessible(true);
                }
                return ((Integer) declaredMethod.invoke(null, surface)).intValue();
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
                Logger.e("OutputConfigCompat", "Unable to retrieve surface format.", e2);
                return 0;
            }
        }

        @SuppressLint({"SoonBlockedPrivateApi"})
        private static int getSurfaceGenerationId(@NonNull Surface surface) {
            try {
                return ((Integer) Surface.class.getDeclaredMethod(GET_GENERATION_ID_METHOD, new Class[0]).invoke(surface, new Object[0])).intValue();
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
                Logger.e("OutputConfigCompat", "Unable to retrieve surface generation id.", e2);
                return -1;
            }
        }

        @SuppressLint({"BlockedPrivateApi"})
        private static Size getSurfaceSize(@NonNull Surface surface) {
            try {
                Method declaredMethod = Class.forName(LEGACY_CAMERA_DEVICE_CLASS).getDeclaredMethod(GET_SURFACE_SIZE_METHOD, Surface.class);
                declaredMethod.setAccessible(true);
                return (Size) declaredMethod.invoke(null, surface);
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
                Logger.e("OutputConfigCompat", "Unable to retrieve surface size.", e2);
                return null;
            }
        }

        public boolean equals(Object obj) {
            if (obj instanceof OutputConfigurationParamsApi21) {
                OutputConfigurationParamsApi21 outputConfigurationParamsApi21 = (OutputConfigurationParamsApi21) obj;
                if (this.f839b.equals(outputConfigurationParamsApi21.f839b) && this.f840c == outputConfigurationParamsApi21.f840c && this.f841d == outputConfigurationParamsApi21.f841d && this.f843f == outputConfigurationParamsApi21.f843f && Objects.equals(this.f842e, outputConfigurationParamsApi21.f842e)) {
                    int min = Math.min(this.f838a.size(), outputConfigurationParamsApi21.f838a.size());
                    for (int i2 = 0; i2 < min; i2++) {
                        if (this.f838a.get(i2) != outputConfigurationParamsApi21.f838a.get(i2)) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            int hashCode = this.f838a.hashCode() ^ 31;
            int i2 = this.f841d ^ ((hashCode << 5) - hashCode);
            int hashCode2 = this.f839b.hashCode() ^ ((i2 << 5) - i2);
            int i3 = this.f840c ^ ((hashCode2 << 5) - hashCode2);
            int i4 = (this.f843f ? 1 : 0) ^ ((i3 << 5) - i3);
            int i5 = (i4 << 5) - i4;
            String str = this.f842e;
            return (str == null ? 0 : str.hashCode()) ^ i5;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OutputConfigurationCompatBaseImpl(@NonNull Surface surface) {
        this.f837a = new OutputConfigurationParamsApi21(surface);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OutputConfigurationCompatBaseImpl(@NonNull Object obj) {
        this.f837a = obj;
    }

    boolean a() {
        return ((OutputConfigurationParamsApi21) this.f837a).f843f;
    }

    @Override // androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.OutputConfigurationCompatImpl
    public void addSurface(@NonNull Surface surface) {
        Preconditions.checkNotNull(surface, "Surface must not be null");
        if (getSurface() == surface) {
            throw new IllegalStateException("Surface is already added!");
        }
        if (!a()) {
            throw new IllegalStateException("Cannot have 2 surfaces for a non-sharing configuration");
        }
        throw new IllegalArgumentException("Exceeds maximum number of surfaces");
    }

    @Override // androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.OutputConfigurationCompatImpl
    public void enableSurfaceSharing() {
        ((OutputConfigurationParamsApi21) this.f837a).f843f = true;
    }

    public boolean equals(Object obj) {
        if (obj instanceof OutputConfigurationCompatBaseImpl) {
            return Objects.equals(this.f837a, ((OutputConfigurationCompatBaseImpl) obj).f837a);
        }
        return false;
    }

    @Override // androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.OutputConfigurationCompatImpl
    public int getMaxSharedSurfaceCount() {
        return 1;
    }

    @Override // androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.OutputConfigurationCompatImpl
    @Nullable
    public Object getOutputConfiguration() {
        return null;
    }

    @Override // androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.OutputConfigurationCompatImpl
    @Nullable
    public String getPhysicalCameraId() {
        return ((OutputConfigurationParamsApi21) this.f837a).f842e;
    }

    @Override // androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.OutputConfigurationCompatImpl
    @Nullable
    public Surface getSurface() {
        List list = ((OutputConfigurationParamsApi21) this.f837a).f838a;
        if (list.size() == 0) {
            return null;
        }
        return (Surface) list.get(0);
    }

    @Override // androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.OutputConfigurationCompatImpl
    public int getSurfaceGroupId() {
        return -1;
    }

    @Override // androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.OutputConfigurationCompatImpl
    @NonNull
    public List<Surface> getSurfaces() {
        return ((OutputConfigurationParamsApi21) this.f837a).f838a;
    }

    public int hashCode() {
        return this.f837a.hashCode();
    }

    @Override // androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.OutputConfigurationCompatImpl
    public void removeSurface(@NonNull Surface surface) {
        if (getSurface() != surface) {
            throw new IllegalArgumentException("Surface is not part of this output configuration");
        }
        throw new IllegalArgumentException("Cannot remove surface associated with this output configuration");
    }

    @Override // androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.OutputConfigurationCompatImpl
    public void setPhysicalCameraId(@Nullable String str) {
        ((OutputConfigurationParamsApi21) this.f837a).f842e = str;
    }
}
