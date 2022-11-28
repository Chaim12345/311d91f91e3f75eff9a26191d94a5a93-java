package androidx.car.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
/* loaded from: classes.dex */
public abstract class Session implements LifecycleOwner {
    private final CarContext mCarContext;
    private final LifecycleRegistry mRegistry;
    final LifecycleRegistry mRegistryPublic;

    /* loaded from: classes.dex */
    class LifecycleObserverImpl implements DefaultLifecycleObserver {
        LifecycleObserverImpl() {
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
        public void onCreate(@NonNull LifecycleOwner lifecycleOwner) {
            Session.this.mRegistryPublic.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
        public void onDestroy(@NonNull LifecycleOwner lifecycleOwner) {
            Session.this.mRegistryPublic.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
            lifecycleOwner.getLifecycle().removeObserver(this);
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
        public void onPause(@NonNull LifecycleOwner lifecycleOwner) {
            Session.this.mRegistryPublic.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
        public void onResume(@NonNull LifecycleOwner lifecycleOwner) {
            Session.this.mRegistryPublic.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
        public void onStart(@NonNull LifecycleOwner lifecycleOwner) {
            Session.this.mRegistryPublic.handleLifecycleEvent(Lifecycle.Event.ON_START);
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
        public void onStop(@NonNull LifecycleOwner lifecycleOwner) {
            Session.this.mRegistryPublic.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        }
    }

    public Session() {
        LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
        this.mRegistry = lifecycleRegistry;
        this.mRegistryPublic = new LifecycleRegistry(this);
        lifecycleRegistry.addObserver(new LifecycleObserverImpl());
        this.mCarContext = CarContext.create(lifecycleRegistry);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void configure(@NonNull Context context, @NonNull HandshakeInfo handshakeInfo, @NonNull HostInfo hostInfo, @NonNull ICarHost iCarHost, @NonNull Configuration configuration) {
        this.mCarContext.updateHandshakeInfo(handshakeInfo);
        this.mCarContext.updateHostInfo(hostInfo);
        this.mCarContext.attachBaseContext(context, configuration);
        this.mCarContext.setCarHost(iCarHost);
    }

    @NonNull
    public final CarContext getCarContext() {
        return this.mCarContext;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    @NonNull
    public Lifecycle getLifecycle() {
        return this.mRegistryPublic;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Lifecycle getLifecycleInternal() {
        return this.mRegistry;
    }

    public void onCarConfigurationChanged(@NonNull Configuration configuration) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onCarConfigurationChangedInternal(@NonNull Configuration configuration) {
        this.mCarContext.onCarConfigurationChanged(configuration);
        onCarConfigurationChanged(this.mCarContext.getResources().getConfiguration());
    }

    @NonNull
    public abstract Screen onCreateScreen(@NonNull Intent intent);

    public void onNewIntent(@NonNull Intent intent) {
    }
}
