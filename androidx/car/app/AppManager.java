package androidx.car.app;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.AppManager;
import androidx.car.app.IAppManager;
import androidx.car.app.managers.Manager;
import androidx.car.app.utils.RemoteUtils;
import androidx.lifecycle.Lifecycle;
import java.util.Objects;
/* loaded from: classes.dex */
public class AppManager implements Manager {
    @NonNull
    private final IAppManager.Stub mAppManager;
    @NonNull
    private final CarContext mCarContext;
    @NonNull
    private final HostDispatcher mHostDispatcher;
    @NonNull
    private final Lifecycle mLifecycle;

    /* renamed from: androidx.car.app.AppManager$1  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass1 extends IAppManager.Stub {
        final /* synthetic */ CarContext val$carContext;

        AnonymousClass1(CarContext carContext) {
            this.val$carContext = carContext;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ Object lambda$onBackPressed$0(CarContext carContext) {
            carContext.getOnBackPressedDispatcher().onBackPressed();
            return null;
        }

        @Override // androidx.car.app.IAppManager
        public void getTemplate(IOnDoneCallback iOnDoneCallback) {
            Lifecycle lifecycle = AppManager.this.getLifecycle();
            final ScreenManager screenManager = (ScreenManager) AppManager.this.getCarContext().getCarService(ScreenManager.class);
            Objects.requireNonNull(screenManager);
            RemoteUtils.dispatchCallFromHost(lifecycle, iOnDoneCallback, "getTemplate", new RemoteUtils.HostCall() { // from class: androidx.car.app.e
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    return ScreenManager.this.getTopTemplate();
                }
            });
        }

        @Override // androidx.car.app.IAppManager
        public void onBackPressed(IOnDoneCallback iOnDoneCallback) {
            Lifecycle lifecycle = AppManager.this.getLifecycle();
            final CarContext carContext = this.val$carContext;
            RemoteUtils.dispatchCallFromHost(lifecycle, iOnDoneCallback, "onBackPressed", new RemoteUtils.HostCall() { // from class: androidx.car.app.d
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onBackPressed$0;
                    lambda$onBackPressed$0 = AppManager.AnonymousClass1.lambda$onBackPressed$0(CarContext.this);
                    return lambda$onBackPressed$0;
                }
            });
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected AppManager(@NonNull CarContext carContext, @NonNull HostDispatcher hostDispatcher, @NonNull Lifecycle lifecycle) {
        this.mCarContext = carContext;
        this.mHostDispatcher = hostDispatcher;
        this.mLifecycle = lifecycle;
        this.mAppManager = new AnonymousClass1(carContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AppManager create(@NonNull CarContext carContext, @NonNull HostDispatcher hostDispatcher, @NonNull Lifecycle lifecycle) {
        Objects.requireNonNull(carContext);
        Objects.requireNonNull(hostDispatcher);
        Objects.requireNonNull(lifecycle);
        return new AppManager(carContext, hostDispatcher, lifecycle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$setSurfaceCallback$0(SurfaceCallback surfaceCallback, IAppHost iAppHost) {
        iAppHost.setSurfaceCallback(RemoteUtils.stubSurfaceCallback(this.mLifecycle, surfaceCallback));
        return null;
    }

    @NonNull
    CarContext getCarContext() {
        return this.mCarContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IAppManager.Stub getIInterface() {
        return this.mAppManager;
    }

    @NonNull
    Lifecycle getLifecycle() {
        return this.mLifecycle;
    }

    public void invalidate() {
        this.mHostDispatcher.dispatch(CarContext.APP_SERVICE, "invalidate", c.f1457a);
    }

    @SuppressLint({"ExecutorRegistration"})
    public void setSurfaceCallback(@Nullable final SurfaceCallback surfaceCallback) {
        this.mHostDispatcher.dispatch(CarContext.APP_SERVICE, "setSurfaceListener", new HostCall() { // from class: androidx.car.app.a
            @Override // androidx.car.app.HostCall
            public final Object dispatch(Object obj) {
                Object lambda$setSurfaceCallback$0;
                lambda$setSurfaceCallback$0 = AppManager.this.lambda$setSurfaceCallback$0(surfaceCallback, (IAppHost) obj);
                return lambda$setSurfaceCallback$0;
            }
        });
    }

    public void showToast(@NonNull final CharSequence charSequence, final int i2) {
        Objects.requireNonNull(charSequence);
        this.mHostDispatcher.dispatch(CarContext.APP_SERVICE, "showToast", new HostCall() { // from class: androidx.car.app.b
            @Override // androidx.car.app.HostCall
            public final Object dispatch(Object obj) {
                Object showToast;
                showToast = ((IAppHost) obj).showToast(charSequence, i2);
                return showToast;
            }
        });
    }
}
