package androidx.camera.core;

import android.os.Handler;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.CameraFactory;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.internal.TargetConfig;
import java.util.UUID;
import java.util.concurrent.Executor;
import org.apache.commons.cli.HelpFormatter;
/* loaded from: classes.dex */
public final class CameraXConfig implements TargetConfig<CameraX> {

    /* renamed from: a  reason: collision with root package name */
    static final Config.Option f964a = Config.Option.create("camerax.core.appConfig.cameraFactoryProvider", CameraFactory.Provider.class);

    /* renamed from: b  reason: collision with root package name */
    static final Config.Option f965b = Config.Option.create("camerax.core.appConfig.deviceSurfaceManagerProvider", CameraDeviceSurfaceManager.Provider.class);

    /* renamed from: c  reason: collision with root package name */
    static final Config.Option f966c = Config.Option.create("camerax.core.appConfig.useCaseConfigFactoryProvider", UseCaseConfigFactory.Provider.class);

    /* renamed from: d  reason: collision with root package name */
    static final Config.Option f967d = Config.Option.create("camerax.core.appConfig.cameraExecutor", Executor.class);

    /* renamed from: e  reason: collision with root package name */
    static final Config.Option f968e = Config.Option.create("camerax.core.appConfig.schedulerHandler", Handler.class);

    /* renamed from: f  reason: collision with root package name */
    static final Config.Option f969f = Config.Option.create("camerax.core.appConfig.minimumLoggingLevel", Integer.TYPE);

    /* renamed from: g  reason: collision with root package name */
    static final Config.Option f970g = Config.Option.create("camerax.core.appConfig.availableCamerasLimiter", CameraSelector.class);
    private final OptionsBundle mConfig;

    /* loaded from: classes.dex */
    public static final class Builder implements TargetConfig.Builder<CameraX, Builder> {
        private final MutableOptionsBundle mMutableConfig;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder() {
            this(MutableOptionsBundle.create());
        }

        private Builder(MutableOptionsBundle mutableOptionsBundle) {
            this.mMutableConfig = mutableOptionsBundle;
            Class cls = (Class) mutableOptionsBundle.retrieveOption(TargetConfig.OPTION_TARGET_CLASS, null);
            if (cls == null || cls.equals(CameraX.class)) {
                setTargetClass(CameraX.class);
                return;
            }
            throw new IllegalArgumentException("Invalid target class configuration for " + this + ": " + cls);
        }

        @NonNull
        public static Builder fromConfig(@NonNull CameraXConfig cameraXConfig) {
            return new Builder(MutableOptionsBundle.from((Config) cameraXConfig));
        }

        @NonNull
        private MutableConfig getMutableConfig() {
            return this.mMutableConfig;
        }

        @NonNull
        public CameraXConfig build() {
            return new CameraXConfig(OptionsBundle.from(this.mMutableConfig));
        }

        @NonNull
        @ExperimentalAvailableCamerasLimiter
        public Builder setAvailableCamerasLimiter(@NonNull CameraSelector cameraSelector) {
            getMutableConfig().insertOption(CameraXConfig.f970g, cameraSelector);
            return this;
        }

        @NonNull
        public Builder setCameraExecutor(@NonNull Executor executor) {
            getMutableConfig().insertOption(CameraXConfig.f967d, executor);
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setCameraFactoryProvider(@NonNull CameraFactory.Provider provider) {
            getMutableConfig().insertOption(CameraXConfig.f964a, provider);
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setDeviceSurfaceManagerProvider(@NonNull CameraDeviceSurfaceManager.Provider provider) {
            getMutableConfig().insertOption(CameraXConfig.f965b, provider);
            return this;
        }

        @NonNull
        @ExperimentalLogging
        public Builder setMinimumLoggingLevel(@IntRange(from = 3, to = 6) int i2) {
            getMutableConfig().insertOption(CameraXConfig.f969f, Integer.valueOf(i2));
            return this;
        }

        @NonNull
        @ExperimentalCustomizableThreads
        public Builder setSchedulerHandler(@NonNull Handler handler) {
            getMutableConfig().insertOption(CameraXConfig.f968e, handler);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.internal.TargetConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setTargetClass(@NonNull Class<CameraX> cls) {
            getMutableConfig().insertOption(TargetConfig.OPTION_TARGET_CLASS, cls);
            if (getMutableConfig().retrieveOption(TargetConfig.OPTION_TARGET_NAME, null) == null) {
                setTargetName(cls.getCanonicalName() + HelpFormatter.DEFAULT_OPT_PREFIX + UUID.randomUUID());
            }
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.internal.TargetConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setTargetName(@NonNull String str) {
            getMutableConfig().insertOption(TargetConfig.OPTION_TARGET_NAME, str);
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setUseCaseConfigFactoryProvider(@NonNull UseCaseConfigFactory.Provider provider) {
            getMutableConfig().insertOption(CameraXConfig.f966c, provider);
            return this;
        }
    }

    /* loaded from: classes.dex */
    public interface Provider {
        @NonNull
        CameraXConfig getCameraXConfig();
    }

    CameraXConfig(OptionsBundle optionsBundle) {
        this.mConfig = optionsBundle;
    }

    @Nullable
    @ExperimentalAvailableCamerasLimiter
    public CameraSelector getAvailableCamerasLimiter(@Nullable CameraSelector cameraSelector) {
        return (CameraSelector) this.mConfig.retrieveOption(f970g, cameraSelector);
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Executor getCameraExecutor(@Nullable Executor executor) {
        return (Executor) this.mConfig.retrieveOption(f967d, executor);
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CameraFactory.Provider getCameraFactoryProvider(@Nullable CameraFactory.Provider provider) {
        return (CameraFactory.Provider) this.mConfig.retrieveOption(f964a, provider);
    }

    @Override // androidx.camera.core.impl.ReadableConfig
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Config getConfig() {
        return this.mConfig;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CameraDeviceSurfaceManager.Provider getDeviceSurfaceManagerProvider(@Nullable CameraDeviceSurfaceManager.Provider provider) {
        return (CameraDeviceSurfaceManager.Provider) this.mConfig.retrieveOption(f965b, provider);
    }

    @ExperimentalLogging
    public int getMinimumLoggingLevel() {
        return ((Integer) this.mConfig.retrieveOption(f969f, 3)).intValue();
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Handler getSchedulerHandler(@Nullable Handler handler) {
        return (Handler) this.mConfig.retrieveOption(f968e, handler);
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public UseCaseConfigFactory.Provider getUseCaseConfigFactoryProvider(@Nullable UseCaseConfigFactory.Provider provider) {
        return (UseCaseConfigFactory.Provider) this.mConfig.retrieveOption(f966c, provider);
    }
}
