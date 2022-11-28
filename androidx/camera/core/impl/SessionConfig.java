package androidx.camera.core.impl;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import androidx.annotation.NonNull;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CaptureConfig;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes.dex */
public final class SessionConfig {
    private final List<CameraDevice.StateCallback> mDeviceStateCallbacks;
    private final List<ErrorListener> mErrorListeners;
    private final CaptureConfig mRepeatingCaptureConfig;
    private final List<CameraCaptureSession.StateCallback> mSessionStateCallbacks;
    private final List<CameraCaptureCallback> mSingleCameraCaptureCallbacks;
    private final List<DeferrableSurface> mSurfaces;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class BaseBuilder {

        /* renamed from: a  reason: collision with root package name */
        final Set f1144a = new HashSet();

        /* renamed from: b  reason: collision with root package name */
        final CaptureConfig.Builder f1145b = new CaptureConfig.Builder();

        /* renamed from: c  reason: collision with root package name */
        final List f1146c = new ArrayList();

        /* renamed from: d  reason: collision with root package name */
        final List f1147d = new ArrayList();

        /* renamed from: e  reason: collision with root package name */
        final List f1148e = new ArrayList();

        /* renamed from: f  reason: collision with root package name */
        final List f1149f = new ArrayList();

        BaseBuilder() {
        }
    }

    /* loaded from: classes.dex */
    public static class Builder extends BaseBuilder {
        @NonNull
        public static Builder createFrom(@NonNull UseCaseConfig<?> useCaseConfig) {
            OptionUnpacker sessionOptionUnpacker = useCaseConfig.getSessionOptionUnpacker(null);
            if (sessionOptionUnpacker != null) {
                Builder builder = new Builder();
                sessionOptionUnpacker.unpack(useCaseConfig, builder);
                return builder;
            }
            throw new IllegalStateException("Implementation is missing option unpacker for " + useCaseConfig.getTargetName(useCaseConfig.toString()));
        }

        public void addAllCameraCaptureCallbacks(@NonNull Collection<CameraCaptureCallback> collection) {
            this.f1145b.addAllCameraCaptureCallbacks(collection);
            this.f1149f.addAll(collection);
        }

        public void addAllDeviceStateCallbacks(@NonNull Collection<CameraDevice.StateCallback> collection) {
            for (CameraDevice.StateCallback stateCallback : collection) {
                addDeviceStateCallback(stateCallback);
            }
        }

        public void addAllRepeatingCameraCaptureCallbacks(@NonNull Collection<CameraCaptureCallback> collection) {
            this.f1145b.addAllCameraCaptureCallbacks(collection);
        }

        public void addAllSessionStateCallbacks(@NonNull List<CameraCaptureSession.StateCallback> list) {
            for (CameraCaptureSession.StateCallback stateCallback : list) {
                addSessionStateCallback(stateCallback);
            }
        }

        public void addCameraCaptureCallback(@NonNull CameraCaptureCallback cameraCaptureCallback) {
            this.f1145b.addCameraCaptureCallback(cameraCaptureCallback);
            this.f1149f.add(cameraCaptureCallback);
        }

        public void addDeviceStateCallback(@NonNull CameraDevice.StateCallback stateCallback) {
            if (this.f1146c.contains(stateCallback)) {
                throw new IllegalArgumentException("Duplicate device state callback.");
            }
            this.f1146c.add(stateCallback);
        }

        public void addErrorListener(@NonNull ErrorListener errorListener) {
            this.f1148e.add(errorListener);
        }

        public void addImplementationOptions(@NonNull Config config) {
            this.f1145b.addImplementationOptions(config);
        }

        public void addNonRepeatingSurface(@NonNull DeferrableSurface deferrableSurface) {
            this.f1144a.add(deferrableSurface);
        }

        public void addRepeatingCameraCaptureCallback(@NonNull CameraCaptureCallback cameraCaptureCallback) {
            this.f1145b.addCameraCaptureCallback(cameraCaptureCallback);
        }

        public void addSessionStateCallback(@NonNull CameraCaptureSession.StateCallback stateCallback) {
            if (this.f1147d.contains(stateCallback)) {
                throw new IllegalArgumentException("Duplicate session state callback.");
            }
            this.f1147d.add(stateCallback);
        }

        public void addSurface(@NonNull DeferrableSurface deferrableSurface) {
            this.f1144a.add(deferrableSurface);
            this.f1145b.addSurface(deferrableSurface);
        }

        public void addTag(@NonNull String str, @NonNull Integer num) {
            this.f1145b.addTag(str, num);
        }

        @NonNull
        public SessionConfig build() {
            return new SessionConfig(new ArrayList(this.f1144a), this.f1146c, this.f1147d, this.f1149f, this.f1148e, this.f1145b.build());
        }

        public void clearSurfaces() {
            this.f1144a.clear();
            this.f1145b.clearSurfaces();
        }

        @NonNull
        public List<CameraCaptureCallback> getSingleCameraCaptureCallbacks() {
            return Collections.unmodifiableList(this.f1149f);
        }

        public void removeSurface(@NonNull DeferrableSurface deferrableSurface) {
            this.f1144a.remove(deferrableSurface);
            this.f1145b.removeSurface(deferrableSurface);
        }

        public void setImplementationOptions(@NonNull Config config) {
            this.f1145b.setImplementationOptions(config);
        }

        public void setTemplateType(int i2) {
            this.f1145b.setTemplateType(i2);
        }
    }

    /* loaded from: classes.dex */
    public interface ErrorListener {
        void onError(@NonNull SessionConfig sessionConfig, @NonNull SessionError sessionError);
    }

    /* loaded from: classes.dex */
    public interface OptionUnpacker {
        void unpack(@NonNull UseCaseConfig<?> useCaseConfig, @NonNull Builder builder);
    }

    /* loaded from: classes.dex */
    public enum SessionError {
        SESSION_ERROR_SURFACE_NEEDS_RESET,
        SESSION_ERROR_UNKNOWN
    }

    /* loaded from: classes.dex */
    public static final class ValidatingBuilder extends BaseBuilder {
        private static final String TAG = "ValidatingBuilder";
        private boolean mValid = true;
        private boolean mTemplateSet = false;

        public void add(@NonNull SessionConfig sessionConfig) {
            CaptureConfig repeatingCaptureConfig = sessionConfig.getRepeatingCaptureConfig();
            if (repeatingCaptureConfig.getTemplateType() != -1) {
                if (!this.mTemplateSet) {
                    this.f1145b.setTemplateType(repeatingCaptureConfig.getTemplateType());
                    this.mTemplateSet = true;
                } else if (this.f1145b.getTemplateType() != repeatingCaptureConfig.getTemplateType()) {
                    Logger.d(TAG, "Invalid configuration due to template type: " + this.f1145b.getTemplateType() + " != " + repeatingCaptureConfig.getTemplateType());
                    this.mValid = false;
                }
            }
            this.f1145b.addAllTags(sessionConfig.getRepeatingCaptureConfig().getTagBundle());
            this.f1146c.addAll(sessionConfig.getDeviceStateCallbacks());
            this.f1147d.addAll(sessionConfig.getSessionStateCallbacks());
            this.f1145b.addAllCameraCaptureCallbacks(sessionConfig.getRepeatingCameraCaptureCallbacks());
            this.f1149f.addAll(sessionConfig.getSingleCameraCaptureCallbacks());
            this.f1148e.addAll(sessionConfig.getErrorListeners());
            this.f1144a.addAll(sessionConfig.getSurfaces());
            this.f1145b.getSurfaces().addAll(repeatingCaptureConfig.getSurfaces());
            if (!this.f1144a.containsAll(this.f1145b.getSurfaces())) {
                Logger.d(TAG, "Invalid configuration due to capture request surfaces are not a subset of surfaces");
                this.mValid = false;
            }
            this.f1145b.addImplementationOptions(repeatingCaptureConfig.getImplementationOptions());
        }

        @NonNull
        public SessionConfig build() {
            if (this.mValid) {
                return new SessionConfig(new ArrayList(this.f1144a), this.f1146c, this.f1147d, this.f1149f, this.f1148e, this.f1145b.build());
            }
            throw new IllegalArgumentException("Unsupported session configuration combination");
        }

        public boolean isValid() {
            return this.mTemplateSet && this.mValid;
        }
    }

    SessionConfig(List list, List list2, List list3, List list4, List list5, CaptureConfig captureConfig) {
        this.mSurfaces = list;
        this.mDeviceStateCallbacks = Collections.unmodifiableList(list2);
        this.mSessionStateCallbacks = Collections.unmodifiableList(list3);
        this.mSingleCameraCaptureCallbacks = Collections.unmodifiableList(list4);
        this.mErrorListeners = Collections.unmodifiableList(list5);
        this.mRepeatingCaptureConfig = captureConfig;
    }

    @NonNull
    public static SessionConfig defaultEmptySessionConfig() {
        return new SessionConfig(new ArrayList(), new ArrayList(0), new ArrayList(0), new ArrayList(0), new ArrayList(0), new CaptureConfig.Builder().build());
    }

    @NonNull
    public List<CameraDevice.StateCallback> getDeviceStateCallbacks() {
        return this.mDeviceStateCallbacks;
    }

    @NonNull
    public List<ErrorListener> getErrorListeners() {
        return this.mErrorListeners;
    }

    @NonNull
    public Config getImplementationOptions() {
        return this.mRepeatingCaptureConfig.getImplementationOptions();
    }

    @NonNull
    public List<CameraCaptureCallback> getRepeatingCameraCaptureCallbacks() {
        return this.mRepeatingCaptureConfig.getCameraCaptureCallbacks();
    }

    @NonNull
    public CaptureConfig getRepeatingCaptureConfig() {
        return this.mRepeatingCaptureConfig;
    }

    @NonNull
    public List<CameraCaptureSession.StateCallback> getSessionStateCallbacks() {
        return this.mSessionStateCallbacks;
    }

    @NonNull
    public List<CameraCaptureCallback> getSingleCameraCaptureCallbacks() {
        return this.mSingleCameraCaptureCallbacks;
    }

    @NonNull
    public List<DeferrableSurface> getSurfaces() {
        return Collections.unmodifiableList(this.mSurfaces);
    }

    public int getTemplateType() {
        return this.mRepeatingCaptureConfig.getTemplateType();
    }
}
