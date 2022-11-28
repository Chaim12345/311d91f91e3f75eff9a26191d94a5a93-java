package com.bumptech.glide.manager;

import android.content.Context;
import androidx.annotation.NonNull;
import com.bumptech.glide.manager.ConnectivityMonitor;
/* loaded from: classes.dex */
final class DefaultConnectivityMonitor implements ConnectivityMonitor {

    /* renamed from: a  reason: collision with root package name */
    final ConnectivityMonitor.ConnectivityListener f4803a;
    private final Context context;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultConnectivityMonitor(@NonNull Context context, @NonNull ConnectivityMonitor.ConnectivityListener connectivityListener) {
        this.context = context.getApplicationContext();
        this.f4803a = connectivityListener;
    }

    private void register() {
        SingletonConnectivityReceiver.a(this.context).b(this.f4803a);
    }

    private void unregister() {
        SingletonConnectivityReceiver.a(this.context).c(this.f4803a);
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onDestroy() {
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStart() {
        register();
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStop() {
        unregister();
    }
}
